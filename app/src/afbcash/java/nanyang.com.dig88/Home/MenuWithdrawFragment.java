package nanyang.com.dig88.Home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Fragment.WithdrawListFragment;
import nanyang.com.dig88.Home.Presenter.MenuWithdrawPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2019/6/20.
 */

public class MenuWithdrawFragment extends BaseFragment<MenuWithdrawPresenter> {
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_toolbar_right)
    TextView tvToolbarRight;
    @BindView(R.id.img_toolbar_left)
    ImageView imgToolbarLeft;
    @BindView(R.id.edt_amount)
    EditText edtAmount;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.rc_bank)
    RecyclerView rc_bank;
    @BindView(R.id.ll_afb)
    LinearLayout ll_afb;
    private String ip;
    String amount;
    int minWithdraw = 1;
    int maxWithdraw = 10000;
    String password;
    @BindView(R.id.tv_bank_name)
    TextView tv_bank_name;
    @BindView(R.id.edt_bank_num)
    EditText edt_bank_num;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_menu_withdraw;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new MenuWithdrawPresenter(this));
        tvToolbarTitle.setText(getString(R.string.withdraw));
        tvToolbarRight.setBackgroundResource(R.mipmap.deposit_list);
        ip = SharePreferenceUtil.getString(mContext, "IP");
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActivityFragmentShow.class);
                intent.putExtra("type", WithdrawListFragment.class.getName());
                startActivity(intent);
            }
        });
        if (isNeedShowBack) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imgToolbarLeft.getLayoutParams();
            params.width = UIUtil.dip2px(mContext, 25);
            params.height = UIUtil.dip2px(mContext, 25);
            imgToolbarLeft.setLayoutParams(params);
            imgToolbarLeft.setImageResource(R.mipmap.back_new);
            imgToolbarLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });
        }
        if (getCurrency().equals("IDR") || getCurrency().equals("USD")) {
            edtPassword.setVisibility(View.GONE);
            if (getCurrency().equals("USD")) {
                VipInfoBean info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
                tv_bank_name.setText(info.getBank_acc_name2());
                edt_bank_num.setText(info.getBank_acc_no2());
                ll_afb.setVisibility(View.VISIBLE);
            }
        }
        presenter.getWithdrawBank();
    }

    String bankName;

    private void verification() {
        amount = edtAmount.getText().toString().trim();
        if (TextUtils.isEmpty(amount)) {
            ToastUtils.showShort(getString(R.string.shurujine));
            return;
        } else if (Integer.parseInt(amount) < minWithdraw) {
            ToastUtils.showShort(getString(R.string.qukuanda) + minWithdraw);
            return;
        } else if (Integer.parseInt(amount) > maxWithdraw) {
            ToastUtils.showShort(getString(R.string.qukuanxiao) + maxWithdraw);
            return;
        }
        String balance = getUserInfo().getMoneyBalance().getBalance();
//        if (Double.parseDouble(balance) < Double.parseDouble(amount)) {
//            ToastUtils.showShort(getString(R.string.Balance_not_enough));
//            return;
//        }
        if (!getCurrency().equals("IDR") && !getCurrency().equals("USD")) {
            password = edtPassword.getText().toString();
            if (TextUtils.isEmpty(password)) {
                ToastUtils.showShort(getString(R.string.password_cannot_null));
                return;
            }
        }
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        p.put("amount", amount);
        if (!getCurrency().equals("IDR") && !getCurrency().equals("USD")) {
            p.put("password", password);
        }
        if (getCurrency().equals("USD")) {
            p.put("bankaccount", id_mod_bank_account);
            p.put("accountname", tv_bank_name.getText().toString().trim());
            p.put("accountno", edt_bank_num.getText().toString().trim());
        }
        presenter.goWithdraw(p);
    }

    private int clickPosition;
    String id_mod_bank_account;
    BaseRecyclerAdapter<Integer> adapter;

    private void setBankPic(final List<BankAccountDetailBean> beanList) {
        if (getCurrency().equals("USD")) {
            BankAccountDetailBean bean = beanList.get(0);
            bankName = bean.getBank_name();
            id_mod_bank_account = bean.getId_mod_bank_account();
            List<Integer> picList = new ArrayList<>();
            for (int i = 0; i < beanList.size(); i++) {
                String bank_name = beanList.get(i).getBank_name();
                if (bank_name.equals("ABA BANK")) {
                    picList.add(R.mipmap.aba);
                } else if (bank_name.equals("WING")) {
                    picList.add(R.mipmap.wing);
                } else if (bank_name.equals("ACLEDA")) {
                    picList.add(R.mipmap.ac);
                } else if (bank_name.equals("True Money")) {
                    picList.add(R.mipmap.true_money);
                } else if (bank_name.equals("Wing Weiluy")) {
                    picList.add(R.mipmap.wing_1);
                }
            }
            adapter = new BaseRecyclerAdapter<Integer>(mContext, picList, R.layout.item_bank_img) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, Integer item) {
                    ImageView imageView = holder.getImageView(R.id.img_content);
                    if (clickPosition == position) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                        layoutParams.width = UIUtil.dip2px(mContext, 45);
                        layoutParams.height = UIUtil.dip2px(mContext, 45);
                        imageView.setLayoutParams(layoutParams);
                    } else {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                        layoutParams.width = UIUtil.dip2px(mContext, 40);
                        layoutParams.height = UIUtil.dip2px(mContext, 40);
                        imageView.setLayoutParams(layoutParams);
                    }
                    imageView.setImageResource(item);
                }
            };
            adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<Integer>() {
                @Override
                public void onItemClick(View view, Integer item, int position) {
                    BankAccountDetailBean bankAccountDetailBean = beanList.get(position);
                    String maxWithdrawStr = bankAccountDetailBean.getMax_deposit();
                    String minWithdrawStr = bankAccountDetailBean.getMin_deposit();
                    if (!TextUtils.isEmpty(minWithdrawStr)) {
                        minWithdraw = Integer.parseInt(minWithdrawStr);
                    }
                    if (!TextUtils.isEmpty(maxWithdrawStr)) {
                        maxWithdraw = Integer.parseInt(maxWithdrawStr);
                    }
                    bankName = bankAccountDetailBean.getBank_name();
                    id_mod_bank_account = bankAccountDetailBean.getId_mod_bank_account();
                    clickPosition = position;
                    adapter.notifyDataSetChanged();
                }
            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rc_bank.setLayoutManager(linearLayoutManager);
            rc_bank.setAdapter(adapter);
        }
    }

    public void onGetWithdrawBank(List<BankAccountDetailBean> beanList) {
        setBankPic(beanList);
        BankAccountDetailBean bankAccountDetailBean = beanList.get(0);
        String maxWithdrawStr = bankAccountDetailBean.getMax_deposit();
        String minWithdrawStr = bankAccountDetailBean.getMin_deposit();
        if (!TextUtils.isEmpty(minWithdrawStr)) {
            minWithdraw = Integer.parseInt(minWithdrawStr);
        }
        if (!TextUtils.isEmpty(maxWithdrawStr)) {
            maxWithdraw = Integer.parseInt(maxWithdrawStr);
        }
    }

    public void onGetWithdrawResult(String msg) {
        ToastUtils.showShort(msg);
    }

    public void clearAmount() {
        edtAmount.setText("");
    }

    @OnClick({R.id.btn_submit})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                verification();
                break;
        }
    }

}
