package com.elite.floatbutton.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.TextView;

import com.elite.floatbutton.R;

/**
 * Elite Group
 * Created by wjc133 on 2015/8/12.
 */
public class TouchActivity extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private final static String TAG = "TouchActivity";
    private TextView mPointView;
    private GestureDetectorCompat mDetector;
    private VelocityTracker mVelocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_touch);
        mPointView = (TextView) findViewById(R.id.touch_text);
        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int index = event.getActionIndex();
        int pointerId = event.getPointerId(index);

        switch (action) {
            case MotionEvent.ACTION_UP:
                Log.v(TAG, "ACTION_UP");
                return true;
            case MotionEvent.ACTION_DOWN:
                Log.v(TAG, "ACTION_DOWN");
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(event);
                return true;
            case MotionEvent.ACTION_CANCEL:
                Log.v(TAG, "ACTION_CANCEL");
                mVelocityTracker.recycle();
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.v(TAG, "ACTION_MOVE");
//                mPointView.setText("坐标:(" + event.getX() + "," + event.getY() + ")");
                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000);
                String xpos = "X velocity: " +
                        VelocityTrackerCompat.getXVelocity(mVelocityTracker,
                                pointerId);
                String ypos = "Y velocity: " +
                        VelocityTrackerCompat.getYVelocity(mVelocityTracker,
                                pointerId);
                mPointView.setText("X速度:"+xpos+" Y速度:"+ypos);
                return true;
            case MotionEvent.ACTION_OUTSIDE:
                Log.v(TAG, "Movement occurred outside bounds of current screen element");
                return true;
            default:
                return super.onTouchEvent(event);
        }
//        if (event.getActionMasked()==MotionEvent.ACTION_MOVE){
//            mPointView.setText("坐标:("+event.getX()+","+event.getY()+")");
//        }
//        mDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        log("onSingleTapConfirmed " + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        log("onDoubleTap " + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        log("onDoubleTapEvent " + e.toString());
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        log("MotionEvent " + e.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        log("onShowPress " + e.toString());

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        log("onSingleTapUp " + e.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        log("onScroll " + e1.toString() + e2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        log("onLongPress " + e.toString());

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        log("onFling " + e1.toString() + e2.toString());
        return true;
    }

    private void log(String msg) {
        Log.v(TAG, msg);
    }
}
