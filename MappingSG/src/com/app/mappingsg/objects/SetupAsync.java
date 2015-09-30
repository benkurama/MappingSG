package com.app.mappingsg.objects;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.ListView;

import com.app.mappingsg.R;
import com.app.mappingsg.interfeis.AsyncResponse;
import com.app.mappingsg.utils.XmlParser;

public class SetupAsync extends AsyncTask<Void, String, Void> {
	
	private AsyncResponse coreInterface;
	private ListView lvProviceList;
	private Context core;
	
	public SetupAsync(AsyncResponse coreAsyn, ListView lv, Context context){
		
		this.coreInterface = coreAsyn;
		this.lvProviceList = lv;
		this.core = context;
	}

	@Override
	protected Void doInBackground(Void... params) {

		CheckBoxArrayAdapter adapter = (CheckBoxArrayAdapter)lvProviceList.getAdapter();
		int itemCount = adapter.getCount();
		// ---
		this.publishProgress("Populate Region Part 1...");
		coreInterface.setMax(240);
		int inc = 0;
		//
		DatabaseMain db = new DatabaseMain(core);
		db.openToRead();
		// query for Region populate
		db.clearTable(DatabaseMain.REGION_TABLE);
		for(int x = 0; x < itemCount; x++){
			CheckBox cb = (CheckBox)adapter.getView(x, null, null).findViewById(R.id.cbRowProvince);
			
			if(cb.isChecked()){
				String code = adapter.getItem(x).getItem().Code;
				String desc = adapter.getItem(x).getItem().Description;
				db.insertRegion(code, desc);
			}
		}
		// query for Region Province Relationship
		ArrayList<String> codeRegionList = db.selectRegionCode();
		ArrayList<AddressObj> regvinceList = XmlParser.readFromXmlRegvince(core, R.xml.region_province_relation);
		ArrayList<AddressObj> selectedRegvinceList = new ArrayList<AddressObj>();
 		//
		for(String code : codeRegionList){
			
			for(AddressObj obj : regvinceList){
				
				if(obj.getItem().Code.contains(code)){
					selectedRegvinceList.add(obj);
				}
			}
		}
		coreInterface.updateProgress(inc +10);
		//
		db.clearTable(DatabaseMain.REGION_PROVICE_TABLE);
		this.publishProgress("Populate Region Part 2");
		
		for(AddressObj obj : selectedRegvinceList){
			
			String code = obj.getItem().Code;
			String desc = obj.getItem().Description;
			db.insertRegvince(code, desc);
			//
		} 
		coreInterface.updateProgress(inc +10);
		//query for Province populate
		this.publishProgress("Populate Province...");
		db.clearTable(DatabaseMain.PROVINCE_TABLE);
		ArrayList<String> descProvince = db.selectRegVinceDesc();
		XmlParser.readAndInsertFromXML(core, R.xml.province, DatabaseMain.PROVINCE_TABLE, db, descProvince);
		coreInterface.updateProgress(inc +=10);
		//query for City populate
		this.publishProgress("Populate City...");
		db.clearTable(DatabaseMain.CITY_TABLE);
		ArrayList<String> codeProvince = db.selectProvinceCode();
		XmlParser.readAndInsertFromXML(core, R.xml.city, DatabaseMain.CITY_TABLE, db, codeProvince);
		coreInterface.updateProgress(inc +=10);
		// Populate Barangay
		ArrayList<String> codeCity = db.selectCityCode(); 
		//A
		this.publishProgress("Populate Barangay A...");
		db.clearTable(DatabaseMain.BARANGAY_TABLE);
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_a, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//B = 60
		this.publishProgress("Populate Barangay B...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_b, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//C
		this.publishProgress("Populate Barangay C...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_c, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//D
		this.publishProgress("Populate Barangay D...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_d, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//E
		this.publishProgress("Populate Barangay E...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_e, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//F
		this.publishProgress("Populate Barangay F...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_f, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//G
		this.publishProgress("Populate Barangay G...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_g, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//H
		this.publishProgress("Populate Barangay H...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_h, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//I
		this.publishProgress("Populate Barangay I...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_i, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//J
		this.publishProgress("Populate Barangay J...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_j, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//K
		this.publishProgress("Populate Barangay K...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_k, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//L
		this.publishProgress("Populate Barangay L...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_l, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//M
		this.publishProgress("Populate Barangay M...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_m, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//N
		this.publishProgress("Populate Barangay N...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_n, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//O
		this.publishProgress("Populate Barangay O...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_o, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//P
		this.publishProgress("Populate Barangay P...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_p, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//Q
		this.publishProgress("Populate Barangay Q...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_q, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//R
		this.publishProgress("Populate Barangay R...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_r, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//S
		this.publishProgress("Populate Barangay S...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_s, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//T
		this.publishProgress("Populate Barangay T...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_t, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//U
		this.publishProgress("Populate Barangay U...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_u, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//VWXYZ
		this.publishProgress("Populate Barangay VWXYZ...");
		XmlParser.readAndInsertFromXML(core, R.xml.barangay_vwxyz, DatabaseMain.BARANGAY_TABLE, db, codeCity);
		coreInterface.updateProgress(inc +=10);
		//
		db.close();
		// --
		return null;
	}

	

	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		//super.onProgressUpdate(values);
		coreInterface.changeMessage(values[0]);
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		
		coreInterface.processFinish("fin");
	}

	
}
