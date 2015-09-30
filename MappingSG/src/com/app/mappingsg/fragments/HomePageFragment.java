package com.app.mappingsg.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.app.mappingsg.HomeContent;
import com.app.mappingsg.R;
import com.app.mappingsg.Vars;
import com.app.mappingsg.objects.CodeDescPair;
import com.app.mappingsg.objects.DatabaseMain;
import com.app.mappingsg.utils.RegionItemSelected;
import com.app.mappingsg.utils.StreetLongPress;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class HomePageFragment extends Fragment{
	 // =========================================================================
 	 // TODO Variables
 	 // =========================================================================
	private int num_pages = 2;
	private ViewPager pager;
	private HomeContent core;
	private PageIndicator indicator;
	
	private String[] titles = new String[]{"Geological Profile", "Business Profile"};
	
	private Spinner spnRegion, spnProvince, spnCity, spnBarangay;
	private Button btnMainSend;
	
	private EditText etStreet;
	
	 // =========================================================================
	 // TODO Life Cycles
	 // =========================================================================
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//
		setHasOptionsMenu(true);
		//
		core = (HomeContent)getActivity();
		pager = (ViewPager)getActivity().findViewById(R.id.homePager);
		pager.setAdapter(new MyAdapter());
		pager.setOnPageChangeListener(onPageChange);
		//
		indicator = (TitlePageIndicator)getView().findViewById(R.id.homeIndicator);
		indicator.setViewPager(pager);
		indicator.setOnPageChangeListener(onPageChange);
		// 
	}   
	 // =========================================================================
	 // TODO Overrides
	 // =========================================================================
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		
		inflater.inflate(R.menu.send_button, menu);
		
		super.onCreateOptionsMenu(menu, inflater);
		//
		//menu.findItem(R.id.btnItemSend).setEnabled(false);
		menu.findItem(R.id.btnItemSend).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				
				sendMessage();
				return false;
			}
		});
	}
	// =========================================================================
	 // TODO Implementation
	 // =========================================================================
	private OnPageChangeListener onPageChange =  new OnPageChangeListener() {
		@Override
		public void onPageSelected(int pos) {
			SlidingMenu sm = core.getMenuSlide();
			
			switch (pos) {
			case 0:
				sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				break;
			default:
				sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			}
		}
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	};
	// =========================================================================
	 // TODO View Pager Page 1 Functions
	 // =========================================================================
	private void populateRegions(){
		
		DatabaseMain db = new DatabaseMain(getActivity());
		db.openToRead();
		ArrayList<CodeDescPair> allRegions = db.selecAllRegions();
		db.close();
		
		ArrayAdapter<CodeDescPair> codeAdapter = new ArrayAdapter<CodeDescPair>(getActivity(),
				android.R.layout.simple_spinner_item);
		codeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		for(CodeDescPair list:allRegions){
			codeAdapter.add(list);
		}
		spnRegion.setAdapter(codeAdapter);
	}
	 // =========================================================================
	 // TODO View Pager Page 2 Functions
	 // =========================================================================
	private void sendMessage(){
		
		if(Vars.DEBUG_MODE == 0){
			//
			if(core.gpsLocation.getGpsStatus() == LocationProvider.AVAILABLE){
				//
				TextMapping();
			} else {
				
				new AlertDialog.Builder(getActivity())
				.setTitle("No GPS")
				.setMessage("No GPS Coordinates Received, Change your location")
				.setCancelable(false)
				.setNeutralButton("OK", null)
				.show();
			}
		} else {
			TextMapping();
		}
		
	}
	// =========================================================================
	private void TextMapping(){
		
		String MESSAGE_MAP = "";
		String longLat = core.gpsLocation.getLongitude() + "/" + core.gpsLocation.getLatitude();
		String RegionCode = ((CodeDescPair)spnRegion.getSelectedItem()).Code;
		String ProvinceCode = ((CodeDescPair)spnProvince.getSelectedItem()).Code;
		String CityCode = ((CodeDescPair)spnCity.getSelectedItem()).Code;
		String BarangayCode = ((CodeDescPair)spnBarangay.getSelectedItem()).Code;
		
		MESSAGE_MAP += "SGCODE/" + longLat +"/" + RegionCode +"/" + ProvinceCode +"/"+ 
				CityCode +"/" + "/" + BarangayCode + "/";
		MESSAGE_MAP += "coming soon";
		
		
		new AlertDialog.Builder(getActivity())
		.setTitle("Text Mappping Guide")
		.setMessage(MESSAGE_MAP).setNeutralButton("OK", null)
		.show();
		
	}
	 // =========================================================================
	 // TODO Inner Class
	 // =========================================================================
	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {

			return num_pages;
		}
		@Override
		public boolean isViewFromObject(View view, Object o) {

			return view == o;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			//return "Hello";
			return titles[position];
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			View new_view = null;
			LayoutInflater inflater = getActivity().getLayoutInflater();

			switch (position) {
			case 0:
				new_view = inflater.inflate(R.layout.page_geological, null);
				setupGeological(new_view);
				//
				break;
			case 1:
				new_view = inflater.inflate(R.layout.page_business, null);
				setupBusiness(new_view);
				//
				break;
			}
			container.addView(new_view);
			return new_view;
		}
	}
	// =========================================================================
	private void setupGeological(View view){
		
		spnRegion = (Spinner)view.findViewById(R.id.spnRegion);
		spnProvince = (Spinner)view.findViewById(R.id.spnProvince);
		spnCity = (Spinner)view.findViewById(R.id.spnCity);
		spnBarangay = (Spinner)view.findViewById(R.id.spnBarangay);
		//
		spnRegion.setOnItemSelectedListener(new RegionItemSelected(getActivity(), spnProvince,
				spnCity, spnBarangay));
		etStreet = (EditText)view.findViewById(R.id.etStreet);
		etStreet.setOnLongClickListener(new StreetLongPress(getActivity(),etStreet));
		//
		populateRegions();
		//
	}
	// =========================================================================
	private void setupBusiness(View view){
		
		btnMainSend = (Button)view.findViewById(R.id.btnMainSend);
		btnMainSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 sendMessage();
			}
		});
		//
	}
	
}
