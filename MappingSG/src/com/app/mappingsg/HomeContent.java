package com.app.mappingsg;

import com.app.mappingsg.fragments.HomePageFragment;
import com.app.mappingsg.objects.GPSLocation;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeContent extends BaseActivity{
	// =========================================================================
	// TODO Variables
	// =========================================================================
	public LocationManager locationManager;
	public GPSLocation gpsLocation;
	
	public HomeContent() {
		super(R.string.app_name);
		
	} 
	// =========================================================================
	// TODO Life Cycles
	// =========================================================================
	@Override
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		getSlidingMenu().setMode(SlidingMenu.LEFT);
		getSlidingMenu().setShadowWidth(5);
		getSlidingMenu().setBehindScrollScale(0);
		getSlidingMenu().setFadeEnabled(false);
		//
		setContentView(R.layout.frame_content);
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.frame_content, new HomePageFragment())
		.commit();
		
		//if (Vars.DEBUG_MODE == 0) {
			//
			locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
			gpsLocation = new GPSLocation(this);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gpsLocation);
		//}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		//if (Vars.DEBUG_MODE == 0) {
			//
			locationManager.removeUpdates(gpsLocation);
		//}
	}
	// =========================================================================
	// TODO Overrides
	// =========================================================================
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			break;
		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	// =========================================================================
	// TODO Main Functions
	// =========================================================================
	public SlidingMenu getMenuSlide(){
		return getSlidingMenu();
	}
	
}
