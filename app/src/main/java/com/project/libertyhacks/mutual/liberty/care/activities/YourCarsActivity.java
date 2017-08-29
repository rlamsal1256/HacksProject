package com.project.libertyhacks.mutual.liberty.care.activities;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    private RelativeLayout addCarLayout;
    private FloatingActionButton addAnotherCarBtn;
    private RecyclerView myRecyclerView;
    private LinearLayoutManager myLayoutManager;
    ArrayList<Car> cars;
    String totalStepsStr;

    // Receives updates from @StepCounterAndDetectActivityService when steps are detected
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Inside youractivity", "Results received******");
            getMilesFromSharedPreferenceAndUpdateUI();
            updateAdapter();
        }
    };

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

        getMilesFromSharedPreferenceAndUpdateUI();

        populateScreenWithCars();

        createApiClientAndConnect();

        // ImageButton to add a car
        ImageButton addCarBtn = findViewById(R.id.addCarBtn);
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

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(
                StepCounterAndDetectActivityService.NOTIFICATION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private void populateScreenWithCars() {
        myRecyclerView = findViewById(R.id.cardView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        updateAdapter();
    }

    private void updateAdapter() {
        if (cars.size() > 0) {
            YourCarsAdapter myAdapter = new YourCarsAdapter(cars, totalStepsStr);
            myRecyclerView.setAdapter(myAdapter);
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

    private void getMilesFromSharedPreferenceAndUpdateUI() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String steps = prefs.getString("steps", "0");

        extractSteps(steps);
        totalStepsStr = totalSteps + " steps";
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
