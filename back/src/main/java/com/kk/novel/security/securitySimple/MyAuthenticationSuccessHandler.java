package com.kk.novel.security.securitySimple;

import com.alibaba.fastjson.JSON;
import com.kk.novel.common.responseDoMain.ResponseDoMain;
import com.kk.novel.common.utils.AddressUtil;
import com.kk.novel.common.utils.DateFormat;
import com.kk.novel.common.utils.IPUtil;
import com.kk.novel.security.jwt.JwtUser;
import com.kk.novel.security.jwt.JwtUtils;
import com.kk.novel.userInfo.entity.TLoginLog;
import com.kk.novel.userInfo.service.TLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring security登录成功处理类,返回jwt
 */

@Slf4j
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    TLoginLogService tLoginLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");

        String ip = IPUtil.getIpAddr(httpServletRequest);
        String city = AddressUtil.getCityInfo(ip);

        //从authentication中获取用户信息
        final JwtUser userDetail = (JwtUser) authentication.getPrincipal();
        //生成jwt
        String token =  JwtUtils.createJwtToken(userDetail.getUserId() ,userDetail.getUsername(), userDetail.getAuthorities().toString());

        TLoginLog loginLog = new TLoginLog();
        loginLog.setIp(ip);
        loginLog.setLocation(city);
        loginLog.setUsername(userDetail.getUsername());
        loginLog.setLoginTime(new Date());

        tLoginLogService.insert(loginLog);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("userId", userDetail.getUserId());
        result.put("userName", userDetail.getUsername());

        httpServletResponse.addHeader("token", "Bearer " + token);
        httpServletResponse.setStatus(200);
        httpServletResponse.getWriter().write(JSON.toJSONString(ResponseDoMain.success(result, "登录成功")));
        httpServletResponse.getWriter().flush();
    }
}
