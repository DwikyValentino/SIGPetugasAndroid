package com.dwikyv.sigpertanian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;


public class MapActivity extends AppCompatActivity {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //initialize fragment
        Fragment fragment = new MapFragment();

        //Open fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

}
