package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.graphics.Color;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Model.Score;
import com.example.edu_connect.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.example.edu_connect.Model.Test;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class StatisticActivity extends AppCompatActivity {

    BarChart barChart;
    PieChart pieChart;
    List<BarEntry> entries;
    Test test;
    List<Score> scoreList;
    TextView sum, avg, mid;

    RadioGroup rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        init();
    }

    void init() {
        pieChart = findViewById(R.id.statistic_pie_chart);
        rd = findViewById(R.id.statistic_rd);
        sum = findViewById(R.id.statistic_sum);
        avg = findViewById(R.id.statistic_avg);
        mid = findViewById(R.id.statistic_mid);
        barChart = findViewById(R.id.statistic_bar_chart);
        Intent intent = getIntent();
        if (intent != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                test = intent.getSerializableExtra("Test", Test.class);
            } else {
                test = (Test) intent.getSerializableExtra("Test");
            }
        }

        scoreList = new ArrayList<>();
        double sumScore = 0;
        int scoreH5 = 0, scoreL5 = 0;

        ArrayList<Double> scores = new ArrayList<Double>();
        if (!test.getScores().entrySet().isEmpty()) {
            for (Map.Entry<String, Score> entry : test.getScores().entrySet()) {
                scoreList.add(entry.getValue());
            }
        }
        int[][] score = new int[11][1];
        for (int i = 0; i < 11; i++) {
            score[i][0] = 0;
        }
        entries = new ArrayList<>();
        for (int i = 0; i < scoreList.size(); i++) {
            sumScore += scoreList.get(i).getScore();
            score[(int) scoreList.get(i).getScore()][0]++;
            scores.add(scoreList.get(i).getScore());
            if (scoreList.get(i).getScore() >= 5) scoreH5++;
            else scoreL5++;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,##");
        sumScore = sumScore / scoreList.size();
        Collections.sort(scores);
        sum.setText(sum.getText().toString() + scoreList.size());
        avg.setText(sum.getText().toString() + decimalFormat.format(sumScore));
        mid.setText(sum.getText().toString() + scores.get(scores.size() / 2));


        for (int i = 0; i < 11; i++) {
            entries.add(new BarEntry(score[(int) scoreList.get(i).getScore()][0], 0));
        }
        BarDataSet barDataSet = new BarDataSet(entries, "Score");
        barDataSet.setColor(Color.BLUE);
        barDataSet.setValueTextColor(Color.GREEN);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        barChart.invalidate();


        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry((float) scoreH5, "Điểm >=5"));
        pieEntries.add(new PieEntry((float) scoreL5, "Điểm <5"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Pie chart");
        pieDataSet.setColor(Color.BLUE, Color.RED);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }

    void settingUpListener() {
        rd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.statistic_rd_bar_chart:
                        pieChart.setVisibility(View.GONE);
                        barChart.setVisibility(View.VISIBLE);
                        break;
                    case R.id.statistic_rd_pie_chart:
                        pieChart.setVisibility(View.VISIBLE);
                        barChart.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }
}