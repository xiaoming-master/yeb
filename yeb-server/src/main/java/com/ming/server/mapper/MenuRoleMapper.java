package com.ming.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 插入角色菜单信息
     *
     * @param rid  角色id
     * @param mids 菜单id数组
     */
    Integer insertRecode(@Param("rid") Integer rid, @Param("mids") Integer[] mids);

}
