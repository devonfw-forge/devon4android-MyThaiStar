package com.devonfw.mythaistar.location


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.base.BaseFragment
import com.devonfw.mythaistar.main.MainComponent
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.devonfw.mythaistar.home.HomeFragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*



/**
 * Created by zaptsiau on 20.11.2017.
 */
class LocationFragment : BaseFragment<MainComponent,LocationUI,LocationPresenter>(),
        LocationUI, OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks{



    override val layoutId =  R.layout.fragment_location
    override val ui= this
    private  var mMapView: MapView? = null
    private var googleApiClien : GoogleApiClient? = null
    private val position = LatLng(38.886662,1.406482)



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, inState: Bundle?): View {
        var view = inflater.inflate(R.layout.fragment_location,container,false)
         mMapView = view.findViewById(R.id.map) as MapView
        mMapView?.onCreate(inState)
        mMapView?.getMapAsync(this)
        buildGoogleClient()
        return view
    }

    @Synchronized
    protected  fun  buildGoogleClient(){
        googleApiClien = GoogleApiClient.Builder(activity.applicationContext)
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .build()
    }


    override fun onResume() {
        mMapView?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMapView?.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        googleApiClien?.connect()
    }

    override fun onStop() {
        super.onStop()
        googleApiClien?.disconnect()
    }



    override fun onMapReady(map: GoogleMap?) {
        map!!.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isRotateGesturesEnabled = true
        map.isBuildingsEnabled = true
        val cameraPosition = buildCameraPosition()
        map.addMarker(MarkerOptions().position(position).title("My Thai Star"))
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }





    override fun onConnectionFailed(p0: ConnectionResult) {

    }



    override fun onConnected(p0: Bundle?) {


    }

    override fun onConnectionSuspended(p0: Int) {

    }


    override fun inject(component: MainComponent) {
        component.inject(this)
    }

    override fun handleBackButton(): Boolean {
        navigator.show(HomeFragment())
        return true
    }




    private fun buildCameraPosition() : CameraPosition{
        val cameraPosition = CameraPosition.Builder()
                .target(position)
                .zoom(20f)                   // Sets the zoom
                .bearing(90f)                // Sets the orientation of the camera to east
                .tilt(30f) // Sets the tilt of the camera to 30 degrees
                .build()

        return cameraPosition

    }

}