package com.topway.dto;

import com.topway.form.LoginForm;
import com.topway.pojo.UserRole;

/**
 * Created by haizhi on 2019/6/21.
 */
public class LoginForm2UserRoleConvert {

    public static UserRole convert(LoginForm loginForm){
        UserRole userRole = new UserRole();
        userRole.setOpenId(loginForm.getUserid());
        userRole.setUserName(loginForm.getUsername());
        userRole.setSpcodeId(loginForm.getOperator());
        userRole.setBusinessOfficeId(loginForm.getBranch());

        return userRole;

    }
}
