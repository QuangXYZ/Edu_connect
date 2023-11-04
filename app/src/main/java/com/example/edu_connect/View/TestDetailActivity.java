package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.google.android.material.appbar.MaterialToolbar;

public class TestDetailActivity extends AppCompatActivity {

    Test test;
    MaterialToolbar toolbar;
    TextView title, question, date;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_detail);
        init();
        settingUpListeners();
    }
    void init(){
        toolbar = findViewById(R.id.test_detail_toolbar);
        title = findViewById(R.id.test_detail_title);
        question = findViewById(R.id.test_detail_question);

        date = findViewById(R.id.test_detail_date);


        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                test = intent.getSerializableExtra("Test", Test.class);
            }
            else {
                test = (Test) intent.getSerializableExtra("Test");
            }
        }

        title.setText("Tiêu đề: "+test.getTitle());
        question.setText("Tổng số câu hỏi: "+test.getQuestions().size());
        date.setText(test.getDate());
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