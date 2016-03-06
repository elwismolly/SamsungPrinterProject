package com.samsung.printpdf.activity;

import java.io.File;

import com.samsung.printpdf.Config;
import com.samsung.printpdf.R;
import com.samsung.printpdf.net.UploadFile;
import com.samsung.printpdf.net.UploadFile.FailCallback;
import com.samsung.printpdf.net.UploadFile.SuccessCallback;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class PrintActivity extends Activity {

	File uploadFile = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_print);
		findViewById(R.id.btnPrint).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				print();
				
			}
		});
	}

	protected void print() {
		if(uploadFile!=null){
			UploadFile upload = new UploadFile(new SuccessCallback() {
				
				@Override
				public void onSuccess(String result) {
					// TODO Auto-generated method stub
					
				}
			}, new FailCallback() {
				
				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					
				}
			}, uploadFile, Config.username);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.print, menu);
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
