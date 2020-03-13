package com.topway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haizhi on 2019/6/25.
 */
@RestController
@RequestMapping("/warning")
@Slf4j
public class WarningController_bak {
//
//    @Autowired
//    WarningServiceImpl service;
//
//
//    @PostMapping("/list")
//    public ResultVO warningList(@RequestBody @Valid WarningForm warningForm,
//                                BindingResult bindingResult) {
//
//        log.info("【预警数据接口】-------------------------------------------------------");
//
//        final String OPERATOR = warningForm.getOperator();
//        final String BRANCH = warningForm.getBranch();
//        final String GRIDS = warningForm.getGrids();
//        final String TARGETS = warningForm.getTargets();
//
//        log.info("【预警数据接口】接收参数 OPERATOR: " + OPERATOR);
//        log.info("【预警数据接口】接收参数 BRANCH: " + BRANCH);
//        log.info("【预警数据接口】接收参数 GRIDS: " + GRIDS);
//        log.info("【预警数据接口】接收参数 TARGETS: " + TARGETS);
//
//        /** 1.校验form表单是否正确 */
//        if (bindingResult.hasErrors()){
//            log.error("【参数错误】传入的参数有误,warningForm={}", warningForm.toString());
//            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
//                    bindingResult.getFieldError().getDefaultMessage());
//        }
//
////        /** 2.识别用户身份,判断权限 */
////        UserAloneAuthentication userAloneAuthentication = new UserAloneAuthentication();
////        UserRoleDTO userRoleDTO = service.authentication(loginForm);
////        if (userRoleDTO==null){
////            return ResultVOUtil.error(ResultEnum.USER_NOT_FOUND.getCode(),
////                    ResultEnum.USER_NOT_FOUND.getDesc());
////        }
//
//        /** 2.获取网格信息 */
//        List<String> gridStr = new ArrayList<>();
//        List<String> operatorStr = new ArrayList<>();
//        List<String> branchStr = new ArrayList<>();
//        List<String> targetsStr = new ArrayList<>();
//
//
//        // 如果网格编码为-1,就是用运营商和区域分公司进行数据筛选,反之是用网格编码筛选
//        if (GRIDS.equals("-1")){
//            // 取运营商和区域分公司
//            operatorStr = String2ListConvert.convertTo(OPERATOR);
//            branchStr = String2ListConvert.convertTo(BRANCH);
//            Collections.addAll(targetsStr,TARGETS.split(","));
//            gridStr = null;
//            log.info("【预警数据接口】通过运营商和区域分公司查询");
//            log.info("【预警数据接口】运营商: " + operatorStr);
//            log.info("【预警数据接口】分公司: " + branchStr);
//
//        }else {
//            // 取网格编码串
//            operatorStr = null;
//            branchStr = null;
//            Collections.addAll(gridStr,GRIDS.split(","));
//            Collections.addAll(targetsStr,TARGETS.split(","));
//            log.info("【预警数据接口】通过网格编码查询");
//            log.info("【预警数据接口】网格编码: " + gridStr);
//        }
//
//        /** 3.拼接WarningVO */
//        WarningVO warningVO = new WarningVO();
//
//        // 保留两位小数
//        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//
//        // 根据需求返回值
//        if (targetsStr.contains("1")){
//            // 24小时安装处理成功率
////            warningVO.setInstallSuccess24h(Double.valueOf(df.format(service.InstallSuccess24h(gridStr, operatorStr, branchStr))));
//        }
//        if (targetsStr.contains("2")){
//            // 片区故障平均处理时长
////            warningVO.setRegionTimeLength(Double.valueOf(df.format(service.RegionAverageNum(gridStr, operatorStr, branchStr))));
//        }
//        if (targetsStr.contains("3")){
//            // 故障及时处理成功率
////            warningVO.setFaultSuccess(Double.valueOf(df.format(service.RegionFaultSuccess(gridStr, operatorStr, branchStr))));
//        }
////        List<Double> WatchLossNumAndWbLossNumList = service.WatchLossNumAndWbLossNum(userRoleDTO);
//
//        // 数字电视终端流失数
////        warningVO.setWatchLossNum(WatchLossNumAndWbLossNumList.get(0));
//
//        // 宽带终端流失数
////        warningVO.setWbLossNum(WatchLossNumAndWbLossNumList.get(1));
//
//        // 24小时安装处理成功率
////        warningVO.setInstallSuccess24h(Double.valueOf(df.format(service.InstallSuccess24h(gridStr, operatorStr, branchStr, targetsStr))));
//
//        // 假单数
////        warningVO.setFaultOrderNum(0.0);
//
//        // 服务投诉数
////        warningVO.setServiceComplaintNum(0.0);
//
//        // 营销生效额
////        warningVO.setMarketEffectiveNum(service.MarketEffectNum(userRoleDTO));
//
//        // 片区故障平均处理时长
////        warningVO.setRegionTimeLength(Double.valueOf(df.format(service.RegionAverageNum(gridStr, operatorStr, branchStr, targetsStr))));
//
//        // 故障及时处理成功率
////        warningVO.setFaultSuccess(Double.valueOf(df.format(service.RegionFaultSuccess(gridStr, operatorStr, branchStr, targetsStr))));
//
//
//        return ResultVOUtil.success(warningVO);
//
//    }
}