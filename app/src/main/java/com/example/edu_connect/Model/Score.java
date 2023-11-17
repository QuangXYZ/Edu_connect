package com.example.edu_connect.Model;

import java.io.Serializable;

public class Score implements Serializable {
    String uid;
    double score;

    public Score() {
    }

    public Score(String uid, double score) {
        this.uid = uid;
        this.score = score;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
