package com.example.edu_connect.Controller;

import androidx.annotation.NonNull;

import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Model.Teacher;
import com.example.edu_connect.Shared.DataLocalManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileController {
    public void getProfile(final Callback callback) {
        if(DataLocalManager.getUserIsTeacher()) {
            DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Teacher");
            root.child(FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                Teacher teacher = snapshot.getValue(Teacher.class);
                                callback.onTeacherProfile(teacher);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
        else {
            DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Student");
            root.child(FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {

                                Student student = snapshot.getValue(Student.class);
                                callback.onStudentProfile(student);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }

    }

    public void updateProfile(String name,String mssv, String classString, String date,String phone, final UpdateCallback callback) {

            getProfile(new Callback() {
                @Override
                public void onStudentProfile(Student student) {
                    student.setBirthday(date);
                    student.setMssv(mssv);
                    student.setPhoneNumber(phone);
                    student.setUserName(name);
                    student.setClassName(classString);
                    DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Student");
                    root.child(FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid())
                            .setValue(student)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    callback.onSuccess();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    callback.onFailure(e);
                                }
                            });

                }

                @Override
                public void onTeacherProfile(Teacher teacher) {
                    teacher.setBirthday(date);
                    teacher.setMsgv(mssv);
                    teacher.setPhoneNumber(phone);
                    teacher.setUserName(name);
                    teacher.setClassName(classString);

                    DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Teacher");
                    root.child(FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getUid())
                            .setValue(teacher)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    callback.onSuccess();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    callback.onFailure(e);
                                }
                            });
                }

                @Override
                public void onFailure(Exception e) {

                }
            });

    }
    public interface Callback {
        void onStudentProfile(Student student);
        void onTeacherProfile(Teacher teacher);

        void onFailure(Exception e);
    }
    public interface UpdateCallback {
        void onSuccess();
        void onFailure(Exception e);
    }
}