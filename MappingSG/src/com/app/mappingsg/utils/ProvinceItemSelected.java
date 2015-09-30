package com.app.mappingsg.utils;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

import com.app.mappingsg.objects.CodeDescPair;
import com.app.mappingsg.objects.DatabaseMain;

public class ProvinceItemSelected implements OnItemSelectedListener{
	
	private Context core;
	private Spinner spnCity, spnBarangay;
	
	public ProvinceItemSelected(Context core, Spinner spnCity, Spinner spnBarangay) {
		this.core = core;
		this.spnCity = spnCity;
		this.spnBarangay = spnBarangay;
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		//
		String ProvCode = ((CodeDescPair)parent.getSelectedItem()).Code;
		//
		DatabaseMain db = new DatabaseMain(core);
		db.openToRead();
		ArrayList<CodeDescPair> cityList = db.selectCity(ProvCode);
		db.close();
		//
		ArrayAdapter<CodeDescPair> adapter = Utils.populateSpinner(core, "Select City", cityList);
		//
		spnCity.setAdapter(adapter);
		spnCity.setOnItemSelectedListener(new CityItemSelected(core, spnBarangay));
		//
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

	
}
