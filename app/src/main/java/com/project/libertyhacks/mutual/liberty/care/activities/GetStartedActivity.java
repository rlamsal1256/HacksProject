package com.project.libertyhacks.mutual.liberty.care.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.utilities.FirebaseAccess;

public class GetStartedActivity extends AppCompatActivity {

    private static final String TAG = "AnonymousAuth";

    private FirebaseAuth mAuth;
    private FirebaseAccess firebaseAccess = new FirebaseAccess();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAccess.setGetStartedActivity(this);
        firebaseAccess.onCreate();

        mAuth = FirebaseAuth.getInstance();
        signInAnonymously();
    }

    private void updateUI(FirebaseUser user)
    {
        firebaseAccess.isExistingUser(user);
    }

    public void existingUser()
    {
        Intent intent = new Intent(GetStartedActivity.this, TakeLicensePictureAcitivity.class);
        startActivity(intent);
    }

    public void newUser()
    {
        showUI();
    }

    private void showUI()
    {
        setContentView(R.layout.activity_get_started);
        Button getStartedBtn = (Button) findViewById(R.id.getStartedBtn);
        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStartedActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

    }

    private void signInAnonymously() {
        // [START signin_anonymously]
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            Log.d(TAG, mAuth.getCurrentUser().getUid());
                            FirebaseUser user = mAuth.getCurrentUser();
                            firebaseAccess.getCurrentUser(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(GetStartedActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
//                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END signin_anonymously]
    }

    private void signOut() {
        mAuth.signOut();
//        updateUI(null);
    }
}
