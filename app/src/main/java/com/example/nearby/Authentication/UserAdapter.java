package com.example.nearby.Authentication;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Viewholder>{
    Context application;
    ArrayList<UserBean> arrayList;
    public UserAdapter(Application application, ArrayList<UserBean> arrayList) {
        this.application=application;
        this.arrayList=arrayList;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(application).inflate(R.layout.user_row,parent,false);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        UserBean user=arrayList.get(position);
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(user.getId()))
        {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }
        holder.user_name.setText(user.fname);

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class Viewholder extends RecyclerView.ViewHolder
    {
        CircleImageView use_pro;
        TextView user_name;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            user_name=itemView.findViewById(R.id.name);
            use_pro = itemView.findViewById(R.id.profile_image);
        }
    }
}
