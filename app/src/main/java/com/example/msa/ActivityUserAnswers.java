package com.example.msa;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.msa.Model.Answer;
import com.example.msa.Model.DBHelper;

import java.util.ArrayList;

public class ActivityUserAnswers extends AppCompatActivity {

    private ArrayList<Answer> answers;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_answers_listview);

        dbHelper = new DBHelper(this);
        answers = dbHelper.getAnswerList();

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
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.activity_user_answers_rows, parent, false);
            }

            Answer answer = getItem(position);

            // Assuming your XML layout for each answer row contains TextViews with ids: answer1, answer2, ..., answer10
            TextView answer1 = view.findViewById(R.id.answer1);
            TextView answer2 = view.findViewById(R.id.answer2);
            TextView answer3 = view.findViewById(R.id.answer3);
            TextView answer4 = view.findViewById(R.id.answer4);
            TextView answer5 = view.findViewById(R.id.answer5);
            TextView answer6 = view.findViewById(R.id.answer6);
            TextView answer7 = view.findViewById(R.id.answer7);
            TextView answer8 = view.findViewById(R.id.answer8);
            TextView answer9 = view.findViewById(R.id.answer9);
            TextView answer10 = view.findViewById(R.id.answer10);


            // Populate the TextViews with the answer data
            answer1.setText(answer.getAnswer());
            answer2.setText(answer.getAnswer());
            answer3.setText(answer.getAnswer());
            answer4.setText(answer.getAnswer());
            answer5.setText(answer.getAnswer());
            answer6.setText(answer.getAnswer());
            answer7.setText(answer.getAnswer());
            answer8.setText(answer.getAnswer());
            answer9.setText(answer.getAnswer());
            answer10.setText(answer.getAnswer());
            // ... Set other TextViews with respective answers up to answer10

            return view;
        }
    }
}

