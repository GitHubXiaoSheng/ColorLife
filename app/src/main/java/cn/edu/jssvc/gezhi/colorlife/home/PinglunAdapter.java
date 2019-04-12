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

public class PinglunAdapter extends ArrayAdapter<Pinglun> {

    private int resourceId;
    private Context context;

    public PinglunAdapter(Context context, int resource, List<Pinglun> objects) {
        super(context, resource, objects);
        resourceId = resource;
        this.context = context;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Pinglun pinglun = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageView = view.findViewById(R.id.title_img);
        TextView textView_name = view.findViewById(R.id.name_text);
        TextView textView_time = view.findViewById(R.id.time_text);
        TextView textView_pl = view.findViewById(R.id.pinglun_text);

        Glide.with(context)
                .load(pinglun.getTilteImg())
                .thumbnail(0.2f)
                .transition(withCrossFade())
                .into(imageView);

        textView_name.setText(pinglun.getNameText());
        textView_time.setText(pinglun.getTimeText());
        textView_pl.setText(pinglun.getContentText());

        return view;
    }
}
