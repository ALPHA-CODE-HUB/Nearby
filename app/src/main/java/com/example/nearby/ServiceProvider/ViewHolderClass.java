package com.example.nearby.ServiceProvider;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.R;

public class ViewHolderClass extends RecyclerView.ViewHolder {

    TextView sr;
    public ViewHolderClass(@NonNull View itemView) {
        super(itemView);
    }
    public void setItem(FragmentActivity activity, String serviceRequest, String desc, String diff, String date, long timestamp, int pin){
        sr=itemView.findViewById(R.id.tvRV);
        sr.setText(serviceRequest);
    }
}

