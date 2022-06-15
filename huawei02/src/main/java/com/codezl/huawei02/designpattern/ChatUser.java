package com.codezl.huawei02.designpattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: code-zl
 * @Date: 2022/06/08/15:14
 * @Description:
 */
public class ChatUser {

    private String name;
    private String message;

    public ChatUser(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void sendMsg(String msg) {
        ChatRoom.sendMessage(this, msg);
    }
}
