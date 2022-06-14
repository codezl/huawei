package com.codezl.huawei02.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/06/07/11:34
 * @Description:
 */
@Configuration
public class HeartBeatConfig {

    @Value("${channel.id:10}")
    private long id;

    @Bean(value = "heartBeat")
    public CustomProtocol heartBeat() {
        return new CustomProtocol(id,"ping");
    }

    public static CustomProtocol getHeatBeat() {
        HeartBeatConfig config = new HeartBeatConfig();
        return config.heartBeat();
    }
}
