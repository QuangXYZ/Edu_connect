package com.example.edu_connect.Repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Model.Teacher;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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


    public static void addCourse(Course course, StudentRepository.Callback callback) {


        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Student");
        String uid = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid();
        getStudent(uid, new GetStudentCallback() {
            @Override
            public void onSuccess(Student student) {
                student.addCourses(course.getIdCourse());
                root.child(uid).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
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

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });


    }
    public static void getStudents(List<String> studentIds, final GetStudentsCallback getStudentCallback){
        List<Student> students = new ArrayList<>();
        Log.d("Studeent", studentIds.toString());
        for (String studentId : studentIds) {
            DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Student");
            root.child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Student student = snapshot.getValue(Student.class);
                        Log.d("Student", student.toString());
                        students.add(student);
                        if (students.size()==studentIds.size()) {
                            getStudentCallback.onSuccess(students);
                        }

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    getStudentCallback.onFailure(error.toException());
                }
            });
        }
    }

    public static void getStudent(String studentId, final GetStudentCallback getStudentCallback){
            DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Student");
            root.child(studentId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        Student student = snapshot.getValue(Student.class);
                        getStudentCallback.onSuccess(student);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    getStudentCallback.onFailure(error.toException());
                }
            });

    }
    public interface Callback {
        void onSuccess();
        void onFailure(Exception e);
    }
    public interface GetStudentsCallback {
        void onSuccess(List<Student> students);
        void onFailure(Exception e);
    }
    public interface GetStudentCallback {
        void onSuccess(Student student);
        void onFailure(Exception e);
    }

}