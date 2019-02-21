package com.jevo.alexander.lesson1.tools;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationListener implements LocationListener {
    @Override
    public void onLocationChanged(Location loc) {

        String longitude = "Longitude: " + loc.getLongitude();
        Log.v("ТАГ", longitude);
        String latitude = "Latitude: " + loc.getLatitude();
        Log.v("ТАГ", latitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.v("ТАГ", "onStatusChanged");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.v("ТАГ", "onProviderEnabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.v("ТАГ", "onProviderDisabled");
    }
}
