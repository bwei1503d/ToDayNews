package com.example.bwei.todaynews.other;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bwei.todaynews.R;

public class ScaleActivity extends Activity {

    private ImageView imageView;
    float  last = -1 ;
    float current = 0 ;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);





    }


}
