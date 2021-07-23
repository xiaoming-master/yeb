package com.ming.server.config.security.filter;

import com.ming.server.pojo.Menu;
import com.ming.server.pojo.Role;
import com.ming.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 将所有需要的权限加载到spring security
 *
 * @Author: ming
 * @create: 2021-07-21 22:13
 * @program: yeb
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService iMenuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 这个接口中的 getAttributes(Object object)方法能够根据请求的URL，获取这个URL所需要的权限，那么我们就可以在这个类初始化的时候将所有需要的权限加载进来，然后根据我们的规则进行获取
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) o;
        //获取请求url
        String requestUrl = filterInvocation.getRequestUrl();
        //获取所有角色对应的菜单权限
        List<Menu> menus = iMenuService.getMenusWithRole();
        for (Menu menu : menus) {
            //将权限路径与当前访问路径匹对
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {

                List<Role> roles = menu.getRoles();
                String[] roleNames = new String[roles.size()];

                for (int i = 0; i < roles.size(); i++) {
                    roleNames[i] = roles.get(i).getName();
                }
                //将所有的URL与访问这个URL所需要的权限的映射数据加载到Spring Security中。
                return SecurityConfig.createList(roleNames);
            }
        }
        //没匹配的url默认登陆即可访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
