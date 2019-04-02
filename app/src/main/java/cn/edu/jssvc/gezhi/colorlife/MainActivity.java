package cn.edu.jssvc.gezhi.colorlife;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.search.SearchFragment;
import cn.edu.jssvc.gezhi.colorlife.home.HomeFragment;
import cn.edu.jssvc.gezhi.colorlife.my.MyFragment;
import cn.edu.jssvc.gezhi.colorlife.share.ShareFragment;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MainActivity";

    private BottomNavigationView bottomNavigationView;
    private int index = 0;
    private Fragment mFrag;//当前附加的碎片
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ShareFragment shareFragment;
    private MyFragment myFragment;
    //存放主界面的四个碎片
    private List<Fragment> fragmentList;
    //静态变量用于返回主活动的指定碎片使用
    public static int MainHome = 0;
    public static int MainSearch = 1;
    public static int MainShare = 2;
    public static int MainMy = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = getIntent().getIntExtra("BackToMainAction", 0);
        initView();
        loadFragment(index);
    }

    private void initView(){
        fragmentList = new ArrayList<>();
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        shareFragment = new ShareFragment();
        myFragment = new MyFragment();
        fragmentList.add(homeFragment);
        fragmentList.add(searchFragment);
        fragmentList.add(shareFragment);
        fragmentList.add(myFragment);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.mian_bottomnavigationview);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.main_navigation_1:
                        loadFragment(0);
                        return true;
                    case R.id.main_navigation_2:
                        loadFragment(1);
                        return true;
                    case R.id.main_navigation_3:
                        loadFragment(2);
                        return true;
                    case R.id.main_navigation_4:
                        loadFragment(3);
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void loadFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        getSupportFragmentManager().getPrimaryNavigationFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentList.get(position);
            if(mFrag != null){
                transaction.hide(mFrag);
            }
        if(!fragment.isAdded()) {
            transaction.add(R.id.main_framelayout, fragment);
        } else {
            transaction.show(fragment);
        }
        mFrag = fragment;
        transaction.commit();
    }

}
