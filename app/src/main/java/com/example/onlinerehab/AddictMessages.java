package com.example.onlinerehab;

import android.os.Bundle;

import com.example.onlinerehab.Adapter.ChatsAdapter;
import com.example.onlinerehab.Model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddictMessages extends AppCompatActivity {
    private RecyclerView recycler;
    private ChatsAdapter chatsAdapter;
    private List<Chat> mChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addict_messages);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycler = findViewById(R.id.recycler_viw);

        recycler.setHasFixedSize(true);

        recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mChat = new ArrayList<>();
        readChats();

    }

    private void readChats() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    mChat.add(chat);
                }
                chatsAdapter = new ChatsAdapter(getApplicationContext(), mChat);
                recycler.setAdapter(chatsAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        mAuth.signOut();
//    }
}