package nanyang.com.dig88.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.DepositListBean;
import nanyang.com.dig88.Fragment.Presenter.DepositListPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/21. (存款列表 )
 */
public class DepositListFragment extends BaseFragment<DepositListPresenter> {
    @Bind(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<DepositListBean> adapter;
//    DepositListAdapter depositListAdapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_deposit_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new DepositListPresenter(this));
        getAct().setTitle(getString(R.string.cunkuanlist));
        getAct().setleftViewEnable(true);
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        presenter.getDepositListData(p);
    }

    private void initAdapter(List<DepositListBean> list) {
        adapter = new BaseRecyclerAdapter<DepositListBean>(mContext, list, R.layout.item_new_deposit_list) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, DepositListBean item) {
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
                    if (BuildConfig.FLAVOR.equals("kimsa1")) {
                        llContent.setVisibility(View.GONE);
                        llKimsa1.setVisibility(View.VISIBLE);
                        TextView tvDateTime = holder.getView(R.id.tv_date_time);
                        TextView tvStatus1 = holder.getView(R.id.tv_status1);
                        TextView tvAmount1 = holder.getView(R.id.tv_amount1);
                        TextView tvOptions = holder.getView(R.id.tv_options);
                        tvDateTime.setText(item.getDate_time());
                        tvAmount1.setText(item.getAmount());
                        String status = item.getStatus();
                        if (status.equals("1")) {
                            tvStatus1.setTextColor(Color.parseColor("#2DCB67"));
                        } else {
                            tvStatus1.setTextColor(Color.parseColor("#FC5885"));
                        }
                        switch (status) {
                            case "0":
                                tvStatus1.setText(getString(R.string.newshengqing));
                                break;
                            case "1":
                                tvStatus1.setText(getString(R.string.houtaichuli));
                                break;
                            case "2":
                                tvStatus1.setText(getString(R.string.chulizhong));
                                break;
                            case "3":
                                tvStatus1.setText(getString(R.string.caoshi));
                                break;
                            case "4":
                                tvStatus1.setText(getString(R.string.gaunliyuan));
                                break;
                            case "6":
                                tvStatus1.setText(getString(R.string.qitaleix));
                                break;
                        }
                        try {
                            String s = new String(item.getTrs_id().getBytes("ISO-8859-1"));
                            tvOptions.setText(s);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        llKimsa1.setVisibility(View.GONE);
                        llContent.setVisibility(View.VISIBLE);
                        tvDate.setText(item.getDate_time());
                        String status = item.getStatus();
                        if (BuildConfig.FLAVOR.equals("gasia88")) {
                            llRemark.setVisibility(View.VISIBLE);
                            tvRemark.setText(getString(R.string.remak) + " : ");
                            tvRemarkContent.setText(item.getTrs_id());
                        }
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
            }
        };
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }

    public void onGetDepositData(List<DepositListBean> list) {
        initAdapter(list);
    }

}
