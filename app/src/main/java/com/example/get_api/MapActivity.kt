package com.example.get_api

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    lateinit var currentLocation : Location
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    private val REQUEST_CODE=101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)
    }

    private fun fetchLastLocation() {
       if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
           ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
            return
        }
        var task : Task<Location> = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener {
            object : OnSuccessListener<Location>{
                override fun onSuccess(p0: Location?) {
                    if(p0 != null)
                    {
                        currentLocation=p0

                    }
                }

            }
        }

    }

    override fun onMapReady(p0: GoogleMap) {
        mMap=p0

        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        /*val latLug : LatLng= LatLng(currentLocation.latitude, currentLocation.longitude)
        val markerOptions : MarkerOptions=MarkerOptions().position(latLug).title("Current Location")
        p0.moveCamera(CameraUpdateFactory.newLatLng(latLug))
        p0.animateCamera(CameraUpdateFactory.newLatLng(latLug))
        p0.animateCamera(CameraUpdateFactory.newLatLngZoom(latLug,5f))
        p0.addMarker(markerOptions)*/



    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            when(requestCode)
            {
                REQUEST_CODE -> {
                    if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    {
                        fetchLastLocation()
                    }
                }
            }
    }


}