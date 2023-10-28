package com.example.edu_connect.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.R;
import com.example.edu_connect.Shared.DataLocalManager;
import com.example.edu_connect.View.CreateTestActivity;
import com.example.edu_connect.View.LoginActivity;
import com.example.edu_connect.View.StudentHomeActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class TestFragment extends Fragment {
    Course course;
    LinearLayout addTest;


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

    }
    void settingUpListeners(){
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreateTestActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

            }
        });
    }
    public void setData(Course course) {
        this.course = course;
    }
}