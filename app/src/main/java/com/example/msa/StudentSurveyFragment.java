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

    private String currentSurveys;

    public StudentSurveyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_surveys, container, false);

        LinearLayout layoutSurveys = view.findViewById(R.id.layoutSurveys);

        currentSurveys = "";

        Bundle arguments = getArguments();

        return view;
    }
}
