package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edu_connect.Controller.ProfileController;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Model.Teacher;
import com.example.edu_connect.R;

public class ProfileActivity extends AppCompatActivity {

    TextView name, mssv, className, date, sdt, email;
    ImageView backBtn;
    Button editBtn;
    ProfileController profileController;

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
        profileController.getProfile(new ProfileController.Callback() {
            @Override
            public void onStudentProfile(Student student) {
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

            @Override
            public void onTeacherProfile(Teacher teacher) {
                name.setText(teacher.getUserName());
                email.setText(teacher.getEmail());
                if (teacher.getBirthday()==null)
                    date.setText("Không xác định");
                else date.setText(teacher.getBirthday());
                if (teacher.getPhoneNumber()==null)
                    sdt.setText("Không xác định");
                else sdt.setText(teacher.getPhoneNumber());
                if (teacher.getClassName()==null)
                    className.setText("Không xác định");
                else className.setText(teacher.getClassName());
                if (teacher.getMsgv()==null)
                    mssv.setText("Không xác định");
                else mssv.setText(teacher.getMsgv());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });



    }
    void settingUpListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ProfileEditActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                finish();
            }
        });
    }
}