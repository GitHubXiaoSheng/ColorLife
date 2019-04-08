package cn.edu.jssvc.gezhi.colorlife.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cn.edu.jssvc.gezhi.colorlife.R;

public class ContentActivity extends AppCompatActivity {

    private ImageView imageView_back;      //返回按钮

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
    }
}
