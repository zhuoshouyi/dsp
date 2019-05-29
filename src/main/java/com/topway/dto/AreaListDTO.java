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
    private String fk999cd340 = "";

    /** 小区代码. */
    @JsonProperty("areaId")
    private String fka9350c89 = "";

    /** 网格名称. */
    @JsonProperty("grid")
    private String fk3c052170 = "";

    /** 城中村标识. */
    @JsonProperty("attribute")
    private String fkfeffc5ea = "";

    /** 维护站名称. */
    @JsonProperty("station")
    private String fk75638e31 = "";

}
