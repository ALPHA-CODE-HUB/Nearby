package com.example.nearby.Client.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearby.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private Context context;
    private List<model> users;

    public Adapter(Context context, List<model> users) {
        this.users = users;
        this.context = context;

    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_row, parent, false);

        return new Adapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        model user = users.get(position);
        holder.username.setText(user.getFname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,chatpageActivity.class);
                intent.putExtra("userid",user.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public TextView username;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.usname);
        }
    }
}
