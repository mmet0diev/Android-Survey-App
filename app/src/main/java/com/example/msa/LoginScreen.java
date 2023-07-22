package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msa.Model.DBHelper;
import com.example.msa.Model.User;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    private ArrayList<User> current_users;
    private User user;
    public EditText login_user;
    public EditText login_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        current_users = new DBHelper(this).getUserList();
        login_user = findViewById(R.id.luserinput);
        login_pass = findViewById(R.id.lpassinput);
        String loginName = login_user.getText().toString();
        findUser(loginName);
    }

    public Boolean onLogin(View view) {
        boolean success = false;
        /* Check login credentials */
        String u_name = login_user.getText().toString();
        String u_pass = login_pass.getText().toString();

        if (u_name.isEmpty() || u_pass.isEmpty())
            Toast.makeText(this, "All input fields need to be filled", Toast.LENGTH_LONG).show();
        else {
            if (checkIfValidLogin(u_name, u_pass) == 0) {
                if(user.getIsAdmin() == 0) {
                    Intent i = new Intent(this, UserPane.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("userid", user.getId());
                    i.putExtras(bundle);
                    startActivity(i);
                    Toast.makeText(this, "Logged in", Toast.LENGTH_LONG).show();
                }

                if(user.getIsAdmin() == 1){
                    startActivity(new Intent(this, AdminPane.class));
                    Toast.makeText(this, "Logged in", Toast.LENGTH_LONG).show();
                }
                success = true;
            } else {
                Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_LONG).show();
            }
        }
        return success;
    }

    // return 0: login combination valid
    private int checkIfValidLogin(String u_name, String u_pass) {
        for (User u : current_users) {
            if (u.getLoginName().equals(u_name) && u.getPassword().equals(u_pass)) {
                user = u;
                return 0;
            }
        }
        return -1;
    }

    public User findUser(String loginName){
        for(User u : current_users){
            if(u.getLoginName().equals(loginName)){
                user = u;
            }
        }
        return user;
    }
}