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
public class FragmentWifiDirectHost extends Fragment {

	
	private View V;
	private DataInputStream mIn;
	private DataOutputStream mOut;
	private Round mRound;
	private CountedPlayer mPlayer;
	private GameView gameView;

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		V = inflater.inflate(R.layout.fragment_wifi_direct_host, container,
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
		try {
			mOut.writeUTF(mRound.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mPlayer = (CountedPlayer)mRound.getPlayers().get(0);
		gameView.newRound(mRound);
		((RelativeLayout)V).addView(gameView, params);
		
		
		((ImageView)V.findViewById(R.id.imageView1)).setOnTouchListener(new View.OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if(action == MotionEvent.ACTION_DOWN){
					if(mPlayer.getState()!=CountedPlayer.PLAYER_DEAD){
						gameView.updateTrace(mPlayer,0);
						mPlayer.setState(CountedPlayer.PLAYER_LEFT);
						try {
							mOut.writeUTF(ConnectionMsg.MSG_LEFT+mPlayer.getPosition());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(action == MotionEvent.ACTION_UP){
					gameView.updateTrace(mPlayer,0);
					if(mPlayer.getState()!=CountedPlayer.PLAYER_DEAD){
						mPlayer.setState(CountedPlayer.PLAYER_FORWARD);
						try {
							mOut.writeUTF(ConnectionMsg.MSG_FORWARD+mPlayer.getPosition());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
						try {
							mOut.writeUTF(ConnectionMsg.MSG_RIGHT+mPlayer.getPosition());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(action == MotionEvent.ACTION_UP){
					if(mPlayer.getState()!=CountedPlayer.PLAYER_DEAD){
						gameView.updateTrace(mPlayer,0);
						mPlayer.setState(CountedPlayer.PLAYER_FORWARD);
						try {
							mOut.writeUTF(ConnectionMsg.MSG_FORWARD+mPlayer.getPosition());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				return true;
			}
		});
		
		((Button)V.findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					mOut.writeUTF(ConnectionMsg.MSG_START_ROUND);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mRound.start();
				
			}
		});
		
		
		
		
		
		
		return V;
	}
	
	
	
	
	












	public void setSocketEndPoints(DataInputStream in, DataOutputStream out) {
		this.mIn = in;
		this.mOut = out;
	}

}
