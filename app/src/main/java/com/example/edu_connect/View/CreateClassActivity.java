package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.edu_connect.Controller.CreateClassController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class CreateClassActivity extends AppCompatActivity {
    TextInputEditText name;
    TextInputEditText description;
    TextInputEditText room;
    TextInputEditText note;
    Button createBtn;
    MaterialToolbar toolbar;

    CreateClassController createClassController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        init();
        settingUpListeners();

    }
    void init(){
        name = findViewById(R.id.create_class_name);
        description = findViewById(R.id.create_class_description);
        room = findViewById(R.id.create_class_room);
        note = findViewById(R.id.create_class_note);
        createBtn = findViewById(R.id.create_class_btn);
        toolbar = findViewById(R.id.create_class_toolbar);
        createClassController = new CreateClassController();
    }
    void settingUpListeners(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTxt="", descriptionTxt = "", roomTxt = "", noteTxt = "";

                if (name.getText().toString().isEmpty()) {
                    name.setError("Tên không được trống");
                    name.requestFocus();
                }
                else {
                    nameTxt = name.getText().toString();
                }
                if (!description.getText().toString().isEmpty()) {
                    descriptionTxt = description.getText().toString();
                }
                if (!room.getText().toString().isEmpty()) {
                    roomTxt = room.getText().toString();
                }
                if (!note.getText().toString().isEmpty()) {
                    noteTxt = note.getText().toString();
                }

                createClassController.createClass(nameTxt, descriptionTxt, roomTxt, noteTxt, new CreateClassController.Callback() {
                    @Override
                    public void onSuccess() {
                        new MaterialAlertDialogBuilder(CreateClassActivity.this)
                                .setTitle("Thành công")
                                .setMessage("Lớp đã được tạo")
                                .setPositiveButton("OK", (dialog, which) -> {
                                    finish();
                                } ).show();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        new MaterialAlertDialogBuilder(CreateClassActivity.this)
                                .setTitle("Lỗi")
                                .setMessage(e.getMessage())
                                .setPositiveButton("OK", (dialog, which) -> {} ).show();
                    }
                });

            }
        });
    }
}