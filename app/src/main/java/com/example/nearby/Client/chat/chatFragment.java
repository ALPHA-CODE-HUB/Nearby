package com.example.nearby.Client.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.Authentication.UserAdapter;
import com.example.nearby.Authentication.UserBean;
import com.example.nearby.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class chatFragment extends Fragment {

    RecyclerView rec;
    UserAdapter adapter;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<UserBean> arrayList;
    FirebaseUser firebaseUser;
    LinearLayout tool;
    String uid;

    public chatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View v = inflater.inflate(R.layout.fragment_chat, container, false);



        return v;
    }
}