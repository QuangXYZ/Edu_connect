package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.edu_connect.Controller.TestController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Question;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Adapter.QuestionAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestEditActivity extends AppCompatActivity {
    MaterialToolbar toolbar;
    LinearLayout addQuestion;
    ArrayList<Question> questionArrayList;
    RecyclerView recyclerView;
    QuestionAdapter questionAdapter;
    TestController testController;
    TextView title;

    Course course;
    Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_edit);
        init();
        settingUpListeners();
    }
    void init() {
        toolbar = findViewById(R.id.test_edit_toolbar);
        addQuestion = findViewById(R.id.edit_test_add_question);
        title = findViewById(R.id.edit_test_name);
        testController = new TestController();
        questionArrayList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                course = intent.getSerializableExtra("Course", Course.class);
                test = intent.getSerializableExtra("Test", Test.class);
            }
            else {
                course = (Course) intent.getSerializableExtra("Course");
                test = (Test) intent.getSerializableExtra("Test");

            }
        }
        title.setText(test.getTitle());
        questionArrayList.addAll(test.getQuestions());
        recyclerView = findViewById(R.id.edit_test_recycle_view);
        questionAdapter = new QuestionAdapter(questionArrayList,TestEditActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(TestEditActivity.this));
        recyclerView.setAdapter(questionAdapter);
        recyclerView.setNestedScrollingEnabled(true);



    }
    void settingUpListeners(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.create_test_btn) {
                    if (title.getText().toString().isEmpty()) {
                        title.setError("Nhập", null);
                    }
                    else {
                        Test test = new Test();
                        test.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                        test.setTitle(title.getText().toString());
                        test.setQuestions(questionArrayList);


                        testController.addTest(course, test, new TestController.Callback() {
                            @Override
                            public void onSuccess() {
                                new MaterialAlertDialogBuilder(TestEditActivity.this)
                                        .setTitle("Thành công")
                                        .setMessage("Lớp đã được tạo")
                                        .setPositiveButton("OK", (dialog, which) -> {
                                            finish();
                                        }).show();
                            }

                            @Override
                            public void onFailure(Exception e) {
                                new MaterialAlertDialogBuilder(TestEditActivity.this)
                                        .setTitle("Error")
                                        .setMessage(e.getMessage())
                                        .setPositiveButton("OK", (dialog, which) -> {

                                        }).show();
                            }
                        });
                    };
                    finish();
                    return true;
                }
                return false;
            }
        });

        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetInit();
            }
        });

//        createBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                }
//            }
//        });
    }
    void bottomSheetInit(){
        View viewDialog = getLayoutInflater().inflate(R.layout.add_question_bottom_sheet, null);
        BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(TestEditActivity.this);
//                bottomSheetDialog.setContentView(R.layout.add_question_bottom_sheet);
        bottomSheetDialog.setContentView(viewDialog);


        TextView questionNo = viewDialog.findViewById(R.id.add_question_no);
        EditText questioTitle = viewDialog.findViewById(R.id.add_question_title);
        RadioButton btnA =  viewDialog.findViewById(R.id.optionARadioButton);
        RadioButton btnB =  viewDialog.findViewById(R.id.optionBRadioButton);
        RadioButton btnC =  viewDialog.findViewById(R.id.optionCRadioButton);
        RadioButton btnD =  viewDialog.findViewById(R.id.optionDRadioButton);

        Button btnAdd = viewDialog.findViewById(R.id.add_question_button);

        EditText edtA =  viewDialog.findViewById(R.id.optionAEditText);
        EditText edtB =  viewDialog.findViewById(R.id.optionBEditText);
        EditText edtC =  viewDialog.findViewById(R.id.optionCEditText);
        EditText edtD =  viewDialog.findViewById(R.id.optionDEditText);



        questionNo.setText("Câu "+(questionArrayList.size()+1));


        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnB.setActivated(false);
                btnC.setActivated(false);
                btnD.setActivated(false);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnA.setActivated(false);
                btnC.setActivated(false);
                btnD.setActivated(false);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnA.setActivated(false);
                btnB.setActivated(false);
                btnD.setActivated(false);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnA.setActivated(false);
                btnB.setActivated(false);
                btnC.setActivated(false);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = new Question();
                List<String> options = new ArrayList<>();
                options.add(edtA.getText().toString());
                options.add(edtB.getText().toString());
                options.add(edtC.getText().toString());
                options.add(edtD.getText().toString());

                question.setOptions(options);

                question.setTitle(questioTitle.getText().toString());
                if (btnA.isChecked()) question.setCorrectOption(1);
                if (btnB.isChecked()) question.setCorrectOption(2);
                if (btnC.isChecked()) question.setCorrectOption(3);
                if (btnD.isChecked()) question.setCorrectOption(4);

                questionArrayList.add(question);
                questionAdapter.notifyDataSetChanged();
                bottomSheetDialog.dismiss();

            }
        });
        bottomSheetDialog.show();
    }
}