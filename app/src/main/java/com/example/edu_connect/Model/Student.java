package com.example.edu_connect.Model;

import java.util.ArrayList;

public class Student extends User{
    String idStudent;
    ArrayList<String> courses;



    public Student(String userName, String email) {
        super(userName, email);
    }

    public String getIdTeacher() {
        return idStudent;
    }

    public void setIdTeacher(String idTeacher) {
        this.idStudent = idTeacher;
    }
}
