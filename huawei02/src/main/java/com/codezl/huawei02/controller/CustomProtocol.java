package com.codezl.huawei02.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomProtocol implements Serializable {

    private static final long serialVersionUID = 4671171056588401542L;
    private long id ;
    private String content ;
    //省略 getter/setter

    public CustomProtocol() {}

    public CustomProtocol(long id, String content) {
        this.id = id;
        this.content = content;
    }
}