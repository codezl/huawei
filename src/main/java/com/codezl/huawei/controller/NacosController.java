package com.codezl.huawei.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/05/27/12:01
 * @Description:
 */
@RestController
@RequestMapping("huawei")
public class NacosController {

    @RequestMapping("1")
    public String addConfig() {
        System.out.print("111");
        return "success";
    }
}
