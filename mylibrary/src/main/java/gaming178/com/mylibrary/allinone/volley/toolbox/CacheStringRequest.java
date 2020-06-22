package gaming178.com.mylibrary.allinone.volley.toolbox;

import java.util.Map;

import gaming178.com.mylibrary.allinone.volley.Cache.Entry;
import gaming178.com.mylibrary.allinone.volley.Response.ErrorListener;
import gaming178.com.mylibrary.allinone.volley.Response.Listener;
import gaming178.com.mylibrary.allinone.volley.VolleyError;

public class CacheStringRequest extends StringRequest {
    private final static String CACHE_NAME = "xs";
    private static int params;

    public CacheStringRequest(String url, Listener<String> listener,
                              ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    public CacheStringRequest(int method, final String url,
                              final Listener<String> listener, final ErrorListener errorListener) {
        super(method, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                saveChae(url, response);
                listener.onResponse(response);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String response = getCache(url);
                if (response != null && !"".equals(response)) {
                    listener.onResponse(response);
                } else {
                    errorListener.onErrorResponse(error);
                }
            }
        });
    }

    protected static String getCache(String url) {
        url += CACHE_NAME;
        String response = null;
        Entry entry = Volley.diskBasedCache.get(url + params);
        if (entry != null) {
            response = new String(entry.data);
        }
        return response;
    }

    protected static void saveChae(String url, String response) {
        url += CACHE_NAME;
        Entry entry = new Entry();
        entry.data = response.getBytes();
        entry.etag = "";
        entry.softTtl = 0;
        entry.ttl = 0;
        entry.serverDate = 0;
        entry.responseHeaders = null;
        Volley.diskBasedCache.put(url + params, entry);
    }

    protected void setCacheParams(Map<String, String> map) {
        params = map.hashCode();
    }

}
