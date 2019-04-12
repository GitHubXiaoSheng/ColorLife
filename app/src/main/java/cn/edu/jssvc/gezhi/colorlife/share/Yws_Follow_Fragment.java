package cn.edu.jssvc.gezhi.colorlife.share;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class Yws_Follow_Fragment extends Fragment {
    private List<Yws_Follow> myfollowlist=new ArrayList<>();
    private View view;

    private String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view= inflater.inflate(R.layout.yws_fragment_follow, container, false);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        initview();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.follow_recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Yws_follow_adapter yws_follow_adapter=new Yws_follow_adapter(myfollowlist);
        Log.d("tag",myfollowlist.toString());
        recyclerView.setAdapter(yws_follow_adapter);




    }
    void initview(){
                for (int i=0;i<5;i++){
                    Yws_Follow yws_follow1=new Yws_Follow("爱画boy","我的漫画书屋，有书更精彩","04-02 16:47 来自 weibo.com",R.drawable.head1,R.drawable.follow1,R.drawable.follow2);
                    myfollowlist.add(yws_follow1);

                    Yws_Follow yws_follow2=new Yws_Follow("夏天空调更凉快","扎心了老铁，以前被骗的举个手","04-02 16:50 来自 baidu.com",R.drawable.head2,R.drawable.follow3,R.drawable.follow4);
                    myfollowlist.add(yws_follow2);
                    Yws_Follow yws_follow3=new Yws_Follow("遇见精致的自己","我的漫画书屋，有书更精彩","04-04 18:60 来自 weibo.com",R.drawable.head3,R.drawable.follow5,R.drawable.follow6jpg);
                    myfollowlist.add(yws_follow3);
                    Yws_Follow yws_follow4=new Yws_Follow("liy李呆呆","会讲故事的一只猪","04-04 19:37 来自 weibo.com",R.drawable.head4,R.drawable.follow7,R.drawable.follow8);
                    myfollowlist.add(yws_follow4);
                    Yws_Follow yws_follow5=new Yws_Follow("你是我全世界最喜欢的猪","水彩画哪家强","04-05 20:40 来自 weibo.com",R.drawable.head3,R.drawable.follow2,R.drawable.follow4);
                    myfollowlist.add(yws_follow5);
                }
    }
}
