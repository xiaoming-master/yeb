package com.ming.server.controller;


import com.ming.server.pojo.Menu;
import com.ming.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @ApiOperation(value = "根据用户id获取菜单")
    @GetMapping("/menu")
    public List<Menu> getMenus() {
        return menuService.getMenusByAdminId();
    }

}
