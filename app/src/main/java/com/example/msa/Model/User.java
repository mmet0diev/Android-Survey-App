package com.example.msa.Model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private int id;
    private int isAdmin;
    private String loginName;
    private String password;
    private String surveys;

    public User(int id, int isAdmin, String loginName, String password, String availableSurveys) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.loginName = loginName;
        this.password = password;
        this.surveys = availableSurveys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurveys(){
        return surveys;
    }

    public ArrayList<String> getSurveysList(String surveys) {
        String[] surveysArray = surveys.split(", "); // Split the string into an array of strings
        ArrayList<String> surveysList = new ArrayList<>(Arrays.asList(surveysArray)); // Convert the array to an ArrayList
        return surveysList;
    }

    @Override
    public String toString() {
        return "User: id -> " + getId() + "\nusername -> " + getLoginName() + "\nisAdmin -> " + getIsAdmin()
                + "\nsurveys -> " + getSurveys();
    }
}
