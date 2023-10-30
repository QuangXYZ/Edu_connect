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
import com.example.edu_connect.Model.Test;
import com.example.edu_connect.R;
import com.example.edu_connect.View.CourseMainActivity;
import com.example.edu_connect.View.TestDetailActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder>{
    List<Test> tests;
    Activity context;

    public TestAdapter(List<Test> tests, Activity context) {
        this.tests = tests;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card_test,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Test test = tests.get(position);
        holder.name.setText(test.getTitle());
        holder.date.setText(test.getDate());
        holder.question.setText(test.getQuestions().size()+"");
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TestDetailActivity.class);
                intent.putExtra("Test", test);
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

            }
        });

    }

    @Override
    public int getItemCount() {
        return tests.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;
        MaterialCardView materialCardView;
        TextView question;
        TextView done;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.single_test_name);
            date = itemView.findViewById(R.id.single_test_date);
            question = itemView.findViewById(R.id.single_test_question);
            done = itemView.findViewById(R.id.single_test_done);
            materialCardView = itemView.findViewById(R.id.single_card_test);
        }
    }

}
