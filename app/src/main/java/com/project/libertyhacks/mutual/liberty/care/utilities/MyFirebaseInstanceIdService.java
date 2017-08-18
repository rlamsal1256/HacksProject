package com.project.libertyhacks.mutual.liberty.care.utilities;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by andrewcunningham on 8/17/17.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        if (Singleton.getInstance().getFirebaseUser() != null)
        {
            postToken(Singleton.getInstance().getFirebaseUser().getUid(), token);
        }
    }

    private void postToken(String userKey, String token)
    {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("/users/" + userKey);
        mDatabase.child("token").setValue(token);
        Log.d("TOKEN", "********************************************");
        Log.d("POSTED", token);
        Log.d("TOKEN", "********************************************");
    }


}
