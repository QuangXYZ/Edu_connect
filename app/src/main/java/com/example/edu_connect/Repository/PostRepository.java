package com.example.edu_connect.Repository;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Shared.CodeGenerator;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    public static void addPost(Course course,Post post, List<Uri> uriList, PostRepository.Callback callback) {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Course");
        String key = root.push().getKey();
        // Tạo tham chiếu đến vị trí lưu trữ trên Firebase Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference fileRef = storageReference.child(key).child(System.currentTimeMillis()+".jpg"); // Đặt tên tệp
        for (Uri fileUri : uriList) {
            UploadTask uploadTask = fileRef.putFile(fileUri);
            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        post.addUrl(fileRef.getDownloadUrl().toString());
                    }
                }
            });
        }
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



