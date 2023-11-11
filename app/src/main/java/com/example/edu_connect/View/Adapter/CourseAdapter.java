package com.example.edu_connect.View.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.edu_connect.Model.Course;
import com.example.edu_connect.R;
import com.example.edu_connect.Repository.CourseRepository;
import com.example.edu_connect.View.CourseMainActivity;

import com.example.edu_connect.View.TeacherHomeActivity;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{
    List<Course> courses;
    Activity context;
    boolean isStored;

    public CourseAdapter(List<Course> courses,boolean isStored, Activity context) {
        this.courses = courses;
        this.context = context;
        this.isStored = isStored;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card_course,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Course course = courses.get(position);
        if (!isStored) {
            if (course.isStored()) return;
        }
        if (isStored) {
            if (!course.isStored()) return;
        }

        holder.name.setText(course.getClassName());
        holder.description.setText(course.getDescription());
        holder.teacherName.setText(course.getTeacherName());
        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseMainActivity.class);
                intent.putExtra("Course", course);
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);

            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.btnMenu);
                popupMenu.inflate(R.menu.course_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.store_course:

                                new MaterialAlertDialogBuilder(context)
                                        .setTitle("Confirm")
                                        .setMessage("Bạn có chắc chắn")
                                        .setNeutralButton("Có", (dialog, which) -> {
                                            CourseRepository.storeCourse(course, new CourseRepository.Callback() {
                                                @Override
                                                public void onSuccess() {
                                                    courses.remove(course);
                                                }

                                                @Override
                                                public void onFailure(Exception e) {

                                                }
                                            });
                                        })
                                        .setPositiveButton("Không", (dialog, which) -> {} ).show();
                                return true;
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;
        MaterialCardView materialCardView;
        TextView teacherName;
        LinearLayout linearLayout;
        TextView btnMenu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.single_Card_Course_Name);
            description = itemView.findViewById(R.id.single_Card_Course_Description);
            teacherName = itemView.findViewById(R.id.single_Card_Course_Teacher);
            linearLayout = itemView.findViewById(R.id.single_Card_Course_Click);
            materialCardView = itemView.findViewById(R.id.single_Card_Course_CartView);
            btnMenu = itemView.findViewById(R.id.single_course_menu);
        }

    }

}
