package com.app.mappingsg.utils;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.mappingsg.objects.CodeDescPair;
import com.app.mappingsg.objects.DatabaseMain;

public class CityItemSelected implements OnItemSelectedListener{
	
	private Context core;
	private Spinner spnBarangay;
	
	public CityItemSelected(Context core, Spinner spnBarangay){
		this.core = core;
		this.spnBarangay = spnBarangay;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		//
		String CityCode = ((CodeDescPair)parent.getSelectedItem()).Code;
		//
		DatabaseMain db = new DatabaseMain(core);
		db.openToRead();
		ArrayList<CodeDescPair> barangayList = db.selectBarangay(CityCode);
		db.close();
		//
		ArrayAdapter<CodeDescPair> adapter = Utils.populateSpinner(core, "Select Barangay", barangayList);
		spnBarangay.setAdapter(adapter);
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

}
