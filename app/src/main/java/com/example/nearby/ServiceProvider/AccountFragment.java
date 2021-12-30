package com.example.nearby.ServiceProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nearby.Authentication.LogIn;
import com.example.nearby.Authentication.UserBean;
import com.example.nearby.Client.account.About_us;
import com.example.nearby.Client.account.Privacy_policy;
import com.example.nearby.Client.account.contact_us;
import com.example.nearby.Client.chat.model;
import com.example.nearby.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AccountFragment extends Fragment {
    TextView tv1;
    LinearLayout l2, l3, l4;
    Button b1;
    FirebaseUser firebaseUser;
    DatabaseReference reference;


    public AccountFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_account2, container, false);
        l2 = v.findViewById(R.id.l2);
        l3 = v.findViewById(R.id.l3);
        tv1 = v.findViewById(R.id.nametv2);

        b1 = v.findViewById(R.id.sob1);
        l4 = v.findViewById(R.id.l4);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserBean user = dataSnapshot.getValue(UserBean.class);
                String s1 = user.getFname().toString();
                tv1.setText(s1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplication(), LogIn.class));
            }
        });


        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), Privacy_policy.class);
                startActivity(intent);
            }
        });

        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), contact_us.class);
                startActivity(intent);
            }
        });

        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), About_us.class);
                startActivity(intent);
            }
        });
        return v;

    }
}