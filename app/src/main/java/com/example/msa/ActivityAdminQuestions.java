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

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.Question;

import java.util.ArrayList;

public class ActivityAdminQuestions extends AppCompatActivity {

    private DBHelper dbHelper;
    private ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_questions);

        dbHelper = new DBHelper(this);
        questions = dbHelper.getQuestionList();

        ListView listView = findViewById(R.id.quest_listview);
        QuestionAdapter adapter = new QuestionAdapter(this, questions);
        listView.setAdapter(adapter);
    }

    public class QuestionAdapter extends BaseAdapter {

        private ArrayList<Question> questions;
        private LayoutInflater inflater;

        public QuestionAdapter(Context context, ArrayList<Question> questions){
            this.questions = questions;
            inflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return questions.size();
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
            if(view == null){
                view = inflater.inflate(R.layout.activity_admin_questions_view, parent, false);
            }

            Question quest = questions.get(position);

            TextView questTxt = view.findViewById(R.id.questiontxt);
            questTxt.setText(quest.toString());

            return view;
        }
    }
}