package com.example.mytutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Subjects extends AppCompatActivity {

    Button btnMath, btnCompSci, btnToHomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjects_linear);

        btnMath = findViewById(R.id.btnMath);
        btnCompSci = findViewById(R.id.btnCompSci);
        btnToHomePage = findViewById(R.id.btnBack);

        btnToHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHomePage = new Intent(Subjects.this, MainActivity.class);
                startActivity(toHomePage);
            }
        });

        btnMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMathAppointments = new Intent(Subjects.this, MathAppointments.class);
                startActivity(toMathAppointments);
            }
        });
        btnCompSci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCSAppointments = new Intent(Subjects.this, CSAppointments.class);
                startActivity(toCSAppointments);
            }
        });
    }
}