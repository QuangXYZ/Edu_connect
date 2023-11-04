package com.example.edu_connect.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edu_connect.Controller.LoginController;
import com.example.edu_connect.R;
import com.example.edu_connect.Repository.TeacherRepository;
import com.example.edu_connect.Shared.DataLocalManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextView signupText;
    TextInputEditText loginEmail;
    TextInputEditText loginPassword;
    Button loginBtn;
    ProgressBar loginProgressbar;
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
        loginProgressbar = findViewById(R.id.loginProgressBar);
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
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                loginBtn.setVisibility(View.GONE);
                loginProgressbar.setVisibility(View.VISIBLE);
                if (loginEmail.getText().toString().isEmpty()) {
                    loginEmail.setError("Nhập email");
                    loginEmail.requestFocus();
                }
                else if (loginPassword.getText().toString().isEmpty()){
                    loginPassword.setError("Nhập password",null);
                    loginPassword.requestFocus();
                }
                else if (!loginEmail.getText().toString().matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
                }
                else if (loginPassword.getText().toString().length()<8) {
                    Toast.makeText(getApplicationContext(), "Password chưa đủ 8 kí tự", Toast.LENGTH_SHORT).show();
                }
                else
                loginController.loginfunc(loginEmail.getText().toString(), loginPassword.getText().toString(), new LoginController.AuthCallback() {
                    @Override
                    public void onSuccess(FirebaseUser user) {
                        TeacherRepository.isTeacher(new TeacherRepository.IsTeacherCallback() {
                            @Override
                            public void onSuccess(Boolean isTeacher) {
                                Intent intent;
                                if (isTeacher) {
                                    intent = new Intent(LoginActivity.this, TeacherHomeActivity.class);
                                    DataLocalManager.setUserIsTeacher(true);
                                }
                                else {
                                    intent = new Intent(LoginActivity.this, StudentHomeActivity.class);
                                    DataLocalManager.setUserIsTeacher(false);
                                }
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                                finish();
                            }

                            @Override
                            public void onFailure(Exception e) {
                                new MaterialAlertDialogBuilder(LoginActivity.this)
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

                        new MaterialAlertDialogBuilder(LoginActivity.this)
                                .setTitle("Đăng nhập không thành công")
                                .setMessage(e.getMessage())
                                .setPositiveButton("OK", (dialog, which) -> {} ).show();
                    }
                });
                loginBtn.setVisibility(View.VISIBLE);
                loginProgressbar.setVisibility(View.GONE);
            }
        });
    }
}