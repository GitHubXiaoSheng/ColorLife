package cn.edu.jssvc.gezhi.colorlife.my.item2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import cn.edu.jssvc.gezhi.colorlife.bean.Collector;
import cn.edu.jssvc.gezhi.colorlife.bean.MemberInfo;
import cn.edu.jssvc.gezhi.colorlife.db.DbConnection;
import cn.edu.jssvc.gezhi.colorlife.db.DbDao;
import cn.edu.jssvc.gezhi.colorlife.home.Arts_info;
import cn.edu.jssvc.gezhi.colorlife.util.Shared;

public class MyItem2Activity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "My_item2Fragment";
    private TextView myitem1_title_tv;
    private ImageView myitem1_back_img;
    private RecyclerView recyclerView;
    private Item2_Adapter adapter;
    private List<Item2_Bean> beanList;

    private String account = Shared.getString(MyItem2Activity.this, "account", "");
    private String imgUrl = Shared.getString(MyItem2Activity.this, "accountPhoto", "");
    private int memberId = Shared.getInt(MyItem2Activity.this, "accountId", 0);

    private DbDao dbDao = new DbDao();
    private Connection conn;

    private int position;

    private List<ArtInfo> artInfoList;
    private List<Collector> collectorList;


    private int commentNum = 0;//评论数

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
        myitem1_title_tv = (TextView) findViewById(R.id.myitem1_title_tv);
    }

    private void initData() {
        switch (position) {
            case 1:
                item1();
                break;
            case 3:
                item3();
                break;
            case 4:
                item4();
                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
    }


    private void item1(){
        myitem1_title_tv.setText("艺术圈");
        conn = dbDao.getConn();
        Future<List<ArtInfo>> futures = MyApplication.executorService.submit(new Callable<List<ArtInfo>>() {
            @Override
            public List<ArtInfo> call() throws Exception {
                if (conn == null) conn = DbConnection.getConnection();
                return dbDao.queryAllArtsinfo();
            }
        });
        int Size = 0;
        try {
            Size = futures.get().size();
            artInfoList = futures.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(Size>0){
            for (int i = Size-1; i > Size-15; i--) {
                final ArtInfo artInfo = artInfoList.get(i);
                Future<MemberInfo> future = MyApplication.executorService.submit(new Callable<MemberInfo>() {
                    @Override
                    public MemberInfo call() throws Exception {
//                            conn = DbConnection.getConnection();
                        if(conn == null){
                            conn = DbConnection.getConnection();
                        }
                        commentNum = dbDao.queryCommentNum(artInfo.getId());
                        return dbDao.queryMemberInfo(artInfo.getAuthorId());
                    }
                });
                Item2_Bean bean = null;
                try {
                    bean = new Item2_Bean(future.get().getPhotoUrl(),
                            future.get().getNickName(), artInfo.getContent(),
                            artInfo.getUrl(), (int)(Math.random()*10+10), commentNum, (int)(Math.random()*10+10),false);
                    bean.setArtId(artInfo.getId());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                beanList.add(bean);
            }
        }else {
            Toast.makeText(this, "没查询到记录！", Toast.LENGTH_SHORT).show();
        }
    }

    private void item3(){
        myitem1_title_tv.setText("藏品");
        Future<Boolean> colltorFuture = MyApplication.executorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                conn = DbConnection.getConnection();
                collectorList = dbDao.queryCollectorInfo(memberId);
                for(int i=0;i<collectorList.size();i++){
                    Collector collector = collectorList.get(i);
                    commentNum = dbDao.queryCommentNum(collector.getArtsId());
                    Item2_Bean bean = new Item2_Bean(imgUrl, account, "", collector.getArtUrl(), (int) (Math.random() * 10 + 10), commentNum, (int) (Math.random() * 10 + 10), true);
                    bean.setArtId(collector.getArtsId());
                    beanList.add(bean );
                }
                return true;
            }
        });
        try {
            if(colltorFuture.get()){
                adapter.notifyDataSetChanged();
                if(!conn.isClosed()){
                    conn.close();
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void item4(){
        myitem1_title_tv.setText("我的艺术");
        try {
            Future<Boolean> future = MyApplication.executorService.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    conn = DbConnection.getConnection();
                    artInfoList = dbDao.queryAllArtInfo(Shared.getInt(MyItem2Activity.this, "accountId", -1));
                    for(int i=0;i<artInfoList.size();i++){
                        ArtInfo artInfo = artInfoList.get(i);
                        commentNum = dbDao.queryCommentNum(artInfo.getId());
                        Item2_Bean bean = new Item2_Bean(imgUrl, account, artInfo.getContent(), artInfo.getUrl(),
                                (int) (Math.random() * 10 + 10), commentNum, (int) (Math.random() * 10 + 10), false);
                        bean.setArtId(artInfo.getId());
                        beanList.add(bean);
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
