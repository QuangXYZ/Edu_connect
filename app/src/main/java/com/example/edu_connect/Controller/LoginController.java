package com.example.edu_connect.Controller;

import com.example.edu_connect.Model.FirebaseAuthManager;
import com.google.firebase.auth.FirebaseUser;

public class LoginController {

    public void loginfunc(String email, String password, final AuthCallback callback){
        FirebaseAuthManager.getFirebaseAuthManagerInstance().loginUser(email, password, new FirebaseAuthManager.AuthCallback() {
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
