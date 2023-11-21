package com.example.edu_connect.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test implements Serializable {
    String idTest;
    String title;
    ArrayList<Question> questions;
    String date;
    Map<String,String> students;
    Map<String,Score> scores;


    public Test() {
    }



    public String getIdTest() {
        return idTest;
    }

    public Map<String, String> getStudents() {
        return students;
    }

    public void setStudents(Map<String, String> students) {
        this.students = students;
    }

    public Map<String, Score> getScores() {
        return scores;
    }

    public void setScores(Map<String, Score> scores) {
        this.scores = scores;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}

