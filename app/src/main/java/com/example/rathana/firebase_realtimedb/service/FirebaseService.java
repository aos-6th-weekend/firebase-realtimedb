package com.example.rathana.firebase_realtimedb.service;

import com.example.rathana.firebase_realtimedb.model.Chat;
import com.example.rathana.firebase_realtimedb.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FirebaseService {

    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference ref;

    public  static final String USER_NODE="users";
    public  static final String CHAT="chat";
    public  static String USER_NODE_KEY;
    public  static String CHAT_NODE_KEY;

    public DatabaseReference initFireBase(String rootNode){
        return db.getReference(rootNode);
    }

    public  User writeUser(DatabaseReference ref, User user){
        USER_NODE_KEY =ref.push().getKey();
        Map<String,Object> updateValue=new HashMap<>();
        updateValue.put("/"+USER_NODE_KEY,user);
        user.setUid(USER_NODE_KEY);
        ref.updateChildren(updateValue);
        return user;
    }

    public Chat write(DatabaseReference ref, Chat chat){
        CHAT_NODE_KEY=ref.push().getKey();
        chat.setUid(CHAT_NODE_KEY);
        Map<String,Object> updateValue=new HashMap<>();
        updateValue.put("/"+CHAT_NODE_KEY,chat);
        ref.updateChildren(updateValue);
        return chat;
    }

}
