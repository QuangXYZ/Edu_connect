package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.edu_connect.Controller.CreateClassController;
import com.example.edu_connect.Controller.InviteClassController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class InviteClassActivity extends AppCompatActivity {
    TextInputEditText code;
    Button createBtn;
    MaterialToolbar toolbar;
    InviteClassController inviteClassController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_class);
        init();
        settingUpListeners();

    }

    void init() {

        code = findViewById(R.id.invite_class_code);
        createBtn = findViewById(R.id.invite_class_btn);
        toolbar = findViewById(R.id.invite_class_toolbar);
        inviteClassController = new InviteClassController();
    }

    void settingUpListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (code.getText().toString().isEmpty()) {
                    code.setError("Nhập invite code");
                    code.requestFocus();
                }
                else inviteClassController.getCourse(code.getText().toString(), new InviteClassController.GetCourseCallback() {
                    @Override
                    public void onSuccess(Course course) {
                        Log.d("Course",course.toString());
                        inviteClassController.inviteClass(course, new InviteClassController.Callback() {
                            @Override
                            public void onSuccess() {
                                new MaterialAlertDialogBuilder(InviteClassActivity.this)
                                        .setTitle("Thành công")
                                        .setMessage("Đã tham gia vào lớp")
                                        .setPositiveButton("OK", (dialog, which) -> {
                                            finish();
                                        } ).show();
                            }

                            @Override
                            public void onFailure(Exception e) {
                                new MaterialAlertDialogBuilder(InviteClassActivity.this)
                                        .setTitle("Lỗi")
                                        .setMessage(e.getMessage())
                                        .setPositiveButton("OK", (dialog, which) -> {
                                            finish();
                                        } ).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
            }
        });

    }
}