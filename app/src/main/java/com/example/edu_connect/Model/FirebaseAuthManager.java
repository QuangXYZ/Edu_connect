package com.example.edu_connect.Model;

import com.example.edu_connect.Repository.StudentRepository;
import com.example.edu_connect.Repository.TeacherRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

public class FirebaseAuthManager {
    private FirebaseAuth mAuth;
    private FirebaseModel firebaseModel;

    private static FirebaseAuthManager instance;
    public FirebaseAuthManager() {
        mAuth = FirebaseAuth.getInstance();
        firebaseModel = FirebaseModel.getFirebaseModelInstance();
    }

    public static synchronized FirebaseAuthManager getFirebaseAuthManagerInstance() {
        if (instance == null) {
            instance = new FirebaseAuthManager();
        }
        return instance;
    }

    public void registerStudent(String email,String username, String password, final AuthCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Đăng ký thành công
                        FirebaseUser user = mAuth.getCurrentUser();
                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                        .build();
                        user.updateProfile(userProfileChangeRequest);
                        user.sendEmailVerification();
                        Student student = new Student(username,email);
                        student.setIdStudent(user.getUid());

                        StudentRepository.addStudent(student, new StudentRepository.Callback() {
                            @Override
                            public void onSuccess() {
                                callback.onSuccess(user);
                            }

                            @Override
                            public void onFailure(Exception e) {

                            }
                        });

                    } else {
                        // Đăng ký thất bại
                        callback.onFailure(task.getException());
                    }
                });
    }
    public void registerTeacher(String email,String username, String password, final AuthCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Đăng ký thành công
                        FirebaseUser user = mAuth.getCurrentUser();
                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(username)
                                .build();
                        user.updateProfile(userProfileChangeRequest);
                        user.sendEmailVerification();
                        Teacher teacher = new Teacher(username,email);
                        teacher.setIdTeacher(user.getUid());
                        teacher.setCourses(null);
                        TeacherRepository.addTeacher(teacher, new TeacherRepository.Callback() {
                            @Override
                            public void onSuccess() {
                                callback.onSuccess(user);
                            }

                            @Override
                            public void onFailure(Exception e) {

                            }
                        });

                    } else {
                        // Đăng ký thất bại
                        callback.onFailure(task.getException());
                    }
                });
    }

    public void loginUser(String email, String password, final AuthCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Đăng nhập thành công
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user.isEmailVerified()) callback.onSuccess(user);
                        else callback.onFailure( new Exception("Email chưa được xác thực") );
                    } else {
                        // Đăng nhập thất bại
                        callback.onFailure(task.getException());
                    }
                });
    }

    public void logoutUser() {
        mAuth.signOut();
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public boolean isUserLoggedIn() {
        return mAuth.getCurrentUser() != null;
    }

    public interface AuthCallback {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception e);
    }
}
