package com.example.personaldiary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ToggleButton;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText developerName, password, email;
    private ToggleButton togglePasswordVisibility;
    private Button saveSettingsButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        developerName = findViewById(R.id.developerName);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);  // Initialize the new EditText
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);
        saveSettingsButton = findViewById(R.id.saveSettingsButton);
        backButton = findViewById(R.id.backButton);

        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (togglePasswordVisibility.isChecked()) {
                    password.setInputType(144); // Visible password
                } else {
                    password.setInputType(129); // Hidden password
                }
                password.setSelection(password.getText().length());
            }
        });

        saveSettingsButton.setOnClickListener(v -> {
            String name = developerName.getText().toString();
            String pwd = password.getText().toString();
            String userEmail = email.getText().toString();  // Get the email from the input
            // Encode password to Base64 before saving
            String encodedPassword = encryptPassword(pwd);
            saveCredentials(name, encodedPassword, userEmail);
        });

        backButton.setOnClickListener(v -> finish()); // This will close the current activity and go back
    }

    private void saveCredentials(String name, String encodedPassword, String email) {
        SharedPreferences sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("DeveloperName", name);
        editor.putString("Password", encodedPassword);
        editor.putString("Email", email);  // Save the email
        editor.apply();
    }

    private String encryptPassword(String password) {
        // Simple encoding to Base64, replace with actual encryption method if needed
        return Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
    }
}
