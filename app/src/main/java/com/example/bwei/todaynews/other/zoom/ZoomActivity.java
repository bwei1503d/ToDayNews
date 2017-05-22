package com.example.bwei.todaynews.other.zoom;

import android.os.Bundle;
import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.example.bwei.todaynews.R;


/**
 * https://github.com/chrisbanes/PhotoView
 */
public class ZoomActivity extends Activity  {

    private ImageView imageView;
    private GestureDetector.SimpleOnGestureListener gestureDetector;
    private GestureDetector gestureDetector1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

//        imageView = (ImageView) findViewById(R.id.zoomimageview);

//        gestureDetector1 = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
//            @Override
//            public boolean onSingleTapConfirmed(MotionEvent e) {
//                System.out.println("e = onSingleTapConfirmed " + e);
//                return super.onSingleTapConfirmed(e);
//            }
//
//            @Override
//            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//                System.out.println("e = onFling " + e1);
//                return super.onFling(e1, e2, velocityX, velocityY);
//            }
//
//            @Override
//            public boolean onDoubleTap(MotionEvent e) {
//                System.out.println("e = onDoubleTap " + e);
//                return super.onDoubleTap(e);
//            }
//
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                System.out.println("e = onSingleTapUp " + e);
//                return super.onSingleTapUp(e);
//            }
//        });





    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(gestureDetector1 != null){
            gestureDetector1.onTouchEvent(event);
        }
        return true;
    }
}
