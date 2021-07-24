package com.ming.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.server.mapper.MenuMapper;
import com.ming.server.pojo.Admin;
import com.ming.server.pojo.Menu;
import com.ming.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

    public static final String REDIS_MENU_KEY_PRE = "menu_";


    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据用户id获取菜单
     *
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {

        //获取当前登陆用户id
        Integer id = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        String key = REDIS_MENU_KEY_PRE + id;
        List<Menu> menus = (List<Menu>) redisTemplate.opsForValue().get(key);

        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenuByAdminId(id);
            redisTemplate.opsForValue().set(key, menus);
        }

        return menus;
    }

    /**
     * 根据角色id获取菜单
     *
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 获取所有的菜单
     *
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
