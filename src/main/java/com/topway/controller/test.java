package com.topway.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by haizhi on 2019/5/28.
 */
public class test {

    public static void main(String[] args){

        // 实例化当天的日期
        Date today = new Date();

        // 用当天的日期减去一天的秒数得到昨天的日期
        String yesterday = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date(today.getTime()-86400000L));

        System.out.println(yesterday);
    }

}
