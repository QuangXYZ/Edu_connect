package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.edu_connect.Controller.TestController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Question;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Adapter.QuestionAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TakeATestActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    ArrayList<Question> questionArrayList;
    RecyclerView recyclerView;
    QuestionAdapter questionAdapter;
    TestController testController;
    Test test;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_atest);
        init();
        settingUpListeners();
    }

    void init() {


        toolbar = findViewById(R.id.test_toolbar);


        testController = new TestController();
        questionArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.test_recycle_view);

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
//        questionArrayList = (ArrayList<Question>) test.getQuestions().clone();


        for (Question q : test.getQuestions()) {
            Question question = new Question();
            question.setTitle(q.getTitle());
            question.setOptions(q.getOptions());
            question.setCorrectOption(-1);
            questionArrayList.add(question);
        }
        questionAdapter = new QuestionAdapter(questionArrayList,TakeATestActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(TakeATestActivity.this));
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setNestedScrollingEnabled(true);


    }
    void settingUpListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.submit_test_btn) {
                    testController.submitTest(test, course, questionArrayList, new TestController.ScoreCallback() {
                                @Override
                                public void onSuccess(int score) {
                                    Intent intent = new Intent(TakeATestActivity.this, TestResultActivity.class);
                                    intent.putExtra("Test", test);
                                    intent.putExtra("Score", score);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                                    finish();
                                }

                                @Override
                                public void onFailure(Exception e) {

                                }
                            }
                    );
                    return true;
                }


                return false;
            }
        });
    }

}