package nanyang.com.dig88.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.Activity.IbetPokerActivity;
import nanyang.com.dig88.Activity.gd88StatementActivity;
import nanyang.com.dig88.Adapter.CompleteReportFormAdapter;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.Afb1188StatementBean;
import nanyang.com.dig88.Entity.AfbSportsStatementBean;
import nanyang.com.dig88.Entity.AgStatementBean;
import nanyang.com.dig88.Entity.AllBetStatementBean;
import nanyang.com.dig88.Entity.BestGameStatementBean;
import nanyang.com.dig88.Entity.CockfightStatementBean;
import nanyang.com.dig88.Entity.CompleteReportFormBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.Dg99StatementBean;
import nanyang.com.dig88.Entity.EvoStatementBean;
import nanyang.com.dig88.Entity.FFYLStatementBean;
import nanyang.com.dig88.Entity.ForexStatementBean;
import nanyang.com.dig88.Entity.GoldStatementBean;
import nanyang.com.dig88.Entity.HabaStatementBean;
import nanyang.com.dig88.Entity.JokerGameStatementBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.Lottery4DStatementBean;
import nanyang.com.dig88.Entity.MgSlotsStatementBean;
import nanyang.com.dig88.Entity.NewKenoStatementBean;
import nanyang.com.dig88.Entity.PPlayStatementBean;
import nanyang.com.dig88.Entity.PtStatementBean;
import nanyang.com.dig88.Entity.SagamingStatementBean;
import nanyang.com.dig88.Entity.SexyCasinoStatementBean;
import nanyang.com.dig88.Entity.StatementInfoBean;
import nanyang.com.dig88.Entity.W88StatementBean;
import nanyang.com.dig88.Fragment.Presenter.CompleteRecordPresenter;
import nanyang.com.dig88.NewKeno.NewKenoActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseListPopWindow;
import nanyang.com.dig88.Util.CalendarPopuWindow;
import nanyang.com.dig88.Util.DateUtils;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2015/12/21.
 */
public class CompleteRecordFragment extends BaseFragment<CompleteRecordPresenter> {
    @Bind(R.id.lv_deposit)
    ListView lv_deposit;
    @Bind(R.id.tv_choice_statement)
    TextView tv_choice_statement;
    @Bind(R.id.tv_start_time)
    TextView tv_start_time;
    @Bind(R.id.tv_end_time)
    TextView tv_end_time;
    @Bind(R.id.btn_sure)
    Button btn_sure;
    @Bind(R.id.ll_go_game)
    LinearLayout llGoGame;
    @Bind(R.id.btn_go_game)
    Button btnGoGame;
    String currentGameType;
    String currentGameUrl;
    CalendarPopuWindow startPop;
    CalendarPopuWindow endPop;
    LoginInfoBean loginInfoBean;
    List<StatementInfoBean> gameContentList;
    List<String> gameNameList;
    BaseListPopWindow popSwitchGame;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_complete_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new CompleteRecordPresenter(this));
        if (BuildConfig.FLAVOR.equals("ibet567")) {
            getAct().setTitle(getString(R.string.afb1188_sports) + " " + getString(R.string.wancenjilu));
        } else {
            getAct().setTitle(getString(R.string.wancenjilu));
        }
        getAct().setleftViewEnable(true);
        gameContentList = presenter.getGameContentList();
        gameNameList = new ArrayList<>();
        for (int i = 0; i < gameContentList.size(); i++) {
            StatementInfoBean statementInfoBean = gameContentList.get(i);
            gameNameList.add(statementInfoBean.getGameName());
        }
        tv_choice_statement.setText(gameNameList.get(0));
        loginInfoBean = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        if (BuildConfig.FLAVOR.equals("ibet567")) {
            currentGameType = AppConfig.Statement_afb1188;
            currentGameUrl = WebSiteUrl.Afb1188StatementUrl;
        } else {
            currentGameType = AppConfig.Statement_statement;
            currentGameUrl = WebSiteUrl.StatementsList;
        }
        tv_start_time.setText(DateUtils.getCurrentTime("yyyy-MM-dd") + " 00:00");
        tv_end_time.setText(DateUtils.getCurrentTime("yyyy-MM-dd") + " 23:59");
        search();
    }

    public void search() {
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        p.put("start_date", tv_start_time.getText().toString());
        p.put("end_date", tv_end_time.getText().toString());
        presenter.getListData(currentGameUrl, p, currentGameType);
    }

    @OnClick({R.id.tv_start_time, R.id.tv_end_time, R.id.btn_sure, R.id.tv_choice_statement, R.id.btn_go_game})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choice_statement:
                if (popSwitchGame == null) {
                    popSwitchGame = new BaseListPopWindow(mContext, tv_choice_statement, tv_choice_statement.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                        @Override
                        public List<String> getContentData() {
                            return gameNameList;
                        }

                        @Override
                        public void onClickItem(int position, String item) {
                            llGoGame.setVisibility(View.GONE);
                            tv_choice_statement.setText(item);
                            StatementInfoBean statementInfoBean = gameContentList.get(position);
                            String url = statementInfoBean.getUrl();
                            currentGameType = statementInfoBean.getGameType();
                            currentGameUrl = url;
                            if (currentGameType.equals(AppConfig.Statement_gd88Casino)) {
                                lv_deposit.setVisibility(View.GONE);
                            } else if (currentGameType.equals(AppConfig.Statement_Keno)) {
                                btnGoGame.setText(getString(R.string.keno));
                                llGoGame.setVisibility(View.VISIBLE);
                                lv_deposit.setVisibility(View.GONE);
                            } else if (currentGameType.equals(AppConfig.Statement_Ongdo)) {
                                btnGoGame.setText(getString(R.string.ongdo_poker));
                                llGoGame.setVisibility(View.VISIBLE);
                                lv_deposit.setVisibility(View.GONE);
                            }
                            if (BuildConfig.FLAVOR.equals("ibet567")) {
                                getAct().setTitle(item + " " + getString(R.string.wancenjilu));
                            }
                        }
                    };
                }
                popSwitchGame.showPopupDownWindow();
                break;
            case R.id.tv_start_time:
                startPop = new CalendarPopuWindow(mContext, view, screenWidth / 10 * 9, screenWidth / 10 * 9) {
                    @Override
                    public void getChoiceDateStr(String date) {
                        tv_start_time.setText(date);
                    }
                };
                startPop.showPopupCenterWindowBlack();
                break;
            case R.id.tv_end_time:
                endPop = new CalendarPopuWindow(mContext, view, screenWidth / 10 * 9, screenWidth / 10 * 9) {
                    @Override
                    public void getChoiceDateStr(String date) {
                        tv_end_time.setText(date);
                    }
                };
                endPop.showPopupCenterWindowBlack();
                break;
            case R.id.btn_sure:
                if (currentGameType.equals(AppConfig.Statement_Keno) || currentGameType.equals(AppConfig.Statement_Ongdo)) {
                    return;
                }
                if (currentGameType.equals(AppConfig.Statement_gd88Casino)) {
                    currentGameUrl += WebSiteUrl.WebId + "s" + loginInfoBean.getUsername();
                    Intent intent = new Intent(mContext, gd88StatementActivity.class);
                    intent.putExtra("url", currentGameUrl);
                    startActivity(intent);
                } else {
                    search();
                }
                break;
            case R.id.btn_go_game:
                if (currentGameType.equals(AppConfig.Statement_Keno)) {
                    startActivity(new Intent(mContext, NewKenoActivity.class));
                } else if (currentGameType.equals(AppConfig.Statement_Ongdo)) {
                    Intent intent = new Intent(mContext, IbetPokerActivity.class);
                    ContentInfoBean contentInfoBean = new ContentInfoBean();
                    contentInfoBean.setContent(getString(R.string.ongdo_poker));
                    contentInfoBean.setContentId("1");
                    intent.putExtra(AppConfig.Ibet567_Poker, contentInfoBean);
                    startActivity(intent);
                }
                break;
        }
    }

    public void onGetStatementData(List<CompleteReportFormBean> beanList) {
        CompleteReportFormAdapter completeReportFormAdapter = new CompleteReportFormAdapter(getActivity(), beanList);
        lv_deposit.setAdapter(completeReportFormAdapter);
    }

    public void onGetNewKenoStatementData(final NewKenoStatementBean newKenoStatementBean) {
        QuickBaseAdapter<NewKenoStatementBean.DataBean> adapter = new QuickBaseAdapter<NewKenoStatementBean.DataBean>(mContext, R.layout.item_complete_record_sexy_casino, newKenoStatementBean.getData()) {
            @Override
            protected void convert(ViewHolder helper, NewKenoStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_betType = helper.retrieveView(R.id.tv_betType);
                    TextView tv_tableId = helper.retrieveView(R.id.tv_tableId);
                    TextView tv_bet_amount = helper.retrieveView(R.id.tv_bet_amount);
                    TextView tv_odds = helper.retrieveView(R.id.tv_odds);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    TextView tv_result = helper.retrieveView(R.id.tv_result);
                    TextView tv_period = helper.retrieveView(R.id.tv_period);
                    TextView tv_status = helper.retrieveView(R.id.tv_status);
                    TextView tv_total = helper.retrieveView(R.id.tv_total);
                    tv_no.setText("No:" + (position + 1));
                    tv_betType.setText(getString(R.string.youxitype) + "NEW KENO");
                    tv_tableId.setText(getString(R.string.bet_detail) + item.getBet_detail());
                    tv_bet_amount.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount());
                    tv_odds.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWin_loss());
                    tv_result.setText(getString(R.string.result) + ":" + item.getResult());
                    tv_period.setText(getString(R.string.qihao1) + item.getPeriod());
                    tv_status.setText(getString(R.string.wanpeilv) + item.getFactor());
                    if (position == newKenoStatementBean.getData().size() - 1) {
                        tv_total.setVisibility(View.VISIBLE);
                        tv_total.setText(getString(R.string.total) + newKenoStatementBean.getWin_loss());
                    } else {
                        tv_total.setVisibility(View.GONE);
                    }
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetAllBetStatementData(final AllBetStatementBean allBetStatementBean) {
        QuickBaseAdapter<AllBetStatementBean.DataBean> adapter = new QuickBaseAdapter<AllBetStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, allBetStatementBean.getData()) {
            @Override
            protected void convert(ViewHolder helper, AllBetStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    TextView tv_total = helper.retrieveView(R.id.tv_total);
                    tv_no.setText("No:" + (position + 1));
                    String gameType = item.getGame_type();
                    switch (gameType) {
                        case "101":
                            gameType = "Normal Baccarat";
                            break;
                        case "102":
                            gameType = "VIP Baccarat";
                            break;
                        case "103":
                            gameType = "Rapid Baccarat";
                            break;
                        case "104":
                            gameType = "Jing Mi Baccarat";
                            break;
                        case "106":
                            gameType = "Treasure Baccarat";
                            break;
                        case "201":
                            gameType = "Sic Bo";
                            break;
                        case "301":
                            gameType = "Dragon Tiger";
                            break;
                        case "401":
                            gameType = "Roulette";
                            break;
                        case "801":
                            gameType = "Bull Bull";
                            break;
                        case "901":
                            gameType = "Win Three Cards";
                            break;
                    }
                    tv_leixing.setText(getString(R.string.youxitype) + gameType);
                    tv_youxi.setText(getString(R.string.qihao1) + item.getTableName());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWinloss());
                    if (position == allBetStatementBean.getData().size() - 1) {
                        tv_total.setVisibility(View.VISIBLE);
                        tv_total.setText(getString(R.string.total) + allBetStatementBean.getWin_loss());
                    } else {
                        tv_total.setVisibility(View.GONE);
                    }
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetFfylStatementData(List<FFYLStatementBean.DataBean> ffylList) {
        QuickBaseAdapter<FFYLStatementBean.DataBean> adapter = new QuickBaseAdapter<FFYLStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, ffylList) {
            @Override
            protected void convert(ViewHolder helper, FFYLStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame_name());
                    tv_youxi.setText(getString(R.string.bet_detail) + item.getTrs_id());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWin_loss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetEvoStatementData(List<EvoStatementBean.DataBean> evoList) {
        QuickBaseAdapter<EvoStatementBean.DataBean> adapter = new QuickBaseAdapter<EvoStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, evoList) {
            @Override
            protected void convert(ViewHolder helper, EvoStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame_type());
                    tv_youxi.setText(getString(R.string.bet_detail) + item.getTable_id());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWin_loss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetPPlayStatementData(List<PPlayStatementBean.DataBean> ppList) {
        QuickBaseAdapter<PPlayStatementBean.DataBean> adapter = new QuickBaseAdapter<PPlayStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, ppList) {
            @Override
            protected void convert(ViewHolder helper, PPlayStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGameid());
                    tv_youxi.setText(getString(R.string.qihao1) + item.getTrs_id());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getResult_win_loss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetCocfightStatementData(List<CockfightStatementBean.DataBean> cockfightList, final String gameType) {
        QuickBaseAdapter<CockfightStatementBean.DataBean> adapter = new QuickBaseAdapter<CockfightStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, cockfightList) {
            @Override
            protected void convert(ViewHolder helper, CockfightStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame_id());
                    tv_youxi.setText(getString(R.string.bet_detail) + item.getTableName());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWinloss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetAfb1188StatementData(List<Afb1188StatementBean.DataBean> afb1188List, final String gameType) {
        QuickBaseAdapter<Afb1188StatementBean.DataBean> adapter = new QuickBaseAdapter<Afb1188StatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, afb1188List) {
            @Override
            protected void convert(ViewHolder helper, Afb1188StatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + getString(R.string.afb1188_sports));
                    tv_youxi.setText(getString(R.string.bet_detail) + item.getGame_id());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWinloss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetForexStatementData(List<ForexStatementBean.DataBean> forexList) {
        QuickBaseAdapter<ForexStatementBean.DataBean> adapter = new QuickBaseAdapter<ForexStatementBean.DataBean>(mContext, R.layout.item_complete_record_forex, forexList) {
            @Override
            protected void convert(ViewHolder helper, ForexStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_wanfa = helper.retrieveView(R.id.tv_wanfa);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_peilv = helper.retrieveView(R.id.tv_peilv);
                    TextView tv_jieguo = helper.retrieveView(R.id.tv_jieguo);
                    TextView tv_shuying = helper.retrieveView(R.id.tv_shuying);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame());
                    tv_wanfa.setText(getString(R.string.wanfa1) + item.getType());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujinee) + ":" + item.getBet_amount());
                    tv_peilv.setText(getString(R.string.wanpeilv) + item.getFactor());
                    tv_jieguo.setText(getString(R.string.jieguo) + ":" + item.getBet_rate() + "-" + item.getDraw_rate());
                    tv_shuying.setText(getString(R.string.shuying) + item.getWin_loss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetW88StatementData(List<W88StatementBean.DataBean> w88List) {
        QuickBaseAdapter<W88StatementBean.DataBean> adapter = new QuickBaseAdapter<W88StatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, w88List) {
            @Override
            protected void convert(ViewHolder helper, W88StatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount() == -1) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame());
                    tv_youxi.setText(getString(R.string.youxi1));
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWinloss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetGoldStatementData(final GoldStatementBean goldStatementBean) {
        QuickBaseAdapter<GoldStatementBean.DataBean> adapter = new QuickBaseAdapter<GoldStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, goldStatementBean.getData()) {
            @Override
            protected void convert(ViewHolder helper, GoldStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_time().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    TextView tv_total = helper.retrieveView(R.id.tv_total);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame());
                    tv_youxi.setText(getString(R.string.youxi1) + item.getPool());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.deal_num) + item.getTrs_id());
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWin_loss());
                    if (position == goldStatementBean.getData().size() - 1) {
                        tv_total.setVisibility(View.VISIBLE);
                        tv_total.setText(getString(R.string.total) + goldStatementBean.getWin_loss());
                    } else {
                        tv_total.setVisibility(View.GONE);
                    }
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetMgStatementData(List<MgSlotsStatementBean.DataBean> mgList) {
        QuickBaseAdapter<MgSlotsStatementBean.DataBean> adapter = new QuickBaseAdapter<MgSlotsStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, mgList) {
            @Override
            protected void convert(ViewHolder helper, MgSlotsStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame());
                    tv_youxi.setText(getString(R.string.youxi1) + item.getPool());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    if (item.getBet_amount() == null) {
                        tv_xiazhujie.setText(getString(R.string.xiazhujine));
                    } else {
                        tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    }
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWin_loss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetBestGameStatementData(List<BestGameStatementBean.DataBean> bestGameList) {
        QuickBaseAdapter<BestGameStatementBean.DataBean> adapter = new QuickBaseAdapter<BestGameStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, bestGameList) {
            @Override
            protected void convert(ViewHolder helper, BestGameStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_time().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame());
                    tv_youxi.setText(getString(R.string.youxi1) + item.getPool());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.wanfa) + ":" + item.getBet_type());
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWin_loss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetAfbStatementData(List<AfbSportsStatementBean.DataBean> afbList, final String gameType) {
        QuickBaseAdapter<AfbSportsStatementBean.DataBean> adapter = new QuickBaseAdapter<AfbSportsStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, afbList) {
            @Override
            protected void convert(ViewHolder helper, AfbSportsStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    tv_leixing.setVisibility(View.GONE);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + getString(R.string.afb_sport_betting));
                    if (gameType.equals(AppConfig.Statement_afbSports)) {
                        tv_youxi.setText(getString(R.string.youxi1) + getString(R.string.afb_sport_betting));
                    } else {
                        tv_youxi.setText(getString(R.string.youxi1) + getString(R.string.list2_saba));
                    }
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWinloss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetSaStatementData(List<SagamingStatementBean.DataBean> saList) {
        QuickBaseAdapter<SagamingStatementBean.DataBean> adapter = new QuickBaseAdapter<SagamingStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, saList) {
            @Override
            protected void convert(ViewHolder helper, SagamingStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame_type());
                    tv_youxi.setText(getString(R.string.youxi) + "ID: " + item.getGame_id());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWin_loss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetDg99StatementData(List<Dg99StatementBean.DataBean> dg99List, final String gameType) {
        QuickBaseAdapter<Dg99StatementBean.DataBean> adapter = new QuickBaseAdapter<Dg99StatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, dg99List) {
            @Override
            protected void convert(ViewHolder helper, Dg99StatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    if (gameType.equals(AppConfig.Statement_dg99Casino)) {
                        tv_leixing.setText(getString(R.string.youxitype) + item.getGame_name());
                        tv_youxi.setText(getString(R.string.youxi) + "ID: " + item.getTableName());
                    } else {
                        tv_leixing.setText(getString(R.string.youxitype) + item.getSport_type());
                        tv_youxi.setText(getString(R.string.youxi) + "ID: " + item.getGame_id());
                    }
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_xiazhujie.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount() + "");
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWinloss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetAgStatementData(List<AgStatementBean.DataBean> agList) {
        QuickBaseAdapter<AgStatementBean.DataBean> adapter = new QuickBaseAdapter<AgStatementBean.DataBean>(mContext, R.layout.item_complete_record_forex, agList) {
            @Override
            protected void convert(ViewHolder helper, AgStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_wanfa = helper.retrieveView(R.id.tv_wanfa);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_peilv = helper.retrieveView(R.id.tv_peilv);
                    TextView tv_jieguo = helper.retrieveView(R.id.tv_jieguo);
                    TextView tv_shuying = helper.retrieveView(R.id.tv_shuying);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGametype());
                    tv_wanfa.setText(getString(R.string.youxi1) + item.getPlatType());
                    tv_bet_time.setText(getString(R.string.youxi) + "ID: " + item.getGameid());
                    tv_xiazhujie.setText(getString(R.string.xiazhushijian) + ":" + item.getBet_time());
                    tv_peilv.setText("Table ID: " + item.getTableid());
                    tv_jieguo.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount());
                    tv_shuying.setText(getString(R.string.shuying) + item.getWin_loss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetHabaStatementData(List<HabaStatementBean.DataBean> habaList) {
        QuickBaseAdapter<HabaStatementBean.DataBean> adapter = new QuickBaseAdapter<HabaStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, habaList) {
            @Override
            protected void convert(ViewHolder helper, HabaStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_time().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getTrs_type());
                    tv_youxi.setText(getString(R.string.youxi) + ": " + item.getGamename());
                    tv_bet_time.setText(getString(R.string.qihao1) + item.getGame_no());
                    tv_xiazhujie.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWinorloss());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetJokerStatementData(List<JokerGameStatementBean.DataBean> jokerList) {
        QuickBaseAdapter<JokerGameStatementBean.DataBean> adapter = new QuickBaseAdapter<JokerGameStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, jokerList) {
            @Override
            protected void convert(ViewHolder helper, JokerGameStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    String gameName;
                    Object game_name = item.getGame_name();
                    if (game_name == null) {
                        gameName = "Ocean Paradise";
                    } else {
                        gameName = game_name.toString();
                    }
                    tv_leixing.setText(getString(R.string.youxitype) + gameName);
                    tv_youxi.setText(getString(R.string.youxi) + ": " + item.getType());
                    tv_bet_time.setText("TRS ID : " + item.getTrs_id());
                    tv_xiazhujie.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_win_lose.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetPtStatementData(List<PtStatementBean.DataBean> ptList) {
        QuickBaseAdapter<PtStatementBean.DataBean> adapter = new QuickBaseAdapter<PtStatementBean.DataBean>(mContext, R.layout.item_complete_record_w88, ptList) {
            @Override
            protected void convert(ViewHolder helper, PtStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getGamedate().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_youxi = helper.retrieveView(R.id.tv_youxi);
                    TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                    TextView tv_xiazhujie = helper.retrieveView(R.id.tv_xiazhujie);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getBet_type());
                    tv_youxi.setText(getString(R.string.youxi) + ": " + item.getGamename());
                    tv_bet_time.setText(getString(R.string.qihao1) + item.getGameid());
                    tv_xiazhujie.setText(getString(R.string.xiazhushijian) + item.getGamedate());
                    tv_win_lose.setText(getString(R.string.shuying) + item.getBet());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGetSexyCasinoStatementData(List<SexyCasinoStatementBean.DataBean> sexyList) {
        QuickBaseAdapter<SexyCasinoStatementBean.DataBean> adapter = new QuickBaseAdapter<SexyCasinoStatementBean.DataBean>(mContext, R.layout.item_complete_record_sexy_casino, sexyList) {
            @Override
            protected void convert(ViewHolder helper, SexyCasinoStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_betType = helper.retrieveView(R.id.tv_betType);
                    TextView tv_tableId = helper.retrieveView(R.id.tv_tableId);
                    TextView tv_bet_amount = helper.retrieveView(R.id.tv_bet_amount);
                    TextView tv_odds = helper.retrieveView(R.id.tv_odds);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    TextView tv_result = helper.retrieveView(R.id.tv_result);
                    TextView tv_period = helper.retrieveView(R.id.tv_period);
                    TextView tv_status = helper.retrieveView(R.id.tv_status);
                    tv_no.setText("No:" + (position + 1));
                    tv_betType.setText(getString(R.string.BetType) + ":" + item.getCategory());
                    tv_tableId.setText(getString(R.string.Table_Id) + ":" + item.getTable_id());
                    tv_bet_amount.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount());
                    tv_odds.setText(getString(R.string.wanpeilv) + item.getOdds());
                    tv_win_lose.setText(getString(R.string.shuying) + item.getWin_loss());
                    String result = item.getResult();
                    result = result.replace("[", "").replace("]", "").replace("\"", "");
                    String[] split = result.split(",");
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < split.length; i++) {
                        String s = split[i];
                        if (i == 0) {
                            sb.append("P-");
                        } else if (i == 3) {
                            sb.append(" B-");
                        }
                        sb.append(" ");
                        String substring = s.substring(1, s.length());
                        switch (substring) {
                            case "11":
                                substring = "J";
                                break;
                            case "12":
                                substring = "Q";
                                break;
                            case "13":
                                substring = "K";
                                break;
                        }
                        sb.append(substring);
                    }
                    tv_result.setText(getString(R.string.result) + ":" + sb.toString());
                    tv_period.setText(getString(R.string.period) + ":" + item.getTransfer_id());
                    tv_status.setText(getString(R.string.state) + ":" + item.getType());
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void onGet4dLotteryStatementData(List<Lottery4DStatementBean.DataBean> lottery4dList) {
        QuickBaseAdapter<Lottery4DStatementBean.DataBean> adapter = new QuickBaseAdapter<Lottery4DStatementBean.DataBean>(mContext, R.layout.item_complete_record_lottery_four_d, lottery4dList) {
            @Override
            protected void convert(ViewHolder helper, Lottery4DStatementBean.DataBean item, int position) {
                RelativeLayout rl_content = helper.retrieveView(R.id.rl_content);
                TextView tv_no_data = helper.retrieveView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = helper.retrieveView(R.id.tv_no);
                    TextView tv_leixing = helper.retrieveView(R.id.tv_leixing);
                    TextView tv_game = helper.retrieveView(R.id.tv_game);
                    TextView tv_wanfa = helper.retrieveView(R.id.tv_wanfa);
                    TextView tv_number = helper.retrieveView(R.id.tv_number);
                    TextView tv_bet_amount = helper.retrieveView(R.id.tv_bet_amount);
                    TextView tv_win_lose = helper.retrieveView(R.id.tv_win_lose);
                    TextView tv_period = helper.retrieveView(R.id.tv_period);
                    TextView tv_one = helper.retrieveView(R.id.tv_one);
                    TextView tv_two = helper.retrieveView(R.id.tv_two);
                    TextView tv_three = helper.retrieveView(R.id.tv_three);
                    TextView tv_special = helper.retrieveView(R.id.tv_special);
                    TextView tv_consolation = helper.retrieveView(R.id.tv_consolation);
                    TextView tv_odds1 = helper.retrieveView(R.id.tv_odds1);
                    TextView tv_odds2 = helper.retrieveView(R.id.tv_odds2);
                    TextView tv_odds3 = helper.retrieveView(R.id.tv_odds3);
                    TextView tv_odds4 = helper.retrieveView(R.id.tv_odds4);
                    TextView tv_odds5 = helper.retrieveView(R.id.tv_odds5);
                    TextView tv_result = helper.retrieveView(R.id.tv_result);
                    tv_result.setText(getString(R.string.Result) + ": ");
                    tv_no.setText("No:" + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame());
                    tv_game.setText(getString(R.string.youxi) + ": " + item.getPool());
                    tv_wanfa.setText(getString(R.string.wanfa1) + item.getType());
                    tv_number.setText(getString(R.string.haoma1) + item.getNumber());
                    tv_bet_amount.setText(getString(R.string.xiazhujine) + ": " + String.format("%.2f", Double.parseDouble(item.getBet_amount())));
                    tv_win_lose.setText(getString(R.string.shuying) + String.format("%.2f", item.getWin_loss()));
                    tv_period.setText(getString(R.string.qihao1) + item.getPeriod());
                    String type3 = item.getType3();
                    String factor = item.getFactor();
                    String[] oddSplit = factor.split("#");
                    tv_odds1.setText(oddSplit[0]);
                    tv_odds2.setText(oddSplit[1]);
                    tv_odds3.setText(oddSplit[2]);
                    String result = item.getResult();
                    String[] resultSplit = result.split("#");
                    tv_one.setText("1st: " + resultSplit[0]);
                    tv_two.setText("2nd: " + resultSplit[1]);
                    tv_three.setText("3rd: " + resultSplit[2]);
                    if (type3.equals("108")) {
                        tv_special.setVisibility(View.VISIBLE);
                        tv_consolation.setVisibility(View.VISIBLE);
                        tv_odds4.setVisibility(View.VISIBLE);
                        tv_odds5.setVisibility(View.VISIBLE);
                        tv_odds4.setText(oddSplit[3]);
                        tv_odds5.setText(oddSplit[4]);
                        String special = "";
                        String consolation = "";
                        for (int i = 0; i < resultSplit.length; i++) {
                            if (i > 2 && i < 13) {
                                special += resultSplit[i];
                                if (i != 12) {
                                    special += "-";
                                }
                            }
                            if (i > 13) {
                                consolation += resultSplit[i];
                                if (i != resultSplit.length - 1) {
                                    consolation += "-";
                                }
                            }
                        }
                        tv_special.setText("Special: " + special);
                        tv_consolation.setText("Consolation: " + consolation);
                    } else {
                        tv_special.setVisibility(View.GONE);
                        tv_consolation.setVisibility(View.GONE);
                        tv_odds4.setVisibility(View.GONE);
                        tv_odds5.setVisibility(View.GONE);
                    }
                }
            }
        };
        lv_deposit.setAdapter(adapter);
    }

    public void showContent() {
        lv_deposit.setVisibility(View.VISIBLE);
    }
}
