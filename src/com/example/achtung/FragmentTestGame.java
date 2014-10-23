package com.example.achtung;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.spec.MGF1ParameterSpec;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import android.widget.*;
import android.widget.RelativeLayout.LayoutParams;
public class FragmentTestGame extends Fragment {

	
	private View V;

	private Round mRound;
	private CountedPlayer mPlayer;
	private GameView gameView;

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		V = inflater.inflate(R.layout.fragment_wifi_direct_client, container,
				false);	
		getActivity().getActionBar().hide();
		getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Log.d("pacz", V.getHeight()+"h");
		
		int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		
		RelativeLayout.LayoutParams params = new LayoutParams(height, height);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		
		gameView = new GameView(getActivity());
		
		mRound = new Round(2);
//		mRound.start();
		mPlayer = (CountedPlayer)mRound.getPlayers().get(0);
		gameView.newRound(mRound);
		((RelativeLayout)V).addView(gameView, params);
		
		
		
//		((ImageView)V.findViewById(R.id.imageView1)).setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(mPlayer.getState()!=CountedPlayer.PLAYER_DEAD)mPlayer.setState(CountedPlayer.PLAYER_LEFT);
//			}
//		});
//		((ImageView)V.findViewById(R.id.imageView2)).setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(mPlayer.getState()!=CountedPlayer.PLAYER_DEAD)mPlayer.setState(CountedPlayer.PLAYER_RIGHT);
//			}
//		});
		
		((ImageView)V.findViewById(R.id.imageView1)).setOnTouchListener(new View.OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if(action == MotionEvent.ACTION_DOWN){
					if(mPlayer.getState()!=CountedPlayer.PLAYER_DEAD){
						gameView.updateTrace(mPlayer,0);
						mPlayer.setState(CountedPlayer.PLAYER_LEFT);
					}
				}else if(action == MotionEvent.ACTION_UP){
					if(mPlayer.getState()!=CountedPlayer.PLAYER_DEAD){
						gameView.updateTrace(mPlayer,0);
						mPlayer.setState(CountedPlayer.PLAYER_FORWARD);
					}
				}
				
				return true;
			}
		});		
		((ImageView)V.findViewById(R.id.imageView2)).setOnTouchListener(new View.OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if(action == MotionEvent.ACTION_DOWN){
					if(mPlayer.getState()!=CountedPlayer.PLAYER_DEAD){
						gameView.updateTrace(mPlayer,0);
						mPlayer.setState(CountedPlayer.PLAYER_RIGHT);
					}
				}else if(action == MotionEvent.ACTION_UP){
					if(mPlayer.getState()!=CountedPlayer.PLAYER_DEAD){
						gameView.updateTrace(mPlayer,0);
						mPlayer.setState(CountedPlayer.PLAYER_FORWARD);
					}
				}
				
				return true;
			}
		});
		
		
		
		
		
		mRound.start();
		
		return V;
	}
	
	
	
	
	
	





//	public void setSocketEndPoints(DataInputStream in, DataOutputStream out) {
//		this.mIn = in;
//		this.mOut = out;
//		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while(true){
//					try {
//						String msg = mIn.readUTF();
//						MainActivity.log(msg);
//						if(msg.equals(ConnectionMsg.MSG_START_ROUND))mRound.start();	
//						if(msg.startsWith("round")){
//							mRound = new Round(msg.substring(7, msg.length()-2));
//							gameView.newRound(mRound);
//						}
//						if(msg.equals(ConnectionMsg.MSG_LEFT))((CountedPlayer)mRound.getPlayers().get(0)).setState(CountedPlayer.PLAYER_LEFT);
//						if(msg.equals(ConnectionMsg.MSG_RIGHT))((CountedPlayer)mRound.getPlayers().get(0)).setState(CountedPlayer.PLAYER_RIGHT);
//						if(msg.equals(ConnectionMsg.MSG_FORWARD))((CountedPlayer)mRound.getPlayers().get(0)).setState(CountedPlayer.PLAYER_FORWARD);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					
//				}
//				
//				
//			}
//		}).start();
//	}

}
