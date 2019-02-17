package com.jevo.alexander.lesson1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jevo.alexander.lesson1.R;

import androidx.annotation.Nullable;

public class Preview extends View {

  private static final String TAG = "Preview";
  private Paint paint;
  private int color;

  public Preview(Context context) {
    super(context);
    Log.d(TAG, "Constructor");
    init();
  }

  public Preview(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public Preview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public Preview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init() {
    paint = new Paint();
    color = getResources().getColor(R.color.orangeColor);
    paint.setColor(color);
    paint.setStyle(Paint.Style.FILL);
    paint.setStrokeWidth(10);
  }

  @Override
  protected void onAttachedToWindow() {
    Log.d(TAG, "onAttachedToWindow");
    super.onAttachedToWindow();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    Log.d(TAG, "onMeasure");
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  public void layout(int l, int t, int r, int b) {
    Log.d(TAG, "layout");
    super.layout(l, t, r, b);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    Log.d(TAG, "onLayout");
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override
  public void draw(Canvas canvas) {
    Log.d(TAG, "draw");
    super.draw(canvas);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    Log.d(TAG, "onDraw");
    super.onDraw(canvas);
    drawLuch(canvas);
    drawWhite(canvas);
    canvas.drawCircle(500, 500, 200, paint);
  }

  private void drawWhite(Canvas canvas) {
    paint.setColor(Color.WHITE);
    for (float angle = 5; angle <= 365; angle += 30)
    {   //move round the circle to each point
      float x = 500 + ((float)Math.cos(Math.toRadians(angle)) * 250); //convert angle to radians for x and y coordinates
      float y = 500 + ((float)Math.sin(Math.toRadians(angle)) * 250);
      canvas.drawLine(500, 500, x, y, paint); //draw a line from center point back to the point
    }
    paint.setColor(color);
  }

  private void drawLuch(Canvas canvas) {
    for (float angle = 5; angle <= 365; angle += 30)
    {   //move round the circle to each point
      float x = 500 + ((float)Math.cos(Math.toRadians(angle)) * 400); //convert angle to radians for x and y coordinates
      float y = 500 + ((float)Math.sin(Math.toRadians(angle)) * 400);
      canvas.drawLine(500, 500, x, y, paint); //draw a line from center point back to the point
    }
  }


  @Override
  public void invalidate() {
    Log.d(TAG, "invalidate");
    super.invalidate();
  }

  @Override
  public void requestLayout() {
    Log.d(TAG, "requestLayout");
    super.requestLayout();
  }
}
