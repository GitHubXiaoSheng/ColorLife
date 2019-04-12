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

public class PaihangFragment extends Fragment {
    private List<Search_paihang> Paihang = new ArrayList<>();
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_paihang,container,false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.paihang_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        PaihangAdapter adapter = new PaihangAdapter(Paihang);
        recyclerView.setAdapter(adapter);
        showList();
    }

    void showList(){
         Search_paihang item1 = new Search_paihang(R.drawable.lhd_search1,"秋天的田野","喜欢 2230");
         Paihang.add(item1);
        Search_paihang item2 = new Search_paihang(R.drawable.lhd_search2,"成熟的樱桃","喜欢 1950");
        Paihang.add(item2);
        Search_paihang item3 = new Search_paihang(R.drawable.lhd_search3,"河边飘荡的芦苇","喜欢 1723");
        Paihang.add(item3);
    }
public class PaihangAdapter extends RecyclerView.Adapter<PaihangAdapter.ViewHolder>{
    private List<Search_paihang>mPaihangList;
       public class ViewHolder extends RecyclerView.ViewHolder{
           private ImageView image;
           private TextView biaoti;
           private TextView redu;
           public ViewHolder(@NonNull View itemView) {
               super(itemView);
               image = (ImageView)itemView.findViewById(R.id.image);
               biaoti  = (TextView)itemView.findViewById(R.id.biaoti);
               redu = (TextView)itemView.findViewById(R.id.redu);
           }
       }
       public PaihangAdapter(List<Search_paihang>PaihangList){
           mPaihangList = PaihangList;
       }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
           View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_search_item1,viewGroup,false);
           ViewHolder viewHolder = new ViewHolder(view);

           return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
           Search_paihang paihang = mPaihangList.get(i);
           viewHolder.image.setImageResource(paihang.getImage());
           viewHolder.biaoti.setText(paihang.getBiaoti());
           viewHolder.redu.setText(paihang.getRedu());
    }

    @Override
    public int getItemCount() {
        return mPaihangList.size();
    }
}

}
