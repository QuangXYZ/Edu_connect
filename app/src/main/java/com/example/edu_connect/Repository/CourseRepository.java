package com.example.edu_connect.Repository;

import androidx.annotation.NonNull;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.FirebaseModel;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Shared.CodeGenerator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseRepository {
    public static void addCourse(Course course,Callback callback) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
        String key = root.push().getKey();
        course.setInviteCode(CodeGenerator.generatorCode(key));
        course.setIdCourse(key);


        root.child(key).setValue(course)
                .addOnSuccessListener(new OnSuccessListener<Void>() {

                    @Override
                    public void onSuccess(Void unused) {

                        TeacherRepository.addCourse(course, new TeacherRepository.Callback() {
                            @Override
                            public void onSuccess() {
                                callback.onSuccess();
                            }

                            @Override
                            public void onFailure(Exception e) {

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

    public static void getCourse(List<String> courseIds, final GetCourseCallback getCourseCallback){
        List<Course> courses = new ArrayList<>();
        for (String courseId : courseIds) {
            DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
            root.child(courseId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        Course course = snapshot.getValue(Course.class);
                        courses.add(course);
                        if (courses.size()==courseIds.size()) {
                            getCourseCallback.onSuccess(courses);
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    getCourseCallback.onFailure(error.toException());
                }
            });
        }



    }
    public static void getCourseByInviteCode(String inviteCode, final GetCourseByInviteCodeCallback getCourseByInviteCodeCallback){
            DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Course course = dataSnapshot.getValue(Course.class);
                            if (Objects.equals(course.getInviteCode(), inviteCode)) {
                                getCourseByInviteCodeCallback.onSuccess(course);

                            }
                        }
                        getCourseByInviteCodeCallback.onFailure(new Exception("Không tìm thấy lớp học"));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    getCourseByInviteCodeCallback.onFailure(error.toException());
                }
            });


    }
    public static void addStudent(Course course, Callback callback) {
        List<String> students = new ArrayList<>();
        students.add(FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid());
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
        root.child(course.getIdCourse()).child("students").setValue(students)
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

    public static void storeCourse(Course course,Callback callback) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
        root.child(course.getIdCourse()).child("stored").setValue(true)
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
    public interface GetCourseCallback {
        void onSuccess(List<Course> course);
        void onFailure(Exception e);
    }
    public interface GetCourseByInviteCodeCallback {
        void onSuccess(Course course);
        void onFailure(Exception e);
    }

}
