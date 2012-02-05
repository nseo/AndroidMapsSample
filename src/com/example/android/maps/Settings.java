package com.example.android.maps;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class Settings extends MapActivity {
	private static final int MENU_SATELLITE = (Menu.FIRST + 1);
	private static final int MENU_STREET_VIEW = (Menu.FIRST + 2);
	private static final int MENU_TRAFFIC = (Menu.FIRST + 3);
	MapView mapView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mapView = new MapView(this, getResources().getString(R.string.map_key));
		mapView.setEnabled(true);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		setContentView(mapView);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, MENU_SATELLITE, Menu.NONE, "モード切替");
		menu.add(Menu.NONE, MENU_STREET_VIEW, Menu.NONE, "ストリートビュー");
		menu.add(Menu.NONE, MENU_TRAFFIC, Menu.NONE, "交通量");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean rc = true;
		switch (item.getItemId()) {
		case MENU_SATELLITE:
			mapView.setSatellite(!mapView.isSatellite());
			break;
		case MENU_STREET_VIEW:
			mapView.setStreetView(!mapView.isStreetView());
			break;
		case MENU_TRAFFIC:
			mapView.setTraffic(!mapView.isTraffic());
			break;
		default:
			rc = super.onOptionsItemSelected(item);
			break;
		}
		return rc;
	}
}