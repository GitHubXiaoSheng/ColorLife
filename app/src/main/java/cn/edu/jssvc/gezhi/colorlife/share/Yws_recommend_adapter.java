package cn.edu.jssvc.gezhi.colorlife.share;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class Yws_recommend_adapter extends RecyclerView.Adapter<Yws_recommend_adapter.ViewHolder> {
    private List<Yws_Recommend> myremcommendlist;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2,textView3,textView4,textView5,textView6;
        ImageView imageViewhead,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
        public ViewHolder( View itemView) {
            super(itemView);
            textView1=(TextView)itemView.findViewById(R.id.text_name_recommend);
            textView2=(TextView)itemView.findViewById(R.id.text_time_recommend);
            textView3=(TextView)itemView.findViewById(R.id.text_content_recommend);
            textView4=(TextView)itemView.findViewById(R.id.text_zhuangfa_recommend);
            textView5=(TextView)itemView.findViewById(R.id.text_comment_recommend);
            textView6=(TextView)itemView.findViewById(R.id.text_like_recommend);
            imageViewhead=(ImageView)itemView.findViewById(R.id.image_header_recommend);
            imageView1=(ImageView)itemView.findViewById(R.id.image_1_recommend);
            imageView2=(ImageView)itemView.findViewById(R.id.image_2_recommend);
            imageView3=(ImageView)itemView.findViewById(R.id.image_3_recommend);
            imageView4=(ImageView)itemView.findViewById(R.id.image_4_recommend);
            imageView5=(ImageView)itemView.findViewById(R.id.image_5_recommend);
            imageView6=(ImageView)itemView.findViewById(R.id.image_6_recommend);
            imageView7=(ImageView)itemView.findViewById(R.id.image_7_recommend);
            imageView8=(ImageView)itemView.findViewById(R.id.image_8_recommend);
            imageView9=(ImageView)itemView.findViewById(R.id.image_9_recommend);
        }
    }

    public Yws_recommend_adapter(List<Yws_Recommend> ywsremcomendlist){
        myremcommendlist=ywsremcomendlist;

    }


    @Override
    public Yws_recommend_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.yws_recommend_child,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Yws_Recommend yws_remcomend=myremcommendlist.get(i);
        viewHolder.textView1.setText(yws_remcomend.getText_remcommend_name());
        viewHolder.textView2.setText(yws_remcomend.getText_remcommend_time());
        viewHolder.textView3.setText(yws_remcomend.getText_remcommend_content());
        viewHolder.textView4.setText(yws_remcomend.getText_remcommend_zhuanfa());
        viewHolder.textView5.setText(yws_remcomend.getText_remcommend_commemt());
        viewHolder.textView6.setText(yws_remcomend.getText_remcommend_like());
        viewHolder.imageViewhead.setImageResource(yws_remcomend.getImg_remcommend_head());
        viewHolder.imageView1.setImageResource(yws_remcomend.getImg_remcommend_1());
        viewHolder.imageView2.setImageResource(yws_remcomend.getImg_remcommend_2());
        viewHolder.imageView3.setImageResource(yws_remcomend.getImg_remcommend_3());
        viewHolder.imageView4.setImageResource(yws_remcomend.getImg_remcommend_4());
        viewHolder.imageView5.setImageResource(yws_remcomend.getImg_remcommend_5());
        viewHolder.imageView6.setImageResource(yws_remcomend.getImg_remcommend_6());
        viewHolder.imageView7.setImageResource(yws_remcomend.getImg_remcommend_7());
        viewHolder.imageView8.setImageResource(yws_remcomend.getImg_remcommend_8());
        viewHolder.imageView9.setImageResource(yws_remcomend.getImg_remcommend_9());

    }

    @Override
    public int getItemCount() {
        return myremcommendlist.size();
    }
}
