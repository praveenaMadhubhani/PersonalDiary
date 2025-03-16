package com.example.personaldiary;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.firebase.firestore.FirebaseFirestore;
public class LoginActivity extends AppCompatActivity {

    private TextView dateTimeDisplay;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ToggleButton togglePasswordVisibility;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView imageView = findViewById(R.id.homeImage);
        dateTimeDisplay = findViewById(R.id.dateTimeDisplay);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);
        loginButton = findViewById(R.id.loginButton);

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        dateTimeDisplay.setText("Date: " + sdfDate.format(new Date()) + "\nTime: " + sdfTime.format(new Date()));

        Glide.with(this)
                .asGif()
                .load(R.drawable.animation_2)
                .into(imageView);

        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (togglePasswordVisibility.isChecked()) {
                    passwordEditText.setInputType(144); // Visible password
                } else {
                    passwordEditText.setInputType(129); // Hidden password
                }
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (validateLogin(username, password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateLogin(String username, String password) {
        return "Praveena".equals(username) && "Pa1234@Pa".equals(password);
    }
}
