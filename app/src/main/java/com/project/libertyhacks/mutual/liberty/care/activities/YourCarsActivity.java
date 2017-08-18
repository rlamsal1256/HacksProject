package com.project.libertyhacks.mutual.liberty.care.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.Car;
import com.project.libertyhacks.mutual.liberty.care.services.StepCounterAndDetectActivityService;
import com.project.libertyhacks.mutual.liberty.care.utilities.Singleton;

import java.util.List;

public class YourCarsActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String MY_PREFS_NAME = "StepCounter";
    public GoogleApiClient mApiClient;
    public int lastStepsAmt;
    public int totalSteps;
    private String steps = "";
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    // ImageButton to add a car
    ImageButton addCarBtn;
    List<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cars);

        // Create action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Your Cars");
        }

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        steps = prefs.getString("steps", "0");
        updateUI();

        listener = (sharedPreferences, s) -> updateUI();

        prefs.registerOnSharedPreferenceChangeListener(listener);

        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mApiClient.connect();

        cars = Singleton.getInstance().getCars();
        // cars = new ArrayList<>();

        // Get associated layout
        RelativeLayout layout = findViewById(R.id.add_cars_layout);

        // TextView for when user has no cars added
        TextView noCarsTextView = findViewById(R.id.noCarsText);

        // ImageButton to add a car
        addCarBtn = findViewById(R.id.addCarBtn);

        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YourCarsActivity.this, EnterCarInfoActivity.class);
                startActivity(intent);
            }
        });

        if (cars.isEmpty()) {
            noCarsTextView.setVisibility(View.VISIBLE);
        } else {

            moveAddCarBtnToBottom();

            noCarsTextView.setVisibility(View.INVISIBLE);
        }
    }

    private void updateUI() {
        Log.d("StepsfromSP***", steps);
        extractSteps(steps);

        String lastDistance = lastStepsAmt + " steps";
        String totalStepsStr = totalSteps + " steps";
        Log.d("YourCarsActivity**", "last step: " + lastDistance);
        Log.d("YourCarsActivity**", "total step: " + totalStepsStr);
//        ((TextView) findViewById(R.id.lastDistanceTxtView)).setText(lastDistance);
//        ((TextView) findViewById(R.id.totalDistanceTextView)).setText(totalStepsStr);
    }

    private void extractSteps(String steps) {
        String[] parts = steps.split(",");
        if (!steps.equals("0")) {
            lastStepsAmt = Integer.parseInt(parts[0]);
            totalSteps = Integer.parseInt(parts[2]);
        }
    }


    // Moves the addCarBtn to the bottom of the screen
    private void moveAddCarBtnToBottom() {
        RelativeLayout.LayoutParams addCarBtnLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        addCarBtnLayoutParams.width = dpInPx(60);
        addCarBtnLayoutParams.height = dpInPx(60);
        addCarBtnLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        addCarBtnLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        addCarBtnLayoutParams.bottomMargin = 23;
        addCarBtn.setLayoutParams(addCarBtnLayoutParams);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_notification:
                Intent intent = new Intent(YourCarsActivity.this, NotificationsActivity.class);
                startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    // Converts dp to px
    private int dpInPx(int dp) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
//        if (!cars.isEmpty()) {
        Intent intent = new Intent(this, StepCounterAndDetectActivityService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(mApiClient, 1000, pendingIntent);
//        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
