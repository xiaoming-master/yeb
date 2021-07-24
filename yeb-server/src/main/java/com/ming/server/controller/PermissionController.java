package com.ming.server.controller;

/**
 * 权限控制
 *
 * @Author: ming
 * @create: 2021-07-23 17:54
 * @program: yeb
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.server.common.ResBean;
import com.ming.server.pojo.Menu;
import com.ming.server.pojo.MenuRole;
import com.ming.server.pojo.Role;
import com.ming.server.service.IMenuRoleService;
import com.ming.server.service.IMenuService;
import com.ming.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "查询所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/")
    public ResBean addRole(@RequestBody Role role) {
        if (StringUtils.isEmpty(role.getName())) {
            return ResBean.error("角色名称不能为空");
        }
        if (role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return ResBean.success("添加成功");
        } else {
            return ResBean.error("添加失败");
        }
    }

//    @ApiOperation(value = "修改角色信息")
//    @PutMapping("/")
//    public ResBean updateRole(@RequestBody Role role) {
//        if (!StringUtils.isEmpty(role.getName()) && role.getName().startsWith("ROLE_")) {
//            role.setName("ROLE_" + role.getName());
//        }
//        if (roleService.updateById(role)) {
//            return ResBean.success("修改成功");
//        } else {
//            return ResBean.error("修改失败");
//        }
//    }

    @ApiOperation(value = "删除角色信息")
    @DeleteMapping("/role/{id}")
    public ResBean deleteRoleById(@PathVariable Integer id) {
        if (roleService.removeById(id)) {
            return ResBean.success("删除成功");
        } else {
            return ResBean.error("删除失败");
        }
    }

    @ApiOperation(value = "获取所有的菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMenuIdByRoleId(@PathVariable Integer rid) {
        List<MenuRole> menuRoles = menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid));
        List<Integer> list = new ArrayList<>();
        menuRoles.forEach(menuRole -> list.add(menuRole.getMid()));
        return list;
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public ResBean updateRoleMenu(Integer rid, Integer[] mids) {
        return menuRoleService.updateRoleMenu(rid, mids);
    }
}
