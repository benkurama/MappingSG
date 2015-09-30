package com.app.mappingsg.objects;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.app.mappingsg.R;

public class CheckBoxArrayAdapter extends ArrayAdapter<AddressObj>{
	
	private final Activity core;
	private final ArrayList<AddressObj> provinceList;

	public CheckBoxArrayAdapter(Activity context, ArrayList<AddressObj> allItems) {
		super(context, R.layout.row_province_items, allItems);
		core = context;
		provinceList = allItems;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//return super.getView(position, convertView, parent);
		View view = null;
		
		if(convertView == null){
			
			LayoutInflater inflater = core.getLayoutInflater();
			
			view = inflater.inflate(R.layout.row_province_items, null);
			final CheckBox cb = (CheckBox)view.findViewById(R.id.cbRowProvince);
			cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					
					AddressObj item = (AddressObj)cb.getTag();
					item.setSelected(buttonView.isChecked());
				}
			});
			cb.setTag(provinceList.get(position));
			//
		}else {
			
			view = convertView;
			CheckBox cb = (CheckBox)view.findViewById(R.id.cbRowProvince);
			cb.setTag(provinceList.get(position));
		}
		
		CheckBox cb = (CheckBox)view.findViewById(R.id.cbRowProvince);
		cb.setText(provinceList.get(position).getItem().Description);
		cb.setChecked(provinceList.get(position).isSelected());
		
		return view;
	}

}
