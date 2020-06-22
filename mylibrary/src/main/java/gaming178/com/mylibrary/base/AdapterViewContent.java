package gaming178.com.mylibrary.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;

import java.util.List;

import gaming178.com.mylibrary.R;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase.Mode;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

/**
 * @Filename ContentView.java
 * @Description
 * @Version 1.0
 * @Author xs
 */

public class AdapterViewContent<T> implements IAdapterContent<T> {
    public Mode refreshType = Mode.DISABLED;
    /**
     * listview或者gridview(如果是PullToRefreshBase，那就得到他的PullToRefreshBase.
     * getRefreshableView)
     */
    protected AdapterView<ListAdapter> lv;
    /**
     * 显示数据的适配器 extends BaseAdapter
     */
    protected QuickBaseAdapter<T> quickAdapter;
    protected Context context;
    /**
     * 当前加载的数据页面、每次加载数据成功后页面会自加1
     */
    int page;
    /**
     * 总共会加载的数据条数，加载页面数据成功后会更新这个条数
     */
    int total;
    /**
     * 上拉下来操作的 PullToRefreshBase（PullToRefreshListView/PullToRefreshGridView/
     * PullToRefreshScrollView）
     */
    private PullToRefreshBase pv;
    /**
     * 得到的要显示的数据
     */
    private List<T> list;
    /**
     * 指定Adapter中每个Item Layout源，对每个Item数据填充的接口
     */
    private QuickAdapterImp<T> adapterImp;
    /**
     * 加载数据的网络线程接口（常实例化一个QuickGsonThreadHandler）
     */
    private PageThreadhandler thread;
    /**
     * 在没有可以显示数据进行网络请求时默认会显示的loading样式
     */
    private View loadingV;
    /**
     * 加载成功后是否使用动画渐入方式显示每条Item
     */
    private boolean isAnimation = false;
    private boolean isRefreshEnable = false;
    private boolean loadingAble = true;
    /**
     */
    private OnRefreshListener<AbsListView> onRefreshListener = new OnRefreshListener<AbsListView>() {
        @Override
        public void onRefresh(PullToRefreshBase<AbsListView> refreshView) {
            if (refreshView.getCurrentMode() == Mode.PULL_FROM_START) {
                page = 1;
                refreshType = Mode.PULL_FROM_START;
                startThread(page);

            } else {
                if (total > quickAdapter.getCount()) {
                    refreshType = Mode.PULL_FROM_END;
                    startThread(page);
                } else {
                    if (pv != null)
                        pv.onRefreshComplete();
                }
            }
        }
    };

    public AdapterViewContent(Context mContext, AdapterView<ListAdapter> lv) {
        setAdapterView(mContext, lv);
    }

    public boolean isLoadingAble() {
        return loadingAble;
    }

    public void setLoadingAble(boolean loadingAble) {
        this.loadingAble = loadingAble;
    }

    public void setRefreshView(PullToRefreshBase pv) {
        this.pv = pv;
        pv.setOnRefreshListener(onRefreshListener);

    }

    public void setLoadingView(View loadingV) {
        this.loadingV = loadingV;
    }

    private void showLoadingView() {
        if (!loadingAble)
            return;
        if (page < 2) {
            WidgetUtil.coverView(lv, loadingV);
            loadingV.setVisibility(View.VISIBLE);
            // lv.setVisibility(View.GONE);
        }

    }

    private void hideLoadingView() {
        loadingV.setVisibility(View.GONE);
        // lv.setVisibility(View.VISIBLE);

    }

    public void setBaseAdapter(QuickAdapterImp<T> adapterImp) {
        this.adapterImp = adapterImp;
        initDate();
    }

    @Deprecated
    public void setIsAnimation(boolean isAnimation) {
        this.isAnimation = isAnimation;
    }

    private void initDate() {
        quickAdapter = new QuickBaseAdapter<T>(context,
                adapterImp.getBaseItemResource()) {

            @Override
            protected void convert(ViewHolder helper, T item, int position) {
                adapterImp.convert(helper, item, position);
            }
        };
 /*       if (isAnimation) {
             AnimationAdapter animationAdapter = new SwingBottomInAnimationAdapter(quickAdapter);
             animationAdapter.setAbsListView(lv);
             lv.setAdapter(animationAdapter);
        } else*/
            lv.setAdapter(quickAdapter);

    }

    public void setItemClick(final ItemCLickImp<T> itemclick) {
        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                T modle = (T) parent.getItemAtPosition(position);
                itemclick.itemCLick(view,modle, position);
            }
        });
    }

    public int getPage() {
        return page;
    }

    protected void setPage(int page) {
        this.page = page;
    }

    @Override
    public void setData(List<T> obj) {
        hideLoadingView();
        quickAdapter.setList(obj);

    }

    @Override
    public <A extends PageThreadhandler> void setThread(A thread) {
        this.thread = thread;
        thread.setDisplayLoading(false);
    }
    public int getDataCount(){
        return quickAdapter.getCount();
    }

    public T getItem(int position) {
        return quickAdapter.getItem(position);
    }
    public QuickBaseAdapter<T> getAdapter(){
        return quickAdapter;
    }

    public void updateItem(int poistion, T item) {
        quickAdapter.updateItem(poistion, item);
    }

    public void removeItem(int position) {
        quickAdapter.removeItem(position);
    }

    public void AddData(List<T> listData) {
        quickAdapter.addAll(listData);
    }

    public void clearAdapter() {
        quickAdapter.clear();
    }

    /**
     * 有分页的时候才传 没分页传null
     *
     * @param page 当前数据页数
     */
    public void startThread(Integer page) {
        if (page != null) {
            this.page = page;
            isRefreshEnable = true;
        } else
            isRefreshEnable = false;
        showLoadingView();
        thread.startThread(page);
    }

    public void setPageDate(int page, List<T> list) {
        if (page == 1) {
            quickAdapter.setList(list);
        } else if (page > 1) {
            quickAdapter.addAll(list);
        } else {
            quickAdapter.clear();
        }
    }

    /**
     * list带分页时候 调用此方法，把总共的数据个数和本页加载出来的数据传进来（不需要分页直接用setDate（））
     *
     * @param total
     * @param list
     */
    public void successList(int total, List<T> list) {
        hideLoadingView();
        if (pv != null)
            pv.onRefreshComplete();
        setPageDate(page, list);
        this.total = total;
        this.page = ++page;
        if (total > quickAdapter.getCount()) {
            if (pv != null && isRefreshEnable)
                pv.setMode(Mode.BOTH);
        } else {
            if (pv != null && isRefreshEnable)
                pv.setMode(Mode.PULL_FROM_START);
        }
    }

    public void error(String msg) {
        if (pv != null)
            pv.onRefreshComplete();
        hideLoadingView();
    }

    public Mode getRefreshType() {
        return refreshType;
    }

    public void setRefreshType(Mode refreshType) {
        this.refreshType = refreshType;
    }

    public void notifyDataSetChanged() {
        quickAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAdapterView(Context context, AdapterView<ListAdapter> view) {
        this.context = context;
        this.lv = view;
        page = 1;
        this.loadingV = LayoutInflater.from(context).inflate(
                R.layout.base_center_loading, null);
        View v = loadingV.findViewById(R.id.base_center_loading_ll);
        v.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });
    }

}