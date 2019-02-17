package com.example.rathana.firebase_realtimedb;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
implements ValueEventListener {

    EditText message;
    Button btnSend;

    TextView result;

    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference ref= database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message=findViewById(R.id.message);
        btnSend=findViewById(R.id.btnSend);
        result=findViewById(R.id.result);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.setValue(message.getText().toString());
            }
        });

        ref.addValueEventListener(this);
    }



    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String value=dataSnapshot.getValue(String.class);
        result.setText(value);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
