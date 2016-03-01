package com.samsung.samsungprinterclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		findViewById(R.id.btnGetFiles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                GetFiles();
            }
        });
	}

	protected void GetFiles() {
//		http://192.168.139.128:8080/
//		String servletUrl = "http://135.23.64.27:8080/SamsungServer/servlet/DownloadPDF";
		String servletUrl = "http://192.168.139.128:8080/SamsungServer/servlet/DownloadPDF";
		String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/";
		String fileName = "f2.zip";
		try{
			URL url = new URL(servletUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setUseCaches(false);
	        httpConn.setDoOutput(true);
	        httpConn.setRequestMethod("POST");
	        httpConn.connect();
	        InputStream in = httpConn.getInputStream();
	        
	        File file = new File(filePath+fileName);
	        FileOutputStream fileOut = new FileOutputStream(file);
	        byte[] buffer = new byte[1024];
	        int len = -1;
	        while((len = in.read(buffer))!=-1){
	        	fileOut.write(buffer, 0, len);
	        }
	        
	        int responseCode = httpConn.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            // reads server's response
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    httpConn.getInputStream()));
	            String response = reader.readLine();
	            System.out.println("Server's response: " + response);
	        } else {
	            System.out.println("Server returned non-OK code: " + responseCode);
	        }
	        
	        
			in.close();
			fileOut.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}

