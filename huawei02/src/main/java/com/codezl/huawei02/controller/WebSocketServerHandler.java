package com.codezl.huawei02.controller;
 
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
 
import java.net.InetSocketAddress;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
 
 
@Sharable
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        private WebSocketServerHandshaker handshaker;
 
        protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
                //传统的HTTP接入
                if (msg instanceof FullHttpRequest) {
                        handleHttpRequest(ctx, (FullHttpRequest) msg);
                } //WebSocket接入
                else if (msg instanceof WebSocketFrame) {
                        handleWebSocketFrame(ctx, (WebSocketFrame) msg);
                }
        }
 
        private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
                //判断是否关闭链路指令
                if (frame instanceof CloseWebSocketFrame) {
                        handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
                        return;
                }
                //判断是否是Ping消息
                if (frame instanceof PingWebSocketFrame) {
                        ctx.channel().write(new PongWebSocketFrame(frame.content()).retain());
                        return;
                }
                //本例程仅支持文本信息，不支持二进制消息
                if (!(frame instanceof TextWebSocketFrame)) {
                        throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
                }
                Channel channel = ctx.channel();
                InetSocketAddress insocket = (InetSocketAddress) channel.remoteAddress();
                String hostName = insocket.getHostName();
                String hostString = insocket.getHostString();
                int port = insocket.getPort();
                String clientIP = insocket.getAddress().getHostAddress();
                logger.info("客户端连接," + " clientIP:" + clientIP + " hostName:" + hostName + " hostString:" + hostString + " port:" + port);
               //接收到的消息，可进行后续操作
                String protocol = ((TextWebSocketFrame) frame).text();
                
                //消息返回
                ctx.channel().write(new TextWebSocketFrame(protocol + "欢迎使用Netty WebSocket服务，现在时刻:" + new Date().toString()));
        }
 
        private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
                //返回应答给客户端
                if (res.getStatus().code() != 200) {
                        ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
                        res.content().writeBytes(buf);
                        buf.release();
                        setContentLength(res, res.content().readableBytes());
                }
                //如果是非Keep-Alive，关闭连接
                ChannelFuture f = ctx.channel().writeAndFlush(res);
                if (!isKeepAlive(req) || res.getStatus().code() != 200) {
                        f.addListener(ChannelFutureListener.CLOSE);
                }
        }
 
        private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
                //如果HTTP解码失败，返回HTTP异常
                if (!req.getDecoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
                        sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
                        return;
                }
                //构造握手响应返回，本机测试
                WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
                handshaker = wsFactory.newHandshaker(req);
                if (handshaker == null) {
                        WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
                } else {
                        //把握手消息返回给客户端
                        handshaker.handshake(ctx.channel(), req);
                }
        }
 
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                ctx.flush();
        }
 
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                cause.printStackTrace();
                ctx.close();
        }
 
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                this.messageReceived(ctx, msg);
 
        }
 
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                super.channelActive(ctx);
        }
 
        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                super.channelInactive(ctx);
        }
 
}