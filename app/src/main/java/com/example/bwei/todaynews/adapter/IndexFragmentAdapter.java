package com.example.bwei.todaynews.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bwei.todaynews.fragments.IndexFragment;
import com.example.bwei.todaynews.fragments.subfragments.ShipinFragment;
import com.example.bwei.todaynews.fragments.subfragments.TuijianFragment;

/**
 * Created by muhanxi on 17/4/30.
 */

public class IndexFragmentAdapter extends FragmentPagerAdapter {

    public String [] TITLE = {"推荐","热点","本地","视频","社会","娱乐","科技","汽车","科技","汽车","体育","财经","军事","国际","段子","趣图","健康","美女"} ;

    public IndexFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            TuijianFragment tuijianFragment =  new TuijianFragment() ;

            Bundle bundle = new Bundle();
            bundle.putInt("pos",position);
            tuijianFragment.setArguments(bundle);
            return tuijianFragment;
        }else {

            return new ShipinFragment();
        }

    }

    @Override
    public int getCount() {
        return TITLE.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position] ;
    }
}
