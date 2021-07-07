package com.example.onlinerehab;

public class Message {
    public String UserId;
    public String message;
    public String time;
    public String date;

    public Message(String userId, String message, String time, String date) {
        UserId = userId;
        this.message = message;
        this.time = time;
        this.date = date;
    }

    public String getUserId() {
        return UserId;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
