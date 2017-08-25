package com.project.libertyhacks.mutual.liberty.care.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.project.libertyhacks.mutual.liberty.care.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button nonLibertyBtn = (Button) findViewById(R.id.takePicBtn);
        nonLibertyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, DriverLicenseActivity.class);
                startActivity(intent);
            }
        });

        Button libertyBtn = (Button) findViewById(R.id.libertyCustomersBtn);
        libertyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, TakeLicensePictureAcitivity.class);
                startActivity(intent);
            }
        });

    }
}
