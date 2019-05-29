package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceComplaintDTO {

    /** id. */
    private Integer id;

    /** 受理时间. */
    private String acceptTime = "";

    /** 联系电话. */
    private String phone = "";

    /** 投诉内容. */
    private String content = "";


}
