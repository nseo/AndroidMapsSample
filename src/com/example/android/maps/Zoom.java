package com.example.android.maps;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class Zoom extends MapActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MapView mapView = new MapView(this, getResources().getString(R.string.map_key));
		mapView.setEnabled(true);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		setContentView(mapView);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
