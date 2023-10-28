package com.example.edu_connect.View.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.R;
import com.example.edu_connect.View.CourseMainActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>{
    List<Student> students;
    Activity context;

    public StudentAdapter(List<Student> students, Activity context) {
        this.students = students;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card_student,parent,false);
        return new StudentAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
        Student student = students.get(position);
        holder.name.setText(student.getUserName());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.single_student_name);

        }
    }

}

