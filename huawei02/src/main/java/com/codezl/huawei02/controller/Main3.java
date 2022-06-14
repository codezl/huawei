package com.codezl.huawei02.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/06/07/15:48
 * @Description:
 */
public class Main3 {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 容器上下文对象
     */
    private static FileSystemXmlApplicationContext context;

    public static void main(String[] args) {
        Main3 main = new Main3();
        main.start();
    }

    private void start() {
        //加载spring
        context = new FileSystemXmlApplicationContext("classpath:config/netty.xml");
        logger.info("netty开始启动");
        //启动netty
        int port = 8080;
        try {
            HeartBeatClient heartBeatClient = new HeartBeatClient();
            heartBeatClient.start();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("netty启动异常" + e);
        }
    }
}
