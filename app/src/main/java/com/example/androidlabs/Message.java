package com.example.androidlabs;

public class Message {
    private String message;
    private String action;

    public Message(String message, String action){
        this.message = message;
        this.action = action;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public void setAction(String action){
        this.action = action;
    }
    public String getMessage(){
        return message;
    }
    public String getAction(){
        return action;
    }
}
