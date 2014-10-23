package com.example.achtung;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FragmentSinglePlayerGame extends Fragment {
	
	
	
	
	private View V;
	private Round mRound;
	private GameView gv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		V = inflater.inflate(R.layout.fragment_single_game, container,
				false);	
		
		
		getActivity().getActionBar().hide();
		getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		
		gv = new GameView(getActivity());
		mRound = new Round(2);
		gv.newRound(mRound);
		
		RelativeLayout rl = (RelativeLayout)V;
		
		int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(height, height);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		
		rl.addView(gv, params );
		
		gv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!mRound.isStarted()){
					mRound.start();					
				}else if(mRound.isFinished()){
					mRound = new Round(2);
					gv.clearTrace();
					gv.newRound(mRound);
				}
				
			}
		});
		
		
		V.findViewById(R.id.button1).setOnTouchListener(new View.OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if(action == MotionEvent.ACTION_DOWN){
					gv.updateTrace(((CountedPlayer)mRound.getPlayers().get(0)), 0);
					((CountedPlayer)mRound.getPlayers().get(0)).setState(CountedPlayer.PLAYER_LEFT);
					return true;
				}else if(action == MotionEvent.ACTION_UP){
					gv.updateTrace(((CountedPlayer)mRound.getPlayers().get(0)), 0);
					((CountedPlayer)mRound.getPlayers().get(0)).setState(CountedPlayer.PLAYER_FORWARD);
					return true;
				}				
				return false;
			}
		});
		V.findViewById(R.id.button2).setOnTouchListener(new View.OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if(action == MotionEvent.ACTION_DOWN){
					gv.updateTrace(((CountedPlayer)mRound.getPlayers().get(0)), 0);
					((CountedPlayer)mRound.getPlayers().get(0)).setState(CountedPlayer.PLAYER_RIGHT);
					return true;
				}else if(action == MotionEvent.ACTION_UP){
					gv.updateTrace(((CountedPlayer)mRound.getPlayers().get(0)), 0);
					((CountedPlayer)mRound.getPlayers().get(0)).setState(CountedPlayer.PLAYER_FORWARD);
					return true;
				}				
				return false;
			}
		});
		V.findViewById(R.id.button3).setOnTouchListener(new View.OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if(action == MotionEvent.ACTION_DOWN){
					gv.updateTrace(((CountedPlayer)mRound.getPlayers().get(1)), 1);
					((CountedPlayer)mRound.getPlayers().get(1)).setState(CountedPlayer.PLAYER_LEFT);
					return true;
				}else if(action == MotionEvent.ACTION_UP){
					gv.updateTrace(((CountedPlayer)mRound.getPlayers().get(1)), 1);
					((CountedPlayer)mRound.getPlayers().get(1)).setState(CountedPlayer.PLAYER_FORWARD);
					return true;
				}				
				return false;
			}
		});
		V.findViewById(R.id.button4).setOnTouchListener(new View.OnTouchListener() {			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if(action == MotionEvent.ACTION_DOWN){
					gv.updateTrace(((CountedPlayer)mRound.getPlayers().get(1)), 1);
					((CountedPlayer)mRound.getPlayers().get(1)).setState(CountedPlayer.PLAYER_RIGHT);
					return true;
				}else if(action == MotionEvent.ACTION_UP){
					gv.updateTrace(((CountedPlayer)mRound.getPlayers().get(1)), 1);
					((CountedPlayer)mRound.getPlayers().get(1)).setState(CountedPlayer.PLAYER_FORWARD);
					return true;
				}				
				return false;
			}
		});
		
		
		return V;	
	}
	
	
	
	
	
	
	
	

}
