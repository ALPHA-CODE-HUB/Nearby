package com.example.nearby.ServiceProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nearby.Client.chat.Adapter;
import com.example.nearby.Client.chat.model;
import com.example.nearby.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment {
    private RecyclerView rec;
    private Adapter adapter;

    private List<model> Susers;
    private String uid;

    public ChatFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_chat2, container, false);


        rec = v.findViewById(R.id.rec1);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rec.setLayoutManager(manager);
        rec.setHasFixedSize(true);

        Susers = new ArrayList<>();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    model users1=snapshot.getValue(model.class);
                    assert users1 != null;
                    assert firebaseUser != null;
                    if(!users1.getId().equals(firebaseUser.getUid()))
                    {
                        Susers.add(users1);

                    }

                }
                adapter = new Adapter(getContext(), Susers);
                rec.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return v;
    }
}