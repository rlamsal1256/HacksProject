package com.project.libertyhacks.mutual.liberty.care.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.User;
import com.project.libertyhacks.mutual.liberty.care.utilities.Singleton;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        User currentUser = Singleton.getInstance().getCurrentUser();
        Log.d("USER INFO", currentUser.toString());
        Log.d("USER NAME", currentUser.getUserName());

        TextView name = findViewById(R.id.nametextView);
        name.setText(currentUser.getUserName());

        //TextView age = findViewById(R.id.textView13);
        //age.setText(currentUser.getUserAge());

        TextView gender = findViewById(R.id.textView14);
        gender.setText(currentUser.getUserGender());

        TextView dob = findViewById(R.id.textView15);
        dob.setText(currentUser.getUserDOB().toString());

        TextView licNum = findViewById(R.id.textView7);
        licNum.setText(currentUser.getUserLicenseNum());

        TextView expDate = findViewById(R.id.textView9);
        expDate.setText(currentUser.getUserLicenseExpDate().toString());

        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, InputLicenseInfoActivity.class);
                startActivity(intent);
            }
        });

        Button continueBtn = findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, AddCarsActivity.class);
                startActivity(intent);
            }
        });
    }
}
