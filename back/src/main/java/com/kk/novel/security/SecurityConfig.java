package com.kk.novel.security;

import com.kk.novel.security.jwt.MD5Util;
import com.kk.novel.security.jwt.MyJwtTokenFilter;
import com.kk.novel.security.securitySimple.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * Spring security的总配置类
 * 配置密码验证规则、拦截的url、登录接口地址、登录成功与失败后的处理器、各种异常处理器
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Spring会自动寻找同样类型的具体类注入，这里就是JwtUserDetailsServiceImpl了
    @Autowired
    MyUserDetailsService userDetailsService;
    //登录成功处理类，如返回自定义jwt
    @Autowired
    MyAuthenticationSuccessHandler authenticationSuccessHandler;
    //登录失败处理类
    @Autowired
    MyAuthenticationFailHandler authenticationFailHandler;
    //token 过滤器，解析token
    @Autowired
    MyJwtTokenFilter jwtTokenFilter;
    //权限不足处理类
    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;
    //其他异常处理类
    @Autowired
    MyAuthenticationException myAuthenticationException;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService 获取user对象
                .userDetailsService(this.userDetailsService)
                // 自定义密码验证方法
                .passwordEncoder(new PasswordEncoder() {
                    //这个方法没用
                    @Override
                    public String encode(CharSequence charSequence) {
                        return "";
                    }
                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
                        String pass = MD5Util.string2MD5(charSequence.toString());
                        System.out.println("用户输入密码:" + charSequence + "与数据库相同？" + s.equals(pass));
                        return s.equals(pass);
                    }
                });

    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()
                .loginProcessingUrl("/user/login") // 前端提交地址 必须表单提交，不然接受不到参数
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailHandler)
                .and()
                .csrf().disable()
                .cors() // 开启跨域
                .and()
                .headers().frameOptions().disable() //解决X-Frame-Options: DENY
                .and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() //处理跨域请求中的Preflight请求
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/test/*").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtTokenFilter, LogoutFilter.class)
                // 添加权限不足 filter
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                //其他异常处理类
                .authenticationEntryPoint(myAuthenticationException);

    }
}
