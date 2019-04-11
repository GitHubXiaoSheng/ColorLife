package cn.edu.jssvc.gezhi.colorlife.photo;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


import com.bumptech.glide.Glide;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * 图片的处理
 * Created by Administrator on 2019/4/6.
 */

public class PhotoUtil {

    HttpURLConnection conn1 = null;
    InputStream inStrem = null;
    HHandler myHandler = new HHandler();

    private static final int SIGNIN_MSG_OKHTTP = 2;
    private static final int SIGNIN_MSG_HTTPURLCONNECTION = 1;
    public static final int CHOOSE_ALBUM = 3;

    Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

//    public static void loadPhoto(Context context,String photoUrl){
//        try {
//            Bitmap bitmap = Glide.with(context).asBitmap()
//                    .load(photoUrl).into(100,100).get();
//            savePicture(context,bitmap,"");
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void savePicture(Context context ,Bitmap bm, String fileName) {
//        Log.i("xing", "savePicture: ------------------------");
//        if (null == bm) {
//            Log.i("xing", "savePicture: ------------------图片为空------");
//            return;
//        }
//        //建立指定文件夹
//        File foder = new File(Environment.getExternalStorageDirectory() , "zzp_sale");
//        if (!foder.exists()) {
//            foder.mkdirs();
//        }
//        File myCaptureFile = new File(foder, fileName);
//        try {
//            if (!myCaptureFile.exists()) {
//                myCaptureFile.createNewFile();
//            }
//            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
//            //压缩保存到本地
//            bm.compress(Bitmap.CompressFormat.JPEG, 90, bos);
//            bos.flush();
//            bos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    myCaptureFile.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + myCaptureFile.getPath())));
//
//        Toast.makeText(context, "保存成功!", Toast.LENGTH_SHORT).show();
//
//    }

    //方法一：下载web服务器图片
    public void getPictureByHttpURLConnection(String url) {
        Bitmap bmp = null;
        try {
            URL url1 = new URL( url );
            conn1 = (HttpURLConnection) url1.openConnection();
            conn1.setConnectTimeout( 5000 );
            conn1.setRequestMethod( "GET" );
            if (conn1.getResponseCode() == 200) {
                inStrem = conn1.getInputStream();
                bmp = BitmapFactory.decodeStream( inStrem );
                myHandler.obtainMessage( 0, bmp ).sendToTarget();
                int result = inStrem.read();
                while (result != -1) {
                    result = inStrem.read();
                }
                inStrem.close();
                Message msg = new Message();
                msg.what = SIGNIN_MSG_HTTPURLCONNECTION;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    //方法二：下载web服务器图片
    public void getPictureByOkHttp(String url, final int requestCode) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url( url )
                .build();
        client.newCall( request ).enqueue( new Callback() {
            public void onFailure(Call call, IOException e) {

            }

            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();//得到图片的流
                Bitmap bitmap = BitmapFactory.decodeStream( inputStream );
                Message msg = new Message();
                msg.obj = bitmap;
                msg.what = requestCode;
                myHandler.sendMessage( msg );
            }
        } );

    }

    /**
     *
     */
    class HHandler extends Handler {
        public void handleMessage(Message msg) {
            super.handleMessage( msg );
            switch (msg.what) {
                case SIGNIN_MSG_HTTPURLCONNECTION:
                    Bitmap bitmap=scaleZip((Bitmap) msg.obj);
                    break;
                case SIGNIN_MSG_OKHTTP:
                    Bitmap bitmap1=scaleZip((Bitmap) msg.obj);
                    break;
            }
        }
    }

    //打开相册
    public static void openAlbum(Activity activity) {
//        Intent intent2=new Intent("android.intent.action.GET_CONTENT");
        Intent intent3 = new Intent( Intent.ACTION_GET_CONTENT );
        intent3.setType( "image/*" );
        activity.startActivityForResult( intent3, CHOOSE_ALBUM );
    }


    /**
     * 将图片缩放
     * @param bitmap 要缩放的图片
     * @return 缩放后的图片
     */
    private Bitmap scaleZip(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setScale( 0.3f, 0.3f );
        bitmap = Bitmap.createBitmap( bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true );
        Log.i( "wechat", "压缩后图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight() );
        return bitmap;
    }


    /**
     * 当api>=19,用此方法获取本地图片
     * @param activity  活动
     * @param data 返回的数据
     * @return 返回本地的图片路径
     */
    public static String handleImageOnKitKat(Activity activity, Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri( activity, uri )) {
            String docId = DocumentsContract.getDocumentId( uri );
            if ("com.android.providers.media.documents".equals( uri.getAuthority() )) {
                Log.d( "tag", docId );
                String id = docId.split( ":" )[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath( activity, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection );
            } else if ("com.android.providers.downloads.documents".equals( uri.getAuthority() )) {
                Uri contentUri = ContentUris.withAppendedId( Uri.parse( "content://downloads/public_downloads" ),
                        Long.valueOf( docId ) );
                imagePath = getImagePath( activity, contentUri, null );
            }
        } else if ("content".equalsIgnoreCase( uri.getScheme() )) {
            imagePath = getImagePath( activity, uri, null );
        } else if ("file".equalsIgnoreCase( uri.getScheme() )) {
            imagePath = uri.getPath();
        }
        return imagePath;
//        displayImage(imagePath);
    }

    /**
     * 解析系统返回的图片路径
     * @param activity 活动
     * @param uri 获取到的本地图片路径
     * @param selection
     * @return 返回真是的本地图片路径
     */
    public static String getImagePath(Activity activity, Uri uri, String selection) {
        String path = null;
        Cursor cursor = activity.getContentResolver().query( uri, null, selection, null, null );
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString( cursor.getColumnIndex( MediaStore.Images.Media.DATA ) );
            }
            cursor.close();
        }
        return path;
    }

    /**
     * 当api<19,用此方法获取本地图片
     * @param activity  活动
     * @param data 返回的数据
     * @return 返回本地的图片路径
     */
    public static String handleImageBeforeKitKat(Activity activity, Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath( activity, uri, null );//自定义方法
        return imagePath;
//        displayImage(imagePath);//自定义方法
    }

}
