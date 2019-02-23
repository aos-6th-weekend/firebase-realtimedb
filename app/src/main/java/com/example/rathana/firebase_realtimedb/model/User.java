package com.example.rathana.firebase_realtimedb.model;

public class User {
    private String uid;
    private String name;
    private String gender;
    private String password;

    public User() {
    }

    public User(String uid, String name, String gender, String password) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
