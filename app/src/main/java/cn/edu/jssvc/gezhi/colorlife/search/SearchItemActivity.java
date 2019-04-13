package cn.edu.jssvc.gezhi.colorlife.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.MyApplication;
import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.home.Arts_info;

public class SearchItemActivity extends AppCompatActivity {
    private List<Arts_info> artslist = new ArrayList<>();
    private List<SearchItem>searchItems = new ArrayList<SearchItem>();
    private SearchItemAdapter searchItemAdapter;
    private ListView listView;
    private TextView textView;
    private ImageView imageView;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);
        listView  =(ListView)findViewById(R.id.search_item);
        textView  =(TextView)findViewById(R.id.type);
        imageView = (ImageView)findViewById(R.id.type_back);
        Intent intent = getIntent();
        type = intent.getStringExtra("leibie");
        textView.setText(type);
    }

    @Override
    protected void onStart() {
        super.onStart();
        artslist = MyApplication.mArtsInfoList;
        searchitem();
        searchItemAdapter = new SearchItemAdapter(SearchItemActivity.this,R.layout.fragment_search_type,searchItems);
        listView.setAdapter(searchItemAdapter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    void searchitem(){
        for (Arts_info arts_info : artslist){
            String content = arts_info.getContent();
            if (content.indexOf(type)!=-1){
                Log.d("lhd",content);
               SearchItem searchItem = new SearchItem(arts_info.getUrl(),arts_info.getMaptilte(),arts_info.getContent(),arts_info.getCreate_date());
               Log.d("lhd",arts_info.getUrl()+"==="+arts_info.getMaptilte()+"===="+arts_info.getContent()+"==="+arts_info.getCreate_date());
               searchItems.add(searchItem);
            }
        }
    }
}
