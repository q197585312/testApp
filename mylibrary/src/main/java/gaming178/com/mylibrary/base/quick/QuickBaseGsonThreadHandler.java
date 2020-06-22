package gaming178.com.mylibrary.base.quick;

import android.content.Context;

import java.util.Map;

import gaming178.com.mylibrary.allinone.volley.AuthFailureError;
import gaming178.com.mylibrary.allinone.volley.Request;
import gaming178.com.mylibrary.allinone.volley.Response;
import gaming178.com.mylibrary.allinone.volley.VolleyError;
import gaming178.com.mylibrary.allinone.volley.toolbox.GsonRequest;
import gaming178.com.mylibrary.base.RequestBean;

public abstract class QuickBaseGsonThreadHandler<T> extends QuickThreadHandler<T, T, String> {
    Context mContext;
    public QuickBaseGsonThreadHandler(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected GsonRequest<T> getRequest(final QuickRequestBean bean) {
        if (bean.getMethod() == RequestBean.Method.GET) {
            return new GsonRequest<T>(mContext,Request.Method.GET, bean.getUrl(), bean.getResponeType(), new Response.Listener<T>() {
                @Override
                public void onResponse(T response) {
                    successEnd(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorEnd(error.getMessage());
                }
            });
        } else {
            return new GsonRequest<T>(mContext,Request.Method.POST, bean.getUrl(), bean.getResponeType(), new Response.Listener<T>() {
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
                protected Map<String, String> getParams() throws AuthFailureError {
                    return bean.getParams();
                }
            };
        }

    }

}
