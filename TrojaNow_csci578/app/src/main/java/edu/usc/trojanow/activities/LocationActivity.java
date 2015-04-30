package edu.usc.trojanow.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends Activity implements LocationListener {

    private TextView latitudeField;
    private TextView longitudeField;
    private TextView cityText;
    private LocationManager locationManager;
    private String provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        latitudeField = (TextView) findViewById(R.id.latitude);
        longitudeField = (TextView) findViewById(R.id.longitude);
        cityText = (TextView) findViewById(R.id.city);

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the location provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            latitudeField.setText("Location not available");
            longitudeField.setText("Location not available");
        }

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button includeButton = (Button) findViewById(R.id.includeButton);
        includeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mCity = "";
                if(cityText.getText().length()>0)
                {
                    mCity = " in " + cityText.getText().toString();
                }

                System.out.println ("city = " + mCity);

                Intent intent = new Intent();
                intent.putExtra("LocationInfo", mCity);
                setResult(RESULT_OK, intent);
                finish();
            }
        });




    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double fLat = location.getLatitude();
        double fLong = location.getLongitude();
        int iLat = (int) fLat;
        int iLong = (int) fLong;
        latitudeField.setText(String.valueOf(iLat));
        longitudeField.setText(String.valueOf(iLong));

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(fLat, fLong, 1);
            String city = addresses.get(0).getLocality() + ", " + addresses.get(0).getAdminArea();
            cityText.setText(city);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }



}
