package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.msa.Model.Answer;
import com.example.msa.Model.DBHelper;
import com.example.msa.Model.DBModel;
import com.example.msa.Model.Question;
import com.example.msa.Model.Survey;
import com.example.msa.Model.User;

import java.util.ArrayList;

public class AdminPane extends AppCompatActivity {

//    private ArrayList<Answer> answers;
//    private ArrayList<Question> questions;
//    private ArrayList<Survey> surveys;
//    private ArrayList<User> users;
//    private DBModel dbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pane);

        Button viewAnswersBtn = findViewById(R.id.viewanswersbtn);
        viewAnswersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPane.this, ActivityAdminAnswers.class);
                startActivity(intent);
            }
        });
//
        Button viewQuestionsBtn = findViewById(R.id.viewquestionsbtn);
        viewQuestionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPane.this, ActivityAdminQuestions.class);
                startActivity(intent);
            }
        });

        Button viewSurveysBtn = findViewById(R.id.viewsurveysbtn);
        viewSurveysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPane.this, ActivityAdminSurveys.class);
                startActivity(intent);
            }
        });

        Button viewUsersBtn = findViewById(R.id.viewusersbtn);
        viewUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPane.this, ActivityAdminUsers.class);
                startActivity(intent);
            }
        });
    }


    public void logOut1(View view){
        Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show();
        finish();
        return;
    }
}