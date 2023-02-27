package com.example.mytutor;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MathAppointments extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker, btnConfirm, btnBackToSub;
    EditText txtDate, txtTime;
    TextView txtErrorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_appointments);

        btnDatePicker= findViewById(R.id.btn_date);
        btnTimePicker = findViewById(R.id.btn_time);
        txtDate = findViewById(R.id.in_date);
        txtTime = findViewById(R.id.in_time);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBackToSub = findViewById(R.id.btnBackToSub2);
        txtErrorMsg = findViewById(R.id.txtMerror);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences appTime = getSharedPreferences("Appointment", MODE_PRIVATE);
                int hour = appTime.getInt("hour", 0);
                int minute = appTime.getInt("minute", 0);

                if (15 <= hour && hour <= 20 && minute == 0){
                    Intent toConfirm = new Intent(MathAppointments.this, Confirmation.class);
                    startActivity(toConfirm);
                    txtErrorMsg.setVisibility(View.INVISIBLE);
                } else {
                    txtTime.setText("");
                    txtErrorMsg.setVisibility(View.VISIBLE);
                }
            }
        });

        btnBackToSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(MathAppointments.this, Subjects.class);
                startActivity(back);
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @SuppressLint({"SetTextI18n", "ApplySharedPref"})
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            SharedPreferences appTime = getSharedPreferences("Appointment", MODE_PRIVATE);
                            SharedPreferences.Editor editor = appTime.edit();
                            editor.putString("Appointment_Date", dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            editor.commit();

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @SuppressLint({"SetTextI18n", "ApplySharedPref"})
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            if (minute < 10) {
                                txtTime.setText(hourOfDay + ":0" + minute);
                            } else {
                                txtTime.setText(hourOfDay + ":" + minute);
                            }

                            SharedPreferences appTime = getSharedPreferences("Appointment", MODE_PRIVATE);
                            SharedPreferences.Editor editor = appTime.edit();
                            if (minute < 10){
                                editor.putString("Appointment_Time", hourOfDay + ":0" + minute);
                            } else {
                                editor.putString("Appointment_Time", hourOfDay + ":" + minute);
                            }
                            editor.putInt("minute", minute);
                            editor.putInt("hour", hourOfDay);
                            editor.commit();

                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}