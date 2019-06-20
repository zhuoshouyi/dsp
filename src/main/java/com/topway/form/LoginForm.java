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
    @NotEmpty(message = "userid 必填")
    private String userid;

    /**
     * 门户名字
     */
    @NotEmpty(message = "username 必填")
    private String username;

    /**
     * 工单用户id
     */
    private String us_id = "";

    /**
     * 运营商id
     */
    private String operator = "";

    /**
     * 分公司id
     */
    private String branch = "";

}
