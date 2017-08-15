package com.project.libertyhacks.mutual.liberty.care.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.project.libertyhacks.mutual.liberty.care.R;

import java.util.Calendar;

public class EnterCarInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_car_info);

        ImageButton nextScreenBtn = findViewById(R.id.nextScreenBtn);
        nextScreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Will change null to a valid screen
                Intent intent = new Intent(EnterCarInfoActivity.this, null);
                startActivity(intent);
            }
        });

        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterCarInfoActivity.this, AddCarsActivity.class);
                startActivity(intent);
            }
        });

        final EditText lastRegistrationDate = findViewById(R.id.lastRegistrationTxt);
        final EditText lastOilChangeDate = findViewById(R.id.lastOilChangeTxt);

        ImageButton setLastRegistrationBtn = findViewById(R.id.setLastRegistrationImgBtn);
        setLastRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EnterCarInfoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                lastRegistrationDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        ImageButton setLastOilChangeBtn = findViewById(R.id.setLastOilChangeImgBtn);
        setLastOilChangeBtn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EnterCarInfoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                lastOilChangeDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
}
