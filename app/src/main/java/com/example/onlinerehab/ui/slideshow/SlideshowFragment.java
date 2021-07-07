package com.example.onlinerehab.ui.slideshow;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.onlinerehab.R;
import com.example.onlinerehab.UserDetails;
import com.example.onlinerehab.psy;
import com.example.onlinerehab.psyList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

//    DatabaseReference databasePsychologist;
//    ListView listView;
//    ArrayAdapter<String> adapter;
//    String[] default_items = new String[]{"Name", "Speciality"};
//    List<psy> MessageList;
//    psy info;
//    List<UserDetails> PsyList;
    TextView txt;
    DatabaseReference ref;
    Button toPsy;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        txt = root.findViewById(R.id.tvText);
        ref = FirebaseDatabase.getInstance().getReference("addictsInformation");
        toPsy = root.findViewById(R.id.bt_toPsy);
        toPsy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), psyList.class);
                startActivity(intent);
            }
        });


//        listView = (ListView) root.findViewById(R.id.users_list);
//        databasePsychologist = FirebaseDatabase.getInstance().getReference("psyInformation");


//        databasePsychologist.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot myItem: dataSnapshot.getChildren()){
//                    psy psy = myItem.getValue(psy.class);
//                    ArrayList<UserInfo> arrayList = new ArrayList();
//
//                    itemList.add(psy.psy_name);
//                    itemList.add(psy.psy_speciality);
//                    itemList.add(psy.psy_phone);
//                }
//                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getContext(), "an error occured",Toast.LENGTH_SHORT).show();
//            }
//        });
//        PsyList = new ArrayList<UserDetails>();
//
//        PsyList.add(new UserDetails("tesst name1","bangi"));
//        PsyList.add(new UserDetails("test name2","keg"));
//
////        listView = (ListView) root.findViewById(R.id.users_list);
//
//        PsyListAdapter listAdapter = new PsyListAdapter(getContext(), R.layout.custompsy, PsyList);
//
//        listView.setAdapter(listAdapter);




//        databasePsychologist.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
