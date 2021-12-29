package com.example.nearby.Client.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class mAdapter extends RecyclerView.Adapter<mAdapter.Viewholder> {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1  ;

    private Context context;
    private List<cmodel> schat;
    FirebaseUser fuser;


    public mAdapter(Context context, List<cmodel> schat) {
        this.schat = schat;
        this.context=context;


    }


    @NonNull
    @Override
    public mAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {

            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false);

            return new mAdapter.Viewholder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout, parent, false);

            return new mAdapter.Viewholder(view);

        }


    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        cmodel chat=schat.get(position);
        holder.show_message.setText(chat.getMessage());


    }

    @Override
    public int getItemCount() {
        return schat.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public TextView show_message;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.txtmessages);
        }

    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (schat.get(position).getSender().equals(fuser.getUid())) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}