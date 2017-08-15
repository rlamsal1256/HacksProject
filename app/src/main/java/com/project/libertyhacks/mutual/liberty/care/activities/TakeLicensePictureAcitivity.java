package com.project.libertyhacks.mutual.liberty.care.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.Car;
import com.project.libertyhacks.mutual.liberty.care.utilities.FirebaseAccess;

public class TakeLicensePictureAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_license_picture_acitivity);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Car car = new Car();
        FirebaseAccess firebaseAccess = new FirebaseAccess();
        firebaseAccess.getCarData("1234");
    }
}
