package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/15.
 */
@Data
public class LoginForm {

    /**
     * token
     */
    @NotEmpty(message = "token必填")
    private String token;

}
