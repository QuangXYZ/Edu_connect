package com.example.edu_connect.Controller;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Repository.PostRepository;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostController {





    public void addPost(Course course,String title, String context, ArrayList<Uri> uriArrayList , PostController.Callback callback) {

        String teacherName = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getDisplayName();
        String currentDate = DateFormat.getDateTimeInstance().format(new Date());
        Post post = new Post(title, context, currentDate, teacherName);
        PostRepository.addPost(course, post,uriArrayList, new PostRepository.Callback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();

            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);

            }
        });
    }
    public void getPosts(String courseId, final GetPostsCallback getPostsCallback){
        PostRepository.getPosts(courseId, new PostRepository.GetPostsCallback() {
            @Override
            public void onSuccess(List<Post> posts) {
                getPostsCallback.onSuccess(posts);
            }

            @Override
            public void onFailure(Exception e) {
                getPostsCallback.onFailure(e);

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
