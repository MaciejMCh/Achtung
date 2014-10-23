package com.example.achtung;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import android.widget.RelativeLayout.LayoutParams;

public class PeerView {
	
	private RelativeLayout mView;
	private WifiP2pDevice mPeer;
	private Context mContext;
	
	public PeerView(WifiP2pDevice aPeer, Context aContext){
		mContext = aContext;
		mPeer = aPeer;
		LayoutInflater li = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mView = (RelativeLayout) li.inflate(R.layout.listitem_peer, null);
		((TextView)mView.findViewById(R.id.textView_peer_name)).setText(mPeer.deviceName);
		((TextView)mView.findViewById(R.id.textView_peer_adress)).setText(mPeer.deviceAddress);
		
		((Button)mView.findViewById(R.id.button_connect_to_peer)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int size = mView.findViewById(R.id.button_connect_to_peer).getHeight();
				mView.findViewById(R.id.button_connect_to_peer).setVisibility(View.GONE);
				ProgressBar pb = new ProgressBar(mContext);
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(size, size);
				lp.addRule(RelativeLayout.CENTER_VERTICAL);
				lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				mView.addView(pb,lp);
				WifiDirectLobbyController.getInstance().ConnectToPeer(mPeer);
			}
		});
	}

	public View getView() {
		return mView;
	}
	
}
