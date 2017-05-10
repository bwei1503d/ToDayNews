package com.example.bwei.todaynews.fragments.subfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.bwei.springview.container.DefaultFooter;
import com.bwei.springview.container.DefaultHeader;
import com.bwei.springview.widget.SpringView;
import com.example.bwei.todaynews.IApplication;
import com.example.bwei.todaynews.MainActivity;
import com.example.bwei.todaynews.R;
import com.example.bwei.todaynews.adapter.NewsAdapter;
import com.example.bwei.todaynews.base.BaseFragment;
import com.example.bwei.todaynews.bean.TuijianBean;
import com.example.bwei.todaynews.constants.Urls;
import com.example.bwei.todaynews.task.IAsyncTask;
import com.example.bwei.todaynews.task.ResponseListener;
import com.google.gson.Gson;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by muhanxi on 17/4/30.
 * 推荐
 */

public class TuijianFragment extends BaseFragment implements SpringView.OnFreshListener , ResponseListener {


    private ListView listView;
    private SpringView springView;
    private NewsAdapter adapter ;

    private List<TuijianBean.DataBean> list = new ArrayList<TuijianBean.DataBean>();
    //标志 那个页面 1 推荐
    private int type ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(getArguments() != null) {
            type = getArguments().getInt("pos");
        }
        View view = inflater.inflate(R.layout.tuijian_fragment,container,false);


        initView(view);
        return view;
    }

    private void initView(View view) {

        listView = (ListView) view.findViewById(R.id.tuijian_listview);

        springView = (SpringView) view.findViewById(R.id.tuijian_springview);

        springView.setHeader(new DefaultHeader(getContext()));
        springView.setFooter(new DefaultFooter(getContext()));

        adapter = new NewsAdapter(getActivity(),list);
        listView.setAdapter(adapter);

        springView.setListener(this);


        getData();
    }



    private void getData(){

        IAsyncTask task =  new IAsyncTask(this) ;

        String path = "" ;
        if(type == 0){
            path = Urls.tuijian ;
        }
        task.iExecuteOnExecutor(task,path);
    }

    //上啦 下啦
    @Override
    public void onRefresh() {


    }

    @Override
    public void onLoadmore() {


    }


    //请求数据 结果
    @Override
    public void onSuccess(String string) {


        Gson gson = new Gson();
        TuijianBean bean = gson.fromJson(string, TuijianBean.class);

        list.addAll(bean.getData());
        adapter.notifyDataSetChanged();

        MainActivity mainActivity = (MainActivity) getActivity() ;
        IApplication application = (IApplication) mainActivity.getApplication() ;
        DbManager dbManager = x.getDb(application.getDaoConfig());

        com.example.bwei.todaynews.db.DbManager.getInstance().save(dbManager,bean.getData());

        try {
            List<TuijianBean.DataBean> lists =  dbManager.findAll(TuijianBean.DataBean.class);
            System.out.println("lists = " + lists);

        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFail() {

    }
}
