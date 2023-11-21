package com.example.edu_connect.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.R;

public class CourseInfoFragment extends Fragment {

    Course course;
    TextView className, invideCode, description, teacherName, date, room, note;
    public CourseInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_info, container, false);
        init(view);
        return view;
    }

    void init(View view) {
        className = view.findViewById(R.id.course_detail_name);
        invideCode = view.findViewById(R.id.course_detail_invite_code);
        description = view.findViewById(R.id.course_detail_description);
        teacherName = view.findViewById(R.id.course_detail_teacher_name);
        date = view.findViewById(R.id.course_detail_date);
        room = view.findViewById(R.id.course_detail_room);
        note = view.findViewById(R.id.course_detail_note);

        className.setText(course.getClassName());
        invideCode.setText(course.getInviteCode());
        description.setText(course.getDescription());
        teacherName.setText(course.getTeacherName());
        date.setText(course.getDate());
        room.setText(course.getRoom());
        note.setText(course.getNote());

    }
    public void setCourse(Course course) {
        this.course = course;
    }
}