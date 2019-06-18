package com.topway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by haizhi on 2019/5/22.
 */
@Data
public class AreaListDTO {

    /** 小区名称. */
    @JsonProperty("areaName")
    private String areaName = "";

    /** 小区代码. */
    @JsonProperty("areaId")
    private String areaId = "";

    /** 网格名称. */
    @JsonProperty("grid")
    private String gridName = "";

    /** 城中村标识. */
    @JsonProperty("attribute")
    private String buildAttrbute = "";

    /** 维护站名称. */
    @JsonProperty("station")
    private String station = "";

}
