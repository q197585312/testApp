package gaming178.com.mylibrary.allinone.volley.toolbox;

import android.content.Context;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import gaming178.com.mylibrary.allinone.util.JSONUtils;
import gaming178.com.mylibrary.allinone.volley.Cache.Entry;
import gaming178.com.mylibrary.allinone.volley.Response.ErrorListener;
import gaming178.com.mylibrary.allinone.volley.Response.Listener;

/**
 * Wrapper for Volley requests to facilitate parsing of json responses.
 *
 * @param <T>
 */
public class GsonRequest<T> extends CookieRequest<T>  {
    private Map<String, String> mHeaders=new HashMap<String, String>(1);
    /**
     * Gson parser
     */
    private final Gson mGson;
    /**
     * Callback for response delivery
     */
    private final Listener<T> mListener;
    private boolean refreshNeedCheckServer = false;
    /**
     * Class type for the response
     */
    private Type type;
    private T t;
    private Context mContext;
    /**
     * @param method        Request type.. Method.GET etc
     * @param url           path for the requests
     * @param type          expected class type for the response. Used by gson for
     *                      serialization.
     * @param listener      handler for the response
     * @param errorListener handler for errors
     */
    public GsonRequest(Context context,int method, String url, Type type, Listener<T> listener,
                       ErrorListener errorListener) {
        super(context,method,url,listener,errorListener);
        this.type = type;
        this.mListener = listener;
        mGson = new Gson();
        this.mContext=context;
    }
    public GsonRequest(Context context,int method, String url, Listener<T> listener,
                       ErrorListener errorListener) {
        super(context,method,url,listener,errorListener);
        this.type = JSONUtils.getT(this,0);
        this.mListener = listener;
        mGson = new Gson();
        this.mContext=context;
    }


    @Override
    protected T getResultFromResponse(Entry response) {
            T t=null;
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.responseHeaders));
            t = mGson.fromJson(json, type);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return t;
    }


}