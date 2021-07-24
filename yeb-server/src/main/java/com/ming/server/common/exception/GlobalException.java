package com.ming.server.common.exception;

import com.ming.server.common.ResBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 *
 * @Author: ming
 * @create: 2021-07-23 15:33
 * @program: yeb
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public ResBean mySqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return ResBean.error("该数据存在关联数据，操作失败");
        }
        return ResBean.error("数据库异常，操作失败");
    }
}
