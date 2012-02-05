package com.example.android.maps;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class OverlaySample extends MapActivity {
	class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
		public MyItemizedOverlay(Drawable defaultMarker) {
			super(defaultMarker);
			boundCenterBottom(defaultMarker);
			populate();
		}
		
		@Override
		protected OverlayItem createItem(int index) {
			OverlayItem item = new OverlayItem(new GeoPoint(35658610, 139745447), "東京タワー", "東京タワー");
			return item;
		}
		
		@Override
		public int size() {
			return 1;
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MapView mapView = new MapView(this, getResources().getString(R.string.map_key));
		mapView.setEnabled(true);
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		Drawable mark = getResources().getDrawable(R.drawable.marker);
		mark.setBounds(0, 0, mark.getMinimumWidth(), mark.getMinimumHeight()); // これを行わないと表示されない
		MyItemizedOverlay overlay = new MyItemizedOverlay(mark);
		mapView.getOverlays().add(overlay);
		setContentView(mapView);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}