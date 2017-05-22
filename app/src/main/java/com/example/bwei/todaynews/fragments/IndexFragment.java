package com.example.bwei.todaynews.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bwei.todaynews.R;
import com.example.bwei.todaynews.adapter.IndexFragmentAdapter;
import com.example.bwei.todaynews.base.BaseFragment;
import com.example.bwei.todaynews.constants.Urls;
import com.example.bwei.todaynews.fragments.subfragments.ShipinFragment;
import com.example.bwei.todaynews.other.tabview.ColorTrackTabLayout;
import com.example.bwei.todaynews.task.IAsyncTask;
import com.example.bwei.todaynews.task.ResponseListener;

import java.util.Locale;

/**
 * Created by muhanxi on 17/4/30.
 */

public class IndexFragment extends BaseFragment  {


    private TextView textViewadd;
    private TextView textViewsub;

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
    private TextToSpeech textToSpeech;

    private void initView(View view) {

        tabLayout = (TabLayout) view.findViewById(R.id.index_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.index_viewpager);
        indexFragmentAdapter = new IndexFragmentAdapter(getActivity().getSupportFragmentManager(),false);
        viewPager.setAdapter(indexFragmentAdapter);



        tabLayout.setupWithViewPager(viewPager);
        setWhiteMode();
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        textViewadd = (TextView) view.findViewById(R.id.text_add);
        textViewsub = (TextView) view.findViewById(R.id.text_sub);

        textViewadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//            TabLayout.Tab tab =  tabLayout.newTab();
////
//            tab.setText("添加");
//                Fragment fragment =  new ShipinFragment();
//                tab.setCustomView(fragment.getView());
////
//            tabLayout.addTab(tab,IndexFragmentAdapter.TITLE.length);
//
//                System.out.println("fragment = " + IndexFragmentAdapter.TITLE.length);


//
//                indexFragmentAdapter = new IndexFragmentAdapter(getActivity().getSupportFragmentManager(),true);
//                viewPager.setAdapter(indexFragmentAdapter);
//                tabLayout.setupWithViewPager(viewPager);



            }
        });
        textViewsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                tabLayout.removeTabAt(IndexFragmentAdapter.TITLE.length-1);
//                System.out.println("fragment = " + IndexFragmentAdapter.TITLE.length);

            }
        });


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

    // 改变tablayout 字颜色 下标颜色
    private void setWhiteMode(){
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.title_color));
        tabLayout.setTabTextColors(getResources().getColor(R.color.iblack),getResources().getColor(R.color.title_color));
    }
    private void setNightMode(){
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.title_color));
        tabLayout.setTabTextColors(getResources().getColor(R.color.iblack),getResources().getColor(R.color.title_color));
    }


}
