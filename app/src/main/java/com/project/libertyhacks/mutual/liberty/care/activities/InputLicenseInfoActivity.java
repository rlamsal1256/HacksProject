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
    private Calendar calendar;
    private int year, month, day;
    private int mYear, mMonth, mDay;
    private EditText userDob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_license_info);

//        calendar = Calendar.getInstance();
//        year = calendar.get(Calendar.YEAR);
//
//        month = calendar.get(Calendar.MONTH);
//        day = calendar.get(Calendar.DAY_OF_MONTH);
//        showDate(year, month+1, day);

        EditText userName = findViewById(R.id.userNameTxt);
        EditText userAge = findViewById(R.id.userAgeTxt);
        RadioButton userIsMale = findViewById(R.id.userMaleRadioBtn);
        RadioButton userIsFemale =  findViewById(R.id.userFemaleRadioBtn);
        userDob = findViewById(R.id.dobTxt);
        setUserDobBtn = findViewById(R.id.setUserDobImgBtn);
        setUserDobBtn.setOnClickListener(this);
        EditText userLicenseNum = findViewById(R.id.licenseNumTxt);
        EditText userLicenseExpDate = findViewById(R.id.expDateTxt);

        //TODO: use date picker
        User newUser = new User(userName.getText().toString(), Integer.parseInt(userAge.getText().toString()),
                userGender(userIsMale, userIsFemale), null, userLicenseNum.getText().toString(), null);

        Log.d("InputLicenseInfo", newUser.toString());

    }

    private void showDate(int year, int i, int day) {
        userDob.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private char userGender(RadioButton userIsMale, RadioButton userIsFemale) {
        //TODO: check both radio buttons
        return 'F';
    }

//    @Override
//    protected Dialog onCreateDialog(int id) {
//        // TODO Auto-generated method stub
//        if (id == 999) {
//            return new DatePickerDialog(this,
//                    myDateListener, year, month, day);
//        }
//        return null;
//    }
//
//    private DatePickerDialog.OnDateSetListener myDateListener = new
//            DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker arg0,
//                                      int arg1, int arg2, int arg3) {
//                    // TODO Auto-generated method stub
//                     arg1 = year;
//                     arg2 = month;
//                     arg3 = day;
//                    showDate(arg1, arg2+1, arg3);
//                }
//            };

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

                            userDob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
