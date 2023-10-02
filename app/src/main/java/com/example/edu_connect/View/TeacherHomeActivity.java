package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.edu_connect.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeacherHomeActivity extends AppCompatActivity {


    FloatingActionButton fabAddClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        init();
        settingUpListeners();
    }
    void init(){
        fabAddClass = findViewById(R.id.teacher_home_float_btn);
    }
    void settingUpListeners () {
        fabAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherHomeActivity.this, CreateClassActivity.class);
                startActivity(intent);
            }
        });

    }
}