package com.codezl.huawei02.designpattern;

import java.util.Date;
import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/06/08/15:13
 * @Description:
 */
public class ChatRoom {

    public static void sendMessage(ChatUser user,String message) {
        Consumer<String> consumer = System.out::print;
        consumer.accept(new Date().toString()+" [ "+user.getName()+" ] "+" :"+message);
    }

    public static void main(String[] args) {
        ChatUser john = new ChatUser("john");
        ChatUser davi = new ChatUser("davi");

        john.sendMsg("HI! davi."+"\n");
        davi.sendMsg("Hello, john.");
    }
}
