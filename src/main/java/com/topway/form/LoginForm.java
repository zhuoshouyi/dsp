package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/15.
 */
@Data
public class LoginForm {

    /**
     * 门户id
     */
    @NotEmpty(message = "openId 必填")
    private String openId;

    /**
     * 门户名字
     */
    @NotEmpty(message = "userName 必填")
    private String userName;

    /**
     * 工单用户id
     */
    private String userId = "";

    /**
     * 运营商id
     */
    @NotEmpty(message = "spcodeId 必填")
    private String spcodeId;

    /**
     * 分公司id
     */
    @NotEmpty(message = "businessOfficeId 必填")
    private String businessOfficeId;

}
