package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/23.
 */
@Entity
@Data
public class Customer {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 客户编码. */
    private String customerId;

    /** 客户名称. */
    private String customerName;

    /** 客户类型. */
    private String customerType;

    /** 身份证号. */
    private String paperNo;

    /** 电话号码. */
    private String phone;
}
