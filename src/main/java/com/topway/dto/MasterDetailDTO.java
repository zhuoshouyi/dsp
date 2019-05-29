package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class MasterDetailDTO {

    /** 创建时间. */
    private String createTime = "";

    /** 派单时间. */
    private String sendTime = "";

    /** 单据类型. */
    private String type = "";

    /** 派单内容. */
    private String content = "";

    /** 处理详细. */
    private String handleDetails = "";

    /** 派单备注. */
    private String remarks = "";


}
