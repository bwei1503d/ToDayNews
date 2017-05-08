package com.example.bwei.todaynews.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwei.todaynews.R;
import com.example.bwei.todaynews.adapter.IndexFragmentAdapter;
import com.example.bwei.todaynews.base.BaseFragment;

/**
 * Created by muhanxi on 17/4/30.
 */

public class IndexFragment extends BaseFragment {



    //推荐
    public static IndexFragment newInstance(int type) {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putInt("args",type);
        fragment.setArguments(args);
        return fragment;
    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private IndexFragmentAdapter indexFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.index_fragment,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.index_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.index_viewpager);
        indexFragmentAdapter = new IndexFragmentAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(indexFragmentAdapter);

        tabLayout.setupWithViewPager(viewPager);
        setWhiteMode();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }



    /**
     * 切换夜间模式
     * @param white
     */
    public void changeMode(boolean white) {
        if(white){
            tabLayout.setBackgroundColor(Color.GRAY);
            setWhiteMode();
        }else {
            tabLayout.setBackgroundColor(Color.BLACK);
            setNightMode();
        }

    }

    private void setWhiteMode(){
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.title_color));
        tabLayout.setTabTextColors(getResources().getColor(R.color.iblack),getResources().getColor(R.color.title_color));
    }
    private void setNightMode(){
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.title_color));
        tabLayout.setTabTextColors(getResources().getColor(R.color.iblack),getResources().getColor(R.color.title_color));
    }

}
