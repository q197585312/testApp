package gaming178.com.casinogame.Util;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;



public class ReceiveMessage extends Service {

    private SocketChannel client = null;

    private InetSocketAddress isa = null;

    private String message = "";
    private String ChatHost = "10.132.224.76";
    private int ChatPort = 7275;

    public void onCreate() {
        super.onCreate();
        ConnectToServer();
        StartServerListener();
    }

    public void onDestroy() {
        super.onDestroy();
        DisConnectToServer();
    }


    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }


    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public class LocalBinder extends Binder {


        ReceiveMessage getService() {
            return ReceiveMessage.this;
        }


    }


    private final IBinder mBinder = new LocalBinder();
    //用于链接服务器端


    public void ConnectToServer()


    {
        try {
            client = SocketChannel.open();
            isa = new InetSocketAddress(ChatHost, ChatPort);
            client.connect(isa);
            client.configureBlocking(false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //断开与服务器端的链接


    public void DisConnectToServer()
    {
        try {
            client.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //启动服务器端的监听线程，从Server端接收消息

    public void StartServerListener()
    {
        ServerListenerThread a = new ServerListenerThread();
        a.start();
    }

    //向Server端发送消息


    public void SendMessageToServer(String msg)


    {
        try {
            ByteBuffer bytebuf = ByteBuffer.allocate(1024);
            bytebuf = ByteBuffer.wrap(msg.getBytes("UTF-8"));
            client.write(bytebuf);
            bytebuf.flip();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void shownotification(String tab)


    {

        NotificationManager barmanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification msg = new Notification(android.R.drawable.stat_notify_chat, "A Message Coming!", System.currentTimeMillis());

//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, ChatRoomActivity.class), PendingIntent.FLAG_ONE_SHOT);
//        msg.setLatestEventInfo(this, "Message", "Message:" + tab, contentIntent);
        barmanager.notify(0, msg);
    }


    private class ServerListenerThread extends Thread
    {
        public void run() {
            try {
                //无线循环，监听服务器,如果有不为空的信息送达，则更新Activity的UI
                while (true) {
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    client.read(buf);
                    buf.flip();
                    Charset charset = Charset.forName("UTF-8");
                    CharsetDecoder decoder = charset.newDecoder();
                    CharBuffer charBuffer;
                    charBuffer = decoder.decode(buf);
                    String result = charBuffer.toString();
                    if (result.length() > 0)
                        shownotification(result);
                }
            } catch (CharacterCodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}