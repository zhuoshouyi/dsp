package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/28.
 */
@Data
public class DeviceSlideForm {

    /**
     * customerId
     */
    @NotEmpty(message = "customerId,必填")
    private String customerId;

    /**
     * deviceNo
     */
    @NotEmpty(message = "deviceNo,必填")
    private String deviceNo;

    /**
     * 查看第几页
     */
    private int pageNo = 0;

    /**
     * 每一页显示的个数
     */
    private int pageSize = 5;
}
