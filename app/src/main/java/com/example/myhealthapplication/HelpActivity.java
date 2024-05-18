package com.example.myhealthapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class HelpActivity extends AppCompatActivity {
    private TextView tvExit;
    private TextView tvHelp;
    private TextView tvIdea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        tvExit = findViewById(R.id.tv_exit);
        tvHelp = findViewById(R.id.tv_help);
        tvIdea = findViewById(R.id.tv_idea);
        tvExit.setOnClickListener(v -> startActivity(new Intent(HelpActivity.this, LoginActivity.class)));
        tvHelp.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/sokolovvladimir"));
            startActivity(intent);
        });
        tvIdea.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/sokolovvladimir"));
            startActivity(intent);
        });

    }
}