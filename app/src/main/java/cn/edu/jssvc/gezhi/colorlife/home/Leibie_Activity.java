package cn.edu.jssvc.gezhi.colorlife.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class Leibie_Activity extends AppCompatActivity {

    private int jieshouData;

    private ImageView imageView_back;    //返回按钮
    private TextView textView_title;     //标题

    private GridView gridView;           //gridView
    private Item item;
    private List<Item> itemList = new ArrayList<>();
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leibie);
        Intent intent = getIntent();
        jieshouData = Integer.valueOf(intent.getStringExtra("lei"));
        init();
        addData();
    }

    private void addData() {
        if (jieshouData == 1) {
            textView_title.setText("速写");
            for (int i = 0; i <= 10; i++) {
                item = new Item("http://pic41.nipic.com/20140429/12728082_192158998000_2.jpg", "这是第" + i + "个速写画", "附加内容" + i, "2019-02-" + i);
                itemList.add(item);
                itemAdapter.notifyDataSetChanged();
            }
        } else if (jieshouData == 2) {
            textView_title.setText("素描");
            for (int i = 0; i <= 10; i++) {
                item = new Item("http://pic1.win4000.com/wallpaper/9/5450ae2fdef8a.jpg", "这是第" + i + "个素描画", "附加内容" + i, "2019-02-" + i);
                itemList.add(item);
                itemAdapter.notifyDataSetChanged();
            }
        } else if (jieshouData == 3) {
            textView_title.setText("水彩");
            for (int i = 0; i <= 10; i++) {
                item = new Item("http://pic69.nipic.com/file/20150608/9252150_134415115986_2.jpg", "这是第" + i + "个水彩画", "附加内容" + i, "2019-02-" + i);
                itemList.add(item);
                itemAdapter.notifyDataSetChanged();
            }
        } else if (jieshouData == 4) {
            textView_title.setText("水粉");
            for (int i = 0; i <= 10; i++) {
                item = new Item("http://pic37.nipic.com/20140110/17563091_221827492154_2.jpg", "这是第" + i + "个水粉画", "附加内容" + i, "2019-02-" + i);
                itemList.add(item);
                itemAdapter.notifyDataSetChanged();
            }
        } else if (jieshouData == 5) {
            textView_title.setText("油画");
            for (int i = 0; i <= 10; i++) {
                item = new Item("http://pic.58pic.com/58pic/13/16/45/68p58PICJZr_1024.png", "这是第" + i + "个油画", "附加内容" + i, "2019-02-" + i);
                itemList.add(item);
                itemAdapter.notifyDataSetChanged();
            }
        } else if (jieshouData == 6) {
            textView_title.setText("水墨");
            for (int i = 0; i <= 10; i++) {
                item = new Item("http://pic31.nipic.com/20130720/5793914_122325176000_2.jpg", "这是第" + i + "个水墨画", "附加内容" + i, "2019-02-" + i);
                itemList.add(item);
                itemAdapter.notifyDataSetChanged();
            }
        } else if (jieshouData == 7) {
            textView_title.setText("书法");
            for (int i = 0; i <= 10; i++) {
                item = new Item("http://pic.qiantucdn.com/58pic/11/01/09/17V58PICsEi.jpg", "这是第" + i + "个书法", "附加内容" + i, "2019-02-" + i);
                itemList.add(item);
                itemAdapter.notifyDataSetChanged();
            }
        } else if (jieshouData == 8) {
            textView_title.setText("马克笔");
            for (int i = 0; i <= 10; i++) {
                item = new Item("http://pic34.nipic.com/20131020/6704106_203943375000_2.jpg", "这是第" + i + "个马克笔画", "附加内容" + i, "2019-02-" + i);
                itemList.add(item);
                itemAdapter.notifyDataSetChanged();
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