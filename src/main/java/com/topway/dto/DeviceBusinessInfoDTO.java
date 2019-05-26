package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceBusinessInfoDTO {

    /** 产品名称. */
    private String productName;

    /** 开通日期. */
    private String openTime;

    /** 当前状态. */
    private String status;
}