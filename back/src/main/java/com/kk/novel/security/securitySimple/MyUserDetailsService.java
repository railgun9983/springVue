package com.kk.novel.security.securitySimple;

import com.kk.novel.security.jwt.JwtUser;
import com.kk.novel.userInfo.entity.TUser;
import com.kk.novel.userInfo.service.TUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 根据用户名获取数据库该用户信息，spring security在登录时自动调用
 * WebSecurityConfigurerAdapter会拿这里的User里的password与用户输入的对比验证
 */
@Slf4j
@Service
public  class MyUserDetailsService implements UserDetailsService {

    @Resource
    private TUserService tUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        TUser tUser = tUserService.queryByUserName(s);
        if (tUser == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        String role = tUserService.getRoleById(tUser.getUserId());
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        GrantedAuthority au = new SimpleGrantedAuthority(role);
        list.add(au);
        JwtUser JwtUser = new JwtUser(tUser.getUserId(), s, tUser.getPassword(), tUser.getStatus(), list);
        log.info(String.valueOf(JwtUser));
        return JwtUser;
    }
}
