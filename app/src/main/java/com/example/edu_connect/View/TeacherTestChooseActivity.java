package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.edu_connect.Controller.TestController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.Repository.CourseRepository;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class TeacherTestChooseActivity extends AppCompatActivity {
    LinearLayout testEdit, testResult, testStatistic,testDelete;
    Course course;
    MaterialToolbar toolbar;
    Test test;
    TestController testController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_test_choose);
        init();
        settingUpListeners();
    }
    void init() {
        toolbar = findViewById(R.id.teacher_test_choose);
        testEdit = findViewById(R.id.teacher_test_choose_test);
        testResult = findViewById(R.id.teacher_test_choose_result);
        testStatistic = findViewById(R.id.teacher_test_choose_statistic);
        testDelete = findViewById(R.id.teacher_test_choose_delete);
        testController = new TestController();
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        testStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StatisticActivity.class);
                intent.putExtra("Test", test);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
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
        testDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(TeacherTestChooseActivity.this)
                        .setTitle("Confirm")
                        .setMessage("Bạn có chắc chắn")
                        .setNeutralButton("Có", (dialog, which) -> {
                            testController.deleteTest(course, test, new TestController.Callback() {
                                @Override
                                public void onSuccess() {
                                    new MaterialAlertDialogBuilder(TeacherTestChooseActivity.this)
                                            .setTitle("Success")
                                            .setMessage("Xóa thành công")
                                            .setPositiveButton("OK", (dialog, which) -> {finish();} ).show();
                                }

                                @Override
                                public void onFailure(Exception e) {

                                }
                            });
                        })
                        .setPositiveButton("Không", (dialog, which) -> {} ).show();
            }
        });

    }
}