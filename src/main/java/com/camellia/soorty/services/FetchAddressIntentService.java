package com.camellia.soorty.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;


import com.camellia.soorty.R;
import com.camellia.soorty.utills.AppConstant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by amandeep on 6/2/18.
 */

public class FetchAddressIntentService extends IntentService {
    public final String TAG="FetchAddress";
    protected ResultReceiver mReceiver;
    protected Geocoder geocoder;
    private String pinCode;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public FetchAddressIntentService() {
        super("AddressService");
    }


    private void deliverResultToReceiver(int resultCode, String message, String pinCode) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConstant.RESULT_ADDRESS_KEY, message);
       // bundle.putString(AppConstant.RESULT_PINCODE_KEY, pinCode);
        mReceiver.send(resultCode, bundle);
    }



    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String errorMessage = "";

        mReceiver=intent.getParcelableExtra(AppConstant.RECEIVER);
        geocoder = new Geocoder(this, Locale.getDefault());

        // Get the location passed to this service through an extra.
        Location location = intent.getParcelableExtra(
                AppConstant.LOCATION_DATA_EXTRA);

        // ...

        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    // In this sample, get just a single address.
                    1);
        } catch (IOException ioException) {
            // Catch network or other I/O problems.
            errorMessage = getString(R.string.service_not_available);
            Log.e(TAG, errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = getString(R.string.invalid_lat_long_used);
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
            }
            deliverResultToReceiver(AppConstant.FAILURE_RESULT, errorMessage,"");
        } else {
            Address address = addresses.get(0);
            pinCode=address.getPostalCode();
            ArrayList<String> addressFragments = new ArrayList<String>();


            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }

            deliverResultToReceiver(AppConstant.SUCCESS_RESULT_ADDRESS_LATLONG,
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments),pinCode);
        }

    }

}
