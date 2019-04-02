package cn.edu.jssvc.gezhi.colorlife.my;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.support.design.widget.TabLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.home.HomeFragment;

public class MyFragment extends Fragment {
    private View view;
    private TextView nameTv;//用户名
    private TextView myHomeTv;//我的主页
    private TextView gradeTv;//等级
    private TextView followTv;//关注
    private TextView fansTv;//粉丝
    private TextView fabulousTv;//赞
    private TextView setTv;//设置
    private ImageView headImg;//头像
    private Button mallBtn;//商城
    private TabLayout tableLayout;//存放三个标题
    private List<String> titleList;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;//保存三个碎片

    private RelativeLayout headLayout;
    private AlphaAnimation mShowAnim, mHiddenAmin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        initView();
        initData();
        setListener();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView(){
        //控件显示的动画
        mShowAnim = new AlphaAnimation(0.0f, 1.0f);
        mShowAnim.setDuration(300);

        //控件隐藏的动画
        mHiddenAmin = new AlphaAnimation(1.0f, 0.0f);
        mHiddenAmin.setDuration(300);
        headLayout = (RelativeLayout) view.findViewById(R.id.fragmy_head_layout);


        nameTv = (TextView) view.findViewById(R.id.fragmy_user_name_tv);
        myHomeTv = (TextView) view.findViewById(R.id.fragmy_myhome_tv);
        gradeTv = (TextView) view.findViewById(R.id.fragmy_grade_tv);
        followTv = (TextView) view.findViewById(R.id.fragmy_head_follow_tv);
        fansTv = (TextView) view.findViewById(R.id.fragmy_head_fans_tv);
        fabulousTv = (TextView) view.findViewById(R.id.fragmy_head_fabulous_tv);
        setTv = (TextView) view.findViewById(R.id.fragmy_seting_tv);
        headImg = (ImageView) view.findViewById(R.id.fragmy_head_img);
        mallBtn = (Button) view.findViewById(R.id.fragmy_mall_btn);
        tableLayout = (TabLayout) view.findViewById(R.id.fragmy_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.fragmy_viewpager);
    }

    /**
     * 设置控件的监听事件
     */
    private void setListener(){
        mallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headLayout.startAnimation(mHiddenAmin);
                headLayout.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 初始化一些数据
     */
    private void initData(){
        titleList = new ArrayList<>();
        titleList.add("动态");
        titleList.add("藏品");
        titleList.add("作品");
        fragmentList = new ArrayList<>();
        fragmentList.add(new My_item1Fragment());
        fragmentList.add(new My_item2Fragment());
        fragmentList.add(new My_item3Fragment());
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if(manager != null){
            viewPager.setAdapter(new MyPagerAdapter(manager,getActivity(),fragmentList,titleList));
        }
        tableLayout.setupWithViewPager(viewPager,true);
    }

}
