package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import nanyang.com.dig88.Entity.Afb2BetRecordBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.IbcBetRecordBean;
import nanyang.com.dig88.Entity.ReportFormBean;
import nanyang.com.dig88.Entity.SboBetRecordBean;
import nanyang.com.dig88.Fragment.Presenter.BetRecordPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/21.(未下注记录)
 */

public class BetRecordFragment extends BaseFragment<BetRecordPresenter> {
    @BindView(R.id.rc_deposit)
    RecyclerView rcDeposit;
    @BindView(R.id.tv_choice_type)
    TextView tvChoiceType;
    List<ContentInfoBean> list;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_bet_record;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getAct().setTitle(getString(R.string.xiazhujilu));
        getAct().setleftViewEnable(true);
        createPresenter(new BetRecordPresenter(this));
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        list = presenter.getContentList();
        presenter.getBetListData(list.get(0));
    }

    public void onGetIbcData(List<IbcBetRecordBean.DataBean> ibcList) {
        BaseRecyclerAdapter<IbcBetRecordBean.DataBean> adapter = new BaseRecyclerAdapter<IbcBetRecordBean.DataBean>(mContext, ibcList, R.layout.item_afb2_bet_record) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, IbcBetRecordBean.DataBean item) {
                RelativeLayout rl_content = holder.getView(R.id.rl_content);
                TextView tv_no_data = holder.getView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = holder.getView(R.id.tv_no);
                    TextView tv_game = holder.getView(R.id.tv_game);
                    TextView tv_even = holder.getView(R.id.tv_even);
                    TextView tv_bet_detail = holder.getView(R.id.tv_bet_detail);
                    TextView tv_bet_time = holder.getView(R.id.tv_bet_time);
                    TextView tv_bet_amount = holder.getView(R.id.tv_bet_amount);
                    tv_no.setText(getString(R.string.no) + (position + 1));
                    tv_game.setText(getString(R.string.youxitype) + tvChoiceType.getText().toString());
                    tv_even.setText(item.getBet_detail() + "\n" + item.getHdp() + "\n" + item.getBet_type_name() + "\n" +
                            item.getHome_team_name() + "-Vs-" + item.getAway_team_name() + "\n" + item.getSport_table_name() + "/" + item.getLeague_name());
                    tv_bet_detail.setText(getString(R.string.bet_detail) + item.getOdds() + " " + item.getOdds_type_name());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_bet_amount.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount());
                }
            }
        };
        rcDeposit.setLayoutManager(new LinearLayoutManager(mContext));
        rcDeposit.setAdapter(adapter);
    }

    public void onGetSboData(List<SboBetRecordBean.DataBean> sboList) {
        BaseRecyclerAdapter<SboBetRecordBean.DataBean> adapter = new BaseRecyclerAdapter<SboBetRecordBean.DataBean>(mContext, sboList, R.layout.item_afb2_bet_record) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, SboBetRecordBean.DataBean item) {
                RelativeLayout rl_content = holder.getView(R.id.rl_content);
                TextView tv_no_data = holder.getView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = holder.getView(R.id.tv_no);
                    TextView tv_game = holder.getView(R.id.tv_game);
                    TextView tv_even = holder.getView(R.id.tv_even);
                    TextView tv_bet_detail = holder.getView(R.id.tv_bet_detail);
                    TextView tv_bet_time = holder.getView(R.id.tv_bet_time);
                    TextView tv_bet_amount = holder.getView(R.id.tv_bet_amount);
                    tv_no.setText(getString(R.string.no) + (position + 1));
                    tv_game.setText(getString(R.string.youxitype) + item.getSport_type());
                    tv_even.setText(item.getBet_team() + "\n" + item.getMatch_name() + "\n" + item.getLeague() + "@" + item.getMatch_time());
                    tv_bet_detail.setText(getString(R.string.bet_detail) + item.getOdds());
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                    tv_bet_amount.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount());
                }
            }
        };
        rcDeposit.setLayoutManager(new LinearLayoutManager(mContext));
        rcDeposit.setAdapter(adapter);
    }

    public void onGetAfbData(List<Afb2BetRecordBean.DataBean> afbList) {
        BaseRecyclerAdapter<Afb2BetRecordBean.DataBean> adapter = new BaseRecyclerAdapter<Afb2BetRecordBean.DataBean>(mContext, afbList, R.layout.item_afb2_bet_record) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, Afb2BetRecordBean.DataBean item) {
                RelativeLayout rl_content = holder.getView(R.id.rl_content);
                TextView tv_no_data = holder.getView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = holder.getView(R.id.tv_no);
                    TextView tv_game = holder.getView(R.id.tv_game);
                    TextView tv_even = holder.getView(R.id.tv_even);
                    TextView tv_bet_detail = holder.getView(R.id.tv_bet_detail);
                    TextView tv_bet_time = holder.getView(R.id.tv_bet_time);
                    TextView tv_bet_amount = holder.getView(R.id.tv_bet_amount);
                    tv_no.setText(getString(R.string.no) + (position + 1));
                    tv_game.setText(getString(R.string.youxitype) + tvChoiceType.getText().toString());
                    tv_even.setText(item.getLeague_name() + "\n" + item.getHome() + " VS " + item.getAway() + "\n" + item.getWorkdate());
                    tv_bet_detail.setText(getBetDetail(item));
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getLaster_update_time());
                    tv_bet_amount.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount());
                }
            }
        };
        rcDeposit.setLayoutManager(new LinearLayoutManager(mContext));
        rcDeposit.setAdapter(adapter);
    }

    private String getBetDetail(Afb2BetRecordBean.DataBean item) {
        String flg = item.getFlg();
        String game = item.getGame();
        String side = item.getSide();
        String firstStr;
        if (game.equals("OE") && side.equals("1")) {
            firstStr = "ODD";
        } else if (game.equals("OE") && side.equals("2")) {
            firstStr = "EVEN";
        } else if (game.equals("OU") && side.equals("1")) {
            firstStr = "OVER";
        } else if (game.equals("OU") && side.equals("2")) {
            firstStr = "UNDER";
        } else if (game.equals("1X2") && side.equals("0")) {
            firstStr = item.getHome() + "(DRAW)";
        } else if (game.equals("1X2") && side.equals("1")) {
            firstStr = item.getHome() + "(WIN)";
        } else if (game.equals("1X2") && side.equals("2")) {
            firstStr = item.getAway() + "(WIN)";
        } else if (game.equals("HDP") && side.equals("1")) {
            firstStr = item.getHome();
        } else if (game.equals("HDP") && side.equals("2")) {
            firstStr = item.getAway();
        } else {
            firstStr = game;
        }
        String content = getString(R.string.bet_detail) + firstStr + " " + item.getInfo() + "@" + item.getOdds();
        if (!TextUtils.isEmpty(flg)) {
            content += "(" + flg + ")";
        }
        return content;
    }

    public void onGetAfb2Data(List<Afb2BetRecordBean.DataBean> afb2List) {
        BaseRecyclerAdapter<Afb2BetRecordBean.DataBean> adapter = new BaseRecyclerAdapter<Afb2BetRecordBean.DataBean>(mContext, afb2List, R.layout.item_afb2_bet_record) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, Afb2BetRecordBean.DataBean item) {
                RelativeLayout rl_content = holder.getView(R.id.rl_content);
                TextView tv_no_data = holder.getView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = holder.getView(R.id.tv_no);
                    TextView tv_game = holder.getView(R.id.tv_game);
                    TextView tv_even = holder.getView(R.id.tv_even);
                    TextView tv_bet_detail = holder.getView(R.id.tv_bet_detail);
                    TextView tv_bet_time = holder.getView(R.id.tv_bet_time);
                    TextView tv_bet_amount = holder.getView(R.id.tv_bet_amount);
                    tv_no.setText(getString(R.string.no) + (position + 1));
                    tv_game.setText(getString(R.string.youxitype) + tvChoiceType.getText().toString());
                    tv_even.setText(item.getLeague_id() + "\n" + item.getHome() + " Vs " + item.getAway() + "\n" + item.getWorkdate());
                    tv_bet_detail.setText(getBetDetail(item));
                    tv_bet_time.setText(getString(R.string.xiazhushijian) + item.getLaster_update_time());
                    tv_bet_amount.setText(getString(R.string.xiazhujine) + ":" + item.getBet_amount());
                }
            }
        };
        rcDeposit.setLayoutManager(new LinearLayoutManager(mContext));
        rcDeposit.setAdapter(adapter);
    }

    public void onGetBetListData(List<ReportFormBean> beanList) {
        BaseRecyclerAdapter<ReportFormBean> adapter = new BaseRecyclerAdapter<ReportFormBean>(mContext, beanList, R.layout.item_bet_record) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, ReportFormBean item) {
                RelativeLayout rl_content = holder.getView(R.id.rl_content);
                TextView tv_no_data = holder.getView(R.id.tv_no_data);
                if (item.getBet_amount().equals("-1")) {
                    rl_content.setVisibility(View.GONE);
                    tv_no_data.setVisibility(View.VISIBLE);
                } else {
                    rl_content.setVisibility(View.VISIBLE);
                    tv_no_data.setVisibility(View.GONE);
                    TextView tv_no = holder.getView(R.id.tv_no);
                    TextView tv_leixing = holder.getView(R.id.tv_leixing);
                    TextView tv_youxi = holder.getView(R.id.tv_youxi);
                    TextView tv_wanfa = holder.getView(R.id.tv_wanfa);
                    TextView tv_haoma = holder.getView(R.id.tv_haoma);
                    TextView tv_peilv = holder.getView(R.id.tv_peilv);
                    TextView tv_youhui = holder.getView(R.id.tv_youhui);
                    TextView tv_xiazhujine = holder.getView(R.id.tv_xiazhujine);
                    TextView tv_qihao = holder.getView(R.id.tv_qihao);
                    TextView tv_xiazhushijian = holder.getView(R.id.tv_xiazhushijian);
                    tv_no.setText(getString(R.string.no) + (position + 1));
                    tv_leixing.setText(getString(R.string.youxitype) + item.getGame());
                    tv_youxi.setText(getString(R.string.youxi1) + item.getPool());
                    tv_wanfa.setText(getString(R.string.wanfa1) + item.getType());
                    tv_haoma.setText(getString(R.string.haoma1) + item.getNumber());
                    tv_peilv.setText(getString(R.string.peilv1) + item.getFactor());
                    tv_youhui.setText(getString(R.string.youhui1) + item.getDiscount());
                    tv_xiazhujine.setText(getString(R.string.xiazhujinee) + item.getBet_amount());
                    tv_qihao.setText(getString(R.string.qihao1) + item.getPeriod());
                    tv_xiazhushijian.setText(getString(R.string.xiazhushijian) + item.getBet_time());
                }
            }
        };
        rcDeposit.setLayoutManager(new LinearLayoutManager(mContext));
        rcDeposit.setAdapter(adapter);
    }

    @OnClick({R.id.tv_choice_type})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_choice_type:
                BaseContentListPopWindow popWindow = new BaseContentListPopWindow(mContext, tvChoiceType, tvChoiceType.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<ContentInfoBean> getContentData() {
                        return list;
                    }

                    @Override
                    public void onClickItem(int position, ContentInfoBean item) {
                        tvChoiceType.setText(item.getContent());
                        presenter.getBetListData(item);
                    }
                };
                popWindow.showPopupDownWindow();
                break;
        }
    }
}
