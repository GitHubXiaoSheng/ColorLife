package cn.edu.jssvc.gezhi.colorlife.my.item1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class Item1_Adapter extends RecyclerView.Adapter<Item1_Adapter.ViewHolder> {
    private Context context;
    private List<Item1_Bean> beanList;

    public Item1_Adapter(Context context, List<Item1_Bean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_my_1_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Item1_Bean bean = beanList.get(i);
        holder.headImg.setImageResource(R.mipmap.default_head);
        holder.contentImg.setImageResource(R.drawable.zzj_a);
        holder.name.setText(bean.getName());
        holder.share.setText(String.valueOf(bean.getShare()));
        holder.comment.setText(String.valueOf(bean.getComment()));
        holder.like.setText(String.valueOf(bean.getLike()));
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView headImg;
        private ImageView contentImg;
        private TextView name;
        private TextView share;
        private TextView comment;
        private TextView like;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headImg = (ImageView) itemView.findViewById(R.id.fragmy_itme1_head_img);
            contentImg = (ImageView) itemView.findViewById(R.id.fragmy_itme1_content_img);
            name = (TextView) itemView.findViewById(R.id.fragmy_itme1_name_tv);
            share = (TextView) itemView.findViewById(R.id.fragmy_itme1_share_tv);
            comment = (TextView) itemView.findViewById(R.id.fragmy_itme1_comment_tv);
            like = (TextView) itemView.findViewById(R.id.fragmy_itme1_like_tv);
        }
    }

}
