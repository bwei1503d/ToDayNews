package com.example.bwei.todaynews;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.slidingmenu.SlidingMenu;
import com.bwei.slidingmenu.app.SlidingFragmentActivity;
import com.example.bwei.todaynews.events.MainActivityEvent;
import com.example.bwei.todaynews.fragments.ActivitysFragment;
import com.example.bwei.todaynews.fragments.CollectFragment;
import com.example.bwei.todaynews.fragments.FriendsFragment;
import com.example.bwei.todaynews.fragments.IndexFragment;
import com.example.bwei.todaynews.fragments.MenuLeftFragment;
import com.example.bwei.todaynews.fragments.MenuRightFragment;
import com.example.bwei.todaynews.fragments.MyTalkFragment;
import com.example.bwei.todaynews.fragments.ShopFragment;
import com.example.bwei.todaynews.other.CustomTextView;
import com.example.bwei.todaynews.other.CustomViewActivity;
import com.example.bwei.todaynews.other.ViewgroupActivity;
import com.example.bwei.todaynews.services.PushIntentService;
import com.example.bwei.todaynews.services.PushService;
import com.igexin.sdk.PushManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends SlidingFragmentActivity implements UMAuthListener , MenuRightFragment.ShareListener {

    private SlidingMenu slidingMenu;

    private List<Fragment> list = new ArrayList<Fragment>();
    private IApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = (IApplication)  getApplicationContext();

        initLeftRight();
        initFragment(savedInstanceState);


        switchFragment(0);


        initGrayBackgroud();

        //如果当前页面 已经注册了  则不需要注册
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }




    }




    private void initLeftRight(){
        Fragment leftFragment = new MenuLeftFragment();
//        设置侧滑菜单的布局
        setBehindContentView(R.layout.left_menu_frame);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_leftmenu_id,leftFragment).commit();


        slidingMenu = getSlidingMenu();
        // 设置slidingmenu滑动的方式
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置阴影的宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        // 设置slidingmenu边界的阴影图片
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);

        //设置右边（二级）侧滑菜单
        MenuRightFragment rightMenuFragment = new MenuRightFragment();
        rightMenuFragment.setShareListener(this);
        slidingMenu.setSecondaryMenu(R.layout.right_menu_frame);

        getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_rightmenu, rightMenuFragment).commit();
    }

    private void initFragment(Bundle savedInstanceState){
        if(list != null && list.size() > 0){
            list.clear();
        }
        IndexFragment indexFragment = null ;
        FriendsFragment friendsFragment = null;
        MyTalkFragment myTalkFragment = null ;
        CollectFragment collectFragment = null;
        ActivitysFragment activitysFragment = null;
        ShopFragment shopFragment = null;
        if(savedInstanceState != null){
            indexFragment = (IndexFragment)getSupportFragmentManager().findFragmentByTag("IndexFragment");
            friendsFragment = (FriendsFragment)getSupportFragmentManager().findFragmentByTag("FriendsFragment");
            myTalkFragment = (MyTalkFragment)getSupportFragmentManager().findFragmentByTag("MyTalkFragment");
            collectFragment = (CollectFragment)getSupportFragmentManager().findFragmentByTag("CollectFragment");
            activitysFragment = (ActivitysFragment)getSupportFragmentManager().findFragmentByTag("ActivitysFragment");
            shopFragment = (ShopFragment)getSupportFragmentManager().findFragmentByTag("ShopFragment");
        }
        if(indexFragment == null){
            indexFragment = new IndexFragment();
        }
        if(friendsFragment == null){
            friendsFragment = new FriendsFragment();
        }
        if(myTalkFragment == null){
            myTalkFragment = new MyTalkFragment();
        }
        if(collectFragment == null){
            collectFragment = new CollectFragment();
        }
        if(activitysFragment == null){
            activitysFragment = new ActivitysFragment();
        }
        if(shopFragment == null){
            shopFragment = new ShopFragment();
        }
        list.add(indexFragment);
        list.add(friendsFragment);
        list.add(myTalkFragment);
        list.add(collectFragment);
        list.add(activitysFragment);
        list.add(shopFragment);


    }


    private void switchFragment(int pos){

       FragmentManager fragmentManager =  getSupportFragmentManager() ;
       FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        if(!list.get(pos).isAdded()){
            fragmentTransaction.add(R.id.container,list.get(pos),list.get(pos).getClass().getSimpleName());
        }
        for(int i=0;i<list.size();i++){
            if(i == pos){
                fragmentTransaction.show(list.get(pos));
            }else {
                fragmentTransaction.hide(list.get(i));
            }
        }
        fragmentTransaction.commit();
    }


    WindowManager windowManager ;
    WindowManager.LayoutParams layoutParams ;
    View view ;

    public void initGrayBackgroud() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

//        应用程序窗口。 WindowManager.LayoutParams.TYPE_APPLICATION
//        所有程序窗口的“基地”窗口，其他应用程序窗口都显示在它上面。
//        普通应用功能程序窗口。token必须设置为Activity的token，以指出该窗口属谁。

//        后面的view获得焦点
        layoutParams = new WindowManager.LayoutParams
                (WindowManager.LayoutParams.TYPE_APPLICATION,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        PixelFormat.TRANSPARENT);
        view = new View(this);

        view.setBackgroundResource(R.color.color_window);

    }


    // 日 夜切换
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainActivityEvent(MainActivityEvent event){
        System.out.println("isChecked = " + event.isWhite());

        if(event.isWhite()){
            // 日
            windowManager.removeViewImmediate(view);
        } else  {
            // true 夜
            windowManager.addView(view, layoutParams);

        }
        //对所有的控件取出,设置对应的图片
        setView();
        //更改字体颜色
        switchTextViewColor((ViewGroup) getWindow().getDecorView(),event.isWhite());

        IndexFragment indexFragment = (IndexFragment) list.get(0);

        indexFragment.changeMode(event.isWhite());
    }


    // 更改 控件 背景
    private    void setView(){


    }

    /**
     * 遍历出所有的textView设置对应的颜色
     * @param view
     */
    public void switchTextViewColor(ViewGroup view,boolean white) {
//        getChildCount 获取ViewGroup下view的个数
//        view.getChildAt(i) 根据下标获取对应的子view
        for (int i = 0; i < view.getChildCount(); i++) {
            if (view.getChildAt(i) instanceof ViewGroup) {
                switchTextViewColor((ViewGroup) view.getChildAt(i),white);
            } else if (view.getChildAt(i) instanceof TextView) {
                //设置颜色
                int resouseId ;
                TextView textView = (TextView) view.getChildAt(i);
                if(white){
                    resouseId = Color.BLACK;
                }else {
                    resouseId = Color.WHITE;
                }
                textView.setTextColor(resouseId);
            }
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }



    //   888

    public void share(SHARE_MEDIA share_media){
//        UMShareAPI umShareAPI = UMShareAPI.get(getApplicationContext());
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(this).setShareConfig(config);
        application.umShareAPI.getPlatformInfo(this, share_media,this);
    }


    //
    // 分享的回调
    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        System.out.println("share_media = " + share_media);
        System.out.println("share_media =i " + i);


    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        //111

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);

        System.out.println("requestCode = " + requestCode);
        System.out.println("resultCode = " + resultCode);
        System.out.println("data = " + data);


    }



}
