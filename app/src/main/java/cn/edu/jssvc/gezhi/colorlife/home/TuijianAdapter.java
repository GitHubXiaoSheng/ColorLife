package cn.edu.jssvc.gezhi.colorlife.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class TuijianAdapter extends ArrayAdapter<Tuijian> {

    private int resourceId;
    private Context mContext;

    public TuijianAdapter(Context context, int resource, List<Tuijian> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tuijian tuijian = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageView = view.findViewById(R.id.home_listView_image);
        TextView textView_title = view.findViewById(R.id.home_listView_tilte);
        TextView textView_guankan = view.findViewById(R.id.home_listView_guankan);
        TextView textView_shoucang = view.findViewById(R.id.home_listView_shoucang);

        Glide.with(mContext)
                .load(tuijian.getImage())
                .thumbnail(0.4f)
                .transition(withCrossFade())
                .into(imageView);
        textView_title.setText(tuijian.getTitle());
        textView_guankan.setText(tuijian.getGuankan());
        textView_shoucang.setText(tuijian.getShoucang());

        return view;
    }
}
