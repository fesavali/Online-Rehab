package com.example.onlinerehab;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseReference databaseAddicts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseAddicts = FirebaseDatabase.getInstance().getReference("addictsInformation");

        Button save = findViewById(R.id.btn_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInformation();
            }
        });


    }
    private void saveInformation() {

        EditText phone = findViewById(R.id.edt_phone);
        EditText dob = findViewById(R.id.edt_dob);
        EditText drug = findViewById(R.id.edt_drug);
        EditText other = findViewById(R.id.editText4);
        EditText duration = findViewById(R.id.editText6);
        EditText location = findViewById(R.id.editText7);
        Spinner gender = findViewById(R.id.spinner_g);

        final ProgressBar loader = findViewById(R.id.reg_progress);

        String phoneN = phone.getText().toString().trim();
        String dobN = dob.getText().toString().trim();
        String drugN = drug.getText().toString().trim();
        String otherN = other.getText().toString().trim();
        String durationN = duration.getText().toString().trim();
        String locationN = location.getText().toString().trim();
        String genderN = gender.getSelectedItem().toString();

        if (phoneN.isEmpty()) {
            phone.setError("Phone Number Is Required");
            phone.requestFocus();
            return;
        }
        if (dobN.isEmpty()) {
            dob.setError("Date of Birth Is Required");
            dob.requestFocus();
            return;
        }
        if (drugN.isEmpty()) {
            drug.setError("Drug Is Required");
            drug.requestFocus();
            return;
        }

        if (durationN.isEmpty()) {
            duration.setError("Duration Is Required");
            duration.requestFocus();
            return;
        }
        if (locationN.isEmpty()) {
            location.setError("Location Is Required");
            location.requestFocus();
            return;
        }
        String uid = databaseAddicts.push().getKey();

        addict addict = new addict(uid, phoneN, dobN, drugN, otherN, durationN, locationN, genderN);

        databaseAddicts.child(uid).setValue(addict);
        loader.setVisibility(View.VISIBLE);
        databaseAddicts.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                loader.setVisibility(View.GONE);
                Toast.makeText(Register.this, "Information Saved Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Register.this, Profile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
                Toast.makeText(Register.this, "Registration Failed",Toast.LENGTH_LONG).show();
                Toast.makeText(Register.this, databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

}
