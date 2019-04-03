package cn.edu.jssvc.gezhi.colorlife.my;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class My_item1Fragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private Item1_Adapter adapter;
    private List<Item1_Bean> beanList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_item1, container, false);
        initView();
        initData();
        setListener();
        return view;
    }

    private void initView(){
        beanList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.myfrag_item1_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new Item1_Adapter(getContext(), beanList);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        beanList.add(new Item1_Bean("", "一口蒸鸡蛋", "", 24, 35, 62));
        adapter.notifyDataSetChanged();
    }

    private void setListener(){}

}