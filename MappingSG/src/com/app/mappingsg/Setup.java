package com.app.mappingsg;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.app.mappingsg.interfeis.AsyncResponse;
import com.app.mappingsg.objects.AddressObj;
import com.app.mappingsg.objects.CheckBoxArrayAdapter;
import com.app.mappingsg.objects.SetupAsync;
import com.app.mappingsg.utils.XmlParser;

public class Setup extends Activity implements AsyncResponse{
// =========================================================================
// TODO Variables
// =========================================================================	
	private Button btnSetupNow;
	private ListView lvSetupProvince;
	
	private ProgressDialog pdProgress;
// =========================================================================
// TODO Activity Life Cycle
// =========================================================================		
   /** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
		
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
	   
	   String pref = Vars.getSetup(this);
	   // check if Setup is done
	   if(!pref.equals("null")){
		   
		   if(Build.VERSION.SDK_INT < 11){
			   startActivity(new Intent(this, HomeContentVer10.class));
		   } else {
			   startActivity(new Intent(this, HomeContent.class));

		   }
		   finish();
	   } 
   	   	
	   btnSetupNow = (Button)findViewById(R.id.btnSetupNow);
	   btnSetupNow.setOnClickListener(onSetup);
	   
	   lvSetupProvince = (ListView)findViewById(R.id.lvSetupProvince);
	   
	   ArrayList<AddressObj> provinceList = XmlParser.readFromXml(this, R.xml.region);

	   CheckBoxArrayAdapter adapter = new CheckBoxArrayAdapter(this, provinceList);
	   
	   lvSetupProvince.setAdapter(adapter);
   }
   
   public void prepareSetup(){
	   
	   pdProgress =  new ProgressDialog(this);
	   pdProgress.setMessage("Setup begin..");
	   pdProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	   pdProgress.setIndeterminate(false);
	   pdProgress.setCancelable(false);
	   pdProgress.show();
	   
		new SetupAsync(this, lvSetupProvince, this).execute();
   }
// =========================================================================
// TODO Implementation
// =========================================================================
	private OnClickListener onSetup = new OnClickListener() {
		@Override
		public void onClick(View v) {
			prepareSetup();
		}
	};

	@Override
	public void processFinish(String output) {

		if (output.equals("fin")) {

			pdProgress.dismiss();
			Vars.setSetup(this, "APPROVE");
			startActivity(new Intent(getBaseContext(), HomeContent.class));
		}
	}
	@Override
	public void setMax(int max) {
		pdProgress.setMax(max);
	}
	@Override
	public void updateProgress(int update) {
		pdProgress.setProgress(update);
	}
	@Override
	public void changeMessage(String msg) {
		pdProgress.setMessage(msg);
	}
// =========================================================================
// TODO Sub Functions
// =========================================================================

// =========================================================================
// TODO Inner Class
// =========================================================================
	
// =========================================================================
// TODO Final Destination
}
