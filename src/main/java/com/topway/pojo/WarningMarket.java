package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/6/25.
 */
@Data
@Entity
public class WarningMarket {

    /** id. */
    @Id
    @GeneratedValue
    private int id;

    /** 生效日. fk06a803b6 */
    private String date;

    /** 运营商. fk1695ced8 */
    private String spcode;

    /** 区域分公司. fk2a1d2cf3 */
    private String branch;

    /** 维护站名称. fk973750cd */
    private String station;

    /** 网格编码. fk0dc50847 */
    private String grid_id;

    /** 网格名称. fke042ec3b */
    private String grid_name;

    /** 发展人编码. fkb9a4f9bb */
    private String develop_id;

    /** 发展人. fk5093a21d */
    private String develop_name;

    /** 受理渠道编码. fk724964c3 */
    private String access_id;

    /** 受理渠道. fka6ea4558 */
    private String access;

    /** 业务类型. fke77b811a */
    private String business_type;

    /** 营销生效额. fk599a5ef5 */
    private Double market_effect_num;
}
