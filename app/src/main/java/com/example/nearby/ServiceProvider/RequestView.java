package com.example.nearby.ServiceProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearby.Client.RequestBean;
import com.example.nearby.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class RequestView extends Fragment {
    private TextView serreq, reqbtn, des,date,diff;
    RequestBean requestBean;



    public RequestView() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_request_view, container, false);
        serreq=v.findViewById(R.id.serviceRequest01);
        des=v.findViewById(R.id.description01);
        date=v.findViewById(R.id.datea);
        diff=v.findViewById(R.id.leveljob);
        reqbtn=v.findViewById(R.id.requestbtn);
        Bundle bundle=this.getArguments();
        serreq.setText(bundle.getString("ServiceRequest"));
        des.setText(bundle.getString("Description"));
        date.setText("Date of Posting: "+bundle.getString("Date"));
        diff.setText(bundle.getString("Difficulty"));
        FirebaseDatabase.getInstance().getReference().child("Requests").child(bundle.getString("UserId")).child(bundle.getString("Timestamp")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                requestBean=snapshot.getValue(RequestBean.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(requestBean.list.contains(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                    Toast.makeText(getActivity().getApplication(),"Your Request Limit Exceeded!",Toast.LENGTH_LONG).show();
                }else {
                    ArrayList<String> list=requestBean.getList();
                    list.add(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    HashMap hashMap=new HashMap();
                    hashMap.put("requests",requestBean.getRequests()+1);
                    hashMap.put("list",list);

                    FirebaseDatabase.getInstance().getReference().child("Requests").child(bundle.getString("UserId")).child(bundle.getString("Timestamp")).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getActivity().getApplication(),"Request has been sent to client",Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(getActivity().getApplication(),"Something happened! Try again later",Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }

            }
        });


        return v;
    }
}