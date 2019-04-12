package cn.edu.jssvc.gezhi.colorlife.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {
    private static SharedPreferences sp;
    public static SharedPreferences get(Context context){
        return context.getSharedPreferences("ColorLife",Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor edit(Context context){
        return get(context).edit();
    }

    /**
     * 保存boolean信息的操作
     */
    public static void saveBoolean(Context context,String key,boolean value){
        if (sp == null) {
            sp = context.getSharedPreferences("ColorLife", Context.MODE_PRIVATE);
        }
        //保存数据
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * 获取boolean信息的操作
     */
    public static boolean getBoolean(Context context,String key,boolean defValue){
        if (sp == null) {
            sp = context.getSharedPreferences("ColorLife", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }



    /**
     * 保存String信息的操作
     */
    public static void saveString(Context context,String key,String value){
        if (sp == null) {
            sp = context.getSharedPreferences("ColorLife", Context.MODE_PRIVATE);
        }
        //保存数据
        sp.edit().putString(key, value).apply();
    }


    /**
     * 获取String信息的操作
     */
    public static String getString(Context context,String key,String defValue){
        if (sp == null) {
            sp = context.getSharedPreferences("ColorLife", Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

    /**
     * 保存int信息的操作
     */
    public static void saveInt(Context context,String key,int value){
        if (sp == null) {
            sp = context.getSharedPreferences("ColorLife", Context.MODE_PRIVATE);
        }
        //保存数据
        sp.edit().putInt(key, value).apply();
    }

    /**
     * 获取Int信息的操作
     */
    public static int getInt(Context context,String key,int defValue){
        if (sp == null) {
            sp = context.getSharedPreferences("ColorLife", Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }

}
