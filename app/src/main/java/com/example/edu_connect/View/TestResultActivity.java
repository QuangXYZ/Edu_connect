package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.edu_connect.Controller.TestController;
import com.example.edu_connect.Model.Question;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Adapter.QuestionAdapter;

import java.util.ArrayList;

public class TestResultActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    Test test;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        init();
        settingUpListeners();
    }
    void init() {
        textView = findViewById(R.id.test_result_score);
        button = findViewById(R.id.test_result_btn);



        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                test = intent.getSerializableExtra("Test", Test.class);
                score = intent.getIntExtra("Score", 0);
            }
            else {
                test = (Test) intent.getSerializableExtra("Test");
                score = intent.getIntExtra("Score", 0);
            }
        }
        textView.setText(score+"/"+test.getQuestions().size());


    }
    void settingUpListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}