package com.kaywalker.newone.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.kaywalker.newone.R;
import com.kaywalker.newone.adapter.myadapter;
import com.kaywalker.newone.model.dataholder;

public class Frag_List extends Fragment {

    private View view;
    RecyclerView recview;
    myadapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_frag_list, container, false);

        recview=(RecyclerView)view.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<dataholder> options =
                new FirebaseRecyclerOptions.Builder<dataholder>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users"), dataholder.class)
                        .build();

        adapter=new myadapter(options);
        recview.setAdapter(adapter);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}

