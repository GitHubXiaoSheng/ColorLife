package cn.edu.jssvc.gezhi.colorlife.share;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.MainActivity;
import cn.edu.jssvc.gezhi.colorlife.R;

public class Yws_follow_adapter extends RecyclerView.Adapter<Yws_follow_adapter.ViewHolder> {
    private List<Yws_Follow> myfollowlist;
    private static Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView_head,imageView1,imageView2;
        TextView textView_head,textView_content,textView_time,textView_down;
        public ViewHolder(View view){
            super(view);
            mContext = view.getContext();


            imageView_head=(ImageView)view.findViewById(R.id.image_header);
            imageView1=(ImageView)view.findViewById(R.id.image_1);
            imageView2=(ImageView)view.findViewById(R.id.image_2);
            textView_head=(TextView)view.findViewById(R.id.text_name);
            textView_content=(TextView)view.findViewById(R.id.text_content);
            textView_time=(TextView)view.findViewById(R.id.text_time);
            textView_down=(TextView)view.findViewById(R.id.text_down);


        }

    }
    public static Context getContext(){

        return mContext;
    }
    public Yws_follow_adapter(List<Yws_Follow> yws_followlist){
        myfollowlist=yws_followlist;

    }
    @Override
    public Yws_follow_adapter.ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.yws_follow_child,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
            Yws_Follow yws_follow=myfollowlist.get(i);
            viewHolder.textView_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View addview=LayoutInflater.from(mContext).inflate(R.layout.yws_dialog,null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setView(addview);
                    builder.show();

                }
            });
            viewHolder.textView_head.setText(yws_follow.getText_head());
            viewHolder.textView_content.setText(yws_follow.getText_content());
            viewHolder.textView_time.setText(yws_follow.getText_time());
            viewHolder.imageView_head.setImageResource(yws_follow.getImagehead());
            viewHolder.imageView1.setImageResource(yws_follow.getImage1());
            viewHolder.imageView2.setImageResource(yws_follow.getImage2());

    }

    @Override
    public int getItemCount() {
        return myfollowlist.size();
    }
}
