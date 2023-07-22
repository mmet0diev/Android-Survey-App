package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.msa.Model.Answer;
import com.example.msa.Model.DBHelper;
import com.example.msa.Model.Survey;

import java.util.ArrayList;

public class ActivityAdminAnswers extends AppCompatActivity {

    private ArrayList<Answer> answers;
    private ArrayList<Survey> surveys;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_answers);

        dbHelper = new DBHelper(this);
        answers = dbHelper.getAnswerList();
        surveys = dbHelper.getSurveyList();

        ListView listView = findViewById(R.id.answers_listview);
        AnswerAdapter adapter = new AnswerAdapter(this, surveys, answers);
        listView.setAdapter(adapter);

    }

    public class AnswerAdapter extends BaseAdapter {

        private ArrayList<Survey> surveys;
        private ArrayList<Answer> answers;
        private LayoutInflater inflater;

        public AnswerAdapter(Context context, ArrayList<Survey> surveys, ArrayList<Answer> answers) {
            this.surveys = surveys;
            this.answers = answers;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return surveys.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = inflater.inflate(R.layout.activity_admin_answers_view, parent, false);
            }

            Survey survey = surveys.get(position);

            TextView surveyText = view.findViewById(R.id.text_survey);
            surveyText.setText(survey.getTitle());

            TextView answerText = view.findViewById(R.id.text_answers);
            answerText.setText(""); // clear the TextView

            StringBuilder answersBuilder = new StringBuilder();
            int startIndex = position * 10;
            int endIndex = Math.min(startIndex + 10, answers.size());
            for (int i = startIndex; i < endIndex; i++) {
                Answer answer = answers.get(i);
                answersBuilder.append(answer.toString());
            }
            answerText.setText(answersBuilder.toString());

            return view;
        }
    }
}