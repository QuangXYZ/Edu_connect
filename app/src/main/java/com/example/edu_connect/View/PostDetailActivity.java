package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Adapter.UrlAdapter;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class PostDetailActivity extends AppCompatActivity {

    Post post;
    MaterialToolbar toolbar;
    TextView title, content, createBy, date;
    ArrayList<String> uriList;
    RecyclerView recyclerView;
    UrlAdapter urlAdapter;

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
        recyclerView = findViewById(R.id.post_detail_recycle_view);

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


        if (post.getFileUrl()!=null) {
            urlAdapter = new UrlAdapter(post.getFileUrl(), PostDetailActivity.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(PostDetailActivity.this));
            recyclerView.setAdapter(urlAdapter);
            recyclerView.setNestedScrollingEnabled(true);
        }



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