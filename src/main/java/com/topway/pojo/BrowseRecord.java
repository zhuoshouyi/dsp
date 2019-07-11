package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/7/11.
 */
@Data
@Entity
public class BrowseRecord {

    /** id. */
    @Id
    @GeneratedValue
    private Integer id;

    /** 操作员id. */
    private String userId;

    /** 浏览类型 小区area 用户user. */
    private String type;

    /** 数值 小区areaId 用户customerId. */
    private String valueId;

    /** 数值 小区areaName 用户customerName. */
    private String valueName;

    /** 时间 创建时间. */
    private String createTime;
}
