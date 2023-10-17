package com.example.edu_connect.Model;

import java.util.ArrayList;

public class Teacher extends User{
    String idTeacher;
    ArrayList<String> courses;



    public Teacher(String userName, String email) {
        super(userName, email);
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }
}
