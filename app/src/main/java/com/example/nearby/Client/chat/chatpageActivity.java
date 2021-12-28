package com.example.nearby.Client.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearby.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class chatpageActivity extends AppCompatActivity {
    TextView username;
    Intent intent;
    FirebaseUser fuser;
    EditText message;
    CardView send;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatpage);
        send = findViewById(R.id.sendbtn);

        username=findViewById(R.id.uname);
        message = findViewById(R.id.message);
        intent=getIntent();
        String userid= intent.getStringExtra("userid");
        fuser= FirebaseAuth.getInstance().getCurrentUser();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=message.getText().toString();
                if(!msg.equals(""))
                {
                    sendmessage(fuser.getUid(),userid,msg);
                }else{
                    Toast.makeText(chatpageActivity.this, "You can't send empty messages", Toast.LENGTH_SHORT).show();
                }
                message.setText("");
            }
        });
        reference= FirebaseDatabase.getInstance().getReference("Users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model user=dataSnapshot.getValue(model.class);
                username.setText(user.getFname());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void sendmessage(String sender,String reciver,String messages){
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",reciver);
        hashMap.put("messages",messages);

reference.child("Chats").push().setValue(hashMap);
    }
}