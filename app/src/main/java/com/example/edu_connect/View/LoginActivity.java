package com.example.edu_connect.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.edu_connect.Controller.LoginController;
import com.example.edu_connect.R;

public class LoginActivity extends AppCompatActivity {
    TextView signupText;
    EditText loginEmail;
    EditText loginPassword;
    Button loginBtn;
    LoginController loginController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        settingUpListeners();
    }
    void init(){
        signupText = findViewById(R.id.signupText);
        loginBtn = findViewById(R.id.loginButton);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginController = new LoginController();
    }
    void settingUpListeners(){
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginController.loginfunc(loginEmail.getText().toString(), loginPassword.getText().toString(), new LoginController.AuthCallback() {
                    @Override
                    public void onSuccess() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("Đăng nhập thất bại")
                                .setMessage("thành cng")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("Đăng nhập thất bại")
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