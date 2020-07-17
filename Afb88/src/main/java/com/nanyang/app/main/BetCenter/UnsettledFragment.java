package com.nanyang.app.main.BetCenter;

import android.graphics.Color;
import androidx.annotation.IdRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.BetGoalWindowUtils;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.BetCenter.Bean.RunningBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen2ListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen3ListDataBean;
import com.nanyang.app.main.BetCenter.Presenter.UnsettledPresenter;
import com.nanyang.app.main.home.sport.football.SoccerRunningGoalManager;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by Administrator on 2019/4/4.
 */

public class UnsettledFragment extends BaseFragment<UnsettledPresenter> {
    @Bind(R.id.running_list)
    RecyclerView rv;
    @Bind(R.id.running_group)
    RadioGroup rgType;
    String type = "W";
    @Bind(R.id.tv_waite_count)
    TextView tvWaiteCount;
    @Bind(R.id.ll_note)
    LinearLayout llNote;
    Map<String, Boolean> showDetailMap = new HashMap<>();
    BetGoalWindowUtils utils=new BetGoalWindowUtils();
    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_running;
    }


    @Override
    public void initData() {
        super.initData();
        createPresenter(new UnsettledPresenter(this));
        rgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.running_waiting:
                        type = "W";
                        break;
                    case R.id.running_accepted:
                        type = "A";
                        break;
                    case R.id.running_rejected:
                        type = "R";
                        break;
                    case R.id.running_cancelled:
                        type = "C";
                        break;
                }
                presenter.getRunningList(type);
            }
        });
        presenter.getRunningList(type);
        setAdapter();

    }

    BaseRecyclerAdapter<RunningBean> adapter;

    BaseRecyclerAdapter<StatementOpen2ListDataBean> adapter1;

    private void setAdapter() {



        adapter1 = new BaseRecyclerAdapter<StatementOpen2ListDataBean>(mContext, new ArrayList<StatementOpen2ListDataBean>(), R.layout.item_statement_open2) {
            @Override
            public void convert(MyRecyclerViewHolder view, int position, StatementOpen2ListDataBean bean) {
                updateRc1(getmDatas(), view, position, bean);
            }
        };

        rv.setLayoutManager( new LinearLayoutManager(mContext));
        adapter = new BaseRecyclerAdapter<RunningBean>(mContext, new ArrayList<RunningBean>(), R.layout.item_running) {
            @Override
            public void convert(final MyRecyclerViewHolder holder, final int position, final RunningBean item) {
                String isRun5 = item.getIsRun5();
                if (!StringUtils.isNull(isRun5) && isRun5.equals("True")) {
                    holder.getHolderView().setBackgroundResource(R.color.green_content1);
                } else {
                    holder.getHolderView().setBackgroundResource(R.color.white);
                }
                LinearLayout ll1 = holder.getLinearLayout(R.id.ll_running_par);
                View ll2 = holder.getView(R.id.ll_running_hdp);
                final LinearLayout l = holder.getLinearLayout(R.id.ll_running_detail_1);
                final LinearLayout l2 = holder.getLinearLayout(R.id.ll_running_detail_2);
                 TextView running_par_DangerStatus = holder.getView(R.id.running_par_DangerStatus);
                TextView tv_running_status = holder.getView(R.id.running_Status);
                l.setVisibility(View.GONE);
                if (item.getBetType18().equals("PAR") || item.getBetType18().equals("PAM")) {
                    holder.getHolderView().setBackgroundResource(R.color.white);
                    ll1.setVisibility(View.VISIBLE);
                    ll2.setVisibility(View.GONE);
                    TextView running_par_Home = holder.getTextView(R.id.running_par_Home);
                    running_par_Home.setText(item.getHome1());
                    TextView running_par_Odds1 = holder.getTextView(R.id.running_par_Odds1);
                    TextView running_bet_num = holder.getTextView(R.id.running_bet_num);
                    TextView running_par_Odds2 = holder.getTextView(R.id.running_par_Odds2);
                    TextView running_par_CombInfo = holder.getTextView(R.id.running_par_CombInfo);
                    TextView running_par_Amt = holder.getTextView(R.id.running_par_Amt);

                    TextView running_par_IdAndTransDate = holder.getTextView(R.id.running_par_IdAndTransDate);

                    TextView par_id = holder.getTextView(R.id.par_id);
                    TextView par_type = holder.getTextView(R.id.par_type);
                    running_par_IdAndTransDate.setText("ID[" + item.getRefNo12() + "]" + item.getTransDate0());
                    running_par_DangerStatus.setText(item.getDangerStatus8());

                    running_bet_num.setText((getItemCount() - position) + "");
                    String odds = item.getOdds3();
                    BigDecimal bd = new BigDecimal(odds);
                    bd = bd.multiply(new BigDecimal("100"));
                    String s = AfbUtils.getPayout(item.getOdds3(), item.getAmt9(), item.getCombInfo16());
                    running_par_Odds1.setText(s);
                    running_par_Odds2.setText(getString(R.string.Odds) + ":" + odds);

                    running_par_CombInfo.setText(item.getCombInfo16());

                    TextView running_Amt_text = holder.getTextView(R.id.running_Amt_text);
                    running_Amt_text.setText(getString(R.string.Amt) + ":");
                    running_par_Amt.setText(item.getAmt9());
                    par_id.setText(item.getSocTransId17());
                    par_type.setText(item.getBetType18());
                    final TextView open_detail_list = holder.getTextView(R.id.open_detail_list);
                    if (StringUtils.isNull(item.getCombInfo16()) || item.getCombInfo16().endsWith("1")) {
                        open_detail_list.setVisibility(View.GONE);
                    } else {
                        open_detail_list.setVisibility(View.VISIBLE);
                    }

                    if (openDetailMap.get(item.getSocTransId17()) != null && !StringUtils.isNull(openDetailMap.get(item.getSocTransId17()).getType())) {
                        l.setVisibility(View.VISIBLE);
                        l1Content(openDetailMap.get(item.getSocTransId17()).getType(), holder);
                        if (openDetailMap.get(item.getSocTransId17()).getParent() != null) {
                            open_detail_list.setText(getString(R.string.CloseDetail));
                            l2.setVisibility(View.VISIBLE);
                            if (!StringUtils.isNull(openDetailMap.get(item.getSocTransId17()).getParent())) {
                                l2content(openDetailMap.get(item.getSocTransId17()).getParent(), holder);
                            }
                        } else {
                            open_detail_list.setText(getString(R.string.OpenDetail));
                            l2.setVisibility(View.GONE);
                        }
                    } else {
                        l.setVisibility(View.GONE);
                    }
                    ll1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            open_detail_list.setText(getString(R.string.OpenDetail));
                            if (l.getVisibility() == View.VISIBLE) {
                                l.setVisibility(View.GONE);
                                openDetailMap.put(item.getSocTransId17(), null);
                            } else {
                                showLoadingDialog();
                                presenter.getParList(item.getSocTransId17(), new BaseConsumer<String>(getIBaseContext()) {
                                    @Override
                                    protected void onBaseGetData(String data) throws JSONException {
                                        l.setVisibility(View.VISIBLE);
                                        MenuItemInfo<String> stringMenuItemInfo1 = openDetailMap.get(item.getSocTransId17());
                                        if (stringMenuItemInfo1 != null) {
                                            stringMenuItemInfo1.setType(data);
                                        } else {
                                            openDetailMap.put(item.getSocTransId17(), new MenuItemInfo<String>(0, 0, data, null));
                                        }
                                        l1Content(data, holder);
                                    }
                                });
                            }

                        }
                    });
                    open_detail_list.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (openDetailMap.get(item.getSocTransId17()) != null && !StringUtils.isNull(openDetailMap.get(item.getSocTransId17()).getType()) && !StringUtils.isNull(openDetailMap.get(item.getSocTransId17()).getParent())) {
                                open_detail_list.setText(getString(R.string.OpenDetail));
                                l2.setVisibility(View.GONE);
                                openDetailMap.get(item.getSocTransId17()).setParent(null);
                            } else {

                                showLoadingDialog();
                                presenter.getParList2(item.getSocTransId17(), item.getBetType18(), new BaseConsumer<String>(getIBaseContext()) {
                                    @Override
                                    protected void onBaseGetData(String data) throws JSONException {
                                        openDetailMap.get(item.getSocTransId17()).setParent(data);
                                        open_detail_list.setText(getString(R.string.CloseDetail));
                                        openDetailMap.put(item.getSocTransId17(), openDetailMap.get(item.getSocTransId17()));
                                        l2content(data, holder);
                                        l2.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                        }
                    });
                    View view = holder.getView(R.id.ll_live_parent1);


                } else {

                    ll1.setVisibility(View.GONE);
                    ll2.setVisibility(View.VISIBLE);

                    TextView refNo = holder.getTextView(R.id.running_RefNo);
                    TextView running_hdp_bet_num = holder.getTextView(R.id.running_hdp_bet_num);
                    running_hdp_bet_num.setText((getItemCount() - position) + "");
                    refNo.setText(item.getRefNo12());
                    TextView running_TransDate = holder.getTextView(R.id.running_TransDate);
                    running_TransDate.setText(" (" + item.getTransDate0() + ")");
                    TextView running_ModuleTitle = holder.getTextView(R.id.running_ModuleTitle);
                    running_ModuleTitle.setText(item.getModuleTitle11());
                    TextView running_Home = holder.getTextView(R.id.running_Home);
                    running_Home.setText(item.getHome1());
                    TextView running_Away = holder.getTextView(R.id.running_Away);
                    running_Away.setText(item.getAway2());
                    TextView running_FullTimeId = holder.getTextView(R.id.running_FullTimeId);
                    String fullTimeId13 = item.getFullTimeId13();
                    boolean isOu = false;
                    TextView running_BetType = holder.getTextView(R.id.running_BetType);
                    String transType10 = item.getTransType10();
                    utils.showBetType(getBaseActivity(),running_FullTimeId,running_BetType,fullTimeId13,transType10,isOu);

                    TextView running_Score = holder.getTextView(R.id.running_Score);
                    TextView running_BetType2 = holder.getTextView(R.id.running_BetType2);
                    TextView running_BetType2_odds = holder.getTextView(R.id.running_BetType2_odds);
                    String betType2 = item.getBetType323();

                    String gameType314 = item.getGameType314();
                    if (StringUtils.isNull(item.getBetType424())) {
                        running_BetType2.setVisibility(View.GONE);
                    } else {
                        running_BetType2.setVisibility(View.VISIBLE);
                        running_BetType2.setText(HtmlTagHandler.spanFontHtml(item.getBetType424()));
                    }

                    running_BetType2_odds.setText(HtmlTagHandler.spanFontHtml((gameType314.equals("O") ? "Outright" : betType2)));
                    running_Score.setText((isRun5.equals("True") ? item.getScore19() : ""));
                    TextView running_Odds = holder.getTextView(R.id.running_Odds);
                    String odds = item.getOdds3();
//                    odds = AfbUtils.delHTMLTag(odds);
                    running_Odds.setText(HtmlTagHandler.spanFontHtml("@" + odds + " "));
                    TextView running_OddsType = holder.getTextView(R.id.running_OddsType);
                    String oddsType15 = item.getOddsType15();
                    if (!TextUtils.isEmpty(oddsType15)) {
                        oddsType15 = "(" + oddsType15 + ") ";
                    }
                    running_OddsType.setText(oddsType15);
                    TextView running_OldStatus = holder.getTextView(R.id.running_OldStatus);
                    View running_split = holder.getImageView(R.id.running_split);
                    if (TextUtils.isEmpty(item.getOldStatus22())) {
                        running_OldStatus.setVisibility(View.GONE);
                        running_split.setVisibility(View.GONE);
                    } else {
                        running_OldStatus.setVisibility(View.VISIBLE);
                        running_split.setVisibility(View.VISIBLE);
                    }
                    String dangerStatus8 = item.getDangerStatus8();
                    dangerStatus8 = dangerStatus8.replace("&nbsp;", " ");

                    running_OldStatus.setText(item.getOldStatus22());
                    tv_running_status.setText(dangerStatus8);
                    TextView running_Amt = holder.getTextView(R.id.running_Amt);
                    running_Amt.setText(item.getAmt9());
                    View view2 = holder.getView(R.id.ll_content_3);

                    ll2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Boolean aBoolean1 = showDetailMap.get(item.getSocTransId17());
                            if (aBoolean1 == null) {
                                showDetailMap.put(item.getSocTransId17(), true);
                            } else {
                                showDetailMap.put(item.getSocTransId17(), !showDetailMap.get(item.getSocTransId17()));
                            }
                            notifyItemChanged(position);
                        }
                    });
                    showDetail(item, refNo, running_TransDate, running_ModuleTitle, running_OddsType, isOu, view2);
                }
                View view = holder.getView(R.id.ll_live_parent);
                View view1 = holder.getView(R.id.ll_live_parent1);
                TextView tv_running_score = holder.getView(R.id.tv_running_score);
                TextView tv_running_score1 = holder.getView(R.id.tv_running_score1);
                TextView tv_running_time = holder.getView(R.id.tv_running_time);
                TextView tv_running_time1 = holder.getView(R.id.tv_running_time1);
                setLiveView(item, view, tv_running_time, tv_running_score);
                setLiveView(item, view1, tv_running_time1, tv_running_score1);
                utils.showStatus(getBaseActivity(),item.getStatus20(),running_par_DangerStatus);
                utils.showStatus(getBaseActivity(),item.getStatus20(),tv_running_status);
            }
        };
        rv.setAdapter(adapter);
    }

    @Override
    public void initWaitData() {
        type = "W";
        RadioButton rb = (RadioButton) rgType.getChildAt(0);
        rb.setChecked(true);
        presenter.getRunningList(type);
    }

    Map<String, MenuItemInfo<String>> openDetailMap = new HashMap<>();

    public void setRvlist(List<RunningBean> list) {
        if (list != null && list.size() > 0) {
            llNote.setVisibility(View.VISIBLE);
        } else {
            llNote.setVisibility(View.GONE);
        }
        if (type.equals("W")) {
            if (list.size() > 0) {
                tvWaiteCount.setText(list.size() + "");
                tvWaiteCount.setVisibility(View.VISIBLE);
            } else {
                tvWaiteCount.setVisibility(View.GONE);
            }
        }
        adapter.addAllAndClear(list);
        if (list != null && list.size() > 0)
            rv.setVisibility(View.VISIBLE);
        else
            rv.setVisibility(View.GONE);
    }

    private void l2content(String data, MyRecyclerViewHolder holder) {
        String updateString = data.replace("&nbsp;", " ");
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(updateString);
            if (jsonArray.length() > 3) {
                final List<StatementOpen3ListDataBean> list = presenter.getBeanList3(jsonArray);
                RecyclerView rc1 = holder.getView(R.id.rc_par_2);
                LinearLayoutManager llm = new LinearLayoutManager(mContext) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };
                rc1.setLayoutManager(llm);
                BaseRecyclerAdapter<StatementOpen3ListDataBean> adapter = new BaseRecyclerAdapter<StatementOpen3ListDataBean>(mContext, list, R.layout.item_statement_open2) {
                    @Override
                    public void convert(MyRecyclerViewHolder view, int position, StatementOpen3ListDataBean bean) {
                        updateRc2(list, view, position, bean);
                    }
                };
                rc1.setAdapter(adapter);
            }
            hideLoadingDialog();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    List<StatementOpen2ListDataBean> list;

    private void l1Content(String data, MyRecyclerViewHolder holder) {
        String updateString = data.replace("&nbsp;", " ");
        try {
            JSONArray jsonArray = new JSONArray(updateString);
            if (jsonArray.length() > 3) {
                list = presenter.getBeanList2(jsonArray);
                RecyclerView rc1 = holder.getView(R.id.rc_par_1);
                rc1.setLayoutManager(new LinearLayoutManager(mContext));
                rc1.setAdapter(adapter1);
                adapter1.addAllAndClear(list);
            }
            hideLoadingDialog();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void showDetail(RunningBean item, TextView refNo, TextView running_transDate, TextView running_moduleTitle, TextView running_oddsType, boolean isOu, View view2) {

        if (showDetailMap.get(item.getSocTransId17()) == null || !showDetailMap.get(item.getSocTransId17())) {
            refNo.setVisibility(View.GONE);
            running_transDate.setVisibility(View.GONE);
            running_moduleTitle.setVisibility(View.GONE);
            running_oddsType.setVisibility(View.GONE);
            if (isOu) {
                view2.setVisibility(View.VISIBLE);
            } else {
                view2.setVisibility(View.GONE);
            }
        } else {
            refNo.setVisibility(View.VISIBLE);
            running_transDate.setVisibility(View.VISIBLE);
            running_moduleTitle.setVisibility(View.VISIBLE);
            running_oddsType.setVisibility(View.VISIBLE);
            view2.setVisibility(View.VISIBLE);
        }

    }

    private void setLiveView(RunningBean item, View view, TextView tv_running_status, TextView tv_running_score) {
        if (item.getTeamIsRun27().equals("True")) {
            SoccerRunningGoalManager.getInstance().runScoreStyle(item.getSocTransId17(), tv_running_score, item.getHomeScore25(), item.getAwayScore26(), item.getScore19(), item.getIsHomeGoal32());
//            tv_running_score.setText( item.getHomeScore25()+" - "+item.getAwayScore26());
            view.setVisibility(View.VISIBLE);
            String mExtraTime30 = item.getMExtraTime30();
            String teamStatus28 = item.getTeamStatus28();
            String curMinute29 = item.getCurMinute29();
            String live31 = item.getLive31();
            SoccerRunningGoalManager.getInstance().runTimeStyle(tv_running_status, mExtraTime30, teamStatus28, curMinute29, live31);
        } else {
            view.setVisibility(View.GONE);
        }
    }


    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void refreshData() {
        presenter.getRunningList(type);
    }

    public void updateRc2(List<StatementOpen3ListDataBean> list, MyRecyclerViewHolder view, int position, StatementOpen3ListDataBean bean) {
        LinearLayout llTitle = view.getLinearLayout(R.id.ll_title);
        TextView tvTotalOdds = view.getTextView(R.id.tv_total_odds);
        TextView tvAmt = view.getTextView(R.id.tv_amt);
        TextView tvIdDate = view.getTextView(R.id.tv_id_date);
        TextView tvMatchType = view.getTextView(R.id.tv_match_type);
        TextView tvMatchVs = view.getTextView(R.id.tv_match_vs);
        TextView tvMatchAt1 = view.getTextView(R.id.tv_match_at1);
        TextView tv_match_at2_1 = view.getTextView(R.id.tv_match_at2_1);
        TextView tv_match_at2_2 = view.getTextView(R.id.tv_match_at2_2);
        TextView tvMatchAt3 = view.getTextView(R.id.tv_match_at3);
        TextView tvMatchAt4 = view.getTextView(R.id.tv_match_at4);
        TextView tvMatchScore = view.getTextView(R.id.tv_match_score);
        TextView tvWL = view.getTextView(R.id.tv_wl);
        TextView tvScore = view.getTextView(R.id.tv_score);
        if (position > 0) {
            String lastId = list.get(position - 1).getIndex22();
            String id = bean.getIndex22();
            if (id.equals(lastId)) {
                tvIdDate.setVisibility(View.INVISIBLE);
            } else {
                tvIdDate.setVisibility(View.VISIBLE);
            }
        }
        tvIdDate.setText(bean.getIndex22() + "(" + bean.getIndex5() + ")");
        tvMatchType.setText(bean.getIndex1());
        tvMatchVs.setText(bean.getIndex3() + "-vs-" + bean.getIndex4());
        String matchAtStr1 = bean.getIndex16();
        tvMatchAt1.setText(HtmlTagHandler.spanFontHtml(matchAtStr1));
        String index22 = bean.getIndex23();
        Spanned spanned = HtmlTagHandler.spanFontHtml(index22);
        tv_match_at2_1.setText(spanned);
        String index21 = bean.getIndex21();
        if (index21.equals("True")) {
            tvMatchScore.setVisibility(View.VISIBLE);
            String showStr = " " + bean.getIndex19() + "-" + bean.getIndex20() + " ";
            tvMatchScore.setText(showStr);
        } else {
            tvMatchScore.setVisibility(View.GONE);
        }
        tvMatchAt3.setText("@");
        tvMatchAt4.setText(bean.getIndex13() + "");
        String wlStr = bean.getIndex8();
        if (wlStr.contains("green")) {
            tvWL.setTextColor(ContextCompat.getColor(mContext, R.color.green900));
        } else if (wlStr.contains("red")) {
            tvWL.setTextColor(Color.RED);
        } else {
            tvWL.setTextColor(Color.BLACK);
        }
        tvWL.setText(AfbUtils.delHTMLTag(wlStr));
        String index24 = bean.getIndex24();
        String scoreStr;
        if (index24.equals("False")) {
            scoreStr = "-";
        } else {
            scoreStr = bean.getIndex6() + "-" + bean.getIndex7();
        }
        tvScore.setText(getString(R.string.Result) + " " + scoreStr);
        if (position == 0) {
            llTitle.setVisibility(View.VISIBLE);
            tvTotalOdds.setText(getString(R.string.TotalOdds) + ":" + bean.getIndex18());
            tvAmt.setText(getString(R.string.Amt) + " " + bean.getIndex15());
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public void updateRc1(List<StatementOpen2ListDataBean> list, MyRecyclerViewHolder view, int position, StatementOpen2ListDataBean bean) {

        TextView tvIdDate = view.getTextView(R.id.tv_id_date);
        TextView tvMatchType = view.getTextView(R.id.tv_match_type);
        TextView tvMatchVs = view.getTextView(R.id.tv_match_vs);
        TextView tvMatchAt1 = view.getTextView(R.id.tv_match_at1);
        TextView tv_match_at2_1 = view.getTextView(R.id.tv_match_at2_1);
        TextView tvMatchAt3 = view.getTextView(R.id.tv_match_at3);
        TextView tvMatchAt4 = view.getTextView(R.id.tv_match_at4);
        TextView tvMatchScore = view.getTextView(R.id.tv_match_score);
        TextView tvWL = view.getTextView(R.id.tv_wl);
        TextView tvScore = view.getTextView(R.id.tv_score);
        if (position > 0) {
            String lastId = list.get(position - 1).getIndex21();
            String id = bean.getIndex21();
            if (id.equals(lastId)) {
                tvIdDate.setVisibility(View.INVISIBLE);
            } else {
                tvIdDate.setVisibility(View.VISIBLE);
            }
        }
        String idDate = bean.getIndex21() + "(" + bean.getIndex5() + ")";
        tvIdDate.setText(idDate);
        tvMatchType.setText(bean.getIndex1());
        tvMatchVs.setText(bean.getIndex3() + "-vs-" + bean.getIndex4());
        String matchAtStr1 = bean.getIndex16();
        tvMatchAt1.setText(HtmlTagHandler.spanFontHtml(matchAtStr1));

        String index22 = bean.getIndex22();
        tv_match_at2_1.setText(HtmlTagHandler.spanFontHtml(index22));
        String index20 = bean.getIndex20();
        LogUtil.d("index20", "index20:" + index20 + "," + bean.getIndex18() + "-" + bean.getIndex19());
        if (index20.equals("True")) {
            tvMatchScore.setVisibility(View.VISIBLE);
            String showStr = " " + bean.getIndex18() + "-" + bean.getIndex19() + " ";
            tvMatchScore.setText(showStr);
        } else {
            tvMatchScore.setVisibility(View.GONE);
        }
        tvMatchAt3.setText("@");
        tvMatchAt4.setText(bean.getIndex13() + "");
        String wlStr = bean.getIndex8();
        if (wlStr.contains("green")) {
            tvWL.setTextColor(ContextCompat.getColor(mContext, R.color.green900));
        } else if (wlStr.contains("red")) {
            tvWL.setTextColor(Color.RED);
        } else {
            tvWL.setTextColor(Color.BLACK);
        }
        tvWL.setText(AfbUtils.delHTMLTag(wlStr));
        String index23 = bean.getIndex23();
        String scoreStr;
        if (index23.equals("False")) {
            scoreStr = "-";
        } else {
            scoreStr = bean.getIndex6() + "-" + bean.getIndex7();
        }
        tvScore.setText(getString(R.string.Result) + ":" + scoreStr);
    }

}
