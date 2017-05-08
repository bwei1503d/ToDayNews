package com.example.bwei.todaynews.base;

import android.app.Activity;
import android.os.Bundle;

import com.bwei.slidingmenu.SlidingMenu;
import com.example.bwei.todaynews.R;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


    }


}
