package com.ming.server.controller;

import com.ming.server.common.ResBean;
import com.ming.server.pojo.Admin;
import com.ming.server.service.IAdminService;
import com.ming.server.vo.AdminLoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 登陆接口
 *
 * @Author: ming
 * @create: 2021-07-18 10:41
 * @program: yeb
 */
@Api(value = "loginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService iAdminService;

    /**
     * 用户登陆接口
     *
     * @param adminLoginParam
     * @param request
     * @return
     */
    @ApiOperation(value = "登陆接口")
    @PostMapping("/login")
    public ResBean login(AdminLoginParam adminLoginParam, HttpServletRequest request) {
        return iAdminService.login(adminLoginParam, request);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param principal security的内置的对象
     * @return
     */
    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/admin/info")
    public Admin getUserInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        String username = principal.getName();
        Admin admin = iAdminService.getAdminInfoByUsername(username);
        admin.setRoles(iAdminService.getRolesByAdminId(admin.getId()));
        admin.setPassword("");
        return admin;
    }


    /**
     * 退出登陆
     *
     * @return
     */
    @ApiOperation(value = "注销")
    @PostMapping("/logout")
    public ResBean logout() {
        SecurityContextHolder.clearContext();
        return ResBean.success("注销成功");
    }

}
