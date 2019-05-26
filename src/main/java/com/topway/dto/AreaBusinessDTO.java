package com.topway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by haizhi on 2019/5/23.
 */
@Data
public class AreaBusinessDTO {

    @JsonProperty("数字电视在线收费终端")
    private double f1;

    @JsonProperty("单电视+单宽用户")
    private double f2;

    @JsonProperty("高清双向终端")
    private double f3;

    @JsonProperty("高清双向占比")
    private double f4;

    @JsonProperty("4K终端")
    private double f5;

    @JsonProperty("4K占比")
    private double f6;

    @JsonProperty("新融合产品在线量")
    private double f7;

    @JsonProperty("新融合占比")
    private double f8;

    @JsonProperty("互动业务在线量")
    private double f9;

    @JsonProperty("订购互动业务终端数")
    private double f10;

    @JsonProperty("互动业务订购率")
    private double f11;

    @JsonProperty("宽带在线收费终端")
    private double f12;

    @JsonProperty("单宽百兆")
    private double f13;

    @JsonProperty("融合百兆")
    private double f14;

    @JsonProperty("百兆占比")
    private double f15;

    @JsonProperty("单宽光纤")
    private double f16;

    @JsonProperty("融合光纤")
    private double f17;

    @JsonProperty("光纤占比")
    private double f18;


}
