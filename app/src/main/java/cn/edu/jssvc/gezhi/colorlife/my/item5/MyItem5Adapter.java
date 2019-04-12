package cn.edu.jssvc.gezhi.colorlife.my.item5;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.photo.PhotoUtil;

public class MyItem5Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Activity activity;
    private List<Bitmap> bitmapList;
    private Bitmap bitmap=null;
    private int FOOT_TYPE = -1;

    public MyItem5Adapter(Activity activity, List<Bitmap> bitmapList) {
        this.activity = activity;
        this.bitmapList = bitmapList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("当前位置", "onCreateViewHolder: "+i);
        if(i==FOOT_TYPE){
            View view = LayoutInflater.from(activity).inflate(R.layout.fragment_my_5_item2, viewGroup, false);
            return new FootHolder(view);
        }
        View view = LayoutInflater.from(activity).inflate(R.layout.fragment_my_5_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int i) {
        if(i>=bitmapList.size() || bitmapList.size()==0){
            ((FootHolder)holder).view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isGrantedPermission(activity)) {
                        PhotoUtil.openAlbum(activity);
                    }
                }
            });
        }else {
            bitmap = bitmapList.get(i);
            ((ViewHolder)holder).imageView.setImageBitmap(bitmap);
            ((ViewHolder)holder).imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((ViewHolder)holder).textView.setVisibility(View.VISIBLE);
                    return false;
                }
            });
            ((ViewHolder)holder).textView.setOnClickListener(this);
        }


    }

    @Override
    public int getItemCount() {
        return bitmapList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position>=bitmapList.size() || bitmapList.size()==0){
            return FOOT_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_item5_2_img:
                if (isGrantedPermission(activity)) {
                    PhotoUtil.openAlbum(activity);
                }
                break;
            case R.id.my_item5_1_tv:
                bitmapList.remove(bitmap);
                Log.d("", "onClick: 点击了删除");
                notifyDataSetChanged();
                break;
            default:break;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private View view;
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.my_item5_1_img);
            textView = (TextView) itemView.findViewById(R.id.my_item5_1_tv);
        }
    }

    public class FootHolder extends RecyclerView.ViewHolder{
        private View view;
        private ImageView imageView;
        public FootHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.my_item5_2_img);
        }
    }

    public boolean isGrantedPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }
        return true;
    }


}
