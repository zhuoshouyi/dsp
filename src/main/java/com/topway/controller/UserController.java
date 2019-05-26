package com.topway.controller;

import com.topway.VO.ResultVO;
import com.topway.dto.*;
import com.topway.enums.ResultEnum;
import com.topway.exception.ParamException;
import com.topway.form.CustomerIdForm;
import com.topway.form.DeviceNoForm;
import com.topway.form.UserListForm;
import com.topway.service.Impl.UserServiceImpl;
import com.topway.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haizhi on 2019/5/23.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;

    /**
     * 客户搜索列表接口
     *
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(@RequestBody UserListForm userListForm,
                         BindingResult bindingResult){

        // 参数1,搜索的关键字
        final String KEYWORD = userListForm.getKeyword();
        // 参数2,搜索的类型
        final String SEARCHTYPE = userListForm.getSearchType();
        // 参数3,查看的页数
        final int PAGENO = userListForm.getPageNo();
        // 参数4,每页的个数
        final int PAGESIZE = userListForm.getPageSize();

        // 搜索结果数据总条数
        long total=0L;

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的 keyword 或 searchType 有误,userListForm={}", userListForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }


        PageRequest pageRequest = new PageRequest(PAGENO, PAGESIZE);
        Page<CustomerDTO> customerDTOPage;
        /** 2.判断用户是使用什么类型搜索的 */
        switch (SEARCHTYPE){
            // 资源号查询
            case "deviceNo":
                log.info("【资源号查询】查询资源号为" + KEYWORD);
                customerDTOPage = userService.findByDeviceNoLike(KEYWORD, pageRequest);
                total = customerDTOPage.getTotalElements();
                break;

            // 电话号码查询
            case "phone":
                log.info("【电话号码查询】查询电话号码为" + KEYWORD);
                customerDTOPage = userService.findByPhoneLike(KEYWORD, pageRequest);
                total = customerDTOPage.getTotalElements();
                break;

            // 客户名称查询
            case "customerName":
                log.info("【客户名称查询】查询名称为" + KEYWORD);
                customerDTOPage = userService.findByCustomerNameLike(KEYWORD, pageRequest);
                total = customerDTOPage.getTotalElements();
                break;

            // 客户编码查询
            case "customerId":
                log.info("【客户编码查询】查询编码为" + KEYWORD);
                customerDTOPage = userService.findByCustomerIdLike(KEYWORD, pageRequest);
                total = customerDTOPage.getTotalElements();
                break;

            // 错误参数
            default:
                log.error("【参数不正确】传入的参数不是四种搜索类型中的一种,无法识别");
                throw new ParamException(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getDesc());
        }

        return ResultVOUtil.successPage(customerDTOPage.getContent(), PAGENO, PAGESIZE, total);
    }


    /**
     * 客户详情查看接口
     *
     * @param customerIdForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/detail")
    public ResultVO detail(@RequestBody CustomerIdForm customerIdForm,
                           BindingResult bindingResult){

        final String CUSTOMERID = customerIdForm.getCustomerId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的 customerId 有误,customerIdForm={}", customerIdForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.通过客户编码查找客户信息 */
        CustomerDTO customerDTO = userService.findByCustomerId(CUSTOMERID);

        return ResultVOUtil.success(customerDTO);

    }


    /**
     * 终端信息
     *
     * @param deviceNoForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/device/list")
    public ResultVO deviceList(@RequestBody DeviceNoForm deviceNoForm,
                               BindingResult bindingResult){

        final String CUSTOMERID = deviceNoForm.getCustomerId();
        final String DEVICENO = deviceNoForm.getDeviceNo();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的 deviceNo 有误,deviceNoForm={}", deviceNoForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.通过资源号查找终端信息 */
        DeviceDTO deviceDTO = new DeviceDTO();

        // 终端信息
        DeviceBasicInfoDTO deviceBasicInfoDTO = userService.findDeviceBasicInfoDTO(CUSTOMERID, DEVICENO);

        // 业务基本信息
        List<DeviceBusinessInfoDTO> deviceBusinessInfoDTO = userService.findDeviceBusinessInfoDTO(CUSTOMERID, DEVICENO);

        // 工单信息
        List<DeviceWorkOrderDTO> deviceWorkOrderDTO = userService.findDeviceWorkOrderDTO(CUSTOMERID, DEVICENO);

        // 投诉单信息
        DeviceComplaintDTO deviceComplaintDTO = new DeviceComplaintDTO();

        // 收视行为信息
        DeviceWatchActionDTO deviceWatchActionDTO = new DeviceWatchActionDTO();

        // 网络质量诊断信息
        DeviceNetworkQualityDTO deviceNetworkQualityDTO = new DeviceNetworkQualityDTO();

        /** 3.拼装deviceDTO */
        deviceDTO.setBasicInfo(deviceBasicInfoDTO);
        deviceDTO.setBusinessInfo(deviceBusinessInfoDTO);
        deviceDTO.setWorkOrder(deviceWorkOrderDTO);
        deviceDTO.setComplaint(deviceComplaintDTO);
        deviceDTO.setWatchAction(deviceWatchActionDTO);
        deviceDTO.setNetworkQuality(deviceNetworkQualityDTO);

        return ResultVOUtil.success(deviceDTO);
    }


}
