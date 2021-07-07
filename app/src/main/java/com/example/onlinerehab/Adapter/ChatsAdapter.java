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
import com.example.onlinerehab.Model.Chat;
import com.example.onlinerehab.Model.User;
import com.example.onlinerehab.R;
import com.example.onlinerehab.ReplyAddict;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {

    private Context mContext;
    private List<Chat> mChat;

    public ChatsAdapter(Context mContext, List<Chat> mChat){
        this.mChat = mChat;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ChatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent,false);
        return new ChatsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsAdapter.ViewHolder holder, int position) {

        final Chat chat = mChat.get(position);
        holder.psyName.setText(chat.getSender());
        holder.psyPhone.setText(chat.getReceiver());
        holder.psySpeciality.setText(chat.getMessage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReplyAddict.class);
                intent.putExtra("psy_name", chat.getReceiver());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChat.size();
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
