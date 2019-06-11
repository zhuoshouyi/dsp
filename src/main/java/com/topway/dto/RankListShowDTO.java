package com.topway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by haizhi on 2019/6/5.
 */
@Data
public class RankListShowDTO<T> {

    @JsonProperty("营销生效额")
    private T f1;

    @JsonProperty("24安装处理成功率(数字+宽带)")
    private T f2;

    @JsonProperty("48安装处理成功率(数字+宽带)")
    private T f3;

    @JsonProperty("故障及时处理成功率(数字)")
    private T f4;

    @JsonProperty("故障及时处理成功率(宽带)")
    private T f5;

    @JsonProperty("故障单预约规范率")
    private T f6;

    @JsonProperty("数字电视用户流失数/率")
    private T f7;

    @JsonProperty("20M宽带流失数/率")
    private T f8;

    @JsonProperty("100M宽带流失数/率")
    private T f9;

    @JsonProperty("网格报障率")
    private T f10;

    @JsonProperty("重复故障率")
    private T f11;

}
