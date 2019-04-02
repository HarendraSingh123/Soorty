package com.camellia.soorty.utills;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class AppHelper {

    public static boolean isLocationProviderEnabled(Context context) {
        boolean enableGPS, enableNetwork;
        LocationManager lmngr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        enableNetwork = lmngr.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        enableGPS = lmngr.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d("is gps enabled", "-" + enableGPS + "----" + enableNetwork);
        return enableGPS || enableNetwork;
    }

    static Location bestLocation = null;

    @SuppressLint("MissingPermission")
    public static Location getLastKnownLocation(Context context)   {
        boolean enableGPS, enableNetwork;
        Location locationGps = null, locationNetwork = null;

        LocationManager lmngr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        enableNetwork = lmngr.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        enableGPS = lmngr.isProviderEnabled(LocationManager.GPS_PROVIDER);

        Log.d("is gps enabled", "-" + enableGPS + "----" + enableNetwork);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                bestLocation = location;
                Log.e("changed location", bestLocation.getLatitude() + " received");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (enableNetwork) {
            lmngr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationNetwork = lmngr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (enableGPS) {
            lmngr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            locationGps = lmngr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        if (enableNetwork && enableGPS) {
            if (locationGps != null && locationNetwork != null) {
                if (locationGps.getAccuracy() < locationNetwork.getAccuracy()) {
                    bestLocation = locationGps;
                } else bestLocation = locationNetwork;
            } else if (locationGps != null) {
                bestLocation = locationGps;
            } else if (locationNetwork != null) {
                bestLocation = locationNetwork;
            }
        } else if (enableNetwork) bestLocation = locationNetwork;
        else if (enableGPS) bestLocation = locationGps;
        Log.d("last know location", "dd" + bestLocation);
        lmngr.removeUpdates(locationListener);
        return bestLocation;
    }
}
