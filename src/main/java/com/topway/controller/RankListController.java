package com.topway.controller;

import com.topway.VO.RankListShowVO;
import com.topway.VO.ResultVO;
import com.topway.dto.RankListFilterDTO;
import com.topway.dto.RankListShowGridDTO;
import com.topway.dto.RankListShowPersonDTO;
import com.topway.dto.UserRoleDTO;
import com.topway.enums.ResultEnum;
import com.topway.form.RankListForm;
import com.topway.service.Impl.RankListServiceImpl;
import com.topway.utils.ResultVOUtil;
import com.topway.utils.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/6/5.
 */
@RestController
@RequestMapping("/rank")
@Slf4j
public class RankListController {


    @Autowired
    RankListServiceImpl rankListService;

//    RankListShowBranchDTO rankListShowBranchDTO = new RankListShowBranchDTO();


    @PostMapping("/filter")
    public ResultVO rankFilter(HttpServletRequest httpServletRequest){

        log.info("【排行榜筛选】-------------------------------------------------------");

        /** 1.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        RankListFilterDTO rankListFilterDTO = rankListService.rankListFilter(userRoleDTO);
        if (rankListFilterDTO.getBranch().equals("") || rankListFilterDTO.getStation().equals("")){
            return ResultVOUtil.error(ResultEnum.USER_GRID_MSG_ERROR.getCode(),
                    ResultEnum.USER_GRID_MSG_ERROR.getDesc());
        }else {

            return ResultVOUtil.success(rankListService.rankListFilter(userRoleDTO));
        }
    }



    @PostMapping("/list")
    public ResultVO rankList(@Valid @RequestBody RankListForm rankListForm,
                             BindingResult bindingResult,
                             HttpServletRequest httpServletRequest){

        log.info("【排行榜展示】-------------------------------------------------------");

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,rankListForm={}", rankListForm.toString());
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        final String BRANCHORSTATION = rankListForm.getBranchOrStation();
        final String GRIDORPERSON = rankListForm.getGridOrPerson();

        /** 2.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        /** 3.计算各个排行 */
        RankListShowVO rankListShowDTO = new RankListShowVO();

        List<RankListShowPersonDTO> rank1List = new ArrayList<>();
        List rank2List = new ArrayList<>();
        List rank3List = new ArrayList<>();
        List rank4List = new ArrayList<>();
        List rank5List = new ArrayList<>();
        List rank6List = new ArrayList<>();
        List<RankListShowGridDTO> rank7List = new ArrayList<>();
        List<RankListShowGridDTO> rank8List = new ArrayList<>();
        List<RankListShowGridDTO> rank9List = new ArrayList<>();
        List<RankListShowGridDTO> rank10List = new ArrayList<>();
        List<RankListShowGridDTO> rank11List = new ArrayList<>();


        // 营销生效额
        log.info("【排行榜】营销生效额排行");
        rank1List = rankListService.findTop1(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 24安装处理成功率(数字+宽带)
        log.info("【排行榜】24安装处理成功率(数字+宽带)");
        rank2List = rankListService.findTop2(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 48安装处理成功率(数字+宽带)
        log.info("【排行榜】48安装处理成功率(数字+宽带)");
        rank3List = rankListService.findTop3(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 故障及时处理成功率(数字)
        log.info("【排行榜】故障及时处理成功率(数字)");
        rank4List = rankListService.findTop4(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 故障及时处理成功率(宽带)
        log.info("【排行榜】故障及时处理成功率(宽带)");
        rank5List = rankListService.findTop5(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 故障单预约规范率
        log.info("【排行榜】故障单预约规范率");
        rank6List = rankListService.findTop6(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 数字电视用户流失数/率
        log.info("【排行榜】数字电视用户流失数/率");
        rank7List = rankListService.findTop7(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 20M宽带流失数/率
        log.info("【排行榜】20M宽带流失数/率");
        rank8List = rankListService.findTop8(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 100M宽带流失数/率
        log.info("【排行榜】100M宽带流失数/率");
        rank9List = rankListService.findTop9(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 网格保障率(网格内上月top5小区)
        log.info("【排行榜】网格保障率(网格内上月top5小区)");
        rank10List = rankListService.findTop10(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        // 重复故障率
        log.info("【排行榜】重复故障率");
        rank11List = rankListService.findTop11(userRoleDTO, BRANCHORSTATION, GRIDORPERSON);

        /** 4.拼接json */
        rankListShowDTO.setF1(rank1List);
        rankListShowDTO.setF2(rank2List);
        rankListShowDTO.setF3(rank3List);
        rankListShowDTO.setF4(rank4List);
        rankListShowDTO.setF5(rank5List);
        rankListShowDTO.setF6(rank6List);
        rankListShowDTO.setF7(rank7List);
        rankListShowDTO.setF8(rank8List);
        rankListShowDTO.setF9(rank9List);
        rankListShowDTO.setF10(rank10List);
        rankListShowDTO.setF11(rank11List);

        return ResultVOUtil.success(rankListShowDTO);
    }
}
