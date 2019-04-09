package cn.edu.jssvc.gezhi.colorlife.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.R;

public class SearchFragment extends Fragment {
private View view;
    private SearchView mSearchView,searchview;
    private EditText editText;
    private Button zkbtn;
    private TabLayout tablayout;
    private FlowLayout mflowlayout;
    private ViewPager viewpager;
    private ArrayList<Fragment> fragmentList;
    private List<String> list_Title;
    private String mNames[] = {
            "welcome","android","TextView",
            "apple","jamy","kobe bryant",
            "jordan","layout","viewgroup",
            "margin","padding","text",
            "name","type"
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_search, container, false);
        mSearchView = (SearchView)view.findViewById(R.id.edit_search);
        zkbtn = (Button)view.findViewById(R.id.zhankai);
        tablayout = (TabLayout)view.findViewById(R.id.tablayout);
        viewpager = (ViewPager)view.findViewById(R.id.viewpage);
        mSearchView.setFocusable(false);
        mSearchView.clearFocus();
        initChildView();
        initSearch();
        initView();
        return view;

    }
    private void initSearch(){
        if (mSearchView==null){
            return;
        }else {
            SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete)mSearchView.findViewById(R.id.search_src_text);
            textView.setTextColor(Color.GRAY);
            textView.setHintTextColor(Color.GRAY);
            textView.setTextSize(15);

        }
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }
    private  void initChildView() {
        mflowlayout = (FlowLayout) view.findViewById(R.id.flowlayout);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;
        for (int i = 0; i < mNames.length; i++) {
            TextView view = new TextView(getContext());
            view.setText(mNames[i]);
            view.setTextColor(Color.WHITE);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.textview_bg));
            mflowlayout.addView(view, lp);
        }
    }
    private  void initView(){
        fragmentList = new ArrayList<>();
        list_Title = new ArrayList<>();
        fragmentList.add(new ReMenFragment());
        fragmentList.add(new HuaTiBangFragment());
        fragmentList.add(new SheQuFragment());
        list_Title.add("热门话题");
        list_Title.add("话题榜");
        list_Title.add("社区");
        viewpager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager(),getActivity(),fragmentList,list_Title));
        tablayout.setupWithViewPager(viewpager);
    }


}
