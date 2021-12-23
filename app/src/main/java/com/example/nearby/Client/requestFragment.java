package com.example.nearby.Client;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nearby.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class requestFragment extends Fragment {

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    public requestFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v=inflater.inflate(R.layout.fragment_request, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=getActivity().findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference=database.getReference("Requests").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        FirebaseRecyclerOptions<RequestBean> options= new FirebaseRecyclerOptions.Builder<RequestBean>().setQuery(databaseReference,RequestBean.class).build();

        FirebaseRecyclerAdapter<RequestBean,ViewHolder_> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<RequestBean, ViewHolder_>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_ holder, int position, @NonNull RequestBean model) {
                holder.setItem(getActivity(),model.getServiceRequest(),model.getDesc(),model.getDiff(), model.getDate(),model.getTimestamp(),model.getPin(),model.getService());
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference id=FirebaseDatabase.getInstance().getReference("Requests").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(String.valueOf(model.timestamp));
                        id.removeValue();
                    }
                });
            }

            @NonNull
            @Override
            public ViewHolder_ onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrv,parent,false);

                return  new ViewHolder_(view);

            }
        };
        firebaseRecyclerAdapter.startListening();

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}