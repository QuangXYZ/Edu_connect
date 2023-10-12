package com.example.edu_connect.View.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.edu_connect.R;
import com.example.edu_connect.View.CreatePostActivity;


public class HomeFragment extends Fragment {

    LinearLayout addPost;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_home, container, false);
        init(view);
        return view;
    }
    void init(View view) {
        addPost = view.findViewById(R.id.course_home_addPost)
    }
    void settingUpListeners(){
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreatePostActivity.class);
                startActivity(intent);
            }
        });
    }
}