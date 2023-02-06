package nanyang.com.dig88.ThaiLottery;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Adapter.MyFragmentPagerAdapter;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.GameBaseActivity;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.base.quick.QuickRequestBean;
import xs.com.mylibrary.myview.indicator.PagerSlidingTabStrip;

/**
 * Created by Administrator on 2017/7/19.
 */

public class ThaiLotteryActivity extends GameBaseActivity {
    //    private BaseFragment hotNumberFragment = new HotNumberFragment();
    protected NyVolleyJsonThreadHandler<List<DigGameOddsBean>> gameStateThread;
    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabStrip;
    @Bind(R.id.pager)
    ViewPager lotteryViewPager;
    @Bind(R.id.total_amount_tv)
    TextView total_amount_tv;
    @Bind(R.id.bet_total_amount_tv)
    TextView bet_total_amount_tv;
    @Bind(R.id.lottery_bottom_submit_btn)
    Button lottery_bottom_submit_btn;
    @Bind(R.id.lottery_bottom_bet_fl)
    LinearLayout lottery_bottom_bet_fl;
    private List<BaseFragment> fragmentsList;
    private ThaiLotteryBaseFragment indexFragment;
    private List<DigGameOddsBean> thaiLottertGameList;
    private BaseFragment thaiFastBetFragment = new ThaiLotteryFastBetFragment();
    private BaseFragment thai1DFragment = new ThaiLottery1DFragment();
    private BaseFragment thai2DFragment = new ThaiLottery2DFragment();
    private BaseFragment thai3DFragment = new ThaiLottery3DFragment();
    private BaseFragment thaiBigSmallFragment = new ThaiLotteryBigSmallFragment();
    private BaseFragment thaiResultFragment = new ThaiLotteryResultFragment();
    private BaseFragment thaiBetListFragment = new ThaiLotteryBetListFragment();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_thai_lottery;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        String lg = AppTool.getAppLanguage(mContext);
        AppTool.setAppLanguage(mContext, lg);
        setTitle("Thai " + getString(R.string.tw_game));
        thaiFastBetFragment.setTitle(mContext.getString(R.string.fast_bet));
        thai1DFragment.setTitle(mContext.getString(R.string.D1));
        thai2DFragment.setTitle(mContext.getString(R.string.D2));
        thai3DFragment.setTitle(mContext.getString(R.string.D3));
        thaiBigSmallFragment.setTitle(getString(R.string.big_small));
        thaiResultFragment.setTitle(getString(R.string.jieguolist));
        thaiBetListFragment.setTitle(getString(R.string.bet_list));
//        hotNumberFragment.setTitle("Hot Number");
        fragmentsList = new ArrayList<>(Arrays.asList(thaiFastBetFragment, thai1DFragment, thai2DFragment, thai3DFragment, thaiBigSmallFragment, /*hotNumberFragment,*/ thaiBetListFragment, thaiResultFragment));
        indexFragment = (ThaiLotteryBaseFragment) thaiFastBetFragment;
        final MyFragmentPagerAdapter pageAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList);
        lotteryViewPager.setAdapter(pageAdapter);
        tabStrip.setBackgroundColor(Color.BLACK);
        tabStrip.setSelectedBgColor(R.color.select_bg);
        tabStrip.setTextSize(12);
        tabStrip.setTextColorResource(R.color.white);
        tabStrip.setBackGroundColorRes(R.color.black);
        tabStrip.setViewPager(lotteryViewPager);
        lotteryViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lottery_bottom_bet_fl.setVisibility(View.VISIBLE);
                if (position == pageAdapter.getCount() - 1 || position == pageAdapter.getCount() - 2) {
                    lottery_bottom_bet_fl.setVisibility(View.GONE);
                }
                indexFragment = (ThaiLotteryBaseFragment) pageAdapter.getItem(position);
                updataFragment();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initLotteryGame();
        lottery_bottom_submit_btn.setText(getString(R.string.submit_sure));
        lottery_bottom_submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexFragment.betSubmit();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }

    private void initLotteryGame() {
        if (thaiLottertGameList == null) {
            thaiLottertGameList = new ArrayList<>();
        }
        gameStateThread = new NyVolleyJsonThreadHandler<List<DigGameOddsBean>>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", getUserInfoBean().getUser_id());
                params.put("session_id", getUserInfoBean().getSession_id());
                return new QuickRequestBean(WebSiteUrl.GameSetSubmitter, params
                        , new TypeToken<NyBaseResponse<List<DigGameOddsBean>>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, List<DigGameOddsBean> data) {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getType1().equals("14") && data.get(i).getType2().equals("97")) {
                        thaiLottertGameList.add(data.get(i));
                    }
                }
                updataFragment();
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
            }

            @Override
            public void startThread(Integer obj) {
                super.startThread(obj);
            }
        };
        gameStateThread.startThread(null);
    }

    private void updataFragment() {
        indexFragment.updataGameState();
    }

    public List<DigGameOddsBean> getThaiLotteryDataList() {
        return thaiLottertGameList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Dig88Utils.setLang(mContext);
    }
}
