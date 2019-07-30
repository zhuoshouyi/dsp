package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceListDTO<T> {

    private T basicInfo;

    private T businessInfo;

    private T workOrder;

    private T complaint;

    private T watchAction;

    private T networkQuality;
}
