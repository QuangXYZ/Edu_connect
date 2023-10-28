package com.example.edu_connect.Model;

import java.util.ArrayList;

public class Teacher {
    String idTeacher;
    ArrayList<String> courses;
    String userName;
    String email;

    public Teacher(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public Teacher() {
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }
}
