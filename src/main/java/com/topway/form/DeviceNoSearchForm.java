package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/7/3.
 */
@Data
public class DeviceNoSearchForm {

    /**
     * 前端传递的customerId,用于展示客户详情
     */
    @NotEmpty(message = "customerId,必填")
    private String customerId;

    /**
     * 前端传递的keyword,用于展示客户详情
     */
    @NotEmpty(message = "keyword,必填")
    private String keyword;

}
