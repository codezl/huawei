package com.codezl.huawei02.controller;
 
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.springframework.beans.factory.annotation.Autowired;
 
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Service;

/**
 * @author ayue
 */
@Service
public class SockerChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Autowired
        private WebSocketServerHandler webSocketServerHandler;
 
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
                System.out.print("\n初始化链接"+socketChannel+"\n");
                ChannelPipeline pipeline = socketChannel.pipeline();
                //用于Http请求的编码或者解码
                pipeline.addLast("http-codec", new HttpServerCodec());
                //把Http消息组成完整地HTTP消息
                pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
                //向客户端发送HTML5文件
                pipeline.addLast("http-chunked", new ChunkedWriteHandler());
                //实际处理的Handler，名字好像是自定义？
                pipeline.addLast("handler", webSocketServerHandler);
                // 路径
                pipeline.addLast(new MyWsServerProtocolHandler("/{orderNo}"));
        System.out.print(pipeline);
        }
}