package com.unkonw.testapp.libs.androidAsync;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.util.Log;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.AsyncServerSocket;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.ListenCallback;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.callback.HttpConnectCallback;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.koushikdutta.async.util.Charsets;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 异步网络框架工具类
 * Created by xubaolun on 2018/7/26.
 */

public class AsyncUtils {
    private static final String TAG = "AsyncUtils";

    private static final int PORT = 15234;

    public static String path = Environment.getExternalStorageDirectory().toString();

    private static final String FILE_FLAG = "/fileTrans";

    private AsyncUtils() {
    }

    static AsyncHttpServer asyncHttpServer = new AsyncHttpServer();
    static AsyncServer asyncServer = new AsyncServer();


    public static void startServer() {
        asyncServer.listen(null, PORT, new ListenCallback() {
            @Override
            public void onAccepted(final AsyncSocket socket) {
                Log.i(TAG, "------------startServer--------onAccepted--------------------");
                socket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.i(TAG, "------------startServer--------onAccepted--------------------" + bb.readString(Charsets.UTF_8));
                        final String order = "响应测试信息";

                        Util.writeAll(socket, order.getBytes(), new CompletedCallback() {
                            @Override
                            public void onCompleted(Exception ex) {
                                if (ex != null) {
                                    Log.d("Socket", "writeAll出错");
                                    return;
                                }
                                Log.d("Socket", "发送了：" + order);
                            }
                        });
                    }
                });
            }

            @Override
            public void onListening(AsyncServerSocket socket) {
                Log.i(TAG, "------------startServer--------onListening--------------------");
            }

            @Override
            public void onCompleted(Exception ex) {
                Log.i(TAG, "------------startServer--------onCompleted--------------------");
            }
        });

    }

    public static void startClient() {

        AsyncServer.getDefault().connectSocket("10.0.0.9", PORT, new com.koushikdutta.async.callback.ConnectCallback() {
            @Override
            public void onConnectCompleted(Exception ex, AsyncSocket socket) {
                Log.i(TAG, "------------startClient--------onConnectCompleted--------------------");
                if (ex != null) {
                    Log.d("Socket", "连接出错");
                    ex.printStackTrace();
                    return;
                }


                socket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.d("Socket", "接收到：" + new String(bb.getAllByteArray()));
                    }
                });

                socket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "setClosedCallback出错");
                            return;
                        }
                        Log.d("Socket", "setClosedCallback");
                    }
                });

                socket.setEndCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "setEndCallback出错");
                            return;
                        }
                        Log.d("Socket", "setEndCallback");
                    }
                });

                socket.setWriteableCallback(new WritableCallback() {
                    @Override
                    public void onWriteable() {
                        Log.d("Socket", "onWriteable");
                    }
                });

                final String order = "测试信息";

                Util.writeAll(socket, order.getBytes(), new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "writeAll出错");
                            return;
                        }
                        Log.d("Socket", "发送了：" + order);
                    }
                });

            }
        });

    }


    public static void startHttpServerWebSocket() {
        asyncHttpServer.listen(PORT);

        asyncHttpServer.setErrorCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                Log.i(TAG, "----------------startHttpServerWebSocket------setErrorCallback---onCompleted-");
                ex.printStackTrace();
            }
        });

        asyncHttpServer.websocket("/", new AsyncHttpServer.WebSocketRequestCallback() {
            @Override
            public void onConnected(final WebSocket webSocket, AsyncHttpServerRequest request) {
                Log.i(TAG, "----------------startHttpServerWebSocket------websocket---onConnected-");
                webSocket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.i(TAG, "------------startHttpServerWebSocket--------onDataAvailable--------------------" + bb.readString(Charsets.UTF_8));
                        final String order = "响应测试信息";

                        Util.writeAll(webSocket, order.getBytes(), new CompletedCallback() {
                            @Override
                            public void onCompleted(Exception ex) {
                                if (ex != null) {
                                    Log.d("Socket", "writeAll出错");
                                    return;
                                }
                                Log.d("Socket", "发送了：" + order);
                            }
                        });
                    }
                });
            }
        });

    }

    public static void startFileServerWebSocket() {
        asyncHttpServer.listen(PORT);

        asyncHttpServer.setErrorCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                Log.i(TAG, "----------------startHttpServerWebSocket------setErrorCallback---onCompleted-");
                ex.printStackTrace();
            }
        });
        asyncHttpServer.websocket(FILE_FLAG, new AsyncHttpServer.WebSocketRequestCallback() {
            @Override
            public void onConnected(final WebSocket webSocket, AsyncHttpServerRequest request) {
                Log.i(TAG, "----------------startHttpServerWebSocket------websocket---onConnected-");
                webSocket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.i(TAG, "------------startHttpServerWebSocket--------onDataAvailable--------------------" + bb.readString(Charsets.UTF_8));
                        final String order = "响应测试信息";
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + Environment.getExternalStorageDirectory().getAbsolutePath();
                        Log.i("path", "----------------path---" + path);
                        final String fileName = "download.jpg";
                        File file = new File(path, fileName);
                        Util.writeAll(webSocket, getBytes(file.getAbsolutePath()), new CompletedCallback() {
                            @Override
                            public void onCompleted(Exception ex) {
                                if (ex != null) {
                                    Log.d("Socket", "writeAll出错");
                                    return;
                                }
                                Log.d("Socket", "发送文件");
                            }
                        });
                    }
                });
            }
        });
    }


    private static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void startHttpClientWebSocket() {
        AsyncHttpClient.getDefaultInstance().websocket("ws://10.0.0.9:" + PORT, null, new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {

                Log.i(TAG, "----------------startHttpClientWebSocket------websocket---onCompleted-" + webSocket);
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                webSocket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.d("Socket", "接收到：" + new String(bb.getAllByteArray()));
                    }
                });

                webSocket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "setClosedCallback出错");
                            return;
                        }
                        Log.d("Socket", "setClosedCallback");
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
                        Log.d("Socket", "onWriteable");
                    }
                });

                final String order = "测试信息";

                Util.writeAll(webSocket, order.getBytes(), new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "writeAll出错");
                            return;
                        }
                        Log.d("Socket", "发送了：" + order);
                    }
                });
            }
        });

    }

    public static void startFileClientWebSocket(final OnFileListener fileListener) {
        AsyncHttpClient.getDefaultInstance().websocket("ws://10.0.0.9:" + PORT + FILE_FLAG, null, new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                Log.i(TAG, "----------------startHttpClientWebSocket------websocket---onCompleted-" + webSocket);
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                webSocket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.d("Socket", "接收到文件");

                        getFile(bb.getAllByteArray(), path, "hello.jpg");
                        Log.d("Socket", "接收到文件完毕");
                        fileListener.onSuccess(path + "/" + "hello.jpg");
                    }
                });

                webSocket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "setClosedCallback出错");
                            return;
                        }
                        Log.d("Socket", "setClosedCallback");
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
                        Log.d("Socket", "onWriteable");
                    }
                });

                final String order = "测试信息";

                Util.writeAll(webSocket, order.getBytes(), new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "writeAll出错");
                            return;
                        }
                        Log.d("Socket", "发送了：" + order);
                    }
                });
            }
        });


    }

    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "/" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void startHttpServer() {

        asyncHttpServer.listen(PORT);

        asyncHttpServer.setErrorCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                Log.i(TAG, "----------------startHttpServer------setErrorCallback---onCompleted-");
                ex.printStackTrace();
            }
        });

        asyncHttpServer.get("/", new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                Log.i(TAG, "----------------startHttpServer------get---onRequest-----");
            }
        });

        asyncHttpServer.post("/", new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                Log.i(TAG, "----------------startHttpServer------post---onRequest-");
            }
        });

        asyncHttpServer.get("/wf_files/.*", new HttpServerRequestCallback() {
            @Override
            public void onRequest(final AsyncHttpServerRequest request, final AsyncHttpServerResponse response) {
            }
        });

    }


    public static void startHttpClient() {
        AsyncHttpClient.getDefaultInstance().execute("http://10.0.0.9:" + PORT, new HttpConnectCallback() {
            @Override
            public void onConnectCompleted(Exception ex, AsyncHttpResponse response) {

            }
        });
    }


    public static void getFile(String uri, String fileName, AsyncHttpClient.FileCallback fileCallback) {
        AsyncHttpClient.getDefaultInstance().executeFile(new AsyncHttpGet(uri), fileName, fileCallback);
    }

    public static void getString(String uri) {
        AsyncHttpClient.getDefaultInstance().executeString(new AsyncHttpGet(uri), new AsyncHttpClient.StringCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, String result) {

            }
        });
    }


    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }


    public interface OnFileListener {
        void onSuccess(String file);
    }


}