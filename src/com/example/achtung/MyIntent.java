package com.example.achtung;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntent extends IntentService {

	public MyIntent(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("wifii","poiszlooo");
	}

}
