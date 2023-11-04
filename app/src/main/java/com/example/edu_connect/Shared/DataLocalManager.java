package com.example.edu_connect.Shared;

import android.content.Context;

import com.example.edu_connect.Model.User;
import com.google.gson.Gson;

public class DataLocalManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static final String PREF_USER = "PREF_USER";
    private static final String USER_IS_TEACHER = "USER_IS_TEACHER";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();

        }
        return instance;
    }

    public static void setFirstInstalled(Boolean isFirst) {
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(PREF_FIRST_INSTALL, isFirst);

    }
    public static void setUserIsTeacher(Boolean isTeacher) {
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(USER_IS_TEACHER, isTeacher);

    }
    public static boolean getUserIsTeacher() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(USER_IS_TEACHER);
    }
    public static boolean getFirstInstalled() {
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(PREF_FIRST_INSTALL);
    }

    public static void setUser(User user) {
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(user);
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(PREF_USER,strJsonUser);
        
    }

    public static User getUser(){
        String strUser = DataLocalManager.getInstance().mySharedPreferences.getStringValue(PREF_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strUser,User.class);
        return user;
    }

}
