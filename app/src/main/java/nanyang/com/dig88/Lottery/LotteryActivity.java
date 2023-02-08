package nanyang.com.dig88.Lottery;

import android.content.BroadcastReceiver;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.TimeUtils;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;
import gaming178.com.mylibrary.myview.indicator.PagerSlidingTabStrip;
import gaming178.com.mylibrary.popupwindow.AbsListPopupWindow;
import nanyang.com.dig88.Adapter.MyFragmentPagerAdapter;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryCountBean;
import nanyang.com.dig88.Entity.LotteryPromptBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/8.彩票
 */
public class LotteryActivity extends GameBaseActivity {

    private static final String TIMER_ACTION = "TIMER_ACTION";
    @BindView(R.id.lottery_bottom_bet_fl)
    LinearLayout lotteryBottomBetFl;
    @BindView(R.id.lottery_bottom_submit_btn)
    Button submitBtn;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    //更新操作
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;
    @BindView(R.id.pager)
    ViewPager contentPager;
    @BindView(R.id.total_amount_tv)
    TextView total_amount_tv;
    @BindView(R.id.bet_total_amount_tv)
    TextView bet_total_amount_tv;
    @BindView(R.id.prompt_single_tv)
    TextView prompt_single_tv;
    @BindView(R.id.prompt_total_tv)
    TextView prompt_total_tv;
    LotteryStateGameBean selectedStateBean;
    Handler updateHandler = new Handler();
    Lottery2DNewFragment lottery2DFrontFragment;
    Lottery2DNewFragment lottery2DMFragment;
    private NyVolleyJsonThreadHandler<List<LotteryStateGameBean>> stateThread; //游戏状态
    Runnable dataStateUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            stateThread.startThread(null);
            updateHandler.postDelayed(this, 40000);// 50是延时时长
        }
    };
    private LotteryBaseFragment indexFragment;
    private List<LotteryBaseFragment> fragmentsList;
    private ArrayList<DigGameOddsBean> selectedOddsList;
    private BroadcastReceiver timerReceiver;
    private int index = 0;
    private LotteryCountBean countBean;
    private AbsListPopupWindow<LotteryStateGameBean> rightMenuPop;


    private List<DigGameOddsBean> oddslist;

    public LotteryCountBean getCountBean() {
        return countBean;
    }

    public void setCountBean(LotteryCountBean countBean) {
        this.countBean = countBean;
    }

    public LotteryStateGameBean getSelectedStateBean() {
        return selectedStateBean;
    }

    public void setSelectedStateBean(LotteryStateGameBean selectedStateBean) {
        this.selectedStateBean = selectedStateBean;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home_lottery;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        AppTool.setAppLanguage(mContext, "");
        super.initData(savedInstanceState);
        rightTv.setVisibility(View.VISIBLE);
        initDisplayView();
        initOddsList();
        initStateThread();
        initRightMenuPop();
    }

    /*1.	SINGAPORE
    2.	HONGKONGPOOL
    3.	SYDNEY
    4.	HIROSHIMA
    5.	NAGASAKI
    6.	GENTING 4D
    7.	MACAU SUPERLOTTO
    8.	MAGNUM 4D
    */
    private void initRightMenuPop() {
        rightMenuPop = new AbsListPopupWindow<LotteryStateGameBean>(mContext, rightTv, 400, 600) {
            @Override
            protected void popItemCLick(LotteryStateGameBean bean, int position) {
                String name = bean.getGame_name();
                name = changeName(name);
                setLotteryActTitle(name);
                selectedStateBean = bean;
                stateThread.startThread(null);
                if (indexFragment.adapter != null) {
                    indexFragment.adapter.clearListMoney();
                    indexFragment.adapter.countTotal();
                }

            }

            @Override
            protected void convertItem(ViewHolder helper, LotteryStateGameBean item, int position) {
                String name = item.getGame_name();
                name = changeName(name);
                helper.setText(R.id.item_tv, name);
            }

            @Override
            protected int getItemLayoutRes() {
                return R.layout.item_popwindow;
            }

            @Override
            protected int getListViewId() {
                return R.id.lv_dialog;
            }

            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.area_popwindow;
            }
        };
    }

    private String changeName(String name) {
        name = name.replace("159", "").replace("-", "");
        if (name.equals("SINGAPORE")) {
            name = getString(R.string.SINGAPORE);
        } else if (name.equals("TAIPEI")) {
            name = getString(R.string.TAIPEI);
        } else if (name.equals("KUALA_LUMPUR")) {
            name = getString(R.string.KUALA_LUMPUR);
        } else if (name.equals("HONGKONG")) {
            name = "HONGKONGPOOL";
        } else if (name.equals("MALAYSIA")) {
            name = getString(R.string.MALAYSIA);
        } else if (name.equals("CHINA")) {
            name = getString(R.string.CHINA);
        } else if (name.equals("HKD")) {
            name = "HK4D";
        } else if (name.equals("CAMBODIA")) {
            name = getString(R.string.CAMBODIA);
        } else if (name.equals("MACAU")) {
            name = getString(R.string.MACAU);
        } else if (name.equals("CANADA4D")) {
            name = getString(R.string.CANADA4D);
        } else if (name.equals("HONGKONGPOOL")) {
            name = "HK LOTTO";
        } else if (name.equals("SYDNEY")) {
            name = getString(R.string.SYDNEY);
        } else if (name.equals("TaiWan")) {
            name = getString(R.string.TaiWan);
        }
        return name;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (oddslist != null)
            updateStateData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeStateUpdate();
    }

    public void updateStateData() {
        removeStateUpdate();
        updateHandler.post(dataStateUpdateRunnable);// 打开定时器，执行操作
    }

    public void removeStateUpdate() {
        updateHandler.removeCallbacks(dataStateUpdateRunnable);// 关闭定时器处理
    }

    private void initStateThread() {
        stateThread = new NyVolleyJsonThreadHandler<List<LotteryStateGameBean>>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", getUserInfoBean().getUser_id());
                params.put("session_id", getUserInfoBean().getSession_id());
                return new QuickRequestBean(WebSiteUrl.LotteryStatusSubmitter, params
                        , new TypeToken<NyBaseResponse<List<LotteryStateGameBean>>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, List<LotteryStateGameBean> data) {

                initRightMenu(data);
                dismissBlockDialog();
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                if (obj != null)
                    Toast.makeText(LotteryActivity.this, obj, Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void setLotteryActTitle(String gameName) {
        setTitle(getString(R.string.tw_game) + "(" + gameName + ")");
    }

    private void initRightMenu(List<LotteryStateGameBean> data) {
        if (data.size() < 1)
            return;
        if (selectedStateBean != null) {
            for (LotteryStateGameBean lotteryStateGameBean : data) {
                if (selectedStateBean.getGame_name().equals(lotteryStateGameBean.getGame_name())) {
                    selectedStateBean = lotteryStateGameBean;
                    break;
                }
            }
        } else {
            selectedStateBean = data.get(0);
        }
        String name = selectedStateBean.getGame_name();
        name = changeName(name);
        setLotteryActTitle(name);
        rightMenuPop.setData(data);
        updateGameOddsSate();
    }

    private void updateGameOddsSate() {
        if (selectedStateBean == null || oddslist == null)
            return;
        gameOpenState(selectedStateBean);
        selectedOddsList = new ArrayList<>();
        for (DigGameOddsBean oddsBean : oddslist) {
            if (oddsBean.getType1().equals("5") && oddsBean.getType2().equals(selectedStateBean.getType2())) {
                selectedOddsList.add(oddsBean);
            }
        }
        if (indexFragment != null)
            indexFragment.updateGameState(selectedOddsList, selectedStateBean);
    }

    public ArrayList<DigGameOddsBean> getSelectedOddsList() {
        return selectedOddsList;
    }

    public void setSelectedOddsList(ArrayList<DigGameOddsBean> selectedOddsList) {
        this.selectedOddsList = selectedOddsList;
    }

    private void initDisplayView() {
        submitBtn.setText(getString(R.string.submit_sure));
        setTitle(mContext.getString(R.string.caipiao));
        rightTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.caipiaocaidan, 0);
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightMenuPop.showPopupDownWindow();
            }
        });
        //2,3,4D
        Lottery234DFragment lottery234DFragment = new Lottery234DFragment();
        //组合
        LotteryCombinationFragment comFragment = new LotteryCombinationFragment();
        //生肖
        LotteryShioFragment zoFragment = new LotteryShioFragment();
        //CLOCK
        LotteryColokFragment clockFragment = new LotteryColokFragment();
        //交叉
        LotteryCrossFragment crossFragment = new LotteryCrossFragment();

        lottery2DFrontFragment = new Lottery2DNewFragment();
        lottery2DFrontFragment.setBetType("2DFront");
        lottery2DFrontFragment.setTitle(getString(R.string.FRONT_2D));
        lottery2DMFragment = new Lottery2DNewFragment();
        lottery2DMFragment.setBetType("2DMid");
        lottery2DMFragment.setTitle(getString(R.string.MID_2D));
        //特殊
        SpecialFragment specialFragment = new SpecialFragment();
        //结果列表
        ResultListFragment reFragment = new ResultListFragment();
        // 彩票时间
        lottery234DFragment.setTitle("2D3D4D");
        comFragment.setTitle(getString(R.string.zhuhe));
        zoFragment.setTitle(getString(R.string.shengxiao));
        clockFragment.setTitle(getString(R.string.colok));
        crossFragment.setTitle(getString(R.string.alternate));
        specialFragment.setTitle(getString(R.string.speailpeilv));
        reFragment.setTitle(getString(R.string.jieguolist));
        fragmentsList = new ArrayList<LotteryBaseFragment>(Arrays.asList(lottery234DFragment, comFragment, zoFragment, clockFragment, crossFragment, lottery2DFrontFragment, lottery2DMFragment, specialFragment, reFragment));
        final MyFragmentPagerAdapter pageAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList);
        contentPager.setAdapter(pageAdapter);
        contentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                index = position;
                if (selectedStateBean == null) {
                    Toast.makeText(mContext, getString(R.string.no_data), Toast.LENGTH_SHORT).show();
                    finish();
                }
                indexFragment = (LotteryBaseFragment) pageAdapter.getItem(position);
                if (indexFragment.adapter != null) {
                    indexFragment.updateGameState(selectedOddsList, selectedStateBean);
                    indexFragment.adapter.clearListMoney();
                    indexFragment.adapter.countTotal();
                    indexFragment.adapter.updatePrompt();
                } else if (indexFragment.equals(lottery2DFrontFragment) || indexFragment.equals(lottery2DMFragment)) {
                    indexFragment.updateGameState(selectedOddsList, selectedStateBean);
                    total_amount_tv.setText(getString(R.string.zonge) + "0.00");
                    bet_total_amount_tv.setText(getString(R.string.zongtouzhue) + "0.00");
                }
//                int index = 4;
//                if (WebSiteUrl.WebId.equals("19")) {
//                    index = 6;
//                }
                if (position > 6) {
                    lotteryBottomBetFl.setVisibility(View.GONE);
                } else {
                    lotteryBottomBetFl.setVisibility(View.VISIBLE);
                }
                if (selectedStateBean != null)
                    gameOpenState(selectedStateBean);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabs.setBackgroundColor(Color.BLACK);
        tabs.setSelectedBgColor(R.color.select_bg);
        tabs.setTextColorResource(R.color.white);
        tabs.setBackGroundColorRes(R.color.black);
        tabs.setTextSize(12);
        tabs.setViewPager(contentPager);
        indexFragment = (LotteryBaseFragment) pageAdapter.getItem(0);

    }

    /**
     * 算取是否关闭游戏
     */
    private void gameOpenState(LotteryStateGameBean bean) {
        if (index < 5) {
            String openrule = bean.getOpen_rule();
            String closetime = bean.getClose_time();
            String nowTime = bean.getNow_time();
            String openTime = bean.getOpen_time();
            // 是否关闭游戏
//      结果=0；当天正常关闭游戏
//      结果！=0；当天不关闭游戏
            int openR = Integer.valueOf(openrule);
            Date today = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            //结果

            int i = (int) Math.pow(2, weekday - 1 - 1);
            int result = openR & i;
            if (result == 0) {
                String newTime = nowTime.substring(nowTime.length() - 8, nowTime.length());
                long isClose = TimeUtils.diffTime(newTime, closetime, "HH:mm:ss");
                long isOpen = TimeUtils.diffTime(newTime, openTime, "HH:mm:ss");
                if (isClose > 0 && isOpen < 0) {
                    indexFragment.setProgressVisibility(true);
                    submitBtn.setEnabled(false);
                } else {
                    indexFragment.setProgressVisibility(false);
                    submitBtn.setEnabled(true);
                }
            } else {
                indexFragment.setProgressVisibility(false);
                submitBtn.setEnabled(true);
            }
        } else {
            indexFragment.setProgressVisibility(false);
            submitBtn.setEnabled(true);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AppTool.setAppLanguage(mContext, "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppTool.setAppLanguage(mContext, "");
    }

    /**
     * 获取赔率列表
     */
    private void initOddsList() {
        NyVolleyJsonThreadHandler oddThread = new NyVolleyJsonThreadHandler<List<DigGameOddsBean>>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", getUserInfoBean().getUser_id());
                params.put("session_id", getUserInfoBean().getSession_id());
                Log.w("HttpVolley", "bet:" + getUserInfoBean().getSession_id());
                //游戏状态appi01.sgtest.dig88api.net/index.php?page=game_set_submitter
                return new QuickRequestBean(WebSiteUrl.GameSetSubmitter, params
                        , new TypeToken<NyBaseResponse<List<DigGameOddsBean>>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, List<DigGameOddsBean> data) {

                if (data == null || data.size() < 1)
                    initOddsList2();
                else {
                    oddslist = data;
                    updateStateData();
                }
            }

            @Override
            public void successEnd(NyBaseResponse<List<DigGameOddsBean>> obj) {
                if (obj.getMsg().trim().equals("1")) {
                    int total = 0;
                    if (obj.getTotal() != null) {
                        total = obj.getTotal();
                    }
                    successEndT(total, obj.getData());
                } else {
                    errorEnd("Code:" + obj.getCode() + ",Msg:" + obj.getMsg());
                }
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                initOddsList2();
            }
        };

        showBlockDialog();
        oddThread.startThread(null);
    }

    private void initOddsList2() {
        NyVolleyJsonThreadHandler oddThread2 = new NyVolleyJsonThreadHandler<List<DigGameOddsBean>>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebIdDefault);
                params.put("user_id", getUserInfoBean().getUser_id());
                params.put("session_id", getUserInfoBean().getSession_id());
                Log.w("HttpVolley", "bet:" + getUserInfoBean().getSession_id());
                //游戏状态appi01.sgtest.dig88api.net/index.php?page=game_set_submitter
                return new QuickRequestBean(WebSiteUrl.GameSetSubmitter, params
                        , new TypeToken<NyBaseResponse<List<DigGameOddsBean>>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, List<DigGameOddsBean> data) {
                oddslist = data;
                updateStateData();
            }

        };
        showBlockDialog();
        oddThread2.startThread(null);
    }

    public void setLotteryPrompt(LotteryPromptBean prompt) {

//        prompt_single_tv.setText(prompt.getSinglePrompt());
//        prompt_total_tv.setText(prompt.getTotalPrompt());
    }

    public void setLotteryCount(LotteryCountBean bean) {
        setCountBean(bean);
        total_amount_tv.setText(getString(R.string.zonge) + bean.getMoneyTotal());
        bet_total_amount_tv.setText(getString(R.string.zongtouzhue) + bean.getBetTotal());
    }

    public void setLotteryCount(String totalMoney, String netMoney) {
        total_amount_tv.setText(getString(R.string.zonge) + totalMoney);
        bet_total_amount_tv.setText(getString(R.string.zongtouzhue) + netMoney);
    }

    public void clickSubmit(View v) {
        if (!AppTool.isShort2Click(1000)) {
            if (indexFragment.betType().equals("2DFront") || indexFragment.betType().equals("2DMid")) {
                indexFragment.submitFor2D(v);
            } else {
                indexFragment.submitBet(v);
            }
        }
    }


}
