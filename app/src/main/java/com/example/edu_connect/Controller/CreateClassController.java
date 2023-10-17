package com.example.edu_connect.Controller;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Repository.CourseRepository;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Date;

public class CreateClassController {

    public void createClass(String name, String description, String room, String note, Callback callback){
        String teacherName = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getDisplayName();
        String teacherID = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid();
        String currentDate = DateFormat.getDateTimeInstance().format(new Date());
        Course course = new Course(name, description, teacherName , teacherID, room, note,  currentDate );
        CourseRepository.addCourse(course, new CourseRepository.Callback() {
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



    public interface Callback {
        void onSuccess();
        void onFailure(Exception e);
    }
}
