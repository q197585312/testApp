package gaming178.com.casinogame.Activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.apng.utils.FileUtils;
import com.apng.view.ApngImageView;
import com.apng.view.ApngLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.widget.VideoHelper;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.autolayout.config.UseLandscape;
import com.zhy.autolayout.utils.AutoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.nodemedia.NodePlayerView;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Activity.entity.ApngPlayBean;
import gaming178.com.casinogame.Activity.entity.GoodRoadDataBean;
import gaming178.com.casinogame.Bean.Baccarat;
import gaming178.com.casinogame.Bean.BaccaratPlayerBetTypeBean;
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.casinogame.Bean.GameRefreshBean;
import gaming178.com.casinogame.Bean.LiveInfoBean;
import gaming178.com.casinogame.Chat.ChatEmoji;
import gaming178.com.casinogame.Chat.FaceConversionUtil;
import gaming178.com.casinogame.Chat.FaceRelativeLayout;
import gaming178.com.casinogame.Chat.MsgBean;
import gaming178.com.casinogame.Chat.ReceiveMsgBean;
import gaming178.com.casinogame.Control.PageWidgetT;
import gaming178.com.casinogame.Control.flipCallBack;
import gaming178.com.casinogame.Popupwindow.PopRule;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.BackgroudMuzicService;
import gaming178.com.casinogame.Util.BetUiHelper;
import gaming178.com.casinogame.Util.ChipShowHelper;
import gaming178.com.casinogame.Util.CountDownView;
import gaming178.com.casinogame.Util.FrontMuzicService;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.ImageRotate3D;
import gaming178.com.casinogame.Util.PopGoodRoad;
import gaming178.com.casinogame.Util.UIUtil;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.BitmapTool;
import gaming178.com.mylibrary.allinone.util.GdToastUtils;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.lib.service.Client;
import gaming178.com.mylibrary.lib.service.ISocketResponse;
import gaming178.com.mylibrary.lib.service.Packet;
import gaming178.com.mylibrary.lib.util.LogUtil;
import gaming178.com.mylibrary.myview.View.GridBackgroundView;
import gaming178.com.mylibrary.myview.miscwidgets.interpolator.BounceInterpolator;
import gaming178.com.mylibrary.myview.miscwidgets.interpolator.EasingType;
import gaming178.com.mylibrary.myview.miscwidgets.widget.Panel;

import static gaming178.com.baccaratgame.R.string.banker_home;
import static gaming178.com.baccaratgame.R.string.player_home;
import static gaming178.com.casinogame.Activity.BaccaratActivity.BaccaratBetType.Banker;
import static gaming178.com.casinogame.Activity.BaccaratActivity.BaccaratBetType.Player;

/**
 * Created by Administrator on 2016/4/11.
 */
public class BaccaratActivity extends BaseActivity implements UseLandscape {
    boolean allClose = false;
    Map<Boolean, Integer> selectedMap = new HashMap<>();
    Map<String, Integer> gameBetMap = new HashMap<>();
    Map<String, Integer> flipMap = new HashMap<>();
    private int hideType = 0;
    private AdapterView lv_baccarat_chips;
    List<Integer> otherTableIdList = new ArrayList<>();
    List<GoodRoadDataBean> goodRoadDataBeenList = new ArrayList<>();


    @OnClick(R2.id.gd__iv_baccarat_change_table)
    public void clickTable(View v) {
        showChangeTable(v);
    }


    /*
        @BindView(R2.id.gd__iv_baccarat_road_handle)
        ImageView iv;*/
    @BindView(R2.id.tv_change_bet_content)
    TextView tv_change_bet_content;
    @BindView(R2.id.gd__layout1)
    HorizontalScrollView layout1;
    @BindView(R2.id.gd__baccarat_background_gridlayout1)
    GridBackgroundView baccarat_background_gridlayout1;
    @BindView(R2.id.gd__baccarat_background_gridlayout1_big)
    GridBackgroundView baccarat_background_gridlayout1_big;
    @BindView(R2.id.gd__ll_big_road_parent2)
    LinearLayout ll_big_road_parent2;
    @BindView(R2.id.gd__ll_small_road_parent)
    LinearLayout ll_small_road_parent;
    @BindView(R2.id.gd__fl_big_road1)
    View fl_big_road1;

    @BindView(R2.id.gd__hsv_small_road_1)
    HorizontalScrollView hsv_small_road_1;
    View fl_small_road_parent1;
    GridBackgroundView smallway_item1;
    GridBackgroundView smallway_item1_big;
    @BindView(R2.id.gd__hsv_small_road_2)
    HorizontalScrollView hsv_small_road_2;
    FrameLayout fl_small_road_parent2;
    GridBackgroundView smallway_item2;
    GridBackgroundView smallway_item2_big;
    @BindView(R2.id.gd__hsv_small_road_3)
    HorizontalScrollView hsv_small_road_3;
    View fl_small_road_parent3;
    GridBackgroundView smallway_item3;
    GridBackgroundView smallway_item3_big;

    @BindView(R2.id.gd__layout2)
    View layout2;
    @BindView(R2.id.gd__baccarat_background_gridlayout2)
    GridBackgroundView baccarat_background_gridlayout2;
    @BindView(R2.id.gd__baccarat_background_gridlayout2_big)
    GridBackgroundView baccarat_background_gridlayout2_big;

    @BindView(R2.id.gd__ll_baccarat_parent)
    View ll_baccarat_parent;
    @BindView(R2.id.gd__ll_banker_ask)
    View ll_banker_ask;
    @BindView(R2.id.gd__ll_player_ask)
    View ll_player_ask;
    @BindView(R2.id.gd__rl_good_road)
    View rl_good_road;
    @BindView(R2.id.gd__tv_good_road_count)
    TextView tv_good_road_count;
    @BindView(R2.id.gd__btn_limit)
    TextView btn_limit;
    @BindView(R2.id.gd__ll_bet_btn_parent)
    LinearLayout ll_bet_btn_parent;

    @BindView(R2.id.gd__fl_bet1_bg)
    FrameLayout fl_bet1_bg;

    @BindView(R2.id.gd__fl_baccarat_a_table)
    View fl_baccarat_a_table;

    @BindView(R2.id.gd__fl_baccarat_b_table)
    View fl_baccarat_b_table;
    @BindView(R2.id.fl_bet_content_1)
    View fl_bet_content_1;
    @BindView(R2.id.fl_bet_content_2)
    View fl_bet_content_2;

    @BindView(R2.id.gd__tv_ask1)
    TextView tv_ask1;
    @BindView(R2.id.gd__tv_ask2)
    TextView tv_ask2;
    @BindView(R2.id.gd__tv_ask1_name)
    TextView tv_ask1_name;
    @BindView(R2.id.gd__tv_ask2_name)
    TextView tv_ask2_name;
    @BindView(R2.id.gd__tv_good_road_name)
    TextView tv_good_road_name;

    @BindView(R2.id.gd__iv_baccarat_chat_logo)
    TextView iv_baccarat_chat_logo;
    @BindView(R2.id.gd__fl_baccarat_chat)
    FrameLayout fl_baccarat_chat;
    @BindView(R2.id.gd__bottomPanel1)
    Panel bottomPanel1;
    @BindView(R2.id.gd__fl_baccarat_parent)
    View fl_baccarat_parent;

    @BindView(R2.id.gd__iv_baccarat_table_banker)
    ImageView iv_baccarat_table_banker;
    @BindView(R2.id.gd__iv_baccarat_table_player)
    ImageView iv_baccarat_table_player;
    @BindView(R2.id.gd__iv_baccarat_table_tie)
    ImageView iv_baccarat_table_tie;
    @BindView(R2.id.gd__iv_baccarat_table_banker_pair)
    ImageView iv_baccarat_table_banker_pair;
    @BindView(R2.id.gd__iv_baccarat_table_player_pair)
    ImageView iv_baccarat_table_player_pair;
    @BindView(R2.id.gd__fl_banker_pw_parent)
    FrameLayout fl_banker_pw_parent;
    @BindView(R2.id.gd__fl_player_pw_parent)
    FrameLayout fl_player_pw_parent;
    @BindView(R2.id.gd__v_background_player)
    View v_background_player;
    @Nullable
    @BindView(R2.id.gd__img_left_player_rotate)
    ImageView img_left_player_rotate;
    @Nullable
    @BindView(R2.id.gd__img_right_player_rotate)
    ImageView img_right_player_rotate;
    @Nullable
    @BindView(R2.id.gd__img_center_player_rotate)
    ImageView img_center_player_rotate;
    @Nullable
    @BindView(R2.id.gd__img_left_banker_rotate)
    ImageView img_left_banker_rotate;
    @Nullable
    @BindView(R2.id.gd__img_right_banker_rotate)
    ImageView img_right_banker_rotate;
    @Nullable
    @BindView(R2.id.gd__img_center_banker_rotate)
    ImageView img_center_banker_rotate;
    @BindView(R2.id.gd__v_background_banker)
    View v_background_banker;

    @BindView(R2.id.gd__iv_poker_center_banker1)
    ImageView iv_poker_center_banker1;
    @BindView(R2.id.gd__iv_poker_center_banker2)
    ImageView iv_poker_center_banker2;
    @BindView(R2.id.gd__iv_poker_center_banker3)
    ImageView iv_poker_center_banker3;
    @BindView(R2.id.gd__iv_poker_center_player1)
    ImageView iv_poker_center_player1;
    @BindView(R2.id.gd__iv_poker_center_player2)
    ImageView iv_poker_center_player2;
    @BindView(R2.id.gd__iv_poker_center_player3)
    ImageView iv_poker_center_player3;

    @BindView(R2.id.gd__fl_baccarat_table_player)
    FrameLayout fl_baccarat_table_player;
    @BindView(R2.id.gd__fl_baccarat_table_banker)
    FrameLayout fl_baccarat_table_banker;
    @BindView(R2.id.gd__fl_baccarat_table_player_bg)
    FrameLayout fl_baccarat_table_player_bg;
    @BindView(R2.id.gd__fl_baccarat_table_banker_bg)
    FrameLayout fl_baccarat_table_banker_bg;

    @BindView(R2.id.gd__fl_poker_parent)
    View fl_poker_parent;


    @BindView(R2.id.gd__tv_player_bet_money)
    TextView tv_player_bet_money;
    @BindView(R2.id.gd__tv_banker_bet_money)
    TextView tv_banker_bet_money;
    @BindView(R2.id.gd__tv_tie_bet_money)
    TextView tv_tie_bet_money;
    @BindView(R2.id.gd__tv_banker_pair_bet_money)
    TextView tv_banker_pair_bet_money;
    @BindView(R2.id.gd__tv_player_pair_bet_money)
    TextView tv_player_pair_bet_money;

    @BindView(R2.id.gd__tv_player_bet_count)
    TextView tv_player_bet_count;
    @BindView(R2.id.gd__tv_banker_pair_bet_count)
    TextView tv_banker_pair_bet_count;
    @BindView(R2.id.gd__tv_player_pair_bet_count)
    TextView tv_player_pair_bet_count;
    @BindView(R2.id.gd__tv_banker_bet_count)
    TextView tv_banker_bet_count;
    @BindView(R2.id.gd__tv_tie_bet_count)
    TextView tv_tie_bet_count;
    @BindView(R2.id.gd__fl_poker_result)
    FrameLayout fl_poker_result;

    @BindView(R2.id.gd__tv_poker_center_left)
    TextView tv_poker_center_left;

    @BindView(R2.id.gd__tv_poker_center_right)
    TextView tv_poker_center_right;

    @BindView(R2.id.gd__tv_table_bet_replay)
    TextView tvTableBetReplay;
    @BindView(R2.id.gd__tv_table_bet_sure)
    TextView tvTableBetSure;
    @BindView(R2.id.gd__tv_table_bet_cancel)
    TextView tvTableBetCancel;
    /* @BindView(R2.id.gd__tv_table_bet_pol)
     TextView tvTableBetPol;*/
    @BindView(R2.id.gd__leftPanel1)
    Panel leftPanel1;
    @BindView(R2.id.gd__handle1)
    View handle1;
    @BindView(R2.id.gd__lv_table_pool)
    ListView lv_table_pool;
    @BindView(R2.id.gd__lv_person_bet_info)
    ListView lv_person_bet_info;
    @BindView(R2.id.gd__lv_table_bet_limit_red)
    ListView lvTableBetLimitRed;
    /*  @BindView(R2.id.gd__ll_chip_parent)
      LinearLayout ll_chip_parent;*/
    @BindView(R2.id.gd__tv_table_timer)
    TextView tv_table_timer;
    @BindView(R2.id.gd__tv_mi_timer)
    TextView tv_mi_timer;
    @BindView(R2.id.gd__countdown_view)
    CountDownView countdown_view;
    @BindView(R2.id.gd__tv_menu)
    TextView tvMenu;

    @BindView(R2.id.gd__fl_poker_bottom_parent)
    View fl_poker_bottom_parent;
    @BindView(R2.id.gd__rb_chat_usual)
    RadioButton rbChatUsual;
    @BindView(R2.id.gd__rb_chat_emoticons)
    RadioButton rbChatEmoticons;
    @BindView(R2.id.gd__rb_chat_content)
    RadioButton rbChatContent;
    @BindView(R2.id.gd__rg_chat_parent)
    RadioGroup rgChatParent;
    @BindView(R2.id.gd__lv_chat_usual)
    ListView lvChatUsual;
    @BindView(R2.id.gd__fl_chat_emoticons)
    FrameLayout gvChatEmoticons;
    @BindView(R2.id.gd__lv_chat_content)
    ListView lvChatContent;
    @BindView(R2.id.gd__edt_chat_content)
    EditText edtChatContent;
    @BindView(R2.id.gd__btn_chat_send)
    TextView btnChatSend;
    @BindView(R2.id.gd__ll_chat_content)
    LinearLayout llChatContent;
    @BindView(R2.id.gd__FaceRelativeLayout)
    FaceRelativeLayout faceLayout;
    @BindView(R2.id.gd__tv_table_game_number)
    TextView tv_table_game_number;
    @BindView(R2.id.gd__tv_table_game_number1)
    TextView tv_table_game_number1;
    @BindView(R2.id.gd__tv_service_time)
    TextView serviceTime;
    @BindView(R2.id.gd__ll_result)
    LinearLayout ll_result;
    @BindView(R2.id.gd__tv_player_result)
    TextView tv_player_result;
    @BindView(R2.id.gd__tv_banker_result)
    TextView tv_banker_result;
    @BindView(R2.id.gd__img_apng_player)
    ApngImageView img_apng_player;
    @BindView(R2.id.gd__img_apng_tie)
    ApngImageView img_apng_tie;
    @BindView(R2.id.gd__img_apng_banker)
    ApngImageView img_apng_banker;
    @BindView(R2.id.gd__img_player_animation)
    ImageView img_player_animation;
    @BindView(R2.id.gd__img_banker_animation)
    ImageView img_banker_animation;
    @BindView(R2.id.gd__rl_player_parent)
    RelativeLayout rl_player_parent;
    @BindView(R2.id.gd__rl_banker_parent)
    RelativeLayout rl_banker_parent;
    @BindView(R2.id.gd__tv_player_result_name)
    TextView tv_player_result_name;
    @BindView(R2.id.gd__tv_banker_result_name)
    TextView tv_banker_result_name;

    @BindView(R2.id.gd__img_bet_bg_pp)
    ImageView img_bet_bg_pp;
    @BindView(R2.id.gd__img_bet_bg_bp)
    ImageView img_bet_bg_bp;
    @BindView(R2.id.gd__img_bet_bg_p)
    ImageView img_bet_bg_p;
    @BindView(R2.id.gd__img_bet_bg_t)
    ImageView img_bet_bg_t;
    @BindView(R2.id.gd__img_bet_bg_b)
    ImageView img_bet_bg_b;
    @BindView(R2.id.gd__img_bet_bg_lucky)
    ImageView img_bet_bg_lucky;
    @BindView(R2.id.gd__img_bet_bg_any)
    ImageView img_bet_bg_any;
    @BindView(R2.id.gd__img_bet_bg_player_n)
    ImageView img_bet_bg_player_n;
    @BindView(R2.id.gd__img_bet_bg_perfect)
    ImageView img_bet_bg_perfect;
    @BindView(R2.id.gd__img_bet_bg_banker_n)
    ImageView img_bet_bg_banker_n;
    @BindView(R2.id.gd__img_bet_bg_c_p)
    ImageView img_bet_bg_c_p;
    @BindView(R2.id.gd__img_bet_bg_c_t)
    ImageView img_bet_bg_c_t;
    @BindView(R2.id.gd__img_bet_bg_c_b)
    ImageView img_bet_bg_c_b;
    @BindView(R2.id.gd__tv_lucky_bet_money)
    TextView tv_lucky_bet_money;
    @BindView(R2.id.gd__tv_lucky_bet_count)
    TextView tv_lucky_bet_count;
    @BindView(R2.id.gd__tv_any_pair_bet_money)
    TextView tv_any_pair_bet_money;
    @BindView(R2.id.gd__tv_any_pair_bet_count)
    TextView tv_any_pair_bet_count;
    @BindView(R2.id.gd__tv_player_n_bet_money)
    TextView tv_player_n_bet_money;
    @BindView(R2.id.gd__tv_player_n_bet_count)
    TextView tv_player_n_bet_count;
    @BindView(R2.id.gd__tv_perfect_bet_money)
    TextView tv_perfect_bet_money;
    @BindView(R2.id.gd__tv_perfect_bet_count)
    TextView tv_perfect_bet_count;
    @BindView(R2.id.gd__tv_banker_n_bet_money)
    TextView tv_banker_n_bet_money;
    @BindView(R2.id.gd__tv_banker_n_bet_count)
    TextView tv_banker_n_bet_count;
    @BindView(R2.id.view_small)
    View view_small;
    @BindView(R2.id.view_big)
    View view_big;

    private AnimationDrawable animationPlayer;
    private AnimationDrawable animationBanker;
    List<ApngPlayBean> apngPlayBeanList = new ArrayList<>();
    private TextView shufflingTv;

    private NodePlayerView mPreview;
    private SurfaceHolder holder;
    private String path;
    private Bundle extras;
    private static final String MEDIA = "media";

    private boolean betInfoShowAble = false;
    private boolean personInfoShowAble = false;
    private boolean showRoad = false;
    private boolean isFirstBet = true;


    //////////////lanjian 修改

    private int tableId = 0;
    private int areaId;
    private int baccaratTimer = 0;
    AdapterViewContent<LiveInfoBean> contentPool = null;
    AdapterViewContent<LiveInfoBean> contentInfo = null;
    AdapterViewContent<String> contentBetPool = null;
    private String betType = "Banker";
    private float density;
    private boolean bUpdateRoad = true;
    private int chooseChip = 0;
    private int clickBankerCount = 0;
    private int clickPlayerCount = 0;
    private int clickTieCount = 0;
    private int clickLuckyCount = 0;
    private int clickAnyCount = 0;
    private int clickPlayerNCount = 0;
    private int clickPerfectCount = 0;
    private int clickBankerNCount = 0;
    private int clickCPlayerCount = 0;
    private int clickCTieCount = 0;
    private int clickCBankerCount = 0;
    private int clickPlayerPairCount = 0;
    private int clickBankerPairCount = 0;
    private boolean bBetSucess = false;
    private int bankerBet = 0;
    private int playerBet = 0;
    private int tieBet = 0;
    private int luckyBet = 0;
    private int anyBet = 0;
    private int playerNBet = 0;
    private int perfectBet = 0;
    private int bankerNBet = 0;
    private int cPlayerBet = 0;
    private int cTieBet = 0;
    private int cBankerBet = 0;
    private int bankerPairBet = 0;
    private int playerPairBet = 0;
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
    private ImageView iv_poker_player1;
    private ImageView iv_poker_player2;
    private ImageView iv_poker_player3;
    ImageView iv_poker_banker1;
    private ImageView iv_poker_banker2;
    private ImageView iv_poker_banker3;
    private BitmapDrawable bitmapDrawablePlayer3;
    private BitmapDrawable bitmapDrawableBanker3;
    private String gameNumber = "";
    private TextView tv_point_banker;
    private TextView tv_point_player;


    private ChipShowHelper chipHelperPlayer;
    private ChipShowHelper chipHelperBanker;
    private ChipShowHelper chipHelperPlayerPair;
    private ChipShowHelper chipHelperBankerPair;
    private ChipShowHelper chipHelperTie;
    private ChipShowHelper chipHelperLucky;
    private ChipShowHelper chipHelperAny;
    private ChipShowHelper chipHelperPlayerN;
    private ChipShowHelper chipHelperPerfect;
    private ChipShowHelper chipHelperBankerN;
    private ChipShowHelper chipHelperCPlayer;
    private ChipShowHelper chipHelperCTie;
    private ChipShowHelper chipHelperCBanker;
    private ChipShowHelper chipHelperCurrent;
    private AnimationDrawable animationDrawablePokerBanker;
    private AnimationDrawable animationDrawablePokerPlayer;


    private UpdateStatus updateStatus = null;
    private Thread threadStatus = null;
    private boolean bGetStatus = true;
    //得到结果
    private boolean isResultEnd = false;

    private VideoHelper videoHelper;
    private String tableName = "";
    private boolean canBet = true;
    PageWidgetT pw_poker_banker1;
    PageWidgetT pw_poker_banker2;
    PageWidgetT pw_poker_player1;
    PageWidgetT pw_poker_player2;
    private CountDownTimer timer;
    private TextView tv_poker_timer;
    private String gameIdNumber = "";
    private Map<String, Integer> gamePokerMap = new HashMap<>();
    private PageWidgetT pw_poker_banker3, pw_poker_player3;
    private LinearLayout ll_poker_pw;
    private View ll_poker_parent;
    private int i = 0;
    private boolean isAttached = false;
    private int hallId;
    private Client user;
    private AdapterViewContent<String> chatContent;
    private ArrayList<String> chatMsgs;
    private boolean chatShowAble = false;
    private int chipY;
    private int chipX;
    private int tieX;
    private int chipPlayerBankerY;
    private int chipPlayerBankerX;
    private int tipY;
    private boolean stateInit = false;
    private float xSkizeP1 = 0;
    private float xSkizeP2 = 0;
    private float xSkizeP3 = 0;
    private float xSkizeB1 = 0;
    private float xSkizeB2 = 0;
    private float xSkizeB3 = 0;
    private long animationTime = 500;
    private int animationHeight = -820;
    private float banker2x;
    private float banker1x;
    private float banker3x;
    private float player1x;
    private float player2x;
    private float player3x;


    public class UpdateStatus implements Runnable {

        public void run() {
            while (bGetStatus) {
                try {
                    handler.sendEmptyMessage(HandlerCode.UPDATE_STATUS);
                    Thread.sleep(1050);
                } catch (InterruptedException e) {
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
                        + "&Xhid=" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + "&Blid=" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber() +
                        "&Xh=" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.BJL_BET_MONEY_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateBetMoney params= " + params);
                Log.i("dfsafa", "UpdateBetMoney = " + strRes);

                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 10) {
                        if ("".equals(strInfo[2]) || strInfo[2] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setAllBetMoney(0);
                        else {
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setAllBetMoney((int) Double.parseDouble(strInfo[2]));
                            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAllBetMoney() > 0)
                                bBetSucess = true;
                        }
                        if ("".equals(strInfo[4]) || strInfo[4] == null) {
                            //    Log.i(WebSiteUrl.Tag, "setBanker");
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBanker(0);
                        } else {
                            //    Log.i(WebSiteUrl.Tag, "setBanker");
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBanker((int) Double.parseDouble(strInfo[4]));
                            gameBetMap.put(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber(), 2);
                            //   Log.i(WebSiteUrl.Tag, "UpdateBetMoney Banker= " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker());
//                            setFlipBankerEnable(false);
                        }
                        if ("".equals(strInfo[3]) || strInfo[3] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPlayer(0);
                        else {
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPlayer((int) Double.parseDouble(strInfo[3]));
                            gameBetMap.put(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber(), 1);
//                            setFlipPlayerEnable(false);
                        }

                        if ("".equals(strInfo[5]) || strInfo[5] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setTie(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setTie((int) Double.parseDouble(strInfo[5]));
                        if ("".equals(strInfo[6]) || strInfo[6] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBankerPair(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBankerPair((int) Double.parseDouble(strInfo[6]));
                        if ("".equals(strInfo[7]) || strInfo[7] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPlayerPair(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPlayerPair((int) Double.parseDouble(strInfo[7]));
                        if ("".equals(strInfo[8]) || strInfo[8] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBig(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBig((int) Double.parseDouble(strInfo[8]));
                        if ("".equals(strInfo[9]) || strInfo[9] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setSmall(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setSmall((int) Double.parseDouble(strInfo[9]));

                        if ("".equals(strInfo[10]) || strInfo[10] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setLucky(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setLucky((int) Double.parseDouble(strInfo[10]));
                        if ("".equals(strInfo[11]) || strInfo[11] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setAny(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setAny((int) Double.parseDouble(strInfo[11]));
                        if ("".equals(strInfo[12]) || strInfo[12] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPerfect(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPerfect((int) Double.parseDouble(strInfo[12]));
                        if ("".equals(strInfo[13]) || strInfo[13] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBankerN(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBankerN((int) Double.parseDouble(strInfo[13]));
                        if ("".equals(strInfo[14]) || strInfo[14] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPlayerN(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPlayerN((int) Double.parseDouble(strInfo[14]));
                        if ("".equals(strInfo[15]) || strInfo[15] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setCowBanker(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setCowBanker((int) Double.parseDouble(strInfo[15]));
                        if ("".equals(strInfo[16]) || strInfo[16] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setCowPlayer(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setCowPlayer((int) Double.parseDouble(strInfo[16]));
                        if ("".equals(strInfo[17]) || strInfo[17] == null)
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setCowTie(0);
                        else
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setCowTie((int) Double.parseDouble(strInfo[17]));

                        handler.sendEmptyMessage(HandlerCode.SHOW_BET_MONEY);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private BaccaratBet baccaratBet = null;
    private Thread threadBaccaratBet = null;
    BaccaratBetType type = BaccaratBetType.All;
    volatile boolean isCanbet = true;

    public class BaccaratBet implements Runnable {
        public BaccaratBet(BaccaratBetType type) {
            BaccaratActivity.this.type = type;

        }


        public void run() {
            if (isCanbet) {
                isCanbet = false;
                try {
                    String player = "0";
                    String banker = "0";
                    String tie = "0";
                    String lucky = "0";
                    String any = "0";
                    String playerN = "0";
                    String perfect = "0";
                    String bankerN = "0";
                    String cPlayer = "0";
                    String cTie = "0";
                    String cBanker = "0";
                    String playerPair = "0";
                    String bankerPair = "0";

                    if (playerBet > 0 && (type == BaccaratBetType.All || type == Player))
                        player = "" + (playerBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer());
                    if (bankerBet > 0 && (type == BaccaratBetType.All || type == Banker))
                        banker = "" + (bankerBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker());
                    if (tieBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.Tie))
                        tie = "" + (tieBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getTie());

                    if (luckyBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.Lucky))
                        lucky = "" + (luckyBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getLucky());
                    if (anyBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.Any))
                        any = "" + (anyBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAny());
                    if (playerNBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.PlayerN))
                        playerN = "" + (playerNBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerN());
                    if (perfectBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.Perfect))
                        perfect = "" + (perfectBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPerfect());
                    if (bankerNBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.BankerN))
                        bankerN = "" + (bankerNBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerN());
                    if (cPlayerBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.CowPlayer))
                        cPlayer = "" + (cPlayerBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer());
                    if (cTieBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.CowTie))
                        cTie = "" + (cTieBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowTie());
                    if (cBankerBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.CowBanker))
                        cBanker = "" + (cBankerBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker());

                    if (playerPairBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.PlayerPair))
                        playerPair = "" + (playerPairBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerPair());
                    if (bankerPairBet > 0 && (type == BaccaratBetType.All || type == BaccaratBetType.BankerPair))
                        bankerPair = "" + (bankerPairBet - mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerPair());

                    String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                            + "&Xhid=" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + "&Blid=" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber()
                            + "&Xh=" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet()
                            + "&Areaid=" + mAppViewModel.getAreaId() + "&Serial=" + mAppViewModel.getSerialId() + "&Hl=1"
                            + "&Player=" + player + "&Banker=" + banker + "&Tie=" + tie + "&Bd=" + bankerPair + "&Pd=" + playerPair
                            + "&Big=0&Small=0";

                    params += "&Lucky6=" + lucky + "&AnyPairs=" + any + "&NPlayer=" + playerN + "&PerfectPairs=" + perfect + "&NBanker=" + bankerN +
                            "&CowPlayer=" + cPlayer + "&CowTie=" + cTie + "&CowBanker=" + cBanker;

                    String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.BJL_BET_URL, params);
                    Log.i("BaccaratBet", "BaccaratBet params= " + params);
                    Log.i("BaccaratBet", "BaccaratBet = " + strRes);
                    String strInfo[] = strRes.split("#");
                    if (strRes.startsWith("Results=ok")) {
                        if (strInfo.length >= 10) {
                            if (isFirstBet) {
                                isFirstBet = false;
                            }
                            mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                            double resMoney = Double.parseDouble(strInfo[4]);
                            //清除之前的下注记录
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().Init();

                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBanker((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setBanker((int) resMoney);

                            resMoney = Double.parseDouble(strInfo[2]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setAllBetMoney((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setAllBetMoney((int) resMoney);

                            resMoney = Double.parseDouble(strInfo[3]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPlayer((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setPlayer((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[5]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setTie((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setTie((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[6]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBankerPair((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setBankerPair((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[7]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPlayerPair((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setPlayerPair((int) resMoney);

                            resMoney = Double.parseDouble(strInfo[10]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setLucky((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setLucky((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[11]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setAny((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setAny((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[12]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPerfect((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setPerfect((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[13]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setBankerN((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setBankerN((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[14]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setPlayerN((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setPlayerN((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[15]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setCowBanker((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setCowBanker((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[16]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setCowPlayer((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setCowPlayer((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[17]);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().setCowTie((int) resMoney);
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().setCowTie((int) resMoney);


                            //提示下注成功,清除下注信息
                            bBetSucess = true;
                            betTimeCount = 0;
                            clearAllShowChip();
                            handler.sendEmptyMessage(HandlerCode.SHOW_BET_SUCCESS);


                        }

                        if (!player.equals("0") || !cPlayer.equals("0")) {
                            gameBetMap.put(gameIdNumber, 1);
                            setBigPoker(pw_poker_player1, 1);
                            setBigPoker(pw_poker_player2, 2);
                        } else if (!banker.equals("0") || !cBanker.equals("0")) {
                            gameBetMap.put(gameIdNumber, 2);
                            setBigPoker(pw_poker_banker1, 1);
                            setBigPoker(pw_poker_banker2, 2);
                        }
                        if (!player.equals("0") && !cBanker.equals("0")) {
                            gameBetMap.put(gameIdNumber, 3);
                            setSmallPoker(pw_poker_player1, 1);
                            setSmallPoker(pw_poker_player2, 2);
                            setSmallPoker(pw_poker_banker1, 1);
                            setSmallPoker(pw_poker_banker2, 2);
                        }
                        if (!banker.equals("0") && !cPlayer.equals("0")) {
                            gameBetMap.put(gameIdNumber, 3);
                            setSmallPoker(pw_poker_player1, 1);
                            setSmallPoker(pw_poker_player2, 2);
                            setSmallPoker(pw_poker_banker1, 1);
                            setSmallPoker(pw_poker_banker2, 2);
                        }
                        if (!cPlayer.equals("0") && !cBanker.equals("0")) {
                            gameBetMap.put(gameIdNumber, 3);
                            setSmallPoker(pw_poker_player1, 1);
                            setSmallPoker(pw_poker_player2, 2);
                            setSmallPoker(pw_poker_banker1, 1);
                            setSmallPoker(pw_poker_banker2, 2);
                        }
                    } else {//提示下注失败
                        handler.sendEmptyMessage(HandlerCode.SHOW_BET_ERROR);
                    }
                    isCanbet = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    isCanbet = true;
                    handler.sendEmptyMessage(HandlerCode.THREAD_ERROR);
                } finally {
                    isCanbet = true;
                }
            }

        }

    }

    private void setBigPoker(PageWidgetT pw_poker, int orientation) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(view_big.getWidth(), view_big.getHeight());
        if (orientation == 1) {
            layoutParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
            layoutParams.leftMargin = ll_baccarat_parent.getWidth() / 9;
        } else {
            layoutParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
            layoutParams.rightMargin = ll_baccarat_parent.getWidth() / 9;
        }
        layoutParams.bottomMargin = view_big.getTop();
        pw_poker.setLayoutParams(layoutParams);
        pw_poker.setImageCurRes(R.mipmap.gd_poker_bg);
        pw_poker.coverPic();
    }

    private void setSmallPoker(PageWidgetT pw_poker, int orientation) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(view_small.getWidth(), view_small.getHeight());
        if (orientation == 1) {
            layoutParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
        } else {
            layoutParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        }
        layoutParams.bottomMargin = view_small.getTop();
        pw_poker.setLayoutParams(layoutParams);
        pw_poker.setImageCurRes(R.mipmap.gd_poker_bg);
        pw_poker.coverPic();
    }

    public enum BaccaratBetType {
        Player,
        Banker,
        Tie,
        Lucky,
        Any,
        PlayerN,
        Perfect,
        BankerN,
        CowPlayer,
        CowTie,
        CowBanker,
        PlayerPair,
        BankerPair,
        All
    }

    private UpdateRoad updateRoad = null;
    private Thread threadUpdateRoad = null;

    public class UpdateRoad implements Runnable {
        public void run() {

            try {
                if (bUpdateRoad == false)
                    Thread.sleep(3000);
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.BJL_LUZI_URL, params);
                //    Log.i(WebSiteUrl.Tag, "UpdateRoad params= " + params);
                String strInfo[] = strRes.split("\\|");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
//                        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setBigRoad(strInfo[1]);
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
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.BJL_TABLE_GAMENUM, params);
                Log.i(WebSiteUrl.Tag, "UpdateGameNumber params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateGameNumber = " + strRes + ",gameNumber=" + gameNumber);
                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                        LogUtil.d("tv_baccarat_shoe_number", "UpdateGameNumber,setShoeNumber:" + strInfo[1] + "," + strInfo[2]);
                        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setShoeNumber(strInfo[1]);
                        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setGameNumber(strInfo[2]);
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
//                Thread.sleep(2000);
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                        + "&Xhid=" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + "&Blid=" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber() +
                        "&Xh=" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.BJL_WON_MONEY_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateWonMoney params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateWonMoney = " + strRes);

                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                        if (baccaratTimer == 0 && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 5) {
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setWonMoney(Double.parseDouble(strInfo[2]));
                        } else {
                            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setWonMoney(0);
                        }
                        mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                        if (isShowWinLose) {
                            handler.sendEmptyMessage(HandlerCode.SHOW_WIN_LOSS);
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void isRemove(FrameLayout fl) {
        if (fl != null) {
            fl.removeAllViews();
        }
    }

    private int currentShufflingStatus = 1;//1是没有洗牌2是洗牌中
    private int finalShufflingStatus = -1;

    public void updateTimer() {
        if (mAppViewModel.getBaccarat(tableId).getGameStatus() == 8) {
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
        if (baccaratTimer == 0 && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 1 && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getTimer() > 0) {
            if (isShowWinLose) {
                isShowWinLose = false;
                updateWonMoney = new UpdateWonMoney();
                threadUpdateWonMoney = new Thread(updateWonMoney);
                threadUpdateWonMoney.start();
            }
            mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setWonMoney(0);
    /*        if (updateGameNumber == null) {
                updateGameNumber = new UpdateGameNumber();
                threadUpdateGameNumber = new Thread(updateGameNumber);
                threadUpdateGameNumber.start();
            }*/
            if (!gameNumber.equals(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber())) {
                clearAllShowChip();
                gameNumber = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber();
                baccaratTimer = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getTimer();
                countdown_view.setCountdownTime(baccaratTimer);
                countdown_view.startCountDown();
                isCanRefreshBetImg = true;
                for (int j = 0; j < userBetList.size(); j++) {
                    userBetMap.put(userBetList.get(j), "");
                }
                if (tableId > 3 && tableId != 71) {
                    isRemove(fl_user_bet_img1);
                    isRemove(fl_user_bet_img2);
                    isRemove(fl_user_bet_img3);
                    isRemove(fl_user_bet_img5);
                    isRemove(fl_user_bet_img6);
                    isRemove(fl_user_bet_img7);
                    isRemove(fl_user_bet_img8);
                }
                stateInit = true;
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).Init();
                clearAllChips();
                //    Log.i(WebSiteUrl.Tag,"-------baccaratTimer="+baccaratTimer);
                bUpdateRoad = true;
                bBetSucess = false;
                betTimeCount++;
                if (betTimeCount == 6)//跳转到大厅
                {
                    if (!WidgetUtil.isRunBackground(BaccaratActivity.this)) {
                        GdToastUtils.showBackToast(mContext, getString(R.string.friendly_message), getString(R.string.show_back_lobby));
                    }
                    backLobby = new BackLobby();
                    threadBackLobby = new Thread(backLobby);
                    threadBackLobby.start();
                } else if (betTimeCount == 4) {
                    if (!WidgetUtil.isRunBackground(BaccaratActivity.this)) {
                        GdToastUtils.showBackToast(mContext, getString(R.string.friendly_message), getString(R.string.three_no_bet));
                    }
                }
                tvTableBetSure.setEnabled(true);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, 2, componentFront, mContext, mAppViewModel.getFrontVolume());
                gameIdNumber = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber();
                tv_table_game_number.setText(tableName + ":" + gameIdNumber);
                tv_table_game_number1.setText(tv_table_game_number.getText().toString());
                tv_baccarat_shoe_number.setText(gameIdNumber);
                LogUtil.d("tv_baccarat_shoe_number1", tableName + ":" + gameIdNumber);
            }

            //需要调用一下gamegum.jsp接口，更新局号
            initPokerState();
            if (tableId != 71) {

                hidePoker(3);

            } else {
                if (bottomPanel1.isOpen()) {
                    bottomPanel1.setOpen(false, true);
                }
            }
        } else if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 5)//延迟3秒调用路子接口
        {

            showPoker();
            //更新路子
            if (bUpdateRoad) {
                if (tableId == 71) {
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        final int heightPixels = WidgetUtil.getScreenHeight(this);
                        final int[] location3 = new int[2];
                        ll_result.getLocationOnScreen(location3);//获取在整个屏幕内的绝对坐标
                        if (location3[1] >= heightPixels && isCanShowResult) {
                            isCanShowResult = false;
                            WidgetUtil.chipTranslateAnimation(ll_result, 0, ScreenUtil.dip2px(mContext, -50), new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

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

                    } else {
                        int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(BaccaratActivity.this);
                        if (isCanShowResult) {
                            isCanShowResult = false;
                            WidgetUtil.chipPortraitTranslateAnimation(ll_result, portraitScreenWidth, 0, new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    if (ll_result != null) {
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
                }
                bUpdateRoad = false;
                updateRoad = new UpdateRoad();
                threadUpdateRoad = new Thread(updateRoad);
                threadUpdateRoad.start();
                showResultsOnUI();
                //检查输赢
                isShowWinLose = true;
                updateWonMoney = new UpdateWonMoney();
                threadUpdateWonMoney = new Thread(updateWonMoney);
                threadUpdateWonMoney.start();
                if (tableId == 71) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            hidePoker(3);
                        }
                    }, 3000);
                }
//                tablePop.setTablesData(afbApp, games);
            }
        } else if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 2) {
 /*           if (!gameNumber.equals(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber())) {//如果第一局下注完，返回大厅，第2局开牌或者出结果的时候 需要清除筹码
                //        Log.i(WebSiteUrl.Tag, "gameNumber="+gameNumber+",gameNumber1="+mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber());
                clearAllChips();
            }*/
            if (playerBet > 0 || bankerBet > 0 || tieBet > 0 || bankerPairBet > 0 || playerPairBet > 0 || luckyBet > 0 || anyBet > 0 ||
                    playerNBet > 0 || perfectBet > 0 || bankerNBet > 0 || cPlayerBet > 0 || cTieBet > 0 || cBankerBet > 0) {

                clearNoBetChip();
            }
            showPoker();
            if (tvTableBetSure.isEnabled()) {
                tvTableBetSure.setEnabled(false);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_NOMOREBETS, 3, componentFront, mContext, mAppViewModel.getFrontVolume());
            }
        }
        Log.d("GameStatus123", "bUpdateRoad: " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus());

        if ("0".equals(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber())) {
            tv_table_game_number.setText(tableName + ":" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - 0");
            tv_table_game_number1.setText(tv_table_game_number.getText().toString());
        }
//        Log.d("GameStatus123", "GameStatus: "+mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus());
        Log.d("betTimeCount", "GameStatus: " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus());
        Log.d("betTimeCount", "gameNumber: " + gameNumber);
        Log.d("betTimeCount", "betTimeCount: " + betTimeCount);
        Log.d("betTimeCount", "getGameNumber: " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber());
    }

    private void initPokerState() {
        flipMap.clear();
        tv_point_banker.setText("0");
        tv_point_player.setText("0");
        tv_banker_result.setText("0");
        tv_player_result.setText("0");
        tv_poker_center_left.setText(getString(player_home) + " 0");
        tv_poker_center_right.setText(getString(banker_home) + " 0");
        fl_poker_result.setVisibility(View.GONE);
//        tv_table_timer.setVisibility(View.VISIBLE);
        hideType = 0;
    }

    private void displayAll(boolean b) {
        if (!b) {
            lvTableBetLimitRed.setVisibility(View.GONE);
            if (lv_table_pool.getVisibility() == View.VISIBLE) {
                WidgetUtil.showAnimation(lv_table_pool, false, Gravity.BOTTOM);
            }
            toolbar.setNavigationIcon(null);
        } else {
            toolbar.setNavigationIcon(R.mipmap.gd_back_black);
        }
        showHideUserInfo();
        allClose = b;
        closeAll();

    }

    private boolean isCanRefreshBetImg = true;
    boolean isCanShowChip = true;
    boolean isCanHideChip = true;
    boolean isCanShowResult = true;
    boolean isCanHideResult = true;

    public void updateInterface() {
        if (baccaratTimer > 0 && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 2) {
            for (int i = 0; i < apngPlayBeanList.size(); i++) {
                if (apngPlayBeanList.get(i).getApngImageView().getVisibility() == View.VISIBLE) {
                    apngPlayBeanList.get(i).getApngImageView().stop();
                    apngPlayBeanList.get(i).getApngImageView().setVisibility(View.INVISIBLE);
                    break;
                }
            }
            if ((objectAnimatorB.isRunning() || objectAnimatorP.isRunning() || objectAnimatorT.isRunning()) &&
                    mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 1 && baccaratTimer > 0) {
                objectAnimatorB.cancel();
                objectAnimatorP.cancel();
                objectAnimatorT.cancel();
                objectAnimatorBP.cancel();
                objectAnimatorPP.cancel();
                objectAnimatorLucky.cancel();
                objectAnimatorAny.cancel();
                objectAnimatorPN.cancel();
                objectAnimatorPerfect.cancel();
                objectAnimatorBN.cancel();
                objectAnimatorCP.cancel();
                objectAnimatorCT.cancel();
                objectAnimatorCB.cancel();
                clearBetBg();
                animationPlayer.stop();
                animationPlayer.selectDrawable(0);
                animationBanker.stop();
                animationBanker.selectDrawable(0);
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                final int heightPixels = WidgetUtil.getScreenHeight(this);
                final int[] location2 = new int[2];
                lv_baccarat_chips.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                int[] location3 = new int[2];
                ll_result.getLocationOnScreen(location3);//获取在整个屏幕内的绝对坐标
                if (location3[1] < heightPixels && isCanHideResult && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 1) {
                    isCanHideResult = false;
                    WidgetUtil.chipTranslateAnimation(ll_result, ScreenUtil.dip2px(mContext, -50), 0, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideResult = true;
                            if (location2[1] >= heightPixels && isCanShowChip && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 1) {
                                isCanShowChip = false;
                                WidgetUtil.chipTranslateAnimation(lv_baccarat_chips, 0, ScreenUtil.dip2px(mContext, -54), new Animator.AnimatorListener() {
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
                } else {
                    if (location2[1] >= heightPixels && isCanShowChip && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 1) {
                        isCanShowChip = false;
                        WidgetUtil.chipTranslateAnimation(lv_baccarat_chips, 0, ScreenUtil.dip2px(mContext, -54), new Animator.AnimatorListener() {
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

            } else {
                int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(this);
                int[] location2 = new int[2];
                ll_result.getLocationOnScreen(location2);
                if (location2[0] == 0 && isCanHideResult && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 1) {
                    isCanHideResult = false;
                    WidgetUtil.chipPortraitTranslateAnimation(ll_result, 0, portraitScreenWidth, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideResult = true;
                            int[] location = new int[2];
                            lv_baccarat_chips.getLocationOnScreen(location);
                            int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(BaccaratActivity.this);
                            if (location[0] >= portraitScreenWidth && isCanShowChip && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 1) {
                                isCanShowChip = false;
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
                } else {
                    int[] location = new int[2];
                    lv_baccarat_chips.getLocationOnScreen(location);
                    if (location[0] >= portraitScreenWidth && isCanShowChip && mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 1) {
                        isCanShowChip = false;
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
            baccaratTimer--;
            tv_table_timer.setText("" + baccaratTimer);
//            if (baccaratTimer == 10) {
//                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_TIMER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
//            }
            if (baccaratTimer < 6) {
                tv_table_timer.setTextColor(getResources().getColor(R.color.red));
                mAppViewModel.startFrontMuzicService("TIMER", 1, componentFront, mContext, mAppViewModel.getFrontVolume());
            } else
                tv_table_timer.setTextColor(getResources().getColor(R.color.white));
            //    tv_table_timer.setTextColor(getResources().getColor(R.color.green500));
            //    Log.i(WebSiteUrl.Tag,"baccaratTimer = "+baccaratTimer);
            // tv_table_timer.setTextSize(18);
            //
        } else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                final int heightPixels = WidgetUtil.getScreenHeight(this);
                int[] location2 = new int[2];
                lv_baccarat_chips.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                final int[] location3 = new int[2];
                ll_result.getLocationOnScreen(location3);//获取在整个屏幕内的绝对坐标
                if (location2[1] < heightPixels && isCanHideChip && (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 2 || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 5)) {
                    isCanHideChip = false;
                    WidgetUtil.chipTranslateAnimation(lv_baccarat_chips, ScreenUtil.dip2px(mContext, -54), 0, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideChip = true;
                            if (tableId != 71) {
                                if (location3[1] >= heightPixels && isCanShowResult && (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 2 || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 5)) {
                                    isCanShowResult = false;
                                    WidgetUtil.chipTranslateAnimation(ll_result, 0, ScreenUtil.dip2px(mContext, -50), new Animator.AnimatorListener() {
                                        @Override
                                        public void onAnimationStart(Animator animation) {

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
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            isCanHideChip = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                } else {
                    if (tableId != 71) {
                        if (location3[1] >= heightPixels && isCanShowResult && (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 2 || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 5)) {
                            isCanShowResult = false;
                            WidgetUtil.chipTranslateAnimation(ll_result, 0, ScreenUtil.dip2px(mContext, -50), new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

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
                }
            } else {
                int[] location = new int[2];
                lv_baccarat_chips.getLocationOnScreen(location);
                int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(this);
                if (location[0] == 0 && isCanHideChip && (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 2 || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 5)) {
                    isCanHideChip = false;
                    WidgetUtil.chipPortraitTranslateAnimation(lv_baccarat_chips, 0, portraitScreenWidth, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideChip = true;
                            if (tableId == 71) {
                                return;
                            }
                            int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(BaccaratActivity.this);
                            if (isCanShowResult && (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 2 || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 5)) {
                                isCanShowResult = false;
                                WidgetUtil.chipPortraitTranslateAnimation(ll_result, portraitScreenWidth, 0, new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        if (ll_result != null) {
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
            isCanRefreshBetImg = false;
            baccaratTimer = 0;
            tv_table_timer.setText("" + baccaratTimer);
//            if (stateInit)
//                displayAll(false);
//            stateInit = false;
//            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 2) {
//
//            }
        }
        String serverTimer = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getServerTime();
        String time = "";
        if (serverTimer != null && serverTimer.indexOf("-") > 0)
            time = "GMT+7  " + serverTimer.substring(serverTimer.indexOf("-") + 1, serverTimer.length());
        tv_time.setText(time);

        updateTablePool();
        updateInfo();
        updateBetPool();
        if (!isShowAskRoad) {
            mAppViewModel.updateRoad(mContext, density, mAppViewModel.getBaccarat(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road, baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road
                    , tv_baccarat_shoe_number, tv_baccarat_total_number, tv_baccarat_banker_number, tv_baccarat_player_number, tv_baccarat_tie_number
                    , tv_baccarat_bp_number, tv_baccarat_pp_number, null, null, isBigShow, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3);
        }
        updatePool();
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 5) {
            timerOnFinish();
        }
        String serverGameNumber = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber();
        if (!StringUtils.isNull(serverGameNumber) && !serverGameNumber.equals("0")) {
            gameIdNumber = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + serverGameNumber;
            tv_table_game_number.setText(tableName + ":" + gameIdNumber);
            tv_table_game_number1.setText(tv_table_game_number.getText().toString());
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
                        layoutParams.width = ScreenUtil.dip2px(mContext, 113);
                    } else {
                        layoutParams.width = ScreenUtil.dip2px(mContext, 162);
                    }
                    layout1.setLayoutParams(layoutParams);
                    density = ScreenUtil.getDisplayMetrics(mContext).density;
                }
                mAppViewModel.getBaccarat(tableId).setBigRoadOld("");
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
                mAppViewModel.getBaccarat(tableId).setBigRoadOld("");
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
                mAppViewModel.getBaccarat(tableId).setBigRoadOld("");
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
                mAppViewModel.getBaccarat(tableId).setBigRoadOld("");
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
                mAppViewModel.getBaccarat(tableId).setBigRoadOld("");
            }
        });
        //test
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
                popGoodRoad.setContent();
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    int windowPos[] = new int[2];
                    rl_good_road.getLocationOnScreen(windowPos);
                    popGoodRoad.showPopupGravityWindow(Gravity.TOP | Gravity.LEFT, windowPos[0], 0);
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
            return R.mipmap.gd_img_road1;
        } else if (name.equals(getString(R.string.GoodRoad_2))) {
            return R.mipmap.gd_img_road2;
        } else if (name.equals(getString(R.string.GoodRoad_3))) {
            return R.mipmap.gd_img_road3;
        } else if (name.equals(getString(R.string.GoodRoad_4))) {
            return R.mipmap.gd_img_road4;
        } else if (name.equals(getString(R.string.GoodRoad_5))) {
            return R.mipmap.gd_img_road5;
        } else if (name.equals(getString(R.string.GoodRoad_6))) {
            return R.mipmap.gd_img_road6;
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
            mAppViewModel.updateAskRoad(mContext, density, mAppViewModel.getBaccarat(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                    baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBigRoad() + type,
                    isBigShow, true, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, true);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateAskRoad(mContext, density, mAppViewModel.getBaccarat(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBigRoad(),
                            isBigShow, false, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, false);
                }
            }, 500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateAskRoad(mContext, density, mAppViewModel.getBaccarat(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBigRoad() + type,
                            isBigShow, true, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, false);
                }
            }, 1000);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateAskRoad(mContext, density, mAppViewModel.getBaccarat(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBigRoad(),
                            isBigShow, false, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, false);
                }
            }, 1500);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateAskRoad(mContext, density, mAppViewModel.getBaccarat(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBigRoad() + type,
                            isBigShow, true, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, false);
                }
            }, 2000);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.updateAskRoad(mContext, density, mAppViewModel.getBaccarat(mAppViewModel.getTableId()), baccarat_head_road, baccarat_big_road,
                            baccarat_bigeyes_road, baccarat_smalleyes_road, baccarat_roach_road, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBigRoad(),
                            isBigShow, false, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3, true);
                    isShowAskRoad = false;
                }
            }, 2500);
        }
    }

    private boolean isShowAskRoad = false;

    private void updatePool() {
        contentPool.setData(getPoolData());
    }

    public void timerOnFinish() {
        if (timer != null) {
            timer.onFinish();
        }
    }

    public void updateBetMoney() {
        tv_banker_bet_money.setText(bankerLotteryPool);
        tv_player_bet_money.setText(playerLotteryPool);
        tv_tie_bet_money.setText(tieLotteryPool);
        tv_banker_pair_bet_money.setText(bpLotteryPool);
        tv_player_pair_bet_money.setText(ppLotteryPool);

        tv_lucky_bet_money.setText(luckySixMoney);
        tv_lucky_bet_count.setText(luckySixPeople);
        tv_any_pair_bet_money.setText(anyPairMoney);
        tv_any_pair_bet_count.setText(anyPairPeople);
        tv_player_n_bet_money.setText(playerNaturalMoney);
        tv_player_n_bet_count.setText(playerNaturalPeople);
        tv_perfect_bet_money.setText(perfectPairMoney);
        tv_perfect_bet_count.setText(perfectPairPeople);
        tv_banker_n_bet_money.setText(bankerNaturalMoney);
        tv_banker_n_bet_count.setText(bankerNaturalPeople);

        tv_banker_bet_count.setText(bankerPeople.equals("null") ? "0" : bankerPeople);
        tv_player_bet_count.setText(playerPeople.equals("null") ? "0" : playerPeople);
        tv_tie_bet_count.setText(tiePeople.equals("null") ? "0" : tiePeople);
        tv_banker_pair_bet_count.setText(bpPeople.equals("null") ? "0" : bpPeople);
        tv_player_pair_bet_count.setText(ppPeople.equals("null") ? "0" : ppPeople);
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
                    gameIdNumber = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber();
                    tv_table_game_number.setText(tableName + ":" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber());
                    tv_table_game_number1.setText(tv_table_game_number.getText().toString());
                    tv_baccarat_shoe_number.setText("" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber());
                    LogUtil.d("tv_baccarat_shoe_number4", mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber());

                    if (!gameNumber.equals(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber())) {//如果第一局下注完，返回大厅，第2局开牌或者出结果的时候 需要清除筹码
                        //        Log.i(WebSiteUrl.Tag, "gameNumber="+gameNumber+",gameNumber1="+mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber());
                        clearAllChips();
                    }
                    break;
                case HandlerCode.SHOW_WIN_LOSS:
                    serviceTime.setText(mAppViewModel.covertBalance((int) mAppViewModel.getUser().getBalance()));
                    //提示输赢
                    if (bBetSucess) {
//                        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney() >= 0) {
//                            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney() > 0)
//                                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_RESULTS, 7, componentFront, mContext, mAppViewModel.getFrontVolume());
//                            ToastUtils.showToast(mContext, getResources().getString(R.string.show_win) + " " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney(), ContextCompat.getColor(mContext,R.color.blue_word));
//                        } else if (/*Double.parseDouble(*/mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()/*)*/ == 0) {
//                            ToastUtils.showToast(mContext, getResources().getString(tie), Color.GREEN);
//
//                        } else
//                            ToastUtils.showToast(mContext, getResources().getString(R.string.show_loss) + " " + (-mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()), Color.RED);
                        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney() > 0 && !WidgetUtil.isRunBackground(BaccaratActivity.this)) {
                            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_RESULTS, 7, componentFront, mContext, mAppViewModel.getFrontVolume());
                            GdToastUtils.showWinningToast(mContext, getResources().getString(R.string.show_win) + " " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney(), ContextCompat.getColor(mContext, R.color.gold));
                        }
                        if (mAppViewModel.getTableId() == 71) {
                            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney() > 0) {
                                tv_win_lose_bet.setTextColor(ContextCompat.getColor(mContext, R.color.win_color));
                                tv_win_lose_bet.setText(mAppViewModel.covertWinLose(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()) + "");
                                rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                                rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()) + "");
                            } else if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney() == 0) {
                                tv_win_lose_bet.setTextColor(ContextCompat.getColor(mContext, R.color.yellow_gold));
                                tv_win_lose_bet.setText(getString(R.string.W_L) + " :0");
                                rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.bet_color));
                                rightWinLoseTv.setText(getString(R.string.W_L) + " :0");
                            } else {
                                tv_win_lose_bet.setTextColor(ContextCompat.getColor(mContext, R.color.lose_color));
                                tv_win_lose_bet.setText(mAppViewModel.covertWinLose(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()) + "");
                                rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.banker_color));
                                rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()) + "");
                            }
                        }
                    }
                    //清除所有的下注的筹码
//                    clearAllChips();

                    break;
                case HandlerCode.SHOW_BET_SUCCESS:
                    initBetInformation(type);

                    dismissBlockDialog();
                    GdToastUtils.showBetSuccessToast(mContext, getResources().getString(R.string.show_bet_sucess) + " " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAllBetMoney());

                    serviceTime.setText(mAppViewModel.covertBalance((int) mAppViewModel.getUser().getBalance()));
                    break;
                case HandlerCode.SHOW_BET_MONEY:
                    showBetMoney();
                    break;
                case HandlerCode.SHOW_BET_ERROR:
                    dismissBlockDialog();
                    Toast.makeText(mContext, R.string.show_bet_error, Toast.LENGTH_LONG).show();
                    clearNoBetChip();
                    break;
                case HandlerCode.THREAD_ERROR:
                    dismissBlockDialog();
                    break;
                case HandlerCode.REFRESH_GAME:
                    //Results=ok^1#3#DLDLDLYY18^1#5#dldldlyy17^3#5#DLDLDLYY16^
                    if (WebSiteUrl.isDomain) {
                        return;
                    }
                    if (beanList == null) {
                        beanList = new ArrayList<>();
                    }
                    beanList.clear();
                    String data = (String) msg.obj;
                    String[] gameInfo = data.split("\\^");
                    if (gameInfo.length > 1) {
                        for (int j = 1; j < gameInfo.length; j++) {
                            String[] userInfo = gameInfo[j].split("#");
                            GameRefreshBean bean = new GameRefreshBean(userInfo[0], userInfo[1], userInfo[2]);
                            if (userInfo[0].equals(Integer.valueOf(areaId).toString())) {
                                beanList.add(bean);
                            }
                        }
                    }
                    if (baccaratA) {
                        return;
                    }
                    if (tableId > 3 && tableId != 71 && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        refreshName(beanList);
                    }
                    break;
                case HandlerCode.REFRESH_GAME_BET_IMG:
                    showUserBetImg(player1BetMoney, fl_user_bet_img1, 0);
                    showUserBetImg(player2BetMoney, fl_user_bet_img2, 0);
                    showUserBetImg(player3BetMoney, fl_user_bet_img3, 0);
                    showUserBetImg(player5BetMoney, fl_user_bet_img5, 0);
                    showUserBetImg(player6BetMoney, fl_user_bet_img6, 0);
                    showUserBetImg(player7BetMoney, fl_user_bet_img7, 0);
                    showUserBetImg(player8BetMoney, fl_user_bet_img8, 0);
                    break;
                case HandlerCode.REFRESH_GAME_LOTTERY_POOL:
                    updateBetMoney();
                    break;
            }
            //

        }
    };
    List<GameRefreshBean> beanList;
    String Player1 = "Player1";
    String Player2 = "Player2";
    String Player3 = "Player3";
    String Player5 = "Player5";
    String Player6 = "Player6";
    String Player7 = "Player7";
    String Player8 = "Player8";
    String Tie1 = "Tie1";
    String Tie2 = "Tie2";
    String Tie3 = "Tie3";
    String Tie5 = "Tie5";
    String Tie6 = "Tie6";
    String Tie7 = "Tie7";
    String Tie8 = "Tie8";
    String Pd1 = "Pd1";
    String Pd2 = "Pd2";
    String Pd3 = "Pd3";
    String Pd5 = "Pd5";
    String Pd6 = "Pd3";
    String Pd7 = "Pd7";
    String Pd8 = "Pd8";
    String Bd1 = "Bd1";
    String Bd2 = "Bd2";
    String Bd3 = "Bd3";
    String Bd5 = "Bd5";
    String Bd6 = "Bd6";
    String Bd7 = "Bd7";
    String Bd8 = "Bd8";
    String Banker1 = "Banker1";
    String Banker2 = "Banker2";
    String Banker3 = "Banker3";
    String Banker5 = "Banker5";
    String Banker6 = "Banker6";
    String Banker7 = "Banker7";
    String Banker8 = "Banker8";
    Map<String, String> userBetMap;
    List<String> userBetList;
    String bankerPeople;
    String playerPeople;
    String tiePeople;
    String ppPeople;
    String bpPeople;

    String luckySixPeople;
    String anyPairPeople;
    String perfectPairPeople;
    String bankerNaturalPeople;
    String playerNaturalPeople;
    String cowBankerPeople;
    String cowPlayerPeople;
    String cowTiePeople;

    String luckySixMoney;
    String anyPairMoney;
    String perfectPairMoney;
    String bankerNaturalMoney;
    String playerNaturalMoney;
    String cowBankerMoney;
    String cowPlayerMoney;
    String cowTieMoney;

    String bankerLotteryPool;
    String playerLotteryPool;
    String tieLotteryPool;
    String bpLotteryPool;
    String ppLotteryPool;

    @Override
    public void refreshUserBetMsg(String str) {
        super.refreshUserBetMsg(str);
//        if (WebSiteUrl.isDomain) {
//            return;
//        }
//Results=ok^Banker6:10^#^player3:I155078806^player5:DLDLDLYY16^player6:DLDLDLYY15^#1#3#0#20290(5)#52400#2#398#2#0#0#KH 2017-07-12 10:00:21#61#Navy#null#null#null|null#null#null#13127#36#|2#0#0#0#0
//Results=ok^Banker3:40^Banker5:10^Banker8:10^#^player2:emboy007^player3:LK00AUBANGOKAY^player5:LK00AWRAKA05^player6:demoafbai9^player8:LK00BRMASTER^#5#0#2001#209#1045#2#103#103#0#0#KH 2017-08-18 15:40:43#3#sisi#02#27#null|08#26#null#78136#56#
        Log.d("updateBetMoney", str);
        if (str.startsWith("Results=ok")) {
            String[] userBetMsg = str.split("#");
            String userBet = userBetMsg[0];
            String[] singleBetMsg = userBet.split("\\^");
            bankerLotteryPool = userBetMsg[4];
            playerLotteryPool = userBetMsg[5];
            tieLotteryPool = userBetMsg[6];
            bpLotteryPool = userBetMsg[7];
            ppLotteryPool = userBetMsg[8];
            String[] typePeople = str.split("\\|");
            if (typePeople.length >= 3) {
                String people = typePeople[2];
                String[] betTypePeople = people.split("#");
                bankerPeople = betTypePeople[0];
                playerPeople = betTypePeople[1];
                tiePeople = betTypePeople[2];
                bpPeople = betTypePeople[3];
                ppPeople = betTypePeople[4];
                String newPeople = typePeople[3];
                String[] betTypePeople2 = newPeople.split("#");
                luckySixMoney = betTypePeople2[0];
                anyPairMoney = betTypePeople2[1];
                perfectPairMoney = betTypePeople2[2];
                bankerNaturalMoney = betTypePeople2[3];
                playerNaturalMoney = betTypePeople2[4];
                cowBankerMoney = betTypePeople2[5];
                cowPlayerMoney = betTypePeople2[6];
                cowTieMoney = betTypePeople2[7];
                luckySixPeople = betTypePeople2[8];
                anyPairPeople = betTypePeople2[9];
                perfectPairPeople = betTypePeople2[10];
                bankerNaturalPeople = betTypePeople2[11];
                playerNaturalPeople = betTypePeople2[12];
                cowBankerPeople = betTypePeople2[13];
                cowPlayerPeople = betTypePeople2[14];
                cowTiePeople = betTypePeople2[15];
            } else {
                bankerPeople = "0";
                playerPeople = "0";
                tiePeople = "0";
                bpPeople = "0";
                ppPeople = "0";
            }
            handler.sendEmptyMessage(HandlerCode.REFRESH_GAME_LOTTERY_POOL);
//            if (singleBetMsg.length > 1) {
//                //B区数据处理
//                for (int j = 0; j < singleBetMsg.length; j++) {
//                    for (int i = 0; i < userBetList.size(); i++) {
//                        if (singleBetMsg[j].startsWith(userBetList.get(i))) {
//                            userBetMap.put(userBetList.get(i), singleBetMsg[j]);
//                        }
//                    }
//                }
//                if (!baccaratA) {
//                    player1BetMoney = subStringBetMoneyInt(userBetMap.get(Player1)) + subStringBetMoneyInt(userBetMap.get(Pd1)) + subStringBetMoneyInt(userBetMap.get(Tie1)) + subStringBetMoneyInt(userBetMap.get(Bd1)) + subStringBetMoneyInt(userBetMap.get(Banker1));
//                    player2BetMoney = subStringBetMoneyInt(userBetMap.get(Player2)) + subStringBetMoneyInt(userBetMap.get(Pd2)) + subStringBetMoneyInt(userBetMap.get(Tie2)) + subStringBetMoneyInt(userBetMap.get(Bd2)) + subStringBetMoneyInt(userBetMap.get(Banker2));
//                    player3BetMoney = subStringBetMoneyInt(userBetMap.get(Player3)) + subStringBetMoneyInt(userBetMap.get(Pd3)) + subStringBetMoneyInt(userBetMap.get(Tie3)) + subStringBetMoneyInt(userBetMap.get(Bd3)) + subStringBetMoneyInt(userBetMap.get(Banker3));
//                    player5BetMoney = subStringBetMoneyInt(userBetMap.get(Player5)) + subStringBetMoneyInt(userBetMap.get(Pd5)) + subStringBetMoneyInt(userBetMap.get(Tie5)) + subStringBetMoneyInt(userBetMap.get(Bd5)) + subStringBetMoneyInt(userBetMap.get(Banker5));
//                    player6BetMoney = subStringBetMoneyInt(userBetMap.get(Player6)) + subStringBetMoneyInt(userBetMap.get(Pd6)) + subStringBetMoneyInt(userBetMap.get(Tie6)) + subStringBetMoneyInt(userBetMap.get(Bd6)) + subStringBetMoneyInt(userBetMap.get(Banker6));
//                    player7BetMoney = subStringBetMoneyInt(userBetMap.get(Player7)) + subStringBetMoneyInt(userBetMap.get(Pd7)) + subStringBetMoneyInt(userBetMap.get(Tie7)) + subStringBetMoneyInt(userBetMap.get(Bd7)) + subStringBetMoneyInt(userBetMap.get(Banker7));
//                    player8BetMoney = subStringBetMoneyInt(userBetMap.get(Player8)) + subStringBetMoneyInt(userBetMap.get(Pd8)) + subStringBetMoneyInt(userBetMap.get(Tie8)) + subStringBetMoneyInt(userBetMap.get(Bd8)) + subStringBetMoneyInt(userBetMap.get(Banker8));
//                    if (isCanRefreshBetImg && tableId > 3 && tableId != 71 && this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                        handler.sendEmptyMessage(HandlerCode.REFRESH_GAME_BET_IMG);
//                    }
//                }
//            }
        }
    }

    private int subStringBetMoneyInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        } else {
            String money[] = str.split(":");
            return Integer.parseInt(money[1]);
        }
    }

    private int player1BetMoney;
    private int player2BetMoney;
    private int player3BetMoney;
    private int player5BetMoney;
    private int player6BetMoney;
    private int player7BetMoney;
    private int player8BetMoney;

    private void refreshName(List<GameRefreshBean> list) {
        fl_baccarat_name1.setVisibility(View.GONE);
        fl_baccarat_name1.setBackgroundResource(0);
        fl_baccarat_name2.setVisibility(View.GONE);
        fl_baccarat_name2.setBackgroundResource(0);
        fl_baccarat_name3.setVisibility(View.GONE);
        fl_baccarat_name3.setBackgroundResource(0);
        fl_baccarat_name5.setVisibility(View.GONE);
        fl_baccarat_name5.setBackgroundResource(0);
        fl_baccarat_name6.setVisibility(View.GONE);
        fl_baccarat_name6.setBackgroundResource(0);
        fl_baccarat_name7.setVisibility(View.GONE);
        fl_baccarat_name7.setBackgroundResource(0);
        fl_baccarat_name8.setVisibility(View.GONE);
        fl_baccarat_name8.setBackgroundResource(0);
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            switch (list.get(i).getSeatNum()) {
                case "1":
                    fl_baccarat_name1.setVisibility(View.VISIBLE);
                    tv_name1.setText(name);
                    initNameBg(fl_baccarat_name1, name, "1");
                    break;
                case "2":
                    fl_baccarat_name2.setVisibility(View.VISIBLE);
                    tv_name2.setText(name);
                    initNameBg(fl_baccarat_name2, name, "2");
                    break;
                case "3":
                    fl_baccarat_name3.setVisibility(View.VISIBLE);
                    tv_name3.setText(name);
                    initNameBg(fl_baccarat_name3, name, "3");
                    break;
                case "5":
                    fl_baccarat_name5.setVisibility(View.VISIBLE);
                    tv_name5.setText(name);
                    initNameBg(fl_baccarat_name5, name, "5");
                    break;
                case "6":
                    fl_baccarat_name6.setVisibility(View.VISIBLE);
                    tv_name6.setText(name);
                    initNameBg(fl_baccarat_name6, name, "6");
                    break;
                case "7":
                    fl_baccarat_name7.setVisibility(View.VISIBLE);
                    tv_name7.setText(name);
                    initNameBg(fl_baccarat_name7, name, "7");
                    break;
                case "8":
                    fl_baccarat_name8.setVisibility(View.VISIBLE);
                    tv_name8.setText(name);
                    initNameBg(fl_baccarat_name8, name, "8");
                    break;
            }
        }
    }

    private void initNameBg(FrameLayout fl, String name, String index) {
        String userNname = mAppViewModel.getUser().getName();
        switch (index) {
            case "1":
                if (userNname.equals(name)) {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_me1);
                } else {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_other1);
                }
                break;
            case "2":
                if (userNname.equals(name)) {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_me2);
                } else {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_other2);
                }
                break;
            case "3":
                if (userNname.equals(name)) {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_me3);
                } else {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_other3);
                }
                break;
            case "5":
                if (userNname.equals(name)) {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_me5);
                } else {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_other5);
                }
                break;
            case "6":
                if (userNname.equals(name)) {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_me6);
                } else {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_other6);
                }
                break;
            case "7":
                if (userNname.equals(name)) {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_me7);
                } else {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_other7);
                }
                break;
            case "8":
                if (userNname.equals(name)) {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_me8);
                } else {
                    fl.setBackgroundResource(R.mipmap.gd_baccarat_name_bg_other8);
                }
                break;
        }
    }

    public void initUI() {
        baccaratTimer = 0;
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setTimer(0);
        gameNumber = "0";
        tv_table_timer.setText("0");
        bBetSucess = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        AutoLayoutConifg.getInstance().setSize(this);
//        startUpdateStatusThread();
        if (mAppViewModel.isMusicOpen()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.startBackgroudMuzicService(mAppViewModel.getMuzicIndex(), componentBack, mContext, mAppViewModel.getBackgroudVolume());
                }
            }, 1000);
        }
        videoHelper.playVideo();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (isAttached && lvTableBetLimitRed != null && btn_limit != null) {
//                    lvTableBetLimitRed.setVisibility(View.GONE);
//                }
//            }
//        }, 10000);
//        startVideoPlayback();
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
        if (isNeedGetWidth) {
            isNeedGetWidth = false;
            initResultAnimation();
        }
    }

    private void initResultAnimation() {
        tv_banker_result_name.measure(0, 0);
        tv_player_result_name.measure(0, 0);
        ViewGroup.LayoutParams layoutParams1 = img_banker_animation.getLayoutParams();
        layoutParams1.width = tv_banker_result_name.getMeasuredWidth();
        layoutParams1.height = tv_banker_result_name.getMeasuredHeight();
        img_banker_animation.setLayoutParams(layoutParams1);
        ViewGroup.LayoutParams layoutParams2 = img_player_animation.getLayoutParams();
        layoutParams2.width = tv_player_result_name.getMeasuredWidth();
        layoutParams2.height = tv_player_result_name.getMeasuredHeight();
        img_player_animation.setLayoutParams(layoutParams2);

        animationPlayer = (AnimationDrawable) img_player_animation.getBackground();
        animationBanker = (AnimationDrawable) img_banker_animation.getBackground();
    }

    /**
     * 监听手机旋转，不销毁activity进行画面旋转，再缩放显示区域
     */


    @Override
    protected void leftClick() {
        mAppViewModel.setbLobby(true);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setBigRoadOld("");
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).Init();
        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 0);
        skipAct(LobbyActivity.class, bundle);
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
//            mAppViewModel.startBackgroudMuzicService(mAppViewModel.getMuzicIndex(), componentBack, mContext, mAppViewModel.getBackgroudVolume());

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
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AutoLayoutConifg.getInstance().setmDesignHeight(420);
        } else {
            AutoLayoutConifg.getInstance().setmDesignWidth(420);
        }


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

    @BindView(R2.id.gd__fl_baccarat_table_lucky)
    FrameLayout fl_baccarat_table_lucky;
    @BindView(R2.id.gd__fl_baccarat_table_any)
    FrameLayout fl_baccarat_table_any;
    @BindView(R2.id.gd__fl_baccarat_table_player_natural)
    FrameLayout fl_baccarat_table_player_natural;
    @BindView(R2.id.gd__fl_baccarat_table_perfect)
    FrameLayout fl_baccarat_table_perfect;
    @BindView(R2.id.gd__fl_baccarat_table_banker_natural)
    FrameLayout fl_baccarat_table_banker_natural;
    @BindView(R2.id.gd__fl_baccarat_table_cow_player)
    FrameLayout fl_baccarat_table_cow_player;
    @BindView(R2.id.gd__fl_baccarat_table_cow_tie)
    FrameLayout fl_baccarat_table_cow_tie;
    @BindView(R2.id.gd__fl_baccarat_table_cow_banker)
    FrameLayout gfl_baccarat_table_cow_banker;

    FrameLayout fl_baccarat_table_tie;
    FrameLayout fl_baccarat_table_player_pair;
    FrameLayout fl_baccarat_table_banker_pair;
    FrameLayout fl_user_bet_img1;
    FrameLayout fl_user_bet_img2;
    FrameLayout fl_user_bet_img3;
    FrameLayout fl_user_bet_img5;
    FrameLayout fl_user_bet_img6;
    FrameLayout fl_user_bet_img7;
    FrameLayout fl_user_bet_img8;
    FrameLayout fl_baccarat_name1;
    FrameLayout fl_baccarat_name2;
    FrameLayout fl_baccarat_name3;
    FrameLayout fl_baccarat_name5;
    FrameLayout fl_baccarat_name6;
    FrameLayout fl_baccarat_name7;
    FrameLayout fl_baccarat_name8;
    TextView tv_name1;
    TextView tv_name2;
    TextView tv_name3;
    TextView tv_name5;
    TextView tv_name6;
    TextView tv_name7;
    TextView tv_name8;
    private boolean baccaratA;

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            isFirstBet = savedInstanceState.getBoolean("isFirstBet");
        }
        objectAnimatorB = WidgetUtil.startAlphaAnimation(img_bet_bg_b);
        objectAnimatorP = WidgetUtil.startAlphaAnimation(img_bet_bg_p);
        objectAnimatorT = WidgetUtil.startAlphaAnimation(img_bet_bg_t);
        objectAnimatorBP = WidgetUtil.startAlphaAnimation(img_bet_bg_bp);
        objectAnimatorPP = WidgetUtil.startAlphaAnimation(img_bet_bg_pp);
        objectAnimatorLucky = WidgetUtil.startAlphaAnimation(img_bet_bg_lucky);
        objectAnimatorAny = WidgetUtil.startAlphaAnimation(img_bet_bg_any);
        objectAnimatorPN = WidgetUtil.startAlphaAnimation(img_bet_bg_player_n);
        objectAnimatorPerfect = WidgetUtil.startAlphaAnimation(img_bet_bg_perfect);
        objectAnimatorBN = WidgetUtil.startAlphaAnimation(img_bet_bg_banker_n);
        objectAnimatorCP = WidgetUtil.startAlphaAnimation(img_bet_bg_c_p);
        objectAnimatorCT = WidgetUtil.startAlphaAnimation(img_bet_bg_c_t);
        objectAnimatorCB = WidgetUtil.startAlphaAnimation(img_bet_bg_c_b);
        initUI();
        initApngList();
        initUserBetMsg();
        initOrientation();
        toolbar_right_bottom_tv.setVisibility(View.GONE);
        toolbar_right_top_tv.setVisibility(View.GONE);
        toolbar.setNavigationIcon(null);
        hallId = mAppViewModel.getHallId();
        tableId = mAppViewModel.getTableId();
        areaId = getIntent().getIntExtra("areaId", -1);
        if (!WebSiteUrl.isDomain) {
            baccaratA = getIntent().getBooleanExtra("baccaratA", true);
        }
        if (betTypeList == null) {
            betTypeList = new ArrayList<>();
        }
        shufflingTv = (TextView) findViewById(R.id.gd__tv_shuffling);
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

        if (tableId == 71) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            chipY = AutoUtils.getPercentHeightSize(50);
            chipPlayerBankerY = AutoUtils.getPercentHeightSize(50);
        } else {
            chipY = AutoUtils.getPercentHeightSize(82);
            chipPlayerBankerY = AutoUtils.getPercentHeightSize(82);
        }
        tipY = AutoUtils.getPercentHeightSize(30);
        chipX = AutoUtils.getPercentHeightSize(4);
        tieX = AutoUtils.getPercentHeightSize(4);
        chipPlayerBankerX = AutoUtils.getPercentHeightSize(4);

        fl_bet1_bg.setVisibility(View.VISIBLE);
        fl_baccarat_table_tie = (FrameLayout) fl_bet1_bg.findViewById(R.id.gd__fl_baccarat_table_tie);
        fl_baccarat_table_player_pair = (FrameLayout) fl_bet1_bg.findViewById(R.id.gd__fl_baccarat_table_player_pair);
        fl_baccarat_table_banker_pair = (FrameLayout) fl_bet1_bg.findViewById(R.id.gd__fl_baccarat_table_banker_pair);
//            R.layout.include_baccarat_bet2_table


        initControl();
        ////////////////////

        serviceTime.setText(mAppViewModel.covertBalance((int) mAppViewModel.getUser().getBalance()));

        rightTv.setTextColor(getResources().getColor(R.color.white));
        toolbar.setBackgroundResource(R.color.transparent);
//        serviceTime.setVisibility(View.GONE);
        if (mAppViewModel.isMusicOpen()) {
            mAppViewModel.startBackgroudMuzicService(mAppViewModel.getMuzicIndex(), componentBack, mContext, mAppViewModel.getBackgroudVolume());
        }
        setTablePool(lv_pool);
        setInfoData(lv_user_info);
        setTableBetPool(lv_person_bet_info, "Banker");


        startPlayVideo();
        //lanjian  xiugai
        setTableLimit();
        setClickListener();
        setChip();
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setBigRoadOld("");
        gameIdNumber = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber();
        otherTableIdList.clear();
        if (tableId == 1) {
            tableName = "LB1";
        } else if (tableId == 2) {
            tableName = "LB2";
        } else if (tableId == 3) {
            tableName = "LB3";
        } else if (tableId == 61) {
            tableName = "LB5";
        } else if (tableId == 62) {
            tableName = "LB6";
        } else if (tableId == 63) {
            tableName = "LB7";
        } else if (tableId == 71) {
            tableName = "BM1";
        }
        if (tableId != 1) {
            otherTableIdList.add(1);
        }
        if (tableId != 2) {
            otherTableIdList.add(2);
        }
        if (tableId != 3) {
            otherTableIdList.add(3);
        }
        if (tableId != 61) {
            otherTableIdList.add(61);
        }
        if (tableId != 62) {
            otherTableIdList.add(62);
        }
        if (tableId != 63) {
            otherTableIdList.add(63);
        }
        if (tableId != 71) {
            otherTableIdList.add(71);
        }
        initArcMenu(tvMenu, tableName, hallId);

        tv_table_game_number.setText(tableName + ":" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber());
        tv_table_game_number1.setText(tv_table_game_number.getText().toString());
        initChat();
        if (tableId == 71)
            checkGuide();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ll_baccarat_parent.setBackgroundResource(R.color.table_color);
        }
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setBanker1(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setBanker2(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setBanker3(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setPlayer1(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setPlayer2(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setPlayer3(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().setBanker_palyer_tie(-100);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().setBankerPair(-100);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().setPlayerPair(-100);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setGameStatus(-1);
        setAskClick();
        startUpdateStatusThread();
        lv_user_info.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        isSlideInfo = false;
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                    case SCROLL_STATE_FLING:
                        isSlideInfo = true;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
        lv_pool.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        isSlideInfo = false;
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                    case SCROLL_STATE_FLING:
                        isSlideInfo = true;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    private boolean isSlideInfo = false;

    @Override
    public void initBaccarat() {
        tableId = mAppViewModel.getTableId();
        fl_bet_content_1.setVisibility(View.VISIBLE);
        fl_bet_content_2.setVisibility(View.INVISIBLE);
        if (getCurrentBetContent() == 1) {
            tv_change_bet_content.setText(getString(R.string.nn));
        } else {
            tv_change_bet_content.setText(getString(R.string.bjl));
        }
        clearAllShowChip();
        clearAllChips();
        clearBetBg();
        initClickCount();
        initPokerState();
        hidePoker(3);
        bUpdateRoad = true;
        for (int i = 0; i < apngPlayBeanList.size(); i++) {
            if (apngPlayBeanList.get(i).getApngImageView().getVisibility() == View.VISIBLE) {
                apngPlayBeanList.get(i).getApngImageView().stop();
                apngPlayBeanList.get(i).getApngImageView().setVisibility(View.INVISIBLE);
                break;
            }
        }
        objectAnimatorB.cancel();
        objectAnimatorP.cancel();
        objectAnimatorT.cancel();
        objectAnimatorBP.cancel();
        objectAnimatorPP.cancel();
        objectAnimatorLucky.cancel();
        objectAnimatorAny.cancel();
        objectAnimatorPN.cancel();
        objectAnimatorPerfect.cancel();
        objectAnimatorBN.cancel();
        objectAnimatorCP.cancel();
        objectAnimatorCT.cancel();
        objectAnimatorCB.cancel();
        animationPlayer.stop();
        animationPlayer.selectDrawable(0);
        animationBanker.stop();
        animationBanker.selectDrawable(0);
        countdown_view.stopCountDown();
        betTimeCount = 0;
        videoHelper.stopVideo();
        stopUpdateStatusThread();
        initUI();
        if (tableId == 71) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }
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
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            chipY = AutoUtils.getPercentHeightSize(50);
            chipPlayerBankerY = AutoUtils.getPercentHeightSize(50);
        } else {
            chipY = AutoUtils.getPercentHeightSize(82);
            chipPlayerBankerY = AutoUtils.getPercentHeightSize(82);
        }
        tipY = AutoUtils.getPercentHeightSize(30);
        chipX = AutoUtils.getPercentHeightSize(4);
        tieX = AutoUtils.getPercentHeightSize(4);
        chipPlayerBankerX = AutoUtils.getPercentHeightSize(4);
        initControl();
        serviceTime.setText(mAppViewModel.covertBalance((int) mAppViewModel.getUser().getBalance()));
        if (mAppViewModel.isMusicOpen()) {
            mAppViewModel.startBackgroudMuzicService(mAppViewModel.getMuzicIndex(), componentBack, mContext, mAppViewModel.getBackgroudVolume());
        }
        setTablePool(lv_pool);
        setInfoData(lv_user_info);
        setTableBetPool(lv_person_bet_info, "Banker");
        setTableLimit();
        setClickListener();
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setBigRoadOld("");
        gameIdNumber = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber();
        otherTableIdList.clear();
        if (tableId == 1) {
            tableName = "LB1";
        } else if (tableId == 2) {
            tableName = "LB2";
        } else if (tableId == 3) {
            tableName = "LB3";
        } else if (tableId == 61) {
            tableName = "LB5";
        } else if (tableId == 62) {
            tableName = "LB6";
        } else if (tableId == 63) {
            tableName = "LB7";
        } else if (tableId == 71) {
            tableName = "BM1";
        }
        if (tableId != 1) {
            otherTableIdList.add(1);
        }
        if (tableId != 2) {
            otherTableIdList.add(2);
        }
        if (tableId != 3) {
            otherTableIdList.add(3);
        }
        if (tableId != 61) {
            otherTableIdList.add(61);
        }
        if (tableId != 62) {
            otherTableIdList.add(62);
        }
        if (tableId != 63) {
            otherTableIdList.add(63);
        }
        if (tableId != 71) {
            otherTableIdList.add(71);
        }
        if (tableId != 71) {
            if (tv_mi_timer.getVisibility() == View.VISIBLE) {
                tv_mi_timer.setVisibility(View.GONE);
                tv_table_timer.setVisibility(View.VISIBLE);
            }
        }
        initArcMenu(tvMenu, tableName, hallId);
        tv_table_game_number.setText(tableName + ":" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getShoeNumber() + " - " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameNumber());
        tv_table_game_number1.setText(tv_table_game_number.getText().toString());
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setBanker1(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setBanker2(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setBanker3(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setPlayer1(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setPlayer2(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().setPlayer3(0);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().setBanker_palyer_tie(-100);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().setBankerPair(-100);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().setPlayerPair(-100);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setGameStatus(-1);
        startPlayVideo();
        videoHelper.playVideo();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startUpdateStatusThread();
            }
        }, 1500);
    }

    private void initApngList() {
        apngPlayBeanList.clear();
        String lg = AppTool.getAppLanguage(mContext);
        if (lg.equals("zh") || lg.equals("zh_TW")) {
            apngPlayBeanList.add(new ApngPlayBean(img_apng_player, "assets://player_win_zh.png"));
            apngPlayBeanList.add(new ApngPlayBean(img_apng_tie, "assets://tie_win_cn.png"));
            apngPlayBeanList.add(new ApngPlayBean(img_apng_banker, "assets://banker_win_zh.png"));
        } else {
            apngPlayBeanList.add(new ApngPlayBean(img_apng_player, "assets://player_win_en.png"));
            apngPlayBeanList.add(new ApngPlayBean(img_apng_tie, "assets://tie_win_en.png"));
            apngPlayBeanList.add(new ApngPlayBean(img_apng_banker, "assets://banker_win_en.png"));
        }
    }


    private void showUserBetImg(int money, FrameLayout fl, int bottomMargin) {
        int betMoney = money;
        fl.removeAllViews();
        while (money > 0) {
            A:
            for (int i = chipList.size() - 1; i >= 0; i--) {
                int index = money / chipList.get(i).getValue();
                if (index > 0) {
                    FrameLayout.LayoutParams params = new AutoFrameLayout.LayoutParams(AutoUtils.getPercentHeightSize(32), AutoUtils.getPercentHeightSize(15));
                    params.gravity = Gravity.BOTTOM | Gravity.CENTER;
                    params.bottomMargin = bottomMargin;
                    ImageView img = new ImageView(mContext);
                    img.setBackgroundResource(chipList.get(i).getDrawableRes());
                    img.setLayoutParams(params);
                    fl.addView(img);
                    money = money - chipList.get(i).getValue();
                    bottomMargin = bottomMargin + AutoUtils.getPercentHeightSize(4);
                    break A;
                }
            }
        }
        if (betMoney > 0) {
            TextView moneyTv = new TextView(mContext);
            FrameLayout.LayoutParams params = new AutoFrameLayout.LayoutParams(AutoUtils.getPercentHeightSize(22), AutoUtils.getPercentHeightSize(12));
            params.gravity = Gravity.BOTTOM | Gravity.RIGHT;
            params.rightMargin = AutoUtils.getPercentHeightSize(5);
            params.bottomMargin = AutoUtils.getPercentHeightSize(2);
            moneyTv.setLayoutParams(params);
            moneyTv.setText(betMoney + "");
            moneyTv.setTextSize(6);
            moneyTv.setGravity(Gravity.CENTER);
            moneyTv.setTextColor(Color.WHITE);
            moneyTv.setBackgroundResource(R.drawable.gd_rectangle_trans_chip_tips);
            fl.addView(moneyTv);
        }
    }

    private void initUserBetMsg() {
        userBetList = new ArrayList<>();
        userBetMap = new HashMap<>();
        userBetList.add(Player1);
        userBetList.add(Player2);
        userBetList.add(Player3);
        userBetList.add(Player5);
        userBetList.add(Player6);
        userBetList.add(Player7);
        userBetList.add(Player8);
        userBetList.add(Pd1);
        userBetList.add(Pd2);
        userBetList.add(Pd3);
        userBetList.add(Pd5);
        userBetList.add(Pd6);
        userBetList.add(Pd7);
        userBetList.add(Pd8);
        userBetList.add(Tie1);
        userBetList.add(Tie2);
        userBetList.add(Tie3);
        userBetList.add(Tie5);
        userBetList.add(Tie6);
        userBetList.add(Tie7);
        userBetList.add(Tie8);
        userBetList.add(Bd1);
        userBetList.add(Bd2);
        userBetList.add(Bd3);
        userBetList.add(Bd5);
        userBetList.add(Bd6);
        userBetList.add(Bd7);
        userBetList.add(Bd8);
        userBetList.add(Banker1);
        userBetList.add(Banker2);
        userBetList.add(Banker3);
        userBetList.add(Banker5);
        userBetList.add(Banker6);
        userBetList.add(Banker7);
        userBetList.add(Banker8);
        for (int j = 0; j < userBetList.size(); j++) {
            userBetMap.put(userBetList.get(j), "");
        }
    }

    protected void initOrientation() {
        super.initOrientation();
        AppTool.setAppLanguage(this, AppTool.getAppLanguage(this));
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            lv_baccarat_chips = (AdapterView) findViewById(R.id.gd__lv_baccarat_chips);
            lv_baccarat_chips = (AdapterView) findViewById(R.id.gd__lv_baccarat_chips_h);
            toolbar.post(new Runnable() {
                @Override
                public void run() {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) toolbar.getLayoutParams();
                    layoutParams.leftMargin = ScreenUtil.dip2px(mContext, 12);
                    toolbar.setLayoutParams(layoutParams);
                }
            });
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            {

                lv_baccarat_chips = (AdapterView) findViewById(R.id.gd__lv_baccarat_chips_h);
                leftPanel1.setOpen(true, true);

            }
            ll_bet_btn_parent.setVisibility(View.GONE);
        }
        setToolbarAndSet("", usName);
        AutoUtils.auto(baseContentView);
    }

    private void checkGuide() {
        Boolean guide = (Boolean) AppTool.getObjectData(mContext, AppConfig.ACTION_KEY_Guide);
        if (guide == null || !guide) {
            skipAct(HomeGuideActivity.class);
        }
    }

    private void initView2Bind(ViewBindHolder viewBind) {
        iv_baccarat_table_player = viewBind.ivBaccaratTablePlayer;
        iv_baccarat_table_banker = viewBind.ivBaccaratTableBanker;
        iv_baccarat_table_tie = viewBind.ivBaccaratTableTie;
        iv_baccarat_table_player_pair = viewBind.ivBaccaratTablePlayerPair;
        iv_baccarat_table_banker_pair = viewBind.ivBaccaratTableBankerPair;

        tv_player_bet_money = viewBind.tvPlayerBetMoney;
        tv_player_bet_count = viewBind.tvPlayerBetCount;
        tv_player_pair_bet_money = viewBind.tvPlayerPairBetMoney;

        tv_banker_bet_count = viewBind.tvBankerBetCount;
        tv_banker_bet_money = viewBind.tvBankerBetMoney;
        tv_banker_pair_bet_count = viewBind.tvBankerPairBetCount;
        tv_player_pair_bet_count = viewBind.tvPlayerPairBetCount;
        tv_banker_pair_bet_money = viewBind.tvBankerPairBetMoney;
        tv_tie_bet_count = viewBind.tvTieBetCount;
        tv_tie_bet_money = viewBind.tvTieBetMoney;
        tv_banker_pair_bet_money = viewBind.tvBankerPairBetMoney;
        tv_player_pair_bet_money = viewBind.tvPlayerPairBetMoney;
        fl_baccarat_table_player = viewBind.flBaccaratTablePlayer;
        fl_baccarat_table_banker = viewBind.flBaccaratTableBanker;
        fl_baccarat_table_player_bg = viewBind.flBaccaratTablePlayer_bg;
        fl_baccarat_table_banker_bg = viewBind.flBaccaratTableBanker_bg;
    }


    public void initControl() {


        density = ScreenUtil.getDisplayMetrics(mContext).density;
        baccarat_head_road = (GridLayout) findViewById(R.id.gd__baccarat_gridlayout1);
        baccarat_big_road = (GridLayout) findViewById(R.id.gd__baccarat_gridlayout2);
        baccarat_bigeyes_road = (GridLayout) findViewById(R.id.gd__baccarat_gridlayout3);
        fl_small_road_parent1 = findViewById(R.id.gd__fl_small_road_parent);
        smallway_item1 = findViewById(R.id.gd__smallway_item);
        smallway_item1_big = findViewById(R.id.gd__smallway_item_big);
        baccarat_smalleyes_road = findViewById(R.id.gd__baccarat_gridlayout4).findViewById(R.id.gd__baccarat_gridlayout3);
        fl_small_road_parent2 = findViewById(R.id.gd__baccarat_gridlayout4);
        smallway_item2 = findViewById(R.id.gd__baccarat_gridlayout4).findViewById(R.id.gd__smallway_item);
        smallway_item2_big = findViewById(R.id.gd__baccarat_gridlayout4).findViewById(R.id.gd__smallway_item_big);
        baccarat_roach_road = (GridLayout) findViewById(R.id.gd__baccarat_gridlayout5).findViewById(R.id.gd__baccarat_gridlayout3);
        fl_small_road_parent3 = findViewById(R.id.gd__baccarat_gridlayout5);
        smallway_item3 = findViewById(R.id.gd__baccarat_gridlayout5).findViewById(R.id.gd__smallway_item);
        smallway_item3_big = findViewById(R.id.gd__baccarat_gridlayout5).findViewById(R.id.gd__smallway_item_big);
        tv_baccarat_shoe_number = (TextView) findViewById(R.id.gd__text_shoe_game_number);

        tv_baccarat_total_number = (TextView) findViewById(R.id.gd__text_total);
        tv_baccarat_banker_number = (TextView) findViewById(R.id.gd__text_banker);
        tv_baccarat_player_number = (TextView) findViewById(R.id.gd__text_player);
        tv_baccarat_tie_number = (TextView) findViewById(R.id.gd__text_tie);
        tv_baccarat_bp_number = (TextView) findViewById(R.id.gd__text_bp);
        tv_baccarat_pp_number = (TextView) findViewById(R.id.gd__text_pp);

        iv_poker_player1 = (ImageView) findViewById(R.id.gd__iv_poker_player1_a1);
        iv_poker_player2 = (ImageView) findViewById(R.id.gd__iv_poker_player2_a1);
        iv_poker_player3 = (ImageView) findViewById(R.id.gd__iv_poker_player3_a1);
        iv_poker_banker1 = (ImageView) findViewById(R.id.gd__iv_poker_banker1_a1);
        iv_poker_banker2 = (ImageView) findViewById(R.id.gd__iv_poker_banker2_a1);
        iv_poker_banker3 = (ImageView) findViewById(R.id.gd__iv_poker_banker3_a1);

        ll_poker_pw = ((LinearLayout) findViewById(R.id.gd__ll_poker_pw));
        ll_poker_parent = ((FrameLayout) findViewById(R.id.gd__ll_poker_parent_a));


        llCenter.setVisibility(View.VISIBLE);

        tv_point_player = (TextView) findViewById(R.id.gd__tv_bottom_panel_left_a);
        tv_point_banker = (TextView) findViewById(R.id.gd__tv_bottom_panel_right_a);

        pw_poker_banker1 = (PageWidgetT) findViewById(R.id.gd__page_widget_poker_banker1);
        pw_poker_banker2 = (PageWidgetT) findViewById(R.id.gd__page_widget_poker_banker2);
        pw_poker_banker3 = (PageWidgetT) findViewById(R.id.gd__page_widget_poker_banker3);

        pw_poker_player1 = (PageWidgetT) findViewById(R.id.gd__page_widget_poker_player1);
        pw_poker_player2 = (PageWidgetT) findViewById(R.id.gd__page_widget_poker_player2);
        pw_poker_player3 = (PageWidgetT) findViewById(R.id.gd__page_widget_poker_player3);
        tv_poker_timer = (TextView) findViewById(R.id.gd__tv_poker_timer);


//        LinearLayout.LayoutParams lp= (LinearLayout.LayoutParams) serviceTime.getLayoutParams();
//        lp.gravity = Gravity.RIGHT;
//        lp.setMargins(AutoUtils.getPercentWidthSize(280),0,0,0);
        player1x = AutoUtils.getPercentHeightSize(1100);
        player2x = AutoUtils.getPercentHeightSize(900);
        player3x = AutoUtils.getPercentHeightSize(950);
        banker1x = AutoUtils.getPercentHeightSize(25);
        banker2x = -AutoUtils.getPercentHeightSize(100);
        banker3x = -AutoUtils.getPercentHeightSize(50);

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ll_poker_parent.getLayoutParams();
//            ll_poker_parent.setPadding(ll_poker_parent.getPaddingLeft(),ll_poker_parent.getTop(),ll_poker_parent.getRight(),ll_poker_parent.getBottom()+AutoUtils.getPercentHeightSize(28));
//            params.setMargins(0, 0, 0, AutoUtils.getPercentHeightSize(28));
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            xSkizeP1 = 0.26f;
            xSkizeP2 = 0.21f;
            xSkizeP3 = 0.21f;
            xSkizeB1 = 0.17f;
            xSkizeB2 = 0.21f;
            xSkizeB3 = 0.17f;
            animationHeight = -400;
        }
//            iv_poker_player1.setLayoutParams(findViewById(R.id.gd__iv_poker_player1_a1).getLayoutParams());
//            iv_poker_player2.setLayoutParams(findViewById(R.id.gd__iv_poker_player2_a1).getLayoutParams());
//            iv_poker_player3.setLayoutParams(findViewById(R.id.gd__iv_poker_player3_a1).getLayoutParams());
//            iv_poker_banker1.setLayoutParams(findViewById(R.id.gd__iv_poker_banker1_a1).getLayoutParams());
//            iv_poker_banker2.setLayoutParams(findViewById(R.id.gd__iv_poker_banker2_a1).getLayoutParams());
//            iv_poker_banker3.setLayoutParams(findViewById(R.id.gd__iv_poker_banker3_a1).getLayoutParams());


    }

    public void parentClick(View v) {
        parentClick();
    }

    public void setOnRepeatListener() {
        //    showPopBottom(lv_table_pool);
        showRepeatBetBg();
        if (bBetSucess == false)//当前局还没有下注的时候允许重复下注
        {

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayer() > 0)//清楚未下注的筹码
            {
                chipHelperPlayer.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayer(), chipPlayerBankerX, chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
                playerBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayer();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperPlayer;
                }
            } else {
                chipHelperPlayer.setOperationButtonDisplay(false);
            }

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBanker() > 0)//清楚未下注的筹码
            {
                chipHelperBanker.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBanker(), chipPlayerBankerX, chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                bankerBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBanker();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperBanker;
                }
            } else {
                chipHelperBanker.setOperationButtonDisplay(false);
            }

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getTie() > 0)//清楚未下注的筹码
            {
                chipHelperTie.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getTie(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                tieBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getTie();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperTie;
                }
            } else {
                chipHelperTie.setOperationButtonDisplay(false);
            }

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayerPair() > 0)//清楚未下注的筹码
            {
                chipHelperPlayerPair.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayerPair(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                playerPairBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayerPair();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperPlayerPair;
                }
            } else
                chipHelperPlayerPair.setOperationButtonDisplay(false);

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBankerPair() > 0)//清楚未下注的筹码
            {
                chipHelperBankerPair.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBankerPair(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                bankerPairBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBankerPair();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperBankerPair;
                }
            } else
                chipHelperBankerPair.setOperationButtonDisplay(false);

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getLucky() > 0)//清楚未下注的筹码
            {
                chipHelperLucky.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getLucky(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                luckyBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getLucky();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperLucky;
                }
            } else {
                chipHelperLucky.setOperationButtonDisplay(false);
            }

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getAny() > 0)//清楚未下注的筹码
            {
                chipHelperAny.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getAny(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                anyBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getAny();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperAny;
                }
            } else {
                chipHelperAny.setOperationButtonDisplay(false);
            }
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayerN() > 0)//清楚未下注的筹码
            {
                chipHelperPlayerN.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayerN(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                playerNBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayerN();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperPlayerN;
                }
            } else {
                chipHelperPlayerN.setOperationButtonDisplay(false);
            }
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPerfect() > 0)//清楚未下注的筹码
            {
                chipHelperPerfect.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPerfect(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                perfectBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPerfect();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperPerfect;
                }
            } else {
                chipHelperPerfect.setOperationButtonDisplay(false);
            }
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBankerN() > 0)//清楚未下注的筹码
            {
                chipHelperBankerN.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBankerN(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                bankerNBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBankerN();
                if (getCurrentBetContent() == 1) {
                    chipHelperCurrent = chipHelperBankerN;
                }
            } else {
                chipHelperBankerN.setOperationButtonDisplay(false);
            }
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowPlayer() > 0)//清楚未下注的筹码
            {
                chipHelperCPlayer.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowPlayer(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                cPlayerBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowPlayer();
                if (getCurrentBetContent() == 2) {
                    chipHelperCurrent = chipHelperCPlayer;
                }
            } else {
                chipHelperCPlayer.setOperationButtonDisplay(false);
            }
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowTie() > 0)//清楚未下注的筹码
            {
                chipHelperCTie.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowTie(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                cTieBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowTie();
                if (getCurrentBetContent() == 2) {
                    chipHelperCurrent = chipHelperCTie;
                }
            } else {
                chipHelperCTie.setOperationButtonDisplay(false);
            }
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowBanker() > 0)//清楚未下注的筹码
            {
                chipHelperCBanker.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowBanker(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

                cBankerBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowBanker();
                if (getCurrentBetContent() == 2) {
                    chipHelperCurrent = chipHelperCBanker;
                }
            } else {
                chipHelperCBanker.setOperationButtonDisplay(false);
            }

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayer() > 0 ||
                    mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBanker() > 0
                    || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getTie() > 0
                    || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayerPair() > 0
                    || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBankerPair() > 0)
                BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            chipHelperCurrent.setOperationButtonDisplay(true);
            closeOtherSure();
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentFront, mContext, mAppViewModel.getFrontVolume());
        }
    }

    public void setClickListener() {


        fl_baccarat_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentClick();
            }
        });
//        tvTableBetReplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
       /* tvTableBetPol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betInfoShowAble = !betInfoShowAble;
                WidgetUtil.showAnimation(lv_table_pool, betInfoShowAble, Gravity.BOTTOM);

            }
        });*/
        tvTableBetCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        });
        tvTableBetSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        });
        if (!WebSiteUrl.isDomain) {
            if (tableId > 3 && tableId != 71 && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                return;
            }
            tv_banker_bet_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showUpPlayerTypeBet("Banker");
                }
            });

            tv_player_bet_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showUpPlayerTypeBet("Player");
                }
            });
            tv_tie_bet_count.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showUpPlayerTypeBet("Tie");
                }
            });
        }

        //路子
        leftPanel1.setOnPanelListener(new Panel.OnPanelListener() {
            @Override
            public void onPanelClosed(Panel panel) {
                showRoad = false;
//                btn_limit.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPanelOpened(Panel panel) {
                //  mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setBigRoadOld("");
                showRoad = true;
//                btn_limit.setVisibility(View.GONE);
            }
        });
        leftPanel1.setInterpolator(new BounceInterpolator(EasingType.Type.OUT));
        iv_baccarat_chat_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatShowAble = !chatShowAble;
                WidgetUtil.showAnimation(fl_baccarat_chat, chatShowAble, Gravity.BOTTOM);

            }
        });
    }

    private void clearAllBet() {
        if (playerBet > 0 || bankerBet > 0 || tieBet > 0 || bankerPairBet > 0 || playerPairBet > 0 || luckyBet > 0 || anyBet > 0 ||
                playerNBet > 0 || perfectBet > 0 || bankerNBet > 0 || cPlayerBet > 0 || cTieBet > 0 || cBankerBet > 0) {
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentFront, mContext, mAppViewModel.getFrontVolume());
        }
        img_bet_bg_b.setBackgroundResource(0);
        img_bet_bg_p.setBackgroundResource(0);
        img_bet_bg_t.setBackgroundResource(0);
        img_bet_bg_bp.setBackgroundResource(0);
        img_bet_bg_pp.setBackgroundResource(0);
        img_bet_bg_lucky.setBackgroundResource(0);
        img_bet_bg_any.setBackgroundResource(0);
        img_bet_bg_player_n.setBackgroundResource(0);
        img_bet_bg_perfect.setBackgroundResource(0);
        img_bet_bg_banker_n.setBackgroundResource(0);
        img_bet_bg_c_p.setBackgroundResource(0);
        img_bet_bg_c_t.setBackgroundResource(0);
        img_bet_bg_c_b.setBackgroundResource(0);
        clearAllShowChip();
        clearNoBetChip();
    }

    private void betAll() {
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 2 || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() == 5)
            return;
        if (mAppViewModel.getUser().getBalance() <= 0) {
            GdToastUtils.showToast(mContext, getString(R.string.Insufficient));
            return;
        }
        if (playerBet > 0 || bankerBet > 0 || tieBet > 0 || bankerPairBet > 0 || playerPairBet > 0 || luckyBet > 0 || anyBet > 0 ||
                playerNBet > 0 || perfectBet > 0 || bankerNBet > 0 || cPlayerBet > 0 || cTieBet > 0 || cBankerBet > 0) {
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 10, componentFront, mContext, mAppViewModel.getFrontVolume());
            //执行下注的线程
            baccaratBet = new BaccaratBet(BaccaratBetType.All);
            threadBaccaratBet = new Thread(baccaratBet);
            showBlockDialog();
            Executors.newSingleThreadExecutor().execute(threadBaccaratBet);
        }
    }

    public void clickBankerPair(View v) {
        showUpPlayerTypeBet("Pd");
    }

    public void clickPlayerPair(View v) {
        showUpPlayerTypeBet("Bd");
    }

    public void parentClick() {
        if (toolbar.getNavigationIcon() != null) {
            displayAll(false);
        } else {
            displayAll(true);
        }
        closeChat();
//        closeAll();
    }

    private void closeAll() {
        this.allClose = !allClose;
        if (allClose) {
//            ll_chip_parent.setVisibility(View.GONE);
//            ll_bet_btn_parent.setVisibility(View.GONE);
//            if (lv_table_pool.getVisibility() == View.VISIBLE)
//                WidgetUtil.showAnimation(lv_table_pool, false, Gravity.BOTTOM);
//            toolbar.setVisibility(View.GONE);
//            tvMenu.setVisibility(View.GONE);
//            leftPanel1.setVisibility(View.GONE);
        } else {
//            ll_chip_parent.setVisibility(View.VISIBLE);
//            ll_bet_btn_parent.setVisibility(View.VISIBLE);
//            toolbar.setVisibility(View.VISIBLE);
//            tvMenu.setVisibility(View.VISIBLE);
//            leftPanel1.setVisibility(View.VISIBLE);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ll_bet_btn_parent.setVisibility(View.GONE);
        }
    }

    public void closeChat() {
        if (chatShowAble) {
            chatShowAble = !chatShowAble;
            WidgetUtil.showAnimation(fl_baccarat_chat, chatShowAble, Gravity.BOTTOM);
        }
    }

    @Override
    public void onSwitchChipFinish() {
        chooseChip = 0;
        if (selectedMap != null) {
            selectedMap.clear();
        }
        setChip();
    }

    private List<ChipShowHelper> chipShowHelperList = new ArrayList<>();

    public void setChip() {


        final AdapterViewContent<ChipBean> chips = new AdapterViewContent<>(mContext, lv_baccarat_chips);
        chips.setBaseAdapter(new QuickAdapterImp<ChipBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_image_chip;
            }

            @Override
            public void convert(final ViewHolder helper, ChipBean item, final int position) {
                ImageView chipImg = helper.retrieveView(R.id.gd__iv_chip_pic);
                final LinearLayout llParent = helper.retrieveView(R.id.gd__ll_chip_parent);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    llParent.post(new Runnable() {
                        @Override
                        public void run() {
                            if (position == 0) {
                                int screenWidth = WidgetUtil.getScreenWidth(BaccaratActivity.this);
                                int padding = (screenWidth - ScreenUtil.dip2px(mContext, 60) * 7) / 2;
                                FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) lv_baccarat_chips.getLayoutParams();
                                layoutParams1.leftMargin = padding;
                                layoutParams1.rightMargin = padding;
                                lv_baccarat_chips.setLayoutParams(layoutParams1);
                            }
                        }
                    });
                    int margin = ScreenUtil.dip2px(mContext, 5);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llParent.getLayoutParams();
                    layoutParams.width = ScreenUtil.dip2px(mContext, 50);
                    layoutParams.height = ScreenUtil.dip2px(mContext, 50);
                    layoutParams.leftMargin = margin;
                    layoutParams.rightMargin = margin;
                    llParent.setLayoutParams(layoutParams);
                    chipImg.setImageResource(item.getDrawableRes());
                } else {
                    int screenWidth = WidgetUtil.getPortraitScreenWidth(BaccaratActivity.this);
                    int margin = (screenWidth / 7 - ScreenUtil.dip2px(mContext, 50)) / 2;
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llParent.getLayoutParams();
                    layoutParams.width = ScreenUtil.dip2px(mContext, 50);
                    layoutParams.height = ScreenUtil.dip2px(mContext, 50);
                    layoutParams.leftMargin = margin;
                    layoutParams.rightMargin = margin;
                    llParent.setLayoutParams(layoutParams);
                    chipImg.setImageResource(item.getDrawableRes());
                }
                helper.setText(R.id.gd__tv_chip_amount, item.getName());
                if (currentShufflingStatus == 1) {
                    if (selectedMap.get(true) != null && position == selectedMap.get(true).intValue()) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) chipImg.getLayoutParams();
                        layoutParams.width = (int) (layoutParams.width * 1.2);
                        layoutParams.height = (int) (layoutParams.height * 1.2);
                        chipImg.setLayoutParams(layoutParams);
                        helper.setBackgroundRes(R.id.gd__ll_chip_parent, R.drawable.gd_rectangle_trans_stroke_yellow);
                    } else {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) chipImg.getLayoutParams();
                        layoutParams.width = ScreenUtil.dip2px(mContext, 50);
                        layoutParams.height = ScreenUtil.dip2px(mContext, 50);
                        chipImg.setLayoutParams(layoutParams);
                        helper.setBackgroundRes(R.id.gd__ll_chip_parent, 0);
                    }
                } else {
                    selectedMap = new HashMap<>();
                    helper.setBackgroundRes(R.id.gd__ll_chip_parent, 0);
                }
            }
        });
        if (currentShufflingStatus == 1) {
            chips.setItemClick(new ItemCLickImp<ChipBean>() {
                @Override
                public void itemCLick(View view, ChipBean chipBean, int position) {
                    if (chipBean.getValue() != -101) {
                        chooseChip = chipBean.getValue();
                        selectedMap.put(true, position);
                        chips.notifyDataSetChanged();
                        initClickCount();
                        mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 8, componentFront, mContext, mAppViewModel.getFrontVolume());
                    } else {
                        showChooseChip(view);
                    }
                }
            });
            chips.setData(getCurrentChip(false));
        } else {
            chooseChip = 0;
            chips.setItemClick(new ItemCLickImp<ChipBean>() {
                @Override
                public void itemCLick(View view, ChipBean chipBean, int position) {

                }
            });
            chips.setData(getCurrentChip(false));
        }
        chipShowHelperList.clear();
        chipHelperPlayer = new ChipShowHelper(mContext, fl_baccarat_table_player_bg, chipList);
        chipHelperBanker = new ChipShowHelper(mContext, fl_baccarat_table_banker_bg, chipList);
        chipHelperTie = new ChipShowHelper(mContext, fl_baccarat_table_tie, chipList);
        chipHelperLucky = new ChipShowHelper(mContext, fl_baccarat_table_lucky, chipList);
        chipHelperAny = new ChipShowHelper(mContext, fl_baccarat_table_any, chipList);
        chipHelperPlayerN = new ChipShowHelper(mContext, fl_baccarat_table_player_natural, chipList);
        chipHelperPerfect = new ChipShowHelper(mContext, fl_baccarat_table_perfect, chipList);
        chipHelperBankerN = new ChipShowHelper(mContext, fl_baccarat_table_banker_natural, chipList);
        chipHelperCPlayer = new ChipShowHelper(mContext, fl_baccarat_table_cow_player, chipList);
        chipHelperCTie = new ChipShowHelper(mContext, fl_baccarat_table_cow_tie, chipList);
        chipHelperCBanker = new ChipShowHelper(mContext, gfl_baccarat_table_cow_banker, chipList);
        chipHelperPlayerPair = new ChipShowHelper(mContext, fl_baccarat_table_player_pair, chipList);
        chipHelperBankerPair = new ChipShowHelper(mContext, fl_baccarat_table_banker_pair, chipList);
        chipShowHelperList.add(chipHelperPlayer);
        chipShowHelperList.add(chipHelperBanker);
        chipShowHelperList.add(chipHelperTie);
        chipShowHelperList.add(chipHelperLucky);
        chipShowHelperList.add(chipHelperAny);
        chipShowHelperList.add(chipHelperPlayerN);
        chipShowHelperList.add(chipHelperPerfect);
        chipShowHelperList.add(chipHelperBankerN);
        chipShowHelperList.add(chipHelperCPlayer);
        chipShowHelperList.add(chipHelperCTie);
        chipShowHelperList.add(chipHelperCBanker);
        chipShowHelperList.add(chipHelperPlayerPair);
        chipShowHelperList.add(chipHelperBankerPair);
        chipHelperPlayer.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(Player);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperBanker.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(Banker);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperTie.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.Tie);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperLucky.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.Lucky);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperAny.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.Any);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperPlayerN.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.PlayerN);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperPerfect.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.Perfect);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperBankerN.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.BankerN);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperCPlayer.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.CowPlayer);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperCTie.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.CowTie);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperCBanker.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.CowBanker);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperPlayerPair.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.PlayerPair);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperBankerPair.setOperationButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                betAll();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllBet();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstBet) {
                    clearNoBetChip(BaccaratBetType.BankerPair);
                    setOnRepeatListener();
                }
            }
        });
        chipHelperCurrent = chipHelperPlayer;
        chipHelperPlayerPair.setTextGravity(Gravity.TOP);
        chipHelperTie.setTextGravity(Gravity.TOP);
        chipHelperLucky.setTextGravity(Gravity.TOP);
        chipHelperAny.setTextGravity(Gravity.TOP);
        chipHelperPlayerN.setTextGravity(Gravity.TOP);
        chipHelperPerfect.setTextGravity(Gravity.TOP);
        chipHelperBankerN.setTextGravity(Gravity.TOP);
        chipHelperCPlayer.setTextGravity(Gravity.TOP);
        chipHelperCTie.setTextGravity(Gravity.TOP);
        chipHelperCBanker.setTextGravity(Gravity.TOP);
        chipHelperBanker.setTextGravity(Gravity.TOP);
        chipHelperPlayer.setTextGravity(Gravity.TOP);
        chipHelperBankerPair.setTextGravity(Gravity.TOP);
        chipHelperPlayerPair.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperTie.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperLucky.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperAny.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperPlayerN.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperPerfect.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperBankerN.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperCPlayer.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperCTie.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperCBanker.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperBanker.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperPlayer.setTopMargin(AutoUtils.getPercentHeightSize(7));
        chipHelperBankerPair.setTopMargin(AutoUtils.getPercentHeightSize(7));

        chipHelperPlayerPair.setMoneyTipsTextSize(12);
        chipHelperBankerPair.setMoneyTipsTextSize(12);
        chipHelperTie.setMoneyTipsTextSize(12);
        chipHelperLucky.setMoneyTipsTextSize(12);
        chipHelperAny.setMoneyTipsTextSize(12);
        chipHelperPlayerN.setMoneyTipsTextSize(12);
        chipHelperPerfect.setMoneyTipsTextSize(12);
        chipHelperBankerN.setMoneyTipsTextSize(12);
        chipHelperCPlayer.setMoneyTipsTextSize(12);
        chipHelperCTie.setMoneyTipsTextSize(12);
        chipHelperCBanker.setMoneyTipsTextSize(12);
        chipHelperBanker.setMoneyTipsTextSize(12);
        chipHelperPlayer.setMoneyTipsTextSize(12);
    }

    public void setTableLimit() {
        AdapterViewContent<LiveInfoBean> contentList = new AdapterViewContent<>(mContext, lvTableBetLimitRed);
        contentList.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_live_info;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.gd__tv_type);
                tvType.setText(item.getType());
                switch (position) {
                    case 0:
                        tvType.setTextColor(ContextCompat.getColor(mContext, R.color.red));
                        break;
                    case 1:
                        tvType.setTextColor(ContextCompat.getColor(mContext, R.color.blue));
                        break;
                    case 2:
                        tvType.setTextColor(ContextCompat.getColor(mContext, R.color.green500));
                        break;
                    case 3:
                        tvType.setTextColor(ContextCompat.getColor(mContext, R.color.orange));
                        break;
                    case 4:
                        tvType.setTextColor(ContextCompat.getColor(mContext, R.color.blue_300));
                        break;
                }
                helper.setText(R.id.gd__tv_value, item.getValue1() + "  -  " + item.getValue2());
                helper.setTextColor(R.id.gd__tv_value, getResources().getColor(R.color.yellow_gold));
                helper.setVisibility(R.id.gd__tv_value2, View.GONE);

            }
        });
        List<LiveInfoBean> limit = new ArrayList<LiveInfoBean>();
        limit.add(new LiveInfoBean(getString(R.string.banker1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinBankerPlayerBet())
                , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxBankerPlayerBet())));
        limit.add(new LiveInfoBean(getString(R.string.player1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinBankerPlayerBet())
                , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxBankerPlayerBet())));
        limit.add(new LiveInfoBean(getString(R.string.tie1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinTieBet())
                , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTieBet())));
        limit.add(new LiveInfoBean(getString(R.string.PP1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPairBet())
                , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPairBet())));
        limit.add(new LiveInfoBean(getString(R.string.BP1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPairBet())
                , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPairBet())));
        contentList.setData(limit);
        tableName = "";
        switch (mAppViewModel.getTableId()) {
            case 1:
                tableName = "LB1";
                break;
            case 2:
                tableName = "LB2";
                break;
            case 3:
                tableName = "LB3";
                break;
            case 61:
                tableName = "LB5";
                break;
            case 62:
                tableName = "LB6";
                break;
            case 63:
                tableName = "LB7";
                break;
            case 71:
                tableName = "BM1";
                break;
        }
        btn_limit.setText(tableName + ":" + getString(R.string.LIMIT));

    }

    private void setTablePool(ListView listView) {
        if (contentPool == null)
            contentPool = new AdapterViewContent<>(mContext, listView);
        contentPool.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {

            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_user_info;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.gd__tv_name);
                TextView tvValue = helper.retrieveView(R.id.gd__tv_value);
                tvType.setText(item.getType());
                tvValue.setText(item.getValue1());
            }
        });


        contentPool.setData(getPoolData());
    }


    public List<LiveInfoBean> getPoolData() {
        List<LiveInfoBean> strData = new ArrayList<LiveInfoBean>();
        LiveInfoBean data;
        if (getCurrentBetContent() == 1) {
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getBanker() > 0)
                data = new LiveInfoBean(getString(R.string.gd_b), mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getBanker() + "", "");
            else
                data = new LiveInfoBean(getString(R.string.gd_b), "0", "");
            strData.add(data);
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getPlayer() > 0)
                data = new LiveInfoBean(getString(R.string.gd_p), mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getPlayer() + "", "");
            else
                data = new LiveInfoBean(getString(R.string.gd_p), "0", "");
            strData.add(data);
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getTie() > 0)
                data = new LiveInfoBean(getString(R.string.tie), mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getTie() + "", "");
            else
                data = new LiveInfoBean(getString(R.string.tie), "0", "");
            strData.add(data);
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getBankerPair() > 0)
                data = new LiveInfoBean(getString(R.string.BP), mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getBankerPair() + "", "");
            else
                data = new LiveInfoBean(getString(R.string.BP), "0", "");
            strData.add(data);
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getPlayerPair() > 0)
                data = new LiveInfoBean(getString(R.string.PP), mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPool().getPlayerPair() + "", "");
            else
                data = new LiveInfoBean(getString(R.string.PP), "0", "");
            strData.add(data);
            data = new LiveInfoBean(getString(R.string.lucky6), luckySixMoney, "");
            strData.add(data);
            data = new LiveInfoBean(getString(R.string.anypairs), anyPairMoney, "");
            strData.add(data);
            data = new LiveInfoBean(getString(R.string.nplayer), playerNaturalMoney, "");
            strData.add(data);
            data = new LiveInfoBean(getString(R.string.perfectpair), perfectPairMoney, "");
            strData.add(data);
            data = new LiveInfoBean(getString(R.string.nbanker), bankerNaturalMoney, "");
            strData.add(data);
        } else {
            data = new LiveInfoBean(getString(R.string.cplayer), cowPlayerMoney, "");
            strData.add(data);
            data = new LiveInfoBean(getString(R.string.ctie), cowTieMoney, "");
            strData.add(data);
            data = new LiveInfoBean(getString(R.string.cbanker), cowBankerMoney, "");
            strData.add(data);
        }
        return strData;
    }

    public List<String> getBetData(String type) {
        List<String> strData = new ArrayList<String>();
        String data = "";

        //   mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getOtherUserBetInfomation()
        for (int i = 0; i < mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getOtherUserBetInfomation().size(); i++) {
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getOtherUserBetInfomation().get(i).getType().equals(type)) {
                for (int j = 0; j < mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPlayer().size(); j++) {
                    if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPlayer().get(j).getNumber().contains(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getOtherUserBetInfomation().get(i).getAreaId())) {
                        data = mAppViewModel.hideName(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPlayer().get(j).getName()) + " : " +
                                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getOtherUserBetInfomation().get(i).getBetMoney();
                        strData.add(data);
                        break;
                    }
                }
            }
        }

        return strData;
    }

    private void setTableBetPool(ListView listView, String type) {
        if (contentBetPool == null)
            contentBetPool = new AdapterViewContent<>(mContext, listView);
        contentBetPool.setBaseAdapter(new QuickAdapterImp<String>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_limit_red_text;
            }

            @Override
            public void convert(ViewHolder helper, String item, int position) {
                TextView tv = helper.retrieveView(R.id.gd__text_tv1);
                tv.setTextSize(10);
                tv.setText(item);
                tv.setTextColor(getResources().getColor(R.color.yellow_brown_white_word));
            }
        });
        contentBetPool.setData(getBetData(type));
    }

    @Override
    protected int getLayoutRes() {

        return R.layout.gd_activity_baccarat_bet_game;
    }

    public void startPlayVideo() {
        mPreview = findViewById(R.id.gd__surface);
        videoHelper = new VideoHelper(mContext, mPreview) {
            @Override
            public void doVideoFix() {
                super.doVideoFix();
                if (findViewById(R.id.gd__fl_baccarat_bg) != null)
                    findViewById(R.id.gd__fl_baccarat_bg).setVisibility(View.GONE);
            }
        };
        String localPath = "/myvideo1";
        if (mAppViewModel.getTableId() > 3) {
            localPath = "/b" + mAppViewModel.getTableId() + "1";
        }
        switch (tableId) {
            case 1:
                localPath = "/L01";
                break;
            case 2:
                localPath = "/L02";
                break;
            case 3:
                localPath = "/L03";
                break;
            case 61:
                localPath = "/L061";
                break;
            case 62:
                localPath = "/L062";
                break;
            case 63:
                localPath = "/L063";
                break;
        }
        path = mAppViewModel.getUser().getVideoUrl() + "/" + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getVideoUrlIndex() + localPath;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            switch (tableId) {
                case 1:
                    path = mAppViewModel.getUser().getVideoUrl() + "/live/L01new";
                    break;
                case 2:
                    path = mAppViewModel.getUser().getVideoUrl() + "/live/L02new";
                    break;
                case 3:
                    path = mAppViewModel.getUser().getVideoUrl() + "/live/L03new";
                    break;
                case 61:
                    path = mAppViewModel.getUser().getVideoUrl() + "/live/L06new";
                    break;
                case 62:
                    path = mAppViewModel.getUser().getVideoUrl() + "/live/L07new";
                    break;
                case 63:
                    path = mAppViewModel.getUser().getVideoUrl() + "/live/L08new";
                    break;
            }
        }
//        path = "rtmp://202.36.58.169/live/M01";
        videoHelper.setPlayUrl(path);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        stopUpdateStatusThread();
        videoHelper.stopVideo();
//        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setGameStatus(1);
        mAppViewModel.closeMuzicService(mContext, BackgroudMuzicService.class);
        mAppViewModel.closeMuzicService(mContext, FrontMuzicService.class);
//        hidePoker(0);
    }

    @Override
    protected void onStop() {
        super.onStop();

        timerCancel();
        if (user != null)
            user.close();
    }


    private void timerCancel() {
        if (timer != null) {
            timer.cancel();
        }
    }

    String playerBetMsg;
    String ppBetMsg;
    String tieBetMsg;
    String bpBetMsg;
    String bankerBetMsg;
    PopupWindow userBetPop;

    public void showUpPop() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.gd_layout_baccarat_b_bet, null);
        TextView tv_player_money = (TextView) view.findViewById(R.id.gd__tv_player_money);
        tv_player_money.setText(playerBetMsg);
        TextView tv_player_pair_money = (TextView) view.findViewById(R.id.gd__tv_player_pair_money);
        tv_player_pair_money.setText(ppBetMsg);
        TextView tv_tie_money = (TextView) view.findViewById(R.id.gd__tv_tie_money);
        tv_tie_money.setText(tieBetMsg);
        TextView tv_banker_pair_money = (TextView) view.findViewById(R.id.gd__tv_banker_pair_money);
        tv_banker_pair_money.setText(bpBetMsg);
        TextView tv_banker_money = (TextView) view.findViewById(R.id.gd__tv_banker_money);
        tv_banker_money.setText(bankerBetMsg);
        userBetPop = new PopupWindow(view, 300, 400);
// 允许点击外部消失
        userBetPop.setBackgroundDrawable(new BitmapDrawable());
        userBetPop.setOutsideTouchable(true);
        userBetPop.setFocusable(true);
        userBetPop.showAtLocation(tv_table_game_number, Gravity.TOP | Gravity.LEFT, 100, 125);
    }

    private int playerBetMoney1;
    private int playerBetMoney2;
    private int playerBetMoney3;
    private int playerBetMoney5;
    private int playerBetMoney6;
    private int playerBetMoney7;
    private int playerBetMoney8;
    List<BaccaratPlayerBetTypeBean> betTypeList;

    private void showUpPlayerTypeBet(String type) {
        playerBetMoney1 = 0;
        playerBetMoney2 = 0;
        playerBetMoney3 = 0;
        playerBetMoney5 = 0;
        playerBetMoney6 = 0;
        playerBetMoney7 = 0;
        playerBetMoney8 = 0;
        if (beanList != null && beanList.size() > 0) {
            betTypeList.clear();
            switch (type) {
                case "Banker":
                    playerBetMoney1 = subStringBetMoneyInt(userBetMap.get(Banker1));
                    playerBetMoney2 = subStringBetMoneyInt(userBetMap.get(Banker2));
                    playerBetMoney3 = subStringBetMoneyInt(userBetMap.get(Banker3));
                    playerBetMoney5 = subStringBetMoneyInt(userBetMap.get(Banker5));
                    playerBetMoney6 = subStringBetMoneyInt(userBetMap.get(Banker6));
                    playerBetMoney7 = subStringBetMoneyInt(userBetMap.get(Banker7));
                    playerBetMoney8 = subStringBetMoneyInt(userBetMap.get(Banker8));
                    break;
                case "Player":
                    playerBetMoney1 = subStringBetMoneyInt(userBetMap.get(Player1));
                    playerBetMoney2 = subStringBetMoneyInt(userBetMap.get(Player2));
                    playerBetMoney3 = subStringBetMoneyInt(userBetMap.get(Player3));
                    playerBetMoney5 = subStringBetMoneyInt(userBetMap.get(Player5));
                    playerBetMoney6 = subStringBetMoneyInt(userBetMap.get(Player6));
                    playerBetMoney7 = subStringBetMoneyInt(userBetMap.get(Player7));
                    playerBetMoney8 = subStringBetMoneyInt(userBetMap.get(Player8));
                    break;
                case "Tie":
                    playerBetMoney1 = subStringBetMoneyInt(userBetMap.get(Tie1));
                    playerBetMoney2 = subStringBetMoneyInt(userBetMap.get(Tie2));
                    playerBetMoney3 = subStringBetMoneyInt(userBetMap.get(Tie3));
                    playerBetMoney5 = subStringBetMoneyInt(userBetMap.get(Tie5));
                    playerBetMoney6 = subStringBetMoneyInt(userBetMap.get(Tie6));
                    playerBetMoney7 = subStringBetMoneyInt(userBetMap.get(Tie7));
                    playerBetMoney8 = subStringBetMoneyInt(userBetMap.get(Tie8));
                    break;
                case "Pd":
                    playerBetMoney1 = subStringBetMoneyInt(userBetMap.get(Pd1));
                    playerBetMoney2 = subStringBetMoneyInt(userBetMap.get(Pd2));
                    playerBetMoney3 = subStringBetMoneyInt(userBetMap.get(Pd3));
                    playerBetMoney5 = subStringBetMoneyInt(userBetMap.get(Pd5));
                    playerBetMoney6 = subStringBetMoneyInt(userBetMap.get(Pd6));
                    playerBetMoney7 = subStringBetMoneyInt(userBetMap.get(Pd7));
                    playerBetMoney8 = subStringBetMoneyInt(userBetMap.get(Pd8));
                    break;
                case "Bd":
                    playerBetMoney1 = subStringBetMoneyInt(userBetMap.get(Bd1));
                    playerBetMoney2 = subStringBetMoneyInt(userBetMap.get(Bd2));
                    playerBetMoney3 = subStringBetMoneyInt(userBetMap.get(Bd3));
                    playerBetMoney5 = subStringBetMoneyInt(userBetMap.get(Bd5));
                    playerBetMoney6 = subStringBetMoneyInt(userBetMap.get(Bd6));
                    playerBetMoney7 = subStringBetMoneyInt(userBetMap.get(Bd7));
                    playerBetMoney8 = subStringBetMoneyInt(userBetMap.get(Bd8));
                    break;
            }
            String userNname = mAppViewModel.getUser().getName();
            for (int j = 0; j < beanList.size(); j++) {
                GameRefreshBean bean = beanList.get(j);
                String name = bean.getName();
                if (!name.equals(userNname)) {
                    betTypeList.add(getPlayerBetTypeBean(bean));
                }
            }
            View view = LayoutInflater.from(mContext).inflate(R.layout.gd_popupwindow_list_bet_people, null);
            ListView listView = (ListView) view.findViewById(R.id.gd__lv_people_bet_info);
            AdapterViewContent<BaccaratPlayerBetTypeBean> adapterViewContent = new AdapterViewContent<BaccaratPlayerBetTypeBean>(mContext, listView);
            adapterViewContent.setBaseAdapter(new QuickAdapterImp<BaccaratPlayerBetTypeBean>() {
                @Override
                public int getBaseItemResource() {
                    return R.layout.gd_item_baccarat_player_bet;
                }

                @Override
                public void convert(ViewHolder helper, BaccaratPlayerBetTypeBean item, int position) {
                    helper.setText(R.id.gd__tv_player_name, item.getName());
                    helper.setText(R.id.gd__tv_player_money, item.getMoney() + "");
                }
            });
            adapterViewContent.setData(betTypeList);
            PopupWindow pop = new PopupWindow(view, 380, 470);
            pop.setBackgroundDrawable(new BitmapDrawable());
            pop.setOutsideTouchable(true);
            pop.setFocusable(true);
            pop.showAtLocation(tv_table_game_number, Gravity.TOP | Gravity.RIGHT, 180, 200);
        }

    }

    private BaccaratPlayerBetTypeBean getPlayerBetTypeBean(GameRefreshBean bean) {
        switch (bean.getSeatNum()) {
            case "1":
                return new BaccaratPlayerBetTypeBean(bean.getName() + ":", playerBetMoney1);
            case "2":
                return new BaccaratPlayerBetTypeBean(bean.getName() + ":", playerBetMoney2);
            case "3":
                return new BaccaratPlayerBetTypeBean(bean.getName() + ":", playerBetMoney3);
            case "5":
                return new BaccaratPlayerBetTypeBean(bean.getName() + ":", playerBetMoney5);
            case "6":
                return new BaccaratPlayerBetTypeBean(bean.getName() + ":", playerBetMoney6);
            case "7":
                return new BaccaratPlayerBetTypeBean(bean.getName() + ":", playerBetMoney7);
            case "8":
                return new BaccaratPlayerBetTypeBean(bean.getName() + ":", playerBetMoney8);
        }
        return null;
    }

    private String subStringBetMoney(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            String money[] = str.split(":");
            return money[1];
        }
    }

    private void clearUserBet() {
        playerBetMsg = "";
        ppBetMsg = "";
        tieBetMsg = "";
        bpBetMsg = "";
        bankerBetMsg = "";
    }

    public void clickname1(View fl) {
        if (mAppViewModel.getUser().getName().equals(tv_name1.getText().toString())) {
            return;
        }
        clearUserBet();
        playerBetMsg = subStringBetMoney(userBetMap.get(Player1));
        ppBetMsg = subStringBetMoney(userBetMap.get(Pd1));
        tieBetMsg = subStringBetMoney(userBetMap.get(Tie1));
        bpBetMsg = subStringBetMoney(userBetMap.get(Bd1));
        bankerBetMsg = subStringBetMoney(userBetMap.get(Banker1));
        showUpPop();
    }

    public void clickname2(View fl) {
        if (mAppViewModel.getUser().getName().equals(tv_name2.getText().toString())) {
            return;
        }
        clearUserBet();
        playerBetMsg = subStringBetMoney(userBetMap.get(Player2));
        ppBetMsg = subStringBetMoney(userBetMap.get(Pd2));
        tieBetMsg = subStringBetMoney(userBetMap.get(Tie2));
        bpBetMsg = subStringBetMoney(userBetMap.get(Bd2));
        bankerBetMsg = subStringBetMoney(userBetMap.get(Banker2));
        showUpPop();
    }

    public void clickname3(View fl) {
        if (mAppViewModel.getUser().getName().equals(tv_name3.getText().toString())) {
            return;
        }
        clearUserBet();
        playerBetMsg = subStringBetMoney(userBetMap.get(Player3));
        ppBetMsg = subStringBetMoney(userBetMap.get(Pd3));
        tieBetMsg = subStringBetMoney(userBetMap.get(Tie3));
        bpBetMsg = subStringBetMoney(userBetMap.get(Bd3));
        bankerBetMsg = subStringBetMoney(userBetMap.get(Banker3));
        showUpPop();
    }

    public void clickname5(View fl) {
        if (mAppViewModel.getUser().getName().equals(tv_name5.getText().toString())) {
            return;
        }
        clearUserBet();
        playerBetMsg = subStringBetMoney(userBetMap.get(Player5));
        ppBetMsg = subStringBetMoney(userBetMap.get(Pd5));
        tieBetMsg = subStringBetMoney(userBetMap.get(Tie5));
        bpBetMsg = subStringBetMoney(userBetMap.get(Bd5));
        bankerBetMsg = subStringBetMoney(userBetMap.get(Banker5));
        showUpPop();
    }

    public void clickname6(View fl) {
        if (mAppViewModel.getUser().getName().equals(tv_name6.getText().toString())) {
            return;
        }
        clearUserBet();
        playerBetMsg = subStringBetMoney(userBetMap.get(Player6));
        ppBetMsg = subStringBetMoney(userBetMap.get(Pd6));
        tieBetMsg = subStringBetMoney(userBetMap.get(Tie6));
        bpBetMsg = subStringBetMoney(userBetMap.get(Bd6));
        bankerBetMsg = subStringBetMoney(userBetMap.get(Banker6));
        showUpPop();
    }

    public void clickname7(View fl) {
        if (mAppViewModel.getUser().getName().equals(tv_name7.getText().toString())) {
            return;
        }
        clearUserBet();
        playerBetMsg = subStringBetMoney(userBetMap.get(Player7));
        ppBetMsg = subStringBetMoney(userBetMap.get(Pd7));
        tieBetMsg = subStringBetMoney(userBetMap.get(Tie7));
        bpBetMsg = subStringBetMoney(userBetMap.get(Bd7));
        bankerBetMsg = subStringBetMoney(userBetMap.get(Banker7));
        showUpPop();
    }

    public void clickname8(View fl) {
        if (mAppViewModel.getUser().getName().equals(tv_name8.getText().toString())) {
            return;
        }
        clearUserBet();
        playerBetMsg = subStringBetMoney(userBetMap.get(Player8));
        ppBetMsg = subStringBetMoney(userBetMap.get(Pd8));
        tieBetMsg = subStringBetMoney(userBetMap.get(Tie8));
        bpBetMsg = subStringBetMoney(userBetMap.get(Bd8));
        bankerBetMsg = subStringBetMoney(userBetMap.get(Banker8));
        showUpPop();
    }

    int bankerShowChip;
    int playerShowChip;
    int tieShowChip;
    int luckyShowChip;
    int anyShowChip;
    int playerNShowChip;
    int perfectShowChip;
    int bankerNShowChip;
    int cPlayerShowChip;
    int cTieShowChip;
    int cBankerShowChip;
    int bbShowChip;
    int ppShowChip;

    private void clearAllShowChip() {
        bankerShowChip = 0;
        playerShowChip = 0;
        tieShowChip = 0;
        luckyShowChip = 0;
        anyShowChip = 0;
        playerNShowChip = 0;
        perfectShowChip = 0;
        bankerNShowChip = 0;
        cPlayerShowChip = 0;
        cTieShowChip = 0;
        cBankerShowChip = 0;
        bbShowChip = 0;
        ppShowChip = 0;
    }

    public void clickBetPlayer(final View fl) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperPlayer;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        if (bankerBet > 0 || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker() > 0) {
            Toast.makeText(mContext, R.string.show_limit_banker_player, Toast.LENGTH_LONG).show();
            return;
        }
        clickPlayerCount++;

        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinBankerPlayerBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxBankerPlayerBet(), clickPlayerCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        playerShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "P");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_p.setBackgroundResource(R.mipmap.gd_bet_v_p);
            } else {
                img_bet_bg_p.setBackgroundResource(R.mipmap.gd_bet_h_p);
            }
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            chipHelperCurrent.showChip(betMoney, chipPlayerBankerX, chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            playerBet = betMoney;
            //    mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP,9,componentFront,this,mAppViewModel.getFrontVolume());
            closeOtherSure();
        } else {
//            chipHelperCurrent.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer(), AutoUtils.getPercentHeightSize(4), chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            clickPlayerCount = 0;
            playerBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    private void closeOtherSure() {
        for (int i = 0; i < chipShowHelperList.size(); i++) {
            ChipShowHelper chipShowHelper = chipShowHelperList.get(i);
            if (chipShowHelper != null) {
                if (!chipShowHelper.equals(chipHelperCurrent)) {
                    chipShowHelper.setOperationButtonDisplay(false);
                }
            }
        }
    }

    public boolean checkChoose() {
        if (chooseChip < 1) {
            Toast.makeText(getApplicationContext(), getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void clickBetBanker(final View fl) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperBanker;
//        chipCount = chipCount + 10;
//
//        chipHelper1.showChip(chipCount,AutoUtils.getPercentHeightSize(4), AutoUtils.getPercentHeightSize(5), 80, 60, AutoUtils.getPercentHeightSize(46),tipY, AutoUtils.getPercentHeightSize(38), AutoUtils.getPercentHeightSize(20));
//        ((AnimationDrawable) tableImage2.getBackground()).start();
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        if (playerBet > 0 || mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer() > 0) {
            Toast.makeText(mContext, R.string.show_limit_banker_player, Toast.LENGTH_LONG).show();
            return;
        }
        clickBankerCount++;

        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinBankerPlayerBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxBankerPlayerBet(), clickBankerCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        bankerShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "B");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_b.setBackgroundResource(R.mipmap.gd_bet_v_b);
            } else {
                img_bet_bg_b.setBackgroundResource(R.mipmap.gd_bet_h_b);
            }
            chipHelperCurrent.showChip(betMoney, -chipPlayerBankerX, chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            bankerBet = betMoney;
            closeOtherSure();
        } else {
//            chipHelperCurrent.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker(), AutoUtils.getPercentHeightSize(4), chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            clickBankerCount = 0;
            bankerBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    private int getBetMoney(int betMoney, String type) {
        int allShowChip = 0;
        int tie = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getTie();
        int playerPair = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerPair();
        int bankerPair = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerPair();
        int banker = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker();
        int player = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer();
        int lucky = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getLucky();
        int any = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAny();
        int playerN = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerN();
        int perfect = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPerfect();
        int bankerN = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerN();
        int cPlayer = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer();
        int cTie = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowTie();
        int cBanker = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker();
        switch (type) {
            case "B":
                allShowChip = playerShowChip + tieShowChip + bbShowChip + ppShowChip + luckyShowChip + anyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + player + tie + playerPair + bankerPair + lucky + any + playerN + perfect + bankerN + cPlayer + cTie + cBanker;
                break;
            case "P":
                allShowChip = bankerShowChip + tieShowChip + bbShowChip + ppShowChip + luckyShowChip + anyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + banker + tie + playerPair + bankerPair + lucky + any + playerN + perfect + bankerN + cPlayer + cTie + cBanker;
                break;
            case "T":
                allShowChip = bankerShowChip + playerShowChip + bbShowChip + ppShowChip + luckyShowChip + anyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + player + banker + playerPair + bankerPair + lucky + any + playerN + perfect + bankerN + cPlayer + cTie + cBanker;
                break;
            case "BB":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + ppShowChip + luckyShowChip + anyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + player + tie + playerPair + banker + lucky + any + playerN + perfect + bankerN + cPlayer + cTie + cBanker;
                break;
            case "PP":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + bbShowChip + luckyShowChip + anyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + player + tie + banker + bankerPair + lucky + any + playerN + perfect + bankerN + cPlayer + cTie + cBanker;
                break;
            case "Lucky":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + bbShowChip + ppShowChip + anyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + player + tie + banker + playerPair + bankerPair + any + playerN + perfect + bankerN + cPlayer + cTie + cBanker;
                break;
            case "Any":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + bbShowChip + ppShowChip + luckyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + player + tie + banker + playerPair + bankerPair + lucky + playerN + perfect + bankerN + cPlayer + cTie + cBanker;
                break;
            case "PlayerN":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + bbShowChip + ppShowChip + luckyShowChip + anyShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + player + tie + banker + playerPair + bankerPair + lucky + any + perfect + bankerN + cPlayer + cTie + cBanker;
                break;
            case "Perfect":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + bbShowChip + ppShowChip + luckyShowChip + anyShowChip + playerNShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + player + tie + banker + playerPair + bankerPair + lucky + any + playerN + bankerN + cPlayer + cTie + cBanker;
                break;
            case "BankerN":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + bbShowChip + ppShowChip + luckyShowChip + anyShowChip + playerNShowChip + perfectShowChip + cPlayerShowChip + cTieShowChip + cBankerShowChip + player + tie + banker + playerPair + bankerPair + lucky + any + playerN + perfect + cPlayer + cTie + cBanker;
                break;
            case "CPlayer":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + bbShowChip + ppShowChip + luckyShowChip + anyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cTieShowChip + cBankerShowChip + player + tie + banker + playerPair + bankerPair + lucky + any + playerN + perfect + bankerN + cTie + cBanker;
                break;
            case "CTie":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + bbShowChip + ppShowChip + luckyShowChip + anyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cBankerShowChip + player + tie + banker + playerPair + bankerPair + lucky + any + playerN + perfect + bankerN + cPlayer + cBanker;
                break;
            case "CBanker":
                allShowChip = bankerShowChip + playerShowChip + tieShowChip + bbShowChip + ppShowChip + luckyShowChip + anyShowChip + playerNShowChip + perfectShowChip + bankerNShowChip + cPlayerShowChip + cTieShowChip + player + tie + banker + playerPair + bankerPair + lucky + any + playerN + perfect + bankerN + cPlayer + cTie;
                break;
        }
        int maxTotalBet = mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet();

        int canBet = maxTotalBet - allShowChip;
        Log.d("getBetMoney", "betMoney: " + betMoney + "----" + "maxTotalBet: " + maxTotalBet + "----" + "allShowChip: " + allShowChip + "----" + "canBet: " + canBet);
        if (betMoney > canBet) {
            betMoney = canBet;
        }
        return betMoney;
    }

    public void clickBetTie(final View f) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperTie;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickTieCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinTieBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTieBet(), clickTieCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getTie(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        tieShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "T");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_t.setBackgroundResource(R.mipmap.gd_bet_v_t);
            } else {
                img_bet_bg_t.setBackgroundResource(R.mipmap.gd_bet_h_t);
            }
            chipHelperCurrent.showChip(betMoney, tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            tieBet = betMoney;
            closeOtherSure();
        } else {
//            chipHelperCurrent.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getTie(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            clickTieCount = 0;
            tieBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetLucky(final View f) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperLucky;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickLuckyCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinLuckySixBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxLuckySixBet(), clickLuckyCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getLucky(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        luckyShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "Lucky");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_lucky.setBackgroundResource(R.mipmap.gd_bet_v_lucky);
            } else {
                img_bet_bg_lucky.setBackgroundResource(R.mipmap.gd_bet_h_lucky);
            }
            chipHelperCurrent.showChip(betMoney, tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            luckyBet = betMoney;
            closeOtherSure();
        } else {
            clickLuckyCount = 0;
            luckyBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetAny(final View f) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperAny;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickAnyCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinAnyPairBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxAnyPairBet(), clickAnyCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAny(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        anyShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "Any");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_any.setBackgroundResource(R.mipmap.gd_bet_v_any);
            } else {
                img_bet_bg_any.setBackgroundResource(R.mipmap.gd_bet_h_any);
            }
            chipHelperCurrent.showChip(betMoney, tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            anyBet = betMoney;
            closeOtherSure();
        } else {
            clickAnyCount = 0;
            anyBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetPlayerNatural(final View f) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperPlayerN;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickPlayerNCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPlayerNaturalBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPlayerNaturalBet(), clickPlayerNCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerN(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        playerNShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "PlayerN");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_player_n.setBackgroundResource(R.mipmap.gd_bet_v_pn);
            } else {
                img_bet_bg_player_n.setBackgroundResource(R.mipmap.gd_bet_h_pn);
            }
            chipHelperCurrent.showChip(betMoney, tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            playerNBet = betMoney;
            closeOtherSure();
        } else {
            clickPlayerNCount = 0;
            playerNBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetPlayerPerfect(final View f) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperPerfect;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickPerfectCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPerfectPairBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPerfectPairBet(), clickPerfectCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPerfect(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        perfectShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "Perfect");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_perfect.setBackgroundResource(R.mipmap.gd_bet_v_perfect);
            } else {
                img_bet_bg_perfect.setBackgroundResource(R.mipmap.gd_bet_h_perfect);
            }
            chipHelperCurrent.showChip(betMoney, tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            perfectBet = betMoney;
            closeOtherSure();
        } else {
            clickPerfectCount = 0;
            perfectBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetBankerNatural(final View f) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperBankerN;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickBankerNCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinBankerNaturalBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxBankerNaturalBet(), clickBankerNCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerN(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        bankerNShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "BankerN");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_banker_n.setBackgroundResource(R.mipmap.gd_bet_v_bn);
            } else {
                img_bet_bg_banker_n.setBackgroundResource(R.mipmap.gd_bet_h_bn);
            }
            chipHelperCurrent.showChip(betMoney, tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            bankerNBet = betMoney;
            closeOtherSure();
        } else {
            clickBankerNCount = 0;
            bankerNBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetCowPlayer(final View f) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperCPlayer;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickCPlayerCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinCowPlayerBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxCowPlayerBet(), clickCPlayerCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        cPlayerShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "CPlayer");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_c_p.setBackgroundResource(R.mipmap.gd_bet_v_cp);
            } else {
                img_bet_bg_c_p.setBackgroundResource(R.mipmap.gd_bet_h_cp);
            }
            chipHelperCurrent.showChip(betMoney, tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            cPlayerBet = betMoney;
            closeOtherSure();
        } else {
            clickCPlayerCount = 0;
            cPlayerBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetCowTie(final View f) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperCTie;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickCTieCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinCowTieBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxCowTieBet(), clickCTieCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowTie(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        cTieShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "CTie");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_c_t.setBackgroundResource(R.mipmap.gd_bet_v_ct);
            } else {
                img_bet_bg_c_t.setBackgroundResource(R.mipmap.gd_bet_h_ct);
            }
            chipHelperCurrent.showChip(betMoney, tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            cTieBet = betMoney;
            closeOtherSure();
        } else {
            clickCTieCount = 0;
            cTieBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetCowBanker(final View f) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperCBanker;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickCBankerCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinCowBankerBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxCowBankerBet(), clickCBankerCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        bankerNShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "CBanker");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_c_b.setBackgroundResource(R.mipmap.gd_bet_v_cb);
            } else {
                img_bet_bg_c_b.setBackgroundResource(R.mipmap.gd_bet_h_cb);
            }
            chipHelperCurrent.showChip(betMoney, tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            cBankerBet = betMoney;
            closeOtherSure();
        } else {
            clickCBankerCount = 0;
            cBankerBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetPlayerPair(final View fl2) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperPlayerPair;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickPlayerPairCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPairBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPairBet(), clickPlayerPairCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerPair(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        ppShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "PP");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_pp.setBackgroundResource(R.mipmap.gd_bet_v_pp);
            } else {
                img_bet_bg_pp.setBackgroundResource(R.mipmap.gd_bet_h_pp);
            }
            chipHelperCurrent.showChip(betMoney, chipX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            playerPairBet = betMoney;
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            closeOtherSure();
        } else {
//            chipHelperCurrent.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerPair(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            clickPlayerPairCount = 0;
            playerPairBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickBetBankerPair(final View fl) {
        if (checkChoose()) return;
        chipHelperCurrent = chipHelperBankerPair;
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 1)
            return;
        clickBankerPairCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPairBet(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPairBet(), clickBankerPairCount,
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerPair(),
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        bbShowChip = betMoney;
        betMoney = getBetMoney(betMoney, "BB");
        if (betMoney > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_bp.setBackgroundResource(R.mipmap.gd_bet_v_bp);
            } else {
                img_bet_bg_bp.setBackgroundResource(R.mipmap.gd_bet_h_bp);
            }
            chipHelperCurrent.showChip(betMoney, -chipX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            BetUiHelper.betStateColor(chipHelperCurrent, tvTableBetSure, true);
            bankerPairBet = betMoney;
            closeOtherSure();
        } else {
//            chipHelperCurrent.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerPair(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            clickBankerPairCount = 0;
            bankerPairBet = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    @Override
    protected void showLimit() {
        super.showLimit();
        if (lvTableBetLimitRed.getVisibility() == View.VISIBLE) {
            lvTableBetLimitRed.setVisibility(View.GONE);
        } else {
            lvTableBetLimitRed.setVisibility(View.VISIBLE);
        }
    }

    public void clickCancel(View v) {
        //  chipHelper1.clearAllChips();
        clearNoBetChip();
    }

    public void clearNoBetChip() {
        chipHelperCurrent = null;
        clearAllChips();
        /*chipHelperPlayer.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer(), AutoUtils.getPercentHeightSize(4),  chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32), AutoUtils.getPercentHeightSize(20));
        chipHelperBanker.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker(), AutoUtils.getPercentHeightSize(4),  chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32), AutoUtils.getPercentHeightSize(20));
        chipHelperTie.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getTie(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32), AutoUtils.getPercentHeightSize(20));
        chipHelperPlayerPair.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerPair(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32), AutoUtils.getPercentHeightSize(20));
        chipHelperBankerPair.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerPair(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32), AutoUtils.getPercentHeightSize(20));
*/
        initBetInformation(BaccaratBetType.All);
    }


    public void initBetInformation(BaccaratBetType type) {
        clearNoBetChip(type);
    }

    public void clearAllChips() {
        BetUiHelper.betStateColor(tvTableBetSure, false);
        chipHelperBankerPair.setOperationButtonDisplay(false);
        chipHelperPlayerPair.setOperationButtonDisplay(false);
        chipHelperTie.setOperationButtonDisplay(false);
        chipHelperLucky.setOperationButtonDisplay(false);
        chipHelperAny.setOperationButtonDisplay(false);
        chipHelperPlayerN.setOperationButtonDisplay(false);
        chipHelperPerfect.setOperationButtonDisplay(false);
        chipHelperBankerN.setOperationButtonDisplay(false);
        chipHelperCPlayer.setOperationButtonDisplay(false);
        chipHelperCTie.setOperationButtonDisplay(false);
        chipHelperCBanker.setOperationButtonDisplay(false);
        chipHelperBanker.setOperationButtonDisplay(false);
        chipHelperPlayer.setOperationButtonDisplay(false);

        chipHelperPlayer.clearAllChips();
        chipHelperBanker.clearAllChips();
        chipHelperTie.clearAllChips();
        chipHelperLucky.clearAllChips();
        chipHelperAny.clearAllChips();
        chipHelperPlayerN.clearAllChips();
        chipHelperPerfect.clearAllChips();
        chipHelperBankerN.clearAllChips();
        chipHelperCPlayer.clearAllChips();
        chipHelperCTie.clearAllChips();
        chipHelperCBanker.clearAllChips();
        chipHelperPlayerPair.clearAllChips();
        chipHelperBankerPair.clearAllChips();

    }

    public void showBetMoney() {
        //     Log.i(WebSiteUrl.Tag, "showBetMoney Banker= " + mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker());
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer() > 0)//清楚未下注的筹码
        {
            chipHelperPlayer.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer(), AutoUtils.getPercentHeightSize(4), chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker() > 0)//清楚未下注的筹码
        {
            chipHelperBanker.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker(), AutoUtils.getPercentHeightSize(4), chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getTie() > 0)//清楚未下注的筹码
        {
            chipHelperTie.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getTie(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getLucky() > 0)//清楚未下注的筹码
        {
            chipHelperLucky.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getLucky(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAny() > 0)//清楚未下注的筹码
        {
            chipHelperAny.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAny(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerN() > 0)//清楚未下注的筹码
        {
            chipHelperPlayerN.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerN(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPerfect() > 0)//清楚未下注的筹码
        {
            chipHelperPerfect.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPerfect(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerN() > 0)//清楚未下注的筹码
        {
            chipHelperBankerN.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerN(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer() > 0)//清楚未下注的筹码
        {
            chipHelperCPlayer.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowTie() > 0)//清楚未下注的筹码
        {
            chipHelperCTie.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowTie(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker() > 0)//清楚未下注的筹码
        {
            chipHelperCBanker.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerPair() > 0)//清楚未下注的筹码
        {
            chipHelperPlayerPair.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerPair(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerPair() > 0)//清楚未下注的筹码
        {
            chipHelperBankerPair.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerPair(), AutoUtils.getPercentHeightSize(4), chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));

        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getGameStatus() != 5) {
            showBetBg();
        }

        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer() > 0 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer() > 0) {
            setBigPoker(pw_poker_player1, 1);
            setBigPoker(pw_poker_player2, 2);
        } else if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker() > 0 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker() > 0) {
            setBigPoker(pw_poker_banker1, 1);
            setBigPoker(pw_poker_banker2, 2);
        }

        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker() > 0 &&
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer() > 0) {
            setSmallPoker(pw_poker_player1, 1);
            setSmallPoker(pw_poker_player2, 2);
            setSmallPoker(pw_poker_banker1, 1);
            setSmallPoker(pw_poker_banker2, 2);
        }

        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer() > 0 &&
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker() > 0) {
            setSmallPoker(pw_poker_player1, 1);
            setSmallPoker(pw_poker_player2, 2);
            setSmallPoker(pw_poker_banker1, 1);
            setSmallPoker(pw_poker_banker2, 2);
        }

        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker() > 0 &&
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer() > 0) {
            setSmallPoker(pw_poker_player1, 1);
            setSmallPoker(pw_poker_player2, 2);
            setSmallPoker(pw_poker_banker1, 1);
            setSmallPoker(pw_poker_banker2, 2);
        }
    }

    private void showApng(int position) {
        for (int i = 0; i < apngPlayBeanList.size(); i++) {
            ApngImageView apngImageView = apngPlayBeanList.get(i).getApngImageView();
            String uri = apngPlayBeanList.get(i).getUri();
            if (i == position) {
                File file = FileUtils.processApngFile(uri, mContext);
                ApngLoader.getInstance().loadApng(file.getAbsolutePath(), apngImageView);
                apngImageView.setVisibility(View.VISIBLE);
                apngImageView.positon = i;
            } else {
                apngImageView.stop();
                apngImageView.setVisibility(View.INVISIBLE);
            }
        }
    }

    ObjectAnimator objectAnimatorB;
    ObjectAnimator objectAnimatorP;
    ObjectAnimator objectAnimatorT;
    ObjectAnimator objectAnimatorBP;
    ObjectAnimator objectAnimatorPP;
    ObjectAnimator objectAnimatorLucky;
    ObjectAnimator objectAnimatorAny;
    ObjectAnimator objectAnimatorPN;
    ObjectAnimator objectAnimatorPerfect;
    ObjectAnimator objectAnimatorBN;
    ObjectAnimator objectAnimatorCP;
    ObjectAnimator objectAnimatorCT;
    ObjectAnimator objectAnimatorCB;

    public void showResultsOnUI() {
//        clearBetBg();
        if (animationBanker == null || animationPlayer == null) {
            initResultAnimation();
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getLucky6() == 12 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getLucky6() == 20 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getLucky6() == 1) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_lucky.setBackgroundResource(R.mipmap.gd_bet_v_lucky);
            } else {
                img_bet_bg_lucky.setBackgroundResource(R.mipmap.gd_bet_h_lucky);
            }
            if (objectAnimatorLucky.isRunning()) {
                objectAnimatorLucky.cancel();
                objectAnimatorLucky.start();
            } else {
                objectAnimatorLucky.start();
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getAnyPairs() == 12 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getAnyPairs() == 20 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getAnyPairs() == 1) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_any.setBackgroundResource(R.mipmap.gd_bet_v_any);
            } else {
                img_bet_bg_any.setBackgroundResource(R.mipmap.gd_bet_h_any);
            }
            if (objectAnimatorAny.isRunning()) {
                objectAnimatorAny.cancel();
                objectAnimatorAny.start();
            } else {
                objectAnimatorAny.start();
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getnPlayer() == 12 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getnPlayer() == 20 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getnPlayer() == 1) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_player_n.setBackgroundResource(R.mipmap.gd_bet_v_pn);
            } else {
                img_bet_bg_player_n.setBackgroundResource(R.mipmap.gd_bet_h_pn);
            }
            if (objectAnimatorPN.isRunning()) {
                objectAnimatorPN.cancel();
                objectAnimatorPN.start();
            } else {
                objectAnimatorPN.start();
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getPerfectPairs() == 12 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getPerfectPairs() == 20 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getPerfectPairs() == 1) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_perfect.setBackgroundResource(R.mipmap.gd_bet_v_perfect);
            } else {
                img_bet_bg_perfect.setBackgroundResource(R.mipmap.gd_bet_h_perfect);
            }
            if (objectAnimatorPerfect.isRunning()) {
                objectAnimatorPerfect.cancel();
                objectAnimatorPerfect.start();
            } else {
                objectAnimatorPerfect.start();
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getnBanker() == 12 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getnBanker() == 20 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getnBanker() == 1) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_banker_n.setBackgroundResource(R.mipmap.gd_bet_v_bn);
            } else {
                img_bet_bg_banker_n.setBackgroundResource(R.mipmap.gd_bet_h_bn);
            }
            if (objectAnimatorBN.isRunning()) {
                objectAnimatorBN.cancel();
                objectAnimatorBN.start();
            } else {
                objectAnimatorBN.start();
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getBanker_palyer_tie() == 1) {
//            iv_baccarat_table_banker.setVisibility(View.VISIBLE);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_b.setBackgroundResource(R.mipmap.gd_bet_v_b);
                img_bet_bg_c_b.setBackgroundResource(R.mipmap.gd_bet_v_cb);
            } else {
                img_bet_bg_b.setBackgroundResource(R.mipmap.gd_bet_h_b);
                img_bet_bg_c_b.setBackgroundResource(R.mipmap.gd_bet_h_cb);
            }
            if (objectAnimatorB.isRunning()) {
                objectAnimatorB.cancel();
                objectAnimatorB.start();
            } else {
                objectAnimatorB.start();
            }
            if (objectAnimatorCB.isRunning()) {
                objectAnimatorCB.cancel();
                objectAnimatorCB.start();
            } else {
                objectAnimatorCB.start();
            }
            showApng(2);
            animationBanker.start();
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_RESULTS, 4, componentFront, mContext, mAppViewModel.getFrontVolume());
        } else if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getBanker_palyer_tie() == 2) {
            showApng(0);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_p.setBackgroundResource(R.mipmap.gd_bet_v_p);
                img_bet_bg_c_p.setBackgroundResource(R.mipmap.gd_bet_v_cp);
            } else {
                img_bet_bg_p.setBackgroundResource(R.mipmap.gd_bet_h_p);
                img_bet_bg_c_p.setBackgroundResource(R.mipmap.gd_bet_h_cp);
            }
            animationPlayer.start();
//            iv_baccarat_table_player.setVisibility(View.VISIBLE);
            if (objectAnimatorP.isRunning()) {
                objectAnimatorP.cancel();
                objectAnimatorP.start();
            } else {
                objectAnimatorP.start();
            }
            if (objectAnimatorCP.isRunning()) {
                objectAnimatorCP.cancel();
                objectAnimatorCP.start();
            } else {
                objectAnimatorCP.start();
            }
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_RESULTS, 5, componentFront, mContext, mAppViewModel.getFrontVolume());
        } else if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getBanker_palyer_tie() == 3) {
            showApng(1);
//            iv_baccarat_table_tie.setVisibility(View.VISIBLE);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_t.setBackgroundResource(R.mipmap.gd_bet_v_t);
                img_bet_bg_c_t.setBackgroundResource(R.mipmap.gd_bet_v_ct);
            } else {
                img_bet_bg_t.setBackgroundResource(R.mipmap.gd_bet_h_t);
                img_bet_bg_c_t.setBackgroundResource(R.mipmap.gd_bet_h_ct);
            }
            if (objectAnimatorT.isRunning()) {
                objectAnimatorT.cancel();
                objectAnimatorT.start();
            } else {
                objectAnimatorT.start();
            }
            if (objectAnimatorCT.isRunning()) {
                objectAnimatorCT.cancel();
                objectAnimatorCT.start();
            } else {
                objectAnimatorCT.start();
            }
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_RESULTS, 6, componentFront, mContext, mAppViewModel.getFrontVolume());
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getBankerPair() == 1) {
//            iv_baccarat_table_banker_pair.setVisibility(View.VISIBLE);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_bp.setBackgroundResource(R.mipmap.gd_bet_v_bp);
            } else {
                img_bet_bg_bp.setBackgroundResource(R.mipmap.gd_bet_h_bp);
            }
            if (objectAnimatorBP.isRunning()) {
                objectAnimatorBP.cancel();
                objectAnimatorBP.start();
            } else {
                objectAnimatorBP.start();
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratResults().getPlayerPair() == 1) {
//            iv_baccarat_table_player_pair.setVisibility(View.VISIBLE);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_pp.setBackgroundResource(R.mipmap.gd_bet_v_pp);
            } else {
                img_bet_bg_pp.setBackgroundResource(R.mipmap.gd_bet_h_pp);
            }
            if (objectAnimatorPP.isRunning()) {
                objectAnimatorPP.cancel();
                objectAnimatorPP.start();
            } else {
                objectAnimatorPP.start();
            }
        }
    }

    public void showPoker() {
        isResultEnd = true;
        if (isResultEnd) {
            if (mAppViewModel.getTableId() == 71) {
                showPoker71();
                return;
            }
            if (!bottomPanel1.isOpen()) {
                bottomPanel1.setOpen(true, true);
            }
            fl_poker_parent.setVisibility(View.VISIBLE);
            ll_poker_parent.setVisibility(View.VISIBLE);
            ll_poker_pw.setVisibility(View.GONE);
            BitmapFactory.Options opts = new BitmapFactory.Options();
// 缩放的比例，缩放是很难按准备的比例进行缩放的，其值表明缩放的倍数，SDK中建议其值是2的指数值,值越大会导致图片不清晰
            opts.inSampleSize = 2;
            if (getBanker1() > 0) {
//                iv_poker_banker1.setImageResource(getPokerResource(getBanker1()));

                if (iv_poker_banker1.getVisibility() == View.GONE) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getPokerResource(getBanker1()));
                    iv_poker_banker1.setVisibility(View.VISIBLE);
                    iv_poker_banker1.setImageBitmap(BitmapTool.skewBitmap(bitmap, xSkizeB1, 0f));
                    WidgetUtil.translateAnimation(iv_poker_banker1, banker1x, 0f, AutoUtils.getPercentWidthSize(animationHeight), 0f, animationTime);
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.GET_POKER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());

                }
            }
            if (getBanker2() > 0) {
              /*  iv_poker_banker2.setImageResource(getPokerResource(getBanker2()));
                iv_poker_banker2.setVisibility(View.VISIBLE);*/
                if (iv_poker_banker2.getVisibility() == View.GONE) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getPokerResource(getBanker2()));
                    iv_poker_banker2.setVisibility(View.VISIBLE);
                    iv_poker_banker2.setImageBitmap(BitmapTool.skewBitmap(bitmap, xSkizeB2, 0f));
                    WidgetUtil.translateAnimation(iv_poker_banker2, banker2x, 0f, AutoUtils.getPercentWidthSize(animationHeight), 0f, animationTime);
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.GET_POKER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());

                }
            }
            if (getBanker3() > 0) {
             /*   iv_poker_banker3.setImageResource(getPokerResource(getBanker3()));
                iv_poker_banker3.setVisibility(View.VISIBLE);
                bitmapDrawableBanker3 = (BitmapDrawable) iv_poker_banker3.getDrawable();
                iv_poker_banker3.setImageBitmap(BitmapTool.toturn(bitmapDrawableBanker3.getBitmap(), 90));*/
                if (iv_poker_banker3.getVisibility() == View.GONE) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getPokerResource(getBanker3()));
                    iv_poker_banker3.setVisibility(View.VISIBLE);
                    iv_poker_banker3.setImageBitmap(BitmapTool.skewBitmap(BitmapTool.toturn(bitmap, 90), xSkizeB3, 0f));
                    WidgetUtil.translateAnimation(iv_poker_banker3, banker3x, 0f, AutoUtils.getPercentWidthSize(animationHeight - 150), 0f, animationTime + 100);
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.GET_POKER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
                }
            }
            if (getPlayer1() > 0) {
//                iv_poker_player1.setImageResource(getPokerResource(getPlayer1()));
                if (iv_poker_player1.getVisibility() == View.GONE) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getPokerResource(getPlayer1()));
                    iv_poker_player1.setVisibility(View.VISIBLE);
                    iv_poker_player1.setImageBitmap(BitmapTool.skewBitmap(bitmap, -xSkizeP1, 0f));
                    WidgetUtil.translateAnimation(iv_poker_player1, player1x, 0f, AutoUtils.getPercentWidthSize(animationHeight), 0f, animationTime);
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.GET_POKER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
                }
            }
            if (getPlayer2() > 0) {
//                iv_poker_player2.setImageResource(getPokerResource(getPlayer2()));
//                iv_poker_player2.setVisibility(View.VISIBLE);
                if (iv_poker_player2.getVisibility() == View.GONE) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getPokerResource(getPlayer2()));
                    iv_poker_player2.setVisibility(View.VISIBLE);
                    iv_poker_player2.setImageBitmap(BitmapTool.skewBitmap(bitmap, -xSkizeP2, 0f));
                    WidgetUtil.translateAnimation(iv_poker_player2, player2x, 0f, AutoUtils.getPercentWidthSize(animationHeight), 0f, animationTime);
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.GET_POKER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
                }
            }
            if (getPlayer3() > 0) {
//                iv_poker_player3.setImageResource(getPokerResource(getPlayer3()));
//                iv_poker_player3.setVisibility(View.VISIBLE);
//                bitmapDrawablePlayer3 = (BitmapDrawable) iv_poker_player3.getDrawable();
//                iv_poker_player3.setImageBitmap(BitmapTool.toturn(bitmapDrawablePlayer3.getBitmap(), 90));
                if (iv_poker_player3.getVisibility() == View.GONE) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), getPokerResource(getPlayer3()));
                    iv_poker_player3.setVisibility(View.VISIBLE);
                    iv_poker_player3.setImageBitmap(BitmapTool.skewBitmap(BitmapTool.toturn(bitmap, 90), -xSkizeP3, 0f));
                    WidgetUtil.translateAnimation(iv_poker_player3, player3x, 0f, AutoUtils.getPercentWidthSize(animationHeight - 150), 0f, animationTime + 100);
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.GET_POKER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
                }
            }

            if (getPlayer1() < 1 && getPlayer2() < 1 && getPlayer3() < 1 && getBanker1() < 1 && getBanker2() < 1 && getBanker3() < 1)
                return;

            mAppViewModel.showPoint(getPlayer1(),
                    getPlayer2(),
                    getPlayer3(),
                    getBanker1(),
                    getBanker2(),
                    getBanker3(),
                    /*tv_point_banker, tv_point_player*/tv_banker_result, tv_player_result, "", "");


        }
    }

    protected void pokerFlipTimeCount(int duration, final TextView timerTv, final List<PageWidgetT> pws) {
        timerOnFinish();
        startTimer(duration, timerTv, pws);
        if (!countdown_view.viewIsRunning()) {
            countdown_view.setCountdownTime(duration);
            countdown_view.startCountDown();
        }
    }

    private void startTimer(final int duration, final TextView timerTv, final List<PageWidgetT> pws) {
        timerTv.setVisibility(View.VISIBLE);
        tv_table_timer.setVisibility(View.GONE);
        tv_mi_timer.setVisibility(View.VISIBLE);
        timer = new CountDownTimer(duration * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTv.setVisibility(View.VISIBLE);
                String time = "" + millisUntilFinished / 1000;
                timerTv.setText(time);
                if (millisUntilFinished < 6000) {
                    tv_mi_timer.setTextColor(Color.RED);
                } else {
                    tv_mi_timer.setTextColor(Color.WHITE);
                }

                tv_mi_timer.setText(time);
            }

            @Override
            public void onFinish() {
                timerTv.setText("0");
                tv_mi_timer.setText("0");
                tv_mi_timer.setVisibility(View.GONE);
                tv_table_timer.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        timerTv.setVisibility(View.GONE);
                    }
                }, 1000);

                for (PageWidgetT pw : pws) {
                    if (!isFinishing() && isAttached && pw.getVisibility() == View.VISIBLE)
                        pw.flipPicAnimation3D();
                }
                mAppViewModel.showPoint(getPlayer1(),
                        getPlayer2(),
                        getPlayer3(),
                        getBanker1(),
                        getBanker2(),
                        getBanker3(),
                        tv_poker_center_right, tv_poker_center_left, getString(banker_home) + " ", getString(player_home) + " ");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hidePoker(3);
                    }
                }, 2000);
                if (timer != null)
                    timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoHelper.onDestroy();
        stopUpdateStatusThread();
        objectAnimatorB.cancel();
        objectAnimatorP.cancel();
        objectAnimatorT.cancel();
        objectAnimatorBP.cancel();
        objectAnimatorPP.cancel();
        objectAnimatorLucky.cancel();
        objectAnimatorAny.cancel();
        objectAnimatorPN.cancel();
        objectAnimatorPerfect.cancel();
        objectAnimatorBN.cancel();
        objectAnimatorCP.cancel();
        objectAnimatorCT.cancel();
        objectAnimatorCB.cancel();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttached = true;
        if (mAppViewModel.getTableId() == 71) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                checkSlideHint(tv_table_timer);
            }
        } else {
            checkSlideHint(tv_table_timer);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onAttachedToWindow();
        isAttached = false;
    }

    private void showPoker71() {
        //最新分支
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer() > 0 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer() > 0 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker() > 0 ||
                mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker() > 0) {
            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer() > 0 ||
                    mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer() > 0) {
                gameBetMap.put(gameIdNumber, 1);
            } else if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker() > 0 ||
                    mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker() > 0) {
                gameBetMap.put(gameIdNumber, 2);
            }

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker() > 0 &&
                    mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer() > 0) {
                gameBetMap.put(gameIdNumber, 3);
            }

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer() > 0 &&
                    mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker() > 0) {
                gameBetMap.put(gameIdNumber, 3);
            }

            if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker() > 0 &&
                    mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer() > 0) {
                gameBetMap.put(gameIdNumber, 3);
            }
            fl_poker_bottom_parent.setVisibility(View.VISIBLE);
            ll_poker_pw.setVisibility(View.VISIBLE);
        } else {
            fl_poker_bottom_parent.setVisibility(View.GONE);
            ll_poker_pw.setVisibility(View.GONE);
        }
        fl_poker_result.setVisibility(View.VISIBLE);
//        tv_table_timer.setVisibility(View.GONE);
        tv_point_banker.setVisibility(View.GONE);
        tv_point_player.setVisibility(View.GONE);
        if (!bottomPanel1.isOpen()) {
            bottomPanel1.setOpen(true, true);
        }
        ll_poker_parent.setVisibility(View.GONE);
//        ll_poker_pw.setVisibility(View.VISIBLE);
        if (gamePokerMap.get(gameIdNumber) == null) {

            pw_poker_banker1.setImageCurRes(R.mipmap.gd_poker_bg);
            pw_poker_banker2.setImageCurRes(R.mipmap.gd_poker_bg);
            pw_poker_banker3.setImageCurRes(R.mipmap.gd_poker_bg);
            pw_poker_player1.setImageCurRes(R.mipmap.gd_poker_bg);
            pw_poker_player2.setImageCurRes(R.mipmap.gd_poker_bg);
            pw_poker_player3.setImageCurRes(R.mipmap.gd_poker_bg);

            pw_poker_banker1.coverPic();
            pw_poker_banker1.setVisibility(View.GONE);
            iv_poker_center_banker1.setVisibility(View.INVISIBLE);
            pw_poker_banker2.coverPic();
            pw_poker_banker2.setVisibility(View.GONE);
            iv_poker_center_banker2.setVisibility(View.INVISIBLE);
            pw_poker_banker3.coverPic();
            pw_poker_banker3.setVisibility(View.GONE);
            iv_poker_center_banker3.setVisibility(View.INVISIBLE);
            pw_poker_player1.coverPic();

            pw_poker_player1.setVisibility(View.GONE);
            iv_poker_center_player1.setVisibility(View.INVISIBLE);
            pw_poker_player2.coverPic();
            pw_poker_player2.setVisibility(View.GONE);
            iv_poker_center_player2.setVisibility(View.INVISIBLE);
            pw_poker_player3.coverPic();
            pw_poker_player3.setVisibility(View.GONE);
            iv_poker_center_player3.setVisibility(View.INVISIBLE);
            img_left_player_rotate.setVisibility(View.GONE);
            img_right_player_rotate.setVisibility(View.GONE);
            img_center_player_rotate.setVisibility(View.GONE);
            img_left_banker_rotate.setVisibility(View.GONE);
            img_right_banker_rotate.setVisibility(View.GONE);
            img_center_banker_rotate.setVisibility(View.GONE);
        }
        if (hideType > 0) {
            fl_poker_bottom_parent.setVisibility(View.GONE);
            ll_poker_pw.setVisibility(View.GONE);
        }

        if (getBanker1() > 0 && getBanker2() > 0
                && getPlayer1() > 0 && getPlayer2() > 0) {
            if (gamePokerMap.get(gameIdNumber) == null || gamePokerMap.get(gameIdNumber) != 1) {
//                fl_poker_bottom_parent.setVisibility(View.VISIBLE);
//                ll_poker_pw.setVisibility(View.VISIBLE);
                gamePokerMap.put(gameIdNumber, 1);
                pw_poker_banker1.setImageNextRes(getPokerResource(getBanker1()));

                iv_poker_center_banker1.setVisibility(View.VISIBLE);
                iv_poker_center_banker1.setImageResource(R.mipmap.gd_poker_bg);

                pw_poker_banker1.setFlipCallBack(new flipCallBack() {
                    @Override
                    public void hasFlip() {
                        if (iv_poker_center_banker1 != null) {
                            ImageRotate3D rotate3D = new ImageRotate3D(iv_poker_center_banker1, getPokerResource(getBanker1()));
                            rotate3D.setAnimation3D();
                        }
                        flipMap.put(gameIdNumber + "banker1", getBanker1());
                        showBankerPoint(1);

                    }
                });

                img_left_banker_rotate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pw_poker_banker1.rotate();
                    }
                });
                iv_poker_center_banker2.setVisibility(View.VISIBLE);
                iv_poker_center_banker2.setImageResource(R.mipmap.gd_poker_bg);
                pw_poker_banker2.setImageNextRes(getPokerResource(getBanker2()));
                pw_poker_banker2.setFlipCallBack(new flipCallBack() {
                    @Override
                    public void hasFlip() {
                        if (iv_poker_center_banker2 != null) {
//                            iv_poker_center_banker2.setImageResource(getPokerResource(getBanker2()));
                            ImageRotate3D rotate3D = new ImageRotate3D(iv_poker_center_banker2, getPokerResource(getBanker2()));
                            rotate3D.setAnimation3D();
                            flipMap.put(gameIdNumber + "banker2", getBanker2());
                            showBankerPoint(2);
                        }
                    }
                });

                img_right_banker_rotate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pw_poker_banker2.rotate();
                    }
                });
                iv_poker_center_player1.setVisibility(View.VISIBLE);
                iv_poker_center_player1.setImageResource(R.mipmap.gd_poker_bg);

                pw_poker_player1.setImageNextRes(getPokerResource(getPlayer1()));
                pw_poker_player1.setFlipCallBack(new flipCallBack() {
                    @Override
                    public void hasFlip() {
                        if (iv_poker_center_player1 != null) {
//                            iv_poker_center_player1.setImageResource(getPokerResource(getPlayer1()));
                            ImageRotate3D rotate3D = new ImageRotate3D(iv_poker_center_player1, getPokerResource(getPlayer1()));
                            rotate3D.setAnimation3D();
                            flipMap.put(gameIdNumber + "player1", getPlayer1());
                            showPlayerPoint(1);
                        }
                    }
                });

                img_left_player_rotate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pw_poker_player1.rotate();
                    }
                });

                iv_poker_center_player2.setVisibility(View.VISIBLE);
                iv_poker_center_player2.setImageResource(R.mipmap.gd_poker_bg);
                pw_poker_player2.setImageNextRes(getPokerResource(getPlayer2()));
                pw_poker_player2.setFlipCallBack(new flipCallBack() {
                    @Override
                    public void hasFlip() {
                        if (iv_poker_center_player2 != null) {
//                            iv_poker_center_player2.setImageResource(getPokerResource(getPlayer2()));
                            ImageRotate3D rotate3D = new ImageRotate3D(iv_poker_center_player2, getPokerResource(getPlayer2()));
                            rotate3D.setAnimation3D();
                            flipMap.put(gameIdNumber + "player2", getPlayer2());
                            showPlayerPoint(2);
                        }
                    }
                });

                img_right_player_rotate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pw_poker_player2.rotate();
                    }
                });

                pw_poker_banker1.setVisibility(View.VISIBLE);
                pw_poker_banker2.setVisibility(View.VISIBLE);
                pw_poker_player1.setVisibility(View.VISIBLE);
                pw_poker_player2.setVisibility(View.VISIBLE);

                img_left_player_rotate.setVisibility(View.VISIBLE);
                img_right_player_rotate.setVisibility(View.VISIBLE);
                img_left_banker_rotate.setVisibility(View.VISIBLE);
                img_right_banker_rotate.setVisibility(View.VISIBLE);

                if (gameBetMap.get(gameIdNumber) != null && gameBetMap.get(gameIdNumber) == 1) {
                    setFlipPlayerEnable(false);
                    setFlipBankerEnable(true);
                    setShowFlipPoker(true);
                } else if (gameBetMap.get(gameIdNumber) != null && gameBetMap.get(gameIdNumber) == 2) {
                    setFlipBankerEnable(false);
                    setFlipPlayerEnable(true);
                    setShowFlipPoker(true);
                } else {
                    setShowFlipPoker(true);
                    setFlipBankerEnable(false);
                    setFlipPlayerEnable(false);

                }
                pw_poker_player3.setVisibility(View.GONE);
                pw_poker_banker3.setVisibility(View.GONE);

                img_center_player_rotate.setVisibility(View.GONE);
                img_center_banker_rotate.setVisibility(View.GONE);

                pokerFlipTimeCount(20, tv_poker_timer, new ArrayList<>(Arrays.asList(pw_poker_player1, pw_poker_player2, pw_poker_banker1, pw_poker_banker2)));
            }
            if (hasOtherTwoPoker()) {
                Log.d("hide", "hideType:" + hideType + ",show2");
                if (gamePokerMap.get(gameIdNumber + "2") == null || gamePokerMap.get(gameIdNumber + "2") != 2) {
                    if (getPlayer3() > 0 && getBanker3() > 0) {
                        hideType = 0;
//                        fl_poker_bottom_parent.setVisibility(View.VISIBLE);
//                        ll_poker_pw.setVisibility(View.VISIBLE);
                        setAppImage();
                        gamePokerMap.put(gameIdNumber + "2", 2);
                        pw_poker_player3.setVisibility(View.VISIBLE);

                        img_center_player_rotate.setVisibility(View.VISIBLE);

                        pw_poker_player3.setImageRes(R.mipmap.gd_poker_bg, getPokerResource(getPlayer3()));

                        pw_poker_banker3.setVisibility(View.VISIBLE);

                        img_center_banker_rotate.setVisibility(View.VISIBLE);

                        pw_poker_banker3.setImageRes(R.mipmap.gd_poker_bg, getPokerResource(getBanker3()));
                        iv_poker_center_banker3.setVisibility(View.VISIBLE);
                        iv_poker_center_banker3.setImageResource(R.mipmap.gd_poker_bg);
                        BitmapDrawable draw = (BitmapDrawable) iv_poker_center_banker3.getDrawable();
                        iv_poker_center_banker3.setImageBitmap(BitmapTool.toturn(draw.getBitmap(), 90));
                        pw_poker_banker3.setFlipCallBack(new flipCallBack() {
                            @Override
                            public void hasFlip() {
                                if (iv_poker_center_banker3 == null)
                                    return;
                                setBankerImageChange();

                            }
                        });

                        img_center_banker_rotate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pw_poker_banker3.rotate();
                            }
                        });

                        iv_poker_center_player3.setVisibility(View.VISIBLE);
                        iv_poker_center_player3.setImageResource(R.mipmap.gd_poker_bg);
                        BitmapDrawable draw3 = (BitmapDrawable) iv_poker_center_player3.getDrawable();
                        iv_poker_center_player3.setImageBitmap(BitmapTool.toturn(draw3.getBitmap(), 90));
                        pw_poker_player3.setFlipCallBack(new flipCallBack() {
                            @Override
                            public void hasFlip() {
                                if (iv_poker_center_player3 == null)
                                    return;
                                setPlayerImageChange();

                            }
                        });

                        img_center_player_rotate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pw_poker_player3.rotate();
                            }
                        });
                        pokerFlipTimeCount(10, tv_poker_timer, new ArrayList<>(Arrays.asList(pw_poker_player3, pw_poker_banker3)));
                        if (gameBetMap.get(gameIdNumber) != null && gameBetMap.get(gameIdNumber) == 2) {
                            setShowFlipPoker(true);
                            setFlipPlayerEnable(true);
                            setFlipBankerEnable(false);
                        } else if (gameBetMap.get(gameIdNumber) != null && gameBetMap.get(gameIdNumber) == 1) {
                            setShowFlipPoker(true);
                            setFlipPlayerEnable(false);
                            setFlipBankerEnable(true);
                        } else {
                            setShowFlipPoker(true);
                            setFlipBankerEnable(false);
                            setFlipPlayerEnable(false);
                        }
                        pw_poker_banker1.setVisibility(View.GONE);
                        pw_poker_banker2.setVisibility(View.GONE);
                        pw_poker_player1.setVisibility(View.GONE);
                        pw_poker_player2.setVisibility(View.GONE);

                        img_left_player_rotate.setVisibility(View.GONE);
                        img_right_player_rotate.setVisibility(View.GONE);
                        img_left_banker_rotate.setVisibility(View.GONE);
                        img_right_banker_rotate.setVisibility(View.GONE);
                    }
                }
            } else {
                if (getBanker3() > 0 && (gamePokerMap.get(gameIdNumber + "0") == null || gamePokerMap.get(gameIdNumber + "0") != 0)) {
                    hideType = 0;
//                    fl_poker_bottom_parent.setVisibility(View.VISIBLE);
//                    ll_poker_pw.setVisibility(View.VISIBLE);
                    gamePokerMap.put(gameIdNumber + "0", 0);
                    setAppImage();
                    pw_poker_banker3.setVisibility(View.VISIBLE);
                    img_center_banker_rotate.setVisibility(View.VISIBLE);
                    pw_poker_banker3.setImageRes(R.mipmap.gd_poker_bg, getPokerResource(getBanker3()));

                    iv_poker_center_banker3.setVisibility(View.VISIBLE);
                    iv_poker_center_banker3.setImageResource(R.mipmap.gd_poker_bg);
                    BitmapDrawable draw3 = (BitmapDrawable) iv_poker_center_banker3.getDrawable();
                    iv_poker_center_banker3.setImageBitmap(BitmapTool.toturn(draw3.getBitmap(), 90));

                    pw_poker_banker3.setFlipCallBack(new flipCallBack() {
                        @Override
                        public void hasFlip() {
                            if (iv_poker_center_banker3 == null)
                                return;
                            setBankerImageChange();
                        }
                    });

                    img_center_banker_rotate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pw_poker_banker3.rotate();
                        }
                    });

                    pokerFlipTimeCount(10, tv_poker_timer, new ArrayList<>(Arrays.asList(pw_poker_banker3)));
                    if (gameBetMap.get(gameIdNumber) != null && gameBetMap.get(gameIdNumber) == 2) {
                        setShowFlipPoker(true);
                        setFlipBankerEnable(false);
                        setFlipPlayerEnable(true);
                    } else if (gameBetMap.get(gameIdNumber) != null && gameBetMap.get(gameIdNumber) == 3) {
                        setShowFlipPoker(true);
                        setFlipBankerEnable(false);
                        setFlipPlayerEnable(false);
                    } else {
                        setShowFlipPoker(false);
                    }
                    pw_poker_banker1.setVisibility(View.GONE);
                    pw_poker_banker2.setVisibility(View.GONE);
                    pw_poker_player1.setVisibility(View.GONE);
                    pw_poker_player2.setVisibility(View.GONE);

                    img_left_player_rotate.setVisibility(View.GONE);
                    img_right_player_rotate.setVisibility(View.GONE);
                    img_left_banker_rotate.setVisibility(View.GONE);
                    img_right_banker_rotate.setVisibility(View.GONE);
                }
                if (getPlayer3() > 0 && (gamePokerMap.get(gameIdNumber + "1") == null || gamePokerMap.get(gameIdNumber + "1") != 1)) {

                    hideType = 0;
//                    fl_poker_bottom_parent.setVisibility(View.VISIBLE);
//                    ll_poker_pw.setVisibility(View.VISIBLE);

                    gamePokerMap.put(gameIdNumber + "1", 1);
                    setAppImage();
                    pw_poker_player3.setVisibility(View.VISIBLE);

                    img_center_player_rotate.setVisibility(View.VISIBLE);

                    pw_poker_player3.setImageRes(R.mipmap.gd_poker_bg, getPokerResource(getPlayer3()));

                    iv_poker_center_player3.setVisibility(View.VISIBLE);
                    iv_poker_center_player3.setImageResource(R.mipmap.gd_poker_bg);
                    BitmapDrawable draw3 = (BitmapDrawable) iv_poker_center_player3.getDrawable();
                    iv_poker_center_player3.setImageBitmap(BitmapTool.toturn(draw3.getBitmap(), 90));
                    pw_poker_player3.setFlipCallBack(new flipCallBack() {
                        @Override
                        public void hasFlip() {
                            if (iv_poker_center_player3 == null)
                                return;

                            setPlayerImageChange();
                        }
                    });

                    img_center_player_rotate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pw_poker_player3.rotate();
                        }
                    });

                    pokerFlipTimeCount(10, tv_poker_timer, new ArrayList<>(Arrays.asList(pw_poker_player3)));
                    if (gameBetMap.get(gameIdNumber) != null && gameBetMap.get(gameIdNumber) == 1) {
                        setShowFlipPoker(true);
                        setFlipBankerEnable(true);
                        setFlipPlayerEnable(false);
                    } else if (gameBetMap.get(gameIdNumber) != null && gameBetMap.get(gameIdNumber) == 2) {
                        setShowFlipPoker(false);
                    } else {
                        setShowFlipPoker(true);
                        setFlipBankerEnable(false);
                        setFlipPlayerEnable(false);
                    }
                    pw_poker_banker1.setVisibility(View.GONE);
                    pw_poker_banker2.setVisibility(View.GONE);
                    pw_poker_player1.setVisibility(View.GONE);
                    pw_poker_player2.setVisibility(View.GONE);

                    img_left_player_rotate.setVisibility(View.GONE);
                    img_right_player_rotate.setVisibility(View.GONE);
                    img_left_banker_rotate.setVisibility(View.GONE);
                    img_right_banker_rotate.setVisibility(View.GONE);
                }
            }

        }


    }

    public void showBankerPoint(final int type) {

        int banker1 = flipMap.get(gameIdNumber + "banker1") == null ? 0 : flipMap.get(gameIdNumber + "banker1");
        int banker2 = flipMap.get(gameIdNumber + "banker2") == null ? 0 : flipMap.get(gameIdNumber + "banker2");
        int banker3 = flipMap.get(gameIdNumber + "banker3") == null ? 0 : flipMap.get(gameIdNumber + "banker3");
        mAppViewModel.showPointBanker(banker1,
                banker2,
                banker3,
                /*tv_point_banker*/tv_banker_result, /*getString(banker)*/"");
//        if (type < 3 && (banker1 == 0 || banker2 == 0)) {
//
//        } else {
//            if (fl_banker_pw_parent.getVisibility() == View.VISIBLE) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        hidePoker(type);
//                    }
//                }, 3000);
//            }
//        }
    }

    public void showPlayerPoint(final int type) {
        int player1 = flipMap.get(gameIdNumber + "player1") == null ? 0 : flipMap.get(gameIdNumber + "player1");
        int player2 = flipMap.get(gameIdNumber + "player2") == null ? 0 : flipMap.get(gameIdNumber + "player2");
        int player3 = flipMap.get(gameIdNumber + "player3") == null ? 0 : flipMap.get(gameIdNumber + "player3");
        mAppViewModel.showPointPlayer(player1,
                player2,
                player3,
                /*tv_point_player*/tv_player_result, /*getString(player)*/"");
//        if (type < 3 && (player1 == 0 || player2 == 0)) {
//
//        } else {
//            if (fl_player_pw_parent.getVisibility() == View.VISIBLE) {
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        hidePoker(type);
//                    }
//                }, 3000);
//            }
//        }
    }

    public void setPlayerImageChange() {
        ImageRotate3D rotate3D = new ImageRotate3D(iv_poker_center_player3, getPokerResource(getPlayer3()), 90);
        rotate3D.setAnimation3D();
        flipMap.put(gameIdNumber + "player3", getPlayer3());
        showPlayerPoint(3);
    }

    public void setBankerImageChange() {

        ImageRotate3D rotate3D = new ImageRotate3D(iv_poker_center_banker3, getPokerResource(getBanker3()), 90);
        rotate3D.setAnimation3D();
        flipMap.put(gameIdNumber + "banker3", getBanker3());
        showBankerPoint(3);
    }


    private void setAppImage() {
        iv_poker_center_player1.setImageResource(getPokerResource(getPlayer1()));
        iv_poker_center_player2.setImageResource(getPokerResource(getPlayer2()));
        iv_poker_center_banker1.setImageResource(getPokerResource(getBanker1()));
        iv_poker_center_banker2.setImageResource(getPokerResource(getBanker2()));
    }

    //    p1:46  p2:26  p3:0  b1:36  b2:35  b3:0
    private int getBanker3() {
        return mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().getBanker3();
//        return 0;
//        i++;
//        if (i / 8 % 2 == 0)
//            return 0;
//        else
//            return 47;
    }

    private int getBanker2() {
        return mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().getBanker2();
//        return 35;
//        return 16;
    }

    private int getBanker1() {
        return mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().getBanker1();
//        return 36;
//        return 1;
    }

    private int getPlayer3() {
        return mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().getPlayer3();
//        return 0;
//        return 19;
    }

    private int getPlayer2() {
        return mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().getPlayer2();
//        return 26;
//        return 21;
    }

    private int getPlayer1() {
        return mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratPoker().getPlayer1();
//        return 46;
//        return 41;

    }

    public void setFlipPlayerEnable(boolean f) {
        pw_poker_player1.setCanNotFlip(f);
        pw_poker_player2.setCanNotFlip(f);
        pw_poker_player3.setCanNotFlip(f);
        if (f) {
            v_background_player.setVisibility(View.GONE);
            fl_player_pw_parent.setVisibility(View.GONE);
        } else {
            v_background_player.setVisibility(View.VISIBLE);
            fl_player_pw_parent.setVisibility(View.VISIBLE);
        }
    }

    private void setFlipBankerEnable(boolean canNotFlip) {
        pw_poker_banker1.setCanNotFlip(canNotFlip);
        pw_poker_banker2.setCanNotFlip(canNotFlip);
        pw_poker_banker3.setCanNotFlip(canNotFlip);
        if (canNotFlip) {
            fl_banker_pw_parent.setVisibility(View.GONE);
            v_background_banker.setVisibility(View.GONE);
        } else {
            fl_banker_pw_parent.setVisibility(View.VISIBLE);
            v_background_banker.setVisibility(View.VISIBLE);
        }
    }

    private void setShowFlipPoker(boolean b) {
        if (b)
            fl_poker_parent.setVisibility(View.VISIBLE);
        else
            fl_poker_parent.setVisibility(View.GONE);
    }

    private boolean hasOtherTwoPoker() {
        int p1 = getPlayer1();
        int p2 = getPlayer2();
        int b1 = getBanker1();
        int b2 = getBanker2();
        Map<String, Integer> map = mAppViewModel.getPoint(p1, p2, b1, b2);
        int p = map.get("player");
        int b = map.get("banker");
        if (p < 6 && b < 3)
            return true;
        return false;
    }

    boolean isShowWinLose = true;

    public void hidePoker(int hideType) {
        this.hideType = hideType;
        Log.d("hide", "hideType" + hideType);
        //   ll_poker_right_bg.setBackgroundResource(0);
        //   ll_poker_left_bg.setBackgroundResource(0);
        if (isFinishing())
            return;
        isResultEnd = false;
        if (fl_poker_bottom_parent == null)
            return;
        fl_poker_bottom_parent.setVisibility(View.GONE);
        bottomPanel1.setOpen(false, true);
        iv_poker_banker1.setVisibility(View.GONE);
        iv_poker_banker2.setVisibility(View.GONE);
        iv_poker_banker3.setVisibility(View.GONE);
        iv_poker_player1.setVisibility(View.GONE);
        iv_poker_player2.setVisibility(View.GONE);
        iv_poker_player3.setVisibility(View.GONE);

    }

    public int getPokerResource(int poker) {
        int poker_res = 0;
        switch (poker) {
            case 1:
                poker_res = R.mipmap.gd_pk_1;
                break;
            case 2:
                poker_res = R.mipmap.gd_pk_2;
                break;
            case 3:
                poker_res = R.mipmap.gd_pk_3;
                break;
            case 4:
                poker_res = R.mipmap.gd_pk_4;
                break;
            case 5:
                poker_res = R.mipmap.gd_pk_5;
                break;
            case 6:
                poker_res = R.mipmap.gd_pk_6;
                break;
            case 7:
                poker_res = R.mipmap.gd_pk_7;
                break;
            case 8:
                poker_res = R.mipmap.gd_pk_8;
                break;
            case 9:
                poker_res = R.mipmap.gd_pk_9;
                break;
            case 10:
                poker_res = R.mipmap.gd_pk_10;
                break;
            case 11:
                poker_res = R.mipmap.gd_pk_11;
                break;
            case 12:
                poker_res = R.mipmap.gd_pk_12;
                break;
            case 13:
                poker_res = R.mipmap.gd_pk_13;
                break;
            case 14:
                poker_res = R.mipmap.gd_pk_14;
                break;
            case 15:
                poker_res = R.mipmap.gd_pk_15;
                break;
            case 16:
                poker_res = R.mipmap.gd_pk_16;
                break;
            case 17:
                poker_res = R.mipmap.gd_pk_17;
                break;
            case 18:
                poker_res = R.mipmap.gd_pk_18;
                break;
            case 19:
                poker_res = R.mipmap.gd_pk_19;
                break;
            case 20:
                poker_res = R.mipmap.gd_pk_20;
                break;
            case 21:
                poker_res = R.mipmap.gd_pk_21;
                break;
            case 22:
                poker_res = R.mipmap.gd_pk_22;
                break;
            case 23:
                poker_res = R.mipmap.gd_pk_23;
                break;
            case 24:
                poker_res = R.mipmap.gd_pk_24;
                break;
            case 25:
                poker_res = R.mipmap.gd_pk_25;
                break;
            case 26:
                poker_res = R.mipmap.gd_pk_26;
                break;
            case 27:
                poker_res = R.mipmap.gd_pk_27;
                break;
            case 28:
                poker_res = R.mipmap.gd_pk_28;
                break;
            case 29:
                poker_res = R.mipmap.gd_pk_29;
                break;
            case 30:
                poker_res = R.mipmap.gd_pk_30;
                break;
            case 31:
                poker_res = R.mipmap.gd_pk_31;
                break;
            case 32:
                poker_res = R.mipmap.gd_pk_32;
                break;
            case 33:
                poker_res = R.mipmap.gd_pk_33;
                break;
            case 34:
                poker_res = R.mipmap.gd_pk_34;
                break;
            case 35:
                poker_res = R.mipmap.gd_pk_35;
                break;
            case 36:
                poker_res = R.mipmap.gd_pk_36;
                break;
            case 37:
                poker_res = R.mipmap.gd_pk_37;
                break;
            case 38:
                poker_res = R.mipmap.gd_pk_38;
                break;
            case 39:
                poker_res = R.mipmap.gd_pk_39;
                break;
            case 40:
                poker_res = R.mipmap.gd_pk_40;
                break;
            case 41:
                poker_res = R.mipmap.gd_pk_41;
                break;
            case 42:
                poker_res = R.mipmap.gd_pk_42;
                break;
            case 43:
                poker_res = R.mipmap.gd_pk_43;
                break;
            case 44:
                poker_res = R.mipmap.gd_pk_44;
                break;
            case 45:
                poker_res = R.mipmap.gd_pk_45;
                break;
            case 46:
                poker_res = R.mipmap.gd_pk_46;
                break;
            case 47:
                poker_res = R.mipmap.gd_pk_47;
                break;
            case 48:
                poker_res = R.mipmap.gd_pk_48;
                break;
            case 49:
                poker_res = R.mipmap.gd_pk_49;
                break;
            case 50:
                poker_res = R.mipmap.gd_pk_50;
                break;
            case 51:
                poker_res = R.mipmap.gd_pk_51;
                break;
            case 52:
                poker_res = R.mipmap.gd_pk_52;
                break;
        }
        return poker_res;
    }

    public void gotoLobby() {
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).Init();
        //   mAppViewModel.setTableId(0);
        mAppViewModel.setSerialId(0);
        mAppViewModel.setAreaId(0);
        mAppViewModel.setbLobby(true);
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setBigRoadOld("");


        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 0);
        skipAct(LobbyActivity.class, bundle);
        finish();
    }

    //重新选择了筹码，重新开始算下注筹码
    public void initClickCount() {
        clickBankerCount = 0;
        clickPlayerCount = 0;
        clickBankerPairCount = 0;
        clickPlayerPairCount = 0;
        clickTieCount = 0;
        clickLuckyCount = 0;
        clickAnyCount = 0;
        clickPlayerNCount = 0;
        clickPerfectCount = 0;
        clickBankerNCount = 0;
        clickCPlayerCount = 0;
        clickCTieCount = 0;
        clickCBankerCount = 0;
    }


    public void initChat() {
        iv_baccarat_chat_logo.setVisibility(View.GONE);

    }

    public void sendChatMsg(String str) {
        Packet packet = new Packet();
        packet.pack(str);
        user.send(packet);
    }

    private void initChatView() {

        faceLayout.setEdtMessage(edtChatContent);
        faceLayout.setItemCallBack(new FaceRelativeLayout.ItemCallBack() {
            @Override
            public void clickItem(ChatEmoji emoji) {
                rgChatParent.check(R.id.gd__rb_chat_content);
            }
        });
        rgChatParent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                lvChatUsual.setVisibility(View.GONE);
                gvChatEmoticons.setVisibility(View.GONE);
                llChatContent.setVisibility(View.GONE);
                if (checkedId == R.id.gd__rb_chat_content) {
                    llChatContent.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.gd__rb_chat_emoticons) {
                    gvChatEmoticons.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.gd__rb_chat_usual) {
                    lvChatUsual.setVisibility(View.VISIBLE);
                }
            }
        });
        btnChatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = edtChatContent.getText().toString().trim();
                if (!str.isEmpty()) {
                    sendUsualMsg(str);
                    edtChatContent.setText("");
                }
            }
        });
        AdapterViewContent<String> usualContent = new AdapterViewContent<>(mContext, lvChatUsual);
        usualContent.setBaseAdapter(new QuickAdapterImp<String>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_text;
            }

            @Override
            public void convert(ViewHolder helper, String item, int position) {
                helper.setText(R.id.gd__text_tv1, item);
                helper.setTextColorRes(R.id.gd__text_tv1, R.color.white);
            }
        });
        usualContent.setItemClick(new ItemCLickImp<String>() {
            @Override
            public void itemCLick(View view, String s, int position) {
                sendUsualMsg(s);
                rgChatParent.check(R.id.gd__rb_chat_content);
            }
        });
        usualContent.setData(new ArrayList<>(Arrays.asList(getString(R.string.nice_to_meet), getString(R.string.sure_to_win)
                , getString(R.string.bad_luck), getString(R.string.make_friends), getString(R.string.see_you), getString(R.string.game_to_end), getString(R.string.one_more_banker), getString(R.string.one_more_player))));
        chatContent = new AdapterViewContent<>(mContext, lvChatContent);
        chatContent.setBaseAdapter(new QuickAdapterImp<String>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_chat;
            }

            @Override
            public void convert(ViewHolder helper, String item, int position) {
                SpannableString spannableString = FaceConversionUtil.getInstace()
                        .getExpressionString(mContext, item);
                TextView tv = helper.retrieveView(R.id.gd__text_tv1);
                tv.setText(spannableString);
            }
        });
        chatMsgs = new ArrayList<String>();
        chatContent.setData(chatMsgs);
    }

    public void sendUsualMsg(String str) {
        MsgBean msgBean = new MsgBean("sendMsg", mAppViewModel.getUser().getName(), str);
        sendChatMsg(msgBean.toString());
    }

    private ISocketResponse socketListener = new ISocketResponse() {

        @Override
        public void onSocketResponse(final String txt) {
            final ReceiveMsgBean bean = (new Gson()).fromJson(txt, new TypeToken<ReceiveMsgBean>() {
            }.getType());
            runOnUiThread(new Runnable() {
                public void run() {
                    if (bean != null && bean.getMessage() != null) {
                        chatContent.getAdapter().add(bean.getMessage());
                        lvChatContent.setSelection(chatContent.getDataCount() - 1);
                    }
                }
            });
        }

    };

    static class ViewBindHolder {
        @BindView(R2.id.gd__iv_baccarat_table_player)
        ImageView ivBaccaratTablePlayer;
        @BindView(R2.id.gd__tv_player_bet_money)
        TextView tvPlayerBetMoney;
        @BindView(R2.id.gd__tv_player_bet_count)
        TextView tvPlayerBetCount;
        @BindView(R2.id.gd__fl_baccarat_table_player)
        FrameLayout flBaccaratTablePlayer;
        @BindView(R2.id.gd__fl_baccarat_table_player_bg)
        FrameLayout flBaccaratTablePlayer_bg;
        @BindView(R2.id.gd__iv_baccarat_table_banker)
        ImageView ivBaccaratTableBanker;
        @BindView(R2.id.gd__tv_banker_bet_money)
        TextView tvBankerBetMoney;
        @BindView(R2.id.gd__tv_banker_bet_count)
        TextView tvBankerBetCount;
        @BindView(R2.id.gd__fl_baccarat_table_banker)
        FrameLayout flBaccaratTableBanker;
        @BindView(R2.id.gd__fl_baccarat_table_banker_bg)
        FrameLayout flBaccaratTableBanker_bg;
        @BindView(R2.id.gd__iv_baccarat_table_tie)
        ImageView ivBaccaratTableTie;
        @BindView(R2.id.gd__tv_tie_bet_money)
        TextView tvTieBetMoney;
        @BindView(R2.id.gd__tv_tie_bet_count)
        TextView tvTieBetCount;
        @BindView(R2.id.gd__iv_baccarat_table_player_pair)
        ImageView ivBaccaratTablePlayerPair;
        @BindView(R2.id.gd__tv_player_pair_bet_money)
        TextView tvPlayerPairBetMoney;
        @BindView(R2.id.gd__tv_player_pair_bet_count)
        TextView tvPlayerPairBetCount;
        @BindView(R2.id.gd__iv_baccarat_table_banker_pair)
        ImageView ivBaccaratTableBankerPair;
        @BindView(R2.id.gd__tv_banker_pair_bet_money)
        TextView tvBankerPairBetMoney;
        @BindView(R2.id.gd__tv_banker_pair_bet_count)
        TextView tvBankerPairBetCount;
//        @BindView(R2.id.gd__tv_banker_pair_bet_count)
//        TextView tvbankerpairbetcount;
//        @BindView(R2.id.gd__tv_player_pair_bet_count)
//        TextView tvplayerpairbetcount;


        ViewBindHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

//    public void singleBet(BaccaratBetType type) {
//        if (mAppViewModel.getUser().getBalance() <= 0) {
//            GdToastUtils.showToast(mContext, getString(R.string.Insufficient));
//            return;
//        }
//        if (playerBet > 0 || bankerBet > 0 || tieBet > 0 || bankerPairBet > 0 || playerPairBet > 0) {
//            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 10, componentFront, mContext, mAppViewModel.getFrontVolume());
//            //执行下注的线程
//            if (type == Player)
//                chipHelperCurrent = chipHelperPlayer;
//            else if (type == Banker)
//                chipHelperCurrent = chipHelperBanker;
//            else if (type == BaccaratBetType.BankerPair)
//                chipHelperCurrent = chipHelperBankerPair;
//            else if (type == BaccaratBetType.PlayerPair)
//                chipHelperCurrent = chipHelperPlayerPair;
//            else if (type == BaccaratBetType.Tie)
//                chipHelperCurrent = chipHelperTie;
//            else {
//                chipHelperCurrent = null;
//            }
//            baccaratBet = new BaccaratBet(type);
//            threadBaccaratBet = new Thread(baccaratBet);
//            showBlockDialog();
//            Executors.newSingleThreadExecutor().execute(threadBaccaratBet);
//
//        }
//    }

    public void clearNoBetChip(BaccaratBetType type) {
        chipHelperCurrent = null;
        showBetBg();
        if (type == BaccaratBetType.All || type == Player) {
            chipHelperPlayer.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer(), chipPlayerBankerX, chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            playerBet = 0;
            clickPlayerCount = 0;
            chipHelperPlayer.setOperationButtonDisplay(false);

        }
        if (type == BaccaratBetType.All || type == Banker) {
            chipHelperBanker.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker(), -chipPlayerBankerX, chipPlayerBankerY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            bankerBet = 0;
            clickBankerCount = 0;
            chipHelperBanker.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.Tie) {
            chipHelperTie.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getTie(), tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            tieBet = 0;
            clickTieCount = 0;
            chipHelperTie.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.Lucky) {
            chipHelperLucky.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getLucky(), tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            luckyBet = 0;
            clickLuckyCount = 0;
            chipHelperLucky.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.Any) {
            chipHelperAny.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAny(), tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            anyBet = 0;
            clickAnyCount = 0;
            chipHelperAny.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.PlayerN) {
            chipHelperPlayerN.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerN(), tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            playerNBet = 0;
            clickPlayerNCount = 0;
            chipHelperPlayerN.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.Perfect) {
            chipHelperPerfect.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPerfect(), tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            perfectBet = 0;
            clickPerfectCount = 0;
            chipHelperPerfect.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.BankerN) {
            chipHelperBankerN.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerN(), tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            bankerNBet = 0;
            clickBankerNCount = 0;
            chipHelperBankerN.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.CowPlayer) {
            chipHelperCPlayer.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer(), tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            cPlayerBet = 0;
            clickCPlayerCount = 0;
            chipHelperCPlayer.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.CowTie) {
            chipHelperCTie.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowTie(), tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            cTieBet = 0;
            clickCTieCount = 0;
            chipHelperCTie.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.CowBanker) {
            chipHelperCBanker.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker(), tieX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            cBankerBet = 0;
            clickCBankerCount = 0;
            chipHelperCBanker.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.PlayerPair) {
            chipHelperPlayerPair.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerPair(), chipX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            playerPairBet = 0;
            clickPlayerPairCount = 0;
            chipHelperPlayerPair.setOperationButtonDisplay(false);
        }
        if (type == BaccaratBetType.All || type == BaccaratBetType.BankerPair) {
            chipHelperBankerPair.showChip(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerPair(), -chipX, chipY, AutoUtils.getPercentHeightSize(40), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(46), tipY, AutoUtils.getPercentHeightSize(32) * 2, AutoUtils.getPercentHeightSize(20));
            bankerPairBet = 0;
            clickBankerPairCount = 0;
            chipHelperBankerPair.setOperationButtonDisplay(false);
        }
        if (playerBet == 0 &&
                bankerBet == 0 &&
                playerPairBet == 0 &&
                bankerPairBet == 0 &&
                tieBet == 0 &&
                luckyBet == 0 &&
                anyBet == 0 &&
                playerNBet == 0 &&
                perfectBet == 0 &&
                bankerNBet == 0 &&
                cPlayerBet == 0 &&
                cTieBet == 0 &&
                cBankerBet == 0 &&
                clickBankerCount == 0 &&
                clickPlayerCount == 0 &&
                clickBankerPairCount == 0 &&
                clickPlayerPairCount == 0 &&
                clickTieCount == 0 &&
                clickLuckyCount == 0 &&
                clickAnyCount == 0 &&
                clickPlayerNCount == 0 &&
                clickPerfectCount == 0 &&
                clickBankerNCount == 0 &&
                clickCPlayerCount == 0 &&
                clickCTieCount == 0 &&
                clickCBankerCount == 0
        ) {
            BetUiHelper.betStateColor(tvTableBetSure, false);
        }
    }

    private void clearBetBg() {
        img_bet_bg_p.setBackgroundResource(0);
        img_bet_bg_b.setBackgroundResource(0);
        img_bet_bg_t.setBackgroundResource(0);
        img_bet_bg_pp.setBackgroundResource(0);
        img_bet_bg_bp.setBackgroundResource(0);
        img_bet_bg_lucky.setBackgroundResource(0);
        img_bet_bg_any.setBackgroundResource(0);
        img_bet_bg_player_n.setBackgroundResource(0);
        img_bet_bg_perfect.setBackgroundResource(0);
        img_bet_bg_banker_n.setBackgroundResource(0);
        img_bet_bg_c_p.setBackgroundResource(0);
        img_bet_bg_c_t.setBackgroundResource(0);
        img_bet_bg_c_b.setBackgroundResource(0);
    }

    private void showBetBg() {
        clearBetBg();
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayer() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_p.setBackgroundResource(R.mipmap.gd_bet_v_p);
            } else {
                img_bet_bg_p.setBackgroundResource(R.mipmap.gd_bet_h_p);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBanker() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_b.setBackgroundResource(R.mipmap.gd_bet_v_b);
            } else {
                img_bet_bg_b.setBackgroundResource(R.mipmap.gd_bet_h_b);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getTie() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_t.setBackgroundResource(R.mipmap.gd_bet_v_t);
            } else {
                img_bet_bg_t.setBackgroundResource(R.mipmap.gd_bet_h_t);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerPair() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_pp.setBackgroundResource(R.mipmap.gd_bet_v_pp);
            } else {
                img_bet_bg_pp.setBackgroundResource(R.mipmap.gd_bet_h_pp);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerPair() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_bp.setBackgroundResource(R.mipmap.gd_bet_v_bp);
            } else {
                img_bet_bg_bp.setBackgroundResource(R.mipmap.gd_bet_h_bp);
            }
        }

        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getLucky() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_lucky.setBackgroundResource(R.mipmap.gd_bet_v_lucky);
            } else {
                img_bet_bg_lucky.setBackgroundResource(R.mipmap.gd_bet_h_lucky);
            }
        }

        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAny() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_any.setBackgroundResource(R.mipmap.gd_bet_v_any);
            } else {
                img_bet_bg_any.setBackgroundResource(R.mipmap.gd_bet_h_any);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPlayerN() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_player_n.setBackgroundResource(R.mipmap.gd_bet_v_pn);
            } else {
                img_bet_bg_player_n.setBackgroundResource(R.mipmap.gd_bet_h_pn);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getPerfect() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_perfect.setBackgroundResource(R.mipmap.gd_bet_v_perfect);
            } else {
                img_bet_bg_perfect.setBackgroundResource(R.mipmap.gd_bet_h_perfect);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getBankerN() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_banker_n.setBackgroundResource(R.mipmap.gd_bet_v_bn);
            } else {
                img_bet_bg_banker_n.setBackgroundResource(R.mipmap.gd_bet_h_bn);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowPlayer() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_c_p.setBackgroundResource(R.mipmap.gd_bet_v_cp);
            } else {
                img_bet_bg_c_p.setBackgroundResource(R.mipmap.gd_bet_h_cp);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowTie() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_c_t.setBackgroundResource(R.mipmap.gd_bet_v_ct);
            } else {
                img_bet_bg_c_t.setBackgroundResource(R.mipmap.gd_bet_h_ct);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getCowBanker() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_c_b.setBackgroundResource(R.mipmap.gd_bet_v_cb);
            } else {
                img_bet_bg_c_b.setBackgroundResource(R.mipmap.gd_bet_h_cb);
            }
        }
    }

    private void showRepeatBetBg() {
        clearBetBg();
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayer() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_p.setBackgroundResource(R.mipmap.gd_bet_v_p);
            } else {
                img_bet_bg_p.setBackgroundResource(R.mipmap.gd_bet_h_p);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBanker() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_b.setBackgroundResource(R.mipmap.gd_bet_v_b);
            } else {
                img_bet_bg_b.setBackgroundResource(R.mipmap.gd_bet_h_b);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getTie() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_t.setBackgroundResource(R.mipmap.gd_bet_v_t);
            } else {
                img_bet_bg_t.setBackgroundResource(R.mipmap.gd_bet_h_t);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayerPair() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_pp.setBackgroundResource(R.mipmap.gd_bet_v_pp);
            } else {
                img_bet_bg_pp.setBackgroundResource(R.mipmap.gd_bet_h_pp);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBankerPair() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_bp.setBackgroundResource(R.mipmap.gd_bet_v_bp);
            } else {
                img_bet_bg_bp.setBackgroundResource(R.mipmap.gd_bet_h_bp);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getLucky() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_lucky.setBackgroundResource(R.mipmap.gd_bet_v_lucky);
            } else {
                img_bet_bg_lucky.setBackgroundResource(R.mipmap.gd_bet_h_lucky);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getAny() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_any.setBackgroundResource(R.mipmap.gd_bet_v_any);
            } else {
                img_bet_bg_any.setBackgroundResource(R.mipmap.gd_bet_h_any);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPlayerN() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_player_n.setBackgroundResource(R.mipmap.gd_bet_v_pn);
            } else {
                img_bet_bg_player_n.setBackgroundResource(R.mipmap.gd_bet_h_pn);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getPerfect() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_perfect.setBackgroundResource(R.mipmap.gd_bet_v_perfect);
            } else {
                img_bet_bg_perfect.setBackgroundResource(R.mipmap.gd_bet_v_perfect);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getBankerN() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_banker_n.setBackgroundResource(R.mipmap.gd_bet_v_bn);
            } else {
                img_bet_bg_banker_n.setBackgroundResource(R.mipmap.gd_bet_v_bn);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowPlayer() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_c_p.setBackgroundResource(R.mipmap.gd_bet_v_cp);
            } else {
                img_bet_bg_c_p.setBackgroundResource(R.mipmap.gd_bet_h_cp);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowTie() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_c_t.setBackgroundResource(R.mipmap.gd_bet_v_ct);
            } else {
                img_bet_bg_c_t.setBackgroundResource(R.mipmap.gd_bet_h_ct);
            }
        }
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratRepeatBetInformation().getCowBanker() > 0) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                img_bet_bg_c_b.setBackgroundResource(R.mipmap.gd_bet_v_cb);
            } else {
                img_bet_bg_c_b.setBackgroundResource(R.mipmap.gd_bet_h_cb);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isFirstBet", isFirstBet);
    }

    @Override
    protected void showPool() {
        super.showPool();
        if (lv_table_pool.getVisibility() == View.GONE) {
            WidgetUtil.showAnimation(lv_table_pool, true, Gravity.BOTTOM);
        } else {
            WidgetUtil.showAnimation(lv_table_pool, false, Gravity.BOTTOM);
        }
    }

    @BindView(R2.id.gd__ll_info)
    LinearLayout ll_info;
    @BindView(R2.id.gd__lv_user_info)
    ListView lv_user_info;
    @BindView(R2.id.gd__lv_pool)
    ListView lv_pool;
    @BindView(R2.id.gd__tv_time)
    TextView tv_time;
    @BindView(R2.id.gd__tv_total_bet)
    TextView tv_total_bet;
    @BindView(R2.id.gd__tv_win_lose_bet)
    TextView tv_win_lose_bet;

    @Override
    protected void showMenuPop(View v) {
        showHideUserInfo();
    }

    private void showHideUserInfo() {
        if (ll_info.getLayoutParams().height > 0) {
            WidgetUtil.shrinkAnimation(ll_info, ScreenUtil.dip2px(mContext, 165), 0);
        } else {
            WidgetUtil.shrinkAnimation(ll_info, 0, ScreenUtil.dip2px(mContext, 165));
        }
    }

    private List<LiveInfoBean> updateInfoData() {
        AppTool.setAppLanguage(this, AppTool.getAppLanguage(this));
        List<LiveInfoBean> strData = new ArrayList<LiveInfoBean>();
        LiveInfoBean data;
        String name = "";
        if (!TextUtils.isEmpty(usName)) {
            name = usName.toUpperCase();
        }
        data = new LiveInfoBean(getString(R.string.gd_ID), name, "");
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.gd_BET), mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratBetInformation().getAllBetMoney() + "", "");
        if (Integer.parseInt(data.getValue1()) > 0) {
            tv_total_bet.setText(mAppViewModel.covertLimit(Integer.parseInt(data.getValue1())) + "");
            rightBetTv.setText(mAppViewModel.covertLimit(Integer.parseInt(data.getValue1())) + "");
        } else {
            tv_total_bet.setText(getString(R.string.gd_BET) + " :0");
            rightBetTv.setText(getString(R.string.gd_BET) + " :0");
        }
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.W_L), mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney() + "", "");
        if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney() > 0) {
            tv_win_lose_bet.setTextColor(ContextCompat.getColor(mContext, R.color.win_color));
            tv_win_lose_bet.setText(mAppViewModel.covertWinLose(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()) + "");
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.win_color));
            rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()) + "");
        } else if (mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney() == 0) {
            tv_win_lose_bet.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            tv_win_lose_bet.setText(getString(R.string.W_L) + " :0");
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            rightWinLoseTv.setText(getString(R.string.W_L) + " :0");
        } else {
            tv_win_lose_bet.setTextColor(ContextCompat.getColor(mContext, R.color.lose_color));
            tv_win_lose_bet.setText(mAppViewModel.covertWinLose(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()) + "");
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.banker_color));
            rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getWonMoney()) + "");
        }
        strData.add(data);
        data = new LiveInfoBean(TextUtils.isEmpty(currency) ? getString(R.string.BAL) : currency, mAppViewModel.getUser().getBalance() + "", "");
        rightBalanceTv.setText(mAppViewModel.getUser().getBalance() + "");
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.LIMIT_POP), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinBankerPlayerBet()) + "-" + mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxBankerPlayerBet()), "");
        strData.add(data);
        if (getCurrentBetContent() == 1) {
            strData.add(new LiveInfoBean(getString(R.string.banker1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinBankerPlayerBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxBankerPlayerBet())));
            strData.add(new LiveInfoBean(getString(R.string.player1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinBankerPlayerBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxBankerPlayerBet())));
            strData.add(new LiveInfoBean(getString(R.string.tie1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinTieBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxTieBet())));
            strData.add(new LiveInfoBean(getString(R.string.PP1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPairBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPairBet())));
            strData.add(new LiveInfoBean(getString(R.string.BP1), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPairBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPairBet())));
            strData.add(new LiveInfoBean(getString(R.string.lucky6), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinLuckySixBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxLuckySixBet())));
            strData.add(new LiveInfoBean(getString(R.string.anypairs), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinAnyPairBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxAnyPairBet())));
            strData.add(new LiveInfoBean(getString(R.string.nplayer), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPlayerNaturalBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPlayerNaturalBet())));
            strData.add(new LiveInfoBean(getString(R.string.perfectpair), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinPerfectPairBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxPerfectPairBet())));
            strData.add(new LiveInfoBean(getString(R.string.nbanker), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinBankerNaturalBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxBankerNaturalBet())));
        } else {
            strData.add(new LiveInfoBean(getString(R.string.cplayer), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinCowPlayerBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxCowPlayerBet())));
            strData.add(new LiveInfoBean(getString(R.string.ctie), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinCowTieBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxCowTieBet())));
            strData.add(new LiveInfoBean(getString(R.string.cbanker), mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMinCowBankerBet())
                    , mAppViewModel.covertLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getBaccaratLimit(mAppViewModel.getBaccarat(mAppViewModel.getTableId()).getLimitIndex()).getMaxCowBankerBet())));
        }

        return strData;
    }

    private void setInfoData(ListView listView) {
        if (contentInfo == null)
            contentInfo = new AdapterViewContent<>(mContext, listView);
        contentInfo.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {

            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_user_info1;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.gd__tv_name);
                TextView tvValue = helper.retrieveView(R.id.gd__tv_value);
                tvType.setText(item.getType());
                tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.gold));
                if (position > 4) {
                    tvValue.setText(item.getValue1() + "-" + item.getValue2());
                } else {
                    if (position == 2) {
                        if (item.getValue1().startsWith("-")) {
                            tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.banker_color));
                        } else if (Double.parseDouble(item.getValue1()) > 0) {
                            tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.player_color));
                        } else {
                            tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.gold));
                        }
                    }
                    tvValue.setText(item.getValue1());
                }
            }
        });
        contentInfo.setData(updateInfoData());
    }

//    @BindView(R.id.tv_niu_p_hint)
//    TextView tv_niu_p_hint;
//    @BindView(R.id.tv_niu_b_hint)
//    TextView tv_niu_b_hint;

    @Override
    public void onInGameChooseLanguage() {
        initApngList();
        mAppViewModel.getBaccarat(mAppViewModel.getTableId()).setBigRoadOld("");
        fl_baccarat_a_table.setBackgroundResource(0);
        fl_baccarat_b_table.setBackgroundResource(0);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fl_baccarat_a_table.setBackgroundResource(R.mipmap.gd_baccarat_bg_h);
            fl_baccarat_b_table.setBackgroundResource(R.mipmap.gd_baccarat_bg_h_niu);
        } else {
            fl_baccarat_a_table.setBackgroundResource(R.mipmap.gd_bet_a_bg_trans);
            fl_baccarat_b_table.setBackgroundResource(R.mipmap.gd_bet_a_bg_trans_niu);
        }
        tv_ask1.setText(getString(R.string.banker1));
        tv_ask2.setText(getString(R.string.player1));
        tv_ask1_name.setText(getString(R.string.ask));
        tv_ask2_name.setText(getString(R.string.ask));
        tv_good_road_name.setText(getString(R.string.good_road));
        ((TextView) findViewById(R.id.gd__tv_banker)).setText(getString(R.string.banker_m));
        ((TextView) findViewById(R.id.gd__tv_player)).setText(getString(R.string.player_m));
        ((TextView) findViewById(R.id.gd__tv_tie)).setText(getString(R.string.tie1));
        ((TextView) findViewById(R.id.gd__tv_banker_pair)).setText(getString(R.string.BP));
        ((TextView) findViewById(R.id.gd__tv_player_pair)).setText(getString(R.string.PP));
        ((TextView) findViewById(R.id.gd__tv_total)).setText(getString(R.string.total_m));
        tv_player_result_name.setText(getString(R.string.player_home));
        tv_banker_result_name.setText(getString(R.string.banker_home));
//        tv_niu_p_hint.setText(getString(R.string.niu_hint));
//        tv_niu_b_hint.setText(getString(R.string.niu_hint));
        if (getCurrentBetContent() == 1) {
            tv_change_bet_content.setText(getString(R.string.nn));
        } else {
            tv_change_bet_content.setText(getString(R.string.bjl));
        }
        initResultAnimation();
    }

    public void clickNiuRule(View f) {
        PopRule popRule = new PopRule(mContext, f, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popRule.showPopupCenterWindow();
    }

    public void clickChangeBetContent(View f) {
        if (getCurrentBetContent() == 1) {
            fl_bet_content_1.setVisibility(View.INVISIBLE);
            fl_bet_content_2.setVisibility(View.VISIBLE);
            tv_change_bet_content.setText(getString(R.string.bjl));
        } else {
            fl_bet_content_1.setVisibility(View.VISIBLE);
            fl_bet_content_2.setVisibility(View.INVISIBLE);
            tv_change_bet_content.setText(getString(R.string.nn));
        }
    }

    private int getCurrentBetContent() {
        if (fl_bet_content_1.getVisibility() == View.VISIBLE && fl_bet_content_2.getVisibility() == View.INVISIBLE) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public boolean isCanSlideChangeTable() {
        if (isSlideInfo) {
            return false;
        }
        if (mAppViewModel.getTableId() == 71) {
            if (fl_poker_bottom_parent.getVisibility() == View.VISIBLE) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
