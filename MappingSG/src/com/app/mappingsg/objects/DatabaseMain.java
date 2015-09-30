package com.app.mappingsg.objects;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseMain {
 // =========================================================================
 // TODO Variables
 // =========================================================================
	public static String MY_DATABASE_NAME = "DatabaseSG.db";
	public static int MY_DATABASE_VERSION = 1;
	//
	public SQLiteDatabase sqLiteDatabase;
	public SQLiteHelper sqLiteHelper;
	public Context context;
	//
	public static final String REGION_TABLE = "Region";
	public static final String REGION_PROVICE_TABLE ="Regvince";
	public static final String PROVINCE_TABLE = "Province";
	public static final String CITY_TABLE = "City";
	public static final String BARANGAY_TABLE = "Barangay";
	//
	public static final String COL_ID = "id";
	public static final String COL_CODE = "code";
	public static final String COL_DESC = "description";
	//
	public static final String REGION_TABLE_CREATE = "create table "+REGION_TABLE+" ("+COL_ID+" integer"+
	" primary key autoincrement, "+COL_CODE+" text not null, "+COL_DESC+" text not null)";
	
	public static final String REGVINCE_TABLE_CREATE = "create table "+REGION_PROVICE_TABLE+" ("+COL_ID+" integer"+
	" primary key autoincrement, "+COL_CODE+" text not null, "+COL_DESC+" text not null)";
	
	public static final String PROVINCE_TABLE_CREATE = "create table "+PROVINCE_TABLE+" ("+COL_ID+" integer"+
	" primary key autoincrement, "+COL_CODE+" text not null, "+COL_DESC+" text not null)";
	
	public static final String CITY_TABLE_CREATE = "create table "+CITY_TABLE+" ("+COL_ID+" integer"+
			" primary key autoincrement, "+COL_CODE+" text not null, "+COL_DESC+" text not null)";
	
	public static final String 	BARANGAY_TABLE_CREATE = "create table "+BARANGAY_TABLE+" ("+COL_ID+" integer"+
			" primary key autoincrement, "+COL_CODE+" text not null, "+COL_DESC+" text not null)";
 // =========================================================================
 // TODO Class Object for SQLiteHelper
 // =========================================================================
	public class SQLiteHelper extends SQLiteOpenHelper {
		public SQLiteHelper(Context context, String name,CursorFactory factory, int version) {
			super(context, name, factory, version);
		}
 // =========================================================================
 // TODO Activity Life Cycle
 // =========================================================================
	@Override
	public void onCreate(SQLiteDatabase db) {
		//Login Table
		db.execSQL(REGION_TABLE_CREATE);
		db.execSQL(REGVINCE_TABLE_CREATE);
		db.execSQL(PROVINCE_TABLE_CREATE);
		db.execSQL(CITY_TABLE_CREATE);
		db.execSQL(BARANGAY_TABLE_CREATE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}
	}	
 // =========================================================================
 // TODO Main Functions
 // =========================================================================	
	public DatabaseMain(Context c){
		context = c;
	}
	/// Default Functions
	public DatabaseMain openToRead() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MY_DATABASE_NAME, null, MY_DATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		return this;	
	}
	public DatabaseMain openToWrite() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MY_DATABASE_NAME, null,MY_DATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		return this;	
	}
	public void close(){
		sqLiteHelper.close();
	}
 // =========================================================================
 // TODO Implementation
 // =========================================================================
	 // =========================================================================
	 // TODO Selections
	public ArrayList<String> selectRegionCode(){
		
		ArrayList<String> codeList = new ArrayList<String>();
		
		String STATEMENT = "select "+COL_CODE+" from "+REGION_TABLE;
		
		Cursor cur = sqLiteDatabase.rawQuery(STATEMENT, null);
		
		if(cur.getCount() != 0){
			
			for(cur.moveToFirst();(!cur.isAfterLast());cur.moveToNext()){
				
				String codes = cur.getString(0);
				codeList.add(codes);
			}
		}
		
		return codeList;
	}
	// =========================================================================
	public ArrayList<CodeDescPair> selecAllRegions(){
		
		ArrayList<CodeDescPair> allRegions = new ArrayList<CodeDescPair>();
		
		Cursor cur = sqLiteDatabase.rawQuery("select * from "+REGION_TABLE, null);
		
		if(cur.getCount() != 0){
			
			for(cur.moveToFirst(); (!cur.isAfterLast()); cur.moveToNext()){
				
				CodeDescPair cdp = new CodeDescPair(cur.getString(1), cur.getString(2));
				allRegions.add(cdp);
			}
		}
		return allRegions;
	}
	// =========================================================================
	public ArrayList<String> selectRegVinceDesc(){
		//
		ArrayList<String> codeList = new ArrayList<String>();
		String STATEMENT = "select "+COL_DESC+" from "+REGION_PROVICE_TABLE;
		Cursor cur = sqLiteDatabase.rawQuery(STATEMENT, null);
		
		if(cur.getCount() != 0){
			
			for(cur.moveToFirst();(!cur.isAfterLast());cur.moveToNext()){
				String desc = cur.getString(0);
				codeList.add(desc);
			}
		}
		return codeList;
	}
	// =========================================================================
	public ArrayList<CodeDescPair> selectProvince(String code){
		 
		ArrayList<String> selectedProvinceCode = new ArrayList<String>();
		Cursor cur = sqLiteDatabase.rawQuery("select * from "+REGION_PROVICE_TABLE, null);
		
		if(cur.getCount() != 0){
			//
			for(cur.moveToFirst(); (!cur.isAfterLast()); cur.moveToNext()){
				//
				String RegCode = cur.getString(1);
				String RelCode = cur.getString(2);
				if(code.startsWith(RegCode)){
					//
					selectedProvinceCode.add(RelCode);
				}
			}
		}
		//
		ArrayList<CodeDescPair> ProvinceSelected = new ArrayList<CodeDescPair>();
		Cursor cursor = sqLiteDatabase.rawQuery("select * from "+PROVINCE_TABLE, null);
		
		if(cursor.getCount() != 0){
			//
			for(cursor.moveToFirst(); (!cursor.isAfterLast()); cursor.moveToNext()){
				// n
				String ProvCode = cursor.getString(1);
				String ProvDesc = cursor.getString(2);
				for(String selCode: selectedProvinceCode){
					//
					if(selCode.startsWith(ProvCode)){
						//
						ProvinceSelected.add(new CodeDescPair(ProvCode, ProvDesc));
					}
				}
			}
		}
		
		return ProvinceSelected;
	}
	
	public ArrayList<String> selectProvinceCode(){
		//
		ArrayList<String> provinceCodeList = new ArrayList<String>();
		Cursor cur = sqLiteDatabase.rawQuery("select * from "+PROVINCE_TABLE,null);
		
		if(cur.getCount() != 0){
			//
			for(cur.moveToFirst(); (!cur.isAfterLast()); cur.moveToNext()){
				//
				provinceCodeList.add(cur.getString(1));
			}
		}
		//
		return provinceCodeList;
	}
	// =========================================================================
	public ArrayList<CodeDescPair> selectCity(String code){
		//
		ArrayList<CodeDescPair> CitySelected = new ArrayList<CodeDescPair>();
		Cursor cur = sqLiteDatabase.rawQuery("select * from "+CITY_TABLE, null);
		
		if(cur.getCount() != 0){
			//
			for(cur.moveToFirst(); (!cur.isAfterLast()); cur.moveToNext()){
				//
				String cityCode = cur.getString(1);
				String cityDesc = cur.getString(2);
				if(cityCode.startsWith(code)){
					//
					CitySelected.add(new CodeDescPair(cityCode, cityDesc));
				}
			}
		}
		return CitySelected;
	}
	// =========================================================================
	public ArrayList<String> selectCityCode(){
		//
		ArrayList<String> cityCodeList = new ArrayList<String>();
		Cursor cur = sqLiteDatabase.rawQuery("select * from "+CITY_TABLE, null);
		
		if(cur.getCount() != 0){
			//
			for(cur.moveToFirst(); (!cur.isAfterLast()); cur.moveToNext()){
				//
				cityCodeList.add(cur.getString(1));
			}
		}
		
		return cityCodeList;
	}
	// =========================================================================
	public ArrayList<CodeDescPair> selectBarangay(String code){
		//
		ArrayList<CodeDescPair> BarangaySelected = new ArrayList<CodeDescPair>();
		Cursor cur = sqLiteDatabase.rawQuery("select * from "+BARANGAY_TABLE, null);
		
		if(cur.getCount() != 0){
			//
			for(cur.moveToFirst(); (!cur.isAfterLast()); cur.moveToNext()){
				//
				String barangayCode = cur.getString(1);
				String barangayDesc = cur.getString(2);
				if(barangayCode.startsWith(code)){
					//
					BarangaySelected.add(new CodeDescPair(barangayCode, barangayDesc));
				}
			}
		}
		return BarangaySelected;
	}
	// =========================================================================
	// TODO Insertion
	public void insertRegion(String code, String desc){
		
		sqLiteDatabase.insert(REGION_TABLE, null, intoContentValues(code, desc));
	}
	public void insertRegvince(String code, String desc){
		
		sqLiteDatabase.insert(REGION_PROVICE_TABLE, null, intoContentValues(code, desc));
	}
	public void insertAllIn(String code, String desc, String table){
		
		sqLiteDatabase.insert(table, null, intoContentValues(code, desc));
	}
	// =========================================================================
	// TODO Deletion
	public void clearTable(String table){
		sqLiteDatabase.delete(table, null, null);
	}
	// ---------------------------------------------------------------------
	public ContentValues intoContentValues(String code, String desc){
		
		ContentValues cv = new ContentValues();
		
		cv.put(COL_CODE, code);
		cv.put(COL_DESC, desc);
		
		return cv;
	}
}
