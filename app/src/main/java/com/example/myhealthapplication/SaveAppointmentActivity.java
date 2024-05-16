package com.example.myhealthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SaveAppointmentActivity extends AppCompatActivity {

    private TextView tvDoctorName;
    private TextView tvAppointmentTime;
    private Calendar appointmentDateTime;
    private Button btnSelectTime;
    private Button btnSaveAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_appointment);

        tvDoctorName = findViewById(R.id.tv_doctor_name);
        tvAppointmentTime = findViewById(R.id.tv_appointment_time);
        btnSelectTime = findViewById(R.id.btn_select_time);
        btnSaveAppointment = findViewById(R.id.btn_save_appointment);

        Intent intent = getIntent();
        String doctorName = intent.getStringExtra("doctorName");
        tvDoctorName.setText(doctorName);

        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        btnSaveAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAppointment();
            }
        });
    }

    private void showTimePickerDialog() {
        final Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        appointmentDateTime = Calendar.getInstance();
                        appointmentDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        appointmentDateTime.set(Calendar.MINUTE, minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        tvAppointmentTime.setText("Время приема: " + sdf.format(appointmentDateTime.getTime()));
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    private void saveAppointment() {
        if (appointmentDateTime == null) {
            Toast.makeText(this, "Пожалуйста, выберите время приема", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username","").toString();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH:mm", Locale.getDefault());
        String formattedDateTime = sdf.format(appointmentDateTime.getTime());
        String appointmentInfo = tvDoctorName.getText().toString() + " " + formattedDateTime;

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppointments", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username + "_appointment_" + formattedDateTime, appointmentInfo);
        editor.apply();

        Toast.makeText(this, "Запись сохранена", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SaveAppointmentActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}