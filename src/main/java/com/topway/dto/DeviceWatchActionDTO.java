package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceWatchActionDTO<T> {

    // 近两年终端开机率
    private T yearStartRate;

    // 近六个月终端开机率
    private T monthStartRate;

    // 终端在线时长
    private T lengthOfOnline;

    // 进入应用次数
    private T timesOfEnter;


}
