package com.example.myhealthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OtherDetailsActivity extends AppCompatActivity {

    private Button btnClearUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_details);

        btnClearUserData = findViewById(R.id.btn_clear_user_data);

        btnClearUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearUserData();
            }
        });
    }

    private void clearUserData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppointments", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(OtherDetailsActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}