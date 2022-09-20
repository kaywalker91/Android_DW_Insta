package com.kaywalker.newone;

import static com.kaywalker.newone.adapter.ImageAdapter.LoadChangedImage;
import static com.kaywalker.newone.adapter.ImageAdapter.PIKE_IMAGE_CODE;
import static com.kaywalker.newone.adapter.ImageAdapter.filepath;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.kaywalker.newone.fragment.Frag_Board;
import com.kaywalker.newone.fragment.Frag_Home;
import com.kaywalker.newone.fragment.Frag_List;
import com.kaywalker.newone.fragment.Frag_Profile;
import com.squareup.picasso.Picasso;

public class CenterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag_Home frag_home;
    private Frag_Board frag_board;
    private Frag_Profile frag_profile;
    private Frag_List frag_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_borad:
                        setFrag(1);
                        break;
                    case R.id.action_profile:
                        setFrag(2);
                        break;
                    case R.id.action_userlist:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        frag_home = new Frag_Home();
        frag_board = new Frag_Board();
        frag_profile = new Frag_Profile();
        frag_list = new Frag_List();

        setFrag(0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PIKE_IMAGE_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath = data.getData();
            Picasso.get().load(filepath).into(LoadChangedImage);
        }

    }

    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_frame, frag_home);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, frag_board);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, frag_profile);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, frag_list);
                ft.commit();
                break;
        }
    }

}