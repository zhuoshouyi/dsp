package com.topway.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by haizhi on 2019/5/26.
 */
@Entity
@Data
public class WatchAction {

    /** id. */
    @Id
    @GeneratedValue
    private int id;

    /** 智能卡号. */
    private String devicNo;

    /** 统计类型. */
    private String statisticalType;

    /** 统计周期. */
    private String statisticalCycle;

    /** 次数. */
    private double numbers;

    /** 时长. */
    private double timeLength;

}
