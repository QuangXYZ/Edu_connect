package com.example.edu_connect.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.edu_connect.Controller.PostController;
import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Post;
import com.example.edu_connect.R;
import com.example.edu_connect.View.Adapter.PostAdapter;
import com.example.edu_connect.View.CreatePostActivity;
import com.example.edu_connect.View.TeacherHomeActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    LinearLayout addPost;
    Course course;
    TextView name;
    TextView description;
    RecyclerView recyclerView;
    TextView emptyPost;
    PostAdapter postAdapter;
    ArrayList<Post> postsArrayList;
    PostController postController;
    SwipeRefreshLayout swipeRefreshLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_home, container, false);
        init(view);
        settingUpListeners();
        return view;
    }
    void init(View view) {
        addPost = view.findViewById(R.id.course_home_addPost);
        name = view.findViewById(R.id.home_course_name);
        description = view.findViewById(R.id.home_course_description);
        recyclerView = view.findViewById(R.id.course_home_recycleview);
        emptyPost = view.findViewById(R.id.course_home_no_post);

        postsArrayList = new ArrayList<>();
        postController = new PostController();

        postController.getPosts(course.getIdCourse(), new PostController.GetPostsCallback() {
            @Override
            public void onSuccess(List<Post> posts) {
                postsArrayList.addAll(posts);
                if (postsArrayList==null||postsArrayList.isEmpty()) {
                    emptyPost.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
                else {
                    postAdapter = new PostAdapter(postsArrayList, (Activity) getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(postAdapter);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });


        Log.d("course", course.toString());
        name.setText(course.getClassName());
        description.setText(course.getDescription());



    }
    void settingUpListeners(){
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreatePostActivity.class);
                intent.putExtra("Course", course);
                startActivity(intent);
            }
        });
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                postController.getPosts(course.getIdCourse(), new PostController.GetPostsCallback() {
//                    @Override
//                    public void onSuccess(List<Post> posts) {
//                        postsArrayList.clear();
//                        postsArrayList.addAll(posts);
//                        if (postsArrayList==null||postsArrayList.isEmpty()) {
//                            emptyPost.setVisibility(View.VISIBLE);
//                            recyclerView.setVisibility(View.GONE);
//                        }
//                        else {
//                            postAdapter.notifyDataSetChanged();
//                            swipeRefreshLayout.setRefreshing(false);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//
//                    }
//                });
//
//            }
//        });
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}