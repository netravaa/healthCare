package com.example.myhealthapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class DoctorAppointments extends AppCompatActivity {

    private TextView tvAppointmentInfo;
    private TextView btnCancelAppointment;
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointments);

        tvAppointmentInfo = findViewById(R.id.tv_appointment_info);
        btnCancelAppointment = findViewById(R.id.btn_cancel_appointment);
        container = findViewById(R.id.container);

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
            btnCancelAppointment.setVisibility(View.GONE);
        }else {
            tvAppointmentInfo.setVisibility(View.GONE);
            btnCancelAppointment.setVisibility(View.GONE);
            for (Map.Entry<String, String> entry : recentAppointments.entrySet()) {
                TextView appointmentTextView = new TextView(this);
                appointmentTextView.setText(entry.getValue());
                appointmentTextView.setTextSize(18);
                appointmentTextView.setTextColor(getResources().getColor(android.R.color.black));
                appointmentTextView.setPadding(0, 16, 0, 16);

                TextView cancelButton = new TextView(this);
                cancelButton.setText("Отменить");
                cancelButton.setTextSize(16);
                cancelButton.setTextColor(getResources().getColor(android.R.color.black));
                cancelButton.setPadding(0, 16, 0, 16);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelAppointment(entry.getKey());
                    }
                });

                container.addView(appointmentTextView);
                container.addView(cancelButton);
            }
        }
    }

    private void cancelAppointment(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppointments", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
        recreate();
    }
}
