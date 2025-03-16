package com.example.personaldiary;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView; // Make sure this import is added
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class WelcomeActivity extends AppCompatActivity {

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Initialize the ImageView and load the GIF
        ImageView imageView = findViewById(R.id.welcomeImage);
        Glide.with(this)
                .asGif() // This ensures Glide treats the input as a GIF
                .load(R.drawable.animation_7) // Ensure your GIF file is correctly placed in res/drawable
                .into(imageView);
    }

    public void goToLoginPage(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
