package com.example.achtung;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class DataTransferService extends IntentService {

	public static final String ACTION_GAME_BEGIN = "begin game";
	public static final String EXTRAS_GROUP_OWNER_ADDRESS = "go_host";
	public static final String EXTRAS_GROUP_OWNER_PORT = "go_port";
	
	public DataTransferService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		if (intent.getAction().equals(ACTION_GAME_BEGIN)) {
			Toast.makeText(getApplicationContext(), "hurra", Toast.LENGTH_LONG);
		}
		
	}

}
