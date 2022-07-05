package com.codezl.huawei02.controller;

import com.codezl.huawei02.domain.bo.MobilePhone;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/07/05/14:26
 * @Description:
 */
@RestController
@RequestMapping("makePhone")
public class MakePhoneController {

    public static void main(String[] args) {
        MobilePhone.MakePhoneBrand makePhoneBrand = brand -> {
                MobilePhone phone = new MobilePhone();
                phone.setBrand(brand);
                return phone;
        };

        MobilePhone.MakePhone makePhone = new MobilePhone.MakePhone() {
            @Override
            public MobilePhone makeName(String name) {
                MobilePhone phone = new MobilePhone();
                phone.setName(name);
                return phone;
            }

            @Override
            public MobilePhone makeOne(String name, String brand, Integer price) {
                MobilePhone phone = new MobilePhone();
                phone.setName(name);
                phone.setBrand(brand);
                phone.setPrice(price);
                phone.setPublicTime(new Date());
                return phone;
            }
        };

        Consumer<Object> print = System.out::print;
        print.accept(makePhoneBrand.make("小米")+"\n");
        print.accept(makePhone.makeName("小米11")+"\n");
        print.accept(makePhone.makeOne("小米11","小米",4699));
    }
}
