package gaming178.com.mylibrary.allinone.volley.toolbox;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import gaming178.com.mylibrary.allinone.VolleyUtil;
import gaming178.com.mylibrary.allinone.volley.Cache.Entry;
import gaming178.com.mylibrary.allinone.volley.NetworkResponse;
import gaming178.com.mylibrary.allinone.volley.ParseError;
import gaming178.com.mylibrary.allinone.volley.Request;
import gaming178.com.mylibrary.allinone.volley.Response;
import gaming178.com.mylibrary.allinone.volley.Response.ErrorListener;
import gaming178.com.mylibrary.allinone.volley.Response.Listener;

/**
 * Wrapper for Volley requests to facilitate parsing of json responses.
 *
 * @param <T>
 */
public abstract class CookieRequest<T> extends Request<T>  {
    private Map<String, String> mHeaders=new HashMap<String, String>(1);

    /**
     * Callback for response delivery
     */
    private final Listener<T> mListener;

    private boolean refreshNeedCheckServer = false;
    /**
     * Class type for the response
     */
    private Context mContext;
    /**
     * @param method        Request type.. Method.GET etc
     * @param url           path for the request
     * @param listener      handler for the response
     * @param errorListener handler for errors
     */
    public CookieRequest(Context context, int method, String url,  Listener<T> listener,
                         ErrorListener errorListener) {

        super(method, url, errorListener);
        this.mListener = listener;
        this.mContext=context;
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
//           String mHeader = response.headers.toString();
//            Log.w("HttpVolley","get headers in parseNetworkResponse "+mHeader);
            //使用正则表达式从reponse的头中提取cookie内容的子串
//            Pattern pattern=Pattern.compile("Set-Cookie.*?;");
//            Matcher m=pattern.matcher(mHeader);
//
//            if(m.find()){
//                cookieFromResponse =m.group();
//                Log.w("HttpVolley","cookie from server "+ cookieFromResponse);
//            }
//            if(!cookieFromResponse.equals("")) {
//                //去掉cookie末尾的分号
//                cookieFromResponse = cookieFromResponse.substring(11, cookieFromResponse.length() - 1);
//                VolleyUtil.saveCookie(mContext, cookieFromResponse);
//            }
            String cookieFromResponse = response.headers.get("Set-Cookie");

            if(cookieFromResponse!=null) {
                String[] cookies=cookieFromResponse.split(";");
                if(cookies!=null&&!cookies[0].equals(""))
                    cookieFromResponse=cookies[0];

            }
            VolleyUtil.saveCookie(mContext, cookieFromResponse);
            Entry entry = new Entry();
            entry.data = response.data;
            Log.w("HttpVolley","data:"+new String(response.data,"UTF-8"));
            try {
                entry.etag = getUrl() + getParams().hashCode();
            } catch (Exception e) {
                entry.etag = getUrl();
                e.printStackTrace();
            }

            entry.softTtl = System.currentTimeMillis() + 1000 * 60 * 2;
            entry.ttl = entry.softTtl;
            entry.serverDate = System.currentTimeMillis();
            entry.responseHeaders =response.headers;
            T t= getResultFromResponse(entry);
            if (!refreshNeedCheckServer)
                saveCache(response);
            return Response.success(t,
                    HttpHeaderParser.parseCacheHeaders(response));

        } catch (Exception e) {
            if (!refreshNeedCheckServer) {
                Response<T> res = null;
                try {
                    res = getCache();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    return Response.error(new ParseError(e));
                }
                if (res != null)
                    return res;
            }

            return Response.error(new ParseError(e));
        }
    }

    protected abstract T getResultFromResponse(Entry response);

    private Response<T> getCache() throws Exception {
        String key = "";
        try {
            key = getUrl() + getParams().hashCode();
        } catch (Exception e) {
            key = getUrl();
            e.printStackTrace();
        }
        Entry entry = Volley.diskBasedCache.get(key);
        T t = getResultFromResponse(entry);
        return Response.success(t, entry);
    }

    private Entry saveCache(NetworkResponse response) {
        Entry entry = new Entry();
        entry.data = response.data;
        try {
            entry.etag = getUrl() + getParams().hashCode();
        } catch (Exception e) {
            entry.etag = getUrl();
            e.printStackTrace();
        }

        entry.softTtl = System.currentTimeMillis() + 1000 * 60 * 2;
        entry.ttl = entry.softTtl;
        entry.serverDate = System.currentTimeMillis();
        entry.responseHeaders =response.headers;
        Volley.diskBasedCache.put(entry.etag, entry);
        return entry;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }


//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        Log.w("HttpVolley","get-header:"+mHeaders);
//        return mHeaders;
//    }
    public void setCookie(String cookie) {
        mHeaders.put("cookie+", cookie);
    }

}