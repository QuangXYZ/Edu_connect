package com.example.edu_connect.Repository;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import androidx.annotation.NonNull;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Shared.CodeGenerator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    public static void addPost(Course course,Post post, PostRepository.Callback callback) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
        String key = root.push().getKey();
        root.child(course.getIdCourse()).child("Posts").child(key).setValue(post)
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
    public static void getPosts(String courseId, final GetPostsCallback getPostsCallback){
        List<Post> posts = new ArrayList<>();

            DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
            root.child(courseId).child("Posts").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        Post post = data.getValue(Post.class);
                        posts.add(post);
                    }
                    getPostsCallback.onSuccess(posts);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    getPostsCallback.onFailure(error.toException());
                }
            });

    }
    public interface Callback {
        void onSuccess();
        void onFailure(Exception e);
    }
    public interface GetPostsCallback {
        void onSuccess(List<Post> posts);
        void onFailure(Exception e);
    }
}



