package com.example.mytutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Confirmation extends AppCompatActivity {

    TextView txtTime, txtDate;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        txtTime = findViewById(R.id.txtTime);
        txtDate = findViewById(R.id.txtDate);
        btnBack = findViewById(R.id.btnConfirm);

        //Getting appointment data
        SharedPreferences appTime = getSharedPreferences("Appointment", MODE_PRIVATE);
        String appointmentDate = appTime.getString("Appointment_Date","");
        String appointmentTime = appTime.getString("Appointment_Time","");

        txtTime.setText(appointmentTime);
        txtDate.setText(appointmentDate);
    }
}