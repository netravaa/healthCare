package com.example.myhealthapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        Button buttonTherapist = findViewById(R.id.buttonTherapist);
        Button buttonGeneralPractitioner = findViewById(R.id.buttonGeneralPractitioner);
        Button buttonEndocrinologist = findViewById(R.id.buttonEndocrinologist);
        Button buttonOphthalmologist = findViewById(R.id.buttonOphthalmologist);
        Button buttonSurgeons = findViewById(R.id.buttonSurgeons);
        Button buttonDoctorAppointments = findViewById(R.id.buttonMyAppointments);

        buttonTherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к активности с терапевтами
                Intent intent = new Intent(FindDoctorActivity.this, TerapevtActivity.class);
                startActivity(intent);
            }
        });

        buttonGeneralPractitioner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к активности с участковыми
                Intent intent = new Intent(FindDoctorActivity.this, GeneralPractitionerActivity.class);
                startActivity(intent);
            }
        });
        buttonEndocrinologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к активности с участковыми
                Intent intent = new Intent(FindDoctorActivity.this, EndocrinologistActivity.class);
                startActivity(intent);
            }
        });
        buttonOphthalmologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к активности с участковыми
                Intent intent = new Intent(FindDoctorActivity.this, OphtalmologistActivity.class);
                startActivity(intent);
            }
        });
        buttonSurgeons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к активности с участковыми
                Intent intent = new Intent(FindDoctorActivity.this, SurgeonActivity.class);
                startActivity(intent);
            }
        });

        buttonDoctorAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к активности с записями пользователя
                Intent intent = new Intent(FindDoctorActivity.this, DoctorAppointments.class);
                startActivity(intent);
            }
        });

        
    }
}