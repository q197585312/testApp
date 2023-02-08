package nanyang.com.dig88.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Activity.MainTabActivity;
import nanyang.com.dig88.Activity.MsgBoxActivity;
import nanyang.com.dig88.Entity.UserContentBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Home.Presenter.MenuUserPresenter;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2019/6/20.
 */

public class MenuUserFragment extends BaseFragment<MenuUserPresenter> {
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_cumulative)
    TextView tvCumulative;
    @BindView(R.id.tv_surplus)
    TextView tvSurplus;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_total_bonus_name)
    TextView tvTotalBonusName;
    @BindView(R.id.tv_balance_name)
    TextView tvBalanceName;
    @BindView(R.id.tv_last_bonus_name)
    TextView tvLastBonusName;
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    @BindView(R.id.sdv_user)
    SimpleDraweeView sdvUser;
    BaseRecyclerAdapter<UserContentBean> adapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_menu_user;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new MenuUserPresenter(this));
//        tvToolbarTitle.setText(getString(R.string.user));
        initUi();
        setBalance();
        initAdapter();
    }

    private void initUi() {
        tvBalanceName.setText(getString(R.string.Account_Balance));
        tvTotalBonusName.setText(getString(R.string.Total_Bonus));
        tvLastBonusName.setText(getString(R.string.Last_Bonus));
        Uri uri = Uri.parse("res://mipmap-xhdpi/" + R.mipmap.default_useravatar);
        sdvUser.setImageURI(uri);
        if (getCurrency().equals("IDR") || getLocalLanguage().equals("in")) {
            tvTotalBonusName.setVisibility(View.GONE);
            tvCumulative.setVisibility(View.GONE);
        }
    }

    @Override
    public void refreshMoney(String money) {
        double amount = Double.valueOf(money);
        String result = String.format("%.2f", amount);
        if (tvBalance != null) {
            tvBalance.setText(getCurrency() + " " + result);
        }
    }

    private void setBalance() {
        VipInfoBean vipInfoBean = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
        tvUsername.setText(vipInfoBean.getUsername());
        tvCumulative.setText(StringUtils.format2F(vipInfoBean.getTotal_refer()));
        tvSurplus.setText(StringUtils.format2F(vipInfoBean.getLaster_refer()));
        tvBalance.setText(getCurrency() + " " + StringUtils.format2F(getUserInfo().getMoneyBalance().getBalance()));
    }

    private void initAdapter() {
        adapter = new BaseRecyclerAdapter<UserContentBean>(mContext, presenter.getContentData(), R.layout.item_user_content) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, UserContentBean item) {
                TextView tvName = holder.getView(R.id.tv_name);
                tvName.setText(item.getGameName());
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<UserContentBean>() {
            @Override
            public void onItemClick(View view, UserContentBean item, int position) {
                String className = item.getClassName();
                if (item.getGameName().equals(getString(R.string.yuyanqiehuan)) && TextUtils.isEmpty(className)) {
                    startActivity(new Intent(mContext, SwitchLanguageActivity.class));
                } else if (item.getGameName().equals(getString(R.string.exit_login)) && TextUtils.isEmpty(className)) {
                    presenter.logout();
                } else if (item.getGameName().equals(getString(R.string.notification)) && TextUtils.isEmpty(className)) {
                    startActivity(new Intent(mContext, MsgBoxActivity.class));
                } else {
                    Intent intent = new Intent(mContext, ActivityFragmentShow.class);
                    intent.putExtra("type", className);
                    startActivity(intent);
                }
            }
        });
        rcContent.setLayoutManager(new LinearLayoutManager(mContext));
        rcContent.setAdapter(adapter);
    }

    public void onLogout() {
        Intent intent = new Intent(mContext, MainTabActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
