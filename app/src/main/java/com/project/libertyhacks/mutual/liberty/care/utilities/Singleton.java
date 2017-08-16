package com.project.libertyhacks.mutual.liberty.care.utilities;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.project.libertyhacks.mutual.liberty.care.activities.TakeLicensePictureAcitivity;
import com.project.libertyhacks.mutual.liberty.care.models.Car;
import com.project.libertyhacks.mutual.liberty.care.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by andrewcunningham on 8/15/17.
 */

public class Singleton {
    private static final Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        if (this.takeLicensePictureAcitivity != null)
        {
            this.takeLicensePictureAcitivity.postUserSuccessful();
        }

    }
    public User getCurrentUser() { return  this.currentUser; }
    private User currentUser;
    private ArrayList<Car> cars = new ArrayList<>();

    public boolean carExists(String vin)
    {
        for (Car c : cars)
        {
            if (c.getKey().equals(vin))
            {
                return true;
            }
        }
        return false;
    }

    public void updateCar(Car car)
    {
        for (Car c : cars)
        {
            if (c.getKey().equals(car.getKey()))
            {
                c.setValues(car);
            }
        }
    }

    // Stubs
    private TakeLicensePictureAcitivity takeLicensePictureAcitivity;
    public void setTakeLicensePictureAcitivity(TakeLicensePictureAcitivity
                                                       takeLicensePictureAcitivity)
    {
        this.takeLicensePictureAcitivity = takeLicensePictureAcitivity;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addCar(Car c)
    {
        Log.d("NEW CAR", c.toString());
        cars.add(c);

    }

    private FirebaseUser firebaseUser;

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }
}
