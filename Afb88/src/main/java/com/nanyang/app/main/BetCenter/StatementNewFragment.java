package com.nanyang.app.main.BetCenter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.main.BetCenter.Bean.StatementFirstBean;
import com.nanyang.app.main.BetCenter.Bean.StatementListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen2ListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen3ListDataBean;
import com.nanyang.app.main.BetCenter.Presenter.StatementNewPresenter;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2019/4/4.
 */

public class StatementNewFragment extends BaseFragment<StatementNewPresenter> {
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    private LinearLayout currentLlAddView;
    private LayoutInflater layoutInflater;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_statement_new;
    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new StatementNewPresenter(this));
        layoutInflater = LayoutInflater.from(mContext);
        getStatementData();
    }

    private List<StatementListDataBean> lastStatementList;

    private void setSvContent(final StatementFirstBean statementFirstBean, List<StatementListDataBean> list) {
        if (lastStatementList == null) {
            lastStatementList = list;
        } else {
            if (lastStatementList.size() == list.size()) {
                return;
            }
        }
        llContent.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            final StatementListDataBean bean = list.get(i);
            View view = layoutInflater.inflate(R.layout.item_statement_new, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomMargin = AfbUtils.dp2px(mContext, 8);
            view.setLayoutParams(layoutParams);
            TextView tvDate = view.findViewById(R.id.tv_date);
            TextView tvCom = view.findViewById(R.id.tv_com);
            TextView tvWinLose = view.findViewById(R.id.tv_WinLose);
            TextView tvSettled = view.findViewById(R.id.tv_settled);
            final LinearLayout llAddView = view.findViewById(R.id.ll_addView);
            String date = DateUtils.format(bean.getIndex1(), "yyyy-MM-dd", "dd/MM/yyyy") + " " +
                    DateUtils.getChinaWeek(DateUtils.format(bean.getIndex1(), "yyyy-MM-dd"));
            if (bean.getIndex0().equals("1")) {
                date = getString(R.string.Lastweeksummary);
                view.setTag(1);//1是上周数据 2是本周正常数据
            } else {
                view.setTag(2);
            }
            tvDate.setText(date);
            tvCom.setText(getString(R.string.Com) + " " + bean.getIndex5() + " ");
            String wl = bean.getIndex4();
            tvWinLose.setText(" " + wl + " ");
            if (wl.startsWith("-")) {
                tvWinLose.setTextColor(Color.RED);
            } else {
                tvWinLose.setTextColor(Color.BLUE);
            }
            tvSettled.setText(" " + getString(R.string.Settled) + " " + bean.getIndex6());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentLlAddView = llAddView;
                    int visibility = llAddView.getVisibility();
                    if (visibility == View.VISIBLE) {
                        llAddView.setVisibility(View.GONE);
                    } else {
                        llAddView.setVisibility(View.VISIBLE);
                    }
                    int requestType = (int) v.getTag();
                    if (llAddView.getChildCount() < 1) {
                        if (requestType == 2) {
                            getStatementOpen1Data(bean.getIndex1());
                        } else {
                            int summaryDate = statementFirstBean.getSummaryDate();
                            String startDay;
                            if (summaryDate == 1) {
                                startDay = DateUtils.getAddDay(bean.getIndex1(), -6, "yyyy-MM-dd");
                            } else {
                                startDay = bean.getIndex1();
                            }
                            getStatementLastWeekData(startDay, bean.getIndex1());
                        }
                    }
                }
            });
            llContent.addView(view);
        }
    }

    public void onGetStatementData(StatementFirstBean statementFirstBean, List<StatementListDataBean> list) {
        setSvContent(statementFirstBean, list);
    }

    public void onGetStatementLastWeekData(List<StatementListDataBean> list) {
        for (int i = 0; i < list.size(); i++) {
            final StatementListDataBean bean = list.get(i);
            View view = layoutInflater.inflate(R.layout.item_statement_new, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomMargin = AfbUtils.dp2px(mContext, 8);
            view.setLayoutParams(layoutParams);
            TextView tvDate = view.findViewById(R.id.tv_date);
            TextView tvCom = view.findViewById(R.id.tv_com);
            TextView tvWinLose = view.findViewById(R.id.tv_WinLose);
            TextView tvSettled = view.findViewById(R.id.tv_settled);
            final LinearLayout llAddView = view.findViewById(R.id.ll_addView);
            String date = bean.getIndex1();
            final String[] dateTrue = date.split(" ");
            String dateStr = DateUtils.format(dateTrue[0], "yyyy-MM-dd", "dd/MM/yyyy") + " " +
                    DateUtils.getChinaWeek(DateUtils.format(bean.getIndex1(), "yyyy-MM-dd"));
            if (bean.getIndex0().equals("1")) {
                dateStr = getString(R.string.Lastweeksummary);
            }
            tvDate.setText(dateStr);
            tvCom.setText(getString(R.string.Com) + " " + bean.getIndex5() + " ");
            String wl = bean.getIndex4();
            tvWinLose.setText(" " + wl + " ");
            if (wl.startsWith("-")) {
                tvWinLose.setTextColor(Color.RED);
            } else {
                tvWinLose.setTextColor(Color.BLUE);
            }
            tvSettled.setText(" " + getString(R.string.Settled) + " " + bean.getIndex6());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentLlAddView = llAddView;
                    int visibility = llAddView.getVisibility();
                    if (visibility == View.VISIBLE) {
                        llAddView.setVisibility(View.GONE);
                    } else {
                        llAddView.setVisibility(View.VISIBLE);
                    }
                    if (llAddView.getChildCount() < 1) {
                        getStatementOpen1Data(dateTrue[0]);
                    }
                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
            View v = new View(mContext);
            v.setBackgroundColor(Color.BLACK);
            v.setLayoutParams(params);
            currentLlAddView.addView(v);
            currentLlAddView.addView(view);
        }
    }

    public void onGetStatementOpen1Data(List<StatementListDataBean> list) {
        for (int i = 0; i < list.size(); i++) {
            final StatementListDataBean bean = list.get(i);
            final String index11 = bean.getIndex11();
            View view;
            if (index11.equals("PAR") || index11.equals("PAM")) {
                view = layoutInflater.inflate(R.layout.item_statement_open1_typ1, null);
                TextView tvIdDate = view.findViewById(R.id.tv_id_date);
                TextView tvNumber = view.findViewById(R.id.tv_number);
                TextView tvMatchType = view.findViewById(R.id.tv_match_type);
                TextView tvEstPayout = view.findViewById(R.id.tv_est_payout);
                TextView tvOdds = view.findViewById(R.id.tv_odds);
                TextView tvType = view.findViewById(R.id.tv_type);
                final LinearLayout llAddView1 = view.findViewById(R.id.ll_addView1);
                TextView tvAmt = view.findViewById(R.id.tv_amt);
                TextView tvWl = view.findViewById(R.id.tv_wl);
                TextView tvCom = view.findViewById(R.id.tv_com);
                tvIdDate.setText("ID:[" + bean.getIndex13() + "]" + bean.getIndex0());
                int number = i + 1;
                tvNumber.setText(number < 10 ? ("0" + number) : "" + number);
                tvMatchType.setText(bean.getIndex1());
                tvEstPayout.setText(getString(R.string.Est_Payout) + " " + AfbUtils.decimalValue(Float.parseFloat(bean.getIndex3()) * Float.parseFloat(bean.getIndex9()), "0.00"));
                tvOdds.setText(getString(R.string.Odds) + " " + bean.getIndex3());
                tvType.setText(bean.getIndex17());
                tvAmt.setText(getString(R.string.Amt) + " " + bean.getIndex9());
                String winLose = bean.getIndex10();
                tvWl.setText(winLose);
                if (winLose.startsWith("-")) {
                    tvWl.setTextColor(Color.RED);
                } else {
                    tvWl.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
                }
                tvCom.setText(getString(R.string.Com) + " " + bean.getIndex18());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentLlAddView = llAddView1;
                        int visibility = llAddView1.getVisibility();
                        if (visibility == View.VISIBLE) {
                            llAddView1.setVisibility(View.GONE);
                        } else {
                            llAddView1.setVisibility(View.VISIBLE);
                        }
                        if (llAddView1.getChildCount() < 1) {
                            getStatementOpen2Data(bean.getIndex22(), index11);
                        }
                    }
                });
            } else {
                view = layoutInflater.inflate(R.layout.item_statement_open1_typ2, null);
                TextView tvIdDate = view.findViewById(R.id.tv_id_date);
                TextView tvNumber = view.findViewById(R.id.tv_number);
                TextView tvMatchType = view.findViewById(R.id.tv_match_type);
                TextView tvMatchVs = view.findViewById(R.id.tv_match_vs);
                TextView tvMatchAt1 = view.findViewById(R.id.tv_match_at1);
                TextView tvMatchAt2 = view.findViewById(R.id.tv_match_at2);
                TextView tvMatchAt3 = view.findViewById(R.id.tv_match_at3);
                TextView tvMatchAt4 = view.findViewById(R.id.tv_match_at4);
                TextView tvMatchGrade = view.findViewById(R.id.tv_match_grade);
                TextView tvAmt = view.findViewById(R.id.tv_amt);
                TextView tvOdds = view.findViewById(R.id.tv_odds);
                TextView tvHandicap = view.findViewById(R.id.tv_handicap);
                TextView tvCom = view.findViewById(R.id.tv_com);
                TextView tvWl = view.findViewById(R.id.tv_wl);
                tvIdDate.setText(getString(R.string.ID) + ":[" + bean.getIndex13() + "]" + bean.getIndex0());
                int number = i + 1;
                tvNumber.setText(number < 10 ? ("0" + number) : "" + number);
                tvMatchType.setText(bean.getIndex12());
                tvMatchVs.setText(bean.getIndex1() + "-VS-" + bean.getIndex2());
                String index24 = bean.getIndex24();
                if (index24.contains("gbGive")) {
                    tvMatchAt1.setTextColor(Color.RED);
                } else {
                    tvMatchAt1.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
                }
                index24 = AfbUtils.delHTMLTag(index24);
                String odds = bean.getIndex3();
                boolean isOutRight = bean.getIndex15().equals("O");
                if (isOutRight) {
                    tvMatchAt1.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    tvMatchAt1.setText("Outright");
                    tvMatchAt2.setVisibility(View.GONE);
                    tvMatchAt3.setVisibility(View.GONE);
                    tvMatchAt4.setVisibility(View.GONE);
                } else {
                    tvMatchAt1.setText(index24);
                    tvMatchAt2.setText(bean.getIndex14() + " (" + index24 + ") @");
                    odds = AfbUtils.delHTMLTag(odds);
                    tvMatchAt3.setText(odds);
                    tvMatchAt4.setText("(" + bean.getIndex16() + ")");
                    tvMatchAt2.setVisibility(View.VISIBLE);
                    tvMatchAt3.setVisibility(View.VISIBLE);
                    tvMatchAt4.setVisibility(View.VISIBLE);
                }
                tvMatchGrade.setText(bean.getIndex5() + " " + bean.getIndex20());
                tvAmt.setText(getString(R.string.Amt) + " " + bean.getIndex9());
                if (odds.startsWith("-")) {
                    tvOdds.setTextColor(Color.RED);
                    tvMatchAt3.setTextColor(Color.RED);
                } else {
                    tvOdds.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    tvMatchAt3.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                }
                tvOdds.setText(odds);
                tvHandicap.setText("(" + bean.getIndex16() + ")");
                tvCom.setText(getString(R.string.Com) + " " + bean.getIndex18());
                String winLose = bean.getIndex10();
                tvWl.setText(winLose);
                if (winLose.startsWith("-")) {
                    tvWl.setTextColor(Color.RED);
                } else {
                    tvWl.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
                }
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomMargin = AfbUtils.dp2px(mContext, 8);
            view.setLayoutParams(layoutParams);
            currentLlAddView.addView(view);
        }
    }

    public void onGetStatementOpen2Data(List<StatementOpen2ListDataBean> list, final String id, final String transType) {
        for (int i = 0; i < list.size(); i++) {
            final StatementOpen2ListDataBean bean = list.get(i);
            View view = layoutInflater.inflate(R.layout.item_statement_open2, null);
            TextView tvIdDate = view.findViewById(R.id.tv_id_date);
            TextView tvMatchType = view.findViewById(R.id.tv_match_type);
            TextView tvMatchVs = view.findViewById(R.id.tv_match_vs);
            TextView tvMatchAt1 = view.findViewById(R.id.tv_match_at1);
            TextView tvMatchAt2 = view.findViewById(R.id.tv_match_at2);
            TextView tvMatchAt3 = view.findViewById(R.id.tv_match_at3);
            TextView tvMatchAt4 = view.findViewById(R.id.tv_match_at4);
            TextView tvWL = view.findViewById(R.id.tv_wl);
            final LinearLayout llAddView2 = view.findViewById(R.id.ll_addView2);
            TextView tvScore = view.findViewById(R.id.tv_score);
            tvIdDate.setText(bean.getIndex21() + "(" + bean.getIndex5() + ")");
            tvMatchType.setText(bean.getIndex1());
            tvMatchVs.setText(bean.getIndex3() + "-VS-" + bean.getIndex4());
            String matchAtStr1 = bean.getIndex16();
            tvMatchAt1.setText(AfbUtils.delHTMLTag(matchAtStr1));
            if (matchAtStr1.contains("gbGive")) {
                tvMatchAt1.setTextColor(Color.RED);
            } else if (matchAtStr1.contains("gbTake2")) {
                tvMatchAt1.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
            } else {
                tvMatchAt1.setTextColor(Color.BLACK);
            }
            String matchAtStr2 = AfbUtils.delHTMLTag(bean.getIndex22());
//            if (matchAtStr2.startsWith("<span")) {
//
//            }
            tvMatchAt2.setText("(" + matchAtStr2 + ")");
            tvMatchAt3.setText("@");
            tvMatchAt4.setText(bean.getIndex13() + "");
            String wlStr = bean.getIndex8();
            if (wlStr.contains("green")) {
                tvWL.setTextColor(ContextCompat.getColor(mContext, R.color.green900));
            } else {
                tvWL.setTextColor(Color.RED);
            }
            tvWL.setText(AfbUtils.delHTMLTag(wlStr));
            tvScore.setText(getString(R.string.Result) + ":" + bean.getIndex6() + "-" + bean.getIndex7());
            if (i == 0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
                int margin = AfbUtils.dp2px(mContext, 3);
                params.topMargin = margin;
                params.bottomMargin = margin;
                View v = new View(mContext);
                v.setBackgroundColor(Color.BLACK);
                v.setLayoutParams(params);
                currentLlAddView.addView(v);
            }
            if (i == list.size() - 1) {
                TextView tvDetail = view.findViewById(R.id.tv_open_detail);
                tvDetail.setVisibility(View.VISIBLE);
                tvDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv = (TextView) v;
                        currentLlAddView = llAddView2;
                        int visibility = llAddView2.getVisibility();
                        if (visibility == View.VISIBLE) {
                            tv.setText(getString(R.string.OpenDetail));
                            llAddView2.setVisibility(View.GONE);
                        } else {
                            tv.setText(getString(R.string.CloseDetail));
                            llAddView2.setVisibility(View.VISIBLE);
                        }
                        if (llAddView2.getChildCount() < 1) {
                            getStatementOpen3Data(id, transType);
                        }
                    }
                });
            }
            currentLlAddView.addView(view);
        }
    }

    public void onGetStatementOpen3Data(List<StatementOpen3ListDataBean> list) {
        for (int i = 0; i < list.size(); i++) {
            StatementOpen3ListDataBean bean = list.get(i);
            View view = layoutInflater.inflate(R.layout.item_statement_open2, null);
            LinearLayout llTitle = view.findViewById(R.id.ll_title);
            TextView tvTotalOdds = view.findViewById(R.id.tv_total_odds);
            TextView tvAmt = view.findViewById(R.id.tv_amt);
            TextView tvIdDate = view.findViewById(R.id.tv_id_date);
            TextView tvMatchType = view.findViewById(R.id.tv_match_type);
            TextView tvMatchVs = view.findViewById(R.id.tv_match_vs);
            TextView tvMatchAt1 = view.findViewById(R.id.tv_match_at1);
            TextView tvMatchAt2 = view.findViewById(R.id.tv_match_at2);
            TextView tvMatchAt3 = view.findViewById(R.id.tv_match_at3);
            TextView tvMatchAt4 = view.findViewById(R.id.tv_match_at4);
            TextView tvWL = view.findViewById(R.id.tv_wl);
            TextView tvScore = view.findViewById(R.id.tv_score);
            tvIdDate.setText(bean.getIndex22() + "(" + bean.getIndex5() + ")");
            tvMatchType.setText(bean.getIndex1());
            tvMatchVs.setText(bean.getIndex3() + "-VS-" + bean.getIndex4());
            String matchAtStr1 = bean.getIndex16();
            tvMatchAt1.setText(AfbUtils.delHTMLTag(matchAtStr1));
            if (matchAtStr1.contains("gbGive")) {
                tvMatchAt1.setTextColor(Color.RED);
            } else if (matchAtStr1.contains("gbTake2")) {
                tvMatchAt1.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
            } else {
                tvMatchAt1.setTextColor(Color.BLACK);
            }
            String matchAtStr2 = AfbUtils.delHTMLTag(bean.getIndex23());
            tvMatchAt2.setText("(" + matchAtStr2 + ")");
            tvMatchAt3.setText("@");
            tvMatchAt4.setText(bean.getIndex13() + "");
            String wlStr = bean.getIndex8();
            if (wlStr.contains("green")) {
                tvWL.setTextColor(ContextCompat.getColor(mContext, R.color.green900));
            } else {
                tvWL.setTextColor(Color.RED);
            }
            tvWL.setText(AfbUtils.delHTMLTag(wlStr));
            tvScore.setText(getString(R.string.Result) + " " + bean.getIndex6() + "-" + bean.getIndex7());
            if (i == 0) {
                llTitle.setVisibility(View.VISIBLE);
                tvTotalOdds.setText(getString(R.string.TotalOdds) + ":" + bean.getIndex18());
                tvAmt.setText(getString(R.string.Amt) + " " + bean.getIndex15());
            } else {
                int currentPositionId = bean.getIndex0();
                int lastPositionId = list.get(i - 1).getIndex0();
                if (currentPositionId != lastPositionId) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3);
                    int margin = AfbUtils.dp2px(mContext, 3);
                    params.topMargin = margin;
                    params.bottomMargin = margin;
                    View v = new View(mContext);
                    v.setBackgroundColor(ContextCompat.getColor(mContext, R.color.green));
                    v.setLayoutParams(params);
                    currentLlAddView.addView(v);
                    llTitle.setVisibility(View.VISIBLE);
                    tvTotalOdds.setText(getString(R.string.TotalOdds) + ":" + bean.getIndex18());
                    tvAmt.setText(getString(R.string.Amt) + " " + bean.getIndex15());
                }
            }
            currentLlAddView.addView(view);
        }
    }

    private void getStatementData() {
        presenter.getStatementData();
    }

    private void getStatementLastWeekData(String startDate, String endData) {
        presenter.getStatementLastWeekData(startDate, endData);
    }

    private void getStatementOpen1Data(String date) {
        presenter.getStatemenOpen1Data(date);
    }

    private void getStatementOpen2Data(String id, String transType) {
        presenter.getStatementOpen2Data(id, transType);
    }

    private void getStatementOpen3Data(String id, String transType) {
        presenter.getStatementOpen3Data(id, transType);
    }

    @Override
    public void refreshData() {
        getStatementData();
    }
}
