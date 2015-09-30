package com.app.mappingsg.objects;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;

public class GPSLocation implements LocationListener {
	
	private Context core;
	private int GPSstatus;
	private double Longitude = 0.0;
	private double Latitude = 0.0;
	
	public GPSLocation(Context con){
		
		this.core = con;
	}

	@Override
	public void onLocationChanged(Location location) {
		
		if(location.getProvider().matches(LocationManager.GPS_PROVIDER)){
			GPSstatus = LocationProvider.AVAILABLE;
			Longitude = location.getLongitude();
			Latitude = location.getLatitude();
		}
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
		if(provider.matches(LocationManager.GPS_PROVIDER)){
			
			GPSstatus = status;
		}
	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onProviderDisabled(String provider) {
		
		new AlertDialog.Builder(core)
		.setTitle("No GPS")
		.setMessage("Enable GPS Now")
		.setCancelable(false)
		.setNeutralButton("Yes", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				core.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
				dialog.dismiss();
			}
		})
		.show();
	}
	
	public double getLongitude(){
		return this.Longitude;
	}
	
	public double getLatitude(){
		return this.Latitude;
	}

	public int getGpsStatus(){
		return GPSstatus;
	}
}
