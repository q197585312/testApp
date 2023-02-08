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
import nanyang.com.dig88.Entity.WithDrawListBean;
import nanyang.com.dig88.Fragment.Presenter.WithdrawPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/21. (取款列表)
 */
public class WithdrawListFragment extends BaseFragment<WithdrawPresenter> {
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<WithDrawListBean> adapter;
    //    WithDrawAdapter withDrawAdapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_withdraw_money_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getAct().setTitle(getString(R.string.qukuanlist));
        createPresenter(new WithdrawPresenter(this));
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        presenter.getWithdrawData(p);
    }

    private void initAdapter(List<WithDrawListBean> list) {
        adapter = new BaseRecyclerAdapter<WithDrawListBean>(mContext, list, R.layout.item_new_deposit_list) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, WithDrawListBean item) {
                TextView tvDate = holder.getView(R.id.tv_date);
                TextView tvStatus = holder.getView(R.id.tv_status);
                TextView tvAmount = holder.getView(R.id.tv_amount);
                TextView tvRemark = holder.getView(R.id.tv_remark);
                TextView tvRemarkContent = holder.getView(R.id.tv_remark_content);
                LinearLayout llRemark = holder.getView(R.id.ll_remark);
                LinearLayout llContent = holder.getView(R.id.ll_content);
                LinearLayout llKimsa1 = holder.getView(R.id.ll_kimsa1);
                TextView tvNoData = holder.getView(R.id.tv_no_data);
                if (item.getAmount().equals("-1")) {
                    llContent.setVisibility(View.GONE);
                    tvNoData.setVisibility(View.VISIBLE);
                } else {
                    llContent.setVisibility(View.VISIBLE);
                    tvNoData.setVisibility(View.GONE);
                    llKimsa1.setVisibility(View.GONE);
                    llContent.setVisibility(View.VISIBLE);
                    tvDate.setText(item.getDate_time());
                    String status = item.getStatus();
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
                    tvAmount.setText("(" + getCurrency() + ")" + item.getAmount());
                }
            }
        };
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }

    public void onGetWithdrawData(List<WithDrawListBean> list) {
        initAdapter(list);
    }
}
