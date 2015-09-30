package com.app.mappingsg.utils;

import com.app.mappingsg.HomeContent;
import com.app.mappingsg.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class StreetLongPress implements OnLongClickListener, ActionMode.Callback{

	private Activity core;
	private ActionMode actionmode;
	private EditText edittext;
	
	public StreetLongPress(Activity core, EditText edittext){
		this.core = core;
		this.edittext = edittext;
	}
	
	@Override
	public boolean onLongClick(View v) {
		
		//Toast.makeText(core, "Long Press Enabled", Toast.LENGTH_LONG).show();
		if(actionmode == null){
			actionmode = core.startActionMode(this);
		}
		
		return true;
	}

	@SuppressLint("DefaultLocale")
	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.cap:
			
			String txt = edittext.getText().toString();
			txt = txt.toUpperCase();
			edittext.setText(txt);
			//
			actionmode.finish();
			break;
		}
		
		return true;
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		// TODO Auto-generated method stub
		
		MenuInflater inflater = core.getMenuInflater();
		inflater.inflate(R.menu.action_mode, menu);
		mode.setTitle("Action Mode");
		return true;
	}

	@Override
	public void onDestroyActionMode(ActionMode arg0) {
		// TODO Auto-generated method stub
		actionmode = null;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
