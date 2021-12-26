package com.example.nearby.Client;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.Authentication.UserBean;
import com.example.nearby.R;

import java.util.List;

public class HelperAdapter extends RecyclerView.Adapter {
    List<UserBean> list;

    public HelperAdapter(List<UserBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewserviceproviders,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);

        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass= (ViewHolderClass) holder;
        UserBean userBean=list.get(position);
        viewHolderClass.name.setText(userBean.fname);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView name;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.serviceprovidername);
        }
    }
}
