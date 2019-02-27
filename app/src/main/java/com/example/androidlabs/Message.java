package com.example.androidlabs;

public class Message {
    private String message;
    private boolean isSent = false;
    private long id;

    public Message(long id, String message, boolean isSent){
        this.message = message;
        this.isSent = isSent;
        this.id = id;
    }
    //chain constructor
    public Message(long id, String message){
        this.id = id;
        this.message = message;
    }
    public Message(){
        this.id = id;
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSent(boolean sent) {
        this.isSent = sent;
    }



    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSent() {
        return isSent;
    }



    public long getId() {
        return id;
    }
}
