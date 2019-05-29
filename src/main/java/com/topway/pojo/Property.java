package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 小区物业信息
 *
 * Created by haizhi on 2019/5/23.
 */
@Entity
@Data
public class Property {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** areaId. */
    private String areaId;

    /** 物业名称. */
    private String propertyName;

    /** 物业地址. */
    private String propertyAddress;

    /** 物业联系人. */
    private String propertyManagerName;

    /** 物业负责人电话. */
    private String propertyManagerPhone;

    /** 电工联系人. */
    private String electricianName;

    /** 电工电话. */
    private String electricianPhone;

    /** 历史营销记录. */
    private Integer historyMarket;
}
