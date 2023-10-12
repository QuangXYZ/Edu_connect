package com.example.edu_connect.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.edu_connect.R;
import com.example.edu_connect.View.Fragment.HomeFragment;
import com.example.edu_connect.View.Fragment.StudentsFragment;
import com.example.edu_connect.View.Fragment.TestFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class CourseMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private static final int FRAGMENT_HOME = 1;
    private static final int FRAGMENT_TEST = 2;
    private static final int FRAGMENT_MEMBER = 3;
    private int currentFragment = FRAGMENT_HOME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_main);
        init();
        settingUpListeners();

    }
    void init(){
        bottomNavigationView = findViewById(R.id.bottom_menu);
        replaceFagment(new HomeFragment());
    }
    void settingUpListeners() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.menu_home:
                        if (currentFragment != FRAGMENT_HOME) {
                            replaceFagment(new HomeFragment());
                            currentFragment = FRAGMENT_HOME;
                        }
                        break;
                    case R.id.menu_test:
                        if (currentFragment != FRAGMENT_TEST) {
                            replaceFagment(new TestFragment());
                            currentFragment = FRAGMENT_TEST;
                        }
                        break;
                    case R.id.menu_member:
                        if (currentFragment != FRAGMENT_MEMBER) {
                            replaceFagment(new StudentsFragment());
                            currentFragment = FRAGMENT_MEMBER;
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