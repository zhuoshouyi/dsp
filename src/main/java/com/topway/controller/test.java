package com.topway.controller;

/**
 * Created by haizhi on 2019/5/28.
 */
public class test {

    public static void main(String[] args){

//        // 实例化当天的日期
//        Date today = new Date();
//
//        // 用当天的日期减去一天的秒数得到昨天的日期
//        String yesterday = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date(today.getTime()-86400000L));
//
//        System.out.println(yesterday);
//        String s1="123456";
//        System.out.println("");
        for (int i=100000; i<=999999; i++){
            if (i%8==0){
                if (i%11==0){
                    String s = String.valueOf(i);
                    if (s.substring(1,5).equals("9202"))
                        System.out.println("i=" + i);
                }
            }
        }
    }

}
