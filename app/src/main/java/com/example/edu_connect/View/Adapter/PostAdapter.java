package com.example.edu_connect.View.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edu_connect.Model.Post;
import com.example.edu_connect.R;
import com.example.edu_connect.View.CourseMainActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder>{
    List<Post> posts;
    Activity context;

    public PostAdapter(List<Post> posts, Activity context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card_post,parent,false);
        return new PostAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.title.setText(post.getTitle());
        holder.description.setText(post.getContent());
        holder.date.setText(post.getDate());
        holder.postBy.setText(post.getCreateBy());
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseMainActivity.class);
                intent.putExtra("Post", post);
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView date;
        TextView postBy;
        MaterialCardView materialCardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.single_post_date);
            description = itemView.findViewById(R.id.single_post_description);
            postBy = itemView.findViewById(R.id.single_post_by);
            title = itemView.findViewById(R.id.single_post_title);
            materialCardView = itemView.findViewById(R.id.single_post_card);
        }
    }

}

