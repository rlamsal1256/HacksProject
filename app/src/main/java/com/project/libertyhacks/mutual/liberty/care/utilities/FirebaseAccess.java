package com.project.libertyhacks.mutual.liberty.care.utilities;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.libertyhacks.mutual.liberty.care.interfaces.Mapable;
import com.project.libertyhacks.mutual.liberty.care.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by andrewcunningham on 8/14/17.
 */

public class FirebaseAccess {

    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;

    public boolean post(String url, Mapable m)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference(url);
        Log.d("Database Reference", mDatabase.toString());

        Map<String, Object> map = m.toMap();
        DatabaseReference key = mDatabase.child(UUID.randomUUID().toString());
        key.setValue(map);

        Log.d("InputInfo", key + ": " + map.toString());
        return true;
    }
/*
    public Task<AuthResult> signIn()
    {
        Log.d("SIGNING IN", "SIGNING IN");
        return mAuth.signInAnonymously();
    }
*/
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
                    Log.d("SGINED IN", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("SIGNED OUT", "onAuthStateChanged:signed_out");
                }
            }
        };


    }
}
