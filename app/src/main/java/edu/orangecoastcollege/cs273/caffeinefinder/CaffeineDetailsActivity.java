package edu.orangecoastcollege.cs273.caffeinefinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CaffeineDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Location selectedLocation;
    private GoogleMap map;

    private TextView nameTextView;
    private TextView addressTextView;
    private TextView phoneTextView;
    private TextView positionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_details);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        positionTextView = (TextView) findViewById(R.id.positionTextView);

        nameTextView.setText(selectedLocation.getName());
        addressTextView.setText(selectedLocation.getAddress());
        phoneTextView.setText(selectedLocation.getPhone());
        positionTextView.setText(selectedLocation.getLatitude() + ", " + selectedLocation.getLongitude());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.caffeineDetailsMapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng caffeineLatLng = new LatLng(selectedLocation.getLatitude(),selectedLocation.getLongitude());

        map.addMarker(new MarkerOptions().title(selectedLocation.getName()).position(caffeineLatLng));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(caffeineLatLng).zoom(15.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);

        map.moveCamera(cameraUpdate);


    }
}
