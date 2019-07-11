package com.topway.controller;

import com.topway.VO.ResultVO;
import com.topway.VO.WarningVO;
import com.topway.dto.UserRoleDTO;
import com.topway.enums.ResultEnum;
import com.topway.form.LoginForm;
import com.topway.service.Impl.WarningServiceImpl;
import com.topway.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by haizhi on 2019/6/25.
 */
@RestController
@RequestMapping("/warning")
@Slf4j
public class WarningController {

    @Autowired
    WarningServiceImpl service;


    @PostMapping("/list")
    public ResultVO warningList(@Valid @RequestBody LoginForm loginForm,
                                BindingResult bindingResult) {

        log.info("【预警数据接口】-------------------------------------------------------");

        final String USERID = loginForm.getUserid();
        final String USERNAME = loginForm.getUsername();
        final String US_ID = loginForm.getUs_id();
        final String SPCODE = loginForm.getOperator();
        final String BRANCH = loginForm.getBranch();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,deviceNoForm={}", loginForm.toString());
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.识别用户身份,判断权限 */
//        UserAloneAuthentication userAloneAuthentication = new UserAloneAuthentication();
        UserRoleDTO userRoleDTO = service.authentication(loginForm);
        if (userRoleDTO==null){
            return ResultVOUtil.error(ResultEnum.USER_NOT_FOUND.getCode(),
                    ResultEnum.USER_NOT_FOUND.getDesc());
        }

        /** 3.拼接WarningVO */
        WarningVO warningVO = new WarningVO();

        List<Double> WatchLossNumAndWbLossNumList = service.WatchLossNumAndWbLossNum(userRoleDTO);

        // 数字电视终端流失数
        warningVO.setWatchLossNum(WatchLossNumAndWbLossNumList.get(0));

        // 宽带终端流失数
        warningVO.setWbLossNum(WatchLossNumAndWbLossNumList.get(1));

        // 24小时安装处理成功率
        warningVO.setInstallSuccess24h(service.InstallSuccess24h(userRoleDTO));

        // 假单数
        warningVO.setFaultOrderNum(0.0);

        // 服务投诉数
        warningVO.setServiceComplaintNum(0.0);

        // 营销生效额
        warningVO.setMarketEffectiveNum(service.MarketEffectNum(userRoleDTO));

        if (userRoleDTO.getUserRole().equals("站长")){
            // 片区故障平均处理时长
            warningVO.setRegionTimeLength(service.RegionAverageNum(userRoleDTO));
        }

        if (userRoleDTO.getUserRole().equals("基础网格员")){
            // 故障及时处理成功率
            warningVO.setFaultSuccess(service.RegionFaultSuccess(userRoleDTO));
        }


        return ResultVOUtil.success(warningVO);

    }
}