package com.ming.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.server.pojo.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 查询所有的部门
     *
     * @param parentId
     * @return
     */
    List<Department> getAllDepartment(@Param("parentId") Integer parentId);
}
