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

            ArrayList<String> newAnswers = retrieveAnswers();

            // Populate the TextViews with the answer data
            answer1.setText(newAnswers.get(0));
            answer2.setText(newAnswers.get(1));
            answer3.setText(newAnswers.get(2));
            answer4.setText(newAnswers.get(3));
            answer5.setText(newAnswers.get(4));
            answer6.setText(newAnswers.get(5));
            answer7.setText(newAnswers.get(6));
            answer8.setText(newAnswers.get(7));
            answer9.setText(newAnswers.get(8));
            answer10.setText(newAnswers.get(9));
            // ... Set other TextViews with respective answers up to answer10

            return view;
        }

        public ArrayList<String> retrieveAnswers(){
            ArrayList<String> takenAnswers = new ArrayList<>();
            if(answers.size() >= 10) {
                for (int i = answers.size()-10; i < answers.size(); i++) {
                    takenAnswers.add(answers.get(i).getAnswer()+"");
                }
            }
            return takenAnswers;
        }
    }
}

