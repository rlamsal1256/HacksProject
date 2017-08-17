package com.project.libertyhacks.mutual.liberty.care.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.project.libertyhacks.mutual.liberty.care.R;

import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class StepCounterAndDetectActivityService extends IntentService implements SensorEventListener {

    private static final String MY_PREFS_NAME = "StepCounter";
    private int stepsUntilNow = 0;
    int stepCounter;
    int totalCount;
    boolean countValueChanged = false;

    public StepCounterAndDetectActivityService() {
        super("StepCounterAndDetectActivityService");
    }

    public StepCounterAndDetectActivityService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)) {

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String steps = prefs.getString("steps", "0");
            extractStepsUntilNow(steps);

            requestSensor();

            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities( result.getProbableActivities() );
        }
    }

    private void extractStepsUntilNow(String steps) {
        String[] parts = steps.split(",");
        stepsUntilNow =  Integer.parseInt(parts[1]);
    }


    private void requestSensor() {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        for( DetectedActivity activity : probableActivities ) {
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.d( "ActivityRecogition", "Still: " + activity.getConfidence() );

                    if (activity.getConfidence() >=100 && countValueChanged){
                        countValueChanged = false;

                        // Calculate steps taken based on first counter value received.
                        stepCounter = totalCount - stepsUntilNow;
                        stepsUntilNow = totalCount;
                        saveStepsInSharedPref();
                        //TODO: update stepCounter and totalCount to firebase

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
                        builder.setContentText("We detected " + stepCounter + " steps. Total steps is now " + totalCount);
                        builder.setSmallIcon( R.mipmap.ic_launcher );
                        builder.setContentTitle( getString( R.string.app_name ) );
                        NotificationManagerCompat.from(this).notify(0, builder.build());
                    }
                    break;
                }
                case DetectedActivity.WALKING: {
                    Log.d( "ActivityRecogition", "Walking: " + activity.getConfidence() );
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    break;
                }
            }
        }
    }

    private void saveStepsInSharedPref() {
        SharedPreferences.Editor editor =
                getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("steps", String.valueOf(stepCounter) + "," + String.valueOf(stepsUntilNow) + "," + String.valueOf(totalCount));
        editor.apply();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        totalCount = (int) sensorEvent.values[0];


        if (stepsUntilNow != totalCount) {
            //don't send a notification if the user hasn't taken new steps
            countValueChanged = true;
        }

        Log.d( "onSensorChanged", "Step: " + stepsUntilNow );
        Log.d( "onSensorChanged", "Total: " + totalCount );


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}