package com.example.edu_connect.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edu_connect.Controller.PostController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class CreatePostActivity extends AppCompatActivity {
    Button createBtn;
    EditText title;
    EditText content;
    TextView addFile;
    Course course;
    PostController postController;
    ArrayList<String> fileUrl;
    MaterialToolbar toolbar;
    private static final int PICK_FILE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        init();
        settingUpListeners();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri fileUri = data.getData();
               // PostController.class(fileUri);
            }
        }
    }
    void init() {
        toolbar = findViewById(R.id.create_post_toolbar);
        createBtn = findViewById(R.id.create_post_btn);
        title = findViewById(R.id.create_post_title);
        content = findViewById(R.id.create_post_title);
        addFile = findViewById(R.id.create_post_file);
        postController = new PostController();
        fileUrl = new ArrayList<>();
        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                course = intent.getSerializableExtra("Course", Course.class);
            }
            else {
                course = (Course) intent.getSerializableExtra("Course");
            }
        }
    }
    void settingUpListeners() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*"); // Chỉ định kiểu tệp cần chọn, ví dụ: image/* cho hình ảnh
                startActivityForResult(intent, PICK_FILE_REQUEST);
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postController.addPost(course, title.getText().toString(), content.getText().toString(), fileUrl, new PostController.Callback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreatePostActivity.this);
                        builder.setTitle("Không thành công")
                                .setMessage(e.getMessage())
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }
                });
            }
        });
    }
}