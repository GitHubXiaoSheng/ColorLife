package cn.edu.jssvc.gezhi.colorlife.share;


import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.view.ViewPager;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;


import java.util.ArrayList;

import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;


public class Yws_Hot_Fragment extends Fragment {
    private ViewPager viewPager;

    private List<String> list = new ArrayList<String>();

    private List<Fragment> listFragment = new ArrayList<Fragment>();

    private MyAdapter adapter;

    private TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yws_fragment_hot, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
    }
    void  init(){
        tabLayout = (TabLayout) getActivity().findViewById(R.id.tabLayout);
        viewPager = (ViewPager)getActivity(). findViewById(R.id.viewPager);
        for (int i=0; i<8; i++){
            list.add("推荐");
            list.add("同城");
            list.add("榜单");
            list.add("历史人物");
            list.add("名人画史");
            list.add("青春纪念册");
            list.add("更多");

            Yws_Remcommend_Fragment testFragment = new Yws_Remcommend_Fragment();
            testFragment.initView(list.get(i));
            listFragment.add(testFragment);

        }



        adapter = new MyAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
//        setIndicator(getContext(),tabLayout,10,10);

    }
    private class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);

        }
        @Override

        public Fragment getItem(int position) {
            return listFragment.get(position);

        }

        @Override

        public int getCount() {
            return listFragment.size();

        }


        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position );

        }

    }
//    public void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
//
//        //通过反射获取tabLayout的类对象
//        Class<?> tabLayout = tabs.getClass();
//        Field tabStrip = null;
//        try {
//
//            //获取tabLayout一个名叫mTabStrip的属性字段
//            tabStrip = tabLayout.getDeclaredField("mTabStrip");
//
//        } catch (NoSuchFieldException e) {
//
//            e.printStackTrace();
//
//        }
//
//        //设置tabStrip属性可以被调用，如果不设置为true，该属性为private时候，会报错
//
//        tabStrip.setAccessible(true);
//
//        LinearLayout ll_tab = null;
//
//        try {
//
//            //获取tabStrip这个字段对应的属性对象，
//
//            //这里可以看出，tabayout底部的下划线应该是一个linearLayout或者linearLayout的子类
//
//            ll_tab = (LinearLayout) tabStrip.get(tabs);
//
//        } catch (IllegalAccessException e) {
//
//            e.printStackTrace();
//
//        }
//
//        //进行dp到px的数值转换
//
//        int left  = dip2px(leftDip);
//        int right = dip2px(rightDip);
//
//
//
//        //一次获取每个tabItem的下划线对象并设置该下划线和左右两侧的距离
//        for (int i = 0; i < ll_tab.getChildCount(); i++) {
//            View child = ll_tab.getChildAt(i);
//            child.setPadding(0, 0, 0, 0);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//            params.leftMargin =  left;
//            params.rightMargin = right;
//            child.setLayoutParams(params);
//            child.invalidate();
//
//        }
//
//    }

    /**

     * dip转换成px

     */

//    public int dip2px(float dpValue) {
//
//        //获取当前设备像素密度
//        final float scale = getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
//
//    }
}
