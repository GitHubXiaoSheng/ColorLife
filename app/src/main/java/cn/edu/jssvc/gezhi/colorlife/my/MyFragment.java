package cn.edu.jssvc.gezhi.colorlife.my;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.bean.MemberInfo;
import cn.edu.jssvc.gezhi.colorlife.login.LoginActivity;
import cn.edu.jssvc.gezhi.colorlife.my.follow.MyFollowActivity;
import cn.edu.jssvc.gezhi.colorlife.my.item1.MyItem1Activity;

public class MyFragment extends Fragment implements View.OnClickListener{
    private String TAG = "MyFragment";
    private View view;
    private TextView nameTv;//用户名
//    private TextView myHomeTv;//我的主页
    private TextView gradeTv;//等级
    private TextView followTv;//关注
    private TextView fansTv;//粉丝
    private TextView fabulousTv;//赞
//    private TextView setTv;//设置
    private ImageView headImg;//头像

    private RelativeLayout item1;
    private RelativeLayout item2;
    private RelativeLayout item3;
    private RelativeLayout item4;
    private RelativeLayout item5;
    private RelativeLayout item6;
    private RelativeLayout item7;

    private MemberInfo memberInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        initView();
        setListener();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView(){
        nameTv = (TextView) view.findViewById(R.id.fragmy_user_name_tv);
        gradeTv = (TextView) view.findViewById(R.id.fragmy_grade_tv);
        followTv = (TextView) view.findViewById(R.id.fragmy_head_follow_tv);
        fansTv = (TextView) view.findViewById(R.id.fragmy_head_fans_tv);
        fabulousTv = (TextView) view.findViewById(R.id.fragmy_head_fabulous_tv);
        headImg = (ImageView) view.findViewById(R.id.fragmy_head_img);

        item1 = (RelativeLayout) view.findViewById(R.id.fragmy_item1_layout);
        item2 = (RelativeLayout) view.findViewById(R.id.fragmy_item2_layout);
        item3 = (RelativeLayout) view.findViewById(R.id.fragmy_item3_layout);
        item4 = (RelativeLayout) view.findViewById(R.id.fragmy_item4_layout);
        item5 = (RelativeLayout) view.findViewById(R.id.fragmy_item5_layout);
        item6 = (RelativeLayout) view.findViewById(R.id.fragmy_item6_layout);
        item7 = (RelativeLayout) view.findViewById(R.id.fragmy_item7_layout);

    }

    /**
     * 设置控件的监听事件
     */
    private void setListener(){
        nameTv.setOnClickListener(this);
        gradeTv.setOnClickListener(this);
        followTv.setOnClickListener(this);
        fansTv.setOnClickListener(this);
        fabulousTv.setOnClickListener(this);
        headImg.setOnClickListener(this);
        item1.setOnClickListener(this);
        item2.setOnClickListener(this);
        item3.setOnClickListener(this);
        item4.setOnClickListener(this);
        item5.setOnClickListener(this);
        item6.setOnClickListener(this);
        item7.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragmy_user_name_tv:
                break;
            case R.id.fragmy_grade_tv:
                break;
            case R.id.fragmy_head_follow_tv:
                Intent followIntent = new Intent(getActivity(), MyFollowActivity.class);
                startActivity(followIntent);
                break;
            case R.id.fragmy_head_fans_tv:
                Intent fansIntent = new Intent(getActivity(), MyFollowActivity.class);
                startActivity(fansIntent);
                break;
            case R.id.fragmy_head_fabulous_tv:
                break;
            case R.id.fragmy_head_img:
                Intent headIntent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(headIntent,1);
                break;
            case R.id.fragmy_item1_layout:
                break;
            case R.id.fragmy_item2_layout://艺术圈
                Intent item1Intent = new Intent(getActivity(), MyItem1Activity.class);
                startActivity(item1Intent);
                break;
            case R.id.fragmy_item3_layout:
                break;
            case R.id.fragmy_item4_layout:
                break;
            case R.id.fragmy_item5_layout:
                break;
            case R.id.fragmy_item6_layout:
                break;
            case R.id.fragmy_item7_layout:
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(data != null){
                memberInfo = (MemberInfo)data.getSerializableExtra("memberInfo");
                if(memberInfo != null){

                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
