package com.ming.server.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ming.server.common.ResBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当访问接口没有权限的返回结果
 *
 * @Author: ming
 * @create: 2021-07-18 13:11
 * @program: yeb
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        ResBean bean = ResBean.error("权限不足，请联系管理员");
        bean.setCode(401);
        writer.write(new ObjectMapper().writeValueAsString(bean));
    }
}
