package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/29.
 */
@Data
public class PropertyForm {

    /**
     * areaId
     */
    @NotEmpty(message = "areaId,必填")
    private String areaId;

    /**
     * 物业名称
     */
    private String propertyName;

    /**
     * 物业地址
     */
    private String propertyAddress;

    /**
     * 物业联系人
     */
    private String propertyManagerName;

    /**
     * 物业负责人电话
     */
    private String propertyManagerPhone;

    /**
     * 电工联系人
     */
    private String electricianName;

    /**
     * 电工电话
     */
    private String electricianPhone;

}
