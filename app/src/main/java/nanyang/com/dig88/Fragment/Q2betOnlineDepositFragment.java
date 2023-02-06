package nanyang.com.dig88.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.Activity.DesipotWebActitvity;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.BankInfoBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.Presenter.Q2betOnlineDepositPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.BaseListPopWindow;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.SharePreferenceUtil;

/**
 * Created by Administrator on 2019/8/8.
 */

public class Q2betOnlineDepositFragment extends BaseFragment<Q2betOnlineDepositPresenter> {
    @Bind(R.id.tv_deposit_way)
    TextView tvDepositWay;
    @Bind(R.id.tv_choice_bank)
    TextView tvChoiceBank;
    @Bind(R.id.edt_amount)
    EditText edtAmount;
    String bankId;
    @Bind(R.id.tv_select_promotion)
    TextView tvSelectPromotion;
    @Bind(R.id.tv_hint)
    TextView tvHint;
    @Bind(R.id.tv_k9th_hint)
    TextView tvK9thHint;
    String amount;
    BaseContentListPopWindow popPromotion;
    String promotionCode;
    private List<BankInfoBean> depositWayBankList;
    private VipInfoBean info;
    private List<ContentInfoBean> club988OnlineBankList;
    private int club988RequestType = 0;
    private String club988Payment;
    private String ip;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_q2bet_online_deposite;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new Q2betOnlineDepositPresenter(this));
        depositWayBankList = presenter.getDepositWayBank();
        info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
        if (BuildConfig.FLAVOR.equals("ttwin168")) {
            tvDepositWay.setText("TTPay2u");
        } else if (BuildConfig.FLAVOR.equals("club988")) {
            tvDepositWay.setText("TTPay2u");
            club988Payment = "ibank88";
        } else if (BuildConfig.FLAVOR.equals("afbcash")) {
            club988OnlineBankList = new ArrayList<>();
            club988OnlineBankList.add(new ContentInfoBean("Help2pay", "help2pay"));
            club988OnlineBankList.add(new ContentInfoBean("Paytrust", "paytrust"));
            tvDepositWay.setText(club988OnlineBankList.get(0).getContent());
            club988Payment = club988OnlineBankList.get(0).getContentId();
            tvChoiceBank.setVisibility(View.GONE);
            ip = SharePreferenceUtil.getString(mContext, "IP");
        } else if (BuildConfig.FLAVOR.equals("hjlh6688")) {
            club988OnlineBankList = new ArrayList<>();
            club988OnlineBankList.add(new ContentInfoBean("支付宝", "1"));
            club988OnlineBankList.add(new ContentInfoBean("云闪付", "9"));
            club988OnlineBankList.add(new ContentInfoBean("支付宝2", "11"));
            tvDepositWay.setText(club988OnlineBankList.get(0).getContent());
            club988Payment = club988OnlineBankList.get(0).getContentId();
            tvChoiceBank.setVisibility(View.GONE);
            ip = SharePreferenceUtil.getString(mContext, "IP");
        } else if (BuildConfig.FLAVOR.equals("mgold1")) {
            tvDepositWay.setVisibility(View.GONE);
            ip = SharePreferenceUtil.getString(mContext, "IP");
        } else if (BuildConfig.FLAVOR.equals("k9th")) {
            tvK9thHint.setVisibility(View.VISIBLE);
            tvDepositWay.setText("โอนผ่านมือถือ");
            tvChoiceBank.setText(depositWayBankList.get(0).getName());
            bankId = depositWayBankList.get(0).getId_mod_bank();
        } else {
            if (BuildConfig.FLAVOR.equals("q2bet")) {
                tvSelectPromotion.setVisibility(View.VISIBLE);
                presenter.getPromotion();
            }
            tvDepositWay.setText("Help2pay");
        }
        if (BuildConfig.FLAVOR.equals("win3888")) {
            edtAmount.setHint(getString(R.string.amount_hint));
            if (getLocalLanguage().equals("vn")) {
                tvHint.setVisibility(View.VISIBLE);
            }
        }
    }

    public void onGetPromotionData(final List<ContentInfoBean> list) {
        if (list != null && list.size() > 0) {
            popPromotion = new BaseContentListPopWindow(mContext, tvSelectPromotion, tvSelectPromotion.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                @Override
                public List<ContentInfoBean> getContentData() {
                    return list;
                }

                @Override
                public void onClickItem(int position, ContentInfoBean item) {
                    tvSelectPromotion.setText(item.getContent());
                    promotionCode = item.getContentId();
                }
            };
        }
    }

    @OnClick({R.id.tv_choice_bank, R.id.btn_submit, R.id.tv_deposit_way, R.id.tv_select_promotion})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_deposit_way:
                if (BuildConfig.FLAVOR.equals("afbcash") || BuildConfig.FLAVOR.equals("hjlh6688")) {
                    BaseContentListPopWindow bankNamePop = new BaseContentListPopWindow(mContext, tvDepositWay, tvDepositWay.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                        @Override
                        public List<ContentInfoBean> getContentData() {
                            return club988OnlineBankList;
                        }

                        @Override
                        public void onClickItem(int position, ContentInfoBean item) {
                            club988RequestType = position;
                            tvDepositWay.setText(item.getContent());
                            club988Payment = item.getContentId();
                            depositWayBankList = presenter.getDepositWayBank();
                            if (position == 0) {
                                depositWayBankList.remove(depositWayBankList.size() - 1);
                            }
                        }
                    };
                    bankNamePop.showPopupDownWindow();
                }
                break;
            case R.id.tv_choice_bank:
                BaseListPopWindow bankPop = new BaseListPopWindow(mContext, tvChoiceBank, tvChoiceBank.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<String> getContentData() {
                        List<String> bankName = new ArrayList<>();
                        for (int i = 0; i < depositWayBankList.size(); i++) {
                            bankName.add(depositWayBankList.get(i).getName());
                        }
                        return bankName;
                    }

                    @Override
                    public void onClickItem(int position, String item) {
                        tvChoiceBank.setText(item);
                        bankId = depositWayBankList.get(position).getId_mod_bank();
                    }
                };
                bankPop.showPopupDownWindow();
                break;
            case R.id.btn_submit:
                submit();
                break;
            case R.id.tv_select_promotion:
                if (popPromotion != null) {
                    popPromotion.showPopupDownWindow();
                }
                break;
        }
    }

    private void submit() {
        amount = edtAmount.getText().toString();
        String url = "http://pay.khmergaming.com/help2pay/pay-go.php";
        String param = "paymentType=1" + "&bank_id=" + bankId + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
                "&bankaccount=" + "&promo=" + promotionCode;
        if (BuildConfig.FLAVOR.equals("ttwin168")) {
            url = "http://pay.8188.ws/ibank/pay-go.php";
            param = "banktype=" + bankId + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                    "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
                    "&bankaccount=187";
        } else if (BuildConfig.FLAVOR.equals("club988")) {
            url = "http://pay.8188.ws/ibank/pay-go.php";
            param = "payment=" + club988Payment + "&banktype=" + bankId + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                    "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
                    "&bankaccount=4948";
        } else if (BuildConfig.FLAVOR.equals("afbcash")) {
            if (club988RequestType == 0) {
                url = "http://pay.khmergaming.com/help2pay/pay-go.php";
                param = "payment=" + club988Payment + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                        "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
                        "&bankaccount=2946" + "&ipaddress=" + ip;
            } else {
                url = "http://pay.khmergaming.com/trust88/pay-go.php";
                param = "payment=" + club988Payment + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                        "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
                        "&bankaccount=1484" + "&ipaddress=" + ip;
            }
//            url = "http://pay.khmergaming.com/trust88/pay-go.php";
//            param = "payment=" + club988Payment + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
//                    "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
//                    "&bankaccount=1484" + "&ipaddress=" + ip;
        } else if (BuildConfig.FLAVOR.equals("hjlh6688")) {
            url = "http://pay.8188.ws/p07/pay-go.php";
            param = "banktype=" + club988Payment + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                    "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
                    "&bankaccount=4634";
        } else if (BuildConfig.FLAVOR.equals("mgold1")) {
            url = "http://pay.khmergaming.com/help2pay/pay-go.php";
            param = "bank_id=" + bankId + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                    "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
                    "&bankaccount=3018" + "&hidden=save";
        } else if (BuildConfig.FLAVOR.equals("win3888")) {
            url = "http://pay.khmergaming.com/help2pay/pay-go.php";
            param = "bank_id=" + bankId + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                    "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
                    "&bankaccount=5328" + "&hidden=save";
        } else if (BuildConfig.FLAVOR.equals("k9th")) {
            url = "http://pay.8188.ws/amb/pay-go.php";
            param = "paymentType=7" + "&banktype=" + bankId + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                    "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=" + info.getEmail() + "&language=" + presenter.getLanguage() +
                    "&bankaccount=27";
        }
        Intent intent = new Intent(mContext, DesipotWebActitvity.class);
        intent.putExtra("url", url);
        intent.putExtra("parmas", param);
        startActivity(intent);
    }
}
