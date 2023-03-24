/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gaming178.com.casinogame.Util;

import org.apache.http.cookie.Cookie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClient {

    //   private String IP = "http://192.168.100.22";
    //   private String IP = "http://10.10.30.82/jisuan/";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.3) Gecko/20100401 Firefox/3.6.3";
    public Cookie web_Cookie = null;
    HttpURLConnection httpConn = null;
    private String url;
    private String cookie;
    private String sessionId;
    private boolean connected = false;
    private Map<String, String> requestHeader = new HashMap<String, String>();

    public HttpClient(String url) {
        this(url, null);
    }

    public HttpClient() {
       /* try {
            InetAddress localhost = InetAddress.getLocalHost();
            if ("10.10.10.16".equals(localhost.getHostAddress())) {
                IP = IP + ":8084";
            } else {
                IP = IP + ":80";
            }
        } catch (UnknownHostException e) {
            System.err.println("Localhost not seeable. Something is odd. ");
        }*/
    }

    public HttpClient(String url, String cookie) {
        this.url = url;
        this.cookie = cookie;
    }

    public static void main(String[] args) throws Exception {
        HttpClient httpClient = new HttpClient(WebSiteUrl.INDEX, "");
        System.out.println(httpClient.getCookie());

    }

    public String getHttpClient(String url, String cookie) {
        String sReturn = "";
        this.url = url;
        this.cookie = cookie;
        connect("GET");
        try {
            sReturn = getBodyString();
        } catch (Exception e) {//Exception  MalformedURLException
            e.printStackTrace();
        }

        disconnect();
        return sReturn;
    }

    public String sendPost(String urlString, String sParams) {
        String sReturn = "";
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader in = null;
        try {
            url = new URL(urlString);

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(130000);
            conn.setReadTimeout(130000);
//            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            if (!urlString.contains("112api.gd88bet.net")) {
                conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Cookie", cookie);
//            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.connect();
            conn.getOutputStream().write(sParams.getBytes());
            conn.getOutputStream().flush();
            conn.getOutputStream().close();
            int code = conn.getResponseCode();
            if (code != 200)
                System.out.print(code);
            in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            sReturn = sb.toString();
            sb = null;
        } catch (Exception e) {
            e.printStackTrace();
            sReturn = "netError";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    sReturn = "netError";
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    sReturn = "netError";
                    e.printStackTrace();
                }
            }
            url = null;
            conn = null;
            in = null;
        }
        LogIntervalUtils.logTime("url:" + urlString + ",sParams:" + sParams + ",Response:" + sReturn);
        return sReturn;
    }

    public String sendPostCQ(String urlString, String sParams) {
        String sReturn = "";
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader in = null;
        try {
            url = new URL(urlString);

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
//            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
            conn.setUseCaches(false);
            //     HttpURLConnection.setFollowRedirects(false);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Cookie", cookie);
//            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.connect();
            conn.getOutputStream().write(sParams.getBytes());
            conn.getOutputStream().flush();
            conn.getOutputStream().close();
            int code = conn.getResponseCode();
            if (code != 200)
                System.out.print(code);
            in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            sReturn = sb.toString();
            sb = null;
        } catch (Exception e) {
            e.printStackTrace();
            sReturn = "netError";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    sReturn = "netError";
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    sReturn = "netError";
                    e.printStackTrace();
                }
            }
            url = null;
            conn = null;
            in = null;
        }
        return sReturn;
    }

    public String encode(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
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

    public boolean connect(String method) {
        // System.out.println(method);
        boolean bReturn = true;
        try {
            URLConnection con = new URL(url).openConnection();
            if (con instanceof HttpURLConnection) {
                httpConn = (HttpURLConnection) con;
            }

            httpConn.setConnectTimeout(30000);
            httpConn.setReadTimeout(30000);

            if ("POST".equals(method)) {
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);
                httpConn.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");
            }

            httpConn.setInstanceFollowRedirects(false);
            httpConn.setRequestMethod(method);
            if (cookie != null) {
                //System.out.println(cookie);
                httpConn.addRequestProperty("Cookie", cookie);
            }
            httpConn.setRequestProperty("User-Agent", USER_AGENT);

            httpConn.setUseCaches(false);

            if (!this.requestHeader.isEmpty()) {
                for (String key : this.requestHeader.keySet()) {
                    httpConn.setRequestProperty(key, this.requestHeader.get(key));
                }
            }
            httpConn.connect();
            cookie = getCookie();
            sessionId = cookie;
            connected = true;
        } catch (Exception e) {//Exception  MalformedURLException
            bReturn = false;
            e.printStackTrace();
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
        for (int i = 1; (key = httpConn.getHeaderFieldKey(i)) != null; i++) {
            if ("set-cookie".equalsIgnoreCase(key)) {
                String cookieVal = httpConn.getHeaderField(i);
                cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
                cookie = cookie + cookieVal + ";";
                //    cookie = cookie + cookieVal ;
            }
        }
        //   System.out.println(cookie);
        LogIntervalUtils.logTime("cookie:" + cookie);
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

    public String buildRequestData(Map<String, String> patameterMap, String namespace, String methodName) {
        StringBuffer soapRequestData = new StringBuffer();
        soapRequestData.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        soapRequestData
                .append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                        + " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"");
        if (!namespace.equals(""))
            soapRequestData.append(" xmlns:ns1=\"" + namespace + "\"");

        soapRequestData
                .append(" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");

        soapRequestData.append("<soap:Body>");
        soapRequestData.append("<ns1:" + methodName + ">");


        Set<String> nameSet = patameterMap.keySet();
        for (String name : nameSet) {
            soapRequestData.append("<" + name + ">" + patameterMap.get(name)
                    + "</" + name + ">");
        }


        soapRequestData.append("</ns1:" + methodName + ">");
        soapRequestData.append("</soap:Body>");
        soapRequestData.append("</soap:Envelope>");

        return soapRequestData.toString();
    }

    public String sendPostSoap(String urlString, String sParams) {
        String sReturn = "";
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader in = null;
        try {
            url = new URL(urlString);

            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
//            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
            conn.setUseCaches(false);
            //     HttpURLConnection.setFollowRedirects(false);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestProperty("Content-Type",
                    "text/xml; charset=utf-8");
            conn.connect();

            conn.getOutputStream().write(sParams.getBytes());

            conn.getOutputStream().flush();

            conn.getOutputStream().close();
            int code = 0;
            code = conn.getResponseCode();    //用来获取服务器响应状态
            if (code == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream()));


                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                sReturn = sb.toString();
                sb = null;
            } else {
                sReturn = "netError";
            }


        } catch (Exception e) {
            //	Log.i(WebSiteUrl.Tag,"111111");
            e.printStackTrace();
            sReturn = "netError";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    sReturn = "netError";
                    e.printStackTrace();
                    //	Log.i(WebSiteUrl.Tag,"22222");
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    sReturn = "netError";
                    e.printStackTrace();
                    //Log.i(WebSiteUrl.Tag,"33333");
                }
            }
            url = null;
            conn = null;
            in = null;
        }
        return sReturn;
    }
}
