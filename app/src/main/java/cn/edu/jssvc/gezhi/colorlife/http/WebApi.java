package cn.edu.jssvc.gezhi.colorlife.http;

import android.util.Log;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/4/6.
 */

public class WebApi {
    private WebListener mWebListener = null;
    private static final WebApi ourInstance = new WebApi();
    public static String TAG_ART = "art";
    public static String TAG_HEAD = "head";

    public static WebApi getInstance() {
        return ourInstance;
    }

    public WebApi() {
    }

    public void init(WebListener webListener){
        mWebListener = webListener;
    }


    /**
     * @param imagePath 图片的本地url
     * @param tag head 代表上传的是头像，art代表上传的是艺术品
     * @param url 上传的服务器路径
     * @param imgFileName 文件名称
     * @param webListener 回调监听
     * @throws IOException
     * @throws JSONException
     */
    public void uploadImage(String imagePath, String tag,String url, String imgFileName, final WebListener webListener) throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();
//        String postUrl = "http://192.168.43.83:8081/OkHttpServer";
//        Log.d("imagePath", imagePath);
        File file = new File( imagePath );
        RequestBody image = RequestBody.create( MediaType.parse( "image/*" ), file );
        RequestBody requestBody = new MultipartBody.Builder()
                .setType( MultipartBody.FORM )
                .addFormDataPart("file", imgFileName, image )
                .addFormDataPart("pattern",tag)
                .build();
        Request request = new Request.Builder()
                .url( url )
                .post( requestBody )
                .build();
        okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        okHttpClient = httpBuilder.build();
        okHttpClient.newCall( request ).enqueue( new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d( "tag-okhttpfailure", e.getMessage() );
                webListener.onFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String htmlStr = response.body().string();
                Log.d( "tag-response", htmlStr );
                if(response.code()==200){
//                    signUpSuccess();
                    webListener.onSuccess();
                }
                else
                {
                    webListener.onFailure();
                }
            }
        } );
//        Response response = okHttpClient.newCall(request).execute();
//        JSONObject jsonObject = new JSONObject(response.body().string());
//        return jsonObject.optString("image");
    }
}
