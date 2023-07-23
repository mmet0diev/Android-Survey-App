package com.example.msa.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBModel extends SQLiteOpenHelper {

    private static final String DB_NAME = "appdatabase.db";
    private static final int VERSION = 1;

    /* Answer Table */
    private final String answerTable = "Answer";
    private final String answerColId = "id";
    private final String answerColAnswer = "answer";

    /* Question Table */
    private final String questionTable = "Question";
    private final String questionColId = "id";
    private final String questionCol = "question";

    /* Survey Table */
    private final String surveyTable = "Survey";
    private final String surveyColId = "id";
    private final String surveyTitleCol = "title";

    /* User Table */
    private final String userTable = "User";
    private final String userIdCol = "id";
    private final String userIsAdminCol = "isAdmin";
    private final String userLoginNameCol = "loginName";
    private final String userPassCol = "password";
    private final String userSurveysCol = "surveys";

    public DBModel(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    // This is called the first time a database is accessed
    // Create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sqlCreateStatement1 = "CREATE TABLE " + answerTable + " ( " + answerColId +
                    " INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, " + answerColAnswer + " INTEGER NOT NULL ) ";

            String sqlCreateStatement2 = "CREATE TABLE " + questionTable + " ( " + questionColId +
                    " INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, " + questionCol + " TEXT NOT NULL)";

            String sqlCreateStatement3 = "CREATE TABLE " + surveyTable + " ( " + surveyColId +
                    " INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, " +
                    surveyTitleCol + " TEXT NOT NULL)";

            String sqlCreateStatement4 = "CREATE TABLE " + userTable + " (" +
                    userIdCol + " INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, " +
                    userIsAdminCol + " INTEGER NOT NULL COLLATE BINARY, " +
                    userLoginNameCol + " TEXT NOT NULL UNIQUE, " +
                    userPassCol + " TEXT NOT NULL, " +
                    userSurveysCol + " TEXT NOT NULL )"; // Add the surveys column with a default value of an empty string

            db.execSQL(sqlCreateStatement1);
            db.execSQL(sqlCreateStatement2);
            db.execSQL(sqlCreateStatement3);
            db.execSQL(sqlCreateStatement4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase p0, int p1, int p2) {
        if (VERSION < 2) {
            // Perform the necessary changes to update from version 1 to version 2
            // For example, if you need to add new columns or modify existing ones, do it here

            // You can use the ALTER TABLE statement to add columns, like this:
            // String sqlAlterStatement = "ALTER TABLE " + userTable + " ADD COLUMN " + userSurveysCol + " TEXT DEFAULT ''";
            // db.execSQL(sqlAlterStatement);
        }
        // Handle other version updates if needed...
    }

    /**
     * return 1 : the new user has been added to the database successfully
     * return -1 : Error, adding new user
     * return -2 : can not open database
     * return -3 : user name already exists
     */

    public long addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(questionCol, question.getText());

        long success = db.insert(questionTable, null, cv);
        db.close();
        return success;
    }

    public void addAnswer(Answer answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(answerColAnswer, answer.getAnswer());

        db.insert(answerTable, null, cv);
        db.close();
    }

    // Add a new survey to the database
    public long addSurvey(Survey survey) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(surveyTitleCol, survey.getTitle());

        long success = db.insert(surveyTable, null, cv);
        db.close();
        return success;
    }

    public int addUser(User user) {
        int isUserNameAlreadyExists = checkUserName(user);
        if (isUserNameAlreadyExists < 0)
            return isUserNameAlreadyExists;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(userLoginNameCol, user.getLoginName());
        cv.put(userPassCol, user.getPassword());
        cv.put(userIsAdminCol, user.getIsAdmin());
        cv.put(userSurveysCol, user.getSurveys()); // Save the surveys data

        long success = db.insert(userTable, null, cv);

        db.close();
        if (success == -1)
            return (int) success;
        else
            return 1;
    }

    public int checkUserName(User user) {
        SQLiteDatabase db;
        try {
            db = this.getReadableDatabase();
        } catch (SQLiteException e) {
            return -2;
        }

        String userName = user.getLoginName().trim().toLowerCase();

        String sqlStatement = "SELECT * FROM " + userTable + " WHERE LOWER(" + userLoginNameCol + ") = ?";
        String[] param = {userName};
        Cursor cursor = db.rawQuery(sqlStatement, param);

        if (cursor.moveToFirst()) {
            int n = cursor.getInt(0);
            cursor.close();
            db.close();
            return -3;
        }

        cursor.close();
        db.close();
        return 0;
    }

    public ArrayList<Answer> getAllAnswers() {
        ArrayList<Answer> answersList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlStatement = " SELECT * FROM " + answerTable;

        Cursor cursor = db.rawQuery(sqlStatement, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int answer = cursor.getInt(1);
                Answer a = new Answer(id, answer);
                answersList.add(a);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return answersList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlStatement = " SELECT * FROM " + questionTable;

        Cursor cursor = db.rawQuery(sqlStatement, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String question = cursor.getString(1);
                Question q = new Question(id, question);
                questionList.add(q);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return questionList;
    }

    public ArrayList<Survey> getAllSurveys() {
        ArrayList<Survey> surveyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlStatement = " SELECT * FROM " + surveyTable;

        Cursor cursor = db.rawQuery(sqlStatement, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);

                Survey s = new Survey(id, title);
                surveyList.add(s);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return surveyList;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlStatement = "SELECT * FROM " + userTable;

        Cursor cursor = db.rawQuery(sqlStatement, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int admin = cursor.getInt(1);
                String login = cursor.getString(2);
                String pass = cursor.getString(3);

                User u = new User(id, admin, login, pass, "");
                userList.add(u);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }

    public User getUserById(int userId) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns you want to retrieve from the User table
        String[] columns = {userIdCol, userIsAdminCol, userLoginNameCol, userPassCol, userSurveysCol};

        // Define the WHERE clause to find the user by ID
        String selection = userIdCol + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        // Query the User table to get the user with the given ID
        Cursor cursor = db.query(userTable, columns, selection, selectionArgs, null, null, null);

        // Check if the cursor has a valid result
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(userIdCol));
            int isAdmin = cursor.getInt(cursor.getColumnIndex(userIsAdminCol));
            String loginName = cursor.getString(cursor.getColumnIndex(userLoginNameCol));
            String password = cursor.getString(cursor.getColumnIndex(userPassCol));
            String surveys = cursor.getString(cursor.getColumnIndex(userSurveysCol));

            // Create the User object with the retrieved data
            user = new User(id, isAdmin, loginName, password, surveys);
            cursor.close();
        }

        // Close the database connection and return the User object
        db.close();
        return user;
    }

    // Method to remove a question by its ID
    public boolean removeQuestion(long questionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(questionTable, questionColId + " = ?", new String[]{String.valueOf(questionId)});
        db.close();
        return result != 0; // Return true if the row was deleted, false otherwise
    }

    public void removeSurvey(int surveyId) {
        SQLiteDatabase db = getWritableDatabase();

        // Delete the survey from the Survey table
        String deleteSurveyStatement = "DELETE FROM " + surveyTable + " WHERE " + surveyColId + " = ?";
        String[] surveyIdArgs = {String.valueOf(surveyId)};
        db.execSQL(deleteSurveyStatement, surveyIdArgs);

        // Delete the corresponding answers from the Answer table
        String deleteAnswersStatement = "DELETE FROM " + answerTable + " WHERE " + answerColId + " = ?";
        String[] surveyIdArgsForAnswers = {String.valueOf(surveyId)};
        db.execSQL(deleteAnswersStatement, surveyIdArgsForAnswers);

        db.close();
    }

}