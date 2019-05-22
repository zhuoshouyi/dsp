package com.topway.exception;

import com.topway.enums.ResultEnum;

/**
 * Created by haizhi on 2019/5/15.
 */
public class UserNotFoundException extends RuntimeException{

    private Integer code;

    public UserNotFoundException(ResultEnum resultEnum) {
        super(resultEnum.getDesc());
        this.code = resultEnum.getCode();
    }

    public UserNotFoundException(Integer code, String desc) {
        super(desc);
        this.code = code;
    }
}
