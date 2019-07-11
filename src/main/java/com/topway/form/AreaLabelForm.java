package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/5/29.
 */
@Data
public class AreaLabelForm {

    /**
     * 小区id
     */
    @NotEmpty(message = "areaId,必填")
    private String areaId;

    /**
     * 楼盘属性
     */
    private String buildAttrbute = "";

    /**
     * 小区自住比例
     */
    private String areaLiveProportion = "";

    /**
     * 是否合同小区
     */
    private String isContractArea = "";

    /**
     * 是否可以进场
     */
    private String isPermittedAdmission = "";

    /**
     * 是否竞争小区
     */
    private String isCompeteArea = "";

    /**
     * 是否正轨覆盖
     */
    private String isRegularCover = "";

    /**
     * 网络覆盖属性
     */
    private String networkCoverageProperties = "";

    /**
     * 小区住户稳定性
     */
    private String isStabilityLiver = "";

    /**
     * 客户自定义标签
     */
    private List<String> customFields = new ArrayList<>();

}
