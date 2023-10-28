package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.R;
import com.google.android.material.appbar.MaterialToolbar;

public class PostDetailActivity extends AppCompatActivity {

    Post post;
    MaterialToolbar toolbar;
    TextView title, content, createBy, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        init();
        settingUpListeners();
    }
    void init(){
        toolbar = findViewById(R.id.post_detail_toolbar);
        title = findViewById(R.id.post_detail_title);
        content = findViewById(R.id.post_detail_content);
        createBy = findViewById(R.id.post_detail_create_by);
        date = findViewById(R.id.post_detail_date);


        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                post = intent.getSerializableExtra("Post", Post.class);
            }
            else {
                post = (Post) intent.getSerializableExtra("Post");
            }
        }

        title.setText(post.getTitle());
        content.setText(post.getContent());
        createBy.setText(post.getCreateBy());
        date.setText(post.getDate());


    }
    void settingUpListeners(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}