package com.example.onlinerehab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListAdapter extends ArrayAdapter<Message> {
    Context mCtx;
    int resource;
    List<Message> MessageList;

    public ListAdapter(Context mCtx, int resource, List<Message> MessageList){
        super(mCtx, resource, MessageList);

        this.mCtx = mCtx;
        this.resource = resource;
        this.MessageList = MessageList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(resource, null);

        TextView user = view.findViewById(R.id.user_id);
        TextView user_message = view.findViewById(R.id.user_text);
        TextView date = view.findViewById(R.id.time_date_s);

        Message message = MessageList.get(position);
        user.setText(message.getUserId());
        user_message.setText(message.getMessage());
        date.setText(message.getDate());
        date.setText(message.getTime());

        return view;
    }
}
