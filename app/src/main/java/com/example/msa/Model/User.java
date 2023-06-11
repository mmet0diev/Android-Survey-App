package com.example.msa.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private int id;
    private int isAdmin;
    private String loginName;
    private String password;
    private ArrayList<String> surveyTitles;

    public User(int id, int isAdmin, String loginName, String password) {
        this.id = id;
        this.isAdmin = isAdmin;
        this.loginName = loginName;
        this.password = password;
        this.surveyTitles = new ArrayList<>();
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

    public void rmSurvey(String s){
        this.surveyTitles.remove(s);
    }

    public void addSurvey(String s){
        this.surveyTitles.add(s);
    }

    public ArrayList<String> getSurveyTitles(){
        return this.surveyTitles;
    }

    @Override
    public String toString() {
        return "User: id ->" + getId() + "\nusername -> " + getLoginName() + "\nisAdmin -> " + getIsAdmin();
    }
}
