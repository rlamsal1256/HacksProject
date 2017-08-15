package com.project.libertyhacks.mutual.liberty.care.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.DateTemplate;
import com.project.libertyhacks.mutual.liberty.care.models.User;
import com.project.libertyhacks.mutual.liberty.care.utilities.FirebaseAccess;
import com.project.libertyhacks.mutual.liberty.care.utilities.Singleton;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button nonLibertyBtn = (Button) findViewById(R.id.nonLibertyBtn);
        nonLibertyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, DriverLicenseActivity.class);
                startActivity(intent);
            }
        });

        Button libertyBtn = (Button) findViewById(R.id.libertyCustomersBtn);
        libertyBtn.setOnClickListener((v) -> {
            {
                String licNum = "1263748";
                //Log.d("USER UID", Singleton.getInstance().userUID);
                User newUser = new User( Singleton.getInstance().getFirebaseUser().getUid(), "joe", 45, "m", new DateTemplate(1, 1, 1970), licNum, new DateTemplate(1, 1, 2018));
                FirebaseAccess fa = new FirebaseAccess();
                fa.post("/users/", newUser);
            }
        });
    }
}
