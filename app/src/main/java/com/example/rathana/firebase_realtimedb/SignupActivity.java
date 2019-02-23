package com.example.rathana.firebase_realtimedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.rathana.firebase_realtimedb.data.UserPreference;
import com.example.rathana.firebase_realtimedb.model.User;
import com.example.rathana.firebase_realtimedb.service.FirebaseService;
import com.google.firebase.database.DatabaseReference;

public class SignupActivity extends AppCompatActivity {

    EditText username,password,gender;
    Button btnSave;
    FirebaseService firebaseService;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        gender=findViewById(R.id.gender);
        btnSave=findViewById(R.id.btnSave);

        firebaseService=new FirebaseService();
        ref= firebaseService.initFireBase(FirebaseService.USER_NODE);

        btnSave.setOnClickListener(v->{

            User user=new User();
            user.setName(username.getText().toString());
            user.setGender(gender.getText().toString());
            user.setPassword(password.getText().toString());

            User u= firebaseService.writeUser(ref,user);
            UserPreference.save(this,u);
            startActivity(new Intent(this,MainActivity.class));
            finish();
        });

    }
}
