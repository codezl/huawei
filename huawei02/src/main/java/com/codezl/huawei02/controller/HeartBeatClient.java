package com.codezl.huawei02.controller;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.nio.channels.SocketChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/06/07/11:41
 * @Description:
 */
public class HeartBeatClient {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private final EventLoopGroup group = new NioEventLoopGroup();

    @Value("${netty.server.port:8081}")
    private int nettyPort;

    @Value("${netty.server.host:localhost}")
    private String host;

    private Channel channel;

    @PostConstruct
    public void start() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
        .channel(NioSocketChannel.class)
        .handler(new CustomerHanleInitializer());

        ChannelFuture future = bootstrap.connect("localhost", 8081).sync();

        if (future.isSuccess()) {
            LOG.info("启动 Netty 成功");
        }

        channel =  future.channel();
    }

    public class CustomerHanleInitializer extends ChannelInitializer<Channel> {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline()
                    .addLast(new IdleStateHandler(0,10,0))
                    .addLast(new HeartBeatEncoder())
                    .addLast(new MyNettyChannelHandler());
        }
    }
}
