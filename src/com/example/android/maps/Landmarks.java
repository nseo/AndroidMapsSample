package com.example.android.maps;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class Landmarks extends MapActivity {
	class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
		public MyItemizedOverlay(Drawable defaultMarker) {
			super(defaultMarker);
			populate();
		}
		
		@Override
		protected OverlayItem createItem(int index) {
			String name = null;
			GeoPoint gp = list.get(index);
			for (Object[] o : landmarks) {
				if (gp == o[1]) {
					name = (String)o[0];
					break;
				}
			}
			return new OverlayItem(gp, name, name);
		}
		
		@Override
		public int size() {
			return list.size();
		}
		
		@Override
		protected boolean onTap(int index) {
			mapView.getController().animateTo(getItem(index).getPoint());
			return true;
		}
	}
	
	Object[][] landmarks = {
		{"東京タワー", new GeoPoint(35658610, 139745447), false},
		{"横浜ランドマークタワー", new GeoPoint(35454559, 139631373), false},
		{"通天閣", new GeoPoint(34652553, 135506333), false},
		{"福岡タワー", new GeoPoint(33593313, 130351478), false},
	};
	ArrayList<GeoPoint> list = new ArrayList<GeoPoint>();
	
	MapView mapView;
	MyItemizedOverlay overlay;
	Drawable mark;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mapView = new MapView(this, getResources().getString(R.string.map_key));
		mapView.setEnabled(true);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		mark = getResources().getDrawable(R.drawable.marker);
		mark.setBounds(0, 0, mark.getMinimumWidth(), mark.getMinimumHeight()); // これを行わないと表示されない
		overlay = new MyItemizedOverlay(mark);
		mapView.getOverlays().add(overlay);
		setContentView(mapView);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		for (int i = 0; i < landmarks.length; i++) {
			menu.add(Menu.NONE, Menu.FIRST + 1 + i, Menu.NONE, (String)landmarks[i][0]);
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		for (int i = 0; i < landmarks.length; i++) {
			menu.findItem(Menu.FIRST + 1 + i).setTitle(((Boolean)landmarks[i][2]).booleanValue() ? "×" + landmarks[i][0] : (String)landmarks[i][0]);
		}
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int index = item.getItemId() - (Menu.FIRST + 1);
		landmarks[index][2] = !((Boolean)landmarks[index][2]).booleanValue();
		mapView.getOverlays().clear();
		list.clear();
		for (Object[] o : landmarks) {
			if (((Boolean)o[2]).booleanValue()) {
				list.add((GeoPoint)o[1]);
			}
		}
		overlay = new MyItemizedOverlay(mark);
		mapView.getOverlays().add(overlay);
		mapView.postInvalidate();
		return true;
	}
}