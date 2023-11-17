package com.example.edu_connect.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.graphics.Color;

import com.example.edu_connect.Model.Post;
import com.example.edu_connect.Model.Score;
import com.example.edu_connect.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.example.edu_connect.Model.Test;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticActivity extends AppCompatActivity {

    LineChart lineChart;
    List<Entry> entries;
    Test test;
    List<Score> scoreList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        init();
    }
    void init(){
        lineChart = findViewById(R.id.statistic_line_chart);
        Intent intent = getIntent();
        if (intent!=null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                test = intent.getSerializableExtra("Test", Test.class);
            }
            else {
                test = (Test) intent.getSerializableExtra("Test");
            }
        }

        scoreList = new ArrayList<>();
        if (!test.getScores().entrySet().isEmpty()) {
            for (Map.Entry<String, Score> entry : test.getScores().entrySet()) {
                scoreList.add(entry.getValue());
            }
        }

        entries = new ArrayList<>();
        for (int i=0;i<scoreList.size();i++){
            entries.add(new Entry(i+1,(float)scoreList.get(i).getScore()));
        }
        LineDataSet lineDataSet = new LineDataSet(entries,"Score");
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setValueTextColor(Color.GREEN);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        lineChart.invalidate();


    }
}