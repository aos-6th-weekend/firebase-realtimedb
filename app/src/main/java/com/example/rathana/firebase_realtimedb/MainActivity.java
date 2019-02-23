package com.example.rathana.firebase_realtimedb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rathana.firebase_realtimedb.data.UserPreference;
import com.example.rathana.firebase_realtimedb.model.Chat;
import com.example.rathana.firebase_realtimedb.model.User;
import com.example.rathana.firebase_realtimedb.service.FirebaseService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
implements ValueEventListener {


    private static final String TAG = "MainActivity";
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference ref;

    FirebaseService firebaseService;
    RecyclerView rvChat;
    ChatAdapter chatAdapter;
    List<Chat> chatList=new ArrayList<>();

    EditText tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init firebase service
        firebaseService=new FirebaseService();
        ref= firebaseService.initFireBase(FirebaseService.CHAT);

        if(UserPreference.read(this).getUid().equals("") || UserPreference.read(this).getUid()==null){
            startActivity(new Intent(this,SignupActivity.class));
            finish();
        }
        tvMessage=findViewById(R.id.message);
        rvChat=findViewById(R.id.rvChat);
        chatAdapter=new ChatAdapter(chatList);
        rvChat.setLayoutManager(new LinearLayoutManager(this));
        rvChat.setAdapter(chatAdapter);

        //read chat
        readChat(ref);


    }

    private void readChat(DatabaseReference ref) {
        //ref.addListenerForSingleValueEvent(this);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Chat chat= dataSnapshot.getValue(Chat.class);
                User user=chat.getUser();
                if(user.getUid().equals(UserPreference.read(MainActivity.this).getUid())){
                    chat.setType(Chat.OWNER);
                }else{
                    chat.setType(Chat.USER);
                }
                chatAdapter.addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.e(TAG, "onDataChange: "+dataSnapshot.getValue().toString());
        if(FirebaseService.CHAT_NODE_KEY!=null){

            Chat chat=dataSnapshot.child(FirebaseService.CHAT_NODE_KEY).
                    getValue(Chat.class);
            User user=chat.getUser();
            if(user.getUid()==UserPreference.read(this).getUid())
                chat.setType(Chat.OWNER);
            else
                chat.setType(Chat.USER);

            chatAdapter.addChat(chat);
        }else{
            Log.e(TAG, "onDataChange: No message " );
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    public void onSend(View view) {

        User user=UserPreference.read(this);
        Chat chat=new Chat();
        chat.setUser(user);
        chat.setType(Chat.OWNER);
        chat.setText(tvMessage.getText().toString());
        Date date=new Date(Calendar.getInstance().get(Calendar.DATE));
        chat.setDate(date.toString());

       /* chatAdapter.addChat(chat);*/
        rvChat.smoothScrollToPosition(chatList.size()-1);
        firebaseService.write(ref,chat);
    }
}
