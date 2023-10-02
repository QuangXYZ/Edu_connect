package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.R;

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
            Intent intent = new Intent(SplashScreen.this, TeacherHomeActivity.class);
            startActivity(intent);
            finish();
        }
        else  {
            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}