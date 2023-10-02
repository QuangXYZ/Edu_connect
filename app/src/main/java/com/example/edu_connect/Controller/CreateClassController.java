package com.example.edu_connect.Controller;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Repository.CourseRepository;
import com.google.firebase.auth.FirebaseUser;

public class CreateClassController {

    public void createClass(Course course){
        CourseRepository.addCourse(course, new CourseRepository.Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }



    public interface Callback {
        void onSuccess();
        void onFailure(Exception e);
    }
}
