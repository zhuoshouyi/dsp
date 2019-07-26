package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/29.
 */
@Entity
@Data
public class UserLabel {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** customerId. */
    private String customerId;

    /** createTime. */
    private String createTime;

    /** 是否智能电视. */
    private String isSmartTV;

    /** 是否自住. */
    private String isRenting;

    /** 用户类型. */
    private String customerType;

    /** 有无小孩. */
    private String isHaveChildren;

    /** 有无老人. */
    private String isHaveElderly;

    /** 是否用其他运营商产品. */
    private String isUseOtherProduct;

    /** 收视兴趣(可多选). */
    private String watchInterest;

    /** 用户类型(可多选). */
    private String customerCharacteristic;

    /** 沟通类型. */
    private String communicationType;

    /** 客户自定义标签. */
    private String customerFileds;
}
