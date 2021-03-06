package com.ming.server.config.security;

import com.ming.server.config.security.filter.CustomFilter;
import com.ming.server.config.security.filter.JwtAuthenticationTokenFilter;
import com.ming.server.pojo.Admin;
import com.ming.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: ming
 * @create: 2021-07-18 11:29
 * @program: yeb
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private CustomUrlDecisionManager customUrlDecisionManager;

    @Autowired
    private CustomFilter customFilter;


    @Override
    public void configure(WebSecurity web) throws Exception {
        //需要直接放行的请求，不会进入过滤器链
        web.ignoring().mvcMatchers(
                "/css/**",
                "/login",
                "/js/**",
                "index.html",
                "/favicon.ico",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "v2/api-docs/**",
                "/captcha"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        //使用jwt不需要csrf
        http.csrf()
                .disable()
                .sessionManagement()
                //基于token不需要session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/logout", "/doc.html")
                .permitAll()
                //其他所有的请求都需要验证
                .anyRequest()
                .authenticated()
                //动态权限配置
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(customUrlDecisionManager);
                        o.setSecurityMetadataSource(customFilter);
                        return o;
                    }
                });
//                .and()
//                //不用缓存
//                .headers()
//                .cacheControl();
        //jwt验证过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加未授权，未登陆处理器
        http.exceptionHandling()
                //没有权限
                .accessDeniedHandler(restAccessDeniedHandler)
                //没有登陆
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    /**
     * 让security使用自定义的userDetailsService
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }


    /**
     * 重写userDetailsService
     *
     * @return
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Admin admin = adminService.getAdminInfoByUsername(username);
                if (null == admin) {
                    throw new UsernameNotFoundException("用户名或密码错误");
                }
                admin.setRoles(adminService.getRolesByAdminId(admin.getId()));
                return admin;
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }
}
