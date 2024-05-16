package com.example.myhealthapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class RecipesActivity extends AppCompatActivity {

    EditText notesEditText;
    SharedPreferences sharedPreferences;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        notesEditText = findViewById(R.id.notesEditText);

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        username = sharedpreferences.getString("username","").toString();
        sharedPreferences = getSharedPreferences("RecipesNotes", Context.MODE_PRIVATE);
        String savedNotes = sharedPreferences.getString(username, "");
        notesEditText.setText(savedNotes);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username, notesEditText.getText().toString());
        editor.apply();
    }
}