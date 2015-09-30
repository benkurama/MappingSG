package com.app.mappingsg.interfeis;

import java.util.ArrayList;


public interface AsyncResponse {
	
	void processFinish(String output);
	void setMax(int max);
	void updateProgress(int update);
	void changeMessage(String msg);
	
}
