package com.example.chigratitude;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageFullActivity extends AppCompatActivity {

    private ImageView fullimageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full);

        fullimageview = findViewById(R.id.fullimageView);
        Glide.with(this).load(getIntent().getStringExtra("image"))
                .into(fullimageview);
    }
}