package gaming178.com.mylibrary.allinone.http;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import gaming178.com.mylibrary.allinone.volley.AuthFailureError;
import gaming178.com.mylibrary.allinone.volley.NetworkResponse;
import gaming178.com.mylibrary.allinone.volley.Response;
import gaming178.com.mylibrary.allinone.volley.Response.ErrorListener;
import gaming178.com.mylibrary.allinone.volley.Response.Listener;
import gaming178.com.mylibrary.allinone.volley.toolbox.StringRequest;

public class StringRequestCookie extends StringRequest {

    public static String cookie="";

    public StringRequestCookie(String url, Listener<String> listener,
                               ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public StringRequestCookie(int method,String url, Listener<String> listener,
                               ErrorListener errorListener) {
        super(method,url, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {

        // 仿cookies store, 存储cookies
        HashMap<String, String> map = new HashMap<String, String>();
        clearCookie();
        for (String s : response.headers.keySet()) {
            if (s.contains("Set-Cookie")) {
                String mCookie = response.headers.get(s);
                mCookie = mCookie.substring(0, mCookie.indexOf(";"));
                cookie = cookie + mCookie + ";";
                String[] cookieWhole = mCookie.split("; ");
                for (String string : cookieWhole) {

                    int startIndex = string.indexOf("=");
                    if (startIndex != -1) {
                        String key = string.substring(0, startIndex);
                        String value = string.substring(startIndex + 1,
                                string.length());
                        map.put(key, value);
                    }
                }
            }
        }
        if(cookie!=null)
        cookie = cookie +  "ILSessionInfo=;";
        Log.w("HttpVolley", cookie);
        return super.parseNetworkResponse(response);
    }

    /**
     * 重新设置Cookie
     *
     * @param cookie
     */
    public void setCookie(String cookie) {
        StringRequestCookie.cookie = cookie;
    }

    public void clearCookie() {
        cookie = "";
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> map = new HashMap<String, String>();
		map.put("Cookie", cookie);
        Log.w("HttpVolley","map:-------->"+ cookie);
        return map;
    }

}