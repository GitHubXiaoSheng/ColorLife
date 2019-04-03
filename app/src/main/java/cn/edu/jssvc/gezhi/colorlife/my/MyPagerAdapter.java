package cn.edu.jssvc.gezhi.colorlife.my;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 这是“我的”模块的viewpager组件的适配器
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private Context contxt;
    private List<Fragment> fragmentList;
    private List<String> list_Title;
    public MyPagerAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList, List<String> list_Title) {
        super(fm);
        contxt = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }
}
