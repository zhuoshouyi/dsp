package com.topway.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
        String  xml = "<xml>\n" +
                "      <appId>"+appId+"</appId>\n" +
                "      <token>"+token+"</token>\n" +
                "      <sign>"+ sign+ "</sign>\n" +
                "</xml>\n";

        System.out.println("开始发送请求:");
        interfaceTest("http://192.168.41.157:9014/ApiEvent/authorUser ","xml="+xml);
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

    //path: 接口地址  data : 参数
    public static void interfaceTest(String path, String data) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.connect();
            PrintWriter out = null;
            out = new PrintWriter(httpConn.getOutputStream());
            //发送请求参数即数据
            out.print(data);
            //缓冲数据
            out.flush();
            //获取httpConn对象对应的输入流
            InputStream is = httpConn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
            //关闭流
            is.close();
            httpConn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
