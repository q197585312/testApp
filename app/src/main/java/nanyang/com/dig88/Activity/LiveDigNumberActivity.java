package nanyang.com.dig88.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.DigGameBetBean;
import nanyang.com.dig88.Entity.LiveGameBean;
import nanyang.com.dig88.Entity.VideoPlayControlBean;
import nanyang.com.dig88.Ijkplayer.IRenderView;
import nanyang.com.dig88.Ijkplayer.IjkVideoView;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/2/25.
 */
public class LiveDigNumberActivity extends GameBaseActivity implements View.OnClickListener {
    public static final int SHOW_TIMER = 0;
    public static final int SHOW_BET_SECCESS = 1;
    public static final int SHOW_BET_ERROR = 2;
    public Dialog dialogshow;
    //42balls
    @Bind(R.id.tv_game_name42)
    TextView tv_game_name42;
    @Bind(R.id.tv_time42)
    TextView tv_time42;
    @Bind(R.id.btn_comin42)
    Button btn_comin42;
    @Bind(R.id.refresh_list42)
    ImageView refresh_list42;
    @Bind(R.id.tv_period42)
    TextView tv_period42;
    @Bind(R.id.dianji42)
    ImageView dianji42;
    @Bind(R.id.rl_red42)
    LinearLayout rl_red42;
    @Bind(R.id.tv_red_odds42)
    TextView tv_red_odds42;
    @Bind(R.id.rl_yellow42)
    LinearLayout rl_yellow42;
    @Bind(R.id.tv_yelllow_odds42)
    TextView tv_yelllow_odds42;
    @Bind(R.id.rl_blue42)
    LinearLayout rl_blue42;
    @Bind(R.id.tv_blue_odds42)
    TextView tv_blue_odds42;
    @Bind(R.id.rl_big42)
    LinearLayout rl_big42;
    @Bind(R.id.tv_big_odds42)
    TextView tv_big_odds42;
    @Bind(R.id.rl_small42)
    LinearLayout rl_small42;
    @Bind(R.id.tv_small_odds42)
    TextView tv_small_odds42;
    @Bind(R.id.rl_double42)
    LinearLayout rl_double42;
    @Bind(R.id.tv_double_odds42)
    TextView tv_double_odds42;
    @Bind(R.id.rl_single42)
    LinearLayout rl_single42;
    @Bind(R.id.tv_single_odds42)
    TextView tv_single_odds42;
    @Bind(R.id.rl_small_double42)
    LinearLayout rl_small_double42;
    @Bind(R.id.tv_small_double_odds42)
    TextView tv_small_double_odds42;
    @Bind(R.id.rl_big_double42)
    LinearLayout rl_big_double42;
    @Bind(R.id.tv_big_double_odds42)
    TextView tv_big_double_odds42;
    @Bind(R.id.rl_small_single42)
    LinearLayout rl_small_single42;
    @Bind(R.id.tv_small_single_odds42)
    TextView tv_small_single_odds42;
    @Bind(R.id.rl_big_single42)
    LinearLayout rl_big_single42;
    @Bind(R.id.tv_big_single_odds42)
    TextView tv_big_single_odds42;
    @Bind(R.id.tv_toumingdu42)
    TextView tv_toumingdu42;
    @Bind(R.id.rl_dialog42)
    RelativeLayout rl_dialog42;
    @Bind(R.id.num_fragment42)
    FrameLayout num_fragment42;
    //36balls
    @Bind(R.id.tv_game_name36)
    TextView tv_game_name36;
    @Bind(R.id.tv_time36)
    TextView tv_time36;
    @Bind(R.id.btn_comin36)
    Button btn_comin36;
    @Bind(R.id.refresh_list36)
    ImageView refresh_list36;
    @Bind(R.id.tv_period36)
    TextView tv_period36;
    @Bind(R.id.dianji36)
    ImageView dianji36;
    @Bind(R.id.rl_red36)
    LinearLayout rl_red36;
    @Bind(R.id.tv_red_odds36)
    TextView tv_red_odds36;
    @Bind(R.id.rl_yellow36)
    LinearLayout rl_yellow36;
    @Bind(R.id.tv_yelllow_odds36)
    TextView tv_yelllow_odds36;
    @Bind(R.id.rl_blue36)
    LinearLayout rl_blue36;
    @Bind(R.id.tv_blue_odds36)
    TextView tv_blue_odds36;
    @Bind(R.id.rl_big36)
    LinearLayout rl_big36;
    @Bind(R.id.tv_big_odds36)
    TextView tv_big_odds36;
    @Bind(R.id.rl_small36)
    LinearLayout rl_small36;
    @Bind(R.id.tv_small_odds36)
    TextView tv_small_odds36;
    @Bind(R.id.rl_double36)
    LinearLayout rl_double36;
    @Bind(R.id.tv_double_odds36)
    TextView tv_double_odds36;
    @Bind(R.id.rl_single36)
    LinearLayout rl_single36;
    @Bind(R.id.tv_single_odds36)
    TextView tv_single_odds36;
    @Bind(R.id.rl_small_double36)
    LinearLayout rl_small_double36;
    @Bind(R.id.tv_small_double_odds36)
    TextView tv_small_double_odds36;
    @Bind(R.id.rl_big_double36)
    LinearLayout rl_big_double36;
    @Bind(R.id.tv_big_double_odds36)
    TextView tv_big_double_odds36;
    @Bind(R.id.rl_small_single36)
    LinearLayout rl_small_single36;
    @Bind(R.id.tv_small_single_odds36)
    TextView tv_small_single_odds36;
    @Bind(R.id.rl_big_single36)
    LinearLayout rl_big_single36;
    @Bind(R.id.tv_big_single_odds36)
    TextView tv_big_single_odds36;
    @Bind(R.id.tv_toumingdu36)
    TextView tv_toumingdu36;
    @Bind(R.id.rl_dialog36)
    RelativeLayout rl_dialog36;
    @Bind(R.id.num_fragment36)
    FrameLayout num_fragment36;
    //12balls
    @Bind(R.id.tv_game_name12)
    TextView tv_game_name12;
    @Bind(R.id.tv_time12)
    TextView tv_time12;
    @Bind(R.id.btn_comin12)
    Button btn_comin12;
    @Bind(R.id.refresh_list12)
    ImageView refresh_list12;
    @Bind(R.id.tv_period12)
    TextView tv_period12;
    @Bind(R.id.dianji12)
    ImageView dianji12;
    @Bind(R.id.rl_red12)
    LinearLayout rl_red12;
    @Bind(R.id.tv_red_odds12)
    TextView tv_red_odds12;
    @Bind(R.id.rl_yellow12)
    LinearLayout rl_yellow12;
    @Bind(R.id.tv_yelllow_odds12)
    TextView tv_yelllow_odds12;
    @Bind(R.id.rl_blue12)
    LinearLayout rl_blue12;
    @Bind(R.id.tv_blue_odds12)
    TextView tv_blue_odds12;
    @Bind(R.id.rl_big12)
    LinearLayout rl_big12;
    @Bind(R.id.tv_big_odds12)
    TextView tv_big_odds12;
    @Bind(R.id.rl_small12)
    LinearLayout rl_small12;
    @Bind(R.id.tv_small_odds12)
    TextView tv_small_odds12;
    @Bind(R.id.rl_double12)
    LinearLayout rl_double12;
    @Bind(R.id.tv_double_odds12)
    TextView tv_double_odds12;
    @Bind(R.id.rl_single12)
    LinearLayout rl_single12;
    @Bind(R.id.tv_single_odds12)
    TextView tv_single_odds12;
    @Bind(R.id.rl_small_double12)
    LinearLayout rl_small_double12;
    @Bind(R.id.tv_small_double_odds12)
    TextView tv_small_double_odds12;
    @Bind(R.id.rl_big_double12)
    LinearLayout rl_big_double12;
    @Bind(R.id.tv_big_double_odds12)
    TextView tv_big_double_odds12;
    @Bind(R.id.rl_small_single12)
    LinearLayout rl_small_single12;
    @Bind(R.id.tv_small_single_odds12)
    TextView tv_small_single_odds12;
    @Bind(R.id.rl_big_single12)
    LinearLayout rl_big_single12;
    @Bind(R.id.tv_big_single_odds12)
    TextView tv_big_single_odds12;
    @Bind(R.id.tv_toumingdu12)
    TextView tv_toumingdu12;
    @Bind(R.id.rl_dialog12)
    RelativeLayout rl_dialog12;
    @Bind(R.id.num_fragment12)
    FrameLayout num_fragment12;
    //48balls
    @Bind(R.id.tv_game_name48)
    TextView tv_game_name48;
    @Bind(R.id.tv_time48)
    TextView tv_time48;
    @Bind(R.id.btn_comin48)
    Button btn_comin48;
    @Bind(R.id.refresh_list48)
    ImageView refresh_list48;
    @Bind(R.id.tv_period48)
    TextView tv_period48;
    @Bind(R.id.dianji48)
    ImageView dianji48;
    @Bind(R.id.rl_golden48)
    LinearLayout rl_golden48;
    @Bind(R.id.tv_golden_odds48)
    TextView tv_golden_odds48;
    @Bind(R.id.rl_silver48)
    LinearLayout rl_silver48;
    @Bind(R.id.tv_silver_odds48)
    TextView tv_silver_odds48;
    @Bind(R.id.rl_big48)
    LinearLayout rl_big48;
    @Bind(R.id.tv_big_odds48)
    TextView tv_big_odds48;
    @Bind(R.id.rl_small48)
    LinearLayout rl_small48;
    @Bind(R.id.tv_small_odds48)
    TextView tv_small_odds48;
    @Bind(R.id.rl_double48)
    LinearLayout rl_double48;
    @Bind(R.id.tv_double_odds48)
    TextView tv_double_odds48;
    @Bind(R.id.rl_single48)
    LinearLayout rl_single48;
    @Bind(R.id.tv_single_odds48)
    TextView tv_single_odds48;
    @Bind(R.id.rl_small_double48)
    LinearLayout rl_small_double48;
    @Bind(R.id.tv_small_double_odds48)
    TextView tv_small_double_odds48;
    @Bind(R.id.rl_big_double48)
    LinearLayout rl_big_double48;
    @Bind(R.id.tv_big_double_odds48)
    TextView tv_big_double_odds48;
    @Bind(R.id.rl_small_single48)
    LinearLayout rl_small_single48;
    @Bind(R.id.tv_small_single_odds48)
    TextView tv_small_single_odds48;
    @Bind(R.id.rl_big_single48)
    LinearLayout rl_big_single48;
    @Bind(R.id.tv_big_single_odds48)
    TextView tv_big_single_odds48;
    @Bind(R.id.tv_toumingdu48)
    TextView tv_toumingdu48;
    @Bind(R.id.rl_dialog48)
    RelativeLayout rl_dialog48;
    @Bind(R.id.num_fragment48)
    FrameLayout num_fragment48;
    //SICBO
    @Bind(R.id.tv_game_namesicbo)
    TextView tv_game_namesicbo;
    @Bind(R.id.tv_timesicbo)
    TextView tv_timesicbo;
    @Bind(R.id.btn_cominsicbo)
    Button btn_cominsicbo;
    @Bind(R.id.refresh_listsicbo)
    ImageView refresh_listsicbo;
    @Bind(R.id.tv_periodsicbo)
    TextView tv_periodsicbo;
    @Bind(R.id.dianjisicbo)
    ImageView dianjisicbo;
    @Bind(R.id.btn_number_1)
    Button btn_number_1;
    @Bind(R.id.btn_number_2)
    Button btn_number_2;
    @Bind(R.id.btn_number_3)
    Button btn_number_3;
    @Bind(R.id.btn_number_4)
    Button btn_number_4;
    @Bind(R.id.btn_number_5)
    Button btn_number_5;
    @Bind(R.id.btn_number_6)
    Button btn_number_6;
    @Bind(R.id.rl_bigsicbo)
    LinearLayout rl_bigsicbo;
    @Bind(R.id.tv_big_oddssicbo)
    TextView tv_big_oddssicbo;
    @Bind(R.id.rl_smallsicbo)
    LinearLayout rl_smallsicbo;
    @Bind(R.id.tv_small_oddssicbo)
    TextView tv_small_oddssicbo;
    @Bind(R.id.rl_doublesicbo)
    LinearLayout rl_doublesicbo;
    @Bind(R.id.tv_double_oddssicbo)
    TextView tv_double_oddssicbo;
    @Bind(R.id.rl_singlesicbo)
    LinearLayout rl_singlesicbo;
    @Bind(R.id.tv_single_oddssicbo)
    TextView tv_single_oddssicbo;
    @Bind(R.id.tv_toumingdusicbo)
    TextView tv_toumingdusicbo;
    @Bind(R.id.rl_dialogsicbo)
    RelativeLayout rl_dialogsicbo;
    @Bind(R.id.num_fragmentsicbo)
    FrameLayout num_fragmentsicbo;
    //ROULETTE BALLS
    @Bind(R.id.tv_game_nameroulette)
    TextView tv_game_nameroulette;
    @Bind(R.id.tv_timeroulette)
    TextView tv_timeroulette;
    @Bind(R.id.btn_cominroulette)
    Button btn_cominroulette;
    @Bind(R.id.refresh_listroulette)
    ImageView refresh_listroulette;
    @Bind(R.id.tv_periodroulette)
    TextView tv_periodroulette;
    @Bind(R.id.dianjiroulette)
    ImageView dianjiroulette;
    @Bind(R.id.rl_redred)
    LinearLayout rl_redred;
    @Bind(R.id.tv_redred_odds)
    TextView tv_redred_odds;
    @Bind(R.id.rl_black)
    LinearLayout rl_black;
    @Bind(R.id.tv_black_odds)
    TextView tv_black_odds;
    @Bind(R.id.rl_bigroulette)
    LinearLayout rl_bigroulette;
    @Bind(R.id.tv_big_oddsroulette)
    TextView tv_big_oddsroulette;
    @Bind(R.id.rl_smallroulette)
    LinearLayout rl_smallroulette;
    @Bind(R.id.tv_small_oddsroulette)
    TextView tv_small_oddsroulette;
    @Bind(R.id.rl_doubleroulette)
    LinearLayout rl_doubleroulette;
    @Bind(R.id.tv_double_oddsroulette)
    TextView tv_double_oddsroulette;
    @Bind(R.id.rl_singleroulette)
    LinearLayout rl_singleroulette;
    @Bind(R.id.tv_single_oddsroulette)
    TextView tv_single_oddsroulette;
    @Bind(R.id.tv_toumingduroulette)
    TextView tv_toumingduroulette;
    @Bind(R.id.rl_dialogroulette)
    RelativeLayout rl_dialogroulette;
    @Bind(R.id.num_fragmentroulette)
    FrameLayout num_fragmentroulette;
    //MULTIPLE 36BALLS
    @Bind(R.id.tv_game_namemult)
    TextView tv_game_namemult;
    @Bind(R.id.tv_timemult)
    TextView tv_timemult;
    @Bind(R.id.btn_cominmult)
    Button btn_cominmult;
    @Bind(R.id.refresh_listmult)
    ImageView refresh_listmult;
    @Bind(R.id.tv_periodmult)
    TextView tv_periodmult;
    @Bind(R.id.dianjimult)
    ImageView dianjimult;
    /////ceshi
    @Bind(R.id.tv_toumingdu49)
    TextView tv_toumingdu49;
    @Bind(R.id.rl_dialog49)
    RelativeLayout rl_dialog49;
    @Bind(R.id.num_fragment49)
    FrameLayout num_fragment49;
    /////////lanjian tianjia
    @Bind(R.id.ll_item42)
    LinearLayout live_linearlayout42;
    @Bind(R.id.ll_item36)
    LinearLayout live_linearlayout36;
    @Bind(R.id.ll_item12)
    LinearLayout live_linearlayout12;
    @Bind(R.id.ll_item48)
    LinearLayout live_linearlayout48;
    @Bind(R.id.ll_itemsicbo)
    LinearLayout live_linearlayout_sicbo;
    @Bind(R.id.ll_itemroulette)
    LinearLayout live_linearlayout_roulette;
    @Bind(R.id.ll_itemmult)
    LinearLayout live_linearlayout_multiple36;
    boolean bBet = true;
    boolean bGetNumberStatus = true;
    boolean updateAllData = false;
    boolean bTimerStatus = true;
    @Bind(R.id.b_imageView_video_42)
    IjkVideoView b_imageView_video_42;
    @Bind(R.id.b_imageView_video_36)
    IjkVideoView b_imageView_video_36;
    @Bind(R.id.b_imageView_video_12)
    IjkVideoView b_imageView_video_12;
    @Bind(R.id.b_imageView_video_48)
    IjkVideoView b_imageView_video_48;
    @Bind(R.id.b_imageView_video_sicbo)
    IjkVideoView b_imageView_video_sicbo;
    @Bind(R.id.b_imageView_video_roulette)
    IjkVideoView b_imageView_video_roulette;
    @Bind(R.id.b_imageView_video_mult)
    IjkVideoView b_imageView_video_mult;
    @Bind(R.id.img_42)
    ImageView img_42;
    @Bind(R.id.img_36)
    ImageView img_36;
    @Bind(R.id.img_12)
    ImageView img_12;
    @Bind(R.id.img_48)
    ImageView img_48;
    @Bind(R.id.img_sicbo)
    ImageView img_sicbo;
    @Bind(R.id.img_roulette)
    ImageView img_roulette;
    @Bind(R.id.img_mult)
    ImageView img_mult;
    @Bind(R.id.btn_stop_42)
    Button btn_stop_42;
    @Bind(R.id.btn_stop_36)
    Button btn_stop_36;
    @Bind(R.id.btn_stop_12)
    Button btn_stop_12;
    @Bind(R.id.btn_stop_48)
    Button btn_stop_48;
    @Bind(R.id.btn_stop_sicbo)
    Button btn_stop_sicbo;
    @Bind(R.id.btn_stop_roulette)
    Button btn_stop_roulette;
    @Bind(R.id.btn_stop_mult)
    Button btn_stop_mult;
    @Bind(R.id.b_text_video)
    TextView b_text_video;
    @Bind(R.id.b_text_video_36)
    TextView b_text_video_36;
    @Bind(R.id.b_text_video_12)
    TextView b_text_video_12;
    @Bind(R.id.b_text_video_48)
    TextView b_text_video_48;
    @Bind(R.id.b_text_video_sicbo)
    TextView b_text_video_sicbo;
    @Bind(R.id.b_text_video_roulette)
    TextView b_text_video_roulette;
    @Bind(R.id.b_text_video_mult)
    TextView b_text_video_mult;
    List<VideoPlayControlBean> controlBeanList;
    private Context context;
    private BlockDialog blockDialog;
    private String cookie="";
    private String NumberGameParams = "";
    private String NumberGameStatusMessage="";
    private String BetErrorMessage = "";
    //线程，定时拿状态信息
    private UpdateGameStatus updateGameStatus = null;
    private Thread threadGameStatus = null;
    private SimpleDateFormat format;
    private String game_name;
    //线程，定时更新界面状态
    private UpdateTimer updateTimer = null;
    private Thread threadTimer = null;
    private Handler handlerTimer = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if(!isAttached)
                return;
            switch(msg.what)
            {
                case SHOW_TIMER:
                    UpdateInterface();
                    break;
                case SHOW_BET_SECCESS:
                    blockDialog.dismiss();
                    Toast.makeText(LiveDigNumberActivity.this, R.string.xiazhusuccess, Toast.LENGTH_LONG).show();
                    break;
                case SHOW_BET_ERROR:
                    blockDialog.dismiss();
                    dialogshow.hide();
                    Toast.makeText(LiveDigNumberActivity.this, BetErrorMessage, Toast.LENGTH_LONG).show();
                    break;
            }
            //

        }
    };

    private void playVideo(IjkVideoView surfaceView,ImageView imgPlay){
        for (int i = 0; i < controlBeanList.size(); i++) {
            VideoPlayControlBean bean = controlBeanList.get(i);
            if (bean.getImgPlay().equals(imgPlay)){
                bean.getImgPlay().setVisibility(View.GONE);
                bean.getImgStop().setVisibility(View.VISIBLE);
                bean.getImgHint().setVisibility(View.GONE);
                break;
            }
        }
        surfaceView.start();
    }

    private void pauseVideo(IjkVideoView surfaceView,View v){
        for (int i = 0; i < controlBeanList.size(); i++) {
            VideoPlayControlBean bean = controlBeanList.get(i);
            if (v.equals(bean.getImgStop())){
                bean.getImgStop().setVisibility(View.GONE);
                bean.getImgPlay().setVisibility(View.VISIBLE);
                bean.getImgHint().setVisibility(View.VISIBLE);
                break;
            }
        }
        surfaceView.pause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.dianji42:
               playVideo(b_imageView_video_42,dianji42);
               break;
            case R.id.dianji36:
                playVideo(b_imageView_video_36,dianji36);
                break;
            case R.id.dianji12:
                playVideo(b_imageView_video_12,dianji12);
                break;
            case R.id.dianji48:
                playVideo(b_imageView_video_48,dianji48);
                break;
            case R.id.dianjisicbo:
                playVideo(b_imageView_video_sicbo,dianjisicbo);
                break;
            case R.id.dianjiroulette:
                playVideo(b_imageView_video_roulette,dianjiroulette);
                break;
            case R.id.dianjimult:
                playVideo(b_imageView_video_mult,dianjimult);
                break;
            case R.id.btn_stop_42:
                pauseVideo(b_imageView_video_42,v);
                break;
            case R.id.btn_stop_36:
                pauseVideo(b_imageView_video_36,v);
                break;
            case R.id.btn_stop_12:
                pauseVideo(b_imageView_video_12,v);
                break;
            case R.id.btn_stop_48:
                pauseVideo(b_imageView_video_48,v);
                break;
            case R.id.btn_stop_sicbo:
                pauseVideo(b_imageView_video_sicbo,v);
                break;
            case R.id.btn_stop_roulette:
                pauseVideo(b_imageView_video_roulette,v);
                break;
            case R.id.btn_stop_mult:
                pauseVideo(b_imageView_video_mult,v);
                break;
        }
    }

    private void initVedioClick() {
        b_imageView_video_42.setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
        b_imageView_video_42.setVideoURI(Uri.parse(WebSiteUrl.LiveNumberGameVideoUrl_42));
        b_imageView_video_36.setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
        b_imageView_video_36.setVideoURI(Uri.parse(WebSiteUrl.LiveNumberGameVideoUrl_36));
        b_imageView_video_12.setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
        b_imageView_video_12.setVideoURI(Uri.parse(WebSiteUrl.LiveNumberGameVideoUrl_12));
        b_imageView_video_48.setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
        b_imageView_video_48.setVideoURI(Uri.parse(WebSiteUrl.LiveNumberGameVideoUrl_48));
        b_imageView_video_sicbo.setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
        b_imageView_video_sicbo.setVideoURI(Uri.parse(WebSiteUrl.LiveGameVideoUrl_Sicbo));
        b_imageView_video_roulette.setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
        b_imageView_video_roulette.setVideoURI(Uri.parse(WebSiteUrl.LiveGameVideoUrl_Roulette));
        b_imageView_video_mult.setAspectRatio(IRenderView.AR_ASPECT_FILL_PARENT);
        b_imageView_video_mult.setVideoURI(Uri.parse(WebSiteUrl.LiveNumberGameVideoUrl_Multiple_36));
        controlBeanList = new ArrayList<>();
        controlBeanList.add(new VideoPlayControlBean(dianji42,img_42,btn_stop_42));
        controlBeanList.add(new VideoPlayControlBean(dianji36,img_36,btn_stop_36));
        controlBeanList.add(new VideoPlayControlBean(dianji12,img_12,btn_stop_12));
        controlBeanList.add(new VideoPlayControlBean(dianji48,img_48,btn_stop_48));
        controlBeanList.add(new VideoPlayControlBean(dianjisicbo,img_sicbo,btn_stop_sicbo));
        controlBeanList.add(new VideoPlayControlBean(dianjiroulette,img_roulette,btn_stop_roulette));
        controlBeanList.add(new VideoPlayControlBean(dianjimult,img_mult,btn_stop_mult));
        dianji42.setOnClickListener(this);
        dianji36.setOnClickListener(this);
        dianji12.setOnClickListener(this);
        dianji48.setOnClickListener(this);
        dianjisicbo.setOnClickListener(this);
        dianjiroulette.setOnClickListener(this);
        dianjimult.setOnClickListener(this);
        btn_stop_42.setOnClickListener(this);
        btn_stop_36.setOnClickListener(this);
        btn_stop_12.setOnClickListener(this);
        btn_stop_48.setOnClickListener(this);
        btn_stop_sicbo.setOnClickListener(this);
        btn_stop_roulette.setOnClickListener(this);
        btn_stop_mult.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_live_game_hall;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        //初始化List
        AppTool.setAppLanguage(this, "");
        setTitle(getString(R.string.livenumber));
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        blockDialog = new BlockDialog(LiveDigNumberActivity.this, LiveDigNumberActivity.this.getString(R.string.xiazhuzhong));
        StartUpdateGameStatus();
        InitInterface();
        InitOddsFactorText();
        InitButtonClick();
        initVedioClick();
    }

    public void InitButtonClick()
    {
        //42balls
        rl_red42.setOnClickListener(new ButtonClick());
        rl_yellow42.setOnClickListener(new ButtonClick());
        rl_blue42.setOnClickListener(new ButtonClick());
        rl_big42.setOnClickListener(new ButtonClick());
        rl_small42.setOnClickListener(new ButtonClick());
        rl_double42.setOnClickListener(new ButtonClick());
        rl_single42.setOnClickListener(new ButtonClick());
        rl_small_double42.setOnClickListener(new ButtonClick());
        rl_big_double42.setOnClickListener(new ButtonClick());
        rl_small_single42.setOnClickListener(new ButtonClick());
        rl_big_single42.setOnClickListener(new ButtonClick());
        //36balls
        rl_red36.setOnClickListener(new ButtonClick());
        rl_yellow36.setOnClickListener(new ButtonClick());
        rl_blue36.setOnClickListener(new ButtonClick());
        rl_big36.setOnClickListener(new ButtonClick());
        rl_small36.setOnClickListener(new ButtonClick());
        rl_double36.setOnClickListener(new ButtonClick());
        rl_single36.setOnClickListener(new ButtonClick());
        rl_small_double36.setOnClickListener(new ButtonClick());
        rl_big_double36.setOnClickListener(new ButtonClick());
        rl_small_single36.setOnClickListener(new ButtonClick());
        rl_big_single36.setOnClickListener(new ButtonClick());
        //12balls
        rl_red12.setOnClickListener(new ButtonClick());
        rl_yellow12.setOnClickListener(new ButtonClick());
        rl_blue12.setOnClickListener(new ButtonClick());
        rl_big12.setOnClickListener(new ButtonClick());
        rl_small12.setOnClickListener(new ButtonClick());
        rl_double12.setOnClickListener(new ButtonClick());
        rl_single12.setOnClickListener(new ButtonClick());
        rl_small_double12.setOnClickListener(new ButtonClick());
        rl_big_double12.setOnClickListener(new ButtonClick());
        rl_small_single12.setOnClickListener(new ButtonClick());
        rl_big_single12.setOnClickListener(new ButtonClick());
        //48balls
        rl_golden48.setOnClickListener(new ButtonClick());
        rl_silver48.setOnClickListener(new ButtonClick());
        rl_big48.setOnClickListener(new ButtonClick());
        rl_small48.setOnClickListener(new ButtonClick());
        rl_double48.setOnClickListener(new ButtonClick());
        rl_single48.setOnClickListener(new ButtonClick());
        rl_small_double48.setOnClickListener(new ButtonClick());
        rl_big_double48.setOnClickListener(new ButtonClick());
        rl_small_single48.setOnClickListener(new ButtonClick());
        rl_big_single48.setOnClickListener(new ButtonClick());
        //SICBO BALLS
        btn_number_1.setOnClickListener(new ButtonClick());
        btn_number_2.setOnClickListener(new ButtonClick());
        btn_number_3.setOnClickListener(new ButtonClick());
        btn_number_4.setOnClickListener(new ButtonClick());
        btn_number_5.setOnClickListener(new ButtonClick());
        btn_number_6.setOnClickListener(new ButtonClick());
        rl_bigsicbo.setOnClickListener(new ButtonClick());
        rl_smallsicbo.setOnClickListener(new ButtonClick());
        rl_doublesicbo.setOnClickListener(new ButtonClick());
        rl_singlesicbo.setOnClickListener(new ButtonClick());

        //ROULETTE BALLS
        rl_redred.setOnClickListener(new ButtonClick());
        rl_black.setOnClickListener(new ButtonClick());
        rl_bigroulette.setOnClickListener(new ButtonClick());
        rl_smallroulette.setOnClickListener(new ButtonClick());
        rl_doubleroulette.setOnClickListener(new ButtonClick());
        rl_singleroulette.setOnClickListener(new ButtonClick());

        //进入单桌子
        btn_comin42.setOnClickListener(new ButtonClick());
        btn_comin36.setOnClickListener(new ButtonClick());
        btn_comin12.setOnClickListener(new ButtonClick());
        btn_comin48.setOnClickListener(new ButtonClick());
        btn_cominsicbo.setOnClickListener(new ButtonClick());
        btn_cominroulette.setOnClickListener(new ButtonClick());
        btn_cominmult.setOnClickListener(new ButtonClick());


    }

    public void EnterSingleTable(LiveGameBean digGameBean)
    {
        Log.i("LanjianTest","EnterSingleTable"+digGameBean.getGame_name());
        Bundle bundle=new Bundle();
        bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA,digGameBean.getGame_name());
        if (digGameBean.getGame_name().equals("48_Balls")){
		   AppTool.activiyJump(LiveDigNumberActivity.this, LiveDDigBetActivity.class, bundle);
        }else if (digGameBean.getGame_name().equals("Sicbo_Balls")){
            AppTool.activiyJump(LiveDigNumberActivity.this, LiveEDigBetActivity.class, bundle);

        }else if (digGameBean.getGame_name().equals("Roulette_Balls")){
            AppTool.activiyJump(LiveDigNumberActivity.this, LiveFDigBetActivity.class, bundle);

        }else if (digGameBean.getGame_name().equals("Multiple_36Balls")){
		   AppTool.activiyJump(LiveDigNumberActivity.this, LiveGDigBetActivity.class, bundle);

        }else{
            AppTool.activiyJump(LiveDigNumberActivity.this, LiveGameNumberSingleTableActivity.class, bundle);
        }
        finish();
    }

    public void showBetDialog(final LiveGameBean digGameBean,final int betType)
    {
        if(digGameBean.getCount_down() <=0 || digGameBean.getCount_down() > 3600000)
            return;
        context = LiveDigNumberActivity.this;
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.betting_dialog, null);
        ImageView iv_hide = (ImageView) view.findViewById(R.id.iv_hide);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_bet_pop_title);  //大小
        TextView tv_betType = (TextView) view.findViewById(R.id.tv_bet_pop_number);  //投资号码
        TextView tv_odds = (TextView) view.findViewById(R.id.tv_bet_pop_odds);  //赔率
        final EditText tv_bet_num = (EditText) view.findViewById(R.id.edt_bet_pop_amount);  //投注数目
        final TextView tv_act_bet = (TextView) view.findViewById(R.id.tv_bet_pop_actual_amount);  //实际投注
        Button btn_confim = (Button) view.findViewById(R.id.btn_bet_pop_sure); //确定
        Button btn_cancel = (Button) view.findViewById(R.id.btn_bet_pop_cancel); //取消

        //Init
        switch(betType)
        {
            case 1:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.hong);
                tv_odds.setText(digGameBean.getColor_factor());
                break;
            case 2:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.huang);
                tv_odds.setText(digGameBean.getColor_factor());
                break;
            case 3:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.lan);
                tv_odds.setText(digGameBean.getColor_factor());
                break;
            case 4:
                tv_title.setText(R.string.big_small);
                tv_betType.setText(R.string.big);
                tv_odds.setText(digGameBean.getBigsmall_factor());
                break;
            case 5:
                tv_title.setText(R.string.big_small);
                tv_betType.setText(R.string.small);
                tv_odds.setText(digGameBean.getBigsmall_factor());
                break;
            case 6:
                tv_title.setText(R.string.even_odd);
                tv_betType.setText(R.string.even_lottery);
                tv_odds.setText(digGameBean.getOddeven_factor());
                break;
            case 7:
                tv_title.setText(R.string.even_odd);
                tv_betType.setText(R.string.odd_lottery);
                tv_odds.setText(digGameBean.getOddeven_factor());
                break;
            case 8:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.small)+" "+ getString(R.string.even_lottery));
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 9:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.small) +" "+  getString(R.string.odd_lottery));
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 10:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.big)+" "+ getString(R.string.even_lottery));
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 11:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.big)+" "+ getString(R.string.odd_lottery));//qdadan
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 12:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.jinesedui);
                tv_odds.setText(digGameBean.getGolodsliver_factor());
                break;
            case 13:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.yinsedui);
                tv_odds.setText(digGameBean.getGolodsliver_factor());
                break;
            case 14:
                tv_title.setText(R.string.sanjun);
                tv_betType.setText("SINGLE NUMBER 1");
                tv_odds.setText(digGameBean.getThreeforces_factor());
                break;
            case 15:
                tv_title.setText(R.string.sanjun);
                tv_betType.setText("SINGLE NUMBER 2");
                tv_odds.setText(digGameBean.getThreeforces_factor());
                break;
            case 16:
                tv_title.setText(R.string.sanjun);
                tv_betType.setText("SINGLE NUMBER 3");
                tv_odds.setText(digGameBean.getThreeforces_factor());
                break;
            case 17:
                tv_title.setText(R.string.sanjun);
                tv_betType.setText("SINGLE NUMBER 4");
                tv_odds.setText(digGameBean.getThreeforces_factor());
                break;
            case 18:
                tv_title.setText(R.string.sanjun);
                tv_betType.setText("SINGLE NUMBER 5");
                tv_odds.setText(digGameBean.getThreeforces_factor());
                break;
            case 19:
                tv_title.setText(R.string.sanjun);
                tv_betType.setText("SINGLE NUMBER 6");
                tv_odds.setText(digGameBean.getThreeforces_factor());
                break;
            case 20:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.hong);
                tv_odds.setText(digGameBean.getRedblack_factor());
                break;
            case 21:
                tv_title.setText(R.string.yanse);
                tv_betType.setText(R.string.hei);
                tv_odds.setText(digGameBean.getRedblack_factor());
                break;
            default:
                break;
        }

        tv_bet_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    String strNumber = editable.toString().trim();
                    tv_act_bet.setText(String.format("%.2f", Double.parseDouble(strNumber)));
                } catch (Exception e) {
                }
            }
        });
        //bet
        btn_confim.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              NumberGameBet(tv_bet_num.getText().toString().trim(), digGameBean,betType);
                                          }
                                      }
        );
        btn_cancel.setOnClickListener(new View.OnClickListener()
                                      {
                                          @Override
                                          public void onClick(View view) {
                                              dialogshow.hide();
                                          }
                                      }
        );
        iv_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogshow.hide();
            }
        });
        alertdialog.setView(view);
        dialogshow = alertdialog.show();
        this.game_name=digGameBean.getGame_name();
        dialogshow.setCanceledOnTouchOutside(true);
        Window window = dialogshow.getWindow();
        window.setWindowAnimations(R.style.DialogAnimation);
        dialogshow.setOnDismissListener(new
                                                DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface arg0) {
                                                        dialogshow = null;
                                                    }
                                                });

    }

    private void dissmisCurrentDialog(String game_name) {
        if (isAttached&&dialogshow!=null&&dialogshow.isShowing()&&this.game_name!=null&&this.game_name.equals(game_name)) {
            dialogshow.hide();
        }
    }

    public void NumberGameBet(String betMoney,LiveGameBean digGameBean,int betType)
    {
        boolean bBet = true;
        try{
            if (betMoney == null || betMoney.length() == 0) {
                Toast.makeText(LiveDigNumberActivity.this, R.string.qingchashumu, Toast.LENGTH_LONG).show();
                return;
            }
            if (!"".equals(getApp().getUserInfo().getMoneyBalance().getBalance())||
                    (getApp().getUserInfo().getMoneyBalance().getBalance()!=null))
            {
                String balance = getApp().getUserInfo().getMoneyBalance().getBalance();
                double b_balance=Double.valueOf(balance);
                double d_betMoney = Double.valueOf(betMoney);
                if(b_balance < d_betMoney)
                {
                    dialogshow.hide();
                    bBet = false;
                    Toast.makeText(LiveDigNumberActivity.this, R.string.yuebuzhu,Toast.LENGTH_LONG).show();
                    return ;
                }

                switch (betType)
                {
                    case 1://red
                    case 2:
                    case 3:

                        bBet = LimitBet(d_betMoney,digGameBean.getColor_minbet(),digGameBean.getColor_maxbet(),digGameBean.getColor_total(),digGameBean.getColor_bet_total());
                        break;
                    case 4:
                    case 5:
                      //  android.util.Log.w("HtttpVolley", "总的大小=====" +digGameBean.getBigsmall_total());
                        bBet = LimitBet(d_betMoney,digGameBean.getBigsmall_minbet(),digGameBean.getBigsmall_maxbet(),digGameBean.getBigsmall_total(),digGameBean.getBigsmall_bet_total());

                        break;
                    case 6:
                    case 7:
                        bBet = LimitBet(d_betMoney,digGameBean.getOddeven_mixbet(),digGameBean.getOddeven_maxbet(),digGameBean.getOddeven_total(),digGameBean.getOddeven_bet_total());


                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        bBet = LimitBet(d_betMoney,digGameBean.getCombination_mixbet(),digGameBean.getCombination_maxbet(),digGameBean.getCombination_total(),digGameBean.getCombination_bet_total());


                        break;
                    case 12:
                    case 13:
                        bBet = LimitBet(d_betMoney,digGameBean.getGolodsliver_mixbet(),digGameBean.getGolodsliver_maxbet(),digGameBean.getGolodsliver_total(),digGameBean.getGolodsliver_bet_total());


                        break;
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                        bBet = LimitBet(d_betMoney,digGameBean.getThreeforces_mixbet(),digGameBean.getThreeforces_maxbet(),digGameBean.getThreeforces_total(),digGameBean.getThreeforces_bet_total());


                        break;
                    case 20:
                    case 21:
                        bBet = LimitBet(d_betMoney,digGameBean.getRedblack_mixbet(),digGameBean.getRedblack_maxbet(),digGameBean.getRedblack_total(),digGameBean.getRedblack_bet_total());


                        break;
                }
                //
                if(bBet)
                {
                    Log.i("LanjianTest","d_betMoney="+d_betMoney+" betType="+betType);
                    dialogshow.hide();
                    blockDialog.show();
                    BetNumberGame betNumberGame = new BetNumberGame();
                    betNumberGame.setBetType(betType);
                    betNumberGame.setBetMoney(betMoney);
                    betNumberGame.setDigGameBean(digGameBean);
                    Thread threadBetGame = new Thread(betNumberGame);
                    threadBetGame.start();
                }
            }
            else//ti shi yu e you wen ti
            {

            }
        }catch (Exception e){
            Log.i("LanjianTest","NumberGameBet="+e.getMessage());
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "onResume()");
        // bGetNumberStatus = true;
        updateAllData = true;//返回大厅，重新刷新一次所有桌状态
        StartUpdateGameStatus();
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "onPause()");
        StopUpdateGameStatus();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "onRestart()");
        AppTool.setAppLanguage(this, "");
        updateAllData = true;//返回大厅，重新刷新一次所有桌状态
        StartUpdateGameStatus();
        InitInterface();
        //初始化界面所有数据
        super.onRestart();
    }

    public void StartUpdateGameStatus()
    {
        Log.i("LanjianTest", "StartUpdateGameStatus()");
        bGetNumberStatus = true;
        if(updateGameStatus == null)
        {
            Log.i("LanjianTest", "StartUpdateGameStatus() Start");
            updateGameStatus = new UpdateGameStatus();
            threadGameStatus = new Thread(updateGameStatus);
            threadGameStatus.start();
        }
        //   handler1.postDelayed(runnable,5000);
        bTimerStatus = true;
        if(updateTimer == null)
        {
            updateTimer = new UpdateTimer();
            threadTimer = new Thread(updateTimer);
            threadTimer.start();
        }
    }

    public void StopUpdateGameStatus()
    {
        bGetNumberStatus = false;
        if(updateGameStatus != null)
            updateGameStatus = null;
        if(threadGameStatus != null)
            threadGameStatus = null;
        bTimerStatus = false;
        if(updateTimer != null)
            updateTimer = null;
        if(threadTimer != null)
            threadTimer = null;
    }

    public void CountDownTime(LiveGameBean digGameBean)
    {
        Long count_downtime = digGameBean.getCount_down();
        if(count_downtime!=null && count_downtime > 0 )
        {
            count_downtime=count_downtime-1000;
            digGameBean.setCount_down(count_downtime);

        }
    }

    public void UpdateCountDownTime()
    {
        CountDownTime(getApp().getLivegame_42balls());
        CountDownTime(getApp().getLivegame_36balls());
        CountDownTime(getApp().getLivegame_12balls());
        CountDownTime(getApp().getLivegame_48balls());
        CountDownTime(getApp().getLivegame_Sicoballs());
        CountDownTime(getApp().getLivegame_Scollballs());
        CountDownTime(getApp().getLivegame_MULTIPLEballs());
    }

    public void UpdateInterface(LiveGameBean digGameBean,boolean init,TextView gameName,TextView gameNumber,TextView countDown,TextView betBackGround,
                                RelativeLayout rl_circle,ViewGroup betLayout,LinearLayout linearLayout)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
     /*   if(digGameBean.getGame_name().equals("Multiple_36Balls")){
            if (digGameBean.isGame_status())
              Log.i("LanjianTest","Live4="+digGameBean.getCount_down());
            else
                Log.i("LanjianTest","Live5="+digGameBean.isGame_status());
        }*/
        if (digGameBean.isGame_status()){
            if(init){
                if(digGameBean.getGame_name().contains("42_")||digGameBean.getGame_name().contains("36_")||digGameBean.getGame_name().contains("12_")
                        ||digGameBean.getGame_name().contains("48_")){
                    gameName.setText(digGameBean.getGame_name().substring(0,2) + " "+this.getString(R.string.qiu));
                }else if(digGameBean.getGame_name().contains("Sicbo")){
                    gameName.setText(this.getString(R.string.saibao));
                }else if(digGameBean.getGame_name().contains("Roulette")){
                    gameName.setText(this.getString(R.string.lunpan));
                }else if(digGameBean.getGame_name().contains("Multiple")){
                    gameName.setText(this.getString(R.string.zhuhe36qiu));
                }

            }
            if(View.GONE ==betLayout.getVisibility())
                betLayout.setVisibility(View.VISIBLE);
            if(View.GONE ==linearLayout.getVisibility())
                linearLayout.setVisibility(View.VISIBLE);

            gameNumber.setText(digGameBean.getGame_perid());
            Date date=null;
            if (digGameBean.getCount_down()>=0){
                 date=new Date(digGameBean.getCount_down());
            }else{
                date=new Date(0);
            }

            String splite[]=simpleDateFormat.format(date).split(":");
            long Min = digGameBean.getOpenUrl()*60*1000;
            if (digGameBean.getCount_down() <= 0 || digGameBean.getCount_down() >= Min || (splite[0].equals("00")&&splite[1].equals("00"))) {
                countDown.setText("00:00");
                dissmisCurrentDialog(digGameBean.getGame_name());
                if(View.GONE ==betBackGround.getVisibility())
                    betBackGround.setVisibility(View.VISIBLE);
                if(View.GONE ==rl_circle.getVisibility())
                    rl_circle.setVisibility(View.VISIBLE);
                /*if(digGameBean.getGame_name().equals("Multiple_36Balls")){
                    Log.i("LanjianTest","Live1="+digGameBean.getCount_down());
                }*/

            }else{
               /* if(digGameBean.getGame_name().equals("Multiple_36Balls")){
                    Log.i("LanjianTest","Live2="+digGameBean.getCount_down());
                }*/
                countDown.setText(simpleDateFormat.format(date));
                if(View.VISIBLE == betBackGround.getVisibility())
                    betBackGround.setVisibility(View.GONE);
                if(View.VISIBLE == rl_circle.getVisibility())
                    rl_circle.setVisibility(View.GONE);
            }
            date = null;
        }else{//如果游戏关闭了 ，隐藏这一桌的信息
            if(View.VISIBLE ==betLayout.getVisibility())
                betLayout.setVisibility(View.GONE);
            if(View.VISIBLE ==linearLayout.getVisibility())
                linearLayout.setVisibility(View.GONE);

        }
        simpleDateFormat = null;
    }

    public void InitOddsFactorText(LiveGameBean digGameBean,TextView tv_red_odds,TextView tv_yelllow_odds,TextView tv_blue_odds,TextView tv_big_odds,TextView tv_small_odds
            ,TextView tv_single_odds,TextView tv_double_odds,TextView tv_small_double_odds,TextView tv_big_double_odds,TextView tv_small_single_odds,TextView tv_big_single_odds)
    {
        tv_red_odds.setText(digGameBean.getColor_factor());
        tv_yelllow_odds.setText(digGameBean.getColor_factor());
        tv_blue_odds.setText(digGameBean.getColor_factor());
        tv_big_odds.setText(digGameBean.getBigsmall_factor());
        tv_small_odds.setText(digGameBean.getBigsmall_factor());
        tv_double_odds.setText(digGameBean.getOddeven_factor());
        tv_single_odds.setText(digGameBean.getOddeven_factor());
        tv_small_double_odds.setText(digGameBean.getCombination_factor());
        tv_small_single_odds.setText(digGameBean.getCombination_factor());
        tv_big_double_odds.setText(digGameBean.getCombination_factor());
        tv_big_single_odds.setText(digGameBean.getCombination_factor());
    }

    public void InitOddsFactor48Text(LiveGameBean digGameBean,TextView tv_golden_odds48,TextView tv_silver_odds48,TextView tv_big_odds,TextView tv_small_odds
            ,TextView tv_single_odds,TextView tv_double_odds,TextView tv_small_double_odds,TextView tv_big_double_odds,TextView tv_small_single_odds,TextView tv_big_single_odds)
    {
        tv_golden_odds48.setText(digGameBean.getGolodsliver_factor());
        tv_silver_odds48.setText(digGameBean.getGolodsliver_factor());
        tv_big_odds.setText(digGameBean.getBigsmall_factor());
        tv_small_odds.setText(digGameBean.getBigsmall_factor());
        tv_double_odds.setText(digGameBean.getOddeven_factor());
        tv_single_odds.setText(digGameBean.getOddeven_factor());
        tv_small_double_odds.setText(digGameBean.getCombination_factor());
        tv_small_single_odds.setText(digGameBean.getCombination_factor());
        tv_big_double_odds.setText(digGameBean.getCombination_factor());
        tv_big_single_odds.setText(digGameBean.getCombination_factor());
    }

    public void InitOddsFactorSICBOText(LiveGameBean digGameBean,TextView tv_big_odds,TextView tv_small_odds
            ,TextView tv_single_odds,TextView tv_double_odds)
    {
        tv_big_odds.setText(digGameBean.getBigsmall_factor());
        tv_small_odds.setText(digGameBean.getBigsmall_factor());
        tv_double_odds.setText(digGameBean.getOddeven_factor());
        tv_single_odds.setText(digGameBean.getOddeven_factor());
    }

    public void InitOddsFactorRollText(LiveGameBean digGameBean,TextView tv_redred_odds,TextView tv_black_odds,TextView tv_big_odds,TextView tv_small_odds
            ,TextView tv_single_odds,TextView tv_double_odds)
    {
        tv_redred_odds.setText(digGameBean.getRedblack_factor());
        tv_black_odds.setText(digGameBean.getRedblack_factor());
        tv_big_odds.setText(digGameBean.getBigsmall_factor());
        tv_small_odds.setText(digGameBean.getBigsmall_factor());
        tv_double_odds.setText(digGameBean.getOddeven_factor());
        tv_single_odds.setText(digGameBean.getOddeven_factor());
    }

    public void InitOddsFactorMultText(LiveGameBean digGameBean)
    {
    }

    public void InitOddsFactorText()
    {
        InitOddsFactorText(getApp().getLivegame_42balls(), tv_red_odds42, tv_yelllow_odds42, tv_blue_odds42, tv_big_odds42, tv_small_odds42
                , tv_single_odds42, tv_double_odds42, tv_small_double_odds42, tv_big_double_odds42, tv_small_single_odds42, tv_big_single_odds42);

        InitOddsFactorText(getApp().getLivegame_36balls(), tv_red_odds36, tv_yelllow_odds36, tv_blue_odds36, tv_big_odds36, tv_small_odds36
                , tv_single_odds36, tv_double_odds36, tv_small_double_odds36, tv_big_double_odds36, tv_small_single_odds36, tv_big_single_odds36);

        InitOddsFactorText(getApp().getLivegame_12balls(), tv_red_odds12, tv_yelllow_odds12, tv_blue_odds12, tv_big_odds12, tv_small_odds12
                , tv_single_odds12, tv_double_odds12, tv_small_double_odds12, tv_big_double_odds12, tv_small_single_odds12, tv_big_single_odds12);

        InitOddsFactor48Text(getApp().getLivegame_48balls(), tv_golden_odds48, tv_silver_odds48, tv_big_odds48, tv_small_odds48
                , tv_single_odds48, tv_double_odds48, tv_small_double_odds48, tv_big_double_odds48, tv_small_single_odds48, tv_big_single_odds48);
        InitOddsFactorSICBOText(getApp().getLivegame_Sicoballs(), tv_big_oddssicbo, tv_small_oddssicbo
                , tv_single_oddssicbo, tv_double_oddssicbo);
        InitOddsFactorRollText(getApp().getLivegame_Scollballs(), tv_redred_odds, tv_black_odds, tv_big_oddsroulette, tv_small_oddsroulette
                , tv_single_oddsroulette, tv_double_oddsroulette);
        InitOddsFactorMultText(getApp().getLivegame_MULTIPLEballs());

    }

    public void InitInterface()
    {
        //42球
        UpdateInterface(getApp().getLivegame_42balls(), true, tv_game_name42, tv_period42, tv_time42, tv_toumingdu42, rl_dialog42, num_fragment42,live_linearlayout42);
        UpdateInterface(getApp().getLivegame_36balls(), true, tv_game_name36, tv_period36, tv_time36, tv_toumingdu36, rl_dialog36, num_fragment36,live_linearlayout36);
        UpdateInterface(getApp().getLivegame_12balls(), true, tv_game_name12, tv_period12, tv_time12, tv_toumingdu12, rl_dialog12, num_fragment12,live_linearlayout12);
        UpdateInterface(getApp().getLivegame_48balls(), true, tv_game_name48, tv_period48, tv_time48, tv_toumingdu48, rl_dialog48, num_fragment48,live_linearlayout48);
        UpdateInterface(getApp().getLivegame_Sicoballs(), true, tv_game_namesicbo, tv_periodsicbo, tv_timesicbo, tv_toumingdusicbo, rl_dialogsicbo, num_fragmentsicbo,live_linearlayout_sicbo);
        UpdateInterface(getApp().getLivegame_Scollballs(), true, tv_game_nameroulette, tv_periodroulette, tv_timeroulette, tv_toumingduroulette, rl_dialogroulette, num_fragmentroulette,live_linearlayout_roulette);
        UpdateInterface(getApp().getLivegame_MULTIPLEballs(), true, tv_game_namemult, tv_periodmult, tv_timemult, tv_toumingdu49, rl_dialog49, num_fragment49,live_linearlayout_multiple36);
    }

    public void UpdateInterface()
    {
        //42球
        UpdateInterface(getApp().getLivegame_42balls(), false, tv_game_name42, tv_period42, tv_time42, tv_toumingdu42, rl_dialog42, num_fragment42,live_linearlayout42);
        UpdateInterface(getApp().getLivegame_36balls(), false, tv_game_name36, tv_period36, tv_time36, tv_toumingdu36, rl_dialog36, num_fragment36,live_linearlayout36);
        UpdateInterface(getApp().getLivegame_12balls(), false, tv_game_name12, tv_period12, tv_time12, tv_toumingdu12, rl_dialog12, num_fragment12,live_linearlayout12);
        UpdateInterface(getApp().getLivegame_48balls(), false, tv_game_name48, tv_period48, tv_time48, tv_toumingdu48, rl_dialog48, num_fragment48,live_linearlayout48);
        UpdateInterface(getApp().getLivegame_Sicoballs(), false, tv_game_namesicbo, tv_periodsicbo, tv_timesicbo, tv_toumingdusicbo, rl_dialogsicbo, num_fragmentsicbo,live_linearlayout_sicbo);
        UpdateInterface(getApp().getLivegame_Scollballs(), false, tv_game_nameroulette, tv_periodroulette, tv_timeroulette, tv_toumingduroulette, rl_dialogroulette, num_fragmentroulette,live_linearlayout_roulette);
        UpdateInterface(getApp().getLivegame_MULTIPLEballs(), false, tv_game_namemult, tv_periodmult, tv_timemult, tv_toumingdu49, rl_dialog49, num_fragment49,live_linearlayout_multiple36);
    }

    @Override
    protected void onDestroy() {
        StopUpdateGameStatus();
        getApp().setHttpClient(null);
        b_imageView_video_42.stopPlayback();
        b_imageView_video_36.stopPlayback();
        b_imageView_video_12.stopPlayback();
        b_imageView_video_48.stopPlayback();
        b_imageView_video_sicbo.stopPlayback();
        b_imageView_video_roulette.stopPlayback();
        b_imageView_video_mult.stopPlayback();
        super.onDestroy();
    }

    //bet limit
    public boolean  LimitBet(double betMoney,double minBet,double maxBet,double maxTotalBet,double totalBet){
        Log.i("BetTest","betMoney="+betMoney+",totalBet="+totalBet+"，minBet="+minBet+",maxBet="+maxBet+",maxTotalBet="+maxTotalBet);
        boolean returnValue = true;
        if(betMoney < minBet || betMoney > maxBet)
        {
            returnValue = false;
            Toast.makeText(this,this.getString(R.string.xiane)+
                            "["+(new Double(minBet)).intValue()+
                            "-" +(new Double(maxBet)).intValue()+"]"
                    ,Toast.LENGTH_LONG).show();

        }else if (betMoney + totalBet > maxTotalBet){
            returnValue = false;
            Toast.makeText(this,this.getString(R.string.caoguobet)
                    ,Toast.LENGTH_LONG).show();
        }
        return returnValue;
    }

    public class UpdateGameStatus implements Runnable {
        public void run() {
            while (bGetNumberStatus)
            {
                try {
                    getApp().GetLiveGameStatus(updateAllData,format,"LiveDigNumberActivity");
                    updateAllData=false;
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public class UpdateTimer implements Runnable {
        public void run() {
            while (bTimerStatus)
            {
                try {
                    //  Log.i("LanjianTest","UpdateTimer");
                    UpdateCountDownTime();
                    handlerTimer.sendEmptyMessage(SHOW_TIMER);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    class ButtonClick implements View.OnClickListener
    {
        public void onClick(View v)
        {
            switch (v.getId()) {
                case R.id.rl_red42:
                    showBetDialog(getApp().getLivegame_42balls(),1);
                    break;
                case R.id.rl_red36:
                    showBetDialog(getApp().getLivegame_36balls(),1);
                    break;
                case R.id.rl_red12:
                    showBetDialog(getApp().getLivegame_12balls(),1);
                    break;
                case R.id.rl_yellow42:
                    showBetDialog(getApp().getLivegame_42balls(),2);
                    break;
                case R.id.rl_yellow36:
                    showBetDialog(getApp().getLivegame_36balls(),2);
                    break;
                case R.id.rl_yellow12:
                    showBetDialog(getApp().getLivegame_12balls(),2);
                    break;
                case R.id.rl_blue42:
                    showBetDialog(getApp().getLivegame_42balls(),3);
                    break;
                case R.id.rl_blue36:
                    showBetDialog(getApp().getLivegame_36balls(),3);
                    break;
                case R.id.rl_blue12:
                    showBetDialog(getApp().getLivegame_12balls(),3);
                    break;
                case R.id.rl_big42:
                    showBetDialog(getApp().getLivegame_42balls(),4);
                    break;
                case R.id.rl_big36:
                    showBetDialog(getApp().getLivegame_36balls(),4);
                    break;
                case R.id.rl_big12:
                    showBetDialog(getApp().getLivegame_12balls(),4);
                    break;
                case R.id.rl_big48:
                    showBetDialog(getApp().getLivegame_48balls(),4);
                    break;
                case R.id.rl_bigsicbo:
                    showBetDialog(getApp().getLivegame_Sicoballs(),4);
                    break;
                case R.id.rl_bigroulette:
                    showBetDialog(getApp().getLivegame_Scollballs(),4);
                    break;
                case R.id.rl_small42:
                    showBetDialog(getApp().getLivegame_42balls(),5);
                    break;
                case R.id.rl_small36:
                    showBetDialog(getApp().getLivegame_36balls(),5);
                    break;
                case R.id.rl_small12:
                    showBetDialog(getApp().getLivegame_12balls(),5);
                    break;
                case R.id.rl_small48:
                    showBetDialog(getApp().getLivegame_48balls(),5);
                    break;
                case R.id.rl_smallsicbo:
                    showBetDialog(getApp().getLivegame_Sicoballs(),5);
                    break;
                case R.id.rl_smallroulette:
                    showBetDialog(getApp().getLivegame_Scollballs(),5);
                    break;
                case R.id.rl_double42:
                    showBetDialog(getApp().getLivegame_42balls(),6);
                    break;
                case R.id.rl_double36:
                    showBetDialog(getApp().getLivegame_36balls(),6);
                    break;
                case R.id.rl_double12:
                    showBetDialog(getApp().getLivegame_12balls(),6);
                    break;
                case R.id.rl_double48:
                    showBetDialog(getApp().getLivegame_48balls(),6);
                    break;
                case R.id.rl_doublesicbo:
                    showBetDialog(getApp().getLivegame_Sicoballs(),6);
                    break;
                case R.id.rl_doubleroulette:
                    showBetDialog(getApp().getLivegame_Scollballs(),6);
                    break;
                case R.id.rl_single42:
                    showBetDialog(getApp().getLivegame_42balls(),7);
                    break;
                case R.id.rl_single36:
                    showBetDialog(getApp().getLivegame_36balls(),7);
                    break;
                case R.id.rl_single12:
                    showBetDialog(getApp().getLivegame_12balls(),7);
                    break;
                case R.id.rl_single48:
                    showBetDialog(getApp().getLivegame_48balls(),7);
                    break;
                case R.id.rl_singlesicbo:
                    showBetDialog(getApp().getLivegame_Sicoballs(),7);
                    break;
                case R.id.rl_singleroulette:
                    showBetDialog(getApp().getLivegame_Scollballs(),7);
                    break;
                case R.id.rl_small_double42:
                    showBetDialog(getApp().getLivegame_42balls(),8);
                    break;
                case R.id.rl_small_double36:
                    showBetDialog(getApp().getLivegame_36balls(),8);
                    break;
                case R.id.rl_small_double12:
                    showBetDialog(getApp().getLivegame_12balls(),8);
                    break;
                case R.id.rl_small_double48:
                    showBetDialog(getApp().getLivegame_48balls(),8);
                    break;
                case R.id.rl_small_single42:
                    showBetDialog(getApp().getLivegame_42balls(),9);
                    break;
                case R.id.rl_small_single36:
                    showBetDialog(getApp().getLivegame_36balls(),9);
                    break;
                case R.id.rl_small_single12:
                    showBetDialog(getApp().getLivegame_12balls(),9);
                    break;
                case R.id.rl_small_single48:
                    showBetDialog(getApp().getLivegame_48balls(),9);
                    break;
                case R.id.rl_big_double42:
                    showBetDialog(getApp().getLivegame_42balls(),10);
                    break;
                case R.id.rl_big_double36:
                    showBetDialog(getApp().getLivegame_36balls(),10);
                    break;
                case R.id.rl_big_double12:
                    showBetDialog(getApp().getLivegame_12balls(),10);
                    break;
                case R.id.rl_big_double48:
                    showBetDialog(getApp().getLivegame_48balls(),10);
                    break;
                case R.id.rl_big_single42:
                    showBetDialog(getApp().getLivegame_42balls(),11);
                    break;
                case R.id.rl_big_single36:
                    showBetDialog(getApp().getLivegame_36balls(),11);
                    break;
                case R.id.rl_big_single12:
                    showBetDialog(getApp().getLivegame_12balls(),11);
                    break;
                case R.id.rl_big_single48:
                    showBetDialog(getApp().getLivegame_48balls(),11);
                    break;
                case R.id.rl_golden48:
                    showBetDialog(getApp().getLivegame_48balls(),12);
                    break;
                case R.id.rl_silver48:
                    showBetDialog(getApp().getLivegame_48balls(),13);
                    break;
                case R.id.btn_number_1:
                    showBetDialog(getApp().getLivegame_Sicoballs(),14);
                    break;
                case R.id.btn_number_2:
                    showBetDialog(getApp().getLivegame_Sicoballs(),15);
                    break;
                case R.id.btn_number_3:
                    showBetDialog(getApp().getLivegame_Sicoballs(),16);
                    break;
                case R.id.btn_number_4:
                    showBetDialog(getApp().getLivegame_Sicoballs(),17);
                    break;
                case R.id.btn_number_5:
                    showBetDialog(getApp().getLivegame_Sicoballs(),18);
                    break;
                case R.id.btn_number_6:
                    showBetDialog(getApp().getLivegame_Sicoballs(),19);
                    break;
                case R.id.rl_redred:
                    showBetDialog(getApp().getLivegame_Scollballs(),20);
                    break;
                case R.id.rl_black:
                    showBetDialog(getApp().getLivegame_Scollballs(),21);
                    break;
                case R.id.btn_comin42:
                    EnterSingleTable(getApp().getLivegame_42balls());
                    break;
                case R.id.btn_comin36:
                    EnterSingleTable(getApp().getLivegame_36balls());
                    break;
                case R.id.btn_comin12:
                    EnterSingleTable(getApp().getLivegame_12balls());
                    break;
                case R.id.btn_comin48:
                    EnterSingleTable(getApp().getLivegame_48balls());
                    break;
                case R.id.btn_cominsicbo:
                    EnterSingleTable(getApp().getLivegame_Sicoballs());
                    break;
                case R.id.btn_cominroulette:
                    EnterSingleTable(getApp().getLivegame_Scollballs());
				 break;
				 case R.id.btn_cominmult:
				 EnterSingleTable(getApp().getLivegame_MULTIPLEballs());
                    break;
                default:
                    break;
            }
        }
    }

    public class BetNumberGame implements Runnable {
        String betMoney;
        LiveGameBean digGameBean;
        private int betType;

        public void setBetMoney(String betMoney) {
            this.betMoney = betMoney;
        }

        public void setDigGameBean(LiveGameBean digGameBean) {
            this.digGameBean = digGameBean;
        }

        public void setBetType(int betType) {
            this.betType = betType;
        }

        public void run() {
            try{
                String playType="";
                String type = "";
                String betUrl = "";
                String params = "web_id="+WebSiteUrl.WebId+"&user_id="+getApp().getUserInfo().getUser_id()+"&session_id="+getApp().getUserInfo().getSession_id()+"&from=App";
                switch(betType)
                {
                    case 1:
                        playType = "color";
                        type = "0";
                        break;
                    case 2:
                        playType = "color";
                        type = "1";
                        break;
                    case 3:
                        playType = "color";
                        type = "2";
                        break;
                    case 4:
                        playType = "bigsmall";
                        type = "1";
                        break;
                    case 5:
                        playType = "bigsmall";
                        type = "0";
                        break;
                    case 6:
                        playType = "evenodd";
                        type = "0";
                        break;
                    case 7:
                        playType = "evenodd";
                        type = "1";
                        break;
                    case 8:
                        playType = "combination";
                        type = "0";
                        break;
                    case 9:
                        playType = "combination";
                        type = "1";
                        break;
                    case 10:
                        playType = "combination";
                        type = "2";
                        break;
                    case 11:
                        playType = "combination";
                        type = "3";
                        break;
                    case 12:
                        playType = "color";
                        type = "1";
                        break;
                    case 13:
                        playType = "color";
                        type = "0";
                        break;
                    case 14:
                        playType = "threeforces";
                        type = "1";
                        break;
                    case 15:
                        playType = "threeforces";
                        type = "2";
                        break;
                    case 16:
                        playType = "threeforces";
                        type = "3";
                        break;
                    case 17:
                        playType = "threeforces";
                        type = "4";
                        break;
                    case 18:
                        playType = "threeforces";
                        type = "5";
                        break;
                    case 19:
                        playType = "threeforces";
                        type = "6";
                        break;
                    case 20:
                        playType = "color";
                        type = "1";
                        break;
                    case 21:
                        playType = "color";
                        type = "0";
                        break;
                    default:
                        break;
                }
                params += "&get_bet="+playType+"#"+type;
                String postBetMoney = String.format("%.3f",Double.parseDouble(betMoney));
                params += "#" +postBetMoney;
                switch (digGameBean.getGame_name())
                {
                    case "42_Balls":
                        betUrl = WebSiteUrl.LiveGameBet42;
                        break;
                    case "36_Balls":
                        betUrl = WebSiteUrl.LiveGameBet36;
                        break;
                    case "12_Balls":
                        betUrl = WebSiteUrl.LiveGameBet12;
                        break;
                    case "48_Balls":
                        betUrl = WebSiteUrl.LiveGameBet48;
                        break;
                    case "Sicbo_Balls":
                        betUrl = WebSiteUrl.LiveGameBetSicbo;
                        break;
                    case "Roulette_Balls":
                        betUrl = WebSiteUrl.LiveGameBetRoulette;
                        break;
                    case "Multiple_36Balls":
                        betUrl = WebSiteUrl.LiveGameBetMultiple;
                        break;
                    default:
                        break;
                }
                Log.i("LanjianTest","params="+params);
                Log.i("LanjianTest","betUrl="+betUrl);
                String betResults = getApp().getHttpClient().sendPost(betUrl,params);
                Gson gson = new Gson();
                NyBaseResponse<DigGameBetBean> orgData ;
                orgData = gson.fromJson(betResults, new TypeToken<NyBaseResponse<DigGameBetBean>>() {
                }.getType());
                if(orgData.getMsg().equals("1"))
                {
                    switch(betType)
                    {
                        case 1:
                        case 2:
                        case 3:
                            digGameBean.setColor_bet_total(digGameBean.getColor_bet_total() + Double.parseDouble(postBetMoney));
                            break;
                        case 4:
                        case 5:
                            digGameBean.setBigsmall_bet_total(digGameBean.getBigsmall_bet_total() + Double.parseDouble(postBetMoney));
                            break;
                        case 6:
                        case 7:
                            digGameBean.setOddeven_bet_total(digGameBean.getOddeven_bet_total() + Double.parseDouble(postBetMoney));
                            break;
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                            digGameBean.setCombination_bet_total(digGameBean.getCombination_bet_total() + Double.parseDouble(postBetMoney));
                            break;
                        case 12:
                        case 13:
                            digGameBean.setGolodsliver_bet_total(digGameBean.getGolodsliver_bet_total() + Double.parseDouble(postBetMoney));
                            break;
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                            digGameBean.setThreeforces_bet_total(digGameBean.getThreeforces_bet_total() + Double.parseDouble(postBetMoney));
                            break;
                        case 20:
                        case 21:
                            digGameBean.setRedblack_total(digGameBean.getRedblack_total() + Double.parseDouble(postBetMoney));
                            break;
                        default:
                            break;
                    }
                    handlerTimer.sendEmptyMessage(SHOW_BET_SECCESS);
                }else{
                    switch (orgData.getMsg())
                    {
                        case "-1":
                            BetErrorMessage = LiveDigNumberActivity.this.getString(R.string.xiazhucuowu);
                            break;
                        case "-2":
                            BetErrorMessage = LiveDigNumberActivity.this.getString(R.string.xiazhuzuixiao);
                            break;
                        case "-3":
                            BetErrorMessage = LiveDigNumberActivity.this.getString(R.string.xiazhuzuida);
                            break;
                        case "-4":
                            BetErrorMessage = LiveDigNumberActivity.this.getString(R.string.xiazhucaoguo);
                            break;
                        case "-5":
                            BetErrorMessage = LiveDigNumberActivity.this.getString(R.string.betshibai);
                            break;
                        case "-6":
                            BetErrorMessage = LiveDigNumberActivity.this.getString(R.string.daojishiend);
                            break;
                        default:
                            break;
                    }
                    handlerTimer.sendEmptyMessage(SHOW_BET_ERROR);
                }
                orgData = null;
                gson = null;

                //  Toast.makeText(context, R.string.xiazhusuccess, Toast.LENGTH_LONG).show();
                //  blockDialog.dismiss();
            }catch (Exception e)
            {
                BetErrorMessage = "NetWorkError_001";
                handlerTimer.sendEmptyMessage(SHOW_BET_ERROR);
            }
        }
    }

}
