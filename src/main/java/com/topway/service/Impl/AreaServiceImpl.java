package com.topway.service.Impl;

import com.topway.DAO.AreaDao;
import com.topway.DAO.AreaLabelDao;
import com.topway.DAO.HistoryMarketDao;
import com.topway.DAO.PropertyDao;
import com.topway.VO.ResultVO;
import com.topway.convert.AreaLabel2AreaLabelFormConvert;
import com.topway.convert.AreaLabel2AreaLabelShowDTOConvert;
import com.topway.convert.HistoryMarketForm2HistoryMarketConvert;
import com.topway.dto.AreaBusinessDTO;
import com.topway.dto.AreaLabelShowDTO;
import com.topway.dto.AreaMonthlyDevelopmentDTO;
import com.topway.dto.UserRoleDTO;
import com.topway.enums.ResultEnum;
import com.topway.form.AreaLabelForm;
import com.topway.form.HistoryMarketForm;
import com.topway.form.PropertyForm;
import com.topway.pojo.Area;
import com.topway.pojo.AreaLabel;
import com.topway.pojo.HistoryMarket;
import com.topway.pojo.Property;
import com.topway.service.AreaService;
import com.topway.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by haizhi on 2019/5/22.
 */
@Service
@Slf4j
public class AreaServiceImpl implements AreaService{

    // 全局统一时间格式化格式
    SimpleDateFormat FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 实例化当天的日期
    Date today = new Date();

    // 用当天的日期减去昨天的日期
    String yesterday = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date(today.getTime()-86400000L));


    @Autowired
    AreaDao dao;

    @Autowired
    AreaLabelDao areaLabelDao;

    @Autowired
    HistoryMarketDao historyMarketDao;

    @Autowired
    PropertyDao propertyDao;



    @Override
    public List<Area> findByAreaList() {
        return null;
    }


    /** 根据小区id查找小区信息 */
    @Override
    public Area findByAreaId(String areaId, UserRoleDTO userRoleDTO) {
        // TODO 修改日期,改为昨天
        Area area;
        switch (userRoleDTO.getUserRole()){
            case "基础网格员":
            case "支撑网格员":
            case "站长":
                log.info("【认证】身份为 基础网格员、支撑网格员 或 站长");
                area = dao.findByAreaId(
                        areaId, "收费", "2019-05-27 00:00:00", userRoleDTO.getServiceGridId(), null, null);
                break;

            case "公司领导":
            case "业务部门":
                log.info("【认证】身份为 公司领导 或 业务部门");
                area = dao.findByAreaId(
                        areaId, "收费", "2019-05-27 00:00:00", null, userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId());
                break;

            default:
                log.info("【认证】无用户身份,默认网格员");
                area = dao.findByAreaId(
                        areaId, "收费", "2019-05-27 00:00:00", userRoleDTO.getServiceGridId(), null, null);
                break;
        }
        return area;
    }


    /** 根据小区名称查找小区信息 */
    @Override
    public Page<Area> findByAreaNameLike(String areaName, UserRoleDTO userRoleDTO, Pageable pageable) {
        // TODO 修改日期,改为昨天
        Page<Area> areaPage = null;
        log.info("【查询】userRole=" + userRoleDTO.getUserRole());
        switch (userRoleDTO.getUserRole()){
            case "基础网格员":
            case "支撑网格员":
            case "站长":
                log.info("【查询】网格查找");
//                log.info("【查询】serviceGridId=" + userRoleDTO.getServiceGridId().toString());
                areaPage = dao.findByAreaNameLike(
                        areaName, "2019-05-27 00:00:00", userRoleDTO.getServiceGridId(), null, null, pageable);
                break;

            case "公司领导":
            case "业务部门":
                log.info("【查询】运营商分公司查找");
                areaPage = dao.findByAreaNameLike(
                        areaName, "2019-05-27 00:00:00", null, userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), pageable);
                break;

            default:
                // TODO
                log.info("【查询】无用户身份,默认网格员");
                areaPage = dao.findByAreaNameLike(
                        areaName, "2019-05-27 00:00:00", userRoleDTO.getServiceGridId(), null, null, pageable);
                break;
        }
        return areaPage;
    }


    /** 查询小区的物业信息 */
    @Override
    public Property findProperty(String areaId){
        return propertyDao.findByAreaId(areaId);
    }


    /** 修改小区的物业信息 */
    @Override
    public ResultVO saveProperty(UserRoleDTO userRoleDTO, Property property, PropertyForm propertyForm, Integer historyMarketNum){

        log.info("【物业信息编辑】用户身份:" + userRoleDTO.getUserRole());
        if (userRoleDTO.getUserRole().equals("站长") || userRoleDTO.getUserRole().equals("管理员")){

            // 站长和管理员有物业信息的编辑权限
            if (!propertyForm.getPropertyName().isEmpty()) property.setPropertyName(propertyForm.getPropertyName());
            if (!propertyForm.getPropertyAddress().isEmpty()) property.setPropertyAddress(propertyForm.getPropertyAddress());
            if (!propertyForm.getPropertyManagerName().isEmpty()) property.setPropertyManagerName(propertyForm.getPropertyManagerName());
            if (!propertyForm.getPropertyManagerPhone().isEmpty()) property.setPropertyManagerPhone(propertyForm.getPropertyManagerPhone());
            if (!propertyForm.getElectricianName().isEmpty()) property.setElectricianName(propertyForm.getElectricianName());
            if (!propertyForm.getElectricianPhone().isEmpty()) property.setElectricianPhone(propertyForm.getElectricianPhone());

        }else if (userRoleDTO.getUserRole().equals("支撑网格员") || userRoleDTO.getUserRole().equals("基础网格员")){

            // 支撑网格员和基础网格员只有第一次编辑的权限
            // 物业名称
            if (!propertyForm.getPropertyName().isEmpty()){
                if (property.getPropertyName().isEmpty()) {
                    // 只有字段为空才能编辑
                    property.setPropertyName(propertyForm.getPropertyName());
                }
                else {
                    // 否则返回错误,用户无编辑权限
                    log.info("【物业信息编辑】非第一次编辑,无物业名称编辑权限.");
                    return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                            "非第一次编辑,无物业名称编辑权限.");
                }
            }

            // 物业地址
            if (!propertyForm.getPropertyAddress().isEmpty()){
                if (property.getPropertyAddress().isEmpty()) {
                    // 只有字段为空才能编辑
                    property.setPropertyAddress(propertyForm.getPropertyAddress());
                }
                else {
                    // 否则返回错误,用户无编辑权限
                    log.info("【物业信息编辑】非第一次编辑,无物业地址编辑权限.");
                    return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                            "非第一次编辑,无物业地址编辑权限.");
                }
            }

            // 物业联系人
            if (!propertyForm.getPropertyManagerName().isEmpty()){
                if (property.getPropertyManagerName().isEmpty()) {
                    // 只有字段为空才能编辑
                    property.setPropertyManagerName(propertyForm.getPropertyManagerName());
                }
                else {
                    // 否则返回错误,用户无编辑权限
                    log.info("【物业信息编辑】非第一次编辑,无物业联系人编辑权限.");
                    return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                            "非第一次编辑,无物业联系人编辑权限.");
                }
            }

            // 物业负责人电话
            if (!propertyForm.getPropertyManagerPhone().isEmpty()){
                if (property.getPropertyManagerPhone().isEmpty()) {
                    // 只有字段为空才能编辑
                    property.setPropertyManagerPhone(propertyForm.getPropertyManagerPhone());
                }
                else {
                    // 否则返回错误,用户无编辑权限
                    log.info("【物业信息编辑】非第一次编辑,无物业负责人电话编辑权限.");
                    return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                            "非第一次编辑,无电工联系人编辑权限.");
                }
            }

            // 电工联系人
            if (!propertyForm.getElectricianName().isEmpty()){
                if (property.getElectricianName().isEmpty()) {
                    // 只有字段为空才能编辑
                    property.setElectricianName(propertyForm.getElectricianName());
                }
                else {
                    // 否则返回错误,用户无编辑权限
                    log.info("【物业信息编辑】非第一次编辑,无电工联系人编辑权限.");
                    return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                            "非第一次编辑,无电工联系人编辑权限.");
                }
            }

            // 电工电话
            if (!propertyForm.getElectricianPhone().isEmpty()){
                if (property.getElectricianPhone().isEmpty()) {
                    // 只有字段为空才能编辑
                    property.setElectricianPhone(propertyForm.getElectricianPhone());
                }
                else {
                    // 否则返回错误,用户无编辑权限
                    log.info("【物业信息编辑】非第一次编辑,无电工电话编辑权限.");
                    return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                            "非第一次编辑,无电工电话编辑权限.");
                }
            }

        }else {
            log.info("【物业信息编辑】身份非管理员、站长、网格员,无编辑权限.");
            return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                    "身份非管理员、站长、网格员,无编辑权限.");
        }


        property.setHistoryMarket(historyMarketNum);
        propertyDao.save(property);

        log.info("【物业信息编辑】物业信息修改成功.");
        return ResultVOUtil.success();
    }


    /** 计算小区的业务在线情况 */
    @Override
    public AreaBusinessDTO calAreaBusiness(String areaId) {
        AreaBusinessDTO areaBusinessDTO = new AreaBusinessDTO();

        Area area1 = dao.findByAreaIdAndPaymentTypeAndDate(areaId, "收费", "2019-05-22 00:00:00");
//        Area area2 = dao.findByFka9350c89AndFk560a959bAndFkfceb956f(areaId, "免费", "2019-04-20 00:00:00");

        // 1 数字电视在线收费终端数(fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF1(area1.getWatchNum());

        // 2 单电视+单宽用户(fk20d1fbb4 单电视用户数, fkcef5362c 单宽用户数)
        areaBusinessDTO.setF2(area1.getWatchOnlyNum() + area1.getWbOnlyNum());

        // 3 高清双向终端(fk2d7cd0f7 高清双向用户数)
        areaBusinessDTO.setF3(area1.getTwoWayNum());

        // 4 高清双向占比(fk2d7cd0f7 高清双向用户数, fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF4((area1.getTwoWayNum()) / (area1.getWatchNum()));

        // 5 4K终端(fkf50b49a2 高清4K用户数)
        areaBusinessDTO.setF5(area1.getWatch4kNum());

        // 6 4K占比(fkf50b49a2 高清4K用户数, fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF6((area1.getWatch4kNum()) / (area1.getWatchNum()));

        // 7 新融合产品在线量(fk2705c653 新融合在线数)
        areaBusinessDTO.setF7(area1.getNewMixOnlineNum());

        // 8 新融合占比(fk2705c653 新融合在线数, fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF8(area1.getNewMixOnlineNum() / area1.getWatchNum());

        // 9 互动业务在线量(fk3a3f0350 新融合单互动在线数)
        areaBusinessDTO.setF9(area1.getNewMixInteractionOnlineNum());

        // 10 订购互动业务终端数(fka6e43d86 新融合单互动用户数)
        areaBusinessDTO.setF10(area1.getNewMixInteractionNum());

        // 11 互动业务订购率(fka6e43d86 新融合单互动用户数, fk06266ce4 数字电视用户数)
        areaBusinessDTO.setF11(area1.getNewMixInteractionNum() / area1.getWatchNum());

        // 12 宽带在线收费终端(fk4de24ed7 宽带用户数)
        areaBusinessDTO.setF12(area1.getWbNum());

        // 13 单宽百兆(fk63d253a7 单宽百兆用户数)
        areaBusinessDTO.setF13(area1.getWbOnly100mNum());

        // 14 融合百兆(fk80c2f500 融合百兆用户数)
        areaBusinessDTO.setF14(area1.getMix100mNum());

        // 15 百兆占比(fk27ed9a8c 百兆用户数, fk4de24ed7 宽带用户数)
        areaBusinessDTO.setF15(area1.getWb100mNum() / area1.getWbNum());

        // 16 单宽光纤(fkd9012b7e 单宽光纤用户数)
        areaBusinessDTO.setF16(area1.getWbOnlyFiberNum());

        // 17 融合光纤(fkd8fecceb 融合光纤用户数)
        areaBusinessDTO.setF17(area1.getMixFiberNum());

        // 18 光纤占比(fk64d7d498 光纤用户数, fk4de24ed7 宽带用户数)
        areaBusinessDTO.setF18(area1.getFiberNum() / area1.getWbNum());

        return areaBusinessDTO;
    }


    /** 计算小区的终端月度发展情况 */
    @Override
    public AreaMonthlyDevelopmentDTO calMonthlyDevelopment(String areaId) {
        AreaMonthlyDevelopmentDTO areaMonthlyDevelopmentDTO = new AreaMonthlyDevelopmentDTO();

        // TODO 时间
        Area area1 = dao.findByAreaIdAndPaymentTypeAndDate(areaId, "收费", "2019-05-22 00:00:00");
//        Area area2 = dao.findByFka9350c89AndFk560a959bAndFkfceb956f(areaId, "免费", "2019-04-20 00:00:00");


        // 1 数字电视发展量(fk8c01e6bb 本月数字电视发展数)
        areaMonthlyDevelopmentDTO.setF1(area1.getNowWatchDevelopNum());

        // 2 数字电视发展占比(fk8c01e6bb 本月数字电视发展数, fk13dd3f5c 月底数字电视用户数)
        areaMonthlyDevelopmentDTO.setF2(area1.getWatchDevelopNum() / area1.getEndOfMonthWatchNum());

        // 3 宽带发展量(fk8b3a75eb 本月宽带发展数)
        areaMonthlyDevelopmentDTO.setF3(area1.getNowWbDevelopNum());

        // 4 宽带发展占比(fk8b3a75eb 本月宽带发展数, fk877f3ef8 月底宽带用户数)
        areaMonthlyDevelopmentDTO.setF4(area1.getNowWbDevelopNum() / area1.getEndOfMonthWbNum());

        // 5 数字电视流失量(fk3feba260 本月数字电视流失数)
        areaMonthlyDevelopmentDTO.setF5(area1.getNowWatchLossNum());

        // 6 数字电视流占比(fk3feba260 本月数字电视流失数, fk13dd3f5c 月底数字电视用户数)
        areaMonthlyDevelopmentDTO.setF6(area1.getNowWatchLossNum() / area1.getEndOfMonthWatchNum());

        // 7 宽带流失量(fk7378541a 本月宽带流失数)
        areaMonthlyDevelopmentDTO.setF7(area1.getNowWbLossNum());

        // 8 宽带流失占比(fk7378541a 本月宽带流失数, fk877f3ef8 月底宽带用户数)
        areaMonthlyDevelopmentDTO.setF8(area1.getNowWbLossNum() / area1.getEndOfMonthWbNum());

        return areaMonthlyDevelopmentDTO;
    }


    /** 小区标签列表展示查询 */
    @Override
    public List<AreaLabelShowDTO> findAreaLabelShow(String areaId){
        List<AreaLabel> areaLabelList = areaLabelDao.findByAreaIdOrderByCreateTimeDesc(areaId);

        return AreaLabel2AreaLabelShowDTOConvert.convert(areaLabelList);
    }

    /** 小区标签列表查询 */
    @Override
    public List<AreaLabel> findAreaLabel(String areaId){

        return areaLabelDao.findByAreaIdOrderByCreateTimeDesc(areaId);
    }

    /** 小区标签查询最后一条 */
    @Override
    public ResultVO findAreaLabelLastRecord(String areaId){
        List<AreaLabel> areaLabelList = areaLabelDao.findByAreaIdOrderByCreateTimeDesc(areaId);
        if (areaLabelList.size()>0) {
            AreaLabel areaLabel = areaLabelList.get(0);
            AreaLabelForm areaLabelForm = AreaLabel2AreaLabelFormConvert.convert(areaLabel);
            return ResultVOUtil.success(areaLabelForm);
        }else {
            return ResultVOUtil.error(ResultEnum.RESULT_NOT_FOUND.getCode(),
                    ResultEnum.RESULT_NOT_FOUND.getDesc());
        }
    }

    /** 更新小区标签 */
    @Override
    public ResultVO saveAreaLabel(UserRoleDTO userRoleDTO, AreaLabelForm areaLabelForm){

        if (userRoleDTO.getUserRole().equals("业务部门") || userRoleDTO.getUserRole().equals("站长") || userRoleDTO.getUserRole().equals("支撑网格员")
                || userRoleDTO.getUserRole().equals("基础网格员") || userRoleDTO.getUserRole().equals("管理员") || userRoleDTO.getUserRole().equals("")) {

            log.info("【小区标签编辑】用户身份:" + userRoleDTO.getUserRole());

            AreaLabel areaLabel = new AreaLabel();

            areaLabel.setAreaId(areaLabelForm.getAreaId());
            areaLabel.setBuildAttrbute(areaLabelForm.getBuildAttrbute());
            areaLabel.setAreaLiveProportion(areaLabelForm.getAreaLiveProportion());
            areaLabel.setIsContractArea(areaLabelForm.getIsContractArea());
            areaLabel.setIsPermittedAdmission(areaLabelForm.getIsPermittedAdmission());
            areaLabel.setIsCompeteArea(areaLabelForm.getIsCompeteArea());
            areaLabel.setIsRegularCover(areaLabelForm.getIsRegularCover());
            areaLabel.setNetworkCoverageProperties(areaLabelForm.getNetworkCoverageProperties());
            areaLabel.setIsStabilityLiver(areaLabelForm.getIsStabilityLiver());
            areaLabel.setCustomFields(StringUtils.strip(areaLabelForm.getCustomFields().toString(), "[]"));
            areaLabel.setCreateTime(FMT.format(new Date()));
            areaLabelDao.save(areaLabel);

        }else {
            log.info("【小区标签编辑】无编辑权限.");
            return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                    ResultEnum.USER_HAVE_NOT_PRIVILEGE.getDesc());
        }


        log.info("【小区标签编辑】小区标签数据写入成功.");
        return ResultVOUtil.success();
    }


    /** 查询历史营销记录 */
    @Override
    public Page<HistoryMarket> findHistoryMarket(String areaId, Pageable pageable){
        return historyMarketDao.findByAreaIdOrderByCreateTimeDesc(areaId, pageable);
    }


    /** 插入历史营销记录 */
    @Override
    public ResultVO saveHistoryMarket(UserRoleDTO userRoleDTO, HistoryMarketForm historyMarketForm, String date){

        log.info("【营销记录编辑】用户身份:" + userRoleDTO.getUserRole());
        if (userRoleDTO.getUserRole().equals("站长") || userRoleDTO.getUserRole().equals("管理员") ) {
            // 将 historyMarketForm 转成 HistoryMarket
            HistoryMarket historyMarket = HistoryMarketForm2HistoryMarketConvert.convert(historyMarketForm);

            // 增加时间字段
            historyMarket.setCreateTime(date);

            // 写入数据库
            historyMarketDao.save(historyMarket);

        }else {
            log.info("【营销记录编辑】无编辑权限.");
            return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                    ResultEnum.USER_HAVE_NOT_PRIVILEGE.getDesc());
        }

        log.info("【营销记录编辑】历史营销进场记录数据库写入成功.");
        return ResultVOUtil.success();

    }


}
