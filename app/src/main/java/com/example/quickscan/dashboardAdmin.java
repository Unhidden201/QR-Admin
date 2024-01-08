package com.example.quickscan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class dashboardAdmin extends AppCompatActivity {
private Button attendance,logout,qr,mayor,view;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        attendance = findViewById(R.id.attendance);
        qr = findViewById(R.id.qr);
        view = findViewById(R.id.viewact);
        mayor = findViewById(R.id.mayor);
        logout = findViewById(R.id.logout);

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardAdmin.this,qrgenerator.class);
                startActivity(intent);
                finish();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardAdmin.this,viewactivity.class);
                startActivity(intent);
                finish();
            }
        });


        mayor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardAdmin.this,addmayor.class);
                startActivity(intent);
                finish();
            }
        });
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboardAdmin.this,activityattendance.class);
                startActivity(intent);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(),"Account Logout",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}