package com.topway.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by haizhi on 2019/5/19.
 */
@Data
@Table(name = "area")
public class Area_temp {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 维护站名称. */
    private String fk75638e31;

    /** 小区名称. */
    private String fk999cd340;

    /** 小区代码. */
    private String fka9350c89;

    /** 网格编码. */
    private String fk89cc3f4d;

    /** 网格名称. */
    private String fk3c052170;

    /** 城中村标识. */
    private String fkfeffc5ea;

    /** 缴费类型. */
    private String fk560a959b;

    /** 运营商. */
    private String fkeef0a02b;

    /** 区域分公司. */
    private String fk93e2d7ec;

    /** 数字电视用户数. */
    private double fk06266ce4;

    /** 单电视用户数. */
    private double fk20d1fbb4;

    /** 融合电视用户数. */
    private double fk6e8b29fc;

    /** 高清4K用户数. */
    private double fkf50b49a2;

    /** 宽带用户数. */
    private double fk4de24ed7;

    /** 单宽用户数. */
    private double fkcef5362c;

    /** 百兆用户数. */
    private double fk27ed9a8c;

    /** 数字电视发展数. */
    private double fke8518bbc;

    /** 宽带发展数. */
    private double fk7d58c606;

    /** 数字电视流失数. */
    private double fk24baf8af;

    /** 宽带流失数. */
    private double fka9fae233;

    /** 高清双向用户数. */
    private double fk2d7cd0f7;

    /** 统计日期. */
    private String fkfceb956f;

    /** 融合光纤用户数. */
    private double fkd8fecceb;

    /** 新融合用户数. */
    private double fk1adc9b4a;

    /** 单宽百兆用户数. */
    private double fk63d253a7;

    /** 单电视单宽用户数. */
    private double fkba2558d1;

    /** 融合百兆用户数. */
    private double fk80c2f500;

    /** 新融合在线数. */
    private double fk2705c653;

    /** 光纤用户数. */
    private double fk64d7d498;

    /** 新融合单互动在线数. */
    private double fk3a3f0350;

    /** 单宽光纤用户数. */
    private double fkd9012b7e;

    /** 新融合单互动用户数. */
    private double fka6e43d86;

    /** 统计月份. */
    private String fkabc5da99;

    /** 本月数字电视发展数. */
    private double fk8c01e6bb;

    /** 本月数字电视流失数. */
    private double fk3feba260;

    /** 本月宽带发展数. */
    private double fk8b3a75eb;

    /** 本月宽带流失数. */
    private double fk7378541a;

    /** 月底数字电视用户数. */
    private double fk13dd3f5c;

    /** 月底宽带用户数. */
    private double fk877f3ef8;

}

