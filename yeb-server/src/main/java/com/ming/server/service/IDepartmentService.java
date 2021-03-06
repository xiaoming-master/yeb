package com.ming.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.server.pojo.Department;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门
     *
     * @return
     */
    List<Department> getAllDepartment();
}
