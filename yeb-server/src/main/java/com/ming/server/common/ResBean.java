package com.ming.server.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回结果
 *
 * @Author: ming
 * @create: 2021-07-18 10:23
 * @program: yeb
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResBean {

    private int code;
    private String message;
    private Object data;

    /**
     * 成功返回结果
     *
     * @param message
     * @return
     */
    public static ResBean success(String message) {
        return new ResBean(200, message, null);
    }

    /**
     * 公成功返回结果
     *
     * @param message
     * @param data
     * @return
     */
    public static ResBean success(String message, Object data) {
        return new ResBean(200, message, data);
    }

    /**
     * 失败返回结果
     *
     * @param message
     * @return
     */
    public static ResBean error(String message) {
        return new ResBean(500, message, null);
    }

    /**
     * 失败返回结果
     *
     * @param message
     * @param data
     * @return
     */
    public static ResBean error(String message, Object data) {
        return new ResBean(500, message, data);
    }

}
