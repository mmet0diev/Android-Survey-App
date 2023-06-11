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
    private final String surveyStartCol = "start";
    private final String surveyEndCol = "endd";

    /* User Table */
    private final String userTable = "User";
    private final String userIdCol = "id";
    private final String userIsAdminCol = "isAdmin";
    private final String userLoginNameCol = "loginName";
    private final String userPassCol = "password";

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

            db.execSQL(sqlCreateStatement1);

            String sqlCreateStatement2 = "CREATE TABLE " + questionTable + " ( " + questionColId +
                    " INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, " + questionCol + " TEXT NOT NULL)";

            db.execSQL(sqlCreateStatement2);

            String sqlCreateStatement3 = "CREATE TABLE " + surveyTable + " ( " + surveyColId +
                    " INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, " +
                    surveyTitleCol + " TEXT NOT NULL,\n" + surveyStartCol + " TEXT ," + surveyEndCol + " TEXT )";

            db.execSQL(sqlCreateStatement3);

            String sqlCreateStatement4 = "CREATE TABLE " + userTable + " ( " + userIdCol +
                    " INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, " + userIsAdminCol +
                    " INTEGER NOT NULL COLLATE BINARY,\n" +
                    userLoginNameCol + " TEXT NOT NULL UNIQUE, " + userPassCol + " TEXT NOT NULL )";

            db.execSQL(sqlCreateStatement4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase p0, int p1, int p2) {
        // Not yet implemented
    }

    /**
     * return 1 : the new user has been added to the database successfully
     * return -1 : Error, adding new user
     * return -2 : can not open database
     * return -3 : user name already exists
     */

    public void addQuestion(Question question) {

    }

    public void addAnswer(Answer answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(answerColAnswer, answer.getAnswer());

        db.insert(answerTable, null, cv);
        db.close();
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

        String userName = user.getLoginName().toLowerCase();

        String sqlStatement = "SELECT * FROM " + userTable + " WHERE " + userLoginNameCol + " = ?";
        String[] param = { userName };
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
                String start = cursor.getString(2);
                String end = cursor.getString(3);

                Survey s = new Survey(id, title, start, end);
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

                User u = new User(id, admin, login, pass);
                userList.add(u);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return userList;
    }

    public User getUser(String loginName) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + userTable + " WHERE " + userLoginNameCol + " = ?";
        String[] args = {loginName};
        Cursor cursor = db.rawQuery(sql, args);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            int isAdmin = cursor.getInt(1);
            String name = cursor.getString(2);
            String password = cursor.getString(3);
            db.close();
            return new User(id, isAdmin, name, password);
        } else {
            db.close();
            return null;
        }
    }

}
