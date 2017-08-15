package com.project.libertyhacks.mutual.liberty.care.utilities;

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
}
