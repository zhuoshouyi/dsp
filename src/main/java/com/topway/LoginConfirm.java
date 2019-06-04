package com.topway;

import com.topway.VO.LoginVO;
import com.topway.dto.UserRoleDTO;
import com.topway.enums.ResultEnum;
import com.topway.exception.UserNotFoundException;
import com.topway.form.LoginForm;
import com.topway.utils.DomConvert;
import com.topway.utils.JwtUtil;
import com.topway.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;


/**
 * Created by haizhi on 2019/5/14.
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class LoginConfirm {

    @PostMapping("/hello")
    public @ResponseBody
    Object hellWorld() {
        return "Hello World! This is a protected api";
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginForm loginForm,
                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【登陆失败】参数不正确, loginForm={}", loginForm);
            throw new UserNotFoundException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        LoginVO loginVO = new LoginVO();
        UserRoleDTO userRoleDTO = new UserRoleDTO();

        /** 1.获取token */
//        String userId = "";
        String token = loginForm.getToken();
        log.info("【用户登录】获取用户的token:" + token);

        /** 2.获取openId */
        String openId = getOpenId(token);
        log.info("【用户登录】获取到用户的openId:" + openId);

        /** 3.获取userId */
        String userId = getUserId(token, openId);
        log.info("【用户登录】获取到用户的userid:" + userId);

        if (userId=="" || userId.isEmpty()){
            throw new UserNotFoundException(ResultEnum.USER_NOT_FOUND);
        }
        if (isValidUser(userId)){
            String jwt = JwtUtil.encode(userId);
//            String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2cDl0NzVtUzJhNyIsImV4cCI6MTU1Nzk3Mjc4OCwidWlkIjoxOTJ9.Xx46NOJz5juaUQtbhwM-8PU5FwduBJ8trXgLqzMJXoo";

            userRoleDTO.setUserId(userId);
            userRoleDTO.setRoleId(1);
            userRoleDTO.setRoleName("公司领导");
            loginVO.setBearer(jwt);
            loginVO.setUserInfo(userRoleDTO);

            return ResultVOUtil.success(loginVO);

        }else {
            return ResultVOUtil.error(ResultEnum.USER_NOT_FOUND.getCode(), ResultEnum.USER_NOT_FOUND.getDesc());
        }

    }


    private boolean isValidUser(String userId) {
        // TODO
        return true;
    }


    public String getOpenId(String token){
        String appId = "7";
        String appSecret = "D8988D91-E362-D95D-F748-CAC270A97700";
        String sign = MD5("appId="+appId + "&token="+token+appSecret).toUpperCase();
        String  xml = "<xml>\n" +
                "      <appId>"+appId+"</appId>\n" +
                "      <token>"+token+"</token>\n" +
                "      <sign>"+ sign+ "</sign>\n" +
                "</xml>\n";

        String resXml = interfaceTest("http://192.168.41.157:9014/ApiEvent/authorUser ","xml="+xml);
        // TODO 判断code,抛出异常
        return DomConvert.getXmlById(resXml, "openId");
    }

    public String getUserId(String token, String openId){
        String appId = "7";
//        String token = "1559035399855";
//        String openId = "71559035399855D8988D91-E362-D95D-F748-CAC270A97700";
        String appSecret = "D8988D91-E362-D95D-F748-CAC270A97700";
//        String sign = MD5("appId="+appId + "&token="+token+appSecret).toUpperCase();
        String sign = MD5("appId="+appId +"&openId="+openId+appSecret).toUpperCase();
        String  xml = "<xml>\n" +
                "      <appId>"+appId+"</appId>\n" +
                "      <token>"+token+"</token>\n" +
                "      <sign>"+ sign+ "</sign>\n" +
                "</xml>\n";

        String resXml = interfaceTest("http://192.168.41.157:9014/ApiEvent/getUserByOpenId ","xml="+xml);
        return DomConvert.getXmlById(resXml, "userId");
    }

    public String MD5(String inStr) {
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
    public String interfaceTest(String path, String data) {
        URL url = null;
        String str = "";
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

            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
            //关闭流
            is.close();
            httpConn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;

    }


}
