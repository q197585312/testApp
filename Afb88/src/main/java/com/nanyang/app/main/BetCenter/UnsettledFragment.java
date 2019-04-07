package com.nanyang.app.main.BetCenter;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.BetCenter.Bean.RunningBean;
import com.nanyang.app.main.BetCenter.Presenter.UnsettledPresenter;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

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
            public void convert(MyRecyclerViewHolder holder, int position, RunningBean item) {
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
}
