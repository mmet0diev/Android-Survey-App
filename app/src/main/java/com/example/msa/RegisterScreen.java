package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msa.Model.DBModel;
import com.example.msa.Model.User;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
    }

    public void onReg(View view){
        // Check and reg new users
        String reg_user = ((EditText)findViewById(R.id.ruserinput)).getText().toString();
        String reg_pass = ((EditText)findViewById(R.id.rpassinput)).getText().toString();
        String reg_pass1 = ((EditText)findViewById(R.id.rpassinput1)).getText().toString();

        if(reg_user.isEmpty() || reg_pass.isEmpty() || reg_pass1.isEmpty()){
            Toast.makeText(this, "Some/all fields not filled", Toast.LENGTH_LONG).show();
        }else{
            if(reg_pass.equals(reg_pass1)) {
                User newUsr = new User(-1, 0, reg_user, reg_pass, "");
                DBModel myDB = new DBModel(this);
                int res = myDB.addUser(newUsr);
                if(res == 1){
                    Toast.makeText(this, "User registered", Toast.LENGTH_LONG).show();
                }else if(res == -3){
                    Toast.makeText(this, "Username already exists", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Error, user could not be registered", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "passwords don't match", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onCancel(View view){
        Intent intent = new Intent(this, InitScreen.class);
        finish();
        startActivity(intent);
    }
}