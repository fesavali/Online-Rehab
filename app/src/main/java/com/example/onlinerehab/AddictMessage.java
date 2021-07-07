package com.example.onlinerehab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinerehab.Adapter.MessageAdapter;
import com.example.onlinerehab.Model.Chat;
import com.example.onlinerehab.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddictMessage extends AppCompatActivity {
    FirebaseAuth mAuth;
    TextView psyName;
    TextView psyPhone;
    TextView psySpeciality;
    DatabaseReference reference;
    Intent intent;
    ImageButton btn_send;
    EditText msg_send;
    FirebaseUser fuser;

    MessageAdapter messageAdapter;
    List<Chat> mChat;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addict_message);

    mAuth = FirebaseAuth.getInstance();

        psyName = findViewById(R.id.psy_chat);
        psyPhone = findViewById(R.id.psy_phone_chat);
        psySpeciality = findViewById(R.id.psy_spec_chat);

        btn_send = findViewById(R.id.btn_message);
        msg_send = findViewById(R.id.text_mssg);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        intent = getIntent();
        final String psyChatName = intent.getStringExtra("psy_name");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msg_send.getText().toString().trim();
                if(!msg.equals("")){
                    sendMessage(fuser.getUid(), psyChatName, msg);
                }else{
                    Toast.makeText(AddictMessage.this, "You cant send Empty Text",Toast.LENGTH_SHORT).show();
                }
                msg_send.setText("");
            }
        });


        reference = FirebaseDatabase.getInstance().getReference("psyInformation").child(psyChatName);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                psyName.setText(user.getPsy_name());
                psyPhone.setText(user.getPsy_phone());
                psySpeciality.setText(user.getPsy_speciality());

                readMessages(user.getPsy_name(), psyChatName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void sendMessage(String sender, String receiver, String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);

    }
    private void readMessages(final String myId, final String userId){
        mChat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats").child(mAuth.getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if(chat.getReceiver().equals(myId) && chat.getSender().equals(userId) ||
                            chat.getReceiver().equals(userId) && chat.getSender().equals(myId)){
                        mChat.add(chat);
                    }
                    messageAdapter = new MessageAdapter(AddictMessage.this, mChat);
                    recyclerView.setAdapter(messageAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
