package nanyang.com.dig88.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/9/19.
 */

public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {
    private View rooterView;
    private boolean isHaveMoreData = true;// 是否有更多数据(默认为有)
    private ProgressBar progressBar;
    private TextView tipContext;
    private String loading;
    private String loadingComplete;

    private RefreshAndLoadMoreView mRefreshAndLoadMoreView;
    private boolean isLoading = false;// 是否正在加载

    private OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //动态载入底部布局
        loading = context.getString(R.string.loading);
        loadingComplete = context.getString(R.string.Loading_completed);
        rooterView = LayoutInflater.from(context).inflate(
                R.layout.pull_to_load_footer, null);
        progressBar = (ProgressBar) rooterView.findViewById(R.id.footer_progressbar);
        tipContext = (TextView) rooterView.findViewById(R.id.footer_hint_textview);
        //向listView的底部添加布局(此时当给listView设置Item点击事件的时候，默认不触发这个添加的布局的点击事件)
        addFooterView(rooterView, null, false);
        setOnScrollListener(this);
    }

    public void setRefreshAndLoadMoreView(RefreshAndLoadMoreView mRefreshAndLoadMoreView) {
        this.mRefreshAndLoadMoreView = mRefreshAndLoadMoreView;
    }

    /**
     * 设置是否还有更多数据
     *
     * @param isHaveMoreData
     */
    public void setHaveMoreData(boolean isHaveMoreData) {
        this.isHaveMoreData = isHaveMoreData;
        if (!isHaveMoreData) {
            tipContext.setText(loadingComplete);
            progressBar.setVisibility(View.GONE);
        } else {
            tipContext.setText(loading);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载完成
     */
    public void onLoadComplete() {
        isLoading = false;
    }

    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            if (view.getLastVisiblePosition() == view.getCount() - 1 && (mRefreshAndLoadMoreView != null &&
                    !mRefreshAndLoadMoreView.isRefreshing()) && !isLoading && mOnLoadMoreListener != null && isHaveMoreData) {
                isLoading = true;
                mOnLoadMoreListener.onLoadMore();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    /**
     * 设置加载监听
     *
     * @param mOnLoadMoreListener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    /**
     * 加载更多的监听
     */
    public static interface OnLoadMoreListener {
        public void onLoadMore();
    }
}
