package com.example.msa;

import static org.junit.Assert.*;

import android.widget.EditText;
import org.junit.Test;
import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testOnLogin() {
        // Setup
        ActivityController<LoginScreen> activityController = Robolectric.buildActivity(LoginScreen.class);
        LoginScreen loginScreen = activityController.get();
        EditText usernameEditText = loginScreen.findViewById(R.id.luserinput);
        EditText passwordEditText = loginScreen.findViewById(R.id.lpassinput);
        String validUsername = "user1";
        String validPassword = "pass1";

        // Test with valid credentials
        usernameEditText.setText(validUsername);
        passwordEditText.setText(validPassword);
        assertTrue(loginScreen.onLogin(loginScreen.findViewById(android.R.id.content)));

        // Test with invalid username
        usernameEditText.setText("invalidUsername");
        passwordEditText.setText(validPassword);
        assertTrue(loginScreen.onLogin(loginScreen.findViewById(android.R.id.content)));

        // Test with invalid password
        usernameEditText.setText(validUsername);
        passwordEditText.setText("invalidPassword");
        assertTrue(loginScreen.onLogin(loginScreen.findViewById(android.R.id.content)));

        // Test with empty fields
        usernameEditText.setText("");
        passwordEditText.setText("");
        assertTrue(loginScreen.onLogin(loginScreen.findViewById(android.R.id.content)));

        // Cleanup
        activityController.destroy();
    }
}


