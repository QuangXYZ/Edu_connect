package com.example.edu_connect.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course {
    String idCourse;
    String inviteCode;
    String className;
    String description;
    ArrayList<Student> students;
    String teacherName;
    String teacherID;
    String room;
    String note;
    String date;

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

    @Override
    public String toString() {
        return "Course{" +
                "className='" + className + '\'' +
                ", description='" + description + '\'' +
                ", students=" + students +
                ", teacherName='" + teacherName + '\'' +
                ", note='" + note + '\'' +
                ", date=" + date +
                '}';
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

    public ArrayList<Student> getStudents() {
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
}
