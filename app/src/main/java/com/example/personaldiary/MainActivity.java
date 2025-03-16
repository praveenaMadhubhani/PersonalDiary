package com.example.personaldiary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewDiaryButton = findViewById(R.id.viewDiaryButton);
        Button addEntryButton = findViewById(R.id.addEntryButton);
        Button settingsButton = findViewById(R.id.settingsButton);

        viewDiaryButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewEntriesActivity.class)));
        addEntryButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddEntryActivity.class)));
        settingsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));
    }
}
