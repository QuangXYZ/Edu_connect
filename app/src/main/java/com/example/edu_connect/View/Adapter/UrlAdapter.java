package com.example.edu_connect.View.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.View.CourseMainActivity;
import com.example.edu_connect.View.DetailMemberCourse;
import com.example.edu_connect.View.ImageViewActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class UrlAdapter extends RecyclerView.Adapter<UrlAdapter.MyViewHolder>{
    List<String> uriList;
    Activity context;

    public UrlAdapter(List<String> uriList, Activity context) {
        this.uriList = uriList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card_student,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String uri = uriList.get(position);
        holder.name.setText("Image "+(position+1));
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageViewActivity.class);
                intent.putExtra("URL", uri);
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.single_student_name);

        }
    }

}
