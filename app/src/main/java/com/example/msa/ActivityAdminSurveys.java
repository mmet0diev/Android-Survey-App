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
import com.example.msa.Model.Survey;

import java.util.ArrayList;

public class ActivityAdminSurveys extends AppCompatActivity {

    private ArrayList<Survey> surveys;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_surveys);

        dbHelper = new DBHelper(this);
        surveys = dbHelper.get_surveyList();

        ListView listView = findViewById(R.id.survey_listview);
        SurveyAdapter adapter = new SurveyAdapter(this, surveys);
        listView.setAdapter(adapter);
    }

    public class SurveyAdapter extends BaseAdapter {

        private ArrayList<Survey> surveys;
        private LayoutInflater inflater;

        public SurveyAdapter(Context context, ArrayList<Survey> surveys){
            this.surveys = surveys;
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
            if(view == null){
                view = inflater.inflate(R.layout.activity_admin_surveys_view, parent, false);
            }

            Survey survey = surveys.get(position);

            TextView surveyTxt = view.findViewById(R.id.surveytxt);
            surveyTxt.setText(survey.toString());

            return view;
        }
    }
}