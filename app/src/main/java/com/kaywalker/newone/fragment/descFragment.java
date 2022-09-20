package com.kaywalker.newone.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kaywalker.newone.R;


public class descFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    String name, course, email, purl;

    public descFragment() {

    }

    public descFragment(String name, String course, String email, String purl) {
        this.name = name;
        this.course = course;
        this.email = email;
        this.purl = purl;
    }

    public static descFragment newInstance(String param1, String param2) {
        descFragment fragment = new descFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_desc, container, false);

        ImageView imageholder=view.findViewById(R.id.imagegholder);
        TextView nameholder=view.findViewById(R.id.nameholder);
        TextView courseholder=view.findViewById(R.id.courseholder);
        TextView emailholder=view.findViewById(R.id.emailholder);

        nameholder.setText(name);
        courseholder.setText(course);
        emailholder.setText(email);
        Glide.with(getContext()).load(purl).into(imageholder);

        return view;
    }

    public void onBackPressed()
    {

        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Frag_List()).addToBackStack(null).commit();

    }

}