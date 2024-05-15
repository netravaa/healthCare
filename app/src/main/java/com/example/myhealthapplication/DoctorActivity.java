package com.example.myhealthapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    ListView doctorListView;
    Button buttonBookAppointment;
    String doctorType;

    private static final List<String> therapistList = new ArrayList<>();
    private static final List<String> generalPractitionerList = new ArrayList<>();
    private static final List<String> endocrinologistList = new ArrayList<>();
    private static final List<String> ophthalmologistList = new ArrayList<>();
    private static final List<String> surgeonList = new ArrayList<>();

    static {
        // Заполните списки врачей
        therapistList.add("Иванов, 10 лет опыта");
        therapistList.add("Петров, 8 лет опыта");
        therapistList.add("Сидоров, 7 лет опыта");
        therapistList.add("Васильев, 6 лет опыта");
        therapistList.add("Смирнова, 5 лет опыта");

        // Добавьте аналогичные строки для других типов врачей
        generalPractitionerList.addAll(therapistList);
        endocrinologistList.addAll(therapistList);
        ophthalmologistList.addAll(therapistList);

        surgeonList.add("Янчев, 10 лет опыта");
        surgeonList.add("Яковлев, 8 лет опыта");
        surgeonList.add("Иванов, 7 лет опыта");
        surgeonList.add("Щелкунов, 6 лет опыта");
        surgeonList.add("Наумов, 5 лет опыта");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        doctorListView = findViewById(R.id.doctorListView);
        buttonBookAppointment = findViewById(R.id.buttonBookAppointment);

        doctorType = getIntent().getStringExtra("doctorType");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getDoctorList(doctorType));
        doctorListView.setAdapter(adapter);

        doctorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedDoctor = getDoctorList(doctorType)[position];
                Toast.makeText(DoctorActivity.this, "Выбран врач: " + selectedDoctor, Toast.LENGTH_SHORT).show();
            }
        });

        buttonBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorActivity.this, SaveAppointmentActivity.class);
                intent.putExtra("doctorName", doctorType + " " + getDoctorList(doctorType)[0]);
                startActivity(intent);
            }
        });
    }

    private String[] getDoctorList(String doctorType) {
        switch (doctorType) {
            case "Терапевт":
                return therapistList.toArray(new String[0]);
            case "Участковый врач":
                return generalPractitionerList.toArray(new String[0]);
            case "Эндокринолог":
                return endocrinologistList.toArray(new String[0]);
            case "Офтальмолог":
                return ophthalmologistList.toArray(new String[0]);
            case "Хирург":
                return surgeonList.toArray(new String[0]);
            default:
                return new String[0];
        }
    }
}