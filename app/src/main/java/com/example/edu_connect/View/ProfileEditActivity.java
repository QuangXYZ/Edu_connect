package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.edu_connect.Controller.ProfileController;
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

                profileController.updateProfile();
            }
        });


    }

    void updateLabel(){
        String myFormat="MM//dd//yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }
}