package cn.edu.jssvc.gezhi.colorlife.register;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import cn.edu.jssvc.gezhi.colorlife.db.DbConnection;
import cn.edu.jssvc.gezhi.colorlife.db.DbDao;
import cn.edu.jssvc.gezhi.colorlife.http.WebApi;
import cn.edu.jssvc.gezhi.colorlife.http.WebListener;
import cn.edu.jssvc.gezhi.colorlife.photo.PhotoUtil;
import cn.edu.jssvc.gezhi.colorlife.util.Shared;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "RegisterActivity";

    private ImageView register_back_img;
    private CircleImageView singup_headimg_img;
    private EditText singup_nickname_et;
    private EditText singup_password_et;
    private EditText signup_password2_et;
    private EditText signup_qq_et;
    private EditText signup_telephone_et;
    private EditText signup_hobby_et;
    private RadioButton singup_sex_nan_radiobtn;
    private RadioButton singup_sex_nv_radiobtn;
    private RadioGroup singup_sex_radiogroup;
    private EditText signup_birthday_et;
    private EditText signup_address_et;
    private Button signup_submit_btn;
    private Button singup_cancel_btn;

    private String imagePath;//准备上传的本地图片路径
    private DbDao dbDao;
    private Connection conn;
    private String iisUrl = "http://123.206.19.177:8006/api/appservice/asyupload";
    private String imgUrlPath = "http://123.206.19.177:8006/file/head";
    private String sex = "未知";
    private String imgFileName;
    private MemberInfo memberInfo;
    private boolean isSuccesful = false;//上传是否成功

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_layout);
        initView();
    }

    private void initView() {
        register_back_img = (ImageView) findViewById(R.id.register_back_img);
        singup_headimg_img = (CircleImageView) findViewById(R.id.singup_headimg_img);
        singup_nickname_et = (EditText) findViewById(R.id.singup_nickname_et);
        singup_password_et = (EditText) findViewById(R.id.singup_password_et);
        signup_password2_et = (EditText) findViewById(R.id.signup_password2_et);
        signup_qq_et = (EditText) findViewById(R.id.signup_qq_et);
        signup_telephone_et = (EditText) findViewById(R.id.signup_telephone_et);
        signup_hobby_et = (EditText) findViewById(R.id.signup_hobby_et);
        singup_sex_nan_radiobtn = (RadioButton) findViewById(R.id.singup_sex_nan_radiobtn);
        singup_sex_nv_radiobtn = (RadioButton) findViewById(R.id.singup_sex_nv_radiobtn);
        singup_sex_radiogroup = (RadioGroup) findViewById(R.id.singup_sex_radiogroup);
        signup_birthday_et = (EditText) findViewById(R.id.signup_birthday_et);
        signup_address_et = (EditText) findViewById(R.id.signup_address_et);
        signup_submit_btn = (Button) findViewById(R.id.signup_submit_btn);
        singup_cancel_btn = (Button) findViewById(R.id.singup_cancel_btn);

        register_back_img.setOnClickListener(this);
        singup_headimg_img.setOnClickListener(this);
        signup_submit_btn.setOnClickListener(this);
        singup_cancel_btn.setOnClickListener(this);
        dbDao = new DbDao();
        memberInfo = new MemberInfo();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singup_headimg_img:
                if (isGrantedPermission(this)) {
                    PhotoUtil.openAlbum(this);
                }
                break;
            case R.id.signup_submit_btn:
                submit();
                break;
            case R.id.singup_cancel_btn:
            case R.id.register_back_img:
                finish();
                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        final String nickname = singup_nickname_et.getText().toString().trim();
        if (TextUtils.isEmpty(nickname)) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        final String password = singup_password_et.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password2 = signup_password2_et.getText().toString().trim();
        if (TextUtils.isEmpty(password2)) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;
        }else if(!password.equals(password2)){
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
        }

        String qq = signup_qq_et.getText().toString().trim();
//        if (TextUtils.isEmpty(qq)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }

        final String telephone = signup_telephone_et.getText().toString().trim();
//        if (TextUtils.isEmpty(telephone)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }

        final String hobby = signup_hobby_et.getText().toString().trim();
//        if (TextUtils.isEmpty(hobby)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }

        if (singup_sex_radiogroup.getCheckedRadioButtonId() == R.id.singup_sex_nan_radiobtn) {
            sex = "男";
        } else {
            sex = "女";
        }

        final String birthday = signup_birthday_et.getText().toString().trim();
//        if (TextUtils.isEmpty(birthday)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }

        final String address = signup_address_et.getText().toString().trim();
//        if (TextUtils.isEmpty(address)) {
//            Toast.makeText(this, "et不能为空", Toast.LENGTH_SHORT).show();
////            return;
//        }
        memberInfo.setNickName(nickname);
        imgFileName = setFileName(nickname);
        memberInfo.setPhotoUrl(imgUrlPath + File.separator + imgFileName);
        memberInfo.setPassword(password);
        memberInfo.setRegisterDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        memberInfo.setPhone(telephone);
        memberInfo.setHobbies(hobby);
        memberInfo.setSex(sex);
        memberInfo.setBirthday(birthday);
        memberInfo.setPostalAddress(address);
        memberInfo.setPoints(100);
        memberInfo.setMatto("");
        memberInfo.setLevel(1);
        conn = dbDao.getConn();
        try {
            WebApi.getInstance().uploadImage(imagePath, WebApi.TAG_HEAD, iisUrl, imgFileName, new WebListener() {
                @Override
                public void onSuccess() {
                    Future<Boolean> future = MyApplication.executorService.submit(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return dbDao.insertMemberInfoData(memberInfo);
                        }
                    });
                    try {
                        if(future.get()){
                            Shared.saveString(RegisterActivity.this,"account",nickname);
                            Intent intent = new Intent();
                            intent.putExtra("register", memberInfo);
                            setResult(RESULT_OK, intent);
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
            singup_headimg_img.setImageBitmap(bitmap);
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
