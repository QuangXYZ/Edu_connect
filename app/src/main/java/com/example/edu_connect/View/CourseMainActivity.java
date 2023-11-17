package com.example.edu_connect.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Fragment.CourseInfoFragment;
import com.example.edu_connect.View.Fragment.HomeFragment;
import com.example.edu_connect.View.Fragment.StudentsFragment;
import com.example.edu_connect.View.Fragment.TestFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class CourseMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private static final int FRAGMENT_HOME = 1;
    private static final int FRAGMENT_TEST = 2;
    private static final int FRAGMENT_MEMBER = 3;
    private static final int FRAGMENT_DETAIL= 4;
    private int currentFragment = FRAGMENT_HOME;
    Course course;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_main);
        init();
        settingUpListeners();

    }
    void init(){
        bottomNavigationView = findViewById(R.id.bottom_menu);
        toolbar = findViewById(R.id.class_main_toolbar);




        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                course = intent.getSerializableExtra("Course", Course.class);
            }
            else {
                course = (Course) intent.getSerializableExtra("Course");
            }
        }

        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setCourse(course);
        replaceFagment(homeFragment);
    }
    void settingUpListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_home:
                        if (currentFragment != FRAGMENT_HOME) {
                            HomeFragment homeFragment = new HomeFragment();
                            bottomNavigationView.getMenu().findItem( R.id.menu_home).setChecked(true);

                            homeFragment.setCourse(course);
                            replaceFagment(homeFragment);
                            currentFragment = FRAGMENT_HOME;
                        }
                        break;
                    case R.id.menu_test:
                        if (currentFragment != FRAGMENT_TEST) {
                            TestFragment testFragment = new TestFragment();
                            testFragment.setData(course);
                            bottomNavigationView.getMenu().findItem(R.id.menu_test).setChecked(true);


                            replaceFagment(testFragment);
                            currentFragment = FRAGMENT_TEST;
                        }
                        break;
                    case R.id.menu_member:
                        if (currentFragment != FRAGMENT_MEMBER) {
                            bottomNavigationView.getMenu().findItem(R.id.menu_member).setChecked(true);
                            StudentsFragment studentsFragment = new StudentsFragment();
                            studentsFragment.setData(course);
                            replaceFagment(studentsFragment);
                            currentFragment = FRAGMENT_MEMBER;
                        }
                        break;
                    case R.id.menu_info:
                        if (currentFragment != FRAGMENT_DETAIL) {
                            bottomNavigationView.getMenu().findItem(R.id.menu_info).setChecked(true);
                            CourseInfoFragment courseInfoFragment = new CourseInfoFragment();
                            courseInfoFragment.setCourse(course);
                            replaceFagment(courseInfoFragment);
                            currentFragment = FRAGMENT_DETAIL;
                        }
                        break;
                }
                return false;
            }
        });
    }
    private void replaceFagment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment);
        fragmentTransaction.commit();
    }
}