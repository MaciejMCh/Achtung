package com.example.achtung;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PeerItem extends LinearLayout {

	private WifiP2pDevice mPeer;
	
	public PeerItem(Context context, WifiP2pDevice aPeer) {
		super(context);
		mPeer = aPeer;
	
		
		LayoutInflater inflater = (LayoutInflater) context
		           .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        inflater.inflate(R.layout.listitem_peer, this, true);
		
	}
	
	
	
	
	
	

}
