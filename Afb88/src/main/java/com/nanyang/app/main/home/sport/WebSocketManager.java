package com.nanyang.app.main.home.sport;

import android.support.v7.util.SortedList;
import android.util.Log;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;
import com.nanyang.app.common.MainPresenter;

import org.json.JSONException;

/**
 * Created by ASUS on 2019/7/1.
 */

public class WebSocketManager {

    private static WebSocketManager instance;

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
    public void createWebSocket(final MainPresenter.CallBack<Void> back, SortedList.Callback<String> stringCallback){
        AsyncHttpClient.getDefaultInstance().websocket("ws://ws.afb1188.com:8888/fnOddsGen", null, new AsyncHttpClient.WebSocketConnectCallback() {

            @Override
            public void onCompleted(Exception ex, final WebSocket webSocket) {
                Log.d("Socket", "onCompleted-----------" + webSocket.getSocket().toString());
                if (ex != null) {
                    Log.e("Socket", "Exception----------------" + ex.getLocalizedMessage());
                    ex.printStackTrace();
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
                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(final String s) {
                        Log.d("Socket", "onStringAvailable-----------" + s);



                    }
                });

                try {
                    back.onBack(null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
