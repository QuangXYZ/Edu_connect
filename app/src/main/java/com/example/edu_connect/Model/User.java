package com.example.edu_connect.Model;

public class User {
    String userName;
    String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +

                '}';
    }
}
