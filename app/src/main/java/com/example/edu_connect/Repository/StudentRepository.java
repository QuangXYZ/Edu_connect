package com.example.edu_connect.Repository;

import androidx.annotation.NonNull;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Model.Teacher;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentRepository {
    public static void addStudent(Student student, final StudentRepository.Callback callback) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Student");
        String uid = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid();
        root.child(uid).setValue(student)
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


    public static void addCourse(Course course, TeacherRepository.Callback callback) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Teacher");
        String uid = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid();
        root.child(uid).child("courses").push().setValue(course.getIdCourse())
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

    public interface Callback {
        void onSuccess();
        void onFailure(Exception e);
    }
}