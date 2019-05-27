package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceWorkOrderDTO {

    /** id. */
    private Integer id;

    /** 工单类型. */
    private String orderType;

    /** 创建日期. */
    private String createTime;

    /** 派单内容. */
    private String content;


}
