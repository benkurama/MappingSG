package com.app.mappingsg.objects;

import java.io.Serializable;

public class CodeDescPair implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6193689928176833299L;
	public String Code;
	public String Description;
	
	public CodeDescPair(String code, String description) {
		super();
		Code = code;
		Description = description;
	}

	@Override
	public String toString() {
		
		return Description;
	}
}
