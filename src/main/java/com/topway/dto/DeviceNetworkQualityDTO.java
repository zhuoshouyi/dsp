package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceNetworkQualityDTO {

    /** 上行电平波动率. */
    private double f1;

    /** 上行电平偏离率. */
    private double f2;

    /** 上行信噪比波动率. */
    private double f3;

    /** 上行信噪比偏离率. */
    private double f4;

    /** 端口信噪比波动率. */
    private double f5;

    /** 端口信噪比偏离率. */
    private double f6;

    /** 上行流量超阈值占比. */
    private double f7;

    /** 下行流量超阈值占比. */
    private double f8;

    /** MTR平均值. */
    private double f9;
}
