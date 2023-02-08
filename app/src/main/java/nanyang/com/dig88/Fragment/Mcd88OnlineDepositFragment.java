package nanyang.com.dig88.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Activity.DesipotWebActitvity;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.Presenter.Mcd88OnlineDepositPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2019/8/8.
 */

public class Mcd88OnlineDepositFragment extends BaseFragment<Mcd88OnlineDepositPresenter> {
    @BindView(R.id.tv_deposit_method)
    TextView tvDepositMethod;
    @BindView(R.id.tv_select_promotion)
    TextView tvSelectPromotion;
    @BindView(R.id.edt_amount)
    EditText edtAmount;
    String amount;
    List<ContentInfoBean> payMethodList;
    String depositMethod;
    BaseContentListPopWindow popPromotion;
    String promotionCode;
    private VipInfoBean info;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_mcd88_online_deposite;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setTitle(getString(R.string.Quick_deposit));
        createPresenter(new Mcd88OnlineDepositPresenter(this));
        info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
        payMethodList = new ArrayList<>();
        payMethodList.add(new ContentInfoBean("MBB", "mbb"));
        payMethodList.add(new ContentInfoBean("CIMB", "cimb"));
        payMethodList.add(new ContentInfoBean("HLB", "hlb"));
        payMethodList.add(new ContentInfoBean("PBB", "pbb"));
        presenter.getPromotion();
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

    @OnClick({R.id.btn_submit, R.id.tv_deposit_method, R.id.tv_select_promotion})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_deposit_method:
                BaseContentListPopWindow pop = new BaseContentListPopWindow(mContext, tvDepositMethod, tvDepositMethod.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<ContentInfoBean> getContentData() {
                        return payMethodList;
                    }

                    @Override
                    public void onClickItem(int position, ContentInfoBean item) {
                        tvDepositMethod.setText(item.getContent());
                        depositMethod = item.getContentId();
                    }
                };
                pop.showPopupDownWindow();
                break;
            case R.id.tv_select_promotion:
                if (popPromotion != null) {
                    popPromotion.showPopupDownWindow();
                }
                break;
            case R.id.btn_submit:
                submit();
                break;
        }
    }

    private void submit() {
        amount = edtAmount.getText().toString();
        String url = "http://pay.8188.ws/ibank/pay-go.php";
        String param = "payment=ok2pay" + "&banktype=" + depositMethod + "&amount=" + amount + "&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfo().getUser_id() +
                "&username=" + info.getUsername() + "&currency=" + getCurrency() + "&tel=" + info.getTel() + "&email=email" + info.getEmail() + "&language=" + presenter.getLang() +
                "&bankaccount=4170" + "&promo=" + promotionCode;
        Intent intent = new Intent(mContext, DesipotWebActitvity.class);
        intent.putExtra("url", url);
        intent.putExtra("parmas", param);
        startActivity(intent);
    }
}
