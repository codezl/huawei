package com.codezl.huawei02.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main1 {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 容器上下文对象
     */
    private static FileSystemXmlApplicationContext context;

    public static void main(String[] args) {
        Main1 main = new Main1();
        main.start();
    }

    private void start() {
        //加载spring
        context = new FileSystemXmlApplicationContext("classpath:config/netty.xml");
        logger.info("netty开始启动");
        //启动netty
        int port = 8080;
        try {
            HeartBeatServer heartBeatServer = new HeartBeatServer();
            heartBeatServer.start();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("netty启动异常" + e);
        }
    }
}
