package nanyang.com.dig88.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Fragment.DepositListFragment;
import nanyang.com.dig88.Home.Presenter.MenuDepositPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.BaseListPopWindow;
import nanyang.com.dig88.Util.ChoicePicHelper;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.SharePreferenceUtil;

/**
 * Created by Administrator on 2019/6/20.
 */

public class MenuDepositFragment extends BaseFragment<MenuDepositPresenter> {
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.tv_toolbar_right)
    TextView tvToolbarRight;
    @Bind(R.id.img_toolbar_left)
    ImageView imgToolbarLeft;
    @Bind(R.id.tv_choice_bank)
    TextView tvChoiceBank;
    @Bind(R.id.edt_amount)
    EditText edtAmount;
    @Bind(R.id.sdv_choose_pic)
    SimpleDraweeView sdvChoosePic;
    @Bind(R.id.tv_no_choose)
    TextView tvNoChoose;
    @Bind(R.id.tv_from_bank)
    TextView tvFromBank;
    @Bind(R.id.edt_from_bank_acc_name)
    EditText edtFromBankAccName;
    @Bind(R.id.edt_frome_bank_num)
    EditText edtFromeBankNum;
    @Bind(R.id.tv_to_bank)
    TextView tvToBank;
    @Bind(R.id.edt_to_bank_acc_name)
    EditText edtToBankAccName;
    @Bind(R.id.edt_to_bank_num)
    EditText edtToBankNum;
    @Bind(R.id.edt_remark)
    EditText edtRemark;
    @Bind(R.id.ll_other)
    LinearLayout llOther;
    @Bind(R.id.ll_normal)
    LinearLayout llNormal;
    @Bind(R.id.ll_afb)
    LinearLayout ll_afb;
    @Bind(R.id.rc_bank)
    RecyclerView rc_bank;
    @Bind(R.id.tv_bank_name)
    TextView tv_bank_name;
    @Bind(R.id.tv_bank_num)
    TextView tv_bank_num;
    BaseListPopWindow popBank;
    BaseListPopWindow popFromBank;
    BaseListPopWindow popToBank;
    String bankId;
    int minDeposit = 1;
    int maxDeposit = 100000;
    String amount;
    private String ip;
    VipInfoBean vipInfoBean;
    boolean isFromBankCanClick = false;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_menu_deposit;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new MenuDepositPresenter(this));
        ip = SharePreferenceUtil.getString(mContext, "IP");
        tvToolbarTitle.setText(getString(R.string.deposit));
        tvToolbarRight.setBackgroundResource(R.mipmap.deposit_list);
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActivityFragmentShow.class);
                intent.putExtra("type", DepositListFragment.class.getName());
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
//        BaseActivity activity = (BaseActivity) getActivity();
//        activity.setUploadPicFragment(this);
//        requestPermission();
//        GenericDraweeHierarchy hierarchy = sdvChoosePic.getHierarchy();
//        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        initUiData();
    }

    private void initUiData() {
        presenter.getDepositBank();
        if (getCurrency().equals("IDR")) {
            llOther.setVisibility(View.VISIBLE);
            llNormal.setVisibility(View.GONE);
            vipInfoBean = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
            if (getCurrency().equals("USD")) {
                tvFromBank.setText(vipInfoBean.getBank_name());
                edtFromBankAccName.setText(vipInfoBean.getBank_acc_name2());
                edtFromeBankNum.setText(vipInfoBean.getBank_acc_no2());
                String BankAccName = edtFromBankAccName.getText().toString();
                if (TextUtils.isEmpty(BankAccName) || BankAccName.equals("default")) {
                    edtFromBankAccName.setEnabled(true);
                } else {
                    edtFromBankAccName.setEnabled(false);
                }
                isFromBankCanClick = true;
                edtFromeBankNum.setEnabled(true);
            } else {
                isFromBankCanClick = false;
                tvFromBank.setText(vipInfoBean.getBank_name());
                edtFromBankAccName.setText(vipInfoBean.getBank_acc_name2());
                edtFromeBankNum.setText(vipInfoBean.getBank_acc_no2());
                edtFromBankAccName.setEnabled(false);
                edtFromeBankNum.setEnabled(false);
            }
            edtToBankAccName.setEnabled(false);
            edtToBankNum.setEnabled(false);
        } else {
            llOther.setVisibility(View.GONE);
            llNormal.setVisibility(View.VISIBLE);
        }
    }

    //    @Override
//    public void showChoosePic(Uri uri) {
//        tvNoChoose.setVisibility(View.INVISIBLE);
//        sdvChoosePic.setImageURI(uri);
//    }

    private int clickPosition;
    BaseRecyclerAdapter<Integer> adapter;

    private void setBankPic(final List<BankAccountDetailBean> beanList) {
        if (getCurrency().equals("USD")) {
            BankAccountDetailBean bean = beanList.get(0);
            bankId = bean.getId_mod_bank_account();
            tv_bank_name.setText(getString(R.string.zhucezhanghu) + ":" + bean.getAccount());
            tv_bank_num.setText(getString(R.string.zhucezhanghao) + ":" + bean.getNo());
            llNormal.setVisibility(View.GONE);
            ll_afb.setVisibility(View.VISIBLE);
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
                        layoutParams.height =UIUtil.dip2px(mContext, 45);
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
                    bankId = bankAccountDetailBean.getId_mod_bank_account();
                    String maxDepositStr = bankAccountDetailBean.getMax_deposit();
                    String minDepositStr = bankAccountDetailBean.getMin_deposit();
                    if (!TextUtils.isEmpty(minDepositStr)) {
                        minDeposit = Integer.parseInt(minDepositStr);
                    }
                    if (!TextUtils.isEmpty(maxDepositStr)) {
                        maxDeposit = Integer.parseInt(maxDepositStr);
                    }
                    tv_bank_name.setText(getString(R.string.zhucezhanghu) + ":" + bankAccountDetailBean.getAccount());
                    tv_bank_num.setText(getString(R.string.zhucezhanghao) + ":" + bankAccountDetailBean.getNo());
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

    public void onGetDepositBank(final List<BankAccountDetailBean> beanList) {
        setBankPic(beanList);
        final List<String> bankNameList = new ArrayList<>();
        for (int i = 0; i < beanList.size(); i++) {
            BankAccountDetailBean bankAccountDetailBean = beanList.get(i);
            String bankName = bankAccountDetailBean.getBank_name();
            String account = bankAccountDetailBean.getAccount();
            String no = bankAccountDetailBean.getNo();
            if (getCurrency().equals("IDR")) {
                bankNameList.add(bankName);
            } else {
                bankNameList.add(bankName + "-" + account + "-" + no);
            }
        }
        if (getCurrency().equals("IDR")) {
            if (getCurrency().equals("USD")) {
                if (TextUtils.isEmpty(vipInfoBean.getAddress())) {
                    tvFromBank.setText(beanList.get(0).getBank_name());
                    edtFromBankAccName.setText("");
                    edtFromeBankNum.setText("");
                } else {
                    edtFromBankAccName.setText(vipInfoBean.getBank_acc_name2());
                    edtFromeBankNum.setText(vipInfoBean.getBank_acc_no2());
                    tvFromBank.setText(vipInfoBean.getAddress());
                }
            }
            tvToBank.setText(beanList.get(0).getBank_name());
            edtToBankAccName.setText(beanList.get(0).getAccount());
            edtToBankNum.setText(beanList.get(0).getNo());
            bankId = beanList.get(0).getId_mod_bank_account();
            popFromBank = new BaseListPopWindow(mContext, tvFromBank, tvFromBank.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                @Override
                public List<String> getContentData() {
                    return bankNameList;
                }

                @Override
                public void onClickItem(int position, String item) {
                    tvFromBank.setText(item);
                }
            };
            popToBank = new BaseListPopWindow(mContext, tvToBank, tvToBank.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                @Override
                public List<String> getContentData() {
                    return bankNameList;
                }

                @Override
                public void onClickItem(int position, String item) {
                    tvToBank.setText(item);
                    BankAccountDetailBean bankAccountDetailBean = beanList.get(position);
                    bankId = bankAccountDetailBean.getId_mod_bank_account();
                    String maxDepositStr = bankAccountDetailBean.getMax_deposit();
                    String minDepositStr = bankAccountDetailBean.getMin_deposit();
                    if (!TextUtils.isEmpty(minDepositStr)) {
                        minDeposit = Integer.parseInt(minDepositStr);
                    }
                    if (!TextUtils.isEmpty(maxDepositStr)) {
                        maxDeposit = Integer.parseInt(maxDepositStr);
                    }
                    String account = bankAccountDetailBean.getAccount();
                    String no = bankAccountDetailBean.getNo();
                    edtToBankAccName.setText(account);
                    edtToBankNum.setText(no);
                }
            };
        } else {
            popBank = new BaseListPopWindow(mContext, tvChoiceBank, tvChoiceBank.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                @Override
                public List<String> getContentData() {
                    return bankNameList;
                }

                @Override
                public void onClickItem(int position, String item) {
                    tvChoiceBank.setText(item);
                    BankAccountDetailBean bankAccountDetailBean = beanList.get(position);
                    bankId = bankAccountDetailBean.getId_mod_bank_account();
                    String maxDepositStr = bankAccountDetailBean.getMax_deposit();
                    String minDepositStr = bankAccountDetailBean.getMin_deposit();
                    if (!TextUtils.isEmpty(minDepositStr)) {
                        minDeposit = Integer.parseInt(minDepositStr);
                    }
                    if (!TextUtils.isEmpty(maxDepositStr)) {
                        maxDeposit = Integer.parseInt(maxDepositStr);
                    }
                }
            };
        }
    }

    public void verification() {
        if (TextUtils.isEmpty(bankId)) {
            ToastUtils.showShort(getString(R.string.qingxuanyh));
            return;
        }
        amount = edtAmount.getText().toString().trim();
        if (TextUtils.isEmpty(amount)) {
            ToastUtils.showShort(getString(R.string.shurujine));
            return;
        } else if (Integer.parseInt(amount) < minDeposit) {
            ToastUtils.showShort(getString(R.string.cunkuanxiao) + minDeposit);
            return;
        } else if (Integer.parseInt(amount) > maxDeposit) {
            ToastUtils.showShort(getString(R.string.cunkuanda) + maxDeposit);
            return;
        }
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        p.put("bankaccount", bankId);
        p.put("amount", amount);
        p.put("ip", ip + "-android/app");
        if (getCurrency().equals("IDR")) {
            p.put("accountname", edtToBankAccName.getText().toString());
            p.put("accountno", edtToBankNum.getText().toString());
            p.put("bank_name", tvFromBank.getText().toString());
            p.put("bankaccname_from", edtFromBankAccName.getText().toString());
            p.put("bankaccber_from", edtFromeBankNum.getText().toString());
            p.put("remark", edtRemark.getText().toString());
        }
        if (!TextUtils.isEmpty(getReceipt())) {
            p.put("data_img", getReceipt());
        }
        presenter.goDeposit(p);
    }

    public void clearAmount() {
        edtAmount.setText("");
    }

    public void onGetDepositResult(String msg) {
        ToastUtils.showShort(msg);
    }

    @OnClick({R.id.btn_submit, R.id.tv_choice_bank, R.id.btn_upload, R.id.tv_from_bank, R.id.tv_to_bank})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_choice_bank:
                popBank.showPopupDownWindow();
                break;
            case R.id.btn_submit:
                verification();
                break;
            case R.id.btn_upload:
                ChoicePicHelper.goChoicePic(getActivity());
                break;
            case R.id.tv_from_bank:
                if (popFromBank != null && isFromBankCanClick) {
                    popFromBank.showPopupDownWindow();
                }
                break;
            case R.id.tv_to_bank:
                if (popToBank != null) {
                    popToBank.showPopupDownWindow();
                }
                break;
        }
    }
}
