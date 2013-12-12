package com.arawind.kmap;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

public class KmapGridView extends GridView {
	/*private Canvas canvas, mCanvas;
	private Paint paint;
	private Bitmap mBitmap, bitmap;
	private float start_x, start_y;
	private float end_x, end_y;
	private boolean mouseUp;*/

	public KmapGridView(Context context) {
		super(context);
		//init();
	}
	
	public KmapGridView(Context context, AttributeSet attrs){
		super(context, attrs);
		//init();
	}
	
	public KmapGridView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		//init();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/*canvas.drawColor(0xFFAAAAAA);
		if(mouseUp)
			canvas.drawBitmap(bitmap, 0, 0, paint);
		else{
			canvas.drawBitmap(bitmap, 0, 0, paint);
			canvas.drawBitmap(mBitmap, 0, 0, paint);
		}*/
	}
	
	/*public void setCanvas(Canvas canvas){
		this.canvas = canvas;
	}
	
	private void init(){
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeWidth(1);
		start_x = 0;
		start_y = 0;
		end_x = 0;
		end_y = 0;
		mouseUp = true;
	}*/
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /*mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);*/
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return super.onTouchEvent(ev);
		/*if(ev.getAction() ==  MotionEvent.ACTION_DOWN){
			start_x = ev.getX();
			start_y = ev.getY();
			mouseUp = false;
		}
		else if (ev.getAction() == MotionEvent.ACTION_MOVE){
			end_x = ev.getX();
			end_y = ev.getY();
			mCanvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
			mCanvas.drawRect(start_x, start_y, end_x, end_y, paint);
			invalidate();
		}
		else if (ev.getAction() == MotionEvent.ACTION_UP){
			canvas.drawRect(start_x, start_y, end_x, end_y, paint);
			mouseUp = true;
			invalidate();
		}
		
		return true;*/
	}
	
}
