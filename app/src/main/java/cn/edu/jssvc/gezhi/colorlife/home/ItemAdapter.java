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

public class ItemAdapter extends ArrayAdapter<Item> {
    private int resourceId;
    private Context mContext;
    public ItemAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        resourceId = resource;
        mContext = context;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        Item item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        ImageView imageView = view.findViewById(R.id.leibie_gridView_image);
        TextView textView_title = view.findViewById(R.id.leibie_gridView_title);
        TextView textView_fujia = view.findViewById(R.id.leibie_gridView_fujia);
        TextView textView_time = view.findViewById(R.id.leibie_gridView_time);

        Glide.with(mContext)
                .load(item.getImage())
                .transition(withCrossFade())
                .into(imageView);

        textView_title.setText(item.getTitle());
        textView_fujia.setText(item.getFujia());
        textView_time.setText(item.getTime());

        return view;
    }
}
