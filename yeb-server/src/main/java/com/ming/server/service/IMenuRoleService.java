package com.ming.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.server.common.ResBean;
import com.ming.server.pojo.MenuRole;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     *
     * @param rid
     * @param mids
     * @return
     */
    ResBean updateRoleMenu(Integer rid, Integer[] mids);
}
