package com.samsung.printpdf;

import android.content.Context;

public class Config {
	
	public static final String KEY_TOKEN = "token";
	public static final String APP_ID = "com.samsung.printpdf";
	public static String username="";
	
	
	//token is like session to store login information	
	public static void cacheToken(Context context, String token){
		
	}
	
	public static String getCachedToken(Context context){
		
		return null;
	}

}
