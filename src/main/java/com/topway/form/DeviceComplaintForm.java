package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class DeviceComplaintForm {

    /**
     * 前端传递的 customerId,用于展示投诉详情
     */
    @NotEmpty(message = "customerId,必填")
    private String customerId;

    /**
     * 前端传递的 deviceNo,用于展示投诉详情
     */
    @NotEmpty(message = "deviceNo,必填")
    private String deviceNo;

    /**
     * 前端传递的 id,用于展示投诉详情
     */
    @NotEmpty(message = "id,必填")
    private String id;

}
