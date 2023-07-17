package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.msa.Model.Answer;
import com.example.msa.Model.DBHelper;

import java.util.ArrayList;

public class ActivityUserAnswers extends AppCompatActivity {

    private ArrayList<Answer> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_answers_listview);

        answers = new DBHelper(this).get_answerList();

        ListView answerListView = findViewById(R.id.answerlistview);
        SurveyAnswerAdapter adapter = new SurveyAnswerAdapter(this, answers);
        answerListView.setAdapter(adapter);
    }

    public class SurveyAnswerAdapter extends ArrayAdapter<Answer> {

        public SurveyAnswerAdapter(Context context, ArrayList<Answer> answers) {
            super(context, 0, answers);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
//            Answer answer = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_user_answers_rows, parent, false);
            }

            // Return the completed view to render on screen
            return convertView;
        }
    }
}

