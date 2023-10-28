package com.example.edu_connect.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edu_connect.Controller.CourseController;
import com.example.edu_connect.Controller.PostController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Adapter.PostAdapter;
import com.example.edu_connect.View.Adapter.StudentAdapter;
import com.example.edu_connect.View.CreatePostActivity;

import java.util.ArrayList;
import java.util.List;


public class StudentsFragment extends Fragment {

    Course course;
    ArrayList<Student> studentArrayList;
    StudentAdapter studentAdapter;
    RecyclerView recyclerView;
    CourseController courseController;
    TextView noStudent;
    public StudentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_students, container, false);
        init(view);
        return view;

    }

    void init(View view) {
        noStudent= view.findViewById(R.id.fragment_student_no_post);
        courseController = new CourseController();
        recyclerView = view.findViewById(R.id.fragment_student_recycleview);
        if (course.getStudents() == null) {
            noStudent.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            studentArrayList = new ArrayList<>();
            courseController.getStudent(course.getStudents(), new CourseController.GetStudentCallback() {
                @Override
                public void onSuccess(List<Student> students) {

                    studentArrayList.addAll(students) ;
                    studentAdapter = new StudentAdapter(students, getActivity());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),LinearLayoutManager.VERTICAL);
                    recyclerView.addItemDecoration(dividerItemDecoration);
                    recyclerView.setAdapter(studentAdapter);
                    recyclerView.setNestedScrollingEnabled(true);
                }
                @Override
                public void onFailure(Exception e) {

                }
            });

        }



    }

    public void setData(Course course) {
        this.course = course;
    }
}