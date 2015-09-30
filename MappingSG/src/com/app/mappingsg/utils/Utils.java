package com.app.mappingsg.utils;

import java.util.ArrayList;

import com.app.mappingsg.objects.CodeDescPair;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Utils {

	public static void callToast(Context core, String msg){
		
		Toast.makeText(core, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static ArrayAdapter<CodeDescPair> populateSpinner(Context core, String msg, ArrayList<CodeDescPair> codeDescList){
		//
		ArrayAdapter<CodeDescPair> adapter = new ArrayAdapter<CodeDescPair>(core, 
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//
		adapter.add(new CodeDescPair("0", msg));
		//
		for(CodeDescPair list: codeDescList){
			//
			adapter.add(list);
		}
		
		return adapter;
	}
}
