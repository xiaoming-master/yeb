package com.ming.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ming
 * @create: 2021-07-21 23:51
 * @program: yeb
 */
@RestController
public class TestController {

    @GetMapping("/employee/basic/")
    public String Test1() {
        return "/employee/basic/";
    }

    @GetMapping("/employee/advanced/")
    public String test2() {
        return "/employee/advanced/";
    }
}
