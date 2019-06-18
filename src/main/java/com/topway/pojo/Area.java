package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/6/18.
 */
@Data
@Entity
public class Area {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 维护站名称 fk75638e31. */
    private String station;

    /** 小区名称 fk999cd340. */
    private String areaName;

    /** 小区代码 fka9350c89. */
    private String areaId;

    /** 网格编码 fk89cc3f4d. */
    private String gridId;

    /** 网格名称 fk3c052170. */
    private String gridName;

    /** 城中村标识 fkfeffc5ea. */
    private String buildAttrbute;

    /** 缴费类型 fk560a959b. */
    private String paymentType;

    /** 运营商 fkeef0a02b. */
    private String spcode;

    /** 区域分公司 fk93e2d7ec. */
    private String branch;

    /** 数字电视用户数 fk06266ce4. */
    private double watchNum;

    /** 单电视用户数 fk20d1fbb4. */
    private double watchOnlyNum;

    /** 融合电视用户数 fk6e8b29fc. */
    private double watchMixNum;

    /** 高清4K用户数 fkf50b49a2. */
    private double watch4kNum;

    /** 宽带用户数 fk4de24ed7. */
    private double wbNum;

    /** 单宽用户数 fkcef5362c. */
    private double wbOnlyNum;

    /** 百兆用户数 fk27ed9a8c. */
    private double wb100mNum;

    /** 数字电视发展数 fke8518bbc. */
    private double watchDevelopNum;

    /** 宽带发展数 fk7d58c606. */
    private double wbDevelopNum;

    /** 数字电视流失数 fk24baf8af. */
    private double watchLossNum;

    /** 宽带流失数 fka9fae233. */
    private double wbLossNum;

    /** 高清双向用户数 fk2d7cd0f7. */
    private double twoWayNum;

    /** 统计日期 fkfceb956f. */
    private String date;

    /** 融合光纤用户数 fkd8fecceb. */
    private double mixFiberNum;

    /** 新融合用户数 fk1adc9b4a. */
    private double newMixNum;

    /** 单宽百兆用户数 fk63d253a7. */
    private double wbOnly100mNum;

    /** 单电视单宽用户数 fkba2558d1. */
    private double watchOnlyWbOnlyNum;

    /** 融合百兆用户数 fk80c2f500. */
    private double mix100mNum;

    /** 新融合在线数 fk2705c653. */
    private double newMixOnlineNum;

    /** 光纤用户数 fk64d7d498. */
    private double fiberNum;

    /** 新融合单互动在线数 fk3a3f0350. */
    private double newMixInteractionOnlineNum;

    /** 单宽光纤用户数 fkd9012b7e. */
    private double wbOnlyFiberNum;

    /** 新融合单互动用户数 fka6e43d86. */
    private double newMixInteractionNum;

    /** 统计月份 fkabc5da99. */
    private String month;

    /** 本月数字电视发展数 fk8c01e6bb. */
    private double nowWatchDevelopNum;

    /** 本月数字电视流失数 fk3feba260. */
    private double nowWatchLossNum;

    /** 本月宽带发展数 fk8b3a75eb. */
    private double nowWbDevelopNum;

    /** 本月宽带流失数 fk7378541a. */
    private double nowWbLossNum;

    /** 月底数字电视用户数 fk13dd3f5c. */
    private double endOfMonthWatchNum;

    /** 月底宽带用户数 fk877f3ef8. */
    private double endOfMonthWbNum;

}
