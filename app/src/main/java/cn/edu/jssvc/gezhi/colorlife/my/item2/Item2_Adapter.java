package cn.edu.jssvc.gezhi.colorlife.my.item2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.home.ContentActivity;
import cn.edu.jssvc.gezhi.colorlife.home.Leibie_Activity;

public class Item2_Adapter extends RecyclerView.Adapter<Item2_Adapter.ViewHolder> {
    private Context context;
    private List<Item2_Bean> beanList;

    public Item2_Adapter(Context context, List<Item2_Bean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_my_2_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        final Item2_Bean bean = beanList.get(i);
        if(bean.getHeadImgUrl().equals("")){
            holder.headImg.setImageResource(R.mipmap.default_head);
        }else {
            Glide.with(context).load(bean.getHeadImgUrl()).into(holder.headImg);
        }
        if(bean.getContentmgUrl().equals("")){
            holder.contentImg.setImageResource(R.drawable.zzj_a);
        }else {
            Glide.with(context).load(bean.getContentmgUrl()).into(holder.contentImg);
        }
        holder.name.setText(bean.getName());
        holder.share.setText(String.valueOf(bean.getShare()));
        holder.comment.setText(String.valueOf(bean.getComment()));
        holder.like.setText(String.valueOf(bean.getLike()));
        holder.contentTv.setText(bean.getContent());
        if(bean.isLikeing()){
            holder.likeImg.setImageResource(R.drawable.icon_likeing);
            holder.like.setText(String.valueOf(bean.getLike()+1));
        }else {
            holder.likeImg.setImageResource(R.drawable.icon_like);
            holder.like.setText(String.valueOf(bean.getLike()));
        }
        holder.likeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean.isLikeing()){
                    holder.likeImg.setImageResource(R.drawable.icon_like);
                    holder.like.setText(String.valueOf(bean.getLike()));
                    bean.setLikeing(false);
                }else {
                    holder.likeImg.setImageResource(R.drawable.icon_likeing);
                    holder.like.setText(String.valueOf(bean.getLike()+1));
                    bean.setLikeing(true);
                }
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("id", bean.getArtId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView headImg;
        private TextView contentTv;
        private ImageView contentImg;
        private ImageView likeImg;
        private TextView name;
        private TextView share;
        private TextView comment;
        private TextView like;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            headImg = (ImageView) itemView.findViewById(R.id.fragmy_itme1_head_img);
            contentTv = (TextView) itemView.findViewById(R.id.fragmy_itme1_content_tv);
            contentImg = (ImageView) itemView.findViewById(R.id.fragmy_itme1_content_img);
            name = (TextView) itemView.findViewById(R.id.fragmy_itme1_name_tv);
            share = (TextView) itemView.findViewById(R.id.fragmy_itme1_share_tv);
            comment = (TextView) itemView.findViewById(R.id.fragmy_itme1_comment_tv);
            like = (TextView) itemView.findViewById(R.id.fragmy_itme1_like_tv);
            likeImg = (ImageView) itemView.findViewById(R.id.fragmy_itme1_like_img);
        }
    }

}
