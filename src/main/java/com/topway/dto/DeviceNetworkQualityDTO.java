package com.topway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceNetworkQualityDTO {

    /** 上行电平波动率. */
    @JsonProperty("上行电平波动率")
    private double f1 = 0;

    /** 上行电平偏离率. */
    @JsonProperty("上行电平偏离率")
    private double f2 = 0;

    /** 上行信噪比波动率. */
    @JsonProperty("上行信噪比波动率")
    private double f3 = 0;

    /** 上行信噪比偏离率. */
    @JsonProperty("上行信噪比偏离率")
    private double f4 = 0;

    /** 端口信噪比波动率. */
    @JsonProperty("端口信噪比波动率")
    private double f5 = 0;

    /** 端口信噪比偏离率. */
    @JsonProperty("端口信噪比偏离率")
    private double f6 = 0;

    /** 上行流量超阈值占比. */
    @JsonProperty("上行流量超阈值占比")
    private double f7 = 0;

    /** 下行流量超阈值占比. */
    @JsonProperty("下行流量超阈值占比")
    private double f8 = 0;

    /** MTR平均值. */
    @JsonProperty("MTR平均值")
    private double f9 = 0;
}
