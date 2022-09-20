package com.kaywalker.newone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class NewActivity extends AppCompatActivity {

    private ImageView fullImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        fullImageView = findViewById(R.id.fullImageView);

        Glide.with(this).load(getIntent().getStringExtra("image@#"))
                .into(fullImageView);

    }
}