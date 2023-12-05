package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.R;

public class ImageViewActivity extends AppCompatActivity {

    ImageView imageView;
    String imgLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        init();
    }
    void init(){
        imageView = findViewById(R.id.Image_view_layout);


        Intent intent = getIntent();
        if (intent!=null) {
            imgLink = intent.getStringExtra("URL");
        }
        Glide.with(this).load(imgLink).into(imageView);
    }
}