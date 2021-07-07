package com.example.onlinerehab;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin extends AppCompatActivity {

    DatabaseReference databasePsychologist;
    EditText name;
    EditText mail;
    EditText phone;
    EditText speciality;
    EditText location;
    Button add_psy;
    DatabaseReference chat;
    EditText password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        databasePsychologist = FirebaseDatabase.getInstance().getReference("psyInformation");
        add_psy = findViewById(R.id.btn_add_psy);
        mAuth = FirebaseAuth.getInstance();

        add_psy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void save() {
        name = findViewById(R.id.psy_name);
        mail = findViewById(R.id.psy_loc);
        phone = findViewById(R.id.psy_mail);
        speciality = findViewById(R.id.psy_number);
        location = findViewById(R.id.psy_spc);
        password = findViewById(R.id.password);


        final String PNmae = name.getText().toString().trim();
        final String email = mail.getText().toString().trim();
        final String PPhone = phone.getText().toString().trim();
        final String specl = speciality.getText().toString().trim();
        final String place = location.getText().toString().trim();
        final String uid = databasePsychologist.push().getKey();
        final String passwo = password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(PPhone, passwo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Admin.this, "Auth Enabled",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Admin.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        psy psy = new psy(PNmae,specl,place);

        databasePsychologist.child(PNmae).setValue(psy);
        databasePsychologist.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(Admin.this, "psy added successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Admin.this, Login2.class);
                startActivity(intent);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Admin.this, "psy not added",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
