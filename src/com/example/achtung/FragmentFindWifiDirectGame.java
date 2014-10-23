package com.example.achtung;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.database.DataSetObserver;
import android.net.Uri;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ChannelListener;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceRequest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.*;
public class FragmentFindWifiDirectGame extends Fragment implements ChannelListener, PeerConnectionView
{
	
	

	

	
	private View V;
	private MainActivity mActivity;
	private ArrayList<WifiP2pDevice> mPeerList;
	private WifiP2pManager mManager;
	private Channel mChannel;
	private InetAddress mHost;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		V = inflater.inflate(R.layout.fragment_find_wifidirect_game, container,
				false);	
	
		
		
		
		IntentFilter intentFilter = new IntentFilter();
		intentFilter .addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
	    
	    mManager = (WifiP2pManager) V.getContext().getSystemService(Context.WIFI_P2P_SERVICE);
	    mChannel = mManager.initialize(V.getContext(), V.getContext().getMainLooper(), null);

	    WifiDirectReceiver receiver = new WifiDirectReceiver(mManager, mChannel, this);
        V.getContext().registerReceiver(receiver, intentFilter);

		mManager.discoverPeers(mChannel, new ActionListener() {
			
			@Override
			public void onSuccess() {
				Log.d("wifii","discovered");
				
			}
			
			@Override
			public void onFailure(int reason) {
				Log.d("wifii","not discovered");
			}
		});
		
		
		
		
		
		
		
		return V;
	}

	

	

	
        
        
       
        
  

	


	public void setActivity(MainActivity mainActivity) {
		this.mActivity = mainActivity;
	}















	@Override
	public void onChannelDisconnected() {
		// TODO Auto-generated method stub
		
	}















	@Override
	public void onPeersFound(WifiP2pDeviceList peers) {
		V.findViewById(R.id.container_progress).setVisibility(View.GONE);
		((ListView)V.findViewById(R.id.listView_peers)).setAdapter(new PeerListAdapter(peers, getActivity()));
		V.findViewById(R.id.listView_peers).setVisibility(View.VISIBLE);
		
		mPeerList = new ArrayList<WifiP2pDevice>();
		mPeerList.addAll(peers.getDeviceList());
	
		
		
//		LinearLayout sraka = (LinearLayout) V.findViewById(R.id.container_peers);
//		sraka.addView(new PeerItem(getActivity(), mPeerList.get(0)));
//		sraka.addView(new PeerItem(getActivity(), mPeerList.get(0)));
		
		((Button)V.findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				WifiP2pDevice device = mPeerList.get(0);
				WifiP2pConfig config = new WifiP2pConfig();
		        config.deviceAddress = device.deviceAddress;
		        config.wps.setup = WpsInfo.PBC;
		        
		        
		        mManager.connect(mChannel, config, new ActionListener() {

		            @Override
		            public void onSuccess() {
		            	Log.d("wifii","connected device");
		            }

		            @Override
		            public void onFailure(int reason) {
		            	Log.d("wifii","not connected device");
		            }
		        });

		        

			}
		});
		
		((Button)V.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
			
		

			@Override
			public void onClick(View v) {
				
				try{
					Socket socket = new Socket();

					socket.bind(null);
					
//					String host = aHost;
					int port = 8888;
					
					
					MainActivity.log("klient laczy z serwerem: "+mHost.getHostAddress().toString());
				    socket.connect((new InetSocketAddress(mHost.getHostAddress(), port)), 500);

				    MainActivity.log("klient polaczyl");	
				    Toast.makeText(getActivity(), "klient laczy", Toast.LENGTH_LONG).show(); 
				    
					
				    
					}catch(Exception e){
						
					}
				
			}
		});
		
	}















	@Override
	public void onDeviceConnected(WifiP2pInfo info) {
		Log.d("wifii",info.isGroupOwner+"");
		
//		Intent intent = new Intent(getActivity(), MyIntent.class);
//		intent.setAction("tescik");
//		getActivity().startService(intent);
		
	}















	@Override
	public void imServer() {
//		Toast.makeText(getActivity(), "jeste serwere", Toast.LENGTH_LONG).show();
		MainActivity.log("jeste serwere");
		new ServerAsyncTask().execute();
		
//		new Thread(new Runnable(){
//
//			@Override
//			public void run() {
//				MainActivity.log("Serwer laczy");
//				try{
//					ServerSocket serverSocket;
//					
//					serverSocket = new ServerSocket(8888);
//					MainActivity.log("Serwer: socket opened "+serverSocket.getInetAddress().toString());
//					Socket client = serverSocket.accept();
//					
//					MainActivity.log("Serwer: socket connected");
//					
//					serverSocket.close();
//				}catch(Exception e){
//					
//				}
//			}
//			
//		}).start();
		
	}















	@Override
	public void imClient(InetAddress aHost) {
//		Toast.makeText(getActivity(), "jeste kliente", Toast.LENGTH_LONG).show();
		MainActivity.log("jeste kliente");
		mHost = aHost;
		
	    
	}
	
	
	
	
	
	
	
	public static class ServerAsyncTask extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			
			
			MainActivity.log("serwer startuje");
			
			try {
				ServerSocket serverSocket;
				
				serverSocket = new ServerSocket(8888);
				Socket client = serverSocket.accept();
				
				MainActivity.log("Serwer polaczyl");
				
				serverSocket.close();
				
				
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
			
			return null;
		}
		
	}







	
	
	
	
	

	

}


