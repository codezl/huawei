package com.codezl.huawei02.controller;

import com.codezl.huawei02.domain.bo.InnerInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/07/05/15:24
 * @Description:
 */
@RestController
@RequestMapping("innerInterFace")
public class InnerInterfaceController {

    private static Print print;

    interface Print {
        void print(Object msg);
    }

    static {
        print = System.out::print;
    }

    public static void main(String[] args) {
        ConcurrentMap<String, InnerInterface.Addtion> add = InnerInterface.getAdd();
        InnerInterface.Addtion addtion = add.get("ADD");
        print.print(addtion.add(1,2)+"\n");
        ConcurrentMap<String, InnerInterface.Multi> mul = InnerInterface.getMul();
        InnerInterface.Multi multi = mul.get("MUL");
        print.print(multi.mul(2.2f,3.0f));
    }
}
