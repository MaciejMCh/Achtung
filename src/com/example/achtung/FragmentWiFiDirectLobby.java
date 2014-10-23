package com.example.achtung;



import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;








import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
public class FragmentWiFiDirectLobby extends Fragment implements PeerListListener, ConnectionInfoListener {
	
	private View V;
	private WifiP2pManager mManager;
	private Channel mChannel;
	private WiFiDirectBroadcastReceiver2 receiver;
	private IntentFilter mIntentFilter;
	private WiFiDirectBroadcastReceiver2 mReceiver;
	private WifiP2pDevice mPeer;
	private InetAddress mHost;
	private ArrayList<String> mPeerList;


	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		V = inflater.inflate(R.layout.fragment_wifidirect_lobby, container,
				false);	
		WifiDirectLobbyController.getInstance().setFragment(this);
		
		mIntentFilter = new IntentFilter();
		mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        mManager = (WifiP2pManager) V.getContext().getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(V.getContext(), V.getContext().getMainLooper(), null);
        mReceiver = new WiFiDirectBroadcastReceiver2(mManager, mChannel, this);
		
		
        this.initButtons();
        
		return V;
	}
	
	
	
	private void initButtons() {
		((Button)V.findViewById(R.id.button_find_game)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		((Button)V.findViewById(R.id.button_host_game)).setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			V.findViewById(R.id.button_host_game).setVisibility(View.INVISIBLE);
			
			mManager.discoverPeers(mChannel, new ActionListener() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onFailure(int reason) {
					// TODO Auto-generated method stub
					
				}
			});
				
			}
		});
		((Button)V.findViewById(R.id.button_connect_peer)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				V.findViewById(R.id.button_connect_peer).setVisibility(View.INVISIBLE);
				WifiP2pConfig config = new WifiP2pConfig();
				config.deviceAddress = mPeer.deviceAddress;
				mManager.connect(mChannel, config, new ActionListener() {

				    @Override
				    public void onSuccess() {
				        //success logic
				    }

				    @Override
				    public void onFailure(int reason) {
				        //failure logic
				    }
				});


				
			}
		});
		((Button)V.findViewById(R.id.button_send_msg)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				Uri uri = data.getData();
//		        TextView statusText = (TextView) mContentView.findViewById(R.id.status_text);
//		        statusText.setText("Sending: " + uri);
//		        Log.d(WiFiDirectActivity.TAG, "Intent----------- " + uri);
		       
				
//				Uri uri = data.getData();
//		        TextView statusText = (TextView) mContentView.findViewById(R.id.status_text);
//		        statusText.setText("Sending: " + uri);
//		        Log.d(WiFiDirectActivity.TAG, "Intent----------- " + uri);
		        Intent serviceIntent = new Intent(getActivity(), FileTransferService.class);
		        serviceIntent.setAction(FileTransferService.ACTION_SEND_FILE);
		        serviceIntent.putExtra(FileTransferService.EXTRAS_FILE_PATH, "lmao");
		        serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_ADDRESS,
		                mHost.getHostAddress());
		        serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_PORT, 8988);
		        getActivity().startService(serviceIntent);
				
				
				
				
				
				
				
				
				
				
				
				
				
//				Intent intent = new Intent(getActivity(), TestTransferService.class);
//				intent.setAction(TestTransferService.ACTION_SEND_FILE);
//				
//				intent.putExtra(TestTransferService.EXTRAS_GROUP_OWNER_ADDRESS,
//		                mHost.getHostAddress());
//		        intent.putExtra(TestTransferService.EXTRAS_GROUP_OWNER_PORT, 8988);
//				
//				getActivity().startService(intent);
				
				
				
				
				
//				
//				Socket socket = new Socket();
//				try{
//					
//					int port = 8988;
//					socket.bind(null);
//				    socket.connect((new InetSocketAddress(mHost.getHostAddress(), port)), 500);
//				}catch(Exception e){
//					
//				}
//				finally{
//					if(socket!=null)
//						try {
//							socket.close();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//				}
				
			}
		});
		((Button)V.findViewById(R.id.button_hostgame)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mManager.discoverPeers(mChannel, new ActionListener() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onFailure(int reason) {
						// TODO Auto-generated method stub
						
					}
				});
				((TextView)V.findViewById(R.id.textView_waiting)).setText("Finding peers...");
				V.findViewById(R.id.button_hostgame).setVisibility(View.GONE);				
			}
		});
	}



	@Override
	public void onResume() {
	    super.onResume();
	    V.getContext().registerReceiver(mReceiver, mIntentFilter);
	}
	/* unregister the broadcast receiver */
	@Override
	public void onPause() {
	    super.onPause();
	    V.getContext().unregisterReceiver(mReceiver);
	}



	@Override
	public void onPeersAvailable(WifiP2pDeviceList peers) {
		V.findViewById(R.id.button_connect_peer).setVisibility(View.VISIBLE);
		
		
		
		ArrayList<WifiP2pDevice> peerList = new ArrayList<WifiP2pDevice>();
		peerList.addAll(peers.getDeviceList());
		
		if(mPeerList == null)mPeerList = new ArrayList<String>();
		if(mPeerList.isEmpty()){
			for(int i=0; i<=peerList.size()-1; i++){
				mPeerList.add(peerList.get(i).deviceName);
				PeerView pv = new PeerView(peerList.get(i), getActivity());
				((LinearLayout)V.findViewById(R.id.container_peers)).addView(pv.getView());
			}
		}
		for(int i=0; i<=peerList.size()-1; i++){
			if(!mPeerList.contains(peerList.get(i).deviceName)){
				mPeerList.add(peerList.get(i).deviceName);
				PeerView pv = new PeerView(peerList.get(i), getActivity());
				((LinearLayout)V.findViewById(R.id.container_peers)).addView(pv.getView());
			}
		}
		
//		mPeer = peerList.get(0);
//		MainActivity.log("mam peery "+mPeer.deviceName);
//		V.findViewById(R.id.container_wait_for_server).setVisibility(View.GONE);
//		PeerView pv = new PeerView(mPeer, getActivity());
//		((LinearLayout)V.findViewById(R.id.container_peers)).addView(pv.getView());
	}



	@Override
	public void onConnectionInfoAvailable(WifiP2pInfo info) {
		if (info.groupFormed && info.isGroupOwner) {
//            new FileServerAsyncTask(getActivity(), mContentView.findViewById(R.id.status_text))
//                    .execute();
			
			
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						ServerSocket serverSocket = new ServerSocket(8988);
						MainActivity.log("Server socket opened");
				        Socket client = serverSocket.accept();
				        MainActivity.log("Server socket connected");
				        
				        
				        
//				        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
//				        BufferedReader in = new BufferedReader(
//		                new InputStreamReader(client.getInputStream()));
//				        
//				        
//				        String inputLine;
//				        while ((inputLine = in.readLine()) != null) {
//				            
//				            MainActivity.log(inputLine);
//				            
//				        }
				        
				        DataInputStream in =
				                  new DataInputStream(client.getInputStream());
				        
				        DataOutputStream out =
				                 new DataOutputStream(client.getOutputStream());
				        
				        Controller.getInstance().startWifiDirectGameServer(in, out);
				        
//				        while(true){
//				        	MainActivity.log(in.readUTF());
//				        }
				        
				        
				        
					}catch(Exception e){
						
					}

				}
			}).start();
			
        } else if (info.groupFormed) {
            // The other device acts as the client. In this case, we enable the
            // get file button.
//            mContentView.findViewById(R.id.btn_start_client).setVisibility(View.VISIBLE);
//            ((TextView) mContentView.findViewById(R.id.status_text)).setText(getResources()
//                    .getString(R.string.client_text));
        	mHost = info.groupOwnerAddress;
        	V.findViewById(R.id.button_send_msg).setVisibility(View.VISIBLE);
        	
        	Intent serviceIntent = new Intent(getActivity(), FileTransferService.class);
	        serviceIntent.setAction(FileTransferService.ACTION_SEND_FILE);
	        serviceIntent.putExtra(FileTransferService.EXTRAS_FILE_PATH, "lmao");
	        serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_ADDRESS,
	                mHost.getHostAddress());
	        serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_PORT, 8988);
	        getActivity().startService(serviceIntent);
        	
        }
		
	}



	public void connectToPeer(WifiP2pDevice aPeer) {
		WifiP2pConfig config = new WifiP2pConfig();
		config.deviceAddress = aPeer.deviceAddress;
		mManager.connect(mChannel, config, new ActionListener() {

		    @Override
		    public void onSuccess() {
		        //success logic
		    }

		    @Override
		    public void onFailure(int reason) {
		        //failure logic
		    }
		});		
	}
	
	 
	
	
	

		
	

}
