package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.DBModel;
import com.example.msa.Model.Question;
import com.example.msa.Model.Survey;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActivityAdminSurveys extends AppCompatActivity {

    private ArrayList<Survey> surveys;
    private DBHelper dbHelper;
    private DBModel dbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_surveys);

        dbHelper = new DBHelper(this);
        dbModel = new DBModel(this);
        surveys = dbHelper.getSurveyList();

        ListView listView = findViewById(R.id.survey_listview);
        SurveyAdapter adapter = new SurveyAdapter(this, surveys);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbModel != null) {
            dbModel.close(); // Close the database connection
        }
    }
    public void addSurvey(View view) {
        EditText addSurveyTxt = findViewById(R.id.addSurveyTxt);
        String surveyName = addSurveyTxt.getText().toString().trim();

        if (!surveyName.isEmpty()) {
            // Create a new Survey object with the provided name
            Survey newSurvey = new Survey(surveyName);

            // Save the new survey in the database
            long success = dbModel.addSurvey(newSurvey);

            if (success != -1) {
                // If the survey was added successfully, update the UI
                // The id field in the newSurvey object will be updated automatically

                // Add the new survey to the surveys list and update the ListView
                surveys.add(newSurvey);
                SurveyAdapter adapter = (SurveyAdapter) ((ListView) findViewById(R.id.survey_listview)).getAdapter();
                adapter.notifyDataSetChanged();

                // Clear the input field after adding the survey
                addSurveyTxt.setText("");
            } else {
                // Handle the case where the survey could not be added

            }
        } else {
            // Handle the case where the user didn't provide a survey name
            Toast.makeText(this, "Can't create a survey with no title", Toast.LENGTH_LONG).show();
        }
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