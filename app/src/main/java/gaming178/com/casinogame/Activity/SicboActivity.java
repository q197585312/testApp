package gaming178.com.casinogame.Activity;

import android.animation.Animator;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.unkonw.testapp.libs.widget.VideoHelper;
import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.autolayout.config.UseLandscape;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.autolayout.utils.L;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.OnClick;
import cn.nodemedia.NodePlayerView;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Activity.entity.DiceBean;
import gaming178.com.casinogame.Activity.entity.DiceContentBean;
import gaming178.com.casinogame.Bean.BetDetail;
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.casinogame.Bean.LiveInfoBean;
import gaming178.com.casinogame.Bean.Sicbo;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.BackgroudMuzicService;
import gaming178.com.casinogame.Util.ChipShowHelper;
import gaming178.com.casinogame.Util.CountDownView;
import gaming178.com.casinogame.Util.FrontMuzicService;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.GdToastUtils;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.allinone.util.WidgetUtil;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.myview.miscwidgets.widget.Panel;


/**
 * Created by Administrator on 2016/4/11.
 */
public class SicboActivity extends BaseActivity implements UseLandscape {
    static boolean isActive = false;
    Map<Boolean, Integer> selectedMap = new HashMap<>();
    Map<FrameLayout, ChipShowHelper> ChipMap = new HashMap<>();
    String limitPop;
    @BindView(R2.id.gd__tv_service_time)
    TextView serviceTime;
    @BindView(R2.id.gd__lv_percentage)
    ListView lv_percentage;
    @Nullable
    @BindView(R2.id.gd__ll_chip)
    LinearLayout ll_chip;
    @BindView(R2.id.gd__handle1)
    View handle1;
    @BindView(R2.id.gd__ll_bet_btn_parent)
    View ll_bet_btn_parent;
    @BindView(R2.id.gd__sibao_bet_bg)
    View sibao_bet_bg;
    @BindView(R2.id.gd__tv_menu)
    TextView tvMenu;
    @BindView(R2.id.gd__bottonPanel1)
    Panel bottonPanel1;
    @BindView(R2.id.gd__fl_baccarat_bg)
    FrameLayout fl_baccarat_bg;
    @BindView(R2.id.gd__fl_baccarat_parent)
    FrameLayout fl_baccarat_parent;
    /* @BindView(R2.id.gd__tv_table_pool)
     TextView tv_table_pool;*/
    @BindView(R2.id.gd__tv_table_bet_replay)
    ImageView tvTableBetReplay;
    @BindView(R2.id.gd__tv_table_bet_sure)
    ImageView tvTableBetSure;
    @BindView(R2.id.gd__tv_table_bet_cancel)
    ImageView tvTableBetCancel;
    @BindView(R2.id.gd__leftPanel1)
    Panel leftPanel1;
    @BindView(R2.id.gd__fl_sicbo_result)
    FrameLayout fl_sicbo_result;
    @BindView(R2.id.gd__lv_table_bet_limit_red)
    ListView lvTableBetLimitRed;
    @BindView(R2.id.gd__tv_table_timer)
    TextView tv_table_timer;
    @BindView(R2.id.gd__countdown_view)
    CountDownView countdown_view;
    @BindView(R2.id.gd__btn_table_limit_red_title)
    TextView btnTableLimit;
    @BindView(R2.id.gd__lv_table_pool)
    ListView lv_table_pool;
    @BindView(R2.id.gd__fl_sicbo_big_f1)
    FrameLayout flSicboBigF1;
    @BindView(R2.id.gd__fl_sicbo_even_f1)
    FrameLayout flSicboEvenF1;
    @BindView(R2.id.gd__fl_sicbo_dices6_f1)
    FrameLayout flSicboDices6F1;
    @BindView(R2.id.gd__fl_sicbo_dices5_f1)
    FrameLayout flSicboDices5F1;
    @BindView(R2.id.gd__fl_sicbo_dices4_f1)
    FrameLayout flSicboDices4F1;
    @BindView(R2.id.gd__fl_sicbo_dices3_f1)
    FrameLayout flSicboDices3F1;
    @BindView(R2.id.gd__fl_sicbo_dices2_f1)
    FrameLayout flSicboDices2F1;
    @BindView(R2.id.gd__fl_sicbo_dices1_f1)
    FrameLayout flSicboDices1F1;
    @BindView(R2.id.gd__fl_sicbo_odd_f1)
    FrameLayout flSicboOddF1;
    @BindView(R2.id.gd__fl_sicbo_small_f1)
    FrameLayout flSicboSmallF1;
    @BindView(R2.id.gd__iv_sicbo_big_bg)
    ImageView ivSicboBigBg;
    @BindView(R2.id.gd__iv_sicbo_even_bg)
    ImageView ivSicboEvenBg;
    @BindView(R2.id.gd__iv_sicbo_dices6_bg)
    ImageView ivSicboDices6Bg;
    @BindView(R2.id.gd__iv_sicbo_dices5_bg)
    ImageView ivSicboDices5Bg;
    @BindView(R2.id.gd__iv_sicbo_dices4_bg)
    ImageView ivSicboDices4Bg;
    @BindView(R2.id.gd__iv_sicbo_dices3_bg)
    ImageView ivSicboDices3Bg;
    @BindView(R2.id.gd__iv_sicbo_dices2_bg)
    ImageView ivSicboDices2Bg;
    @BindView(R2.id.gd__iv_sicbo_dices1_bg)
    ImageView ivSicboDices1Bg;
    @BindView(R2.id.gd__iv_sicbo_odd_bg)
    ImageView ivSicboOddBg;
    @BindView(R2.id.gd__iv_sicbo_small_bg)
    ImageView ivSicboSmallBg;
    @BindView(R2.id.gd__iv_sicbo_pairs6_bg)
    ImageView ivSicboPairs6Bg;
    @BindView(R2.id.gd__fl_sicbo_pairs6)
    FrameLayout flSicboPairs6;
    @BindView(R2.id.gd__iv_sicbo_pairs5_bg)
    ImageView ivSicboPairs5Bg;
    @BindView(R2.id.gd__fl_sicbo_pairs5)
    FrameLayout flSicboPairs5;
    @BindView(R2.id.gd__iv_sicbo_pairs4_bg)
    ImageView ivSicboPairs4Bg;
    @BindView(R2.id.gd__fl_sicbo_pairs4)
    FrameLayout flSicboPairs4;
    @BindView(R2.id.gd__iv_sicbo_three_forces_bg)
    ImageView ivSicboThreeForcesBg;
    @BindView(R2.id.gd__fl_sicbo_all_dice)
    FrameLayout flSicboAlldice;
    @BindView(R2.id.gd__iv_sicbo_pairs3_bg)
    ImageView ivSicboPairs3Bg;
    @BindView(R2.id.gd__fl_sicbo_pairs3)
    FrameLayout flSicboPairs3;
    @BindView(R2.id.gd__iv_sicbo_pairs2_bg)
    ImageView ivSicboPairs2Bg;
    @BindView(R2.id.gd__fl_sicbo_pairs2)
    FrameLayout flSicboPairs2;
    @BindView(R2.id.gd__iv_sicbo_pairs1_bg)
    ImageView ivSicboPairs1Bg;
    @BindView(R2.id.gd__fl_sicbo_pairs1)
    FrameLayout flSicboPairs1;
    @BindView(R2.id.gd__iv_sicbo_points17_bg)
    ImageView ivSicboPoints17Bg;
    @BindView(R2.id.gd__fl_sicbo_points17)
    FrameLayout flSicboPoints17;
    @BindView(R2.id.gd__iv_sicbo_points16_bg)
    ImageView ivSicboPoints16Bg;
    @BindView(R2.id.gd__fl_sicbo_points16)
    FrameLayout flSicboPoints16;
    @BindView(R2.id.gd__iv_sicbo_points15_bg)
    ImageView ivSicboPoints15Bg;
    @BindView(R2.id.gd__fl_sicbo_points15)
    FrameLayout flSicboPoints15;
    @BindView(R2.id.gd__iv_sicbo_points14_bg)
    ImageView ivSicboPoints14Bg;
    @BindView(R2.id.gd__fl_sicbo_points14)
    FrameLayout flSicboPoints14;
    @BindView(R2.id.gd__iv_sicbo_points13_bg)
    ImageView ivSicboPoints13Bg;
    @BindView(R2.id.gd__fl_sicbo_points13)
    FrameLayout flSicboPoints13;
    @BindView(R2.id.gd__iv_sicbo_points12_bg)
    ImageView ivSicboPoints12Bg;
    @BindView(R2.id.gd__fl_sicbo_points12)
    FrameLayout flSicboPoints12;
    @BindView(R2.id.gd__iv_sicbo_points11_bg)
    ImageView ivSicboPoints11Bg;
    @BindView(R2.id.gd__fl_sicbo_points11)
    FrameLayout flSicboPoints11;
    @BindView(R2.id.gd__iv_sicbo_points10_bg)
    ImageView ivSicboPoints10Bg;
    @BindView(R2.id.gd__fl_sicbo_points10)
    FrameLayout flSicboPoints10;
    @BindView(R2.id.gd__iv_sicbo_points9_bg)
    ImageView ivSicboPoints9Bg;
    @BindView(R2.id.gd__fl_sicbo_points9)
    FrameLayout flSicboPoints9;
    @BindView(R2.id.gd__iv_sicbo_points8_bg)
    ImageView ivSicboPoints8Bg;
    @BindView(R2.id.gd__fl_sicbo_points8)
    FrameLayout flSicboPoints8;
    @BindView(R2.id.gd__iv_sicbo_points7_bg)
    ImageView ivSicboPoints7Bg;
    @BindView(R2.id.gd__fl_sicbo_points7)
    FrameLayout flSicboPoints7;
    @BindView(R2.id.gd__iv_sicbo_points6_bg)
    ImageView ivSicboPoints6Bg;
    @BindView(R2.id.gd__fl_sicbo_points6)
    FrameLayout flSicboPoints6;
    @BindView(R2.id.gd__iv_sicbo_points5_bg)
    ImageView ivSicboPoints5Bg;
    @BindView(R2.id.gd__fl_sicbo_points5)
    FrameLayout flSicboPoints5;
    @BindView(R2.id.gd__iv_sicbo_points4_bg)
    ImageView ivSicboPoints4Bg;
    @BindView(R2.id.gd__fl_sicbo_points4)
    FrameLayout flSicboPoints4;
    @BindView(R2.id.gd__iv_sicbo_combination56_bg)
    ImageView ivSicboCombination56Bg;
    @BindView(R2.id.gd__fl_sicbo_combination56)
    FrameLayout flSicboCombination56;
    @BindView(R2.id.gd__iv_sicbo_combination46_bg)
    ImageView ivSicboCombination46Bg;
    @BindView(R2.id.gd__iv_sicbo_combination45_bg)
    ImageView ivSicboCombination45Bg;
    @BindView(R2.id.gd__fl_sicbo_combination46)
    FrameLayout flSicboCombination46;
    @BindView(R2.id.gd__fl_sicbo_combination45)
    FrameLayout flSicboCombination45;
    @BindView(R2.id.gd__iv_sicbo_combination36_bg)
    ImageView ivSicboCombination36Bg;
    @BindView(R2.id.gd__fl_sicbo_combination36)
    FrameLayout flSicboCombination36;
    @BindView(R2.id.gd__iv_sicbo_combination35_bg)
    ImageView ivSicboCombination35Bg;
    @BindView(R2.id.gd__fl_sicbo_combination35)
    FrameLayout flSicboCombination35;
    @BindView(R2.id.gd__iv_sicbo_combination34_bg)
    ImageView ivSicboCombination34Bg;
    @BindView(R2.id.gd__fl_sicbo_combination34)
    FrameLayout flSicboCombination34;
    @BindView(R2.id.gd__iv_sicbo_combination26_bg)
    ImageView ivSicboCombination26Bg;
    @BindView(R2.id.gd__fl_sicbo_combination26)
    FrameLayout flSicboCombination26;
    @BindView(R2.id.gd__iv_sicbo_combination25_bg)
    ImageView ivSicboCombination25Bg;
    @BindView(R2.id.gd__fl_sicbo_combination25)
    FrameLayout flSicboCombination25;
    @BindView(R2.id.gd__iv_sicbo_combination24_bg)
    ImageView ivSicboCombination24Bg;
    @BindView(R2.id.gd__fl_sicbo_combination24)
    FrameLayout flSicboCombination24;
    @BindView(R2.id.gd__iv_sicbo_combination23_bg)
    ImageView ivSicboCombination23Bg;
    @BindView(R2.id.gd__fl_sicbo_combination23)
    FrameLayout flSicboCombination23;
    @BindView(R2.id.gd__iv_sicbo_combination16_bg)
    ImageView ivSicboCombination16Bg;
    @BindView(R2.id.gd__fl_sicbo_combination16)
    FrameLayout flSicboCombination16;
    @BindView(R2.id.gd__iv_sicbo_combination15_bg)
    ImageView ivSicboCombination15Bg;
    @BindView(R2.id.gd__fl_sicbo_combination15)
    FrameLayout flSicboCombination15;
    @BindView(R2.id.gd__iv_sicbo_combination14_bg)
    ImageView ivSicboCombination14Bg;
    @BindView(R2.id.gd__fl_sicbo_combination14)
    FrameLayout flSicboCombination14;
    @BindView(R2.id.gd__iv_sicbo_combination13_bg)
    ImageView ivSicboCombination13Bg;
    @BindView(R2.id.gd__fl_sicbo_combination13)
    FrameLayout flSicboCombination13;
    @BindView(R2.id.gd__iv_sicbo_combination12_bg)
    ImageView ivSicboCombination12Bg;
    @BindView(R2.id.gd__fl_sicbo_combination12)
    FrameLayout flSicboCombination12;
    @BindView(R2.id.gd__iv_sicbo_single6_bg)
    ImageView ivSicboSingle6Bg;
    @BindView(R2.id.gd__fl_sicbo_single6)
    FrameLayout flSicboSingle6;
    @BindView(R2.id.gd__iv_sicbo_single5_bg)
    ImageView ivSicboSingle5Bg;
    @BindView(R2.id.gd__fl_sicbo_single5)
    FrameLayout flSicboSingle5;
    @BindView(R2.id.gd__iv_sicbo_single4_bg)
    ImageView ivSicboSingle4Bg;
    @BindView(R2.id.gd__fl_sicbo_single4)
    FrameLayout flSicboSingle4;
    @BindView(R2.id.gd__iv_sicbo_single3_bg)
    ImageView ivSicboSingle3Bg;
    @BindView(R2.id.gd__fl_sicbo_single3)
    FrameLayout flSicboSingle3;
    @BindView(R2.id.gd__iv_sicbo_single2_bg)
    ImageView ivSicboSingle2Bg;
    @BindView(R2.id.gd__fl_sicbo_single2)
    FrameLayout flSicboSingle2;
    @BindView(R2.id.gd__iv_sicbo_single1_bg)
    ImageView ivSicboSingle1Bg;
    @BindView(R2.id.gd__fl_sicbo_single1)
    FrameLayout flSicboSingle1;
    FrameLayout flSicbaoGg;
    List<AnimationDrawable> animationDrawableList = new ArrayList<>();
    AdapterViewContent<LiveInfoBean> contentPool = null;
    AdapterViewContent<LiveInfoBean> contentInfo = null;
    AdapterViewContent<LiveInfoBean> contentPercentage = null;
    AdapterViewContent<DiceContentBean> contentResults = null;
    FrameLayout fl_vedio_location_parent;
    FrameLayout fl_vedio_parent;
    FrameLayout fl_surface_parent;
    SbBetType type = SbBetType.All;
    boolean isShowWinLose = true;
    boolean isCanShowChip = true;
    boolean isCanHideChip = true;
    boolean isFirst = true;
    ImageView currentSure;
    ImageView currentCancel;
    volatile boolean isCanbet = true;
    @BindView(R2.id.gd__ll_info)
    LinearLayout ll_info;
    @BindView(R2.id.gd__lv_user_info)
    ListView lv_user_info;
    @BindView(R2.id.gd__lv_pool)
    ListView lv_pool;
    @BindView(R2.id.gd__tv_time)
    TextView tv_time;
    private AdapterView lv_baccarat_chips;
    private AdapterView lv_table_results;
    private TextView tv_table_game_number;
    private boolean limitRedShowAble = true;
    private boolean betInfoShowAble = false;
    private boolean limitResults = true;
    private int chooseChip = 0;
    private int clickBigCount = 0;
    private int clickSmallCount = 0;
    private int clickOddCount = 0;
    private int clickEvenCount = 0;
    private int clickWaidiceCount1 = 0;
    private int clickWaidiceCount2 = 0;
    private int clickWaidiceCount3 = 0;
    private int clickWaidiceCount4 = 0;
    private int clickWaidiceCount5 = 0;
    private int clickWaidiceCount6 = 0;
    private int clickPairsCount1 = 0;
    private int clickPairsCount2 = 0;
    private int clickPairsCount3 = 0;
    private int clickPairsCount4 = 0;
    private int clickPairsCount5 = 0;
    private int clickPairsCount6 = 0;
    private int clickPointsCount4 = 0;
    private int clickPointsCount5 = 0;
    private int clickPointsCount6 = 0;
    private int clickPointsCount7 = 0;
    private int clickPointsCount8 = 0;
    private int clickPointsCount9 = 0;
    private int clickPointsCount10 = 0;
    private int clickPointsCount11 = 0;
    private int clickPointsCount12 = 0;
    private int clickPointsCount13 = 0;
    private int clickPointsCount14 = 0;
    private int clickPointsCount15 = 0;
    private int clickPointsCount16 = 0;
    private int clickPointsCount17 = 0;
    private int clickNinewayCount12 = 0;
    private int clickNinewayCount13 = 0;
    private int clickNinewayCount14 = 0;
    private int clickNinewayCount15 = 0;
    private int clickNinewayCount16 = 0;
    private int clickNinewayCount23 = 0;
    private int clickNinewayCount24 = 0;
    private int clickNinewayCount25 = 0;
    private int clickNinewayCount26 = 0;
    private int clickNinewayCount34 = 0;
    private int clickNinewayCount35 = 0;
    private int clickNinewayCount36 = 0;
    private int clickNinewayCount45 = 0;
    private int clickNinewayCount46 = 0;
    private int clickNinewayCount56 = 0;
    private int clickAlldiceCount = 0;
    private int clickThreeCount1 = 0;
    private int clickThreeCount2 = 0;
    private int clickThreeCount3 = 0;
    private int clickThreeCount4 = 0;
    private int clickThreeCount5 = 0;
    private int clickThreeCount6 = 0;
    private int bigBet = 0;
    private int smallBet = 0;
    private int oddBet = 0;
    private int evenBet = 0;
    private int waidiceBet1 = 0;
    private int waidiceBet2 = 0;
    private int waidiceBet3 = 0;
    private int waidiceBet4 = 0;
    private int waidiceBet5 = 0;
    private int waidiceBet6 = 0;
    private int pairsBet1 = 0;
    private int pairsBet2 = 0;
    private int pairsBet3 = 0;
    private int pairsBet4 = 0;
    private int pairsBet5 = 0;
    private int pairsBet6 = 0;
    private int alldiceBet = 0;
    private int pointsBet4 = 0;
    private int pointsBet5 = 0;
    private int pointsBet6 = 0;
    private int pointsBet7 = 0;
    private int pointsBet8 = 0;
    private int pointsBet9 = 0;
    private int pointsBet10 = 0;
    private int pointsBet11 = 0;
    private int pointsBet12 = 0;
    private int pointsBet13 = 0;
    private int pointsBet14 = 0;
    private int pointsBet15 = 0;
    private int pointsBet16 = 0;
    private int pointsBet17 = 0;
    private int ninewayBet12 = 0;
    private int ninewayBet13 = 0;
    private int ninewayBet14 = 0;
    private int ninewayBet15 = 0;
    private int ninewayBet16 = 0;
    private int ninewayBet23 = 0;
    private int ninewayBet24 = 0;
    private int ninewayBet25 = 0;
    private int ninewayBet26 = 0;
    private int ninewayBet34 = 0;
    private int ninewayBet35 = 0;
    private int ninewayBet36 = 0;
    private int ninewayBet45 = 0;
    private int ninewayBet46 = 0;
    private int ninewayBet56 = 0;
    private int threeBet1 = 0;
    private int threeBet2 = 0;
    private int threeBet3 = 0;
    private int threeBet4 = 0;
    private int threeBet5 = 0;
    private int threeBet6 = 0;
    private int betTimeCount = 0;
    private AnimationDrawable animationDrawableBig;
    private AnimationDrawable animationDrawableSmall;
    private AnimationDrawable animationDrawableOdd;
    private AnimationDrawable animationDrawableEven;
    private AnimationDrawable animationDrawableAllDices;
    private AnimationDrawable animationDrawableWaidice1;
    private AnimationDrawable animationDrawableWaidice2;
    private AnimationDrawable animationDrawableWaidice3;
    private AnimationDrawable animationDrawableWaidice4;
    private AnimationDrawable animationDrawableWaidice5;
    private AnimationDrawable animationDrawableWaidice6;
    private AnimationDrawable animationDrawablePair1;
    private AnimationDrawable animationDrawablePair2;
    private AnimationDrawable animationDrawablePair3;
    private AnimationDrawable animationDrawablePair4;
    private AnimationDrawable animationDrawablePair5;
    private AnimationDrawable animationDrawablePair6;
    private AnimationDrawable animationDrawableThree1;
    private AnimationDrawable animationDrawableThree2;
    private AnimationDrawable animationDrawableThree3;
    private AnimationDrawable animationDrawableThree4;
    private AnimationDrawable animationDrawableThree5;
    private AnimationDrawable animationDrawableThree6;
    private AnimationDrawable animationDrawablePoint4;
    private AnimationDrawable animationDrawablePoint5;
    private AnimationDrawable animationDrawablePoint6;
    private AnimationDrawable animationDrawablePoint7;
    private AnimationDrawable animationDrawablePoint8;
    private AnimationDrawable animationDrawablePoint9;
    private AnimationDrawable animationDrawablePoint10;
    private AnimationDrawable animationDrawablePoint11;
    private AnimationDrawable animationDrawablePoint12;
    private AnimationDrawable animationDrawablePoint13;
    private AnimationDrawable animationDrawablePoint14;
    private AnimationDrawable animationDrawablePoint15;
    private AnimationDrawable animationDrawablePoint16;
    private AnimationDrawable animationDrawablePoint17;
    private AnimationDrawable animationDrawableNineway12;
    private AnimationDrawable animationDrawableNineway13;
    private AnimationDrawable animationDrawableNineway14;
    private AnimationDrawable animationDrawableNineway15;
    private AnimationDrawable animationDrawableNineway16;
    private AnimationDrawable animationDrawableNineway23;
    private AnimationDrawable animationDrawableNineway24;
    private AnimationDrawable animationDrawableNineway25;
    private AnimationDrawable animationDrawableNineway26;
    private AnimationDrawable animationDrawableNineway34;
    private AnimationDrawable animationDrawableNineway35;
    private AnimationDrawable animationDrawableNineway36;
    private AnimationDrawable animationDrawableNineway45;
    private AnimationDrawable animationDrawableNineway46;
    private AnimationDrawable animationDrawableNineway56;
    private TextView btn_results;
    private TextView tv_sicbo_number01;
    private TextView tv_even01;
    private TextView tv_odd01;
    private TextView tv_small01;
    private TextView tv_big01;
    private TextView tv_waidic01;
    private GridLayout sicbo_bigsmall_road;
    private GridLayout sicbo_evenodd_road;
    private boolean personInfoShowAble;
    private NodePlayerView mPreview;
    private boolean isBottomOpen = false;
    private boolean isVisibility = false;
    private boolean isDiceVisible;
    private float density;
    private boolean bUpdateRoad = true;
    private boolean showRoad = false;
    private int sicboTimer = 0;
    private boolean bBetSucess = false;
    private String gameNumber = "";
    private UpdateStatus updateStatus = null;
    private Thread threadStatus = null;
    private boolean bGetStatus = true;
    private VideoHelper videoHelper;
    private boolean stateInit = false;
    private BackLobby backLobby = null;
    private Thread threadBackLobby = null;
    private UpdateBetMoney updateBetMoney = null;
    private Thread threadUpdateBetMoney = null;
    private SicboBet sicboBet = null;
    private Thread threadSicboBet = null;
    private UpdateRoad updateRoad = null;
    private Thread threadUpdateRoad = null;
    private UpdateGameNumber updateGameNumber = null;
    private Thread threadUpdateGameNumber = null;
    private UpdateWonMoney updateWonMoney = null;
    private Thread threadUpdateWonMoney = null;
    private boolean isSlideInfo = false;
    private boolean isCanClickVedio = true;
    private boolean isNeedBigVedio = true;
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
//                    if(btn_results == null )
//                        Log.i(WebSiteUrl.Tag,"btn_results==null");
//                    if(afbApp == null ) {
//                        Log.i(WebSiteUrl.Tag, "afbApp==null");
//                        break;
//                    }
                    btn_results.setText(getString(R.string.number) + ":" + mAppViewModel.getSicbo01().getGameNumber());
                    contentResults.setData(getResultsData());
                    contentResults.notifyDataSetChanged();
                    tv_sicbo_number01.setText(mAppViewModel.getSicbo01().getGameNumber());
                    tv_table_game_number.setText("SB1:" + mAppViewModel.getSicbo01().getGameNumber());
                    if (!gameNumber.equals(mAppViewModel.getSicbo01().getGameNumber())) {
                        clearAllChips();
                    }
                    break;
                case HandlerCode.SHOW_WIN_LOSS:
                    serviceTime.setText(mAppViewModel.getUser().getBalance() + "");
                    //提示输赢
                    if (bBetSucess) {
//                        if (mAppViewModel.getSicbo01().getWonMoney() >= 0 && mAppViewModel.getSicbo01().getGameStatus() == 5) {
//                            if (mAppViewModel.getSicbo01().getWonMoney() > 0)
//                                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_RESULTS, 7, componentFront, mContext, mAppViewModel.getFrontVolume());
//                            ToastUtils.showToast(mContext, getResources().getString(R.string.show_win) + " " + mAppViewModel.getSicbo01().getWonMoney(), ContextCompat.getColor(mContext, R.color.blue_word));
//                        } else
//                            ToastUtils.showToast(mContext, getResources().getString(R.string.show_loss) + " " + (-mAppViewModel.getSicbo01().getWonMoney()), Color.RED);
                        if (mAppViewModel.getSicbo01().getWonMoney() > 0 && !WidgetUtil.isRunBackground(SicboActivity.this)) {
                            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_RESULTS, 7, componentFront, mContext, mAppViewModel.getFrontVolume());
                            GdToastUtils.showWinningToast(mContext, getResources().getString(R.string.show_win) + " " + mAppViewModel.getSicbo01().getWonMoney(), ContextCompat.getColor(mContext, R.color.gold));
                        }
                    }


                    //清除所有的下注的筹码
                    clearAllChips();

                    break;
                case HandlerCode.SHOW_BET_SUCCESS:
                    int sure = R.mipmap.gd_sureimg;
                    if (currentSure != null && sure != 0) {
                        currentSure.setBackgroundResource(sure);
                    }
                    int no = R.mipmap.gd_noimg;
                    if (currentCancel != null && no != 0) {
                        currentCancel.setBackgroundResource(no);
                    }
                    clearBetChip(type);
                    dismissBlockDialog();
                    GdToastUtils.showBetSuccessToast(mContext, getResources().getString(R.string.show_bet_sucess) + " " + mAppViewModel.getSicbo01().getSicboBetInformation().getAllBetMoney());
                    serviceTime.setText(mAppViewModel.getUser().getBalance() + "");
                    break;
                case HandlerCode.SHOW_BET_MONEY:
                    showBetMoney();
                    isActive = true;
                    break;
                case HandlerCode.SHOW_BET_ERROR:
                    dismissBlockDialog();
                    Toast.makeText(mContext, R.string.show_bet_error, Toast.LENGTH_LONG).show();
                    clearBetChip(type);
                    break;
                case HandlerCode.SHOW_POPUP_RESULTS_WINDOW:
                    initPopResultsWindows();
                    break;
                case HandlerCode.THREAD_ERROR:
                    dismissBlockDialog();
                    break;

            }
            //

        }
    };

    @OnClick(R2.id.gd__iv_baccarat_change_table)
    public void clickTable(View v) {
        showChangeTable(v);
    }

    public void clickLeftPanel(View view) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (leftPanel1.isOpen()) {
                leftPanel1.setOpen(false, true);
            } else
                leftPanel1.setOpen(true, true);
        }
    }

    public void updateTimer() {
        if (sicboTimer == 0 && mAppViewModel.getSicbo01().getGameStatus() == 1 && mAppViewModel.getSicbo01().getTimer() > 0) {
            if (isShowWinLose) {
                isShowWinLose = false;
                updateWonMoney = new UpdateWonMoney();
                threadUpdateWonMoney = new Thread(updateWonMoney);
                threadUpdateWonMoney.start();
            }
            if (fl_vedio_location_parent != null && fl_vedio_parent != null && fl_surface_parent != null) {
                if (!isNeedBigVedio && fl_vedio_parent.getHeight() > fl_vedio_location_parent.getHeight()) {
                    isNeedBigVedio = true;
                    closeBigVedio();
                } else {
                    isNeedBigVedio = true;
                }
            }

//            if(updateGameNumber == null){
//                updateGameNumber = new UpdateGameNumber();
//                threadUpdateGameNumber = new Thread(updateGameNumber);
//                threadUpdateGameNumber.start();
//            }
            if (!gameNumber.equals(mAppViewModel.getSicbo01().getGameNumber())) {
                gameNumber = mAppViewModel.getSicbo01().getGameNumber();
                mAppViewModel.getSicbo01().Init();
                clearAllChips();
                ll_bet_btn_parent.setVisibility(View.VISIBLE);
                sicboTimer = mAppViewModel.getSicbo01().getTimer();
                setPercentageData(lv_percentage);
                countdown_view.setCountdownTime(sicboTimer);
                countdown_view.startCountDown();
                stateInit = true;
                bUpdateRoad = true;
                bBetSucess = false;
                betTimeCount++;
                if (betTimeCount == 6)//跳转到大厅
                {
                    if (!WidgetUtil.isRunBackground(SicboActivity.this)) {
                        GdToastUtils.showBackToast(mContext, getString(R.string.friendly_message), getString(R.string.show_back_lobby));
                    }
                    backLobby = new BackLobby();
                    threadBackLobby = new Thread(backLobby);
                    threadBackLobby.start();
                } else if (betTimeCount == 4) {
                    if (!WidgetUtil.isRunBackground(SicboActivity.this)) {
                        GdToastUtils.showBackToast(mContext, getString(R.string.friendly_message), getString(R.string.three_no_bet));
                    }
                }
                tvTableBetSure.setEnabled(true);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_START_BETTING, 2, componentFront, mContext, mAppViewModel.getFrontVolume());
                btn_results.setText(getString(R.string.number) + ":" + mAppViewModel.getSicbo01().getGameNumber());
                contentResults.setData(getResultsData());
                contentResults.notifyDataSetChanged();
                tv_sicbo_number01.setText(mAppViewModel.getSicbo01().getGameNumber());
                tv_table_game_number.setText("SB1:" + mAppViewModel.getSicbo01().getGameNumber());
            }

            if (fl_sicbo_result != null && fl_sicbo_result.getVisibility() == View.VISIBLE)
                fl_sicbo_result.setVisibility(View.GONE);

            //    pokerPopupWindow.closePopupWindow();
            //    hidePoker();


        } else if (mAppViewModel.getSicbo01().getGameStatus() == 5 && bUpdateRoad)//延迟3秒调用路子接口
        {
            bUpdateRoad = false;

            showBetPanel();
            //检查输赢
            isShowWinLose = true;
            updateWonMoney = new UpdateWonMoney();
            threadUpdateWonMoney = new Thread(updateWonMoney);
            threadUpdateWonMoney.start();

            showResultsOnUI();
            //    if(isActive)
            //      initPopResultsWindows();
            //通过发消息的方式解决Activity还没有显示的时候，弹出结果窗口出现异常的问题
            handler.sendEmptyMessageDelayed(HandlerCode.SHOW_POPUP_RESULTS_WINDOW, 0);
//            tablePop.setTablesData(afbApp, games);
            if (fl_vedio_location_parent != null && fl_vedio_parent != null && fl_surface_parent != null) {
                if (isNeedBigVedio && fl_vedio_parent.getHeight() <= fl_vedio_location_parent.getHeight()) {
                    isNeedBigVedio = false;
                    openBigVedio();
                } else {
                    isNeedBigVedio = false;
                }
            }

        } else if (mAppViewModel.getSicbo01().getGameStatus() == 2) {
            if (bigBet > 0 || smallBet > 0 || oddBet > 0 || evenBet > 0 || alldiceBet > 0 || waidiceBet1 > 0 || waidiceBet2 > 0 || waidiceBet3 > 0 || waidiceBet4 > 0 || waidiceBet5 > 0
                    || waidiceBet6 > 0 || pairsBet1 > 0 || pairsBet2 > 0 || pairsBet3 > 0 || pairsBet4 > 0 || pairsBet5 > 0 || pairsBet6 > 0
                    || pointsBet4 > 0 || pointsBet5 > 0 || pointsBet6 > 0 || pointsBet7 > 0 || pointsBet8 > 0 || pointsBet9 > 0 || pointsBet10 > 0
                    || pointsBet11 > 0 || pointsBet12 > 0 || pointsBet13 > 0 || pointsBet14 > 0 || pointsBet15 > 0 || pointsBet16 > 0 || pointsBet17 > 0
                    || ninewayBet12 > 0 || ninewayBet13 > 0 || ninewayBet14 > 0 || ninewayBet15 > 0 || ninewayBet16 > 0 || ninewayBet23 > 0 || ninewayBet24 > 0 || ninewayBet25 > 0
                    || ninewayBet26 > 0 || ninewayBet34 > 0 || ninewayBet35 > 0 || ninewayBet36 > 0 || ninewayBet45 > 0 || ninewayBet46 > 0 || ninewayBet56 > 0 || threeBet1 > 0
                    || threeBet2 > 0 || threeBet3 > 0 || threeBet4 > 0 || threeBet5 > 0 || threeBet6 > 0) {
                Log.i(WebSiteUrl.Tag, "clearNoBetChip()");
                clearBetChip(SbBetType.All);
            }
            if (tvTableBetSure.isEnabled()) {
                tvTableBetSure.setEnabled(false);
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_NOMOREBETS, 3, componentFront, mContext, mAppViewModel.getFrontVolume());
            }
            //   showPopBottom(lv_table_pool);
         /*   if(isActive && pokerPopupWindow.isShowing() == false )
                pokerPopupWindow.showPopupGravityWindow(Gravity.BOTTOM, 0, 0);

            showPoker();*/

            //需要隐藏下注区域，看到视频里出结果
//            hideBetPanel();
            if (fl_vedio_location_parent != null && fl_vedio_parent != null && fl_surface_parent != null) {
                if (isNeedBigVedio && fl_vedio_parent.getHeight() <= fl_vedio_location_parent.getHeight()) {
                    isNeedBigVedio = false;
                    openBigVedio();
                } else {
                    isNeedBigVedio = false;
                }
            }

        }
     /*   if (!gameNumber.equals(mAppViewModel.getSicbo01().getGameNumber())) {
            clearAllChips();
        }*/

    }

    public void updateInterface() {
        if (sicboTimer > 0 && mAppViewModel.getSicbo01().getGameStatus() != 2) {
            if (animationDrawableBig.isRunning() || animationDrawableSmall.isRunning() || animationDrawableOdd.isRunning() || animationDrawableEven.isRunning()) {
                for (int i = 0; i < animationDrawableList.size(); i++) {
                    AnimationDrawable animationDrawable = animationDrawableList.get(i);
                    if (animationDrawable != null) {
                        if (animationDrawable.isRunning()) {
                            animationDrawable.stop();
                            animationDrawable.selectDrawable(0);
                        }
                    }
                }
            }
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                int heightPixels = WidgetUtil.getScreenHeight(this);
                int[] location2 = new int[2];
                ll_chip.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                if (location2[1] >= heightPixels && isCanShowChip) {
                    isCanShowChip = false;
                    WidgetUtil.chipTranslateAnimation(ll_chip, 0, ScreenUtil.dip2px(mContext, -44), new Animator.AnimatorListener() {
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
            } else {
                int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(this);
                int[] location2 = new int[2];
                ll_chip.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                if (location2[0] >= portraitScreenWidth && isCanShowChip) {
                    isCanShowChip = false;
                    WidgetUtil.chipPortraitTranslateAnimation(ll_chip, portraitScreenWidth, 0, new Animator.AnimatorListener() {
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
            sicboTimer--;
            tv_table_timer.setText("" + sicboTimer);
//            if (sicboTimer == 10) {
//                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_TIMER, 0, componentFront, mContext, mAppViewModel.getFrontVolume());
//            }
            if (sicboTimer < 6) {
                mAppViewModel.startFrontMuzicService("TIMER", 1, componentFront, mContext, mAppViewModel.getFrontVolume());
                tv_table_timer.setTextColor(getResources().getColor(R.color.red));
            } else
                tv_table_timer.setTextColor(getResources().getColor(R.color.white));
            if (showRoad) {
                tv_sicbo_number01.setText("" + mAppViewModel.getSicbo01().getGameNumber());
                tv_table_game_number.setText("SB1:" + mAppViewModel.getSicbo01().getGameNumber());
            }

        } else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                int heightPixels = WidgetUtil.getScreenHeight(this);
                int[] location2 = new int[2];
                ll_chip.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                if (location2[1] < heightPixels && isCanHideChip) {
                    isCanHideChip = false;
                    WidgetUtil.chipTranslateAnimation(ll_chip, ScreenUtil.dip2px(mContext, -44), 0, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideChip = true;
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
            } else {
                int portraitScreenWidth = WidgetUtil.getPortraitScreenWidth(this);
                int[] location2 = new int[2];
                ll_chip.getLocationOnScreen(location2);//获取在整个屏幕内的绝对坐标
                if (location2[0] == 0 && isCanHideChip) {
                    isCanHideChip = false;
                    WidgetUtil.chipPortraitTranslateAnimation(ll_chip, 0, portraitScreenWidth, new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isCanHideChip = true;
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
//            ll_bet_btn_parent.setVisibility(View.GONE);
            sicboTimer = 0;
            tv_table_timer.setText("" + sicboTimer);
            if (mAppViewModel.getSicbo01().getGameStatus() == 2) {

            }
//            if (stateInit)
//                displayAll(false);
//            stateInit = false;
        }
        String serverTimer = mAppViewModel.getSicbo01().getServerTime();
        String time = "";
        if (serverTimer != null && serverTimer.indexOf("-") > 0)
            time = "GMT+7  " + serverTimer.substring(serverTimer.indexOf("-") + 1, serverTimer.length());
//        rightTv.setText(time + "\n" + usName);
        tv_time.setText(time);
        tv_time.setVisibility(View.GONE);
        setToolbarAndSet(time, usName);
        updateTablePool();
        updateInfo();

        //  if(showRoad)
        mAppViewModel.updateRoad(mAppViewModel.getSicbo01(), sicbo_bigsmall_road, sicbo_evenodd_road, tv_sicbo_number01, tv_even01, tv_small01, tv_waidic01, tv_big01, tv_odd01, mContext, density);

    }

    @Override
    protected void leftClick() {
        mAppViewModel.setbLobby(true);
        mAppViewModel.getSicbo01().setRoadOld("");
        skipAct(LobbyActivity.class);
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

//            updateRoad = new UpdateRoad();
//            threadUpdateRoad = new Thread(updateRoad);
//            threadUpdateRoad.start();

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

    public void initUI() {
        sicboTimer = 0;
        mAppViewModel.getSicbo01().setTimer(0);
        gameNumber = "0";
        tv_table_timer.setText("0");
//        bBetSucess = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAutoSize();
//        initUI();
        if (mAppViewModel.isMusicOpen()) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAppViewModel.startBackgroudMuzicService(mAppViewModel.getMuzicIndex(), componentBack, mContext, mAppViewModel.getBackgroudVolume());
                }
            }, 1000);
        }
//        startUpdateStatusThread();
        videoHelper.playVideo();

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        checkSlideHint(tv_table_timer);
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

    public void initAutoSize() {
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AutoLayoutConifg.getInstance().setSize(this);
            AutoLayoutConifg.getInstance().setmDesignWidth(880);
            AutoLayoutConifg.getInstance().setmDesignHeight(450);
        } else {
            AutoLayoutConifg.getInstance().setSize(this);
            AutoLayoutConifg.getInstance().setmDesignWidth(450);
            AutoLayoutConifg.getInstance().setmDesignHeight(880);
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

    @Override
    protected void initData(Bundle savedInstanceState) {
        mAppViewModel.getSicbo01().setResult("");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            toolbar.setNavigationIcon(R.mipmap.roulette_p_back);
            toolbar.setBackgroundResource(R.mipmap.roulette_p_title);
            rightTableTv.setVisibility(View.VISIBLE);
            imgBack.setBackgroundResource(R.mipmap.gd_back_black);
        } else {
            toolbar.setNavigationIcon(null);
            toolbar.setBackgroundResource(R.color.transparent);
            rouletteNumberTv.setVisibility(View.VISIBLE);
            changeBetUiTv.setVisibility(View.VISIBLE);
            imgBack.setBackgroundResource(R.mipmap.gd_back_black);
        }
        rightBetTv.setVisibility(View.VISIBLE);
        rightWinLoseTv.setVisibility(View.VISIBLE);
        rightBalanceTv.setVisibility(View.VISIBLE);
        limitPop = getIntent().getStringExtra("limit");
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftClick();
            }
        });
        initControl();
        showBetPanel();

        serviceTime.setText(mAppViewModel.getUser().getBalance() + "");
        rightTv.setTextColor(getResources().getColor(R.color.white));
        setTablePool(lv_pool);
        setInfoData(lv_user_info);
        mPreview = findViewById(R.id.gd__surface);
        setPlayVideo();
        setTableLimit();
        initDiceList();
        setClickListener();
        setChip();
        InitButtonClick();
        mAppViewModel.getSicbo01().setRoadOld("");
        btn_results.setText(getString(R.string.number) + ":" + mAppViewModel.getSicbo01().getGameNumber());
        initArcMenu(tvMenu, "SB1", 1);
        tv_table_game_number.setText("SB1:" + mAppViewModel.getSicbo01().getGameNumber());
        initUI();
        startUpdateStatusThread();
        setPercentageData(lv_percentage);
        fl_vedio_location_parent = findViewById(R.id.gd__fl_vedio_location_parent);
        fl_vedio_parent = findViewById(R.id.gd__fl_vedio_parent);
        fl_surface_parent = findViewById(R.id.gd__fl_surface_parent);

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

        if (fl_vedio_location_parent != null && fl_vedio_parent != null && fl_surface_parent != null) {
            fl_vedio_location_parent.post(new Runnable() {
                @Override
                public void run() {
                    int height = fl_vedio_location_parent.getHeight();
                    ViewGroup.LayoutParams layoutParams = fl_vedio_parent.getLayoutParams();
                    layoutParams.width = fl_vedio_location_parent.getWidth();
                    layoutParams.height = height;
                    fl_vedio_parent.setLayoutParams(layoutParams);
                    fl_vedio_parent.setVisibility(View.VISIBLE);
                }
            });
            fl_vedio_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (fl_vedio_parent.getHeight() <= fl_vedio_location_parent.getHeight()) {
                        openBigVedio();
                    } else {
                        closeBigVedio();
                    }
                }
            });
        }
    }

    private void closeBigVedio() {
        if (isCanClickVedio) {
            isCanClickVedio = false;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fl_vedio_parent.getLayoutParams();
            layoutParams.width = fl_vedio_location_parent.getWidth();
            layoutParams.height = fl_vedio_location_parent.getHeight();
            fl_vedio_parent.setLayoutParams(layoutParams);
            FrameLayout.LayoutParams mPreviewLayoutParams = (FrameLayout.LayoutParams) fl_surface_parent.getLayoutParams();
            mPreviewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mPreviewLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mPreviewLayoutParams.topMargin = 0;
            fl_surface_parent.setLayoutParams(mPreviewLayoutParams);
            fl_vedio_parent.setBackgroundResource(0);
            isCanClickVedio = true;
        }
    }

    private void openBigVedio() {
        if (isCanClickVedio) {
            isCanClickVedio = false;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fl_vedio_parent.getLayoutParams();
                    FrameLayout.LayoutParams mPreviewLayoutParams = (FrameLayout.LayoutParams) fl_surface_parent.getLayoutParams();
                    mPreviewLayoutParams.width = (int) (layoutParams.width * 2.8);
                    mPreviewLayoutParams.height = (int) (layoutParams.height * 2.8);
                    mPreviewLayoutParams.topMargin = ((int) (AutoUtils.getPercentHeight1px() * 40));
                    fl_surface_parent.setLayoutParams(mPreviewLayoutParams);
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    fl_vedio_parent.setLayoutParams(layoutParams);
                    fl_vedio_parent.setBackgroundResource(R.drawable.gd_rectangle_trans_stroke_roulette_black);
                    isCanClickVedio = true;
                }
            }, 500);
        }
    }

    private void setPlayVideo() {
        videoHelper = new VideoHelper(mContext, mPreview) {
            @Override
            public void doVideoFix() {
                if (fl_baccarat_bg != null)
                    fl_baccarat_bg.setVisibility(View.GONE);
            }
        };

        String path = mAppViewModel.getUser().getVideoUrl() + "/" + mAppViewModel.getSicbo01().getVideoUrlIndex() + "/DX";
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            path = mAppViewModel.getUser().getVideoUrl() + "/" + "live/DXshu";
        }
        videoHelper.setPlayUrl(path);
    }

    public void initControl() {

        serviceTime.setTextSize(15);
        density = ScreenUtil.getDisplayMetrics(mContext).density;
        tv_sicbo_number01 = (TextView) findViewById(R.id.gd__text_shoe_game_number);
        tv_table_game_number = (TextView) findViewById(R.id.gd__tv_table_game_number);
        tv_even01 = (TextView) findViewById(R.id.gd__text_even);
        tv_odd01 = (TextView) findViewById(R.id.gd__text_odd);
        tv_big01 = (TextView) findViewById(R.id.gd__text_big);
        tv_small01 = (TextView) findViewById(R.id.gd__text_small);
        tv_waidic01 = (TextView) findViewById(R.id.gd__text_waidic);
        btn_results = (TextView) findViewById(R.id.gd__btn_results);
        HorizontalScrollView hv_bigsmall = (HorizontalScrollView) findViewById(R.id.gd__hv_bigsmall);
        HorizontalScrollView hv_evenodd = (HorizontalScrollView) findViewById(R.id.gd__hv_evenodd);

        sicbo_bigsmall_road = (GridLayout) hv_bigsmall.findViewById(R.id.gd__sicbo_gridlayout1);
        sicbo_evenodd_road = (GridLayout) hv_evenodd.findViewById(R.id.gd__sicbo_gridlayout1);
        animationDrawableList.clear();
//        ivSicboBigBg.setBackgroundResource(R.drawable.gd_table_sicbo_big_trans_black_selector);
        animationDrawableBig = (AnimationDrawable) ivSicboBigBg.getBackground();
        animationDrawableList.add(animationDrawableBig);
//        ivSicboSmallBg.setBackgroundResource(R.drawable.gd_table_sicbo_small_trans_black_selector);
        animationDrawableSmall = (AnimationDrawable) ivSicboSmallBg.getBackground();
        animationDrawableList.add(animationDrawableSmall);
//        ivSicboOddBg.setBackgroundResource(R.drawable.gd_table_sicbo_odd_trans_black_selector);
        animationDrawableOdd = (AnimationDrawable) ivSicboOddBg.getBackground();
        animationDrawableList.add(animationDrawableOdd);
//        ivSicboEvenBg.setBackgroundResource(R.drawable.gd_table_sicbo_even_trans_black_selector);
        animationDrawableEven = (AnimationDrawable) ivSicboEvenBg.getBackground();
        animationDrawableList.add(animationDrawableEven);
//        ivSicboThreeForcesBg.setBackgroundResource(R.drawable.gd_table_sicbo_t_trans_black_selector);
        animationDrawableAllDices = (AnimationDrawable) ivSicboThreeForcesBg.getBackground();
        animationDrawableList.add(animationDrawableAllDices);
//        ivSicboPairs1Bg.setBackgroundResource(R.drawable.gd_table_sicbo_pair1_trans_black_selector);
        animationDrawablePair1 = (AnimationDrawable) ivSicboPairs1Bg.getBackground();
        animationDrawableList.add(animationDrawablePair1);
//        ivSicboPairs2Bg.setBackgroundResource(R.drawable.gd_table_sicbo_pair2_trans_black_selector);
        animationDrawablePair2 = (AnimationDrawable) ivSicboPairs2Bg.getBackground();
        animationDrawableList.add(animationDrawablePair2);
//        ivSicboPairs3Bg.setBackgroundResource(R.drawable.gd_table_sicbo_pair3_trans_black_selector);
        animationDrawablePair3 = (AnimationDrawable) ivSicboPairs3Bg.getBackground();
        animationDrawableList.add(animationDrawablePair3);
//        ivSicboPairs4Bg.setBackgroundResource(R.drawable.gd_table_sicbo_pair4_trans_black_selector);
        animationDrawablePair4 = (AnimationDrawable) ivSicboPairs4Bg.getBackground();
        animationDrawableList.add(animationDrawablePair4);
//        ivSicboPairs5Bg.setBackgroundResource(R.drawable.gd_table_sicbo_pair5_trans_black_selector);
        animationDrawablePair5 = (AnimationDrawable) ivSicboPairs5Bg.getBackground();
        animationDrawableList.add(animationDrawablePair5);
//        ivSicboPairs6Bg.setBackgroundResource(R.drawable.gd_table_sicbo_pair6_trans_black_selector);
        animationDrawablePair6 = (AnimationDrawable) ivSicboPairs6Bg.getBackground();
        animationDrawableList.add(animationDrawablePair6);
//        ivSicboDices1Bg.setBackgroundResource(R.drawable.gd_table_sicbo_t1_trans_black_selector);
        animationDrawableWaidice1 = (AnimationDrawable) ivSicboDices1Bg.getBackground();
        animationDrawableList.add(animationDrawableWaidice1);
//        ivSicboDices2Bg.setBackgroundResource(R.drawable.gd_table_sicbo_t2_trans_black_selector);
        animationDrawableWaidice2 = (AnimationDrawable) ivSicboDices2Bg.getBackground();
        animationDrawableList.add(animationDrawableWaidice2);
//        ivSicboDices3Bg.setBackgroundResource(R.drawable.gd_table_sicbo_t3_trans_black_selector);
        animationDrawableWaidice3 = (AnimationDrawable) ivSicboDices3Bg.getBackground();
        animationDrawableList.add(animationDrawableWaidice3);
//        ivSicboDices4Bg.setBackgroundResource(R.drawable.gd_table_sicbo_t4_trans_black_selector);
        animationDrawableWaidice4 = (AnimationDrawable) ivSicboDices4Bg.getBackground();
        animationDrawableList.add(animationDrawableWaidice4);
//        ivSicboDices5Bg.setBackgroundResource(R.drawable.gd_table_sicbo_t5_trans_black_selector);
        animationDrawableWaidice5 = (AnimationDrawable) ivSicboDices5Bg.getBackground();
        animationDrawableList.add(animationDrawableWaidice5);
//        ivSicboDices6Bg.setBackgroundResource(R.drawable.gd_table_sicbo_t6_trans_black_selector);
        animationDrawableWaidice6 = (AnimationDrawable) ivSicboDices6Bg.getBackground();
        animationDrawableList.add(animationDrawableWaidice6);
//        ivSicboSingle1Bg.setBackgroundResource(R.drawable.gd_table_sicbo_s1_trans_black_selector);
        animationDrawableThree1 = (AnimationDrawable) ivSicboSingle1Bg.getBackground();
        animationDrawableList.add(animationDrawableThree1);
//        ivSicboSingle2Bg.setBackgroundResource(R.drawable.gd_table_sicbo_s2_trans_black_selector);
        animationDrawableThree2 = (AnimationDrawable) ivSicboSingle2Bg.getBackground();
        animationDrawableList.add(animationDrawableThree2);
//        ivSicboSingle3Bg.setBackgroundResource(R.drawable.gd_table_sicbo_s3_trans_black_selector);
        animationDrawableThree3 = (AnimationDrawable) ivSicboSingle3Bg.getBackground();
        animationDrawableList.add(animationDrawableThree3);
//        ivSicboSingle4Bg.setBackgroundResource(R.drawable.gd_table_sicbo_s4_trans_black_selector);
        animationDrawableThree4 = (AnimationDrawable) ivSicboSingle4Bg.getBackground();
        animationDrawableList.add(animationDrawableThree4);
//        ivSicboSingle5Bg.setBackgroundResource(R.drawable.gd_table_sicbo_s5_trans_black_selector);
        animationDrawableThree5 = (AnimationDrawable) ivSicboSingle5Bg.getBackground();
        animationDrawableList.add(animationDrawableThree5);
//        ivSicboSingle6Bg.setBackgroundResource(R.drawable.gd_table_sicbo_s6_trans_black_selector);
        animationDrawableThree6 = (AnimationDrawable) ivSicboSingle6Bg.getBackground();
        animationDrawableList.add(animationDrawableThree6);
//        ivSicboPoints4Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p4_trans_black_selector);
        animationDrawablePoint4 = (AnimationDrawable) ivSicboPoints4Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint4);
//        ivSicboPoints5Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p5_trans_black_selector);
        animationDrawablePoint5 = (AnimationDrawable) ivSicboPoints5Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint5);
//        ivSicboPoints6Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p6_trans_black_selector);
        animationDrawablePoint6 = (AnimationDrawable) ivSicboPoints6Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint6);
//        ivSicboPoints7Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p7_trans_black_selector);
        animationDrawablePoint7 = (AnimationDrawable) ivSicboPoints7Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint7);
//        ivSicboPoints8Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p8_trans_black_selector);
        animationDrawablePoint8 = (AnimationDrawable) ivSicboPoints8Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint8);
//        ivSicboPoints9Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p9_trans_black_selector);
        animationDrawablePoint9 = (AnimationDrawable) ivSicboPoints9Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint9);
//        ivSicboPoints10Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p10_trans_black_selector);
        animationDrawablePoint10 = (AnimationDrawable) ivSicboPoints10Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint10);
//        ivSicboPoints11Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p11_trans_black_selector);
        animationDrawablePoint11 = (AnimationDrawable) ivSicboPoints11Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint11);
//        ivSicboPoints12Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p12_trans_black_selector);
        animationDrawablePoint12 = (AnimationDrawable) ivSicboPoints12Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint12);
//        ivSicboPoints13Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p13_trans_black_selector);
        animationDrawablePoint13 = (AnimationDrawable) ivSicboPoints13Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint13);
//        ivSicboPoints14Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p14_trans_black_selector);
        animationDrawablePoint14 = (AnimationDrawable) ivSicboPoints14Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint14);
//        ivSicboPoints15Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p15_trans_black_selector);
        animationDrawablePoint15 = (AnimationDrawable) ivSicboPoints15Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint15);
//        ivSicboPoints16Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p16_trans_black_selector);
        animationDrawablePoint16 = (AnimationDrawable) ivSicboPoints16Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint16);
//        ivSicboPoints17Bg.setBackgroundResource(R.drawable.gd_table_sicbo_p17_trans_black_selector);
        animationDrawablePoint17 = (AnimationDrawable) ivSicboPoints17Bg.getBackground();
        animationDrawableList.add(animationDrawablePoint17);
//        ivSicboCombination12Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c12_trans_black_selector);
        animationDrawableNineway12 = (AnimationDrawable) ivSicboCombination12Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway12);
//        ivSicboCombination13Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c13_trans_black_selector);
        animationDrawableNineway13 = (AnimationDrawable) ivSicboCombination13Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway13);
//        ivSicboCombination14Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c14_trans_black_selector);
        animationDrawableNineway14 = (AnimationDrawable) ivSicboCombination14Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway14);
//        ivSicboCombination15Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c15_trans_black_selector);
        animationDrawableNineway15 = (AnimationDrawable) ivSicboCombination15Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway15);
//        ivSicboCombination16Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c16_trans_black_selector);
        animationDrawableNineway16 = (AnimationDrawable) ivSicboCombination16Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway16);
//        ivSicboCombination23Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c23_trans_black_selector);
        animationDrawableNineway23 = (AnimationDrawable) ivSicboCombination23Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway23);
//        ivSicboCombination24Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c24_trans_black_selector);
        animationDrawableNineway24 = (AnimationDrawable) ivSicboCombination24Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway24);
//        ivSicboCombination25Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c25_trans_black_selector);
        animationDrawableNineway25 = (AnimationDrawable) ivSicboCombination25Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway25);
//        ivSicboCombination26Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c26_trans_black_selector);
        animationDrawableNineway26 = (AnimationDrawable) ivSicboCombination26Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway26);
//        ivSicboCombination34Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c34_trans_black_selector);
        animationDrawableNineway34 = (AnimationDrawable) ivSicboCombination34Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway34);
//        ivSicboCombination35Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c34_trans_black_selector);
        animationDrawableNineway35 = (AnimationDrawable) ivSicboCombination35Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway35);
//        ivSicboCombination36Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c36_trans_black_selector);
        animationDrawableNineway36 = (AnimationDrawable) ivSicboCombination36Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway36);
//        ivSicboCombination45Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c45_trans_black_selector);
        animationDrawableNineway45 = (AnimationDrawable) ivSicboCombination45Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway45);
//        ivSicboCombination46Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c46_trans_black_selector);
        animationDrawableNineway46 = (AnimationDrawable) ivSicboCombination46Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway46);
//        ivSicboCombination56Bg.setBackgroundResource(R.drawable.gd_table_sicbo_c56_trans_black_selector);
        animationDrawableNineway56 = (AnimationDrawable) ivSicboCombination56Bg.getBackground();
        animationDrawableList.add(animationDrawableNineway56);
        llCenter.setVisibility(View.VISIBLE);

    }

    //结果路子
    private void initDiceList() {
        contentResults = new AdapterViewContent<>(mContext, lv_table_results);
        contentResults.setBaseAdapter(new QuickAdapterImp<DiceContentBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_bet_dice_info;
            }

            @Override
            public void convert(ViewHolder helper, DiceContentBean item, int position) {
                helper.setImageResource(R.id.gd__iv_dice_1, item.getList().get(0).getResDrawable());
                helper.setImageResource(R.id.gd__iv_dice_2, item.getList().get(1).getResDrawable());
                helper.setImageResource(R.id.gd__iv_dice_3, item.getList().get(2).getResDrawable());
                helper.setText(R.id.gd__iv_dice_tv, "" + item.getPoint());
                TextView iv_dice_bs_tv = helper.retrieveView(R.id.gd__iv_dice_bs_tv);
                TextView iv_dice_oe_tv = helper.retrieveView(R.id.gd__iv_dice_oe_tv);
                if (item.getPoint() > 10) {
                    iv_dice_bs_tv.setBackgroundResource(R.drawable.rectangle_sicbo_blue);
                    iv_dice_bs_tv.setText(getString(R.string.gd_B));
                } else {
                    iv_dice_bs_tv.setText(getString(R.string.gd_S));
                    iv_dice_bs_tv.setBackgroundResource(R.drawable.rectangle_sicbo_red);
                }
                if (item.getPoint() % 2 == 0) {
                    iv_dice_oe_tv.setBackgroundResource(R.drawable.rectangle_sicbo_red);
                    iv_dice_oe_tv.setText(getString(R.string.gd_E));
                } else {
                    iv_dice_oe_tv.setBackgroundResource(R.drawable.rectangle_sicbo_blue);
                    iv_dice_oe_tv.setText(getString(R.string.gd_O));
                }
            }
        });
        //     List<DiceContentBean> list= Collections.nCopies(8,new DiceContentBean());
        if (getResultsData() != null)
            contentResults.setData(getResultsData());
    }

    public int getMipmap(int res) {
        int resMipmap = 0;
        switch (res) {
            case 1:
                resMipmap = R.mipmap.gd_dice1;
                break;
            case 2:
                resMipmap = R.mipmap.gd_dice2;
                break;
            case 3:
                resMipmap = R.mipmap.gd_dice3;
                break;
            case 4:
                resMipmap = R.mipmap.gd_dice4;
                break;
            case 5:
                resMipmap = R.mipmap.gd_dice5;
                break;
            case 6:
                resMipmap = R.mipmap.gd_dice6;
                break;
        }
        return resMipmap;
    }

    public List<DiceContentBean> getResultsData() {
        List<DiceContentBean> list = new ArrayList<DiceContentBean>();
        //得到最近15局的结果
        try {
            String luziInfo[] = mAppViewModel.getSicbo01().getRoad().split("\\#");
            if (luziInfo.length <= 0 && luziInfo.length > 100) {//数据格式不对
                return null;
            }
            int point = 0;
            for (int i = 99; i > 84; i--) {
                DiceContentBean diceContentBean = new DiceContentBean();
                DiceBean diceBean1 = new DiceBean();
                DiceBean diceBean2 = new DiceBean();
                DiceBean diceBean3 = new DiceBean();
                String luziInfoDetail[] = luziInfo[i].split("\\-");//路子相信信息9-8-7
                point = Integer.parseInt(luziInfoDetail[0]) + Integer.parseInt(luziInfoDetail[1]) + Integer.parseInt(luziInfoDetail[2]);
                diceContentBean.setPoint(point);
                diceBean1.setResDrawable(getMipmap(Integer.parseInt(luziInfoDetail[0])));

                diceBean2.setResDrawable(getMipmap(Integer.parseInt(luziInfoDetail[1])));

                diceBean3.setResDrawable(getMipmap(Integer.parseInt(luziInfoDetail[2])));
                diceContentBean.getList().add(diceBean1);
                diceContentBean.getList().add(diceBean2);
                diceContentBean.getList().add(diceBean3);
                list.add(diceContentBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
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

    public void setClickListener() {
        fl_baccarat_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentClick();
            }
        });

        //路子
        leftPanel1.setOnPanelListener(new Panel.OnPanelListener() {
            @Override
            public void onPanelClosed(Panel panel) {
                showRoad = false;
//                btn_results.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPanelOpened(Panel panel) {
                showRoad = true;
                //  mAppViewModel.getSicbo01().setRoadOld("");
//                btn_results.setVisibility(View.GONE);
            }
        });
//        leftPanel1.setInterpolator(new BounceInterpolator(EasingType.Type.OUT));
    }

    public void parentClick() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (imgBack.getVisibility() == View.VISIBLE) {
                displayAll(false);
            } else {
                displayAll(true);
            }
        } else {
            showHideUserInfo();
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

    public void setChip() {
        final AdapterViewContent<ChipBean> chips = new AdapterViewContent<>(mContext, lv_baccarat_chips);
        chips.setBaseAdapter(new QuickAdapterImp<ChipBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_image_chip;
            }

            @Override
            public void convert(final ViewHolder helper, ChipBean item, final int position) {
                final LinearLayout llParent = helper.retrieveView(R.id.gd__ll_chip_parent);
                ImageView imgChip = helper.retrieveView(R.id.gd__iv_chip_pic);
                int w = 48;
                int h = 48;
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if (position == 0) {
                        llParent.post(new Runnable() {
                            @Override
                            public void run() {
                                int width = ScreenUtil.dip2px(mContext, 45) * 10;
                                int screenWidth = WidgetUtil.getScreenWidth(SicboActivity.this);
                                int padding = (screenWidth - width) / 2;
                                if (padding > 0) {
                                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) ll_chip.getLayoutParams();
                                    layoutParams1.leftMargin = padding;
                                    ll_chip.setLayoutParams(layoutParams1);
                                }
                            }
                        });
                    }
                    w = 39;
                    h = 39;
                    if (position == 7 || position == 9) {
                        w = 60;
                        h = 30;
                    }
                    int m = 3;
                    LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) llParent.getLayoutParams();
                    layoutParams1.width = ScreenUtil.dip2px(mContext, w);
                    layoutParams1.height = ScreenUtil.dip2px(mContext, 39);
                    if (position == 7) {
                        layoutParams1.leftMargin = ScreenUtil.dip2px(mContext, m);
                        layoutParams1.rightMargin = ScreenUtil.dip2px(mContext, 0);
                    } else if (position == 8) {
                        layoutParams1.leftMargin = ScreenUtil.dip2px(mContext, 0);
                        layoutParams1.rightMargin = ScreenUtil.dip2px(mContext, 0);
                    } else if (position == 9) {
                        layoutParams1.leftMargin = ScreenUtil.dip2px(mContext, 0);
                        layoutParams1.rightMargin = ScreenUtil.dip2px(mContext, m);
                    } else {
                        layoutParams1.leftMargin = ScreenUtil.dip2px(mContext, m);
                        layoutParams1.rightMargin = ScreenUtil.dip2px(mContext, m);
                    }
                    llParent.setLayoutParams(layoutParams1);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                    layoutParams.width = ScreenUtil.dip2px(mContext, w);
                    layoutParams.height = ScreenUtil.dip2px(mContext, h);
                } else {
                    int screenWidth = WidgetUtil.getPortraitScreenWidth(SicboActivity.this);
                    int margin = (screenWidth / 7 - ScreenUtil.dip2px(mContext, w)) / 2;
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llParent.getLayoutParams();
                    layoutParams.width = ScreenUtil.dip2px(mContext, w);
                    layoutParams.height = ScreenUtil.dip2px(mContext, h);
                    layoutParams.leftMargin = margin;
                    layoutParams.rightMargin = margin;
                    llParent.setLayoutParams(layoutParams);
                }
                if (selectedMap.get(true) != null && position == selectedMap.get(true).intValue()) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                    layoutParams.width = (int) (layoutParams.width * 1.2);
                    layoutParams.height = (int) (layoutParams.height * 1.2);
                    imgChip.setLayoutParams(layoutParams);
                    helper.setBackgroundRes(R.id.gd__ll_chip_parent, R.drawable.gd_rectangle_trans_stroke_yellow);
                } else {
                    if (position == 7 || position == 9) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                        layoutParams.width = ScreenUtil.dip2px(mContext, w);
                        layoutParams.height = ScreenUtil.dip2px(mContext, h);
                        imgChip.setLayoutParams(layoutParams);
                        imgChip.setLayoutParams(layoutParams);
                        helper.setBackgroundRes(R.id.gd__ll_chip_parent, 0);
                    } else {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                        layoutParams.width = ScreenUtil.dip2px(mContext, w);
                        layoutParams.height = ScreenUtil.dip2px(mContext, h);
                        imgChip.setLayoutParams(layoutParams);
                        imgChip.setLayoutParams(layoutParams);
                        helper.setBackgroundRes(R.id.gd__ll_chip_parent, 0);
                    }
                }
                imgChip.setBackgroundResource(item.getDrawableRes());
                helper.setText(R.id.gd__tv_chip_amount, item.getName());
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    if (item.getValue() == -1) {
                        currentSure = imgChip;
                    } else if (item.getValue() == -2) {
                        currentCancel = imgChip;
                    }
                }
            }
        });
        chips.setItemClick(new ItemCLickImp<ChipBean>() {
            @Override
            public void itemCLick(View view, ChipBean chipBean, int position) {
                if (chipBean.getValue() > 0) {
                    chooseChip = chipBean.getValue();
                    setGameChooseChip(chooseChip);
                    selectedMap.put(true, position);
                    chips.notifyDataSetChanged();
                    initClickCount();
                    mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 8, componentFront, mContext, mAppViewModel.getFrontVolume());
                } else {
                    switch (chipBean.getValue()) {
                        case -1:
                            bet();
                            break;
                        case -2:
                            cancelBet();
                            break;
                        case -3:
                            repeatBet();
                            break;
                        case -101:
                            showChooseChip(view);
                            break;
                    }
                }
            }
        });
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            chips.setData(getCurrentChip(true));
        } else {
            chips.setData(getCurrentChip(false));
        }
    }

    public void setTableLimit() {
        AdapterViewContent<LiveInfoBean> contentList = new AdapterViewContent<>(mContext, lvTableBetLimitRed);
        contentList.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_live_info3;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.gd__tv_type);
                TextView tvValue = helper.retrieveView(R.id.gd__tv_value);
                TextView tvValue2 = helper.retrieveView(R.id.gd__tv_value2);
                tvType.setText(item.getType());
                tvValue.setText(item.getValue1());
                tvValue2.setText(item.getValue2());
                tvType.setTextColor(getResources().getColor(R.color.white));
                helper.setText(R.id.gd__tv_value, item.getValue1() + "  -  " + item.getValue2());
                tvValue.setTextColor(getResources().getColor(R.color.yellow_brown_white_word));
                tvValue2.setVisibility(View.GONE);
//                tvValue2.setTextColor(getResources().getColor(R.color.yellow_brown_white_word));
            }
        });
        List<LiveInfoBean> limit = new ArrayList<LiveInfoBean>();
        limit.add(new LiveInfoBean(getString(R.string.big_small) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet())));
        limit.add(new LiveInfoBean(getString(R.string.even_odd) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet())));
        limit.add(new LiveInfoBean(getString(R.string.threeforce) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet())));
        limit.add(new LiveInfoBean(getString(R.string.ninewaycard) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet())));
        limit.add(new LiveInfoBean(getString(R.string.pairs) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet())));
        limit.add(new LiveInfoBean(getString(R.string.wai_dice) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinWaidiceBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxWaidiceBet())));
        limit.add(new LiveInfoBean(getString(R.string.all_dice) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinAlldiceBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxAlldiceBet())));
        limit.add(new LiveInfoBean(getString(R.string.points) + "(4/17):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination1Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination1Bet())));
        limit.add(new LiveInfoBean(getString(R.string.points) + "(5/16):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination2Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination2Bet())));
        limit.add(new LiveInfoBean(getString(R.string.points) + "(6/15):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination3Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination3Bet())));
        limit.add(new LiveInfoBean(getString(R.string.points) + "(7/14):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination4Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination4Bet())));
        limit.add(new LiveInfoBean(getString(R.string.points) + "(9-12):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination5Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination5Bet())));
        contentList.setData(limit);
        btnTableLimit.setText("SB1:" + getString(R.string.LIMIT));
    }

    private void setTablePool(ListView listView) {
        if (contentPool == null)
            contentPool = new AdapterViewContent<>(mContext, listView);
        contentPool.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_user_info_roulette;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.gd__tv_name);
                TextView tvValue = helper.retrieveView(R.id.gd__tv_value);
                tvType.setText(item.getType());
                tvValue.setText(item.getValue1());
                tvType.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.gold));

            }
        });


//        contentPool.setData(getPoolData());
    }

    public List<LiveInfoBean> getPoolData() {
        List<LiveInfoBean> strData = new ArrayList<>();
        List<LiveInfoBean> strDataAll = new ArrayList<>();
        LiveInfoBean data = null;
        Log.i(WebSiteUrl.Tag, "GetSicboPool = " + mAppViewModel.getTableId());
        Log.i(WebSiteUrl.Tag, "GetSicboPool = " + mAppViewModel.getSicbo01().getSicboPool().getBigSmall());
        int minMax = 0;
        if (mAppViewModel.getSicbo01().getSicboPool().getBigSmall() > 0) {
            minMax += mAppViewModel.getSicbo01().getSicboPool().getBigSmall();
            data = new LiveInfoBean(getString(R.string.big_small) + ":", mAppViewModel.getSicbo01().getSicboPool().getBigSmall() + "", "");
        } else {
            data = new LiveInfoBean(getString(R.string.big_small) + ":", "0", "");
        }
        strData.add(data);
        if (mAppViewModel.getSicbo01().getSicboPool().getThreeforces() > 0) {
            minMax += mAppViewModel.getSicbo01().getSicboPool().getThreeforces();
            data = new LiveInfoBean(getString(R.string.threeforce) + ":", mAppViewModel.getSicbo01().getSicboPool().getThreeforces() + "", "");
        } else {
            data = new LiveInfoBean(getString(R.string.threeforce) + ":", "0", "");
        }
        strData.add(data);
        if (mAppViewModel.getSicbo01().getSicboPool().getNineway() > 0) {
            minMax += mAppViewModel.getSicbo01().getSicboPool().getNineway();
            data = new LiveInfoBean(getString(R.string.ninewaycard) + ":", mAppViewModel.getSicbo01().getSicboPool().getNineway() + "", "");
        } else {
            data = new LiveInfoBean(getString(R.string.ninewaycard) + ":", "0", "");
        }
        strData.add(data);
        if (mAppViewModel.getSicbo01().getSicboPool().getPair() > 0) {
            minMax += mAppViewModel.getSicbo01().getSicboPool().getPair();
            data = new LiveInfoBean(getString(R.string.pairs) + ":", mAppViewModel.getSicbo01().getSicboPool().getPair() + "", "");
        } else {
            data = new LiveInfoBean(getString(R.string.pairs) + ":", "0", "");
        }
        strData.add(data);
        if (mAppViewModel.getSicbo01().getSicboPool().getWaiDices() > 0) {
            minMax += mAppViewModel.getSicbo01().getSicboPool().getWaiDices();
            data = new LiveInfoBean(getString(R.string.wai_dice) + ":", mAppViewModel.getSicbo01().getSicboPool().getWaiDices() + "", "");
        } else {
            data = new LiveInfoBean(getString(R.string.wai_dice) + ":", "0", "");
        }
        strData.add(data);

        if (mAppViewModel.getSicbo01().getSicboPool().getAllDices() > 0) {
            minMax += mAppViewModel.getSicbo01().getSicboPool().getAllDices();
            data = new LiveInfoBean(getString(R.string.all_dice) + ":", mAppViewModel.getSicbo01().getSicboPool().getAllDices() + "", "");
        } else {
            data = new LiveInfoBean(getString(R.string.all_dice) + ":", "0", "");
        }
        strData.add(data);
        if (mAppViewModel.getSicbo01().getSicboPool().getCombination() > 0) {
            minMax += mAppViewModel.getSicbo01().getSicboPool().getCombination();
            data = new LiveInfoBean(getString(R.string.combination) + ":", mAppViewModel.getSicbo01().getSicboPool().getCombination() + "", "");
        } else {
            data = new LiveInfoBean(getString(R.string.combination) + ":", "0", "");
        }
        strData.add(data);
        strDataAll.add(new LiveInfoBean(getString(R.string.min_max_sicbo) + ":", minMax + "", ""));
        strDataAll.addAll(strData);
        return strDataAll;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.gd_activity_sicbo_bet_game;
    }

    @Override
    protected void onPause() {
        super.onPause();
//        stopUpdateStatusThread();
        mAppViewModel.closeMuzicService(mContext, BackgroudMuzicService.class);
        mAppViewModel.closeMuzicService(mContext, FrontMuzicService.class);
        videoHelper.stopVideo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoHelper.onDestroy();
        stopUpdateStatusThread();
    }

    public void showUpPop(View v) {
        personInfoShowAble = !personInfoShowAble;
        if (personInfoShowAble) {
            lv_table_pool.setVisibility(View.VISIBLE);
        } else {
            lv_table_pool.setVisibility(View.GONE);
        }
        /*View view = LayoutInflater.from(mContext).inflate(R.layout.gd_popupwindow_list_bet_people, null);
        ListView lv = (ListView) view.findViewById(R.id.gd__lv_people_bet_info);
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
        popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1]-popupHeight);
    */
    }

    public void clickLimitRed(View v) {

        showLimit();
    }

    public void clickLimitRed1(View v) {
        if (isFirst) {
            lvTableBetLimitRed.setVisibility(View.GONE);

        }
    }

    public void clickResults(View v) {

        limitResults = !limitResults;
        if (limitResults)
            lv_table_results.setVisibility(View.VISIBLE);
        else
            lv_table_results.setVisibility(View.GONE);
    }

    public void initBetInformation() {
        bigBet = 0;

        clickBigCount = 0;
        clickSmallCount = 0;
        clickOddCount = 0;
        clickEvenCount = 0;
        clickWaidiceCount1 = 0;
        clickWaidiceCount2 = 0;
        clickWaidiceCount3 = 0;
        clickWaidiceCount4 = 0;
        clickWaidiceCount5 = 0;
        clickWaidiceCount6 = 0;
        clickPairsCount1 = 0;
        clickPairsCount2 = 0;
        clickPairsCount3 = 0;
        clickPairsCount4 = 0;
        clickPairsCount5 = 0;
        clickPairsCount6 = 0;
        clickPointsCount4 = 0;
        clickPointsCount5 = 0;
        clickPointsCount6 = 0;
        clickPointsCount7 = 0;
        clickPointsCount8 = 0;
        clickPointsCount9 = 0;
        clickPointsCount10 = 0;
        clickPointsCount11 = 0;
        clickPointsCount12 = 0;
        clickPointsCount13 = 0;
        clickPointsCount14 = 0;
        clickPointsCount15 = 0;
        clickPointsCount16 = 0;
        clickPointsCount17 = 0;
        clickNinewayCount12 = 0;
        clickNinewayCount13 = 0;
        clickNinewayCount14 = 0;
        clickNinewayCount15 = 0;
        clickNinewayCount16 = 0;
        clickNinewayCount23 = 0;
        clickNinewayCount24 = 0;
        clickNinewayCount25 = 0;
        clickNinewayCount26 = 0;
        clickNinewayCount34 = 0;
        clickNinewayCount35 = 0;
        clickNinewayCount36 = 0;
        clickNinewayCount45 = 0;
        clickNinewayCount46 = 0;
        clickNinewayCount56 = 0;
        clickThreeCount1 = 0;
        clickThreeCount2 = 0;
        clickThreeCount3 = 0;
        clickThreeCount4 = 0;
        clickThreeCount5 = 0;
        clickThreeCount6 = 0;
        smallBet = 0;
        oddBet = 0;
        evenBet = 0;
        waidiceBet1 = 0;
        waidiceBet2 = 0;
        waidiceBet3 = 0;
        waidiceBet4 = 0;
        waidiceBet5 = 0;
        waidiceBet6 = 0;
        pairsBet1 = 0;
        pairsBet2 = 0;
        pairsBet3 = 0;
        pairsBet4 = 0;
        pairsBet5 = 0;
        pairsBet6 = 0;
        alldiceBet = 0;
        pointsBet4 = 0;
        pointsBet5 = 0;
        pointsBet6 = 0;
        pointsBet7 = 0;
        pointsBet8 = 0;
        pointsBet9 = 0;
        pointsBet10 = 0;
        pointsBet11 = 0;
        pointsBet12 = 0;
        pointsBet13 = 0;
        pointsBet14 = 0;
        pointsBet15 = 0;
        pointsBet16 = 0;
        pointsBet17 = 0;
        ninewayBet12 = 0;
        ninewayBet13 = 0;
        ninewayBet14 = 0;
        ninewayBet15 = 0;
        ninewayBet16 = 0;
        ninewayBet23 = 0;
        ninewayBet24 = 0;
        ninewayBet25 = 0;
        ninewayBet26 = 0;
        ninewayBet34 = 0;
        ninewayBet35 = 0;
        ninewayBet36 = 0;
        ninewayBet45 = 0;
        ninewayBet46 = 0;
        ninewayBet56 = 0;
        threeBet1 = 0;
        threeBet2 = 0;
        threeBet3 = 0;
        threeBet4 = 0;
        threeBet5 = 0;
        threeBet6 = 0;

    }

    public void clearAllChips() {
        try {
            int sure = R.mipmap.gd_sureimg;
            if (currentSure != null && sure != 0) {
                currentSure.setBackgroundResource(sure);
            }
            int no = R.mipmap.gd_noimg;
            if (currentCancel != null && no != 0) {
                currentCancel.setBackgroundResource(no);
            }
            showBetChipOld(flSicboBigF1, false, 0);
            showBetChipOld(flSicboSmallF1, false, 0);
            showBetChipOld(flSicboOddF1, false, 0);
            showBetChipOld(flSicboEvenF1, false, 0);

            showBetChipOld(flSicboDices6F1, false, 0);
            showBetChipOld(flSicboDices5F1, false, 0);
            showBetChipOld(flSicboDices4F1, false, 0);
            showBetChipOld(flSicboDices3F1, false, 0);
            showBetChipOld(flSicboDices2F1, false, 0);
            showBetChipOld(flSicboDices1F1, false, 0);

            showBetChipOld(flSicboAlldice, false, 0);

            showBetChipOld(flSicboPairs1, false, 0);
            showBetChipOld(flSicboPairs2, false, 0);
            showBetChipOld(flSicboPairs3, false, 0);
            showBetChipOld(flSicboPairs4, false, 0);
            showBetChipOld(flSicboPairs5, false, 0);
            showBetChipOld(flSicboPairs6, false, 0);


            showBetChipOld(flSicboPoints17, false, 0);
            showBetChipOld(flSicboPoints16, false, 0);
            showBetChipOld(flSicboPoints15, false, 0);
            showBetChipOld(flSicboPoints14, false, 0);
            showBetChipOld(flSicboPoints13, false, 0);
            showBetChipOld(flSicboPoints12, false, 0);
            showBetChipOld(flSicboPoints11, false, 0);
            showBetChipOld(flSicboPoints10, false, 0);
            showBetChipOld(flSicboPoints9, false, 0);
            showBetChipOld(flSicboPoints8, false, 0);
            showBetChipOld(flSicboPoints7, false, 0);
            showBetChipOld(flSicboPoints6, false, 0);
            showBetChipOld(flSicboPoints5, false, 0);
            showBetChipOld(flSicboPoints4, false, 0);

            showBetChipOld(flSicboCombination56, false, 0);
            showBetChipOld(flSicboCombination46, false, 0);
            showBetChipOld(flSicboCombination45, false, 0);
            showBetChipOld(flSicboCombination36, false, 0);
            showBetChipOld(flSicboCombination35, false, 0);
            showBetChipOld(flSicboCombination34, false, 0);
            showBetChipOld(flSicboCombination26, false, 0);
            showBetChipOld(flSicboCombination25, false, 0);
            showBetChipOld(flSicboCombination24, false, 0);
            showBetChipOld(flSicboCombination23, false, 0);
            showBetChipOld(flSicboCombination16, false, 0);
            showBetChipOld(flSicboCombination15, false, 0);
            showBetChipOld(flSicboCombination14, false, 0);
            showBetChipOld(flSicboCombination13, false, 0);
            showBetChipOld(flSicboCombination12, false, 0);

            showBetChipOld(flSicboSingle1, false, 0);
            showBetChipOld(flSicboSingle2, false, 0);
            showBetChipOld(flSicboSingle3, false, 0);
            showBetChipOld(flSicboSingle4, false, 0);
            showBetChipOld(flSicboSingle5, false, 0);
            showBetChipOld(flSicboSingle6, false, 0);
        } catch (Exception e) {

        }


    }

    private void showBetChipOld(final FrameLayout f, boolean isShow, int money) {
        if (f == null)
            return;
        ChipShowHelper chipHelper = ChipMap.get(f);
        if (chipHelper == null)
            return;
        chipHelper.showChip(money, 0, AutoUtils.getPercentHeightSize(10), AutoUtils.getPercentHeightSize(24), AutoUtils.getPercentHeightSize(12), 0, -AutoUtils.getPercentHeightSize(10), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(15));
        chipHelper.setOperationButtonDisplay(false);
    }


 /*   public void clearBetChip() {
        clearAllChips();
        showBetChip(flSicboBigF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getBig());
        showBetChip(flSicboSmallF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getSmall());
        showBetChip(flSicboOddF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getOdd());
        showBetChip(flSicboEvenF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getEven());

        showBetChip(flSicboDices6F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("1,1,1"));
        showBetChip(flSicboDices5F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("2,2,2"));
        showBetChip(flSicboDices4F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("3,3,3"));
        showBetChip(flSicboDices3F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("4,4,4"));
        showBetChip(flSicboDices2F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("5,5,5"));
        showBetChip(flSicboDices1F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("6,6,6"));

        showBetChip(flSicboAlldice, true, mAppViewModel.getSicbo01().getSicboBetInformation().getAllDices());

        showBetChip(flSicboPairs1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("1,1"));
        showBetChip(flSicboPairs2, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("2,2"));
        showBetChip(flSicboPairs3, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("3,3"));
        showBetChip(flSicboPairs4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("4,4"));
        showBetChip(flSicboPairs5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("5,5"));
        showBetChip(flSicboPairs6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("6,6"));

        showBetChip(flSicboPoints17, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("17"));
        showBetChip(flSicboPoints16, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("16"));
        showBetChip(flSicboPoints15, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("15"));
        showBetChip(flSicboPoints14, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("14"));
        showBetChip(flSicboPoints13, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("13"));
        showBetChip(flSicboPoints12, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("12"));
        showBetChip(flSicboPoints11, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("11"));
        showBetChip(flSicboPoints10, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("10"));
        showBetChip(flSicboPoints9, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("9"));
        showBetChip(flSicboPoints8, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("8"));
        showBetChip(flSicboPoints7, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("7"));
        showBetChip(flSicboPoints6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("6"));
        showBetChip(flSicboPoints5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("5"));
        showBetChip(flSicboPoints4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("4"));

        showBetChip(flSicboCombination56, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("5,6"));
        showBetChip(flSicboCombination46, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,6"));
        showBetChip(flSicboCombination35, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,5"));
        showBetChip(flSicboCombination36, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,6"));
        showBetChip(flSicboCombination35, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,5"));
        showBetChip(flSicboCombination34, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,4"));
        showBetChip(flSicboCombination26, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,6"));
        showBetChip(flSicboCombination25, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,5"));
        showBetChip(flSicboCombination24, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,4"));
        showBetChip(flSicboCombination23, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,3"));
        showBetChip(flSicboCombination16, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,6"));
        showBetChip(flSicboCombination15, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,5"));
        showBetChip(flSicboCombination14, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,4"));
        showBetChip(flSicboCombination13, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,3"));
        showBetChip(flSicboCombination12, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,2"));

        showBetChip(flSicboSingle1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("1"));
        showBetChip(flSicboSingle2, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("2"));
        showBetChip(flSicboSingle3, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("3"));
        showBetChip(flSicboSingle4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("4"));
        showBetChip(flSicboSingle5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("5"));
        showBetChip(flSicboSingle6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("6"));
        initBetInformation();
    }*/

    private void showBetChip(final FrameLayout f, boolean isShow, int money, boolean operationDisplay) {
        if (f == null)
            return;
        ChipShowHelper chipHelper = ChipMap.get(f);

        if (chipHelper == null) {
            int iCount = f.getChildCount();
            chipHelper = new ChipShowHelper(mContext, f, chipList);
            chipHelper.setFirstIndex(iCount);
            chipHelper.setTopMargin(AutoUtils.getPercentHeightSize(5));
            chipHelper.setMoneyTipsTextSize(8);
            chipHelper.setOperationWH(35, 35);
            chipHelper.setOperationButton(0, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SbBetType type = getTypeFrom(f);
                    singleBet(type);
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SbBetType type = getTypeFrom(f);
                    clearBetChip(type);
                }
            }, null);
            ChipMap.put(f, chipHelper);
        }
        if (isShow && money > 0) {
            int sure = R.mipmap.gd_sureimg_light;
            if (currentSure != null && sure != 0) {
                currentSure.setBackgroundResource(sure);
            }
            int no = R.mipmap.gd_noimg_light;
            if (currentCancel != null && no != 0) {
                currentCancel.setBackgroundResource(no);
            }
            chipHelper.showChip(money, 0, AutoUtils.getPercentHeightSize(10), AutoUtils.getPercentHeightSize(24), AutoUtils.getPercentHeightSize(12), 0, -AutoUtils.getPercentHeightSize(10), AutoUtils.getPercentHeightSize(20), AutoUtils.getPercentHeightSize(15));
        } else {
            chipHelper.clearAllChips();
        }
        chipHelper.setOperationButtonDisplay(operationDisplay);
        showBetChipOld(f, isShow, money);

    }

    public FrameLayout getFrameLayout(SbBetType f) {
        if (SbBetType.bigBet.equals(f))
            return flSicboBigF1;


        if (SbBetType.smallBet.equals(f))
            return flSicboSmallF1;

        if (SbBetType.oddBet.equals(f))
            return flSicboOddF1;


        if (SbBetType.evenBet.equals(f))
            return flSicboEvenF1;


        if (SbBetType.alldiceBet.equals(f))
            return flSicboAlldice;

        if (SbBetType.waidiceBet1.equals(f))
            return flSicboDices1F1;


        if (SbBetType.waidiceBet2.equals(f))
            return flSicboDices2F1;


        if (SbBetType.waidiceBet3.equals(f))
            return flSicboDices3F1;


        if (SbBetType.waidiceBet4.equals(f))
            return flSicboDices4F1;


        if (SbBetType.waidiceBet5.equals(f))
            return flSicboDices5F1;


        if (SbBetType.waidiceBet6.equals(f))

            return flSicboDices6F1;

        if (SbBetType.pairsBet1.equals(f))
            return flSicboPairs1;


        if (SbBetType.pairsBet2.equals(f))
            return flSicboPairs2;


        if (SbBetType.pairsBet3.equals(f))
            return flSicboPairs3;


        if (SbBetType.pairsBet4.equals(f))
            return flSicboPairs4;


        if (SbBetType.pairsBet5.equals(f))
            return flSicboPairs5;


        if (SbBetType.pairsBet6.equals(f))

            return flSicboPairs5;


        if (SbBetType.threeBet1.equals(f))
            return flSicboSingle1;


        if (SbBetType.threeBet2.equals(f))
            return flSicboSingle2;


        if (SbBetType.threeBet3.equals(f))
            return flSicboSingle3;


        if (SbBetType.threeBet4.equals(f))
            return flSicboSingle4;


        if (SbBetType.threeBet5.equals(f))
            return flSicboSingle5;


        if (SbBetType.threeBet6.equals(f))

            return flSicboSingle6;

        if (SbBetType.ninewayBet12.equals(f))
            return flSicboCombination12;


        if (SbBetType.ninewayBet13.equals(f))
            return flSicboCombination13;


        if (SbBetType.ninewayBet14.equals(f))
            return flSicboCombination14;


        if (SbBetType.ninewayBet15.equals(f))
            return flSicboCombination15;


        if (SbBetType.ninewayBet16.equals(f))
            return flSicboCombination16;


        if (SbBetType.ninewayBet23.equals(f))
            return flSicboCombination23;


        if (SbBetType.ninewayBet24.equals(f))
            return flSicboCombination24;


        if (SbBetType.ninewayBet25.equals(f))
            return flSicboCombination25;


        if (SbBetType.ninewayBet26.equals(f))
            return flSicboCombination26;


        if (SbBetType.ninewayBet34.equals(f))
            return flSicboCombination34;


        if (SbBetType.ninewayBet35.equals(f))
            return flSicboCombination35;


        if (SbBetType.ninewayBet36.equals(f))
            return flSicboCombination36;


        if (SbBetType.ninewayBet45.equals(f))
            return flSicboCombination35;


        if (SbBetType.ninewayBet46.equals(f))
            return flSicboCombination46;


        if (SbBetType.ninewayBet56.equals(f)) {
            return flSicboCombination56;

        }
        if (SbBetType.pointsBet4.equals(f))
            return flSicboPoints4;


        if (SbBetType.pointsBet5.equals(f))
            return flSicboPoints5;


        if (SbBetType.pointsBet6.equals(f))
            return flSicboPoints6;


        if (SbBetType.pointsBet7.equals(f))
            return flSicboPoints7;


        if (SbBetType.pointsBet8.equals(f))
            return flSicboPoints8;


        if (SbBetType.pointsBet9.equals(f))
            return flSicboPoints9;


        if (SbBetType.pointsBet10.equals(f))
            return flSicboPoints10;


        if (SbBetType.pointsBet11.equals(f))
            return flSicboPoints11;


        if (SbBetType.pointsBet12.equals(f))
            return flSicboPoints12;


        if (SbBetType.pointsBet13.equals(f))
            return flSicboPoints13;


        if (SbBetType.pointsBet14.equals(f))
            return flSicboPoints14;


        if (SbBetType.pointsBet15.equals(f))
            return flSicboPoints15;


        if (SbBetType.pointsBet16.equals(f))
            return flSicboPoints16;


        if (SbBetType.pointsBet17.equals(f))
            return flSicboPoints17;

            //显示筹码
        else
            return null;
    }

    public SbBetType getTypeFrom(FrameLayout f) {
        if (flSicboBigF1.equals(f))
            return SbBetType.bigBet;


        if (flSicboSmallF1.equals(f))
            return SbBetType.smallBet;

        if (flSicboOddF1.equals(f))
            return SbBetType.oddBet;


        if (flSicboEvenF1.equals(f))
            return SbBetType.evenBet;


        if (flSicboAlldice.equals(f))
            return SbBetType.alldiceBet;

        if (flSicboDices1F1.equals(f))
            return SbBetType.waidiceBet1;


        if (flSicboDices2F1.equals(f))
            return SbBetType.waidiceBet2;


        if (flSicboDices3F1.equals(f))
            return SbBetType.waidiceBet3;


        if (flSicboDices4F1.equals(f))
            return SbBetType.waidiceBet4;


        if (flSicboDices5F1.equals(f))
            return SbBetType.waidiceBet5;


        if (flSicboDices6F1.equals(f))

            return SbBetType.waidiceBet6;

        if (flSicboPairs1.equals(f))
            return SbBetType.pairsBet1;


        if (flSicboPairs2.equals(f))
            return SbBetType.pairsBet2;


        if (flSicboPairs3.equals(f))
            return SbBetType.pairsBet3;


        if (flSicboPairs4.equals(f))
            return SbBetType.pairsBet4;


        if (flSicboPairs5.equals(f))
            return SbBetType.pairsBet5;


        if (flSicboPairs5.equals(f))

            return SbBetType.pairsBet6;


        if (flSicboSingle1.equals(f))
            return SbBetType.threeBet1;


        if (flSicboSingle2.equals(f))
            return SbBetType.threeBet2;


        if (flSicboSingle3.equals(f))
            return SbBetType.threeBet3;


        if (flSicboSingle4.equals(f))
            return SbBetType.threeBet4;


        if (flSicboSingle5.equals(f))
            return SbBetType.threeBet5;


        if (flSicboSingle6.equals(f))

            return SbBetType.threeBet6;

        if (flSicboCombination12.equals(f))
            return SbBetType.ninewayBet12;


        if (flSicboCombination13.equals(f))
            return SbBetType.ninewayBet13;


        if (flSicboCombination14.equals(f))
            return SbBetType.ninewayBet14;


        if (flSicboCombination15.equals(f))
            return SbBetType.ninewayBet15;


        if (flSicboCombination16.equals(f))
            return SbBetType.ninewayBet16;


        if (flSicboCombination23.equals(f))
            return SbBetType.ninewayBet23;


        if (flSicboCombination24.equals(f))
            return SbBetType.ninewayBet24;


        if (flSicboCombination25.equals(f))
            return SbBetType.ninewayBet25;


        if (flSicboCombination26.equals(f))
            return SbBetType.ninewayBet26;


        if (flSicboCombination34.equals(f))
            return SbBetType.ninewayBet34;


        if (flSicboCombination35.equals(f))
            return SbBetType.ninewayBet35;


        if (flSicboCombination36.equals(f))
            return SbBetType.ninewayBet36;


        if (flSicboCombination35.equals(f))
            return SbBetType.ninewayBet45;


        if (flSicboCombination46.equals(f))
            return SbBetType.ninewayBet46;


        if (flSicboCombination56.equals(f)) {
            return SbBetType.ninewayBet56;

        }
        if (flSicboPoints4.equals(f))
            return SbBetType.pointsBet4;


        if (flSicboPoints5.equals(f))
            return SbBetType.pointsBet5;


        if (flSicboPoints6.equals(f))
            return SbBetType.pointsBet6;


        if (flSicboPoints7.equals(f))
            return SbBetType.pointsBet7;


        if (flSicboPoints8.equals(f))
            return SbBetType.pointsBet8;


        if (flSicboPoints9.equals(f))
            return SbBetType.pointsBet9;


        if (flSicboPoints10.equals(f))
            return SbBetType.pointsBet10;


        if (flSicboPoints11.equals(f))
            return SbBetType.pointsBet11;


        if (flSicboPoints12.equals(f))
            return SbBetType.pointsBet12;


        if (flSicboPoints13.equals(f))
            return SbBetType.pointsBet13;


        if (flSicboPoints14.equals(f))
            return SbBetType.pointsBet14;


        if (flSicboPoints15.equals(f))
            return SbBetType.pointsBet15;


        if (flSicboPoints16.equals(f))
            return SbBetType.pointsBet16;


        if (flSicboPoints17.equals(f))
            return SbBetType.pointsBet17;

            //显示筹码
        else
            return SbBetType.All;

    }

    private void clearBetChip(SbBetType type) {
        int sure = R.mipmap.gd_sureimg;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.gd_noimg;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
        FrameLayout fl = getFrameLayout(type);
        ChipShowHelper chipHelper = ChipMap.get(fl);
        if (chipHelper != null)
            chipHelper.setOperationButtonDisplay(false);
        if (type == SbBetType.All || type == SbBetType.bigBet) {
            showBetChipOld(flSicboBigF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getBig());
            clickBigCount = 0;
            bigBet = 0;
        }
        if (type == SbBetType.All || type == SbBetType.smallBet) {
            showBetChipOld(flSicboSmallF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getSmall());
            clickSmallCount = 0;
            smallBet = 0;
        }

        if (type == SbBetType.All || type == SbBetType.oddBet) {
            showBetChipOld(flSicboOddF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getOdd());
            clickOddCount = 0;
            oddBet = 0;

        }

        if (type == SbBetType.All || type == SbBetType.evenBet) {
            showBetChipOld(flSicboEvenF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getEven());
            clickEvenCount = 0;
            evenBet = 0;


        }

        if (type == SbBetType.All || type == SbBetType.alldiceBet) {
            showBetChipOld(flSicboAlldice, true, mAppViewModel.getSicbo01().getSicboBetInformation().getAllDices());
            clickAlldiceCount = 0;
            alldiceBet = 0;

        }


        if (type == SbBetType.All || type == SbBetType.waidiceBet1) {
            showBetChipOld(flSicboDices1F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("1,1,1"));
            clickWaidiceCount1 = 0;
            waidiceBet1 = 0;


        }
        if (type == SbBetType.All || type == SbBetType.waidiceBet2) {
            showBetChipOld(flSicboDices2F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("2,2,2"));
            clickWaidiceCount2 = 0;
            waidiceBet2 = 0;


        }

        if (type == SbBetType.All || type == SbBetType.waidiceBet3) {
            showBetChipOld(flSicboDices3F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("3,3,3"));
            clickWaidiceCount3 = 0;
            waidiceBet3 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.waidiceBet4) {
            showBetChipOld(flSicboDices4F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("4,4,4"));
            clickWaidiceCount4 = 0;
            waidiceBet4 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.waidiceBet5) {
            showBetChipOld(flSicboDices5F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("5,5,5"));
            clickWaidiceCount5 = 0;
            waidiceBet5 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.waidiceBet6) {
            showBetChipOld(flSicboDices6F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("6,6,6"));
            clickWaidiceCount6 = 0;
            waidiceBet6 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pairsBet1) {
            showBetChipOld(flSicboPairs1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("1,1"));
            clickPairsCount1 = 0;
            pairsBet1 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pairsBet2) {
            showBetChipOld(flSicboPairs2, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("2,2"));
            clickPairsCount2 = 0;
            pairsBet2 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pairsBet3) {
            showBetChipOld(flSicboPairs3, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("3,3"));
            clickPairsCount3 = 0;
            pairsBet3 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pairsBet4) {
            showBetChipOld(flSicboPairs4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("4,4"));
            clickPairsCount4 = 0;
            pairsBet4 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pairsBet5) {
            showBetChipOld(flSicboPairs5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("5,5"));
            clickPairsCount5 = 0;
            pairsBet5 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pairsBet6) {
            showBetChipOld(flSicboPairs6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("6,6"));
            clickPairsCount6 = 0;
            pairsBet6 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.threeBet1) {
            showBetChipOld(flSicboSingle1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("1"));
            clickThreeCount1 = 0;
            threeBet1 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.threeBet2) {
            showBetChipOld(flSicboSingle2, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("2"));
            clickThreeCount2 = 0;
            threeBet2 = 0;


        }

        if (type == SbBetType.All || type == SbBetType.threeBet3) {
            showBetChipOld(flSicboSingle3, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("3"));
            clickThreeCount3 = 0;
            threeBet3 = 0;


        }

        if (type == SbBetType.All || type == SbBetType.threeBet4) {
            showBetChipOld(flSicboSingle4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("4"));
            clickThreeCount4 = 0;
            threeBet4 = 0;


        }

        if (type == SbBetType.All || type == SbBetType.threeBet5) {
            showBetChipOld(flSicboSingle5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("5"));
            clickThreeCount5 = 0;
            threeBet5 = 0;


        }

        if (type == SbBetType.All || type == SbBetType.threeBet6) {
            showBetChipOld(flSicboSingle6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("6"));
            clickThreeCount6 = 0;
            threeBet6 = 0;
        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet12) {
            showBetChipOld(flSicboCombination12, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,2"));
            clickNinewayCount12 = 0;
            ninewayBet12 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet13) {
            showBetChipOld(flSicboCombination13, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,3"));
            clickNinewayCount13 = 0;
            ninewayBet13 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet14) {
            showBetChipOld(flSicboCombination14, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,4"));
            clickNinewayCount14 = 0;
            ninewayBet14 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet15) {
            showBetChipOld(flSicboCombination15, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,5"));
            clickNinewayCount15 = 0;
            ninewayBet15 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet16) {
            showBetChipOld(flSicboCombination16, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,6"));
            clickNinewayCount16 = 0;
            ninewayBet16 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet23) {
            showBetChipOld(flSicboCombination23, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,3"));
            clickNinewayCount23 = 0;
            ninewayBet23 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet24) {
            showBetChipOld(flSicboCombination24, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,4"));
            clickNinewayCount24 = 0;
            ninewayBet24 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet25) {
            showBetChipOld(flSicboCombination25, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,5"));
            clickNinewayCount25 = 0;
            ninewayBet25 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet26) {
            showBetChipOld(flSicboCombination26, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,6"));
            clickNinewayCount26 = 0;
            ninewayBet26 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet34) {
            showBetChipOld(flSicboCombination34, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,4"));
            clickNinewayCount34 = 0;
            ninewayBet34 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet35) {
            showBetChipOld(flSicboCombination35, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,5"));
            clickNinewayCount35 = 0;
            ninewayBet35 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet36) {
            showBetChipOld(flSicboCombination36, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,6"));
            clickNinewayCount36 = 0;
            ninewayBet36 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet45) {
            showBetChipOld(flSicboCombination45, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,5"));
            clickNinewayCount45 = 0;
            ninewayBet45 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet46) {
            showBetChipOld(flSicboCombination46, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,6"));
            clickNinewayCount46 = 0;
            ninewayBet46 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.ninewayBet56) {
            showBetChipOld(flSicboCombination56, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("5,6"));
            clickNinewayCount56 = 0;
            ninewayBet56 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet4) {
            showBetChipOld(flSicboPoints4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("4"));
            clickPointsCount4 = 0;
            pointsBet4 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet5) {
            showBetChipOld(flSicboPoints5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("5"));
            clickPointsCount5 = 0;
            pointsBet5 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet6) {
            showBetChipOld(flSicboPoints6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("6"));
            clickPointsCount6 = 0;
            pointsBet6 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet7) {
            showBetChipOld(flSicboPoints7, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("7"));
            clickPointsCount7 = 0;
            pointsBet7 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet8) {
            showBetChipOld(flSicboPoints8, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("8"));
            clickPointsCount8 = 0;
            pointsBet8 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet9) {
            showBetChipOld(flSicboPoints9, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("9"));
            clickPointsCount9 = 0;
            pointsBet9 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet10) {
            showBetChipOld(flSicboPoints10, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("10"));
            clickPointsCount10 = 0;
            pointsBet10 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet11) {
            showBetChipOld(flSicboPoints11, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("11"));
            clickPointsCount11 = 0;
            pointsBet11 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet12) {
            showBetChipOld(flSicboPoints12, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("12"));
            clickPointsCount12 = 0;
            pointsBet12 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet13) {
            showBetChipOld(flSicboPoints13, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("13"));
            clickPointsCount13 = 0;
            pointsBet13 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet14) {
            showBetChipOld(flSicboPoints14, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("14"));
            clickPointsCount14 = 0;
            pointsBet14 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet15) {
            showBetChipOld(flSicboPoints15, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("15"));
            clickPointsCount15 = 0;
            pointsBet15 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet16) {
            showBetChipOld(flSicboPoints16, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("16"));
            clickPointsCount16 = 0;
            pointsBet16 = 0;

        }

        if (type == SbBetType.All || type == SbBetType.pointsBet17) {
            showBetChipOld(flSicboPoints17, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("17"));
            clickPointsCount17 = 0;
            pointsBet17 = 0;

        }


    }

    /**
     * 点击framelayout事件处理
     */
    public void clickDiceList(View iv) {
        isDiceVisible = !isDiceVisible;
        if (isDiceVisible) {
            lv_table_pool.setVisibility(View.VISIBLE);
        } else {
            lv_table_pool.setVisibility(View.GONE);
        }

    }

    public boolean checkChoose() {
        if (chooseChip < 1) {
            Toast.makeText(getApplicationContext(), getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /* public void clickPool(View v){

         isVisibility=!isVisibility;
         if(isVisibility)
             lv_table_pool.setVisibility(View.VISIBLE);
         else
             lv_table_pool.setVisibility(View.GONE);
     }*/
    public void clickBig(View iv) {
        if (checkChoose())
            return;
        //   ImageView img = (ImageView) iv;
        //    clickFrameLayout(flSicboBigF1,img, R.drawable.gd_table_sicbo_big_trans_black_selector);
//        ImageView img = (ImageView) iv;
//        img.setBackgroundResource(R.drawable.gd_table_sicbo_t_trans_black_selector);
//        AnimationDrawable animationDrawable = (AnimationDrawable) img.getBackground();
//        if (animationDrawable.isRunning())
//            animationDrawable.stop();
//        animationDrawable.start();


        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        if (smallBet > 0 || mAppViewModel.getSicbo01().getSicboBetInformation().getSmall() > 0) {
            Toast.makeText(mContext, R.string.show_limit_big_small, Toast.LENGTH_LONG).show();
            return;
        }

        clickBigCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet(), clickBigCount,
                mAppViewModel.getSicbo01().getSicboBetInformation().getBig(), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
//        Log.i(WebSiteUrl.Tag,"clickBig="+betMoney+",chooseChip="+chooseChip);
//        Log.i(WebSiteUrl.Tag,"clickBigMin="+mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet());
//        Log.i(WebSiteUrl.Tag,"clickBigMax="+mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet());
        if (betMoney > 0) {
            showBetChip(flSicboBigF1, true, betMoney, true);
            bigBet = betMoney;
        } else {
            showBetChip(flSicboBigF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getBig(), false);
            bigBet = 0;
            clickBigCount = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }

    }

    public void clickEven(View iv) {
        if (checkChoose())
            return;
//      ImageView img = (ImageView) iv;
//        clickFrameLayout(flSicboEvenF1,img, R.drawable.gd_table_sicbo_even_trans_black_selector);
        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        if (oddBet > 0 || mAppViewModel.getSicbo01().getSicboBetInformation().getOdd() > 0) {
            Toast.makeText(mContext, R.string.show_limit_odd_even, Toast.LENGTH_LONG).show();
            return;
        }
        clickEvenCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet(), clickEvenCount,
                mAppViewModel.getSicbo01().getSicboBetInformation().getEven(), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboEvenF1, true, betMoney, true);
            evenBet = betMoney;
        } else {
            showBetChip(flSicboEvenF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getEven(), false);
            evenBet = 0;
            clickEvenCount = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickOdd(View iv) {
        if (checkChoose())
            return;
//      ImageView img = (ImageView) iv;
//        clickFrameLayout(flSicboOddF1,img, R.drawable.gd_table_sicbo_odd_trans_black_selector);

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        if (evenBet > 0 || mAppViewModel.getSicbo01().getSicboBetInformation().getEven() > 0) {
            Toast.makeText(mContext, R.string.show_limit_odd_even, Toast.LENGTH_LONG).show();
            return;
        }

        clickOddCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet(), clickOddCount,
                mAppViewModel.getSicbo01().getSicboBetInformation().getOdd(), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboOddF1, true, betMoney, true);
            oddBet = betMoney;
        } else {
            showBetChip(flSicboOddF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getOdd(), false);
            oddBet = 0;
            clickOddCount = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickSmall(View iv) {
        if (checkChoose())
            return;
//      ImageView img = (ImageView) iv;
//        clickFrameLayout(flSicboSmallF1,img, R.drawable.gd_table_sicbo_small_trans_black_selector);

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        if (bigBet > 0 || mAppViewModel.getSicbo01().getSicboBetInformation().getBig() > 0) {
            Toast.makeText(mContext, R.string.show_limit_big_small, Toast.LENGTH_LONG).show();
            return;
        }
        clickSmallCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet(), clickSmallCount,
                mAppViewModel.getSicbo01().getSicboBetInformation().getSmall(), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboSmallF1, true, betMoney, true);
            smallBet = betMoney;
        } else {
            showBetChip(flSicboSmallF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getSmall(), false);
            smallBet = 0;
            clickSmallCount = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickTriple6(View iv) {
        if (checkChoose())
            return;
//      ImageView img = (ImageView) iv;
//        clickFrameLayout(flSicboDices6F1,img, R.drawable.gd_table_sicbo_t6_trans_black_selector);

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickWaidiceCount6++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinWaidiceBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxWaidiceBet(), clickWaidiceCount6,
                mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("6,6,6"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboDices6F1, true, betMoney, true);
            waidiceBet6 = betMoney;
        } else {
            showBetChip(flSicboDices6F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("6,6,6"), false);
            waidiceBet6 = 0;
            clickWaidiceCount6 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickTriple5(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickWaidiceCount5++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinWaidiceBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxWaidiceBet(), clickWaidiceCount5,
                mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("5,5,5"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboDices5F1, true, betMoney, true);
            waidiceBet5 = betMoney;
        } else {
            showBetChip(flSicboDices5F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("5,5,5"), false);
            waidiceBet5 = 0;
            clickWaidiceCount5 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickTriple4(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickWaidiceCount4++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinWaidiceBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxWaidiceBet(), clickWaidiceCount4,
                mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("4,4,4"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboDices4F1, true, betMoney, true);
            waidiceBet4 = betMoney;
        } else {
            showBetChip(flSicboDices4F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("4,4,4"), false);
            waidiceBet4 = 0;
            clickWaidiceCount4 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickTriple3(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickWaidiceCount3++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinWaidiceBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxWaidiceBet(), clickWaidiceCount3,
                mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("3,3,3"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboDices3F1, true, betMoney, true);
            waidiceBet3 = betMoney;
        } else {
            showBetChip(flSicboDices3F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("3,3,3"), false);
            waidiceBet3 = 0;
            clickWaidiceCount3 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickTriple2(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickWaidiceCount2++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinWaidiceBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxWaidiceBet(), clickWaidiceCount2,
                mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("2,2,2"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboDices2F1, true, betMoney, true);
            waidiceBet2 = betMoney;
        } else {
            showBetChip(flSicboDices2F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("2,2,2"), false);
            waidiceBet2 = 0;
            clickWaidiceCount2 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickTriple1(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickWaidiceCount1++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinWaidiceBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxWaidiceBet(), clickWaidiceCount1,
                mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("1,1,1"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboDices1F1, true, betMoney, true);
            waidiceBet1 = betMoney;
        } else {
            showBetChip(flSicboDices1F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("1,1,1"), false);
            waidiceBet1 = 0;
            clickWaidiceCount1 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickTriple(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;

        clickAlldiceCount++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinAlldiceBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxAlldiceBet(), clickAlldiceCount,
                mAppViewModel.getSicbo01().getSicboBetInformation().getAllDices(), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboAlldice, true, betMoney, true);
            alldiceBet = betMoney;
        } else {
            showBetChip(flSicboAlldice, true, mAppViewModel.getSicbo01().getSicboBetInformation().getAllDices(), false);
            alldiceBet = 0;
            clickAlldiceCount = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPairs1(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
//        ImageView img = (ImageView) iv;
//        clickFrameLayout(flSicboPairs1, img, R.drawable.gd_table_sicbo_pair1_trans_black_selector);
        clickPairsCount1++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet(), clickPairsCount1,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("1,1"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPairs1, true, betMoney, true);
            pairsBet1 = betMoney;
        } else {
            showBetChip(flSicboPairs1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("1,1"), false);
            pairsBet1 = 0;
            clickPairsCount1 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPairs2(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPairsCount2++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet(), clickPairsCount2,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("2,2"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPairs2, true, betMoney, true);
            pairsBet2 = betMoney;
        } else {
            showBetChip(flSicboPairs2, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("2,2"), false);
            pairsBet2 = 0;
            clickPairsCount2 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPairs3(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPairsCount3++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet(), clickPairsCount3,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("3,3"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPairs3, true, betMoney, true);
            pairsBet3 = betMoney;
        } else {
            showBetChip(flSicboPairs3, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("3,3"), false);
            pairsBet3 = 0;
            clickPairsCount3 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPairs4(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPairsCount4++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet(), clickPairsCount4,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("4,4"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPairs4, true, betMoney, true);
            pairsBet4 = betMoney;
        } else {
            showBetChip(flSicboPairs4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("4,4"), false);
            pairsBet4 = 0;
            clickPairsCount4 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPairs5(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPairsCount5++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet(), clickPairsCount5,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("5,5"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPairs5, true, betMoney, true);
            pairsBet5 = betMoney;
        } else {
            showBetChip(flSicboPairs5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("5,5"), false);
            pairsBet5 = 0;
            clickPairsCount5 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPairs6(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPairsCount6++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet(), clickPairsCount6,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("6,6"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPairs6, true, betMoney, true);
            pairsBet6 = betMoney;
        } else {
            showBetChip(flSicboPairs6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("6,6"), false);
            pairsBet6 = 0;
            clickPairsCount6 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint17(View iv) {
        if (checkChoose())
            return;
//      ImageView img = (ImageView) iv;
//        clickFrameLayout(flSicboPoints17, img, R.drawable.gd_table_sicbo_p17_trans_black_selector);

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount17++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination1Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination1Bet(), clickPointsCount17,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("17"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints17, true, betMoney, true);
            pointsBet17 = betMoney;
        } else {
            showBetChip(flSicboPoints17, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("17"), false);
            pointsBet17 = 0;
            clickPointsCount17 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint16(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount16++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination2Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination2Bet(), clickPointsCount16,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("16"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints16, true, betMoney, true);
            pointsBet16 = betMoney;
        } else {
            showBetChip(flSicboPoints16, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("16"), false);
            pointsBet16 = 0;
            clickPointsCount16 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint15(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount15++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination3Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination3Bet(), clickPointsCount15,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("15"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints15, true, betMoney, true);
            pointsBet15 = betMoney;
        } else {
            showBetChip(flSicboPoints15, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("15"), false);
            pointsBet15 = 0;
            clickPointsCount15 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint14(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount14++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination4Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination4Bet(), clickPointsCount14,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("14"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints14, true, betMoney, true);
            pointsBet14 = betMoney;
        } else {
            showBetChip(flSicboPoints14, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("14"), false);
            pointsBet14 = 0;
            clickPointsCount14 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint13(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount13++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet(), clickPointsCount13,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("13"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints13, true, betMoney, true);
            pointsBet13 = betMoney;
        } else {
            showBetChip(flSicboPoints13, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("13"), false);
            pointsBet13 = 0;
            clickPointsCount13 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint12(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount12++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination5Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination5Bet(), clickPointsCount12,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("12"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints12, true, betMoney, true);
            pointsBet12 = betMoney;
        } else {
            showBetChip(flSicboPoints12, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("12"), false);
            pointsBet12 = 0;
            clickPointsCount12 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint11(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount11++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination5Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination5Bet(), clickPointsCount11,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("11"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints11, true, betMoney, true);
            pointsBet11 = betMoney;
        } else {
            showBetChip(flSicboPoints11, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("11"), false);
            pointsBet11 = 0;
            clickPointsCount11 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint10(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount10++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination5Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination5Bet(), clickPointsCount10,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("10"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints10, true, betMoney, true);
            pointsBet10 = betMoney;
        } else {
            showBetChip(flSicboPoints15, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("10"), false);
            pointsBet10 = 0;
            clickPointsCount10 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint9(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount9++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination5Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination5Bet(), clickPointsCount9,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("9"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints9, true, betMoney, true);
            pointsBet9 = betMoney;
        } else {
            showBetChip(flSicboPoints9, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("9"), false);
            pointsBet9 = 0;
            clickPointsCount9 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint8(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount8++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet(), clickPointsCount8,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("8"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints8, true, betMoney, true);
            pointsBet8 = betMoney;
        } else {
            showBetChip(flSicboPoints8, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("8"), false);
            pointsBet8 = 0;
            clickPointsCount8 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint7(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount7++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination4Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination4Bet(), clickPointsCount7,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("7"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints7, true, betMoney, true);
            pointsBet7 = betMoney;
        } else {
            showBetChip(flSicboPoints7, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("7"), false);
            pointsBet7 = 0;
            clickPointsCount7 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint6(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount6++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination3Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination3Bet(), clickPointsCount6,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("6"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints6, true, betMoney, true);
            pointsBet6 = betMoney;
        } else {
            showBetChip(flSicboPoints6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("6"), false);
            pointsBet6 = 0;
            clickPointsCount6 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint5(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount5++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination2Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination2Bet(), clickPointsCount5,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("5"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints5, true, betMoney, true);
            pointsBet5 = betMoney;
        } else {
            showBetChip(flSicboPoints5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("5"), false);
            pointsBet5 = 0;
            clickPointsCount5 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickPoint4(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickPointsCount4++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination1Bet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination1Bet(), clickPointsCount4,
                mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("4"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboPoints4, true, betMoney, true);
            pointsBet4 = betMoney;
        } else {
            showBetChip(flSicboPoints4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("4"), false);
            pointsBet4 = 0;
            clickPointsCount4 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination56(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
//        ImageView img = (ImageView) iv;
//        clickFrameLayout(flSicboCombination56, img, R.drawable.gd_table_sicbo_c56_trans_black_selector);
        clickNinewayCount56++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount56,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("5,6"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination56, true, betMoney, true);
            ninewayBet56 = betMoney;
        } else {
            showBetChip(flSicboCombination56, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("5,6"), false);
            ninewayBet56 = 0;
            clickNinewayCount56 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination45(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount45++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount45,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,5"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination45, true, betMoney, true);
            ninewayBet45 = betMoney;
        } else {
            showBetChip(flSicboCombination45, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,5"), false);
            ninewayBet45 = 0;
            clickNinewayCount45 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination46(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount46++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount46,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,6"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination46, true, betMoney, true);
            ninewayBet46 = betMoney;
        } else {
            showBetChip(flSicboCombination46, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,6"), false);
            ninewayBet46 = 0;
            clickNinewayCount46 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination34(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount34++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount34,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,4"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination34, true, betMoney, true);
            ninewayBet34 = betMoney;
        } else {
            showBetChip(flSicboCombination34, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,4"), false);
            ninewayBet34 = 0;
            clickNinewayCount34 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination35(View iv) {
        if (checkChoose())
            return;
//      if(animationDrawableNineway35.isRunning())
//            animationDrawableNineway35.stop();
//        animationDrawableNineway35.start();
        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount35++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount35,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,5"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination35, true, betMoney, true);
            ninewayBet35 = betMoney;
        } else {
            showBetChip(flSicboCombination35, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,5"), false);
            ninewayBet35 = 0;
            clickNinewayCount35 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination36(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount36++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount36,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,6"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination36, true, betMoney, true);
            ninewayBet36 = betMoney;
        } else {
            showBetChip(flSicboCombination36, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,6"), false);
            ninewayBet36 = 0;
            clickNinewayCount36 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination23(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount23++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount23,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,3"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination23, true, betMoney, true);
            ninewayBet23 = betMoney;
        } else {
            showBetChip(flSicboCombination23, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,3"), false);
            ninewayBet23 = 0;
            clickNinewayCount23 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination24(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount24++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount24,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,4"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination24, true, betMoney, true);
            ninewayBet24 = betMoney;
        } else {
            showBetChip(flSicboCombination24, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,4"), false);
            ninewayBet24 = 0;
            clickNinewayCount24 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination25(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount25++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount25,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,5"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination25, true, betMoney, true);
            ninewayBet25 = betMoney;
        } else {
            showBetChip(flSicboCombination25, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,5"), false);
            ninewayBet25 = 0;
            clickNinewayCount25 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination26(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount26++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount26,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,6"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination26, true, betMoney, true);
            ninewayBet26 = betMoney;
        } else {
            showBetChip(flSicboCombination26, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,6"), false);
            ninewayBet26 = 0;
            clickNinewayCount26 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination12(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount12++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount12,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,2"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination12, true, betMoney, true);
            ninewayBet12 = betMoney;
        } else {
            showBetChip(flSicboCombination12, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,2"), false);
            ninewayBet12 = 0;
            clickNinewayCount12 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination13(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount13++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount13,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,3"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination13, true, betMoney, true);
            ninewayBet13 = betMoney;
        } else {
            showBetChip(flSicboCombination13, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,3"), false);
            ninewayBet13 = 0;
            clickNinewayCount13 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination14(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount14++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount14,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,4"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination14, true, betMoney, true);
            ninewayBet14 = betMoney;
        } else {
            showBetChip(flSicboCombination14, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,4"), false);
            ninewayBet14 = 0;
            clickNinewayCount14 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination15(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount15++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount15,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,5"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination15, true, betMoney, true);
            ninewayBet15 = betMoney;
        } else {
            showBetChip(flSicboCombination15, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,5"), false);
            ninewayBet15 = 0;
            clickNinewayCount15 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickCombination16(View iv) {
        if (checkChoose())
            return;
        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickNinewayCount16++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet(), clickNinewayCount16,
                mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,6"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboCombination16, true, betMoney, true);
            ninewayBet16 = betMoney;
        } else {
            showBetChip(flSicboCombination16, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,6"), false);
            ninewayBet16 = 0;
            clickNinewayCount16 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickSingle1(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
//        ImageView img = (ImageView) iv;
//        clickFrameLayout(flSicboSingle1, img, R.drawable.gd_table_sicbo_s1_trans_black_selector);
        clickThreeCount1++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet(), clickThreeCount1,
                mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("1"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboSingle1, true, betMoney, true);
            threeBet1 = betMoney;
        } else {
            showBetChip(flSicboSingle1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("1"), false);
            threeBet1 = 0;
            clickThreeCount1 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickSingle2(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickThreeCount2++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet(), clickThreeCount2,
                mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("2"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboSingle2, true, betMoney, true);
            threeBet2 = betMoney;
        } else {
            showBetChip(flSicboSingle2, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("2"), false);
            threeBet2 = 0;
            clickThreeCount2 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickSingle3(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickThreeCount3++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet(), clickThreeCount3,
                mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("3"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboSingle3, true, betMoney, true);
            threeBet3 = betMoney;
        } else {
            showBetChip(flSicboSingle3, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("3"), false);
            threeBet3 = 0;
            clickThreeCount3 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickSingle4(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickThreeCount4++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet(), clickThreeCount4,
                mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("4"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboSingle4, true, betMoney, true);
            threeBet4 = betMoney;
        } else {
            showBetChip(flSicboSingle4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("4"), false);
            threeBet4 = 0;
            clickThreeCount4 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickSingle5(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickThreeCount5++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet(), clickThreeCount5,
                mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("5"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboSingle5, true, betMoney, true);
            threeBet5 = betMoney;
        } else {
            showBetChip(flSicboSingle5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("5"), false);
            threeBet5 = 0;
            clickThreeCount5 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void clickSingle6(View iv) {
        if (checkChoose())
            return;

        if (mAppViewModel.getSicbo01().getGameStatus() != 1)
            return;
        clickThreeCount6++;
        int betMoney = mAppViewModel.getBetMoney(chooseChip, mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet(),
                mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet(), clickThreeCount6,
                mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("6"), mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet(), mContext, componentFront);
        if (betMoney > 0) {
            showBetChip(flSicboSingle6, true, betMoney, true);
            threeBet6 = betMoney;
        } else {
            showBetChip(flSicboSingle6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("6"), false);
            threeBet6 = 0;
            clickThreeCount6 = 0;
            handler.sendEmptyMessage(HandlerCode.SHOW_LIMIT_OVER_MAX);
        }
    }

    public void showBetMoney() {
        showBetChip(flSicboBigF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getBig(), false);
        showBetChip(flSicboSmallF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getSmall(), false);
        showBetChip(flSicboOddF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getOdd(), false);
        showBetChip(flSicboEvenF1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getEven(), false);

        showBetChip(flSicboDices1F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("1,1,1"), false);
        showBetChip(flSicboDices2F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("2,2,2"), false);
        showBetChip(flSicboDices3F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("3,3,3"), false);
        showBetChip(flSicboDices4F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("4,4,4"), false);
        showBetChip(flSicboDices5F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("5,5,5"), false);
        showBetChip(flSicboDices6F1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("6,6,6"), false);

        showBetChip(flSicboAlldice, true, mAppViewModel.getSicbo01().getSicboBetInformation().getAllDices(), false);

        showBetChip(flSicboPairs1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("1,1"), false);
        showBetChip(flSicboPairs2, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("2,2"), false);
        showBetChip(flSicboPairs3, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("3,3"), false);
        showBetChip(flSicboPairs4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("4,4"), false);
        showBetChip(flSicboPairs5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("5,5"), false);
        showBetChip(flSicboPairs6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("6,6"), false);

        showBetChip(flSicboPoints17, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("17"), false);
        showBetChip(flSicboPoints16, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("16"), false);
        showBetChip(flSicboPoints15, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("15"), false);
        showBetChip(flSicboPoints14, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("14"), false);
        showBetChip(flSicboPoints13, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("13"), false);
        showBetChip(flSicboPoints12, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("12"), false);
        showBetChip(flSicboPoints11, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("11"), false);
        showBetChip(flSicboPoints10, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("10"), false);
        showBetChip(flSicboPoints9, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("9"), false);
        showBetChip(flSicboPoints8, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("8"), false);
        showBetChip(flSicboPoints7, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("7"), false);
        showBetChip(flSicboPoints6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("6"), false);
        showBetChip(flSicboPoints5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("5"), false);
        showBetChip(flSicboPoints4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("4"), false);

        showBetChip(flSicboCombination56, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("5,6"), false);
        showBetChip(flSicboCombination46, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,6"), false);
        showBetChip(flSicboCombination45, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,5"), false);
        showBetChip(flSicboCombination36, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,6"), false);
        showBetChip(flSicboCombination35, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,5"), false);
        showBetChip(flSicboCombination34, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,4"), false);
        showBetChip(flSicboCombination26, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,6"), false);
        showBetChip(flSicboCombination25, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,5"), false);
        showBetChip(flSicboCombination24, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,4"), false);
        showBetChip(flSicboCombination23, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,3"), false);
        showBetChip(flSicboCombination16, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,6"), false);
        showBetChip(flSicboCombination15, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,5"), false);
        showBetChip(flSicboCombination14, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,4"), false);
        showBetChip(flSicboCombination13, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,3"), false);
        showBetChip(flSicboCombination12, true, mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,2"), false);

        showBetChip(flSicboSingle1, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("1"), false);
        showBetChip(flSicboSingle2, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("2"), false);
        showBetChip(flSicboSingle3, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("3"), false);
        showBetChip(flSicboSingle4, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("4"), false);
        showBetChip(flSicboSingle5, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("5"), false);
        showBetChip(flSicboSingle6, true, mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("6"), false);
        int sure = R.mipmap.gd_sureimg;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.gd_noimg;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
    }

    public void gotoLobby() {
        mAppViewModel.getSicbo01().Init();
        //   mAppViewModel.setTableId(0);
        mAppViewModel.setSerialId(0);
        mAppViewModel.setAreaId(0);
        mAppViewModel.setbLobby(true);
        mAppViewModel.getSicbo01().setRoadOld("");


        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 0);
        skipAct(LobbyActivity.class, bundle);
        finish();
    }

    public void InitButtonClick() {
        tvTableBetReplay.setOnClickListener(new ButtonClick());
        tvTableBetSure.setOnClickListener(new ButtonClick());
        tvTableBetCancel.setOnClickListener(new ButtonClick());
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            currentSure = tvTableBetSure;
            currentCancel = tvTableBetCancel;
        }
    }

    public void cancelBet() {
        //有任何一个下注，没有点确定之前，都要清除筹码，并显示声音特效
        if (bigBet > 0 || smallBet > 0 || oddBet > 0 || evenBet > 0 || alldiceBet > 0 || waidiceBet1 > 0 || waidiceBet2 > 0 || waidiceBet3 > 0 || waidiceBet4 > 0 || waidiceBet5 > 0
                || waidiceBet6 > 0 || pairsBet1 > 0 || pairsBet2 > 0 || pairsBet3 > 0 || pairsBet4 > 0 || pairsBet5 > 0 || pairsBet6 > 0
                || pointsBet4 > 0 || pointsBet5 > 0 || pointsBet6 > 0 || pointsBet7 > 0 || pointsBet8 > 0 || pointsBet9 > 0 || pointsBet10 > 0
                || pointsBet11 > 0 || pointsBet12 > 0 || pointsBet13 > 0 || pointsBet14 > 0 || pointsBet15 > 0 || pointsBet16 > 0 || pointsBet17 > 0
                || ninewayBet12 > 0 || ninewayBet13 > 0 || ninewayBet14 > 0 || ninewayBet15 > 0 || ninewayBet16 > 0 || ninewayBet23 > 0 || ninewayBet24 > 0 || ninewayBet25 > 0
                || ninewayBet26 > 0 || ninewayBet34 > 0 || ninewayBet35 > 0 || ninewayBet36 > 0 || ninewayBet45 > 0 || ninewayBet46 > 0 || ninewayBet56 > 0 || threeBet1 > 0
                || threeBet2 > 0 || threeBet3 > 0 || threeBet4 > 0 || threeBet5 > 0 || threeBet6 > 0) {
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentFront, mContext, mAppViewModel.getFrontVolume());
        }
        clearBetChip(SbBetType.All);
        int sure = R.mipmap.gd_sureimg;
        if (currentSure != null && sure != 0) {
            currentSure.setBackgroundResource(sure);
        }
        int no = R.mipmap.gd_noimg;
        if (currentCancel != null && no != 0) {
            currentCancel.setBackgroundResource(no);
        }
    }

    public void repeatBet() {
        if (bBetSucess == false)//当前局还没有下注的时候允许重复下注
        {
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getBig() > 0) {
                bigBet = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getBig();
                showBetChip(flSicboBigF1, true, bigBet, true);
            }
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getSmall() > 0) {
                smallBet = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getSmall();
                showBetChip(flSicboSmallF1, true, smallBet, true);
            }
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getOdd() > 0) {
                oddBet = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getOdd();
                showBetChip(flSicboOddF1, true, oddBet, true);
            }
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getEven() > 0) {
                evenBet = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getEven();
                showBetChip(flSicboEvenF1, true, evenBet, true);
            }
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getAllDices() > 0) {
                alldiceBet = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getAllDices();
                showBetChip(flSicboAlldice, true, alldiceBet, true);
            }
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaiDices().size() > 0) {
                for (int i = 0; i < mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaiDices().size(); i++) {
                    switch (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaiDices().get(i).getNumber()) {
                        case "1,1,1":
                            waidiceBet1 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaidicesBetMoney("1,1,1");
                            showBetChip(flSicboDices1F1, true, waidiceBet1, true);
                            break;
                        case "2,2,2":
                            waidiceBet2 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaidicesBetMoney("2,2,2");
                            showBetChip(flSicboDices2F1, true, waidiceBet2, true);
                            break;
                        case "3,3,3":
                            waidiceBet3 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaidicesBetMoney("3,3,3");
                            showBetChip(flSicboDices3F1, true, waidiceBet3, true);
                            break;
                        case "4,4,4":
                            waidiceBet4 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaidicesBetMoney("4,4,4");
                            showBetChip(flSicboDices4F1, true, waidiceBet4, true);
                            break;
                        case "5,5,5":
                            waidiceBet5 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaidicesBetMoney("5,5,5");
                            showBetChip(flSicboDices5F1, true, waidiceBet5, true);
                            break;
                        case "6,6,6":
                            waidiceBet6 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaidicesBetMoney("6,6,6");
                            showBetChip(flSicboDices6F1, true, waidiceBet6, true);
                            break;
                    }
                }
            }
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairs().size() > 0) {
                for (int i = 0; i < mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairs().size(); i++) {
                    switch (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairs().get(i).getNumber()) {
                        case "1,1":
                            pairsBet1 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairsBetMoney("1,1");
                            showBetChip(flSicboPairs1, true, pairsBet1, true);
                            break;
                        case "2,2":
                            pairsBet2 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairsBetMoney("2,2");
                            showBetChip(flSicboPairs2, true, pairsBet2, true);
                            break;
                        case "3,3":
                            pairsBet3 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairsBetMoney("3,3");
                            showBetChip(flSicboPairs3, true, pairsBet3, true);
                            break;
                        case "4,4":
                            pairsBet4 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairsBetMoney("4,4");
                            showBetChip(flSicboPairs4, true, pairsBet4, true);
                            break;
                        case "5,5":
                            pairsBet5 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairsBetMoney("5,5");
                            showBetChip(flSicboPairs5, true, pairsBet5, true);
                            break;
                        case "6,6":
                            pairsBet6 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairsBetMoney("6,6");
                            showBetChip(flSicboPairs6, true, pairsBet6, true);
                            break;
                    }
                }
            }
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforces().size() > 0) {
                for (int i = 0; i < mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforces().size(); i++) {
                    switch (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforces().get(i).getNumber()) {
                        case "1":
                            threeBet1 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforceBetMoney("1");
                            showBetChip(flSicboSingle1, true, threeBet1, true);
                            break;
                        case "2":
                            threeBet2 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforceBetMoney("2");
                            showBetChip(flSicboSingle2, true, threeBet2, true);
                            break;
                        case "3":
                            threeBet3 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforceBetMoney("3");
                            showBetChip(flSicboSingle3, true, threeBet3, true);
                            break;
                        case "4":
                            threeBet4 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforceBetMoney("4");
                            showBetChip(flSicboSingle4, true, threeBet4, true);
                            break;
                        case "5":
                            threeBet5 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforceBetMoney("5");
                            showBetChip(flSicboSingle5, true, threeBet5, true);
                            break;
                        case "6":
                            threeBet6 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforceBetMoney("6");
                            showBetChip(flSicboSingle6, true, threeBet6, true);
                            break;
                    }
                }
            }
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getCombinations().size() > 0) {
                for (int i = 0; i < mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getCombinations().size(); i++) {
                    switch (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getCombinations().get(i).getNumber()) {
                        case "4":
                            pointsBet4 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("4");
                            showBetChip(flSicboPoints4, true, pointsBet4, true);
                            break;
                        case "5":
                            pointsBet5 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("5");
                            showBetChip(flSicboPoints5, true, pointsBet5, true);
                            break;
                        case "6":
                            pointsBet6 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("6");
                            showBetChip(flSicboPoints6, true, pointsBet6, true);
                            break;
                        case "7":
                            pointsBet7 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("7");
                            showBetChip(flSicboPoints7, true, pointsBet7, true);
                            break;
                        case "8":
                            pointsBet8 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("8");
                            showBetChip(flSicboPoints8, true, pointsBet8, true);
                            break;
                        case "9":
                            pointsBet9 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("9");
                            showBetChip(flSicboPoints9, true, pointsBet9, true);
                            break;
                        case "10":
                            pointsBet10 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("10");
                            showBetChip(flSicboPoints10, true, pointsBet10, true);
                            break;
                        case "11":
                            pointsBet11 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("11");
                            showBetChip(flSicboPoints11, true, pointsBet11, true);
                            break;
                        case "12":
                            pointsBet12 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("12");
                            showBetChip(flSicboPoints12, true, pointsBet12, true);
                            break;
                        case "13":
                            pointsBet13 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("13");
                            showBetChip(flSicboPoints13, true, pointsBet13, true);
                            break;
                        case "14":
                            pointsBet14 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("14");
                            showBetChip(flSicboPoints14, true, pointsBet14, true);
                            break;
                        case "15":
                            pointsBet15 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("15");
                            showBetChip(flSicboPoints15, true, pointsBet15, true);
                            break;
                        case "16":
                            pointsBet16 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("16");
                            showBetChip(flSicboPoints16, true, pointsBet16, true);
                            break;
                        case "17":
                            pointsBet17 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPointsBetMoney("17");
                            showBetChip(flSicboPoints17, true, pointsBet17, true);
                            break;
                    }
                }
            }
            if (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNineway().size() > 0) {
                for (int i = 0; i < mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNineway().size(); i++) {
                    switch (mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNineway().get(i).getNumber()) {
                        case "1,2":
                            ninewayBet12 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("1,2");
                            showBetChip(flSicboCombination12, true, ninewayBet12, true);
                            break;
                        case "1,3":
                            ninewayBet13 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("1,3");
                            showBetChip(flSicboCombination13, true, ninewayBet13, true);
                            break;
                        case "1,4":
                            ninewayBet14 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("1,4");
                            showBetChip(flSicboCombination14, true, ninewayBet14, true);
                            break;
                        case "1,5":
                            ninewayBet15 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("1,5");
                            showBetChip(flSicboCombination15, true, ninewayBet15, true);
                            break;
                        case "1,6":
                            ninewayBet16 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("1,6");
                            showBetChip(flSicboCombination16, true, ninewayBet16, true);
                            break;
                        case "2,3":
                            ninewayBet23 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("2,3");
                            showBetChip(flSicboCombination23, true, ninewayBet23, true);
                            break;
                        case "2,4":
                            ninewayBet24 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("2,4");
                            showBetChip(flSicboCombination24, true, ninewayBet24, true);
                            break;
                        case "2,5":
                            ninewayBet25 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("2,5");
                            showBetChip(flSicboCombination25, true, ninewayBet25, true);
                            break;
                        case "2,6":
                            ninewayBet26 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("2,6");
                            showBetChip(flSicboCombination26, true, ninewayBet26, true);
                            break;
                        case "3,4":
                            ninewayBet34 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("3,4");
                            showBetChip(flSicboCombination34, true, ninewayBet34, true);
                            break;
                        case "3,5":
                            ninewayBet35 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("3,5");
                            showBetChip(flSicboCombination35, true, ninewayBet35, true);
                            break;
                        case "3,6":
                            ninewayBet36 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("3,6");
                            showBetChip(flSicboCombination36, true, ninewayBet36, true);
                            break;
                        case "4,5":
                            ninewayBet45 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("4,5");
                            showBetChip(flSicboCombination45, true, ninewayBet45, true);
                            break;
                        case "4,6":
                            ninewayBet46 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("4,6");
                            showBetChip(flSicboCombination46, true, ninewayBet46, true);
                            break;
                        case "5,6":
                            ninewayBet56 = mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNinewayBetMoney("5,6");
                            showBetChip(flSicboCombination56, true, ninewayBet56, true);
                            break;
                    }
                }
            }
            if (bigBet > 0 || smallBet > 0 || oddBet > 0 || evenBet > 0 || alldiceBet > 0 || waidiceBet1 > 0 || waidiceBet2 > 0 || waidiceBet3 > 0 || waidiceBet4 > 0 || waidiceBet5 > 0
                    || waidiceBet6 > 0 || pairsBet1 > 0 || pairsBet2 > 0 || pairsBet3 > 0 || pairsBet4 > 0 || pairsBet5 > 0 || pairsBet6 > 0
                    || pointsBet4 > 0 || pointsBet5 > 0 || pointsBet6 > 0 || pointsBet7 > 0 || pointsBet8 > 0 || pointsBet9 > 0 || pointsBet10 > 0
                    || pointsBet11 > 0 || pointsBet12 > 0 || pointsBet13 > 0 || pointsBet14 > 0 || pointsBet15 > 0 || pointsBet16 > 0 || pointsBet17 > 0
                    || ninewayBet12 > 0 || ninewayBet13 > 0 || ninewayBet14 > 0 || ninewayBet15 > 0 || ninewayBet16 > 0 || ninewayBet23 > 0 || ninewayBet24 > 0 || ninewayBet25 > 0
                    || ninewayBet26 > 0 || ninewayBet34 > 0 || ninewayBet35 > 0 || ninewayBet36 > 0 || ninewayBet45 > 0 || ninewayBet46 > 0 || ninewayBet56 > 0 || threeBet1 > 0
                    || threeBet2 > 0 || threeBet3 > 0 || threeBet4 > 0 || threeBet5 > 0 || threeBet6 > 0) {
                int sure = R.mipmap.gd_sureimg_light;
                if (currentSure != null && sure != 0) {
                    currentSure.setBackgroundResource(sure);
                }
                int no = R.mipmap.gd_noimg_light;
                if (currentCancel != null && no != 0) {
                    currentCancel.setBackgroundResource(no);
                }
                mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 9, componentFront, mContext, mAppViewModel.getFrontVolume());
            }


        }
    }

    public void bet() {
        if (mAppViewModel.getSicbo01().getGameStatus() == 2 || mAppViewModel.getSicbo01().getGameStatus() == 5)
            return;
        if (mAppViewModel.getUser().getBalance() <= 0) {
            GdToastUtils.showToast(mContext, getString(R.string.Insufficient));
            return;
        }
        if (bigBet > 0 || smallBet > 0 || oddBet > 0 || evenBet > 0 || alldiceBet > 0 || waidiceBet1 > 0 || waidiceBet2 > 0 || waidiceBet3 > 0 || waidiceBet4 > 0 || waidiceBet5 > 0
                || waidiceBet6 > 0 || pairsBet1 > 0 || pairsBet2 > 0 || pairsBet3 > 0 || pairsBet4 > 0 || pairsBet5 > 0 || pairsBet6 > 0
                || pointsBet4 > 0 || pointsBet5 > 0 || pointsBet6 > 0 || pointsBet7 > 0 || pointsBet8 > 0 || pointsBet9 > 0 || pointsBet10 > 0
                || pointsBet11 > 0 || pointsBet12 > 0 || pointsBet13 > 0 || pointsBet14 > 0 || pointsBet15 > 0 || pointsBet16 > 0 || pointsBet17 > 0
                || ninewayBet12 > 0 || ninewayBet13 > 0 || ninewayBet14 > 0 || ninewayBet15 > 0 || ninewayBet16 > 0 || ninewayBet23 > 0 || ninewayBet24 > 0 || ninewayBet25 > 0
                || ninewayBet26 > 0 || ninewayBet34 > 0 || ninewayBet35 > 0 || ninewayBet36 > 0 || ninewayBet45 > 0 || ninewayBet46 > 0 || ninewayBet56 > 0 || threeBet1 > 0
                || threeBet2 > 0 || threeBet3 > 0 || threeBet4 > 0 || threeBet5 > 0 || threeBet6 > 0) {
            sicboBet = new SicboBet(SbBetType.All);
            threadSicboBet = new Thread(sicboBet);
            Executors.newSingleThreadExecutor().execute(threadSicboBet);
            showBlockDialog();
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 10, componentFront, mContext, mAppViewModel.getFrontVolume());
        }
    }

    private void singleBet(SbBetType type) {
        if (mAppViewModel.getSicbo01().getGameStatus() == 2 || mAppViewModel.getSicbo01().getGameStatus() == 5)
            return;
        if (mAppViewModel.getUser().getBalance() <= 0) {
            GdToastUtils.showToast(mContext, getString(R.string.Insufficient));
            return;
        }
        if (bigBet > 0 || smallBet > 0 || oddBet > 0 || evenBet > 0 || alldiceBet > 0 || waidiceBet1 > 0 || waidiceBet2 > 0 || waidiceBet3 > 0 || waidiceBet4 > 0 || waidiceBet5 > 0
                || waidiceBet6 > 0 || pairsBet1 > 0 || pairsBet2 > 0 || pairsBet3 > 0 || pairsBet4 > 0 || pairsBet5 > 0 || pairsBet6 > 0
                || pointsBet4 > 0 || pointsBet5 > 0 || pointsBet6 > 0 || pointsBet7 > 0 || pointsBet8 > 0 || pointsBet9 > 0 || pointsBet10 > 0
                || pointsBet11 > 0 || pointsBet12 > 0 || pointsBet13 > 0 || pointsBet14 > 0 || pointsBet15 > 0 || pointsBet16 > 0 || pointsBet17 > 0
                || ninewayBet12 > 0 || ninewayBet13 > 0 || ninewayBet14 > 0 || ninewayBet15 > 0 || ninewayBet16 > 0 || ninewayBet23 > 0 || ninewayBet24 > 0 || ninewayBet25 > 0
                || ninewayBet26 > 0 || ninewayBet34 > 0 || ninewayBet35 > 0 || ninewayBet36 > 0 || ninewayBet45 > 0 || ninewayBet46 > 0 || ninewayBet56 > 0 || threeBet1 > 0
                || threeBet2 > 0 || threeBet3 > 0 || threeBet4 > 0 || threeBet5 > 0 || threeBet6 > 0) {
            sicboBet = new SicboBet(type);
            threadSicboBet = new Thread(sicboBet);
            Executors.newSingleThreadExecutor().execute(threadSicboBet);
            showBlockDialog();
            mAppViewModel.startFrontMuzicService(FrontMuzicService.PLAY_CHIP, 10, componentFront, mContext, mAppViewModel.getFrontVolume());
        }
    }

    public void showResultsOnUI() {
        if (mAppViewModel.getSicbo01().getResult() != null && !"".equals(mAppViewModel.getSicbo01().getResult()) && !"0".equals(mAppViewModel.getSicbo01().getResult())
                && mAppViewModel.getSicbo01().getResult().length() == 3) {


            int dices1 = Integer.parseInt(mAppViewModel.getSicbo01().getResult().substring(0, 1));
            int dices2 = Integer.parseInt(mAppViewModel.getSicbo01().getResult().substring(1, 2));
            int dices3 = Integer.parseInt(mAppViewModel.getSicbo01().getResult().substring(2, 3));
            int point = dices1 + dices2 + dices3;
            int allDices = 0;
            if ((dices1 == dices2) && (dices1 == dices3))
                allDices = 1;
            int bigSmall = 0;
            int oddEven = 0;
            if (point > 10) {
                bigSmall = 1;//big
            } else
                bigSmall = 0;

            if (point % 2 == 0) {
                oddEven = 1;//shuang
            } else
                oddEven = 0;

            if (bigSmall == 1) {
                if (animationDrawableBig.isRunning())
                    animationDrawableBig.stop();
                animationDrawableBig.start();

            } else {
                if (animationDrawableSmall.isRunning())
                    animationDrawableSmall.stop();
                animationDrawableSmall.start();
            }
            if (oddEven == 1) {
                if (animationDrawableEven.isRunning())
                    animationDrawableEven.stop();
                animationDrawableEven.start();
            } else {
                if (animationDrawableOdd.isRunning())
                    animationDrawableOdd.stop();
                animationDrawableOdd.start();
            }
            if (allDices == 1) {
                if (animationDrawableAllDices.isRunning())
                    animationDrawableAllDices.stop();
                animationDrawableAllDices.start();
            }
            switch (mAppViewModel.getSicbo01().getResult()) {
                case "111":
                    if (animationDrawableWaidice1.isRunning())
                        animationDrawableWaidice1.stop();
                    animationDrawableWaidice1.start();
                    break;
                case "222":
                    if (animationDrawableWaidice2.isRunning())
                        animationDrawableWaidice2.stop();
                    animationDrawableWaidice2.start();
                    break;
                case "333":
                    if (animationDrawableWaidice3.isRunning())
                        animationDrawableWaidice3.stop();
                    animationDrawableWaidice3.start();
                    break;
                case "444":
                    if (animationDrawableWaidice4.isRunning())
                        animationDrawableWaidice4.stop();
                    animationDrawableWaidice4.start();
                    break;
                case "555":
                    if (animationDrawableWaidice5.isRunning())
                        animationDrawableWaidice5.stop();
                    animationDrawableWaidice5.start();
                    break;
                case "666":
                    if (animationDrawableWaidice6.isRunning())
                        animationDrawableWaidice6.stop();
                    animationDrawableWaidice6.start();
                    break;
            }
            Log.i(WebSiteUrl.Tag, "showResultsOnUI point=" + point);
            Log.i(WebSiteUrl.Tag, "showResultsOnUI result=" + mAppViewModel.getSicbo01().getResult());
            switch (point) {
                case 4:
                    if (animationDrawablePoint4.isRunning())
                        animationDrawablePoint4.stop();
                    animationDrawablePoint4.start();
                    break;
                case 5:
                    if (animationDrawablePoint5.isRunning())
                        animationDrawablePoint5.stop();
                    animationDrawablePoint5.start();
                    break;
                case 6:
                    if (animationDrawablePoint6.isRunning())
                        animationDrawablePoint6.stop();
                    animationDrawablePoint6.start();
                    break;
                case 7:
                    if (animationDrawablePoint7.isRunning())
                        animationDrawablePoint7.stop();
                    animationDrawablePoint7.start();
                    break;
                case 8:
                    if (animationDrawablePoint8.isRunning())
                        animationDrawablePoint8.stop();
                    animationDrawablePoint8.start();
                    break;
                case 9:
                    if (animationDrawablePoint9.isRunning())
                        animationDrawablePoint9.stop();
                    animationDrawablePoint9.start();
                    break;
                case 10:
                    if (animationDrawablePoint10.isRunning())
                        animationDrawablePoint10.stop();
                    animationDrawablePoint10.start();
                    break;
                case 11:
                    if (animationDrawablePoint11.isRunning())
                        animationDrawablePoint11.stop();
                    animationDrawablePoint11.start();
                    break;
                case 12:
                    if (animationDrawablePoint12.isRunning())
                        animationDrawablePoint12.stop();
                    animationDrawablePoint12.start();
                    break;
                case 13:
                    if (animationDrawablePoint13.isRunning())
                        animationDrawablePoint13.stop();
                    animationDrawablePoint13.start();
                    break;
                case 14:
                    if (animationDrawablePoint14.isRunning())
                        animationDrawablePoint14.stop();
                    animationDrawablePoint14.start();
                    break;
                case 15:
                    if (animationDrawablePoint15.isRunning())
                        animationDrawablePoint15.stop();
                    animationDrawablePoint15.start();
                    break;
                case 16:
                    if (animationDrawablePoint16.isRunning())
                        animationDrawablePoint16.stop();
                    animationDrawablePoint16.start();
                    break;
                case 17:
                    if (animationDrawablePoint17.isRunning())
                        animationDrawablePoint17.stop();
                    animationDrawablePoint17.start();
                    break;
            }
            //san jun
            if (mAppViewModel.getSicbo01().getResult().contains("1")) {
                if (animationDrawableThree1.isRunning())
                    animationDrawableThree1.stop();
                animationDrawableThree1.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("2")) {
                if (animationDrawableThree2.isRunning())
                    animationDrawableThree2.stop();
                animationDrawableThree2.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("3")) {
                if (animationDrawableThree3.isRunning())
                    animationDrawableThree3.stop();
                animationDrawableThree3.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("4")) {
                if (animationDrawableThree4.isRunning())
                    animationDrawableThree4.stop();
                animationDrawableThree4.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("5")) {
                if (animationDrawableThree5.isRunning())
                    animationDrawableThree5.stop();
                animationDrawableThree5.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("6")) {
                if (animationDrawableThree6.isRunning())
                    animationDrawableThree6.stop();
                animationDrawableThree6.start();
            }
            //duizi
            if (mAppViewModel.getSicbo01().getResult().contains("11")) {
                if (animationDrawablePair1.isRunning())
                    animationDrawablePair1.stop();
                animationDrawablePair1.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("22")) {
                if (animationDrawablePair2.isRunning())
                    animationDrawablePair2.stop();
                animationDrawablePair2.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("33")) {
                if (animationDrawablePair3.isRunning())
                    animationDrawablePair3.stop();
                animationDrawablePair3.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("44")) {
                if (animationDrawablePair4.isRunning())
                    animationDrawablePair4.stop();
                animationDrawablePair4.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("55")) {
                if (animationDrawablePair5.isRunning())
                    animationDrawablePair5.stop();
                animationDrawablePair5.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("66")) {
                if (animationDrawablePair6.isRunning())
                    animationDrawablePair6.stop();
                animationDrawablePair6.start();
            }

            if (mAppViewModel.getSicbo01().getResult().contains("12")) {
                if (animationDrawableNineway12.isRunning())
                    animationDrawableNineway12.stop();
                animationDrawableNineway12.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("1") && mAppViewModel.getSicbo01().getResult().contains("3")) {
                if (animationDrawableNineway13.isRunning())
                    animationDrawableNineway13.stop();
                animationDrawableNineway13.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("1") && mAppViewModel.getSicbo01().getResult().contains("4")) {
                if (animationDrawableNineway14.isRunning())
                    animationDrawableNineway14.stop();
                animationDrawableNineway14.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("1") && mAppViewModel.getSicbo01().getResult().contains("5")) {
                if (animationDrawableNineway15.isRunning())
                    animationDrawableNineway15.stop();
                animationDrawableNineway15.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("1") && mAppViewModel.getSicbo01().getResult().contains("6")) {
                if (animationDrawableNineway16.isRunning())
                    animationDrawableNineway16.stop();
                animationDrawableNineway16.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("2") && mAppViewModel.getSicbo01().getResult().contains("3")) {
                if (animationDrawableNineway23.isRunning())
                    animationDrawableNineway23.stop();
                animationDrawableNineway23.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("2") && mAppViewModel.getSicbo01().getResult().contains("4")) {
                if (animationDrawableNineway24.isRunning())
                    animationDrawableNineway24.stop();
                animationDrawableNineway24.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("2") && mAppViewModel.getSicbo01().getResult().contains("5")) {
                if (animationDrawableNineway25.isRunning())
                    animationDrawableNineway25.stop();
                animationDrawableNineway25.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("2") && mAppViewModel.getSicbo01().getResult().contains("6")) {
                if (animationDrawableNineway26.isRunning())
                    animationDrawableNineway26.stop();
                animationDrawableNineway26.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("3") && mAppViewModel.getSicbo01().getResult().contains("4")) {
                if (animationDrawableNineway34.isRunning())
                    animationDrawableNineway34.stop();
                animationDrawableNineway34.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("3") && mAppViewModel.getSicbo01().getResult().contains("5")) {
                if (animationDrawableNineway35.isRunning())
                    animationDrawableNineway35.stop();
                animationDrawableNineway35.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("3") && mAppViewModel.getSicbo01().getResult().contains("6")) {
                if (animationDrawableNineway36.isRunning())
                    animationDrawableNineway36.stop();
                animationDrawableNineway36.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("4") && mAppViewModel.getSicbo01().getResult().contains("5")) {
                if (animationDrawableNineway45.isRunning())
                    animationDrawableNineway45.stop();
                animationDrawableNineway45.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("4") && mAppViewModel.getSicbo01().getResult().contains("6")) {
                if (animationDrawableNineway46.isRunning())
                    animationDrawableNineway46.stop();
                animationDrawableNineway46.start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("5") && mAppViewModel.getSicbo01().getResult().contains("6")) {
                if (animationDrawableNineway56.isRunning())
                    animationDrawableNineway56.stop();
                animationDrawableNineway56.start();
            }


        }
    }

    public void showBetPanel() {
        if (isBottomOpen == false) {
            isBottomOpen = !isBottomOpen;
            bottonPanel1.setOpen(isBottomOpen, false);
        }
    }

    public void initPopResultsWindows() {
        ImageView iv1 = (ImageView) fl_sicbo_result.findViewById(R.id.gd__iv_dice_1);
        ImageView iv2 = (ImageView) fl_sicbo_result.findViewById(R.id.gd__iv_dice_2);
        ImageView iv3 = (ImageView) fl_sicbo_result.findViewById(R.id.gd__iv_dice_3);
        TextView tv = (TextView) fl_sicbo_result.findViewById(R.id.gd__iv_dice_tv);
        TextView tv_big_small = (TextView) fl_sicbo_result.findViewById(R.id.gd__tv_big_small);
        TextView tv_odd_even = (TextView) fl_sicbo_result.findViewById(R.id.gd__tv_odd_even);
        if (mAppViewModel.getSicbo01().getResult() != null && !"".equals(mAppViewModel.getSicbo01().getResult()) && !"0".equals(mAppViewModel.getSicbo01().getResult())
                && mAppViewModel.getSicbo01().getResult().length() == 3 && mAppViewModel.getSicbo01().getGameStatus() == 5) {


            int dices1 = Integer.parseInt(mAppViewModel.getSicbo01().getResult().substring(0, 1));
            int dices2 = Integer.parseInt(mAppViewModel.getSicbo01().getResult().substring(1, 2));
            int dices3 = Integer.parseInt(mAppViewModel.getSicbo01().getResult().substring(2, 3));
            int point = dices1 + dices2 + dices3;
            iv1.setImageResource(getMipmap(dices1));
            iv2.setImageResource(getMipmap(dices2));
            iv3.setImageResource(getMipmap(dices3));
            tv.setText("" + point);
            if (point > 10) {
                tv_big_small.setBackgroundResource(R.drawable.rectangle_sicbo_blue);
                tv_big_small.setText(getString(R.string.gd_B));
            } else {
                tv_big_small.setBackgroundResource(R.drawable.rectangle_sicbo_red);
                tv_big_small.setText(getString(R.string.gd_S));
            }
            if (point % 2 == 0) {
                tv_odd_even.setBackgroundResource(R.drawable.rectangle_sicbo_red);
                tv_odd_even.setText(getString(R.string.gd_E));
            } else {
                tv_odd_even.setBackgroundResource(R.drawable.rectangle_sicbo_blue);
                tv_odd_even.setText(getString(R.string.gd_O));
            }
            fl_sicbo_result.setVisibility(View.VISIBLE);
        } else {
            fl_sicbo_result.setVisibility(View.GONE);
        }

    }

    //重新选择了筹码，重新开始算下注筹码
    public void initClickCount() {
        clickBigCount = 0;
        clickSmallCount = 0;
        clickOddCount = 0;
        clickEvenCount = 0;
        clickWaidiceCount1 = 0;
        clickWaidiceCount2 = 0;
        clickWaidiceCount3 = 0;
        clickWaidiceCount4 = 0;
        clickWaidiceCount5 = 0;
        clickWaidiceCount6 = 0;
        clickPairsCount1 = 0;
        clickPairsCount2 = 0;
        clickPairsCount3 = 0;
        clickPairsCount4 = 0;
        clickPairsCount5 = 0;
        clickPairsCount6 = 0;
        clickPointsCount4 = 0;
        clickPointsCount5 = 0;
        clickPointsCount6 = 0;
        clickPointsCount7 = 0;
        clickPointsCount8 = 0;
        clickPointsCount9 = 0;
        clickPointsCount10 = 0;
        clickPointsCount11 = 0;
        clickPointsCount12 = 0;
        clickPointsCount13 = 0;
        clickPointsCount14 = 0;
        clickPointsCount15 = 0;
        clickPointsCount16 = 0;
        clickPointsCount17 = 0;
        clickNinewayCount12 = 0;
        clickNinewayCount13 = 0;
        clickNinewayCount14 = 0;
        clickNinewayCount15 = 0;
        clickNinewayCount16 = 0;
        clickNinewayCount23 = 0;
        clickNinewayCount24 = 0;
        clickNinewayCount25 = 0;
        clickNinewayCount26 = 0;
        clickNinewayCount34 = 0;
        clickNinewayCount35 = 0;
        clickNinewayCount36 = 0;
        clickNinewayCount45 = 0;
        clickNinewayCount46 = 0;
        clickNinewayCount56 = 0;
        clickThreeCount1 = 0;
        clickThreeCount2 = 0;
        clickThreeCount3 = 0;
        clickThreeCount4 = 0;
        clickThreeCount5 = 0;
        clickThreeCount6 = 0;
    }

    private void displayAll(boolean b) {
        if (b) {
            imgBack.setVisibility(View.VISIBLE);
        } else {
            imgBack.setVisibility(View.GONE);
        }
        showHideUserInfo();
    }

    @Override
    protected void showPool() {
        super.showPool();
//        if (lv_table_pool.getVisibility() == View.GONE)
//            lv_table_pool.setVisibility(View.VISIBLE);
//        else
//            lv_table_pool.setVisibility(View.GONE);

    }

    @Override
    protected void showLimit() {
        super.showLimit();
//        if (lvTableBetLimitRed.getVisibility() == View.GONE)
//            lvTableBetLimitRed.setVisibility(View.VISIBLE);
//        else
//            lvTableBetLimitRed.setVisibility(View.GONE);

    }

    @Override
    protected void initOrientation() {
        super.initOrientation();
        AppTool.setAppLanguage(this, AppTool.getAppLanguage(this));
        lv_baccarat_chips = (AdapterView) findViewById(R.id.gd__lv_baccarat_chips_h);
        lv_table_results = (AdapterView) findViewById(R.id.lv_table_results);
    }

    @Override
    protected void showMenuPop(View v) {
        showHideUserInfo();
    }

    private void showHideUserInfo() {
        if (ll_info.getLayoutParams().height > 0) {
            WidgetUtil.shrinkAnimation(ll_info, ScreenUtil.dip2px(mContext, 135), 0);
        } else {
            WidgetUtil.shrinkAnimation(ll_info, 0, ScreenUtil.dip2px(mContext, 135));
        }
    }

    /*@Override
    protected void clickRight(View v) {
        super.clickRight(v);
        showMenuPop(v);
    }*/

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        L.e(
                ",orientationAuto:" + newConfig.orientation);
        initAutoSize();
    }

    private List<LiveInfoBean> updateInfoData() {
        AppTool.setAppLanguage(this, AppTool.getAppLanguage(this));
        List<LiveInfoBean> strData = new ArrayList<LiveInfoBean>();
        LiveInfoBean data;
        String name = usName;
        data = new LiveInfoBean(getString(R.string.gd_ID), name==null?"":name.toUpperCase(), "");
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.gd_BET), mAppViewModel.getSicbo01().getSicboBetInformation().getAllBetMoney() + "", "");
        if (Integer.parseInt(data.getValue1()) > 0) {
            rightBetTv.setText(mAppViewModel.covertLimit(Integer.parseInt(data.getValue1())) + "");
        } else {
            rightBetTv.setText(getString(R.string.gd_BET) + " :0");
        }
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.W_L), mAppViewModel.getSicbo01().getWonMoney() + "", "");
        if (mAppViewModel.getSicbo01().getWonMoney() > 0) {
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.win_color));
            rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getSicbo01().getWonMoney()) + "");
        } else if (mAppViewModel.getSicbo01().getWonMoney() == 0) {
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            rightWinLoseTv.setText(getString(R.string.W_L) + " :0");
        } else {
            rightWinLoseTv.setTextColor(ContextCompat.getColor(mContext, R.color.banker_color));
            rightWinLoseTv.setText(mAppViewModel.covertWinLose(mAppViewModel.getSicbo01().getWonMoney()) + "");
        }
        strData.add(data);
        data = new LiveInfoBean(TextUtils.isEmpty(currency) ? getString(R.string.BAL) : currency, mAppViewModel.getUser().getBalance() + "", "");
        rightBalanceTv.setText(mAppViewModel.getUser().getBalance() + "");
        strData.add(data);
        data = new LiveInfoBean(getString(R.string.LIMIT_POP), limitPop
                , "");
        strData.add(data);
        strData.add(new LiveInfoBean(getString(R.string.big_small) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet())));
        strData.add(new LiveInfoBean(getString(R.string.even_odd) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinBigSmallBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxBigSmallBet())));
        strData.add(new LiveInfoBean(getString(R.string.threeforce) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinThreeforcesBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxThreeforcesBet())));
        strData.add(new LiveInfoBean(getString(R.string.ninewaycard) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinNinewaycardBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxNinewaycardBet())));
        strData.add(new LiveInfoBean(getString(R.string.pairs) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinPairBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxPairBet())));
        strData.add(new LiveInfoBean(getString(R.string.wai_dice) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinWaidiceBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxWaidiceBet())));
        strData.add(new LiveInfoBean(getString(R.string.all_dice) + ":", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinAlldiceBet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxAlldiceBet())));
        strData.add(new LiveInfoBean(getString(R.string.points) + "(4/17):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination1Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination1Bet())));
        strData.add(new LiveInfoBean(getString(R.string.points) + "(5/16):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination2Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination2Bet())));
        strData.add(new LiveInfoBean(getString(R.string.points) + "(6/15):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination3Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination3Bet())));
        strData.add(new LiveInfoBean(getString(R.string.points) + "(7/14):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination4Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination4Bet())));
        strData.add(new LiveInfoBean(getString(R.string.points) + "(9-12):", mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMinCombination5Bet())
                , mAppViewModel.covertLimit(mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxCombination5Bet())));
        return strData;
    }

    private List<LiveInfoBean> updatePercentageData() {
        Sicbo sicbo = mAppViewModel.getSicbo01();
        double big = sicbo.getBig();
        double small = sicbo.getSmall();
        double even = sicbo.getEven();
        double odd = sicbo.getOdd();
        double waidic = sicbo.getWaidic();
        double total = big + small + even + odd + waidic;
        List<LiveInfoBean> strData = new ArrayList<LiveInfoBean>();
        double bigPercentage = 0;
        double smallPercentage = 0;
        double evenPercentage = 0;
        double oddPercentage = 0;
        double waidicPercentage = 0;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        if (total > 0) {
            if (big > 0) {
                bigPercentage = Double.parseDouble(decimalFormat.format(big / total));
            }
            if (small > 0) {
                smallPercentage = Double.parseDouble(decimalFormat.format(small / total));
            }
            if (even > 0) {
                evenPercentage = Double.parseDouble(decimalFormat.format(even / total));
            }
            if (odd > 0) {
                oddPercentage = Double.parseDouble(decimalFormat.format(odd / total));
            }
            waidicPercentage = Double.parseDouble(decimalFormat.format(1 - (bigPercentage + smallPercentage + oddPercentage + evenPercentage)));
            waidicPercentage = waidicPercentage * 100;
            bigPercentage = bigPercentage * 100;
            smallPercentage = smallPercentage * 100;
            evenPercentage = evenPercentage * 100;
            oddPercentage = oddPercentage * 100;
        }
        strData.add(new LiveInfoBean(getString(R.string.gd_B), (int) bigPercentage + "%", ""));
        strData.add(new LiveInfoBean(getString(R.string.gd_S), (int) smallPercentage + "%", ""));
        strData.add(new LiveInfoBean(getString(R.string.gd_O), (int) oddPercentage + "%", ""));
        strData.add(new LiveInfoBean(getString(R.string.gd_E), (int) evenPercentage + "%", ""));
        strData.add(new LiveInfoBean(getString(R.string.gd_other), (int) waidicPercentage + "%", ""));
        return strData;
    }

    private void setPercentageData(ListView listView) {
        AppTool.setAppLanguage(this, AppTool.getAppLanguage(this));
        if (contentPercentage == null)
            contentPercentage = new AdapterViewContent<>(mContext, listView);
        contentPercentage.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {

            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_percentage;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                LinearLayout ll_parent = helper.retrieveView(R.id.gd__ll_parent);
                if (position == 0 || position == 2) {
                    ll_parent.setBackgroundResource(R.drawable.rectangle_sicbo_blue);
                } else if (position == 1 || position == 3) {
                    ll_parent.setBackgroundResource(R.drawable.rectangle_sicbo_red);
                } else {
                    ll_parent.setBackgroundResource(R.drawable.rectangle_sicbo_black);
                }
                TextView tvType = helper.retrieveView(R.id.gd__tv_name);
                TextView tvValue = helper.retrieveView(R.id.gd__tv_value);
                tvType.setText(item.getType());
                tvValue.setText(item.getValue1());
            }
        });
        contentPercentage.setData(updatePercentageData());
    }

    private void setInfoData(ListView listView) {
        if (contentInfo == null)
            contentInfo = new AdapterViewContent<>(mContext, listView);
        contentInfo.setBaseAdapter(new QuickAdapterImp<LiveInfoBean>() {

            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_user_info1_roulette;
            }

            @Override
            public void convert(ViewHolder helper, LiveInfoBean item, int position) {
                TextView tvType = helper.retrieveView(R.id.gd__tv_name);
                TextView tvValue = helper.retrieveView(R.id.gd__tv_value);
                tvType.setText(item.getType());
                tvValue.setTextColor(ContextCompat.getColor(mContext, R.color.gold));
                if (position > 4) {
                    tvValue.setText(item.getValue1() + " - " + item.getValue2());
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

    @Override
    public void onInGameChooseLanguage() {
        sibao_bet_bg.setBackgroundResource(0);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            sibao_bet_bg.setBackgroundResource(R.mipmap.gd_sicbo_bet_bg);
        } else {
            sibao_bet_bg.setBackgroundResource(R.mipmap.gd_sicbo_bet_bg_h);
        }
        contentPercentage.setData(updatePercentageData());
        contentPercentage.notifyDataSetChanged();
        contentResults.setData(getResultsData());
        contentResults.notifyDataSetChanged();
    }

    @Override
    public boolean isCanSlideChangeTable() {
        if (isSlideInfo) {
            return false;
        }
        return true;
    }

    public enum SbBetType {
        bigBet,
        smallBet,
        oddBet,
        evenBet,
        alldiceBet,
        waidiceBet1,
        waidiceBet2,
        waidiceBet3,
        waidiceBet4,
        waidiceBet5,
        waidiceBet6,
        pairsBet1,
        pairsBet2,
        pairsBet3,
        pairsBet4,
        pairsBet5,
        pairsBet6,
        threeBet1,
        threeBet2,
        threeBet3,
        threeBet4,
        threeBet5,
        threeBet6,
        ninewayBet12,
        ninewayBet13,
        ninewayBet14,
        ninewayBet15,
        ninewayBet16,
        ninewayBet23,
        ninewayBet24,
        ninewayBet25,
        ninewayBet26,
        ninewayBet34,
        ninewayBet35,
        ninewayBet36,
        ninewayBet45,
        ninewayBet46,
        ninewayBet56,

        pointsBet4,
        pointsBet5,
        pointsBet6,
        pointsBet7,
        pointsBet8,
        pointsBet9,
        pointsBet10,
        pointsBet11,
        pointsBet12,
        pointsBet13,
        pointsBet14,
        pointsBet15,
        pointsBet16,
        pointsBet17,
        All
    }

    public class UpdateStatus implements Runnable {
        int iError = 0;

        public void run() {
            while (bGetStatus) {
                try {
                    handler.sendEmptyMessage(HandlerCode.UPDATE_STATUS);
                    Thread.sleep(1020);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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

    public class UpdateBetMoney implements Runnable {

        public void run() {

            try {
                Thread.sleep(1500);
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                        + "&Bl=" + mAppViewModel.getSicbo01().getGameNumber() +
                        "&Xh=" + mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.SICBO_BET_MONEY_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateBetMoney params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateBetMoney = " + strRes);

                String strInfo[] = strRes.split("\\^");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 10) {

                        mAppViewModel.getSicbo01().getSicboBetInformation().setAllBetMoney((int) Double.parseDouble(strInfo[2]));
                        if (mAppViewModel.getSicbo01().getSicboBetInformation().getAllBetMoney() > 0)
                            bBetSucess = true;

                        mAppViewModel.getSicbo01().getSicboBetInformation().setBig((int) Double.parseDouble(strInfo[3]));
                        mAppViewModel.getSicbo01().getSicboBetInformation().setSmall((int) Double.parseDouble(strInfo[4]));
                        mAppViewModel.getSicbo01().getSicboBetInformation().setOdd((int) Double.parseDouble(strInfo[5]));
                        mAppViewModel.getSicbo01().getSicboBetInformation().setEven((int) Double.parseDouble(strInfo[6]));
                        mAppViewModel.getSicbo01().getSicboBetInformation().setAllDices((int) Double.parseDouble(strInfo[7]));
                        if (!"0".equals(strInfo[8])) {//有下注的情况，需要拆分
                            String strThree[] = strInfo[8].split("\\|");
                            mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforces().clear();
                            for (int i = 0; i < strThree.length; i++) {

                                String strThreeDetail[] = strThree[i].split("#");
                                if (strThreeDetail != null && strThreeDetail.length == 2) {
                                    BetDetail betDetail = new BetDetail();
                                    betDetail.setMoney((int) Double.parseDouble(strThreeDetail[1]));
                                    betDetail.setNumber(strThreeDetail[0]);
                                    betDetail.setType("Three");
                                    mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforces().add(betDetail);
                                }

                            }
                        } else
                            mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforces().clear();

                        if (!"0".equals(strInfo[9])) {//短牌
                            String strNineway[] = strInfo[9].split("\\|");
                            mAppViewModel.getSicbo01().getSicboBetInformation().getNineway().clear();
                            for (int i = 0; i < strNineway.length; i++) {

                                String strNinewayDetail[] = strNineway[i].split("#");
                                if (strNinewayDetail != null && strNinewayDetail.length == 2) {
                                    BetDetail betDetail = new BetDetail();
                                    betDetail.setMoney((int) Double.parseDouble(strNinewayDetail[1]));
                                    betDetail.setNumber(strNinewayDetail[0]);
                                    betDetail.setType("Nineway");
                                    mAppViewModel.getSicbo01().getSicboBetInformation().getNineway().add(betDetail);
                                }

                            }
                        } else
                            mAppViewModel.getSicbo01().getSicboBetInformation().getNineway().clear();
                        if (!"0".equals(strInfo[10])) {//长牌
                            String strPairs[] = strInfo[10].split("\\|");
                            mAppViewModel.getSicbo01().getSicboBetInformation().getPairs().clear();
                            for (int i = 0; i < strPairs.length; i++) {

                                String strPairsDetail[] = strPairs[i].split("#");
                                if (strPairsDetail != null && strPairsDetail.length == 2) {
                                    BetDetail betDetail = new BetDetail();
                                    betDetail.setMoney((int) Double.parseDouble(strPairsDetail[1]));
                                    betDetail.setNumber(strPairsDetail[0]);
                                    betDetail.setType("Pairs");
                                    mAppViewModel.getSicbo01().getSicboBetInformation().getPairs().add(betDetail);
                                }

                            }
                        } else
                            mAppViewModel.getSicbo01().getSicboBetInformation().getPairs().clear();
                        if (!"0".equals(strInfo[11])) {//围骰
                            String strWaidice[] = strInfo[11].split("\\|");
                            mAppViewModel.getSicbo01().getSicboBetInformation().getWaiDices().clear();
                            for (int i = 0; i < strWaidice.length; i++) {

                                String strWaidiceDetail[] = strWaidice[i].split("#");
                                if (strWaidiceDetail != null && strWaidiceDetail.length == 2) {
                                    BetDetail betDetail = new BetDetail();
                                    betDetail.setMoney((int) Double.parseDouble(strWaidiceDetail[1]));
                                    betDetail.setNumber(strWaidiceDetail[0]);
                                    betDetail.setType("Waidice");
                                    mAppViewModel.getSicbo01().getSicboBetInformation().getWaiDices().add(betDetail);
                                }

                            }
                        } else
                            mAppViewModel.getSicbo01().getSicboBetInformation().getWaiDices().clear();
                        if (!"0".equals(strInfo[12])) {// 点数
                            String strCombination[] = strInfo[12].split("\\|");
                            mAppViewModel.getSicbo01().getSicboBetInformation().getCombinations().clear();
                            for (int i = 0; i < strCombination.length; i++) {

                                String strCombinationDetail[] = strCombination[i].split("#");
                                if (strCombinationDetail != null && strCombinationDetail.length == 2) {
                                    BetDetail betDetail = new BetDetail();
                                    betDetail.setMoney((int) Double.parseDouble(strCombinationDetail[1]));
                                    betDetail.setNumber(strCombinationDetail[0]);
                                    betDetail.setType("Combination");
                                    mAppViewModel.getSicbo01().getSicboBetInformation().getCombinations().add(betDetail);
                                }

                            }
                        } else
                            mAppViewModel.getSicbo01().getSicboBetInformation().getCombinations().clear();

                        handler.sendEmptyMessage(HandlerCode.SHOW_BET_MONEY);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public class SicboBet implements Runnable {
        public SicboBet(SbBetType type) {
            SicboActivity.this.type = type;

        }

        public void run() {
            if (isCanbet) {
                isCanbet = false;
                try {
                    String big = "0";
                    String small = "0";
                    String odd = "0";
                    String even = "0";
                    String allDices = "0";
                    String threeForces = "";
                    String nineWayGards = "";
                    String pairs = "";
                    String waiDices = "";
                    String points = "";


                    if (bigBet > 0)
                        if (type == SbBetType.All || type == SbBetType.bigBet)
                            big = "" + (bigBet - mAppViewModel.getSicbo01().getSicboBetInformation().getBig());
                    if (smallBet > 0)
                        if (type == SbBetType.All || type == SbBetType.smallBet)
                            small = "" + (smallBet - mAppViewModel.getSicbo01().getSicboBetInformation().getSmall());
                    if (oddBet > 0)
                        if (type == SbBetType.All || type == SbBetType.oddBet)
                            odd = "" + (oddBet - mAppViewModel.getSicbo01().getSicboBetInformation().getOdd());
                    if (evenBet > 0)
                        if (type == SbBetType.All || type == SbBetType.evenBet)
                            even = "" + (evenBet - mAppViewModel.getSicbo01().getSicboBetInformation().getEven());
                    if (alldiceBet > 0)
                        if (type == SbBetType.All || type == SbBetType.alldiceBet)
                            allDices = "" + (alldiceBet - mAppViewModel.getSicbo01().getSicboBetInformation().getAllDices());

                    if (waidiceBet1 > 0)
                        if (type == SbBetType.All || type == SbBetType.waidiceBet1)
                            waiDices += "1,1,1#" + (waidiceBet1 - mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("1,1,1") + "|");
                    if (waidiceBet2 > 0)
                        if (type == SbBetType.All || type == SbBetType.waidiceBet2)
                            waiDices += "2,2,2#" + (waidiceBet2 - mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("2,2,2") + "|");
                    if (waidiceBet3 > 0)
                        if (type == SbBetType.All || type == SbBetType.waidiceBet3)
                            waiDices += "3,3,3#" + (waidiceBet3 - mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("3,3,3") + "|");
                    if (waidiceBet4 > 0)
                        if (type == SbBetType.All || type == SbBetType.waidiceBet4)
                            waiDices += "4,4,4#" + (waidiceBet4 - mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("4,4,4") + "|");
                    if (waidiceBet5 > 0)
                        if (type == SbBetType.All || type == SbBetType.waidiceBet5)
                            waiDices += "5,5,5#" + (waidiceBet5 - mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("5,5,5") + "|");
                    if (waidiceBet6 > 0)
                        if (type == SbBetType.All || type == SbBetType.waidiceBet6)
                            waiDices += "6,6,6#" + (waidiceBet6 - mAppViewModel.getSicbo01().getSicboBetInformation().getWaidicesBetMoney("6,6,6") + "|");
                    if ("".equals(waiDices))
                        waiDices = "0";

                    if (pairsBet1 > 0)
                        if (type == SbBetType.All || type == SbBetType.pairsBet1)
                            pairs += "1,1#" + (pairsBet1 - mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("1,1") + "|");
                    if (pairsBet2 > 0)
                        if (type == SbBetType.All || type == SbBetType.pairsBet2)
                            pairs += "2,2#" + (pairsBet2 - mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("2,2") + "|");
                    if (pairsBet3 > 0)
                        if (type == SbBetType.All || type == SbBetType.pairsBet3)
                            pairs += "3,3#" + (pairsBet3 - mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("3,3") + "|");
                    if (pairsBet4 > 0)
                        if (type == SbBetType.All || type == SbBetType.pairsBet4)
                            pairs += "4,4#" + (pairsBet4 - mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("4,4") + "|");
                    if (pairsBet5 > 0)
                        if (type == SbBetType.All || type == SbBetType.pairsBet5)
                            pairs += "5,5#" + (pairsBet5 - mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("5,5") + "|");
                    if (pairsBet6 > 0)
                        if (type == SbBetType.All || type == SbBetType.pairsBet6)
                            pairs += "6,6#" + (pairsBet6 - mAppViewModel.getSicbo01().getSicboBetInformation().getPairsBetMoney("6,6") + "|");
                    if ("".equals(pairs))
                        pairs = "0";

                    if (threeBet1 > 0)
                        if (type == SbBetType.All || type == SbBetType.threeBet1)
                            threeForces += "1#" + (threeBet1 - mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("1") + "|");
                    if (threeBet2 > 0)
                        if (type == SbBetType.All || type == SbBetType.threeBet2)
                            threeForces += "2#" + (threeBet2 - mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("2") + "|");
                    if (threeBet3 > 0)
                        if (type == SbBetType.All || type == SbBetType.threeBet3)
                            threeForces += "3#" + (threeBet3 - mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("3") + "|");
                    if (threeBet4 > 0)
                        if (type == SbBetType.All || type == SbBetType.threeBet4)
                            threeForces += "4#" + (threeBet4 - mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("4") + "|");
                    if (threeBet5 > 0)
                        if (type == SbBetType.All || type == SbBetType.threeBet5)
                            threeForces += "5#" + (threeBet5 - mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("5") + "|");
                    if (threeBet6 > 0)
                        if (type == SbBetType.All || type == SbBetType.threeBet6)
                            threeForces += "6#" + (threeBet6 - mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforceBetMoney("6") + "|");
                    if ("".equals(threeForces))
                        threeForces = "0";

                    if (ninewayBet12 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet12)
                            nineWayGards += "1,2#" + (ninewayBet12 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,2") + "|");
                    if (ninewayBet13 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet13)
                            nineWayGards += "1,3#" + (ninewayBet13 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,3") + "|");
                    if (ninewayBet14 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet14)
                            nineWayGards += "1,4#" + (ninewayBet14 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,4") + "|");
                    if (ninewayBet15 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet15)
                            nineWayGards += "1,5#" + (ninewayBet15 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,5") + "|");
                    if (ninewayBet16 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet16)
                            nineWayGards += "1,6#" + (ninewayBet16 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("1,6") + "|");
                    if (ninewayBet23 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet23)
                            nineWayGards += "2,3#" + (ninewayBet23 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,3") + "|");
                    if (ninewayBet24 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet24)
                            nineWayGards += "2,4#" + (ninewayBet24 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,4") + "|");
                    if (ninewayBet25 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet25)
                            nineWayGards += "2,5#" + (ninewayBet25 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,5") + "|");
                    if (ninewayBet26 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet26)
                            nineWayGards += "2,6#" + (ninewayBet26 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("2,6") + "|");
                    if (ninewayBet34 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet34)
                            nineWayGards += "3,4#" + (ninewayBet34 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,4") + "|");
                    if (ninewayBet35 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet35)
                            nineWayGards += "3,5#" + (ninewayBet35 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,5") + "|");
                    if (ninewayBet36 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet36)
                            nineWayGards += "3,6#" + (ninewayBet36 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("3,6") + "|");
                    if (ninewayBet45 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet45)
                            nineWayGards += "4,5#" + (ninewayBet45 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,5") + "|");
                    if (ninewayBet46 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet46)
                            nineWayGards += "4,6#" + (ninewayBet46 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("4,6") + "|");
                    if (ninewayBet56 > 0)
                        if (type == SbBetType.All || type == SbBetType.ninewayBet56)
                            nineWayGards += "5,6#" + (ninewayBet56 - mAppViewModel.getSicbo01().getSicboBetInformation().getNinewayBetMoney("5,6") + "|");
                    if ("".equals(nineWayGards))
                        nineWayGards = "0";

                    if (pointsBet4 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet4)
                            points += "4#" + (pointsBet4 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("4") + "|");
                    if (pointsBet5 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet5)
                            points += "5#" + (pointsBet5 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("5") + "|");
                    if (pointsBet6 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet6)
                            points += "6#" + (pointsBet6 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("6") + "|");
                    if (pointsBet7 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet7)
                            points += "7#" + (pointsBet7 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("7") + "|");
                    if (pointsBet8 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet8)
                            points += "8#" + (pointsBet8 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("8") + "|");
                    if (pointsBet9 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet9)
                            points += "9#" + (pointsBet9 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("9") + "|");
                    if (pointsBet10 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet10)
                            points += "10#" + (pointsBet10 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("10") + "|");
                    if (pointsBet11 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet11)
                            points += "11#" + (pointsBet11 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("11") + "|");
                    if (pointsBet12 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet12)
                            points += "12#" + (pointsBet12 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("12") + "|");
                    if (pointsBet13 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet13)
                            points += "13#" + (pointsBet13 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("13") + "|");
                    if (pointsBet14 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet14)
                            points += "14#" + (pointsBet14 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("14") + "|");
                    if (pointsBet15 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet15)
                            points += "15#" + (pointsBet15 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("15") + "|");
                    if (pointsBet16 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet16)
                            points += "16#" + (pointsBet16 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("16") + "|");
                    if (pointsBet17 > 0)
                        if (type == SbBetType.All || type == SbBetType.pointsBet17)
                            points += "17#" + (pointsBet17 - mAppViewModel.getSicbo01().getSicboBetInformation().getPointsBetMoney("17") + "|");
                    if ("".equals(points))
                        points = "0";


                    String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                            + "&Bl=" + mAppViewModel.getSicbo01().getGameNumber()
                            + "&Xh=" + mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet()
                            + "&Hl=1"
                            + "&Big=" + big + "&Small=" + small + "&Odd=" + odd + "&Even=" + even + "&AllDices=" + allDices
                            + "&ThreeForces=" + threeForces + "&NineWayGards=" + nineWayGards + "&Pairs=" + pairs + "&SurroundDices=" + waiDices + "&Points=" + points;

                    String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.SICBO_BET_URL, params);
                    Log.i(WebSiteUrl.Tag, "SicboBet params= " + params);
                    Log.i(WebSiteUrl.Tag, "SicboBet = " + strRes);
                    String strInfo[] = strRes.split("\\^");
                    if (strRes.startsWith("Results=ok")) {
                        if (strInfo.length >= 10) {
                            mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                            double resMoney = Double.parseDouble(strInfo[2]);
                            //清除之前的下注记录
                            mAppViewModel.getSicbo01().getSicboBetRepeatInformation().Init();
                            mAppViewModel.getSicbo01().getSicboBetInformation().setAllBetMoney((int) resMoney);
                            mAppViewModel.getSicbo01().getSicboBetRepeatInformation().setAllBetMoney((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[3]);
                            mAppViewModel.getSicbo01().getSicboBetInformation().setBig((int) resMoney);
                            mAppViewModel.getSicbo01().getSicboBetRepeatInformation().setBig((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[4]);
                            mAppViewModel.getSicbo01().getSicboBetInformation().setSmall((int) resMoney);
                            mAppViewModel.getSicbo01().getSicboBetRepeatInformation().setSmall((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[5]);
                            mAppViewModel.getSicbo01().getSicboBetInformation().setOdd((int) resMoney);
                            mAppViewModel.getSicbo01().getSicboBetRepeatInformation().setOdd((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[6]);
                            mAppViewModel.getSicbo01().getSicboBetInformation().setEven((int) resMoney);
                            mAppViewModel.getSicbo01().getSicboBetRepeatInformation().setEven((int) resMoney);
                            resMoney = Double.parseDouble(strInfo[7]);
                            mAppViewModel.getSicbo01().getSicboBetInformation().setAllDices((int) resMoney);
                            mAppViewModel.getSicbo01().getSicboBetRepeatInformation().setAllDices((int) resMoney);

                            if (!"0".equals(strInfo[8])) {//有下注的情况，需要拆分
                                String strThree[] = strInfo[8].split("\\|");
                                mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforces().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforces().clear();
                                for (int i = 0; i < strThree.length; i++) {

                                    String strThreeDetail[] = strThree[i].split("#");
                                    if (strThreeDetail != null && strThreeDetail.length == 2) {
                                        BetDetail betDetail = new BetDetail();
                                        betDetail.setMoney((int) Double.parseDouble(strThreeDetail[1]));
                                        betDetail.setNumber(strThreeDetail[0]);
                                        betDetail.setType("Three");
                                        mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforces().add(betDetail);
                                        mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforces().add(betDetail);
                                    }

                                }
                            } else {
                                mAppViewModel.getSicbo01().getSicboBetInformation().getThreeforces().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getThreeforces().clear();
                            }

                            if (!"0".equals(strInfo[9])) {//短牌
                                String strNineway[] = strInfo[9].split("\\|");
                                mAppViewModel.getSicbo01().getSicboBetInformation().getNineway().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNineway().clear();
                                for (int i = 0; i < strNineway.length; i++) {

                                    String strNinewayDetail[] = strNineway[i].split("#");
                                    if (strNinewayDetail != null && strNinewayDetail.length == 2) {
                                        BetDetail betDetail = new BetDetail();
                                        betDetail.setMoney((int) Double.parseDouble(strNinewayDetail[1]));
                                        betDetail.setNumber(strNinewayDetail[0]);
                                        betDetail.setType("Nineway");
                                        mAppViewModel.getSicbo01().getSicboBetInformation().getNineway().add(betDetail);
                                        mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNineway().add(betDetail);
                                    }

                                }
                            } else {
                                mAppViewModel.getSicbo01().getSicboBetInformation().getNineway().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getNineway().clear();
                            }
                            if (!"0".equals(strInfo[10])) {//长牌
                                String strPairs[] = strInfo[10].split("\\|");
                                mAppViewModel.getSicbo01().getSicboBetInformation().getPairs().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairs().clear();
                                for (int i = 0; i < strPairs.length; i++) {

                                    String strPairsDetail[] = strPairs[i].split("#");
                                    if (strPairsDetail != null && strPairsDetail.length == 2) {
                                        BetDetail betDetail = new BetDetail();
                                        betDetail.setMoney((int) Double.parseDouble(strPairsDetail[1]));
                                        betDetail.setNumber(strPairsDetail[0]);
                                        betDetail.setType("Pairs");
                                        mAppViewModel.getSicbo01().getSicboBetInformation().getPairs().add(betDetail);
                                        mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairs().add(betDetail);
                                    }

                                }
                            } else {
                                mAppViewModel.getSicbo01().getSicboBetInformation().getPairs().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getPairs().clear();
                            }
                            if (!"0".equals(strInfo[11])) {//围骰
                                String strWaidice[] = strInfo[11].split("\\|");
                                mAppViewModel.getSicbo01().getSicboBetInformation().getWaiDices().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaiDices().clear();
                                for (int i = 0; i < strWaidice.length; i++) {

                                    String strWaidiceDetail[] = strWaidice[i].split("#");
                                    if (strWaidiceDetail != null && strWaidiceDetail.length == 2) {
                                        BetDetail betDetail = new BetDetail();
                                        betDetail.setMoney((int) Double.parseDouble(strWaidiceDetail[1]));
                                        betDetail.setNumber(strWaidiceDetail[0]);
                                        betDetail.setType("Waidice");
                                        mAppViewModel.getSicbo01().getSicboBetInformation().getWaiDices().add(betDetail);
                                        mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaiDices().add(betDetail);
                                    }

                                }
                            } else {
                                mAppViewModel.getSicbo01().getSicboBetInformation().getWaiDices().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getWaiDices().clear();
                            }
                            if (!"0".equals(strInfo[12])) {// 点数
                                String strCombination[] = strInfo[12].split("\\|");
                                mAppViewModel.getSicbo01().getSicboBetInformation().getCombinations().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getCombinations().clear();
                                for (int i = 0; i < strCombination.length; i++) {

                                    String strCombinationDetail[] = strCombination[i].split("#");
                                    if (strCombinationDetail != null && strCombinationDetail.length == 2) {
                                        BetDetail betDetail = new BetDetail();
                                        betDetail.setMoney((int) Double.parseDouble(strCombinationDetail[1]));
                                        betDetail.setNumber(strCombinationDetail[0]);
                                        betDetail.setType("Combination");
                                        mAppViewModel.getSicbo01().getSicboBetInformation().getCombinations().add(betDetail);
                                        mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getCombinations().add(betDetail);
                                    }

                                }
                            } else {
                                mAppViewModel.getSicbo01().getSicboBetInformation().getCombinations().clear();
                                mAppViewModel.getSicbo01().getSicboBetRepeatInformation().getCombinations().clear();
                            }
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

    //取最后一个就可以了
    public class UpdateRoad implements Runnable {
        public void run() {

            try {
                if (bUpdateRoad == false)
                    Thread.sleep(3000);
                //  isActive = true;
             /*   String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.SICBO_LUZI_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateRoad params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateRoad = " + strRes);
                String strInfo[] = strRes.split("\\|");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                     // 再以#号区分，取第一个，如果跟之前的路子的最后一个相等，就不要再添加到路子接口末尾了
                        String strLuzi[] = strInfo[1].split("#");
                        String luzi = strLuzi[0].substring(0,1)+"-"+strLuzi[0].substring(1,2)+"-"+strLuzi[0].substring(2,3);
                        String reslut[] = mAppViewModel.getSicbo01().getRoad().split("#");
                        String lastReslut = "";
                        if(!"".equals(reslut[reslut.length]) && reslut[reslut.length] != null)
                            lastReslut = reslut[reslut.length];
                        else
                            lastReslut = reslut[reslut.length-1];

                        if(!luzi.equals(lastReslut))
                            mAppViewModel.getSicbo01().setRoad(mAppViewModel.getSicbo01().getRoad()+luzi+"#");
                        isActive = true;
                    }
                }*/

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public class UpdateGameNumber implements Runnable {
        public void run() {

            try {
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.SICBO_TABLE_GAMENUM, params);
                Log.i(WebSiteUrl.Tag, "UpdateGameNumber params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateGameNumber = " + strRes);
                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                        mAppViewModel.getSicbo01().setGameNumber(strInfo[1]);
                        handler.sendEmptyMessage(HandlerCode.UPDATE_GAME_NUMBER);
                    }
                } else
                    handler.sendEmptyMessage(HandlerCode.UPDATE_GAME_NUMBER_ERROR);
            } catch (Exception e) {
                handler.sendEmptyMessage(HandlerCode.UPDATE_GAME_NUMBER_ERROR);
                e.printStackTrace();
            }

        }
    }

    public class UpdateWonMoney implements Runnable {
        public void run() {

            try {
//                Thread.sleep(3000);
                String params = "GameType=11&Tbid=" + mAppViewModel.getTableId() + "&Usid=" + mAppViewModel.getUser().getName()
                        + "&Blid=" + mAppViewModel.getSicbo01().getGameNumber() +
                        "&Xh=" + mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet();
                String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.SICBO_WON_MONEY_URL, params);
                Log.i(WebSiteUrl.Tag, "UpdateWonMoney params= " + params);
                Log.i(WebSiteUrl.Tag, "UpdateWonMoney = " + strRes);

                String strInfo[] = strRes.split("#");
                if (strRes.startsWith("Results=ok")) {
                    if (strInfo.length >= 3) {
                        if (sicboTimer == 0 && mAppViewModel.getSicbo01().getGameStatus() == 5 && isShowWinLose) {
                            mAppViewModel.getSicbo01().setWonMoney(Double.parseDouble(strInfo[2]));
                        } else {
                            mAppViewModel.getSicbo01().setWonMoney(0);
                        }
                        mAppViewModel.getUser().setBalance(Double.parseDouble(strInfo[1]));
                        if (isShowWinLose) {
                            handler.sendEmptyMessage(HandlerCode.SHOW_WIN_LOSS);
                        }
                    } else {
                        mAppViewModel.getSicbo01().setWonMoney(0);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class ButtonClick implements View.OnClickListener {
        public void onClick(View v) {


            int id = v.getId();
            if (id == R.id.gd__tv_table_bet_replay) {
                repeatBet();
            } else if (id == R.id.gd__tv_table_bet_sure) {
                bet();
            } else if (id == R.id.gd__tv_table_bet_cancel) {
                cancelBet();
            }
        }


    }
}
