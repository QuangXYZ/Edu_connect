package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.edu_connect.R;

public class SignupActivity extends AppCompatActivity {
    TextView loginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        settingUpListeners();

    }
    void init(){
        loginText = findViewById(R.id.loginText);
    }
    void settingUpListeners() {
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                //overridePendingTransition(R.anim.slide_out_to_right, R.anim.slide_in_from_left);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
    }
}