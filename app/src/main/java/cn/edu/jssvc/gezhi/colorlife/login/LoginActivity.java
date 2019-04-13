package cn.edu.jssvc.gezhi.colorlife.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import cn.edu.jssvc.gezhi.colorlife.MainActivity;
import cn.edu.jssvc.gezhi.colorlife.MyApplication;
import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.bean.MemberInfo;
import cn.edu.jssvc.gezhi.colorlife.db.DbDao;
import cn.edu.jssvc.gezhi.colorlife.photo.PhotoUtil;
import cn.edu.jssvc.gezhi.colorlife.register.RegisterActivity;
import cn.edu.jssvc.gezhi.colorlife.util.Shared;
import de.hdodenhof.circleimageview.CircleImageView;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "LoginActivity";


    private CircleImageView login_headimg;
    private EditText login_account;
    private EditText login_password;
    private CheckBox login_remember;
    private TextView login_forget;
    private Button login_sign;
    private TextView login_register;
    private TextView login_thirdlogin;

    private DbDao dbDao;
    private MemberInfo memberInfo;
    private List<MemberInfo> memberInfoList = new ArrayList<>();
    private Connection conn;
    private ImageView login_back_img;

    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        initView();
    }

    private void initView() {
        login_headimg = (CircleImageView) findViewById(R.id.login_headimg);
        login_account = (EditText) findViewById(R.id.login_account);
        login_password = (EditText) findViewById(R.id.login_password);
        login_remember = (CheckBox) findViewById(R.id.login_remember);
        login_forget = (TextView) findViewById(R.id.login_forget);
        login_sign = (Button) findViewById(R.id.login_sign);
        login_register = (TextView) findViewById(R.id.login_register);
        login_thirdlogin = (TextView) findViewById(R.id.login_thirdlogin);
        login_back_img = (ImageView) findViewById(R.id.login_back_img);

        login_sign.setOnClickListener(this);

        dbDao = new DbDao();
        memberInfo = new MemberInfo();

        login_back_img.setOnClickListener(this);
        login_register.setOnClickListener(this);

        imgUrl = Shared.getString(this, "accountPhoto", "");
        if(!TextUtils.isEmpty(imgUrl)){
            Glide.with(this).load(imgUrl).into(login_headimg);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_sign:
                submit();
                break;
            case R.id.login_back_img:
                finish();
                break;
            case R.id.login_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,2);
                break;
            default:
                break;
        }
    }

    private void submit() {
        // validate
        final String account = login_account.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }

        final String password = login_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        conn = dbDao.getConn();
        if (conn != null) {
            if (loginProving(account, password)) {
                try {
                    conn.close();
                    Shared.saveInt(LoginActivity.this,"accountId",memberInfo.getId());
                    Shared.saveInt(LoginActivity.this,"accountlevel",memberInfo.getLevel());
                    Shared.saveString(LoginActivity.this,"account",memberInfo.getNickName());
                    Shared.saveString(LoginActivity.this,"accountPhoto",memberInfo.getPhotoUrl());
                    Intent intent = new Intent();
                    intent.putExtra("memberInfo", memberInfo);
                    setResult(RESULT_OK, intent);
                    finish();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            if(data != null){
                MemberInfo memberInfo = (MemberInfo) data.getSerializableExtra("register");
                if(memberInfo != null){
                    dbDao = new DbDao();
                    login_account.setText(memberInfo.getNickName());
                    login_password.setText(memberInfo.getPassword());
                }
            }
        }
    }


    /**
     * 验证用户是否存在
     * @param account 账号
     * @param password 密码
     * @return 返回能否登录
     */
    private boolean loginProving(final String account, final String password){
        Future<MemberInfo> future = MyApplication.executorService.submit(new Callable<MemberInfo>() {
            @Override
            public MemberInfo call() throws Exception {
                return dbDao.queryMemberInfo(account,password);
            }
        });
        try {
//            Log.d(TAG, "testE: "+future.get().toString());
            if(future.get()!=null){
                memberInfo = future.get();
                return true;
            }else {
                Toast.makeText(this,"账号或者密码错误！",Toast.LENGTH_SHORT).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
