package edu.neu.madcourse.numad21fa_sihengwei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;


public class LocatorActivity extends AppCompatActivity {

  public static final int LOCATION_CODE = 301;
  private LocationManager locationManager;
  private String locationProvider = null;

  TextView tv_longitude;
  TextView tv_latitude;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_locator);
    tv_longitude = findViewById(R.id.tv_longitude);
    tv_latitude = findViewById(R.id.tv_latitude);
    findViewById(R.id.btn_showLocation).setOnClickListener(view -> getLocation());
    initialItemData(savedInstanceState);
  }

  public void getLocation() {
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    locationProvider = LocationManager.GPS_PROVIDER;
    // check the location permission
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
      // ask for the location permission
      ActivityCompat.requestPermissions(
          this,
          new String[] {
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
          },
          LOCATION_CODE);
    } else {
      // Already have the permission to access the location
      Location location = locationManager.getLastKnownLocation(locationProvider);
      if (location != null) {
        tv_longitude.setText("Longitude: " + location.getLongitude());
        tv_latitude.setText("Latidude: "+ location.getLatitude());
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == LOCATION_CODE) {
      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
              != PackageManager.PERMISSION_GRANTED
          && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
              != PackageManager.PERMISSION_GRANTED) {
        // do not have the permission to access the location
        return;
      }
      // get the permission to access the location
      locationProvider = LocationManager.GPS_PROVIDER;
      Location location = locationManager.getLastKnownLocation(locationProvider);
      if (location != null) {
        tv_longitude.setText("Longitude: " + location.getLongitude());
        tv_latitude.setText("Latidude: "+ location.getLatitude());
      }
    }
  }

  // Handling Orientation Changes on Android
  private void initialItemData(Bundle savedInstanceState) {
    if(savedInstanceState != null && savedInstanceState.containsKey("longitude") && savedInstanceState.containsKey("latitude")){
      tv_longitude.setText(savedInstanceState.getString("longitude"));
      tv_latitude.setText(savedInstanceState.getString("latitude"));
    }
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    outState.putString("longitude", tv_longitude.getText().toString());
    outState.putString("latitude", tv_latitude.getText().toString());
    super.onSaveInstanceState(outState);
  }
}
