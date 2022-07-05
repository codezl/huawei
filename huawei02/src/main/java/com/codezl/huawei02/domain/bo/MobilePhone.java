package com.codezl.huawei02.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/07/05/14:18
 * @Description:
 */
@Data
public class MobilePhone {

    private String name;
    private String brand;
    private Integer price;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publicTime;

    public interface MakePhoneBrand {
        MobilePhone make(String brand);
    }

    public interface MakePhone {
        MobilePhone makeName(String name);
        MobilePhone makeOne(String name,String brand,Integer price);
    }

}
