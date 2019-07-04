package com.nanyang.app.main.home.sport;

import android.util.Log;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;
import com.nanyang.app.common.MainPresenter;

import org.json.JSONException;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ASUS on 2019/7/1.
 */

public class WebSocketManager {

    private static WebSocketManager instance;
    private WebSocket webSocket;
    private MainPresenter.CallBack<String> onPingCloseCallBack;
    private boolean isRunning = false;

    private WebSocketManager() {

    }

    /**
     * get the AppManager instance, the AppManager is singleton.
     */
    public static WebSocketManager getInstance() {
        if (instance == null) {
            instance = new WebSocketManager();
        }
        return instance;
    }

    Timer timerRetry = new Timer();

    public void createWebSocket(final MainPresenter.CallBack<String> back, final WebSocket.StringCallback stringCallback, final MainPresenter.CallBack<String> onPingCloseCallBack) {

        AsyncHttpClient.getDefaultInstance().websocket("ws://ws.afb1188.com:8888/fnOddsGen", null, new AsyncHttpClient.WebSocketConnectCallback() {

            @Override
            public void onCompleted(Exception ex, final WebSocket webSocket) {
                if (ex != null) {
                    Log.e("Socket", "Exception----------------" + ex.getLocalizedMessage());
                    ex.printStackTrace();
                    timerRetry.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            createWebSocket(back, stringCallback, onPingCloseCallBack);
                        }
                    }, 2000);
                    return;
                }
                webSocket.setPingCallback(new WebSocket.PingCallback() {
                    @Override
                    public void onPingReceived(String s) {
                        Log.d("Socket", "onPongCallback" + s);
                    }
                });
                webSocket.setPongCallback(new WebSocket.PongCallback() {
                    @Override
                    public void onPongReceived(String s) {
                        Log.d("Socket", "onPongReceived" + s);
                    }
                });
                webSocket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "onClosedCallback出错");
                            return;
                        }
                        Log.d("Socket", "onClosedCallback");
                    }
                });
                webSocket.setEndCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "setEndCallback出错");
                            return;
                        }
                        Log.d("Socket", "setEndCallback");
                    }
                });
                webSocket.setWriteableCallback(new WritableCallback() {
                    @Override
                    public void onWriteable() {
                        Log.d("Socket", "WritableCallback");

                    }
                });
                webSocket.setStringCallback(stringCallback);
                WebSocketManager.this.webSocket = webSocket;
                try {
                    back.onBack(null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        this.onPingCloseCallBack = onPingCloseCallBack;
    }


    public void stopUpdateData() {
        if (timer != null) {
            timer.cancel();
            timer = null;
            task = null;
        }
        if (webSocket != null) {
            webSocket.getServer().stop();
            webSocket.close();
            webSocket = null;
        }
        isRunning = false;
    }

    private Timer timer;
    private TimerTask task;

    public void startUpdateData() {
        if (timer != null && task != null && isRunning)
            return;
        if (timer == null) {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    String cmd = "1";
                    send(cmd);
                    if (webSocket == null || !webSocket.isOpen()) {
                        try {
                            onPingCloseCallBack.onBack("");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }
        timer.schedule(task, 0, 30000);
        isRunning = true;
    }

    public void send(String cmd) {
        if (webSocket != null && webSocket.isOpen()) {
            webSocket.send(cmd);
            Log.d("Socket", "发送了：" + cmd);
        } else {
            Log.d("Socket", "发送失败了：" + cmd);
        }
    }

    public void test() {
        String s = "01[{\"token\":\"oxwwx0lruea4w0hezjmtymsr\",\"um\":\"\",\"delay\":\"0\",\"pn\":\"1\",\"tf\":-1,\"betable\":false,\"lang\":\"en\",\"LangCol\":\"C\",\"accType\":\"HK\",\"CTOddsDiff\":\"0\",\"CTSpreadDiff\":\"0\",\"oddsDiff\":\"0\",\"spreadDiff\":\"0\",\"ACT\":\"LOS\",\"DBID\":\"1_1_2\",\"ot\":\"t\",\"timess\":null,\"ov\":0,\"mt\":0,\"FAV\":\"\",\"SL\":\"\",\"fh\":false,\"isToday\":false}]";
        send(s);
    }
}
