package gaming178.com.mylibrary.base;

import android.content.Context;
import android.util.Log;

import gaming178.com.mylibrary.allinone.util.BlockDialog;

/**
 * Created by Administrator on 2015/9/2.
 */
public abstract class PageThreadhandler<B extends RequestBean, S, E> extends ThreadHandler<Integer, B, S, E> {
    Context mContext;
    BlockDialog mDialog;
    boolean mLoadable;
    /**
     * 每页默认加载数量
     */
    private int pageSize = 10;

    public PageThreadhandler(Context context) {
        super(context);
        this.mContext = context;
        this.mLoadable = true;
    }

    @Override
    protected void handleHttp(B bean, Integer obj) {
        bean = handleRequestBean(bean, obj, getPageSize());
        handlePageHttp(bean);
    }

    protected abstract void handlePageHttp(B bean);

    protected abstract B handleRequestBean(B bean, Integer obj, int pageSize);

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setDisplayLoading(boolean mLoadable) {
        this.mLoadable = mLoadable;

    }

    @Override
    public void startThread(Integer obj) {
        super.startThread(obj);

    }

    @Override
    public void successEnd(S obj) {
        if(obj!=null)
            Log.d("HttpVolley", obj.toString());

    }

    @Override
    public void errorEnd(E obj) {
        if(obj!=null)
            Log.d("HttpVolley", obj.toString());
    }
}
