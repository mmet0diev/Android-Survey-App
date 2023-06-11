package com.example.msa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.Survey;
import com.example.msa.Model.User;

import java.util.ArrayList;


public class StudentSurveyFragment extends Fragment {

    private ArrayList<Survey> currentSurveys;
    private ArrayList<String> completedSurveys;


    public StudentSurveyFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_surveys, container, false);

        LinearLayout layoutSurveys = view.findViewById(R.id.layoutSurveys);

        currentSurveys = new ArrayList<>();
        completedSurveys = new ArrayList<>();

        Bundle arguments = getArguments();
        if (arguments != null) {
            User loggedUser = (User) arguments.getSerializable("loggedUser");

            if (loggedUser != null) {
                ArrayList<String> currentStudentSurveyTitles = ((StudentPane) getActivity()).getCurrentStudentSurveyTitles();
                for (String surveyTitle : loggedUser.getSurveyTitles()) {
                    Survey survey = getSurveyByTitle(surveyTitle);
                    if (survey != null) {
                        currentSurveys.add(survey);
                    }
                }

                for (String title : currentStudentSurveyTitles) {
                    TextView textView = new TextView(getActivity());
                    textView.setText(title);
                    textView.setPadding(16, 16, 16, 16);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

                    if (completedSurveys.contains(title)) {
                        textView.setEnabled(false); // Disable click action
                        textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.black));
                    } else {
                        textView.setOnClickListener(v -> {
                            Intent intent = new Intent(getActivity(), ActiveSurvey.class);
                            intent.putExtra("title", title);
                            intent.putExtra("loggedUser", loggedUser);
                            startActivity(intent);
                        });
                    }

                    layoutSurveys.addView(textView);
                }
            } else {
                Toast.makeText(getActivity(), "Logged user is null", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Arguments are null", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    public void addCompletedSurvey(String surveyTitle) {
        if (!completedSurveys.contains(surveyTitle)) {
            completedSurveys.add(surveyTitle);
        }
    }

    public Survey getSurveyByTitle(String title) {
        ArrayList<Survey> surveys = new DBHelper(getActivity()).get_surveyList();
        for(Survey s : surveys){
            if(s.getTitle().equals(title)){
                return s;
            }
        }
        return null;
    }
}
