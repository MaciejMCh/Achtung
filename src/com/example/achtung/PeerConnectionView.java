package com.example.achtung;

import java.net.InetAddress;

import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;

public interface PeerConnectionView {
	public void onPeersFound(WifiP2pDeviceList peers);
	public void onDeviceConnected(WifiP2pInfo info);
	public void imServer();


	public void imClient(InetAddress groupOwnerAddress);
}
