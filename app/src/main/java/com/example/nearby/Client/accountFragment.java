package com.example.nearby.Client;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.nearby.Client.account.About_us;
import com.example.nearby.Client.account.Privacy_policy;
import com.example.nearby.Client.account.contact_us;
import com.example.nearby.R;


public class accountFragment extends Fragment {

    LinearLayout l2,l3,l4;
    public accountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_account, container, false);
l2=v.findViewById(R.id.l2);
l3=v.findViewById(R.id.l3);
l4=v.findViewById(R.id.l4);
l3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity().getApplication(), Privacy_policy.class);
        startActivity(intent);
    }
});

l2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity().getApplication(), contact_us.class);
        startActivity(intent);
    }
});

l4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity().getApplication(), About_us.class);
        startActivity(intent);
    }
});
        return v;
    }
}