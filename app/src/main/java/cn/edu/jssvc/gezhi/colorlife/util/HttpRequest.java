package cn.edu.jssvc.gezhi.colorlife.util;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cn.edu.jssvc.gezhi.colorlife.MyApplication;

/**
 * 暂未使用
 *  网络请求
 */
public class HttpRequest {
    public static void post(String name, String param, Response.Listener<JSONObject>listener,Response.ErrorListener errorListener){
        String url="http://192.168.0.100:8088/transportservice/action/"+name+".do";
        JsonObjectRequest request=new JsonObjectRequest(1,url,param,listener,errorListener);
        MyApplication.queue.add(request);
    }

    public static String HEAD = "head_photo";
    public static String ART = "calligraphy";
    /**
     *http://123.206.19.177:8088/colorlife/imgs/head_photo/zhoulin20190401.jpg实例图片路径
     * @param tag 表示要获取的的是头像还是艺术品图片
     * @param imgUrlPath 图片的url
     * @param listener 获取成功的监听
     * @param errorListener 获取出错的监听
     */
    public static void getImgBitmap(String tag ,String imgUrlPath, Response.Listener<Bitmap>listener,Response.ErrorListener errorListener){
        String url="http://123.206.19.177:8088/colorlife/imgs/"+tag+imgUrlPath;
        ImageRequest request=new ImageRequest(url,listener,0,0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888,errorListener);
        MyApplication.queue.add(request);
    }
}

