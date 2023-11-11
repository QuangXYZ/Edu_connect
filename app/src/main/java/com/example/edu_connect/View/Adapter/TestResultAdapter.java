package com.example.edu_connect.View.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.Model.Score;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.Repository.StudentRepository;
import com.example.edu_connect.Shared.DataLocalManager;
import com.example.edu_connect.View.TeacherTestChooseActivity;
import com.example.edu_connect.View.TestDetailActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class TestResultAdapter extends RecyclerView.Adapter<TestResultAdapter.MyViewHolder>{
    List<Score> scores;
    Activity context;


    public TestResultAdapter(List<Score> scores, Activity context) {
        this.scores = scores;
        this.context = context;

    }

    @NonNull
    @Override
    public TestResultAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_score_student,parent,false);
        return new TestResultAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull TestResultAdapter.MyViewHolder holder, int position) {
        Score score = scores.get(position);
        StudentRepository.getStudent(score.getUid(), new StudentRepository.GetStudentCallback() {
            @Override
            public void onSuccess(Student student) {
                holder.name.setText(student.getUserName());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
        holder.score.setText(score.getScore()+"");


    }

    @Override
    public int getItemCount() {
        return scores.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView score;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.single_score_student_name);
            score = itemView.findViewById(R.id.single_score_student_score);

        }
    }
}
