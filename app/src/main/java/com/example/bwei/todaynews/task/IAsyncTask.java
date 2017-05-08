package com.example.bwei.todaynews.task;

import android.os.AsyncTask;
import android.text.TextUtils;


import com.example.bwei.todaynews.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by muhanxi on 17/4/24.
 */

public class IAsyncTask extends AsyncTask<String,Void,String> {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
            sPoolWorkQueue);
    private static final int CORE_POOL_SIZE = 4;
    private static final int MAXIMUM_POOL_SIZE = 10;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);

    private ResponseListener listener ;
    public  IAsyncTask(ResponseListener listener){

        this.listener= listener ;

    }

    //发送请求
    public void iExecuteOnExecutor(IAsyncTask task,String... args){
        task.executeOnExecutor(threadPoolExecutor,args);
    }

    private URL url;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String result = "" ;
        HttpURLConnection connection = null;
        String path = params[0] ;
        InputStream inputStream = null ;
        if(TextUtils.isEmpty(path)){
            return result;
        }


        try {
            Thread.sleep(10000);
            URL url =   new URL(path);

             connection = (HttpURLConnection) url.openConnection() ;

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            if(connection.getResponseCode() == 200){
                inputStream =  connection.getInputStream() ;
                result =  StringUtils.inputStreamToString(inputStream);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                    inputStream = null;
                }
                if(connection != null){
                    connection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("result = " + result);
            return  result ;
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if(listener != null){


            if(TextUtils.isEmpty(result)){
                //回调失败
                listener.onFail();
            }else{
                // 回调成功

                //
                listener.onSuccess(result);
            }
        } else {
            listener.onFail();
        }



    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
        if(listener != null){
            listener.onFail();
        }

    }
}
