package com.example.bwei.todaynews.db;

import com.example.bwei.todaynews.bean.TuijianBean;
import com.example.bwei.todaynews.bean.TuijianBean1;

import org.xutils.ex.DbException;

import java.util.List;

/**
 * Created by muhanxi on 17/5/9.
 */

public class DbManager {

    public static DbManager dbManager = null ;

    private DbManager(){

    }

    public static  DbManager getInstance(){
        if (dbManager == null){
            dbManager = new DbManager();
        }
        return dbManager;
    }


    public void save(org.xutils.DbManager dbManager, List<TuijianBean1.DataBean> list){

        try {
            dbManager.save(list);
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

}
