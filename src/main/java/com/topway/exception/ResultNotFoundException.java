package com.topway.exception;

import com.topway.enums.ResultEnum;

/**
 * Created by haizhi on 2019/5/22.
 */
public class ResultNotFoundException extends RuntimeException {

    private Integer code;

    public ResultNotFoundException(ResultEnum resultEnum) {
        super(resultEnum.getDesc());
        this.code = resultEnum.getCode();
    }

    public ResultNotFoundException(Integer code, String desc) {
        super(desc);
        this.code = code;
    }
}
