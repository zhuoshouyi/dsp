package com.topway.utils;

import com.auth0.jwt.interfaces.Claim;
import com.topway.convert.String2ListConvert;
import com.topway.dto.UserRoleDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by haizhi on 2019/6/18.
 */
@Slf4j
public class UserAuthentication {

    public static UserRoleDTO authentication(HttpServletRequest httpServletRequest){

        log.info("【认证】识别用户身份,判断权限");
        Map<String, Claim> claimMap = JwtUtil.getToken(httpServletRequest);

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setOpenId(claimMap.get("openId") == null? null : claimMap.get("openId").asString());
        log.info("【认证】openId:" + (claimMap.get("openId") == null? null : claimMap.get("openId").asString()));

        userRoleDTO.setUserId(claimMap.get("userId") == null? null : claimMap.get("userId").asString());
        log.info("【认证】userId:" + (claimMap.get("userId") == null? null : claimMap.get("userId").asString()));

        userRoleDTO.setUserName(claimMap.get("userName") == null? null : claimMap.get("userName").asString());
        log.info("【认证】userName:" + (claimMap.get("userName") == null? null : claimMap.get("userName").asString()));

        userRoleDTO.setUserRole(claimMap.get("userRole") == null? null : claimMap.get("userRole").asString());
        log.info("【认证】userRole:" + (claimMap.get("userRole") == null? null : claimMap.get("userRole").asString()));

        userRoleDTO.setServiceGridId(claimMap.get("serviceGridId") == null? null : String2ListConvert.convert(claimMap.get("serviceGridId").asString()));
        log.info("【认证】serviceGridId:" + (claimMap.get("serviceGridId") == null? null : String2ListConvert.convert(claimMap.get("serviceGridId").asString())));

        userRoleDTO.setSpcodeId(claimMap.get("spcodeId") == null? null : String2ListConvert.convertTo(claimMap.get("spcodeId").asString()));
        log.info("【认证】spcodeId:" + (claimMap.get("spcodeId") == null? null : String2ListConvert.convertTo(claimMap.get("spcodeId").asString())));

        userRoleDTO.setBusinessOfficeId(claimMap.get("businessOfficeId") == null? null : String2ListConvert.convertTo(claimMap.get("businessOfficeId").asString()));
        log.info("【认证】businessOfficeId:" + (claimMap.get("businessOfficeId") == null? null : String2ListConvert.convertTo(claimMap.get("businessOfficeId").asString())));

//        userRoleDTO.setServiceGrid(claimMap.get("serviceGrid") == null? null : String2ListConvert.convertTo(claimMap.get("serviceGrid").asString()));
//        log.info("【认证】serviceGrid:" + (claimMap.get("serviceGrid") == null? null : String2ListConvert.convertTo(claimMap.get("serviceGrid").asString())));
        return userRoleDTO;
    }
}
