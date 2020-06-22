package gaming178.com.mylibrary.base.quick;

import android.content.Context;

import java.util.HashMap;

import gaming178.com.mylibrary.base.RequestBean;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;

public abstract class QuickListGsonThreadHandler<T> extends
        QuickBaseGsonThreadHandler<QuickBaseResponse<T>> {
    Context mContext;
    PullToRefreshBase refreshView;

    public QuickListGsonThreadHandler(Context context) {
        super(context);
        this.mContext = context;
    }

    public PullToRefreshBase getRefreshView() {
        return refreshView;
    }

    public void setRefreshView(PullToRefreshBase refreshView) {
        this.refreshView = refreshView;
    }

    @Override
    public void errorEnd(String obj) {
        super.errorEnd(obj);
        if (refreshView != null)
            refreshView.onRefreshComplete();
    }

    @Override
    public void successEnd(QuickBaseResponse<T> obj) {
        super.successEnd(obj);
        if (obj == null)
            return;
        String end = obj.getSuccess();
        if (end.equals("1")) {
            successEndList(obj.getResult());
        } else {
            errorEnd(obj.getReason());
        }
    }

    protected abstract void successEndList(T result);

    @Override
    protected QuickRequestBean handleRequestBean(QuickRequestBean bean, Integer obj, int size) {
        if (bean == null || obj == null) {
            return null;
        }
        if (bean.getMethod() == RequestBean.Method.GET) {
            String url = bean.getUrl();
            url = url + "&pagesize=" + size + "&page=" + obj;
            bean.setUrl(url);
        } else {
            HashMap params = bean.getParams();
            params.put("pagesize", "10");
            params.put("pageSize", size + "");
            bean.setParams(params);
        }
        return bean;
    }

}
