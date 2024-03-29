package com.sajidur.diugo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class PlaceFinderActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_finder);


        mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//
//                String location = searchView.getQuery().toString();
//                List<Address> addressList = null;
//                if(location!=null || !location.equals("")){
//                    Geocoder geocoder = new Geocoder(PlaceFinderActivity.this);
//                    try{
//                        addressList = geocoder.getFromLocationName(location,1);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Address address=addressList.get(0);
//                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
//                    map.addMarker(new MarkerOptions().position(latLng).title(location));
//                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//                }
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });


        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try{
            map=googleMap;

            String location = getIntent().getStringExtra("location");
            Toast.makeText(PlaceFinderActivity.this,location,Toast.LENGTH_LONG).show();
            List<Address> addressList = null;
            if(location!=null || !location.equals("")){
                Geocoder geocoder = new Geocoder(PlaceFinderActivity.this);
                try{
                    addressList = geocoder.getFromLocationName(location,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Address address=addressList.get(0);
            System.out.println(address);
            LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title(location));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
        }catch (Exception e){
            Toast.makeText(PlaceFinderActivity.this,"Network Connection Problem",Toast.LENGTH_LONG).show();
        }
    }
}
