package com.topway.convert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/6/12.
 */
public class String2ListConvert {

    public static List<String> convert(String str){
        String[] strings = str.split(",");
        List<String> stringList = new ArrayList<>();
        for (String s : strings){
            stringList.add(s);
        }
        return stringList;

    }

    public static List<String> convertTo(String str){
        String[] strings = str.split(",");
        List<String> stringList = new ArrayList<>();
        for (String s : strings){
            switch (s){
                case "0": stringList.add("天威"); break;
                case "2": stringList.add("光明"); break;
                case "5": stringList.add("天宝"); break;
                case "6": stringList.add("天隆"); break;
                case "7": stringList.add("深汕"); break;
                case "01": stringList.add("罗湖分公司"); break;
                case "02": stringList.add("福田分公司"); break;
                case "03": stringList.add("南山分公司"); break;
                default: break;
            }
        }
        return stringList;
    }
}
