package com.topway.service.Impl;

import com.topway.DAO.*;
import com.topway.service.WarningService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by haizhi on 2019/6/25.
 */
@Service
@Slf4j
public class WarningServiceImpl implements WarningService {

    @Autowired
    WarningMarketDao warningMarketDao;

    @Autowired
    WarningLossDao warningLossDao;

    @Autowired
    WarningServiceDao warningServiceDao;

    @Autowired
    ServiceGridOptDao serviceGridOptDao;

    @Autowired
    UserRoleDao userRoleDao;

    // 全局统一时间格式化格式
    SimpleDateFormat FMT = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    SimpleDateFormat FMT_MONTH = new SimpleDateFormat("yyyy-MM");

    // 实例化当天的日期
    Date today = new Date();

    // 用当天的日期减去一天的秒数得到昨天的日期
    String yesterday = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date(today.getTime()-86400000L));


    // 网格绩效月


    // 月初计算,累计一月

    // 当天往前推一个月
    public String getLastMonthStart(){

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return FMT.format(m);
    }

    // 上个月的月份
    public String getLastMonth(){

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        return FMT_MONTH.format(m);
    }

    // 获取绩效月开始日
    public String getAchiveMonthStart(){

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        if (today.getDate()>21){
            // 如果时间大于20号,则从当月21号开始计算绩效月
            c.add(Calendar.DAY_OF_MONTH, 21-today.getDate());
            Date m = c.getTime();
            return FMT.format(m);
        }else {
            // 如果时间小于等于20号,则从上月21号开始计算绩效月
            c.add(Calendar.MONTH, -1);
            c.add(Calendar.DAY_OF_MONTH, 21-today.getDate());
            Date m = c.getTime();
            return FMT.format(m);

        }

    }


    // 获取绩效月结束日


//    @Override
//    public List<Double> WatchLossNumAndWbLossNum(UserRoleDTO userRoleDTO) {
//
//        Object[] objects;
//
//        if (userRoleDTO.getUserRole().equals("基础网格员") ||
//                userRoleDTO.getUserRole().equals("支撑网格员") ||
//                userRoleDTO.getUserRole().equals("站长")){
//            // 用户身份为网格员
//            log.info("【预警数据】用户身份为基础网格员、支撑网格员或站长");
//            log.info("【预警数据】用户网格:" + userRoleDTO.getServiceGridId());
//            objects = warningLossDao.findByGridIdAndDate(userRoleDTO.getServiceGridId(), getLastMonth());
//        }else if(userRoleDTO.getUserRole().equals("公司领导") ||
//                userRoleDTO.getUserRole().equals("业务部门")){
//            // 用户身份为公司领导或业务部门
//            log.info("【预警数据】用户为公司领导或业务部门");
//            log.info("【预警数据】用户所管运营商:" + userRoleDTO.getSpcodeId() + ". 分公司:" + userRoleDTO.getBusinessOfficeId());
//            objects = warningLossDao.findBySpcodeAndBranchAndDate(userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), getLastMonth());
//        }else{
//            // 用户无身份,默认网格员
//            log.info("【预警数据】用户无身份,默认网格员");
//            log.info("【预警数据】用户网格:" + userRoleDTO.getServiceGridId());
//            objects = warningLossDao.findByGridIdAndDate(userRoleDTO.getServiceGridId(), getLastMonth());
//        }
//        Object[] object0 = (Object[])objects[0];
//        List<Double> doubleList = new ArrayList<>();
//        System.out.println(object0.length);
//        if (object0!=null && object0.length>0){
//            doubleList.add(object0[0]==null ? 0.0 : (double)object0[0]);
//            doubleList.add(object0[1]==null ? 0.0 : (double)object0[1]);
//        }
//
//        return doubleList;
//    }



    @Override
    public Double InstallSuccess24h(List<String> gridStr, List<String> operatorStr, List<String> branchStr){

        log.info("【预警数据接口】【24小时安装处理成功率】传递参数: $1=" + gridStr + ", $2=" + operatorStr + ", $3=" + branchStr + ", $4=" + getAchiveMonthStart() + ", $5=" + yesterday);
        Object[] objects = warningServiceDao.findBy24(gridStr, operatorStr, branchStr, getAchiveMonthStart(), yesterday);

        return Double.valueOf(objects[0]==null ? "0" : objects[0].toString());
    }



//    @Override
//    public Double MarketEffectNum(UserRoleDTO userRoleDTO){
//
//        Object[] objects = warningMarketDao.findByGridId(userRoleDTO.getServiceGridId(), getAchiveMonthStart(), yesterday);
////        Object[] object0 = (Object[]) objects[0];
//
//        return Double.valueOf(objects[0]==null ? "0" : objects[0].toString());
//    }


    @Override
    public Double RegionAverageNum(List<String> gridStr, List<String> operatorStr, List<String> branchStr){

        log.info("【预警数据接口】【片区故障平均处理时长m】传递参数: $1=" + gridStr + ", $2=" + operatorStr + ", $3=" + branchStr + ", $4=" + getAchiveMonthStart() + ", $5=" + yesterday);
        Object[] objects = warningServiceDao.findByRegionAverage(gridStr, operatorStr, branchStr, getAchiveMonthStart(), yesterday);

        return Double.valueOf(objects[0]==null ? "0" : objects[0].toString());
    }

    @Override
    public Double RegionFaultSuccess(List<String> gridStr, List<String> operatorStr, List<String> branchStr){

        log.info("【预警数据接口】【故障及时处理成功率】传递参数: $1=" + gridStr + ", $2=" + operatorStr + ", $3=" + branchStr + ", $4=" + getAchiveMonthStart() + ", $5=" + yesterday);
        Object[] objects = warningServiceDao.findByFaultSuccess(gridStr, operatorStr, branchStr, getAchiveMonthStart(), yesterday);

        return Double.valueOf(objects[0]==null ? "0" : objects[0].toString());
    }



//    @Override
//    public UserRoleDTO authentication(LoginForm loginForm){
//
//        /** 1.获取参数 */
//        final String OPENID = loginForm.getUserid();
//        final String USERNAME = loginForm.getUsername();
//        final String USERID = loginForm.getUs_id();
//        final String SPCODE = loginForm.getOperator();
//        final String BRANCH = loginForm.getBranch();
//
//        /** 2.验证用户身份 */
//        log.info("【登陆】验证用户身份");
//        LoginVO loginVO = new LoginVO();
//        UserRole userRole = new UserRole();
//
//        // 如果 userId 不为"",说明是基础网格员、支撑网格员、站长。
//        // 否则就是公司领导或者业务部门
//        if (!USERID.equals("") && USERID!=null ){
//            log.info("【登陆】用户为网格员或站长,userId为 " + USERID);
//            log.info("【登陆】用户名称为 " + USERNAME);
//            // 关联工单表,获取用户所管辖的网格
//            List<ServiceGridOpt> serviceGridOptList = serviceGridOptDao.findByOpId(USERID);
//
//            // 如果是网格员但匹配不到网格,返回报错信息
//            if (serviceGridOptList == null || serviceGridOptList.size()==0){
//                log.error("【ERROR】网格员无法匹配到网格");
//                return null;
//            }
//            // 将网格员负责的网格拼接到列表中去
//            List<String> serviceGridIdList = new ArrayList<>();
//            for (ServiceGridOpt serviceGridOpt : serviceGridOptList){
//                if (serviceGridOpt.getServiceGridId()!=null && !serviceGridOpt.getServiceGridId().equals("")){
//                    serviceGridIdList.add(serviceGridOpt.getServiceGridId());
//                }
//            }
//            try {
//                userRole = userRoleDao.findByUserId(USERID);
//
//                // TODO 细化区分五种权限类型
////            userRole.setUserRole("支撑网格员");
//                userRole.setServiceGridId(List2StringConvert.convert(serviceGridIdList));
//
//            }catch (Exception e){
//                // 根据userid查询不到用户,无此用户
//                log.error("【ERROR】根据 userid 无法查询到此用户");
//                return null;
//            }
//
//
//        }else {
//            // 公司领导和业务部门即使用它们的运营商和分公司进行区分
//            // TODO 添加公司领导权限
//            log.info("【登陆】用户为公司领导或者业务部门,运营商为:" + SPCODE + ", 区域分公司为:" + BRANCH);
//
//            userRole = LoginForm2UserRoleConvert.convert(loginForm);
//            userRole.setUserRole("公司领导");
//
//        }
//
//        return UserRole2UserRoleDTOConvert.convert(userRole);
//
//    }


}
