package cn.edu.jssvc.gezhi.colorlife.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class HuaTiBangFragment extends Fragment {
    private List<Search_huati>huatilist = new ArrayList<>();
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_search_huati,container,false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.huati_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        HuatiAdapter adapter = new HuatiAdapter(huatilist);
        recyclerView.setAdapter(adapter);
        showList();
    }

    void showList(){
        Search_huati item1 = new Search_huati(R.drawable.lhd_search4,"#美丽的海底世界#","如何提高水彩画的画画技巧","讨论 2230","阅读 1.4万");
        huatilist.add(item1);
        Search_huati item2 = new Search_huati(R.drawable.lhd_search5,"#秋天的田野#","油画的颜色怎样合理的搭配","讨论 2230","阅读 1.4万");
        huatilist.add(item2);
        Search_huati item3= new Search_huati(R.drawable.lhd_search6,"#优秀的油画作品#","选出你心目中最好看的作品","讨论 2230","阅读 1.4万");
        huatilist.add(item3);

    }
    public class HuatiAdapter extends RecyclerView.Adapter<HuatiAdapter.ViewHolder>{
           private List<Search_huati>mHuaTiList;
        public class ViewHolder extends RecyclerView.ViewHolder{
            private ImageView image;
            private TextView biaoti;
            private TextView content;
            private TextView member;
            private TextView time;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                image = (ImageView)itemView.findViewById(R.id.image_huati);
                biaoti = (TextView)itemView.findViewById(R.id.biaoti_huati);
                content = (TextView)itemView.findViewById(R.id.content_huati);
                member = (TextView)itemView.findViewById(R.id.member);
                time = (TextView)itemView.findViewById(R.id.time);
            }
        }
        public HuatiAdapter (List<Search_huati>Huatilist){
            mHuaTiList = Huatilist;
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_search_item2,viewGroup,false);
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
              Search_huati huati = mHuaTiList.get(i);
              viewHolder.image.setImageResource(huati.getImage());
              viewHolder.biaoti.setText(huati.getBiaoti());
              viewHolder.content.setText(huati.getContent());
              viewHolder.member.setText(huati.getMember());
              viewHolder.time.setText(huati.getTime());
        }

        @Override
        public int getItemCount() {
            return mHuaTiList.size();
        }
    }
}
