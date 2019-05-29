package com.topway.controller;

import java.security.MessageDigest;

/**
 * Created by haizhi on 2019/5/28.
 */
public class test {

    public static void main(String[] args){

        String appId = "7";
        String token = "1559035399855";
        String openId = "71559035399855D8988D91-E362-D95D-F748-CAC270A97700";
        String appSecret = "D8988D91-E362-D95D-F748-CAC270A97700";
//        String sign = MD5("appId="+appId + "&token="+token+appSecret).toUpperCase();
        String sign = MD5("appId="+appId +"&openId="+openId+appSecret).toUpperCase();
        System.out.println("sign:"+sign);
    }

    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = md5.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {

            return "";
        }
    }
}
