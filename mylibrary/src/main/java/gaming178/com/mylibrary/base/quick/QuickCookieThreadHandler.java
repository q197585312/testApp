package gaming178.com.mylibrary.base.quick;

import android.content.Context;

import gaming178.com.mylibrary.allinone.http.StringRequestCookie;
import gaming178.com.mylibrary.allinone.volley.Request;
import gaming178.com.mylibrary.allinone.volley.Response;
import gaming178.com.mylibrary.allinone.volley.VolleyError;
import gaming178.com.mylibrary.base.RequestBean;

public abstract class QuickCookieThreadHandler extends QuickThreadHandler<String, String, String> {
    public Context mContext;
    public QuickCookieThreadHandler(Context context) {
        super(context);
        this.mContext = context;
    }
    @Override
    protected QuickRequestBean handlePageParams(QuickRequestBean bean, Integer obj, int pageSize) {
        return bean;
    }
    @Override
    protected Request<String> getRequest(final QuickRequestBean bean) {
        if (bean.getMethod() == RequestBean.Method.GET) {
            return new StringRequestCookie(Request.Method.GET, bean.getUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    successEnd(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorEnd(error.getMessage());
                }
            });
        }else{
            return new StringRequestCookie(Request.Method.POST, bean.getUrl(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    successEnd(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorEnd(error.getMessage());
                }
            });
        }
    }

}
