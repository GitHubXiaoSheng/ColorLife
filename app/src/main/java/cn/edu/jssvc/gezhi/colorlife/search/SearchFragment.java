package cn.edu.jssvc.gezhi.colorlife.search;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import cn.edu.jssvc.gezhi.colorlife.MyApplication;
import cn.edu.jssvc.gezhi.colorlife.R;
import cn.edu.jssvc.gezhi.colorlife.home.Arts_info;

public class SearchFragment extends Fragment {
private View view;
    private SearchView mSearchView,searchview;
    private EditText editText;
    private Button zkbtn;
    private TabLayout tablayout;
    private FlowLayout mflowlayout;
    private ViewPager viewpager;
    private ListView listView;
    private List<String>list = new ArrayList<String>();
    private ArrayList<Fragment> fragmentList;
    private List<String> list_Title;
    private boolean isKeyUp=true;
    private LinearLayout linearLayout;
    private MyAdapter adapter = null;
    private Button search;
    private ImageView delete;

    private String mNames[] = {
            "水彩画细节","如何提高画画技术","中西结合的作品",
            "作品评估","家庭壁画","油画",
            "世界名画","画展","印象派"

    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_search, container, false);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        mSearchView = (SearchView)view.findViewById(R.id.edit_search);
        editText = (EditText)view.findViewById(R.id.edit_search);
        zkbtn = (Button)view.findViewById(R.id.zhankai);
        tablayout = (TabLayout)view.findViewById(R.id.tablayout);
        viewpager = (ViewPager)view.findViewById(R.id.viewpage);
        listView = (ListView)view.findViewById(R.id.ls_view);
        linearLayout = (LinearLayout)view.findViewById(R.id.listview_1);
        search = (Button)view.findViewById(R.id.search);
        delete  = (ImageView)view.findViewById(R.id.lhd_delete);
//        mSearchView.setFocusable(false);
//        mSearchView.clearFocus();
        replacefragment(new PaihangFragment());
        replacefragment(new HuaTiBangFragment());
        initChildView();
        initedit();
//        initSearch();
        setData();

            initView();



    }
    private void setData() {
//        initData();// 初始化数据

        // 这里创建adapter的时候，构造方法参数传了一个接口对象，这很关键，回调接口中的方法来实现对过滤后的数据的获取
        adapter = new MyAdapter(list, getContext(), new FilterListener() {
            // 回调方法获取过滤后的数据
            public void getFilterData(List<String> list) {
                // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
                Log.e("TAG", "接口回调成功");
                Log.e("TAG", list.toString());
                setItemClick(list);
            }
        });
        listView.setAdapter(adapter);
    }
    protected void setItemClick(final List<String> filter_lists) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 点击对应的item时，弹出toast提示所点击的内容
                Toast.makeText(getContext(), filter_lists.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
//    private void initData() {
//
//
//    }


    void replacefragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.viewpage,fragment).commit();
    }

    void initedit(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                editText.clearFocus();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchItemActivity.class);
                intent.putExtra("leibie",editText.getText().toString());
                startActivity(intent);
            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    listView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                }else{
                    listView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        });
       editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(adapter != null){
                   adapter.getFilter().filter(s);
               }

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
    }
//    private void initSearch(){
//        if (mSearchView==null){
//            return;
//        }else {
//            SearchView.SearchAutoComplete textView = (SearchView.SearchAutoComplete)mSearchView.findViewById(R.id.search_src_text);
//            textView.setTextColor(Color.GRAY);
//            textView.setHintTextColor(Color.GRAY);
//            textView.setTextSize(15);
//
//        }
//
//        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                Log.d("close","close");
//                return false;
//            }
//        });
//        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("open","open");
//            }
//        });
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return true;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if(adapter != null){
//                    adapter.getFilter().filter(newText);
//                }
//                return true;
//            }
//
//        });
//    }



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

        fragmentList.add(new PaihangFragment());
        fragmentList.add(new HuaTiBangFragment());
        list_Title.add("原创排行榜");
        list_Title.add("今日话题");
        FragmentManager childFragment = getChildFragmentManager();
        viewpager.setAdapter(new MyPagerAdapter(childFragment,getActivity(),fragmentList,list_Title));
        tablayout.setupWithViewPager(viewpager);
    }
}
