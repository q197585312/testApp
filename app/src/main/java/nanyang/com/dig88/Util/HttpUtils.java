package nanyang.com.dig88.Util;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/12/29.
 */

public class HttpUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static HttpClient httpClient = new HttpClient("");
    public static void httpPostDelayed(final long delayedTime, final String url, final String params, final RequestCallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(delayedTime);
                    final String result = httpClient.sendPost(url, params);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callBack != null) {
                                callBack.onRequestSucceed(result);
                            }
                        }
                    });
                } catch (final IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callBack != null) {
                                callBack.onRequestFailed(e.toString());
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void httpGetDelayed(final long delayedTime,final String url, final RequestCallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(delayedTime);
                    final String result = httpClient.sendPost(url, "");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (callBack != null) {
                                        callBack.onRequestSucceed(result);
                                    }
                                }
                            });
                        }
                    });
                } catch (final IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (callBack != null) {
                                        callBack.onRequestFailed(e.toString());
                                    }
                                }
                            });
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public static void httpPost(final String url, final String params, final RequestCallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                try {
                    final String result = httpClient.sendPost(url, params);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callBack != null) {
                                callBack.onRequestSucceed(result);
                            }
                        }
                    });
                } catch (final IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callBack != null) {
                                callBack.onRequestFailed(e.toString());
                            }
                        }
                    });
                }
            }
        }.start();
    }

    public static void httpGet(final String url, final RequestCallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                try {
                    final String result = httpClient.sendPost(url, "");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (callBack != null) {
                                        callBack.onRequestSucceed(result);
                                    }
                                }
                            });
                        }
                    });
                } catch (final IOException e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (callBack != null) {
                                        callBack.onRequestFailed(e.toString());
                                    }
                                }
                            });
                        }
                    });
                }
            }
        }.start();
    }

    public static String paramByUrlStr(LinkedHashMap<String, String> param){
        StringBuffer strParam = new StringBuffer();
        for (String str : param.keySet()){
            strParam.append(str).append("=").append(param.get(str)).append("&");
        }
        return strParam.substring(0,strParam.length()-1);
    }

    public interface RequestCallBack {
        void onRequestSucceed(String s);

        void onRequestFailed(String s);
    }
}
