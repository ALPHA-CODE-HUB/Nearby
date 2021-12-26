package com.example.nearby.Client;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.R;

public class Holder extends RecyclerView.ViewHolder {

    TextView name;
    public Holder(@NonNull View itemView) {
        super(itemView);
    }
    public void setItem(FragmentActivity activity,String fname,String lname,String email,String dob, String phone, String address, String pin, String gender, String id){
        name=itemView.findViewById(R.id.serviceprovidername);
        name.setText(fname+" "+lname);
    }
}
