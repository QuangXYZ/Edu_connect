package com.example.edu_connect.Controller;

import com.example.edu_connect.Model.FirebaseAuthManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupController {

    public void registerTeacher(String email,String username, String password, final AuthCallback callback){
        FirebaseAuthManager.getFirebaseAuthManagerInstance().registerTeacher(email,username, password, new FirebaseAuthManager.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                callback.onSuccess(user);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });


    }
    public void registerStudent(String email,String username, String password, final AuthCallback callback){
        FirebaseAuthManager.getFirebaseAuthManagerInstance().registerStudent(email,username, password, new FirebaseAuthManager.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                callback.onSuccess(user);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });


    }

    public interface AuthCallback {
        void onSuccess(FirebaseUser user);
        void onFailure(Exception e);
    }
}