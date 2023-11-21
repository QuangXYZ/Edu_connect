 package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Score;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Adapter.TestResultAdapter;
import com.example.edu_connect.View.Adapter.UrlAdapter;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

 public class TeacherTestResultActivity extends AppCompatActivity {
     Test test;
     MaterialToolbar toolbar;
     RecyclerView recyclerView;
     TestResultAdapter testResultAdapter;
     List<Score> scoreList;
     TextView noResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_test_result);
        init();
        settingUpListeners();
    }
     void init(){
         toolbar = findViewById(R.id.teacher_test_toolbar);
         noResult = findViewById(R.id.teacher_test_result_no_result);



         Intent intent = getIntent();
         if (intent!=null) {
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                 test = intent.getSerializableExtra("Test", Test.class);

             }
             else {
                 test = (Test) intent.getSerializableExtra("Test");

             }
         }
         recyclerView = findViewById(R.id.teacher_test_result_recycle_view);
         scoreList = new ArrayList<>();
         if (!test.getScores().entrySet().isEmpty()) {
             for (Map.Entry<String,Score> entry : test.getScores().entrySet()) {
                 scoreList.add(entry.getValue());
             }
             testResultAdapter = new TestResultAdapter(scoreList, TeacherTestResultActivity.this);
             recyclerView.setLayoutManager(new LinearLayoutManager(TeacherTestResultActivity.this));
             recyclerView.setAdapter(testResultAdapter);
             DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),LinearLayoutManager.VERTICAL);
             recyclerView.addItemDecoration(dividerItemDecoration);
             recyclerView.setNestedScrollingEnabled(true);
         }
         else {
             noResult.setVisibility(View.VISIBLE);

         }

         
     }
     void settingUpListeners() {
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
     }


}