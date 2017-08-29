package com.project.libertyhacks.mutual.liberty.care.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.Car;
import com.project.libertyhacks.mutual.liberty.care.utilities.FirebaseAccess;
import com.project.libertyhacks.mutual.liberty.care.utilities.Singleton;

public class RemoveCarActivity extends AppCompatActivity {
    private String carName;
    private String vin;
    private Button removeCarButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_car);

        Bundle b = getIntent().getExtras();
        carName = b.getString("carName");
        Log.d("REMOVE CAR:", carName);

        for (Car c : Singleton.getInstance().getCars())
        {
            if (c.getName().equals(this.carName))
            {
                this.vin = c.getVin();
                Log.d("FOUND CAR", vin);
            }
        }

        removeCarButton = findViewById(R.id.removeCarButton);
        removeCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAccess firebaseAccess = new FirebaseAccess();
                firebaseAccess.removeCar(vin);
                Intent intent = new Intent(RemoveCarActivity.this, YourCarsActivity.class);
                startActivity(intent);
            }
        });
    }
}
