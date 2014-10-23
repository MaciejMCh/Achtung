package com.example.achtung;

import android.net.wifi.p2p.WifiP2pDevice;

public class WifiDirectLobbyController {
	
	private static WifiDirectLobbyController mInstance = null;
	
	private FragmentWiFiDirectLobby mFragment;
	
	public static WifiDirectLobbyController getInstance(){
		if(mInstance == null){
			mInstance = new WifiDirectLobbyController();
		}
		return mInstance;
	}
	
	public void setFragment(FragmentWiFiDirectLobby aFragment){
		this.mFragment = aFragment;
	}

	public void ConnectToPeer(WifiP2pDevice mPeer) {
		mFragment.connectToPeer(mPeer);
	}
	

}
