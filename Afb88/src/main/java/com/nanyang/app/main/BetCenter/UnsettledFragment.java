package com.nanyang.app.main.BetCenter;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.BetCenter.Bean.RunningBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen2ListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen3ListDataBean;
import com.nanyang.app.main.BetCenter.Presenter.UnsettledPresenter;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.math.BigDecimal;
import java.util.List;

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

    }

    @Override
    public void initWaitData() {
        type = "W";
        RadioButton rb = (RadioButton) rgType.getChildAt(0);
        rb.setChecked(true);
        presenter.getRunningList(type);
    }

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
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        rv.setLayoutManager(llm);
        BaseRecyclerAdapter<RunningBean> adapter = new BaseRecyclerAdapter<RunningBean>(mContext, list, R.layout.item_running) {
            @Override
            public void convert(final MyRecyclerViewHolder holder, int position, final RunningBean item) {
                String isRun5 = item.getIsRun5();
                if(!StringUtils.isNull(isRun5)&&isRun5.equals("True")) {
                    holder.getHolderView().setBackgroundResource(R.color.green_content1);
                }else {
                    holder.getHolderView().setBackgroundResource(R.color.white);
                }
                LinearLayout ll1 = holder.getLinearLayout(R.id.ll_running_par);
                LinearLayout ll2 = holder.getLinearLayout(R.id.ll_running_hdp);
                LinearLayout l = holder.getLinearLayout(R.id.ll_running_detail_1);
                LinearLayout l2 = holder.getLinearLayout(R.id.ll_running_detail_2);
                l.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
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
                    TextView running_par_DangerStatus = holder.getTextView(R.id.running_par_DangerStatus);
                    TextView running_par_IdAndTransDate = holder.getTextView(R.id.running_par_IdAndTransDate);
                    ImageView running_par_load = holder.getImageView(R.id.running_par_load);
                    TextView par_id = holder.getTextView(R.id.par_id);
                    TextView par_type = holder.getTextView(R.id.par_type);
                    running_par_IdAndTransDate.setText("ID[" + item.getRefNo12() + "]" + item.getTransDate0());
                    running_par_DangerStatus.setText(item.getDangerStatus8());
                    String dangerStatus8 = item.getDangerStatus8();
                    dangerStatus8 = dangerStatus8.replace("&nbsp;", " ");
                    if (dangerStatus8.equals("A") || dangerStatus8.equals("D")) {
                        running_par_DangerStatus.setBackgroundColor(ContextCompat.getColor(mContext, R.color.green_dark));

                    } else if (dangerStatus8.equals("W")) {
                        running_par_DangerStatus.setBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow_button));
                    } else {
                        running_par_DangerStatus.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
                    }
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
                    TextView open_detail_list = holder.getTextView(R.id.open_detail_list);
                    ll1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LinearLayout l = holder.getLinearLayout(R.id.ll_running_detail_1);
                            LinearLayout l2 = holder.getLinearLayout(R.id.ll_running_detail_2);
                            TextView open_detail = holder.getTextView(R.id.open_detail_list);
                            if (l.getVisibility() == View.VISIBLE) {
                                l.setVisibility(View.GONE);
                                open_detail.setText(getString(R.string.OpenDetail));
                                l2.setVisibility(View.GONE);
                            } else {
                                l.setVisibility(View.VISIBLE);
                                open_detail.setText(getString(R.string.OpenDetail));
                                String id = holder.getTextView(R.id.par_id).getText().toString();
                                showLoadingDialog();
                                presenter.getParList(id, new BaseConsumer<String>(getIBaseContext()) {
                                    @Override
                                    protected void onBaseGetData(String data) throws JSONException {
                                        String updateString = data.replace("&nbsp;", " ");
                                        JSONArray jsonArray = new JSONArray(updateString);
                                        if (jsonArray.length() > 3) {
                                            final List<StatementOpen2ListDataBean> list = presenter.getBeanList2(jsonArray);
                                            RecyclerView rc1 = holder.getView(R.id.rc_par_1);
                                            LinearLayoutManager llm = new LinearLayoutManager(mContext) {
                                                @Override
                                                public boolean canScrollVertically() {
                                                    return false;
                                                }
                                            };
                                            rc1.setLayoutManager(llm);
                                            BaseRecyclerAdapter<StatementOpen2ListDataBean> adapter = new BaseRecyclerAdapter<StatementOpen2ListDataBean>(mContext, list, R.layout.item_statement_open2) {
                                                @Override
                                                public void convert(MyRecyclerViewHolder view, int position, StatementOpen2ListDataBean bean) {
                                                    updateRc1(list, view, position, bean);
                                                }
                                            };
                                            rc1.setAdapter(adapter);
                                        }
                                        hideLoadingDialog();
                                    }
                                });
                            }

                        }
                    });
                    open_detail_list.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LinearLayout l = holder.getLinearLayout(R.id.ll_running_detail_2);
                            TextView open_detail = holder.getTextView(R.id.open_detail_list);
                            if (l.getVisibility() == View.VISIBLE && open_detail.getText().equals(getString(R.string.CloseDetail))) {
                                open_detail.setText(getString(R.string.OpenDetail));
                                l.setVisibility(View.GONE);
                            } else {
                                open_detail.setText(getString(R.string.CloseDetail));
                                l.setVisibility(View.VISIBLE);
                                showLoadingDialog();
                                String id = holder.getTextView(R.id.par_id).getText().toString();
                                String par_type = holder.getTextView(R.id.par_type).getText().toString();
                                presenter.getParList2(id, par_type, new BaseConsumer<String>(getIBaseContext()) {
                                    @Override
                                    protected void onBaseGetData(String data) throws JSONException {
                                        String updateString = data.replace("&nbsp;", " ");
                                        JSONArray jsonArray = new JSONArray(updateString);
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
                                    }
                                });
                            }
                        }
                    });

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
                    String fullTimeId13 = item.getFullTimeId13().replace("(", "").replace(")", "");
                    running_FullTimeId.setText(fullTimeId13);
                    TextView running_BetType = holder.getTextView(R.id.running_BetType);
                    String transType10 = item.getTransType10();
                    switch (transType10) {
                        case "1":
                        case "2":
                        case "X":
                            transType10 = "1X2";
                            break;
                        case "HDP":
                        case "MMH":
                            transType10 = "HDP";
                            break;
                        case "OU":
                        case "MMO":
                            transType10 = "O/U";
                            break;
                        case "OE":
                            transType10 = "O/E";
                            break;
                        case "DC":
                            transType10 = "DC";
                            break;
                        case "CSR":
                            transType10 = "CSR";
                            break;
                        case "FLG":
                            transType10 = "FG/LG";
                            break;
                        case "TG":
                            transType10 = "TG";
                            break;
                        case "HFT":
                            transType10 = "HT/FT";
                            break;
                    }
                    running_BetType.setText("." + transType10 + " ");
                    TextView running_Score = holder.getTextView(R.id.running_Score);

                    TextView running_BetType2 = holder.getTextView(R.id.running_BetType2);
                    TextView running_BetType2_odds = holder.getTextView(R.id.running_BetType2_odds);
                    String betType2 = item.getBetType323();
                  /*  HtmlTagHandler.spanFontHtml(betType2);
                    if (betType2.contains("red") || item.getBetType424().contains("gbGive")) {
                        running_BetType2.setTextColor(Color.RED);
                        running_BetType2_odds.setTextColor(Color.RED);
                    } else if (betType2.contains("blue") || item.getBetType424().contains("gbTake2")) {
                        running_BetType2.setTextColor(Color.BLUE);
                    } else {
                        running_BetType2.setTextColor(Color.BLACK);
                    }
                    betType2 = AfbUtils.delHTMLTag(betType2);*/

                    String gameType314 = item.getGameType314();
                    running_BetType2.setText(HtmlTagHandler.spanFontHtml(item.getBetType424()));
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
                        running_split.setVisibility(View.INVISIBLE);
                    } else {
                        running_OldStatus.setVisibility(View.VISIBLE);
                        running_split.setVisibility(View.VISIBLE);
                    }
                    running_OldStatus.setText(item.getOldStatus22());
                    TextView running_Status = holder.getTextView(R.id.running_Status);
                    String dangerStatus8 = item.getDangerStatus8();
                    dangerStatus8 = dangerStatus8.replace("&nbsp;", " ");
                    if (dangerStatus8.equals("A") || dangerStatus8.equals("D")) {
                        running_Status.setBackgroundColor(ContextCompat.getColor(mContext, R.color.green_dark));
                    } else if (dangerStatus8.equals("W")) {
                        running_Status.setBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow_button));
                    } else {
                        running_Status.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
                    }
                    running_Status.setText(dangerStatus8);
                    TextView running_Amt = holder.getTextView(R.id.running_Amt);
                    running_Amt.setText(item.getAmt9());
                }
            }
        };
        rv.setAdapter(adapter);
        if (list != null && list.size() > 0)
            rv.setVisibility(View.VISIBLE);
        else
            rv.setVisibility(View.GONE);
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
        tvMatchVs.setText(bean.getIndex3() + "-VS-" + bean.getIndex4());
        String matchAtStr1 = bean.getIndex16();
        tvMatchAt1.setText(HtmlTagHandler.spanFontHtml(matchAtStr1));
      /*  if (matchAtStr1.contains("gbGive")) {
            tvMatchAt1.setTextColor(Color.RED);
        } else if (matchAtStr1.contains("gbTake2")) {
            tvMatchAt1.setTextColor(Color.BLUE);
        } else {
            tvMatchAt1.setTextColor(Color.BLACK);
        }*/

//        String matchAtStr2 = AfbUtils.delHTMLTag(index22);
       /* if (matchAtStr2.contains("Over") || matchAtStr2.contains("Under")) {
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


    public void updateRc1(List<StatementOpen2ListDataBean> list, MyRecyclerViewHolder view, int position, StatementOpen2ListDataBean bean) {

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
        tvMatchVs.setText(bean.getIndex3() + "-VS-" + bean.getIndex4());
        String matchAtStr1 = bean.getIndex16();
        tvMatchAt1.setText(HtmlTagHandler.spanFontHtml(matchAtStr1));
      /*  if (matchAtStr1.contains("gbGive")) {
            tvMatchAt1.setTextColor(Color.RED);
        } else if (matchAtStr1.contains("gbTake2")) {
            tvMatchAt1.setTextColor(Color.BLUE);
        } else {
            tvMatchAt1.setTextColor(Color.BLACK);
        }*/


      /*  String matchAtStr2 = AfbUtils.delHTMLTag(index22);
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
