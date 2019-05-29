package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class DealWithMessageDTO {

    /** detailId. */
    private String detailId = "";

    /** 反馈时间. */
    private String feedBackTime = "";

    /** 内容分类. */
    private String contentType = "";

    /** 处理详细信息. */
    private String detail = "";
}
