package com.example.edu_connect.View.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edu_connect.Model.Question;
import com.example.edu_connect.Model.Student;
import com.example.edu_connect.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder>{
    List<Question> questions;
    Activity context;

    public QuestionAdapter(List<Question> questions, Activity context) {
        this.questions = questions;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card_question,parent,false);
        return new QuestionAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.MyViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.questionNo.setText("Cau "+(position+1)+". "+question.getTitle());
//        holder.title.setText(question.getTitle());
        holder.optionA.setText(question.getOptions().get(0));
        holder.optionB.setText(question.getOptions().get(1));
        holder.optionC.setText(question.getOptions().get(2));
        holder.optionD.setText(question.getOptions().get(3));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expandableLayout.toggle();
            }
        });
    }
    @Override
    public int getItemCount() {
        return questions.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView questionNo;
//        TextView title;
        RadioButton optionA, optionB,optionC, optionD;
        LinearLayout linearLayout;
        ExpandableLayout expandableLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            questionNo = itemView.findViewById(R.id.question_no);
//            title = itemView.findViewById(R.id.question_title);
            optionA = itemView.findViewById(R.id.question_radio_btn_1);
            optionB = itemView.findViewById(R.id.question_radio_btn_2);
            optionC = itemView.findViewById(R.id.question_radio_btn_3);
            optionD = itemView.findViewById(R.id.question_radio_btn_4);
            linearLayout = itemView.findViewById(R.id.single_question_layout);
            expandableLayout  = itemView.findViewById(R.id.expandable_layout);
        }
    }

}

