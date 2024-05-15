package com.example.myhealthapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SurgeonActivity extends AppCompatActivity {

    ListView SurgeonListView;
    Button buttonBookAppointment;
    Calendar appointmentDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgeon);

        SurgeonListView = findViewById(R.id.SurgeonListView);
        buttonBookAppointment = findViewById(R.id.buttonBookAppointment);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getSurgeonList());
        SurgeonListView.setAdapter(adapter);

        SurgeonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedSurgeon = getSurgeonList()[position];
                Toast.makeText(SurgeonActivity.this, "Выбран врач: " + selectedSurgeon, Toast.LENGTH_SHORT).show();
            }
        });

        buttonBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });
    }

    private void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH);
        int hour = currentDate.get(Calendar.HOUR_OF_DAY);
        int minute = currentDate.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        appointmentDateTime = Calendar.getInstance();
                        appointmentDateTime.set(year, monthOfYear, dayOfMonth);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(SurgeonActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        appointmentDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        appointmentDateTime.set(Calendar.MINUTE, minute);

                                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
                                        Toast.makeText(SurgeonActivity.this,
                                                "Выбранная дата и время: " + sdf.format(appointmentDateTime.getTime()),
                                                Toast.LENGTH_LONG).show();

                                        SharedPreferences sharedPreferences = getSharedPreferences("MyAppointments", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("appointmentInfo", "Хирург " + sdf.format(appointmentDateTime.getTime()));
                                        editor.apply();
                                        Intent intent = new Intent(SurgeonActivity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, hour, minute, true);
                        timePickerDialog.show();
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(currentDate.getTimeInMillis());
        datePickerDialog.show();
    }

    private String[] getSurgeonList() {
        return new String[]{
                "Хирург Янчев, 10 лет опыта",
                "Хирург Яковлев, 8 лет опыта",
                "Хирург Иванов, 7 лет опыта",
                "Хирург Щелкунов, 6 лет опыта",
                "Хирург Наумов, 5 лет опыта"
        };
    }
}