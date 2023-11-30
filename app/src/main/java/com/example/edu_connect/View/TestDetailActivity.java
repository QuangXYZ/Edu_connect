package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edu_connect.Controller.TestController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Model.Score;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.Repository.CourseRepository;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class TestDetailActivity extends AppCompatActivity {

    Test test;
    MaterialToolbar toolbar;
    TextView title, question, date;
    Button btn;
    Course course;
    LinearLayout result;
    TextView scoretv;
    TestController testController;
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
        btn = findViewById(R.id.test_detail_btn);
        date = findViewById(R.id.test_detail_date);
        result = findViewById(R.id.test_detail_complex);
        scoretv =findViewById(R.id.test_detail_score);
        testController = new TestController();



        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                test = intent.getSerializableExtra("Test", Test.class);
                course = intent.getSerializableExtra("Course", Course.class);
            }
            else {
                test = (Test) intent.getSerializableExtra("Test");
                course = (Course) intent.getSerializableExtra("Course");
            }
        }
        testController.isTestComplete(test, course, new TestController.IsTestConpleteCallback() {
            @Override
            public void onComplete(Score score) {

                btn.setVisibility(View.GONE);
                scoretv.setText(scoretv.getText().toString()+score.getScore());
            }

            @Override
            public void onNotComplete() {
                result.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(TestDetailActivity.this)
                        .setTitle("Bạn có chắc chắn")
                        .setMessage("Chỉ được làm bài một lần")
                        .setNeutralButton("Có", (dialog, which) -> {
                            Intent intent = new Intent(TestDetailActivity.this, TakeATestActivity.class);
                            intent.putExtra("Test", test);
                            intent.putExtra("Course", course);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                            finish();
                        })
                        .setPositiveButton("Không", (dialog, which) -> {} ).show();

            }
        });

    }
}