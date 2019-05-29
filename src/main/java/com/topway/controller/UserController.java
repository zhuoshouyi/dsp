package com.topway.controller;

import com.topway.VO.ResultVO;
import com.topway.dto.*;
import com.topway.enums.ResultEnum;
import com.topway.exception.ParamException;
import com.topway.form.*;
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

import java.text.SimpleDateFormat;
import java.util.Date;
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

    // 全局统一时间格式化格式
    SimpleDateFormat FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


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
            log.error("【参数错误】传入的参数有误,deviceNoForm={}", deviceNoForm.toString());
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
        List<DeviceComplaintDTO> deviceComplaintDTO = userService.findDeviceComplaintDTO(CUSTOMERID, DEVICENO);

        // 收视行为信息
        DeviceWatchActionDTO deviceWatchActionDTO = userService.findDeviceWatchActionDTO(CUSTOMERID, DEVICENO);

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





    /**
     * 终端业务详情查看
     *
     * @param deviceBusinessForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/device/business")
    public ResultVO deviceBusiness(@RequestBody DeviceBusinessForm deviceBusinessForm,
                                   BindingResult bindingResult){

        final String CUSTOMERID = deviceBusinessForm.getCustomerId();
        final String DEVICENO = deviceBusinessForm.getDeviceNo();
        final String ID = deviceBusinessForm.getId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,deviceBusinessForm={}", deviceBusinessForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.查找产品信息 */
        DeviceBusinessInfoDetailDTO deviceBusinessInfoDetailDTO =
                userService.findDeviceBusinessInfoDetailDTO(CUSTOMERID, DEVICENO, ID);

        return ResultVOUtil.success(deviceBusinessInfoDetailDTO);
    }





    /**
     * 工单信息详情查看
     *
     * @param deviceOrderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/device/order")
    public ResultVO deviceOrder(@RequestBody DeviceOrderForm deviceOrderForm,
                                   BindingResult bindingResult){

        final String CUSTOMERID = deviceOrderForm.getCustomerId();
        final String DEVICENO = deviceOrderForm.getDeviceNo();
        final String ID = deviceOrderForm.getId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,deviceBusinessForm={}", deviceOrderForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.查找工单信息 */
        DeviceWorkOrderDetailDTO deviceWorkOrderDetailDTO = userService.findDeviceWorkOrderDetailDTO(CUSTOMERID, DEVICENO, ID);

        return ResultVOUtil.success(deviceWorkOrderDetailDTO);
    }





    /**
     * 投诉单信息详情查看
     *
     * @param deviceComplaintForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/device/complaint")
    public ResultVO deviceComplaint(@RequestBody DeviceComplaintForm deviceComplaintForm,
                                   BindingResult bindingResult){

        final String CUSTOMERID = deviceComplaintForm.getCustomerId();
        final String DEVICENO = deviceComplaintForm.getDeviceNo();
        final String ID = deviceComplaintForm.getId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,deviceBusinessForm={}", deviceComplaintForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.查找投诉单信息 */
        DeviceComplaintDetailDTO deviceComplaintDetailDTO = new DeviceComplaintDetailDTO();
        // TODO

        return ResultVOUtil.success(deviceComplaintDetailDTO);
    }






    /**
     * 投诉处理信息详情查看
     *
     * @param deviceComplaintDetailForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/device/complaint/detail")
    public ResultVO deviceComplaint(@RequestBody DeviceComplaintDetailForm deviceComplaintDetailForm,
                                    BindingResult bindingResult){

        final String CUSTOMERID = deviceComplaintDetailForm.getCustomerId();
        final String DEVICENO = deviceComplaintDetailForm.getDeviceNo();
        final String ID = deviceComplaintDetailForm.getId();
        final String DETAILID = deviceComplaintDetailForm.getDetailId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,deviceBusinessForm={}", deviceComplaintDetailForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.查找投诉单处理信息 */
        DeviceComplaintDetailDTO deviceComplaintDetailDTO = new DeviceComplaintDetailDTO();
        // TODO

        return ResultVOUtil.success(deviceComplaintDetailDTO);
    }















    /**
     * 产品滑动
     *
     * @param deviceSlideForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/device/businessSlide")
    public ResultVO deviceBusinessSlide(@RequestBody DeviceSlideForm deviceSlideForm,
                                        BindingResult bindingResult){

        final String CUSTOMERID = deviceSlideForm.getCustomerId();
        final String DEVICENO = deviceSlideForm.getDeviceNo();
        final int PAGENO = deviceSlideForm.getPageNo();
        final int PAGESIZE = deviceSlideForm.getPageSize();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,deviceSlideForm={}", deviceSlideForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.查找产品信息 */
        PageRequest pageRequest = new PageRequest(PAGENO, PAGESIZE);
        Page<DeviceBusinessInfoDTO> deviceBusinessInfoDTOPage = userService.findBusinessSlide(CUSTOMERID, DEVICENO, pageRequest);
        long total = deviceBusinessInfoDTOPage.getTotalElements();

        return ResultVOUtil.successPage(deviceBusinessInfoDTOPage.getContent(), PAGENO, PAGESIZE, total);
    }


    /**
     * 工单滑动
     *
     * @param deviceSlideForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/device/orderSlide")
    public ResultVO deviceOrderSlide(@RequestBody DeviceSlideForm deviceSlideForm,
                                        BindingResult bindingResult){

        final String CUSTOMERID = deviceSlideForm.getCustomerId();
        final String DEVICENO = deviceSlideForm.getDeviceNo();
        final int PAGENO = deviceSlideForm.getPageNo();
        final int PAGESIZE = deviceSlideForm.getPageSize();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,deviceSlideForm={}", deviceSlideForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.查找工单信息 */
        PageRequest pageRequest = new PageRequest(PAGENO, PAGESIZE);
        Page<DeviceWorkOrderDTO> deviceWorkOrderDTOPage = userService.findWorkOrderSlide(CUSTOMERID, DEVICENO, pageRequest);
        long total = deviceWorkOrderDTOPage.getTotalElements();

        return ResultVOUtil.successPage(deviceWorkOrderDTOPage.getContent(), PAGENO, PAGESIZE, total);
    }


    /**
     * 投诉单滑动
     *
     * @param deviceSlideForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/device/complaintSlide")
    public ResultVO deviceComplaintSlide(@RequestBody DeviceSlideForm deviceSlideForm,
                                        BindingResult bindingResult){

        final String CUSTOMERID = deviceSlideForm.getCustomerId();
        final String DEVICENO = deviceSlideForm.getDeviceNo();
        final int PAGENO = deviceSlideForm.getPageNo();
        final int PAGESIZE = deviceSlideForm.getPageSize();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,deviceSlideForm={}", deviceSlideForm.toString());
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.查找投诉信息 */
        PageRequest pageRequest = new PageRequest(PAGENO, PAGESIZE);
        Page<DeviceComplaintDTO> deviceComplaintDTOPage = userService.findComplaintSlide(CUSTOMERID, DEVICENO, pageRequest);
        long total = deviceComplaintDTOPage.getTotalElements();

        return ResultVOUtil.successPage(deviceComplaintDTOPage.getContent(), PAGENO, PAGESIZE, total);
    }


    /**
     * 客户标签查看接口
     *
     * @param customerIdForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/label/list")
    public ResultVO userLabelList(@RequestBody CustomerIdForm customerIdForm,
                                         BindingResult bindingResult){

        final String CUSTOMERID = customerIdForm.getCustomerId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,CUSTOMERID={}", CUSTOMERID);
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.查询客户标签 */
        List<UserLabelShowDTO> userLabelShowDTOList = userService.findUserLabelShowDTO(CUSTOMERID);

        return ResultVOUtil.success(userLabelShowDTOList);

    }


    /**
     * 客户标签编辑接口
     *
     * @param userLabelForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/label/save")
    public ResultVO userLabelSave(@RequestBody UserLabelForm userLabelForm,
                                  BindingResult bindingResult){

        final String CUSTOMERID = userLabelForm.getCustomerId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,CUSTOMERID={}", CUSTOMERID);
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.修改客户标签 */
        userService.saveUserLabel(userLabelForm, FMT.format(new Date()));
        log.info("【写入成功】客户标签添加成功.");

        return ResultVOUtil.success();

    }


}
