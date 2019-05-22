package com.topway.exception;

import com.topway.enums.ResultEnum;

/**
 * Created by haizhi on 2019/5/20.
 */
public class ParamException extends RuntimeException {

    private Integer code;

    public ParamException(ResultEnum resultEnum) {
        super(resultEnum.getDesc());
        this.code = resultEnum.getCode();
    }

    public ParamException(Integer code, String desc) {
        super(desc);
        this.code = code;
    }
}
