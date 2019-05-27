package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class ComplaintDTO {

    /** 投诉编号. */
    private String orderId;

    /** 受理时间. */
    private String acceptTime;

    /** 投诉来源. */
    private String complaintFrom;

    /** 结单时间. */
    private String endTime;

    /** 处理方式. */
    private String dealWithWay;

    /** 投诉内容. */
    private String content;
}
