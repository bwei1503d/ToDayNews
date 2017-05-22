package com.example.bwei.todaynews.other.anim;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bwei.todaynews.R;

public class AnimActivity extends Activity {

    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);


        button = (Button) findViewById(R.id.btn_id);
        imageView = (ImageView) findViewById(R.id.anim_id);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startAnim1();

            }
        });




    }

    public void startAnim(){
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 200);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        imageView.setAnimation(translateAnimation);
        imageView.startAnimation(translateAnimation);
    }
    public void startAnim1(){
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF,-1.2f,
                Animation.RELATIVE_TO_SELF, 0.0F,
                Animation.RELATIVE_TO_SELF, -1.2F);
        translateAnimation.setDuration(2000);
        translateAnimation.setFillAfter(true);
        imageView.setAnimation(translateAnimation);
        imageView.startAnimation(translateAnimation);

    }

}
