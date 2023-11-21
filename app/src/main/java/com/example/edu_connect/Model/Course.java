package com.example.edu_connect.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course implements Serializable {
    String idCourse;
    String inviteCode;
    String className;
    String description;
    ArrayList<String> students;
    String teacherName;
    String teacherID;
    String room;
    String note;
    String date;
    boolean stored;

    ArrayList<Post> posts;
    ArrayList<Test> tests;
    public Course() {
    }

    public Course(String className, String description, String teacherName, String teacherID, String room, String note, String date) {
        this.className = className;
        this.description = description;
        this.teacherName = teacherName;
        this.teacherID = teacherID;
        this.room = room;
        this.note = note;
        this.date = date;
        this.stored = false;
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }



    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getStudents() {
        return students;
    }



    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Course{" +
                "idCourse='" + idCourse + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", className='" + className + '\'' +
                ", description='" + description + '\'' +
                ", students=" + students +
                ", teacherName='" + teacherName + '\'' +
                ", teacherID='" + teacherID + '\'' +
                ", room='" + room + '\'' +
                ", note='" + note + '\'' +
                ", date='" + date + '\'' +
                ", posts=" + posts +
                '}';
    }
}
