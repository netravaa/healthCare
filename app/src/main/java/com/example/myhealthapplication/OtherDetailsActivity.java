package com.example.myhealthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
public class OtherDetailsActivity extends AppCompatActivity {

    private TextView tvUserMail;
    private TextView tvExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_details);
        tvUserMail = findViewById(R.id.tv_user_mail);
        tvExit = findViewById(R.id.tv_exit);
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "").toString();
        tvUserMail.setText(username);
        tvExit.setOnClickListener(v -> startActivity(new Intent(OtherDetailsActivity.this, LoginActivity.class)));
    }

}