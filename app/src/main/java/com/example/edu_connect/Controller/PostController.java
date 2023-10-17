package com.example.edu_connect.Controller;

import android.net.Uri;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.FirebaseAuthManager;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Repository.CourseRepository;
import com.example.edu_connect.Repository.PostRepository;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostController {

//    public void uploadFileToFirebase(Uri fileUri) {
//
//        // Tạo tham chiếu đến vị trí lưu trữ trên Firebase Storage
//        StorageReference fileRef = storageReference.child("uploads/" + System.currentTimeMillis()); // Đặt tên tệp
//
//        fileRef.putFile(fileUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        // Tải lên thành công
//                        Toast.makeText(UploadFileActivity.this, "File đã được tải lên thành công", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // Lỗi xảy ra
//                        Toast.makeText(UploadFileActivity.this, "Lỗi khi tải lên: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
    public void addPost(Course course,String title, String context, ArrayList<String> fileUrl , PostController.Callback callback) {
        String teacherName = FirebaseAuthManager.getFirebaseAuthManagerInstance().getCurrentUser().getDisplayName();
        String currentDate = DateFormat.getDateTimeInstance().format(new Date());
        Post post = new Post(title, context, currentDate, teacherName, fileUrl);
        PostRepository.addPost(course, post, new PostRepository.Callback() {
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
