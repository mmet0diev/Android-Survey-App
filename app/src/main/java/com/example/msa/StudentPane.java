package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.Survey;
import com.example.msa.Model.User;

import java.util.ArrayList;

public class StudentPane extends AppCompatActivity {
    private ArrayList<Survey> availableSurveys;
    private ArrayList<User> users;
    private String loggedUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pane);

        availableSurveys = new DBHelper(this).get_surveyList();
        users = new DBHelper(this).get_userList();

        loggedUserName = getIntent().getExtras().getString("username");

        User loggedUser = getLoggedUser(loggedUserName);

        populateCurrentStudSurveys();

        Bundle bundle = new Bundle();
        bundle.putSerializable("loggedUser", loggedUser);

        StudentSurveyFragment fragment = new StudentSurveyFragment();
        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.student_view_container, fragment)
                .commit();
    }

    public User getLoggedUser(String username) {
        User loggeduser = null;
        for (User u : users) {
            if (u.getLoginName().equals(username)) {
                loggeduser = u;
                break;
            }
        }
        return loggeduser;
    }

    public void populateCurrentStudSurveys() {
        User currUser = getLoggedUser(loggedUserName);
        if (currUser != null) {
            ArrayList<String> allSurvTitles = getCurrentSurveyTitles(availableSurveys);
            for (String sTitle : allSurvTitles) {
                currUser.addSurvey(sTitle);
            }
        }
    }

    public ArrayList<String> getCurrentStudentSurveyTitles() {
        return getLoggedUser(loggedUserName).getSurveyTitles();
    }

    public ArrayList<String> getCurrentSurveyTitles(ArrayList<Survey> surveys) {
        ArrayList<String> surveyTitles = new ArrayList<>();
        for (Survey surv : surveys) {
            surveyTitles.add(surv.getTitle());
        }
        return surveyTitles;
    }

    public void viewAnswers(View view) {
        Intent intent = new Intent(this, ActivityStudentAnswers.class);
        startActivity(intent);
    }

    public void logOut(View view) {
        Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show();
        finish();
    }
}
