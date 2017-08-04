package com.project.libertyhacks.mutual.liberty.care.activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.User;

import java.util.Calendar;

public class InputLicenseInfoActivity extends AppCompatActivity implements
        View.OnClickListener{

    private ImageButton setUserDobBtn;
    private ImageButton setUserLicenseExpDateBtn;
    private ImageButton nextScreenBtn;
    private EditText userName;
    private EditText userAge;
    private EditText userLicenseNum;
    private RadioButton userIsMale;
    private RadioButton userIsFemale;
    private EditText userLicenseExpDate;
    private EditText userDob;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_license_info);
        userName = findViewById(R.id.userNameTxt);
        userAge = findViewById(R.id.userAgeTxt);
        userIsMale = findViewById(R.id.userMaleRadioBtn);
        userIsFemale =  findViewById(R.id.userFemaleRadioBtn);
        userDob = findViewById(R.id.dobTxt);
        setUserDobBtn = findViewById(R.id.setUserDobImgBtn);
        setUserLicenseExpDateBtn = findViewById(R.id.setUserLicenseExpDateImgBtn);
        userLicenseNum = findViewById(R.id.licenseNumTxt);
        userLicenseExpDate = findViewById(R.id.expDateTxt);
        nextScreenBtn = findViewById(R.id.nextScreenBtn);


        setUserDobBtn.setOnClickListener(this);
        setUserLicenseExpDateBtn.setOnClickListener(this);
        nextScreenBtn.setOnClickListener(this);

    }



    private char userGender(RadioButton userIsMale, RadioButton userIsFemale) {
        //TODO: check both radio buttons
        return 'F';
    }

    @Override
    public void onClick(View view) {
        if (view == setUserDobBtn) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            userDob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        } else if (view == setUserLicenseExpDateBtn){

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            userLicenseExpDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        } else if (view == nextScreenBtn) {

            User newUser = new User(userName.getText().toString(), Integer.parseInt(userAge.getText().toString()),
                    userGender(userIsMale, userIsFemale), null, userLicenseNum.getText().toString(), null);

            Log.d("InputLicenseInfo", newUser.toString());

            //TODO: fire intent to next screen
        }
    }
}
