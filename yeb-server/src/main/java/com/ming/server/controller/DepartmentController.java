package com.ming.server.controller;


import com.ming.server.pojo.Department;
import com.ming.server.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhanglishen
 * @since 2021-07-17
 */
@RestController
@RequestMapping("system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

//    @ApiOperation(value = "添加部门")
//    @PostMapping("/")
//    public ResBean addDep(Department department) {
//
//    }
}
