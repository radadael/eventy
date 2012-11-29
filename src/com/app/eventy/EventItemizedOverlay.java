package com.app.eventy;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.app.eventy.model.Event;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

@SuppressWarnings("rawtypes")
public class EventItemizedOverlay extends ItemizedOverlay {

	private List<Event> events = new ArrayList<Event>();
	private Context context;
	
	public EventItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		this.context = context;
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		Event event = events.get(i);
		GeoPoint point = new GeoPoint((int) (event.getLat() * 1E6), (int) (event.getLng() * 1E6));
		OverlayItem overlayItem = new OverlayItem(point, null, null);
		return overlayItem;
	}

	@Override
	public int size() {
		  return events.size();
	}
		
	@Override
	protected boolean onTap(int index) {
		Event event = events.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(event.getTitle());
		dialog.setMessage(event.getDescription());
		dialog.show();
		return true;
	}

	public void addEvent(Event event) {
		events.add(event);
		populate();
	}
	
}
