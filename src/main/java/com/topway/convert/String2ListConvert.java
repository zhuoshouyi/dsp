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
}
