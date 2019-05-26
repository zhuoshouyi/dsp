package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/23.
 */
@Data
public class AreaIdForm {

    /**
     * 用户填写的搜索信息,可以搜索小区名称或者小区编码
     */
    @NotEmpty(message = "areaId,必填")
    private String areaId;
}
