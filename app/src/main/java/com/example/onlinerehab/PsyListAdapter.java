//package com.example.onlinerehab;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import java.util.List;
//
//public class PsyListAdapter extends ArrayAdapter<UserDetails> {
//    Context mCtx;
//    int resource;
//    List<UserDetails> PsyList;
//
//    public PsyListAdapter(Context mCtx, int resource, List<UserDetails> PsyList){
//        super(mCtx, resource, PsyList);
//        this.mCtx = mCtx;
//        this.resource = resource;
//        this.PsyList = PsyList;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(mCtx);
//        View view = inflater.inflate(resource, null);
//
//        TextView psy_user = view.findViewById(R.id.psy_name);
//        TextView user_spec = view.findViewById(R.id.psy_spec);
//
//        UserDetails psy = PsyList.get(position);
//
//        psy_user.setText(psy.getName());
//        user_spec.setText(psy.getSpeciality());
//
//        return view;
//    }
//}
