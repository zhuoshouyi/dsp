package com.topway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by haizhi on 2019/5/23.
 */
@Data
public class AreaMonthlyDevelopmentDTO {

    @JsonProperty("数字电视发展量")
    private double f1 = 0;

    @JsonProperty("数字电视发展占比")
    private double f2 = 0;

    @JsonProperty("宽带发展量")
    private double f3 = 0;

    @JsonProperty("宽带发展占比")
    private double f4 = 0;

    @JsonProperty("数字电视流失量")
    private double f5 = 0;

    @JsonProperty("数字电视流失占比")
    private double f6 = 0;

    @JsonProperty("宽带流失量")
    private double f7 = 0;

    @JsonProperty("宽带流失占比")
    private double f8 = 0;
}
