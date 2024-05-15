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

public class OphtalmologistActivity extends AppCompatActivity {

    ListView OphtalmologistListView;
    Button buttonBookAppointment;
    Calendar appointmentDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ophtalmologist);

        OphtalmologistListView = findViewById(R.id.OphtalmologistListView);
        buttonBookAppointment = findViewById(R.id.buttonBookAppointment);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getOphtalmologistList());
        OphtalmologistListView.setAdapter(adapter);

        OphtalmologistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedOphtalmologist = getOphtalmologistList()[position];
                Toast.makeText(OphtalmologistActivity.this, "Выбран врач: " + selectedOphtalmologist, Toast.LENGTH_SHORT).show();
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

                        TimePickerDialog timePickerDialog = new TimePickerDialog(OphtalmologistActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        appointmentDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        appointmentDateTime.set(Calendar.MINUTE, minute);

                                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
                                        Toast.makeText(OphtalmologistActivity.this,
                                                "Выбранная дата и время: " + sdf.format(appointmentDateTime.getTime()),
                                                Toast.LENGTH_LONG).show();

                                        SharedPreferences sharedPreferences = getSharedPreferences("MyAppointments", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("appointmentInfo", "Офтальмолог " + sdf.format(appointmentDateTime.getTime()));
                                        editor.apply();

                                        Intent intent = new Intent(OphtalmologistActivity.this, HomeActivity.class);
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

    private String[] getOphtalmologistList() {
        return new String[]{
                "Офтальмолог Иванов, 10 лет опыта",
                "Офтальмолог Петров, 8 лет опыта",
                "Офтальмолог Сидоров, 7 лет опыта",
                "Офтальмолог Васильев, 6 лет опыта",
                "Офтальмолог Смирнова, 5 лет опыта"
        };
    }
}
