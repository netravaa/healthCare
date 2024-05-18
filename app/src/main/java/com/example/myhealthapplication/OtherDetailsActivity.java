package com.example.myhealthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.net.Uri;

public class OtherDetailsActivity extends AppCompatActivity {

    private TextView tvUserMail;
    private TextView tvExit;
    private TextView tvHelp;
    private TextView tvIdea;
    private TextView tvNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_details);
        tvUserMail = findViewById(R.id.tv_user_mail);
        tvNotifications = findViewById(R.id.tv_notification);
        tvExit = findViewById(R.id.tv_exit);
        tvHelp = findViewById(R.id.tv_help);
        tvIdea = findViewById(R.id.tv_idea);
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "").toString();
        tvUserMail.setText(username);
        tvExit.setOnClickListener(v -> startActivity(new Intent(OtherDetailsActivity.this, LoginActivity.class)));
        tvHelp.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/sokolovvladimir"));
            startActivity(intent);
        });
        tvIdea.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/sokolovvladimir"));
            startActivity(intent);
        });
        tvNotifications.setOnClickListener(v -> setContentView(R.layout.activity_show_notifications));
    }

}