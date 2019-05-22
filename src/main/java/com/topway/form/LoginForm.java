package com.topway.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by haizhi on 2019/5/15.
 */
@Data
public class LoginForm {

    /**
     * 网格员编号id
     */
    @NotEmpty(message = "用户id必填")
    private String userid;

}
