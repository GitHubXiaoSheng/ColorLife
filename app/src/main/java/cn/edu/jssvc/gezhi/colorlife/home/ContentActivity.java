package cn.edu.jssvc.gezhi.colorlife.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.jssvc.gezhi.colorlife.R;

public class ContentActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView_back,        //返回按钮
                        imageView_src;        //图片资源

    private TextView textView_money,         //价格
                        textView_time,        //发布时间
                        textView_title,       //标题
                        textView_content;     //内容

    private Button button_gouwuche,          //购物车
                        button_lijigoumai;    //立即购买

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        init();
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
        button_gouwuche = findViewById(R.id.contentactivity_gouwuche);
        button_gouwuche.setOnClickListener(this);
        button_lijigoumai = findViewById(R.id.contentactivity_goumai);
        button_lijigoumai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contentactivity_gouwuche:
                break;
            case R.id.contentactivity_goumai:
                break;
            default:
                break;
        }
    }
}
