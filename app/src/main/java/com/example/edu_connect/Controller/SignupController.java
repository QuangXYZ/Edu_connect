package com.example.edu_connect.Controller;

import com.example.edu_connect.Model.FirebaseAuthManager;
import com.google.firebase.auth.FirebaseUser;

public class SignupController {

    public void registerfunc(String email,String username, String password, final AuthCallback callback){
        FirebaseAuthManager.getFirebaseAuthManagerInstance().registerUser(email,username, password, new FirebaseAuthManager.AuthCallback() {
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