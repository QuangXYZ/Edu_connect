package com.example.edu_connect.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

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

    public void registerUser(String email,String username, String password, final AuthCallback callback) {
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
                        callback.onSuccess(user);
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
