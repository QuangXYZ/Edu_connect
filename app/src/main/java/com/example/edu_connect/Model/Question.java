package com.example.edu_connect.Model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    String Title;
    List<String> options;
    int correctOption;

    public Question() {
    }

    public Question(String title, List<String> options, int correctOption) {
        Title = title;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }
}
