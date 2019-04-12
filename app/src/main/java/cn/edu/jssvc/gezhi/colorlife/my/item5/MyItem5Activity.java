package cn.edu.jssvc.gezhi.colorlife.my.item5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import cn.edu.jssvc.gezhi.colorlife.MyApplication;
import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.bean.ArtInfo;
import cn.edu.jssvc.gezhi.colorlife.db.DbDao;
import cn.edu.jssvc.gezhi.colorlife.http.WebApi;
import cn.edu.jssvc.gezhi.colorlife.http.WebListener;
import cn.edu.jssvc.gezhi.colorlife.photo.PhotoUtil;
import cn.edu.jssvc.gezhi.colorlife.util.Shared;

public class MyItem5Activity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "上传作品";

    private ImageView my_item5_back_img;
    private ImageView my_item5_content_img;
    private Button my_item5_ok_btn;
    private EditText my_item5_title_et;
    private EditText my_item5_content_et;
//    private RecyclerView my_item5_recyclerview;
    private Spinner my_item5_category_spinner;
    private Spinner my_item5_theme_spinner;
    private EditText my_item5_price_ed;

//    private List<Bitmap> bitmapList;
//    private List<String> imgPathList;
//    private MyItem5Adapter adapter;

    private String iisUrl = "http://123.206.19.177:8006/api/appservice/asyupload";
    private String imgUrlPath = "http://123.206.19.177:8006/file/art";
    private String imgFileName;
    private String imagePath;

    private DbDao dbDao;
    private Connection conn;
    private ArtInfo artInfo;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item5_2);
        initView();
    }

    private void initView() {
        my_item5_back_img = (ImageView) findViewById(R.id.my_item5_back_img);
        my_item5_content_img = (ImageView) findViewById(R.id.my_item5_content_img);
        my_item5_back_img.setOnClickListener(this);
        my_item5_content_img.setOnClickListener(this);
        my_item5_ok_btn = (Button) findViewById(R.id.my_item5_ok_btn);
        my_item5_content_et = (EditText) findViewById(R.id.my_item5_content_et);
        my_item5_title_et = (EditText) findViewById(R.id.my_item5_maptitle_et);
        my_item5_category_spinner = (Spinner) findViewById(R.id.my_item5_category_spinner);
        my_item5_theme_spinner = (Spinner) findViewById(R.id.my_item5_theme_spinner);
//        bitmapList = new ArrayList<>();
//        imgPathList = new ArrayList<>();
//        my_item5_recyclerview = (RecyclerView) findViewById(R.id.my_item5_recyclerview);
//        my_item5_recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
//        adapter = new MyItem5Adapter(this, bitmapList);
//        my_item5_recyclerview.setAdapter(adapter);
        my_item5_ok_btn.setOnClickListener(this);
        my_item5_price_ed = (EditText) findViewById(R.id.my_item5_price_ed);
        my_item5_price_ed.setOnClickListener(this);

        dbDao = new DbDao();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_item5_ok_btn:
                submit();
                break;
            case R.id.my_item5_content_img:
                if(PhotoUtil.isGrantedPermission(MyItem5Activity.this)){
                    PhotoUtil.openAlbum(MyItem5Activity.this);
                }
                break;
            case R.id.my_item5_back_img:
                finish();
                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        String title = my_item5_title_et.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "为作品起一个名字！", Toast.LENGTH_SHORT).show();
            return;
        }
        String content = my_item5_content_et.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "解说一下作品吧！", Toast.LENGTH_SHORT).show();
            return;
        }
        String price = my_item5_price_ed.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            Toast.makeText(this, "价格还没有填写哦，不要钱就填写0！", Toast.LENGTH_SHORT).show();
            return;
        }
        int accountId = Shared.getInt(this, "accountId", -1);
        String account = Shared.getString(this, "account", "");
        Log.d(TAG, "submit: "+accountId);
        if(accountId == -1 || TextUtils.isEmpty(account)){
            Toast.makeText(this, "你还没有登录哦！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(imagePath)) {
            Toast.makeText(this, "你还没有选择上传的照片！", Toast.LENGTH_SHORT).show();
            return;
        }
        imgFileName = setFileName(account);
        artInfo = new ArtInfo();
        artInfo.setContent(content);
        artInfo.setAuthorId(accountId);
        artInfo.setUrl(imgUrlPath + File.separator + imgFileName);
        artInfo.setClassifyId(my_item5_category_spinner.getSelectedItemPosition()+1);
        artInfo.setThemeId(my_item5_theme_spinner.getSelectedItemPosition()+1);
        artInfo.setPrice(Float.parseFloat(price));
        artInfo.setMapTitle(title);
        artInfo.setCreateData(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        conn = dbDao.getConn();
//        for (int i=0;i<imgPathList.size();i++){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("正在上传...");
        progressDialog.setMessage("上传中...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
            try {
                WebApi.getInstance().uploadImage(imagePath, WebApi.TAG_ART, iisUrl, imgFileName, new WebListener() {
                    @Override
                    public void onSuccess(){
                        Future<Boolean> future = MyApplication.executorService.submit(new Callable<Boolean>() {
                            @Override
                            public Boolean call() throws Exception {
                                return dbDao.insertArtInfoData(artInfo);
                            }
                        });
                        try {
                            if(future.get()){
                                conn.close();
                                Log.d(TAG, "onSuccess: 上传成功！");
                                progressDialog.dismiss();
                                Toast.makeText(MyItem5Activity.this,"上传成功！",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(MyItem5Activity.this,"上传失败！",Toast.LENGTH_SHORT).show();
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure() {
                        progressDialog.dismiss();
                        Toast.makeText(MyItem5Activity.this,"上传失败！",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PhotoUtil.CHOOSE_ALBUM:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        imagePath = PhotoUtil.handleImageOnKitKat(this, data);
                        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                        my_item5_content_img.setImageBitmap(bitmap);
//                        bitmapList.add(bitmap);
//                        imgPathList.add(imagePath);
                    } else {
                        imagePath = PhotoUtil.handleImageBeforeKitKat(this, data);
                        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                        my_item5_content_img.setImageBitmap(bitmap);

//                        bitmapList.add(bitmap);
//                        imgPathList.add(imagePath);
                    }

//                    adapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    /**
     * @param nickName 呢称
     * @return 根据昵称生成图片名称
     */
    private String setFileName(String nickName) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmSS");//获取当前时间，进一步转化为字符串
        String fileName = format.format(date);
        Log.d("tag-filename", fileName);
        return nickName + fileName + ".jpg";
    }

}
