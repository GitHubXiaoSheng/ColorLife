package cn.edu.jssvc.gezhi.colorlife.share;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;


public class Yws_Remcommend_Fragment extends Fragment {
    private View view;
    private List<Yws_Recommend> myremmondlist = new ArrayList<>();


    private String title;

    public void initView(String title) {
        this.title = title;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView tv = new TextView(getActivity());
//        tv.setText(title);
//        tv.setTextColor(Color.RED);
//        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        tv.setGravity(Gravity.CENTER);
//        return tv;
        view = inflater.inflate(R.layout.fragment_text_view, container, false);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recommend_recyclerview);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager1);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        Yws_recommend_adapter yws_recommend_adapter = new Yws_recommend_adapter(myremmondlist);
        recyclerView.setAdapter(yws_recommend_adapter);
        showlist();
    }

    void showlist() {
        for (int i = 0; i < 8; i++) {
            Yws_Recommend yws_recommend1 = new Yws_Recommend("很久以后87", "37分钟前", "今天还成为很多游客手机里面的一道风景线", "220", "5", "7", R.drawable.head3, R.drawable.follow1, R.drawable.follow2, R.drawable.follow4, R.drawable.follow8, R.drawable.follow5, R.drawable.follow6jpg);
            myremmondlist.add(yws_recommend1);
            Yws_Recommend yws_recommend2 = new Yws_Recommend("爱zzz的胖琪", "1小时前", "今天还成为很多游客手机里面的一道风景线", "50", "45", "60", R.drawable.head4, R.drawable.follow1, R.drawable.follow3, R.drawable.follow4, R.drawable.follow8, R.drawable.follow5, R.drawable.follow4);
            myremmondlist.add(yws_recommend2);
        }
    }
}
