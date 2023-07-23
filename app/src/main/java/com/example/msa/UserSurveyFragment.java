package com.example.msa;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.Question;
import com.example.msa.Model.Survey;

import java.util.ArrayList;

public class UserSurveyFragment extends Fragment {

    private String currentSurveys;
    private ArrayList<Survey> surveys;
    int questionsNum;
    public UserSurveyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_surveys, container, false);

        LinearLayout layoutSurveys = view.findViewById(R.id.layoutSurveys);

        surveys = getAvailableSurveys(); // Replace this with your method to fetch the available surveys

        // Retrieve the arguments bundle
        Bundle args = getArguments();
        if (args != null) {
            // Extract the questionsNum value from the bundle
            questionsNum = args.getInt("questionsNum", 0);
        }

        for (Survey survey : surveys) {
            TextView surveyTextView = new TextView(requireContext());
            surveyTextView.setText(survey.getTitle());
            surveyTextView.setTextSize(18);
            surveyTextView.setPadding(10, 10, 10, 10);
            surveyTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the survey click event here
                    // Start the ActiveSurvey activity and pass the necessary data
                    startActiveSurveyActivity(survey);
                }
            });

            layoutSurveys.addView(surveyTextView);
        }

        return view;
    }

    private ArrayList<Survey> getAvailableSurveys() {
        // Implement this method to retrieve the available surveys for the logged-in user
        // For now, you can return the allSurveys list as a placeholder.
        return ((UserPane) requireActivity()).allSurveys;
    }

    private void startActiveSurveyActivity(Survey survey) {
        if(questionsNum == 10) {
            Intent intent = new Intent(requireContext(), ActiveSurvey.class);
            int userId = ((UserPane) requireActivity()).loggedUser.getId();
            intent.putExtra("userId", userId);
            intent.putExtra("title", survey.getTitle());
            startActivity(intent);
        }else{
            Toast.makeText(requireContext(), "Not enough questions in Survey.", Toast.LENGTH_LONG).show();
        }
    }
}
