package com.example.msa.Model;

import android.content.Context;

import java.util.ArrayList;

public class DBHelper {
    private DBModel dbModel;
    private ArrayList<Answer> answerList;
    private ArrayList<Question> questionList;
    private ArrayList<Survey> surveyList;
    private ArrayList<User> userList;

    public DBHelper(Context context) {
        dbModel = new DBModel(context);
        answerList = dbModel.getAllAnswers();
        questionList = dbModel.getAllQuestions();
        surveyList = dbModel.getAllSurveys();
        userList = dbModel.getAllUsers();
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public ArrayList<Survey> getSurveyList() {
        return surveyList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}