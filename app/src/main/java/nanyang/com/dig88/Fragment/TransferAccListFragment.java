package nanyang.com.dig88.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import nanyang.com.dig88.Entity.DepositListBean;
import nanyang.com.dig88.Entity.TransferAccPersonBean;
import nanyang.com.dig88.Fragment.Presenter.TransferAccListPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2017/1/19.
 */

public class TransferAccListFragment extends BaseFragment<TransferAccListPresenter> {
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<TransferAccPersonBean.DataBean> adapter;
// listView.setAdapter(new TransferAccPersonAdapter(mContext, list, masterAcc, saba, sb, scr));

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_transferacclist;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new TransferAccListPresenter(this));
        getAct().setleftViewEnable(true);
        getAct().setTitle(getString(R.string.transferacc_list));
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        presenter.getTransferListData(p);
    }

    public void onGetTransferListData(List<TransferAccPersonBean.DataBean> list) {
        adapter = new BaseRecyclerAdapter<TransferAccPersonBean.DataBean>(mContext, list, R.layout.item_new_transfer_list) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, TransferAccPersonBean.DataBean item) {
                LinearLayout llContent = holder.getView(R.id.ll_content);
                TextView tvNoData = holder.getView(R.id.tv_no_data);
                if (item.getInsert_time().equals("-1")) {
                    llContent.setVisibility(View.GONE);
                    tvNoData.setVisibility(View.VISIBLE);
                } else {
                    llContent.setVisibility(View.VISIBLE);
                    tvNoData.setVisibility(View.GONE);
                    TextView tvDate = holder.getView(R.id.tv_date);
                    TextView tvFromName = holder.getView(R.id.tv_from_name);
                    TextView tvFromAmount = holder.getView(R.id.tv_from_amount);
                    TextView tvStatus = holder.getView(R.id.tv_status);
                    TextView tvToName = holder.getView(R.id.tv_to_name);
                    TextView tvToAmount = holder.getView(R.id.tv_to_amount);
                    tvDate.setText(item.getInsert_time());
                    String status = item.getStatus();
                    String statusFrom = item.getFrom_gameid();
                    String statusTo = item.getTo_gameid();
                    if (statusFrom.equals("1")) {
                        tvFromName.setText(getString(R.string.list1_master_acc));
                    } else if (statusFrom.equals("2")) {
                        tvFromName.setText(getString(R.string.list2_saba));
                    } else if (statusFrom.equals("5")) {
                        tvFromName.setText("918KISS");
                    } else if (statusFrom.equals("8")) {
                        tvFromName.setText(getString(R.string.list5_klaspoker));
                    } else if (statusFrom.equals("4")) {
                        tvFromName.setText(getString(R.string.list4_pt));
                    } else if (statusFrom.equals("16")) {
                        tvFromName.setText("RTG " + getString(R.string.game));
                    } else if (statusFrom.equals("6")) {
                        tvFromName.setText(getString(R.string.indonesia_lottery));
                    } else if (statusFrom.equals("11")) {
                        tvFromName.setText(getString(R.string.joker_game_slots));
                    } else if (statusFrom.equals("17")) {
                        tvFromName.setText(getString(R.string.new_keno));
                    } else if (statusFrom.equals("12")) {
                        tvFromName.setText(getString(R.string.ig_lottery));
                    } else if (statusFrom.equals("24")) {
                        tvFromName.setText(getString(R.string.ongdo_poker));
                    } else if (statusFrom.equals("28")) {
                        tvFromName.setText(getString(R.string.mega_game_slots));
                    } else if (statusFrom.equals("27")) {
                        tvFromName.setText(getString(R.string.jdb_game_slots));
                    } else if (statusFrom.equals("25")) {
                        tvFromName.setText(getString(R.string.xe88_game_slots));
                    } else if (statusFrom.equals("20")) {
                        tvFromName.setText(getString(R.string.we1poker));
                    } else if (statusFrom.equals("1036")) {
                        tvFromName.setText("Kai Yuan");
                    }
                    if (statusTo.equals("1")) {
                        tvToName.setText(getString(R.string.list1_master_acc));
                    } else if (statusTo.equals("2")) {
                        tvToName.setText(getString(R.string.list2_saba));
                    } else if (statusTo.equals("5")) {
                        tvToName.setText("918KISS");
                    } else if (statusTo.equals("8")) {
                        tvToName.setText(getString(R.string.list5_klaspoker));
                    } else if (statusTo.equals("4")) {
                        tvToName.setText(getString(R.string.list4_pt));
                    } else if (statusTo.equals("16")) {
                        tvToName.setText("RTG " + getString(R.string.game));
                    } else if (statusTo.equals("6")) {
                        tvToName.setText(getString(R.string.indonesia_lottery));
                    } else if (statusTo.equals("11")) {
                        tvToName.setText(getString(R.string.joker_game_slots));
                    } else if (statusTo.equals("17")) {
                        tvToName.setText(getString(R.string.new_keno));
                    } else if (statusTo.equals("12")) {
                        tvToName.setText(getString(R.string.ig_lottery));
                    } else if (statusTo.equals("24")) {
                        tvToName.setText(getString(R.string.ongdo_poker));
                    } else if (statusTo.equals("28")) {
                        tvToName.setText(getString(R.string.mega_game_slots));
                    } else if (statusTo.equals("27")) {
                        tvToName.setText(getString(R.string.jdb_game_slots));
                    } else if (statusTo.equals("25")) {
                        tvToName.setText(getString(R.string.xe88_game_slots));
                    } else if (statusTo.equals("20")) {
                        tvToName.setText(getString(R.string.we1poker));
                    } else if (statusTo.equals("1036")) {
                        tvToName.setText("Kai Yuan");
                    }
                    String amount;
                    if (item.getAmount().startsWith("-")) {
                        amount = item.getAmount().replace("-", "");
                    } else {
                        amount = item.getAmount();
                    }
                    tvFromAmount.setText("-" + amount + " " + getCurrency());
                    tvToAmount.setText("+" + amount + " " + getCurrency());
                    if (status.equals("1")) {
                        tvStatus.setTextColor(Color.parseColor("#2DCB67"));
                    } else {
                        tvStatus.setTextColor(Color.parseColor("#FC5885"));
                    }
                    switch (status) {
                        case "0":
                            tvStatus.setText(getString(R.string.newshengqing));
                            break;
                        case "1":
                            tvStatus.setText(getString(R.string.houtaichuli));
                            break;
                        case "2":
                            tvStatus.setText(getString(R.string.chulizhong));
                            break;
                        case "3":
                            tvStatus.setText(getString(R.string.caoshi));
                            break;
                        case "4":
                            tvStatus.setText(getString(R.string.gaunliyuan));
                            break;
                        case "6":
                            tvStatus.setText(getString(R.string.qitaleix));
                            break;
                    }
                }
            }
        };
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }
}
