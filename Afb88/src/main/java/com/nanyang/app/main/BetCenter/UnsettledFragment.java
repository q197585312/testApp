package com.nanyang.app.main.BetCenter;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.BetCenter.Bean.RunningBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen2ListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen3ListDataBean;
import com.nanyang.app.main.BetCenter.Presenter.UnsettledPresenter;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.BaseFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
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
                switch (checkedId){
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

    public void setRvlist(List<RunningBean> list){
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        rv.setLayoutManager(llm);
        BaseRecyclerAdapter<RunningBean> adapter = new BaseRecyclerAdapter<RunningBean>(mContext,list,R.layout.item_running) {
            @Override
            public void convert(final MyRecyclerViewHolder holder, int position, final RunningBean item) {
                LinearLayout ll1 = holder.getLinearLayout(R.id.ll_running_par);
                LinearLayout ll2 = holder.getLinearLayout(R.id.ll_running_hdp);
                LinearLayout l = holder.getLinearLayout(R.id.ll_running_detail_1);
                LinearLayout l2 = holder.getLinearLayout(R.id.ll_running_detail_2);
                l.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                if(item.getBetType18().equals("PAR")){
                    ll1.setVisibility(View.VISIBLE);
                    ll2.setVisibility(View.GONE);
                    TextView running_par_Home = holder.getTextView(R.id.running_par_Home);
                    running_par_Home.setText(item.getHome1());
                    TextView running_par_Odds1 = holder.getTextView(R.id.running_par_Odds1);
                    TextView running_par_Odds2 = holder.getTextView(R.id.running_par_Odds2);
                    TextView running_par_CombInfo = holder.getTextView(R.id.running_par_CombInfo);
                    TextView running_par_Amt = holder.getTextView(R.id.running_par_Amt);
                    TextView running_par_DangerStatus = holder.getTextView(R.id.running_par_DangerStatus);
                    TextView running_par_IdAndTransDate = holder.getTextView(R.id.running_par_IdAndTransDate);
                    ImageView running_par_load = holder.getImageView(R.id.running_par_load);
                    TextView par_id = holder.getTextView(R.id.par_id);
                    TextView par_type = holder.getTextView(R.id.par_type);
                    running_par_IdAndTransDate.setText("ID["+item.getRefNo12()+"]"+item.getTransDate0());
                    running_par_DangerStatus.setText(item.getDangerStatus8());
                    String odds = item.getOdds3();
                    double fodds = Double.parseDouble(odds)*100;
                    running_par_Odds1.setText("Est./Payout:"+fodds);
                    running_par_Odds2.setText(getString(R.string.Odds)+ ":"+odds);
                    running_par_CombInfo.setText(item.getCombInfo16());
                    running_par_Amt.setText(getString(R.string.Amt)+":"+item.getAmt9());
                    par_id.setText(item.getSocTransId17());
                    par_type.setText(item.getBetType18());
                    TextView open_detail_list = holder.getTextView(R.id.open_detail_list);
                    ll1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LinearLayout l = holder.getLinearLayout(R.id.ll_running_detail_1);
                            LinearLayout l2 = holder.getLinearLayout(R.id.ll_running_detail_2);
                            TextView open_detail = holder.getTextView(R.id.open_detail_list);
                            if(l.getVisibility()==View.VISIBLE) {
                                l.setVisibility(View.GONE);
                                open_detail.setText(getString(R.string.OpenDetail));
                                l2.setVisibility(View.GONE);
                            }else{
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
                                            List<StatementOpen2ListDataBean> list = presenter.getBeanList2(jsonArray);
                                            RecyclerView rc1 = holder.getView(R.id.rc_par_1);
                                            LinearLayoutManager llm = new LinearLayoutManager(mContext);
                                            rc1.setLayoutManager(llm);
                                            BaseRecyclerAdapter<StatementOpen2ListDataBean> adapter = new BaseRecyclerAdapter<StatementOpen2ListDataBean>(mContext,list,R.layout.item_statement_open2) {
                                                @Override
                                                public void convert(MyRecyclerViewHolder view, int position, StatementOpen2ListDataBean bean) {
                                                    updateRc1(view,position,bean);
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
                            if(l.getVisibility()==View.VISIBLE && open_detail.getText().equals(getString(R.string.CloseDetail))) {
                                open_detail.setText(getString(R.string.OpenDetail));
                                l.setVisibility(View.GONE);
                            }else {
                                open_detail.setText(getString(R.string.CloseDetail));
                                l.setVisibility(View.VISIBLE);
                                showLoadingDialog();
                                String id = holder.getTextView(R.id.par_id).getText().toString();
                                String par_type = holder.getTextView(R.id.par_type).getText().toString();
                                presenter.getParList2(id,par_type, new BaseConsumer<String>(getIBaseContext()) {
                                    @Override
                                    protected void onBaseGetData(String data) throws JSONException {
                                        Log.i("XUZIWEI", "onBaseGetData: "+data);
                                        String updateString = data.replace("&nbsp;", " ");
                                        JSONArray jsonArray = new JSONArray(updateString);
                                        if (jsonArray.length() > 3) {
                                            List<StatementOpen3ListDataBean> list = presenter.getBeanList3(jsonArray);
                                            RecyclerView rc1 = holder.getView(R.id.rc_par_2);
                                            LinearLayoutManager llm = new LinearLayoutManager(mContext);
                                            rc1.setLayoutManager(llm);
                                            BaseRecyclerAdapter<StatementOpen3ListDataBean> adapter = new BaseRecyclerAdapter<StatementOpen3ListDataBean>(mContext,list,R.layout.item_statement_open2) {
                                                @Override
                                                public void convert(MyRecyclerViewHolder view, int position, StatementOpen3ListDataBean bean) {
                                                    updateRc2(view,position,bean);
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

                }else{
                    ll1.setVisibility(View.GONE);
                    ll2.setVisibility(View.VISIBLE);
                    TextView refNo = holder.getTextView(R.id.running_RefNo);
                    refNo.setText(item.getRefNo12());
                    TextView running_TransDate = holder.getTextView(R.id.running_TransDate);
                    running_TransDate.setText(" ("+item.getTransDate0()+")");
                    TextView running_ModuleTitle = holder.getTextView(R.id.running_ModuleTitle);
                    running_ModuleTitle.setText(item.getModuleTitle11());
                    TextView running_Home = holder.getTextView(R.id.running_Home);
                    running_Home.setText(item.getHome1());
                    TextView running_Away = holder.getTextView(R.id.running_Away);
                    running_Away.setText(item.getAway2());
                    TextView running_BetType = holder.getTextView(R.id.running_BetType);
                    String betType = item.getBetType18();
                    if (betType.contains("gbGive")) {
                        running_BetType.setTextColor(Color.RED);
                    } else {
                        running_BetType.setTextColor(ContextCompat.getColor(mContext, R.color.blue2));
                    }
                    betType = AfbUtils.delHTMLTag(betType);
                    running_BetType.setText(betType);
                    TextView running_FullTimeId = holder.getTextView(R.id.running_FullTimeId);
                    running_FullTimeId.setText(item.getFullTimeId13()+" ");
                    TextView running_Score = holder.getTextView(R.id.running_Score);
                    running_Score.setText(item.getScore19()+" ");
                    TextView running_BetType2 = holder.getTextView(R.id.running_BetType2);
                    String betType2 = item.getBetType221();
                    betType2 = AfbUtils.delHTMLTag(betType2);
                    running_BetType2.setText(betType2+" ");
                    TextView running_Odds = holder.getTextView(R.id.running_Odds);
                    String odds = item.getOdds3();
                    odds = AfbUtils.delHTMLTag(odds);
                    running_Odds.setText("@"+odds+" ");
                    TextView running_OddsType = holder.getTextView(R.id.running_OddsType);
                    running_OddsType.setText(item.getOddsType15()+" ");
                    TextView running_OldStatus = holder.getTextView(R.id.running_OldStatus);
                    TextView running_split = holder.getTextView(R.id.running_split);
                    if(TextUtils.isEmpty(item.getOldStatus22())){
                        running_split.setVisibility(View.INVISIBLE);
                    }else{
                        running_split.setVisibility(View.VISIBLE);
                    }
                    running_OldStatus.setText(item.getOldStatus22());
                    TextView running_Status = holder.getTextView(R.id.running_Status);
                    running_Status.setText(item.getStatus20());
                    TextView running_Amt = holder.getTextView(R.id.running_Amt);
                    running_Amt.setText(item.getAmt9());
                }
            }
        };
        rv.setAdapter(adapter);
    }



    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void refreshData() {
        presenter.getRunningList(type);
    }

    public void updateRc2(MyRecyclerViewHolder view, int position, StatementOpen3ListDataBean bean){
        LinearLayout llTitle = view.getLinearLayout(R.id.ll_title);
        TextView tvTotalOdds = view.getTextView(R.id.tv_total_odds);
        TextView tvAmt = view.getTextView(R.id.tv_amt);
        TextView tvIdDate = view.getTextView(R.id.tv_id_date);
        TextView tvMatchType = view.getTextView(R.id.tv_match_type);
        TextView tvMatchVs = view.getTextView(R.id.tv_match_vs);
        TextView tvMatchAt1 = view.getTextView(R.id.tv_match_at1);
        TextView tvMatchAt2 = view.getTextView(R.id.tv_match_at2);
        TextView tvMatchAt3 = view.getTextView(R.id.tv_match_at3);
        TextView tvMatchAt4 = view.getTextView(R.id.tv_match_at4);
        TextView tvWL = view.getTextView(R.id.tv_wl);
        TextView tvScore = view.getTextView(R.id.tv_score);
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
        if(position==0){
            llTitle.setVisibility(View.VISIBLE);
            tvTotalOdds.setText(getString(R.string.TotalOdds) + ":" + bean.getIndex18());
            tvAmt.setText(getString(R.string.Amt) + " " + bean.getIndex15());
        }
    }

    public void updateRc1(MyRecyclerViewHolder view, int position, StatementOpen2ListDataBean bean){
        TextView tvIdDate = view.getTextView(R.id.tv_id_date);
        TextView tvMatchType = view.getTextView(R.id.tv_match_type);
        TextView tvMatchVs = view.getTextView(R.id.tv_match_vs);
        TextView tvMatchAt1 = view.getTextView(R.id.tv_match_at1);
        TextView tvMatchAt2 = view.getTextView(R.id.tv_match_at2);
        TextView tvMatchAt3 = view.getTextView(R.id.tv_match_at3);
        TextView tvMatchAt4 = view.getTextView(R.id.tv_match_at4);
        TextView tvWL = view.getTextView(R.id.tv_wl);
        final LinearLayout llAddView2 = view.getLinearLayout(R.id.ll_addView2);
        TextView tvScore = view.getTextView(R.id.tv_score);
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
    }

}
