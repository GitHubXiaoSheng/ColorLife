package cn.edu.jssvc.gezhi.colorlife.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

import cn.edu.jssvc.gezhi.colorlife.MyApplication;
import cn.edu.jssvc.gezhi.colorlife.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private TextView textView_shenqing,             //申请认证
                    textView_title;                  //ViewPager的标题
    private ViewPager viewPager;                     //存放图片的ViewPager
    private TextView textView_1,textView_2,textView_3,textView_4;   //圆点

    private int[] imageInt;
    private String[] titleString;
    private List<ImageView> imageViewList = new ArrayList<>();

    private Timer timer;
    private int count = 0;

    private MyListView myListView;
    private Tuijian tuijian;
    private List<Tuijian> tuijianList = new ArrayList<>();
    private TuijianAdapter tuijianAdapter;

    private List<Arts_info> arts_info = new ArrayList<>();

    private CircleImageView imageView_fenlei_1,imageView_fenlei_2,imageView_fenlei_3,imageView_fenlei_4,imageView_fenlei_5,imageView_fenlei_6,imageView_fenlei_7,imageView_fenlei_8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        arts_info = MyApplication.mArtsInfoList;
        init();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        },0,3000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Message message = new Message();
                    message.what = 1;
                    handler2.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler2 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                addListViewData();
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (count == 5) {
                        viewPager.setCurrentItem(0);
                        count = 0;
                    }else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        count ++;
                    }
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

        textView_1 = getActivity().findViewById(R.id.home_yuan_1);
        textView_2 = getActivity().findViewById(R.id.home_yuan_2);
        textView_3 = getActivity().findViewById(R.id.home_yuan_3);
        textView_4 = getActivity().findViewById(R.id.home_yuan_4);

        viewPager = getActivity().findViewById(R.id.home_ViewPager);
        viewPager.setOnPageChangeListener(listener);
        imageInt = new int[]{R.drawable.zzj_a,R.drawable.zzj_b,R.drawable.zzj_c,R.drawable.zzj_d};
        titleString = new String[]{"油画佳作折扣聚会","双十一狂欢提前购","水彩佳作必买清单","国画佳作今日必抢"};
        ImageView imageView;
        for (int i = 0; i < imageInt.length; i++) {
            imageView = new ImageView(getContext());
            imageView.setBackgroundResource(imageInt[i]);//设置图片
            imageViewList.add(imageView);
        }
        viewPager.setAdapter(new ImagePagerAdapter(getContext(),imageViewList));
        viewPager.setCurrentItem(0);
        textView_title.setText(titleString[0]);

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
        for (Arts_info arts_info1 : arts_info) {
            tuijian = new Tuijian(arts_info1.getUrl(), arts_info1.getMaptilte(), "", "");
            tuijianList.add(tuijian);
            tuijianAdapter.notifyDataSetChanged();
        }
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {}
        @Override
        public void onPageSelected(int i) {
            imageViewList.get(i);
            textView_title.setText(titleString[i]);
            if (i == 0) {
                textView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan_click));
                textView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
                textView_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
                textView_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
            } else if (i == 1) {
                textView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
                textView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan_click));
                textView_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
                textView_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
            } else if (i == 2) {
                textView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
                textView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
                textView_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan_click));
                textView_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
            } else if (i == 3) {
                textView_1.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
                textView_2.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
                textView_3.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan));
                textView_4.setBackgroundDrawable(getResources().getDrawable(R.drawable.zzj_yuan_click));
            }
        }
        @Override
        public void onPageScrollStateChanged(int i) {}
    };

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

    public class ImagePagerAdapter extends PagerAdapter {
        protected Context context;
        protected List<ImageView> images;
        public ImagePagerAdapter(Context context, List<ImageView> images){
            this.context=context;
            this.images=images;
        }
        @Override
        public int getCount() {
            //对数据作非空判断
            return null!=images?images.size():0;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(images.get(position));
            return images.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }
    }


}
