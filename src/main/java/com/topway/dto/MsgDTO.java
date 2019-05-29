package com.topway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by haizhi on 2019/5/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgDTO {

    /** 错误代码. */
    private Integer code;

    /** 错误详细信息. */
    private String desc = "";
}