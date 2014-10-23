package com.example.achtung;

import android.graphics.Canvas;
import android.graphics.Paint;

public class SimplePlayer extends Player {

	private float mX;
	private float mY;
	
	
	public SimplePlayer(float aX, float aY){
		mX = aX;
		mY = aY;
		
		new Thread(
				new Runnable() {
					
					@Override
					public void run() {
						while(true){
							mX+=0.01;
							mY+=0.01;
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				).start();
	}
	
	@Override
	float getX() {
		return mX;
	}

	@Override
	float getY() {
		return mY;
	}

	

}
