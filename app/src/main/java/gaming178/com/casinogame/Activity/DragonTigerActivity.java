package gaming178.com.casinogame.Activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.apng.utils.FileUtils;
import com.apng.view.ApngImageView;
import com.apng.view.ApngLoader;
import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.autolayout.config.UseLandscape;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Activity.entity.ApngPlayBean;
import gaming178.com.casinogame.Activity.entity.GoodRoadDataBean;
import gaming178.com.casinogame.Bean.Baccarat;
import gaming178.com.casinogame.Bean.BetDetail;
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.casinogame.Bean.LiveInfoBean;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.BackgroudMuzicService;
import gaming178.com.casinogame.Util.BetUiHelper;
import gaming178.com.casinogame.Util.ChipShowHelper;
import gaming178.com.casinogame.Util.CountDownView;
import gaming178.com.casinogame.Util.FrontMuzicService;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.PopGoodRoad;
import gaming178.com.casinogame.Util.SkewTexView;
import gaming178.com.casinogame.Util.UIUtil;
import gaming178.com.casinogame.Util.VideoPlayer;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.BitmapTool;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.allinone.util.ToastUtils;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.myview.View.GridBackgroundView;
import gaming178.com.mylibrary.myview.miscwidgets.interpolator.BounceInterpolator;
import gaming178.com.mylibrary.myview.miscwidgets.interpolator.EasingType;
import gaming178.com.mylibrary.myview.miscwidgets.widget.Panel;

/**
 * Created by Administrator on 2016/4/11.
 */
public class DragonTigerActivity extends BaseActivity {
    Map<Boolean, Integer> selectedMap = new HashMap<>();
    Map<FrameLayout, ChipShowHelper> ChipMap = new HashMap<>();


    @OnClick(R2.id.iv_baccarat_change_table)
    public void clickTable(View v) {
        showChangeTable(v);
    }
    @BindView(R2.id.handle1)
    View handle1;
    @BindView(R2.id.tv_service_time)
    TextView serviceTime;
    @BindView(R2.id.tv_menu)
    TextView tvMenu;
    int chipCount = 0;
    View ll_poker_parent;
    @BindView(R2.id.tv_bottom_panel_left)
    TextView tv_point_dragon;
    @BindView(R2.id.tv_bottom_panel_right)
    TextView tv_point_tiger;
    @BindView(R2.id.bottomPanel1)
    Panel bottomPanel1;
    @BindView(R2.id.fl_baccarat_parent)
    View fl_baccarat_parent;
    private TextView shufflingTv;
    AdapterView lv_baccarat_chips;

    @BindView(R2.id.tv_table_bet_replay)
    TextView tvTableBetReplay;
    @BindView(R2.id.tv_table_bet_sure)
    TextView tvTableBetSure;
    @BindView(R2.id.tv_table_bet_cancel)
    TextView tvTableBetCancel;
    /* @BindView(R2.id.tv_table_bet_pol)
     TextView tvTableBetPol;*/
    @BindView(R2.id.leftPanel1)
    Panel leftPanel1;
    @BindView(R2.id.lv_table_pool)
    ListView lv_table_pool;
    @BindView(R2.id.lv_person_bet_info)
    ListView lv_person_bet_info;
    @BindView(R2.id.lv_table_bet_limit_red)
    ListView lvTableBetLimitRed;
    @BindView(R2.id.tv_table_timer)
    TextView tv_table_timer;
    @BindView(R2.id.countdown_view)
    CountDownView countdown_view;
    @BindView(R2.id.ll_result)
    LinearLayout ll_result;
    @BindView(R2.id.tv_dragon_result)
    TextView tv_dragon_result;
    @BindView(R2.id.tv_tiger_result)
    TextView tv_tiger_result;

    @BindView(R2.id.ll_banker_ask)
    View ll_banker_ask;
    @BindView(R2.id.ll_player_ask)
    View ll_player_ask;
    @BindView(R2.id.rl_good_road)
    View rl_good_road;
    @BindView(R2.id.tv_good_road_count)
    TextView tv_good_road_count;

    @BindView(R2.id.layout1)
    HorizontalScrollView layout1;
    @BindView(R2.id.baccarat_background_gridlayout1)
    GridBackgroundView baccarat_background_gridlayout1;
    @BindView(R2.id.baccarat_background_gridlayout1_big)
    GridBackgroundView baccarat_background_gridlayout1_big;
    @BindView(R2.id.ll_big_road_parent2)
    LinearLayout ll_big_road_parent2;
    @BindView(R2.id.ll_small_road_parent)
    LinearLayout ll_small_road_parent;
    @BindView(R2.id.fl_big_road1)
    View fl_big_road1;

    @BindView(R2.id.hsv_small_road_1)
    HorizontalScrollView hsv_small_road_1;
    View fl_small_road_parent1;
    GridBackgroundView smallway_item1;
    GridBackgroundView smallway_item1_big;
    @BindView(R2.id.hsv_small_road_2)
    HorizontalScrollView hsv_small_road_2;
    FrameLayout fl_small_road_parent2;
    GridBackgroundView smallway_item2;
    GridBackgroundView smallway_item2_big;
    @BindView(R2.id.hsv_small_road_3)
    HorizontalScrollView hsv_small_road_3;
    View fl_small_road_parent3;
    GridBackgroundView smallway_item3;
    GridBackgroundView smallway_item3_big;

    @BindView(R2.id.layout2)
    View layout2;
    @BindView(R2.id.baccarat_background_gridlayout2)
    GridBackgroundView baccarat_background_gridlayout2;
    @BindView(R2.id.baccarat_background_gridlayout2_big)
    GridBackgroundView baccarat_background_gridlayout2_big;

    @BindView(R2.id.tv_ask1)
    TextView tv_ask1;
    @BindView(R2.id.tv_ask2)
    TextView tv_ask2;

    List<GoodRoadDataBean> goodRoadDataBeenList = new ArrayList<>();
    List<Integer> otherTableIdList = new ArrayList<>();

    List<ApngPlayBean> apngPlayBeanList = new ArrayList<>();
    private TextView tv_table_game_number;
    private TextView tv_table_game_number1;
    private VideoPlayer mPreview;
    private String path;
    private boolean isFirstBet =true;

    private boolean personInfoShowAble = false;
    static boolean isActive = false;

    private int tableId = 0;
    private int dragonTigerTimer = 0;
    AdapterViewContent<LiveInfoBean> contentPool = null;
    AdapterViewContent<LiveInfoBean> contentInfo = null;
    AdapterViewContent<String> contentBetPool = null;
    private String betType = "Banker";
    private float density;
    private boolean bUpdateRoad = true;
    private int chooseChip = 0;

    private boolean bBetSucess = false;

    private int betTimeCount = 0;
    private GridLayout baccarat_head_road;
    private GridLayout baccarat_big_road;
    private GridLayout baccarat_bigeyes_road;
    private GridLayout baccarat_smalleyes_road;
    private GridLayout baccarat_roach_road;
    private TextView tv_baccarat_shoe_number;
    private TextView tv_baccarat_total_number;
    private TextView tv_baccarat_banker_number;
    private TextView tv_baccarat_player_number;
    private TextView tv_baccarat_tie_number;
    private TextView tv_baccarat_bp_number;
    private TextView tv_baccarat_pp_number;
    private TextView btn_limit;
    private ImageView iv_poker_player1;
    ImageView iv_poker_banker1;


    private String gameNumber = "";
    private FrameLayout fl_dragon;
    private FrameLayout fl_tiger;
    private FrameLayout fl_tie;
    private FrameLayout fl_dragon_odd;
    private FrameLayout fl_dragon_even;
    private FrameLayout fl_dragon_red;
    private FrameLayout fl_dragon_black;
    private FrameLayout fl_tiger_odd;
    private FrameLayout fl_tiger_even;
    private FrameLayout fl_tiger_red;
    private FrameLayout fl_tiger_black;

    private ImageView iv_dragon;
    private ImageView iv_tiger;
    private ImageView iv_tie;
    private ImageView iv_dragon_odd;
    private ImageView iv_dragon_even;
    private ImageView iv_dragon_red;
    private ImageView iv_dragon_black;
    private ImageView iv_tiger_odd;
    private ImageView iv_tiger_even;
    private ImageView iv_tiger_red;
    private ImageView iv_tiger_black;


    private List<BetDetail> listBetDetail = new ArrayList<BetDetail>();


    private AnimationDrawable animationDrawablePokerBanker;
    private AnimationDrawable animationDrawablePokerPlayer;


    private UpdateStatus updateStatus = null;
    private Thread threadStatus = null;
    private boolean bGetStatus = true;
    //得到结果
    private boolean isResultEnd = false;
    private boolean isBottomOpen = false;
    private VideoHelper videoHelper;
    @BindView(R2.id.ll_chip_parent)
    View ll_chip_parent;
    @BindView(R2.id.ll_bet_btn_parent)
    View ll_bet_btn_parent;
    private boolean stateinit;
    private boolean allClose;
    private float xSkizeB1 = 0.14f;
    private float xSkizeP1 = 0.20f;
    private int animationHeight = -300;
    private long animationTime = 500;
    private String tableName = "";
    private float bankerX;
    private float playerX;

    public void clickDragon(View view) {
        if (getNumberBetMoney("Tiger") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("Tiger") > 0) {
            Toast.makeText(mContext, R.string.show_limit_dragon_tiger, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "Dragon";

        clickChipBet(number, "Dragon", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinDragonTigerBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxDragonTigerBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickTiger(View view) {
        if (getNumberBetMoney("Dragon") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("Dragon") > 0) {
            Toast.makeText(mContext, R.string.show_limit_dragon_tiger, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "Tiger";
        clickChipBet(number, "Tiger", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinDragonTigerBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxDragonTigerBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickTie(final View f) {
        String number = "Tie";
        clickChipBet(number, "Tie", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTieBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTieBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );

    }

    public void clickDragonOdd(View view) {
        int gameNumber = Integer.parseInt(mAppViewModel.getDragonTiger01().getGameNumber());
        if (gameNumber > 30) {
            handler.sendEmptyMessage(HandlerCode.DRAGON_TIGER_GAME_NUMBER_LIMIT);
            return;
        }
        if (getNumberBetMoney("DragonEven") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("DragonEven") > 0) {
            Toast.makeText(mContext, R.string.show_limit_odd_even, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "DragonOdd";
        clickChipBet(number, "DragonOdd", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickDragonEven(View view) {
        int gameNumber = Integer.parseInt(mAppViewModel.getDragonTiger01().getGameNumber());
        if (gameNumber > 30) {
            handler.sendEmptyMessage(HandlerCode.DRAGON_TIGER_GAME_NUMBER_LIMIT);
            return;
        }
        if (getNumberBetMoney("DragonOdd") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("DragonOdd") > 0) {
            Toast.makeText(mContext, R.string.show_limit_odd_even, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "DragonEven";
        clickChipBet(number, "DragonEven", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickDragonRed(View view) {
        int gameNumber = Integer.parseInt(mAppViewModel.getDragonTiger01().getGameNumber());
        if (gameNumber > 30) {
            handler.sendEmptyMessage(HandlerCode.DRAGON_TIGER_GAME_NUMBER_LIMIT);
            return;
        }
        if (getNumberBetMoney("DragonBlack") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("DragonBlack") > 0) {
            Toast.makeText(mContext, R.string.show_limit_red_black, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "DragonRed";
        clickChipBet(number, "DragonRed", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickDragonBlack(View view) {
        int gameNumber = Integer.parseInt(mAppViewModel.getDragonTiger01().getGameNumber());
        if (gameNumber > 30) {
            handler.sendEmptyMessage(HandlerCode.DRAGON_TIGER_GAME_NUMBER_LIMIT);
            return;
        }
        if (getNumberBetMoney("DragonRed") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("DragonRed") > 0) {
            Toast.makeText(mContext, R.string.show_limit_red_black, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "DragonBlack";
        clickChipBet(number, "DragonBlack", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickTigerOdd(View view) {
        int gameNumber = Integer.parseInt(mAppViewModel.getDragonTiger01().getGameNumber());
        if (gameNumber > 30) {
            handler.sendEmptyMessage(HandlerCode.DRAGON_TIGER_GAME_NUMBER_LIMIT);
            return;
        }
        if (getNumberBetMoney("TigerEven") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("TigerEven") > 0) {
            Toast.makeText(mContext, R.string.show_limit_odd_even, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "TigerOdd";
        clickChipBet(number, "TigerOdd", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickTigerEven(View view) {
        int gameNumber = Integer.parseInt(mAppViewModel.getDragonTiger01().getGameNumber());
        if (gameNumber > 30) {
            handler.sendEmptyMessage(HandlerCode.DRAGON_TIGER_GAME_NUMBER_LIMIT);
            return;
        }
        if (getNumberBetMoney("TigerOdd") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("TigerOdd") > 0) {
            Toast.makeText(mContext, R.string.show_limit_odd_even, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "TigerEven";
        clickChipBet(number, "TigerEven", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickTigerRed(View view) {
        int gameNumber = Integer.parseInt(mAppViewModel.getDragonTiger01().getGameNumber());
        if (gameNumber > 30) {
            handler.sendEmptyMessage(HandlerCode.DRAGON_TIGER_GAME_NUMBER_LIMIT);
            return;
        }
        if (getNumberBetMoney("TigerBlack") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("TigerBlack") > 0) {
            Toast.makeText(mContext, R.string.show_limit_red_black, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "TigerRed";
        clickChipBet(number, "TigerRed", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickTigerBlack(View view) {
        int gameNumber = Integer.parseInt(mAppViewModel.getDragonTiger01().getGameNumber());
        if (gameNumber > 30) {
            handler.sendEmptyMessage(HandlerCode.DRAGON_TIGER_GAME_NUMBER_LIMIT);
            return;
        }
        if (getNumberBetMoney("TigerRed") > 0 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney("TigerRed") > 0) {
            Toast.makeText(mContext, R.string.show_limit_red_black, Toast.LENGTH_LONG).show();
            return;
        }
        String number = "TigerBlack";
        clickChipBet(number, "TigerBlack", chooseChip, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(number),
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), getFrameLayout(number)
        );
    }

    public void clickLeftPanel(View view) {
        if (leftPanel1.isOpen()) {
            leftPanel1.setOpen(false, true);
        } else
            leftPanel1.setOpen(true, true);
    }

    public class UpdateStatus implements Runnable, UseLandscape {
        int iError = 0;

        public void run() {
            while (bGetStatus) {
                try {
                    //      Log.i(WebSiteUrl.Tag, "UpdateStatus = " );
                    handler.sendEmptyMessage(HandlerCode.UPDATE_STATUS);
                    Thread.sleep(1050);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private BackLobby backLobby = null;
    private Thread threadBackLobby = null;

    public class BackLobby implements Runnable {
        int iError = 0;

        public void run() {
            try {
                Thread.sleep(1000);
                handler.sendEmptyMessage(HandlerCode.SHOW_BACK_LOBBY);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    private UpdateBetMoney updateBetMoney = null;
    private Thread threadUpdateBetMoney = null;

    public class UpdateBetMoney implements Runnable {
        public void run() {

            try {
                Thread.sleep(1500);//必须延迟2秒，否则下注信息先得到的话，后面倒计时开始的时候会被清除掉
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                        + "&Xhid=" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getShoeNumber() + "&Blid=" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber() +
                        "&Xh=" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LH_BET_MONEY_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateBetMoney params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateBetMoney = " + strRes);

                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 10) {
                        if ("".equals(strInfo[2]) || strInfo[2] == null)
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().setAllBetMoney(0);
                        else {
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().setAllBetMoney((int) Double.parseDouble(strInfo[2]));
                            if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getAllBetMoney() > 0)
                                bBetSucess = true;
                        }

                        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getBetDetail().clear();

                        saveBetInformation("Dragon", strInfo[4], true);
                        saveBetInformation("Tiger", strInfo[3], true);
                        saveBetInformation("Tie", strInfo[5], true);
                        saveBetInformation("DragonOdd", strInfo[10], true);
                        saveBetInformation("DragonEven", strInfo[11], true);
                        saveBetInformation("DragonRed", strInfo[12], true);
                        saveBetInformation("DragonBlack", strInfo[13], true);
                        saveBetInformation("TigerEven", strInfo[7], true);
                        saveBetInformation("TigerOdd", strInfo[6], true);
                        saveBetInformation("TigerRed", strInfo[8], true);
                        saveBetInformation("TigerBlack", strInfo[9], true);
                        handler.sendEmptyMessage(HandlerCode.SHOW_BET_MONEY);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private DrangonTigerBet drangonTigerBet = null;
    private Thread threadDrangonTigerBet = null;

    public class DrangonTigerBet implements Runnable {
        public DrangonTigerBet(DtBetType type) {
            DragonTigerActivity.this.type = type;

        }

        public void run() {
            if (isCanbet) {
                isCanbet = false;
                try {
                    String Dragon = "0";
                    String Tiger = "0";
                    String Tie = "0";
                    String DragonOdd = "0";
                    String DragonEven = "";
                    String DragonRed = "";
                    String DragonBlack = "";
                    String TigerEven = "";
                    String TigerOdd = "";
                    String TigerRed = "";
                    String TigerBlack = "0";

                    for (int i = 0; i < listBetDetail.size(); i++) {
                        if (listBetDetail.get(i).getMoney() > 0)
                            switch (listBetDetail.get(i).getType()) {
                                case "Dragon":
                                    if (type == DtBetType.Dragon || type == DtBetType.All)
                                        Dragon = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "Tiger":
                                    if (type == DtBetType.Tiger || type == DtBetType.All)
                                        Tiger = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "Tie":
                                    if (type == DtBetType.Tie || type == DtBetType.All)
                                        Tie = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "DragonOdd":
                                    if (type == DtBetType.DragonOdd || type == DtBetType.All)
                                        DragonOdd = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "DragonEven":
                                    if (type == DtBetType.DragonEven || type == DtBetType.All)
                                        DragonEven = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "DragonRed":
                                    if (type == DtBetType.DragonRed || type == DtBetType.All)
                                        DragonRed = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "DragonBlack":
                                    if (type == DtBetType.DragonBlack || type == DtBetType.All)
                                        DragonBlack = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "TigerEven":
                                    if (type == DtBetType.TigerEven || type == DtBetType.All)
                                        TigerEven = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "TigerOdd":
                                    if (type == DtBetType.TigerOdd || type == DtBetType.All)
                                        TigerOdd = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "TigerRed":
                                    if (type == DtBetType.TigerRed || type == DtBetType.All)
                                        TigerRed = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;
                                case "TigerBlack":
                                    if (type == DtBetType.TigerBlack || type == DtBetType.All)
                                        TigerBlack = "" + (listBetDetail.get(i).getMoney() - mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getNumberBetMoney(listBetDetail.get(i).getNumber()));
                                    break;

                            }

                    }
                    if ("".equals(TigerBlack))
                        TigerBlack = "0";
                    if ("".equals(TigerRed))
                        TigerRed = "0";
                    if ("".equals(TigerOdd))
                        TigerOdd = "0";
                    if ("".equals(TigerEven))
                        TigerEven = "0";
                    if ("".equals(DragonBlack))
                        DragonBlack = "0";
                    if ("".equals(DragonRed))
                        DragonRed = "0";

                    if ("".equals(DragonEven))
                        DragonEven = "0";
                    if ("".equals(DragonOdd))
                        DragonOdd = "0";
                    if ("".equals(Dragon))
                        Dragon = "0";
                    if ("".equals(Tiger))
                        Tiger = "0";
                    if ("".equals(Tie))
                        Tie = "0";


                    String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                            + "&Xhid=" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getShoeNumber() + "&Blid=" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber()
                            + "&Xh=" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet()
                            + "&Areaid=" + mAppViewModel.getAreaId() + "&Serial=" + mAppViewModel.getSerialId() + "&Hl=1"
                            + "&Dragon=" + Dragon + "&Tiger=" + Tiger + "&Tie=" + Tie + "&DragonOdd=" + DragonOdd + "&DragonEven=" + DragonEven
                            + "&DragonRed=" + DragonRed + "&DragonBlack=" + DragonBlack
                            + "&TigerRed=" + TigerRed + "&TigerBlack=" + TigerBlack
                            + "&TigerOdd=" + TigerOdd + "&TigerEven=" + TigerEven;

                    String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LH_BET_URL, params);
                    Log.i(WebSiteUrl.Tag, "DrangonTigerBet params= " + params);
                    Log.i(WebSiteUrl.Tag, "DrangonTigerBet = " + strRes);
                    String strInfo[] = strRes.split("#");
                    if (strRes.startsWith("Results=ok")) {
                        if (strInfo.length >= 10) {
                            if (isFirstBet){
                                isFirstBet =false;
                            }
                            mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                            double resMoney = 0;
                            //清除之前的下注记录
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetRepeatInformation().Init();


                            resMoney = Double.parseDouble(strInfo[2]);
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().setAllBetMoney((int) resMoney);
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetRepeatInformation().setAllBetMoney((int) resMoney);
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getBetDetail().clear();
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetRepeatInformation().getBetDetail().clear();

                            saveBetInformation("Dragon", strInfo[4], true);
                            saveBetInformation("Tiger", strInfo[3], true);
                            saveBetInformation("Tie", strInfo[5], true);
                            saveBetInformation("DragonOdd", strInfo[10], true);
                            saveBetInformation("DragonEven", strInfo[11], true);
                            saveBetInformation("DragonRed", strInfo[12], true);
                            saveBetInformation("DragonBlack", strInfo[13], true);
                            saveBetInformation("TigerEven", strInfo[7], true);
                            saveBetInformation("TigerOdd", strInfo[6], true);
                            saveBetInformation("TigerRed", strInfo[8], true);
                            saveBetInformation("TigerBlack", strInfo[9], true);


                            //提示下注成功,清除下注信息
                            bBetSucess = true;
                            betTimeCount = 0;
                            handler.sendEmptyMessage(HandlerCode.SHOW_BET_SUCCESS);


                        }
                    } else {//提示下注失败
                        handler.sendEmptyMessage(HandlerCode.SHOW_BET_ERROR);
                    }
                    isCanbet = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(HandlerCode.THREAD_ERROR);
                    isCanbet = true;
                }
            }
        }
    }

    private UpdateRoad updateRoad = null;
    private Thread threadUpdateRoad = null;

    public class UpdateRoad implements Runnable {
        public void run() {

            try {
                if (bUpdateRoad == false)
                    Thread.sleep(3000);
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LH_LUZI_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateRoad params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateRoad = " + strRes);
                String strInfo[] = strRes.split("\\|");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoad(strInfo[1]);
                        isActive = true;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private UpdateGameNumber updateGameNumber = null;
    private Thread threadUpdateGameNumber = null;

    public class UpdateGameNumber implements Runnable {
        public void run() {

            try {
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LH_TABLE_GAMENUM, params);
                Log.i(WebSiteUrl.Tag, "UpdateGameNumber params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateGameNumber = " + strRes);
                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setShoeNumber(strInfo[1]);
                        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setGameNumber(strInfo[2]);
                        handler.sendEmptyMessage(HandlerCode.UPDATE_GAME_NUMBER);
                    }
                } else
                    handler.sendEmptyMessage(HandlerCode.UPDATE_GAME_NUMBER_ERROR);
            } catch (Exception e) {
                e.printStackTrace();
                handler.sendEmptyMessage(HandlerCode.UPDATE_GAME_NUMBER_ERROR);
            }

        }
    }

    private UpdateWonMoney updateWonMoney = null;
    private Thread threadUpdateWonMoney = null;

    public class UpdateWonMoney implements Runnable {
        public void run() {

            try {
                Thread.sleep(2000);
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                        + "&Xhid=" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getShoeNumber() + "&Blid=" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber() +
                        "&Xh=" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LH_WON_MONEY_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateWonMoney params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateWonMoney = " + strRes);

                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                        if (dragonTigerTimer==0&&mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5){
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setWonMoney(Double.parseDouble(strInfo[2]));
                        }else {
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setWonMoney(0);
                        }
                        mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                        if (isShowWinLose){
                            handler.sendEmptyMessage(HandlerCode.SHOW_WIN_LOSS);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private int currentShufflingStatus = 1;//1是没有洗牌2是洗牌中
    private int finalShufflingStatus = -1;

    public void updateTimer() {
        if (mAppViewModel.getDragonTiger01().getGameStatus() == 8) {
            shufflingTv.setVisibility(View.VISIBLE);
            currentShufflingStatus = 2;
        } else {
            shufflingTv.setVisibility(View.GONE);
            currentShufflingStatus = 1;
        }
        if (currentShufflingStatus != finalShufflingStatus) {
            setChip();
            finalShufflingStatus = currentShufflingStatus;
        }
        //    Log.i(WebSiteUrl.Tag,"updateTimer timer="+mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getTimer()+",status="+mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus());
        if (dragonTigerTimer == 0 && mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 1 && mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getTimer() > 0) {
         /*   if(updateGameNumber == null){
                updateGameNumber = new UpdateGameNumber();
                threadUpdateGameNumber = new Thread(updateGameNumber);
                threadUpdateGameNumber.start();
            }*/
            if (!gameNumber.equals(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber())) {
                gameNumber = mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber();
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).Init();
                clearAllChips();
                hidePoker();
                dragonTigerTimer = mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getTimer();
                countdown_view.setCountdownTime(dragonTigerTimer);
                countdown_view.startCountDown();
                bUpdateRoad = true;
                bBetSucess = false;
                betTimeCount++;
                if (betTimeCount == 6)//跳转到大厅
                {
                    ToastUtils.showBackToast(mContext, getString(R.string.friendly_message), getString(R.string.show_back_lobby));
                    backLobby = new BackLobby();
                    threadBackLobby = new Thread(backLobby);
                    threadBackLobby.start();
                }else if (betTimeCount==4){
                    ToastUtils.showBackToast(mContext, getString(R.string.friendly_message), getString(R.string.three_no_bet));
                }
                tvTableBetSure.setEnabled(true);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, 16, componentFront, mContext, mAppViewModel.getFrontVolume());
                tv_table_game_number.setText(tableName + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber());
                tv_table_game_number1.setText(tv_table_game_number.getText().toString());
                tv_baccarat_shoe_number.setText("" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber());

            }

            //需要调用一下gamegum.jsp接口，更新局号
            if (isBottomOpen)//正好在发牌的时候锁频，再次打开屏幕的时候要先隐藏
                hidePoker();


        } else if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5)//延迟3秒调用路子接口
        {
            showPoker();
            if (bUpdateRoad){
                bUpdateRoad = false;
                //更新路子
//                updateRoad = new UpdateRoad();
//                threadUpdateRoad = new Thread(updateRoad);
//                threadUpdateRoad.start();
                //检查输赢
                isShowWinLose = true;
                updateWonMoney = new UpdateWonMoney();
                threadUpdateWonMoney = new Thread(updateWonMoney);
                threadUpdateWonMoney.start();

                showResultsOnUI();
//                tablePop.setTablesData(afbApp, games);
            }

        } else if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 2) {
            if (listBetDetail.size() > 0) {
                Log.i(WebSiteUrl.Tag, "clearNoBetChip()");
                clearNoBetChip();
            }

            showPoker();
            if (tvTableBetSure != null && tvTableBetSure.isEnabled()) {
                tvTableBetSure.setEnabled(false);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_NOMOREBETS, 14, componentFront, mContext, mAppViewModel.getFrontVolume());
            }


        }
       /* if (!gameNumber.equals(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber())) {
            clearAllChips();
        }*/
        if ("0".equals(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber())){
            tv_table_game_number.setText(tableName + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getShoeNumber() + " - 0");
            tv_table_game_number1.setText(tv_table_game_number.getText().toString());
        }

    }

    boolean isCanShowChip = true;
    boolean isCanHideChip = true;
    boolean isCanShowResult = true;
    boolean isCanHideResult = true;

    public void updateInterface() {
        if (dragonTigerTimer > 0 && mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() != 2) {
            for (int i = 0; i < apngPlayBeanList.size(); i++) {
                if (apngPlayBeanList.get(i).getApngImageView().getVisibility()==View.VISIBLE){
                    apngPlayBeanList.get(i).getApngImageView().stop();
                    apngPlayBeanList.get(i).getApngImageView().setVisibility(View.INVISIBLE);
                    break;
                }
            }
            Drawable dragonBackground = iv_dragon.getBackground();
            Drawable tieBackground = iv_tie.getBackground();
            Drawable tigerBackground = iv_tiger.getBackground();
            if (dragonBackground!=null||tieBackground!=null||tigerBackground!=null){
                iv_dragon.setBackgroundResource(0);
                iv_dragon_odd.setBackgroundResource(0);
                iv_dragon_even.setBackgroundResource(0);
                iv_dragon_red.setBackgroundResource(0);
                iv_dragon_black.setBackgroundResource(0);
                iv_tiger.setBackgroundResource(0);
                iv_tiger_odd.setBackgroundResource(0);
                iv_tiger_even.setBackgroundResource(0);
                iv_tiger_red.setBackgroundResource(0);
                iv_tiger_black.setBackgroundResource(0);
                iv_tie.setBackgroundResource(0);
                animationDragon.stop();
                animationDragon.selectDrawable(0);
                animationTiger.stop();
                animationTiger.selectDrawable(0);
            }
            if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                final int heightPixels = WidgetUtil.getScreenHeight(this);
                final int[] location2 = new int[2] ;
                lv_baccarat_chips.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                int[] location3 = new int[2] ;
                ll_result.getLocationOnScreen(location3);//获取在整个屏幕内的绝对坐标
                if (location3[1]<heightPixels&&isCanHideResult&&mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 1){
                    isCanHideResult = false;
                    WidgetUtil.chipTranslateAnimation(ll_result, ScreenUtil.dip2px(mContext, -46), 0, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideResult = true;
                            if (location2[1]>=heightPixels&&isCanShowChip&&mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 1){
                                isCanShowChip =false;
                                WidgetUtil.chipTranslateAnimation(lv_baccarat_chips, 0,ScreenUtil.dip2px(mContext, -52), new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        isCanShowChip = true;
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {
                                        isCanShowChip = true;
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            isCanHideResult = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }else {
                    if (location2[1]>=heightPixels&&isCanShowChip&&mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 1){
                        isCanShowChip =false;
                        WidgetUtil.chipTranslateAnimation(lv_baccarat_chips, 0,ScreenUtil.dip2px(mContext, -52), new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isCanShowChip = true;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                isCanShowChip = true;
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
                    }
                }
            }else {
                int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(this);
                int[] location2 = new int[2] ;
                ll_result.getLocationOnScreen(location2);
                if (location2[0]==0&&isCanHideResult&&mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 1){
                    isCanHideResult = false;
                    WidgetUtil.chipPortraitTranslateAnimation(ll_result, 0, portraitScreenWidth, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideResult = true;
                            int[] location = new int[2] ;
                            lv_baccarat_chips.getLocationOnScreen(location);
                            int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(DragonTigerActivity.this);
                            if (location[0]>=portraitScreenWidth&&isCanShowChip&&mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 1){
                                isCanShowChip =false;
                                WidgetUtil.chipPortraitTranslateAnimation(lv_baccarat_chips, portraitScreenWidth, 0, new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        isCanShowChip = true;
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {
                                        isCanShowChip = true;
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            isCanHideResult = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }else {
                    int[] location = new int[2] ;
                    lv_baccarat_chips.getLocationOnScreen(location);
                    if (location[0]>=portraitScreenWidth&&isCanShowChip&&mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 1){
                        isCanShowChip =false;
                        WidgetUtil.chipPortraitTranslateAnimation(lv_baccarat_chips, portraitScreenWidth, 0, new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isCanShowChip = true;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                isCanShowChip = true;
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
                    }
                }
            }
            dragonTigerTimer--;
            tv_table_timer.setText("" + dragonTigerTimer);
//            if (dragonTigerTimer == 10) {
//                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_TIMER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
//            }
            if (dragonTigerTimer < 6) {
                tv_table_timer.setTextColor(getResources().getColor(R.color.red));
                mAppViewModel.startFrontMuzicService("TIMER", 1, componentFront, mContext, mAppViewModel.getFrontVolume());
            } else
                tv_table_timer.setTextColor(getResources().getColor(R.color.white));
            //    tv_table_timer.setTextColor(getResources().getColor(R.color.green500));
            //    Log.i(WebSiteUrl.Tag,"dragonTigerTimer = "+dragonTigerTimer);
            // tv_table_timer.setTextSize(18);
            //

        } else {
            if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                final int heightPixels = WidgetUtil.getScreenHeight(this);
                int[] location2 = new int[2] ;
                lv_baccarat_chips.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                final int[] location3 = new int[2] ;
                ll_result.getLocationOnScreen(location3);//获取在整个屏幕内的绝对坐标
                if (location2[1]<heightPixels&&isCanHideChip&&(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 2||mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5)){
                    isCanHideChip =false;
                    WidgetUtil.chipTranslateAnimation(lv_baccarat_chips, ScreenUtil.dip2px(mContext, -52),0, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideChip = true;
                            if (location3[1]>=heightPixels&&isCanShowResult&&(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 2||mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5)){
                                isCanShowResult=false;
                                WidgetUtil.chipTranslateAnimation(ll_result, 0, ScreenUtil.dip2px(mContext, -46), new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        isCanShowResult=true;
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {
                                        isCanShowResult=true;
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            isCanHideChip = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }else {
                    if (location3[1]>=heightPixels&&isCanShowResult&&(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 2||mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5)){
                        isCanShowResult=false;
                        WidgetUtil.chipTranslateAnimation(ll_result, 0, ScreenUtil.dip2px(mContext, -46), new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                isCanShowResult=true;
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                isCanShowResult=true;
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        });
                    }
                }
            }else {
                int[] location = new int[2] ;
                lv_baccarat_chips.getLocationOnScreen(location);
                int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(this);
                if (location[0]==0&&isCanHideChip&&(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 2||mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5)){
                    isCanHideChip =false;
                    WidgetUtil.chipPortraitTranslateAnimation(lv_baccarat_chips, 0, portraitScreenWidth, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideChip = true;
                            int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(DragonTigerActivity.this);
                            if (isCanShowResult&&(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 2||mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5)){
                                isCanShowResult = false;
                                WidgetUtil.chipPortraitTranslateAnimation(ll_result, portraitScreenWidth,0, new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        if (ll_result!=null){
                                            ll_result.setVisibility(View.VISIBLE);
                                        }
                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        isCanShowResult = true;
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {
                                        isCanShowResult = true;
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            isCanHideChip = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
            }
            dragonTigerTimer = 0;
            tv_table_timer.setText("" + dragonTigerTimer);
        }
        String serverTimer = mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getServerTime();
        String time = "";
        if (serverTimer != null && serverTimer.indexOf("-") > 0)
            time = "GMT+7  " + serverTimer.substring(serverTimer.indexOf("-") + 1, serverTimer.length());

        tv_time.setText(time);
        setToolbarAndSet(time, usName);
        updateTablePool();
        updateInfo();
        updateBetPool();
        updateBetMoney();
        //   if(showRoad)

        if (!isShowAskRoad) {
            mAppViewModel.updateDragenTigerRoad(mContext, density, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road, baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road
                    , tv_baccarat_shoe_number, tv_baccarat_total_number, tv_baccarat_banker_number, tv_baccarat_player_number, tv_baccarat_tie_number
                    , tv_baccarat_bp_number, tv_baccarat_pp_number,isBigShow, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3);
        }
        if (popGoodRoad != null) {
            updateGoodRoadUi();
        }

    }

    private boolean isBigShow = false;

    private void setAskClick() {
        fl_big_road1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRoad();
                if (baccarat_background_gridlayout1_big.getVisibility() == View.GONE) {
                    isBigShow = true;
                    ll_big_road_parent2.setVisibility(View.GONE);
                    ll_small_road_parent.setVisibility(View.GONE);
                    baccarat_background_gridlayout1.setVisibility(View.GONE);
                    baccarat_background_gridlayout1_big.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams layoutParams = layout1.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layout1.setLayoutParams(layoutParams);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        density = ScreenUtil.getDisplayMetrics(mContext).density;
                    } else {
                        density = ScreenUtil.getDisplayMetrics(mContext).density * (float) 1.44;
                    }
                } else {
                    isBigShow = false;
                    ll_big_road_parent2.setVisibility(View.VISIBLE);
                    ll_small_road_parent.setVisibility(View.VISIBLE);
                    baccarat_background_gridlayout1.setVisibility(View.VISIBLE);
                    baccarat_background_gridlayout1_big.setVisibility(View.GONE);
                    ViewGroup.LayoutParams layoutParams = layout1.getLayoutParams();
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        layoutParams.width = ScreenUtil.dip2px(mContext, 99);
                    } else {
                        layoutParams.width = ScreenUtil.dip2px(mContext, 142);
                    }
                    layout1.setLayoutParams(layoutParams);
                    density = ScreenUtil.getDisplayMetrics(mContext).density;
                }
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoadOld("");
            }
        });

        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRoad();
                if (baccarat_background_gridlayout2_big.getVisibility() == View.GONE) {
                    isBigShow = true;
                    layout1.setVisibility(View.GONE);
                    ll_small_road_parent.setVisibility(View.GONE);
                    baccarat_background_gridlayout2.setVisibility(View.GONE);
                    baccarat_background_gridlayout2_big.setVisibility(View.VISIBLE);
                    density = ScreenUtil.getDisplayMetrics(mContext).density * (float) 1.44;
                } else {
                    isBigShow = false;
                    layout1.setVisibility(View.VISIBLE);
                    ll_small_road_parent.setVisibility(View.VISIBLE);
                    baccarat_background_gridlayout2.setVisibility(View.VISIBLE);
                    baccarat_background_gridlayout2_big.setVisibility(View.GONE);
                    density = ScreenUtil.getDisplayMetrics(mContext).density;
                }
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoadOld("");
            }
        });

        fl_small_road_parent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRoad();
                if (smallway_item1_big.getVisibility() == View.GONE) {
                    isBigShow = true;
                    layout1.setVisibility(View.GONE);
                    ll_big_road_parent2.setVisibility(View.GONE);
                    hsv_small_road_2.setVisibility(View.GONE);
                    hsv_small_road_3.setVisibility(View.GONE);
                    smallway_item1.setVisibility(View.GONE);
                    smallway_item1_big.setVisibility(View.VISIBLE);
                    density = ScreenUtil.getDisplayMetrics(mContext).density * (float) 3.3;
                } else {
                    isBigShow = false;
                    layout1.setVisibility(View.VISIBLE);
                    ll_big_road_parent2.setVisibility(View.VISIBLE);
                    hsv_small_road_2.setVisibility(View.VISIBLE);
                    hsv_small_road_3.setVisibility(View.VISIBLE);
                    smallway_item1.setVisibility(View.VISIBLE);
                    smallway_item1_big.setVisibility(View.GONE);
                    density = ScreenUtil.getDisplayMetrics(mContext).density;
                }
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoadOld("");
            }
        });
        fl_small_road_parent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRoad();
                if (smallway_item2_big.getVisibility() == View.GONE) {
                    isBigShow = true;
                    layout1.setVisibility(View.GONE);
                    ll_big_road_parent2.setVisibility(View.GONE);
                    hsv_small_road_1.setVisibility(View.GONE);
                    hsv_small_road_3.setVisibility(View.GONE);
                    smallway_item2.setVisibility(View.GONE);
                    smallway_item2_big.setVisibility(View.VISIBLE);
                    density = ScreenUtil.getDisplayMetrics(mContext).density * (float) 3.3;
                } else {
                    isBigShow = false;
                    layout1.setVisibility(View.VISIBLE);
                    ll_big_road_parent2.setVisibility(View.VISIBLE);
                    hsv_small_road_1.setVisibility(View.VISIBLE);
                    hsv_small_road_3.setVisibility(View.VISIBLE);
                    smallway_item2.setVisibility(View.VISIBLE);
                    smallway_item2_big.setVisibility(View.GONE);
                    density = ScreenUtil.getDisplayMetrics(mContext).density;
                }
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoadOld("");
            }
        });
        fl_small_road_parent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRoad();
                if (smallway_item3_big.getVisibility() == View.GONE) {
                    isBigShow = true;
                    layout1.setVisibility(View.GONE);
                    ll_big_road_parent2.setVisibility(View.GONE);
                    hsv_small_road_1.setVisibility(View.GONE);
                    hsv_small_road_2.setVisibility(View.GONE);
                    smallway_item3.setVisibility(View.GONE);
                    smallway_item3_big.setVisibility(View.VISIBLE);
                    density = ScreenUtil.getDisplayMetrics(mContext).density * (float) 3.3;
                } else {
                    isBigShow = false;
                    layout1.setVisibility(View.VISIBLE);
                    ll_big_road_parent2.setVisibility(View.VISIBLE);
                    hsv_small_road_1.setVisibility(View.VISIBLE);
                    hsv_small_road_2.setVisibility(View.VISIBLE);
                    smallway_item3.setVisibility(View.VISIBLE);
                    smallway_item3_big.setVisibility(View.GONE);
                    density = ScreenUtil.getDisplayMetrics(mContext).density;
                }
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoadOld("");
            }
        });

        ll_banker_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAskRoadAnimation("1");
            }
        });
        ll_player_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAskRoadAnimation("5");
            }
        });
        popGoodRoad = new PopGoodRoad(mContext, rl_good_road, ScreenUtil.dip2px(mContext, 130), ScreenUtil.dip2px(mContext, 122));
        rl_good_road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    int windowPos[] = new int[2];
                    rl_good_road.getLocationOnScreen(windowPos);
                    popGoodRoad.showPopupGravityWindow(Gravity.NO_GRAVITY, windowPos[0], windowPos[1] - ScreenUtil.dip2px(mContext, 122));
                } else {
                    popGoodRoad.showPopupDownWindow();
                }
            }
        });
    }

    private void clearAllRoad() {
        baccarat_head_road.removeAllViewsInLayout();
        baccarat_big_road.removeAllViewsInLayout();
        baccarat_bigeyes_road.removeAllViewsInLayout();
        baccarat_smalleyes_road.removeAllViewsInLayout();
        baccarat_roach_road.removeAllViewsInLayout();
    }

    ObjectAnimator animator;

    private void updateGoodRoadUi() {
        goodRoadDataBeenList.clear();
        for (int i = 0; i < otherTableIdList.size(); i++) {
            int otherTableId = otherTableIdList.get(i);
            Baccarat baccarat = mAppViewModel.getBaccarat(otherTableId);
            String goodRoad = mAppViewModel.updateGoodRoad(mContext, baccarat.getBigRoad());
            if (!TextUtils.isEmpty(goodRoad)) {
                String tableName = getTableName(otherTableId);
                int goodRoadPic = getGoodRoadPic(goodRoad);
                goodRoadDataBeenList.add(new GoodRoadDataBean(otherTableId + "", tableName, goodRoadPic));
            }
        }
        if (goodRoadDataBeenList.size() > 0) {
            tv_good_road_count.setText(goodRoadDataBeenList.size() + "");
            if (animator == null) {
                animator = ObjectAnimator.ofFloat(tv_good_road_count, "alpha", 1, (float) 0.2, 1);
                animator.setRepeatCount(Animation.INFINITE);
                animator.setDuration(500);
            }
            if (!animator.isRunning()) {
                tv_good_road_count.setVisibility(View.VISIBLE);
                animator.start();
            }
            if (popGoodRoad.isShowing()) {
                popGoodRoad.showHideWait(false);
                popGoodRoad.initUi(goodRoadDataBeenList);
            }
        } else {
            if (animator != null) {
                animator.cancel();
                tv_good_road_count.setVisibility(View.GONE);
            }
            popGoodRoad.hideAllView();
            popGoodRoad.showHideWait(true);
        }
    }

    private int getGoodRoadPic(String name) {
        if (name.equals(getString(R.string.GoodRoad_1))) {
            return R.mipmap.img_road1;
        } else if (name.equals(getString(R.string.GoodRoad_2))) {
            return R.mipmap.img_road2;
        } else if (name.equals(getString(R.string.GoodRoad_3))) {
            return R.mipmap.img_road3;
        } else if (name.equals(getString(R.string.GoodRoad_4))) {
            return R.mipmap.img_road4;
        } else if (name.equals(getString(R.string.GoodRoad_5))) {
            return R.mipmap.img_road5;
        } else if (name.equals(getString(R.string.GoodRoad_6))) {
            return R.mipmap.img_road6;
        }
        return 0;
    }

    private String getTableName(int tableId) {
        switch (tableId) {
            case 1:
                return "LB1";
            case 2:
                return "LB2";
            case 3:
                return "LB3";
            case 61:
                return "LB5";
            case 62:
                return "LB6";
            case 63:
                return "LB7";
            case 71:
                return "BM1";
            default:
                return "";
        }
    }

    PopGoodRoad popGoodRoad;

    private void showAskRoadAnimation(final String type) {
        if (!isShowAskRoad) {
            isShowAskRoad = true;
            mAppViewModel.updateDragenTigerAskRoad(mContext, density, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                    baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBigRoad() + type,
                    isBigShow, true, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, true);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateDragenTigerAskRoad(mContext, density, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBigRoad(),
                            isBigShow, false, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, false);
                }
            }, 500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateDragenTigerAskRoad(mContext, density, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBigRoad() + type,
                            isBigShow, true, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, false);
                }
            }, 1000);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateDragenTigerAskRoad(mContext, density, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBigRoad(),
                            isBigShow, false, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, false);
                }
            }, 1500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateDragenTigerAskRoad(mContext, density, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBigRoad() + type,
                            isBigShow, true, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, false);
                }
            }, 2000);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateDragenTigerAskRoad(mContext, density, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBigRoad(),
                            isBigShow, false, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, true);
                    isShowAskRoad = false;
                }
            }, 2500);
        }
    }

    private boolean isShowAskRoad = false;

    public void updateBetMoney() {

    }

    public void updateTablePool() {
        if (View.VISIBLE == lv_pool.getVisibility()) {
            contentPool.setData(getPoolData());
            contentPool.notifyDataSetChanged();
        }

    }

    public void updateInfo() {
        if (View.VISIBLE == lv_user_info.getVisibility()) {
            contentInfo.setData(updateInfoData());
            contentInfo.notifyDataSetChanged();
        }

    }

    public void updateBetPool() {
        if (View.VISIBLE == lv_person_bet_info.getVisibility()) {
            contentBetPool.setData(getBetData(betType));
            contentBetPool.notifyDataSetChanged();
        }

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (!isAttached)
                return;
            switch (msg.what) {

                case HandlerCode.SHOW_BACK_LOBBY:
                    gotoLobby();
                    break;
                case HandlerCode.UPDATE_STATUS:
                    updateTimer();
                    updateInterface();
                    break;
                case HandlerCode.SHOW_LIMIT_OVER_MAX:
                    Toast.makeText(mContext, R.string.show_limit_over_max, Toast.LENGTH_LONG).show();
                    break;
                case HandlerCode.UPDATE_GAME_NUMBER_ERROR:
                    updateGameNumber = null;
                    break;
                case HandlerCode.UPDATE_GAME_NUMBER:
                    updateGameNumber = null;
                    tv_table_game_number.setText(tableName + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber());
                    tv_table_game_number1.setText(tv_table_game_number.getText().toString());
                    //     if(showRoad)
                    tv_baccarat_shoe_number.setText("" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber());
                    if (!gameNumber.equals(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber())) {
                        clearAllChips();
                    }
                    break;
                case HandlerCode.SHOW_WIN_LOSS:
                    serviceTime.setText(mAppViewModel.getUser().getBalance() + "");
                    //提示输赢
                    if (bBetSucess) {
//                        if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney() >= 0) {
//                            if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney() > 0)
//                                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, 15, componentFront, mContext, mAppViewModel.getFrontVolume());
//                            ToastUtils.showToast(mContext, getResources().getString(R.string.show_win) + " " + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney(), ContextCompat.getColor(mContext,R.color.blue_word));
//                        } else
//                            ToastUtils.showToast(mContext, getResources().getString(R.string.show_loss) + " " + (-mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney()), Color.RED);
                        if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney() > 0){
                            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_RESULTS, 7, componentFront, mContext, mAppViewModel.getFrontVolume());
                            ToastUtils.showWinningToast(mContext, getResources().getString(R.string.show_win) + " " + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney(), ContextCompat.getColor(mContext,R.color.gold));
                        }

                    }
                    //清除所有的下注的筹码
                    clearAllChips();

                    break;
                case HandlerCode.SHOW_BET_SUCCESS:
                    dismissBlockDialog();
                    initBetInformation(type);
                    ToastUtils.showBetSuccessToast(mContext, getResources().getString(R.string.show_bet_sucess) + " " + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getAllBetMoney());
                    serviceTime.setText(mAppViewModel.getUser().getBalance() + "");
                    break;
                case HandlerCode.SHOW_BET_MONEY:
                    showBetMoney(false);
                    break;
                case HandlerCode.SHOW_BET_ERROR:
                    dismissBlockDialog();
                    Toast.makeText(mContext, R.string.show_bet_error, Toast.LENGTH_LONG).show();
                    clearNoBetChip();
                    break;
                case HandlerCode.THREAD_ERROR:
                    dismissBlockDialog();
                    break;
                case HandlerCode.DRAGON_TIGER_GAME_NUMBER_LIMIT:
                    Toast toast = Toast.makeText(mContext, getString(R.string.dragon_tiger_game_number_limit), Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;

            }
            //

        }
    };

    public void initUI() {
        dragonTigerTimer = 0;
        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setTimer(0);
        //  mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setGameStatus(1);
        gameNumber = "0";
        tv_table_timer.setText("0");
        bBetSucess = false;

//        if(isBottomOpen)//正好在发牌的时候锁频，再次打开屏幕的时候要先隐藏
//            hidePoker();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AutoLayoutConifg.getInstance().setSize(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAppViewModel.startBackgroudMuzicService(mAppViewModel.getMuzicIndex(), componentBack, mContext, mAppViewModel.getBackgroudVolume());
            }
        }, 1000);
//        startUpdateStatusThread();
        videoHelper.loadVideo();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AutoLayoutConifg.getInstance().setSize(this);
    }

    private boolean isNeedGetWidth = true;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (isNeedGetWidth){
            isNeedGetWidth = false;
            ViewGroup.LayoutParams layoutParams1 = img_dragon_animation.getLayoutParams();
            layoutParams1.width = rl_dragon_parent.getWidth();
            layoutParams1.height = rl_dragon_parent.getHeight();
            img_dragon_animation.setLayoutParams(layoutParams1);
            ViewGroup.LayoutParams layoutParams2 = img_tiger_animation.getLayoutParams();
            layoutParams2.width = rl_tiger_parent.getWidth();
            layoutParams2.height = rl_tiger_parent.getHeight();
            img_tiger_animation.setLayoutParams(layoutParams2);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isActive = false;
    }

    @Override
    protected void leftClick() {
        mAppViewModel.setbLobby(true);
        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoadOld("");
        AppTool.activiyJump(mContext, LobbyDragonTigerActivity.class);
        finish();
    }

    public void startUpdateStatusThread() {
        if (updateStatus == null) {
            bGetStatus = true;

            updateStatus = new UpdateStatus();
            threadStatus = new Thread(updateStatus);
            threadStatus.start();

            updateBetMoney = new UpdateBetMoney();
            threadUpdateBetMoney = new Thread(updateBetMoney);
            threadUpdateBetMoney.start();

            updateRoad = new UpdateRoad();
            threadUpdateRoad = new Thread(updateRoad);
            threadUpdateRoad.start();

//            updateGameNumber = new UpdateGameNumber();
//            threadUpdateGameNumber = new Thread(updateGameNumber);
//            threadUpdateGameNumber.start();

//            updateWonMoney = new UpdateWonMoney();
//            threadUpdateWonMoney = new Thread(updateWonMoney);
//            threadUpdateWonMoney.start();
        }
    }

    public void stopUpdateStatusThread() {

        if (updateStatus != null) {
            bGetStatus = false;

            updateStatus = null;
            threadStatus = null;

//            updateBetMoney = null;
//            threadUpdateBetMoney = null;

//            updateRoad = null;
//            threadUpdateRoad = null;

//            updateGameNumber = null;
//            threadUpdateGameNumber = null;

        }
        mAppViewModel.closeMuzicService(mContext, BackgroudMuzicService.class);
        mAppViewModel.closeMuzicService(mContext, FrontMuzicService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);


    }

    public void showAnimation(View v, boolean able, int g) {
        TranslateAnimation mShowAction = null;
        TranslateAnimation mHiddenAction = null;
        if (able) {
            if (g == Gravity.TOP) {
                mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mShowAction.setDuration(500);
            }
            if (g == Gravity.BOTTOM) {
                mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                mShowAction.setDuration(500);
            }
            v.startAnimation(mShowAction);
            v.setVisibility(View.VISIBLE);

        } else {
            if (g == Gravity.TOP) {
                mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        -1.0f);
            } else if (g == Gravity.BOTTOM) {
                mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                        0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                        1.0f);
            }
            mHiddenAction.setDuration(500);
            v.startAnimation(mHiddenAction);
            v.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();

    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState!=null){
            isFirstBet = savedInstanceState.getBoolean("isFirstBet");
        }
        initUI();
        animationDragon = (AnimationDrawable) img_dragon_animation.getBackground();
        animationTiger = (AnimationDrawable) img_tiger_animation.getBackground();
        apngPlayBeanList.clear();
        String lg = AppTool.getAppLanguage(mContext);
        if (lg.equals("zh")||lg.equals("zh_TW")){
            apngPlayBeanList.add(new ApngPlayBean(img_apng_dragon,"assets://dragon_win_zh.png"));
            apngPlayBeanList.add(new ApngPlayBean(img_apng_tie,"assets://tie_win_cn.png"));
            apngPlayBeanList.add(new ApngPlayBean(img_apng_tiger,"assets://tiger_win_zh.png"));
        }else {
            apngPlayBeanList.add(new ApngPlayBean(img_apng_dragon,"assets://dragon_win_en.png"));
            apngPlayBeanList.add(new ApngPlayBean(img_apng_tie,"assets://tie_win_en.png"));
            apngPlayBeanList.add(new ApngPlayBean(img_apng_tiger,"assets://tiger_win_en.png"));
        }
        findViewById(R.id.ll_pp).setVisibility(View.GONE);
        findViewById(R.id.ll_bp).setVisibility(View.GONE);
        toolbar_right_bottom_tv.setVisibility(View.GONE);
        toolbar_right_top_tv.setVisibility(View.GONE);
        toolbar.setNavigationIcon(null);
        shufflingTv = (TextView) findViewById(R.id.tv_shuffling);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            shufflingTv.post(new Runnable() {
                @Override
                public void run() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) shufflingTv.getLayoutParams();
                    layoutParams.topMargin = UIUtil.dip2px(mContext, 60);
                    shufflingTv.setLayoutParams(layoutParams);
                }
            });
        }
        //////////////////lanjian xiugai
        tableId = Integer.parseInt(getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_DATA));
        tableName = "DT1:";
        initControl();
        ////////////////////
//        serviceTime.setVisibility(View.GONE);

        serviceTime.setText(mAppViewModel.getUser().getBalance() + "");

        rightTv.setTextColor(getResources().getColor(R.color.white));
        toolbar.setBackgroundResource(R.color.transparent);

        setTablePool(lv_pool);
        setInfoData(lv_user_info);
        setTableBetPool(lv_person_bet_info, "Banker");


        startPlayVideo();
        //lanjian  xiugai
        setTableLimit();
        setClickListener();
        setChip();
        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoadOld("");

        tv_table_game_number.setText(tableName + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameNumber());
        initArcMenu(tvMenu, "DT1", 1);
        tv_table_game_number.setText("DT1" + ":" + mAppViewModel.getDragonTiger01().getGameNumber());
        tv_table_game_number1.setText(tv_table_game_number.getText().toString());
        initName();
        otherTableIdList.clear();
        otherTableIdList.add(1);
        otherTableIdList.add(2);
        otherTableIdList.add(3);
        otherTableIdList.add(61);
        otherTableIdList.add(62);
        otherTableIdList.add(63);
        otherTableIdList.add(71);
        tv_ask1.setText(getString(R.string.dragon_dragon_tiger));
        tv_ask2.setText(getString(R.string.tiger_dragon_tiger));
        setAskClick();
        startUpdateStatusThread();
    }

    private void initName() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            SkewTexView tv_dragon = (SkewTexView) findViewById(R.id.tv_dragon);
            tv_dragon.setTextColor(getResources().getColor(R.color.red_title));
            SkewTexView tv_tie_dragon_tiger = (SkewTexView) findViewById(R.id.tv_tie_dragon_tiger);
            tv_tie_dragon_tiger.setTextColor(getResources().getColor(R.color.green_a6d33f));
            SkewTexView tv_tiger = (SkewTexView) findViewById(R.id.tv_tiger);
            tv_tiger.setTextColor(getResources().getColor(R.color.blue));
            SkewTexView tv_red = (SkewTexView) findViewById(R.id.tv_red);
            tv_red.setTextColor(getResources().getColor(R.color.red_title));
            SkewTexView tv_black = (SkewTexView) findViewById(R.id.tv_black);
            tv_black.setTextColor(getResources().getColor(R.color.red_title));
            SkewTexView tv_red1 = (SkewTexView) findViewById(R.id.tv_red1);
            tv_red1.setTextColor(getResources().getColor(R.color.blue));
            SkewTexView tv_black1 = (SkewTexView) findViewById(R.id.tv_black1);
            tv_black1.setTextColor(getResources().getColor(R.color.blue));
            SkewTexView tv_odd = (SkewTexView) findViewById(R.id.tv_odd);
            tv_odd.setTextColor(getResources().getColor(R.color.red_title));
            SkewTexView tv_even = (SkewTexView) findViewById(R.id.tv_even);
            tv_even.setTextColor(getResources().getColor(R.color.red_title));
            SkewTexView tv_odd1 = (SkewTexView) findViewById(R.id.tv_odd1);
            tv_odd1.setTextColor(getResources().getColor(R.color.blue));
            SkewTexView tv_even1 = (SkewTexView) findViewById(R.id.tv_even1);
            tv_even1.setTextColor(getResources().getColor(R.color.blue));
        }
    }

    public void initControl() {
        ll_poker_parent = findViewById(R.id.ll_poker_parent);
        density = ScreenUtil.getDisplayMetrics(mContext).density;
        baccarat_head_road = (GridLayout) findViewById(R.id.baccarat_gridlayout1);
        baccarat_big_road = (GridLayout) findViewById(R.id.baccarat_gridlayout2);
        baccarat_bigeyes_road = (GridLayout) findViewById(R.id.baccarat_gridlayout3);
        fl_small_road_parent1 = findViewById(R.id.fl_small_road_parent);
        smallway_item1 = findViewById(R.id.smallway_item);
        smallway_item1_big = findViewById(R.id.smallway_item_big);
        baccarat_smalleyes_road = (GridLayout) findViewById(R.id.baccarat_gridlayout4).findViewById(R.id.baccarat_gridlayout3);
        fl_small_road_parent2 = findViewById(R.id.baccarat_gridlayout4);
        smallway_item2 = findViewById(R.id.baccarat_gridlayout4).findViewById(R.id.smallway_item);
        smallway_item2_big = findViewById(R.id.baccarat_gridlayout4).findViewById(R.id.smallway_item_big);
        baccarat_roach_road = (GridLayout) findViewById(R.id.baccarat_gridlayout5).findViewById(R.id.baccarat_gridlayout3);
        fl_small_road_parent3 = findViewById(R.id.baccarat_gridlayout5);
        smallway_item3 = findViewById(R.id.baccarat_gridlayout5).findViewById(R.id.smallway_item);
        smallway_item3_big = findViewById(R.id.baccarat_gridlayout5).findViewById(R.id.smallway_item_big);
        tv_baccarat_shoe_number = (TextView) findViewById(R.id.text_shoe_game_number);
        tv_table_game_number = (TextView) findViewById(R.id.tv_table_game_number);
        tv_table_game_number1 = (TextView) findViewById(R.id.tv_table_game_number1);
        tv_baccarat_total_number = (TextView) findViewById(R.id.text_total);
        tv_baccarat_banker_number = (TextView) findViewById(R.id.text_banker);
        tv_baccarat_player_number = (TextView) findViewById(R.id.text_player);
        tv_baccarat_tie_number = (TextView) findViewById(R.id.text_tie);
        tv_baccarat_bp_number = (TextView) findViewById(R.id.text_bp);
        tv_baccarat_pp_number = (TextView) findViewById(R.id.text_pp);


        iv_poker_player1 = (ImageView) findViewById(R.id.iv_poker_player1);
        iv_poker_banker1 = (ImageView) findViewById(R.id.iv_poker_banker1);

        btn_limit = (TextView) findViewById(R.id.btn_limit);
        llCenter.setVisibility(View.VISIBLE);

        ((TextView) findViewById(R.id.tv_banker)).setText(getString(R.string.dr));
        ((TextView) findViewById(R.id.tv_player)).setText(getString(R.string.ti));
        ((TextView) findViewById(R.id.tv_tie)).setText(getString(R.string.tie));
        ((TextView) findViewById(R.id.tv_banker_pair)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_player_pair)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.text_bp)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.text_pp)).setVisibility(View.GONE);

        fl_dragon = (FrameLayout) findViewById(R.id.fl_dragon);
        fl_tiger = (FrameLayout) findViewById(R.id.fl_tiger);
        fl_tie = (FrameLayout) findViewById(R.id.fl_tie);
        fl_dragon_odd = (FrameLayout) findViewById(R.id.fl_dragon_odd);
        fl_dragon_even = (FrameLayout) findViewById(R.id.fl_dragon_even);
        fl_dragon_red = (FrameLayout) findViewById(R.id.fl_dragon_red);
        fl_dragon_black = (FrameLayout) findViewById(R.id.fl_dragon_black);
        fl_tiger_odd = (FrameLayout) findViewById(R.id.fl_tiger_odd);
        fl_tiger_even = (FrameLayout) findViewById(R.id.fl_tiger_even);
        fl_tiger_red = (FrameLayout) findViewById(R.id.fl_tiger_red);
        fl_tiger_black = (FrameLayout) findViewById(R.id.fl_tiger_black);

        iv_dragon = (ImageView) findViewById(R.id.iv_dragon);
        iv_tiger = (ImageView) findViewById(R.id.iv_tiger);
        iv_tie = (ImageView) findViewById(R.id.iv_tie);
        iv_dragon_odd = (ImageView) findViewById(R.id.iv_dragon_odd);
        iv_dragon_even = (ImageView) findViewById(R.id.iv_dragon_even);
        iv_dragon_red = (ImageView) findViewById(R.id.iv_dragon_red);
        iv_dragon_black = (ImageView) findViewById(R.id.iv_dragon_black);
        iv_tiger_odd = (ImageView) findViewById(R.id.iv_tiger_odd);
        iv_tiger_even = (ImageView) findViewById(R.id.iv_tiger_even);
        iv_tiger_red = (ImageView) findViewById(R.id.iv_tiger_red);
        iv_tiger_black = (ImageView) findViewById(R.id.iv_tiger_black);

        tv_point_dragon = (TextView) bottomPanel1.findViewById(R.id.tv_bottom_panel_left);
        tv_point_tiger = (TextView) bottomPanel1.findViewById(R.id.tv_bottom_panel_right);
//        tv_point_dragon.setTextColor(getResources().getColor(R.color.red));
//        tv_point_tiger.setTextColor(getResources().getColor(R.color.blue));

        ((TextView) findViewById(R.id.tv_banker_road)).setText("D");
        ((TextView) findViewById(R.id.tv_player_road)).setText("T");

        initOrientation();
    }

    public void parentClick(View v) {
        parentClick();
    }

    public void setClickListener() {
        if (ll_poker_parent != null)
            ll_poker_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentClick();
                }
            });
        fl_baccarat_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentClick();
            }
        });
        tvTableBetReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    showPopBottom(lv_table_pool);

                if (bBetSucess == false)//当前局还没有下注的时候允许重复下注
                {
                    showBetMoney(true);

                }
            }
        });
       /* tvTableBetPol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betInfoShowAble = !betInfoShowAble;
                showAnimation(lv_table_pool, betInfoShowAble, Gravity.BOTTOM);

            }
        });*/
        tvTableBetCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listBetDetail.size() > 0)
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentFront, mContext, mAppViewModel.getFrontVolume());
                clearNoBetChip();

            }
        });
        tvTableBetSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 2 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5)
                    return;
                if (mAppViewModel.getUser().getBalance() <= 0) {
                    ToastUtils.showToast(mContext, getString(R.string.Insufficient));
                    return;
                }
                if (listBetDetail.size() > 0) {
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 10, componentFront, mContext, mAppViewModel.getFrontVolume());
                    //执行下注的线程
                    drangonTigerBet = new DrangonTigerBet(DtBetType.All);
                    threadDrangonTigerBet = new Thread(drangonTigerBet);
                    showBlockDialog();
                    Executors.newSingleThreadExecutor().execute(threadDrangonTigerBet);
                }
            }
        });


        //路子
        leftPanel1.setOnPanelListener(new Panel.OnPanelListener() {
            @Override
            public void onPanelClosed(Panel panel) {
//                btn_limit.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPanelOpened(Panel panel) {
                //  mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoadOld("");
//                btn_limit.setVisibility(View.GONE);
            }
        });
        leftPanel1.setInterpolator(new BounceInterpolator(EasingType.Type.OUT));
    }

    volatile boolean isCanbet = true;


    public void parentClick() {
        if (toolbar_right_bottom_tv.getVisibility()==View.VISIBLE){
            displayAll(false);
        }else {
            displayAll(true);
        }
        showHideUserInfo();
        closeAll();
    }

    public void setChip() {
        final AdapterViewContent<ChipBean> chips = new AdapterViewContent<>(mContext, lv_baccarat_chips);
        chips.setBaseAdapter(new QuickAdapterImp<ChipBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_image_chip;
            }

            @Override
            public void convert(final ViewHolder helper, ChipBean item, final int position) {
                ImageView chipImg = helper.retrieveView(R.id.iv_chip_pic);
                final LinearLayout llParent = helper.retrieveView(R.id.ll_chip_parent);
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    llParent.post(new Runnable() {
                        @Override
                        public void run() {
                            if (position==0){
                                int padding =  ScreenUtil.dip2px(mContext, 75);
                                FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) lv_baccarat_chips.getLayoutParams();
                                layoutParams1.leftMargin = padding;
                                layoutParams1.rightMargin = padding;
                                lv_baccarat_chips.setLayoutParams(layoutParams1);
                            }
                        }
                    });
                    int screenWidth = WidgetUtil.getScreenWidth(DragonTigerActivity.this);
                    int margin = ((screenWidth-ScreenUtil.dip2px(mContext, 75)*2)/9-ScreenUtil.dip2px(mContext, 50))/2;
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llParent.getLayoutParams();
                    layoutParams.width=ScreenUtil.dip2px(mContext, 50);
                    layoutParams.height=ScreenUtil.dip2px(mContext, 50);
                    layoutParams.leftMargin=margin;
                    layoutParams.rightMargin=margin;
                    llParent.setLayoutParams(layoutParams);
                    chipImg.setImageResource(item.getDrawableRes());
                }else {
                    int screenWidth = WidgetUtil.getPortraitScreenWidth(DragonTigerActivity.this);
                    int margin = (screenWidth/6-ScreenUtil.dip2px(mContext, 60))/2;
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llParent.getLayoutParams();
                    layoutParams.width=ScreenUtil.dip2px(mContext, 60);
                    layoutParams.height=ScreenUtil.dip2px(mContext, 60);
                    layoutParams.leftMargin=margin;
                    layoutParams.rightMargin=margin;
                    llParent.setLayoutParams(layoutParams);
                    chipImg.setImageResource(item.getDrawableRes());
                }
                if (currentShufflingStatus == 1) {
                    if (selectedMap.get(true) != null && position == selectedMap.get(true).intValue()) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) chipImg.getLayoutParams();
                        layoutParams.width = (int)(layoutParams.width*1.2);
                        layoutParams.height = (int)(layoutParams.height*1.2);
                        chipImg.setLayoutParams(layoutParams);
                        helper.setBackgroundRes(R.id.ll_chip_parent, R.drawable.rectangle_trans_stroke_yellow);
                    } else {
                        if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT){
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) chipImg.getLayoutParams();
                            layoutParams.width = ScreenUtil.dip2px(mContext, 60);
                            layoutParams.height =ScreenUtil.dip2px(mContext, 60);
                            chipImg.setLayoutParams(layoutParams);
                        }else {
                            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) chipImg.getLayoutParams();
                            layoutParams.width = ScreenUtil.dip2px(mContext, 50);
                            layoutParams.height =ScreenUtil.dip2px(mContext, 50);
                            chipImg.setLayoutParams(layoutParams);
                        }
                        helper.setBackgroundRes(R.id.ll_chip_parent, 0);
                    }
                } else {
                    selectedMap = new HashMap<>();
                    helper.setBackgroundRes(R.id.ll_chip_parent, 0);
                }
                helper.setText(R.id.tv_chip_amount, item.getName());
            }
        });
        if (currentShufflingStatus == 1) {
            chips.setItemClick(new ItemCLickImp<ChipBean>() {
                @Override
                public void itemCLick(View view, ChipBean chipBean, int position) {
                    selectedMap.put(true, position);
                    chips.notifyDataSetChanged();
                    chooseChip = chipBean.getValue();
                    initClickCount();
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 8, componentFront, mContext, mAppViewModel.getFrontVolume());
                }
            });
            chips.setData(chipListChoice);
        } else {
            chooseChip = 0;
            chips.setItemClick(new ItemCLickImp<ChipBean>() {
                @Override
                public void itemCLick(View view, ChipBean chipBean, int position) {

                }
            });
            chips.setData(chipListCanNotChoice);
        }
    }

    public void setTableLimit() {
        AdapterViewContent<LiveInfoBean> contentList = new AdapterViewContent<>(mContext, lvTableBetLimitRed);
        contentList.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_live_info3;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.tv_type);
                tvType.setText(item.getType());
                helper.setText(R.id.tv_value, item.getValue1() + "  -  " + item.getValue2());
                helper.setTextColor(R.id.tv_value, getResources().getColor(R.color.yellow_gold));
                helper.setVisibility(R.id.tv_value2, View.GONE);

            }
        });
        List<LiveInfoBean> limit = new ArrayList<LiveInfoBean>();
        limit.add(new LiveInfoBean(getString(R.string.dragon1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinDragonTigerBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxDragonTigerBet())));
        limit.add(new LiveInfoBean(getString(R.string.tiger1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinDragonTigerBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxDragonTigerBet())));
        limit.add(new LiveInfoBean(getString(R.string.tie_dragon_tiger), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTieBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTieBet())));
        limit.add(new LiveInfoBean(getString(R.string.dragon_odd1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        limit.add(new LiveInfoBean(getString(R.string.dragon_even1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        limit.add(new LiveInfoBean(getString(R.string.dragon_red1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        limit.add(new LiveInfoBean(getString(R.string.dragon_black1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        limit.add(new LiveInfoBean(getString(R.string.tiger_odd1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        limit.add(new LiveInfoBean(getString(R.string.tiger_even1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        limit.add(new LiveInfoBean(getString(R.string.tiger_red1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        limit.add(new LiveInfoBean(getString(R.string.tiger_black1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        contentList.setData(limit);
        btn_limit.setText("DT1:" + getString(R.string.LIMIT));
    }

    private void setTablePool(ListView listView) {
        if (contentPool == null)
            contentPool = new AdapterViewContent<>(mContext, listView);
        contentPool.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_user_info;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.tv_name);
                TextView tvValue = helper.retrieveView(R.id.tv_value);
                tvType.setText(item.getType());
                tvValue.setText(item.getValue1());
                switch (position){
                    case 0:
                        tvType.setTextColor(ContextCompat.getColor(mContext,R.color.banker_color));
                        break;
                    case 2:
                        tvType.setTextColor(ContextCompat.getColor(mContext,R.color.player_color));
                        break;
                    case 4:
                        tvType.setTextColor(ContextCompat.getColor(mContext,R.color.tie_color));
                        break;
                }
            }
        });


        contentPool.setData(getPoolData());
    }

    public List<LiveInfoBean> getPoolData() {
        List<LiveInfoBean> strData = new ArrayList<LiveInfoBean>();
        LiveInfoBean data = new LiveInfoBean();
        //    Log.i(WebSiteUrl.Tag,"GetBankerPool = "+mAppViewModel.getTableId());
        //   Log.i(WebSiteUrl.Tag,"GetBankerPool = "+mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBaccaratPool().getBanker());
        if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPool().getDragon() > 0)
            data = new LiveInfoBean(getString(R.string.dragon_dragon_tiger), mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPool().getDragon() + "", "");
        else
            data = new LiveInfoBean(getString(R.string.dragon_dragon_tiger) , "0", "");
        strData.add(data);
        strData.add(new LiveInfoBean("","",""));
        if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPool().getTiger() > 0)
            data = new LiveInfoBean(getString(R.string.tiger_dragon_tiger), mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPool().getTiger() + "", "");
        else
            data = new LiveInfoBean(getString(R.string.tiger_dragon_tiger), "0", "");
        strData.add(data);
        strData.add(new LiveInfoBean("","",""));
        if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPool().getTie() > 0)
            data = new LiveInfoBean(getString(R.string.tie), mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPool().getTie() + "", "");
        else
            data = new LiveInfoBean(getString(R.string.tie), "0", "");
        strData.add(data);

        return strData;
    }

    public List<String> getBetData(String type) {
        List<String> strData = new ArrayList<String>();
        String data = "";

        //   mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getOtherUserBetInfomation()
      /*      for(int i =0;i<mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getOtherUserBetInfomation().size();i++)
            {
                if(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getOtherUserBetInfomation().get(i).getType().equals(type))
                {
                    for(int j =0;j<mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBaccaratPlayer().size();j++)
                    {
                        if(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBaccaratPlayer().get(j).getNumber().contains(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getOtherUserBetInfomation().get(i).getAreaId()))
                        {
                            data = mAppViewModel.hideName(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getBaccaratPlayer().get(j).getName()) + " : " +
                                    mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getOtherUserBetInfomation().get(i).getBetMoney();
                            strData.add(data);
                            break;
                        }
                    }
                }
            }*/

        return strData;
    }

    private void setTableBetPool(ListView listView, String type) {
        if (contentBetPool == null)
            contentBetPool = new AdapterViewContent<>(mContext, listView);
        contentBetPool.setBaseAdapter(new QuickAdapterImp<String>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_limit_red_text;
            }

            @Override
            public void convert(ViewHolder helper, String item, int position) {
                TextView tv = helper.retrieveView(R.id.text_tv1);
                tv.setTextSize(poolSize);
                tv.setText(item);
                tv.setTextColor(getResources().getColor(R.color.yellow_brown_white_word));
            }
        });
        contentBetPool.setData(getBetData(type));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_dragen_tiger_bet_game;
    }

    public void startPlayVideo() {
        mPreview =  findViewById(R.id.surface);
        videoHelper = new VideoHelper(mContext, mPreview) {
            @Override
            public void doVideoFix() {
                super.doVideoFix();
                if (findViewById(R.id.fl_baccarat_bg) != null)
                    findViewById(R.id.fl_baccarat_bg).setVisibility(View.GONE);
            }
        };
        path = mAppViewModel.getUser().getVideoUrl() + "/" + mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getVideoUrlIndex() + "/L05";
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            path = mAppViewModel.getUser().getVideoUrl() + "/" + "live/L05new";
        }
//        path="rtmp://202.36.58.169/live/L05";
        videoHelper.setPlayUrl(path);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //   hidePoker();

        videoHelper.pauseVideo();

        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setGameStatus(1);
        if (isBottomOpen)//正好在发牌的时候锁频，再次打开屏幕的时候要先隐藏
            hidePoker();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoHelper.stopVideo();
        stopUpdateStatusThread();
    }
    /*    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
        doCleanUp();
        stopUpdateStatusThread();
    }*/

    public void showUpPop(View v, String betType) {
        personInfoShowAble = !personInfoShowAble;
        if (personInfoShowAble) {
            lv_person_bet_info.setVisibility(View.VISIBLE);
            this.betType = betType;
        } else {
            lv_person_bet_info.setVisibility(View.GONE);
        }
      /*  View view = LayoutInflater.from(mContext).inflate(R.layout.popupwindow_list_bet_people, null);
        ListView lv = (ListView) view.findViewById(R.id.lv_people_bet_info);
        setListDataTest(lv,null);

        final PopupWindow popupWindow = new PopupWindow(view, 400, 500);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = view.getMeasuredWidth();
        int popupHeight = view.getMeasuredHeight();
        int[] location = new int[2];
// 允许点击外部消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
// 获得位置
        v.getLocationOnScreen(location);//location[1] - popupHeight
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1]-popupHeight);*/
    }


    public void clickLimitRed(View v) {

    }

    @Override
    protected void showLimit() {
        super.showLimit();
        if (lvTableBetLimitRed.getVisibility() == View.VISIBLE) {
            lvTableBetLimitRed.setVisibility(View.GONE);
        }else {
            lvTableBetLimitRed.setVisibility(View.VISIBLE);
        }
    }

    public void clickCancel(View v) {
        //  chipHelper1.clearAllChips();
        clearNoBetChip();
    }

    public void clearNoBetChip() {
        clearAllChips();
        showBetMoney(false);
        initBetInformation(DtBetType.All);
    }

    public void clearNoBetChip(DtBetType type) {
        showBetChip(getFrameLayout(type.toString()), false, 0);
        initBetInformation(type);
        showBetMoney(false);
    }

    public void initBetInformation(DtBetType type) {
        if (type == DtBetType.All)
            listBetDetail.clear();
        else if (listBetDetail != null && listBetDetail.size() > 0) {
            for (int i = 0; i < listBetDetail.size(); i++) {
                BetDetail item = listBetDetail.get(i);
                if (item.getType().equals(type.toString())) {
                    listBetDetail.remove(i);

                }
            }
        }
        if (listBetDetail == null || listBetDetail.size() < 1)
            BetUiHelper.betStateColor(tvTableBetSure, false);
        checkOperationButton();
    }

    public void checkOperationButton() {
        removeAllOperationBtn();
        if (listBetDetail != null && listBetDetail.size() > 0) {
            for (int i = 0; i < listBetDetail.size(); i++) {
                BetDetail item = listBetDetail.get(i);
                checkOperationButton(item);
            }
        }
    }

    private void removeAllOperationBtn() {
        for (DtBetType type : DtBetType.values()) {
            FrameLayout f = getFrameLayout(type.toString());
            if (f == null)
                continue;
            else {
                ChipShowHelper chipHelper = ChipMap.get(f);
                if (chipHelper == null)
                    continue;
                else
                    chipHelper.setOperationButtonDisplay(false);
            }
        }
    }

    public void checkOperationButton(BetDetail item) {
        FrameLayout f = getFrameLayout(item.getType());
        if (f == null)
            return;
        ChipShowHelper chipHelper = ChipMap.get(f);
        if (chipHelper == null)
            return;
        if (item.getMoney() > 0) {
            chipHelper.setOperationButtonDisplay(true);
        } else
            chipHelper.setOperationButtonDisplay(false);
    }

    public void clearAllChips() {
        BetUiHelper.betStateColor(tvTableBetSure, false);
        showBetChip(fl_dragon, false, 0);
        showBetChip(fl_dragon_odd, false, 0);
        showBetChip(fl_dragon_even, false, 0);
        showBetChip(fl_dragon_red, false, 0);
        showBetChip(fl_dragon_black, false, 0);
        showBetChip(fl_tiger, false, 0);
        showBetChip(fl_tiger_odd, false, 0);
        showBetChip(fl_tiger_even, false, 0);
        showBetChip(fl_tiger_red, false, 0);
        showBetChip(fl_tiger_black, false, 0);
        showBetChip(fl_tie, false, 0);
    }

    private void showApng(int position){
        for (int i = 0; i < apngPlayBeanList.size(); i++) {
            ApngImageView apngImageView = apngPlayBeanList.get(i).getApngImageView();
            String uri = apngPlayBeanList.get(i).getUri();
            if (i==position){
                File file = FileUtils.processApngFile(uri, mContext);
                ApngLoader.getInstance().loadApng(file.getAbsolutePath(), apngImageView);
                apngImageView.setVisibility(View.VISIBLE);
                apngImageView.positon=i;
            }else {
                apngImageView.stop();
                apngImageView.setVisibility(View.INVISIBLE);
            }
        }
    }

    private AnimationDrawable animationDragon;
    private AnimationDrawable animationTiger;

    public void showResultsOnUI() {
        if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5 && (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerResults().getDragon_tiger_tie() != 0)) {
            if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerResults().getDragon_tiger_tie() == 1) {
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_dragon.setBackgroundResource(R.mipmap.long_03);
                }else {
                    iv_dragon.setBackgroundResource(R.mipmap.long_03_portrait);
                }
                animationDragon.start();
                showApng(0);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, 11, componentFront, mContext, mAppViewModel.getFrontVolume());
            } else if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerResults().getDragon_tiger_tie() == 2) {
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_tiger.setBackgroundResource(R.mipmap.hu_03);
                }else {
                    iv_tiger.setBackgroundResource(R.mipmap.hu_03_portrait);
                }
                showApng(2);
                animationTiger.start();
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, 12, componentFront, mContext, mAppViewModel.getFrontVolume());
            } else if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerResults().getDragon_tiger_tie() == 3) {
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_tie.setBackgroundResource(R.mipmap.he_03);
                }else {
                    iv_tie.setBackgroundResource(R.mipmap.he_03_portrait);
                }
                showApng(1);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, 13, componentFront, mContext, mAppViewModel.getFrontVolume());
            }

            if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerResults().getDragon_odd_even() == 1) {
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_dragon_odd.setBackgroundResource(R.mipmap.zuo3_03);
                }else {
                    iv_dragon_odd.setBackgroundResource(R.mipmap.zuo3_03_portrait);
                }
            } else if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerResults().getDragon_odd_even() == 2){
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_dragon_even.setBackgroundResource(R.mipmap.zuo4_03);
                }else {
                    iv_dragon_even.setBackgroundResource(R.mipmap.normal_dragon_tiger);
                }
            }

            if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerResults().getDragon_red_black() == 1) {
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_dragon_red.setBackgroundResource(R.mipmap.zuo1_03);
                }else {
                    iv_dragon_red.setBackgroundResource(R.mipmap.normal_dragon_tiger);
                }
            } else{
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_dragon_black.setBackgroundResource(R.mipmap.zuo2_03);
                }else {
                    iv_dragon_black.setBackgroundResource(R.mipmap.normal_dragon_tiger);
                }
            }


            if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerResults().getTiger_odd_even() == 1) {
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_tiger_odd.setBackgroundResource(R.mipmap.zuo7_03);
                }else {
                    iv_tiger_odd.setBackgroundResource(R.mipmap.normal_dragon_tiger);
                }
            } else{
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_tiger_even.setBackgroundResource(R.mipmap.zuo8_03);
                }else {
                    iv_tiger_even.setBackgroundResource(R.mipmap.zuo8_03_portrait);
                }
            }

            if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerResults().getTiger_red_black() == 1) {
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_tiger_red.setBackgroundResource(R.mipmap.zuo5_03);
                }else {
                    iv_tiger_red.setBackgroundResource(R.mipmap.normal_dragon_tiger);
                }
            } else{
                if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
                    iv_tiger_black.setBackgroundResource(R.mipmap.zuo6_03);
                }else {
                    iv_tiger_black.setBackgroundResource(R.mipmap.normal_dragon_tiger);
                }
            }

        }

    }

    public void showPoker() {
        isResultEnd = true;
        if (isResultEnd) {
            isBottomOpen = true;
            bottomPanel1.setOpen(isBottomOpen, true);
            if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPoker().getDragon() > 0) {
                /*iv_poker_banker1.setImageResource(getPokerResource(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPoker().getDragon()));
                iv_poker_banker1.setVisibility(View.VISIBLE);*/
                if (iv_poker_banker1.getVisibility() == View.GONE) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getPokerResource(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPoker().getDragon()));
                    iv_poker_banker1.setVisibility(View.VISIBLE);
                    iv_poker_banker1.setImageBitmap(BitmapTool.skewBitmap(bitmap, xSkizeB1, 0f));
                    WidgetUtil.translateAnimation(iv_poker_banker1, bankerX, 0f, AutoUtils.getPercentWidthSize(animationHeight), 0f, animationTime);
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.GET_POKER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
                }
            }

            if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPoker().getTiger() > 0) {
               /* iv_poker_player1.setImageResource(getPokerResource(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPoker().getTiger()));
                iv_poker_player1.setVisibility(View.VISIBLE);*/
                if (iv_poker_player1.getVisibility() == View.GONE) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getPokerResource(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPoker().getTiger()));
                    iv_poker_player1.setVisibility(View.VISIBLE);
                    iv_poker_player1.setImageBitmap(BitmapTool.skewBitmap(bitmap, -xSkizeP1, 0f));
                    WidgetUtil.translateAnimation(iv_poker_player1, playerX, 0f, AutoUtils.getPercentWidthSize(animationHeight), 0f, animationTime);
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.GET_POKER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
                }
            }
            mAppViewModel.showPoint(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPoker().getDragon(),

                    mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerPoker().getTiger(),

                    tv_dragon_result, tv_tiger_result, "", "");

        }
    }

    boolean isShowWinLose = true;

    public void hidePoker() {
        isResultEnd = false;
        isBottomOpen = false;
        bottomPanel1.setOpen(isBottomOpen, true);
        iv_poker_banker1.setVisibility(View.GONE);

        iv_poker_player1.setVisibility(View.GONE);

        tv_point_dragon.setText(getString(R.string.dragon) + " 0");
        tv_point_tiger.setText(getString(R.string.tiger) + " 0");
        tv_dragon_result.setText("0");
        tv_tiger_result.setText("0");
        isShowWinLose= false;
        updateWonMoney = new UpdateWonMoney();
        threadUpdateWonMoney = new Thread(updateWonMoney);
        threadUpdateWonMoney.start();
    }

    public int getPokerResource(int poker) {
        int poker_res = 0;
        switch (poker) {
            case 1:
                poker_res = R.mipmap.pk_1;
                break;
            case 2:
                poker_res = R.mipmap.pk_2;
                break;
            case 3:
                poker_res = R.mipmap.pk_3;
                break;
            case 4:
                poker_res = R.mipmap.pk_4;
                break;
            case 5:
                poker_res = R.mipmap.pk_5;
                break;
            case 6:
                poker_res = R.mipmap.pk_6;
                break;
            case 7:
                poker_res = R.mipmap.pk_7;
                break;
            case 8:
                poker_res = R.mipmap.pk_8;
                break;
            case 9:
                poker_res = R.mipmap.pk_9;
                break;
            case 10:
                poker_res = R.mipmap.pk_10;
                break;
            case 11:
                poker_res = R.mipmap.pk_11;
                break;
            case 12:
                poker_res = R.mipmap.pk_12;
                break;
            case 13:
                poker_res = R.mipmap.pk_13;
                break;
            case 14:
                poker_res = R.mipmap.pk_14;
                break;
            case 15:
                poker_res = R.mipmap.pk_15;
                break;
            case 16:
                poker_res = R.mipmap.pk_16;
                break;
            case 17:
                poker_res = R.mipmap.pk_17;
                break;
            case 18:
                poker_res = R.mipmap.pk_18;
                break;
            case 19:
                poker_res = R.mipmap.pk_19;
                break;
            case 20:
                poker_res = R.mipmap.pk_20;
                break;
            case 21:
                poker_res = R.mipmap.pk_21;
                break;
            case 22:
                poker_res = R.mipmap.pk_22;
                break;
            case 23:
                poker_res = R.mipmap.pk_23;
                break;
            case 24:
                poker_res = R.mipmap.pk_24;
                break;
            case 25:
                poker_res = R.mipmap.pk_25;
                break;
            case 26:
                poker_res = R.mipmap.pk_26;
                break;
            case 27:
                poker_res = R.mipmap.pk_27;
                break;
            case 28:
                poker_res = R.mipmap.pk_28;
                break;
            case 29:
                poker_res = R.mipmap.pk_29;
                break;
            case 30:
                poker_res = R.mipmap.pk_30;
                break;
            case 31:
                poker_res = R.mipmap.pk_31;
                break;
            case 32:
                poker_res = R.mipmap.pk_32;
                break;
            case 33:
                poker_res = R.mipmap.pk_33;
                break;
            case 34:
                poker_res = R.mipmap.pk_34;
                break;
            case 35:
                poker_res = R.mipmap.pk_35;
                break;
            case 36:
                poker_res = R.mipmap.pk_36;
                break;
            case 37:
                poker_res = R.mipmap.pk_37;
                break;
            case 38:
                poker_res = R.mipmap.pk_38;
                break;
            case 39:
                poker_res = R.mipmap.pk_39;
                break;
            case 40:
                poker_res = R.mipmap.pk_40;
                break;
            case 41:
                poker_res = R.mipmap.pk_41;
                break;
            case 42:
                poker_res = R.mipmap.pk_42;
                break;
            case 43:
                poker_res = R.mipmap.pk_43;
                break;
            case 44:
                poker_res = R.mipmap.pk_44;
                break;
            case 45:
                poker_res = R.mipmap.pk_45;
                break;
            case 46:
                poker_res = R.mipmap.pk_46;
                break;
            case 47:
                poker_res = R.mipmap.pk_47;
                break;
            case 48:
                poker_res = R.mipmap.pk_48;
                break;
            case 49:
                poker_res = R.mipmap.pk_49;
                break;
            case 50:
                poker_res = R.mipmap.pk_50;
                break;
            case 51:
                poker_res = R.mipmap.pk_51;
                break;
            case 52:
                poker_res = R.mipmap.pk_52;
                break;
        }
        return poker_res;
    }

    public void gotoLobby() {
        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).Init();
        //   mAppViewModel.setTableId(0);
        mAppViewModel.setSerialId(0);
        mAppViewModel.setAreaId(0);
        mAppViewModel.setbLobby(true);
        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).setBigRoadOld("");


        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 0);
        AppTool.activiyJump(mContext, LobbyDragonTigerActivity.class, bundle);
    }

    //重新选择了筹码，重新开始算下注筹码
    public void initClickCount() {

    }

    public void saveBetInformation(String type, String betInfo, boolean isRepeat) {
        if (betInfo == null || betInfo.length() == 0)
            return;
        if (!"0".equals(betInfo)) {
            if (!betInfo.contains("#"))//说明只有金额
            {
                BetDetail betDetail = new BetDetail();
                betDetail.setMoney((int) Double.parseDouble(betInfo));
                betDetail.setNumber(type);
                betDetail.setType(type);
                //   Log.i(WebSiteUrl.Tag,"type="+type+",betInfo="+betInfo);
                mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getBetDetail().add(betDetail);
                if (isRepeat)
                    mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetRepeatInformation().getBetDetail().add(betDetail);
            } else {
                String strThree[] = betInfo.split("\\|");

                for (int i = 0; i < strThree.length; i++) {

                    String strThreeDetail[] = strThree[i].split("#");
                    if (strThreeDetail != null && strThreeDetail.length == 2) {
                        BetDetail betDetail = new BetDetail();
                        betDetail.setMoney((int) Double.parseDouble(strThreeDetail[1]));
                        betDetail.setNumber(strThreeDetail[0]);
                        betDetail.setType(type);
                        mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getBetDetail().add(betDetail);
                        if (isRepeat)
                            mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetRepeatInformation().getBetDetail().add(betDetail);
                    }

                }
            }

        }
    }

    public void showBetMoney(boolean bRepeat) {
        List<BetDetail> betDetails = null;
        if (bRepeat) {
            betDetails = mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetRepeatInformation().getBetDetail();
            if (betDetails.size() > 0) {
                BetUiHelper.betStateColor(tvTableBetSure, true);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentFront, mContext, mAppViewModel.getFrontVolume());
            }
        } else {
            betDetails = mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getBetDetail();
        }
        for (int i = 0; i < betDetails.size(); i++) {
            if (bRepeat)
                listBetDetail.add(betDetails.get(i));

            showBetChip(getFrameLayout(betDetails.get(i).getNumber()), true, betDetails.get(i).getMoney());


        }
        if (!bRepeat) {
            BetUiHelper.betStateColor(tvTableBetSure, false);
        }
    }

    private void showBetChip(FrameLayout f, boolean isShow, int money) {
        int height = AutoUtils.getPercentHeightSize(60);
        if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
            height=  AutoUtils.getPercentHeightSize(80);
        }
        showBetChip(f, isShow, money, height, Gravity.TOP);
    }

    private void showBetChip(final FrameLayout f, boolean isShow, int money, int y, int gravity) {
        if (f == null)
            return;
        ChipShowHelper chipHelper = ChipMap.get(f);

        if (chipHelper == null) {

            chipHelper = new ChipShowHelper(mContext, f, chipList);
            int iCount = f.getChildCount();
            chipHelper.setFirstIndex(iCount);
            chipHelper.setTextGravity(gravity);
            chipHelper.setMoneyTipsTextSize(12);
            chipHelper.setTopMargin(AutoUtils.getPercentHeightSize(7));
            chipHelper.setOperationButton(0, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DtBetType type = getTypeFrom(f);
                    singleBet(type);
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DtBetType type = getTypeFrom(f);
                    clearNoBetChip(type);
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isFirstBet){
                        DtBetType type = getTypeFrom(f);
                        clearNoBetChip(type);
                        showBetMoney(true);
                    }
                }
            });
            ChipMap.put(f, chipHelper);
        }
        //   money= chipHelper.getMoneyCount()+money;
        if (isShow && money > 0) {
            checkOperationButton();
            BetUiHelper.betStateColor(tvTableBetSure, true);
            chipHelper.showChip(money, 0, y, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), 0, y + AutoUtils.getPercentHeightSize(4), AutoUtils.getPercentHeightSize(32)*2, AutoUtils.getPercentHeightSize(20));
        } else {
            chipHelper.setOperationButtonDisplay(false);
            chipHelper.clearAllChips();
        }
    }

    private void singleBet(DtBetType type) {
        if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 2 || mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() == 5)
            return;
        if (mAppViewModel.getUser().getBalance() <= 0) {
            ToastUtils.showToast(mContext, getString(R.string.Insufficient));
            return;
        }
        if (listBetDetail.size() > 0) {
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 10, componentFront, mContext, mAppViewModel.getFrontVolume());
            //执行下注的线程
            drangonTigerBet = new DrangonTigerBet(type);
            threadDrangonTigerBet = new Thread(drangonTigerBet);
            showBlockDialog();
            Executors.newSingleThreadExecutor().execute(threadDrangonTigerBet);
        }
    }

    public FrameLayout getFrameLayout(String type) {
        switch (type) {
            case "Dragon":
                //显示筹码
                return fl_dragon;
            case "Tiger":
                //显示筹码
                return fl_tiger;
            case "Tie":
                //显示筹码
                return fl_tie;
            case "DragonOdd":
                //显示筹码
                return fl_dragon_odd;
            case "DragonEven":
                //显示筹码
                return fl_dragon_even;
            case "DragonRed":
                //显示筹码
                return fl_dragon_red;
            case "DragonBlack":
                //显示筹码
                return fl_dragon_black;
            case "TigerOdd":
                //显示筹码
                return fl_tiger_odd;
            case "TigerEven":
                //显示筹码
                return fl_tiger_even;
            case "TigerRed":
                //显示筹码
                return fl_tiger_red;
            case "TigerBlack":
                //显示筹码
                return fl_tiger_black;
            default:
                return null;
        }
    }

    public DtBetType getTypeFrom(FrameLayout f) {
        if (f.equals(fl_dragon))
            return DtBetType.Dragon;
        //显示筹码
        if (f.equals(fl_tiger))
            return DtBetType.Tiger;
        //显示筹码
        if (f.equals(fl_tie))
            return DtBetType.Tie;
        //显示筹码
        if (f.equals(fl_dragon_odd))
            return DtBetType.DragonOdd;
        //显示筹码
        if (f.equals(fl_dragon_even))
            return DtBetType.DragonEven;
        //显示筹码
        if (f.equals(fl_dragon_red))
            return DtBetType.DragonRed;
        //显示筹码
        if (f.equals(fl_dragon_black))
            return DtBetType.DragonBlack;
        //显示筹码
        if (f.equals(fl_tiger_odd))
            return DtBetType.TigerOdd;
        //显示筹码
        if (f.equals(fl_tiger_even))
            return DtBetType.TigerEven;
        //显示筹码
        if (f.equals(fl_tiger_red))
            return DtBetType.TigerRed;
        //显示筹码
        if (f.equals(fl_tiger_black))
            return DtBetType.TigerBlack;
            //显示筹码
        else
            return DtBetType.All;

    }

    public boolean checkChoose() {
        if (chooseChip < 1) {
            Toast.makeText(getApplicationContext(), getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void clickChipBet(String number, String type, int chooseChip, int minLimit, int maxLimit,
                             int alreadyBet, int totalbet, FrameLayout fl_chip) {
        if (checkChoose()) return;
        if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        Log.i(WebSiteUrl.Tag, "alreadyBet = " + alreadyBet);
        boolean isIn = false;
        boolean showBefore = false;
        int betMoney = 0;
        for (int i = 0; i < listBetDetail.size(); i++) {//连续点击，还未下注
            if (number.equals(listBetDetail.get(i).getNumber())) {
                isIn = true;
                listBetDetail.get(i).setClickCount(listBetDetail.get(i).getClickCount() + 1);
                betMoney = mAppViewModel.getBetMoney(chooseChip, minLimit,
                        maxLimit, listBetDetail.get(i).getClickCount(),
                        alreadyBet, totalbet, mContext, componentFront);
                if (betMoney > 0) {
                    listBetDetail.get(i).setMoney(betMoney);
                } else {
                    listBetDetail.remove(i);
                    showBefore = true;
                }
                break;
            }
        }
        if (isIn == false) {
            BetDetail betDetail = new BetDetail();
            betDetail.setType(type);
            betDetail.setNumber(number);
            betDetail.setClickCount(1);

            betMoney = mAppViewModel.getBetMoney(chooseChip, minLimit,
                    maxLimit, 1,
                    alreadyBet, totalbet, mContext, componentFront);
            betDetail.setMoney(betMoney);
            if (betMoney > 0) {
                listBetDetail.add(betDetail);

            } else
                showBefore = true;
        }

        if (showBefore) {
            showBetChip(fl_chip, true, alreadyBet);
            //超过了最大的值，要提醒
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        } else {
            showBetChip(fl_chip, true, betMoney);

        }


    }

    public int getNumberBetMoney(String type) {
        int betMoney = 0;
        for (int i = 0; i < listBetDetail.size(); i++) {
            if (type.equals(listBetDetail.get(i).getNumber())) {
                betMoney = listBetDetail.get(i).getMoney();
                break;
            }

        }
        return betMoney;
    }


    private void displayAll(boolean b) {
        if (!b) {
            lvTableBetLimitRed.setVisibility(View.GONE);
            toolbar_right_bottom_tv.setVisibility(View.GONE);
            toolbar_right_top_tv.setVisibility(View.GONE);
            if (lv_table_pool.getVisibility() == View.VISIBLE) {
                WidgetUtil.showAnimation(lv_table_pool, false, Gravity.BOTTOM);
            }
            toolbar.setNavigationIcon(null);
        } else {
            toolbar_right_bottom_tv.setVisibility(View.VISIBLE);
            toolbar_right_top_tv.setVisibility(View.VISIBLE);
            toolbar.setNavigationIcon(R.mipmap.back_black);
        }
        if (b) {
            stateinit = true;
            allClose = b;
            closeAll();
        } else {
            if (stateinit) {
                allClose = b;
                closeAll();
                stateinit = false;
            }
        }


    }

    private void closeAll() {
        this.allClose = !allClose;
        if (allClose) {
//            ll_chip_parent.setVisibility(View.GONE);
            ll_bet_btn_parent.setVisibility(View.GONE);

        } else {
//            ll_chip_parent.setVisibility(View.VISIBLE);
//            ll_bet_btn_parent.setVisibility(View.VISIBLE);

        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ll_bet_btn_parent.setVisibility(View.GONE);
        }

    }

    public enum DtBetType {
        Dragon,
        Tiger,
        Tie,
        DragonOdd,
        DragonEven,
        DragonRed,
        DragonBlack,
        TigerEven,
        TigerOdd,
        TigerRed,
        TigerBlack,
        All
    }

    DtBetType type = DtBetType.All;


    @Override
    protected void showPool() {
        super.showPool();
        if (lv_table_pool.getVisibility() == View.GONE)
            WidgetUtil.showAnimation(lv_table_pool, true, Gravity.BOTTOM);
        else {
            WidgetUtil.showAnimation(lv_table_pool, false, Gravity.BOTTOM);
        }
//        lv_table_pool.setVisibility(View.VISIBLE);
    }

    protected void initOrientation() {
        super.initOrientation();
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar.post(new Runnable() {
                @Override
                public void run() {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
                    layoutParams.leftMargin =ScreenUtil.dip2px(mContext, 12);
                    toolbar.setLayoutParams(layoutParams);
                }
            });
//            lv_baccarat_chips = (AdapterView) findViewById(R.id.lv_baccarat_chips);
            lv_baccarat_chips = (AdapterView) findViewById(R.id.lv_baccarat_chips_h);
            xSkizeB1 = 0.14f;
            xSkizeP1 = 0.14f;
            playerX = AutoUtils.getPercentHeightSize(800);
            bankerX = AutoUtils.getPercentHeightSize(0);
            animationHeight = -300;

        } else {

            lv_baccarat_chips = (AdapterView) findViewById(R.id.lv_baccarat_chips_h);
            leftPanel1.setOpen(true, true);
            xSkizeB1 = 0f;
            xSkizeP1 = 0f;
            playerX = AutoUtils.getPercentHeightSize(500);
            bankerX = AutoUtils.getPercentHeightSize(0);
            animationHeight = -500;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isFirstBet",isFirstBet);
    }

    @BindView(R2.id.ll_info)
    LinearLayout ll_info;
    @BindView(R2.id.lv_user_info)
    ListView lv_user_info;
    @BindView(R2.id.lv_pool)
    ListView lv_pool;
    @BindView(R2.id.tv_time)
    TextView tv_time;
    @BindView(R2.id.tv_total_bet)
    TextView tv_total_bet;
    @BindView(R2.id.tv_win_lose_bet)
    TextView tv_win_lose_bet;
    @BindView(R2.id.rl_dragon_parent)
    RelativeLayout rl_dragon_parent;
    @BindView(R2.id.img_dragon_animation)
    ImageView img_dragon_animation;
    @BindView(R2.id.rl_tiger_parent)
    RelativeLayout rl_tiger_parent;
    @BindView(R2.id.img_tiger_animation)
    ImageView img_tiger_animation;
    @BindView(R2.id.img_apng_dragon)
    ApngImageView img_apng_dragon;
    @BindView(R2.id.img_apng_tie)
    ApngImageView img_apng_tie;
    @BindView(R2.id.img_apng_tiger)
    ApngImageView img_apng_tiger;

    @Override
    protected void showMenuPop(View v) {
        showHideUserInfo();
    }

    private void showHideUserInfo(){
        if (ll_info.getLayoutParams().height>0){
            WidgetUtil.shrinkAnimation(ll_info,ScreenUtil.dip2px(mContext, 165),0);
        }else {
            WidgetUtil.shrinkAnimation(ll_info,0,ScreenUtil.dip2px(mContext, 165));
        }
    }

    private List<LiveInfoBean> updateInfoData(){
        AppTool.setAppLanguage(this, AppTool.getAppLanguage(this));
        List<LiveInfoBean> strData = new ArrayList<LiveInfoBean>();
        LiveInfoBean data;
        data = new LiveInfoBean(getString(R.string.ID), usName, "");
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.BET), mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerBetInformation().getAllBetMoney()+"", "");
        if (Integer.parseInt(data.getValue1())>0){
            tv_total_bet.setText(mAppViewModel.covertLimit(Integer.parseInt(data.getValue1()))+"");
            rightBetTv.setText(mAppViewModel.covertLimit(Integer.parseInt(data.getValue1()))+"");
        }else {
            tv_total_bet.setText(getString(R.string.BET)+" :0");
            rightBetTv.setText(getString(R.string.BET)+" :0");
        }
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.W_L), mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney()+"", "");
        if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney()>0){
            tv_win_lose_bet.setTextColor(ContextCompat.getColor(mContext,R.color.win_color));
            tv_win_lose_bet.setText(mAppViewModel.covertWinLose(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney())+"");
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext,R.color.black));
            rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney())+"");
        }else if (mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney()==0){
            tv_win_lose_bet.setTextColor(ContextCompat.getColor(mContext,R.color.yellow_gold));
            tv_win_lose_bet.setText(getString(R.string.W_L)+" :0");
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext,R.color.bet_color));
            rightWinLoseTv.setText(getString(R.string.W_L)+" :0");
        }else {
            tv_win_lose_bet.setTextColor(ContextCompat.getColor(mContext,R.color.lose_color));
            tv_win_lose_bet.setText(mAppViewModel.covertWinLose(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney())+"");
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext,R.color.banker_color));
            rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getWonMoney())+"");
        }
        strData.add(data);
        data = new LiveInfoBean(TextUtils.isEmpty(currency) ? getString(R.string.BAL) : currency, mAppViewModel.getUser().getBalance() + "", "");
        rightBalanceTv.setText(mAppViewModel.getUser().getBalance()+"");
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.LIMIT_POP), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinDragonTigerBet())
                +"-"+ mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxDragonTigerBet()), "");
        strData.add(data);
        strData.add(new LiveInfoBean(getString(R.string.dragon1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinDragonTigerBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxDragonTigerBet())));
        strData.add(new LiveInfoBean(getString(R.string.tiger1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinDragonTigerBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxDragonTigerBet())));
        strData.add(new LiveInfoBean(getString(R.string.tie_dragon_tiger), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTieBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTieBet())));
        strData.add(new LiveInfoBean(getString(R.string.dragon_odd1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        strData.add(new LiveInfoBean(getString(R.string.dragon_even1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        strData.add(new LiveInfoBean(getString(R.string.dragon_red1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        strData.add(new LiveInfoBean(getString(R.string.dragon_black1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        strData.add(new LiveInfoBean(getString(R.string.tiger_odd1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        strData.add(new LiveInfoBean(getString(R.string.tiger_even1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        strData.add(new LiveInfoBean(getString(R.string.tiger_red1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        strData.add(new LiveInfoBean(getString(R.string.tiger_black1), mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMinTotalBet())
                , mAppViewModel.covertLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getDragonTigerLimit(mAppViewModel.getDragonTiger(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet())));
        return strData;
    }

    private void setInfoData(ListView listView) {
        if (contentInfo == null)
            contentInfo = new AdapterViewContent<>(mContext, listView);
        contentInfo.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {

            @Override
            public int getBaseItemResource() {
                return R.layout.item_user_info1;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.tv_name);
                TextView tvValue = helper.retrieveView(R.id.tv_value);
                tvType.setText(item.getType());
                tvValue.setText(item.getValue1());
                if (position>4){
                    tvValue.setText(item.getValue1() + "-" + item.getValue2());
                }else {
                    if (position==2){
                        if (item.getValue1().startsWith("-")){
                            tvValue.setTextColor(ContextCompat.getColor(mContext,R.color.red_background));
                        } else if (Double.parseDouble(item.getValue1())>0){
                            tvValue.setTextColor(ContextCompat.getColor(mContext,R.color.blue_bet));
                        } else {
                            tvValue.setTextColor(ContextCompat.getColor(mContext,R.color.gold));
                        }
                    }
                    tvValue.setText(item.getValue1());
                }
            }
        });
        contentInfo.setData(updateInfoData());
    }
}
