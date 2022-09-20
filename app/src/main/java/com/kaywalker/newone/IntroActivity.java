package com.kaywalker.newone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ImageView imageView;

        imageView = findViewById(R.id.intro_im);

        Glide.with(this).load(R.raw.intro2).into(imageView);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                Intent main = new Intent(IntroActivity.this,MainActivity.class);
                startActivity(main);
                finish();
            }
        },2000);
    }
}