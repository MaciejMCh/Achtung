package com.example.achtung;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;

import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.util.Log;

public class Controller {
	private static Controller mInstance;
	
	private Round mCurrentRound;

	private MainActivity mActivity;
	
	public static Controller getInstance(){
		if(mInstance == null){
			mInstance = new Controller();
		}
		return mInstance;
	}
	
	public void newRound(Round aRound){
		mCurrentRound = aRound;
	}
	
	public Round getCurrentRound(){
		return mCurrentRound;
	}

	public void WifiDirectChoosen() {
		mActivity.FindWifiDirectGame();		
	}
	
	public void setActivity(MainActivity aActivity){
		this.mActivity = aActivity;
	}

	public void startWifiDirectGameClient(DataInputStream in, DataOutputStream out) {
		Log.d("leci", "kontroler");
		mActivity.startWifiDirectGameClient(in, out);
		
	}

	public void startWifiDirectGameServer(DataInputStream in, DataOutputStream out) {
		mActivity.startWifiDirectGameServer(in, out);
	}

	public void startTestGame() {
		// TODO Auto-generated method stub
		
	}

	public void singlePlayerChoosen() {
		mActivity.startSinglePlayerGame();
	}

	

	

	

	
}
