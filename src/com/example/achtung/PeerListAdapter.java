package com.example.achtung;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.database.DataSetObserver;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class PeerListAdapter implements ListAdapter {

	private ArrayList<WifiP2pDevice> mPeers;
	private Context mContext;
	
	public PeerListAdapter(WifiP2pDeviceList peers, Context aContext) {
		this.mContext = aContext;

		mPeers = new ArrayList<WifiP2pDevice>();		
		mPeers.addAll(peers.getDeviceList());
		
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount() {
		return mPeers.size();
	}

	@Override
	public Object getItem(int position) {
		return mPeers.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater li = (LayoutInflater)this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = li.inflate(R.layout.listitem_peer, null);
		
		((TextView)view.findViewById(R.id.textView_peer_name)).setText(mPeers.get(position).deviceName);
		((TextView)view.findViewById(R.id.textView_peer_adress)).setText(mPeers.get(position).deviceAddress);
		
		
		return view;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return false;
	}

}
