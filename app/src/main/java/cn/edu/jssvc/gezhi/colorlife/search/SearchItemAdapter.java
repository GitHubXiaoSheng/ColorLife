package cn.edu.jssvc.gezhi.colorlife.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class SearchItemAdapter extends ArrayAdapter<SearchItem> {
    private int  resourceId;
    public SearchItemAdapter( @NonNull Context context, int resource,  @NonNull List<SearchItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {
        SearchItem searchItem = (SearchItem)getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.type_image);
        TextView title  = (TextView)view.findViewById(R.id.type_title);
        TextView content  = (TextView)view.findViewById(R.id.type_content);
        TextView createdata = (TextView)view.findViewById(R.id.type_createtime);

        Glide.with(getContext())
                .load(searchItem.getImage())
                .transition(withCrossFade())
                .into(imageView);
        title.setText(searchItem.getMapTitle());
        content.setText(searchItem.getContent());
        createdata.setText(searchItem.getCreatedata());
        return view;

    }
}
