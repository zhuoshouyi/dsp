package com.topway;

import com.topway.VO.LoginVO;
import com.topway.dto.UserRoleDTO;
import com.topway.enums.ResultEnum;
import com.topway.exception.UserNotFoundException;
import com.topway.form.LoginForm;
import com.topway.utils.JwtUtil;
import com.topway.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by haizhi on 2019/5/14.
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class LoginConfirm {

    @PostMapping("/hello")
    public @ResponseBody
    Object hellWorld() {
        return "Hello World! This is a protected api";
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginForm loginForm,
                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【登陆失败】参数不正确, loginForm={}", loginForm);
            throw new UserNotFoundException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        LoginVO loginVO = new LoginVO();
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        String userid = loginForm.getUserid();

        if (userid=="" || userid.isEmpty()){
            throw new UserNotFoundException(ResultEnum.USER_NOT_FOUND);
        }
        if (isValidUser(userid)){
            String jwt = JwtUtil.encode(userid);
//            String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2cDl0NzVtUzJhNyIsImV4cCI6MTU1Nzk3Mjc4OCwidWlkIjoxOTJ9.Xx46NOJz5juaUQtbhwM-8PU5FwduBJ8trXgLqzMJXoo";

            userRoleDTO.setUserid(userid);
            userRoleDTO.setRoleId(1);
            userRoleDTO.setRoleName("公司领导");
            loginVO.setBearer(jwt);
            loginVO.setUserInfo(userRoleDTO);

            return ResultVOUtil.success(loginVO);

        }else {
            return ResultVOUtil.error(ResultEnum.USER_NOT_FOUND.getCode(), ResultEnum.USER_NOT_FOUND.getDesc());
        }

    }


    private boolean isValidUser(String userid) {
        // TODO
        return true;
    }

}
