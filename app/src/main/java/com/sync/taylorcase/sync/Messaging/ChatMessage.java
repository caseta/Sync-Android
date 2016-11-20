package com.sync.taylorcase.sync.Messaging;

import android.util.Log;

import java.util.Date;

public class ChatMessage {

    public String messageText;
    public String messageUser;
    public long messageTime;

    public ChatMessage(String messageText, String messageUser) {
        Log.e("YEETxxxxx", "5");
        this.messageText = messageText;
        Log.e("YEETxxxxx", "6");
        this.messageUser = messageUser;
        Log.e("YEETxxxxx", "7");
        messageTime = new Date().getTime();
    }

    public ChatMessage(){

    }

    public String getMessageText() {
        Log.e("YEETxxxxx", "9");
        return messageText;
    }

    public void setMessageText(String messageText) {
        Log.e("YEETxxxxx", "10");
        this.messageText = messageText;
    }

    public String getMessageUser() {
        Log.e("YEETxxxxx", "8");
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        Log.e("YEETxxxxx", "11");
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        Log.e("YEETxxxxx", "12");
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        Log.e("YEETxxxxx", "13");
        this.messageTime = messageTime;
    }
}