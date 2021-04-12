package com.crTech.prakashmarble.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.User.DashBoard;

public class SplashActivity extends AppCompatActivity {
    private static String[] PERMISSIONS = {Manifest.permission.INTERNET,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION
            ,Manifest.permission.ACCESS_NETWORK_STATE};
    private static int TIME_OUT = 4000;
    int REQUEST_PERMISSIONS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getPermission();
    }
    private void getPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.INTERNET)
                ||ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)
                ||ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                ||ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_NETWORK_STATE))
        {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSIONS);
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_PERMISSIONS);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                        startActivity(intent);
                    }

                }, TIME_OUT);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                        startActivity(intent);
                    }
                }, TIME_OUT);
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }}
