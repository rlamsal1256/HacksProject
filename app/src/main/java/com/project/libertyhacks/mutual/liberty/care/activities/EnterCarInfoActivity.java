package com.project.libertyhacks.mutual.liberty.care.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.Car;
import com.project.libertyhacks.mutual.liberty.care.utilities.FirebaseAccess;
import com.project.libertyhacks.mutual.liberty.care.utilities.Singleton;
import com.project.libertyhacks.mutual.liberty.care.utilities.VINAnalyzer;

import java.util.Calendar;
import java.util.Random;

public class EnterCarInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_car_info);

        ImageButton nextScreenBtn = findViewById(R.id.next);
        nextScreenBtn.setOnClickListener(v -> {
            // Get input VIN
            EditText vinTxt = findViewById(R.id.vinTxt);
            String vin = vinTxt.getText().toString();
            if (vin.isEmpty()) vin = Integer.toString(new Random().nextInt());

            EditText carsNameEditText = findViewById(R.id.carsNameEditText);
            String carsName = carsNameEditText.getText().toString();
            if (carsName.isEmpty()) carsName = "Dad's car";

            // Get input current mileage
            EditText currentMileageTxt = findViewById(R.id.currentMileageTxt);
            String milesStr = currentMileageTxt.getText().toString();
            int miles = 0;
            if (!milesStr.isEmpty()) miles = Integer.parseInt(milesStr);

            // Retrieve car from VINAnalyzer
            Car car = VINAnalyzer.getCar(vin);

            // Set miles, ownerId, name
            car.setMiles(miles);
            car.setOwnerId(Singleton.getInstance().getFirebaseUser().getUid());
            car.setName(carsName); // Will replace this with user input later
            Log.d("CAR NAME", "*********** CAR NAME:" + carsName);

            this.addCar(car);

            Intent intent = new Intent(EnterCarInfoActivity.this, YourCarsActivity.class);
            startActivity(intent);
        });

        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(EnterCarInfoActivity.this, YourCarsActivity.class);
            startActivity(intent);
        });

        final EditText lastRegistrationDate = findViewById(R.id.lastRegistrationTxt);
//        final EditText lastOilChangeDate = findViewById(R.id.lastOilChangeTxt);

        ImageButton setLastRegistrationBtn = findViewById(R.id.setLastRegistrationImgBtn);
        setLastRegistrationBtn.setOnClickListener(v -> {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(EnterCarInfoActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> lastRegistrationDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

//        ImageButton setLastOilChangeBtn = findViewById(R.id.setLastOilChangeImgBtn);
//        setLastOilChangeBtn.setOnClickListener(v -> {
//            // Get Current Date
//            final Calendar c = Calendar.getInstance();
//            int mYear = c.get(Calendar.YEAR);
//            int mMonth = c.get(Calendar.MONTH);
//            int mDay = c.get(Calendar.DAY_OF_MONTH);
//
//            DatePickerDialog datePickerDialog = new DatePickerDialog(EnterCarInfoActivity.this,
//                    (view, year, monthOfYear, dayOfMonth) -> lastOilChangeDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
//            datePickerDialog.show();
//        });
    }

    void addCar(Car car)
    {
        // Log car in database
        Singleton.getInstance().addCar(car);
        FirebaseAccess firebaseAccess = new FirebaseAccess();
//        Map<String, Object> carMap = new HashMap<>();
//        carMap.put(car.getKey(), true);
        firebaseAccess.post("cars", car);
        firebaseAccess.onAddedCar(car);
    }
}
