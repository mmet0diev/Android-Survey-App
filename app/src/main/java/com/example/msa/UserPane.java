package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.Survey;
import com.example.msa.Model.User;

import java.util.ArrayList;

public class UserPane extends AppCompatActivity {
    private ArrayList<Survey> allSurveys;
    private ArrayList<User> users;
    private int userid;
    private User loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pane);

        allSurveys = new DBHelper(this).get_surveyList();
        users = new DBHelper(this).get_userList();

        userid = getIntent().getExtras().getInt("userid");

        loggedUser = getLoggedUser(userid);

        Bundle bundle = new Bundle();
        bundle.putInt("userid", userid);

        UserSurveyFragment fragment = new UserSurveyFragment();
        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.student_view_container, fragment)
                .commit();
    }

    public User getLoggedUser(int id) {
        User res = null;
        for (User u : users) {
            if (u.getId() == id) {
                res = u;
                break;
            }
        }
        return res;
    }

    public ArrayList<String> getCurrentSurveyTitles(ArrayList<Survey> surveys) {
        ArrayList<String> surveyTitles = new ArrayList<>();
        for (Survey surv : surveys) {
            surveyTitles.add(surv.getTitle());
        }
        return surveyTitles;
    }

    public void viewAnswers(View view) {
        Intent intent = new Intent(this, ActivityUserAnswers.class);
        startActivity(intent);
    }

    public void logOut(View view) {
        Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show();
        finish();
    }
}
