package com.ming.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.server.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根绝用户id查询角色
     *
     * @param id
     * @return
     */
    List<Role> getRolesByAdminId(@Param(value = "id") Integer id);
}
