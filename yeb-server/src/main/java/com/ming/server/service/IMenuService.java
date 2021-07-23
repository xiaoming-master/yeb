package com.ming.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据用户id获取菜单
     *
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据角色id获取菜单
     *
     * @return
     */
    List<Menu> getMenusWithRole();
}
