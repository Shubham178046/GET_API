package com.example.get_api.ui

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.get_api.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,LocationListener,
    GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private var mMap: GoogleMap?=null
    internal var mGoogleApiClient : GoogleApiClient?=null
    internal lateinit var mLastLocation : Location
    internal lateinit var mLocationResult : LocationRequest
    internal var mCurrentLocationMarker : Marker?=null
    lateinit var mLocationCallback: LocationCallback
    internal lateinit var mLocationRequest: LocationRequest
    internal var mFusedLocationClient : FusedLocationProviderClient?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        mGoogleApiClient= GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build()

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    protected override fun onStart(): Unit {
        super.onStart()
        // Connect the client.
        mGoogleApiClient!!.connect()
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap!!.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        if(android.os.Build.VERSION.SDK_INT  >= Build.VERSION_CODES.M)
        {
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                buildGradleApiClient()
                mMap!!.isMyLocationEnabled = true
            }
        }
        else
        {
            buildGradleApiClient()
            mMap!!.isMyLocationEnabled = true
        }
    }
    @Synchronized
    protected  fun buildGradleApiClient() {
        mGoogleApiClient= GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build()
        mGoogleApiClient!!.connect()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(applicationContext,"Connection Failed: "+p0.toString(),Toast.LENGTH_LONG)
    }

    override fun onConnected(p0: Bundle?) {

        val location = LocationServices.FusedLocationApi.getLastLocation(
            mGoogleApiClient
        )
        if (location != null) {
            val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }
        else
        {
             AlertDialog.Builder(this)
                .setTitle("Please activate location")
                .setMessage("Click ok to goto settings else exit.")
                .setPositiveButton(android.R.string.yes, object :  DialogInterface.OnClickListener {
                    public override fun onClick(dialog: DialogInterface, which : Int ) {
                         val intent : Intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                 .setNegativeButton(android.R.string.no, object : DialogInterface.OnClickListener {
                     public override fun onClick(dialog: DialogInterface, which : Int) {
                         System.exit(0);
                     }
                 })
                 .show();
        }

 mLocationResult = LocationRequest()
        mLocationResult.interval=10000
        mLocationResult.fastestInterval=1000
        mLocationResult.priority=LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mFusedLocationClient=LocationServices.getFusedLocationProviderClient(this)
            mFusedLocationClient?.requestLocationUpdates(mLocationResult, mLocationCallback, Looper.myLooper())
        }
    }

    override fun onConnectionSuspended(p0: Int) {
        Toast.makeText(applicationContext,"Connection Suspend: "+p0.toString(),Toast.LENGTH_LONG)
    }

 override fun onLocationChanged(location: Location) {
        mLastLocation= location
        if(mCurrentLocationMarker != null)
        {
            mCurrentLocationMarker!!.remove()
        }

        val latLng= LatLng(location.latitude, location.longitude)
        val markerOptions= MarkerOptions()

        markerOptions.position(latLng)
        markerOptions.title("Current Location")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        mCurrentLocationMarker = mMap!!.addMarker(markerOptions)


        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(11f))

        if(mGoogleApiClient != null)
        {
            mFusedLocationClient?.removeLocationUpdates(mLocationCallback)
        }
    }
}