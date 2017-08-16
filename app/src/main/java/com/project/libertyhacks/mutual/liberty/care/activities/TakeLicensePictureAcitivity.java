package com.project.libertyhacks.mutual.liberty.care.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.Car;
import com.project.libertyhacks.mutual.liberty.care.models.DateTemplate;
import com.project.libertyhacks.mutual.liberty.care.models.User;
import com.project.libertyhacks.mutual.liberty.care.utilities.FirebaseAccess;
import com.project.libertyhacks.mutual.liberty.care.utilities.Singleton;

import java.util.HashMap;

public class TakeLicensePictureAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_license_picture_acitivity);
        Singleton.getInstance().setTakeLicensePictureAcitivity(this);
        if (Singleton.getInstance().getCurrentUser() == null)
        {
            User newUser = makeUser();
            FirebaseAccess firebaseAccess = new FirebaseAccess();
            firebaseAccess.post("/users/", newUser);
        }
        else
        {
            postUserSuccessful();
        }
    }

    public void postUserSuccessful()
    {
        Intent intent = new Intent(TakeLicensePictureAcitivity.this, UserProfileActivity.class);
        startActivity(intent);
    }


    // Stub Method
    private User makeUser()
    {
        return new User(Singleton.getInstance().getFirebaseUser().getUid(),
                "Ray Tang", 30, "M", new DateTemplate(9, 1, 1986),
                "A45682709", new DateTemplate(8, 3, 2017), new HashMap<String, Object>());
    }


    @Override
    protected void onStart() {
        super.onStart();
    }
}
