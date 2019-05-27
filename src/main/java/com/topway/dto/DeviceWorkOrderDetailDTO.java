package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceWorkOrderDetailDTO<T> {

    /** 主单详情. */
    private T masterDetail;

    /** 从单详情. */
    private T followDetail;

}
