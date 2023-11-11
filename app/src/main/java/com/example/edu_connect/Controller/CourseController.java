package com.example.edu_connect.Controller;

import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Repository.StudentRepository;

import java.util.List;

public class CourseController {
    public void getStudent(List<String> studentIds, final GetStudentCallback getStudentCallback) {
        StudentRepository.getStudents(studentIds, new StudentRepository.GetStudentsCallback() {
            @Override
            public void onSuccess(List<Student> students) {
                getStudentCallback.onSuccess(students);
            }

            @Override
            public void onFailure(Exception e) {
                getStudentCallback
                        .onFailure(e);
            }
        });

    }
    public interface GetStudentCallback {
        void onSuccess(List<Student> students);
        void onFailure(Exception e);
    }
}
