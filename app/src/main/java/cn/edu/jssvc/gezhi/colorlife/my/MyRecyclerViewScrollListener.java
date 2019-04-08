package cn.edu.jssvc.gezhi.colorlife.my;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

//滑动监听
public abstract class MyRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private String TAG = "MyRecyclerViewScrollListener";
    private int dy = 0;
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
//        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();//获取最后一个完全显示的ItemPosition
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                onTop(dy);
                Log.d(TAG, "onScrollStateChanged:没有滚动 ");
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                onDrag();
                Log.d(TAG, "onScrollStateChanged:被触摸滚动中 ");
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                Log.d(TAG, "onScrollStateChanged:自动滚动中");
                break;
            default:
                break;
        }
    }
    public abstract void onTop(int dy);
    public abstract void onDrag();

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        this.dy = dy;
    }
}