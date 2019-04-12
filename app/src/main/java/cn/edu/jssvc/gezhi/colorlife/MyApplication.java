package cn.edu.jssvc.gezhi.colorlife;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.edu.jssvc.gezhi.colorlife.home.Arts_info;

public class MyApplication extends Application {
    public static Context appContext;
    public static RequestQueue queue;
    public static ExecutorService executorService;
    public static List<Arts_info> mArtsInfoList;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        queue= Volley.newRequestQueue(getApplicationContext());
        executorService = Executors.newCachedThreadPool();
        mArtsInfoList=new ArrayList<>();
    }

}
