package com.codezl.huawei02.controller;

import com.codezl.huawei02.domain.dto.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/05/27/13:58
 * @Description: 使用nacos自带的ribbon实现服务间调用
 */
@RequestMapping("02")
@RestController
public class Huawei02Controller {

    private static final String SERVER_NAME="http://huawei";

    @Autowired(required = false)
    RestTemplate restTemplate;

    @RequestMapping("animal")
    public void animal() {
        Animal a = new Animal();
        a.say();
    }

    @RequestMapping("2")
    public void ribbonServer() {
//        System.out.print(restTemplate.getMessageConverters());
        String res = restTemplate.getForObject(SERVER_NAME+"/huawei/1", String.class, "");
        System.out.print(res);
    }
}
