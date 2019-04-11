package cn.edu.jssvc.gezhi.colorlife;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.db.DbConnection;
import cn.edu.jssvc.gezhi.colorlife.db.DbDao;
import cn.edu.jssvc.gezhi.colorlife.home.Arts_info;
import cn.edu.jssvc.gezhi.colorlife.home.HomeFragment;
import cn.edu.jssvc.gezhi.colorlife.my.MyFragment;
import cn.edu.jssvc.gezhi.colorlife.search.SearchFragment;
import cn.edu.jssvc.gezhi.colorlife.share.ShareFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private int index = 0;
    //静态变量用于返回主活动的指定碎片使用
    public static int MainHome = R.id.main_navigation_1;
    public static int MainEntry = R.id.main_navigation_2;
    public static int MainShare = R.id.main_navigation_3;
    public static int MainMy = R.id.main_navigation_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = getIntent().getIntExtra("BackToMainAction", R.id.main_navigation_1);
        initView();
        replaceFragment(index);
    }

    private void initView(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.mian_bottomnavigationview);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                replaceFragment(menuItem.getItemId());
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        DbDao dbDao = new DbDao();
    }

    private void replaceFragment(int index){
        switch (index) {
            case R.id.main_navigation_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new HomeFragment()).commit();
                break;
            case R.id.main_navigation_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new SearchFragment()).commit();
                break;
            case R.id.main_navigation_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new ShareFragment()).commit();
                break;
            case R.id.main_navigation_4:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout, new MyFragment()).commit();
                break;
            default:
                break;
        }

    }

}
