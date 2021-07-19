package com.ming.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.server.mapper.MenuMapper;
import com.ming.server.pojo.Admin;
import com.ming.server.pojo.Menu;
import com.ming.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据用户id获取菜单
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {

        Object principal = SecurityContextHolder.getContext().getAuthentication();
        //获取当前登陆用户id
        Integer id = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        List<Menu> menus = menuMapper.getMenuByAdminId(id);
        return menus;
    }
}
