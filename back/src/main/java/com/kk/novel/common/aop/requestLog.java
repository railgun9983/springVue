package com.kk.novel.common.aop;

import com.kk.novel.common.properties.BaseProperties;
import com.kk.novel.common.utils.HttpContextUtil;
import com.kk.novel.common.utils.IPUtil;
import com.kk.novel.security.jwt.JwtUtils;
import com.kk.novel.userInfo.entity.TLog;
import com.kk.novel.userInfo.service.TLogService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class requestLog {

    @Autowired
    private BaseProperties baseProperties;

    @Resource
    TLogService tLogService;

    @Pointcut("@annotation(com.kk.novel.common.annotation.requestLog)")
    public void pointcut(){};

    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 获取 request
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 设置 IP 地址
        String ip = IPUtil.getIpAddr(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        if (baseProperties.isOpenAopLog()) {
            // 保存日志
            String authHeader = request.getHeader("Authorization");
            String authToken = authHeader.substring("Bearer ".length());
            Claims Claims = JwtUtils.parseJWT(authToken);
            String username = (String) Claims.get("username");
            TLog log = new TLog();
            log.setUsername(username);
            log.setIp(ip);
            log.setTime(time);
            tLogService.insert(point, log);
        }
        return result;
    }

}
