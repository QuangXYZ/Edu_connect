package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;

public class TeacherTestChooseActivity extends AppCompatActivity {
    LinearLayout testEdit, testResult, testStatistic;
    Course course;
    Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_test_choose);
        init();
        settingUpListeners();
    }
    void init() {
        testEdit = findViewById(R.id.teacher_test_choose_test);
        testResult = findViewById(R.id.teacher_test_choose_result);
        testStatistic = findViewById(R.id.teacher_test_choose_statistic);
        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                course = intent.getSerializableExtra("Course", Course.class);
                test = intent.getSerializableExtra("Test", Test.class);
            }
            else {
                course = (Course) intent.getSerializableExtra("Course");
                test = (Test) intent.getSerializableExtra("Test");

            }
        }

    }
    void settingUpListeners() {
        testEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestEditActivity.class);
                intent.putExtra("Test", test);
                intent.putExtra("Course", course);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

            }
        });
        testResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeacherTestResultActivity.class);
                intent.putExtra("Test", test);
                intent.putExtra("Course", course);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

            }
        });

    }
}