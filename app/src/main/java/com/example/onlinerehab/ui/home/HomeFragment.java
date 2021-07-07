package com.example.onlinerehab.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.onlinerehab.Home;
import com.example.onlinerehab.Profile;
import com.example.onlinerehab.R;
import com.example.onlinerehab.ui.slideshow.SlideshowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    DatabaseReference databaseAddicts;
    TextView ids;
    TextView dob;
    TextView gende;
    TextView location;
    TextView phon;
    TextView dru;
    ProgressBar loader;
    Button toChat;
    FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ids = root.findViewById(R.id.user_id);
        dob = root.findViewById(R.id.mail);
        gende = root.findViewById(R.id.gender);
        location = root.findViewById(R.id.location);
        phon = root.findViewById(R.id.phone);
        dru =  root.findViewById(R.id.drug);
        loader = root.findViewById(R.id.prof_loader);
        toChat = root.findViewById(R.id.toChats);

        mAuth = FirebaseAuth.getInstance();
        toChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mAuth.signOut();
              Intent intent = new Intent(getContext(), Home.class);
              startActivity(intent);
            }
        });

        retrieve();

//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
    private void retrieve() {
        loader.setVisibility(View.VISIBLE);
        databaseAddicts = FirebaseDatabase.getInstance().getReference("addictsInformation");

        databaseAddicts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String uid = ds.child("uid").getValue(String.class);
                    String dobd = ds.child("dob").getValue(String.class);
                    String local = ds.child("location").getValue(String.class);
                    String phone = ds.child("phone").getValue(String.class);
                    String gender = ds.child("gender").getValue(String.class);
                    String drug = ds.child("drug").getValue(String.class);

                    ids.setText(uid);
                    dob.setText(dobd);
                    gende.setText(gender);
                    location.setText(local);
                    phon.setText(phone);
                    dru.setText(drug);

                    loader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Failed to Connect to database",Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    }

