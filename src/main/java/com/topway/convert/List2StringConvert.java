package com.topway.convert;

import java.util.List;

/**
 * Created by haizhi on 2019/6/13.
 */
public class List2StringConvert {

    public static String convert(List<String> stringList){

        String string = "";
        for (String str : stringList){
            string = string + str + ",";
        }
        string = string.substring(0, string.length()-1);

        return string;

    }
}
