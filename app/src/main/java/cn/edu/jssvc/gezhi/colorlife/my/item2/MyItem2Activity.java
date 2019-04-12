package cn.edu.jssvc.gezhi.colorlife.my.item2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import cn.edu.jssvc.gezhi.colorlife.MyApplication;
import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.bean.ArtInfo;
import cn.edu.jssvc.gezhi.colorlife.db.DbConnection;
import cn.edu.jssvc.gezhi.colorlife.db.DbDao;
import cn.edu.jssvc.gezhi.colorlife.util.Shared;

public class MyItem2Activity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "My_item2Fragment";
    private ImageView myitem1_back_img;
    private RecyclerView recyclerView;
    private Item2_Adapter adapter;
    private List<Item2_Bean> beanList;

    private DbDao dbDao = new DbDao();
    private Connection conn;

    private int position;

    private List<ArtInfo> artInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_item2);
        position = getIntent().getIntExtra("itemIntent", 0);
        initView();
        initData();
    }

    private void initView() {
        beanList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.my_item1_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new Item2_Adapter(this, beanList);
        recyclerView.setAdapter(adapter);
        myitem1_back_img = (ImageView) findViewById(R.id.myitem1_back_img);
        myitem1_back_img.setOnClickListener(this);
    }

    private void initData() {
        Log.d(TAG, "queryAllArtInfo: "+position);
        switch (position) {
            case 1:
                for (int i = 0; i < 5; i++) {
                    beanList.add(new Item2_Bean("", "一口蒸鸡蛋","", "", 24, 35, 62));
                }
                break;
            case 4:
//                dbDao = new DbDao();
                conn = dbDao.getConn();
                try {
//                    if (conn == null) {
//                        Log.d(TAG, "initData: ");
//                        conn = DbConnection.getConnection();
//                    }
                    Future<Boolean> future = MyApplication.executorService.submit(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            conn = DbConnection.getConnection();
                            artInfoList = dbDao.queryAllArtInfo(Shared.getInt(MyItem2Activity.this, "accountId", -1));
                            String account = Shared.getString(MyItem2Activity.this, "account", "");
                            String imgUrl = Shared.getString(MyItem2Activity.this, "accountPhoto", "");
                            for(int i=0;i<artInfoList.size();i++){
                                ArtInfo artInfo = artInfoList.get(i);
                                beanList.add(new Item2_Bean(imgUrl,account,artInfo.getContent(),artInfo.getUrl(),45,45,56));
                            }
                            return true;
                        }
                    });
                    if(future.get()){
                        adapter.notifyDataSetChanged();
                        if(!conn.isClosed()){
                            conn.close();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
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
