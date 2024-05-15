package com.example.myhealthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
public class DoctorAppointments extends AppCompatActivity {

    private TextView tvAppointmentInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointments);

        tvAppointmentInfo = findViewById(R.id.tv_appointment_info);

        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppointments", MODE_PRIVATE);
        Map<String, ?> allEntries = (Map<String, ?>) sharedPreferences.getAll();

        Calendar oneWeekAgo = Calendar.getInstance();
        oneWeekAgo.add(Calendar.WEEK_OF_YEAR, -1);


        Map<String, String> recentAppointments = new TreeMap<>();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().startsWith(username + "_appointment_") && entry.getValue() instanceof String) {
                String key = entry.getKey();
                String value = (String) entry.getValue();
                if (key.substring(username.length() + 12).compareTo(new SimpleDateFormat("dd-MM-yyyy_HH:mm", Locale.getDefault()).format(oneWeekAgo.getTime())) >= 0) {
                    recentAppointments.put(key, value);
                }
            }
        }

        if (recentAppointments.isEmpty()) {
            tvAppointmentInfo.setText("Вы не записаны к врачу");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String appointmentInfo : recentAppointments.values()) {
                sb.append(appointmentInfo).append("\n\n");
            }
            tvAppointmentInfo.setText(sb.toString());
        }
    }
}