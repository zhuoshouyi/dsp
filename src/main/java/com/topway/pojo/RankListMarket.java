package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/6/5.
 */
@Entity
@Data
public class RankListMarket {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 运营商.fk1695ced8 */
    private String spcode;

    /** 区域分公司.fk2a1d2cf3 */
    private String branch_office;

    /** 维护站名称.fk973750cd */
    private String station;

    /** 网格编码.fk0dc50847 */
    private String gridId;

    /** 网格名称.fke042ec3b */
    private String gridName;

    /** 发展人编码.fkb9a4f9bb */
    private String developPeopleNo;

    /** 发展人.fk5093a21d */
    private String developPeople;

    /** 受理渠道编码.fk724964c3 */
    private String acceptChannelNo;

    /** 受理渠道.fka6ea4558 */
    private String acceptChannel;

    /** 业务类型.fke77b811a */
    private String business;

    /** 营销生效额.fk599a5ef5 */
    private double value;

    /** 生效日.fk06a803b6 */
    private String date;
}
