package gaming178.com.mylibrary.base.quick;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import gaming178.com.mylibrary.allinone.util.LogUtils;
import gaming178.com.mylibrary.allinone.volley.AuthFailureError;
import gaming178.com.mylibrary.allinone.volley.Cache;
import gaming178.com.mylibrary.allinone.volley.Request;
import gaming178.com.mylibrary.allinone.volley.Response;
import gaming178.com.mylibrary.allinone.volley.VolleyError;
import gaming178.com.mylibrary.allinone.volley.toolbox.CookieRequest;
import gaming178.com.mylibrary.allinone.volley.toolbox.HttpHeaderParser;
import gaming178.com.mylibrary.base.RequestBean;

public abstract class QuickBaseXmlThreadHandler<T> extends QuickThreadHandler<T, T, String> {
    Context mContext;

    public QuickBaseXmlThreadHandler(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public void errorEnd(String obj) {
        super.errorEnd(obj);
        if (obj != null)
            LogUtils.d(obj);
    }


    @Override
    protected CookieRequest<T> getRequest(final QuickRequestBean bean) {
        if (bean.getMethod() == RequestBean.Method.GET) {
            return new CookieRequest<T>(mContext,Request.Method.GET, bean.getUrl(), new Response.Listener<T>() {
                @Override
                public void onResponse(T response) {
                    successEnd(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorEnd(error.getMessage());
                }
            }) {
                @Override
                protected T getResultFromResponse(Cache.Entry response) {
                    T t=null;
                    try {
                        String json = new String(response.data,
                                HttpHeaderParser.parseCharset(response.responseHeaders));
                        t = baseParseXml(json);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return t;
                }

            };
        } else {
            return new CookieRequest<T>(mContext,Request.Method.POST, bean.getUrl(),  new Response.Listener<T>() {
                @Override
                public void onResponse(T response) {
                    successEnd(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorEnd(error.getMessage());
                }
            }) {
                @Override
                protected T getResultFromResponse(Cache.Entry response) {
                    T t=null;
                    try {
                        String json = new String(response.data,
                                HttpHeaderParser.parseCharset(response.responseHeaders));
                        t = baseParseXml(json);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return t;
                }
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return bean.getParams();
                }
            };
        }

    }

    protected abstract T baseParseXml(String json);


}
