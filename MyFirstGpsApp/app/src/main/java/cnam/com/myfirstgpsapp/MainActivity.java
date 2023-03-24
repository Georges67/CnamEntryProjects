package cnam.com.myfirstgpsapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 123;
    private static final int SHOW_PATH_LIST = 456;
    public static final String PATH_ID = "PathID";
    public static final String SHOW_ACTION = "SHOW";

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    private GoogleMap m_googleMap;
    private LocationManager m_locationManager;
    private Location m_myLastLocation = null;
    private int m_distanceParcourue = 0;
    private TextView m_distanceParcourueTv;
    private boolean m_newPathStarted;
    private MyGpsPathDbHelper m_gpsPathDbHelper;
    private GpsPath m_currentPath;
    private GpsPath m_shownPath;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_gpsPathDbHelper = new MyGpsPathDbHelper(this);

        m_distanceParcourueTv = findViewById(R.id.distanceParcourue);

        // TODO 10) use SupportMapFragment on layout fragment and get notified when the map is ready to be used.

        // TODO 13) get locationManager

        // 6 - Configure all views

        this.configureToolBar();

        this.configureDrawerLayout();

        this.configureNavigationView();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i(LOG_TAG, "PERMISSION REQUIRED");
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return;
        }

        // TODO 14) use locationManager to get notified of location Updates
    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // TODO 8) override method onNavigationItemSelected
    // TODO 16) call method askPathNameBeforeCreating on click start new path

    private void askPathNameBeforeCreating() {
        // TODO 17) create an AlertDialog
    }

    // TODO override onActivityResult

    // ---------------------
    // CONFIGURATION
    // ---------------------

    // 1 - Configure Toolbar
    private void configureToolBar() {
        // TODO 1) fill this method to link toolbar to Activity
    }

    // 2 - Configure Drawer Layout
    private void configureDrawerLayout() {
        // TODO 2) fill this method to link menu to the toolbar
    }

    // 3 - Configure NavigationView
    private void configureNavigationView() {
        // TODO 3) fill this method to be notified of navigation menu item click
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {

                        //Request location updates:
                        // TODO 13) using locationManager, call method requestLocationUpdates to get notified of location updates
                    }

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    finish();
                }
                return;
            }
        }
    }

    // TODO 11) override onMapReady and save given googleMap

    // TODO 14) override onLocationChanged, onStatusChanged, onProviderEnabled and onProviderDisabled

}