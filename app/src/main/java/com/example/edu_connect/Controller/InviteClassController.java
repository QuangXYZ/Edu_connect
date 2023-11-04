package com.example.edu_connect.Controller;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Repository.CourseRepository;
import com.example.edu_connect.Repository.StudentRepository;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class InviteClassController {

    public void inviteClass(Course course, InviteClassController.Callback callback){
        StudentRepository.addCourse(course, new StudentRepository.Callback() {
            @Override
            public void onSuccess() {
                CourseRepository.addStudent(course,new CourseRepository.Callback() {
                    @Override
                    public void onSuccess() {
                        callback.onSuccess();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        callback.onFailure(e);
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });

    }

    public void getCourse(String code, final GetCourseCallback getCourseCallback){
        CourseRepository.getCourseByInviteCode(code, new CourseRepository.GetCourseByInviteCodeCallback() {
            @Override
            public void onSuccess(Course course) {
                getCourseCallback.onSuccess(course);
            }

            @Override
            public void onFailure(Exception e) {
                getCourseCallback.onFailure(e);
            }
        });
    }


    public interface Callback {
        void onSuccess();
        void onFailure(Exception e);
    }
    public interface GetCourseCallback {
        void onSuccess(Course course);
        void onFailure(Exception e);
    }

}
