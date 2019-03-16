package com.app.nearbyhotel.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.app.nearbyhotel.R;
import com.app.nearbyhotel.databinding.ActivityMainBinding;
import com.app.nearbyhotel.fragments.HotelNearbyFragment;
import com.app.nearbyhotel.interfaces.LocationCompletion;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;
    FusedLocationProviderClient locationClient;
    LocationCompletion locationCompletion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        locationClient = LocationServices.getFusedLocationProviderClient(this);
        setFrame(R.id.mainFrame);
        replaceFragment(new HotelNearbyFragment(), false, false);
    }

    public void getCurrentLocation(LocationCompletion handler) {

        locationCompletion = handler;

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermision();
            return;
        }


        locationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    locationCompletion.onLocationChanged(location);
                }
            }
        });
    }


    private void requestLocationPermision() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 55);
    }

    public void hideLoader()
    {
        binding.mainFrame.setVisibility(View.VISIBLE);
        binding.progressBarContainer.setVisibility(View.GONE);
    }

    public void showLoader()
    {
        binding.mainFrame.setVisibility(View.GONE);
        binding.progressBarContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 55:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                    getCurrentLocation(locationCompletion);

                } else {
                    requestLocationPermision();
                    //  Toast.makeText(this, "Please enable location from app settings", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
