package com.example.edu_connect.Model;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable {
    String idTest;
    String title;
    List<Question> questions;
    String date;
    List<String> students;
    List<Score> scores;


    public Test() {
    }



    public String getIdTest() {
        return idTest;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}

