package com.example.edu_connect.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.edu_connect.Controller.TestController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.Shared.DataLocalManager;
import com.example.edu_connect.View.Adapter.TestAdapter;
import com.example.edu_connect.View.CourseMainActivity;
import com.example.edu_connect.View.CreateTestActivity;
import com.example.edu_connect.View.LoginActivity;
import com.example.edu_connect.View.StudentHomeActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;


public class TestFragment extends Fragment {
    Course course;
    LinearLayout addTest;
    RecyclerView recyclerView;
    List<Test> testList;
    TestAdapter testAdapter;
    TestController testController;
    public TestFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        init(view);
        settingUpListeners();
        return view;
    }
    void init(View view){
        addTest = view.findViewById(R.id.test_add);
        recyclerView = view.findViewById(R.id.test_home_recycle_view);
        testList = new ArrayList<>();
        testController = new TestController();
        if (!DataLocalManager.getUserIsTeacher()) addTest.setVisibility(View.GONE);
        testController.getTest(course.getIdCourse(), new TestController.GetTestsCallback() {
            @Override
            public void onSuccess(List<Test> tests) {

                testList.addAll(tests);
                testAdapter = new TestAdapter(testList,course, getActivity());
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(testAdapter);
                recyclerView.setNestedScrollingEnabled(true);
            }
            @Override
            public void onFailure(Exception e) {

            }
        });


    }
    void settingUpListeners(){
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateTestActivity.class);
                intent.putExtra("Course", course);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

            }
        });
    }
    public void setData(Course course) {
        this.course = course;
    }
}