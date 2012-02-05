package com.example.android.maps;

import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class Tracking extends MapActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final MapView mapView = new MapView(this, getResources().getString(R.string.map_key));
		mapView.setEnabled(true);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		final MyLocationOverlay overlay = new MyLocationOverlay(getApplicationContext(), mapView);
		overlay.onProviderEnabled(LocationManager.GPS_PROVIDER);
		overlay.enableMyLocation();
		overlay.runOnFirstFix(new Runnable() {
			@Override
			public void run() {
				mapView.getController().animateTo(overlay.getMyLocation());
			}
		});
		mapView.getOverlays().add(overlay);
		mapView.invalidate();
		setContentView(mapView);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
