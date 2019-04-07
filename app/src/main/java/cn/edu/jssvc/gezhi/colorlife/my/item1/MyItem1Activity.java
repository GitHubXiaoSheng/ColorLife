package cn.edu.jssvc.gezhi.colorlife.my.item1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class MyItem1Activity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "My_item1Fragment";
    private ImageView myitem1_back_img;
    private RecyclerView recyclerView;
    private Item1_Adapter adapter;
    private List<Item1_Bean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item1);
        initView();
        initData();
    }

    private void initView() {
        beanList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.my_item1_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new Item1_Adapter(this, beanList);
        recyclerView.setAdapter(adapter);
        myitem1_back_img = (ImageView) findViewById(R.id.myitem1_back_img);
        myitem1_back_img.setOnClickListener(this);
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            beanList.add(new Item1_Bean("", "一口蒸鸡蛋", "", 24, 35, 62));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myitem1_back_img:
                finish();
                break;
            default:
                break;
        }
    }
}
