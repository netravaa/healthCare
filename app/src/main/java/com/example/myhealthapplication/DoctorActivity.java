package com.example.myhealthapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorActivity extends AppCompatActivity {

    ListView doctorListView;
    Button buttonBookAppointment;
    String  doctorType;

    private static final List<String> therapistList = new ArrayList<>();
    private static final List<String> generalPractitionerList = new ArrayList<>();
    private static final List<String> endocrinologistList = new ArrayList<>();
    private static final List<String> ophthalmologistList = new ArrayList<>();
    private static final List<String> surgeonList = new ArrayList<>();

    private static final String DOCTOR_NAME_KEY = "doctorName";


    static {
        initializeDoctorList(therapistList, "Иванов, 10 лет опыта", "Петров, 8 лет опыта", "Сидоров, 7 лет опыта", "Васильев, 6 лет опыта", "Смирнова, 5 лет опыта");
        initializeDoctorList(generalPractitionerList, therapistList);
        initializeDoctorList(endocrinologistList, therapistList);
        initializeDoctorList(ophthalmologistList, therapistList);
        initializeDoctorList(surgeonList, "Янчев, 10 лет опыта", "Яковлев, 8 лет опыта", "Иванов, 7 лет опыта", "Щелкунов, 6 лет опыта", "Наумов, 5 лет опыта");
    }

    private static void initializeDoctorList(List<String> list, String... doctors) {
        for (String doctor : doctors) {
            list.add(doctor);
        }
    }

    private static void initializeDoctorList(List<String> list, List<String> sourceList) {
        list.addAll(sourceList);
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
                Intent intent = new Intent(DoctorActivity.this, SaveAppointmentActivity.class);
                intent.putExtra(DOCTOR_NAME_KEY, doctorType + " " + selectedDoctor);
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