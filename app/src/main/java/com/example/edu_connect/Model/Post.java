package com.example.edu_connect.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
    String title;
    String content;
    String date;
    String createBy;
    List<String> fileUrl;

    public Post(String title, String content, String date, String createBy, List<String> fileUrl) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.createBy = createBy;
        this.fileUrl = fileUrl;
    }

    public Post() {
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public List<String> getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(List<String> fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", createBy='" + createBy + '\'' +
                ", fileUrl=" + fileUrl +
                '}';
    }



}
