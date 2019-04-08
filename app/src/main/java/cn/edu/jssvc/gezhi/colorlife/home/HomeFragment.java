package cn.edu.jssvc.gezhi.colorlife.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.edu.jssvc.gezhi.colorlife.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private TextView textView_shenqing,             //申请认证
                    textView_title;                  //ViewPager的标题
    private ViewPager viewPager;                     //存放图片的ViewPager
    private LinearLayout linearLayout_yuandian;    //存放小圆点的容器

    private int[] imageResIds;                      //ViewPager图片
    private String[] contentDescs;                  //ViewPager标题
    private ArrayList<ImageView> imageViewList = new ArrayList<>();
    private int CHUSHI_POSITION = 0;               //初始位置

    private Timer timer;

    private MyListView myListView;
    private Tuijian tuijian;
    private List<Tuijian> tuijianList = new ArrayList<>();
    private TuijianAdapter tuijianAdapter;

    private CircleImageView imageView_fenlei_1,imageView_fenlei_2,imageView_fenlei_3,imageView_fenlei_4,imageView_fenlei_5,imageView_fenlei_6,imageView_fenlei_7,imageView_fenlei_8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        // Controller 控制器
        initAdapter();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        },0,3000);


    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    private void init() {
        myListView = getActivity().findViewById(R.id.home_myListView);
        tuijianAdapter = new TuijianAdapter(getContext(),R.layout.fragment_home_listview_item, tuijianList);
        myListView.setAdapter(tuijianAdapter);
        myListView.setFocusable(false);

        textView_shenqing = getActivity().findViewById(R.id.home_ShenqingRenzheng);
        textView_shenqing.setOnClickListener(this);
        textView_title = getActivity().findViewById(R.id.home_title);
        viewPager = getActivity().findViewById(R.id.home_ViewPager);
        viewPager.setOnPageChangeListener(this);// 设置页面更新监听
        linearLayout_yuandian = getActivity().findViewById(R.id.home_yuandain);

        addListViewData();

        imageView_fenlei_1 = getActivity().findViewById(R.id.image_fenlei_1);
        imageView_fenlei_2 = getActivity().findViewById(R.id.image_fenlei_2);
        imageView_fenlei_3 = getActivity().findViewById(R.id.image_fenlei_3);
        imageView_fenlei_4 = getActivity().findViewById(R.id.image_fenlei_4);
        imageView_fenlei_5 = getActivity().findViewById(R.id.image_fenlei_5);
        imageView_fenlei_6 = getActivity().findViewById(R.id.image_fenlei_6);
        imageView_fenlei_7 = getActivity().findViewById(R.id.image_fenlei_7);
        imageView_fenlei_8 = getActivity().findViewById(R.id.image_fenlei_8);
        imageView_fenlei_1.setOnClickListener(this);
        imageView_fenlei_2.setOnClickListener(this);
        imageView_fenlei_3.setOnClickListener(this);
        imageView_fenlei_4.setOnClickListener(this);
        imageView_fenlei_5.setOnClickListener(this);
        imageView_fenlei_6.setOnClickListener(this);
        imageView_fenlei_7.setOnClickListener(this);
        imageView_fenlei_8.setOnClickListener(this);

    }

    private void addListViewData() {
        for (int i = 1; i <= 10; i++) {
            tuijian = new Tuijian("http://img4.imgtn.bdimg.com/it/u=3146195644,3582029842&fm=26&gp=0.jpg", "这是第" + i + "幅画", "12" + i + "3人观看", "6" + i + "6人收藏");
            tuijianList.add(tuijian);
        }
        tuijianAdapter.notifyDataSetChanged();
        // 图片资源id数组
        imageResIds = new int[]{R.drawable.zzj_a, R.drawable.zzj_b, R.drawable.zzj_c, R.drawable.zzj_d};
        // 文本描述
        contentDescs = new String[]{
                "油画佳作折扣聚惠",
                "双十一狂欢提前购",
                "水彩佳作必买清单",
                "国画佳作今日必抢"
        };
        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.length; i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(getContext());
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);

            // 加小白点, 指示器
            pointView = new View(getContext());
            pointView.setBackgroundResource(R.drawable.zzj_a);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (i != 0)
                layoutParams.leftMargin = 10;
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            linearLayout_yuandian.addView(pointView, layoutParams);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_ShenqingRenzheng:
                break;
            case R.id.image_fenlei_1:
                Intent intent = new Intent(getActivity(), Leibie_Activity.class);
                intent.putExtra("lei", "1");
                startActivity(intent);
                break;
            case R.id.image_fenlei_2:
                Intent intent2 = new Intent(getActivity(), Leibie_Activity.class);
                intent2.putExtra("lei", "2");
                startActivity(intent2);
                break;
            case R.id.image_fenlei_3:
                Intent intent3 = new Intent(getActivity(), Leibie_Activity.class);
                intent3.putExtra("lei", "3");
                startActivity(intent3);
                break;
            case R.id.image_fenlei_4:
                Intent intent4 = new Intent(getActivity(), Leibie_Activity.class);
                intent4.putExtra("lei", "4");
                startActivity(intent4);
                break;
            case R.id.image_fenlei_5:
                Intent intent5 = new Intent(getActivity(), Leibie_Activity.class);
                intent5.putExtra("lei", "5");
                startActivity(intent5);
                break;
            case R.id.image_fenlei_6:
                Intent intent6 = new Intent(getActivity(), Leibie_Activity.class);
                intent6.putExtra("lei", "6");
                startActivity(intent6);
                break;
            case R.id.image_fenlei_7:
                Intent intent7 = new Intent(getActivity(), Leibie_Activity.class);
                intent7.putExtra("lei", "7");
                startActivity(intent7);
                break;
            case R.id.image_fenlei_8:
                Intent intent8 = new Intent(getActivity(), Leibie_Activity.class);
                intent8.putExtra("lei", "8");
                startActivity(intent8);
                break;
            default:
                break;
        }
    }

    private void initAdapter() {
        linearLayout_yuandian.getChildAt(0).setEnabled(true);
        textView_title.setText(contentDescs[0]);
        CHUSHI_POSITION = 0;

        // 设置适配器
        viewPager.setAdapter(new MyAdapter());

        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % imageViewList.size());
        // 2147483647 / 2 = 1073741823 - (1073741823 % 5)
        viewPager.setCurrentItem(5000000); // 设置到某个位置
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,int positionOffsetPixels) {
        // 滚动时调用
    }

    @Override
    public void onPageSelected(int position) {
        // 新的条目被选中时调用
//        System.out.println("onPageSelected: " + position);
        int newPosition = position % imageViewList.size();

        //设置文本
        textView_title.setText(contentDescs[newPosition]);

        // 把之前的禁用, 把最新的启用, 更新指示器
        linearLayout_yuandian.getChildAt(CHUSHI_POSITION).setEnabled(false);
        linearLayout_yuandian.getChildAt(newPosition).setEnabled(true);

        // 记录之前的位置
        CHUSHI_POSITION = newPosition;

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // 滚动状态变化时调用
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        // 3. 指定复用的判断逻辑, 固定写法
        @Override
        public boolean isViewFromObject(View view, Object object) {
//			System.out.println("isViewFromObject: "+(view == object));
            // 当划到新的条目, 又返回来, view是否可以被复用.
            // 返回判断规则
            return view == object;
        }

        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            System.out.println("instantiateItem初始化: " + position);
            // container: 容器: ViewPager
            // position: 当前要显示条目的位置 0 -> 4

//			newPosition = position % 5
            int newPosition = position % imageViewList.size();

            ImageView imageView = imageViewList.get(newPosition);
            // a. 把View对象添加到container中
            container.addView(imageView);
            // b. 把View对象返回给框架, 适配器
            return imageView; // 必须重写, 否则报异常
        }

        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // object 要销毁的对象
//            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }
    }

}
