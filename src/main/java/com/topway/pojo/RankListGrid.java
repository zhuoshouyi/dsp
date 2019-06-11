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
public class RankListGrid {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 统计月.fkd7f915d3 */
    private String month;

    /** 运营商.fka7321e69 */
    private String spcode;

    /** 小区编码.fkde1f4c07 */
    private String areaId;

    /** 故障单量.fk12440402 */
    private double faultNum;

    /** 小区用户数.fkcc747e6d */
    private double areaUserNum;

    /** 小区名称.fk1e80f3f0 */
    private String areaName;

    /** 维护站名称.fkeb82a46c */
    private String station;

    /** 区域分公司.fk41873d1c */
    private String branchOffice;

    /** 网格名称.fk1d06e6a7 */
    private String grid;

    /** 分部名称.fkd757f8f6 */
    private String departement;

}
