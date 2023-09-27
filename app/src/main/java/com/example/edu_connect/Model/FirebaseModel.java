package com.example.edu_connect.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseModel {
    private static FirebaseModel instance;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private FirebaseModel() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

    public static synchronized FirebaseModel getFirebaseModelInstance() {
        if (instance == null) {
            instance = new FirebaseModel();
        }
        return instance;
    }

    public void addUser(User user) {
        DatabaseReference productReference = mDatabaseReference.child("User").push();
        productReference.setValue(user);
    }


}








