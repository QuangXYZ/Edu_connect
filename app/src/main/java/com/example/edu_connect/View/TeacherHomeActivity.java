package com.example.edu_connect.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.edu_connect.Controller.TeacherHomeController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Adapter.CourseAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class TeacherHomeActivity extends AppCompatActivity  {


    FloatingActionButton fabAddClass;
    RecyclerView courseRecycleView;
    ArrayList<Course> courseArrayList;

    CourseAdapter courseAdapter;
    TeacherHomeController teacherHomeController;
    SwipeRefreshLayout swipeRefreshLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    MaterialToolbar toolbar;
    FirebaseAuthManager firebaseAuthManager;
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
        courseAdapter = new CourseAdapter(courseArrayList,false,TeacherHomeActivity.this);
        courseRecycleView.setLayoutManager(new LinearLayoutManager(TeacherHomeActivity.this));
        courseRecycleView.setAdapter(courseAdapter);
        firebaseAuthManager = new FirebaseAuthManager();
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
        toolbar = findViewById(R.id.teacher_home_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.teacher_home_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.main_home) {

                }
                else if (id == R.id.nav_logout) {
                    Intent intent = new Intent(TeacherHomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    firebaseAuthManager.logoutUser();

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        // to make the Navigation drawer icon always appear on the action bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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