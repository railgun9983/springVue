package com.kk.novel.security.securitySimple;

import com.alibaba.fastjson.JSON;
import com.kk.novel.common.responseDoMain.ResponseDoMain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring security登录失败处理类
 */
@Slf4j
@Component
public class MyAuthenticationFailHandler implements AuthenticationFailureHandler   {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        System.out.println(e.getClass());
        if (e instanceof BadCredentialsException) {
            httpServletResponse.getWriter().write(JSON.toJSONString(ResponseDoMain.failure("", "登录失败,请检查用户名或者密码是否错误！")));
        } else if (e instanceof InternalAuthenticationServiceException){
            httpServletResponse.getWriter().write(JSON.toJSONString(ResponseDoMain.failure("", "登录失败,账户被锁定！")));
        }
        httpServletResponse.getWriter().flush();
    }
}
