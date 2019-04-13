package cn.edu.jssvc.gezhi.colorlife.my.setting;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import cn.edu.jssvc.gezhi.colorlife.MyApplication;
import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.bean.MemberInfo;
import cn.edu.jssvc.gezhi.colorlife.db.DbDao;
import cn.edu.jssvc.gezhi.colorlife.http.WebApi;
import cn.edu.jssvc.gezhi.colorlife.http.WebListener;
import cn.edu.jssvc.gezhi.colorlife.photo.PhotoUtil;
import cn.edu.jssvc.gezhi.colorlife.util.Shared;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "RegisterActivity";

    private TextView textView_name;    //名字
    private ImageView setting_back_img;
    private CircleImageView setting_headimg_img;
    private EditText setting_password_et;
    private EditText setting_password2_et;
    private EditText setting_qq_et;
    private EditText setting_telephone_et;
    private EditText setting_hobby_et;
    private RadioButton setting_sex_nan_radiobtn;
    private RadioButton setting_sex_nv_radiobtn;
    private RadioGroup setting_sex_radiogroup;
    private EditText setting_birthday_et;
    private EditText setting_address_et;
    private Button setting_submit_btn;
    private Button setting_cancel_btn;

    private String imagePath;//准备上传的本地图片路径
    private DbDao dbDao;
    private Connection conn;
    private String iisUrl = "http://123.206.19.177:8006/api/appservice/asyupload";
    private String imgUrlPath = "http://123.206.19.177:8006/file/head";
    private String imgFileName;
    private String sex = "未知";
    private MemberInfo memberInfo;
    private boolean isSuccesful = false;//上传是否成功

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        textView_name.setText("更改资料的账号为：   " + Shared.getString(SettingActivity.this, "account", ""));

    }

    private void initView() {
        setting_back_img = (ImageView) findViewById(R.id.setting_back_img);
        setting_headimg_img = (CircleImageView) findViewById(R.id.setting_headimg_img);
        textView_name = (TextView) findViewById(R.id.setting_name_tv);
        setting_password_et = (EditText) findViewById(R.id.setting_password_et);
        setting_password2_et = (EditText) findViewById(R.id.setting_password2_et);
        setting_qq_et = (EditText) findViewById(R.id.setting_qq_et);
        setting_telephone_et = (EditText) findViewById(R.id.setting_telephone_et);
        setting_hobby_et = (EditText) findViewById(R.id.setting_hobby_et);
        setting_sex_nan_radiobtn = (RadioButton) findViewById(R.id.setting_sex_nan_radiobtn);
        setting_sex_nv_radiobtn = (RadioButton) findViewById(R.id.setting_sex_nv_radiobtn);
        setting_sex_radiogroup = (RadioGroup) findViewById(R.id.setting_sex_radiogroup);
        setting_birthday_et = (EditText) findViewById(R.id.setting_birthday_et);
        setting_address_et = (EditText) findViewById(R.id.setting_address_et);
        setting_submit_btn = (Button) findViewById(R.id.setting_submit_btn);
        setting_cancel_btn = (Button) findViewById(R.id.setting_cancel_btn);

        setting_back_img.setOnClickListener(this);
        setting_headimg_img.setOnClickListener(this);
        setting_submit_btn.setOnClickListener(this);
        setting_cancel_btn.setOnClickListener(this);
        dbDao = new DbDao();
        memberInfo = new MemberInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_headimg_img:
                if (isGrantedPermission(this)) {
                    PhotoUtil.openAlbum(this);
                }
                break;
            case R.id.setting_submit_btn:
                submit();
                break;
            case R.id.setting_cancel_btn:
                finish();
            case R.id.setting_back_img:
                finish();
                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        final String password = setting_password_et.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password2 = setting_password2_et.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }else if(!password.equals(password2)){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
        }

        String qq = setting_qq_et.getText().toString().trim();
//        if (TextUtils.isEmpty(qq)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }

        final String telephone = setting_telephone_et.getText().toString().trim();
//        if (TextUtils.isEmpty(telephone)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }

        final String hobby = setting_hobby_et.getText().toString().trim();
//        if (TextUtils.isEmpty(hobby)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }

        if (setting_sex_radiogroup.getCheckedRadioButtonId() == R.id.singup_sex_nan_radiobtn) {
            sex = "男";
        } else {
            sex = "女";
        }

        final String birthday = setting_birthday_et.getText().toString().trim();
//        if (TextUtils.isEmpty(birthday)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }

        final String address = setting_address_et.getText().toString().trim();
//        if (TextUtils.isEmpty(address)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }
        memberInfo.setPhotoUrl(imgUrlPath + File.separator + imgFileName);
        memberInfo.setPassword(password);
        memberInfo.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        memberInfo.setPhone(telephone);
        memberInfo.setHobbies(hobby);
        memberInfo.setSex(sex);
        memberInfo.setBirthday(birthday);
        memberInfo.setPostalAddress(address);
        conn = dbDao.getConn();
        try {
            WebApi.getInstance().uploadImage(imagePath, WebApi.TAG_HEAD, iisUrl, imgFileName, new WebListener() {
                @Override
                public void onSuccess() {
                    Future<Boolean> future = MyApplication.executorService.submit(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return dbDao.insertMemberInfoDataRegister(memberInfo,textView_name.getText().toString());
                        }
                    });
                    try {
                        if(future.get()){
                            conn.close();
                            finish();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure() {

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
                        displayImage(imagePath);
                    } else {
                        imagePath = PhotoUtil.handleImageBeforeKitKat(this, data);
                        displayImage(imagePath);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * @param imagePath 根据获取到的本地图片路径，返回一个Bitmap
     */
    private void displayImage(final String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            setting_headimg_img.setImageBitmap(bitmap);
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

    public boolean isGrantedPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }
        return true;
    }

}
