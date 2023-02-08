package nanyang.com.dig88.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.myview.GroupView.MyViewGroup;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.DigGameBean;
import nanyang.com.dig88.Entity.DigGameBetBean;
import nanyang.com.dig88.Entity.DigGameResultBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/2/18.
 */
public class AutoNumberActivity extends GameBaseActivity {


    //////////////////
    public static final int SHOW_TIMER = 0;
    public static final int SHOW_BET_SECCESS = 1;
    public static final int SHOW_BET_ERROR = 2;
    public static final int SHOW_RESULTS_42 = 12;
    public static final int SHOW_RESULTS_36 = 13;
    public static final int SHOW_RESULTS_24 = 14;
    public static final int SHOW_RESULTS_12 = 15;
    public static final int SHOW_RESULTS_18 = 16;
    public static final int SHOW_RESULTS_30 = 17;
    public List<DigGameBean> gamestate_list = new ArrayList<DigGameBean>();
    //  private NyVolleyJsonThreadHandler<List<DigGameStateBean>> tthallThread;//游戏状态
    public SimpleDateFormat format;
    public Dialog dialogshow;
    //////////////////初始化控件
    //42balls
    @BindView(R.id.tv_game_name42)
    TextView tv_game_name42;
    @BindView(R.id.tv_time42)
    TextView tv_time42;
    @BindView(R.id.btn_enter42)
    Button btn_enter42;
    @BindView(R.id.refresh_list42)
    ImageView refresh_list42;
    @BindView(R.id.tv_period42)
    TextView tv_period42;
    @BindView(R.id.rl_red42)
    LinearLayout rl_red42;
    @BindView(R.id.tv_red_odds42)
    TextView tv_red_odds42;
    @BindView(R.id.rl_yellow42)
    LinearLayout rl_yellow42;
    @BindView(R.id.tv_yelllow_odds42)
    TextView tv_yelllow_odds42;
    @BindView(R.id.rl_blue42)
    LinearLayout rl_blue42;
    @BindView(R.id.tv_blue_odds42)
    TextView tv_blue_odds42;
    @BindView(R.id.rl_big42)
    LinearLayout rl_big42;
    @BindView(R.id.tv_big_odds42)
    TextView tv_big_odds42;
    @BindView(R.id.rl_small42)
    LinearLayout rl_small42;
    @BindView(R.id.tv_small_odds42)
    TextView tv_small_odds42;
    @BindView(R.id.rl_single42)
    LinearLayout rl_single42;
    @BindView(R.id.tv_single_odds42)
    TextView tv_single_odds42;
    @BindView(R.id.rl_double42)
    LinearLayout rl_double42;
    @BindView(R.id.tv_double_odds42)
    TextView tv_double_odds42;
    @BindView(R.id.rl_small_double42)
    LinearLayout rl_small_double42;
    @BindView(R.id.tv_small_double_odds42)
    TextView tv_small_double_odds42;
    @BindView(R.id.rl_big_double42)
    LinearLayout rl_big_double42;
    @BindView(R.id.tv_big_double_odds42)
    TextView tv_big_double_odds42;
    @BindView(R.id.rl_small_single42)
    LinearLayout rl_small_single42;
    @BindView(R.id.tv_small_single_odds42)
    TextView tv_small_single_odds42;
    @BindView(R.id.rl_big_single42)
    LinearLayout rl_big_single42;
    @BindView(R.id.tv_big_single_odds42)
    TextView tv_big_single_odds42;
    @BindView(R.id.num_fragment42)
    FrameLayout num_fragment42;
    @BindView(R.id.tv_toumingdu42)
    TextView tv_toumingdu42;
    @BindView(R.id.rl_circle42)
    RelativeLayout rl_circle42;
    //36balls
    @BindView(R.id.tv_game_name36)
    TextView tv_game_name36;
    @BindView(R.id.tv_time36)
    TextView tv_time36;
    @BindView(R.id.btn_enter36)
    Button btn_enter36;
    @BindView(R.id.refresh_list36)
    ImageView refresh_list36;
    @BindView(R.id.tv_period36)
    TextView tv_period36;
    @BindView(R.id.rl_red36)
    LinearLayout rl_red36;
    @BindView(R.id.tv_red_odds36)
    TextView tv_red_odds36;
    @BindView(R.id.rl_yellow36)
    LinearLayout rl_yellow36;
    @BindView(R.id.tv_yelllow_odds36)
    TextView tv_yelllow_odds36;
    @BindView(R.id.rl_blue36)
    LinearLayout rl_blue36;
    @BindView(R.id.tv_blue_odds36)
    TextView tv_blue_odds36;
    @BindView(R.id.rl_big36)
    LinearLayout rl_big36;
    @BindView(R.id.tv_big_odds36)
    TextView tv_big_odds36;
    @BindView(R.id.rl_small36)
    LinearLayout rl_small36;
    @BindView(R.id.tv_small_odds36)
    TextView tv_small_odds36;
    @BindView(R.id.rl_single36)
    LinearLayout rl_single36;
    @BindView(R.id.tv_single_odds36)
    TextView tv_single_odds36;
    @BindView(R.id.rl_double36)
    LinearLayout rl_double36;
    @BindView(R.id.tv_double_odds36)
    TextView tv_double_odds36;
    @BindView(R.id.rl_small_double36)
    LinearLayout rl_small_double36;
    @BindView(R.id.tv_small_double_odds36)
    TextView tv_small_double_odds36;
    @BindView(R.id.rl_big_double36)
    LinearLayout rl_big_double36;
    @BindView(R.id.tv_big_double_odds36)
    TextView tv_big_double_odds36;
    @BindView(R.id.rl_small_single36)
    LinearLayout rl_small_single36;
    @BindView(R.id.tv_small_single_odds36)
    TextView tv_small_single_odds36;
    @BindView(R.id.rl_big_single36)
    LinearLayout rl_big_single36;
    @BindView(R.id.tv_big_single_odds36)
    TextView tv_big_single_odds36;
    @BindView(R.id.num_fragment36)
    FrameLayout num_fragment36;
    @BindView(R.id.tv_toumingdu36)
    TextView tv_toumingdu36;
    @BindView(R.id.rl_circle36)
    RelativeLayout rl_circle36;
    //24balls
    @BindView(R.id.tv_game_name24)
    TextView tv_game_name24;
    @BindView(R.id.tv_time24)
    TextView tv_time24;
    @BindView(R.id.btn_enter24)
    Button btn_enter24;
    @BindView(R.id.refresh_list24)
    ImageView refresh_list24;
    @BindView(R.id.tv_period24)
    TextView tv_period24;
    @BindView(R.id.rl_red24)
    LinearLayout rl_red24;
    @BindView(R.id.tv_red_odds24)
    TextView tv_red_odds24;
    @BindView(R.id.rl_yellow24)
    LinearLayout rl_yellow24;
    @BindView(R.id.tv_yelllow_odds24)
    TextView tv_yelllow_odds24;
    @BindView(R.id.rl_blue24)
    LinearLayout rl_blue24;
    @BindView(R.id.tv_blue_odds24)
    TextView tv_blue_odds24;
    @BindView(R.id.rl_big24)
    LinearLayout rl_big24;
    @BindView(R.id.tv_big_odds24)
    TextView tv_big_odds24;
    @BindView(R.id.rl_small24)
    LinearLayout rl_small24;
    @BindView(R.id.tv_small_odds24)
    TextView tv_small_odds24;
    @BindView(R.id.rl_single24)
    LinearLayout rl_single24;
    @BindView(R.id.tv_single_odds24)
    TextView tv_single_odds24;
    @BindView(R.id.rl_double24)
    LinearLayout rl_double24;
    @BindView(R.id.tv_double_odds24)
    TextView tv_double_odds24;
    @BindView(R.id.rl_small_double24)
    LinearLayout rl_small_double24;
    @BindView(R.id.tv_small_double_odds24)
    TextView tv_small_double_odds24;
    @BindView(R.id.rl_big_double24)
    LinearLayout rl_big_double24;
    @BindView(R.id.tv_big_double_odds24)
    TextView tv_big_double_odds24;
    @BindView(R.id.rl_small_single24)
    LinearLayout rl_small_single24;
    @BindView(R.id.tv_small_single_odds24)
    TextView tv_small_single_odds24;
    @BindView(R.id.rl_big_single24)
    LinearLayout rl_big_single24;
    @BindView(R.id.tv_big_single_odds24)
    TextView tv_big_single_odds24;
    @BindView(R.id.num_fragment24)
    FrameLayout num_fragment24;
    @BindView(R.id.tv_toumingdu24)
    TextView tv_toumingdu24;
    @BindView(R.id.rl_circle24)
    RelativeLayout rl_circle24;
    //12balls
    @BindView(R.id.tv_game_name12)
    TextView tv_game_name12;
    @BindView(R.id.tv_time12)
    TextView tv_time12;
    @BindView(R.id.btn_enter12)
    Button btn_enter12;
    @BindView(R.id.refresh_list12)
    ImageView refresh_list12;
    @BindView(R.id.tv_period12)
    TextView tv_period12;
    @BindView(R.id.rl_red12)
    LinearLayout rl_red12;
    @BindView(R.id.tv_red_odds12)
    TextView tv_red_odds12;
    @BindView(R.id.rl_yellow12)
    LinearLayout rl_yellow12;
    @BindView(R.id.tv_yelllow_odds12)
    TextView tv_yelllow_odds12;
    @BindView(R.id.rl_blue12)
    LinearLayout rl_blue12;
    @BindView(R.id.tv_blue_odds12)
    TextView tv_blue_odds12;
    @BindView(R.id.rl_big12)
    LinearLayout rl_big12;
    @BindView(R.id.tv_big_odds12)
    TextView tv_big_odds12;
    @BindView(R.id.rl_small12)
    LinearLayout rl_small12;
    @BindView(R.id.tv_small_odds12)
    TextView tv_small_odds12;
    @BindView(R.id.rl_single12)
    LinearLayout rl_single12;
    @BindView(R.id.tv_single_odds12)
    TextView tv_single_odds12;
    @BindView(R.id.rl_double12)
    LinearLayout rl_double12;
    @BindView(R.id.tv_double_odds12)
    TextView tv_double_odds12;
    @BindView(R.id.rl_small_double12)
    LinearLayout rl_small_double12;
    @BindView(R.id.tv_small_double_odds12)
    TextView tv_small_double_odds12;
    @BindView(R.id.rl_big_double12)
    LinearLayout rl_big_double12;
    @BindView(R.id.tv_big_double_odds12)
    TextView tv_big_double_odds12;
    @BindView(R.id.rl_small_single12)
    LinearLayout rl_small_single12;
    @BindView(R.id.tv_small_single_odds12)
    TextView tv_small_single_odds12;
    @BindView(R.id.rl_big_single12)
    LinearLayout rl_big_single12;
    @BindView(R.id.tv_big_single_odds12)
    TextView tv_big_single_odds12;
    @BindView(R.id.num_fragment12)
    FrameLayout num_fragment12;
    @BindView(R.id.tv_toumingdu12)
    TextView tv_toumingdu12;
    @BindView(R.id.rl_circle12)
    RelativeLayout rl_circle12;
    //18balls
    @BindView(R.id.tv_game_name18)
    TextView tv_game_name18;
    @BindView(R.id.tv_time18)
    TextView tv_time18;
    @BindView(R.id.btn_enter18)
    Button btn_enter18;
    @BindView(R.id.refresh_list18)
    ImageView refresh_list18;
    @BindView(R.id.tv_period18)
    TextView tv_period18;
    @BindView(R.id.rl_red18)
    LinearLayout rl_red18;
    @BindView(R.id.tv_red_odds18)
    TextView tv_red_odds18;
    @BindView(R.id.rl_yellow18)
    LinearLayout rl_yellow18;
    @BindView(R.id.tv_yelllow_odds18)
    TextView tv_yelllow_odds18;
    @BindView(R.id.rl_blue18)
    LinearLayout rl_blue18;
    @BindView(R.id.tv_blue_odds18)
    TextView tv_blue_odds18;
    @BindView(R.id.rl_big18)
    LinearLayout rl_big18;
    @BindView(R.id.tv_big_odds18)
    TextView tv_big_odds18;
    @BindView(R.id.rl_small18)
    LinearLayout rl_small18;
    @BindView(R.id.tv_small_odds18)
    TextView tv_small_odds18;
    @BindView(R.id.rl_single18)
    LinearLayout rl_single18;
    @BindView(R.id.tv_single_odds18)
    TextView tv_single_odds18;
    @BindView(R.id.rl_double18)
    LinearLayout rl_double18;
    @BindView(R.id.tv_double_odds18)
    TextView tv_double_odds18;
    @BindView(R.id.rl_small_double18)
    LinearLayout rl_small_double18;
    @BindView(R.id.tv_small_double_odds18)
    TextView tv_small_double_odds18;
    @BindView(R.id.rl_big_double18)
    LinearLayout rl_big_double18;
    @BindView(R.id.tv_big_double_odds18)
    TextView tv_big_double_odds18;
    @BindView(R.id.rl_small_single18)
    LinearLayout rl_small_single18;
    @BindView(R.id.tv_small_single_odds18)
    TextView tv_small_single_odds18;
    @BindView(R.id.rl_big_single18)
    LinearLayout rl_big_single18;
    @BindView(R.id.tv_big_single_odds18)
    TextView tv_big_single_odds18;
    @BindView(R.id.num_fragment18)
    FrameLayout num_fragment18;
    @BindView(R.id.tv_toumingdu18)
    TextView tv_toumingdu18;
    @BindView(R.id.rl_circle18)
    RelativeLayout rl_circle18;
    //30balls
    @BindView(R.id.tv_game_name30)
    TextView tv_game_name30;
    @BindView(R.id.tv_time30)
    TextView tv_time30;
    @BindView(R.id.btn_enter30)
    Button btn_enter30;
    @BindView(R.id.refresh_list30)
    ImageView refresh_list30;
    @BindView(R.id.tv_period30)
    TextView tv_period30;
    @BindView(R.id.rl_red30)
    LinearLayout rl_red30;
    @BindView(R.id.tv_red_odds30)
    TextView tv_red_odds30;
    @BindView(R.id.rl_yellow30)
    LinearLayout rl_yellow30;
    @BindView(R.id.tv_yelllow_odds30)
    TextView tv_yelllow_odds30;
    @BindView(R.id.rl_blue30)
    LinearLayout rl_blue30;
    @BindView(R.id.tv_blue_odds30)
    TextView tv_blue_odds30;
    @BindView(R.id.rl_big30)
    LinearLayout rl_big30;
    @BindView(R.id.tv_big_odds30)
    TextView tv_big_odds30;
    @BindView(R.id.rl_small30)
    LinearLayout rl_small30;
    @BindView(R.id.tv_small_odds30)
    TextView tv_small_odds30;
    @BindView(R.id.rl_single30)
    LinearLayout rl_single30;
    @BindView(R.id.tv_single_odds30)
    TextView tv_single_odds30;
    @BindView(R.id.rl_double30)
    LinearLayout rl_double30;
    @BindView(R.id.tv_double_odds30)
    TextView tv_double_odds30;
    @BindView(R.id.rl_small_double30)
    LinearLayout rl_small_double30;
    @BindView(R.id.tv_small_double_odds30)
    TextView tv_small_double_odds30;
    @BindView(R.id.rl_big_double30)
    LinearLayout rl_big_double30;
    @BindView(R.id.tv_big_double_odds30)
    TextView tv_big_double_odds30;
    @BindView(R.id.rl_small_single30)
    LinearLayout rl_small_single30;
    @BindView(R.id.tv_small_single_odds30)
    TextView tv_small_single_odds30;
    @BindView(R.id.rl_big_single30)
    LinearLayout rl_big_single30;
    @BindView(R.id.tv_big_single_odds30)
    TextView tv_big_single_odds30;
    @BindView(R.id.num_fragment30)
    FrameLayout num_fragment30;
    @BindView(R.id.tv_toumingdu30)
    TextView tv_toumingdu30;
    @BindView(R.id.rl_circle30)
    RelativeLayout rl_circle30;
    @BindView(R.id.num_linearlayout42)
    LinearLayout num_linearlayout42;
    @BindView(R.id.num_linearlayout36)
    LinearLayout num_linearlayout36;
    @BindView(R.id.num_linearlayout24)
    LinearLayout num_linearlayout24;
    @BindView(R.id.num_linearlayout12)
    LinearLayout num_linearlayout12;
    @BindView(R.id.num_linearlayout18)
    LinearLayout num_linearlayout18;
    @BindView(R.id.num_linearlayout30)
    LinearLayout num_linearlayout30;
    @BindView(R.id.mvg_ball42)
    MyViewGroup mvg_ball42;
    @BindView(R.id.mvg_ball36)
    MyViewGroup mvg_ball36;
    @BindView(R.id.mvg_ball24)
    MyViewGroup mvg_ball24;
    @BindView(R.id.mvg_ball12)
    MyViewGroup mvg_ball12;
    @BindView(R.id.mvg_ball18)
    MyViewGroup mvg_ball18;
    @BindView(R.id.mvg_ball30)
    MyViewGroup mvg_ball30;
    @BindView(R.id.ground_line42_v)
    View ground_line42_v;
    @BindView(R.id.ground_line36_v)
    View ground_line36_v;
    @BindView(R.id.ground_line24_v)
    View ground_line24_v;
    @BindView(R.id.ground_line12_v)
    View ground_line12_v;
    @BindView(R.id.ground_line18_v)
    View ground_line18_v;
    @BindView(R.id.ground_line30_v)
    View ground_line30_v;
    @BindView(R.id.selected_ball42_tv)
    TextView selected_ball42_tv;
    @BindView(R.id.selected_ball36_tv)
    TextView selected_ball36_tv;
    @BindView(R.id.selected_ball24_tv)
    TextView selected_ball24_tv;
    @BindView(R.id.selected_ball12_tv)
    TextView selected_ball12_tv;
    @BindView(R.id.selected_ball18_tv)
    TextView selected_ball18_tv;
    @BindView(R.id.selected_ball30_tv)
    TextView selected_ball30_tv;
    boolean updateAllData = false;
    boolean bGetNumberStatus = true;
    boolean bTimerStatus = true;
    private String BetErrorMessage = "";
    private Context context;
    private BlockDialog blockDialog;
    //线程，定时拿状态信息
    private UpdateGameStatus updateGameStatus = null;
    private Thread threadGameStatus = null;
    private String game_name;
    //线程，定时更新界面状态
    private UpdateTimer updateTimer = null;
    private Thread threadTimer = null;
    private Handler handlerTimer = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (!isAttached)
                return;
            switch (msg.what) {
                case SHOW_TIMER:
                    UpdateInterface();
                    break;
                case SHOW_BET_SECCESS:
                    blockDialog.dismiss();
                    Toast.makeText(AutoNumberActivity.this, R.string.xiazhusuccess, Toast.LENGTH_LONG).show();

                    break;
                case SHOW_BET_ERROR:
                    blockDialog.dismiss();
                    dialogshow.hide();
                    Toast.makeText(AutoNumberActivity.this, BetErrorMessage, Toast.LENGTH_LONG).show();
                    break;
                case SHOW_RESULTS_42:
                    showBallAnimation(getApp().getDignumbergame_42ball().getGame_result(), 42, mvg_ball42, ground_line42_v, selected_ball42_tv);
                    break;
                case SHOW_RESULTS_36:
                    showBallAnimation(getApp().getDignumbergame_36ball().getGame_result(), 36, mvg_ball36, ground_line36_v, selected_ball36_tv);
                    break;
                case SHOW_RESULTS_24:
                    showBallAnimation(getApp().getDignumbergame_24ball().getGame_result(), 24, mvg_ball24, ground_line24_v, selected_ball24_tv);
                    break;
                case SHOW_RESULTS_12:
                    showBallAnimation(getApp().getDignumbergame_12ball().getGame_result(), 12, mvg_ball12, ground_line12_v, selected_ball12_tv);
                    break;
                case SHOW_RESULTS_18:
                    showBallAnimation(getApp().getDignumbergame_18ball().getGame_result(), 18, mvg_ball18, ground_line18_v, selected_ball18_tv);
                    break;
                case SHOW_RESULTS_30:
                    showBallAnimation(getApp().getDignumbergame_30ball().getGame_result(), 30, mvg_ball30, ground_line30_v, selected_ball30_tv);
                    break;

            }
            //

        }
    };

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        //初始化List
//        AppTool.setAppLanguage(context, "");
        setTitle(getString(R.string.shuzidating));
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Log.i("LanjianTest", "initData");
        //   getAutoList();
        StartUpdateGameStatus();
        InitInterface();
        InitOddsFactorText();
        InitButtonClick();
        blockDialog = new BlockDialog(AutoNumberActivity.this, AutoNumberActivity.this.getString(R.string.xiazhuzhong));
        InitAllBalls();
//      LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout42);
//      linearLayout.setVisibility(View.GONE);
//      num_fragment42.setVisibility(View.GONE);


    }

    private void InitAllBalls() {
        if (getApp().getDignumbergame_42ball().isGame_status())
            InitBalls(42, mvg_ball42, selected_ball42_tv);
        if (getApp().getDignumbergame_36ball().isGame_status())
            InitBalls(36, mvg_ball36, selected_ball36_tv);
        if (getApp().getDignumbergame_24ball().isGame_status())
            InitBalls(24, mvg_ball24, selected_ball24_tv);
        if (getApp().getDignumbergame_12ball().isGame_status())
            InitBalls(12, mvg_ball12, selected_ball12_tv);
        if (getApp().getDignumbergame_18ball().isGame_status())
            InitBalls(18, mvg_ball18, selected_ball18_tv);
        if (getApp().getDignumbergame_30ball().isGame_status())
            InitBalls(30, mvg_ball30, selected_ball30_tv);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "AutoNumberActivity onResume()");
        // bGetNumberStatus = true;
        if (updateAllData == false)
            updateAllData = true;//返回大厅，重新刷新一次所有桌状态
        StartUpdateGameStatus();
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "AutoNumberActivity onPause()");
        StopUpdateGameStatus();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        Log.i("LanjianTest", "AutoNumberActivityon+Restart()");
        AppTool.setAppLanguage(this, "");
        updateAllData = true;//返回大厅，重新刷新一次所有桌状态
        StartUpdateGameStatus();
        InitInterface();
        //初始化界面所有数据
        InitAllBalls();
        super.onRestart();
    }

    public void StartUpdateGameStatus() {
        Log.i("LanjianTest", "StartUpdateGameStatus()");
        bGetNumberStatus = true;
        if (updateGameStatus == null) {
            Log.i("LanjianTest", "StartUpdateGameStatus() Start");
            updateGameStatus = new UpdateGameStatus();
            threadGameStatus = new Thread(updateGameStatus);
            threadGameStatus.start();
        }
        //   handler1.postDelayed(runnable,5000);
        bTimerStatus = true;
        if (updateTimer == null) {
            updateTimer = new UpdateTimer();
            threadTimer = new Thread(updateTimer);
            threadTimer.start();
        }
    }

    public void StopUpdateGameStatus() {
        bGetNumberStatus = false;
        if (updateGameStatus != null)
            updateGameStatus = null;
        if (threadGameStatus != null)
            threadGameStatus = null;
        bTimerStatus = false;
        if (updateTimer != null)
            updateTimer = null;
        if (threadTimer != null)
            threadTimer = null;

    }

    public void CountDownTime(DigGameBean digGameBean) {
        Long count_downtime = digGameBean.getCount_down();
        if (count_downtime != null && count_downtime > 0) {
            count_downtime = count_downtime - 1000;
            digGameBean.setCount_down(count_downtime);
        }

    }

    public void UpdateCountDownTime() {
        CountDownTime(getApp().getDignumbergame_42ball());
        CountDownTime(getApp().getDignumbergame_36ball());
        CountDownTime(getApp().getDignumbergame_24ball());
        CountDownTime(getApp().getDignumbergame_12ball());
        CountDownTime(getApp().getDignumbergame_18ball());
        CountDownTime(getApp().getDignumbergame_30ball());
    }

    public void UpdateInterface(DigGameBean digGameBean, boolean init, TextView gameName, TextView gameNumber, TextView countDown, TextView betBackGround,
                                RelativeLayout rl_circle, ViewGroup betLayout, LinearLayout linearLayout) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        if (digGameBean.isGame_status()) {
            //如果这一桌被关闭了，打开先
            if (View.GONE == betLayout.getVisibility())
                betLayout.setVisibility(View.VISIBLE);
            if (View.GONE == linearLayout.getVisibility())
                linearLayout.setVisibility(View.VISIBLE);

            if (init) {
                gameName.setText(digGameBean.getGame_name().substring(0, 2) + " " + this.getString(R.string.qiu));

            }

            gameNumber.setText(digGameBean.getGame_perid());
            Date date = new Date(digGameBean.getCount_down());
            String splite[] = simpleDateFormat.format(date).split(":");
            long Min = digGameBean.getOpenUrl() * 60 * 1000;
            if (digGameBean.getGame_name().equals("42_Balls"))
                Log.i("LanjianTest", "-----------" + Min + ",Count_down=" + digGameBean.getCount_down() + "," + simpleDateFormat.format(date));
            if (digGameBean.getCount_down() <= 0 || digGameBean.getCount_down() >= Min || (splite[0].equals("00") && splite[1].equals("00"))) {
                dissmisCurrentDialog(digGameBean.getGame_name());
                countDown.setText("00:00");
                if (View.GONE == betBackGround.getVisibility()) {
                    betBackGround.setVisibility(View.VISIBLE);
                    rl_circle.setVisibility(View.VISIBLE);
                    // 创建拿结果的线程
                    digGameBean.setbGetResults(true);
                    GetNumberGameResults getNumberGameResults = new GetNumberGameResults();
                    getNumberGameResults.setGameNumber(digGameBean.getGame_perid());
                    getNumberGameResults.setDigGameBean(digGameBean);
                    Thread threadResult = new Thread(getNumberGameResults);
                    threadResult.start();
                }


                //     if(digGameBean.getGame_name().equals("30_Balls") )
                //      Log.i("LanjianTest30","=="+digGameBean.getGame_perid());

            } else {
                //     if(digGameBean.getGame_name().equals("30_Balls") )
                //       Log.i("LanjianTest30",simpleDateFormat.format(date));
                countDown.setText(simpleDateFormat.format(date));
                if (View.VISIBLE == betBackGround.getVisibility()) {
                    betBackGround.setVisibility(View.GONE);
                    rl_circle.setVisibility(View.GONE);
                    if (digGameBean.isbGetResults())
                        digGameBean.setbGetResults(false);
                }
                //  if(View.VISIBLE == rl_circle.getVisibility())

            }
            date = null;
        } else {//如果游戏关闭了 ，隐藏这一桌的信息
            if (View.VISIBLE == betLayout.getVisibility())
                betLayout.setVisibility(View.GONE);
            if (View.VISIBLE == linearLayout.getVisibility())
                linearLayout.setVisibility(View.GONE);
            if (digGameBean.isbGetResults())
                digGameBean.setbGetResults(false);

        }
        simpleDateFormat = null;
    }

    private void dissmisCurrentDialog(String game_name) {
        if (isAttached&&dialogshow!=null&&dialogshow.isShowing()&&this.game_name!=null&&this.game_name.equals(game_name)) {
            dialogshow.hide();
        }
    }

    public void InitOddsFactorText(DigGameBean digGameBean, TextView tv_red_odds, TextView tv_yelllow_odds, TextView tv_blue_odds, TextView tv_big_odds, TextView tv_small_odds
            , TextView tv_single_odds, TextView tv_double_odds, TextView tv_small_double_odds, TextView tv_big_double_odds, TextView tv_small_single_odds, TextView tv_big_single_odds) {
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

    public void InitOddsFactorText() {
        InitOddsFactorText(getApp().getDignumbergame_42ball(), tv_red_odds42, tv_yelllow_odds42, tv_blue_odds42, tv_big_odds42, tv_small_odds42
                , tv_single_odds42, tv_double_odds42, tv_small_double_odds42, tv_big_double_odds42, tv_small_single_odds42, tv_big_single_odds42);
        InitOddsFactorText(getApp().getDignumbergame_36ball(), tv_red_odds36, tv_yelllow_odds36, tv_blue_odds36, tv_big_odds36, tv_small_odds36
                , tv_single_odds36, tv_double_odds36, tv_small_double_odds36, tv_big_double_odds36, tv_small_single_odds36, tv_big_single_odds36);
        InitOddsFactorText(getApp().getDignumbergame_24ball(), tv_red_odds24, tv_yelllow_odds24, tv_blue_odds24, tv_big_odds24, tv_small_odds24
                , tv_single_odds24, tv_double_odds24, tv_small_double_odds24, tv_big_double_odds24, tv_small_single_odds24, tv_big_single_odds24);
        InitOddsFactorText(getApp().getDignumbergame_12ball(), tv_red_odds12, tv_yelllow_odds12, tv_blue_odds12, tv_big_odds12, tv_small_odds12
                , tv_single_odds12, tv_double_odds12, tv_small_double_odds12, tv_big_double_odds12, tv_small_single_odds12, tv_big_single_odds12);
        InitOddsFactorText(getApp().getDignumbergame_18ball(), tv_red_odds18, tv_yelllow_odds18, tv_blue_odds18, tv_big_odds18, tv_small_odds18
                , tv_single_odds18, tv_double_odds18, tv_small_double_odds18, tv_big_double_odds18, tv_small_single_odds18, tv_big_single_odds18);
        InitOddsFactorText(getApp().getDignumbergame_30ball(), tv_red_odds30, tv_yelllow_odds30, tv_blue_odds30, tv_big_odds30, tv_small_odds30
                , tv_single_odds30, tv_double_odds30, tv_small_double_odds30, tv_big_double_odds30, tv_small_single_odds30, tv_big_single_odds30);

    }

    public void InitInterface() {
        //42球
        UpdateInterface(getApp().getDignumbergame_42ball(), true, tv_game_name42, tv_period42, tv_time42, tv_toumingdu42, rl_circle42, num_fragment42, num_linearlayout42);
        UpdateInterface(getApp().getDignumbergame_36ball(), true, tv_game_name36, tv_period36, tv_time36, tv_toumingdu36, rl_circle36, num_fragment36, num_linearlayout36);
        UpdateInterface(getApp().getDignumbergame_24ball(), true, tv_game_name24, tv_period24, tv_time24, tv_toumingdu24, rl_circle24, num_fragment24, num_linearlayout24);
        UpdateInterface(getApp().getDignumbergame_12ball(), true, tv_game_name12, tv_period12, tv_time12, tv_toumingdu12, rl_circle12, num_fragment12, num_linearlayout12);
        UpdateInterface(getApp().getDignumbergame_18ball(), true, tv_game_name18, tv_period18, tv_time18, tv_toumingdu18, rl_circle18, num_fragment18, num_linearlayout18);
        UpdateInterface(getApp().getDignumbergame_30ball(), true, tv_game_name30, tv_period30, tv_time30, tv_toumingdu30, rl_circle30, num_fragment30, num_linearlayout30);


    }

    public void UpdateInterface() {
        //42球
        UpdateInterface(getApp().getDignumbergame_42ball(), false, tv_game_name42, tv_period42, tv_time42, tv_toumingdu42, rl_circle42, num_fragment42, num_linearlayout42);
        UpdateInterface(getApp().getDignumbergame_36ball(), false, tv_game_name36, tv_period36, tv_time36, tv_toumingdu36, rl_circle36, num_fragment36, num_linearlayout36);
        UpdateInterface(getApp().getDignumbergame_24ball(), false, tv_game_name24, tv_period24, tv_time24, tv_toumingdu24, rl_circle24, num_fragment24, num_linearlayout24);
        UpdateInterface(getApp().getDignumbergame_12ball(), false, tv_game_name12, tv_period12, tv_time12, tv_toumingdu12, rl_circle12, num_fragment12, num_linearlayout12);
        UpdateInterface(getApp().getDignumbergame_18ball(), false, tv_game_name18, tv_period18, tv_time18, tv_toumingdu18, rl_circle18, num_fragment18, num_linearlayout18);
        UpdateInterface(getApp().getDignumbergame_30ball(), false, tv_game_name30, tv_period30, tv_time30, tv_toumingdu30, rl_circle30, num_fragment30, num_linearlayout30);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_auto_game_hall;
    }

    @Override
    protected void onDestroy() {
        Log.i("LanjianTest", "AutoNumberActivity onRestart()");
        StopUpdateGameStatus();
        getApp().setHttpClient(null);
        super.onDestroy();


    }

    public void InitButtonClick() {
        rl_red42.setOnClickListener(new ButtonClick());
        rl_red36.setOnClickListener(new ButtonClick());
        rl_red24.setOnClickListener(new ButtonClick());
        rl_red12.setOnClickListener(new ButtonClick());
        rl_red18.setOnClickListener(new ButtonClick());
        rl_red30.setOnClickListener(new ButtonClick());

        rl_yellow42.setOnClickListener(new ButtonClick());
        rl_yellow36.setOnClickListener(new ButtonClick());
        rl_yellow24.setOnClickListener(new ButtonClick());
        rl_yellow12.setOnClickListener(new ButtonClick());
        rl_yellow18.setOnClickListener(new ButtonClick());
        rl_yellow30.setOnClickListener(new ButtonClick());

        rl_blue42.setOnClickListener(new ButtonClick());
        rl_blue24.setOnClickListener(new ButtonClick());
        rl_blue36.setOnClickListener(new ButtonClick());
        rl_blue12.setOnClickListener(new ButtonClick());
        rl_blue18.setOnClickListener(new ButtonClick());
        rl_blue30.setOnClickListener(new ButtonClick());

        rl_big42.setOnClickListener(new ButtonClick());
        rl_big36.setOnClickListener(new ButtonClick());
        rl_big24.setOnClickListener(new ButtonClick());
        rl_big12.setOnClickListener(new ButtonClick());
        rl_big18.setOnClickListener(new ButtonClick());
        rl_big30.setOnClickListener(new ButtonClick());

        rl_small42.setOnClickListener(new ButtonClick());
        rl_small36.setOnClickListener(new ButtonClick());
        rl_small24.setOnClickListener(new ButtonClick());
        rl_small12.setOnClickListener(new ButtonClick());
        rl_small18.setOnClickListener(new ButtonClick());
        rl_small30.setOnClickListener(new ButtonClick());

        rl_single42.setOnClickListener(new ButtonClick());
        rl_single36.setOnClickListener(new ButtonClick());
        rl_single24.setOnClickListener(new ButtonClick());
        rl_single12.setOnClickListener(new ButtonClick());
        rl_single18.setOnClickListener(new ButtonClick());
        rl_single30.setOnClickListener(new ButtonClick());

        rl_double42.setOnClickListener(new ButtonClick());
        rl_double36.setOnClickListener(new ButtonClick());
        rl_double24.setOnClickListener(new ButtonClick());
        rl_double12.setOnClickListener(new ButtonClick());
        rl_double18.setOnClickListener(new ButtonClick());
        rl_double30.setOnClickListener(new ButtonClick());

        rl_small_double42.setOnClickListener(new ButtonClick());
        rl_small_double36.setOnClickListener(new ButtonClick());
        rl_small_double24.setOnClickListener(new ButtonClick());
        rl_small_double12.setOnClickListener(new ButtonClick());
        rl_small_double18.setOnClickListener(new ButtonClick());
        rl_small_double30.setOnClickListener(new ButtonClick());

        rl_small_single42.setOnClickListener(new ButtonClick());
        rl_small_single36.setOnClickListener(new ButtonClick());
        rl_small_single24.setOnClickListener(new ButtonClick());
        rl_small_single12.setOnClickListener(new ButtonClick());
        rl_small_single18.setOnClickListener(new ButtonClick());
        rl_small_single30.setOnClickListener(new ButtonClick());

        rl_big_double42.setOnClickListener(new ButtonClick());
        rl_big_double36.setOnClickListener(new ButtonClick());
        rl_big_double24.setOnClickListener(new ButtonClick());
        rl_big_double12.setOnClickListener(new ButtonClick());
        rl_big_double18.setOnClickListener(new ButtonClick());
        rl_big_double30.setOnClickListener(new ButtonClick());

        rl_big_single42.setOnClickListener(new ButtonClick());
        rl_big_single36.setOnClickListener(new ButtonClick());
        rl_big_single24.setOnClickListener(new ButtonClick());
        rl_big_single12.setOnClickListener(new ButtonClick());
        rl_big_single18.setOnClickListener(new ButtonClick());
        rl_big_single30.setOnClickListener(new ButtonClick());

        btn_enter42.setOnClickListener(new ButtonClick());
        btn_enter36.setOnClickListener(new ButtonClick());
        btn_enter24.setOnClickListener(new ButtonClick());
        btn_enter12.setOnClickListener(new ButtonClick());
        btn_enter18.setOnClickListener(new ButtonClick());
        btn_enter30.setOnClickListener(new ButtonClick());

    }

    //
    public void showBetDialog(final DigGameBean digGameBean, final int betType) {

        if (digGameBean.getCount_down() <= 0 || digGameBean.getCount_down() > (digGameBean.getOpenUrl() * 1000 * 60))
            return;
        context = AutoNumberActivity.this;
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
        switch (betType) {
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
                tv_betType.setText(getString(R.string.small) + " " + getString(R.string.even_lottery));
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 9:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.small) + " " + getString(R.string.odd_lottery));
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 10:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.big) + " " + getString(R.string.even_lottery));
                tv_odds.setText(digGameBean.getCombination_factor());
                break;
            case 11:
                tv_title.setText(R.string.combination);
                tv_betType.setText(getString(R.string.big) + " " + getString(R.string.odd_lottery));//qdadan
                tv_odds.setText(digGameBean.getCombination_factor());
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
//                    int input_bet_num = Integer.valueOf(strNumber).intValue();
//                    DecimalFormat df = new DecimalFormat("#.00");
                    tv_act_bet.setText(strNumber);
                } catch (Exception e) {
                }
            }
        });
        iv_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogshow.hide();
            }
        });
        //bet
        btn_confim.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              NumberGameBet(tv_bet_num.getText().toString().trim(), digGameBean, betType);

                                          }
                                      }
        );
        btn_cancel.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              dialogshow.hide();
                                          }
                                      }

        );
        alertdialog.setView(view);
        this.game_name=digGameBean.getGame_name();
        dialogshow = alertdialog.show();
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

    public void NumberGameBet(String betMoney, DigGameBean digGameBean, int betType) {
        try {
            if (betMoney == null || betMoney.length() == 0) {
                Toast.makeText(AutoNumberActivity.this, R.string.qingchashumu, Toast.LENGTH_LONG).show();
                return;
            }
            if (!"".equals(getApp().getUserInfo().getMoneyBalance().getBalance()) ||
                    (getApp().getUserInfo().getMoneyBalance().getBalance() != null)) {
                String balance = getApp().getUserInfo().getMoneyBalance().getBalance();
                double b_balance = Double.valueOf(balance);
                double d_betMoney = Double.valueOf(betMoney);
                if (b_balance < d_betMoney) {
                    dialogshow.hide();
                    Toast.makeText(AutoNumberActivity.this, R.string.yuebuzhu, Toast.LENGTH_LONG).show();
                    return;
                }
                boolean bBet = true;
                switch (betType) {
                    case 1://red
                    case 2:
                    case 3:

                        bBet = LimitBet(d_betMoney, digGameBean.getColor_minbet(), digGameBean.getColor_maxbet(), digGameBean.getColor_total(), digGameBean.getColor_bet_total());
                        break;
                    case 4:
                    case 5:

                        bBet = LimitBet(d_betMoney, digGameBean.getBigsmall_minbet(), digGameBean.getBigsmall_maxbet(), digGameBean.getBigsmall_total(), digGameBean.getBigsmall_bet_total());
                        break;
                    case 6:
                    case 7:
                        bBet = LimitBet(d_betMoney, digGameBean.getOddeven_mixbet(), digGameBean.getOddeven_maxbet(), digGameBean.getOddeven_total(), digGameBean.getOddeven_bet_total());

                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                        bBet = LimitBet(d_betMoney, digGameBean.getCombination_mixbet(), digGameBean.getCombination_maxbet(), digGameBean.getCombination_total(), digGameBean.getCombination_bet_total());

                        break;
                    default:
                        bBet = LimitBet(d_betMoney, digGameBean.getNumber_mixbet(), digGameBean.getNumber_maxbet(), digGameBean.getNumber_total(), digGameBean.getNumber_bet_total());

                        break;
                }
                //
                if (bBet) {
                    dialogshow.hide();
                    blockDialog.show();
                    BetNumberGame betNumberGame = new BetNumberGame();
                    betNumberGame.setBetType(betType);
                    betNumberGame.setBetMoney(betMoney);
                    betNumberGame.setDigGameBean(digGameBean);
                    Thread threadBetGame = new Thread(betNumberGame);
                    threadBetGame.start();
                }

            } else//ti shi yu e you wen ti
            {

            }
        } catch (Exception e) {
            Log.i("BetTest", "Error=" + e.toString());
        }
    }

    public boolean LimitBet(double betMoney, double minBet, double maxBet, double maxTotalBet, double totalBet) {
        Log.i("BetTest", "betMoney=" + betMoney + ",totalBet=" + totalBet + "，minBet=" + minBet + ",maxBet=" + maxBet + ",maxTotalBet=" + maxTotalBet);
        boolean returnValue = true;
        if (betMoney < minBet || betMoney > maxBet) {
            returnValue = false;
            Toast.makeText(this, this.getString(R.string.xiane) +
                            "[" + (new Double(minBet)).intValue() +
                            "-" + (new Double(maxBet)).intValue() + "]"
                    , Toast.LENGTH_LONG).show();

        } else if (betMoney + totalBet > maxTotalBet) {
            returnValue = false;
            Toast.makeText(this, this.getString(R.string.caoguobet)
                    , Toast.LENGTH_LONG).show();
        }
        return returnValue;
    }

    public void EnterSingleTable(DigGameBean digGameBean) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, digGameBean.getGame_name());
        AppTool.activiyJump(AutoNumberActivity.this, NumberGameSingleTableActivity.class, bundle);
    }

    private void InitBalls(int total, MyViewGroup myGroup, TextView selected_ball) {
        if (myGroup != null)
            myGroup.removeAllViews();
        for (int i = 1; i <= total; i++) {
            TextView textView = new TextView(mContext);
            textView.setText("" + i);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(10);
            textView.setTextColor(getResources().getColor(R.color.black));
            TextPaint tp = textView.getPaint();
            tp.setFakeBoldText(true);
            textView.setBackgroundResource(R.drawable.oval_white_graystroke_radius0);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ScreenUtil.dip2px(mContext, 14), ScreenUtil.dip2px(mContext, 14));
            myGroup.addView(textView, layoutParams);
        }
        myGroup.initLayout();
        selected_ball.setVisibility(View.GONE);
    }

    private void showBallAnimation(final String result, int total, final MyViewGroup mvg_ball, final View ground_line, final TextView selected_ball) {
        InitBalls(total, mvg_ball, selected_ball);

        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.rotating10);
        //设置动画时间

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                selected_ball.setVisibility(View.GONE);
                mvg_ball.updateLayout();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                slideView(selected_ball, 0.32, result, mvg_ball);
                mvg_ball.initLayout();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ground_line.startAnimation(animation);
    }

    public void slideView(final TextView view, final double xMul, String number, final MyViewGroup mvg_ball) {

        mvg_ball.removeViewAt(Integer.valueOf(number) - 1);//移除
        view.setText(number);
        view.setVisibility(View.VISIBLE);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(1000);
        //设置透明度渐变动画的持续时间
        TranslateAnimation animationY = new TranslateAnimation(0, 0, -view.getHeight(), 0);
        animationY.setInterpolator(new OvershootInterpolator());
        animationY.setDuration(1000);
        AnimationSet set = new AnimationSet(true);    //创建动画集对象
        set.addAnimation(alphaAnimation);       //添加位置变化动画
        set.addAnimation(animationY);           //添加透明度渐变动画
        set.setFillAfter(true);                 //停留在最后的位置
        set.setFillEnabled(true);
        //设置动画
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TranslateAnimation animationX = new TranslateAnimation(0, (float) (ScreenUtil.getScreenWidthPix(mContext) * xMul), 0, 0);
                animationX.setInterpolator(new LinearInterpolator());
                animationX.setFillAfter(true);                 //停留在最后的位置
                animationX.setFillEnabled(true);
                animationX.setDuration(3000);
                view.startAnimation(animationX);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(set);
        set.startNow();
    }

    public class UpdateGameStatus implements Runnable {
        public void run() {
            while (bGetNumberStatus) {
                try {
                    //   Log.i("LanjianTest","UpdateGameStatus");
                    //  getgamestate();
                    getApp().GetGameStatus(updateAllData, format, "AutoNumberActivity");
                    updateAllData = false;
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public class UpdateTimer implements Runnable {
        public void run() {
            while (bTimerStatus) {
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

    public class BetNumberGame implements Runnable {
        String betMoney;
        DigGameBean digGameBean;
        private int betType;

        public void setBetMoney(String betMoney) {
            this.betMoney = betMoney;
        }

        public void setDigGameBean(DigGameBean digGameBean) {
            this.digGameBean = digGameBean;
        }

        public void setBetType(int betType) {
            this.betType = betType;
        }

        public void run() {
            try {
                String playType = "";
                String type = "";
                String betUrl = "";
                Log.i("LanjianTestBet", "betType=" + betType + " betMoney=" + betMoney);
                String params = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getApp().getUserInfo().getUser_id() + "&session_id=" + getApp().getUserInfo().getSession_id() + "&from=App";
                switch (betType) {
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
                    default:
                        break;
                }
                params += "&get_bet=" + playType + "#" + type;
                String postBetMoney = String.format("%.3f", Double.parseDouble(betMoney));
                params += "#" + postBetMoney;
                switch (digGameBean.getGame_name()) {
                    case "42_Balls":
                        betUrl = WebSiteUrl.NumberGameBet42;
                        break;
                    case "36_Balls":
                        betUrl = WebSiteUrl.NumberGameBet36;
                        break;
                    case "24_Balls":
                        betUrl = WebSiteUrl.NumberGameBet24;
                        break;
                    case "12_Balls":
                        betUrl = WebSiteUrl.NumberGameBet12;
                        break;
                    case "18_Balls":
                        betUrl = WebSiteUrl.NumberGameBet18;
                        break;
                    case "30_Balls":
                        betUrl = WebSiteUrl.NumberGameBet30;
                        break;
                    default:
                        break;
                }
                Log.i("LanjianTestBet", "betUrl=" + betUrl + "?" + params);
                String betResults = getApp().getHttpClient().sendPost(betUrl, params);
                Log.i("LanjianTestBet", betResults);
                Gson gson = new Gson();
                NyBaseResponse<DigGameBetBean> orgData;
                orgData = gson.fromJson(betResults, new TypeToken<NyBaseResponse<DigGameBetBean>>() {
                }.getType());
                //   DigGameBetBean data = orgData.getData();
                if (orgData.getMsg().equals("1")) {
                    switch (betType) {
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
                        default:
                            digGameBean.setNumber_bet_total(digGameBean.getNumber_bet_total() + Double.parseDouble(postBetMoney));
                            break;
                    }
                    handlerTimer.sendEmptyMessage(SHOW_BET_SECCESS);
                } else {
                    switch (orgData.getMsg()) {
                        case "-1":
                            BetErrorMessage = AutoNumberActivity.this.getString(R.string.xiazhucuowu);
                            break;
                        case "-2":
                            BetErrorMessage = AutoNumberActivity.this.getString(R.string.xiazhuzuixiao);
                            break;
                        case "-3":
                            BetErrorMessage = AutoNumberActivity.this.getString(R.string.xiazhuzuida);
                            break;
                        case "-4":
                            BetErrorMessage = AutoNumberActivity.this.getString(R.string.xiazhucaoguo);
                            break;
                        case "-5":
                            BetErrorMessage = AutoNumberActivity.this.getString(R.string.betshibai);
                            break;
                        case "-6":
                            BetErrorMessage = AutoNumberActivity.this.getString(R.string.daojishiend);
                            break;
                        default:
                            break;
                    }
                    handlerTimer.sendEmptyMessage(SHOW_BET_ERROR);
                }
                orgData = null;
                gson = null;


            } catch (Exception e) {
                BetErrorMessage = "NetWorkError_001";
                handlerTimer.sendEmptyMessage(SHOW_BET_ERROR);
            }


        }

    }

    public class GetNumberGameResults implements Runnable {
        private String gameNumber;
        private DigGameBean digGameBean;

        public void setGameNumber(String gameNumber) {
            this.gameNumber = gameNumber;
        }

        public void setDigGameBean(DigGameBean digGameBean) {
            this.digGameBean = digGameBean;
        }


        public void run() {
            try {

                String resultUrl = "";
                //     if(digGameBean.getGame_name().equals("42_Balls"))
                //       Log.i("LanjianTestBet","gameName="+digGameBean.getGame_name()+",gameNumber="+digGameBean.getGame_perid());
                String params = "web_id=0" + "&user_id=" + getApp().getUserInfo().getUser_id() + "&session_id=" + getApp().getUserInfo().getSession_id();
                params += "&type1=" + 8;
                params += "&type2=" + digGameBean.getGame_type();
                resultUrl = WebSiteUrl.GetNumberGameResults;
                //    if(digGameBean.getGame_name().equals("42_Balls"))
                //      Log.i("LanjianTestBet","GetNumberGameResults="+resultUrl+"?"+params);
                String results = "";
                while (digGameBean.isbGetResults()) {
                    Thread.sleep(5000);
                    results = getApp().getHttpClient().sendPost(resultUrl, params);
                    //    if(digGameBean.getGame_name().equals("42_Balls"))
                    //      Log.i("LanjianTestBet",results);
                    Gson gson = new Gson();
                    NyBaseResponse<List<DigGameResultBean>> orgData;
                    orgData = gson.fromJson(results, new TypeToken<NyBaseResponse<List<DigGameResultBean>>>() {
                    }.getType());
                    List<DigGameResultBean> data = orgData.getData();
                    /////////拿到结果，线程停止运行
                    if (data != null && data.size() > 0) {
                        //    if(digGameBean.getGame_name().equals("42_Balls"))
                        //      Log.i("LanjianTestBet","GetNumberGameResults="+data.get(0).getResult() +",GameNumber="+data.get(0).getPeriod());
                        if (data.get(0).getPeriod().equals(gameNumber)) {
                            digGameBean.setbGetResults(false);
                            digGameBean.setGame_result(data.get(0).getResult());
                            //     if(digGameBean.getGame_name().equals("42_Balls"))
                            //       Log.i("LanjianTestBet","GetNumberGameResults="+data.get(0).getResult() +",Close="+digGameBean.getGame_name());
                            switch (digGameBean.getGame_name()) {
                                case "42_Balls":
                                    handlerTimer.sendEmptyMessage(SHOW_RESULTS_42);
                                    break;
                                case "36_Balls":
                                    handlerTimer.sendEmptyMessage(SHOW_RESULTS_36);
                                    break;
                                case "24_Balls":
                                    handlerTimer.sendEmptyMessage(SHOW_RESULTS_24);
                                    break;
                                case "12_Balls":
                                    handlerTimer.sendEmptyMessage(SHOW_RESULTS_12);
                                    break;
                                case "18_Balls":
                                    handlerTimer.sendEmptyMessage(SHOW_RESULTS_18);
                                    break;
                                case "30_Balls":
                                    handlerTimer.sendEmptyMessage(SHOW_RESULTS_30);
                                    break;
                            }

                        }

                    }
                    // data.get(0).getResult();
                    //   handlerTimer.sendEmptyMessage(SHOW_RESULTS);

                    data = null;
                    orgData = null;
                    gson = null;

                }


            } catch (Exception e) {
                //   BetErrorMessage = "NetWorkError_001";
                //    handlerTimer.sendEmptyMessage(SHOW_BET_ERROR);
                Log.i("LanjianTestBet", "GetNumberGameResults=Error," + digGameBean.getGame_name());
            }


        }

    }

    class ButtonClick implements View.OnClickListener {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.rl_red42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 1);
                    break;
                case R.id.rl_red36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 1);
                    break;
                case R.id.rl_red24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 1);
                    break;
                case R.id.rl_red12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 1);
                    break;
                case R.id.rl_red18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 1);
                    break;
                case R.id.rl_red30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 1);
                    break;

                case R.id.rl_yellow42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 2);
                    break;
                case R.id.rl_yellow36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 2);
                    break;
                case R.id.rl_yellow24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 2);
                    break;
                case R.id.rl_yellow12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 2);
                    break;
                case R.id.rl_yellow18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 2);
                    break;
                case R.id.rl_yellow30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 2);
                    break;

                case R.id.rl_blue42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 3);
                    break;
                case R.id.rl_blue36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 3);
                    break;
                case R.id.rl_blue24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 3);
                    break;
                case R.id.rl_blue12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 3);
                    break;
                case R.id.rl_blue18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 3);
                    break;
                case R.id.rl_blue30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 3);
                    break;

                case R.id.rl_big42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 4);
                    break;
                case R.id.rl_big36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 4);
                    break;
                case R.id.rl_big24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 4);
                    break;
                case R.id.rl_big12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 4);
                    break;
                case R.id.rl_big18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 4);
                    break;
                case R.id.rl_big30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 4);
                    break;

                case R.id.rl_small42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 5);
                    break;
                case R.id.rl_small36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 5);
                    break;
                case R.id.rl_small24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 5);
                    break;
                case R.id.rl_small12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 5);
                    break;
                case R.id.rl_small18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 5);
                    break;
                case R.id.rl_small30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 5);
                    break;

                case R.id.rl_single42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 7);
                    break;
                case R.id.rl_single36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 7);
                    break;
                case R.id.rl_single24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 7);
                    break;
                case R.id.rl_single12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 7);
                    break;
                case R.id.rl_single18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 7);
                    break;
                case R.id.rl_single30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 7);
                    break;

                case R.id.rl_double42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 6);
                    break;
                case R.id.rl_double36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 6);
                    break;
                case R.id.rl_double24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 6);
                    break;
                case R.id.rl_double12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 6);
                    break;
                case R.id.rl_double18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 6);
                    break;
                case R.id.rl_double30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 6);
                    break;

                case R.id.rl_small_double42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 8);
                    break;
                case R.id.rl_small_double36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 8);
                    break;
                case R.id.rl_small_double24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 8);
                    break;
                case R.id.rl_small_double12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 8);
                    break;
                case R.id.rl_small_double18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 8);
                    break;
                case R.id.rl_small_double30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 8);
                    break;

                case R.id.rl_small_single42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 9);
                    break;
                case R.id.rl_small_single36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 9);
                    break;
                case R.id.rl_small_single24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 9);
                    break;
                case R.id.rl_small_single12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 9);
                    break;
                case R.id.rl_small_single18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 9);
                    break;
                case R.id.rl_small_single30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 9);
                    break;

                case R.id.rl_big_double42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 10);
                    break;
                case R.id.rl_big_double36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 10);
                    break;
                case R.id.rl_big_double24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 10);
                    break;
                case R.id.rl_big_double12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 10);
                    break;
                case R.id.rl_big_double18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 10);
                    break;
                case R.id.rl_big_double30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 10);
                    break;

                case R.id.rl_big_single42:
                    showBetDialog(getApp().getDignumbergame_42ball(), 11);
                    break;
                case R.id.rl_big_single36:
                    showBetDialog(getApp().getDignumbergame_36ball(), 11);
                    break;
                case R.id.rl_big_single24:
                    showBetDialog(getApp().getDignumbergame_24ball(), 11);
                    break;
                case R.id.rl_big_single12:
                    showBetDialog(getApp().getDignumbergame_12ball(), 11);
                    break;
                case R.id.rl_big_single18:
                    showBetDialog(getApp().getDignumbergame_18ball(), 11);
                    break;
                case R.id.rl_big_single30:
                    showBetDialog(getApp().getDignumbergame_30ball(), 11);
                    break;

                case R.id.btn_enter42:
                    EnterSingleTable(getApp().getDignumbergame_42ball());
                    break;
                case R.id.btn_enter36:
                    EnterSingleTable(getApp().getDignumbergame_36ball());
                    break;
                case R.id.btn_enter24:
                    EnterSingleTable(getApp().getDignumbergame_24ball());
                    break;
                case R.id.btn_enter12:
                    EnterSingleTable(getApp().getDignumbergame_12ball());
                    break;
                case R.id.btn_enter18:
                    EnterSingleTable(getApp().getDignumbergame_18ball());
                    break;
                case R.id.btn_enter30:
                    EnterSingleTable(getApp().getDignumbergame_30ball());
                    break;

                default:
                    break;
            }
        }


    }


}
