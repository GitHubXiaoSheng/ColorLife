package cn.edu.jssvc.gezhi.colorlife.my;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

//滑动监听
public abstract class MyRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();//获取最后一个完全显示的ItemPosition
        // 当不滚动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            // 判断是否滚动到顶部
            if (lastVisibleItem == 0) {
                onTop();
//                mTextViewPersonalise.startAnimation(mShowAnim);
//                mTextViewPersonalise.setVisibility(View.VISIBLE);
            }
        } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING ) {//拖动中
            onDrag();
//            if (mTextViewPersonalise.getVisibility() == View.VISIBLE) {
//                mTextViewPersonalise.startAnimation(mHiddenAmin);
//                mTextViewPersonalise.setVisibility(View.INVISIBLE);//注意此处不要使用View.GONE
//            }
        }
    }

    public abstract void onTop();
    public abstract void onDrag();
}