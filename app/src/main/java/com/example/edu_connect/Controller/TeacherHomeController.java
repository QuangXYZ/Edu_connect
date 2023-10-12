package com.example.edu_connect.Controller;



import android.util.Log;


import androidx.annotation.NonNull;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.FirebaseModel;
import com.example.edu_connect.Repository.CourseRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherHomeController {


    public void getAllCourse(final Callback callback){
        String uid = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid();
        FirebaseModel.getFirebaseModelInstance().getmDatabaseReference()
                .child("Teacher").child(uid).child("courses")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> courseIDs = new ArrayList<>();

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String courseID = dataSnapshot.getValue(String.class);
                            courseIDs.add(courseID);
                        }
                        CourseRepository.getCourse(courseIDs, new CourseRepository.GetCourseCallback() {
                            @Override
                            public void onSuccess(List<Course> course) {
                                callback.onSuccess(course);

                            }

                            @Override
                            public void onFailure(Exception e) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        callback.onFailure(error.toException());
                    }
                });
    }
    public interface Callback {
        void onSuccess(List<Course> courses);
        void onFailure(Exception e);
    }
}
