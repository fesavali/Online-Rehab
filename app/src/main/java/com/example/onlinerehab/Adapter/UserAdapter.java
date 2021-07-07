package com.example.onlinerehab.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinerehab.MessageActivity;
import com.example.onlinerehab.Model.User;
import com.example.onlinerehab.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;

    public UserAdapter(Context mContext, List<User> mUsers){
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = mUsers.get(position);
        holder.psyName.setText(user.getPsy_name());
        holder.psyPhone.setText(user.getPsy_phone());
        holder.psySpeciality.setText(user.getPsy_speciality());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("psy_name", user.getPsy_name());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView psyName;
        public TextView psyPhone;
        public TextView psySpeciality;

        public ViewHolder(View itemView){
            super(itemView);

            psyName = itemView.findViewById(R.id.username);
            psyPhone = itemView.findViewById(R.id.phone);
            psySpeciality = itemView.findViewById(R.id.spec);
        }
    }
}
