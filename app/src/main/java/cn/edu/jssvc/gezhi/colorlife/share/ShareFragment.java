package cn.edu.jssvc.gezhi.colorlife.share;

        import android.content.Context;
        import android.graphics.Color;
        import android.graphics.Typeface;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.FrameLayout;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import cn.edu.jssvc.gezhi.colorlife.R;

public class ShareFragment extends Fragment implements View.OnClickListener {
    private TextView followTV,hotTv,followTvline,hotTvline;
    private LinearLayout replacefragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        replacefragment(new Yws_Follow_Fragment());
        followTvline.setBackgroundColor(Color.RED);
        followTV.setTextColor(Color.BLUE);
        followTV.setTextSize(24);
    }

    void init(){
        followTV=getActivity().findViewById(R.id.text_follow);
        hotTv=getActivity().findViewById(R.id.text_hot);
        followTvline=getActivity().findViewById(R.id.text_followline);
        hotTvline=getActivity().findViewById(R.id.text_hotline);
        followTV.setOnClickListener(this);
        hotTv.setOnClickListener(this);
        replacefragment=getActivity().findViewById(R.id.replace_Linearlayout);
    }
    void replacefragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.replace_Linearlayout,fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_follow:
                replacefragment(new Yws_Follow_Fragment());
                followTvline.setBackgroundColor(Color.RED);
                hotTvline.setBackgroundColor(Color.WHITE);
                followTV.setTextColor(Color.BLUE);
                followTV.setTextSize(24);
                hotTv.setTextSize(20);
                hotTv.setTextColor(Color.BLACK);
                followTV.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗

                break;
            case R.id.text_hot:
                replacefragment(new Yws_Hot_Fragment());
                followTvline.setBackgroundColor(Color.WHITE);
                hotTvline.setBackgroundColor(Color.RED);
                hotTv.setTextColor(Color.BLUE);
                hotTv.setTextSize(24);
                followTV.setTextSize(20);
                followTV.setTextColor(Color.BLACK);
                hotTvline.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                break;
                default:
                    break;
        }
    }
}
