package com.example.msa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class UserSurveyFragment extends Fragment {

    private String currentSurveys;

    public UserSurveyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_surveys, container, false);

        LinearLayout layoutSurveys = view.findViewById(R.id.layoutSurveys);

        currentSurveys = "";

        Bundle arguments = getArguments();

        return view;
    }
}
