package cn.edu.jssvc.gezhi.colorlife;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    public static Context appContext;
    public static RequestQueue queue;
    public static ExecutorService executorService;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
        queue= Volley.newRequestQueue(getApplicationContext());
        executorService = Executors.newCachedThreadPool();
    }

}
