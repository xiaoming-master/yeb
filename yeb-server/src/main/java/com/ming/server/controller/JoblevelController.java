package com.ming.server.controller;


import com.ming.server.common.ResBean;
import com.ming.server.pojo.Joblevel;
import com.ming.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("system/basic/joblevel")
public class JoblevelController {
    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJobLevels() {
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称信息")
    @PostMapping("/")
    public ResBean addJobLevel(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return ResBean.success("添加成功");
        } else {
            return ResBean.error("添加失败");
        }
    }

    @ApiOperation(value = "更职称信息")
    @PutMapping("/")
    public ResBean updateJobLevel(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return ResBean.success("跟新职称信息成功");
        } else {
            return ResBean.error("跟新职称信息失败");
        }
    }

    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public ResBean deleteJobLevel(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return ResBean.success("删除成功");
        } else {
            return ResBean.success("删除失败");
        }
    }

    @ApiOperation(value = "批删除职称信息")
    @DeleteMapping("/")
    public ResBean deleteJobLevelByIds(Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return ResBean.success("删除成功");
        } else {
            return ResBean.error("删除失败");
        }
    }
}
