package com.example.achtung;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WifiDirectReceiver extends BroadcastReceiver {

	private WifiP2pManager mManager;
	private Channel mChannel;
	private PeerConnectionView mView;
	

	public WifiDirectReceiver(WifiP2pManager manager, Channel channel, PeerConnectionView aView) {
		this.mManager = manager;
		this.mChannel = channel;
		this.mView = aView;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Determine if Wifi P2P mode is enabled or not, alert
            // the Activity.
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
            	Log.d("wifii","connected");
            } else {
            	Log.d("wifii","disconnected");
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            // The peer list has changed!  We should probably do something about
            // that.
        	
        	Log.d("wifii","peer list changed");
        	mManager.requestPeers(mChannel, new PeerListListener() {
				
				@Override
				public void onPeersAvailable(WifiP2pDeviceList peers) {
					mView.onPeersFound(peers);
				}
			});

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            // Connection state changed!  We should probably do something about
            // that.
        	
        	Log.d("wifii","connection state changed");
        	
        	NetworkInfo networkInfo = (NetworkInfo) intent
                    .getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

        	if (networkInfo.isConnected()) {

                // We are connected with the other device, request connection
                // info to find group owner IP

                mManager.requestConnectionInfo(mChannel, new WifiP2pManager.ConnectionInfoListener() {
					
					@Override
					public void onConnectionInfoAvailable(WifiP2pInfo info) {
						
						
						if (info.groupFormed && info.isGroupOwner) {
				           mView.imServer(); 
				        } else if (info.groupFormed) {
				           mView.imClient(info.groupOwnerAddress);
				        }
						
						
						
						
//						mView.onDeviceConnected(info);
					}
				});
            }


        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
//            DeviceListFragment fragment = (DeviceListFragment) activity.getFragmentManager()
//                    .findFragmentById(R.id.frag_list);
//            fragment.updateThisDevice((WifiP2pDevice) intent.getParcelableExtra(
//                    WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));

        }

	}

}
