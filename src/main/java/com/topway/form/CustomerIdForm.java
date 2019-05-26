package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/23.
 */
@Data
public class CustomerIdForm {

    /**
     * 前端传递的customerid,用于展示客户详情
     */
    @NotEmpty(message = "customerId,必填")
    private String customerId;
}
