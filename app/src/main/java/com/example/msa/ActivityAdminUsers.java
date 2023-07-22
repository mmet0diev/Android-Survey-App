package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.DBModel;
import com.example.msa.Model.User;

import java.util.ArrayList;

public class ActivityAdminUsers extends AppCompatActivity {
    private DBHelper dbHelper;
    private DBModel dbModel;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users);

        dbHelper = new DBHelper(this);
        dbModel = new DBModel(this);
        users = dbHelper.getUserList();

        ListView listView = findViewById(R.id.users_listview);
        UserAdapter adapter = new UserAdapter(this, users);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbModel != null) {
            dbModel.close(); // Close the database connection
        }
    }

    public void addUser(View view) {
        Log.d("AddUser", "addUser method called");

        EditText addUserLoginName = findViewById(R.id.addUserLoginName);
        EditText addUserPassword = findViewById(R.id.addUserPassword);
        Switch addUserIsAdminSwitch = findViewById(R.id.addUserIsAdminSwitch);

        String loginName = addUserLoginName.getText().toString().trim();
        String password = addUserPassword.getText().toString().trim();
        int isAdmin = addUserIsAdminSwitch.isChecked() ? 1 : 0;
        String avlbSurveys = "";

        Log.d("AddUser", "Login Name: " + loginName);
        Log.d("AddUser", "Password: " + password);
        Log.d("AddUser", "Is Admin: " + isAdmin);

        if (!loginName.isEmpty() && !password.isEmpty()) {
            // Create a new User object with the provided details
            User newUser = new User(-1, isAdmin, loginName, password, avlbSurveys);

            // Save the new user in the database
            int result = dbModel.addUser(newUser);

            if (result == 1) {
                // If the user was added successfully, update the UI
                // The id field in the newUser object will be updated automatically

                // Add the new user to the users list and update the ListView
                users.add(newUser);
                UserAdapter adapter = (UserAdapter) ((ListView) findViewById(R.id.users_listview)).getAdapter();
                adapter.notifyDataSetChanged();

                // Clear the input fields after adding the user
                addUserLoginName.setText("");
                addUserPassword.setText("");
                addUserIsAdminSwitch.setChecked(false);

                // Show a toast message indicating success
                Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show();
            } else if (result == -3) {
                // Handle the case where the user name already exists
                Toast.makeText(this, "User name already exists. Please choose a different name.", Toast.LENGTH_SHORT).show();
            } else {
                // Handle the case where the user could not be added
                Toast.makeText(this, "Error adding user. Please try again.", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle the case where the user didn't provide login name or password
            Toast.makeText(this, "Please enter login name and password.", Toast.LENGTH_SHORT).show();
        }
    }


    public class UserAdapter extends BaseAdapter {

        private ArrayList<User> users;
        private LayoutInflater inflater;

        public UserAdapter(Context context, ArrayList<User> users){
            this.users = users;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return users.size();
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
                view = inflater.inflate(R.layout.activity_admin_users_view, parent, false);
            }

            User user = users.get(position);

            TextView userText = view.findViewById(R.id.usertxt);
            userText.setText(user.toString());

            return view;
        }
    }
}