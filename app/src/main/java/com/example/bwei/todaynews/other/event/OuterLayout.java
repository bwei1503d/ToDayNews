package com.example.bwei.todaynews.other.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by muhanxi on 17/5/17.
 */

public class OuterLayout extends LinearLayout {
    public OuterLayout(Context context) {
        super(context);
    }

    public OuterLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public OuterLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 转发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("OuterLayout ev = dispatchTouchEvent " );
        return super.dispatchTouchEvent(ev);
    }

    //拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("OuterLayout ev = onInterceptTouchEvent " );
        return super.onInterceptTouchEvent(ev);
//        return true ;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("OuterLayout ev = onTouchEvent " );
        return super.onTouchEvent(event);
    }
}

