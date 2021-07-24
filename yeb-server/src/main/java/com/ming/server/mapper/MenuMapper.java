package com.ming.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.server.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户id获取菜单
     *
     * @param id
     * @return
     */
    List<Menu> getMenuByAdminId(@Param("id") Integer id);

    /**
     * 根据角色id获取菜单
     *
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 擦寻所有的菜单
     *
     * @return
     */
    List<Menu> getAllMenus();
}
