package com.project.libertyhacks.mutual.liberty.care.utilities;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.project.libertyhacks.mutual.liberty.care.activities.GetStartedActivity;
import com.project.libertyhacks.mutual.liberty.care.activities.InputLicenseInfoActivity;
import com.project.libertyhacks.mutual.liberty.care.activities.TakeLicensePictureAcitivity;
import com.project.libertyhacks.mutual.liberty.care.interfaces.Mapable;
import com.project.libertyhacks.mutual.liberty.care.models.Car;
import com.project.libertyhacks.mutual.liberty.care.models.User;


import java.util.Map;


/**
 * Created by andrewcunningham on 8/14/17.
 */

public class FirebaseAccess {

    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private GetStartedActivity getStartedActivity;
    private InputLicenseInfoActivity inputLicenseInfoActivity;
    private MyFirebaseInstanceIdService firebaseInstanceIdService = new MyFirebaseInstanceIdService();

    public FirebaseAccess()
    {

    }

    public boolean post(String url, Mapable m)
    {
        DatabaseReference mDatabase = database.getReference(url);
        Log.d("Database Reference", mDatabase.toString());

        Map<String, Object> map = m.toMap();
        Log.d("USER UID", m.getKey());
        DatabaseReference key = mDatabase.child(m.getKey());
        key.setValue(map);

        Log.d("InputInfo", key + ": " + map.toString());
        return true;
    }



    public void setGetStartedActivity(GetStartedActivity gsa)
    {
        this.getStartedActivity = gsa;
    }

    public void setInputLicenseInfoActivity(InputLicenseInfoActivity ilia) { this.inputLicenseInfoActivity = ilia; }

    public void getCarData(String vin)
    {
        DatabaseReference mDatabase = database.getReference("cars/" + vin);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Car newCar = dataSnapshot.getValue(Car.class);
                if (newCar != null)
                {
                    Log.d("CAR DATA", newCar.toString());
                    if (!Singleton.getInstance().carExists(newCar.getVin()))
                    {
                        Singleton.getInstance().addCar(newCar);
                    }
                    else
                    {
                        Singleton.getInstance().updateCar(newCar);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void updateCarMiles(String vin, int lastCount, int totalCount)
    {
        DatabaseReference mDatabase = database.getReference("/cars/" + vin);
        mDatabase.child("lastMiles").setValue(lastCount);
        mDatabase.child("totalMiles").setValue(totalCount);
    }


    public void getCurrentUser(FirebaseUser fbUser)
    {
        DatabaseReference mDatabase = database.getReference("users/"+fbUser.getUid());
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null)
                {
                    user.setUserKey(fbUser.getUid());
                    setCurrentUser(user);
                    getCars(user);
                    Log.d("USER SET", "CURRENT USER IS SET");
                    firebaseInstanceIdService.onTokenRefresh();
                }
                else
                {
                    Log.d("USER NOT FOUND", "No user associated with url");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setCurrentUser(User user)
    {
        Singleton.getInstance().setCurrentUser(user);
    }

    private void getCars(User user)
    {
        if (user.getCars() != null)
        {
            for (String key : user.getCars().keySet())
            {
                getCarData(key);
            }
        }
    }



/*
    public Task<AuthResult> signIn()
    {
        Log.d("SIGNING IN", "SIGNING IN");
        return mAuth.signInAnonymously();
    }
*/
    public void makeNewUser(User user)
    {
        if (post("/users/", user))
        {
            inputLicenseInfoActivity.nextScreen();
        }
    }

    public void isExistingUser(FirebaseUser user)
    {
        Singleton.getInstance().setFirebaseUser(user);
        DatabaseReference mDatabase = database.getReference("users/" + user.getUid());
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null)
                {
                    getStartedActivity.existingUser();
                    Log.d("USER", "EXISTING USER");

                }
                else
                {
                    getStartedActivity.newUser();
                    Log.d("USER", "NEW USER");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                getStartedActivity.newUser();
            }
        });
    }

    public void doesCarExist(String vin)
    {
        DatabaseReference mDatabase = database.getReference("/cars/" + vin);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Car car = dataSnapshot.getValue(Car.class);
                if (car != null)
                {
                    // does exist
                }
                else
                {
                    // does not exist
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // release listener in onStop
    public void onStop() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void onStart()
    {
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onCreate()
    {
        // active listen to user logged in or not.
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("SIGNED IN", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("SIGNED OUT", "onAuthStateChanged:signed_out");
                }
            }
        };
    }
}
