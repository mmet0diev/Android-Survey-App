package com.example.msa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InitScreen extends AppCompatActivity {
    // FF7A7070
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLogin(View view){
        Intent intent = new Intent(InitScreen.this, LoginScreen.class);
        startActivity(intent);
    }

    public void goToReg(View view){
        Intent intent = new Intent(InitScreen.this, RegisterScreen.class);
        startActivity(intent);
    }
}