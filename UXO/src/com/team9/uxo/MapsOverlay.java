package com.team9.uxo;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

public class MapsOverlay extends ItemizedOverlay {
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	Context mContext;
	int lat;
	int lng;
	
	
	public MapsOverlay(Drawable defaultMarker) {
		  super(boundCenterBottom(defaultMarker));
		}
	
	public MapsOverlay(Drawable defaultMarker, Context context) {
		  super(boundCenterBottom(defaultMarker));
		  mContext = context;
		}
	
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		  return mOverlays.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		 return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
	
	public boolean onTap(GeoPoint geoPoint, MapView mapView) {
		lat = geoPoint.getLatitudeE6(); 
        lng = geoPoint.getLongitudeE6();
        return super.onTap(geoPoint,mapView);
		
	}
	
	@Override
    public void draw(Canvas canvas, MapView mapV, boolean shadow){
		GeoPoint pt1 = new GeoPoint(lat, lng);
        if(shadow){
            Projection projection = mapV.getProjection();
            Point pt = new Point();
            projection.toPixels(pt1,pt);

            GeoPoint newGeos = new GeoPoint(lat+(10000),lng); // adjust your radius accordingly
            Point pt2 = new Point();
            projection.toPixels(newGeos,pt2);
            float circleRadius = Math.abs(pt2.y-pt.y);

            Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

            circlePaint.setColor(Color.RED);
            circlePaint.setStyle(Style.FILL_AND_STROKE);
            circlePaint.setAlpha(25);
            canvas.drawCircle((float)pt.x, (float)pt.y, circleRadius, circlePaint);
            

            circlePaint.setColor(Color.RED);
            circlePaint.setStyle(Style.STROKE);
            canvas.drawCircle((float)pt.x, (float)pt.y, circleRadius, circlePaint);

            Bitmap markerBitmap = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.red_cross);
            canvas.drawBitmap(markerBitmap,pt.x,pt.y-markerBitmap.getHeight(),null);

            super.draw(canvas,mapV,shadow);
        }
    }
}
