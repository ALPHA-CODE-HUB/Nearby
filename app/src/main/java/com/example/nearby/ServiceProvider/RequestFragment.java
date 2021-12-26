package com.example.nearby.ServiceProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.Client.RequestBean;
import com.example.nearby.Client.ViewHolder_;
import com.example.nearby.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RequestFragment extends Fragment {
    private EditText pin;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    RecyclerView recyclerView;

    public RequestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_request2, container, false);
        pin = v.findViewById(R.id.pin2);
        pin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    try {
                        if (pin.getText().toString().length() < 6) {

                            Toast.makeText(getActivity().getApplication(), "Invalid Pin, Minimum 6 digit", Toast.LENGTH_SHORT).show();

                        } else {
                            if (Integer.parseInt(pin.getText().toString()) > 670001 && Integer.parseInt(pin.getText().toString()) < 695615) {
                                databaseReference=database.getReference("Request").child(pin.getText().toString());
                                FirebaseRecyclerOptions<RequestBean> options= new FirebaseRecyclerOptions.Builder<RequestBean>().setQuery(databaseReference,RequestBean.class).build();
                                FirebaseRecyclerAdapter<RequestBean,ViewHolderClass> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<RequestBean, ViewHolderClass>(options) {
                                    @Override
                                    protected void onBindViewHolder(@NonNull ViewHolderClass holder, int position, @NonNull RequestBean model) {
                                        holder.setItem(getActivity(),model.getServiceRequest(),model.getDesc(),model.getDiff(), model.getDate(),model.getTimestamp(),model.getPin(),model.getRequests(),model.getUserId(), model.getList());
                                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Bundle bundle=new Bundle();
                                                    bundle.putString("ServiceRequest",model.getServiceRequest());
                                                    bundle.putString("Description", model.getDesc());
                                                    bundle.putString("Difficulty",model.getDiff());
                                                    bundle.putString("Date", model.getDate());
                                                    bundle.putString("RequestCount",String.valueOf(model.getRequests()));
                                                    bundle.putString("UserId",model.getUserId());
                                                    bundle.putString("Timestamp",String.valueOf(model.getTimestamp()));
                                                    RequestView requestView=new RequestView();
                                                    requestView.setArguments(bundle);
                                                    getFragmentManager().beginTransaction().replace(R.id.framelayout1,requestView).commit();

                                                    }
                                            });

                                    }

                                    @NonNull
                                    @Override
                                    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrv,parent,false);

                                        return  new ViewHolderClass(view);
                                    }
                                };
                                firebaseRecyclerAdapter.startListening();

                                recyclerView.setAdapter(firebaseRecyclerAdapter);
                                InputMethodManager imm = (InputMethodManager) getActivity().getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(pin.getWindowToken(), 0);

                            } else {
                                Toast.makeText(getActivity().getApplication(), "This pin code is out of kerala", Toast.LENGTH_SHORT).show();

                            }
                        }


                        return true;
                    } catch (Exception e) {
                        Toast.makeText(getActivity().getApplication(), "Invalid Pin", Toast.LENGTH_SHORT).show();

                    }
                }
                return false;
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=getActivity().findViewById(R.id.rv1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



    }
}