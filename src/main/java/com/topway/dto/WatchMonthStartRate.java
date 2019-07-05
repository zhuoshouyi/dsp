package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class WatchMonthStartRate {

    /** 本月开机率. */
    private String nowMonthAgo = "0";

    /** 上月开机率. */
    private String oneMonthAgo = "0";

    /** n-2月开机率. */
    private String twoMonthAgo = "0";

    /** n-3月开机率. */
    private String threeMonthAgo = "0";

    /** n-4月开机率. */
    private String fourMonthAgo = "0";

    /** n-5月开机率. */
    private String fiveMonthAgo = "0";
}
