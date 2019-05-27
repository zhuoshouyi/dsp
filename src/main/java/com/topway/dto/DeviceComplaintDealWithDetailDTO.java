package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceComplaintDealWithDetailDTO {

    /** 反馈时间. */
    private String feedBackTime;

    /** 应反馈截止时间. */
    private String feedBackEndTime;

    /** 是否延迟. */
    private String isDelay;

    /** 处理人. */
    private String dealwithPeople;

    /** 内容分类. */
    private String contentType;

    /** 处理详细信息. */
    private String detail;

}
