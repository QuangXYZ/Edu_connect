package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.edu_connect.R;
import com.google.android.material.textfield.TextInputEditText;

public class CreateClassActivity extends AppCompatActivity {
    TextInputEditText name;
    TextInputEditText description;
    TextInputEditText room;
    TextInputEditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
    }
    void init(){
        name = findViewById(R.id.create_class_name);
        description = findViewById(R.id.create_class_description);
        room = findViewById(R.id.create_class_room);
        note = findViewById(R.id.create_class_note);
    }
}