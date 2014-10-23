package com.example.achtung;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

public class Round {
	
	private static int SPACE_COLOR=-16777216;
	
	private ArrayList<Player> mPlayers;
	private boolean mStarted;
	private boolean mFinished;
	
	
	public Round(int playersCount){
		mStarted = false;
		mPlayers = new ArrayList<Player>();
		Random random = new Random();
//		mPlayers.add(new CountedPlayer(random.nextFloat(), random.nextFloat(), random.nextFloat()));
		for(int i=0; i<=playersCount-1; i++){
			mPlayers.add(new CountedPlayer(random.nextFloat(), random.nextFloat(), random.nextFloat()));			
		}
	}
	
	public Round(String data) {
		mFinished = false;
		mStarted = false;
		mPlayers = new ArrayList<Player>();
		String[] playerData = data.split(",");
		for(int i=0; i<=playerData.length-1; i++){
			mPlayers.add(new CountedPlayer(playerData[i].substring(8, playerData[i].length()-1)));
		}
		Log.d("kat", data);
	}

	public ArrayList<Player> getPlayers(){
		return mPlayers;
	}

	public void checkAlive(Bitmap aBitmap, float aSize, GameView gameView) {
		
		for(int i=0; i<=mPlayers.size()-1; i++){
			
			if((((CountedPlayer)mPlayers.get(i)).getX()<0.1f)||(((CountedPlayer)mPlayers.get(i)).getX()>0.9f)){
				((CountedPlayer)mPlayers.get(i)).setX(0.5f);
			}
			if((((CountedPlayer)mPlayers.get(i)).getY()<0.1f)||(((CountedPlayer)mPlayers.get(i)).getY()>0.9f)){
				((CountedPlayer)mPlayers.get(i)).setY(0.5f);
			}
			
			float[] pixels = ((CountedPlayer)mPlayers.get(i)).getSkull(aSize);
			Log.d("pacz","Sss "+aBitmap.getPixel((int)pixels[0], (int)pixels[1])+","+aBitmap.getPixel((int)pixels[2], (int)pixels[3])+","+aBitmap.getPixel((int)pixels[4], (int)pixels[5])+",");
			if(
					(aBitmap.getPixel((int)pixels[0], (int)pixels[1])!=SPACE_COLOR)||
					(aBitmap.getPixel((int)pixels[2], (int)pixels[3])!=SPACE_COLOR)||
					(aBitmap.getPixel((int)pixels[4], (int)pixels[5])!=SPACE_COLOR)
					){
				gameView.saveTrace();
				((CountedPlayer)mPlayers.get(i)).setState(CountedPlayer.PLAYER_DEAD);
				gameView.roudFinished();
			}
		}
	}
	
	public void start(){
		mStarted = true;
		for(int i=0; i<=mPlayers.size()-1; i++){
			((CountedPlayer)mPlayers.get(i)).setState(CountedPlayer.PLAYER_FORWARD);
		}
		
		
	}

	public boolean getStarted() {
		return mStarted;
	}
	
	@Override
	public String toString(){
		return "round("+mPlayers.toString()+")";
	}

	public void finish() {
		mFinished = true;
		for(int i=0; i<=mPlayers.size()-1; i++){
			((CountedPlayer)mPlayers.get(i)).setState(CountedPlayer.PLAYER_DEAD);
		}
	}

	public boolean isStarted() {
		return mStarted;
	}

	public boolean isFinished() {
		return mFinished;
	}
	
	
	

}
