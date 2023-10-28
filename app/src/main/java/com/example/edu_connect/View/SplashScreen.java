package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Teacher;
import com.example.edu_connect.R;
import com.example.edu_connect.Repository.TeacherRepository;
import com.example.edu_connect.Shared.DataLocalManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        },1000);
    }

    private void nextActivity() {
        if (FirebaseAuthManager.getFirebaseAuthManagerInstance().isUserLoggedIn()){
            if (DataLocalManager.getUserIsTeacher()) {
                Intent intent = new Intent(SplashScreen.this, TeacherHomeActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent = new Intent(SplashScreen.this, StudentHomeActivity.class);
                startActivity(intent);
                finish();
            }
        }
        else  {

            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}