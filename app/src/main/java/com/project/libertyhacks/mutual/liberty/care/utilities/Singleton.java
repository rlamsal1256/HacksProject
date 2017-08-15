package com.project.libertyhacks.mutual.liberty.care.utilities;

import com.google.firebase.auth.FirebaseUser;
import com.project.libertyhacks.mutual.liberty.care.models.User;

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

    public void setCurrentUser(User user) { this.currentUser = user; }
    public User getCurrentUser() { return  this.currentUser; }
    private User currentUser;
    private FirebaseUser firebaseUser;

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser = firebaseUser;
    }
}
