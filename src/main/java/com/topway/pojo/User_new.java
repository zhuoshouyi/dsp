package com.topway.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/30.
 */
@Data
public class User_new {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 用户编码. */
    private String userId;

    /** 客户编码. */
    private String customerId;

    /** 资源号. */
    private String deviceNo;






}
