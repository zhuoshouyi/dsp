package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class FollowDetailDTO {

    /** 派单时间. */
    private String sendTime = "";

    /** 返单时间. */
    private String backTime = "";

    /** 联系人电话. */
    private String phone = "";

    /** 处理人员. */
    private String dealWithPerson = "";

    /** 派单内容. */
    private String sendContent = "";

    /** 派单备注. */
    private String sendRemark = "";

    /** 处理详细. */
    private String dealWithDetails = "";

    /** 返单备注. */
    private String backRemark = "";

}
