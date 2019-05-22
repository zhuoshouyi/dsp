package com.topway.jwt;

import com.topway.utils.JwtUtil;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by haizhi on 2019/5/15.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private PathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (isProtectedUrl(request)) {
                // 检查是否已有Authorization参数
                String token = request.getHeader("Authorization");
                System.out.println("接收token");

                // 检查token是否符合要求
                JwtUtil.decode(token);
            }

        }catch (Exception e){
            System.out.println("2");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
        filterChain.doFilter(request, response);
    }


    private boolean isProtectedUrl(HttpServletRequest request) {
        boolean flag;
        String skipUrl1 = "/account/login";
        String skipUrl2 = "/warning/list";

        if (request.getServletPath().matches(skipUrl1) || request.getServletPath().matches(skipUrl2)){
            flag = false;
        } else {
            flag = true;
        }
        System.out.println(flag);
        return flag;
    }

    private boolean isExceededUrl(HttpServletRequest request) {
        return pathMatcher.match("/dsp/acount/login", request.getServletPath());
    }
}
