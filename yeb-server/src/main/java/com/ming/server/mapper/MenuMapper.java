package com.ming.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.server.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuByAdminId(@Param("id") Integer id);
}
