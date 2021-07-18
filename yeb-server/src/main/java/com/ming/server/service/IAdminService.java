package com.ming.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.server.common.ResBean;
import com.ming.server.pojo.Admin;
import com.ming.server.vo.AdminLoginParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登陆服务
     *
     * @param adminLoginParam
     * @param request
     * @return
     */
    ResBean login(AdminLoginParam adminLoginParam, HttpServletRequest request);

    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return
     */
    Admin getAdminInfoByUsername(String username);
}
