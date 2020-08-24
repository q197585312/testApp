package gaming178.com.casinogame.Util;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceView;

import cn.nodemedia.LivePlayer;
import cn.nodemedia.LivePlayerDelegate;

/**
 * Created by Administrator on 2016/5/18.
 */
public class VideoHelper {
    Context mContext;

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    String playUrl = "rtmp://gnddragon.fc.llnwd.net/gnddragone/myvideo1";

    public void setBufferTime(int bufferTime) {
        this.bufferTime = bufferTime;
    }

    int bufferTime = 300;

    public void setMaxBufferTime(int maxBufferTime) {
        this.maxBufferTime = maxBufferTime;
    }

    int maxBufferTime = 2100;
    SurfaceView sv;

    public VideoHelper(Context mContext, SurfaceView sv) {
        this.mContext = mContext;
        this.sv = sv;
        LivePlayer.init(mContext);

        LivePlayer.setDelegate(new LivePlayerDelegate() {
                                   @Override
                                   public void onEventCallback(int event, String msg) {
                                       Message message = new Message();
                                       Bundle b = new Bundle();
                                       b.putString("msg", msg);
                                       message.setData(b);
                                       message.what = event;
                                       handler.sendMessage(message);
                                   }
                               }
        );
        LivePlayer.setUIVIew(sv);
        /**
         * 设置缓冲区时长，与flash编程时一样，可以设置2个值
         * 第一个bufferTime为从连接成功到开始播放的启动缓冲区长度，越小启动速度越快，最小100毫秒
         * 注意：声音因为没有关键帧，所以这个缓冲区足够马上就可以听到声音，但视频需要等待关键帧后才会开始显示画面。
         * 如果你的服务器支持GOP_cache可以开启来加快画面的出现
         */
        LivePlayer.setBufferTime(bufferTime);

        /**
         * maxBufferTime为最大缓冲区，当遇到网络抖动，较大的maxBufferTime更加平滑，但延迟也会跟着增加。
         * 这个值关乎延迟的大小。
         */
        LivePlayer.setMaxBufferTime(maxBufferTime);

        /**
         * 设置是否接收音视频流  协议参考 rtmp_specification_1.0.pdf 7.2.2.4. & 7.2.2.5.
         * 默认值都为true 如不需要该功能可以不设置该值
         * 注意：目前测试了fms和red5支持该参数设定有效，欢迎测试补充。目前版本只在开始播放前设置有效，中途无法变更。
         */
//		LivePlayer.receiveAudio(true);
//		LivePlayer.receiveVideo(false);

        /**
         * 开始播放
         */

    }

    public void playVideo() {
        LivePlayer.startPlay(playUrl);

    }

    public void stopVideo() {
        LivePlayer.stopPlay();
    }

    private Integer srcWidth;
    private Integer srcHeight;
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
