package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceWatchActionDTO {

    // 近两年终端开机率
    private WatchYearStartRateDTO yearStartRate;

    // 近六个月终端开机率
    private WatchMonthStartRate monthStartRate;

    // 终端在线时长
    private WatchLengthOfOnline lengthOfOnline;

    // 进入应用次数
    private WatchTimesOfEnter timesOfEnter;


}
