package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class WatchYearStartRateDTO {

    /** 今年开机率. */
    private String thisYear;

    /** 上一年开机率. */
    private String lastYear;

}
