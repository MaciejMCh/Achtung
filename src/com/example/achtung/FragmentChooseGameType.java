package com.example.achtung;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.*;

public class FragmentChooseGameType extends Fragment{
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_choose_game_type, container,
				false);	
		
		((Button)rootView.findViewById(R.id.button_wd)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				Controller.getInstance().WifiDirectChoosen();
			}
		});
		
		((Button)rootView.findViewById(R.id.button_sp)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Controller.getInstance().singlePlayerChoosen();
				
			}
		});
		
		return rootView;
	}

}
