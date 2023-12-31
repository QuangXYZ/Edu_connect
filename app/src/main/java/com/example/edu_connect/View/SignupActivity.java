package com.example.edu_connect.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edu_connect.Controller.LoginController;
import com.example.edu_connect.Controller.SignupController;
import com.example.edu_connect.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
    TextView loginText;
    EditText signupEmail;
    EditText signupUsername;
    EditText signupPassword;
    Button signupBtn;
    ProgressBar signupProgressbar;
    SignupController signupController;
    Spinner signupSpinner;
    String[] roleArray;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        settingUpListeners();

    }
    void init(){
        loginText = findViewById(R.id.loginText);
        signupBtn = findViewById(R.id.signupButton);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupUsername = findViewById(R.id.signup_name);
        signupController = new SignupController();
        signupProgressbar = findViewById(R.id.signupProgressBar);
        signupSpinner = findViewById(R.id.signupSpinner);
        roleArray = new String[]{"Student","Teacher"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,roleArray);
        signupSpinner.setAdapter(arrayAdapter);
        role = "Student";
        Sprite doubleBounce = new ThreeBounce();
        signupProgressbar.setIndeterminateDrawable(doubleBounce);
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
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupBtn.setVisibility(View.GONE);
                signupProgressbar.setVisibility(View.VISIBLE);
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if (signupEmail.getText().toString().isEmpty()) {
                    signupEmail.setError("Nhập email");
                    signupEmail.requestFocus();
                }
                else if (signupPassword.getText().toString().isEmpty()){
                    signupPassword.setError("Nhập password");
                    signupPassword.requestFocus();
                }
                else if (signupUsername.getText().toString().isEmpty()){
                    signupPassword.setError("Nhập username");
                    signupPassword.requestFocus();
                }
                else if (!signupEmail.getText().toString().matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
                }
                else if (signupPassword.getText().toString().length()<8) {
                    Toast.makeText(getApplicationContext(), "Password chưa đủ 8 kí tự", Toast.LENGTH_SHORT).show();
                }
                else
                    if (Objects.equals(role, "Teacher")) {
                        signupController.registerTeacher(signupEmail.getText().toString(), signupUsername.getText().toString(), signupPassword.getText().toString(), new SignupController.AuthCallback() {
                            @Override
                            public void onSuccess(FirebaseUser user) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                builder.setTitle("Đã gửi email xác thực")
                                        .setMessage("Vui lòng xác thực email của bạn")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        }).show();

                            }

                            @Override
                            public void onFailure(Exception e) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                builder.setTitle("Đăng ký không thành công")
                                        .setMessage(e.getMessage())
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).show();

                            }
                        });
                    }
                    else {
                        signupController.registerStudent(signupEmail.getText().toString(), signupUsername.getText().toString(), signupPassword.getText().toString(), new SignupController.AuthCallback() {
                            @Override
                            public void onSuccess(FirebaseUser user) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                builder.setTitle("Đã gửi email xác thực stdtudent")
                                        .setMessage("Vui lòng xác thực email của bạn")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        }).show();

                            }

                            @Override
                            public void onFailure(Exception e) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                builder.setTitle("Đăng ký không thành công")
                                        .setMessage(e.getMessage())
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        }).show();
                                signupBtn.setVisibility(View.VISIBLE);
                                signupProgressbar.setVisibility(View.GONE);

                            }
                        });
                    }

            }
        });

        signupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}