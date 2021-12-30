package com.example.nearby.Client.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class chatpageActivity extends AppCompatActivity {
    TextView username;
    Intent intent;

    FirebaseUser fuser;
    EditText message;

    CardView send;
    mAdapter messageadapter;

    DatabaseReference reference;
    List<cmodel> mchat;
    RecyclerView rec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatpage);
        send = findViewById(R.id.sendbtn);

        username = findViewById(R.id.uname);
        message = findViewById(R.id.message);
        intent = getIntent();
        String userid = intent.getStringExtra("userid");
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        rec = findViewById(R.id.messagebox);
        rec.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        rec.setLayoutManager(linearLayoutManager);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = message.getText().toString();
                if (!msg.equals("")) {
                    sendmessage(fuser.getUid(), userid, msg);
                } else {
                    Toast.makeText(chatpageActivity.this, "You can't send empty messages", Toast.LENGTH_SHORT).show();
                }
                message.setText("");
            }
        });
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s1,s2;
                model user = dataSnapshot.getValue(model.class);
                s1=user.getFname().toString();
                s2=user.getLname().toString();
                String myid=fuser.getUid();

                username.setText(s1+""+s2);
                readmessage(myid, userid.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void sendmessage(String sender, String reciver, String messages) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", reciver);
        hashMap.put("messages", messages);

        reference.child("Chats").push().setValue(hashMap);
    }

    private void readmessage(final String myid, final String userid) {
        mchat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cmodel chat = snapshot.getValue(cmodel.class);
                    if (chat.getReceiver().toString().equals(myid) && chat.getSender().toString().equals(userid) || chat.getReceiver().toString().equals(userid) && chat.getSender().toString().equals(myid)) {
                        mchat.add(chat);
                    }

                    messageadapter = new mAdapter(chatpageActivity.this, mchat);
                    rec.setAdapter(messageadapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}