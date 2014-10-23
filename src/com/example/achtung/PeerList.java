package com.example.achtung;

import java.util.ArrayList;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.widget.LinearLayout;

public class PeerList extends LinearLayout {

	private ArrayList<WifiP2pDevice> mPeers;
	
	public PeerList(Context context) {
		super(context);
		mPeers = new ArrayList<WifiP2pDevice>();
		this.setOrientation(LinearLayout.VERTICAL);
	}
	
	public void updatePeers(ArrayList<WifiP2pDevice> aPeersList){
		if(mPeers.isEmpty()){
			mPeers.addAll(aPeersList);
		}else{
			
		
			for(int i=0; i<=aPeersList.size()-1; i++){
				if(mPeers.contains(aPeersList.get(i))){
					mPeers.add(aPeersList.get(i));
				}
			}
			this.removeAllViews();
			for(int i=0; i<=mPeers.size()-1; i++){
				this.addView(new PeerItem(getContext(), mPeers.get(i)));
			}
		}
	}

}
