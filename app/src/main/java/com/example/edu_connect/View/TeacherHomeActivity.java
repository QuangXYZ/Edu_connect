package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.edu_connect.Controller.TeacherHomeController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Adapter.CourseAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TeacherHomeActivity extends AppCompatActivity {


    FloatingActionButton fabAddClass;
    RecyclerView courseRecycleView;
    ArrayList<Course> courseArrayList;

    CourseAdapter courseAdapter;
    TeacherHomeController teacherHomeController;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        init();
        settingUpListeners();
    }
    void init(){
        fabAddClass = findViewById(R.id.teacher_home_float_btn);
        courseRecycleView = findViewById(R.id.teacherHomeListCourse);
        teacherHomeController = new TeacherHomeController();
        swipeRefreshLayout = findViewById(R.id.Teacher_home_swipe);
        courseArrayList = new ArrayList<>();
        courseAdapter = new CourseAdapter(courseArrayList,TeacherHomeActivity.this);
        courseRecycleView.setLayoutManager(new LinearLayoutManager(TeacherHomeActivity.this));
        courseRecycleView.setAdapter(courseAdapter);
        teacherHomeController.getAllCourse(new TeacherHomeController.Callback() {
            @Override
            public void onSuccess(List<Course> courses) {
                courseArrayList.addAll(courses);
                courseAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Exception e) {
                new MaterialAlertDialogBuilder(TeacherHomeActivity.this)
                        .setTitle("Error")
                        .setMessage(e.getMessage())
                        .setPositiveButton("OK", (dialog, which) -> {} ).show();
            }
        });



    }
    void settingUpListeners () {
        fabAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherHomeActivity.this, CreateClassActivity.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                teacherHomeController.getAllCourse(new TeacherHomeController.Callback() {
                    @Override
                    public void onSuccess(List<Course> courses) {
                        courseArrayList.clear();
                        courseArrayList.addAll(courses);
                        courseAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    @Override
                    public void onFailure(Exception e) {
                        new MaterialAlertDialogBuilder(TeacherHomeActivity.this)
                                .setTitle("Error")
                                .setMessage(e.getMessage())
                                .setPositiveButton("OK", (dialog, which) -> {} ).show();
                    }
                });
            }
        });
    }
}