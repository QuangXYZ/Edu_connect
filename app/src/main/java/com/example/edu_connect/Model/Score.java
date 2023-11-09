package com.example.edu_connect.Model;

import java.io.Serializable;

public class Score implements Serializable {
    String uid;
    int score;

    public Score() {
    }

    public Score(String uid, int score) {
        this.uid = uid;
        this.score = score;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}