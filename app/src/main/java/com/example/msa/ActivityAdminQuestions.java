package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.DBModel;
import com.example.msa.Model.Question;

import java.util.ArrayList;

public class ActivityAdminQuestions extends AppCompatActivity {

    private DBModel dbModel;
    private DBHelper dbHelper;
    private ArrayList<Question> questions;
    private EditText addQuestionEditText;
    private Button addQuestionButton;
    private QuestionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_questions);

        dbModel = new DBModel(this);
        dbHelper = new DBHelper(this);
        questions = dbHelper.getQuestionList();

        ListView listView = findViewById(R.id.quest_listview);
        adapter = new QuestionAdapter(this, questions);
        listView.setAdapter(adapter);

        // Get references to the EditText and Button for adding questions
        addQuestionEditText = findViewById(R.id.addQuestionTxt);
        addQuestionButton = findViewById(R.id.addQuestionBtn);

        // Set the click listener for the "Add" button
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle adding a new question
                addNewQuestion();
            }
        });
    }

    // Method to handle adding a new question
    private void addNewQuestion() {
        // Get the question text from the EditText
        String newQuestionText = addQuestionEditText.getText().toString().trim();

        if (newQuestionText.isEmpty()) {
            // If the question text is empty, show a message to the user
            Toast.makeText(this, "Please enter a question.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Question object with the provided question text
        Question newQuestion = new Question(-1, newQuestionText);

        // Insert the new question into the database
        long success = dbModel.addQuestion(newQuestion);

        if (success != -1) {
            // If the insertion was successful, update the question list and refresh the ListView
            questions.add(newQuestion);
            adapter.notifyDataSetChanged();

            // Clear the EditText after adding the question
            addQuestionEditText.setText("");

            // Show a message to the user indicating successful addition
            Toast.makeText(this, "Question added successfully.", Toast.LENGTH_SHORT).show();
        } else {
            // Handle the case where the insertion failed
            Toast.makeText(this, "Failed to add the question.", Toast.LENGTH_SHORT).show();
        }
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
            if (view == null) {
                view = inflater.inflate(R.layout.activity_admin_questions_view, parent, false);
            }

            Question quest = questions.get(position);

            TextView questTxt = view.findViewById(R.id.questiontxt);
            questTxt.setText(quest.toString());

            Button removeButton = view.findViewById(R.id.removeButton);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeQuestion(quest);
                }
            });

            return view;
        }

        // Method to remove the question from the list and database
        public void removeQuestion(Question question) {
            boolean success = dbModel.removeQuestion(question.getId());

            if (success) {
                // If removal was successful, update the question list and refresh the ListView
                questions.remove(question);
                notifyDataSetChanged();

                // Show a message to the user indicating successful removal
                Toast.makeText(ActivityAdminQuestions.this, "Question removed successfully.", Toast.LENGTH_SHORT).show();
            } else {
                // Handle the case where removal failed
                Toast.makeText(ActivityAdminQuestions.this, "Failed to remove the question.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}