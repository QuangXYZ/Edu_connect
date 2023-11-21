package com.example.edu_connect.View;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.edu_connect.Controller.ProfileController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Model.Teacher;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;

public class DetailMemberCourse extends AppCompatActivity {
    TextView name, mssv, className, date, sdt, email;
    ImageView backBtn;
    ProfileController profileController;
    Student student;
    Button editBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        settingUpListener();
    }
    void init() {
        name = findViewById(R.id.profile_name);
        mssv = findViewById(R.id.profile_mssv);
        className = findViewById(R.id.profile_lop);
        date = findViewById(R.id.profile_date);
        sdt = findViewById(R.id.profile_phone);
        email = findViewById(R.id.profile_email);
        backBtn = findViewById(R.id.profile_back);
        editBtn = findViewById(R.id.profile_edit_btn);
        profileController = new ProfileController();
        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                student = intent.getSerializableExtra("Student", Student.class);

            }
            else {
                student = (Student) intent.getSerializableExtra("Student");


            }

        }
        editBtn.setVisibility(View.GONE);

        name.setText(student.getUserName());
        email.setText(student.getEmail());
        if (student.getBirthday()==null)
            date.setText("Không xác định");
        else date.setText(student.getBirthday());
        if (student.getPhoneNumber()==null)
            sdt.setText("Không xác định");
        else sdt.setText(student.getPhoneNumber());
        if (student.getClassName()==null)
            className.setText("Không xác định");
        else className.setText(student.getClassName());
        if (student.getMssv()==null)
            mssv.setText("Không xác định");
        else mssv.setText(student.getMssv());


    }
    void settingUpListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
