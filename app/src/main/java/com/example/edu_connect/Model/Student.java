package com.example.edu_connect.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    String idStudent;
    ArrayList<String> courses = new ArrayList<>();
    String userName;
    String email;

    String phoneNumber;
    String className;
    String birthday;
    String mssv;

    public Student() {
    }

    public Student(String userName, String email) {
        this.userName = userName;
        this.email = email;

    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idTeacher) {
        this.idStudent = idTeacher;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public void addCourses(String courseId) {
        this.courses.add(courseId);
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
}
