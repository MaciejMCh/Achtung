package com.example.achtung;



import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	

	public static final String TAG = "baza";
	public FragmentFindWifiDirectGame fragmentFindWifiDirectGame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		instance = this;
		
		Controller.getInstance().setActivity(this);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new FragmentChooseGameType()).commit();
		}
		
		
		
//		this.startWifiDirectGameClient(null, null);
//		Controller.getInstance().startTestGame();
//		this.startTestGame();
	}

	private void startTestGame() {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, new FragmentTestGame()).addToBackStack("c").commit();
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

	public void FindWifiDirectGame() {
	 	
	 	
//		IntentFilter intentFilter = new IntentFilter();
//		intentFilter .addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
//	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
//	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
//	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
//	    
//	    WifiP2pManager manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
//	    Channel channel = manager.initialize(this, getMainLooper(), null);
//
//	    WifiDirectReceiver receiver = new WifiDirectReceiver(manager, channel);
//        registerReceiver(receiver, intentFilter);
//
//		manager.discoverPeers(channel, new ActionListener() {
//			
//			@Override
//			public void onSuccess() {
//				Log.d("wifii","discovered");
//				
//			}
//			
//			@Override
//			public void onFailure(int reason) {
//				Log.d("wifii","not discovered");
//			}
//		});
		
		
		
		
		
		
		
		
		
		
//	 	fragmentFindWifiDirectGame = new FragmentFindWifiDirectGame();
//	 	fragmentFindWifiDirectGame.setActivity(this);
//		FragmentTransaction ft = getFragmentManager().beginTransaction();
//		ft.replace(R.id.container, fragmentFindWifiDirectGame).addToBackStack("s").commit();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, new FragmentWiFiDirectLobby()).addToBackStack("s").commit();
	}

	private void getWifiDirectDevicesList() {
		
	}

	public void setIsWifiP2pEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void resetData() {
		// TODO Auto-generated method stub
		
	}

	public static void log(final String msg){		
		instance.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				Toast.makeText(instance, msg, Toast.LENGTH_LONG).show();
			}
			
		});
		
	}
	
	static MainActivity instance = null;

	public void startWifiDirectGameClient(DataInputStream in, DataOutputStream out) {
		
	Log.d("leci", "activity");
	FragmentWifiDirectClient fragment = new FragmentWifiDirectClient();
	fragment.setSocketEndPoints(in, out);
	FragmentTransaction ft = getFragmentManager().beginTransaction();
	ft.replace(R.id.container, fragment).addToBackStack("c").commit();
			
		
	}

	public void startWifiDirectGameServer(DataInputStream in, DataOutputStream out) {
		FragmentWifiDirectHost fragment = new FragmentWifiDirectHost();
		fragment.setSocketEndPoints(in, out);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, fragment).addToBackStack("c").commit();		
	}

	public void startSinglePlayerGame() {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, new FragmentSinglePlayerGame()).addToBackStack("s").commit();
	}

	

	

}