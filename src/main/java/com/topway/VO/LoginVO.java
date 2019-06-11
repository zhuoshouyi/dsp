package com.topway.VO;

import com.topway.pojo.UserRole;
import lombok.Data;

/**
 * Created by haizhi on 2019/5/15.
 */
@Data
public class LoginVO {

    /** 用户信息. */
    private UserRole userInfo;

    /** jwt认证. */
    private String bearer;
}
