package com.codezl.huawei02.domain.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/05/27/13:59
 * @Description:
 */
@Data
public class Animal {

    String name;
    Integer age;

    public void say() {
        System.out.print(name+" sound !");
    }
}
