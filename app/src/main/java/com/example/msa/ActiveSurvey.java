package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msa.Model.Answer;
import com.example.msa.Model.DBHelper;
import com.example.msa.Model.DBModel;
import com.example.msa.Model.Question;
import com.example.msa.Model.User;

import java.util.ArrayList;

public class ActiveSurvey extends AppCompatActivity {

    /**
     * 1: strongly agree
     * 2: agree
     * 3: neither agree/disagree
     * 4: disagree
     * 5: strongly disagree
     */

    private int questionNum = 1;
    private DBModel dbModel;
    private ArrayList<Question> questions;
    private ArrayList<Integer> answers;
    private User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_survey);

        dbModel = new DBModel(this);
        questions = new DBHelper(this).getQuestionList();
        answers = new ArrayList<>();

        int userId = getIntent().getIntExtra("userId", -1);
        loggedUser = dbModel.getUserById(userId);

        ((TextView) findViewById(R.id.questiontxt)).setText(questions.get(questionNum - 1).toString());
        ((TextView) findViewById(R.id.surveytitletxt)).setText(getIntent().getStringExtra("title"));
    }

    public void onNext(View view) {
        if (questionNum < 10) {
            if (((RadioButton) findViewById(R.id.rb1)).isChecked()) {
                answers.add(1);
                questionNum++;
                ((TextView) findViewById(R.id.questiontxt)).setText(questions.get(questionNum - 1).toString());
                ((RadioGroup) findViewById(R.id.radGroup)).clearCheck();
            } else if (((RadioButton) findViewById(R.id.rb2)).isChecked()) {
                answers.add(2);
                questionNum++;
                ((TextView) findViewById(R.id.questiontxt)).setText(questions.get(questionNum - 1).toString());
                ((RadioGroup) findViewById(R.id.radGroup)).clearCheck();
            } else if (((RadioButton) findViewById(R.id.rb3)).isChecked()) {
                answers.add(3);
                questionNum++;
                ((TextView) findViewById(R.id.questiontxt)).setText(questions.get(questionNum - 1).toString());
                ((RadioGroup) findViewById(R.id.radGroup)).clearCheck();
            } else if (((RadioButton) findViewById(R.id.rb4)).isChecked()) {
                answers.add(4);
                questionNum++;
                ((TextView) findViewById(R.id.questiontxt)).setText(questions.get(questionNum - 1).toString());
                ((RadioGroup) findViewById(R.id.radGroup)).clearCheck();
            } else if (((RadioButton) findViewById(R.id.rb5)).isChecked()) {
                answers.add(5);
                questionNum++;
                ((TextView) findViewById(R.id.questiontxt)).setText(questions.get(questionNum - 1).toString());
                ((RadioGroup) findViewById(R.id.radGroup)).clearCheck();
            } else {
                Toast.makeText(this, "No answer checked!", Toast.LENGTH_LONG).show();
            }

            // Check if it's the last question and update the text of the button
            if(questionNum == 10){
                ((TextView) findViewById(R.id.questiontxt)).setText(questions.get(questionNum - 1).toString());
                ((Button) findViewById(R.id.nextbtn)).setText("Finish");

            }
        } else if(questionNum == 10){
            // If the last question has been reached, add the answers to the database
            pushAnswersToDB();
            Toast.makeText(this, "Survey completed", Toast.LENGTH_LONG).show();
            UserSurveyFragment userSurveyFragment = (UserSurveyFragment) getSupportFragmentManager().findFragmentById(R.id.layoutSurveys);
            finish();
        }
    }

    public void pushAnswersToDB(){
        Answer answer;
        for(int a : answers){
            answer = new Answer(-1, a);
            dbModel.addAnswer(answer);
        }
    }

    public void onPrev(View view) {
        if(questionNum > 1) {
            questionNum--;
            answers.remove(questionNum - 1); // Subtract 1 from questionNum
            ((TextView) findViewById(R.id.questiontxt)).setText(questions.get(questionNum - 1).toString());
        } else {
            Toast.makeText(this, "Already at the first question", Toast.LENGTH_LONG).show();
        }
    }
}