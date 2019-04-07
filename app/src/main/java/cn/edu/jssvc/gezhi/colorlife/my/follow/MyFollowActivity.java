package cn.edu.jssvc.gezhi.colorlife.my.follow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class MyFollowActivity extends AppCompatActivity {
    private String TAG = "MyFollowActivity";
    private ImageView myfollow_back_img;
    private TextView myfollow_title_tv;
    private SearchView myfollow_search;
    private RecyclerView myfollow_recyclerview;
    private LinearLayoutManager manager;
    private MyFollowAdapter adapter;
    private List<MyFollowItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_follow);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        myfollow_back_img = (ImageView) findViewById(R.id.myfollow_back_img);
        myfollow_title_tv = (TextView) findViewById(R.id.myfollow_title_tv);
        myfollow_search = (SearchView) findViewById(R.id.myfollow_search);


        itemList = new ArrayList<>();
        myfollow_recyclerview = (RecyclerView) findViewById(R.id.myfollow_recyclerview);
        manager = new LinearLayoutManager(this);
        myfollow_recyclerview.setLayoutManager(manager);
        adapter = new MyFollowAdapter(this, itemList);
        myfollow_recyclerview.setAdapter(adapter);

    }

    private void initData() {
        itemList.add(new MyFollowItem("", "张三", "这是张三的账号", true));
        itemList.add(new MyFollowItem("", "李四", "这是李四的账号", true));
        itemList.add(new MyFollowItem("", "刘备", "这是刘备的账号", true));
        itemList.add(new MyFollowItem("", "曹操", "这是曹操的账号", true));
        adapter.notifyDataSetChanged();
    }

    private void setListener(){
        myfollow_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
