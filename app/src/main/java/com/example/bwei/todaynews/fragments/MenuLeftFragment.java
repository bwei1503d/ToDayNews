package com.example.bwei.todaynews.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.bwei.todaynews.R;
import com.example.bwei.todaynews.base.BaseFragment;
import com.example.bwei.todaynews.constants.Constants;
import com.example.bwei.todaynews.events.MainActivityEvent;
import com.example.bwei.todaynews.utils.PreferencesUtils;
import com.kyleduo.switchbutton.SwitchButton;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by muhanxi on 17/4/30.
 */

public class MenuLeftFragment extends BaseFragment implements View.OnClickListener{

    // 日 夜 切换
    private SwitchButton switchButton;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.menuleft_fragment,container,false);


        initView(view);

        return view;
    }

    private void initView(View view) {

        switchButton = (SwitchButton) view.findViewById(R.id.switch_btn);

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // isChecked true false
                System.out.println("isChecked = " + isChecked);
//
                boolean mode =  PreferencesUtils.getValueByKey(getContext(),Constants.isNightMode,isChecked);
                setMode(isChecked);
                EventBus.getDefault().post(new MainActivityEvent(isChecked));

                setBackground(isChecked);

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }


    private void setBackground(boolean white){

        if(white){
            view.setBackgroundColor(Color.WHITE);
        } else {
            //夜间
            view.setBackgroundColor(Color.BLACK);
        }
    }


    // mode true 夜 false 日
    private void setMode(boolean mode){
        PreferencesUtils.addConfigInfo(getContext(), Constants.isNightMode,mode);

    }
}
