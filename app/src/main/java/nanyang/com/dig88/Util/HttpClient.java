package nanyang.com.dig88.Util;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//import org.apache.http.cookie.Cookie;


public class HttpClient implements Serializable {
    //   private String IP = "http://192.168.100.22";
    //   private String IP = "http://10.10.30.82/jisuan/";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3";
    public static String cookie;
    private static String sessionId;
    HttpURLConnection httpConn = null;
    private ICookie iCookie;
    private String url;
    private boolean connected = false;
    private Map<String, String> requestHeader = new HashMap<String, String>();
    //  public Cookie web_Cookie = null;
    public HttpClient(String url) {
        this(url, null);
    }

    public HttpClient(ICookie iCookie) {
        this.iCookie = iCookie;
    }

    public HttpClient(String url, String cookie) {
        this.url = url;
    }

    public ICookie getiCookie() {
        return iCookie;
    }

    public String setHttpClient(String url) throws IOException {
        String sReturn = "";
        this.url = url;
        connect("GET");
        sReturn = getBodyString();
        disconnect();
        return sReturn;
    }


    public String sendPost(String urlString, String sParams) throws IOException {
        String sReturn = "";
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader in = null;
        url = new URL(urlString);
        conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
        conn.setUseCaches(true);
        //     HttpURLConnection.setFollowRedirects(false);
        conn.setInstanceFollowRedirects(false);

        if (iCookie != null && iCookie.getCookie() != null) {
            conn.setRequestProperty("Cookie", iCookie.getCookie());
            Log.w("Test", "SetCookie:" + iCookie.getCookie());
        }
        //   Log.i(WebSiteUrl.Tag,"cookie="+cookie);

        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.connect();
        if (iCookie != null){
            cookie = getCookie();
            Log.w("Test", "getCookie:" + cookie.toString());
        }
        sessionId = cookie;
        connected = true;
        conn.getOutputStream().write(sParams.getBytes());
        // 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）
        conn.getOutputStream().flush();
        // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,
        // 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
        conn.getOutputStream().close();

        // 调用HttpURLConnection连接对象的getInputStream()函数,
        // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
        //  InputStream inStrm = httpConn.getInputStream(); // <===注意，实际发送请求的代码段就在这里
        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        sReturn = sb.toString();
        sb = null;
        if (in != null) {
            in.close();
        }
        if (conn != null) {
            conn.disconnect();
        }
        url = null;
        conn = null;
        in = null;
        return sReturn;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void addRequestHeader(String key, String value) {
        this.requestHeader.put(key, value);
    }

    public boolean connect(String method) throws IOException {
        boolean bReturn = true;

            URLConnection con = new URL(url).openConnection();
            if (con instanceof HttpURLConnection) {
                httpConn = (HttpURLConnection) con;

            httpConn.setConnectTimeout(30000);
            httpConn.setReadTimeout(30000);

            if ("POST".equals(method)) {
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);
                httpConn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
            }
            if (iCookie != null && iCookie.getCookie() != null) {
                httpConn.setRequestProperty("Cookie", iCookie.getCookie());
                Log.w("Test", "SetCooke:" + iCookie.getCookie());
            }
            httpConn.setInstanceFollowRedirects(false);
            httpConn.setRequestMethod(method);
            if (cookie != null) {
                //System.out.println(cookie);
                httpConn.addRequestProperty("Cookie", cookie);
            }
            httpConn.setRequestProperty("User-Agent", USER_AGENT);

            httpConn.setUseCaches(true);

            if (!this.requestHeader.isEmpty()) {
                for (String key : this.requestHeader.keySet()) {
                    httpConn.setRequestProperty(key, this.requestHeader.get(key));
                }
            }
            httpConn.connect();
            cookie = getCookie();
            Log.w("Test", "getCookie:" + cookie.toString());
            sessionId = cookie;
            connected = true;

        }
        return bReturn;
    }

    public boolean followRedirects() throws IOException {
        int resultCode = httpConn.getResponseCode();
        return (resultCode >= 300 && resultCode < 400);
    }

    public String getLocation() {
        String location = httpConn.getHeaderField("Location");
        if (location == null) {
            location = httpConn.getHeaderField("location");
        }
        return location;
    }

    public InputStream getInputStream() throws IOException {
        return httpConn.getInputStream();
    }

    public String getBodyString() throws IOException {
        return getBodyString(null);
    }

    public String getBodyString(String charset) throws IOException {
        BufferedReader reader;
        if (charset == null) {
            reader = new BufferedReader(new InputStreamReader(getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(getInputStream(),
                    charset));
        }
        StringBuffer sb = new StringBuffer();
        String inputLine = null;
        while ((inputLine = reader.readLine()) != null) {
            sb.append(inputLine);
        }
        return sb.toString();
    }

    public void post(List<String> dataList) throws IOException {
        PrintWriter out = new PrintWriter(httpConn.getOutputStream());
        StringBuffer postData = new StringBuffer();
        for (String data : dataList) {
            postData.append(data).append("&");
        }
        out.write(postData.toString());
        out.flush();
        out.close();
    }

    public String getCookie() {
        String key = null;
        String cookie = "";
        if (httpConn == null)
            return "";
        for (int i = 1; (key = httpConn.getHeaderFieldKey(i)) != null; i++) {
            if ("set-cookie".equalsIgnoreCase(key)) {
                String cookieVal = httpConn.getHeaderField(i);
                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
                cookie = cookie + cookieVal + ";";
                //    cookie = cookie + cookieVal ;
            }
        }
        if (iCookie != null && cookie != null && cookie.trim().length() > 0) {
            String[] res = cookie.split("=");
            if (res.length > 1 && res[1].length() > 0)
                iCookie.saveCookie(cookie);
        }
        //   System.out.println(cookie);
        return cookie;
    }

    public void disconnect() {
        if (connected) {
            httpConn.disconnect();
            connected = false;
        }
    }

    public String getAccInfo() throws IOException {
        String str = "";
        connect(METHOD_GET);
        str = getBodyString();
        return str;
    }

}
