package nanyang.com.dig88.Base;

import android.content.Context;

import java.util.HashMap;

import gaming178.com.mylibrary.base.quick.QuickBaseGsonThreadHandler;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;


/**
 * Created by Administrator on 2015/10/30.
 */
public abstract class SportJsonThreadHandler<T> extends QuickBaseGsonThreadHandler<T> {
    public SportJsonThreadHandler(Context context) {
        super(context);
    }

    @Override
    protected QuickRequestBean handlePageParams(QuickRequestBean bean, Integer obj, int pageSize) {
        return bean;
    }
    @Override
    protected QuickRequestBean getRequestBean() {
        HashMap<String, String> params = new HashMap<>();
        return getNyRequestBean(params);
    }

    protected abstract QuickRequestBean getNyRequestBean(HashMap<String, String> params);
}
