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
public class RankListLoss {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 统计月份.fk63789b70 */
    private String month;

    /** 运营商.fk521afa3d */
    private String spcode;

    /** 区域分公司.fkbd2883aa */
    private String branchOffice;

    /** 维护站名称.fkee09067d */
    private String station;

    /** 网格编码.fkf895439a */
    private String gridId;

    /** 网格名称.fkaf296537 */
    private String gridName;

    /** 本月底数字用户数.fkabae59ce */
    private double nowMonthWatchNum;

    /** 上月底数字用户数.fk95df4ea3 */
    private double lastMonthWatchNum;

    /** 本月底20M用户数.fk2c622be8 */
    private double nowMonth20mNum;

    /** 上月底20M用户数.fkf23bd5fa */
    private double lastMonth20mNum;

    /** 本月底100M用户数.fkcfe79abc */
    private double nowMonth100mNum;

    /** 上月底100M用户数.fk86116955 */
    private double lastMonth100mNum;

}
