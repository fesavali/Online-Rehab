package com.example.onlinerehab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PsyLogin extends AppCompatActivity {
    EditText mail;
    EditText passwo;
    Button log;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psy_login);

        mail = findViewById(R.id.ed_emailp);
        passwo = findViewById(R.id.ed_passwordP);
        log = findViewById(R.id.tb_log);

        mAuth = FirebaseAuth.getInstance();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logInUser();
            }
        });
    }

    private void logInUser() {
        String email = mail.getText().toString().trim();
        String password = passwo.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(PsyLogin.this, AddictMessages.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(PsyLogin.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}