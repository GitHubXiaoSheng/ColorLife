package cn.edu.jssvc.gezhi.colorlife.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import cn.edu.jssvc.gezhi.colorlife.MyApplication;
import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.bean.ArtInfo;
import cn.edu.jssvc.gezhi.colorlife.bean.Comment;
import cn.edu.jssvc.gezhi.colorlife.db.DbConnection;
import cn.edu.jssvc.gezhi.colorlife.db.DbDao;
import cn.edu.jssvc.gezhi.colorlife.my.item2.MyItem2Activity;
import cn.edu.jssvc.gezhi.colorlife.util.Shared;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView_back,        //返回按钮
                        imageView_src;        //图片资源

    private TextView textView_money,         //价格
                        textView_time,        //发布时间
                        textView_title,       //标题
                        textView_content;     //内容

//    private Button button_gouwuche,          //购物车
//                        button_lijigoumai;    //立即购买

    private Button button_fs;                //发送评论
    private EditText editText_pl;            //输入评论

    private MyListView myListView;           //显示评论的ListView
    private Pinglun pinglun;
    private List<Pinglun> pinglunList = new ArrayList<>();
    private PinglunAdapter pinglunAdapter;

    private int id;
    private List<Arts_info> arts_info = new ArrayList<>();

    private DbDao dbDao;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        arts_info = MyApplication.mArtsInfoList;
        Intent intent = getIntent();
        id = intent.getIntExtra("id",1);
        Log.d("作品id", id + "");
        init();

        for (Arts_info arts_info1 : arts_info) {
            if (arts_info1.getArt_id() == id){
                Glide.with(this)
                        .load(arts_info1.getUrl())
                        .transition(withCrossFade())
                        .into(imageView_src);

                textView_money.setText(arts_info1.getPrice()+"");
                textView_time.setText("发布日期：" + arts_info1.getRelease_date());
                textView_title.setText(arts_info1.getMaptilte());
                textView_content.setText(arts_info1.getContent());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        addPinglun();
    }

    private void init() {
        imageView_back = findViewById(R.id.contentactivity_back);
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imageView_src = findViewById(R.id.contentactivity_image);
        textView_money = findViewById(R.id.contentactivity_money);
        textView_time = findViewById(R.id.contentactivity_time);
        textView_title = findViewById(R.id.contentactivity_title);
        textView_content = findViewById(R.id.contentactivity_content);
//        button_gouwuche = findViewById(R.id.contentactivity_gouwuche);
//        button_gouwuche.setOnClickListener(this);
//        button_lijigoumai = findViewById(R.id.contentactivity_goumai);
//        button_lijigoumai.setOnClickListener(this);
        editText_pl = findViewById(R.id.editText_pl);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        button_fs = findViewById(R.id.button_fasong);
        button_fs.setOnClickListener(this);
        myListView = findViewById(R.id.content_listView);
        pinglunAdapter = new PinglunAdapter(this,R.layout.zzj_content_listview_item,pinglunList);
        myListView.setAdapter(pinglunAdapter);
    }

    private void addPinglun() {
        dbDao = new DbDao();
        //查询到相关艺术的评论
        Future<List<Comment>> future = MyApplication.executorService.submit(new Callable<List<Comment>>() {
            @Override
            public List<Comment> call() throws Exception {
                Connection conn = DbConnection.getConnection();
                if (conn == null) {
                    conn = DbConnection.getConnection();
                }
                Log.d("我看看", "运行了这里.id="+id);
                return dbDao.queryAllCommentInfo(id);
            }
        });
        try {
            for (Comment comment : future.get()) {
                Log.d("我看看", "运行了这里2222");
                pinglun = new Pinglun();
                pinglun.setTilteImg(comment.getHead());
                pinglun.setNameText(comment.getNickName());
                pinglun.setTimeText(comment.getDate());
                pinglun.setContentText(comment.getComment());
                pinglunList.add(pinglun);
                pinglunAdapter.notifyDataSetChanged();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.contentactivity_gouwuche:
//                break;
//            case R.id.contentactivity_goumai:
//                break;
            case R.id.button_fasong:
                int dangqianId = id;
                String headImage = Shared.getString(ContentActivity.this, "accountPhoto", "");
                String name = Shared.getString(ContentActivity.this, "account", "");
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
                String pinglun = editText_pl.getText().toString();
                if (!name.isEmpty()) {
                    final Comment comment = new Comment();
                    comment.setArtId(dangqianId);
                    comment.setHead(headImage);
                    comment.setNickName(name);
                    comment.setDate(time);
                    comment.setComment(pinglun);
                    Future<Boolean> future = MyApplication.executorService.submit(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return dbDao.insertCommentData(comment);
                        }
                    });
                    try {
                        if (future.get()) {
                            Toast.makeText(ContentActivity.this,"评论成功！",Toast.LENGTH_SHORT).show();
                            editText_pl.setText("");
                            addPinglun();
                        }else {
                            Toast.makeText(ContentActivity.this,"评论失败！",Toast.LENGTH_SHORT).show();
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(ContentActivity.this,"您还未登陆！",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
