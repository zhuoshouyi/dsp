package com.topway;

import com.auth0.jwt.interfaces.Claim;
import com.topway.DAO.ServiceGridOptDao;
import com.topway.DAO.UserRoleDao;
import com.topway.VO.LoginVO;
import com.topway.convert.List2StringConvert;
import com.topway.dto.LoginForm2UserRoleConvert;
import com.topway.enums.ResultEnum;
import com.topway.form.LoginForm;
import com.topway.pojo.ServiceGridOpt;
import com.topway.pojo.UserRole;
import com.topway.utils.JwtUtil;
import com.topway.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by haizhi on 2019/5/14.
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class LoginConfirm {

    @Autowired
    ServiceGridOptDao serviceGridOptDao;

    @Autowired
    UserRoleDao userRoleDao;

    @PostMapping("/hello")
    public @ResponseBody
    Object hellWorld(HttpServletRequest httpServletRequest) {
        Map<String, Claim> claimMap = JwtUtil.getToken(httpServletRequest);
        System.out.println("userId : " + claimMap.get("userId"));
        System.out.println("userName : " + claimMap.get("userName"));
        System.out.println("spcodeId : " + claimMap.get("spcodeId"));
        System.out.println("businessOfficeId : " + claimMap.get("businessOfficeId"));
        System.out.println("serviceGridId : " + claimMap.get("serviceGridId"));
        return "Hello World! This is a protected api";
    }

    @PostMapping("/login")
    public Object login(@Valid @RequestBody LoginForm loginForm,
                        BindingResult bindingResult){

        /** 1.获取传输参数 */
        log.info("【登陆】-------------------------------------------------------");
        log.info("【登陆】获取传输参数");
        final String OPENID = loginForm.getUserid();
        final String USERNAME = loginForm.getUsername();
        final String USERID = loginForm.getUs_id();
        final String SPCODEID = loginForm.getOperator();
        final String BRANCH = loginForm.getBranch();


        /** 2.判断传输参数是否正确 */
        log.info("【登陆】检查参数是否正确");
        log.info("【登陆】 OPENID : " + OPENID);
        log.info("【登陆】 USERNAME : " + USERNAME);
        log.info("【登陆】 USERID : " + USERID);
        log.info("【登陆】 SPCODEID : " + SPCODEID);
        log.info("【登陆】 BRANCH : " + BRANCH);
        if (bindingResult.hasErrors()) {
            log.error("【ERROR】登陆失败,参数不正确, loginForm={}", loginForm);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 3.验证用户身份 */
        log.info("【登陆】验证用户身份");
        LoginVO loginVO = new LoginVO();
        UserRole userRole = new UserRole();

//        // 最先验证特殊权限
//        if (userRole.getUserRoleSpecial()!=null && !"".equals(userRole.getUserRoleSpecial())){
//            // 特殊权限仅可配置业务部门和公司领导,需要配合运营商和区域分公司使用
//            log.info("【特殊权限】校验用户特殊权限:" + userRole.getUserRoleSpecial());
//            log.info("【特殊权限】校验用户运营商:" + userRole.getSpcodeId());
//            log.info("【特殊权限】校验用户区域分公司:" + userRole.getBusinessOfficeId());
//
//            userRole = LoginForm2UserRoleConvert.convert(loginForm);
//        }

        // 如果 userId 不为"",说明是基础网格员、支撑网格员、站长。
        // 否则就是公司领导或者业务部门
        if (USERID!="" ){
            log.info("【登陆】用户为网格员或站长,userId为" + USERID);
            // 关联工单表,获取用户所管辖的网格
            List<ServiceGridOpt> serviceGridOptList = serviceGridOptDao.findByOpId(USERID);

            // 如果是网格员但匹配不到网格,返回报错信息
            if (serviceGridOptList == null || serviceGridOptList.size()==0){
                log.error("【ERROR】网格员无法匹配到网格");
                return ResultVOUtil.error(ResultEnum.USER_NOT_MATCH.getCode(),
                        ResultEnum.USER_NOT_MATCH.getDesc());
            }
            // 将网格员负责的网格拼接到列表中去
            List<String> serviceGridIdList = new ArrayList<>();
            for (ServiceGridOpt serviceGridOpt : serviceGridOptList){
                if (serviceGridOpt.getServiceGridId()!=null && !serviceGridOpt.getServiceGridId().equals("")){
                    serviceGridIdList.add(serviceGridOpt.getServiceGridId());
                }
            }
            log.info("【登陆】用户网格为:" + serviceGridIdList.toString());

            try {
                userRole = userRoleDao.findByUserId(USERID);

                // TODO 细化区分五种权限类型
//            userRole.setUserRole("支撑网格员");
                userRole.setServiceGridId(List2StringConvert.convert(serviceGridIdList));

            }catch (Exception e){
                // 根据userid查询不到用户,无此用户
                log.error("【ERROR】根据userid查询不到用户,无此用户");
                return ResultVOUtil.error(ResultEnum.USER_NOT_FOUND.getCode(),
                        ResultEnum.USER_NOT_FOUND.getDesc());
            }


        }else {
            // 公司领导和业务部门即使用它们的运营商和分公司进行区分
            // TODO 添加公司领导权限
            log.info("【登陆】用户为公司领导或者业务部门,运营商为:" + SPCODEID + ", 区域分公司为:" + BRANCH);

            userRole = LoginForm2UserRoleConvert.convert(loginForm);
            userRole.setUserRole("公司领导");

        }


        String jwt = JwtUtil.encode(userRole);

        loginVO.setBearer(jwt);
        loginVO.setUserInfo(userRole);

        return ResultVOUtil.success(loginVO);


    }



//    public String getOpenId(String token){
//        String appId = "7";
//        String appSecret = "D8988D91-E362-D95D-F748-CAC270A97700";
//        String sign = MD5("appId="+appId + "&token="+token+appSecret).toUpperCase();
//        String  xml = "<xml>\n" +
//                "      <appId>"+appId+"</appId>\n" +
//                "      <token>"+token+"</token>\n" +
//                "      <sign>"+ sign+ "</sign>\n" +
//                "</xml>\n";
//
//        String resXml = interfaceTest("http://192.168.41.157:9014/ApiEvent/authorUser ","xml="+xml);
//        // TODO 判断code,抛出异常
//        return DomConvert.getXmlById(resXml, "openId");
//    }
//
//    public String getUserId(String token, String openId){
//        String appId = "7";
////        String token = "1559035399855";
////        String openId = "71559035399855D8988D91-E362-D95D-F748-CAC270A97700";
//        String appSecret = "D8988D91-E362-D95D-F748-CAC270A97700";
////        String sign = MD5("appId="+appId + "&token="+token+appSecret).toUpperCase();
//        String sign = MD5("appId="+appId +"&openId="+openId+appSecret).toUpperCase();
//        String  xml = "<xml>\n" +
//                "      <appId>"+appId+"</appId>\n" +
//                "      <token>"+token+"</token>\n" +
//                "      <sign>"+ sign+ "</sign>\n" +
//                "</xml>\n";
//
//        String resXml = interfaceTest("http://192.168.41.157:9014/ApiEvent/getUserByOpenId ","xml="+xml);
//        return DomConvert.getXmlById(resXml, "userId");
//    }
//
//    public String MD5(String inStr) {
//        MessageDigest md5 = null;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//            byte[] byteArray = inStr.getBytes("UTF-8");
//            byte[] md5Bytes = md5.digest(byteArray);
//            StringBuffer hexValue = new StringBuffer();
//            for (int i = 0; i < md5Bytes.length; i++) {
//                int val = ((int) md5Bytes[i]) & 0xff;
//                if (val < 16) hexValue.append("0");
//                hexValue.append(Integer.toHexString(val));
//            }
//            return hexValue.toString();
//        } catch (Exception e) {
//
//            return "";
//        }
//    }
//
//
//
//    //path: 接口地址  data : 参数
//    public String interfaceTest(String path, String data) {
//        URL url = null;
//        String str = "";
//        try {
//            url = new URL(path);
//            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
//            httpConn.setRequestMethod("POST");
//            httpConn.setDoOutput(true);
//            httpConn.setDoInput(true);
//            httpConn.connect();
//            PrintWriter out = null;
//            out = new PrintWriter(httpConn.getOutputStream());
//            //发送请求参数即数据
//            out.print(data);
//            //缓冲数据
//            out.flush();
//            //获取httpConn对象对应的输入流
//            InputStream is = httpConn.getInputStream();
//            //构造一个字符流缓存
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            while ((str = br.readLine()) != null) {
//                System.out.println(str);
//            }
//            //关闭流
//            is.close();
//            httpConn.disconnect();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return str;
//
//    }


}
