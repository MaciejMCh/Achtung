package com.example.achtung;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class CountedPlayer extends Player {

	public static int PLAYER_FREEZE = 0;
	public static int PLAYER_FORWARD = 1;
	public static int PLAYER_LEFT = 2;
	public static int PLAYER_RIGHT = 3;
	public static int PLAYER_DEAD = 4;
	private float RIGHT_ANGLE = (float) (Math.PI/2);
	private float ANGLE_45 = (float) (Math.PI/4);
	
	private float mX;
	private float mY;
	private float mAngle;
	private long mTime;
	private int mState;
	private float mSpeed;
	private float mAngleSpeed;
	private float mCurveRadius;
	private float[] x;
	private float[] y;
	private Paint mPaint;
	private Paint mCurvePaint;
	private Paint mHeadPaint;
	
	
	
	
	public CountedPlayer(float aX, float aY, float aA) {
		mX = aX;
		mY = aY;
		mAngle = aA;
		mTime = System.currentTimeMillis();
		mSpeed = 0.0002f;
		
		mState = PLAYER_DEAD;
		mCurveRadius = 0.06f;
		
		mAngleSpeed = mSpeed/mCurveRadius;
		
		
		
	}
	
	public CountedPlayer(String data) {
		String[] floats = data.split(":");
		mX = Float.parseFloat(floats[0]);
		mY = Float.parseFloat(floats[1]);
		mAngle = Float.parseFloat(floats[2]);
		
		mTime = System.currentTimeMillis();
		mSpeed = 0.0002f;		
		mState = PLAYER_DEAD;
		mCurveRadius = 0.06f;		
		mAngleSpeed = mSpeed/mCurveRadius;
	}

	@Override
	float getX() {
		if(mState == PLAYER_FORWARD) return (float) (mX+(System.currentTimeMillis()-mTime)*Math.sin(mAngle)*mSpeed);
		if(mState == PLAYER_RIGHT) return (float) (
				mCurveRadius*Math.sin(mAngle+RIGHT_ANGLE)+
				mX+ mCurveRadius*Math.sin(mAngle-RIGHT_ANGLE+(System.currentTimeMillis()-mTime)*mAngleSpeed));
		if(mState == PLAYER_LEFT) return (float) (
				mCurveRadius*Math.sin(mAngle-RIGHT_ANGLE)+
				mX+ mCurveRadius*Math.sin(mAngle+RIGHT_ANGLE-(System.currentTimeMillis()-mTime)*mAngleSpeed));
		return mX;
	}

	@Override
	float getY() {
		if(mState == PLAYER_FORWARD) return (float) (mY-(System.currentTimeMillis()-mTime)*Math.cos(mAngle)*mSpeed);
		if(mState == PLAYER_RIGHT) return (float) (
				-mCurveRadius*Math.cos(mAngle+RIGHT_ANGLE)+
				mY- mCurveRadius*Math.cos(mAngle-RIGHT_ANGLE+(System.currentTimeMillis()-mTime)*mAngleSpeed));
		if(mState == PLAYER_LEFT) return (float) (
				-mCurveRadius*Math.cos(mAngle-RIGHT_ANGLE)+
				mY- mCurveRadius*Math.cos(mAngle+RIGHT_ANGLE-(System.currentTimeMillis()-mTime)*mAngleSpeed));
		return mY;
	}
	
	private float getAngle(){
		if(mState == PLAYER_FORWARD) return mAngle;
		if(mState == PLAYER_RIGHT) return (float) (mAngle+(System.currentTimeMillis()-mTime)*mAngleSpeed);
		if(mState == PLAYER_LEFT) return (float) (mAngle-(System.currentTimeMillis()-mTime)*mAngleSpeed);
		return mAngle;
	}

	public void setState(int aState) {
		mX = getX();
		mY = getY();
		mAngle = getAngle();
		mTime = System.currentTimeMillis();
		this.mState = aState;
	}
	
	public void setState(int aState, String positionData) {
		String[] datas = positionData.split(",");
		mX = Float.parseFloat(datas[0]);
		mY = Float.parseFloat(datas[1]);
		mAngle = Float.parseFloat(datas[2]);
		mTime = System.currentTimeMillis();
		this.mState = aState;
	}

	public float[] getSkullX(float aSize) {
		x = new float[(int) (2*Math.PI*aSize/100)];
		y = new float[x.length];
		
		float dangle = (float) (2*Math.PI/x.length);
		float radius = aSize/100;
		
		float xp = getX()*aSize;
		float yp = getY()*aSize;
		
		for(int i=0; i<=x.length-1; i++){
			x[i] = (float) (xp+Math.sin(dangle*i)*radius); 
			y[i] = (float) (yp-Math.cos(dangle*i)*radius);
		}
		
		return x;
	}
	public float[] getSkullY(float aSize) {
		return y;
	}
	
	public float[] getSkull(float aSize){
		float[] skull = new float[6];
		
		float angle = getAngle();
		float x = getX();
		float y = getY();
		float correction = 1.2f;
		skull[0]=(float) (x+Math.sin(angle-ANGLE_45)*0.01*correction)*aSize;
		skull[1]=(float) (y-Math.cos(angle-ANGLE_45)*0.01*correction)*aSize;
		skull[2]=(float) (x+Math.sin(angle)*0.01*correction)*aSize;
		skull[3]=(float) (y-Math.cos(angle)*0.01*correction)*aSize;
		skull[4]=(float) (x+Math.sin(angle+ANGLE_45)*0.01*correction)*aSize;
		skull[5]=(float) (y-Math.cos(angle+ANGLE_45)*0.01*correction)*aSize;
		return skull;
	}

	public int getState() {
		return mState;
	}

	public void setX(float f) {
		this.mX = f;
		this.mTime = System.currentTimeMillis();
		this.setState(mState);		
	}
	public void setY(float f) {
		this.mY = f;
		this.mTime = System.currentTimeMillis();
		this.setState(mState);		
	}
	
	@Override
	public String toString(){
		return "player("+mX+":"+mY+":"+mAngle+")";
	}

	public void drawCurrentTrace(Canvas canvas, int aSize, int aColor) {
		if(mHeadPaint == null){
			mPaint = new Paint();
			mPaint.setStrokeWidth(aSize/50);
			mPaint.setColor(aColor);
			mCurvePaint = new Paint();
			mCurvePaint.setStyle(Paint.Style.STROKE);
			mCurvePaint.setStrokeWidth(aSize/50);
			mCurvePaint.setColor(aColor);
			mHeadPaint = new Paint();
			mHeadPaint.setColor(aColor);
		}
		Log.d("leci","kat: "+Math.toDegrees(mAngle));
		if(mState == PLAYER_FORWARD){
			canvas.drawLine(mX*aSize, mY*aSize, getX()*aSize, getY()*aSize, mPaint);
			canvas.drawCircle(getX()*aSize, getY()*aSize, aSize/100, mHeadPaint);
		}else if(mState == PLAYER_RIGHT){	
			canvas.drawCircle(getX()*aSize, getY()*aSize, aSize/100, mHeadPaint);
			float cX = (float)(mX+Math.cos(mAngle)*mCurveRadius)*aSize;
			float cY = (float)(mY+Math.sin(mAngle)*mCurveRadius)*aSize;			
//			canvas.drawPoint(cX, cY, mCurvePaint);			
			float startAngle = (float) Math.toDegrees(mAngle);
			float endAngle = (float) Math.toDegrees(getAngle());			
			canvas.drawArc(
					new RectF(
						cX-mCurveRadius*aSize,
						cY-mCurveRadius*aSize,
						cX+mCurveRadius*aSize,
						cY+mCurveRadius*aSize
							),
					startAngle+180,
					endAngle-startAngle,
					false		
					, mCurvePaint);	
		}else if(mState == PLAYER_LEFT){
			canvas.drawCircle(getX()*aSize, getY()*aSize, aSize/100, mHeadPaint);
			float cX = (float)(mX+Math.cos(mAngle-Math.PI)*mCurveRadius)*aSize;
			float cY = (float)(mY+Math.sin(mAngle-Math.PI)*mCurveRadius)*aSize;			
//			canvas.drawPoint(cX, cY, mCurvePaint);			
			float startAngle = (float) Math.toDegrees(mAngle);
			float endAngle = (float) Math.toDegrees(getAngle());			
			canvas.drawArc(
					new RectF(
						cX-mCurveRadius*aSize,
						cY-mCurveRadius*aSize,
						cX+mCurveRadius*aSize,
						cY+mCurveRadius*aSize
							),
					startAngle,
					endAngle-startAngle,
					false		
					, mCurvePaint);	
		}
		
		
		
	}

	public String getPosition() {
		return mX+","+mY+","+mAngle;
	}

	
	

}
