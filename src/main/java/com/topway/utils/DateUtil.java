package com.topway.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by haizhi on 2019/7/25.
 */
public class DateUtil {


    // 获取系统时间
    static Date date =new Date();

    // 全局统一时间格式化格式
    static SimpleDateFormat FMT = new SimpleDateFormat("yyyy-MM");

    static Calendar calendar = Calendar.getInstance();

    public static String[] AchievementsMonth(){
        String[] strings = new String[2];
        calendar.setTime(date);

        return null;
    }
}
