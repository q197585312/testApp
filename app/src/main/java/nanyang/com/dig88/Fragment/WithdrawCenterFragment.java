package nanyang.com.dig88.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.MainTabActivity;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Home.MenuWithdrawFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2015/12/21. (取款)
 */
public class WithdrawCenterFragment extends BaseFragment {
    @Bind(R.id.rc_content)
    RecyclerView rcContent;
    @Bind(R.id.ll_parent)
    LinearLayout llParent;
    @Bind(R.id.ll_title)
    View llTitle;
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    BaseRecyclerAdapter<GameMenuItem> adapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_withdraw_center;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        if (BuildConfig.FLAVOR.equals("funplay26") || BuildConfig.FLAVOR.equals("mcd88") || BuildConfig.FLAVOR.equals("onegold77") ||
                BuildConfig.FLAVOR.equals("afbcash") || BuildConfig.FLAVOR.equals("hjlh6688") || BuildConfig.FLAVOR.equals("xslot88") ||
                BuildConfig.FLAVOR.equals("mgold1")) {
            BaseActivity baseActivity = (BaseActivity) getActivity();
            if (baseActivity instanceof MainTabActivity) {
                llParent.setBackgroundResource(R.mipmap.base_bg);
                tvToolbarTitle.setText(getString(R.string.qukuanzx));
                llTitle.setVisibility(View.VISIBLE);
            }
        }
        getAct().setTitle(getString(R.string.qukuanzx));
        initAdapter();
    }

    private List<GameMenuItem> getContentData() {
        List<GameMenuItem> list = new ArrayList<>();
        if (BuildConfig.FLAVOR.equals("hjlh6688")) {
            list.add(new GameMenuItem(R.drawable.qukuancenter, getString(R.string.Online_Payment), Hjlh6688OnlineWithdrawFragment.class.getName()));
        }
        if (BuildConfig.FLAVOR.equals("mgold1")) {
            list.add(new GameMenuItem(R.drawable.qukuancenter, "Safepay", MenuWithdrawFragment.class.getName()));
            list.add(new GameMenuItem(R.drawable.qukuancenter, "LOCAL BANK", MenuWithdrawFragment.class.getName()));
        } else {
            list.add(new GameMenuItem(R.drawable.qukuancenter, getString(R.string.withdraw), MenuWithdrawFragment.class.getName()));
        }
        list.add(new GameMenuItem(R.drawable.xiazhujilu, getString(R.string.qukuanlist), WithdrawListFragment.class.getName()));
        if (BuildConfig.FLAVOR.equals("funplay26") || BuildConfig.FLAVOR.equals("mcd88") ||
                BuildConfig.FLAVOR.equals("onegold77") || (BuildConfig.FLAVOR.equals("afbcash") && !getCurrency().equals("IDR")) ||
                BuildConfig.FLAVOR.equals("xslot88")) {
            list.add(new GameMenuItem(R.drawable.qukuancenter, getString(R.string.Fixed_Bank), Funplay26BountBankFragment.class.getName()));
        }
        return list;
    }

    private void initAdapter() {
        adapter = new BaseRecyclerAdapter<GameMenuItem>(mContext, getContentData(), R.layout.item_common_list_content) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, GameMenuItem item) {
                ImageView imgLeft = holder.getView(R.id.img_left);
                imgLeft.setImageResource(item.getDrawableRes());
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(item.getTitle());
                View viewLine = holder.getView(R.id.view_line);
                if (getItemCount() - 1 == position) {
                    viewLine.setVisibility(View.GONE);
                } else {
                    viewLine.setVisibility(View.VISIBLE);
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<GameMenuItem>() {
            @Override
            public void onItemClick(View view, GameMenuItem item, int position) {
                Intent intent = new Intent(mContext, ActivityFragmentShow.class);
                String value = item.getValue();
                if (BuildConfig.FLAVOR.equals("mcd88") || BuildConfig.FLAVOR.equals("onegold77") ||
                        (BuildConfig.FLAVOR.equals("afbcash") && !getCurrency().equals("IDR")) || BuildConfig.FLAVOR.equals("xslot88")) {
                    if (value.equals(MenuWithdrawFragment.class.getName())) {
                        VipInfoBean info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
                        String nullStr = info.getBank_name();
                        if (BuildConfig.FLAVOR.equals("onegold77")) {
                            nullStr = info.getBank_acc_no2();
                        } else if (BuildConfig.FLAVOR.equals("afbcash")) {
                            nullStr = info.getAddress();
                        }
                        if (TextUtils.isEmpty(nullStr)) {
                            value = Funplay26BountBankFragment.class.getName();
                        }
                    }
                }
                intent.putExtra("type", value);
                if (BuildConfig.FLAVOR.equals("mgold1")) {
                    if (item.getTitle().equals("Safepay")) {
                        intent.putExtra("contentType", 1);
                    } else if (item.getTitle().equals("LOCAL BANK")) {
                        intent.putExtra("contentType", 2);
                    }
                }
                startActivity(intent);
            }
        });
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }
}
