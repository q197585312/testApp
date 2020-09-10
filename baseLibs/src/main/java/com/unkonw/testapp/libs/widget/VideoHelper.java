package com.unkonw.testapp.libs.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.unkonw.testapp.libs.utils.LogUtil;

import cn.nodemedia.NodePlayer;
import cn.nodemedia.NodePlayerDelegate;
import cn.nodemedia.NodePlayerView;

/**
 * Created by Administrator on 2016/5/18.
 */
public class VideoHelper {
    private final NodePlayer nodePlayer;
    Context mContext;
    private boolean isStarting;

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
        /**
         * 设置播放直播视频url
         */
        nodePlayer.setInputUrl(playUrl);
    }

    String playUrl = "rtmp://gnddragon.fc.llnwd.net/gnddragone/myvideo1";


    int bufferTime = 300;
    int maxBufferTime = 2100;
    int videoScaleMode =1;
    boolean autoHA=true;
    String rtspTransport="udp";
    public void setMaxBufferTime(int maxBufferTime) {
        this.maxBufferTime = maxBufferTime;
    }
    public void setBufferTime(int bufferTime) {
        this.bufferTime = bufferTime;
    }

    NodePlayerView playSurface;

    public NodePlayer getNodePlayer() {
        return nodePlayer;
    }

    public VideoHelper(Context mContext, NodePlayerView playSurface) {
        this.mContext = mContext;
        this.playSurface = playSurface;

        //设置播放视图的渲染器模式,可以使用SurfaceView或TextureView. 默认SurfaceView
        playSurface.setRenderType(NodePlayerView.RenderType.SURFACEVIEW);
        playSurface.setUIViewContentMode(NodePlayerView.UIViewContentMode.ScaleAspectFit);
        nodePlayer = new NodePlayer(mContext,"M2FmZTEzMGUwMC00ZTRkNTMyMS1jbi5ub2RlbWVkaWEucWxpdmU=-OTv6MJuhXZKNyWWMkdKJWsVKmLHwWPcPfnRbbWGIIf+8t39TqL/mW2f5O5WdT/W8JJE7ePvkvKaS371xVckAZ/U00dSwPp8ShB8Yic2W1GhwCyq04DYETsrGnkOWrhARH7nzNhd3Eq6sVC1Fr74GCEUHbDSCZnCfhcEnzGU9InRiQJ2PImtHORahN3blAGlHb6LZmdnobw5odvKEeUhbkhxYf8S1Fv4VRnSpDCSS3LZ2U3Mp6MfGDA1ZXPadmgdwaJitIrnWA2zP/yqmlUHjMtTv8PzGcc73Tm5k5q+OMbKCJsPq8KSEpFthncvaGZJ2kS2GHx6V5TqYZglBrTx61g==");
        nodePlayer.setNodePlayerDelegate(new NodePlayerDelegate() {
            @Override
            public void onEventCallback(NodePlayer player, int event, String msg) {
                LogUtil.e("onEventCallback:" + event + " msg:" + msg);
                handler.sendEmptyMessage(event);
            }
        });
        nodePlayer.setPlayerView(playSurface);
        /**
         * 设置启动缓冲区时长,单位毫秒.此参数关系视频流连接成功开始获取数据后缓冲区存在多少毫秒后开始播放
         */
        nodePlayer.setBufferTime(bufferTime);

        /**
         * 设置最大缓冲区时长,单位毫秒.此参数关系视频最大缓冲时长.
         * RTMP基于TCP协议不丢包,网络抖动且缓冲区播完,之后仍然会接受到抖动期的过期数据包.
         * 设置改参数,sdk内部会自动清理超出部分的数据包以保证不会存在累计延迟,始终与直播时间线保持最大maxBufferTime的延迟
         */
        nodePlayer.setMaxBufferTime(maxBufferTime);

        /**
         *
         开启硬件解码,支持4.3以上系统,初始化失败自动切为软件解码,默认开启.
         */
        nodePlayer.setHWEnable(autoHA);

        /**
         * 设置连接超时时长,单位毫秒.默认为0 一直等待.
         * 连接部分RTMP服务器,握手并连接成功后,当播放一个不存在的流地址时,会一直等待下去.
         * 如需超时,设置该值.超时后返回1006状态码.
         */
        // np.setConnectWaitTimeout(10*1000);

        /**
         * @brief rtmpdump 风格的connect参数
         * Append arbitrary AMF data to the Connect message. The type must be B for Boolean, N for number, S for string, O for object, or Z for null.
         * For Booleans the data must be either 0 or 1 for FALSE or TRUE, respectively. Likewise for Objects the data must be 0 or 1 to end or begin an object, respectively.
         * Data items in subobjects may be named, by prefixing the type with 'N' and specifying the name before the value, e.g. NB:myFlag:1.
         * This option may be used multiple times to construct arbitrary AMF sequences. E.g.
         */
        // np.setConnArgs("S:info O:1 NS:uid:10012 NB:vip:1 NN:num:209.12 O:0");

        /**
         * 设置RTSP使用TCP传输模式
         * 支持的模式有:
         * NodePlayer.RTSP_TRANSPORT_UDP
         * NodePlayer.RTSP_TRANSPORT_TCP
         * NodePlayer.RTSP_TRANSPORT_UDP_MULTICAST
         * NodePlayer.RTSP_TRANSPORT_HTTP
         */
        nodePlayer.setRtspTransport(rtspTransport);

        /**
         * 设置视频解密秘钥，16字节，空字符串则不进行解密
         */
        nodePlayer.setCryptoKey("");

        /**
         * 在本地开起一个RTMP服务,并进行监听播放,局域网内其他手机或串流器能推流到手机上直接进行播放,无需中心服务器支持
         * 播放的ip可以是本机IP,也可以是0.0.0.0,但不能用127.0.0.1
         * app/stream 可加可不加,只要双方匹配就行
         */
        // np.setLocalRTMP(true);

    }


    public void playVideo() {
        nodePlayer.start();
    }

    public void stopVideo() {
        nodePlayer.stop();
    }

    public void onDestroy() {
        nodePlayer.stop();

        /**
         * 释放资源
         */
        nodePlayer.release();
    }



    private Integer srcWidth;
    private Integer srcHeight;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        // 回调处理
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1000:
                    // Toast.makeText(LivePlayerDemoActivity.this, "正在连接视频",
                    // Toast.LENGTH_SHORT).show();
                    break;
                case 1001:
                    // Toast.makeText(LivePlayerDemoActivity.this, "视频连接成功",
                    // Toast.LENGTH_SHORT).show();
                    isStarting = true;
                    break;
                case 1002:
                    // Toast.makeText(LivePlayerDemoActivity.this, "视频连接失败",
                    // Toast.LENGTH_SHORT).show();
                    //流地址不存在，或者本地网络无法和服务端通信，回调这里。5秒后重连， 可停止
                    //LivePlayer.stopPlay();
                    break;
                case 1003:
                    //Toast.makeText(LivePlayerDemoActivity.this, "视频开始重连",
                    //LivePlayer.stopPlay();	//自动重连总开关
                    break;
                case 1004:
                    // Toast.makeText(LivePlayerDemoActivity.this, "视频播放结束",
                    // Toast.LENGTH_SHORT).show();
                    break;
                case 1005:
                    // Toast.makeText(LivePlayerDemoActivity.this, "网络异常,播放中断",
                    // Toast.LENGTH_SHORT).show();
                    //播放中途网络异常，回调这里。1秒后重连，如不需要，可停止
                    //LivePlayer.stopPlay();
                    break;
                case 1100:
//				System.out.println("NetStream.Buffer.Empty");
                    break;
                case 1101:
//				System.out.println("NetStream.Buffer.Buffering");
                    break;
                case 1102:
//				System.out.println("NetStream.Buffer.Full");
                    break;
                case 1103:
//				System.out.println("Stream EOF");
                    //客服端明确收到服务端发送来的 StreamEOF 和 NetStream.Play.UnpublishNotify时回调这里
                    //收到本事件，说明：此流的发布者明确停止了发布，或者网络异常，被服务端明确关闭了流
                    //本sdk仍然会继续在1秒后重连，如不需要，可停止
//				LivePlayer.stopPlay();
                    break;
                case 1104:
                    /**
                     * 得到 解码后得到的视频高宽值,可用于重绘surfaceview的大小比例 格式为:{width}x{height}
                     * 本例使用LinearLayout内嵌SurfaceView
                     * LinearLayout的大小为最大高宽,SurfaceView在内部等比缩放,画面不失真
                     * 等比缩放使用在不确定视频源高宽比例的场景,用上层LinearLayout限定了最大高宽
                     * */
                    String[] info = msg.getData().getString("msg").split("x");
                    srcWidth = Integer.valueOf(info[0]);
                    srcHeight = Integer.valueOf(info[1]);
                    doVideoFix();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 视频画面高宽等比缩放，此SDK——demo 取屏幕高宽做最大高宽缩放
     */
    public void doVideoFix() {
      /*  DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        float maxWidth=  dm.widthPixels;
        float maxHeight = dm.heightPixels;
        float fixWidth;
        float fixHeight;
        if (srcWidth / srcHeight <= maxWidth / maxHeight) {
            fixWidth = srcWidth * maxHeight / srcHeight;
            fixHeight = maxHeight;
        } else {
            fixWidth = maxWidth;
            fixHeight = srcHeight * maxWidth / srcWidth;
        }
        ViewGroup.LayoutParams lp = sv.getLayoutParams();
        lp.width = (int) maxWidth;
        lp.height = (int) maxHeight;

        sv.setLayoutParams(lp);*/
    }
}
