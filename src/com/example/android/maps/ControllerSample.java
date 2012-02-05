package com.example.android.maps;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class ControllerSample extends MapActivity {
	private static final int MENU_TOKYO = (Menu.FIRST + 1);
	private static final int MENU_OSAKA = (Menu.FIRST + 2);
	private static final int MENU_ZOOM_IN = (Menu.FIRST + 3);
	private static final int MENU_ZOOM_OUT = (Menu.FIRST + 4);
	private static final GeoPoint TOKYO = new GeoPoint(
			new Double(35.68198003744061 * 1E6).intValue(),
			new Double(139.76609230041504 * 1E6).intValue()); 
	private static final GeoPoint OSAKA = new GeoPoint(
			new Double(34.702248 * 1E6).intValue(),
			new Double(135.495329 * 1E6).intValue());
	
	private MapView mapView;
	
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
		menu.add(Menu.NONE, MENU_TOKYO, Menu.NONE, "ìåãû");
		menu.add(Menu.NONE, MENU_OSAKA, Menu.NONE, "ëÂç„");
		menu.add(Menu.NONE, MENU_ZOOM_IN, Menu.NONE, "ägëÂ");
		menu.add(Menu.NONE, MENU_ZOOM_OUT, Menu.NONE, "èkè¨");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean rc = true;
		switch (item.getItemId()) {
		case MENU_TOKYO:
			mapView.getController().animateTo(TOKYO);
			break;
		case MENU_OSAKA:
			mapView.getController().animateTo(OSAKA);
			break;
		case MENU_ZOOM_IN:
			mapView.getController().zoomIn();
			break;
		case MENU_ZOOM_OUT:
			mapView.getController().zoomOut();
			break;
		default:
			rc = super.onOptionsItemSelected(item);
			break;
		}
		return rc;
	}
}