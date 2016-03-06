package com.samsung.printpdf.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import android.os.AsyncTask;
import android.telephony.SubscriptionManager.OnSubscriptionsChangedListener;

public class UploadFile {
	final static String SERVLET_URL = "samsungprinter.cloudapp.net/printServer/UploadFileServlet";
	public UploadFile( final SuccessCallback success, final FailCallback fail, final File uploadFile, final String userName){
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				URL url= null ; 				
				try {
					HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
					httpConn.setUseCaches(false);
			        httpConn.setDoOutput(true);
			        httpConn.setRequestMethod("POST");
			        httpConn.setRequestProperty("fileName", uploadFile.getName());
			        httpConn.setRequestProperty("username", userName);
			        
			        OutputStream outputStream = httpConn.getOutputStream();
			        FileInputStream inputStream = new FileInputStream(uploadFile);
			        byte[] buffer = new byte[1024];
			        int len = -1;
			        while((len = inputStream.read(buffer))!=-1){
			        	outputStream.write(buffer, 0, len);
			        }
			        outputStream.close();
			        inputStream.close();

			        int responseCode = httpConn.getResponseCode();
			        if (responseCode == HttpURLConnection.HTTP_OK) {
			            BufferedReader reader = new BufferedReader(new InputStreamReader(
			                    httpConn.getInputStream()));
			            String response = reader.readLine();
			            String result = "Server's response: " + response;
			            success.onSuccess(result);
			        } else {
			            System.out.println("Server returned non-OK code: " + responseCode);
			            fail.onFail();
			        }
			        
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}
		};
	}
	
	public static interface SuccessCallback{
		void onSuccess(String result);
	}
	public static interface FailCallback{
		void onFail();
	}
}
