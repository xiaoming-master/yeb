package com.ming.server.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限控制
 * 判断用户角色
 *
 * @Author: ming
 * @create: 2021-07-21 23:10
 * @program: yeb
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        for (ConfigAttribute configAttribute : configAttributes) {
            //获取当前url需要的role
            String needRole = configAttribute.getAttribute();

            //判断角色是否是登陆即可访问角色
            if ("ROLE_LOGIN".equals(needRole)) {
                //判断是否登陆 AnonymousAuthenticationToken用于匿名登陆
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new UsernameNotFoundException("尚未登陆，请登陆");
                } else return;
            }

            //判断用户是否为当前url所需的角色
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
