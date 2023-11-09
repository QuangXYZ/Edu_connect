package com.example.edu_connect.Repository;

import androidx.annotation.NonNull;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Score;
import com.example.edu_connect.Model.Teacher;
import com.example.edu_connect.Model.Test;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRepository {
    public static void addTest(Course course, Test test, Callback callback) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
        String key = root.push().getKey();
        test.setIdTest(key);
        

        root.child(course.getIdCourse()).child("Tests").child(key).setValue(test)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure(e);
                    }
                });

    }
    public static void getTests(String courseId, final GetTestsCallback getTestsCallback) {
        List<Test> tests = new ArrayList<>();

        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
        root.child(courseId).child("Tests").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    Test test = data.getValue(Test.class);
                    tests.add(test);
                }
                getTestsCallback.onSuccess(tests);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                getTestsCallback.onFailure(error.toException());
            }
        });

    }
    public static void submitTestScore(String courseId, String testId, int score, final Callback callback) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
        String uid = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid();
        root.child(courseId).child("Tests").child(testId).child("students").push().setValue(uid)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Score score1 = new Score(uid, score);
                        root.child(courseId).child("Tests").child(testId).child("scores").push().setValue(score1)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                callback.onSuccess();
                                            }
                                        });


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure(e);
                    }
                });

    }

    public interface Callback {
        void onSuccess();
        void onFailure(Exception e);
    }
    public interface GetTestsCallback {
        void onSuccess(List<Test> tests);
        void onFailure(Exception e);
    }

}
