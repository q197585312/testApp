package com.nanyang.app.main.BetCenter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.main.BetCenter.Bean.StatementFirstBean;
import com.nanyang.app.main.BetCenter.Bean.StatementListDataBean;
import com.nanyang.app.main.BetCenter.Presenter.StatementNewPresenter;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2019/4/4.
 */

public class StatementNewFragment extends BaseFragment<StatementNewPresenter> {
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    private int clickPosition = -1;
    private List<LinearLayout> itemViewList;
    private LayoutInflater layoutInflater;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_statement_new;
    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new StatementNewPresenter(this));
        itemViewList = new ArrayList<>();
        layoutInflater = LayoutInflater.from(mContext);
        getStatementData();
    }

    private List<StatementListDataBean> lastStatementList;

    private void setSvContent(List<StatementListDataBean> list) {
        if (lastStatementList == null) {
            lastStatementList = list;
        } else {
            if (lastStatementList.size() == list.size()) {
                return;
            }
        }
        itemViewList.clear();
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
            final ImageView imgOpen1 = view.findViewById(R.id.img_open1);
            final LinearLayout llAddView = view.findViewById(R.id.ll_addView);
            imgOpen1.setTag(i);
            tvDate.setText(DateUtils.format(bean.getIndex1(), "yyyy-MM-dd", "dd/MM/yyyy") + " " +
                    DateUtils.getChinaWeek(DateUtils.format(bean.getIndex1(), "yyyy-MM-dd")));
            tvCom.setText("COM:" + bean.getIndex5() + " ");
            String wl = bean.getIndex4();
            tvWinLose.setText(" " + wl + " ");
            if (wl.startsWith("-")) {
                tvWinLose.setTextColor(Color.RED);
            } else {
                tvWinLose.setTextColor(Color.BLUE);
            }
            tvSettled.setText(" SETTLED:" + bean.getIndex6());
            imgOpen1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (llAddView.getChildCount() < 1) {
                        getStatementOpen1Data(bean.getIndex1());
                    } else {
                        int visibility = llAddView.getVisibility();
                        if (visibility == View.VISIBLE) {
                            llAddView.setVisibility(View.GONE);
                        } else {
                            llAddView.setVisibility(View.VISIBLE);
                        }
                    }
                    clickPosition = (int) v.getTag();
                }
            });
            itemViewList.add(llAddView);
            llContent.addView(view);
        }
    }

    public void onGetStatementData(StatementFirstBean statementFirstBean, List<StatementListDataBean> list) {
        setSvContent(list);
    }

    public void onGetStatementOpen1Data(List<StatementListDataBean> list) {
        LinearLayout llAddView = itemViewList.get(clickPosition);
        for (int i = 0; i < list.size(); i++) {
            StatementListDataBean bean = list.get(i);
            String index11 = bean.getIndex11();
            View view;
            if (index11.equals("PAR") || index11.equals("PAM")) {
                view = layoutInflater.inflate(R.layout.item_statement_open1_typ1, null);
                TextView tvIdDate = view.findViewById(R.id.tv_id_date);
                TextView tvNumber = view.findViewById(R.id.tv_number);
                TextView tvMatchType = view.findViewById(R.id.tv_match_type);
                TextView tvEstPayout = view.findViewById(R.id.tv_est_payout);
                TextView tvOdds = view.findViewById(R.id.tv_odds);
                TextView tvType = view.findViewById(R.id.tv_type);
                ImageView imgOpen2 = view.findViewById(R.id.img_open2);
                TextView tvAmt = view.findViewById(R.id.tv_amt);
                TextView tvWl = view.findViewById(R.id.tv_wl);
                TextView tvCom = view.findViewById(R.id.tv_com);
                tvIdDate.setText("ID:[" + bean.getIndex13() + "]" + bean.getIndex0());
                int number = i + 1;
                tvNumber.setText(number < 10 ? ("0" + number) : "" + number);
                tvMatchType.setText(bean.getIndex1());
                tvEstPayout.setText("Est./Payout: " + AfbUtils.decimalValue(Float.parseFloat(bean.getIndex3()) * Float.parseFloat(bean.getIndex9()), "0.00"));
                tvOdds.setText("Odds:" + bean.getIndex3());
                tvType.setText(bean.getIndex17());
                tvAmt.setText("AMT:" + bean.getIndex9());
                String winLose = bean.getIndex10();
                tvWl.setText(winLose);
                if (winLose.startsWith("-")) {
                    tvWl.setTextColor(Color.RED);
                } else {
                    tvWl.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
                }
                tvCom.setText("COM:" + bean.getIndex18());
                imgOpen2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("imgOpen2");
                    }
                });
            } else {
                view = layoutInflater.inflate(R.layout.item_statement_open1_typ2, null);
                TextView tvIdDate = view.findViewById(R.id.tv_id_date);
                TextView tvNumber = view.findViewById(R.id.tv_number);
                TextView tvMatchType = view.findViewById(R.id.tv_match_type);
                TextView tvMatchVs = view.findViewById(R.id.tv_match_vs);
                TextView tvMatchAt = view.findViewById(R.id.tv_match_at);
                TextView tvMatchGrade = view.findViewById(R.id.tv_match_grade);
                TextView tvAmt = view.findViewById(R.id.tv_amt);
                TextView tvOdds = view.findViewById(R.id.tv_odds);
                TextView tvHandicap = view.findViewById(R.id.tv_handicap);
                TextView tvCom = view.findViewById(R.id.tv_com);
                TextView tvWl = view.findViewById(R.id.tv_wl);
                tvIdDate.setText("ID:[" + bean.getIndex13() + "]" + bean.getIndex0());
                int number = i + 1;
                tvNumber.setText(number < 10 ? ("0" + number) : "" + number);
                tvMatchType.setText(bean.getIndex12());
                tvMatchVs.setText(bean.getIndex1() + "-VS-" + bean.getIndex2());
                tvMatchAt.setText(bean.getIndex24() + bean.getIndex14() + " " + bean.getIndex24() + "@" + bean.getIndex3() + "(" + bean.getIndex16() + ")");
                tvMatchGrade.setText(bean.getIndex5() + " " + bean.getIndex20());
                tvAmt.setText("AMT:" + bean.getIndex9());
                String odds = bean.getIndex3();
                if (odds.startsWith("-")) {
                    tvOdds.setTextColor(Color.RED);
                } else {
                    tvOdds.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
                }
                tvOdds.setText(odds);
                tvHandicap.setText("(" + bean.getIndex16() + ")");
                tvCom.setText("COM:" + bean.getIndex18());
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
            llAddView.addView(view);
        }
        llAddView.setVisibility(View.VISIBLE);

    }

    private void getStatementData() {
        presenter.getStatementData();
    }

    private void getStatementOpen1Data(String date) {
        presenter.getStatemenOpen1Data(date);
    }

    @Override
    public void refreshData() {
        getStatementData();
    }
}
