package com.nzt.gmaps;

import java.util.Properties;

public class GMapsInit {

	public static String KEY = "AIzaSyCgdHanyklEnIMsnejdcoZtkRXKgCvvX7Y";

	
	public static void init() {
	    Properties propertiesSystem = new Properties(System.getProperties());
        propertiesSystem.put("http.proxyHost", "officeproxy.it.int");
        propertiesSystem.put("http.proxyPort", String.valueOf(3128));
	}
	
	public static String getGMapKey() {
		return KEY;
	}

}
