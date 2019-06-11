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
public class RankListFault {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 返单日期.fkb99778ab */
    private String backTime;

    /** 运营商_工单.fk4661ab79 */
    private String masterSpcode;

    /** 区域_工单.fkf32b7750 */
    private String masterRegion;

    /** 返单部门编码_从单.fkd7a1cd6d */
    private String followBackDepaNo;

    /** 返单部门名称_从单.fk0e90135e */
    private String followBackDepaName;

    /** 业务网格编码_工单.fkd9a1a6c1 */
    private String masterGridId;

    /** 业务网格_工单.fkf92654c3 */
    private String masterGrid;

    /** 返单人编码_从单.fka97e72a5 */
    private String followBackPeoNo;

    /** 返单人名称_从单.fk3c9956cd */
    private String followBackPeoName;

    /** 安装单处理量.fkcbd7a6f5 */
    private double installNum;

    /** _24小时安装成功量.fk6a6c4643 */
    private double installSuccess24hour;

    /** _48小时内安装量.fka165f3d9 */
    private double installSuccess48hour;

    /** 电视故障处理量.fkab38654e */
    private double watchFaultNum;

    /** 电视故障及时处理成功量.fk42f7eb11 */
    private double watchFaultSuccessNum;

    /** 宽带故障处理量.fk12f0d462 */
    private double networkFaultNum;

    /** 宽带故障及时处理成功量.fk14e6ddfe */
    private double networkFaultSuccessNum;

    /** 重复报障单量.fk5fa4aa1a */
    private double repeatNum;

}
