package com.project.libertyhacks.mutual.liberty.care.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.project.libertyhacks.mutual.liberty.care.R;

public class DriverLicenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_license);


        Button takePicBtn = findViewById(R.id.takePicBtn);
        takePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverLicenseActivity.this, TakeLicensePictureAcitivity.class);
                startActivity(intent);
            }
        });

        Button inputLicenseInfoBtn = findViewById(R.id.inputLicenseInfoBtn);
        inputLicenseInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverLicenseActivity.this, InputLicenseInfoActivity.class);
                startActivity(intent);
            }
        });

        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DriverLicenseActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
