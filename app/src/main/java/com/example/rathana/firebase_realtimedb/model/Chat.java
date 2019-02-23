package com.example.rathana.firebase_realtimedb.model;

import java.security.PublicKey;
import java.sql.Time;
import java.sql.Timestamp;

public class Chat {
    private String uid;
    private User user;
    private String text;
    private String date;

    private int type;
    public static  final int OWNER=1;
    public static final int USER=2;

    public Chat() {
    }

    public Chat(String uid,User user, String text, String  date,int type) {
        this.user = user;
        this.uid=uid;
        this.text=text;
        this.date = date;
        this.type=type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
