package com.project.libertyhacks.mutual.liberty.care.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.DateTemplate;
import com.project.libertyhacks.mutual.liberty.care.models.User;
import com.project.libertyhacks.mutual.liberty.care.utilities.FirebaseAccess;
import com.project.libertyhacks.mutual.liberty.care.utilities.Singleton;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class InputLicenseInfoActivity extends AppCompatActivity implements
        View.OnClickListener {

    private ImageButton setUserDobBtn;
    private ImageButton setUserLicenseExpDateBtn;
    private EditText userName;
    private EditText userLicenseNum;
    private EditText userLicenseExpDate;
    private DateTemplate userLicenseExpDateTemplate;
    private EditText userDob;
    private DateTemplate userDobDateTemplate;
    private boolean isUserMale;
    private FirebaseAccess firebaseAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_license_info);
        firebaseAccess = new FirebaseAccess();
        firebaseAccess.setInputLicenseInfoActivity(this);

        userName = findViewById(R.id.userNameTxt);
        isUserMale = true;
        userDob = findViewById(R.id.dobTxt);
        setUserDobBtn = findViewById(R.id.setUserDobImgBtn);
        setUserLicenseExpDateBtn = findViewById(R.id.setUserLicenseExpDateImgBtn);
        userLicenseNum = findViewById(R.id.licenseNumTxt);
        userLicenseExpDate = findViewById(R.id.expDateTxt);
        ImageButton nextScreenBtn = findViewById(R.id.nextScreenDriverLicenseInfoBtn);


        setUserDobBtn.setOnClickListener(this);
        setUserLicenseExpDateBtn.setOnClickListener(this);
        nextScreenBtn.setOnClickListener(v -> {

            Log.d("lol", "*******InputLicenseInfoActivity");
            int userAge = 30;

            String licNum = userLicenseNum.getText().toString();

            String userUID = Singleton.getInstance().getFirebaseUser().getUid();
            Map<String, Object> cars = new HashMap<>();


            if (userName.getText() != null && userDobDateTemplate != null && userLicenseExpDateTemplate != null) {
                User newUser = new User(userUID, userName.getText().toString(),
                        userAge,
                        String.valueOf(userSex()),
                        userDobDateTemplate,
                        licNum,
                        userLicenseExpDateTemplate,
                        cars);
                firebaseAccess.makeNewUser(newUser);
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.userMaleRadioBtn:
                if (checked)
                    isUserMale = true;
                break;
            case R.id.userFemaleRadioBtn:
                if (checked)
                    isUserMale = false;
                break;
        }
    }


    private char userSex() {
        if (isUserMale)
            return 'M';
        else
            return 'F';
    }

    @Override
    public void onClick(View view) {
        int mMonth;
        int mDay;
        int mYear;
        if (view == setUserDobBtn) {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view1, year, monthOfYear, dayOfMonth) -> {

                        userDob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        userDobDateTemplate = new DateTemplate(monthOfYear + 1, dayOfMonth, year);

                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        } else if (view == setUserLicenseExpDateBtn) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view12, year, monthOfYear, dayOfMonth) -> {

                        userLicenseExpDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        userLicenseExpDateTemplate = new DateTemplate(monthOfYear + 1, dayOfMonth, year);

                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }

    public void nextScreen() {
        Intent intent = new Intent(InputLicenseInfoActivity.this, UserProfileActivity.class);
        startActivity(intent);
    }

}
