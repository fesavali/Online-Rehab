package com.frank.onlinerehab;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button login0 = findViewById(R.id.butt_login);
        TextView newUser = findViewById(R.id.txt_signupt);


        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup1 = new Intent(Login.this, SignUp.class);
                startActivity(signup1);
            }
        });

        login0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }

            private void login() {
                EditText email = findViewById(R.id.edit_mail);
                EditText pssw = findViewById(R.id.edit_password);

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
            }
        });
    }

}
