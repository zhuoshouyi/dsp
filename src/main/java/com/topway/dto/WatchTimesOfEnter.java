package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class WatchTimesOfEnter {
    
    /** 时移业务 7天. */
    private String sevenDayTime;

    /** 天天影院 7天. */
    private String sevenDayCinema;

    /** 单片点播 7天. */
    private String sevenDayDrop;

    /** 直播 7天. */
    private String sevenDayShow;

    /** 时移业务 30天. */
    private String thirtyDayTime;

    /** 天天影院 30天. */
    private String thirtyDayCinema;

    /** 单片点播 30天. */
    private String thirtyDayDrop;

    /** 直播 30天. */
    private String thirtyDayShow;
}
