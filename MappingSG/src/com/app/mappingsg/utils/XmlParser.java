package com.app.mappingsg.utils;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import com.app.mappingsg.interfeis.AsyncResponse;
import com.app.mappingsg.objects.AddressObj;
import com.app.mappingsg.objects.CodeDescPair;
import com.app.mappingsg.objects.DatabaseMain;

public class XmlParser {
	 // =========================================================================
 	 // TODO Variables
 	 // =========================================================================
	public static ArrayList<AddressObj> readFromXml(Context context, int resID){
		
		ArrayList<AddressObj> regionList = new ArrayList<AddressObj>();
		
		try{
			Resources res = context.getResources();
			XmlResourceParser xpp = res.getXml(resID);
			int iEvent;
			String sCurrNode = "";
			String sCode = "";
			String sValue = "";
			//
			xpp.next();
			iEvent = xpp.getEventType();
			//
			while(iEvent != XmlPullParser.END_DOCUMENT){
				//
				boolean isInsert = false;
				//
				if(iEvent == XmlPullParser.START_TAG){
					// 
					sCurrNode = xpp.getName();
					if(sCurrNode.equals("string")){
						sCode = xpp.getAttributeValue(0);
					}
				} else if(iEvent == XmlPullParser.TEXT){
					//
					if(sCurrNode.equals("string")){
						sValue = xpp.getText();
						//
						isInsert = true;
						//
					}
				}
				//
				if(isInsert){
					//
					regionList.add(new AddressObj(new CodeDescPair(sCode, sValue)));
					//
					isInsert = false;
				}
				//
				xpp.next();
				iEvent = xpp.getEventType();
			}
			//
		}catch(Exception ex){
			
		}
		return regionList;
	}

	public static ArrayList<AddressObj> readFromXmlRegvince(Context context, int resID){
		
		ArrayList<AddressObj> regvinceList = new ArrayList<AddressObj>();
		
		try{
			Resources res = context.getResources();
			XmlResourceParser xpp = res.getXml(resID);
			int iEvent;
			String sCurrNode = "";
			String sCode = "";
			String sValue = "";
			//
			xpp.next();
			iEvent = xpp.getEventType();
			//
			while(iEvent != XmlPullParser.END_DOCUMENT){
				//
				boolean isInsert = false;
				
				if(iEvent == XmlPullParser.START_TAG){
					// 
					sCurrNode = xpp.getName();
					if(sCurrNode.equals("string")){
						sCode = xpp.getAttributeValue(0);
					}
				} else if(iEvent == XmlPullParser.TEXT){
					
					if(sCurrNode.equals("string")){
						sValue = xpp.getText();
						//
						isInsert = true;
						//
					}
				}
				//
				if(isInsert){
					//
					regvinceList.add(new AddressObj(new CodeDescPair(sCode, sValue)));
					//
					isInsert = false;
				}
				//
				xpp.next();
				iEvent = xpp.getEventType();
			}
			//
		}catch(Exception ex){
			
		}
		return regvinceList;
	}
	
	public static void readAndInsertFromXML(Context context, int resID, String table, DatabaseMain db, ArrayList<String> compare){
		
		try{
			Resources res = context.getResources();
			XmlResourceParser xpp = res.getXml(resID);
			int iEvent;
			String sCurrNode = "";
			String sCode = "";
			String sValue = "";
			//
			xpp.next();
			iEvent = xpp.getEventType();
			//
			while(iEvent != XmlPullParser.END_DOCUMENT){
				//
				boolean isInsert = false;
				//
				if(iEvent == XmlPullParser.START_TAG){
					// 
					sCurrNode = xpp.getName();
					if(sCurrNode.equals("string")){
						sCode = xpp.getAttributeValue(0);
					}
				} else if(iEvent == XmlPullParser.TEXT){
					//
					if(sCurrNode.equals("string")){
						sValue = xpp.getText();
						//
						isInsert = true;
						//
					}
				}
				//
				if(isInsert){
					//
					boolean found = false;
					for(String code:compare){
						//
						if(sCode.startsWith(code)){
							//
							found = true;
							break;
						}
					}
					if(found){
						db.insertAllIn(sCode, sValue, table);
					}
					//
					isInsert = false;
				}
				//
				xpp.next();
				iEvent = xpp.getEventType();
			}
			
			//
		}catch(Exception ex){
			
		}
	}
}
