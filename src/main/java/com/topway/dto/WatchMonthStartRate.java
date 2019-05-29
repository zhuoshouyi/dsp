package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/5/27.
 */
@Data
public class WatchMonthStartRate {

    /** 本月开机率. */
    private String nowMonthAgo = "";

    /** 上月开机率. */
    private String oneMonthAgo = "";

    /** n-2月开机率. */
    private String twoMonthAgo = "";

    /** n-3月开机率. */
    private String threeMonthAgo = "";

    /** n-4月开机率. */
    private String fourMonthAgo = "";

    /** n-5月开机率. */
    private String fiveMonthAgo = "";
}
