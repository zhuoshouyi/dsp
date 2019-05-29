package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/29.
 */
@Data
public class HistoryMarketForm {

    /**
     * 用户填写的搜索信息,可以搜索小区名称或者小区编码
     */
    @NotEmpty(message = "areaId,必填")
    private String areaId;

    /**
     * 营销进场内容
     */
    @NotEmpty(message = "营销进场内容,必填")
    private String content;

}
