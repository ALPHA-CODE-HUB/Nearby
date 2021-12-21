package com.example.nearby.Client;

import android.app.Application;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.R;

public class ViewHolder_ extends RecyclerView.ViewHolder {

    TextView sr;
    TextView delete;

    public ViewHolder_(@NonNull View itemView) {
        super(itemView);
    }
    public void setItem(FragmentActivity activity,String serviceRequest,String desc, String diff,String date, long timestamp ){
        sr=itemView.findViewById(R.id.tvRV);
        sr.setText(serviceRequest);
        delete=itemView.findViewById(R.id.delete);
    }


}
