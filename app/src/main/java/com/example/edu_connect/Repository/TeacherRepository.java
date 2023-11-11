package com.example.edu_connect.Repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Model.Teacher;
import com.example.edu_connect.Shared.CodeGenerator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class TeacherRepository {
    public static void addTeacher(Teacher teacher, final Callback callback) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Teacher");
        String uid = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid();
        root.child(uid).setValue(teacher)
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


    public static void addCourse(Course course, Callback callback) {


        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Teacher");
        String uid = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid();
        root.child(uid).child("courses").child(course.getIdCourse()).setValue(course.getIdCourse())
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



    public static void isTeacher(final IsTeacherCallback callback) {

        String uid = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid();
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Teacher").child(uid);
        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean check = false;
                if (snapshot.exists()) {
                    check = true;
                }
                Log.d("ID Teacher", Boolean.toString(check));
                callback.onSuccess(check);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onFailure(error.toException());
            }
        });
    }
    public interface Callback {
        void onSuccess();

        void onFailure(Exception e);
    }
    public interface IsTeacherCallback {
        void onSuccess(Boolean isTeacher);

        void onFailure(Exception e);
    }
}


