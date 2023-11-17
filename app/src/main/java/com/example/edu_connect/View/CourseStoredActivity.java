package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.edu_connect.Controller.StudentHomeController;
import com.example.edu_connect.Controller.TeacherHomeController;
import com.example.edu_connect.Controller.TestController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Question;
import com.example.edu_connect.R;
import com.example.edu_connect.Shared.DataLocalManager;
import com.example.edu_connect.View.Adapter.CourseAdapter;
import com.example.edu_connect.View.Adapter.QuestionAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class CourseStoredActivity extends AppCompatActivity {

    MaterialToolbar toolbar;
    ArrayList<Course> courseArrayList;
    SwipeRefreshLayout swipeRefreshLayout;
    CourseAdapter courseAdapter;
    TeacherHomeController teacherHomeController;
    StudentHomeController studentHomeController;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_stored);
        init();
        settingUpListeners();
    }
    void init() {
        toolbar = findViewById(R.id.student_stored_course);
        swipeRefreshLayout = findViewById(R.id.course_stored_swipe);
        recyclerView = findViewById(R.id.student_stored_list_course);
        studentHomeController = new StudentHomeController();
        teacherHomeController = new TeacherHomeController();
        courseArrayList = new ArrayList<>();
        courseAdapter = new CourseAdapter(courseArrayList,CourseStoredActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(CourseStoredActivity.this));
        recyclerView.setAdapter(courseAdapter);
        if (DataLocalManager.getUserIsTeacher()){
            teacherHomeController.getAllCourse(new TeacherHomeController.Callback() {
                @Override
                public void onSuccess(List<Course> courses) {
                    for (Course course : courses)
                        if (course.isStored()) courseArrayList.add(course);

                    courseAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Exception e) {
                    new MaterialAlertDialogBuilder(CourseStoredActivity.this)
                            .setTitle("Error")
                            .setMessage(e.getMessage())
                            .setPositiveButton("OK", (dialog, which) -> {} ).show();
                }
            });
        }
        else
        studentHomeController.getAllCourse(new StudentHomeController.Callback() {
            @Override
            public void onSuccess(List<Course> courses) {
                for (Course course : courses)
                    if (course.isStored()) courseArrayList.add(course);

                courseAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Exception e) {
                new MaterialAlertDialogBuilder(CourseStoredActivity.this)
                        .setTitle("Error")
                        .setMessage(e.getMessage())
                        .setPositiveButton("OK", (dialog, which) -> {} ).show();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                studentHomeController.getAllCourse(new StudentHomeController.Callback() {
                    @Override
                    public void onSuccess(List<Course> courses) {
                        courseArrayList.clear();
                        for (Course course : courses)
                            if (course.isStored()) courseArrayList.add(course);
                        courseAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    @Override
                    public void onFailure(Exception e) {
                        new MaterialAlertDialogBuilder(CourseStoredActivity.this)
                                .setTitle("Error")
                                .setMessage(e.getMessage())
                                .setPositiveButton("OK", (dialog, which) -> {} ).show();
                    }
                });
            }
        });



    }
    void settingUpListeners(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });}
}