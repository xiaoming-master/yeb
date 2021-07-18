package com.ming.server.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ming.server.mapper.DepartmentMapper;
import com.ming.server.pojo.Department;
import com.ming.server.service.IDepartmentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
