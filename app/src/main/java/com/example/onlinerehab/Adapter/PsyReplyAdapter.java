package com.example.onlinerehab.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinerehab.Model.Chat;
import com.example.onlinerehab.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class PsyReplyAdapter extends RecyclerView.Adapter<PsyReplyAdapter.ViewHolder> {

private static final int MSG_TYPE_RIGHT = 0;
private static final int MSG_TYPE_LEFT = 1;
private Context mContext;
private List<Chat> mChat;
FirebaseUser fuser;

public PsyReplyAdapter(Context mContext, List<Chat> mChat){
        this.mChat = mChat;
        this.mContext = mContext;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT){
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent,false);
        return new PsyReplyAdapter.ViewHolder(view);
        }else{
        View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent,false);
        return new PsyReplyAdapter.ViewHolder(view);
        }
        }

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat chat = mChat.get(position);
        holder.show_message.setText(chat.getMessage());
        }

@Override
public int getItemCount() {
        return mChat.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{

    public TextView show_message;


    public ViewHolder(View itemView){
        super(itemView);

        show_message = itemView.findViewById(R.id.show_message);

    }
}

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        }else{
            return MSG_TYPE_LEFT;
        }
    }
}
