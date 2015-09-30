package com.app.mappingsg;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class HomeContentVer10 extends Activity{
// =========================================================================
// TODO Variables
// =========================================================================	
	
// =========================================================================
// TODO Activity Life Cycle
// =========================================================================		
   /** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_home_ver10);
		
		SetupWidgets();
	}
// =========================================================================
// TODO Menu Option
// =========================================================================
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
// =========================================================================
// TODO onClick View
// =========================================================================

// =========================================================================
// TODO Main Functions
// =========================================================================
   public void SetupWidgets(){
   	   	
   }

// =========================================================================
// TODO Implementation
// =========================================================================

// =========================================================================
// TODO Sub Functions
// =========================================================================

// =========================================================================
// TODO Inner Class
// =========================================================================

// =========================================================================
// TODO Final Destination
}
