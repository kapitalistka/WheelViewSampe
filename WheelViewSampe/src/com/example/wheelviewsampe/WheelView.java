package com.example.wheelviewsampe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class WheelView extends View {

	Bitmap bmCircle;
	Paint paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bmHandler;
	Paint paintHandler = new Paint(Paint.ANTI_ALIAS_FLAG);

    int wCircle,hCircle,wHandler,hHandler;

	int mPosition = 0;
	boolean isGo = false;
	Runnable onEveryTime;
	Handler handler;

	public WheelView(Context context) {
		super(context);
		init();
		// TODO Auto-generated constructor stub
	}

	public WheelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

   public void init(){
	   bmCircle = BitmapFactory.decodeResource(getResources(), R.drawable.wheel);
       bmHandler = BitmapFactory.decodeResource(getResources(), R.drawable.qq);

	   onEveryTime=new Runnable() {
		    public void run() {
		        setPosition(mPosition+3);		        
		        if(isGo)
		            {handler.postDelayed(onEveryTime, 100);}
		        }
		    };
   }

	@Override
	protected void onDraw(Canvas canvas) {

		canvas.rotate(mPosition, wCircle/2, hCircle/2);
        canvas.drawBitmap(bmCircle, 0, 0, paintCircle);
        //canvas.save();
        canvas.restore();
        if(!isGo)canvas.drawBitmap(bmHandler, (wCircle-wHandler)/2+20, 20, paintHandler);


	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minw = 100;
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        int minh = 100;
        int h = resolveSizeAndState(MeasureSpec.getSize(w) , heightMeasureSpec, 0);

        wCircle=w;
        hCircle=h;
        wHandler=w/100*40;
        hHandler=h/3;

        bmCircle =Bitmap.createScaledBitmap(bmCircle,wCircle,hCircle,false);
        bmHandler =Bitmap.createScaledBitmap(bmHandler,wHandler,hHandler,false);


        setMeasuredDimension(w, h);
	}

	public void setPosition(int x) {
        mPosition=x;

		if(mPosition>=360|| mPosition<=-360 )mPosition =x % 360;
        if(!isGo && mPosition % 45>0) {
            if (mPosition % 45<=23)mPosition -= (mPosition % 45);
            else {
                mPosition = (mPosition / 45+1)*45;

            }
        }

		invalidate();
	}
	public void doRotationOnTouch() {
		isGo=(isGo)? false: true;
		handler=new Handler();
		onEveryTime.run();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		doRotationOnTouch();
		return super.onTouchEvent(event);
	}
	

	
}
