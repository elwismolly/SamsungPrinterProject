package com.samsung.printpdf;

import com.samsung.printpdf.activity.LoginActivity;
import com.samsung.printpdf.activity.PrintActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String token = Config.getCachedToken(this);
		if(token != null){
			Intent intent = new Intent(this,PrintActivity.class);
			intent.putExtra(Config.KEY_TOKEN, token);
			startActivity(intent);
		}else{
			startActivity(new Intent(this,LoginActivity.class));
		}
		
		finish();
	}


}
