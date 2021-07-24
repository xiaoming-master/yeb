package com.ming.server.controller;


import com.ming.server.common.ResBean;
import com.ming.server.pojo.Position;
import com.ming.server.service.IPositionService;
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
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getPosition() {
        return positionService.list();
    }


    @ApiOperation(value = "添加职位")
    @PostMapping("/")
    public ResBean addPosition(@RequestBody Position position) {
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return ResBean.success("添加成功");
        } else {
            return ResBean.error("添加失败");
        }
    }

    @ApiOperation(value = "更新职位")
    @PutMapping("/")
    public ResBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return ResBean.success("更新成功");
        } else {
            return ResBean.error("更新失败");
        }
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public ResBean deletePosition(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return ResBean.success("删除成功");
        } else {
            return ResBean.error("删除失败");
        }
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public ResBean deletePositionByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return ResBean.success("删除成功");
        } else {
            return ResBean.error("删除失败");
        }
    }
}
