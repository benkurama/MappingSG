package com.app.mappingsg;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

public class Vars extends Application {
	//public static ArrayList<UserObj> UserList;
	public static final int DEBUG_MODE = 1;
	
	@Override
	public void onCreate() {
		super.onCreate();
		//UserList = null;
	}
	// =========================================================================
	// TODO Global Objects
	// =========================================================================
	// -- HORSE PAGE MENU
//	public static ArrayList<UserObj> getHorseListObj() {
//	    return UserList;
//	}
//	public static void setHorseListObj(ArrayList<UserObj> horseList) {
//		Vars.UserList = horseList;
//	}
//	public static void setHorseListObjToNull() {
//		Vars.UserList.clear();
//	}
	public static void setSetup(Context core ,String msg){
		PreferenceManager.getDefaultSharedPreferences(core).edit().putString("setup", msg).commit();
	}
	public static String getSetup(Context core){
		return PreferenceManager.getDefaultSharedPreferences(core).getString("setup", "null");
	}
}
