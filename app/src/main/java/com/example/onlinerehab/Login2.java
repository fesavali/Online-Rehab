package com.example.onlinerehab;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login2 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();



        Button login0 = findViewById(R.id.butt_login3);
        TextView newUser = findViewById(R.id.txt_signupt3);

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup1 = new Intent(Login2.this, SignUp.class);
                startActivity(signup1);
            }
        });
        login0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }

        });

    }

    private void login() {
        EditText email = findViewById(R.id.edit_mail3);
        EditText pssw = findViewById(R.id.edit_password3);
        final ProgressBar loader = findViewById(R.id.re_progress3);

        String mail = email.getText().toString().trim();
        String password = pssw.getText().toString().trim();

        if (mail.isEmpty()) {
            email.setError("Email is Required");
            email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            pssw.setError("Password is Required");
            pssw.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Enter a Valid Email");
            email.requestFocus();
            return;
        }
        loader.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loader.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            String uId = mAuth.getUid();
                            String isAdmin = "3SCtHYQjgZMMWgc1lFaM14GFege2";
                                if(uId.matches(isAdmin)){
                                    Toast.makeText(Login2.this, "wewe ni admin",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Login2.this, Admin.class);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(Login2.this, "Use Logged in Successfully", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Login2.this, Profile.class);
                                    startActivity(intent);
                                }
                        }else{
                            Toast.makeText(Login2.this,"User Login failed",Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    }

