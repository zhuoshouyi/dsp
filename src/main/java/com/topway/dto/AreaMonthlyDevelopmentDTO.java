package com.topway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by haizhi on 2019/5/23.
 */
@Data
public class AreaMonthlyDevelopmentDTO {

    @JsonProperty("数字电视发展量")
    private double f1;

    @JsonProperty("数字电视发展占比")
    private double f2;

    @JsonProperty("宽带发展量")
    private double f3;

    @JsonProperty("宽带发展占比")
    private double f4;

    @JsonProperty("数字电视流失量")
    private double f5;

    @JsonProperty("数字电视流失占比")
    private double f6;

    @JsonProperty("宽带流失量")
    private double f7;

    @JsonProperty("宽带流失占比")
    private double f8;
}
