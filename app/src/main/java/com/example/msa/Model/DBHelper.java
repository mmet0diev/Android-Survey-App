package com.example.msa.Model;

import android.content.Context;

import java.util.ArrayList;

public class DBHelper {
    private static ArrayList<Answer> answerList;
    private static ArrayList<Question> questionList;
    private static ArrayList<Survey> surveyList;
    private static ArrayList<User> userList;
    private static DBModel DBHelper;

    public DBHelper(Context context){
        DBHelper = new DBModel(context);
        answerList = DBHelper.getAllAnswers();
        questionList = DBHelper.getAllQuestions();
        surveyList = DBHelper.getAllSurveys();
        userList = DBHelper.getAllUsers();
    }

    public ArrayList<Answer> get_answerList(){
        return answerList;
    }

    public ArrayList<Question> get_questionList(){
        return questionList;
    }

    public ArrayList<Survey> get_surveyList(){
        return surveyList;
    }

    public ArrayList<User> get_userList(){
        return userList;
    }
}
