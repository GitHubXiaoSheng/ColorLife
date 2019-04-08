package cn.edu.jssvc.gezhi.colorlife.share;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class TabFragmentAdapter extends FragmentStatePagerAdapter {
    List<String> titileList;
    List<Fragment> fmList;

    public TabFragmentAdapter(FragmentManager fm, List<Fragment> fmlist, List<String> titlelist){
        super(fm);
        this.fmList=fmlist;
        this.titileList=titlelist;
    }
    @Override
    public Fragment getItem(int i) {
        return fmList.get(i);
    }

    @Override
    public int getCount() {
        if (fmList==null) {
            return 0;
        }else {
            return fmList.size();
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titileList.get(position);
    }

}
