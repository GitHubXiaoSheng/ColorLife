package cn.edu.jssvc.gezhi.colorlife.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.MainActivity;
import cn.edu.jssvc.gezhi.colorlife.MyApplication;
import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.db.DbDao;

public class Leibie_Activity extends AppCompatActivity {

    private int jieshouData;

    private ImageView imageView_back;    //返回按钮
    private TextView textView_title;     //标题

    private GridView gridView;           //gridView
    private Item item;
    private List<Item> itemList = new ArrayList<>();
    private ItemAdapter itemAdapter;

    private List<Arts_info> arts_info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leibie);
        Intent intent = getIntent();
        jieshouData = Integer.valueOf(intent.getStringExtra("lei"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        arts_info = MyApplication.mArtsInfoList;
        Log.d("长度", arts_info.size() + "");
        init();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                addData();
            }
        }
    };

    private void addData() {
        itemList.clear();
        if (jieshouData == 1) {
            textView_title.setText("速写");
            for (Arts_info arts_info1 : arts_info) {
                if (arts_info1.getClassify_id() == 1){
                    item = new Item(arts_info1.getUrl(), arts_info1.getMaptilte(), arts_info1.getTags(),arts_info1.getRelease_date());
                    itemList.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        } else if (jieshouData == 2) {
            textView_title.setText("素描");
            for (Arts_info arts_info1 : arts_info) {
                if (arts_info1.getClassify_id() == 2){
                    item = new Item(arts_info1.getUrl(), arts_info1.getMaptilte(), arts_info1.getTags(),arts_info1.getRelease_date());
                    itemList.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        } else if (jieshouData == 3) {
            textView_title.setText("水彩");
            for (Arts_info arts_info1 : arts_info) {
                if (arts_info1.getClassify_id() == 3){
                    item = new Item(arts_info1.getUrl(), arts_info1.getMaptilte(), arts_info1.getTags(),arts_info1.getRelease_date());
                    itemList.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        } else if (jieshouData == 4) {
            textView_title.setText("水粉");
            for (Arts_info arts_info1 : arts_info) {
                if (arts_info1.getClassify_id() == 4){
                    item = new Item(arts_info1.getUrl(), arts_info1.getMaptilte(), arts_info1.getTags(),arts_info1.getRelease_date());
                    itemList.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        } else if (jieshouData == 5) {
            textView_title.setText("油画");
            for (Arts_info arts_info1 : arts_info) {
                if (arts_info1.getClassify_id() == 5){
                    item = new Item(arts_info1.getUrl(), arts_info1.getMaptilte(), arts_info1.getTags(),arts_info1.getRelease_date());
                    itemList.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        } else if (jieshouData == 6) {
            textView_title.setText("水墨");
            for (Arts_info arts_info1 : arts_info) {
                if (arts_info1.getClassify_id() == 6){
                    item = new Item(arts_info1.getUrl(), arts_info1.getMaptilte(), arts_info1.getTags(),arts_info1.getRelease_date());
                    itemList.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        } else if (jieshouData == 7) {
            textView_title.setText("书法");
            for (Arts_info arts_info1 : arts_info) {
                if (arts_info1.getClassify_id() == 7){
                    item = new Item(arts_info1.getUrl(), arts_info1.getMaptilte(), arts_info1.getTags(),arts_info1.getRelease_date());
                    itemList.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        } else if (jieshouData == 8) {
            textView_title.setText("马克笔");
            for (Arts_info arts_info1 : arts_info) {
                if (arts_info1.getClassify_id() == 8){
                    item = new Item(arts_info1.getUrl(), arts_info1.getMaptilte(), arts_info1.getTags(),arts_info1.getRelease_date());
                    itemList.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void init () {
        imageView_back = findViewById(R.id.leibieactivity_back);
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView_title = findViewById(R.id.leibieactivity_title);
        gridView = findViewById(R.id.leibieactivity_GridView);
        gridView.setFocusable(false);
        itemAdapter = new ItemAdapter(Leibie_Activity.this, R.layout.leibie_activity_gridview_item, itemList);
        gridView.setAdapter(itemAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Leibie_Activity.this, ContentActivity.class);
                intent.putExtra("id", "");
                startActivity(intent);
            }
        });
    }

}