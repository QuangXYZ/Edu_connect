package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.edu_connect.Controller.ProfileController;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Model.Teacher;
import com.example.edu_connect.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfileEditActivity extends AppCompatActivity {
    TextInputEditText name, mssv, lop,sdt,date;
    Button buttonUpdate;

    Calendar calendar;

    ProfileController profileController;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        init();
        settingUpListener();
    }
    void init() {
        name = findViewById(R.id.profile_edit_name);
        mssv = findViewById(R.id.profile_edit_mssv);
        lop = findViewById(R.id.profile_edit_class);
        sdt = findViewById(R.id.profile_edit_phone);
        date = findViewById(R.id.profile_edit_date);
        profileController = new ProfileController();

        buttonUpdate = findViewById(R.id.profile_edit_update);

        calendar = Calendar.getInstance();
        profileController.getProfile(new ProfileController.Callback() {
            @Override
            public void onStudentProfile(Student student) {
                name.setText(student.getUserName());
                if (student.getBirthday()==null)
                    date.setText("");
                else date.setText(student.getBirthday());
                if (student.getPhoneNumber()==null)
                    sdt.setText("");
                else sdt.setText(student.getPhoneNumber());
                if (student.getClassName()==null)
                    lop.setText("");
                else lop.setText(student.getClassName());
                if (student.getMssv()==null)
                    mssv.setText("Không xác định");
                else mssv.setText(student.getMssv());

            }

            @Override
            public void onTeacherProfile(Teacher teacher) {
                name.setText(teacher.getUserName());
                if (teacher.getBirthday()==null)
                    date.setText("");
                else date.setText(teacher.getBirthday());
                if (teacher.getPhoneNumber()==null)
                    sdt.setText("");
                else sdt.setText(teacher.getPhoneNumber());
                if (teacher.getClassName()==null)
                    lop.setText("");
                else lop.setText(teacher.getClassName());
                if (teacher.getMsgv()==null)
                    mssv.setText("");
                else mssv.setText(teacher.getMsgv());
            }
            @Override
            public void onFailure(Exception e) {

            }
            });



    }
    void settingUpListener(){

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ProfileEditActivity.this,dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameString = name.getText().toString();
                String mssvString = mssv.getText().toString();
                String lopString = lop.getText().toString();
                String sdtString = sdt.getText().toString();
                String dateString = date.getText().toString();


                profileController.updateProfile(nameString, mssvString, lopString, dateString, sdtString, new ProfileController.UpdateCallback() {
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent(ProfileEditActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        finish();
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
            }
        });


    }

    void updateLabel(){
        String myFormat="MM//dd//yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }
}