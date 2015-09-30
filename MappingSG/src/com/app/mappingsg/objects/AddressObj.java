package com.app.mappingsg.objects;

import java.io.Serializable;

public class AddressObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3339844276269680823L;
	private CodeDescPair itemCodeDesc;
	private boolean itemSelected;
	
	public CodeDescPair getItem(){
		return itemCodeDesc;
	}
	
	public void setItem(CodeDescPair cdp){
		itemCodeDesc = cdp;
	}
	
	public boolean isSelected(){
		return itemSelected;
	}
	
	public void setSelected (boolean bool){
		itemSelected = bool;
	}
	
	public AddressObj(CodeDescPair cdp){
		itemCodeDesc = cdp;
		itemSelected = false;
	}
}
