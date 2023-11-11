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
import android.view.MenuItem;
import android.view.View;

import com.example.edu_connect.Controller.StudentHomeController;
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

public class StudentHomeActivity extends AppCompatActivity {
    FloatingActionButton fabAddClass;
    RecyclerView courseRecycleView;
    ArrayList<Course> courseArrayList;

    CourseAdapter courseAdapter;
    StudentHomeController studentHomeController;
    SwipeRefreshLayout swipeRefreshLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    MaterialToolbar toolbar;
    FirebaseAuthManager firebaseAuthManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        init();
        settingUpListeners();
    }
    void init(){
        fabAddClass = findViewById(R.id.student_home_float_btn);
        courseRecycleView = findViewById(R.id.student_home_ListCourse);
        studentHomeController = new StudentHomeController();
        swipeRefreshLayout = findViewById(R.id.student_home_swipe);
        firebaseAuthManager = new FirebaseAuthManager();
        courseArrayList = new ArrayList<>();
        courseAdapter = new CourseAdapter(courseArrayList,false,StudentHomeActivity.this);
        courseRecycleView.setLayoutManager(new LinearLayoutManager(StudentHomeActivity.this));
        courseRecycleView.setAdapter(courseAdapter);
        studentHomeController.getAllCourse(new StudentHomeController.Callback() {
            @Override
            public void onSuccess(List<Course> courses) {
                courseArrayList.addAll(courses);
                courseAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Exception e) {
                new MaterialAlertDialogBuilder(StudentHomeActivity.this)
                        .setTitle("Error")
                        .setMessage(e.getMessage())
                        .setPositiveButton("OK", (dialog, which) -> {} ).show();
            }
        });

        toolbar = findViewById(R.id.student_home_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.student_drawer_layour);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.student_nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.main_home) {

                }
                if (id == R.id.nav_stored) {
                    Intent intent = new Intent(StudentHomeActivity.this, CourseStoredActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_logout) {
                    Intent intent = new Intent(StudentHomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    firebaseAuthManager.logoutUser();

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
    void settingUpListeners () {
        fabAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomeActivity.this, InviteClassActivity.class);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                studentHomeController.getAllCourse(new StudentHomeController.Callback() {
                    @Override
                    public void onSuccess(List<Course> courses) {
                        courseArrayList.clear();
                        courseArrayList.addAll(courses);
                        courseAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    @Override
                    public void onFailure(Exception e) {
                        new MaterialAlertDialogBuilder(StudentHomeActivity.this)
                                .setTitle("Error")
                                .setMessage(e.getMessage())
                                .setPositiveButton("OK", (dialog, which) -> {} ).show();
                    }
                });
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}