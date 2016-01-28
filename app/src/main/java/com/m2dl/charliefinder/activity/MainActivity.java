package com.m2dl.charliefinder.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Button;

import com.m2dl.charliefinder.FullscreenActivity;
import com.m2dl.charliefinder.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST = 0;
    private static final int SETTINGS_ACTIVITY_REQUEST_CODE = 100;

    private Button settings;
    private Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        settings = (Button) findViewById(R.id.btnSettings);
        play = (Button) findViewById(R.id.btnPlay);

    }

    public void loadSettings(View view) {
        Intent myIntent = new Intent(this, SettingsActivity.class);
        startActivityForResult(myIntent, SETTINGS_ACTIVITY_REQUEST_CODE);
    }

    public void permissions() {
        String permissions[] = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        ActivityCompat.requestPermissions(this, permissions, 3);
    }

    public void launchSettingsActivity(View view) {
        //Intent intent = new Intent(this, PlanActivity.class);
        //startActivity(intent);
    }

    public void launchEndGameActivity(View view) {
        Intent intent = new Intent(this, EndGameActivity.class);
        startActivity(intent);
    }

    public void launchGameActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }
}
