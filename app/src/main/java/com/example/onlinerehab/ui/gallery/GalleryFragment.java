package com.example.onlinerehab.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.onlinerehab.ListAdapter;
import com.example.onlinerehab.Message;
import com.example.onlinerehab.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    List<Message> MessageList;
    ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        MessageList = new ArrayList<>();

        MessageList.add(new Message("user: Ajdjbkfhn5jsd7","hello guys how's the going","07:05pm","tarehe12/05/122"));
        MessageList.add(new Message("user: Djddnd5xdbdckd","am enjoying the process","07:15pm","tarehe12/05/1221"));
        MessageList.add(new Message("user: Ndhvch74bnxnvv","am two months clean","7:20pm","tarehe12/05/1222"));
        MessageList.add(new Message("user: Kbdbdnbcc9bxcc","lets engage our councilors more","7:05pm","tarehe12/05/1232"));

        listView = (ListView) root.findViewById(R.id.forum_listV);

        ListAdapter listAdapter = new ListAdapter(getContext(), R.layout.custom_list, MessageList);

        listView.setAdapter(listAdapter);

//        final TextView textView = root.findViewById(R.id.text_gallery);
//        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
