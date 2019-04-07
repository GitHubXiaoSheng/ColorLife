package cn.edu.jssvc.gezhi.colorlife.my.follow;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFollowAdapter extends RecyclerView.Adapter<MyFollowAdapter.ViewHolder> {
    private String TAG = "MyFollowAdapter";
    private Context context;
    private List<MyFollowItem> itemList;
    private boolean isFollow =true;

    public MyFollowAdapter(Context context, List<MyFollowItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.myfollow_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        final MyFollowItem item = itemList.get(i);
//        holder.HeadImg.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_foreground));
        holder.NameTv.setText(item.getName());
        holder.DetailsTv.setText(item.getDetails());
        holder.FollowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isFollow()){
                    holder.FollowBtn.setText("+关注");
                    holder.FollowBtn.setBackgroundColor(Color.parseColor("#00C5CD"));
                    item.setFollow(false);
                }else {
                    holder.FollowBtn.setText("已关注");
                    holder.FollowBtn.setBackgroundColor(Color.parseColor("#DCDCDC"));
                    item.setFollow(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private CircleImageView HeadImg;
        private TextView NameTv;
        private TextView DetailsTv;
        private Button FollowBtn;

        public ViewHolder(View view) {
            super(view);
            itemView = view;
            HeadImg = (CircleImageView) view.findViewById(R.id.item_myfollow_head_img);
            NameTv = (TextView) view.findViewById(R.id.item_myfollow_name_tv);
            DetailsTv = (TextView) view.findViewById(R.id.item_myfollow_details_tv);
            FollowBtn = (Button) view.findViewById(R.id.item_myfollow_follow_btn);
        }
    }
}
