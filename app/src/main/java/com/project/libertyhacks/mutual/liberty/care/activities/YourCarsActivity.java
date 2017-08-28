package com.project.libertyhacks.mutual.liberty.care.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.project.libertyhacks.mutual.liberty.care.R;
import com.project.libertyhacks.mutual.liberty.care.models.Car;
import com.project.libertyhacks.mutual.liberty.care.services.StepCounterAndDetectActivityService;
import com.project.libertyhacks.mutual.liberty.care.utilities.Singleton;
import com.project.libertyhacks.mutual.liberty.care.utilities.YourCarsAdapter;

import java.util.ArrayList;

public class YourCarsActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String MY_PREFS_NAME = "StepCounter";
    public GoogleApiClient mApiClient;
    public int lastStepsAmt;
    public int totalSteps;
    private String steps = "";
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    private RelativeLayout addCarLayout;
    private ImageButton addCarBtn;
    private FloatingActionButton addAnotherCarBtn;
    ArrayList<Car> cars;
    String totalStepsStr;
    private YourCarsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cars);

        // Create action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Your Cars");
        }

        cars = Singleton.getInstance().getCars();

        getStepsFromSharedPrefAndUpdateUI();

        createApiClientAndConnect();

//        populateScreenWithCars();


        // ImageButton to add a car
        addCarBtn = findViewById(R.id.addCarBtn);
        addAnotherCarBtn = findViewById(R.id.addAnotherCarBtn);
        addCarLayout = findViewById(R.id.addCarLayout);

        addCarBtn.setOnClickListener(v -> {
            Intent intent = new Intent(YourCarsActivity.this, EnterCarInfoActivity.class);
            startActivity(intent);
        });

        addAnotherCarBtn.setOnClickListener(v -> {
            Intent intent = new Intent(YourCarsActivity.this, EnterCarInfoActivity.class);
            startActivity(intent);
        });

        resolveVisibility();
    }

    private void populateScreenWithCars() {

        extractSteps(steps);

        String lastDistance = lastStepsAmt + " steps";
        totalStepsStr = totalSteps + " steps";


            RecyclerView myRecyclerView = findViewById(R.id.cardView);
            myRecyclerView.setHasFixedSize(true);
            LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
            myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            if (cars.size() > 0 & myRecyclerView != null) {
                adapter = new YourCarsAdapter(cars, totalStepsStr);
                myRecyclerView.setAdapter(adapter);
                myRecyclerView.setLayoutManager(myLayoutManager);
            }
        }

    private void resolveVisibility() {
        if (cars.isEmpty()) {
            addAnotherCarBtn.setVisibility(View.INVISIBLE);
            addCarLayout.setVisibility(View.VISIBLE);
        } else {
            addAnotherCarBtn.setVisibility(View.VISIBLE);
            addCarLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void createApiClientAndConnect() {
        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mApiClient.connect();
    }

    private void getStepsFromSharedPrefAndUpdateUI() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        steps = prefs.getString("steps", "0");
//        updateUI();
        populateScreenWithCars();


//        listener = (sharedPreferences, s) -> updateUI();
        listener = (sharedPreferences, s) -> populateScreenWithCars();
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }

    private void updateUI() {
        Log.d("StepsfromSP***", steps);
        extractSteps(steps);

        String lastDistance = lastStepsAmt + " steps";
        totalStepsStr = totalSteps + " steps";
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_notification:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (!cars.isEmpty()) {
        Intent intent = new Intent(this, StepCounterAndDetectActivityService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(mApiClient, 1000, pendingIntent);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
