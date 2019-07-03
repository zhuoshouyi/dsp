package com.topway.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.topway.enums.ResultEnum;
import com.topway.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by haizhi on 2019/5/15.
 */
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private PathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (isProtectedUrl(request)) {
                // 检查是否已有Authorization参数
                String token = request.getHeader("Authorization").substring(7);
                log.info("【认证】接收token=" + token);

                // 检查token是否符合要求
                Map<String, Claim> claimMap = JwtUtil.decode(token);
                // TODO 校验用户权限表,看用户是否在权限表中
            }

        }catch (Exception e){
            log.error("【认证】认证失败,token不符合规范");
            response.sendError(ResultEnum.USER_NOT_FOUND.getCode(), ResultEnum.USER_NOT_FOUND.getDesc());
            return;
        }

        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(request, response);
    }


    private boolean isProtectedUrl(HttpServletRequest request) {
        boolean flag;
        String skipUrl1 = "/account/login";
        String skipUrl2 = "/user/detail";
        String skipUrl3 = "/area/detail";
        String skipUrl4 = "/user/device/list";
//        String skipUrl5 = "/warning/list";

//        String authUrl1 = "/area/list";
//        String authUrl2 = "/user/list";
//        String authUrl3 = "/user/label/list";

        log.info("【认证】认证url:" + request.getServletPath());
        if (request.getServletPath().matches(skipUrl1) ||
                request.getServletPath().matches(skipUrl2) ||
                request.getServletPath().matches(skipUrl3) ){
            flag = false;
        } else {
            flag = true;
        }
//        System.out.println(flag);
        return flag;
    }

    private boolean isExceededUrl(HttpServletRequest request) {
        return pathMatcher.match("/dsp/acount/login", request.getServletPath());
    }
}
