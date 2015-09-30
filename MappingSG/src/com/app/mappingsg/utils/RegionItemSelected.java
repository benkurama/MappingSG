package com.app.mappingsg.utils;

import java.util.ArrayList;

import com.app.mappingsg.objects.CodeDescPair;
import com.app.mappingsg.objects.DatabaseMain;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegionItemSelected implements OnItemSelectedListener{
	
	Context core;
	Spinner spnProvince, spnCity, spnBarangay;
	
	public RegionItemSelected(Context core, Spinner spnProvince, Spinner spnCity, Spinner spnBarangay){
		this.core = core;
		this.spnProvince = spnProvince;
		this.spnCity = spnCity;
		this.spnBarangay = spnBarangay;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		//Utils.callToast(core, "It Works");
		String regCode = ((CodeDescPair)parent.getSelectedItem()).Code;
		
		DatabaseMain db = new DatabaseMain(core);
		db.openToRead();
		ArrayList<CodeDescPair> provinceList = db.selectProvince(regCode);
		db.close();
		//
		ArrayAdapter<CodeDescPair> adapter = Utils.populateSpinner(core, "Select Province", provinceList);
		//
		spnProvince.setAdapter(adapter);
		spnProvince.setOnItemSelectedListener(new ProvinceItemSelected(core, spnCity, spnBarangay));
		//
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

}
