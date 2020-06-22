package gaming178.com.mylibrary.base.quick;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import gaming178.com.mylibrary.allinone.RequestUtils;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.allinone.volley.Request;
import gaming178.com.mylibrary.base.PageThreadhandler;
import gaming178.com.mylibrary.base.RequestBean;


public abstract class QuickThreadHandler<T, S, E> extends PageThreadhandler<QuickRequestBean, S, E> {

    public Context mContext;


    public QuickThreadHandler(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void handlePageHttp(QuickRequestBean bean) {
        Request<T> request = getRequest(bean);
        if(bean.getMethod()== RequestBean.Method.GET){
            Log.w("HttpVolley",bean.getUrl());
        }else {
            Log.w("HttpVolley",bean.getUrl()+bean.getParams().toString());
        }
        request.setTag(mContext);
        RequestUtils.init(mContext);
        RequestUtils.addRequsetQueue(request, mContext);
    }
    protected abstract Request<T> getRequest(QuickRequestBean bean);

    @Override
    protected QuickRequestBean handleRequestBean(QuickRequestBean bean, Integer obj, int pageSize) {
        if(bean.getMethod()== RequestBean.Method.GET){
           HashMap<String,String>map= bean.getParams();
            if(map!=null) {
                ArrayList<String> strings=new ArrayList<>();
                for (Map.Entry item :
                        map.entrySet()) {
                    strings.add((String) item.getKey());
                    strings.add((String) item.getValue());
                }
                String params = StringUtils.getParameterStr(strings.toArray(new String[strings.size()]));
                String url = bean.getUrl();
                if (url.endsWith("?")) {
                    url = url.substring(0, url.length() - 1) + params;
                } else {
                    url = url + params;
                }
                bean.setUrl(url);
            }
        }
        return   handlePageParams(bean,obj,pageSize);
    }

    protected abstract QuickRequestBean handlePageParams(QuickRequestBean bean, Integer obj, int pageSize);
}
