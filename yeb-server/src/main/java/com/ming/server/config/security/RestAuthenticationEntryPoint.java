package com.ming.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ming.server.common.ResBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未登录，或者token失效时访问接口，自定义返回结果
 *
 * @Author: ming
 * @create: 2021-07-18 13:01
 * @program: yeb
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ResBean bean = ResBean.error("尚未登录，请登录");
        bean.setCode(401);
        writer.write(new ObjectMapper().writeValueAsString(bean));
    }
}
