package com.kk.novel.security.jwt;

import com.kk.novel.common.responseDoMain.ResponseDoMain;
import com.kk.novel.security.securitySimple.MyUserDetailsService;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token 过滤器，在这里解析token，拿到该用户角色，设置到security的上下文环境中，让security自动判断权限
 * 所有请求最先进入此过滤器，包括登录接口，而且在security的密码验证之前执行
 */
@Slf4j
@Component
public class MyJwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    private final String tokenHeader = "Authorization";
    private String tokenHead = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        httpServletRequest.getParameterMap();
        String authHeader = httpServletRequest.getHeader(tokenHeader);

        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            Claims Claims = JwtUtils.parseJWT(authToken);
            if (Claims != null) {
                String username = (String) Claims.get("username");
                //验证token,具体怎么验证看需求，可以只验证token不查库，把权限放在jwt中即可
                UserDetails UserDetails = myUserDetailsService.loadUserByUsername(username);
                if(JwtUtils.isTokenExpired(Claims)){//token过期
                    log.info("token过期" + username);
                }else{
                    log.info("token没过期，放行" + username);
                    //这里只要告诉security权限即可，账户密码就不用提供验证了，这里我们把UserDetails传给security，以便以后我们获取当前登录用户
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(UserDetails, null, UserDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    log.info(String.format("Authenticated userDetail %s, setting security context", username));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else {
                log.info("token过期");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
