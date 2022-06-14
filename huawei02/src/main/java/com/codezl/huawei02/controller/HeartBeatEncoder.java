package com.codezl.huawei02.controller;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/06/07/12:02
 * @Description:
 */
public class HeartBeatEncoder extends MessageToByteEncoder<CustomProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomProtocol msg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeLong(msg.getId());
        byteBuf.writeBytes(msg.getContent().getBytes());
    }
}
