package com.kk.novel.security.securitySimple;

import com.alibaba.fastjson.JSON;
import com.kk.novel.common.responseDoMain.ResponseDoMain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Spring security权限不足处理类
 * 只有登录后（即接口有传token）接口权限不足才会进入AccessDeniedHandler，
 * 如果是未登陆或者会话超时等，不会触发AccessDeniedHandler，而是会直接跳转到登陆页面。
 */
@Slf4j
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //登陆状态下，权限不足执行该方法
        log.info("权限不足：" + e.getMessage());
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        response.getWriter().write(JSON.toJSONString(ResponseDoMain.failure("", "权限不足")));
        printWriter.flush();
    }
}
