package com.project.libertyhacks.mutual.liberty.care.utilities;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by andrewcunningham on 8/17/17.
 */

public class FirebaseToken {
    private static final String TAG = "Firebase Token";
    private static final FirebaseToken ourInstance = new FirebaseToken();
    private static String token;

    public String getToken() {
        return token;
    }

    public static FirebaseToken getInstance() {
        return ourInstance;
    }

    private FirebaseToken() {
        this.token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "********************************************************************");
        Log.d(TAG, token);
        Log.d(TAG, "********************************************************************");
    }


}
