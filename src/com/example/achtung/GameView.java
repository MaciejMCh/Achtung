package com.example.achtung;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.PorterDuff;
public class GameView extends View {

	private int mSize;
	private Paint mBackground;
	private Paint mPlayer;
	private int[] mPlayerColors;
	private Round mRound;
	private float mRadius;
	private Canvas mTraceCanvas;
	private Bitmap mTraceBitmap;
	private boolean mRunning;
	private int mH;
	private Bitmap mCurrentBitmap;
	private Canvas mCurrentCanvas;

	public GameView(Context context) {
		super(context);
		this.setDrawingCacheEnabled(true);
		
		mRunning = false;
		
		mBackground = new Paint();
		mBackground.setColor(Color.BLACK);
		mPlayer = new Paint();
		
		
		
		
		mPlayerColors = new int[8];
		mPlayerColors[0] = Color.RED;
		mPlayerColors[1] = Color.GREEN;
		mPlayerColors[2] = Color.BLUE;
		mPlayerColors[3] = Color.CYAN;
		mPlayerColors[4] = Color.MAGENTA;
		mPlayerColors[5] = Color.YELLOW;
		mPlayerColors[6] = Color.GRAY;
		mPlayerColors[7] = Color.WHITE;
		
		
//		Controller.getInstance().newRound(new Round(3));
//		mRound = Controller.getInstance().getCurrentRound();
		
		new Thread(
				new Runnable() {					
					@Override
					public void run() {	
						while(true){
							postInvalidate();
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
					}
				}
		).start();
		
		
	}
	
	
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh){
		mSize = h;
		mRadius = mSize/100;
		mTraceBitmap = Bitmap.createBitmap(h, h, Config.RGB_565);
		mTraceCanvas = new Canvas(mTraceBitmap);
		mPlayer.setStrokeWidth(mSize/100);
		mH = h;
		mCurrentBitmap = Bitmap.createBitmap(h, h, Config.RGB_565);
		mCurrentCanvas = new Canvas(mCurrentBitmap);
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		
		
		
//		canvas.drawArc(new RectF(0, 0, 100, 100), 0, (float) Math.PI, true, mPlayer);
		

		
		canvas.drawRect(0, 0, mSize, mSize, mBackground);
		
		
		
			
			mCurrentCanvas.drawColor(Color.BLACK);
			

			
			ArrayList<Player> players = mRound.getPlayers();
			for(int i=0; i<=players.size()-1; i++){
				((CountedPlayer)players.get(i)).drawCurrentTrace(mCurrentCanvas, mSize, mPlayerColors[i]);
			}
			
			mCurrentCanvas.drawBitmap(mTraceBitmap, 0, 0, mPlayer);
//			canvas.drawBitmap(Bitmap.createScaledBitmap(mTraceBitmap, 100, 100, false), 0, 0, mPlayer);
			
//			mRound.checkAlive(this.getDrawingCache(), mSize, this);
//			mRound.checkAlive(mTraceBitmap, mSize, this);

		canvas.drawBitmap(mCurrentBitmap, 0, 0, mPlayer);
		
		mRound.checkAlive(mCurrentBitmap, mSize, this);
		
		this.drawSkull(canvas);
		

	}


	private void drawSkull(Canvas canvas) {
		Paint skullp = new Paint();
		skullp.setColor(Color.WHITE);
		float[] skull = ((CountedPlayer)mRound.getPlayers().get(0)).getSkull(mSize);
		canvas.drawPoints(skull, skullp);
	}


	private void drawPlayers(Canvas canvas) {
		ArrayList<Player> players = mRound.getPlayers();
		
//		for(int i=0; i<=players.size()-1; i++){
//			mPlayer.setColor(mPlayerColors[i]);
//			canvas.drawCircle(players.get(i).getX()*mSize, players.get(i).getY()*mSize, mRadius, mPlayer);
//		}
		
		
		
		
		
//		mRound.checkAlive(mTraceBitmap, mSize);
		
	}
	

	
	public void refresh(){
		postInvalidate();
	}


	public void newRound(Round aRound) {		
		this.mRound = aRound;
	}


	public void updateTrace(CountedPlayer aPlayer, int playerNo) {
		aPlayer.drawCurrentTrace(mTraceCanvas, mSize, mPlayerColors[playerNo]);
	}


	public void roudFinished() {
		mRound.finish();		
	}


	public void clearTrace() {
		mTraceBitmap.recycle();
		mTraceBitmap = Bitmap.createBitmap(mH, mH, Config.ARGB_8888);
		mTraceCanvas = new Canvas(mTraceBitmap);
	}


	public void saveTrace() {
		for(int i=0; i<=mRound.getPlayers().size()-1; i++){
			updateTrace((CountedPlayer) mRound.getPlayers().get(i), i);
		}
	}


	

}
