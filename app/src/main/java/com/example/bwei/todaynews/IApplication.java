package com.example.bwei.todaynews;

import android.app.Application;
import android.os.Environment;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.common.QueuedWork;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * Created by muhanxi on 17/5/1.
 */
//

public class IApplication extends Application {

    public UMShareAPI umShareAPI ;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);


        umShareAPI = UMShareAPI.get(this);
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = true;
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");


        getDaoConfig();

    }

    public  static DbManager.DaoConfig daoConfig;
    public static DbManager.DaoConfig getDaoConfig(){
        if(daoConfig==null){
            daoConfig=new DbManager.DaoConfig()
                    .setDbVersion(1)
                    .setDbName("tt")//设置数据库的名字
                    .setAllowTransaction(true)
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                        }
                    });
        }
        return daoConfig;
    }

}
