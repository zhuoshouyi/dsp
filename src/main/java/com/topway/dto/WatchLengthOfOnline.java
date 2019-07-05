package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class WatchLengthOfOnline {

    /** 总计 7天. */
    private String sevenTotal = "0";

    /** 时移业务 7天. */
    private String sevenDayTime = "0";

    /** 天天影院 7天. */
    private String sevenDayCinema = "0";

    /** 单片点播 7天. */
    private String sevenDayDrop = "0";

    /** 直播 7天. */
    private String sevenDayShow = "0";

    /** 总计 30天. */
    private String thirtyTotal = "0";

    /** 时移业务 30天. */
    private String thirtyDayTime = "0";

    /** 天天影院 30天. */
    private String thirtyDayCinema = "0";

    /** 单片点播 30天. */
    private String thirtyDayDrop = "0";

    /** 直播 30天. */
    private String thirtyDayShow = "0";

}
