package nanyang.com.dig88.NewKeno;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.NewKeno.presenter.KenoFragmentPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.DateUtils;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.NestGridView;
import nanyang.com.dig88.Util.NestListView;
import nanyang.com.dig88.Util.SoundPlayUtils;
import xs.com.mylibrary.allinone.util.ThreadPoolUtils;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2018/9/13.
 */

public class KenoFragment extends NewKenoBaseFragment<KenoFragmentPresenter> {
    public String stateUrl;
    public String betListUrl;
    public String gameSetUrl;
    public String historyUrl;
    @Bind(R.id.gv_number)
    NestGridView gvNumber;
    @Bind(R.id.gv_times)
    NestGridView gv_times;
    @Bind(R.id.gv_road)
    NestGridView gv_road;
    @Bind(R.id.tv_big_small_less_than_75)
    TextView tvBigSmallLessThan75;
    @Bind(R.id.tv_over)
    TextView tv_over;
    @Bind(R.id.tv_under)
    TextView tv_under;
    @Bind(R.id.tv_odd)
    TextView tv_odd;
    @Bind(R.id.tv_even)
    TextView tv_even;
    @Bind(R.id.tv_over_odd)
    TextView tv_over_odd;
    @Bind(R.id.tv_over_even)
    TextView tv_over_even;
    @Bind(R.id.tv_under_odd)
    TextView tv_under_odd;
    @Bind(R.id.tv_under_even)
    TextView tv_under_even;
    @Bind(R.id.tv_range_1st)
    TextView tv_range_1st;
    @Bind(R.id.tv_range_2st)
    TextView tv_range_2st;
    @Bind(R.id.tv_range_3st)
    TextView tv_range_3st;
    @Bind(R.id.tv_range_4st)
    TextView tv_range_4st;
    @Bind(R.id.tv_range_5st)
    TextView tv_range_5st;
    @Bind(R.id.tv_smallest12)
    TextView tv_smallest12;
    @Bind(R.id.tv_smallest35)
    TextView tv_smallest35;
    @Bind(R.id.tv_smallest6)
    TextView tv_smallest6;
    @Bind(R.id.tv_biggest75)
    TextView tv_biggest75;
    @Bind(R.id.tv_biggest7678)
    TextView tv_biggest7678;
    @Bind(R.id.tv_biggest7980)
    TextView tv_biggest7980;
    @Bind(R.id.tv_date_currency)
    TextView tv_date_currency;
    @Bind(R.id.tv_bet_credit)
    TextView tv_bet_credit;
    @Bind(R.id.tv_outstanding)
    TextView tv_outstanding;
    @Bind(R.id.lv_last_game)
    NestListView lv_last_game;
    @Bind(R.id.lv_recent_orders)
    NestListView lv_recent_orders;
    @Bind(R.id.ll_recent_orders)
    LinearLayout ll_recent_orders;
    @Bind(R.id.tv_last_gid)
    TextView tv_last_gid;
    @Bind(R.id.tv_current_gid)
    TextView tv_current_gid;
    @Bind(R.id.tv_last_total)
    TextView tv_last_total;
    @Bind(R.id.tv_time)
    TextView tv_time;
    @Bind(R.id.tv_count_down)
    TextView tv_count_down;
    @Bind(R.id.rl_result)
    RelativeLayout rl_result;
    @Bind(R.id.tv_result_bg)
    TextView tv_result_bg;
    @Bind(R.id.tv_result_index)
    TextView tv_result_index;
    @Bind(R.id.tv_result_num)
    TextView tv_result_num;
    @Bind(R.id.tv_result_total)
    TextView tv_result_total;
    HttpClient httpClient;
    String baseUrl;
    List<NewKenoResultNumBean> numberList;
    List<NewKenoResultNumBean> timesList;
    List<String> roadList;
    QuickBaseAdapter<String> roadAdapter;
    QuickBaseAdapter<NewKenoLastGameBean> lastGamesAdapter;
    QuickBaseAdapter<NewKenoBetListBean.DataBean.BetListBean.ListBeanX> recentOrdersAdapter;
    QuickBaseAdapter<NewKenoResultNumBean> numAdapter;
    QuickBaseAdapter<NewKenoResultNumBean> timesAdapter;
    PopNewKenoBet popNewKenoBet;
    PopNewKenoPrint popNewKenoPrint;
    NewKenoStateBean newKenoStateBean;
    NewKenoGameSetBean newKenoGameSetBean;
    NewKenoHistoryBean newKenoHistoryBean;
    NewKenoBetListBean newKenoBetListBean;
    private List<Integer> idList;
    private List<RelativeLayout> viewList;
    private CountDownTimer countDownTimer;
    private int drawingIndex = 1;
    private boolean isNeedCountDown = true;
    private boolean isCanShowResult = true;
    private String overUnderRoadType = "Over/Under";
    private String oddEvenRoadType = "Odd/Even";
    private String parlayRoadType = "Parlay";
    private String rangeRoadType = "Range";
    private String smallestNumberRoadType = "Smallest Number";
    private String biggestNumberRoadType = "Biggest Number";
    private String currentRoadType;
    private boolean isCanPlaySound = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case refreshAnimationNumber:
                    if (msg.obj != null) {
                        String animationNumber = (String) msg.obj;
                        if (tv_result_num != null) {
                            tv_result_num.setText(animationNumber);
                        }
                    }
                    break;
                case refreshAnimationNumberBg:
                    if (msg.obj != null) {
                        int bg = (int) msg.obj;
                        if (tv_result_num != null) {
                            tv_result_num.setBackgroundResource(bg);
                            if (bg != 0) {
                                playSound(SoundPlayUtils.DRAW_STOP);
                            }
                        }
                    }
                    break;
                case showTotalNumber:
                    NewKenoAnimationBean bean = (NewKenoAnimationBean) msg.obj;
                    if (bean != null) {
                        if (tv_result_total != null) {
                            tv_result_index.setText(bean.getIndex());
                            tv_result_total.setText(bean.getTotal());
                            numAdapter.setList(numberList);
                        }
                    }
                    break;
                case drawingFinish:
                    if (rl_result != null && msg.obj != null) {
                        String result = (String) msg.obj;
                        onShowResultFinish(result);
                    }
                    break;
            }
        }
    };
    Runnable timeUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            String currentTime = DateUtils.getCurrentTime("aa hh:mm:ss");
            tv_time.setText(currentTime);
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_new_keno;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        idList = Arrays.asList(R.id.rl_over, R.id.rl_under, R.id.rl_odd, R.id.rl_even, R.id.rl_over_odd, R.id.rl_over_even, R.id.rl_under_odd, R.id.rl_under_even, R.id.rl_1st,
                R.id.rl_2st, R.id.rl_3st, R.id.rl_4st, R.id.rl_5st, R.id.rl_1_2, R.id.rl_3_5, R.id.rl_6, R.id.rl_75, R.id.rl_76_78, R.id.rl_79_80);
        viewList = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            RelativeLayout view = rootView.findViewById(idList.get(i));
            viewList.add(view);
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new KenoFragmentPresenter(this));
        tvBigSmallLessThan75.setText("<=75");
        tv_date_currency.setText(DateUtils.getCurrentTime("yyyy-MM-dd") + getCurrency());
        httpClient = new HttpClient("");
        currentRoadType = overUnderRoadType;
        initAdapter();
        baseUrl = newKenoActivity.getBaseUrl();
        stateUrl = baseUrl + "api.php?page=keno_new_state_submitter";
        betListUrl = baseUrl + "api.php?page=keno_new_betlist_submitter";
        gameSetUrl = baseUrl + "api.php?page=game_set_submitter";
        historyUrl = baseUrl + "api.php?page=keno_new_history_submitter";
        switch (getRule()) {
            case "4":
                tv_result_bg.setBackgroundResource(R.mipmap.keno4);
                break;
            case "3":
                tv_result_bg.setBackgroundResource(R.mipmap.keno3);
                break;
            case "2":
                tv_result_bg.setBackgroundResource(R.mipmap.keno2);
                break;
        }
        initTime();
        presenter.getStateData();
        presenter.getGameSetData();
        presenter.getHistoryData();
    }

    private void initTime() {
        handler.post(timeUpdateRunnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isCanShowResult = false;
        handler.removeCallbacksAndMessages(null);
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        if (popNewKenoBet != null) {
            popNewKenoBet.closePopupWindow();
        }
        popNewKenoBet = null;
        if (popNewKenoPrint != null) {
            popNewKenoPrint.closePopupWindow();
        }
        popNewKenoPrint = null;
    }

    private void initAdapter() {
        roadAdapter = new QuickBaseAdapter<String>(mContext, R.layout.item_new_keno_road, roadList) {
            @Override
            protected void convert(ViewHolder helper, String item, int position) {
                ImageView imgContent = helper.retrieveView(R.id.img_content);
                if (TextUtils.isEmpty(item)) {
                    imgContent.setBackgroundResource(0);
                } else {
                    if (currentRoadType.equals(overUnderRoadType)) {
                        if (item.equals("1")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_over);
                        } else {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_under);
                        }
                    } else if (currentRoadType.equals(oddEvenRoadType)) {
                        if (item.equals("1")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_odd);
                        } else {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_even);
                        }
                    } else if (currentRoadType.equals(parlayRoadType)) {
                        if (item.equals("1")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_over_odd);
                        } else if (item.equals("2")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_over_even);
                        } else if (item.equals("3")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_under_odd);
                        } else {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_under_even);
                        }
                    } else if (currentRoadType.equals(rangeRoadType)) {
                        if (item.equals("1")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_range_one);
                        } else if (item.equals("2")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_range_two);
                        } else if (item.equals("3")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_range_three);
                        } else if (item.equals("4")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_range_four);
                        } else {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_range_five);
                        }
                    } else if (currentRoadType.equals(smallestNumberRoadType)) {
                        if (item.equals("1-2")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_smallest_one_two);
                        } else if (item.equals("3-5")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_smallest_three_five);
                        } else {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_smallest_six);
                        }
                    } else if (currentRoadType.equals(biggestNumberRoadType)) {
                        if (item.equals("79-80")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_biggest_3);
                        } else if (item.equals("76-78")) {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_biggest_2);
                        } else {
                            imgContent.setBackgroundResource(R.mipmap.new_keno_biggest_1);
                        }
                    }
                }
            }
        };
        gv_road.setAdapter(roadAdapter);
        lastGamesAdapter = new QuickBaseAdapter<NewKenoLastGameBean>(mContext, R.layout.item_new_keno_last_game, new ArrayList<NewKenoLastGameBean>()) {
            @Override
            protected void convert(ViewHolder helper, NewKenoLastGameBean item, int position) {
                TextView tv_content = helper.retrieveView(R.id.tv_content);
                TextView tv_content1 = helper.retrieveView(R.id.tv_content1);
                tv_content.setText(item.getContent());
                tv_content1.setText(item.getResult());
                if ((position + 1) % 2 == 0) {
                    tv_content.setBackgroundColor(0xffF2F6F9);
                    tv_content1.setBackgroundColor(0xffF2F6F9);
                } else {
                    tv_content.setBackgroundColor(0xffffffff);
                    tv_content1.setBackgroundColor(0xffffffff);
                }
            }
        };
        lv_last_game.setAdapter(lastGamesAdapter);
        recentOrdersAdapter = new QuickBaseAdapter<NewKenoBetListBean.DataBean.BetListBean.ListBeanX>(mContext, R.layout.item_new_keno_recent_orders, new ArrayList<NewKenoBetListBean.DataBean.BetListBean.ListBeanX>()) {
            @Override
            protected void convert(ViewHolder helper, final NewKenoBetListBean.DataBean.BetListBean.ListBeanX item, int position) {
                TextView tv_content1 = helper.retrieveView(R.id.tv_content1);
                TextView tv_content2 = helper.retrieveView(R.id.tv_content2);
                TextView tv_content3 = helper.retrieveView(R.id.tv_content3);
                TextView tv_content4 = helper.retrieveView(R.id.tv_content4);
                tv_content1.setText(item.getBetstr());
                tv_content2.setText(item.getReturn_amount());
                String win_loss = item.getWin_loss().toString();
                tv_content3.setTextColor(Color.BLACK);
                if (win_loss.equals("0.0")) {
                    tv_content3.setTextColor(Color.RED);
                } else {
                    tv_content3.setTextColor(Color.BLACK);
                }
                tv_content3.setText("=" + win_loss);
                tv_content4.setText("print");
                tv_content4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popNewKenoPrint == null) {
                            popNewKenoPrint = new PopNewKenoPrint(mContext, gv_road, screenWith / 3 * 2, ViewGroup.LayoutParams.WRAP_CONTENT);
                        }
                        popNewKenoPrint.setData(item.getPrint());
                        popNewKenoPrint.showPopupCenterWindow();
                    }
                });
            }
        };
        lv_recent_orders.setAdapter(recentOrdersAdapter);
        numberList = new ArrayList<>();
        for (int i = 1; i <= 80; i++) {
            numberList.add(new NewKenoResultNumBean(i + "", false));
        }
        numAdapter = new QuickBaseAdapter<NewKenoResultNumBean>(mContext, R.layout.item_new_keno, numberList) {
            @Override
            protected void convert(ViewHolder helper, NewKenoResultNumBean item, int position) {
                TextView tvContent = helper.retrieveView(R.id.tv_content);
                tvContent.setText(item.getNum());
                if (item.isCheck()) {
                    tvContent.setBackgroundColor(0xff066385);
                    tvContent.setTextColor(Color.WHITE);
                } else {
                    tvContent.setBackgroundColor(0xffD0D5E6);
                    tvContent.setTextColor(Color.BLACK);
                }
            }
        };
        gvNumber.setAdapter(numAdapter);
        timesList = new ArrayList<>();
        timesAdapter = new QuickBaseAdapter<NewKenoResultNumBean>(mContext, R.layout.item_new_keno, timesList) {
            @Override
            protected void convert(ViewHolder helper, NewKenoResultNumBean item, int position) {
                TextView tvContent = helper.retrieveView(R.id.tv_content);
                tvContent.setText(item.getNum());
                if (item.isCheck()) {
                    tvContent.setBackgroundColor(Color.RED);
                } else {
                    tvContent.setBackgroundColor(0xffF1EECC);
                }
            }
        };
        gv_times.setAdapter(timesAdapter);
        if (popNewKenoBet == null) {
            popNewKenoBet = new PopNewKenoBet(mContext, gv_times, screenWith / 5 * 4, ViewGroup.LayoutParams.WRAP_CONTENT) {
                @Override
                public void betResult(NewKenoBetResultBean newKenoBetResultBean) {
                    String errMsg = newKenoBetResultBean.getErrMsg();
                    if (errMsg.equals("Not enough balance")) {
                        ToastUtils.showShort(getString(R.string.Balance_not_enough));
                    } else if (errMsg.equals("Success")) {
                        ToastUtils.showShort(getString(R.string.Success));
                    } else {
                        ToastUtils.showShort(errMsg);
                    }
                    if (errMsg.equals("Success")) {
                        clear();
                        closePopupWindow();
                    }
                    dismissBlockDialog();
                }

                @Override
                protected void onCloose() {
                    super.onCloose();
                    playSound(SoundPlayUtils.BET_TOUCH);
                }
            };
        }
        gv_times.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getIsCanBet()) {
                    playSound(SoundPlayUtils.BET_TOUCH);
                    String playType = timesList.get(position).getNum();
                    popNewKenoBet.setData(playType, "Frequence", playType, tv_current_gid.getText().toString(), getCurrentData("1"), position, getRule());
                    popNewKenoBet.showPopupCenterWindow();
                    ll_recent_orders.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick({R.id.tv_over_under_road, R.id.tv_odd_even_road, R.id.tv_parlay_road, R.id.tv_range_road, R.id.tv_smallest_road, R.id.tv_biggest_road, R.id.rl_recent_orders,
            R.id.rl_over, R.id.rl_under, R.id.rl_odd, R.id.rl_even, R.id.rl_over_odd, R.id.rl_over_even, R.id.rl_under_odd, R.id.rl_under_even, R.id.rl_1st,
            R.id.rl_2st, R.id.rl_3st, R.id.rl_4st, R.id.rl_5st, R.id.rl_1_2, R.id.rl_3_5, R.id.rl_6, R.id.rl_75, R.id.rl_76_78, R.id.rl_79_80})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_over_under_road:
                currentRoadType = overUnderRoadType;
                initRoadData();
                break;
            case R.id.tv_odd_even_road:
                currentRoadType = oddEvenRoadType;
                initRoadData();
                break;
            case R.id.tv_parlay_road:
                currentRoadType = parlayRoadType;
                initRoadData();
                break;
            case R.id.tv_range_road:
                currentRoadType = rangeRoadType;
                initRoadData();
                break;
            case R.id.tv_smallest_road:
                currentRoadType = smallestNumberRoadType;
                initRoadData();
                break;
            case R.id.tv_biggest_road:
                currentRoadType = biggestNumberRoadType;
                initRoadData();
                break;
            case R.id.rl_recent_orders:
                int visibility = ll_recent_orders.getVisibility();
                if (visibility == View.GONE) {
                    presenter.getBetListData();
                    ll_recent_orders.setVisibility(View.VISIBLE);
                } else {
                    ll_recent_orders.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_over:
                NewKenoGameSetBean.DataBean currentData1 = getCurrentData("2");
                bet(currentData1.getFactor(), "OverUnder", "Over", currentData1);
                break;
            case R.id.rl_under:
                NewKenoGameSetBean.DataBean currentData2 = getCurrentData("2");
                bet(currentData2.getFactor(), "OverUnder", "Under", currentData2);
                break;
            case R.id.rl_odd:
                NewKenoGameSetBean.DataBean currentData3 = getCurrentData("3");
                bet(currentData3.getFactor(), "OddEven", "Odd", currentData3);
                break;
            case R.id.rl_even:
                NewKenoGameSetBean.DataBean currentData4 = getCurrentData("3");
                bet(currentData4.getFactor(), "OddEven", "Even", currentData4);
                break;
            case R.id.rl_over_odd:
                NewKenoGameSetBean.DataBean currentData5 = getCurrentData("4");
                bet(currentData5.getFactor(), "Parlay", "OverOdd", currentData5);
                break;
            case R.id.rl_over_even:
                NewKenoGameSetBean.DataBean currentData6 = getCurrentData("4");
                bet(currentData6.getFactor(), "Parlay", "OverEven", currentData6);
                break;
            case R.id.rl_under_odd:
                NewKenoGameSetBean.DataBean currentData7 = getCurrentData("4");
                bet(currentData7.getFactor(), "Parlay", "UnderOdd", currentData7);
                break;
            case R.id.rl_under_even:
                NewKenoGameSetBean.DataBean currentData8 = getCurrentData("4");
                bet(currentData8.getFactor(), "Parlay", "UnderEven", currentData8);
                break;
            case R.id.rl_1st:
                NewKenoGameSetBean.DataBean currentData9 = getCurrentData("5");
                bet(currentData9.getFactor(), "Range", "1st", currentData9);
                break;
            case R.id.rl_2st:
                NewKenoGameSetBean.DataBean currentData10 = getCurrentData("5");
                bet(currentData10.getFactor2(), "Range", "2nd", currentData10);
                break;
            case R.id.rl_3st:
                NewKenoGameSetBean.DataBean currentData11 = getCurrentData("5");
                bet(currentData11.getFactor3(), "Range", "3rd", currentData11);
                break;
            case R.id.rl_4st:
                NewKenoGameSetBean.DataBean currentData12 = getCurrentData("5");
                bet(currentData12.getFactor2(), "Range", "4th", currentData12);
                break;
            case R.id.rl_5st:
                NewKenoGameSetBean.DataBean currentData13 = getCurrentData("5");
                bet(currentData13.getFactor(), "Range", "5th", currentData13);
                break;
            case R.id.rl_1_2:
                NewKenoGameSetBean.DataBean currentData14 = getCurrentData("6");
                bet(currentData14.getFactor(), "Smallest", "1-2", currentData14);
                break;
            case R.id.rl_3_5:
                NewKenoGameSetBean.DataBean currentData15 = getCurrentData("6");
                bet(currentData15.getFactor2(), "Smallest", "3-5", currentData15);
                break;
            case R.id.rl_6:
                NewKenoGameSetBean.DataBean currentData16 = getCurrentData("6");
                bet(currentData16.getFactor3(), "Smallest", ">=6", currentData16);
                break;
            case R.id.rl_75:
                NewKenoGameSetBean.DataBean currentData17 = getCurrentData("7");
                bet(currentData17.getFactor3(), "Biggest", "<=75", currentData17);
                break;
            case R.id.rl_76_78:
                NewKenoGameSetBean.DataBean currentData18 = getCurrentData("7");
                bet(currentData18.getFactor2(), "Biggest", "76-78", currentData18);
                break;
            case R.id.rl_79_80:
                NewKenoGameSetBean.DataBean currentData19 = getCurrentData("7");
                bet(currentData19.getFactor(), "Biggest", "79-80", currentData19);
                break;
        }
    }

    private void bet(String factor, String betType, String playType, NewKenoGameSetBean.DataBean currentData) {
        if (getIsCanBet()) {
            playSound(SoundPlayUtils.BET_TOUCH);
            popNewKenoBet.setData(factor, betType, playType, tv_current_gid.getText().toString(), currentData, 0, getRule());
            popNewKenoBet.showPopupCenterWindow();
            ll_recent_orders.setVisibility(View.GONE);
        }
    }

    private void initResultData() {
        if (newKenoStateBean == null || newKenoStateBean.getData() == null) {
            return;
        }
        NewKenoStateBean.DataBean data = newKenoStateBean.getData();
        int totalSec = data.getTotal_sec();
        String gidStr = data.getGid();
        boolean needShowDrawing = presenter.isNeedShowDrawing(getMaxSecond(), totalSec);
        if (isFistEnter && needShowDrawing) {
            setIsCanBet(false);
            isFistEnter = false;
            if (tv_current_gid != null) {
                tv_current_gid.setText("#" + gidStr);
            }
            if (tv_last_gid != null) {
                tv_last_gid.setText("#" + (Integer.parseInt(gidStr) - 1));
            }
            tv_count_down.setTextColor(Color.RED);
            tv_count_down.setText("0");
            drawingIndex = presenter.showIndex(getMaxSecond(), totalSec);
            showResult();
        } else {
            isFistEnter = false;
            if (isNeedCountDown) {
                isNeedCountDown = false;
                int lastGid = Integer.parseInt(gidStr);
                if (tv_last_gid != null) {
                    tv_last_gid.setText("#" + lastGid);
                }
                if (tv_current_gid != null) {
                    tv_current_gid.setText("#" + (lastGid + 1));
                }
                String result = data.getResult();
                String[] splitResult = result.split("\\|");
                if (tv_last_total != null) {
                    tv_last_total.setText(splitResult[0]);
                }
                String s = splitResult[5];
                String[] split = s.split("#");
                for (int i = 0; i < split.length; i++) {
                    numberList.get(Integer.parseInt(split[i]) - 1).setCheck(true);
                }
                numAdapter.setList(numberList);
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
                if (totalSec > 10) {
                    playSound(SoundPlayUtils.BET_PLEASE);
                }
                long countDownTime = totalSec * 1000;
                countDownTimer = new CountDownTimer(countDownTime, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (tv_count_down != null) {
                            long time = millisUntilFinished / 1000;
                            if (time <= 10) {
                                tv_count_down.setTextColor(Color.RED);
                                playSound(SoundPlayUtils.COUNT_DOWN);
                            } else {
                                tv_count_down.setTextColor(Color.WHITE);
                            }
                            tv_count_down.setText(Long.valueOf(time).toString());
                        }
                    }

                    @Override
                    public void onFinish() {
                        if (tv_count_down != null) {
                            tv_count_down.setText("0");
                        }
                        playSound(SoundPlayUtils.GOOD_LUCK);
                        setIsCanBet(false);
                        presenter.getStateData();
                    }
                };
                countDownTimer.start();
            } else {
                drawingIndex = 1;
                showResult();
            }
        }
    }

    private void showWinning(String result) {
        String[] winningArr = result.split("\\|");
        String frequenceResult = winningArr[1];
        String[] frequenceArr = frequenceResult.split("#");
        List<String> list = new ArrayList<>();
        NewKenoGameSetBean.DataBean currentData = getCurrentData("1");
        for (int i = 0; i < frequenceArr.length; i++) {
            String s = frequenceArr[i];
            if (s.equals("1")) {
                list.add(currentData.getFactor());
            } else if (s.equals("2-3")) {
                list.add(currentData.getFactor2());
            } else if (s.equals("4+")) {
                list.add(currentData.getFactor3());
            } else {
                list.add(currentData.getFactor4());
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            A:
            for (int j = i * 4; j < i * 4 + 4; j++) {
                String num = timesList.get(j).getNum();
                if (num.equals(str)) {
                    timesList.get(j).setCheck(true);
                    break A;
                }
            }
        }
        timesAdapter.setList(timesList);
        String otherResult = winningArr[2];
        String[] otherArr = otherResult.split("#");
        if (otherArr[0].equals("1")) {
            viewList.get(0).setBackgroundColor(0xffECB629);
        } else {
            viewList.get(1).setBackgroundColor(0xffECB629);
        }
        if (otherArr[1].equals("1")) {
            viewList.get(2).setBackgroundColor(0xffECB629);
        } else {
            viewList.get(3).setBackgroundColor(0xffECB629);
        }
        if (otherArr[2].equals("1")) {
            viewList.get(4).setBackgroundColor(0xffECB629);
        } else if (otherArr[2].equals("2")) {
            viewList.get(5).setBackgroundColor(0xffECB629);
        } else if (otherArr[2].equals("3")) {
            viewList.get(6).setBackgroundColor(0xffECB629);
        } else {
            viewList.get(7).setBackgroundColor(0xffECB629);
        }
        if (otherArr[3].equals("1")) {
            viewList.get(8).setBackgroundColor(0xffECB629);
        } else if (otherArr[3].equals("2")) {
            viewList.get(9).setBackgroundColor(0xffECB629);
        } else if (otherArr[3].equals("3")) {
            viewList.get(10).setBackgroundColor(0xffECB629);
        } else if (otherArr[3].equals("4")) {
            viewList.get(11).setBackgroundColor(0xffECB629);
        } else {
            viewList.get(12).setBackgroundColor(0xffECB629);
        }
        String smallestResult = winningArr[3];
        if (smallestResult.equals("1-2")) {
            viewList.get(13).setBackgroundColor(0xffECB629);
        } else if (smallestResult.equals("3-5")) {
            viewList.get(14).setBackgroundColor(0xffECB629);
        } else {
            viewList.get(15).setBackgroundColor(0xffECB629);
        }
        String biggestResult = winningArr[4];
        if (biggestResult.equals("<=75")) {
            viewList.get(16).setBackgroundColor(0xffECB629);
        } else if (smallestResult.equals("76-78")) {
            viewList.get(17).setBackgroundColor(0xffECB629);
        } else {
            viewList.get(18).setBackgroundColor(0xffECB629);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < timesList.size(); i++) {
                    timesList.get(i).setCheck(false);
                    timesAdapter.setList(timesList);
                }
                for (int i = 0; i < viewList.size(); i++) {
                    viewList.get(i).setBackgroundColor(0x00000000);
                }
                setIsCanBet(true);
                isNeedCountDown = true;
                presenter.getStateData();
                presenter.getGameSetData();
                presenter.getHistoryData();
            }
        }, 4000);
    }

    private void onShowResultFinish(String result) {
        rl_result.setBackgroundColor(0xff9EA1A3);
        tv_result_bg.setVisibility(View.VISIBLE);
        tv_result_index.setVisibility(View.GONE);
        tv_result_num.setVisibility(View.GONE);
        tv_result_total.setVisibility(View.GONE);
        showWinning(result);
    }

    private void showResult() {
        if (rl_result == null || newKenoStateBean == null) {
            return;
        }
        rl_result.setBackgroundColor(0xffF3E979);
        tv_result_bg.setVisibility(View.GONE);
        tv_result_index.setVisibility(View.VISIBLE);
        tv_result_num.setVisibility(View.VISIBLE);
        tv_result_total.setVisibility(View.VISIBLE);
        ThreadPoolUtils.execute(new Runnable() {
            @Override
            public void run() {
                String result = newKenoStateBean.getData().getResult();
                String[] splitResult = result.split("\\|");
                String s = splitResult[5];
                String[] split = s.split("#");
                int total = 0;
                for (int i = 0; i < numberList.size(); i++) {
                    numberList.get(i).setCheck(false);
                }
                NewKenoAnimationBean initBean = new NewKenoAnimationBean("", "", "");
                handler.sendMessage(handler.obtainMessage(showTotalNumber, initBean));
                if (drawingIndex > 1) {
                    for (int i = 0; i < drawingIndex - 1; i++) {
                        total += Integer.parseInt(split[i]);
                        numberList.get(Integer.parseInt(split[i]) - 1).setCheck(true);
                    }
                    NewKenoAnimationBean bean = new NewKenoAnimationBean((drawingIndex - 1) + "", "", total + "");
                    handler.sendMessage(handler.obtainMessage(showTotalNumber, bean));
                }
                try {
                    A:
                    for (int i = drawingIndex - 1; i < split.length; i++) {
                        if (!isCanShowResult) {
                            break A;
                        }
                        handler.sendMessage(handler.obtainMessage(refreshAnimationNumberBg, 0));
                        B:
                        for (int j = 0; j < 100; j++) {
                            if (!isCanShowResult) {
                                break B;
                            }
                            handler.sendMessage(handler.obtainMessage(refreshAnimationNumber, j + ""));
                            Thread.sleep(12);
                        }
                        handler.sendMessage(handler.obtainMessage(refreshAnimationNumber, split[i] + ""));
                        handler.sendMessage(handler.obtainMessage(refreshAnimationNumberBg, R.mipmap.new_keno_result_bg));
                        total += Integer.parseInt(split[i]);
                        NewKenoAnimationBean bean = new NewKenoAnimationBean(drawingIndex + "", "", total + "");
                        numberList.get(Integer.parseInt(split[i]) - 1).setCheck(true);
                        handler.sendMessage(handler.obtainMessage(showTotalNumber, bean));
                        drawingIndex++;
                        Thread.sleep(800);
                    }
                    if (isCanShowResult) {
                        handler.sendMessage(handler.obtainMessage(drawingFinish, result));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initContentData() {
        if (newKenoGameSetBean == null || newKenoGameSetBean.getData() == null) {
            return;
        }
        NewKenoGameSetBean.DataBean frequenceData = getCurrentData("1");
        timesList.clear();
        for (int i = 0; i < 8; i++) {
            List<NewKenoResultNumBean> dataList = Arrays.asList(new NewKenoResultNumBean(frequenceData.getFactor(), false),
                    new NewKenoResultNumBean(frequenceData.getFactor2(), false),
                    new NewKenoResultNumBean(frequenceData.getFactor3(), false),
                    new NewKenoResultNumBean(frequenceData.getFactor4(), false));
            timesList.addAll(dataList);
        }
        timesAdapter.setList(timesList);
        NewKenoGameSetBean.DataBean overUnderData = getCurrentData("2");
        if (overUnderData != null) {
            if (tv_over != null) {
                tv_over.setText(overUnderData.getFactor());
            }
            if (tv_under != null) {
                tv_under.setText(overUnderData.getFactor());
            }
        }
        NewKenoGameSetBean.DataBean oddEvenData = getCurrentData("3");
        if (oddEvenData != null) {
            if (tv_odd != null) {
                tv_odd.setText(oddEvenData.getFactor());
            }
            if (tv_even != null) {
                tv_even.setText(oddEvenData.getFactor());
            }
        }
        NewKenoGameSetBean.DataBean parlayData = getCurrentData("4");
        if (parlayData != null) {
            if (tv_over_odd != null) {
                tv_over_odd.setText(parlayData.getFactor());
            }
            if (tv_over_even != null) {
                tv_over_even.setText(parlayData.getFactor());
            }
            if (tv_under_odd != null) {
                tv_under_odd.setText(parlayData.getFactor());
            }
            if (tv_under_even != null) {
                tv_under_even.setText(parlayData.getFactor());
            }
        }
        NewKenoGameSetBean.DataBean rangeData = getCurrentData("5");
        if (rangeData != null) {
            if (tv_range_1st != null) {
                tv_range_1st.setText(rangeData.getFactor());
            }
            if (tv_range_2st != null) {
                tv_range_2st.setText(rangeData.getFactor2());
            }
            if (tv_range_3st != null) {
                tv_range_3st.setText(rangeData.getFactor3());
            }
            if (tv_range_4st != null) {
                tv_range_4st.setText(rangeData.getFactor2());
            }
            if (tv_range_5st != null) {
                tv_range_5st.setText(rangeData.getFactor());
            }
        }
        NewKenoGameSetBean.DataBean smallestData = getCurrentData("6");
        if (smallestData != null) {
            if (tv_smallest12 != null) {
                tv_smallest12.setText(smallestData.getFactor());
            }
            if (tv_smallest35 != null) {
                tv_smallest35.setText(smallestData.getFactor2());
            }
            if (tv_smallest6 != null) {
                tv_smallest6.setText(smallestData.getFactor3());
            }
        }
        NewKenoGameSetBean.DataBean biggestData = getCurrentData("7");
        if (biggestData != null) {
            if (tv_biggest75 != null) {
                tv_biggest75.setText(biggestData.getFactor3());
            }
            if (tv_biggest7678 != null) {
                tv_biggest7678.setText(biggestData.getFactor2());
            }
            if (tv_biggest7980 != null) {
                tv_biggest7980.setText(biggestData.getFactor());
            }
        }
    }

    private void initRoadData() {
        if (newKenoHistoryBean == null) {
            return;
        }
        if (roadList == null) {
            roadList = new ArrayList<>();
        }
        roadList.clear();
        for (int i = 1; i <= 120; i++) {
            roadList.add("");
        }
        List<String> dataList = new ArrayList<>();
        if (currentRoadType.equals(overUnderRoadType)) {
            dataList = newKenoHistoryBean.getData().getOver_under();
        } else if (currentRoadType.equals(oddEvenRoadType)) {
            dataList = newKenoHistoryBean.getData().getOdd_even();
        } else if (currentRoadType.equals(parlayRoadType)) {
            dataList = newKenoHistoryBean.getData().getParlay();
        } else if (currentRoadType.equals(rangeRoadType)) {
            dataList = newKenoHistoryBean.getData().getRange();
        } else if (currentRoadType.equals(smallestNumberRoadType)) {
            dataList = newKenoHistoryBean.getData().getSmallest();
        } else if (currentRoadType.equals(biggestNumberRoadType)) {
            dataList = newKenoHistoryBean.getData().getBiggest();
        }
        List<String> list = new ArrayList<>();
        for (int i = dataList.size() - 2; i >= 0; i--) {
            list.add(dataList.get(i));
        }
        int indexTimes = 0;
        int currentIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            String content = list.get(i);
            if (i == 0) {
                currentIndex = 0;
                roadList.set(i, content);
            } else {
                int lastContentIndex = i - 1;
                String lastContent = list.get(lastContentIndex);
                if (content.equals(lastContent)) {
                    indexTimes += 1;
                    if (indexTimes > 5) {
                        indexTimes = 5;
                    }
                    int dataIndex = currentIndex + (20 * indexTimes);
                    String s = roadList.get(dataIndex);
                    if (TextUtils.isEmpty(s)) {
                        roadList.set(dataIndex, content);
                    } else {
                        A:
                        while (indexTimes > 0) {
                            String strContent = roadList.get(dataIndex);
                            if (strContent.equals(content)) {
                                for (int j = dataIndex; j < roadList.size(); j++) {
                                    String strContent1 = roadList.get(j);
                                    if (TextUtils.isEmpty(strContent1)) {
                                        roadList.set(j, content);
                                        break;
                                    }
                                }
                                break A;
                            } else {
                                indexTimes--;
                                dataIndex -= 20;
                            }
                        }
                    }
                } else {
                    indexTimes = 0;
                    for (int j = 0; j < roadList.size(); j++) {
                        String s = roadList.get(j);
                        if (TextUtils.isEmpty(s)) {
                            currentIndex = j;
                            break;
                        }
                    }
                    roadList.set(currentIndex, content);
                }
            }
        }
        roadAdapter.setList(roadList);
    }

    private void initRecentOrders() {
        if (newKenoBetListBean == null || newKenoBetListBean.getData() == null) {
            return;
        }
        NewKenoBetListBean.DataBean data = newKenoBetListBean.getData();
        if (tv_bet_credit != null) {
            tv_bet_credit.setText(data.getBalance());
        }
        if (tv_outstanding != null) {
            tv_outstanding.setText(data.getOutstanding());
        }
        List<NewKenoLastGameBean> lastGamesList = new ArrayList<>();
        List<NewKenoBetListBean.DataBean.ListBean> list = data.getList();
        for (int i = 0; i < list.size() - 1; i++) {
            NewKenoBetListBean.DataBean.ListBean listBean = list.get(i);
            String[] split = listBean.getResult().split("\\|");
            lastGamesList.add(new NewKenoLastGameBean(DateUtils.getCurrentTime("MMdd") + "#" + listBean.getLast_game_id() + " " + listBean.getGMT7(), split[0]));
        }
        lastGamesAdapter.setList(lastGamesList);
        List<NewKenoBetListBean.DataBean.BetListBean> betList = data.getBet_list();
        List<NewKenoBetListBean.DataBean.BetListBean.ListBeanX> listData = new ArrayList<>();
        if (betList != null && betList.size() > 0) {
            for (int i = 0; i < betList.size(); i++) {
                NewKenoBetListBean.DataBean.BetListBean betListBean = betList.get(i);
                List<NewKenoBetListBean.DataBean.BetListBean.ListBeanX> list1 = betListBean.getList();
                for (int j = 0; j < list1.size(); j++) {
                    listData.add(list1.get(j));
                }
            }
            recentOrdersAdapter.setList(listData);
        } else {
            recentOrdersAdapter.setList(new ArrayList<NewKenoBetListBean.DataBean.BetListBean.ListBeanX>());
        }
    }

    private NewKenoGameSetBean.DataBean getCurrentData(String type) {
        for (int i = 0; i < newKenoGameSetBean.getData().size(); i++) {
            NewKenoGameSetBean.DataBean dataBean = newKenoGameSetBean.getData().get(i);
            String type3 = dataBean.getType3();
            if (type3.equals(type)) {
                return dataBean;
            }
        }
        return null;
    }

    public void onGetGameSetData(NewKenoGameSetBean newKenoGameSetBean) {
        this.newKenoGameSetBean = newKenoGameSetBean;
        initContentData();
    }

    public void onGetHistoryData(NewKenoHistoryBean newKenoHistoryBean) {
        this.newKenoHistoryBean = newKenoHistoryBean;
        initRoadData();
    }

    public void onGetStateData(NewKenoStateBean newKenoStateBean) {
        this.newKenoStateBean = newKenoStateBean;
        if (newKenoStateBean == null || newKenoStateBean.getData() == null) {
            presenter.getStateData();
        } else {
            initResultData();
        }
    }

    public void onGetBetListData(NewKenoBetListBean newKenoBetListBean) {
        this.newKenoBetListBean = newKenoBetListBean;
        initRecentOrders();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            isCanPlaySound = false;
        } else {
            isCanPlaySound = true;
        }
    }

    public void onGetDataFailed() {
        ToastUtils.showShort(getString(R.string.enter_again));
        newKenoActivity.finish();
    }

    private void playSound(int type) {
        if (tv_over != null && isCanPlaySound) {
            SoundPlayUtils.play(type);
        }
    }
}
