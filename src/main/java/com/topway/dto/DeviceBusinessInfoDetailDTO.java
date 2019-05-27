package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceBusinessInfoDetailDTO {

    /** 资源号. */
    private String deviceNo;

    /** 产品编码. */
    private String productId;

    /** 产品名称. */
    private String productName;

    /** 购买价格. */
    private String purchasePrice;

    /** 购买时长. */
    private String purchaseLength;

    /** 购买时间. */
    private String purchaseTime;

    /** 开通日期. */
    private String openTime;

    /** 到期时间. */
    private String dueTime;

    /** 当前状态. */
    private String status;

    /** 经营范围. */
    private String businessScope;

    /** 授权区域. */
    private String authorizedArea;

    /** 计费方式. */
    private String billingMode;

}