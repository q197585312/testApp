package com.nanyang.app.main.BetCenter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.BetCenter.Bean.StatementFirstBean;
import com.nanyang.app.main.BetCenter.Bean.StatementListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen2ListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen3ListDataBean;
import com.nanyang.app.main.BetCenter.Presenter.StatementNewPresenter;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.LogUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2019/4/4.
 */

public class StatementNewFragment extends BaseFragment<StatementNewPresenter> {
    @BindView(R.id.ll_note)
    LinearLayout llNote;
    @BindView(R.id.nsc_content)
    NestedScrollView nscContent;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    private LinearLayout currentLlAddView;
    private LayoutInflater layoutInflater;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_statement_new;
    }

    public void showLoadingDialog() {

    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new StatementNewPresenter(this));
        layoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public void onResume() {
        super.onResume();
        getStatementData();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setSvContent(final StatementFirstBean statementFirstBean, List<StatementListDataBean> list) {
        if (list != null && list.size() > 0) {
            llNote.setVisibility(View.GONE);
        } else {
            llNote.setVisibility(View.VISIBLE);
        }
        llContent.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            final StatementListDataBean bean = list.get(i);
            View view = layoutInflater.inflate(R.layout.item_statement_new, null);
            if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            } else {
                view.setElevation(7f);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i == 0) {
                layoutParams.topMargin = AfbUtils.dp2px(mContext, 8);
            }
            layoutParams.bottomMargin = AfbUtils.dp2px(mContext, 8);
            layoutParams.leftMargin = AfbUtils.dp2px(mContext, 8);
            layoutParams.rightMargin = AfbUtils.dp2px(mContext, 8);
            view.setLayoutParams(layoutParams);
            TextView tvDate = view.findViewById(R.id.tv_date);
            TextView tvCom = view.findViewById(R.id.tv_com);
            TextView tvWinLose = view.findViewById(R.id.tv_WinLose);
            TextView tvSettled = view.findViewById(R.id.tv_settled);
            final LinearLayout llAddView = view.findViewById(R.id.ll_addView);
            final ImageView imgOpen1 = view.findViewById(R.id.img_open1);
            int chinaWeekDay = DateUtils.getChinaWeek(DateUtils.format(bean.getIndex1(), "yyyy-MM-dd"));
            String date = DateUtils.format(bean.getIndex1(), "yyyy-MM-dd", "dd/MM/yyyy") + " " +
                    AfbUtils.getLangWeek(mContext, chinaWeekDay);
            if (bean.getIndex0().equals("1")) {
                date = getString(R.string.Lastweeksummary);
                view.setTag(1);//1是上周数据 2是本周正常数据
            } else {
                view.setTag(2);
            }
            tvDate.setText(date);
            setWinLoseText(tvCom, bean.getIndex5(), "#.##");
            String wl = bean.getIndex4();
            setWinLoseText(tvWinLose, wl, "#.##");
            setWinLoseText(tvSettled, bean.getIndex6(), "#,###");

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentLlAddView = llAddView;
                    int visibility = llAddView.getVisibility();
                    if (visibility == View.VISIBLE) {
                        imgOpen1.setBackgroundResource(R.mipmap.down_black);
                        llAddView.setVisibility(View.GONE);
                    } else {
                        imgOpen1.setBackgroundResource(R.mipmap.up_black);
                        llAddView.setVisibility(View.VISIBLE);
                    }
                    int requestType = (int) v.getTag();
                    if (llAddView != null) {
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

    //   String f = "#,###.00";
    @SuppressLint("SetTextI18n")
    private void setWinLoseText(TextView tvWinLose, String wl, String fmt) {
        if (wl.startsWith("-")) {
            wl = wl.replace("-", "");
            tvWinLose.setTextColor(Color.RED);
            tvWinLose.setText(AfbUtils.scientificCountingToString(Float.parseFloat(wl)+"", fmt,RoundingMode.FLOOR) + " ");
        } else if (StringUtils.isNull(wl) || wl.trim().equals("0")) {
            tvWinLose.setTextColor(Color.BLACK);
            tvWinLose.setText(wl + " ");
        } else {
            tvWinLose.setTextColor(Color.BLUE);
            tvWinLose.setText(AfbUtils.scientificCountingToString(Float.parseFloat(wl)+"", fmt,RoundingMode.FLOOR) + " ");
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void onGetStatementData(StatementFirstBean statementFirstBean, List<StatementListDataBean> list) {
        setSvContent(statementFirstBean, list);
    }

    public void onGetStatementLastWeekData(List<StatementListDataBean> list) {
        currentLlAddView.removeAllViews();
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
            final ImageView imgOpen1 = view.findViewById(R.id.img_open1);
            String date = bean.getIndex1();
            final String[] dateTrue = date.split(" ");
            int chinaWeekDay = DateUtils.getChinaWeek(DateUtils.format(bean.getIndex1(), "yyyy-MM-dd"));
            String dateStr = DateUtils.format(dateTrue[0], "yyyy-MM-dd", "dd/MM/yyyy") + " " +
                    AfbUtils.getLangWeek(mContext, chinaWeekDay);
            if (bean.getIndex0().equals("1")) {
                dateStr = getString(R.string.Lastweeksummary);
            }
            tvDate.setText(dateStr);
            setWinLoseText(tvCom, bean.getIndex5(), "#.##");
            String wl = bean.getIndex4();
            setWinLoseText(tvWinLose, wl, "#.##");
            setWinLoseText(tvSettled, bean.getIndex6(), "#,###");
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentLlAddView = llAddView;
                    int visibility = llAddView.getVisibility();
                    if (visibility == View.VISIBLE) {
                        imgOpen1.setBackgroundResource(R.mipmap.down_black);
                        llAddView.setVisibility(View.GONE);
                    } else {
                        imgOpen1.setBackgroundResource(R.mipmap.up_black);
                        llAddView.setVisibility(View.VISIBLE);
                    }
                    if (llAddView != null) {
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
        if (currentLlAddView != null)
            currentLlAddView.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            final StatementListDataBean bean = list.get(i);
            final String index11 = bean.getIndex11();
            final String index8 = bean.getIndex8();
            View view;
            String index3 = bean.getIndex3();
            String index9 = bean.getIndex9();
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
                String end17 = bean.getIndex17();
                String s = AfbUtils.getPayout(index3, index9, end17);
                tvEstPayout.setText(getString(R.string.Est_Payout) + " " + s);
                tvOdds.setText(getString(R.string.Odds) + " " + index3);
                tvType.setText(bean.getIndex17());
                tvAmt.setText(getString(R.string.Amt) + ": " + index9);
                setWinLoseText(tvWl,bean.getIndex10(),"#.##");
                setWinLoseText(tvCom,bean.getIndex18(),"#.##");
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
                        if (llAddView1 != null) {
                            getStatementOpen2Data(bean.getIndex22(), index11, bean.getIndex17());
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
                TextView tvMatchAtFt = view.findViewById(R.id.tv_match_at_ft);
                TextView tvWl = view.findViewById(R.id.tv_wl);
                tvIdDate.setText(getString(R.string.ID) + ":[" + bean.getIndex13() + "]" + bean.getIndex0());
                int number = i + 1;
                tvNumber.setText(number < 10 ? ("0" + number) : "" + number);
                tvMatchType.setText(bean.getIndex12());

                tvMatchVs.setText(bean.getIndex1() + "-VS-" + bean.getIndex2());
                String index24 = bean.getIndex24();
                String index25 = bean.getIndex25();

                tvMatchAtFt.setText(bean.getIndex14());
                String index16 = bean.getIndex16();
                boolean isOutRight = bean.getIndex15().equals("O");
                if (isOutRight) {
                    tvMatchAt1.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    tvMatchAt1.setText("Outright");
                    tvMatchAtFt.setVisibility(View.GONE);
                    tvMatchAt2.setVisibility(View.GONE);
                    tvMatchAt3.setVisibility(View.GONE);
                    tvMatchAt4.setVisibility(View.GONE);
                } else {
                    tvMatchAt1.setText(HtmlTagHandler.spanFontHtml(index24));
                    tvMatchAt2.setText(HtmlTagHandler.spanFontHtml(index25));
                    tvMatchAt3.setText(HtmlTagHandler.spanFontHtml(index3));
                    if (!TextUtils.isEmpty(index16.trim())) {
                        tvMatchAt4.setText("(" + bean.getIndex16() + ")");
                        tvMatchAt4.setVisibility(View.VISIBLE);
                    } else {
                        tvMatchAt4.setText("");
                        tvMatchAt4.setVisibility(View.GONE);
                    }
                    tvMatchAtFt.setVisibility(View.VISIBLE);
                    tvMatchAt2.setVisibility(View.VISIBLE);
                    tvMatchAt3.setVisibility(View.VISIBLE);

                }
                tvMatchGrade.setText(HtmlTagHandler.spanFontHtml(bean.getIndex5() + bean.getIndex8() + " " + bean.getIndex20()));
                tvAmt.setText(getString(R.string.Amt) + " " + index9);

                String odds = AfbUtils.delHTMLTag(index3);
                if (odds.startsWith("-")) {
                    tvOdds.setTextColor(Color.RED);

                } else {
                    tvOdds.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                }
                tvOdds.setText(" " + odds);
                if (!TextUtils.isEmpty(bean.getIndex16()))
                    tvHandicap.setText("(" + bean.getIndex16() + ")");
                else {
                    tvHandicap.setText("");
                }
                tvCom.setText(getString(R.string.Com) + " " + bean.getIndex18());
                String winLose = bean.getIndex10();

                if (winLose.startsWith("-")) {
                    tvWl.setTextColor(Color.RED);
                } else if (winLose.equals("0")) {
                    tvWl.setTextColor(Color.BLACK);
                } else {
                    tvWl.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
                }
                String replace = winLose.replace("-", "").trim();
                tvWl.setText(" " + replace);
                if (!StringUtils.isNull(replace) && Float.valueOf(replace) > 0f) {
                    BigDecimal b = new BigDecimal(replace);
                    //保留2位小数
                    double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    tvWl.setText(" " + f1);
                }


                if (index11.startsWith("G")) {
                    tvMatchVs.setVisibility(View.GONE);
                    view.findViewById(R.id.flow_layout).setVisibility(View.GONE);
                    tvMatchGrade.setText(HtmlTagHandler.spanFontHtml(bean.getIndex25()));

                }
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.bottomMargin = AfbUtils.dp2px(mContext, 8);
            view.setLayoutParams(layoutParams);
            currentLlAddView.addView(view);
        }
    }


    public void onGetStatementOpen2Data(List<StatementOpen2ListDataBean> list, final String id, final String transType, String index17) {
        if (currentLlAddView != null)
            currentLlAddView.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            final StatementOpen2ListDataBean bean = list.get(i);
            View view = layoutInflater.inflate(R.layout.item_statement_open2, null);
            view.setBackgroundResource(R.color.white);
            TextView tvIdDate = view.findViewById(R.id.tv_id_date);
            TextView tvMatchType = view.findViewById(R.id.tv_match_type);
            TextView tvMatchVs = view.findViewById(R.id.tv_match_vs);
            TextView tvMatchAt1 = view.findViewById(R.id.tv_match_at1);
            TextView tv_match_at2_1 = view.findViewById(R.id.tv_match_at2_1);
            TextView tv_match_score = view.findViewById(R.id.tv_match_score);
            TextView tvMatchAt3 = view.findViewById(R.id.tv_match_at3);
            TextView tvMatchAt4 = view.findViewById(R.id.tv_match_at4);
            TextView tvWL = view.findViewById(R.id.tv_wl);
            final LinearLayout llAddView2 = view.findViewById(R.id.ll_addView2);
            TextView tvScore = view.findViewById(R.id.tv_score);
            if (i > 0) {
                String lastId = list.get(i - 1).getIndex21();
                String currentId = bean.getIndex21();
                if (currentId.equals(lastId)) {
                    tvIdDate.setVisibility(View.INVISIBLE);
                } else {
                    tvIdDate.setVisibility(View.VISIBLE);
                }
            }
            tvIdDate.setText(bean.getIndex21() + "(" + bean.getIndex5() + ")");
            tvMatchType.setText(bean.getIndex1());
            tvMatchVs.setText(bean.getIndex3() + "-VS-" + bean.getIndex4());
            String matchAtStr1 = bean.getIndex16();
            tvMatchAt1.setText(HtmlTagHandler.spanFontHtml(matchAtStr1));
         /*   tvMatchAt1.setText(AfbUtils.delHTMLTag(matchAtStr1));
            if (matchAtStr1.contains("gbGive")) {
                tvMatchAt1.setTextColor(Color.RED);
            } else if (matchAtStr1.contains("gbTake2")) {
                tvMatchAt1.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
            } else {
                tvMatchAt1.setTextColor(Color.BLACK);
            }*/

            String index22 = bean.getIndex22();
             /*   String matchAtStr2 = AfbUtils.delHTMLTag(index22);
            if (matchAtStr2.contains("Over") || matchAtStr2.contains("Under")) {
                if (index22.contains("red")) {
                    tv_match_at2_1.setTextColor(Color.RED);
                } else if (index22.contains("blue")) {
                    tv_match_at2_1.setTextColor(Color.BLUE);
                } else {
                    tv_match_at2_1.setTextColor(Color.BLACK);
                }
                tv_match_at2_1.setText(matchAtStr2);
                tv_match_at2_2.setText("");
            } else {
                if (matchAtStr2.contains(" ")) {
                    String[] split = matchAtStr2.split(" ");
                    tv_match_at2_1.setText(split[0] + " ");
                    tv_match_at2_2.setText(split[1]);
                    if (index22.contains("red")) {
                        tv_match_at2_2.setTextColor(Color.RED);
                    } else if (index22.contains("blue")) {
                        tv_match_at2_2.setTextColor(Color.BLUE);
                    } else {
                        tv_match_at2_2.setTextColor(Color.BLACK);
                    }
                } else {
                    if (index22.contains("red")) {
                        tv_match_at2_1.setTextColor(Color.RED);
                    } else if (index22.contains("blue")) {
                        tv_match_at2_1.setTextColor(Color.BLUE);
                    } else {
                        tv_match_at2_1.setTextColor(Color.BLACK);
                    }
                    tv_match_at2_1.setText(matchAtStr2);
                    tv_match_at2_2.setText("");
                }
            }*/
            Spanned spanned = HtmlTagHandler.spanFontHtml(index22);
            tv_match_at2_1.setText(spanned);

            LogUtil.d("index20", "index20:" + bean.getIndex20() + "," + bean.getIndex18() + "-" + bean.getIndex19());
            if (bean.getIndex20().equals("True")) {
                tv_match_score.setVisibility(View.VISIBLE);
                String showStr = " " + bean.getIndex18() + "-" + bean.getIndex19() + " ";
                tv_match_score.setText(showStr);
            } else {
                tv_match_score.setVisibility(View.GONE);
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
                if (StringUtils.isNull(index17) || index17.endsWith("1")) {
                    tvDetail.setVisibility(View.GONE);
                } else {
                    tvDetail.setVisibility(View.VISIBLE);
                }

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
                        if (llAddView2 != null) {
                            getStatementOpen3Data(id, transType);
                        }
                    }
                });
            }
            currentLlAddView.addView(view);
        }
    }

    public void onGetStatementOpen3Data(List<StatementOpen3ListDataBean> list) {
        if (currentLlAddView != null)
            currentLlAddView.removeAllViews();
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
            TextView tv_match_at2_1 = view.findViewById(R.id.tv_match_at2_1);
            TextView tv_match_at2_2 = view.findViewById(R.id.tv_match_at2_2);
            TextView tvMatchAt3 = view.findViewById(R.id.tv_match_at3);
            TextView tvMatchAt4 = view.findViewById(R.id.tv_match_at4);
            TextView tvWL = view.findViewById(R.id.tv_wl);
            TextView tvScore = view.findViewById(R.id.tv_score);
            TextView tv_match_score = view.findViewById(R.id.tv_match_score);
            if (i > 0) {
                String lastId = list.get(i - 1).getIndex22();
                String currentId = bean.getIndex22();
                if (currentId.equals(lastId)) {
                    tvIdDate.setVisibility(View.INVISIBLE);
                } else {
                    tvIdDate.setVisibility(View.VISIBLE);
                }
            }
            tvIdDate.setText(bean.getIndex22() + "(" + bean.getIndex5() + ")");
            tvMatchType.setText(bean.getIndex1());
            tvMatchVs.setText(bean.getIndex3() + "-VS-" + bean.getIndex4());
            String matchAtStr1 = bean.getIndex16();
//            tvMatchAt1.setText(AfbUtils.delHTMLTag(matchAtStr1));
            tvMatchAt1.setText(HtmlTagHandler.spanFontHtml(matchAtStr1));
         /*   if (matchAtStr1.contains("gbGive")) {
                tvMatchAt1.setTextColor(Color.RED);
            } else if (matchAtStr1.contains("gbTake2")) {
                tvMatchAt1.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
            } else {
                tvMatchAt1.setTextColor(Color.BLACK);
            }*/

            String index22 = bean.getIndex23();
//            String matchAtStr2 = AfbUtils.delHTMLTag(index22);
           /* if (matchAtStr2.contains("Over") || matchAtStr2.contains("Under")) {
                if (index22.contains("red")) {
                    tv_match_at2_1.setTextColor(Color.RED);
                } else if (index22.contains("blue")) {
                    tv_match_at2_1.setTextColor(Color.BLUE);
                } else {
                    tv_match_at2_1.setTextColor(Color.BLACK);
                }

//                Spanned spanned = matchAtStr2, nuspanFontHtml()HtmlTaHandler("myfont"));
                tv_match_at2_1.setText(matchAtStr2));
spanFontHtml()         tv_match_at2_1.setText(matchAtStr2);
                tv_match_at2_2.setText("");
            } else {
                if (matchAtStr2.contains(" ")) {
                    String[] split = matchAtStr2.split(" ");
                    tv_match_at2_1.setText(split[0] + " ");
                    tv_match_at2_2.setText(split[1]);


                    if (index22.contains("red")) {
                        tv_match_at2_2.setTextColor(Color.RED);
                    } else if (index22.contains("blue")) {
                        tv_match_at2_2.setTextColor(Color.BLUE);
                    } else {
                        tv_match_at2_2.setTextColor(Color.BLACK);
                    }
                } else {
                    if (index22.contains("red")) {
                        tv_match_at2_1.setTextColor(Color.RED);
                    } else if (index22.contains("blue")) {
                        tv_match_at2_1.setTextColor(Color.BLUE);
                    } else {
                        tv_match_at2_1.setTextColor(Color.BLACK);
                    }
                    tv_match_at2_1.setText(matchAtStr2);
                    tv_match_at2_2.setText("");
                }
            }*/
            tv_match_at2_1.setText(HtmlTagHandler.spanFontHtml(index22));
            LogUtil.d("index20", "index21:" + bean.getIndex21() + "," + bean.getIndex19() + "-" + bean.getIndex20());
            if (bean.getIndex21().equals("True")) {
                tv_match_score.setVisibility(View.VISIBLE);
                String showStr = " " + bean.getIndex19() + "-" + bean.getIndex20() + " ";
                tv_match_score.setText(showStr);
            } else {
                tv_match_score.setVisibility(View.GONE);
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

    private void getStatementOpen2Data(String id, String transType, String index17) {
        presenter.getStatementOpen2Data(id, transType, index17);
    }

    private void getStatementOpen3Data(String id, String transType) {
        presenter.getStatementOpen3Data(id, transType);
    }

    @Override
    public void refreshData() {
        getStatementData();
    }
}
