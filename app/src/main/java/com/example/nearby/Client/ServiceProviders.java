package com.example.nearby.Client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.Authentication.UserBean;
import com.example.nearby.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ServiceProviders extends Fragment {

    RequestBean bean;
    List<UserBean> list;
    RecyclerView recyclerView;
    HelperAdapter helperAdapter;
    DatabaseReference databaseReference;
    ArrayList<String> list1;

    public ServiceProviders() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_service_providers, container, false);

        Bundle bundle = this.getArguments();
        String uId = bundle.getString("UserId");

        String timeStamp = bundle.getString("Timestamp");

        FirebaseDatabase.getInstance().getReference().child("Requests").child(uId).child(timeStamp).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bean = snapshot.getValue(RequestBean.class);
                list1 = bean.getList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        recyclerView = v.findViewById(R.id.serviceprovidersrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));
        list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot da : snapshot.getChildren()) {
                    UserBean userBean = da.getValue(UserBean.class);
                    if (list1.contains(userBean.id)) {
                        list.add(userBean);
                    }
                }
                helperAdapter = new HelperAdapter(list);
                recyclerView.setAdapter(helperAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }

}